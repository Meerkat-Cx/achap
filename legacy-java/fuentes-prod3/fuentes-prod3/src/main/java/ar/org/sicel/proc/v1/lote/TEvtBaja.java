/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.6</a>, using an XML
 * Schema.
 * $Id$
 */

package ar.org.sicel.proc.v1.lote;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import ar.org.sicel.proc.v1.lote.types.STDestino;
import ar.org.sicel.proc.v1.lote.types.STMotivo;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento baja, el animal no recinvira mas eventos
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtBaja extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _destino
     */
    private ar.org.sicel.proc.v1.lote.types.STDestino _destino;

    /**
     * Field _motivo
     */
    private ar.org.sicel.proc.v1.lote.types.STMotivo _motivo;

    /**
     * Field _comentariosBaja
     */
    private java.lang.String _comentariosBaja;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtBaja() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtBaja()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Note: hashCode() has not been overriden
     * 
     * @param obj
     * @return boolean
     */
    public boolean equals(java.lang.Object obj)
    {
        if ( this == obj )
            return true;
        
        if (super.equals(obj)==false)
            return false;
        
        if (obj instanceof TEvtBaja) {
        
            TEvtBaja temp = (TEvtBaja)obj;
            if (this._destino != null) {
                if (temp._destino == null) return false;
                else if (!(this._destino.equals(temp._destino))) 
                    return false;
            }
            else if (temp._destino != null)
                return false;
            if (this._motivo != null) {
                if (temp._motivo == null) return false;
                else if (!(this._motivo.equals(temp._motivo))) 
                    return false;
            }
            else if (temp._motivo != null)
                return false;
            if (this._comentariosBaja != null) {
                if (temp._comentariosBaja == null) return false;
                else if (!(this._comentariosBaja.equals(temp._comentariosBaja))) 
                    return false;
            }
            else if (temp._comentariosBaja != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'comentariosBaja'.
     * 
     * @return String
     * @return the value of field 'comentariosBaja'.
     */
    public java.lang.String getComentariosBaja()
    {
        return this._comentariosBaja;
    } //-- java.lang.String getComentariosBaja() 

    /**
     * Returns the value of field 'destino'.
     * 
     * @return STDestino
     * @return the value of field 'destino'.
     */
    public ar.org.sicel.proc.v1.lote.types.STDestino getDestino()
    {
        return this._destino;
    } //-- ar.org.sicel.proc.v1.lote.types.STDestino getDestino() 

    /**
     * Returns the value of field 'motivo'.
     * 
     * @return STMotivo
     * @return the value of field 'motivo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STMotivo getMotivo()
    {
        return this._motivo;
    } //-- ar.org.sicel.proc.v1.lote.types.STMotivo getMotivo() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Sets the value of field 'comentariosBaja'.
     * 
     * @param comentariosBaja the value of field 'comentariosBaja'.
     */
    public void setComentariosBaja(java.lang.String comentariosBaja)
    {
        java.lang.Object oldComentariosBaja = this._comentariosBaja;
        this._comentariosBaja = comentariosBaja;
        notifyPropertyChangeListeners("_comentariosBaja", oldComentariosBaja, this._comentariosBaja);
    } //-- void setComentariosBaja(java.lang.String) 

    /**
     * Sets the value of field 'destino'.
     * 
     * @param destino the value of field 'destino'.
     */
    public void setDestino(ar.org.sicel.proc.v1.lote.types.STDestino destino)
    {
        java.lang.Object oldDestino = this._destino;
        this._destino = destino;
        notifyPropertyChangeListeners("_destino", oldDestino, this._destino);
    } //-- void setDestino(ar.org.sicel.proc.v1.lote.types.STDestino) 

    /**
     * Sets the value of field 'motivo'.
     * 
     * @param motivo the value of field 'motivo'.
     */
    public void setMotivo(ar.org.sicel.proc.v1.lote.types.STMotivo motivo)
    {
        java.lang.Object oldMotivo = this._motivo;
        this._motivo = motivo;
        notifyPropertyChangeListeners("_motivo", oldMotivo, this._motivo);
    } //-- void setMotivo(ar.org.sicel.proc.v1.lote.types.STMotivo) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
