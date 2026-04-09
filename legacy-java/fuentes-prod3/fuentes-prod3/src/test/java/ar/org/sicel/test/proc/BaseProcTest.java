package ar.org.sicel.test.proc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.hibernate.Session;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.IProcesadorXML;
import ar.org.sicel.proc.services.locator.ServiceLocator;
import ar.org.sicel.proc.v1.lote.Lote;

public abstract class BaseProcTest extends TestCase {

    /**
     * Esto SIEMPRE borra el anterior, si es que existe. Esta asi para poder
     * ejecutarlo con el boton derecho de mouse desde eclipse, y al no ponerlo
     * como testAlgo*().. no jode si queremos testear varias cosas a la ves.
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // Las subclases tienen que implementarlo asi:
        // BaseProcTest t = new _ClaseDeTes_r();
        // t.desacerTodos();
    }

    private void procesarLote(String path) throws Exception {
        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
        File f = new File(path + ".xml");
        //String loteXML = IOUtils.toString(new FileInputStream(f));
        StandaloneHibernateStrategy.getInstance().openNewSession();
        Lote lote = null;

        try {

            lote = (Lote) Lote.unmarshal(new FileReader(f));

        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
   //SE TOCO ACA SI SE QUIERE VOLVER TODO PARA ATRAS HAY QUE SACAR EL PARAMETRO USUARIO
        Usuario usuario = new Usuario();
        usuario.setUsername("usuario");
        ProcProces pr = new ProcProces();
        Lote resultado = proc.procesar(pr,lote, null,new LinkedList(),usuario);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        File salida = new File(path + "_resultado.xml");
        FileWriter out = new FileWriter(salida);
        resultado.marshal(out);
        out.flush();
        out.close();
    }

    /**
     * Este es el test. Las subclases no tienen que implementar nada de esto,
     * aunque tienen que definirlo e invocarlo, porque sino Eclipse no lo agrega
     * a ejecutar como.. (ver el ejemplo en Reproduccion.java)
     * 
     * @throws Exception
     */
    public void testProcesamiento() throws Exception {
        String[] lotes = this.getPathArhivosAProcesar();
        for (int i = 0; i < lotes.length; i++) {
            procesarLote(lotes[i]);
            StandaloneHibernateStrategy.getInstance().openNewSession();
            finParcial(lotes[i], HibernateFactory.getSession());
            StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        }
        StandaloneHibernateStrategy.getInstance().openNewSession();
        finProceso(HibernateFactory.getSession());
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        if (deshacerDespuesDeTest())
            deshacerTodos();
    }

    protected void deshacerTodos() throws Exception {
        String[] lotes = this.getPathArhivosAProcesar();
        for (int i = lotes.length - 1; i >= 0; i--) {
            deshacer(lotes[i]);
        }
        System.out
                .println("Todos los efectos de los lotes han sido sacados de la base");
    }

    private void deshacer(String path) throws Exception {
        StandaloneHibernateStrategy.getInstance().openNewSession();
        File f = new File(path + ".xml");
        FileInputStream fis = new FileInputStream(f);
        String loteXML = IOUtils.toString(fis);
        Lote lote = (Lote) Lote.unmarshal(new StringReader(loteXML));
        long numero = lote.getIDLoteInte();
        long inte = lote.getInformante();
        try {
            ProcProces.elimProcesoYEventos(inte, numero);
        } catch (ExcepcionIntegridad e) {
            if (e.getCodigoError().compareTo(MENSAJES.PROC_LOTE_NOEX) == 0)
                System.out.println(e);
            else
                throw e;
        }
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    }

    /**
     * Devuelve un arreglo con los path a los xml a procesar. Los lotes se
     * procesan segun el orden del arreglo, y se eliminan en orden inverso.
     * 
     * @return
     */
    protected abstract String[] getPathArhivosAProcesar();

    /**
     * Invocado cada ves que se termina de procesar un Lote. Las subclases
     * pueden modificarlo para hacer algo especifico, como mostrar resultados
     * intermedios, verificar valores obtenidos, etc. Cuando se llama a este
     * metodo, el archivo resultado ya se grabo en disco, por lo que se puede
     * obtener.
     * 
     * @throws Exception
     */
    protected void finParcial(String pathLote, Session session)
            throws Exception {
        System.out.println("Se termino de procesar el lote " + pathLote);
    }

    /**
     * Invocado una ves que se terminan de procesar todos los lotes. Se pueden
     * controlar valores, mostrar resultados, etc. Este metodo no deberia borrar
     * los lotes, eso se hace desde el main, o si se sobreescribe el metodo
     * deshacerDespuesDeTest
     * 
     * 
     * @throws Exception
     */
    protected void finProceso(Session session) throws Exception {
        System.out
                .println("Se terminaron de procesar todos los lotes del test");
    }

    /**
     * Cambiar este metodo en las subclases para que ni bien termine el test,
     * siempre se borre de la base todo lo producido por el procesamiento
     * durante el test. Por defecto es false, porque se puede querer browsear la
     * base despues del procesamiento, para asegurarse que todo haya quedado
     * bien.
     * 
     * @return
     */
    protected boolean deshacerDespuesDeTest() {
        return false;
    }

    protected void MostrarAnimal(String treg, String nreg, boolean esHembra) throws Exception {
        Session session = StandaloneHibernateStrategy.getInstance().openNewSession();
        Animal an1 = AnimalDAO.findExistentHembraByRegistry(treg, nreg,"HOLA");
        MostrarAnimal(session, an1);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    }
    
    protected void MostrarAnimal(Animal an1) throws Exception {
        Session session = StandaloneHibernateStrategy.getInstance().openNewSession();
        MostrarAnimal(session, an1);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    }

        /**
     * Muestro los eventos del animal.
     */
    protected void MostrarAnimal(Session session, Animal an1) throws Exception {
        System.out.println("Animal");
        System.out.println("======");
        System.out.println(an1.toString());
        System.out.println("EVTs.");
        System.out.println("=====");
        Set evs = an1.getAllEventos();
        for (Iterator iter = evs.iterator(); iter.hasNext();) {
            Evento ev = (Evento) iter.next();
            System.out.println(ev.getResumenYEncab());
        }
    }
    
}
