package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.web.altaEdicion.forms.AnimalForm;
import ar.org.sicel.web.altaEdicion.forms.LactanciasGeneticasForm;

public class ActualizarLactanciasAction extends DispatchAction {
	/*------id 28666   posicion ---1
	------id 3046322 posicion --- 308442
	------id 4199236              616885
	------id 5143524      925326*/
	@SuppressWarnings("unchecked")
	public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Comienza procesamiento de lactancias geneticas...");
		ActualizarLactanciasThread thread = new ActualizarLactanciasThread(Thread.MAX_PRIORITY);
		thread.start();
		return mapping.findForward("init");
	}
	public ActionForward ejecutar2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Comienza procesamiento de lactancias geneticas...");
		
		//ActualizarLactanciasThread thread = null;
		LactanciasGeneticasForm lacForm = (LactanciasGeneticasForm)form;
		 Long idAn = null;
	        if(lacForm.getIdAnimal()==null)
	        	idAn = Long.parseLong((String)request.getAttribute("id"));
	        else
	        	idAn =lacForm.getIdAnimal();
		ActualizarLactanciasThread thread = new ActualizarLactanciasThread(Thread.MAX_PRIORITY,idAn);
		thread.start();
		return mapping.findForward("init");
	}/*
	public ActionForward ejecutar3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Comienza procesamiento de lactancias geneticas...");
		ActualizarLactanciasThread thread = new ActualizarLactanciasThread(Thread.MAX_PRIORITY,4199236L,5143524L);
		thread.start();
		return mapping.findForward("init");
	}
	public ActionForward ejecutar4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Comienza procesamiento de lactancias geneticas...");
		ActualizarLactanciasThread thread = new ActualizarLactanciasThread(Thread.MAX_PRIORITY,5143524L,19000000L);
		thread.start();
		return mapping.findForward("init");
	}*/
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("init");
	}

}
