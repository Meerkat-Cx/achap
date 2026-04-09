package ar.org.sicel.test.old;

import junit.framework.TestCase;


public class AltaAnimal extends TestCase {
    String fileConCompRacial = "resources/sicel/test/altaAnimalConCompRacial";

    String fileConMadre = "resources/sicel/test/altaAnimalConMadre";
/*
    public void testAltaConCompRacial() throws Exception {
        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
        File f = new File(fileConCompRacial + ".xml");
        String loteXML = IOUtils.toString(new FileInputStream(f));
        StandaloneHibernateStrategy.getInstance().openNewSession();
        String resultado = proc.procesar(loteXML);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        File salida = new File(fileConCompRacial + "_resultado.xml");
        FileOutputStream out = new FileOutputStream(salida);
        out.write(resultado.getBytes());
        out.flush();
        out.close();
        // buscar el animal dado de alta en los resultados
        Lote lote = (Lote) Lote.unmarshal(new StringReader(resultado));
        long idanim = lote.getEstabs().getEstab(0).getEvtsEstab().getAltas()
                .getAlta(0).getIDAnim();
        StandaloneHibernateStrategy.getInstance().openNewSession();
        VerEventos(idanim);
        ElimLote.elimLote(loteXML);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
    }

    public void testAltaConMadre() throws Exception {
        IProcesadorXML proc = ServiceLocator.getProcesadorXML();
        File f = new File(fileConMadre + ".xml");
        String loteXML = IOUtils.toString(new FileInputStream(f));
        StandaloneHibernateStrategy.getInstance().openNewSession();
        String resultado = proc.procesar(loteXML);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        File salida = new File(fileConMadre + "_resultado.xml");
        FileOutputStream out = new FileOutputStream(salida);
        out.write(resultado.getBytes());
        out.flush();
        out.close();
        // buscar el animal dado de alta en los resultados
        Lote lote = (Lote) Lote.unmarshal(new StringReader(resultado));
        long idanim = lote.getEstabs().getEstab(0).getEvtsEstab().getAltas()
                .getAlta(0).getIDAnim();
        StandaloneHibernateStrategy.getInstance().openNewSession();
        VerEventos(idanim);
        ElimLote.elimLote(loteXML);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();

    }

    public void VerEventos(long idanim) throws Exception {
        Animal an1 = AnimalDAO.findByPrimaryKey(idanim);
        System.out.println(an1.toString());
        System.out.println("EVTs.");
        System.out.println("=====");
        Set evs = an1.getAllEventos();
        for (Iterator iter = evs.iterator(); iter.hasNext();) {
            Evento ev = (Evento) iter.next();
            System.out.println(ev.getResumenYEncab());
        }
    }
    */
}
