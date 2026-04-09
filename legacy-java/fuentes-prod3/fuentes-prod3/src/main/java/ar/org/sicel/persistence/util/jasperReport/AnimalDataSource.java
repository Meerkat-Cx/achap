package ar.org.sicel.persistence.util.jasperReport;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;


/**
 * @author <a href="mailto:diegopalmisano@yahoo.com.ar">Diego Palmisano</a>
 * 
 * Utilizado en el reporte de las fichas de un Animal.
 * Basicamente, dado un nombre de propiedad usado en el reporte de Jasper, devuelve el objeto correspondiente
 * a dicha propiedad en el animal actual.
 *
 */
public class AnimalDataSource implements JRDataSource {
    ar.org.sicel.proc.v1.anmodif.Animal[] ans = null;
    private Animal actualAnimal = null;
    private int orderIndex = -1;
    private List<Animal> animales;
    Animal[] anims = null;
        
    

    /**
     *
     */
    public AnimalDataSource(ar.org.sicel.proc.v1.anmodif.Animal[] ans) {
        this.ans = ans;
        this.animales = new ArrayList<Animal>();
        int x = -1;
        while(x < ans.length - 1){
        	x++;
        	Animal an = AnimalDAO.findByPrimaryKey(ans[x].getIDAnim());
        	//inicializo el establecimiento para que no vaya a la base en otros threads
        	an.getEstablecimientoCriador();
        	animales.add(an);
        }
    }
    public AnimalDataSource(Animal a) {
       // this.ans = ans;
        this.animales = new ArrayList<Animal>();
        int x = -1;
            
        	//inicializo el establecimiento para que no vaya a la base en otros threads
        	a.getEstablecimientoCriador();
        	animales.add(a);
        	//actualAnimal = a;
        	//orderIndex=-1;
    }
    /**
     *
     */
    public boolean next() throws JRException {
    	if(ans!=null){
	        if (orderIndex < ans.length - 1){
	            orderIndex ++;
	            actualAnimal = this.animales.get(orderIndex);
	//            try {
	//            	actualAnimal = AnimalDAO.findByPrimaryKey(ans[orderIndex].getIDAnim());
	//           } catch (HibernateException e) {
	//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	//            }
	//            StandaloneHibernateStrategy.getInstance().commitCurrentSession();
	            return true;
	        }
	        return false;
    		}
    	else{
    		orderIndex++;
    		if(orderIndex == 0){
	    		actualAnimal = this.animales.get(orderIndex);
	    		System.out.println("animal "+actualAnimal.getId());
	    		return true;
    		}
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
        	value = this.actualAnimal;               	
        
        return value;
    }
}
