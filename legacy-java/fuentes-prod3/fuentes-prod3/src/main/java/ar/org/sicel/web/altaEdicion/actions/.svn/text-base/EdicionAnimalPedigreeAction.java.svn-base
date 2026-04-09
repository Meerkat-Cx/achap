/**
 * 
 */
package ar.org.sicel.web.altaEdicion.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalComentario;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.ArchivoExcel;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Foto;
import ar.org.sicel.persistence.FotoDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Registro;
import ar.org.sicel.persistence.RegistroDAO;
import ar.org.sicel.persistence.TipoRegistroDAO;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.persistence.util.jasperReport.ProjectReport;
import ar.org.sicel.util.Sicel3Conf;
import ar.org.sicel.web.Tokens;
import ar.org.sicel.web.altaEdicion.forms.PedigreeForm;



/**
 * @author jdivars
 * @since 08-05-2007
 */
public class EdicionAnimalPedigreeAction extends DispatchAction {
	/**
	 * Action que da inicio a la cargar manual de animales de pedigree
	 * se cargan las razas puras por que un animal de pedigree 
	 * no puede ser cruza ni desconocido, se cargan todos los tipos de registros que un animal de pedigree puede tener.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initAdd(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(request.getSession().getAttribute("pedigreeForm")!=null)
			request.getSession().removeAttribute("pedigreeForm");
		if(request.getSession().getAttribute("metodo")!=null)
			request.getSession().removeAttribute("metodo");
		if(request.getSession().getAttribute("nombreInter")!=null)
			request.getSession().removeAttribute("nombreInter");
		if(request.getSession().getAttribute("idInter")!=null)
			request.getSession().removeAttribute("idInter");
		List razas = RazaDAO.findRazasPuras();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		List categ = Animal.categorias();
		request.setAttribute("categorias",categ);
		request.setAttribute("categoria","PED");
		request.setAttribute("action","add");
		Propietario p = PropietarioDAO.findPropietarioInt();
		request.getSession().setAttribute("metodo","add");
		request.getSession().setAttribute("idInter",p.getId().toString());
		request.getSession().setAttribute("nombreInter",p.getNombreContacto());
		return mapping.findForward("add");
		
	}
	
	public ActionForward initBuscar(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(request.getSession().getAttribute("pedigreeForm")!=null)
			request.getSession().removeAttribute("pedigreeForm");
		if(request.getSession().getAttribute("metodo")!=null)
			request.getSession().removeAttribute("metodo");
		if(request.getSession().getAttribute("nombreInter")!=null)
			request.getSession().removeAttribute("nombreInter");
		if(request.getSession().getAttribute("idInter")!=null)
			request.getSession().removeAttribute("idInter");
		List razas = RazaDAO.findRazasPuras();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List categ = Animal.categorias();
		request.setAttribute("categorias",categ);
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("action","buscar");
		request.getSession().setAttribute("metodo","update");
		return mapping.findForward("initBuscar");
		
	}
	public ActionForward initPadron(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		PedigreeForm formA = (PedigreeForm)form;
		formA.setDesde("0");
		formA.setHasta("150000");
		formA.setComentario("");
		return mapping.findForward("initPadron");
	}
	public ActionForward buscar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		PedigreeForm formA = (PedigreeForm)form;
		String tipo = formA.getTipoReg();
		String numero = formA.getNumReg();
		String raza = formA.getRazaP();
		String sexo = formA.getSexo();
		Animal anim = (Animal) AnimalDAO.findByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			//if(anim!=null && anim.getCategoria().equals(Animal.CAT_PED)){	
		if(anim!=null){
				loadForm(anim,formA);
				request.setAttribute("action","update");
				validaciones(anim,request,formA);
				List razas = RazaDAO.findRazasPuras();
				List tipos = TipoRegistroDAO.findTiposValidosPedigree();
				List categ = Animal.categorias();
				request.setAttribute("categorias",categ);
				request.setAttribute("tipos",tipos);
				request.setAttribute("razas",razas);
				return mapping.findForward("add");
			}
			else{
				request.setAttribute("error2","El animal buscado no existe");
				List razas = RazaDAO.findRazasPuras();
				List tipos = TipoRegistroDAO.findTiposValidosPedigree();
				request.setAttribute("tipos",tipos);
				request.setAttribute("razas",razas);
				List categ = Animal.categorias();
				request.setAttribute("categorias",categ);
				request.setAttribute("action","buscar");
				return mapping.findForward("initBuscar");
			}
	}
	
	private void validaciones(Animal anim, HttpServletRequest request,PedigreeForm form) {
		if(request.getSession().getAttribute("metodo").equals("update")){
			if(!anim.getAllEventosSinLactanciaAbierta().isEmpty()){//si tiene eventos
				form.setTieneEventos(true);
			}
			if(anim.esHembra()){
				Hembra h = (Hembra)anim;
				if(!h.getCalificacions().isEmpty()){//si tiene calificaciones
					form.setTieneCalif(true);
				}
			}
			else{
				Macho m = (Macho)anim;
				if(!m.getEvtNuevoInds().isEmpty()){
					form.setTieneEvt(true);
				}
				if(!m.getHijosGeneticos().isEmpty()){//si tiene hijos
					form.setTieneHijos(true);
				}
			}
		}
	}

	private void loadForm(Animal anim, PedigreeForm formA) {
		Propietario p = PropietarioDAO.findPropietarioInt();
		formA.reset();
		if(p.getId().equals(anim.getPropietario().getId()))
			formA.setInter(true);
		formA.setIdPropInt(p.getId().toString());
		formA.setNombrePropInt(p.getNombreContacto());
		formA.setIdFoto((anim.getFoto()==null)?null:anim.getFoto().getId());
		formA.setIdAnimal(anim.getId().toString());
		formA.setNombre(anim.getNombre());
		formA.setRp(anim.getRP());
		formA.setCategoria(anim.getCategoria());
		formA.setTaraG(anim.getTaraG());
		formA.setSexo(anim.esHembra()? "H":"M");
		if(anim.getFechaNac()!=null)
			formA.setFechaNac(DateFormatUtils.format(anim.getFechaNac(),"dd/MM/yyyy"));
		formA.setRaza(anim.getRaza().getId());
		formA.setTipoRegId(anim.getRegIdentificador().getTipoRegistro().getId());
		formA.setNumRegId(anim.getRegIdentificador().getNumero());
		formA.setCoBajId(anim.getRegIdentificador().getCodigoBaja()==null ? null:anim.getRegIdentificador().getCodigoBaja().toString());
		formA.setTipoRegOri(anim.getRegOrigen().getTipoRegistro().getId());
		formA.setNumRegOri(anim.getRegOrigen().getNumero());
		formA.setCoBajOri(anim.getRegOrigen().getCodigoBaja()==null ? null :anim.getRegOrigen().getCodigoBaja().toString());
		Set au = new HashSet(anim.getRegistros());
		formA.setRegistros(au);
		Set aux = new HashSet(anim.getComentarios());
		formA.setComentarios(aux);
		Propietario prop = anim.getPropietario();
		formA.setIdProp(prop.getId().toString());
		formA.setNombreProp(prop.getNombreContacto());
		Establecimiento est = anim.getEstablecimiento();
		if(est!=null){
			formA.setIdTam(est.getId().toString());
			formA.setNombreEstab(est.getNombreContacto());
		}
		Propietario propCri = anim.getPropietarioCriador();
		if(propCri!=null){
			formA.setIdPropCri(propCri.getId().toString());
			formA.setNombrePropCri(propCri.getNombreContacto());
		}
		Establecimiento estCri = anim.getEstablecimientoCriador();
		if(estCri!=null){
			formA.setIdTamCriador(estCri.getId().toString());
			formA.setNombreEstabCri(estCri.getNombreContacto());
		}
		Hembra madre = anim.getMadreGenetica();
		if(madre!=null){
			formA.setIdMadre(madre.getId().toString());
			formA.setMadre(madre.getNombre() != null ? madre.getNombre()+", "+madre.getRegOrigen().toString() : "sin nombre"+", "+madre.getRegOrigen().toString());
		}
		Macho padre = anim.getPadre();
		if(padre!=null){
			formA.setIdPadre(padre.getId().toString());
			formA.setPadre(padre.getNombre() != null ? padre.getNombre()+", "+padre.getRegOrigen().toString() : "sin nombre"+", "+padre.getRegOrigen().toString());
		}
		if(anim.getDadorSemen()!=null)
			formA.setDado(anim.getDadorSemen().toString());
		if(anim.getNumeroTransf()!=null)
			formA.setTran(anim.getNumeroTransf().toString());
		if(anim.getNumeroAnalADN()!=null)
			formA.setAnls(anim.getNumeroAnalADN().toString());
		if(anim.getMellizo()!=null)
			formA.setMell(anim.getMellizo().toString());
		if(anim.getTipoServicio()!=null)
			formA.setTserv(anim.getTipoServicio().toString());
		formA.setDona(anim.getDonante());
		formA.setTrns(anim.getTransferencia());
		formA.setApodo(anim.getApodo());
		if(anim.getSRAFesb()!=null)
			formA.setFesb(DateFormatUtils.format(anim.getSRAFesb(),"dd/MM/yyyy"));
		if(anim.getFechaTransf()!=null)
			formA.setFtrt(DateFormatUtils.format(anim.getFechaTransf(),"dd/MM/yyyy"));
		if(anim.getFechaServicio()!=null)
			formA.setFserv(DateFormatUtils.format(anim.getFechaServicio(),"dd/MM/yyyy"));
		if(anim.getFechaUltObs()!=null)
			formA.setFobs(DateFormatUtils.format(anim.getFechaUltObs(),"dd/MM/yyyy"));
		formA.setRpti(anim.getRpti());
	}
	
	public ActionForward initAddEspecial(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		List razas = RazaDAO.findRazasPuras();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		List categ = Animal.categorias();
		request.setAttribute("categorias",categ);
		request.setAttribute("action","add");
		return mapping.findForward("add");
		
	}
	
	public ActionForward initUpdateEspecial(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		List razas = RazaDAO.findRazasPuras();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		List categ = Animal.categorias();
		request.setAttribute("categorias",categ);
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("action","update");
		return mapping.findForward("add");
	}
	
	public ActionForward initBuscarMadre(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setTipoReg("");
		pForm.setNumReg("");
		List razas = RazaDAO.findAll();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("action","buscarMadre");
		return mapping.findForward("initBuscarAnimal");
		
	}
	public ActionForward initBuscarPadre(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setTipoReg("");
		pForm.setNumReg("");
		List razas = RazaDAO.findAll();
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		request.setAttribute("tipos",tipos);
		request.setAttribute("razas",razas);
		request.setAttribute("action","buscarPadre");
		return mapping.findForward("initBuscarAnimal");
		
	}
	public ActionForward initBuscarTambo(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
			
			request.setAttribute("tambo","tambo");
			PedigreeForm pForm = (PedigreeForm)form;
			pForm.setNombreEstab("");
			pForm.setIdTam("");
			return mapping.findForward("initBuscarTambo");
		}
	public ActionForward initRegistros(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		List registros = new ArrayList();
		registros.addAll(pForm.getRegistros());
		request.setAttribute("registros",registros);
		return mapping.findForward("initRegistros");
	}
	public ActionForward initComentarios(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		List comentarios = new ArrayList();
		comentarios.addAll(pForm.getComentarios());
		request.setAttribute("comentarios",comentarios);
		return mapping.findForward("initComentarios");
	}
	public ActionForward initBuscarPropietario(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		request.setAttribute("prop","prop");
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setNombreProp("");
		pForm.setIdProp("");
		return mapping.findForward("initBuscarProp");
	}
	public ActionForward initAddRegistro(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setTipoReg("");
		pForm.setNumReg("");
		pForm.setCoBaj("");
		pForm.setEsId(false);
		pForm.setEsOri(false);
		request.setAttribute("action","add");
		List tipos = TipoRegistroDAO.findTiposValidosPedigree();
		request.setAttribute("tipos",tipos);
		return mapping.findForward("initAddRegistro");
	}
	public ActionForward initAddComentario(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setComentario("");
		return mapping.findForward("initAddComentario");
	}
	public ActionForward addComentario(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		AnimalComentario come = new AnimalComentario();
		come.setComentario(pForm.getComentario());
		come.setFecha(new Date());
		Usuario user = (Usuario) request.getSession().getAttribute(Tokens.CURRENTUSER);
		come.setUsuario(user);
		pForm.getComentarios().add(come);
		request.setAttribute("action","add");
		List comentarios = new ArrayList();
		comentarios.addAll(pForm.getComentarios());
		request.setAttribute("comentarios",comentarios);
		return mapping.findForward("initComentarios");
		
	}
	public ActionForward addRegistro(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
			if(pForm.isEsOri()){
				Date fechaBajORI = new Date();
				if(!StringUtils.isNumeric(pForm.getNumReg())){
					request.setAttribute("error","EL VALOR DE REGORI DEBE SER UN NUMERO");
					request.setAttribute("action","add");
					List tipos = TipoRegistroDAO.findTiposValidosPedigree();
					request.setAttribute("tipos",tipos);
					return mapping.findForward("initAddRegistro");
				}
				
				Registro r = RegistroDAO.create(pForm.getTipoReg(),pForm.getNumReg(),pForm.getCoBaj(),fechaBajORI);
					pForm.setTipoRegOri(pForm.getTipoReg());
					pForm.setNumRegOri(pForm.getNumReg());
					pForm.setCoBajOri(pForm.getCoBaj());
					pForm.setFeBajOri(pForm.getFeBaj());
					pForm.getRegistros().add(r);
			}	
			if(pForm.isEsId()){
				Date fechaBajID = new Date();
				
				Registro r = RegistroDAO.create(pForm.getTipoReg(),pForm.getNumReg(),pForm.getCoBaj(),fechaBajID);
				pForm.setTipoRegId(pForm.getTipoReg());
					pForm.setNumRegId(pForm.getNumReg());
					pForm.setCoBajId(pForm.getCoBaj());
					pForm.setFeBajId(pForm.getFeBaj());
					pForm.getRegistros().add(r);
			}
			
			if(!pForm.isEsId() && !pForm.isEsOri()){
				Date fechaBaj = new Date();
				Registro r = RegistroDAO.create(pForm.getTipoReg(),pForm.getNumReg(),pForm.getCoBaj(),fechaBaj);
				pForm.getRegistros().add(r);
			}
			List registros = new ArrayList();
			registros.addAll(pForm.getRegistros());
			
			request.setAttribute("registros",registros);
			return mapping.findForward("initRegistros");
	
		
	}
	public ActionForward initUpdateRegistro(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		PedigreeForm pForm = (PedigreeForm)form;
		String tipo = request.getParameter("tipo");
		String numero = request.getParameter("numero");
		
			Registro r = RegistroDAO.create(tipo,numero);//lo creo para buscarlo en la coleccion
			Iterator it = pForm.getRegistros().iterator();
			boolean corte = true;
			while(it.hasNext()&& corte){
				Registro h = (Registro)it.next();
				if(h.equals(r)){
					r=h;
					corte=false;
				}
			}
		//}
		pForm.setTipoReg(r.getTipoRegistro().getId());
		pForm.setNumReg(r.getNumero());
		pForm.setCoBaj(r.getCodigoBaja()==null ? null:r.getCodigoBaja().toString());
		if(pForm.getTipoReg().equals(pForm.getTipoRegId()) && pForm.getNumReg().equals(pForm.getNumRegId()))
			pForm.setEsId(true);
		else
			pForm.setEsId(false);
		if(pForm.getTipoReg().equals(pForm.getTipoRegOri()) && pForm.getNumReg().equals(pForm.getNumRegOri()))
			pForm.setEsOri(true);
		else
			pForm.setEsOri(false);
		request.setAttribute("action","update");
		return mapping.findForward("initAddRegistro");//es un update
	}
	public ActionForward updateRegistro(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
			
		PedigreeForm pForm = (PedigreeForm)form;
		Registro r = RegistroDAO.create(pForm.getTipoReg(),pForm.getNumReg());
		Iterator it = pForm.getRegistros().iterator();
		boolean corte = true;
		while(it.hasNext()&& corte){
				Registro h = (Registro)it.next();
				if(h.equals(r)){
					r=h;
					corte=false;
				}
			}
		if(pForm.getCoBaj().equals("0"))
				pForm.setCoBaj(null);
		
		if(pForm.isEsOri()){
			if(!StringUtils.isNumeric(pForm.getNumReg())){
				request.setAttribute("error","EL VALOR DE REGORI DEBE SER UN NUMERO");
				request.setAttribute("action","update");
				return mapping.findForward("initAddRegistro");
			}
			if(r.getCodigoBaja()==null && pForm.getCoBaj()!=null){
				r.setCodigoBaja(new Integer(pForm.getCoBaj()));
				if(pForm.getCoBaj().equals("1"))
					r.setFechaBaja(new Date());
			}
			else{
				if((r.getCodigoBaja()!=null && pForm.getCoBaj()==null)||(r.getCodigoBaja()==null && pForm.getCoBaj()==null))
					r.setCodigoBaja(null);
				else{//si los 2 !=null
					if(!r.getCodigoBaja().toString().equals(pForm.getCoBaj())){
						if(pForm.getCoBaj().equals("1")&& !(r.getCodigoBaja().toString().equals("1")))
							r.setFechaBaja(new Date());
						r.setCodigoBaja(new Integer(pForm.getCoBaj()));
					}
				}
			}
				pForm.setTipoRegOri(pForm.getTipoReg());
				pForm.setNumRegOri(pForm.getNumReg());
				pForm.setCoBajOri(pForm.getCoBaj());
				pForm.setFeBajOri(pForm.getFeBaj());
				pForm.getRegistros().remove(r);
				pForm.getRegistros().add(r);
		}
		else{
			if(pForm.getTipoReg().equals(pForm.getTipoRegOri()) && pForm.getNumReg().equals(pForm.getNumRegOri()))
			{
				pForm.setTipoRegOri("");
				pForm.setNumRegOri("");
			}
		}	
		if(pForm.isEsId()){
			if(r.getCodigoBaja()==null && pForm.getCoBaj()!=null){
				r.setCodigoBaja(new Integer(pForm.getCoBaj()));
				if(pForm.getCoBaj().equals("1"))
					r.setFechaBaja(new Date());
			}
			else{
				if((r.getCodigoBaja()!=null && pForm.getCoBaj()==null)||(r.getCodigoBaja()==null && pForm.getCoBaj()==null))
					r.setCodigoBaja(null);
				else{//si los 2 !=null
					if(!r.getCodigoBaja().toString().equals(pForm.getCoBaj())){
						if(pForm.getCoBaj().equals("1")&& !(r.getCodigoBaja().toString().equals("1")))
							r.setFechaBaja(new Date());
						r.setCodigoBaja(new Integer(pForm.getCoBaj()));
					}
				}
			}
			pForm.setTipoRegId(pForm.getTipoReg());
			pForm.setNumRegId(pForm.getNumReg());
			pForm.setCoBajId(pForm.getCoBaj());
			pForm.setFeBajId(pForm.getFeBaj());
			pForm.getRegistros().remove(r);
			pForm.getRegistros().add(r);
		}
		else{
			if(pForm.getTipoReg().equals(pForm.getTipoRegId()) && pForm.getNumReg().equals(pForm.getNumRegId())){
				pForm.setTipoRegId("");
				pForm.setNumRegId("");
			}
		}
		
		if(!pForm.isEsId() && !pForm.isEsOri()){
			if(r.getCodigoBaja()==null && pForm.getCoBaj()!=null){
				r.setCodigoBaja(new Integer(pForm.getCoBaj()));
				if(pForm.getCoBaj().equals("1"))
					r.setFechaBaja(new Date());
			}
			else{
				if((r.getCodigoBaja()!=null && pForm.getCoBaj()==null)||(r.getCodigoBaja()==null && pForm.getCoBaj()==null))
					r.setCodigoBaja(null);
				else{//si los 2 !=null
					if(!r.getCodigoBaja().toString().equals(pForm.getCoBaj())){
						if(pForm.getCoBaj().equals("1")&& !(r.getCodigoBaja().toString().equals("1")))
							r.setFechaBaja(new Date());
						r.setCodigoBaja(new Integer(pForm.getCoBaj()));
					}
				}
			}
			pForm.getRegistros().remove(r);	
			pForm.getRegistros().add(r);
			if(pForm.getTipoReg().equals(pForm.getTipoRegOri()) && pForm.getNumReg().equals(pForm.getNumRegOri()))
			{
				pForm.setTipoRegOri("");
				pForm.setNumRegOri("");
			}
			if(pForm.getTipoReg().equals(pForm.getTipoRegId()) && pForm.getNumReg().equals(pForm.getNumRegId())){
				pForm.setTipoRegId("");
				pForm.setNumRegId("");
			}
		}
		List registros = new ArrayList();
		registros.addAll(pForm.getRegistros());
		request.setAttribute("registros",registros);
		return mapping.findForward("initRegistros");
		
		}
	public ActionForward removeRegistro(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		PedigreeForm pForm = (PedigreeForm)form;
		String tipo = request.getParameter("tipo");
		String numero = request.getParameter("numero");
		Registro r = RegistroDAO.create(tipo,numero);
			if(pForm.getTipoRegOri().equals(tipo)&&(pForm.getNumRegOri().equals(numero))){
				pForm.setTipoRegOri("");
				pForm.setNumRegOri("");
			}
			if(pForm.getTipoRegId().equals(tipo)&&(pForm.getNumRegId().equals(numero))){
				pForm.setTipoRegId("");
				pForm.setNumRegId("");
			}
			pForm.getRegistros().remove(r);
		List registros = new ArrayList();
		registros.addAll(pForm.getRegistros());
		request.setAttribute("registros",registros);
		return mapping.findForward("initRegistros");
	}
	
	public ActionForward initBuscarPropietarioCri(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		request.setAttribute("prop","propCri");
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setNombrePropCri("");
		pForm.setIdPropCri("");
		return mapping.findForward("initBuscarProp");
	}
	public ActionForward initBuscarTamboCriador(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		request.setAttribute("tambo","tamboCriador");
		PedigreeForm pForm = (PedigreeForm)form;
		pForm.setNombreEstabCri("");
		pForm.setIdTamCriador("");
		return mapping.findForward("initBuscarTambo");
	}
	public ActionForward buscarMadre(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		PedigreeForm formA = (PedigreeForm)form;
		String tipo = formA.getTipoReg();
		String numero = formA.getNumReg();
		String raza = formA.getRazaP();
		String sexo = "H";
		if(formA.getNombrePM()==null || formA.getNombrePM().equals("") ){
			Animal anim = (Animal) AnimalDAO.findByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			if(anim!=null){	
				formA.setIdMadre(anim.getId().toString());
				formA.setMadre(anim.getNombre() != null ? anim.getNombre()+", "+anim.getRegOrigen().toString() : "sin nombre"+", "+anim.getRegOrigen().toString());
			}
			else{
				request.setAttribute("error2","La Hembra buscada no existe");
				List razas = RazaDAO.findRazasPuras();
				List tipos = TipoRegistroDAO.findTiposValidosPedigree();
				request.setAttribute("tipos",tipos);
				request.setAttribute("razas",razas);
				request.setAttribute("action","buscarMadre");
				return mapping.findForward("initBuscarAnimal");
			}
			return mapping.findForward("initAddEspecial");
		}else{
			List hembras = AnimalDAO.findByNombre(formA.getNombrePM(),sexo.toUpperCase()); 
			if(hembras.isEmpty())
				request.setAttribute("error3","La Hembra buscada no existe");
			
			List razas = RazaDAO.findRazasPuras();
			List tipos = TipoRegistroDAO.findTiposValidosPedigree();
			request.setAttribute("animales",hembras);
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("action","buscarMadre");
			formA.setNombrePM(null);
			
			return mapping.findForward("initBuscarAnimal");
		}
	}
	public ActionForward seleccionarAnimal(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String id = request.getParameter("id");
		PedigreeForm formA = (PedigreeForm)form;
		Animal anim = (Animal) AnimalDAO.findByPrimaryKey(Long.valueOf(id)); 
		if(anim!=null){	
			if(anim.esHembra()){
			formA.setIdMadre(anim.getId().toString());
			formA.setMadre(anim.getNombre() != null ? anim.getNombre()+", "+anim.getRegOrigen().toString() : "sin nombre"+", "+anim.getRegOrigen().toString());
			}
			else{
				formA.setIdPadre(anim.getId().toString());
				formA.setPadre(anim.getNombre() != null ? anim.getNombre()+", "+anim.getRegOrigen().toString() : "sin nombre"+", "+anim.getRegOrigen().toString());
			}
		}
		return mapping.findForward("initAddEspecial");
	}
	public ActionForward buscarPadre(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		PedigreeForm formA = (PedigreeForm)form;
		String tipo = formA.getTipoReg();
		String numero = formA.getNumReg();
		String raza = formA.getRazaP();
		String sexo = "M";
		if(formA.getNombrePM()==null || formA.getNombrePM().equals("") ){
			Animal anim = (Animal) AnimalDAO.findByRegistry(tipo.toUpperCase(),numero,raza.toUpperCase(),sexo.toUpperCase()); 
			if(anim!=null){	
				formA.setIdPadre(anim.getId().toString());
				formA.setPadre(anim.getNombre() != null ? anim.getNombre()+", "+anim.getRegOrigen().toString() : "sin nombre"+", "+anim.getRegOrigen().toString());
			}
			else{
				request.setAttribute("error2","El Macho buscado no existe");
				List razas = RazaDAO.findRazasPuras();
				List tipos = TipoRegistroDAO.findTiposValidosPedigree();
				request.setAttribute("tipos",tipos);
				request.setAttribute("razas",razas);
				request.setAttribute("action","buscarPadre");
				return mapping.findForward("initBuscarAnimal");
			}
			return mapping.findForward("initAddEspecial");
		}
		else{
			List machos =  AnimalDAO.findByNombre(formA.getNombrePM(),sexo.toUpperCase()); 
			if(machos.isEmpty())
				request.setAttribute("error3","El Macho buscado no existe");
			List razas = RazaDAO.findRazasPuras();
			List tipos = TipoRegistroDAO.findTiposValidosPedigree();
			request.setAttribute("tipos",tipos);
			request.setAttribute("razas",razas);
			request.setAttribute("action","buscarPadre");
			request.setAttribute("animales",machos);
			formA.setNombrePM("");
			return mapping.findForward("initBuscarAnimal");
		}
	}
	public ActionForward buscarTambos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		PedigreeForm reForm = (PedigreeForm) form;	
		List tambos = null;
		if(reForm.getIdProp()!=null && reForm.getPropSelect()){
			tambos = EstablecimientoDAO.findEstablecimientos(reForm.getIdTam(),null, reForm.getNombreEstab(), reForm.getNombreProp());
		}
		else
			tambos = EstablecimientoDAO.findEstablecimientos(null, reForm.getNombreEstab(), reForm.getIdTam(),new ArrayList<String>());
		if(tambos.isEmpty()){
				request.setAttribute("error","EL TAMBO NO EXISTE"); // ANTES ESTABLECIMIENTO
				request.setAttribute("tambo","tambo");
				return mapping.findForward("initBuscarTambo");			
			}
		request.setAttribute("tambo","tambo");
		request.setAttribute("listaTambos", tambos);
		return mapping.findForward("initBuscarTambo");		
	}
	
	public ActionForward buscarTambosCriador(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
			
			PedigreeForm reForm = (PedigreeForm) form;
			List tambos = null;
			if(reForm.getIdPropCri()!=null && reForm.getPropCriSelect())
				tambos = EstablecimientoDAO.findEstablecimientos( reForm.getIdTamCriador(),null, reForm.getNombreEstabCri(), reForm.getNombrePropCri());
			else	
				tambos = EstablecimientoDAO.findEstablecimientos(null, reForm.getNombreEstabCri(), reForm.getIdTamCriador(),new ArrayList<String>());
			if(tambos.isEmpty()){
				request.setAttribute("error","EL TAMBO NO EXISTE"); // ANTES ESTABLECIMIENTO
				request.setAttribute("tambo","tamboCriador");	
				return mapping.findForward("initBuscarTambo");			
			}
			request.setAttribute("tambo","tamboCriador");
			request.setAttribute("listaTambos", tambos);
			return mapping.findForward("initBuscarTambo");		
		}
		
	public ActionForward seleccionarTambo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
			
			String idTambo = request.getParameter("id");
			PedigreeForm reForm = (PedigreeForm) form;
			reForm.setIdTam(idTambo);
			Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(idTambo));
			reForm.setNombreEstab(estab.getNombreContacto());
			return mapping.findForward("initAddEspecial");		
		}

	public ActionForward seleccionarTamboCriador(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		String idTambo = request.getParameter("id");
		PedigreeForm reForm = (PedigreeForm) form;
		reForm.setIdTamCriador(idTambo);
		Establecimiento estab = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(idTambo));
		reForm.setNombreEstabCri(estab.getNombreContacto());
		return mapping.findForward("initAddEspecial");
	}
	
	public ActionForward buscarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PedigreeForm reForm = (PedigreeForm) form;
		List propietarios = PropietarioDAO.findPropietariosPorIDyNmbre(reForm.getIdProp(),reForm.getNombreProp());
		request.setAttribute("prop","prop");
		if(propietarios.isEmpty()){
				request.setAttribute("error","EL PROPIETARIO NO EXISTE"); // ANTES ESTABLECIMIENTO
				return mapping.findForward("initBuscarProp");			
			}
		request.setAttribute("propietarios", propietarios);
		return mapping.findForward("initBuscarProp");	
	}
	
	public ActionForward buscarPropietarioCri(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PedigreeForm reForm = (PedigreeForm) form;
		List propietarios = PropietarioDAO.findPropietariosPorIDyNmbre(reForm.getIdPropCri(),reForm.getNombrePropCri());
		request.setAttribute("prop","propCri");
		if(propietarios.isEmpty()){
				request.setAttribute("error","EL PROPIETARIO NO EXISTE"); // ANTES ESTABLECIMIENTO
				return mapping.findForward("initBuscarProp");			
			}
		request.setAttribute("propietarios", propietarios);
		
		return mapping.findForward("initBuscarProp");	
}
	public ActionForward seleccionarPropietario(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		request.setAttribute("prop","prop");
		String idProp = request.getParameter("id");
		PedigreeForm reForm = (PedigreeForm) form;
		Propietario p = PropietarioDAO.findByPrimaryKey(Long.valueOf(idProp));
		reForm.setIdProp(idProp);
		reForm.setPropSelect(true);
		reForm.setNombreProp(p.getNombreContacto());
		
		return mapping.findForward("initAddEspecial");		
	}
	
	public ActionForward seleccionarPropietarioCri(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		request.setAttribute("prop","propCri");
		String idProp = request.getParameter("id");
		PedigreeForm reForm = (PedigreeForm) form;
		Propietario p = PropietarioDAO.findByPrimaryKey(Long.valueOf(idProp));
		reForm.setIdPropCri(idProp);
		reForm.setPropCriSelect(true);
		reForm.setNombrePropCri(p.getNombreContacto());
		
		return mapping.findForward("initAddEspecial");		
		}
	public ActionForward add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		PedigreeForm pForm = (PedigreeForm) form;
		try{
			Hembra madre = null;
			Macho padre = null;
			Propietario prop=null;
			if(pForm.getInter())
				prop = PropietarioDAO.findByPrimaryKey(Long.valueOf((String) request.getSession().getAttribute("idInter")));
			else
				prop = PropietarioDAO.findByPrimaryKey(Long.valueOf(pForm.getIdProp()));
			Establecimiento tambo = null;
			if(StringUtils.isNotEmpty(pForm.getIdTam()))
				tambo = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(pForm.getIdTam()));
			Establecimiento tamboCriador=null;
			Propietario propCri = null;
			if(!StringUtils.isEmpty(pForm.getIdPropCri()))
				propCri = PropietarioDAO.findByPrimaryKey(Long.valueOf(pForm.getIdPropCri()));
				
			if(!StringUtils.isEmpty(pForm.getIdTamCriador()))
				tamboCriador = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(pForm.getIdTamCriador()));
			Date fechaN = null;
			if(StringUtils.isNotEmpty(pForm.getFechaNac())){
				fechaN= this.parse(pForm.getFechaNac(),"dd/MM/yyyy");
				if(fechaN==null){
					throw new ExcepcionIntegridad(MENSAJES.FECHA_INCORRECTA, new String[]{});
				}
			}
			Raza raza = RazaDAO.findByPrimaryKey(pForm.getRaza());
			boolean esHembra = pForm.getSexo().equals("H");
			if(!StringUtils.isEmpty(pForm.getIdMadre()))
				madre = (Hembra) AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdMadre())); 
			if(!StringUtils.isEmpty(pForm.getIdPadre()))
				padre = (Macho) AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdPadre())); 
			List msg = new ArrayList();
			Animal nuevoAnimal=null;
			if(madre == null && padre ==null){//es un empadronar
				nuevoAnimal= AnimalDAO.createAnimalPedEmpadronar(tamboCriador,propCri,raza,tambo,prop,null,esHembra,msg,fechaN,pForm.getRp(),pForm.getNombre());
			}
			else{
				nuevoAnimal = AnimalDAO.createAnimalPedPadres(tamboCriador,propCri,padre,madre,raza,tambo,prop,null,esHembra,msg,fechaN,pForm.getRp(),pForm.getNombre(),null,null);
			}
			setearRegistrosYComentarios(nuevoAnimal,pForm);
			setearOtros(nuevoAnimal,pForm);
			byte[] foto = (pForm.getFoto() != null && pForm.getFoto().getFileData().length > 0)?pForm.getFoto().getFileData():null;
			if (foto != null) {
	    		Foto imagen = FotoDAO.createPersistent(foto);
	    		nuevoAnimal.setFoto(imagen);
	    	}
			HibernateFactory.getSession().save(nuevoAnimal);
			if(request.getSession().getAttribute("pedigreeForm")!=null)
				request.getSession().removeAttribute("pedigreeForm");
			if(request.getSession().getAttribute("metodo")!=null)
				request.getSession().removeAttribute("metodo");
			if(request.getSession().getAttribute("nombreInter")!=null)
				request.getSession().removeAttribute("nombreInter");
			if(request.getSession().getAttribute("idInter")!=null)
				request.getSession().removeAttribute("idInter");
			request.setAttribute("action","add");
			pForm.reset();
			return mapping.findForward("comunicacion");
			
		} catch (ExcepcionIntegridad e1) {
			ProcCodMsg cod=null;
			try{
				cod = ProcCodMsgDAO.findByPrimaryKey(e1.getCodigoError());
			}catch (Exception e2) {
				request.setAttribute("error",e1.getCodigoError());
				return mapping.findForward("initAddEspecial");
			}
			if(cod==null){
				request.setAttribute("error",e1.getCodigoError());
			}
			else{
					ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
					request.setAttribute("error",msg.getInformacion());
			}
			return mapping.findForward("initAddEspecial");		
		}
	}
	
	private void setearOtros(Animal nuevoAnimal, PedigreeForm form) throws ExcepcionIntegridad {
		nuevoAnimal.setDadorSemen(StringUtils.isNotEmpty(form.getDado()) ? new Integer(form.getDado()) : null);
		nuevoAnimal.setNumeroTransf(StringUtils.isNotEmpty(form.getTran()) ? new Integer(form.getTran()) : null);
		nuevoAnimal.setNumeroAnalADN(StringUtils.isNotEmpty(form.getAnls()) ? new Integer(form.getAnls()) : null);
		nuevoAnimal.setMellizo(StringUtils.isNotEmpty(form.getMell()) ? new Integer(form.getMell()) : null);
		nuevoAnimal.setTipoServicio(StringUtils.isNotEmpty(form.getTserv()) ? new Integer(form.getTserv()) : null);
		nuevoAnimal.setDonante(StringUtils.isNotEmpty(form.getDona()) ? form.getDona() : null);
		nuevoAnimal.setTransferencia(StringUtils.isNotEmpty(form.getTrns()) ? form.getTrns() : null);
		nuevoAnimal.setApodo(StringUtils.isNotEmpty(form.getApodo()) ? form.getApodo() : null);
		nuevoAnimal.setRpti(StringUtils.isNotEmpty(form.getRpti()) ? form.getRpti() : null);
		nuevoAnimal.setCategoria(form.getCategoria());
		nuevoAnimal.setTaraG(form.getTaraG());
		if(StringUtils.isNotEmpty(form.getFesb())){
			Date nuevo = this.parse(form.getFesb(),"dd/MM/yyyy");
			if(nuevo!=null)
				nuevoAnimal.setSRAFesb(nuevo);
			else{
				throw new ExcepcionIntegridad(MENSAJES.FECHA_INCORRECTA, new String[]{});
			}
		}
		else
			nuevoAnimal.setSRAFesb(null);
		if(StringUtils.isNotEmpty(form.getFtrt())){
			Date nuevo =this.parse(form.getFtrt(),"dd/MM/yyyy");
			if(nuevo!=null)
				nuevoAnimal.setFechaTransf(nuevo);
			else{
				throw new ExcepcionIntegridad(MENSAJES.FECHA_INCORRECTA, new String[]{});
			}
		}
		else
			nuevoAnimal.setFechaTransf(null);
		if(StringUtils.isNotEmpty(form.getFserv())){
			Date nuevo = this.parse(form.getFserv(),"dd/MM/yyyy");
			if(nuevo!=null)
				nuevoAnimal.setFechaServicio(nuevo);
			else
				throw new ExcepcionIntegridad(MENSAJES.FECHA_INCORRECTA, new String[]{});
		}
		else
			nuevoAnimal.setFechaServicio(null);
	}
	private void agregarRegistros(Animal nuevoAnimal,PedigreeForm form) throws ExcepcionIntegridad{
		Date fechaBajID = null;
		if(StringUtils.isNotEmpty(form.getFeBajId()))
					fechaBajID=this.parse(form.getFeBajId(),"dd/MM/yyyy");
		Date fechaBajOri= null;
		if(StringUtils.isNotEmpty(form.getFeBajOri()))
					fechaBajOri=this.parse(form.getFeBajOri(),"dd/MM/yyyy");
		Registro regOri=RegistroDAO.create(form.getTipoRegOri(),form.getNumRegOri(),form.getCoBajOri(),fechaBajOri);
		Registro regId = RegistroDAO.create(form.getTipoRegId(),form.getNumRegId(),form.getCoBajId(),fechaBajID);
		if(regId.equals(regOri)){
			nuevoAnimal.setRegIdentificador(regId);
			nuevoAnimal.setRegOrigen(regId);
			nuevoAnimal.addRegistro(regId);
		}
		else{
			nuevoAnimal.setRegOrigen(regOri);
			nuevoAnimal.addRegistro(regOri);
			nuevoAnimal.setRegIdentificador(regId);
			nuevoAnimal.addRegistro(regId);
		}
		Iterator it = form.getRegistros().iterator();
		while(it.hasNext()){
			Registro d = (Registro)it.next();
			
			if(!nuevoAnimal.getRegistros().contains(d)){
				Registro reg=RegistroDAO.create(d.getTipoRegistro().getId(),d.getNumero(),d.getCodigoBaja()==null ?null:d.getCodigoBaja().toString(),d.getFechaBaja());
				nuevoAnimal.addRegistro(reg);
			}
		}
		
		
	}
	private void setearRegistrosYComentarios(Animal nuevoAnimal,PedigreeForm form) throws ExcepcionIntegridad {
		
		nuevoAnimal.setComentarios(null);
		nuevoAnimal.setComentarios(new HashSet());
		nuevoAnimal.addComentarios(form.getComentarios());
		Registro regOri=RegistroDAO.find(form.getTipoRegOri(),form.getNumRegOri(),form.getRaza(),form.getSexo());
		//si existe el registro y es de otro animal excepcion
		if((regOri !=null && nuevoAnimal.getId()==null)||
				(regOri !=null && nuevoAnimal.getId()!=null && (!nuevoAnimal.getId().equals(regOri.getAnimal().getId())))){
			throw new ExcepcionIntegridad(MENSAJES.REG_ORI_YA_ASIGNADO, new String[]{});
		}
		Date fechaBajID = null;
		if(StringUtils.isNotEmpty(form.getFeBajId()))
					fechaBajID=this.parse(form.getFeBajId(),"dd/MM/yyyy");
		Date fechaBajOri = null;
		if(StringUtils.isNotEmpty(form.getFeBajOri()))
				fechaBajOri=this.parse(form.getFeBajOri(),"dd/MM/yyyy");
		Set res = new HashSet();
		Registro regId = null;
		if((StringUtils.isNotEmpty(form.getTipoRegId()))&&(StringUtils.isNotEmpty(form.getNumRegId())))
			regId = RegistroDAO.create(form.getTipoRegId(),form.getNumRegId(),form.getCoBajId(),fechaBajID);
		else
			regId = RegistroDAO.create(form.getTipoRegOri(),form.getNumRegOri(),form.getCoBajOri(),fechaBajOri);//si no cargo regid pongo el regori como id tmb
		if(!nuevoAnimal.getRegistros().isEmpty()){
			Iterator it2 = nuevoAnimal.getRegistros().iterator();
			while(it2.hasNext()){
				Registro r = (Registro)it2.next();
				if(r.equals(regId)){
					r.setCodigoBaja(regId.getCodigoBaja());
					r.setFechaBaja(regId.getFechaBaja());
					nuevoAnimal.setRegIdentificador(r);
					res.add(r);
				}
				
			}
		}
		else{
			nuevoAnimal.setRegIdentificador(regId);
			nuevoAnimal.addRegistro(regId);
		}
		regOri = RegistroDAO.create(form.getTipoRegOri(),form.getNumRegOri(),form.getCoBajOri(),fechaBajOri);
		if(!nuevoAnimal.getRegistros().isEmpty()){
					Iterator it2 = nuevoAnimal.getRegistros().iterator();
					while(it2.hasNext()){
						Registro r = (Registro)it2.next();
						if(r.equals(regOri)){
							r.setCodigoBaja(regOri.getCodigoBaja());
							r.setFechaBaja(regOri.getFechaBaja());
							nuevoAnimal.setRegOrigen(r);
							res.add(r);
						}
						
					}
			}
		else{
					nuevoAnimal.setRegOrigen(regOri);
					nuevoAnimal.addRegistro(regOri);
			}
		List borrar = new ArrayList();
		Iterator it2 = nuevoAnimal.getRegistros().iterator();
		while(it2.hasNext()){
			Registro r2 = (Registro)it2.next();
			if(!form.getRegistros().contains(r2)){
				borrar.add(r2);
			}
			else{
				if(!r2.equals(regId)&&(!r2.equals(regOri))){//si no es regOri ni regId lo actualizo ya que reg ori y reg id los actualice antes
					Iterator it = form.getRegistros().iterator();
					while(it.hasNext()){
						Registro r = (Registro)it.next();
						if(r.equals(r2)){
							r2.setCodigoBaja(r.getCodigoBaja());
							r2.setFechaBaja(r.getFechaBaja());
							res.add(r2);
						}
					}
				}
			}
		}
		if(!borrar.isEmpty()){
			Iterator it = borrar.iterator();
			while(it.hasNext()){
				Registro r = (Registro)it.next();
				nuevoAnimal.getRegistros().remove(r);
				if(r.getId()!=null)
					RegistroDAO.remove(r);
			}
		}
		if(!form.getRegistros().isEmpty()){
			Iterator de = form.getRegistros().iterator();
			while(de.hasNext()){
				Registro d = (Registro)de.next();
				if(!nuevoAnimal.getRegistros().contains(d))
					nuevoAnimal.addRegistro(d);
			}
		}
		if(!res.isEmpty()){
			Iterator rd = res.iterator();
			while(rd.hasNext()){
				Registro f = (Registro)rd.next();
				nuevoAnimal.getRegistros().remove(f);
				nuevoAnimal.getRegistros().add(f);
			}
		}
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		
		PedigreeForm pForm = (PedigreeForm) form;
		try{
			Animal animal = AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdAnimal()));
			boolean esHembra = pForm.getSexo().equals("H");
			if(esHembra == animal.esHembra())
				copyAnimal(pForm,animal);
			Hembra madre = null;
			Macho padre = null;
			Propietario prop=null;
			if(pForm.getInter())
				prop = PropietarioDAO.findByPrimaryKey(Long.valueOf(pForm.getIdPropInt()));
			else
				prop = PropietarioDAO.findByPrimaryKey(Long.valueOf(pForm.getIdProp()));
			Establecimiento tambo = null;
			if(StringUtils.isNotEmpty(pForm.getIdTam()))
				tambo = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(pForm.getIdTam()));
			
			Establecimiento tamboCriador=null;
			Propietario propCri = null; 
			if(!StringUtils.isEmpty(pForm.getIdPropCri()))
				propCri = PropietarioDAO.findByPrimaryKey(Long.valueOf(pForm.getIdPropCri()));
			if(!StringUtils.isEmpty(pForm.getIdTamCriador()))
				tamboCriador = EstablecimientoDAO.findByPrimaryKey(Long.valueOf(pForm.getIdTamCriador()));
			Raza raza = RazaDAO.findByPrimaryKey(pForm.getRaza());
			//boolean esHembra = pForm.getSexo().equals("H");
			if(!StringUtils.isEmpty(pForm.getIdMadre()))
				if(!new Long(pForm.getIdMadre()).equals(animal.getId()))
					madre = (Hembra) AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdMadre())); 
				else
					throw new ExcepcionIntegridad(MENSAJES.ANIMAL_IGUAL_A_LA_MADRE, new String[]{});
			
			if(!StringUtils.isEmpty(pForm.getIdPadre()))
				if(!new Long(pForm.getIdPadre()).equals(animal.getId()))
					padre = (Macho) AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdPadre())); 
				else
					throw new ExcepcionIntegridad(MENSAJES.ANIMAL_IGUAL_A_LA_PADRE, new String[]{});
			List msg = new ArrayList();
			if(esHembra == animal.esHembra()){
				if(madre == null && padre ==null)//es un empadronar
					AnimalDAO.actualizarAnimalPedEmpadronar(animal,tamboCriador,propCri,raza,tambo,prop,null,esHembra,msg,animal.getFechaNac(),pForm.getRp(),pForm.getNombre(),false);
				else
					AnimalDAO.actulizarAnimalPedPadres(animal,tamboCriador,propCri,padre,madre,raza,tambo,prop,null,esHembra,msg,animal.getFechaNac(),pForm.getRp(),pForm.getNombre(),false);
				animal.setFechaUltObs(new Date());
				setearRegistrosYComentarios(animal,pForm);
				setearOtros(animal,pForm);
				HibernateFactory.getSession().update(animal);
			}
			else{
				Date fNac = animal.getFechaNac();
				HibernateFactory.getSession().delete(animal);
				Animal nuevo =null;
				if(madre == null && padre ==null)//es un empadronar
					nuevo= AnimalDAO.createAnimalPedEmpadronar(tamboCriador,propCri,raza,tambo,prop,null,esHembra,msg,fNac,pForm.getRp(),pForm.getNombre());
				else
					nuevo = AnimalDAO.createAnimalPedPadres(tamboCriador,propCri,padre,madre,raza,tambo,prop,null,esHembra,msg,fNac,pForm.getRp(),pForm.getNombre(),null,null);
				//setearRegistrosYComentarios(nuevo,pForm);
				this.agregarRegistros(nuevo,pForm);
				setearOtros(nuevo,pForm);
				
				HibernateFactory.getSession().save(nuevo);
			}
			if(request.getSession().getAttribute("pedigreeForm")!=null)
				request.getSession().removeAttribute("pedigreeForm");
			if(request.getSession().getAttribute("metodo")!=null)
				request.getSession().removeAttribute("metodo");
			if(request.getSession().getAttribute("nombreInter")!=null)
				request.getSession().removeAttribute("nombreInter");
			if(request.getSession().getAttribute("idInter")!=null)
				request.getSession().removeAttribute("idInter");
			request.setAttribute("action","update");
			pForm.reset();
			return mapping.findForward("comunicacion");
			
		} catch (ExcepcionIntegridad e1) {
			ProcCodMsg cod=null;
			try{
				cod = ProcCodMsgDAO.findByPrimaryKey(e1.getCodigoError());
			}catch (Exception e2) {
				request.setAttribute("error",e1.getCodigoError());
				StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
				return mapping.findForward("initAddEspecial");
			}
			if(cod==null){
				request.setAttribute("error",e1.getCodigoError());
			}
			else{
					ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
					request.setAttribute("error",msg.getInformacion());
			}
			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
			return mapping.findForward("initAddEspecial");		
		}
	}
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {	
		try{
				PedigreeForm pForm = (PedigreeForm) form;
				Animal animal = AnimalDAO.findByPrimaryKey(Long.valueOf(pForm.getIdAnimal()));
				AnimalDAO.validarEliminacion(animal);
				AnimalDAO.delete(animal);	
				request.setAttribute("action","eliminar");
				pForm.reset();
				return mapping.findForward("comunicacion");
			
		} catch (ExcepcionIntegridad e1) {
			ProcCodMsg cod=null;
			try{
				cod = ProcCodMsgDAO.findByPrimaryKey(e1.getCodigoError());
			}catch (Exception e2) {
				request.setAttribute("error",e1.getCodigoError());
				StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
				return mapping.findForward("initAddEspecial");
			}
			if(cod==null){
				request.setAttribute("error",e1.getCodigoError());
			}
			else{
					ProcMsg msg = ProcMsgDAO.create(e1.getCodigoError(),ProcMsg.ERROR,e1.getValores());
					request.setAttribute("error",msg.getInformacion());
			}
			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
			return mapping.findForward("initAddEspecial");		
		}
		}
	private void copyAnimal(PedigreeForm form, Animal animal) throws ExcepcionIntegridad, FileNotFoundException, IOException {
		animal.setNombre(form.getNombre());
		//Date date =this.parse(form.getFechaNac(),"dd/MM/yyyy");
		Date date = null;
		if(StringUtils.isNotEmpty(form.getFechaNac())){
			date= this.parse(form.getFechaNac(),"dd/MM/yyyy");
			if(date==null){
				throw new ExcepcionIntegridad(MENSAJES.FECHA_INCORRECTA, new String[]{});
			}
		}
		byte[] foto = (form.getFoto() != null && form.getFoto().getFileData().length > 0)?form.getFoto().getFileData():null;
		if (foto != null) {
    		Foto imagen = FotoDAO.createPersistent(foto);
    		animal.setFoto(imagen);
    	}
		animal.setFechaNac(date);			
		this.setearRegistrosYComentarios(animal,form);
		this.setearOtros(animal,form);
	}
	public Date parse(String s, String s1) 
	{
	    try
	    {
	        SimpleDateFormat simpledateformat;
	        (simpledateformat = new SimpleDateFormat(s1 != null ? s1 : "dd-MM-yyyy HH:mm:ss")).setLenient(false);
	        return simpledateformat.parse(s);
	    }
	    catch(Exception exception)
	    {
	        return null;
	    }
	}
	public ActionForward generarPadron(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		List data = new ArrayList();
		log.warn("NUMEROOOOO->>>>> "+request.getParameter("numero"));
		Integer numero1 = Integer.valueOf(request.getParameter("numero"));
		Integer numero2 = numero1 + 100000;
		if(numero1.intValue() == 0)
			numero2 = 150000;
		log.warn("NUMEROOOOO111111->>>>> "+numero1);
		log.warn("NUMEROOOOO222222->>>>> "+numero2);
		data = AnimalDAO.findAnimalesMachosPedigreeRangosAnimalHBA(numero1,numero2);
		/*Establecimiento tambo = EstablecimientoDAO.findByPrimaryKey(Long.parseLong("9681"));
		data = AnimalDAO.findAnimalesPorRpyPropietarioyEclo("",null,null,tambo);*/
		log.warn("dataaaaaa->"+data.size());
		String REPORTES_DIR="";
		String REPORTES_DIR_EXCEL="";
		String IMAGENES_DIR="";
		try {
			REPORTES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReportes").getValue();
			//REPORTES_DIR_EXCEL = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteExcel").getValue();
			REPORTES_DIR_EXCEL = AtributoDAO.findByNombre("CONF_PADRON_MACHOS_EXCEL").getValorPorDefecto().getValor();
			IMAGENES_DIR = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirImagenesReportes").getValue();
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//HCER QUE VUELVA A LA PANTALLA INFORMANDO QUE INTENTE DENUEVO
		}
		
		
		ProjectReport report = new ProjectReport(this.getContextPath(REPORTES_DIR), IMAGENES_DIR);
		String nombreGenerado =  "/Padron_animales_MACHOS_HBA_desde"+numero1+"_hasta_"+numero2+".xls";
		log.warn("EXCELLLL->"+this.getContextPath(REPORTES_DIR_EXCEL)+nombreGenerado);
		
		try {
				//byte[] bytes = report.makePadronPedigree(data,this.getContextPath(REPORTES_DIR_EXCEL)+nombreGenerado);
				byte[] bytes = report.makePadronPedigree(data,REPORTES_DIR_EXCEL+nombreGenerado);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward("initPadron");

	}
public ActionForward descargarArchivo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
	String nombre = request.getParameter("name");	
	//String REPORTES_DIR_EXCEL = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteExcel").getValue();
	String REPORTES_DIR_EXCEL = AtributoDAO.findByNombre("CONF_PADRON_MACHOS_EXCEL").getValorPorDefecto().getValor();
//	File root = new File(this.getContextPath(REPORTES_DIR_EXCEL));
	File root = new File(REPORTES_DIR_EXCEL);

	    File[] files = root.listFiles(); 
	    File arch = null;
	    for(int i =0;i<files.length;i++){
	    	if(files[i].getName().equals(nombre)){
	    		arch = files[i];
	    		break;
	    	}
	    }
	    response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(nombre));
	    InputStream is = new FileInputStream(arch);
	    PrintWriter pw = response.getWriter();
		int c = -1;
		while ((c = is.read()) != -1) {
			pw.print((char) c);
		}
		is.close();
		pw.flush();
		pw = null;
	return null;
	}
public ActionForward descargarPadron(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		
		//String REPORTES_DIR_EXCEL = Sicel3Conf.getConf().getConfProcesamiento().getChild("dirReporteExcel").getValue();
		String REPORTES_DIR_EXCEL = AtributoDAO.findByNombre("CONF_PADRON_MACHOS_EXCEL").getValorPorDefecto().getValor();
//		 File root = new File(this.getContextPath(REPORTES_DIR_EXCEL));
		 File root = new File(REPORTES_DIR_EXCEL);

		    File[] files = root.listFiles(); 
		    List archivos = new ArrayList();
		    for(int i =0;i<files.length;i++){
		    	ArchivoExcel ar = new ArchivoExcel();
		    	if(files[i].getName().contains(".xls")){
			    	ar.setName(files[i].getName());
			    	ar.setModificacion(String.format("%tF %<tT", new Date(files[i].lastModified())));
			    	archivos.add(ar);
		    	}
		   }
		 request.setAttribute("archivos",archivos);
		return mapping.findForward("listadoPadron");

	}
	
	
	private String getContextPath(String subPath)
	{
		return getServlet().getServletContext().getRealPath(subPath);
	}


	
}
