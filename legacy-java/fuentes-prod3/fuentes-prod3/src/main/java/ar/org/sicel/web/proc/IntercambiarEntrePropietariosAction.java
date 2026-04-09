/**
 * 
 */
package ar.org.sicel.web.proc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.util.HibernateFactory;


/**
 * @author jdivars
 *
 */
public class IntercambiarEntrePropietariosAction extends DispatchAction {

	public ActionForward init(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		IntercambioForm iForm = (IntercambioForm)form;
		iForm.reset();
		List propietarios = PropietarioDAO.findAll();
		request.setAttribute("propietarios",propietarios);
		request.setAttribute("action","cambiar");
		return mapping.findForward("init");		
	}
	public ActionForward cambiar(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		IntercambioForm iForm = (IntercambioForm)form;
		Long idP1=iForm.getIdProp1();
		Long idP2=iForm.getIdProp2();
		Propietario p1 = PropietarioDAO.findByPrimaryKey(idP1);
		Propietario p2 = PropietarioDAO.findByPrimaryKey(idP2);
		List animalesP1= new ArrayList();
		animalesP1.addAll(p1.getAnimals());
		//List animalesP2 = new ArrayList();		
		while (animalesP1.size()>0){
			Animal an = (Animal)animalesP1.remove(0);
			an.setPropietario(p2);			
			//animalesP2.add(an);			
		}
		//p2.getAnimals().addAll(animalesP2);
		HibernateFactory.getSession().update(p1);
		HibernateFactory.getSession().update(p2);
		iForm.reset();
		List propietarios = PropietarioDAO.findAll();
		request.setAttribute("propietarios",propietarios);
		request.setAttribute("action","init");
		return mapping.findForward("init");
		
	}
	
}
