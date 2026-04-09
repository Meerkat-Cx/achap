/**
 * 
 */
package ar.org.sicel.persistence.util.jasperReport;

import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalHBA;

/**
 * @author jivars
 *
 */
public class AnimalesHBADataSource implements JRDataSource {
	private Collection animales = null;
	private int orderIndex = -1;
	private AnimalHBA animal = null; 
		
	@SuppressWarnings({"unchecked","unchecked"})
	public AnimalesHBADataSource(List animales) {	
		this.animales = animales;
	}
	
	public boolean next() throws JRException {
		
		//StandaloneHibernateStrategy.getInstance().openNewSession();
		if (orderIndex < animales.size() - 1){
            orderIndex ++;
            //Calificacion c = animal.getUltimaCalificacion();
            //c.getCatPuntaje()
            //Registro r =  animal.getRegIdentificador();
           // System.out.println(r.);
           // System.out.println(r.getTipoRegistro().getDescripcion());
           // r.getTipoRegistro() 
          // if(((Registro)((Animal)animalActual).getRegIdentificador()).getTipoRegistro().equals("HBA")){
        	   
       //    }
            
           animal = (AnimalHBA)((List)animales).get(orderIndex);
          //  StandaloneHibernateStrategy.getInstance().commitCurrentSession();
            return true;
        }
		//StandaloneHibernateStrategy.getInstance().commitCurrentSession();
        return false;
	}

	public Object getFieldValue(JRField field) throws JRException {
		
		Object value = null;
        String fieldName = field.getName();
               
        if("animalHBA".equals(fieldName)) 
        	value = animal;
        
        return value;		
	}
        

}
