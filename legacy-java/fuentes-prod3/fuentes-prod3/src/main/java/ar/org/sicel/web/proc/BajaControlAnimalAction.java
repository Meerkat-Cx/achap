/**
 * 
 */
package ar.org.sicel.web.proc;

import java.awt.geom.Arc2D.Float;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtControlAnimal;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.web.altaEdicion.forms.ControlAnimalForm;



/**
 * @author jdivars
 *
 */
public class BajaControlAnimalAction extends DispatchAction{

	
	public ActionForward bajaEvento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		ControlAnimalForm cform = (ControlAnimalForm)form;
		Long id = cform.getId();
		//String d = (String)request.getAttribute("id");
		//Long id = new Long(d)
		Evento control =EventoDAO.findByPrimaryKey(id);
		
	
		try{
			//System.out.println("dando de baja el evento--->"+control.getId());
			control.ejecutarBaja();
		}
		catch (ExcepcionIntegridad e1) {
			ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
			String mensaje = msg.getInformacion();
			//System.out.println("MENSAJEEEE->"+mensaje);
			request.setAttribute("mensaje",mensaje);
			request.setAttribute("urlRetorno", "'buscarAnimalOrdenie.do");
			return mapping.findForward("confirma");
		}
		request.setAttribute("mensaje","la baja se realizó con éxito");
		//request.setAttribute("urlRetorno", "'buscarAnimalOrdenie.do");
		return mapping.findForward("confirma");
	}
	public ActionForward verControl(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		ControlAnimalForm cform = (ControlAnimalForm)form;
		Long id = cform.getId();
		//String d = (String)request.getAttribute("id");
		//Long id = new Long(d)
		EvtControlAnimal control =(EvtControlAnimal) EventoDAO.findByPrimaryKey(id);
		cform.setRp(control.getAnimal().getRP());
		cform.setFecha(control.getFecha());
		cform.setOrdenies(control.getOrdeniesAnimal().size());
		cform.setNombreTambo(control.getEstablecimiento().getNombreContacto());
		
		String nombreEstancia = (control.getEstablecimiento().getEstancia()!=null)?control.getEstablecimiento().getEstancia().getNombreContacto():"";
		cform.setNombreEstablecimiento(nombreEstancia);
		
		String nombrePropietario = (control.getEstablecimiento().getPropietario()!=null)?control.getEstablecimiento().getPropietario().getNombreContacto():"";
		cform.setNombrePropietario(nombrePropietario);
		cform.setCantidadLeche(control.getLeche().toString());
		
		return mapping.findForward("verControl");
	}
	
}
