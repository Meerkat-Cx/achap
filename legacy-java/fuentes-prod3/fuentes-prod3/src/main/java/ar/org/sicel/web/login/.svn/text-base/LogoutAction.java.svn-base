package ar.org.sicel.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ar.org.sicel.web.Tokens;


/**
 * @struts.action
 *      path="/logout"
 *      scope="request"
 *      validate="false"
 *
 * @struts.action-forward
 *      name="success"
 *      path=".main"
 *
 * @struts.action-forward
 *      name="failure"
 *      path=".main"
 *
 */
public final class LogoutAction extends Action {
  
	public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        HttpSession s = request.getSession();
        s.removeAttribute(Tokens.USER_NAME);
        s.removeAttribute(Tokens.CURRENTUSER);
        s.invalidate();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage(Tokens.OUT_SYSTEM));
        saveMessages(request, messages);

        return mapping.findForward(Tokens.SUCCESS);
    }
}
