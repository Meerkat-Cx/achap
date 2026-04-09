package ar.org.sicel.web.find;

import org.apache.struts.action.ActionForm;

public class TiposRegistroForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id = null;
	String descrip = null;
	int tiene = 0;
	public int getTiene() {
		return tiene;
	}
	public void setTiene(int tiene) {
		this.tiene = tiene;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
