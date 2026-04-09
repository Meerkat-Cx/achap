package ar.org.sicel.persistence.util.jasperReport;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;

public class AnimalUnicoDataSource implements JRDataSource{
    private Animal animal;
    
    private boolean animalDevuelto = false;
    
    
    /**
     *
     */
    public AnimalUnicoDataSource(Long idAnimal) {
    	this.animal = AnimalDAO.findByPrimaryKey(idAnimal);
    }

    /**
     *
     */
    public boolean next() throws JRException {
    	if (!animalDevuelto){
    		this.animalDevuelto = true;
        	return true;
    	}
    	else{
    		return false;
    	}
    }

    /**
     *
     */
    public Object getFieldValue(JRField field) throws JRException {
        Object value = null;
        String fieldName = field.getName();
        
        if ("animal".equals(fieldName)) 
        	value = this.animal;               	
        
        return value;
    }

}
