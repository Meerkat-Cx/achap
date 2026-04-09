/*
 * Created on 18/05/2005
 */
package ar.org.sicel.proc.services;

import java.util.Collection;

import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.persistence.Usuario;
import ar.org.sicel.proc.v1.anmodif.AnimsModificados;
import ar.org.sicel.proc.v1.lote.Lote;

/**
 * @author pablo
 * 
 * Este es el servicio base que hay que utilizar para procesar un Lote. 
 * Los demas servicios son utilizados internamente para procesar cada porcion del Lote, y
 * no estan pensados para ser invocados directamente, auqnue esto puede hacerse si se desea.
 * 
 * La diferencia entre el servicio IProcesadorXML y el IProcesadorLote, es que este siempre devuelve
 * un resultado, por ejemplo ante un ErrorFatal, el IProcesadorLote simplemente arroja una excepcion y 
 * termina sin guardar nada, pero el IProcesadorXML (que vendria a ser una especie de wrapper sobre el otro) siempre
 * captura todas las excepciones e igualmente arma un XML valido de resultado, etc. 
 * Ademas el IProcesadorXML es el encargado de rellenar los campos de tiempos, etc.
 *   
 * 
 */
public interface IProcesadorXML {
	/**
	 * @param loteEntrada El lote xml a procesar
	 * @param animsModificados Aqui se devolveran la lista de animales modificados
	 * @param resumen Aqui se devuelve un pequeño resumen de los eventos (se usa para el reporte de resumen de jasper)
	 */
    public Lote procesar(ProcProces proc,Lote loteEntrada,AnimsModificados animsModificados, Collection resumen,Usuario user);

}
