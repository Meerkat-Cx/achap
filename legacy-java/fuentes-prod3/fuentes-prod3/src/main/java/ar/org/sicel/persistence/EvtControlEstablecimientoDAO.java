package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.Horarios;
import ar.org.sicel.util.DateUtils;


public class EvtControlEstablecimientoDAO {
	
	/**
	 * Validacion adicional: se crea si el metodo de contro es el mismo que tiene el establecimiento
	 * @param est
	 * @param fecha
	 * @param metodoControl
	 * @param controlEsAM
	 * @param tipoMuestreo
	 * @param muestreoEsAM
	 * @param numOrdenies
	 * @param msgs
	 * @param horarios
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	public static EvtControlEstablecimiento create(Establecimiento est, Date fecha, String metodoControl, 
			Boolean controlEsAM, String tipoMuestreo, Boolean muestreoEsAM, Integer numOrdenies, List msgs,
			Horarios horarios) throws ExcepcionIntegridad {
		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {
			//TODO QUE PASA SI EL METODO DE CONTROL DEL ESTABLECIMIENTO ES NULL (NO TIENE)
			//if (est.getMetodoControl()!=null && !StringUtils.equals(est.getMetodoControl().getCodigo(), metodoControl)) {
			if (est.getMetodoControl()!=null){
				MetodoControl met = est.getMetodoEnFecha(fecha);
				if(!StringUtils.equals(met.getCodigo(), metodoControl)) 
					throw new ExcepcionIntegridad(MENSAJES.DISTINTO_METODO_INFORMADO, new String[] {est.getId().toString(),DateUtils.format(fecha,"dd/MM/yyyy"), 
							metodoControl,met.getCodigo()}, msgs);
			}
		}	
		EvtControlEstablecimiento ord = new EvtControlEstablecimiento(est,fecha);		
		
		ord.setControlEsAM(controlEsAM);
		try {
			MetodoControl metodoControlNuevo = MetodoControlDAO.findByPrimaryKey(metodoControl);
			ord.setMetodoControl(metodoControlNuevo);
		} catch (HibernateException he){
			throw new ExcepcionIntegridad(MENSAJES.ERROR_GET_METODO_CONTROL, new String[]{metodoControl});
		}

		ord.setMuestreoEsAM(muestreoEsAM);
		ord.setNumOrdenies(numOrdenies);
		ord.setTipoMuestreo(tipoMuestreo);
		if(numOrdenies > 0){
			ord.setHoraIniOrdenie1(horarios.getHorario(0).getHoraIniOrdenie().toDate());
			ord.setHoraFinOrdenie1(horarios.getHorario(0).getHoraFinOrdenie().toDate());
		}
		if(numOrdenies > 1){
			ord.setHoraIniOrdenie2(horarios.getHorario(1).getHoraIniOrdenie().toDate());
			ord.setHoraFinOrdenie2(horarios.getHorario(1).getHoraFinOrdenie().toDate());
		}
		if(numOrdenies > 2){
			ord.setHoraIniOrdenie3(horarios.getHorario(2).getHoraIniOrdenie().toDate());
			ord.setHoraFinOrdenie3(horarios.getHorario(2).getHoraFinOrdenie().toDate());
		}
		ord.setControlesAnimales(new HashSet());
		ord.setEstab(est);
		
		return ord;
	}

	/**
	 * Obtiene el control animal para una fecha dada y un establecimiento dado
	 * @param est - establecimiento
	 * @param fecha - fecha del evento control
	 * @return EvtControlEstablecimiento
	 */
	public static EvtControlEstablecimiento findByFecha(Establecimiento est, Date fecha) {
		Criteria criteriaCE = HibernateFactory.getSession().createCriteria(EvtControlEstablecimiento.class);
		criteriaCE.add(Expression.eq("estab",est));
		criteriaCE.add(Expression.eq("fecha",fecha));
		return (EvtControlEstablecimiento)(criteriaCE.uniqueResult());
	}

	/**
	 * Obtiene el control animal para un animal dado, para una fecha dada y un establecimiento
	 * @param est - establecimiento
	 * @param fecha - fecha del evento control
	 * @param animal - animal
	 * @return EvtControlEstablecimiento
	 */
	public static EvtControlEstablecimiento findByFecha(/*Establecimiento est,*/ Date fecha, Animal animal) {
		Criteria criteriaCE = HibernateFactory.getSession().createCriteria(EvtControlEstablecimiento.class);
		//criteriaCE.add(Expression.eq("estab",est));
		criteriaCE.add(Expression.eq("fecha",fecha));
		return (EvtControlEstablecimiento)(criteriaCE.createCriteria("controlesAnimales").add(Expression.eq("animal",animal))).uniqueResult();
	}	
	
	public static void updateEventoControlEstablecimiento(EvtControlEstablecimiento evtControlEstablecimiento) {
		HibernateFactory.getSession().update(evtControlEstablecimiento);		
	}

}
