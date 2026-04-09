package ar.org.sicel.persistence;

import java.util.Iterator;
import java.util.List;

import ar.org.sicel.util.DateUtils;

public class EvtLactanciaDAO {

	public static List<Object> getLecheLactanciaChart(EvtLactancia lactancia) {
		List<Object> result = new java.util.ArrayList<Object>();
		String tipoin = lactancia.getEventoIniciaLactancia().getNombreTipo();
		String fecha = DateUtils.format(lactancia.getEventoIniciaLactancia().getFecha(), "dd/MM/yyyy") ;
		
		Object[] row1 = new Object[]{tipoin +fecha,0L}; 
		//Object[] row1 = new Object[]{tipoin +fecha};
		result.add(row1);
		Iterator it = lactancia.getControles().iterator();
		while(it.hasNext()){
			EvtControlAnimal eva = (EvtControlAnimal)it.next();
			row1 = new Object[]{eva.getNombreTipo()+DateUtils.format(eva.getFecha(), "dd/MM/yyyy"),eva.getLeche()};
			//row1 = new Object[]{eva.getNombreTipo()+DateUtils.format(eva.getFecha(), "dd/MM/yyyy")};
			result.add(row1);
		}
		if(lactancia.getEventoFinalizaLactancia()!=null){
			tipoin = lactancia.getEventoFinalizaLactancia().getNombreTipo();
			fecha = DateUtils.format(lactancia.getEventoFinalizaLactancia().getFecha(), "dd/MM/yyyy") ;
			
			row1 = new Object[]{tipoin+fecha,0L}; 
			//row1 = new Object[]{tipoin+fecha};
			result.add(row1);
		}
		return result;
	}
	public static List<Object> getLecheTotalControlesLactancias(Hembra hembra) {
		List<Object> result = new java.util.ArrayList<Object>();
		List lactancias = hembra.getLactanciasSicelTres();
		lactancias.add(hembra.getLactanciaEnCurso());
		Iterator it3 = lactancias.iterator();
		while(it3.hasNext()){
			EvtLactancia lactancia = (EvtLactancia)it3.next();
			String tipoin = lactancia.getEventoIniciaLactancia().getNombreTipo();
			String fecha = DateUtils.format(lactancia.getEventoIniciaLactancia().getFecha(), "dd/MM/yyyy") ;
			
			Object[] row1 = new Object[]{tipoin +fecha,0L}; 
			//Object[] row1 = new Object[]{tipoin +fecha};
			result.add(row1);
			Iterator it = lactancia.getControles().iterator();
			while(it.hasNext()){
				EvtControlAnimal eva = (EvtControlAnimal)it.next();
				row1 = new Object[]{eva.getNombreTipo()+DateUtils.format(eva.getFecha(), "dd/MM/yyyy"),eva.getLeche()};
				//row1 = new Object[]{eva.getNombreTipo()+DateUtils.format(eva.getFecha(), "dd/MM/yyyy")};
				result.add(row1);
			}
			if(lactancia.getEventoFinalizaLactancia()!=null){
				tipoin = lactancia.getEventoFinalizaLactancia().getNombreTipo();
				fecha = DateUtils.format(lactancia.getEventoFinalizaLactancia().getFecha(), "dd/MM/yyyy") ;
				
				row1 = new Object[]{tipoin+fecha,0L}; 
				//row1 = new Object[]{tipoin+fecha};
				result.add(row1);
		}
		
		}
		return result;
	}	

}

