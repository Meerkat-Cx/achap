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
public class AnimalesOrdenadosPorRpYNac implements Comparator {

    public int compare(Object arg1, Object arg2) {
        Animal l1 = (Animal) arg1;
        Animal l2 = (Animal) arg2;
        String rp1 = l1.getRP();
        String rp2 = l2.getRP();
        Date fecha1 = l1.getFechaNac();
        Date fecha2 = l2.getFechaNac();
       if(rp1.compareTo(rp2)==0)
            return fecha1.compareTo(fecha2);
       return rp1.compareTo(rp2);
       
       
            
     
       }
}