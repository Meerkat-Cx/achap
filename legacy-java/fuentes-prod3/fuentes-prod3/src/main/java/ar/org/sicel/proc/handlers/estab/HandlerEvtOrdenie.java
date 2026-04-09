package ar.org.sicel.proc.handlers.estab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EventoDAO;
import ar.org.sicel.persistence.EventosPorFechaYTipo;
import ar.org.sicel.persistence.EvtAnimal;
import ar.org.sicel.persistence.EvtControlAnimal;
import ar.org.sicel.persistence.EvtControlEstablecimiento;
import ar.org.sicel.persistence.EvtControlEstablecimientoDAO;
import ar.org.sicel.persistence.EvtEstModificacion;
import ar.org.sicel.persistence.EvtEstModificacionDAO;
import ar.org.sicel.persistence.EvtEstablecimiento;
import ar.org.sicel.persistence.EvtOrdenieAnimal;
import ar.org.sicel.persistence.EvtOrdenieAnimalDAO;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.handlers.HandlerEvtEst;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Control;
import ar.org.sicel.proc.v1.lote.Medicion;
import ar.org.sicel.proc.v1.lote.Ordenie;
import ar.org.sicel.proc.v1.lote.OrdenieAnimal;
import ar.org.sicel.proc.v1.lote.TEvento;
import ar.org.sicel.proc.v1.lote.types.STProdMedObj;
import ar.org.sicel.util.DateUtils;

public class HandlerEvtOrdenie implements HandlerEvtEst {

	// MIRAR Si se informa mal un animal dentro de un evento de control, se
	// rechaza el evento entero, esto esta bien ?

	// MIRAR En las salidas no se están informando los resultados del evento
	// control (solo sale en un resumen, medio raro).

	static Logger log = Logger.getLogger(HandlerEvtOrdenie.class);

	@SuppressWarnings( { "unchecked", "unchecked" })
	public EvtEstablecimiento handleEvt(Establecimiento objEst, TEvento evento,
			List<ProcMsg> msgs, ProcLote procLote, Date fechaEnvioLote ) throws ExcepcionIntegridad {
		Control controlEst = (Control) evento;

		log.info("Procesando Evento Control" + controlEst.getIDEvt());
		
		if (controlEst.getModificaOBaja() != null) {
			Evento evtOld = EventoDAO.findByPrimaryKey(controlEst.getModificaOBaja().getIDEvt());
			if (!(evtOld instanceof EvtControlEstablecimiento)) {
				log.error("Evento informado no es del tipo EventoControlEstablecimiento, probado");
				throw new ExcepcionIntegridad(MENSAJES.ERROR_TIPO_EVT, new String[]{(evtOld==null)?"NO EXISTE":String.valueOf(evtOld.toString()),"Control"});
			}
			Long idEclo = evtOld.getEstablecimiento().getEclo().getId(); // id de la eclo que informo el alta
			Long idEcloInformada = controlEst.getModificaOBaja().getInformante(); // id de la eclo informa
			if (!idEclo.equals(idEcloInformada)) {
				// VERIFICO QUE LA ECLO DEL ESTABLECIMIENTO SE LA INFORMADA EN EL XML
				log.error("El establecimiento que informa el alta a modificar no es el informado");
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_BAJA_ECLO_NO_COINCIDE, new String[]{idEclo.toString(),(idEcloInformada==null)?"NO EXISTE":idEcloInformada.toString()});				
			}
			
			EvtEstModificacion eventoModificacion = ((EvtControlEstablecimiento)evtOld).ejecutarModificacion(controlEst,msgs, objEst);
			EvtEstModificacionDAO.saveEvtEstModificacion(eventoModificacion);
			return eventoModificacion;
		}
		else {			
			// si el establecimiento no tiene de control  siga siga
			if (objEst.getMetodoControl() != null && StringUtils.equals("CRIA", objEst.getMetodoControl().getCodigo())) {				
				throw new ExcepcionIntegridad(MENSAJES.ESTABLECIMIENTO_FACT, new String[] {objEst.getId().toString()}, msgs);
			}

			// TODO: falta ControlEsAM, MuestreoEsAM
			EvtControlEstablecimiento evtControlEst = EvtControlEstablecimientoDAO
					.create(objEst, controlEst.getFecha(), controlEst
							.getMetodoControl().toString(), true, controlEst
							.getTipoMuestreo().toString(), true, controlEst
							.getHorarios().getHorarioCount(), msgs, controlEst.getHorarios());
			//System.out.println("EL ESTAB ES-->"+evtControlEst.getEstablecimiento().getId());
			Enumeration animales = controlEst.getOrdeniesAnimal()
					.enumerateOrdenieAnimal();
			while (animales.hasMoreElements()) {
				OrdenieAnimal a = (OrdenieAnimal) animales.nextElement();
				Enumeration controles = a.getOrdenies().enumerateOrdenie();
				try {
					evtControlEst.procesarOrdeniesAnimal(objEst, msgs, controlEst,
								animales, a, controles);
				} catch (ExcepcionIntegridad ei) {
					ProcMsg msg = ProcMsgDAO.create(ei.getCodigoError(),
							ProcMsg.ERROR, ei.getValores());
					msgs.add(msg);
					msg.setEstaAgregado(true);
					ResultadosUtil.addResultado(a,msg);
				}
			}
			if (evtControlEst.getControlesAnimales().isEmpty()) {
				log.error("rechazo porque no se genero ningun Control Animal");
				throw new ExcepcionIntegridad(MENSAJES.ERROR_CONTROL_ANIMAL_CERO, new String[] {String.valueOf(controlEst.getIDEvt())},msgs);
			}
	
			HibernateFactory.getSession().save(evtControlEst);
	
			return evtControlEst;
		}
	}

}
