/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.excel.excepciones.EmailException;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.EcloDAO;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Registro;
import ar.org.sicel.persistence.RegistroDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.altaEdicion.forms.CambioRegistroForm;

/**
 * Action que es utilizado para que el administrador tenga la funcionalidad de poder cambiar el registro origen
 * de un animal  
 * @author jdivars
 * @since 10-Nov-2007
 */
public class CambioRegistroAction extends DispatchAction {
	
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		Long idAn = Long.parseLong(request.getParameter("id"));
		Animal animal = AnimalDAO.findByPrimaryKey(idAn);
		CambioRegistroForm cambioForm = (CambioRegistroForm)form;
		cambioForm.setTipoR(animal.getRegOrigen().getTipoRegistro().getId());
		cambioForm.setNumR(animal.getRegOrigen().getNumero());
		cambioForm.setId(idAn);
		return mapping.findForward("init");		
		
	}
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		CambioRegistroForm cambioForm = (CambioRegistroForm)form; 
		request.setAttribute("id",cambioForm.getId().toString());
		return (mapping.findForward("cambio"));
	}
	public ActionForward cambio(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    throws Exception {
		
		
		 
		CambioRegistroForm cambioForm = (CambioRegistroForm)form;
		Animal animal = AnimalDAO.findByPrimaryKey(cambioForm.getId());
		Registro rNew = RegistroDAO.create(cambioForm.getTipoNew(),cambioForm.getNumNew());
		Registro r  = RegistroDAO.findByTipoNum(cambioForm.getTipoNew(),cambioForm.getNumNew());
		if(r!=null && !animal.getRegistros().contains(r) ){
			request.setAttribute("error","El registro ya existe");
			return mapping.findForward("init");		
		}
		else{
			if(animal.getRegistros().contains(rNew)){
				
				Iterator it = animal.getRegistros().iterator();
				while(it.hasNext()){
					Registro d = (Registro)it.next();
					if(d.equals(rNew)){
						animal.setRegOrigen(d);
						animal.setRegIdentificador(d);
						break;
					}
				}
			}else{
				r = animal.getRegOrigen();
				animal.addRegistro(rNew);
				animal.setRegOrigen(rNew);
				animal.setRegIdentificador(rNew);
			}
			
			
			HibernateFactory.getSession().update(animal);
			request.setAttribute("id",cambioForm.getId().toString());
			try{
				EcloDAO.sendEmailCambioRegistro(animal, r.getTipoRegistro().getId(), r.getNumero());
			}catch(EmailException mailexception){
				System.out.println("No se pudo mandar el mail.");
				return mapping.findForward("cambio");
			}
			return mapping.findForward("cambio");	
		}
		
		
		

}
}
