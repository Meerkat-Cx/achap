package ar.org.sicel.excel.excepciones;

/**
 * Excepcion para cuando el formato de la celda no es el esperado
 * @author 
 *
 */
public class FormatCellException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long fila;
	private long columna;

	public FormatCellException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormatCellException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FormatCellException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FormatCellException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public long getColumna() {
		return columna;
	}

	public void setColumna(long columna) {
		this.columna = columna;
	}

	public long getFila() {
		return fila;
	}

	public void setFila(long fila) {
		this.fila = fila;
	}
	
	public FormatCellException(String message, long fila, long columna) {
		super(message);
		this.columna = columna;
		this.fila = fila;
	}


}
