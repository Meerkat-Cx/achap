package ar.org.sicel.persistence.util.jasperReport;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.org.sicel.persistence.Animal;


public class AnimalListaDataSource implements JRDataSource {
	
	Iterator iterador;
	
	
	public AnimalListaDataSource(List<Animal> ans) {
        iterador = ans.iterator();
    }
	
	
	
	public boolean next() throws JRException {
		return iterador.hasNext();
	}

	public Object getFieldValue(JRField field) throws JRException {
		return iterador.next();
	} 

}


