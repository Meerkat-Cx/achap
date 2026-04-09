/*
 * Created on 25/06/2005
 */
package ar.org.sicel.util;

import java.util.Date;

/**
 * @author ala
 */
public class StringUtils {

	private static final String NOMBRE_ARG_INI = "[";
	private static final String NOMBRE_ARG_FIN = "]";

    public static String formatDate(Date date) {
        return String.format("%tF", date);
    }

    public static String format(String format, Object[] args) {
    	if(args!=null){
    		if(args.length > 0 && ((String)(args[0])).length()>=2000)
    			args[0] = ((String)(args[0])).substring(0,1999);
    	}
		return String.format(eliminarNombresDeArgumentosDeFormato(format), args);
	}

	/**
	 * En el formato de los mensajes, se incluye el nombre de cada argumento entre corchetes.
	 * Este metodo se utiliza para quitar esos nombres de manera de poder aplicar directamente el formato
	 * utilizando String.format(formato,argumentos)
	 * 
	 * @param formato
	 * @return
	 */
    private static String eliminarNombresDeArgumentosDeFormato(String formato) {
		int sf = formato.indexOf("%");
		if (sf < 0) // no hay formato
			return formato;
		int ef = formato.indexOf(" ", sf);
		if (ef == -1)
			ef = formato.length() - 1;
		int sn = formato.indexOf(NOMBRE_ARG_INI, sf);
		int en = formato.indexOf(NOMBRE_ARG_FIN, sn);
		if (sn < 0 || sn > ef || sn < 0 || sn > ef) // no hay nombre de arg
			return formato;
		return formato.substring(0, sn)
				+ eliminarNombresDeArgumentosDeFormato(formato
						.substring(en + 1));
	}

}
