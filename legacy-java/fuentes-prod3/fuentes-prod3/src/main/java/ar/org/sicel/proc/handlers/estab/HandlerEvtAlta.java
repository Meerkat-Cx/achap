/*
 * Created on 13/04/2005
 */
package ar.org.sicel.proc.handlers.estab;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.CONF;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAltaDAO;
import ar.org.sicel.persistence.EvtCria;
import ar.org.sicel.persistence.EvtEstModificacion;
import ar.org.sicel.persistence.EvtEstModificacionDAO;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Registro;
import ar.org.sicel.persistence.TipoRegistro;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.handlers.HandlerEvtEst;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Alta;
import ar.org.sicel.proc.v1.lote.CompoRacial;
import ar.org.sicel.proc.v1.lote.ComposicionItem;
import ar.org.sicel.proc.v1.lote.Padres;
import ar.org.sicel.proc.v1.lote.TEvento;
import ar.org.sicel.proc.v1.lote.types.STSexo;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

/**
 * @author pablo
 */
public class HandlerEvtAlta implements HandlerEvtEst {
	private static Logger log = Logger.getLogger(HandlerEvtAlta.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.org.sicel.proc.handlers.HandlerEvtEst#handleEvt(java.lang.Object,
	 *      ar.org.sicel.proc.gen.TEvento)
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento,
			List<ProcMsg> mensajes, ProcLote procLote, Date fechaEnvioLote) throws ExcepcionIntegridad {
		
		// MIRAR Habría que agregar al resultado del alta, la categoria que se le asigno al animal.
		
		// MIRAR Devolver como resultado del evento la categoria asignada al animal como un valor mas del resultado

		// MIRAR Si no informa ni "empadronar" ni "composicion racial" ni
		// "padres" hace de cuenta que es "padres". Cambiar esto en el xsd para que sea obligatorio poner 1.
		

		log.warn("Procesando alta " + evento.getIDEvt());
		EvtAlta resultado = null;		
		Alta alta = (Alta) evento;	
		boolean fechaCorrecta = true;
		EvtCria evtCriaAux = null;
		EvtReproduccion evtRep = null;
		if(alta.getSenasa()!=null){
			if(org.apache.commons.lang.StringUtils.isEmpty(alta.getSenasa().getRpSenasa()))
				throw new ExcepcionIntegridad(MENSAJES.INFORMO_PARTE_INFORMACION_SENASA,new String[]{});			
		}
		if (alta.getModificaOBaja() != null) {// si es una modificacion			
			Evento evtOld = EventoDAO.findByPrimaryKey(alta.getModificaOBaja().getIDEvt());
			if (! (evtOld instanceof EvtAlta)) {
				// VERIFICO QUE EL EVENTO INFORMADO ES REALMENTE UNA ALTA
				log.error("evento no es una alta, se probo esta rama, si es null tambien pasa por aca, mejorarlo");
				throw new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT, new String[]{(evtOld==null)?"NO EXISTE":evtOld.toString(),"Alta"}); 				
			}
			Long idEclo = evtOld.getEstablecimiento().getEclo().getId(); // id de la eclo que informo el alta
			Long idEcloInformada = alta.getModificaOBaja().getInformante(); // id de la eclo informa
			//if (!objEst.getId().equals(evtOld.getEstablecimiento().getId())) {
			if (!idEclo.equals(idEcloInformada)) {
				// VERIFICO QUE LA ECLO DEL ESTABLECIMIENTO SE LA INFORMADA EN EL XML
				log.error("El establecimiento que informa el alta a modificar no de informado");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			// LLAMO A EJECUTAR LA MODIFICACION, SE MODIFICARIA EL EVENTO ALTA Y EL ANIMAL DEL EVENTO ALTA
			EvtEstModificacion eventoModificacion = ((EvtAlta)evtOld).ejecutarModificacion(alta, mensajes, objEst, procLote);
			EvtEstModificacionDAO.saveEvtEstModificacion(eventoModificacion);
			return eventoModificacion;
		}
		else {	
			String rp = alta.getRp();
			if (Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
					alta.getFecha()) && !Animal.validarRP(rp))
				throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{rp});
			boolean esHembra = alta.getSexo().equals(STSexo.F);
			String razaDeclarada = RazaUtil.getRazaEnBase(alta.getRaza());
			if(razaDeclarada == null)
				throw new ExcepcionIntegridad(MENSAJES.NO_SETEA_RAZA, new String[]{});
			Raza declarada = RazaDAO.findByPrimaryKey(razaDeclarada);
			String nombre = alta.getNombre();
			
	
			if (alta.getTEvtAltaChoice().getEmpadronar() != null) { // es un alta con empadronar
				Date fechaNac = alta.getTEvtAltaChoice().getEmpadronar()
						.getFechaNacimiento();
				if(alta.getFecha().before(fechaNac))
					throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
				Map comp = new HashMap(); 
				comp.put(declarada.getEspecie().getDesconocida(),new Float(1));
				resultado = EvtAltaDAO.create(alta.getTEvtAltaChoice(),objEst, alta.getFecha(), null,
						esHembra, rp, razaDeclarada, comp, nombre, fechaNac,
						mensajes, procLote,alta.getSenasa());
				
			} else if (alta.getTEvtAltaChoice().getCompoRacial() != null) { // es un alta especificando comp racial
				CompoRacial compoRacial = alta.getTEvtAltaChoice().getCompoRacial();
				Registro reg = null;
				Date fechaNac = null;
				if (compoRacial.getFechaNacimiento() != null)
					fechaNac = compoRacial.getFechaNacimiento();
				if(alta.getFecha().before(fechaNac))
					throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
				Enumeration e = alta.getTEvtAltaChoice().getCompoRacial()
						.getComposicion().enumerateComposicionItem();
				Map comp = new HashMap();
				while (e.hasMoreElements()) {
					ComposicionItem item = (ComposicionItem) e.nextElement();
					String raza = RazaUtil.getRazaEnBase(item.getElemRacial()
							.getRaza());
					Float valor = item.getElemRacial().getValor();
					Raza r = RazaDAO.findByPrimaryKey(raza);
					if (r == null)
						throw new ExcepcionIntegridad(MENSAJES.RAZA_NO_EXISTE,
								new String[] { raza });
					comp.put(r, valor);
				}
				resultado = EvtAltaDAO.create(alta.getTEvtAltaChoice(),objEst, alta.getFecha(), reg,
						esHembra, rp, razaDeclarada, comp, nombre, fechaNac,
						mensajes, procLote,alta.getSenasa());

			} else { // es un alta especificando los padres
				Macho padreGen = null;
				Hembra madreParto = null;
				Hembra madreGeneticaT = null;
					
				Padres padres = alta.getTEvtAltaChoice().getPadres();
					
				if(padres.getMadreGen()==null && padres.getPadreGen()==null){//lo tomo como un empadronar
					Date fechaNac = alta.getTEvtAltaChoice().getPadres().getFechaNacimiento();
					Map comp = new HashMap(); 
					comp.put(declarada.getEspecie().getDesconocida(),new Float(1));
					
					if(alta.getFecha().before(fechaNac))
						throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
					
					resultado = EvtAltaDAO.create(alta.getTEvtAltaChoice(),objEst, alta.getFecha(), null,
							esHembra, rp, razaDeclarada, comp, nombre, fechaNac,
							mensajes, procLote,alta.getSenasa());
				}else if (padres.getMadreGen() != null) {//si informa madre, adentro pregunt si informo padre
					String tRegMadre = TipoRegistroUtil.getTipoRegistroEnBase(padres.getMadreGen().getTReg());
					String nRegMadre = padres.getMadreGen().getNReg();
					String razaM = RazaUtil.getRazaEnBase(padres.getMadreGen().getRaza());
					madreParto = AnimalDAO.findExistentHembraByRegistry(tRegMadre, nRegMadre,razaM);
					//Se controla que la madre haya tenido un evento reproduccion en la fecha de nacimiento informada del animal
					evtRep = (EvtReproduccion)madreParto.getEventoEnFecha(padres.getFechaNacimiento(),Evento.EVT_TIPO_REP);
					//cambio importante, si no hay rep asociada se rechaza el alta
					if(evtRep == null){//si no tiene EVT reproduccion en esa fecha es un empadronar
						throw new ExcepcionIntegridad(MENSAJES.MADRE_SIN_REPROD,new String[] {padres.getFechaNacimiento().toString(), madreParto.getRegistroOrigen()});
						/*ProcMsg cod = ProcMsgDAO.create(MENSAJES.MADRE_SIN_REPROD,ProcMsg.WARNING,new String[] {evento.getFecha().toString(), madreGen.getRegistroID()});
						mensajes.add(cod);
						Map comp = new HashMap(); 
						comp.put(declarada.getEspecie().getDesconocida(),new Float(1));
						resultado = EvtAltaDAO.create(alta.getTEvtAltaChoice(),objEst, alta.getFecha(), null,
								esHembra, rp, razaDeclarada, comp, nombre, padres.getFechaNacimiento(),
								mensajes, procLote,alta.getSenasa());*/
					}else{ // tiene un evento reproducción, si no coincide el padre se rechaza el alta
						
						String razaP ="";
						if((padres.getPadreGen()!=null)){
							String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(padres.getPadreGen().getTReg());
							String nRegPadre = padres.getPadreGen().getNReg();
							razaP = RazaUtil.getRazaEnBase(padres.getPadreGen().getRaza());
							padreGen = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,razaP);	
						}
						if(evtRep.getEvtServicio()!=null){
							if((padreGen==null)||(!padreGen.equals(evtRep.getEvtServicio().getPadreGenetico())))
								throw new ExcepcionIntegridad(MENSAJES.NO_COINCIDE_PADRE_CON_REP,new String[] {alta.getRp(),padreGen==null?"NINGUNO":padreGen.getRegistroOrigen(),evtRep.getEvtServicio()==null?"NINGUNO":evtRep.getEvtServicio().getPadreGenetico().getRegistroOrigen()});
							if(evtRep.getEvtServicio().getMadreGenetica()!=null){
								madreGeneticaT = evtRep.getEvtServicio().getMadreGenetica();
							}
						}
						else{
							if(padreGen!=null)
								throw new ExcepcionIntegridad(MENSAJES.NO_COINCIDE_PADRE_CON_REP,new String[] {alta.getRp(), padreGen==null?"NINGUNO":padreGen.getRegistroOrigen(),evtRep.getEvtServicio()==null?"NINGUNO":evtRep.getEvtServicio().getPadreGenetico().getRegistroOrigen()});
						}
						boolean sexoCorrespondiente = false;
						for(Object obj : evtRep.getEvtCrias()){
							EvtCria evtCria = (EvtCria)obj;
							
							if((evtCria.getSexo().equals("F")&&esHembra)||(evtCria.getSexo().equals("M")&&!esHembra)){
								if(evtCria.getCria()!=null){
									throw new ExcepcionIntegridad(MENSAJES.YA_SE_ENCUENTRA_INSCRIPTA,
																new String[] {madreParto.getRegistroOrigen(),
																			evtCria.getCria().getRP(),
																			evtCria.getCria().getRegistroOrigen()});
								}
								sexoCorrespondiente = true;
								evtCriaAux = evtCria;
								break;
							}
						}
						if(!sexoCorrespondiente)
							throw new ExcepcionIntegridad(MENSAJES.SEXO_NO_CORRESPONDIENTE,
									new String[] {esHembra?"HEMBRA":"MACHO"});
						Date fechaLoteRep = evtRep.getProcEvtAnimal().getLote().getTEnvio();
						int diasLoteEvt = Integer.parseInt(madreParto.getRaza().getParametro("Dias maximo entre fechas de evento y de lote"));
						fechaCorrecta = DateUtils.menos(fechaLoteRep, diasLoteEvt).before(evtRep.getFecha());
						if(!fechaCorrecta)
							mensajes.add(ProcMsgDAO.create(MENSAJES.SUPERA_TIEMPO_LIMITE_LOTE_EVT_REP,ProcMsg.WARNING,new String[]{}));							
						
						if(padres.getPadreGen()!=null){//si informo padre y madre
							/*String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(padres.getPadreGen().getTReg());
							String nRegPadre = padres.getPadreGen().getNReg();
							String razaP = RazaUtil.getRazaEnBase(padres.getPadreGen().getRaza());
							padreGen = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,razaP);*/	
							if(madreGeneticaT!=null)
								razaM=madreGeneticaT.getRaza().getId();
							
							if(alta.getFecha().before(padres.getFechaNacimiento()))
								throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
							
							if(razaM.equals(razaP)){
								if(razaM.equals(razaDeclarada)){//caso 1
									resultado = EvtAltaDAO.create(objEst, alta.getFecha(), null,esHembra, rp, 
															razaDeclarada,(madreGeneticaT!=null)?madreGeneticaT:madreParto, padreGen,nombre, padres.getFechaNacimiento(), mensajes, procLote,alta.getSenasa(),fechaCorrecta);
								}else{
									//LA RAZA DECLARADA DEBE SER LA MISMA QUE LA DE LOS PADRES
									throw new ExcepcionIntegridad(
										MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{rp});
								}
							}else{//raza distinta entre padre y madre
								resultado = EvtAltaDAO.create(objEst, alta.getFecha(), null,esHembra, rp, 
										razaDeclarada,(madreGeneticaT!=null)?madreGeneticaT:madreParto, padreGen,nombre, padres.getFechaNacimiento(), mensajes, procLote,alta.getSenasa(),fechaCorrecta);
							}
						}else{//si informo madre sola
							if(alta.getFecha().before(padres.getFechaNacimiento()))
									throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
							
							if(razaM.equals(razaDeclarada)){//caso 3
								resultado = EvtAltaDAO.create(objEst, alta.getFecha(), null,esHembra, rp, 
										razaDeclarada,(madreGeneticaT!=null)?madreGeneticaT:madreParto, padreGen,nombre, padres.getFechaNacimiento(), mensajes, procLote,alta.getSenasa(),fechaCorrecta);
							}else{
								//SE INFORMO MADRE SOLA Y LA RAZA DE LA MADRE NO COINCIDE CON LA DE LA CRIA
								throw new ExcepcionIntegridad(
										MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{rp});
							}
						}
					}
				}	
				if (padres.getPadreGen() != null && padres.getMadreGen()==null) {
					//NO PUEDE INFORMAR UN ALTA CON PADRES SIN ESPECIFICAR SU MADRE
					throw new ExcepcionIntegridad(
							MENSAJES.INFORMA_PADRE_Y_NO_MADRE, new String[]{rp});
				}
			}
		
			alta.setIDAnim(resultado.getAnimal().getId());// esto solo en alta																		
			resultado.getAnimal().setearCategoria(fechaCorrecta); // esto en alta y modificacion
			//MODIF
			
			Animal a = resultado.getAnimal();
			Registro regAsignado = resultado.getAnimal().getRegOrigen();
			ProcMsg msg = ProcMsgDAO.create(MENSAJES.REGISTRO_ASIGNADO, ProcMsg.INFO,new String[] {a.getRP(), regAsignado.getNumero(), regAsignado.getTipoRegistro().getId(), a.getCategoria()});
			mensajes.add(msg);
			
			if(evtCriaAux!=null){
				for(Object obj : evtRep.getEvtCrias()){
					EvtCria evtCria = (EvtCria)obj;
					if(evtCria.equals(evtCriaAux)){
						evtCria.setCria(resultado.getAnimal());
						evtCria.setRP(resultado.getAnimal().getRP());
						}
					}
				HibernateFactory.getSession().update(evtRep);
			}
				
			try {
				HibernateFactory.getSession().save(resultado);
			} catch (HibernateException e) {
				throw new ErrorFatal(
						"Error al querer guardar el evento Alta, HandlerEvtAlta", e);
			}
			return resultado;
		}		
		
	}
}
