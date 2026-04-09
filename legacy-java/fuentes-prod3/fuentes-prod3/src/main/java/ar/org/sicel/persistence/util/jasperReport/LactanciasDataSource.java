package ar.org.sicel.persistence.util.jasperReport;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.Lactancia;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;

public class LactanciasDataSource implements JRDataSource {
	private Collection lactancias = null;
	private int orderIndex = -1;
	private Lactancia lactanciaActual = null; 
		
	@SuppressWarnings({"unchecked","unchecked"})
	public LactanciasDataSource(Animal animal) {	
		
		this.lactancias = ((Hembra)animal).getLactanciasCerradas();
		//this.lactancias = ((Hembra)animal).getLactancias();
		Collections.sort((List)lactancias, new EventosPorFechaYTipo());
		//this.lactancias = ((Hembra)animal).getLactanciasMasEnCurso();
	}
	
	public boolean next() throws JRException {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		if (orderIndex < lactancias.size() - 1){
            orderIndex ++;         
            lactanciaActual = (Lactancia)((List)lactancias).get(orderIndex);
            StandaloneHibernateStrategy.getInstance().commitCurrentSession();
            return true;
        }
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        return false;
	}

	public Object getFieldValue(JRField field) throws JRException {
		
		Object value = null;
        String fieldName = field.getName();
               
        if("lactancia".equals(fieldName)) 
        	value = lactanciaActual;
        
        return value;		
	}
        

}
