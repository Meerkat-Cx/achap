package ar.org.sicel.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
/**
 * Utilitarios para trabajar con Fechas.
 * TODO: La biblioteca que estoy usando ahora esta buena, podriamos ver de
 * usar esos objetos DateTime en ves de los java.util.Date en los demas lados,
 * y asi no necesitariamos esto. 
 *  
 * @author pablo
 *
 */
public class DateUtils {
		
	public static int diasEntreX(Date comienzo, Date fin) {
		DateTime dComienzo = new DateTime(comienzo);
		DateTime dFin = new DateTime(fin);
		Period p = new Period(dComienzo,dFin,PeriodType.days());
		return p.getDays();
	}
	public static int diasEntre(Date comienzo, Date fin) {
		Date dateComienzo = null;
		Date dateFin = null;
		try {
			dateComienzo =DateUtils.parse(DateUtils.format(comienzo, "dd/MM/yyyy"),"dd/MM/yyyy");
			dateFin =DateUtils.parse(DateUtils.format(fin, "dd/MM/yyyy"),"dd/MM/yyyy");
		} catch (ParseException e) {
			dateComienzo = comienzo;
			dateFin=fin;
			e.printStackTrace();
		}
		
		DateTime dComienzo = new DateTime(dateComienzo);
		DateTime dFin = new DateTime(dateFin);
		Period p = new Period(dComienzo,dFin,PeriodType.days());		 
		return p.getDays();
	}
	public static int mesesEntre(Date comienzo, Date fin) {
		DateTime dComienzo = new DateTime(comienzo);
		DateTime dFin = new DateTime(fin);
		Period p = new Period(dComienzo,dFin,PeriodType.months());
		return p.getMonths();
	}
	
	
	public static Date mas(Date date, int dias ) {
		DateTime t = new DateTime(date);
		return t.plus(Period.days(dias) ).toDate();
	}
	
	public static Date menos(Date date, int dias) {
		Date fecha = date.getClass()==Timestamp.class ? new java.util.Date(date.getTime()):date;
		DateTime t = new DateTime(fecha);
		return t.minus(Period.days(dias) ).toDate();
	}
	/**
	 * dada una fecha retorna la misma fecha menos la cantidad de segundo que se envia como paramentro 
	 * @param date
	 * @param minutos
	 * @return
	 */
	public static Date menosSegundos(Date date, int segundos) {
		Date fecha = date.getClass()==Timestamp.class ? new java.util.Date(date.getTime()):date;
		DateTime t = new DateTime(fecha);
		return t.minus(Period.seconds(segundos) ).toDate();
	}
	/**
	 * dada una fecha retorna la misma fecha mas la cantidad de segundo que se envia como paramentro 
	 * @param date
	 * @param minutos
	 * @return
	 */
	public static Date masSegundos(Date date, int segundos) {
		Date fecha = date.getClass()==Timestamp.class ? new java.util.Date(date.getTime()):date;
		DateTime t = new DateTime(fecha);
		return t.plus(Period.seconds(segundos) ).toDate();
	}

	/**
	 * Verdadero si son las dos fechas el mismo dia (sin importar la hora y los minutos)
	 * @param fecha
	 * @param fecha2
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean mismoDia(Date fecha, Date fecha2) {
		return (fecha.getDate() == fecha2.getDate() && 
			   fecha.getYear() == fecha2.getYear() &&
			   fecha.getMonth() == fecha2.getMonth());
	}

	/**
	 * Verdadero si fecha esta dentro del periodo [inicioIntervalo..finIntervalo]
	 * 
	 * @param fecha
	 * @param inicioIntervalo
	 * @param finIntervalo
	 * @return
	 */
	public static boolean entre(Date fecha, Date inicioIntervalo, Date finIntervalo) {		
		Date fecha1 = fecha.getClass()==Timestamp.class ? new java.util.Date(fecha.getTime()):fecha;
		Date inicio = inicioIntervalo.getClass()==Timestamp.class ? new java.util.Date(inicioIntervalo.getTime()):inicioIntervalo;
		Date fin = finIntervalo.getClass()==Timestamp.class ? new java.util.Date(finIntervalo.getTime()):finIntervalo;
		return (inicio.compareTo(fecha1) <= 0) && (fin.compareTo(fecha1) >= 0);
	}
	
	
	public static String format(Date date, String s) {
		return (new SimpleDateFormat(s != null ? s : "dd/MM/yyyy HH:mm:ss")).format(date);
	}
	/**
	 * Tomando un formato de date.toString lo transforma en un date y luego le da el formato pasado como parametro
	 * @param date
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String parseStringSpanish(String date, String s) throws ParseException {
		Date d = null;
		try{
			d=new SimpleDateFormat(s != null ? s : "dd/MM/yyyy HH:mm:ss").parse(date);
		}
		catch(ParseException exception){
	        return null;
	    }
		
		return format(d,s);
		
	}
	
	public static Date parse(String date, String s) throws ParseException {
		try{
			return (new SimpleDateFormat(s != null ? s : "dd/MM/yyyy HH:mm:ss")).parse(date);
		}
		catch(ParseException exception){
	        return null;
	    }
		
	}
	public static int getDia(Date date) {
		SimpleDateFormat formatDia = new SimpleDateFormat("dd");
		return Integer.parseInt(formatDia.format(date));
	}
	public static int getMes(Date date) {
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		return Integer.parseInt(formatMes.format(date));
	}
	public static int getAnio(Date date) {
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatAnio.format(date));
	}
	

	    	
}
