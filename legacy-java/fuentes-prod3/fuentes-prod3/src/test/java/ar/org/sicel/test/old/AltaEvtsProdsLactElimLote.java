/*
 * Creado el 28/06/2005
 */
package ar.org.sicel.test.old;

import junit.framework.TestCase;

/**
 * @author ala
 * La idea es procesar un lot epara dar un alta (en una transaccion), 
 * luego en otra transacción, en ese mismo animal procesar producciones, 
 * y ver si calcula las lactancias.
 * Se elimina todo, lote, animal, producciones ...
 * 
 */

public class AltaEvtsProdsLactElimLote extends TestCase {
    String fileName1 = "resources/sicel/test/AltaEvtsProdsLactElimLote-Alta";
    String fileName2 = "resources/sicel/test/AltaEvtsProdsLactElimLote-Prods";
/*
    public void testAltaConCompRacial() throws Exception {
        System.out.println("Inicio Transaccion1");
        StandaloneHibernateStrategy.getInstance().openNewSession();
        System.out.println("Procesando lote 1: alta del animal");
        String[] resultado1 = procesarLote(fileName1); 
        System.out.println("Animal dado de alta:");
        Lote lote = (Lote) Lote.unmarshal(new StringReader(resultado1[1]));
        long idanim = lote.getEstabs().getEstab(0).getEvtsEstab().getAltas()
                .getAlta(0).getIDAnim();
        VerEventos(idanim);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        System.out.println("Fin Transaccion1");
        
        System.out.println("Inicio Transaccion2");
        StandaloneHibernateStrategy.getInstance().openNewSession();
        System.out.println("Procesando lote 2: eventos produccion");
        String[] resultado2 = procesarLote(fileName2); 
        System.out.println("eventos resultantes (con lactancia calculada)");
        VerEventos(idanim);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        System.out.println("Fin Transaccion2");

        System.out.println("Inicio Transaccion3");
        StandaloneHibernateStrategy.getInstance().openNewSession();
        System.out.println("Eliminacion del lote 2 (aun no hice q baja anim elimine sus ev)");
        elimLote(resultado2[0]);
        System.out.println("Eliminacion del lote 1");
        elimLote(resultado1[0]);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        System.out.println("Fin Transaccion3");
        System.out.println("Fin");
        
    }

    public void elimLote(String loteXML) throws Exception {
        Lote lote = (Lote) Lote.unmarshal(new StringReader(loteXML));
        long numero = lote.getIDLoteInte();
        long inte = lote.getInformante();
        try {
            ProcProces.elimProcesoYEventos(inte, numero);
        } catch (ExcepcionIntegridad e) {
            if (e.getCodigoError().compareTo(MENSAJES.PROC_LOTE_NOEX) != 0)
                throw e;
        }
    }

    
    private String[] procesarLote(String fileName) throws IOException {
        File f = new File(fileName1 + ".xml");
        FileInputStream fis = new FileInputStream(f);
        String loteXML = IOUtils.toString(fis);
        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
        String loteXMLProcesado = proc.procesar(loteXML);
        File salida = new File(fileName + "_resultado.xml");
        FileOutputStream out = new FileOutputStream(salida);
        out.write(loteXMLProcesado.getBytes());
        out.flush();
        out.close();
        return new String[] {loteXML , loteXMLProcesado};
    }

    public void VerEventos(long idanim) throws Exception {
        Animal animal = AnimalDAO.findByPrimaryKey(idanim);
        System.out.println("AN:");
        System.out.println("===");
        System.out.println(animal.toString());
        System.out.println("EVTs:");
        System.out.println("=====");
        Set evs = animal.getAllEventos();
        for (Iterator iter = evs.iterator(); iter.hasNext();) {
            Evento ev = (Evento) iter.next();
            System.out.println(ev.getResumenYEncab());
        }
    }
*/
}
