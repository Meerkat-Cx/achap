/*
 * Created on 18/05/2005
 *
 */
package ar.org.sicel.proc.services.impl;

import java.util.HashMap;
import java.util.Map;

import ar.org.sicel.proc.v1.lote.types.STRaza;

/**
 * @author pablo
 *
 *
 * Esta clase se usa para mapear y convertir entre los nombres de las razas que figuran
 * en la Base de Datos, con los que se utilizan para informar en el XML. (HO a HOLA, etc..).
 * Se tiene en cuenta que la raza por defecto es Holando.
 * 
 */
public class RazaUtil {
	
	private static Map<String,String> mapaRazas;
	
	static {
		mapaRazas = new HashMap<String,String>();
		//inicializo las entradas de la tabla
		mapaRazas.put(STRaza.HO.toString(),"HOLA");
		mapaRazas.put(STRaza.JY.toString(),"JERY");
       // mapaRazas.put(STRaza.DB.toString(),"DEBO");
        mapaRazas.put(STRaza.XB.toString(),"CRBO");
        mapaRazas.put(STRaza.SB.toString(),"SRBO");
        mapaRazas.put(STRaza.SW.toString(),"SWBO");
        mapaRazas.put(STRaza.MU.toString(),"MUBU");
        mapaRazas.put(STRaza.ME.toString(),"MEBU");
        mapaRazas.put(STRaza.JU.toString(),"JABU");
        mapaRazas.put(STRaza.XU.toString(),"CRBU");
       // mapaRazas.put(STRaza.DU.toString(),"DEBU");
        mapaRazas.put(STRaza.HO.toString(),"HOLA");
		mapaRazas.put(STRaza.JY.toString(),"JERY");
       // mapaRazas.put(STRaza.DB.toString(),"DEBO");
        mapaRazas.put(STRaza.NO.toString(),"NORM");
        mapaRazas.put(STRaza.AY.toString(),"AYSH");
        mapaRazas.put(STRaza.GU.toString(),"GUER");
        mapaRazas.put(STRaza.MO.toString(),"MONT");
        mapaRazas.put(STRaza.SH.toString(),"SHOR");
        mapaRazas.put(STRaza.BR.toString(),"BRAH");
        mapaRazas.put(STRaza.NE.toString(),"NELO");
        mapaRazas.put(STRaza.SI.toString(),"SIMM");
        mapaRazas.put(STRaza.BC.toString(),"BICA");
        
        
        
        
        
        
        
        
        
        
       

	}

	/**
	 * Devuelve el tipo en nuestra base que se corresponde con el
     * tipo de Castor dado. Si tipoCastor es null, se toma como valor
     * por defecto HOLANDO
     * 
     * @param tipoCastor
     * @return
	 */
    public static String getRazaEnBase(STRaza tipoCastor) {
        //MODIF
        if (tipoCastor != null)
            return mapaRazas.get(tipoCastor.toString());
        return null;
    }
    
}
