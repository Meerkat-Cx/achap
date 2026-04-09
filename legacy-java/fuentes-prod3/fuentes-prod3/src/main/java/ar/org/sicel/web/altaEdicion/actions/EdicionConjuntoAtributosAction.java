package ar.org.sicel.web.altaEdicion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Session;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.AtributoForm;

public class EdicionConjuntoAtributosAction extends DispatchAction {
	static Logger log = Logger.getLogger(EdicionConjuntoAtributosAction.class);

	public ActionForward editAtributo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.debug("edicion de Atributo");
		AtributoForm aForm = (AtributoForm) form;		
		String id = request.getParameter("idValor");
		Atributo atributo= AtributoDAO.findByPrimaryKey(Long.valueOf(id));
		this.copiarPropiedades(atributo,aForm);
        request.getSession().setAttribute("action", "update");
        return mapping.findForward("edicion");  
	}

    private void copiarPropiedades(Atributo atributo, AtributoForm form) {
    	form.setId(atributo.getId());
    	form.setNombre(atributo.getNombre());
    	form.setDescripcion(atributo.getDescripcion());
    	form.setValorAdmitidoId(atributo.getValorPorDefecto().getId());
    	form.setValorAdmitido(atributo.getValorPorDefecto().getValor());
	}

    public ActionForward update(ActionMapping actionmapping, ActionForm form, HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
    throws Exception{
    	if (isCancelled(httpservletrequest))
    		return (actionmapping.findForward(Tokens.FAILURE));

    	AtributoForm aForm = (AtributoForm) form;	
    	Atributo atributo= AtributoDAO.findByPrimaryKey(Long.valueOf(aForm.getId()));
    	atributo.getValorPorDefecto().setValor(aForm.getValorAdmitido());
    	Configuracion.suciaCache = true;
    	Configuracion.getConfiguracionActual();
    	return actionmapping.findForward("list");
	}
}
