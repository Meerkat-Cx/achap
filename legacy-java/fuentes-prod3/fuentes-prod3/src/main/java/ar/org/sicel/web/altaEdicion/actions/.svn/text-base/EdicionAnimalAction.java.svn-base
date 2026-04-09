/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AnimalForm;

/**
 * @author Ramiro Trachsel
 *
 */
/**
 * @struts.action 
 * 		path="/edicionAnimal"
 * 		name="animalForm"
 * 		scope="request"
 * 		validate="false"
 * 
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 * 
 * @struts.action-forward
 * 		name="closePopup"
 * 		path=".closePopup"
 * 
 * @struts.action-forward
 *      name="refreshClosePopup"
 *		path=".refreshParentAndClosePopup"  
 */



public class EdicionAnimalAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AnimalForm animalForm = (AnimalForm) form;
				
		Long id = animalForm.getAnimalId();
			
		if(isCancelled(request)) {
			if(id==null)
				return mapping.findForward(Tokens.MAIN);
			else return mapping.findForward("closePopup");
		}
		
		Animal animal = AnimalDAO.findByPrimaryKey(id);
		
		if(animal!=null) {			
			InputStream foto = animalForm.getFoto().getInputStream();
			if(animalForm.getFoto().getFileSize()!=0) {
				if(animal.getFoto()==null)
					animal.setFoto(FotoDAO.createPersistent(IOUtils.toByteArray(foto)));
				else {
					HibernateFactory.getSession().delete(animal.getFoto());
					animal.setFoto(FotoDAO.createPersistent(IOUtils.toByteArray(foto)));
				}
				return mapping.findForward("refreshClosePopup");			
			} 
		} 
		return mapping.findForward("closePopup");		
	}
			

}
