package ar.org.sicel.excel.excepciones;




public class CellError implements Comparable{
	
	private long fila;
	//private long columna;
	private String mensajeError;

	public CellError(long fila/*, long columna*/, String mensaje) {
		super();
		this.fila = fila;
		//this.columna = columna;
		this.mensajeError = mensaje; 
	}

	public long getFila() {
		return fila;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	/*public long getColumna() {
		return columna;
	}*/
	

    public boolean equals(Object obj)
    {
        if(!(obj instanceof CellError))
            return false;
        else
            return fila == ((CellError)obj).getFila();
    }
	

	public int compareTo(Object o) {
		CellError aux = (CellError) o;
		if (fila == aux.getFila())
			return 0;
		if (fila < aux.getFila())
			return -1;
		return 1;
	}


}
