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

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento productivo que se seca al animal
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtSecada extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * los motivos posibles se controlan durante el procesamiento
     */
    private java.lang.String _motivo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtSecada() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtSecada()


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
        
        if (obj instanceof TEvtSecada) {
        
            TEvtSecada temp = (TEvtSecada)obj;
            if (this._motivo != null) {
                if (temp._motivo == null) return false;
                else if (!(this._motivo.equals(temp._motivo))) 
                    return false;
            }
            else if (temp._motivo != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'motivo'. The field 'motivo' has
     * the following description: los motivos posibles se controlan
     * durante el procesamiento
     * 
     * @return String
     * @return the value of field 'motivo'.
     */
    public java.lang.String getMotivo()
    {
        return this._motivo;
    } //-- java.lang.String getMotivo() 

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
     * Sets the value of field 'motivo'. The field 'motivo' has the
     * following description: los motivos posibles se controlan
     * durante el procesamiento
     * 
     * @param motivo the value of field 'motivo'.
     */
    public void setMotivo(java.lang.String motivo)
    {
        java.lang.Object oldMotivo = this._motivo;
        this._motivo = motivo;
        notifyPropertyChangeListeners("_motivo", oldMotivo, this._motivo);
    } //-- void setMotivo(java.lang.String) 

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
