/*
 * Created on 18/05/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.HashMap;
import java.util.Map;

import ar.org.sicel.proc.v1.lote.types.STSexo;
import ar.org.sicel.proc.v1.lote.types.STTReg;

/**
 * @author pablo
 * 
 * Esta clase se usa para mapear y convertir entre los nombres de los tipos de registro que figuran
 * en la Base de Datos, con los que se utilizan para informar en el XML. (C a RC, P a HBA, etc..).
 * Tambien para acceder al elemento <b>sexo</b> del XML, teniendo en cuenta que el sexo por defecto
 * es femenino.
 * 
 *
 */
public class TipoRegistroUtil {
	
	private static Map<String,String> mapaTipos;
	
	static {
		mapaTipos = new HashMap<String,String>();
		//inicializo las entradas de la tabla
		mapaTipos.put(STTReg.C.toString(),"RC");
		mapaTipos.put(STTReg.P.toString(),"HBA");
	}

	/**
	 * Devuelve el tipo en nuestra base que se corresponde con el
     * tipo de Castor dado. Si tipoCastor es null, se toma como valor
     * por defecto RC
     * 
     * @param tipoCastor
     * @return
	 */
    public static String getTipoRegistroEnBase(STTReg tipoCastor) {
        String xmlRepr = null;
        if (tipoCastor != null)
            xmlRepr = tipoCastor.toString();
        else
            xmlRepr = STTReg.C.toString();  //si no se espesifica, por defecto es el tipo C de castor, RC
    	return mapaTipos.get(xmlRepr);
    }
    
    
    /**
     * Devuelve si es hembra o no. Si no se especifica nada,
     * el valor por defecto es Hembra
     * @param sexo
     * @return
     */
    public static boolean esHembra(STSexo sexo) {
        if (sexo == null)
            sexo = STSexo.F; //si no se especifica un sexo, el valor por defecto es hembra
        return sexo.equals(STSexo.F);
    }
}
