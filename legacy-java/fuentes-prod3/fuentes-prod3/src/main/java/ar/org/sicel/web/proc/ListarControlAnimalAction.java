/**
 * 
 */
package ar.org.sicel.web.proc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AnimalForm;

/**
 * @author jdivars
 *
 */
public class ListarControlAnimalAction extends DispatchAction{

	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		AnimalForm af = (AnimalForm)form;
		af.reset();
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		return mapping.findForward("init");
		
		
	}
	
	public ActionForward listar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		 AnimalForm formA = (AnimalForm)form;
		String tipo = formA.getTipoReg();
		String numero = formA.getNumReg();
		String raza = formA.getRaza();
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		List razas = RazaDAO.findAll();
		request.setAttribute("razas",razas);
		//try{
		Animal anim = AnimalDAO.findByRegistryAndUser(tipo.toUpperCase(),numero,raza.toUpperCase(),"H",user); 
		if(anim!=null){
			List eventosControl = anim.getEventos(Evento.EVT_TIPO_CONTROL_ANIMAL);
			if(eventosControl.isEmpty())
				request.setAttribute("mensaje","La vaca no tiene controles asociados");
			else
				request.setAttribute("controles",eventosControl);
			return mapping.findForward("init");
		}
		else
			request.setAttribute("mensaje","La vaca no existe");
		
		return mapping.findForward("init");
	}
}
