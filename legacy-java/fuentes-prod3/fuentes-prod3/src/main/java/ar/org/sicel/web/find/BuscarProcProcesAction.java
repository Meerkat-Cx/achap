package ar.org.sicel.web.find;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcEstablecimiento;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.ProcProcesDAO;
import ar.org.sicel.web.Tokens;

/**
 * @struts.action
 * 		path="/buscarProcProces"
 * 		input=".main"
 * 		name="procProcesForm"
 * 		scope="request"
 * 		validate="true"
 *
 * @struts.action-forward
 * 		name="success"
 * 		path=".procProcesDetalle"
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
public final class BuscarProcProcesAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        // Note la siguiente linea: en caso de que el usuario haya
        // presionado CANCEL, se volvera a la página de inicio,
        // y al no ingresar ningún tipo de usuario, en el menú
        // no se  presentaran las opciones de búsqueda.
        if (isCancelled(request))
            return (mapping.findForward("main"));

        Long procProcesID = (Long) PropertyUtils.getSimpleProperty(form,
                "procProcesId");

        ProcProces procProces = ProcProcesDAO.findByPrimaryKey(procProcesID);

        if (procProces == null){
            ActionErrors errors = new ActionErrors();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.procProcesNoExiste"));
            saveMessages(request, errors);
            return mapping.findForward(Tokens.FAILURE);
        }
        Set mensajes = new HashSet();
        mensajes.addAll(procProces.getProcMsgsses());
        if(procProces.getProcLote()!=null){
        	//mensajes.addAll(procProces.getProcLote().getProcMsgsses());
        	mensajes.addAll(this.getErrores(procProces.getProcLote().getProcMsgsses()));
        	Set establecimientos = procProces.getProcLote().getProcEstablecimientos();
        	if(establecimientos!=null){
        		Iterator it = establecimientos.iterator();
        		while(it.hasNext()){
        			ProcEstablecimiento p = (ProcEstablecimiento)it.next();
        			//mensajes.addAll(p.getProcMsgsses());
        			mensajes.addAll(this.getErrores(p.getProcMsgsses()));
        			Set evtE = p.getProcEvtEsts();
        			if(evtE!=null){
        				Iterator it4 = evtE.iterator();
        				while(it4.hasNext()){
        					ProcEvtEst pe = (ProcEvtEst)it4.next();
        					mensajes.addAll(this.getErrores(pe.getProcMsgsses()));
        				}
        			}
        			Set animales = p.getProcAnimals();
        			if(animales!=null){
	        			Iterator it2 = animales.iterator();
	        			while(it2.hasNext()){
	        				ProcAnimal an = (ProcAnimal)it2.next();
	        				mensajes.addAll(this.getErrores(an.getProcMsgsses()));
	        				Set evtA = an.getProcEvtAnimals();
	        				if(evtA!=null){
	        					Iterator it3 = evtA.iterator();
	        					while(it3.hasNext()){
	        						ProcEvtAnimal pa = (ProcEvtAnimal)it3.next();
	        						mensajes.addAll(this.getErrores(pa.getProcMsgsses()));
	        					}
	        				}
	        			}
        			}
        		}
        	}
        }
      
        PropertyUtils.setSimpleProperty(form, "procProces", procProces);
        PropertyUtils.setSimpleProperty(form, "mensajes", mensajes);
        
		return (mapping.findForward(Tokens.SUCCESS));
	}
	private Set getErrores(Set mensajes){
		Iterator it = mensajes.iterator();
		Set resul = new HashSet();
		while(it.hasNext()){
			ProcMsg p = (ProcMsg)it.next();
			if(p.getNivelError().equals(ProcMsg.ERROR)||p.getNivelError().equals(ProcMsg.FATAL))
				resul.add(p);
				
		}
		return resul;
		
	}

}