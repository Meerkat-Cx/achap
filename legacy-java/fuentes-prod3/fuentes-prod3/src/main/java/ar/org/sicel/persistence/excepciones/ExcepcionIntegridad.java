package ar.org.sicel.persistence.excepciones;

import java.util.List;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.util.StringUtils;

/**
 * 
 * Esta excepcion (y sus subclasses) son arrojadas por los manejadores de
 * eventos para indicar que no pudieron realizar su tarea por un fallo en la
 * integridad entre los datos recividos en el evento y los datos actuales en la
 * base.
 * 
 * 
 * @author pablo
 * 
 */
public class ExcepcionIntegridad extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7001348186015242380L;

	private String codigoError;

    private String[] valores;
    
    private List<ProcMsg> mensajes;

    /**
     * 
     * @param codigoError
     * @param valores
     */
    public ExcepcionIntegridad(String codigoError, String[] valores) {
        super();
        this.codigoError = codigoError;
        this.valores = valores;
    }

    public ExcepcionIntegridad(String codigoError, String[] valores, List<ProcMsg> mensajes) {
        super();
        this.codigoError = codigoError;
        this.valores = valores;
        this.mensajes = mensajes;
    }

    
    /**
     * @return Returns the codigoError.
     */
    public String getCodigoError() {
        return codigoError;
    }

    public String[] getValores() {
        return valores;
    }
    
    public List<ProcMsg> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<ProcMsg> mensajes) {
		this.mensajes = mensajes;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    public String toString() {
        String result = super.toString() + " ( " + codigoError + " ) { ";
        for (int i = 0; i < this.valores.length; i++) {
            result = result + valores[i]
                    + (i == (this.valores.length) - 1 ? " }" : ", ");
        }
        return result;
    }

    public String getInfo() {
        String result = this.getCodigoError()+" ";
        try {
            ProcCodMsg procCodMsg = ProcCodMsgDAO.findByPrimaryKey(this
                    .getCodigoError());
            try {
                result = result
                        + StringUtils.format(procCodMsg.getFormato(), this
                                .getValores());
            } catch (java.util.IllegalFormatConversionException e) {
                result = result + procCodMsg.getFormato();
            }
        } catch (HibernateException e) {
            result = "No se pudo completar la info, causa: " + e.toString();
        }
        return result;
    }
}
