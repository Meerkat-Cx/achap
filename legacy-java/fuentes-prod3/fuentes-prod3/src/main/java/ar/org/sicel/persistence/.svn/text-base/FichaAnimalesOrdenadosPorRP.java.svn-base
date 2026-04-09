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
public class FichaAnimalesOrdenadosPorRP implements Comparator {

    public int compare(Object arg1, Object arg2) {
        FichaAnimalPendiente l1 = (FichaAnimalPendiente) arg1;
        FichaAnimalPendiente l2 = (FichaAnimalPendiente) arg2;
        String rp1 = l1.getAnimal().getRP();
        String rp2 = l2.getAnimal().getRP();
        Date fecha1 = l1.getAnimal().getFechaNac();
        Date fecha2 = l2.getAnimal().getFechaNac();
       if(rp1.compareTo(rp2)==0)
            return fecha1.compareTo(fecha2);
       return rp1.compareTo(rp2);
     }
      
    

}