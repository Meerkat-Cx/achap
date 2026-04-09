/**
 * 
 */
package ar.org.sicel.persistence;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Ramiro Trachsel
 *
 */
/**
*
* @hibernate.class
*     table="En_Foto"
*     lazy="true"
*
*/
public class Foto {
//	 --------------- attributes ---------------------
    private java.lang.Long id;
    private byte[] contenido;
    
    public Foto() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     *     column="id"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_en_foto"
     *
     */
    public java.lang.Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
        this.id = id;
    }
        
    
    /**
    *
    * @hibernate.property
    *     column="foto"
    *     type="ar.org.sicel.persistence.util.HibernateByteBlobType"
    *
    */
   public byte[] getContenido() {
       return contenido;
   }

   public void setContenido(byte[] contenido) {
       this.contenido = contenido;
   }
    
   
   
   public Image getAsImage() throws IOException {
	   return ImageIO.read(new ByteArrayInputStream(getContenido()));
   }

}
