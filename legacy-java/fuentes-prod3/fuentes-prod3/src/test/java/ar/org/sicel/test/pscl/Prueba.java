package ar.org.sicel.test.pscl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lowagie.text.pdf.codec.postscript.ParseException;

import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

class Prueba {

	/**
	 * @param args
	 * @throws java.text.ParseException 
	 */
	public static void main(String[] args) throws java.text.ParseException {
		
		
		System.out.println("Hola Mundo");
		Date fechaI = new Date();
		try {
			fechaI= DateUtils.parse("26/06/2005 00:00:00","dd/MM/yyyy HH:mm:ss");
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date fechaF = new Date();
		try {
			//fechaF= DateUtils.parse("03/01/2007","dd/MM/yyyy");
			fechaF= DateUtils.parse("04/03/2002 00:00:00","dd/MM/yyyy HH:mm:ss");
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Date nueva = DateUtils.mas(fechaF, 480);
		
		//int dias = DateUtils.diasEntre(DateUtils.parse(DateUtils.format(fechaI, "dd/MM/yyyy"),"dd/MM/yyyy"),DateUtils.parse(DateUtils.format(fechaF, "dd/MM/yyyy"),"dd/MM/yyyy"));
		int dias = DateUtils.diasEntre(fechaI,fechaF);
		Date fecha=DateUtils.menos(fechaF, 278);
		
		String rrrr= "ARGT";
		char ultimaLetra =rrrr.charAt(rrrr.length() -1);
		rrrr.substring(0, rrrr.length() -1);
		*/
		List meses = getMeses();
		System.out.println("dias-->"+meses.toString());
		
		System.out.println("dias-->"+rrrr.substring(0, rrrr.length() -1)+"M");
		

	}
	public static int getMes(Date date) {
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		return Integer.parseInt(formatMes.format(date));
	}
	public static int getAnio(Date date) {
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatAnio.format(date));
	}
	private static List getMeses() throws java.text.ParseException{
		Date fechaI= DateUtils.parse("26/06/2011 00:00:00","dd/MM/yyyy HH:mm:ss");
		Date fechaF= DateUtils.parse("04/03/2012 00:00:00","dd/MM/yyyy HH:mm:ss");
		int anioDesde=getAnio(fechaI);
		int anioHasta=getAnio(fechaF);
		int mesDesde = getMes(fechaI);
		int mesHasta = getMes(fechaF);
		List <String> mesesAnios = new ArrayList();
		String valor = mesDesde + "|"+ anioDesde;
		//mesesAnios.add(valor);
		if(anioDesde == anioHasta){//estamos dentro del mismo anio
			while(mesDesde<mesHasta){//no se debe controlar que pase de mes 12
				mesesAnios.add(mesDesde + "|"+ anioDesde);
				mesDesde++;
			}
		}
		else{
			while(anioDesde <=anioHasta){
				if(anioDesde <anioHasta){
					while(mesDesde<=12){//no se debe controlar que pase de mes 12
						mesesAnios.add(mesDesde + "|"+ anioDesde);
						mesDesde++;
					}
					mesDesde =1;
					anioDesde++;
				}
				else{
					while(mesDesde<=mesHasta){//no se debe controlar que pase de mes 12
						mesesAnios.add(mesDesde + "|"+ anioDesde);
						mesDesde++;
					}
					anioDesde++;
				}
			}
			
		}
		return mesesAnios;
	}

	public static int getMes(Date date) {
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		return Integer.parseInt(formatMes.format(date));
	}
	public static int getAnio(Date date) {
		SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatAnio.format(date));
	}
	private static List getMeses() throws java.text.ParseException{
		Date fechaI= DateUtils.parse("26/06/2011 00:00:00","dd/MM/yyyy HH:mm:ss");
		Date fechaF= DateUtils.parse("04/06/2012 00:00:00","dd/MM/yyyy HH:mm:ss");
		int anioDesde=getAnio(fechaI);
		int anioHasta=getAnio(fechaF);
		int mesDesde = getMes(fechaI);
		int mesHasta = getMes(fechaF);
		List <String> mesesAnios = new ArrayList();
		String valor = mesDesde + "|"+ anioDesde;
		//mesesAnios.add(valor);
		if(anioDesde == anioHasta){//estamos dentro del mismo anio
			while(mesDesde<mesHasta){//no se debe controlar que pase de mes 12
				mesesAnios.add(mesDesde + "|"+ anioDesde);
				mesDesde++;
			}
		}
		else{
			while(anioDesde <=anioHasta){
				if(anioDesde <anioHasta){
					while(mesDesde<=12){//no se debe controlar que pase de mes 12
						mesesAnios.add(mesDesde + "|"+ anioDesde);
						mesDesde++;
					}
					mesDesde =1;
					anioDesde++;
				}
				else{
					while(mesDesde<=mesHasta){//no se debe controlar que pase de mes 12
						mesesAnios.add(mesDesde + "|"+ anioDesde);
						mesDesde++;
					}
					anioDesde++;
				}
			}
			
		}
		return mesesAnios;
	}


}
