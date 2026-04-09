/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.Comparator;
import java.util.Date;

/**
 * @author Jivars
 *
 */
public class LogPorFecha implements Comparator {

    public int compare(Object arg1, Object arg2) {
        LogContacto l1 = (LogContacto) arg1;
        LogContacto l2 = (LogContacto) arg2;
        Date fecha1 = l1.getFecha();
        Date fecha2 = l2.getFecha();
       if (fecha1.compareTo(fecha2) != 0)
            return fecha1.compareTo(fecha2);
       else{//si son iguales las fechas
    	   if(l1.getId()!=null && l2.getId()!=null){//si ya estan en la base es por orden de carga
    		   if(l1.getId().longValue()<l2.getId().longValue())
    			   return -1;
    		   return 1;
    	   }
    	   else{
    		   if(l1.getId()==null)
    			   return 1;
    		   return -1;
    	   }
    	   		
    		   
       }
       // else if(tipo1.compareTo(tipo2) != 0)
        //    return tipo1.compareTo(tipo2);
        //else
        	//return -1;
        	//return 0;
    }

}
