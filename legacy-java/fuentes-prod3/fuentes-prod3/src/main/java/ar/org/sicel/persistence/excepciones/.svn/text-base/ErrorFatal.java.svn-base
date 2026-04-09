package ar.org.sicel.persistence.excepciones;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;
import ar.org.sicel.util.StringUtils;


/**
 *
 * Indica que se ha producido un Error grave a nivel de sistema,
 * como por ejemplo que no se pudo conectar con la base de datos
 * o que no se pudo encontrar un servicio requerido.
 * La respuesta general a este tipo de errores es hacer un rollback
 * general de todo el lote.
 *
 * @author pablo
 *
 */
public class ErrorFatal extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6580597525851685664L;

	private String codigoError;

    private String[] valores;
    
	/**
     *
     */
    public ErrorFatal() {
        super();
    }

    /**
     * @param message
     */
    public ErrorFatal(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ErrorFatal(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ErrorFatal(Throwable cause) {
        super(cause);
    }
    
    public ErrorFatal(String codigoError, String[] valores) {
        super();
        this.codigoError = codigoError;
        this.valores = valores;
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
