package ar.org.sicel.persistence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtLactanciaMigrada.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtLactanciaMigrada
 */
public abstract class EvtLactanciaMigradaDAO {
    // ---------------- create method --------------------

    /**
     * Creates a(n) EvtLactanciaMigrada object.
     *
     * @param nroLact
     * @param tipoLact
     * @param fechaInicio
     * @param dias
     * @param anios
     * @param meses
     * @param tipoCert
     * @param nroCert
     * @param leche
     * @param grasa
     * @param proteinas
     * @param celulas
     * @param solidosNoGrasos
     * @param solidosTotales
     * @param urea
     * @param tipoRegistro
     * @param numeroRegistro
     * @param ordenies
     * @param control
     * @param muestreo
     * @param analisis
     * @param epar
     * @param eppar
     * @param diasa
     * @param ipar
     * @param flac
     * @param ilac
     * @param dcon
     * @param tlac
     * @param eac
     * @param cnor
     * @param fecha
     * @return EvtLactanciaMigrada the created object
     */
    public static Lactancia create(java.lang.Integer nroLact,
        java.lang.String tipoLact, java.util.Date fechaInicio,
        java.lang.Integer dias, java.lang.Integer anios,
        java.lang.Integer meses, java.lang.String tipoCert,
        java.lang.Integer nroCert, java.lang.Float leche,
        java.lang.Float grasa, java.lang.Float proteinas,
        java.lang.Integer celulas, java.lang.Float solidosNoGrasos,
        java.lang.Float solidosTotales, java.lang.Float urea,
        java.lang.String tipoRegistro, java.lang.String numeroRegistro,
        java.lang.Integer ordenies, java.lang.String control,
        java.lang.String muestreo, java.lang.String analisis,
        java.lang.Integer epar, java.lang.Integer eppar, java.lang.Integer diasa,
        java.lang.Integer ipar, java.lang.String flac, java.lang.Integer ilac,
        java.lang.Integer dcon, java.lang.Integer tlac, java.lang.String eac,
        java.lang.String cnor, java.util.Date fecha) {
        EvtLactanciaMigrada object = new EvtLactanciaMigrada();

        object.setNroLact(nroLact);
        object.setTipoLact(tipoLact);
        object.setFechaInicio(fechaInicio);
        object.setDias(dias);
        object.setAnios(anios);
        object.setMeses(meses);
        object.setTipoCert(tipoCert);
        object.setNroCert(nroCert);
        object.setLeche(leche);
        object.setGrasaAbsoluto(grasa);
        object.setProteinasAbsoluto(proteinas);
        object.setCelulas(celulas);
        object.setSolidosNoGrasos(solidosNoGrasos);
        object.setSolidosTotalesAbsoluto(solidosTotales);
        object.setUrea(urea);
        object.setTipoRegistro(tipoRegistro);
        object.setNumeroRegistro(numeroRegistro);
        object.setOrdenies(ordenies);
        object.setControl(control);
        object.setMuestreo(muestreo);
        object.setAnalisis(analisis);
        object.setEpar(epar);
        object.setEppar(eppar);
        object.setDiasa(diasa);
        object.setIpar(ipar);
        object.setFlac(flac);
        object.setIlac(ilac);
        object.setDcon(dcon);
        object.setTlac(tlac);
        object.setEac(eac);
        object.setCnor(cnor);
        object.setFecha(fecha);

        return object;
    }

    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtLactanciaMigrada object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static Lactancia findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        Lactancia object = (Lactancia) session.get(EvtLactanciaMigrada.class,
                id);

        return object;
    }
    
    /**
     * Devuelve todas las lactancias migradas de un animal dado para una fecha
     * dada
     * @param a
     * @param fecha
     * @return
     */
    public static List findByAnimalFecha(Animal a, Date fecha) {
    	Session s = HibernateFactory.getSession();
		SimpleDateFormat formatDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		
		String dia = formatDia.format(fecha);
		String mes = formatMes.format(fecha);
		String anio = formatAnio.format(fecha);
    	
    	Criteria criterio = s.createCriteria(EvtLactanciaMigrada.class);
    	criterio.add(Expression.eq("animal", a));
    	
    	if (StringUtils.isNotEmpty(anio))
			criterio.add(Expression.sql("EXTRACT(YEAR FROM {alias}.inicio)=?", anio, Hibernate.STRING));
		if (StringUtils.isNotEmpty(mes))
			criterio.add(Expression.sql("EXTRACT(MONTH FROM {alias}.inicio)=?", mes, Hibernate.STRING));
		if (StringUtils.isNotEmpty(dia))
			criterio.add(Expression.sql("EXTRACT(DAY FROM {alias}.inicio)=?", dia, Hibernate.STRING));
		criterio.add(Expression.not(Expression.eq("dias", new Integer(999))));

    	return criterio.list();
    	
    }
    
}
