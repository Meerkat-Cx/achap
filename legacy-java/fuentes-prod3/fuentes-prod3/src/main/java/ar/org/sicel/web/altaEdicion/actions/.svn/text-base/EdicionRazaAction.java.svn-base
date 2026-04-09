package ar.org.sicel.web.altaEdicion.actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.EspecieDAO;
import ar.org.sicel.persistence.Foto;
import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.web.altaEdicion.forms.RazaForm;

public class EdicionRazaAction extends DispatchAction {

//	public ActionForward initUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//	throws Exception {
//		RazaForm razaForm = (RazaForm) form;
//		
//		String stringId = request.getParameter("id");
//		
//		Raza raza = RazaDAO.findByPrimaryKey(stringId);
//		copiarDatosAlForm(raza, razaForm);
//		Collection listaEspecies = EspecieDAO.findAll();
//		request.setAttribute("listaEspecies", listaEspecies);
//		
//		return mapping.findForward("success");//	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		Collection listaEspecies = EspecieDAO.findAll();
		request.setAttribute("listaEspecies", listaEspecies);
		
		return mapping.findForward("success");
	}

//	private void copiarDatosAlForm(Raza raza, RazaForm form) {
//		form.setEscruza(raza.getEsCruza());
//		form.setEsdesconocido(raza.getEsDesconocido());
//		form.setId(raza.getId());
//		form.setIdEspecie(raza.getEspecie().getId());
//		form.setNombre(raza.getNombre());
//	}
	
//	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//	throws Exception {		
//		if (isCancelled(request))
//			return (mapping.findForward(Tokens.FAILURE));
//		
//		RazaForm razaForm = (RazaForm) form;
//		
//		Raza raza = RazaDAO.findByPrimaryKey(razaForm.getId());
//		actualizarRaza(raza, razaForm);
//		
//		RazaDAO.updateRaza(raza);
//		
//		request.getSession().removeAttribute("razaForm");
//		return mapping.findForward("list");
//	}

//	private void actualizarRaza(Raza raza, RazaForm razaForm) throws Exception {
//		raza.setEsCruza(razaForm.isEscruza());
//		raza.setEsDesconocido(razaForm.isEsdesconocido());
//		Especie especie = EspecieDAO.findByPrimaryKey(razaForm.getIdEspecie());		
//		raza.setEspecie(especie);
//		
//		if (razaForm.getFoto() != null && razaForm.getFoto().getFileData().length != 0) {
//			if (raza.getFoto() != null)
//				FotoDAO.deleteFoto(raza.getFoto());
//			Foto newFoto = FotoDAO.createPersistent(razaForm.getFoto().getFileData());		
//			raza.setFoto(newFoto);
//		}
//		raza.setNombre(razaForm.getNombre());
//	}
	
	
	public ActionForward aceptar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		
		RazaForm razaForm = (RazaForm) form;
				
		Raza raza = new Raza();
        raza.setNombre(razaForm.getNombre());
        raza.setEsCruza(razaForm.isEscruza());
        raza.setEsDesconocido(razaForm.isEsdesconocido());
        raza.setId(razaForm.getId().toUpperCase());
        raza.setCategoriaPura(razaForm.getCategoriaPura().toUpperCase());
        raza.setEspecie(EspecieDAO.findByPrimaryKey(razaForm.getIdEspecie()));
        if (razaForm.getFoto() != null && razaForm.getFoto().getFileData().length != 0) {
			Foto newFoto = FotoDAO.createPersistent(razaForm.getFoto().getFileData());		
			raza.setFoto(newFoto);
		}
        raza.setIdFicticio(RazaDAO.findMayorIdFicticio()+1);
        HibernateFactory.getSession().save(raza);
		request.getSession().removeAttribute("razaForm");

		return mapping.findForward("list");
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {		
		
		request.getSession().removeAttribute("razaForm");
		return mapping.findForward("list");
	}


}
