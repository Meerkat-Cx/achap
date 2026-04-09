package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;

public class EvtPrenezDAO {
	
	public static EvtPrenez create(Establecimiento est, Hembra hem, Date fecha, Boolean esPositivo, List msgs) throws ExcepcionIntegridad {
		EvtPrenez pre = new EvtPrenez(est,fecha,hem,msgs);
		pre.setEsPositivo(esPositivo);
		hem.addEventoAnimal(pre,msgs);
		//hem.setEstadoRetroactivo(pre, msgs);
		hem.setEstadoRetroactivo(pre.getFecha(), msgs,pre.getNombreTipo());
		return pre;
	}

	public static void updateEvento(EvtPrenez prenez) {
		HibernateFactory.getSession().update(prenez);		
	}

}
