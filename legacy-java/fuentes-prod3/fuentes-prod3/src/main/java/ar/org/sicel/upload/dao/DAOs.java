/**
 * 
 */
package ar.org.sicel.upload.dao;




/**
 * @author jdivars
 *
 */
public class DAOs {
	
	
/*	public static UsuarioDAO getUsuarioDAO(HttpServletRequest request){
		return (UsuarioDAO) getApplicationContext(request).getBean("UsuarioDAO");
	}
	*/
	public static BajadaDAO getBajadaDAO(){
		return new BajadaDAO();
	}
}
