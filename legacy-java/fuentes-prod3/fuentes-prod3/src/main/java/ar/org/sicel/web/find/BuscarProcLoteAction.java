package ar.org.sicel.web.find;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcLoteDAO;
import ar.org.sicel.web.Tokens;

/**
 * @struts.action
 * 		path="/buscarProcLote"
 * 		input=".main"
 * 		name="procLoteForm"
 * 		scope="request"
 * 		validate="true"
 *
 * @struts.action-forward
 * 		name="success"
 * 		path=".procLoteDetalle"
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 * @struts.action-forward
 * 		name="failure"
 * 		path=".main"
 *
 */
public final class BuscarProcLoteAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Note la siguiente linea: en caso de que el usuario haya
        // presionado CANCEL, se volvera a la página de inicio,
        // y al no ingresar ningún tipo de usuario, en el menú
        // no se  presentaran las opciones de búsqueda.
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Long procLoteID = (Long) PropertyUtils.getSimpleProperty(form,
                "procLoteId");

        ProcLote procLote = ProcLoteDAO.findByPrimaryKey(procLoteID);

        if (procLote == null){
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.procLoteNoExiste"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }

        PropertyUtils.setSimpleProperty(form, "procLote", procLote);
		return (mapping.findForward(Tokens.SUCCESS));
	}

}