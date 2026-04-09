package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;

public class EvtSecadaDAO {
	
	public static EvtSecada create(Establecimiento est, Hembra animal, Date fecha, String motivo, List msgs) throws ExcepcionIntegridad {
		EvtSecada secada = new EvtSecada(est,fecha,animal,msgs);
		secada.setMotivo(motivo);
		animal.addEventoAnimal(secada,msgs);
		//animal.setEstadoRetroactivo(secada, msgs);
		animal.setEstadoRetroactivo(secada.getFecha(), msgs,secada.getNombreTipo());
		return secada;
	}

	public static void updateEventoSecada(EvtSecada secada) {
		HibernateFactory.getSession().update(secada);
	}

}
