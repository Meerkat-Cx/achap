/*
 * Created on 20/05/2005
 */
package ar.org.sicel.persistence.util;

import java.util.Iterator;
import java.util.List;

import ar.org.sicel.persistence.ProcAnimal;
import ar.org.sicel.persistence.ProcEvtAnimal;
import ar.org.sicel.persistence.ProcEvtEst;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.proc.v1.lote.Animal;
import ar.org.sicel.proc.v1.lote.Estab;
import ar.org.sicel.proc.v1.lote.Lote;
import ar.org.sicel.proc.v1.lote.OrdenieAnimal;
import ar.org.sicel.proc.v1.lote.Rdo;
import ar.org.sicel.proc.v1.lote.Rdos;
import ar.org.sicel.proc.v1.lote.TEvento;

/**
 * @author pablo
 * 
 * 
 * Esta clase se usa para agregar Resultados (generarlamente dados como objetos ar.org.sicel.persistence.ProcMsg manejados por hibernate)
 * a nodos del XML.
 * Es util dado que en Castor hay que tener cuidado con los null, si es el primer resultado que se le agrega al nodo o no, etc.
 * 
 *  
 */
public class ResultadosUtil {
	
	public static Rdos addResultado(Rdos rdos, Rdo message) {
		if (rdos == null) {
			rdos = new Rdos();
		}
		rdos.addRdo(message);
		return rdos;
	}
	
	public static void addResultado(Lote lote, ProcMsg message) {
		lote.setRdos(addResultado(lote.getRdos(),message.getAsCastorRdo()));
	}
	
	public static void addResultado(Estab est, ProcMsg message) {
		est.setRdos(addResultado(est.getRdos(),message.getAsCastorRdo()));
	}
	
	public static void addResultado(Animal animal, ProcMsg message) {
		animal.setRdos(addResultado(animal.getRdos(),message.getAsCastorRdo()));
	}

	public static void addResultado(TEvento evt, ProcMsg message) {
		evt.setRdos(addResultado(evt.getRdos(),message.getAsCastorRdo()));
	}

	public static void addResultado(OrdenieAnimal evt, ProcMsg message) {
		evt.setRdos(addResultado(evt.getRdos(),message.getAsCastorRdo()));
	}

	public static void addAll(ProcAnimal proc, Animal animal, List<ProcMsg> msgs ) {
		for (Iterator iter = msgs.iterator(); iter.hasNext();) {
			ProcMsg procMsg = (ProcMsg) iter.next();
			addResultado(animal,procMsg);
			proc.addMsg(procMsg);
		}
	}
	
	public static void addAll(ProcEvtAnimal proc, TEvento evento, List<ProcMsg> msgs ) {
		for (Iterator iter = msgs.iterator(); iter.hasNext();) {
			ProcMsg procMsg = (ProcMsg) iter.next();
			addResultado(evento,procMsg);
			proc.addMsg(procMsg);
		}
	}
	
	public static void addAll(ProcEvtEst proc, TEvento evento, List<ProcMsg> msgs ) {
		for (Iterator iter = msgs.iterator(); iter.hasNext();) {
			ProcMsg procMsg = (ProcMsg) iter.next();
			if(!procMsg.isEstaAgregado()){
				addResultado(evento,procMsg);
				proc.addMsg(procMsg);
			}	
		}
	}
}
