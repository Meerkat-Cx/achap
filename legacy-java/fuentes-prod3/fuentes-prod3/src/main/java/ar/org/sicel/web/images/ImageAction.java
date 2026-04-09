package ar.org.sicel.web.images;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.org.sicel.persistence.Foto;
import ar.org.sicel.persistence.FotoDAO;
/**
 * @struts.action
 * 		path="/imagen"
 * 		scope="request"
 *
 *
 * @struts.action-forward
 * 		name="main"
 * 		path=".main"
 *
 */
public class ImageAction extends Action {

	
	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String id = req.getParameter("id");
		if (StringUtils.isEmpty(id))
			return null;
		Foto foto = FotoDAO.findByPrimaryKey(Long.valueOf(id)); 
		resp.setContentType("image/jpg");
		resp.setContentLength(foto.getContenido().length);
		 
		 
		OutputStream out = resp.getOutputStream();
		out.flush();
		byte[] contenido = foto.getContenido();
		//int byteRead;
		for (int i=0;i<contenido.length;i++)
			out.write(contenido[i]);
		
		out.flush();
		out.close();
		return null;
	}

	

}
