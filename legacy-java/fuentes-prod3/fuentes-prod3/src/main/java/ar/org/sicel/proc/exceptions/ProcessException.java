package ar.org.sicel.proc.exceptions;

import ar.org.sicel.persistence.ProcProces;
import ar.org.sicel.upload.dao.ConstantsUpload;

/**
 * Clase que representa una excepcion ocurrida durante el procesamiento 
 * de un archivo XML
 * @author 
 *
 */
public class ProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String MENSAJE_FORMATO_INVALIDO = "Error en el formato del archivo xml siendo procesado";
	public static final String MENSAJE_ERROR_VALIDACION = "Error de Validacion en el archivo xml siendo procesado";
	public static final String MENSAJE_ARCHIVO_NO_ENCONTRADO = "El archivo XML no pudo ser encontrado en el servidor";
	public static final String MENSAJE_CERRAR_ARCHIVO = "Hubo inconvenientes mientras se estaba cerrando el archivo";
	public static final String MENSAJE_PROBLEMA_IO = "Hubo inconvenientes cuando se estaba generando el archivo de salida";
	public static final String MENSAJE_PROBLEMA_BASE_DATOS = "La base de datos no esta disponible";
	
	private String lote;
	

	public ProcessException() {
		super();		
	}

	public ProcessException(String message) {
		super(message);
	}

	public ProcessException(ProcProces proc,String message, Throwable cause) {
		super(message, cause);
		//proc.setEstado(ConstantsUpload.PROCESSERROR);
		proc.setEstado(ConstantsUpload.STANDBY);
	}

	public ProcessException(Throwable cause) {
		super(cause);
	}
	
	public ProcessException(ProcProces proc,String message, String loteError, Throwable cause) {
		super(message, cause);
		lote = loteError;
		//proc.setEstado(ConstantsUpload.PROCESSERROR);
		proc.setEstado(ConstantsUpload.STANDBY);
	}
	
	public String getLote(){
		return lote;
	}

}
