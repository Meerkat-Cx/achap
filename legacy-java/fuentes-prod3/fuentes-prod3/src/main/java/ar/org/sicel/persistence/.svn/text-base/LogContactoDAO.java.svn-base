/**
 * 
 */
package ar.org.sicel.persistence;

import java.util.Date;


/**
 * @author jivars
 *
 */
public class LogContactoDAO {
	
	
	public static LogContacto create(String accion,CentroDeComputo centro, Contacto contacto,Date fecha,MetodoControl metodo,Usuario user){
		
		LogContacto log  = new LogContacto();
		log.setAccion(accion);
		log.setCentro(centro);
		log.setContacto(contacto);
		log.setFecha(fecha);
		log.setMetodoControl(metodo);
		log.setMetodoControlNuevo(metodo);
		log.setUsuario(user);
		return log;
		
	}
}
