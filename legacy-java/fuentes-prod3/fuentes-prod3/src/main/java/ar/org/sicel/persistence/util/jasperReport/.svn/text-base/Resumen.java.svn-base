package ar.org.sicel.persistence.util.jasperReport;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EvtAlta;
import ar.org.sicel.persistence.EvtAnimalModificacion;
import ar.org.sicel.persistence.EvtEstModificacion;
import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcEstablecimiento;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcLote;
import ar.org.sicel.proc.v1.lote.CambioRP;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Evt;
import ar.org.sicel.proc.v1.lote.Lote;

public class Resumen {

	public void contarEventosAceptados(ProcLote pLote, Map resumenEventos) {
		//contar cuantos eventos de cada tipo, y cuantos fueron aceptados
		//contar cuantos errores se encontraron (para todos los eventos en general)
		
		Iterator it = pLote.getProcEstablecimientos().iterator();
		while (it.hasNext()) {
			ProcEstablecimiento pEst = (ProcEstablecimiento) it.next();
			Iterator evtEsts = pEst.getProcEvtEsts().iterator();
			while (evtEsts.hasNext()) {
				ProcEvtEst evtEst = (ProcEvtEst)evtEsts.next();
				String tipo = evtEst.getEvtEstablecimiento().getNombreTipo();
				if(tipo.equals(Evento.EVT_TIPO_MODIFEST))
					tipo=((EvtEstModificacion)evtEst.getEvtEstablecimiento()).getEvtEstModificado().getNombreTipo();
				ResumenEventos r = (ResumenEventos) resumenEventos.get(tipo);
				r.incAceptados(1);
			}
			Iterator animals =  pEst.getProcAnimals().iterator();
			while (animals.hasNext()) {
				ProcAnimal procAnimal = (ProcAnimal) animals.next();
				Iterator evtsAnimal = procAnimal.getProcEvtAnimals().iterator();
				while (evtsAnimal.hasNext()) {
					ProcEvtAnimal evt = (ProcEvtAnimal)evtsAnimal.next();
					String tipo = evt.getEvtAnimal().getNombreTipo();
					if(tipo.equals(Evento.EVT_TIPO_MODIFANI))
						tipo=((EvtAnimalModificacion)evt.getEvtAnimal()).getEvtAnimalModificado().getNombreTipo();
					ResumenEventos r = (ResumenEventos) resumenEventos.get(tipo);
					r.incAceptados(1);
				}
			}
		}
		
	}
	public void contarEventosAceptadosEstab(ProcLote pLote, Map resumenEventos,Long id) {
		//contar cuantos eventos de cada tipo, y cuantos fueron aceptados
		//contar cuantos errores se encontraron (para todos los eventos en general)
		 
				Iterator it = pLote.getProcEstablecimientos().iterator();
				while (it.hasNext()) {
					ProcEstablecimiento pEst = (ProcEstablecimiento) it.next();
					if(id.equals(pEst.getEstablecimiento().getId())){
						Iterator evtEsts = pEst.getProcEvtEsts().iterator();
						while (evtEsts.hasNext()) {
							ProcEvtEst evtEst = (ProcEvtEst)evtEsts.next();
							
							String tipo = evtEst.getEvtEstablecimiento().getNombreTipo();
							if(tipo.equals(Evento.EVT_TIPO_MODIFEST))
								tipo=((EvtEstModificacion)evtEst.getEvtEstablecimiento()).getEvtEstModificado().getNombreTipo();
							ResumenEventos r = (ResumenEventos) resumenEventos.get(tipo);
							r.incAceptados(1);
						}
						Iterator animals =  pEst.getProcAnimals().iterator();
						while (animals.hasNext()) {
							ProcAnimal procAnimal = (ProcAnimal) animals.next();
							Iterator evtsAnimal = procAnimal.getProcEvtAnimals().iterator();
							while (evtsAnimal.hasNext()) {
								ProcEvtAnimal evt = (ProcEvtAnimal)evtsAnimal.next();
								String tipo = evt.getEvtAnimal().getNombreTipo();
								if(tipo.equals(Evento.EVT_TIPO_MODIFANI))
									tipo=((EvtAnimalModificacion)evt.getEvtAnimal()).getEvtAnimalModificado().getNombreTipo();
								
								ResumenEventos r = (ResumenEventos) resumenEventos.get(tipo);
								r.incAceptados(1);
							}
						}
					}
				}
		
	}
	public Map cargarEventos(){
		
			Map resumen = new TreeMap();
			
			ResumenEventos altas = new ResumenEventos("Alta");
			resumen.put(EvtAlta.EVT_TIPO_ALT, altas);
			
			ResumenEventos bajas = new ResumenEventos("Baja");
			resumen.put(Evento.EVT_TIPO_BAJ,bajas);
			
			ResumenEventos bajasEv = new ResumenEventos("Baja Evento");
			resumen.put(Evento.EVT_TIPO_BAJA_EVENTO,bajasEv);
			
			ResumenEventos servicio = new ResumenEventos("Servicio");
			resumen.put(Evento.EVT_TIPO_SVC,servicio);
			
			ResumenEventos prenez = new ResumenEventos("Preñez");
			resumen.put(Evento.EVT_TIPO_PRE,prenez);
			
			ResumenEventos reproduccion = new ResumenEventos("Parto/Aborto");
			resumen.put(Evento.EVT_TIPO_REP,reproduccion);
			
			ResumenEventos estado = new ResumenEventos("Estado");
			resumen.put(Evento.EVT_TIPO_EST,estado);		
			
			/**
			 * TODO ESTO SERA CONTROL Y ORDEÑE
			 */
			ResumenEventos ordenies = new ResumenEventos("Control y Ordeñe");
			resumen.put(Evento.EVT_TIPO_CONTROL_EST,ordenies);
			
			ResumenEventos transferencias = new ResumenEventos("Transferencia");
			resumen.put(Evento.EVT_TIPO_TRA,transferencias);
			
			ResumenEventos info = new ResumenEventos("Informacion");
			resumen.put(Evento.EVT_TIPO_INF,info);

			ResumenEventos secadas = new ResumenEventos("Secadas");
			resumen.put(Evento.EVT_TIPO_SEC,secadas);
			
			ResumenEventos cambio = new ResumenEventos("Cambio de RP");
			resumen.put(Evento.EVT_TIPO_CAMBIO_RP,cambio);
			
			return resumen;
	}
	
	@SuppressWarnings({"unchecked","unchecked", "unchecked", "unchecked", "unchecked", "unchecked", "unchecked", "unchecked"})
//	public Map contarEventosInformados(Lote lote) {
	public Map contarEventosInformados(Lote lote) {
		Map resumen = new TreeMap();
		
		Enumeration estabs =  lote.getEstabs().enumerateEstab();
		ResumenEventos altas = new ResumenEventos("Alta");
		resumen.put(EvtAlta.EVT_TIPO_ALT, altas);
		
		ResumenEventos bajas = new ResumenEventos("Baja");
		resumen.put(Evento.EVT_TIPO_BAJ,bajas);
		
		ResumenEventos bajasEv = new ResumenEventos("Baja Evento");
		resumen.put(Evento.EVT_TIPO_BAJA_EVENTO,bajasEv);
		
		ResumenEventos servicio = new ResumenEventos("Servicio");
		resumen.put(Evento.EVT_TIPO_SVC,servicio);
		
		ResumenEventos cambio = new ResumenEventos("Cambio de RP");
		resumen.put(Evento.EVT_TIPO_CAMBIO_RP,cambio);
		
		ResumenEventos prenez = new ResumenEventos("Preñez");
		resumen.put(Evento.EVT_TIPO_PRE,prenez);
		
		ResumenEventos reproduccion = new ResumenEventos("Parto/Aborto");
		resumen.put(Evento.EVT_TIPO_REP,reproduccion);
		
		ResumenEventos estado = new ResumenEventos("Estado");
		resumen.put(Evento.EVT_TIPO_EST,estado);		
		
		/**
		 * TODO ESTO SERA CONTROL Y ORDEÑE
		 */
		ResumenEventos ordenies = new ResumenEventos("Control y Ordeñe");
		resumen.put(Evento.EVT_TIPO_CONTROL_EST,ordenies);
		
		
		ResumenEventos transferencias = new ResumenEventos("Transferencia");
		resumen.put(Evento.EVT_TIPO_TRA,transferencias);
		
		ResumenEventos info = new ResumenEventos("Informacion");
		resumen.put(Evento.EVT_TIPO_INF,info);

		/*ResumenEventos semens = new ResumenEventos("Semen");
		resumen.put(Evento.EVT_TIPO_SMN,semens);
		
		
		ResumenEventos producciones = new ResumenEventos("Producciones");
		resumen.put(Evento.EVT_TIPO_PRO,producciones);*/
		
		ResumenEventos secadas = new ResumenEventos("Secadas");
		resumen.put(Evento.EVT_TIPO_SEC,secadas);
		
		
		while (estabs.hasMoreElements()) {
			Estab estab =  (Estab)estabs.nextElement();
			if (estab.getEvtsEstab() != null) {
				if (estab.getEvtsEstab().getAltas() != null)
					altas.incInformados(estab.getEvtsEstab().getAltas().getAltaCount());
				if (estab.getEvtsEstab().getBajasEvt() != null)
					bajasEv.incInformados(estab.getEvtsEstab().getBajasEvt().getBajaEvtCount());
				if (estab.getEvtsEstab().getControles() != null)
					ordenies.incInformados(estab.getEvtsEstab().getControles().getControlCount());
				/*if (estab.getEvtsEstab().getSemens() != null)
					semens.incInformados(estab.getEvtsEstab().getSemens().getSemenCount());*/
			}
			if (estab.getAnims() != null) {
				Enumeration animales = estab.getAnims().enumerateAnimal();
				while (animales.hasMoreElements()) {
					ar.org.sicel.proc.v1.lote.Animal animal = (ar.org.sicel.proc.v1.lote.Animal) animales.nextElement();
					Enumeration eventos = animal.getEvts().enumerateEvt();
					while (eventos.hasMoreElements()) {
						Evt evt = (Evt) eventos.nextElement();
						if (evt.getBaja() != null)
							bajas.incInformados(1);
						if (evt.getEstado() != null)
							estado.incInformados(1);
						if (evt.getInfo() != null)
							info.incInformados(1);
						if (evt.getPrenez() != null)
							prenez.incInformados(1);
						if (evt.getCambioRP() != null)
							cambio.incInformados(1);
						if(evt.getReprod() != null)
							reproduccion.incInformados(1);
						if(evt.getSecada() != null)
							secadas.incInformados(1);
						if(evt.getServ() != null)
							servicio.incInformados(1);
						if(evt.getTrans() != null)
							transferencias.incInformados(1);
					}
				}
			}
		}		
		
		return resumen;
	}	
	
	
	
}
