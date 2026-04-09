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
 * Evento de prueba de preñez (tacto en muchos mamíferos)
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtPrenez extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Si la prueba de preniez del animal es positiva
     */
    private boolean _esPositivo;

    /**
     * keeps track of state for field: _esPositivo
     */
    private boolean _has_esPositivo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtPrenez() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtPrenez()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteEsPositivo
     * 
     */
    public void deleteEsPositivo()
    {
        this._has_esPositivo= false;
        notifyPropertyChangeListeners("_esPositivo", (this._esPositivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE), null);
    } //-- void deleteEsPositivo() 

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
        
        if (obj instanceof TEvtPrenez) {
        
            TEvtPrenez temp = (TEvtPrenez)obj;
            if (this._esPositivo != temp._esPositivo)
                return false;
            if (this._has_esPositivo != temp._has_esPositivo)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'esPositivo'. The field
     * 'esPositivo' has the following description: Si la prueba de
     * preniez del animal es positiva
     * 
     * @return boolean
     * @return the value of field 'esPositivo'.
     */
    public boolean getEsPositivo()
    {
        return this._esPositivo;
    } //-- boolean getEsPositivo() 

    /**
     * Method hasEsPositivo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasEsPositivo()
    {
        return this._has_esPositivo;
    } //-- boolean hasEsPositivo() 

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
     * Sets the value of field 'esPositivo'. The field 'esPositivo'
     * has the following description: Si la prueba de preniez del
     * animal es positiva
     * 
     * @param esPositivo the value of field 'esPositivo'.
     */
    public void setEsPositivo(boolean esPositivo)
    {
        java.lang.Object oldEsPositivo = (this._esPositivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
        this._esPositivo = esPositivo;
        this._has_esPositivo = true;
        notifyPropertyChangeListeners("_esPositivo", oldEsPositivo, (this._esPositivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE));
    } //-- void setEsPositivo(boolean) 

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
