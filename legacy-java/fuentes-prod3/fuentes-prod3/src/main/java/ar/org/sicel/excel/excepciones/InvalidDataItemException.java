package ar.org.sicel.excel.excepciones;

public class InvalidDataItemException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private long fila;	

	public InvalidDataItemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public InvalidDataItemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}*/

	public InvalidDataItemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidDataItemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public InvalidDataItemException(String message, long fila) {
		super(message);
		this.fila = fila;		
	}

	public long getFila() {
		return fila;
	}


}
