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
 * Evento transferencia
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtTrans extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _propNuevo
     */
    private long _propNuevo;

    /**
     * keeps track of state for field: _propNuevo
     */
    private boolean _has_propNuevo;

    /**
     * Field _rpNuevo
     */
    private java.lang.String _rpNuevo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtTrans() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtTrans()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deletePropNuevo
     * 
     */
    public void deletePropNuevo()
    {
        this._has_propNuevo= false;
        notifyPropertyChangeListeners("_propNuevo", new java.lang.Long(this._propNuevo), null);
    } //-- void deletePropNuevo() 

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
        
        if (obj instanceof TEvtTrans) {
        
            TEvtTrans temp = (TEvtTrans)obj;
            if (this._propNuevo != temp._propNuevo)
                return false;
            if (this._has_propNuevo != temp._has_propNuevo)
                return false;
            if (this._rpNuevo != null) {
                if (temp._rpNuevo == null) return false;
                else if (!(this._rpNuevo.equals(temp._rpNuevo))) 
                    return false;
            }
            else if (temp._rpNuevo != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'propNuevo'.
     * 
     * @return long
     * @return the value of field 'propNuevo'.
     */
    public long getPropNuevo()
    {
        return this._propNuevo;
    } //-- long getPropNuevo() 

    /**
     * Returns the value of field 'rpNuevo'.
     * 
     * @return String
     * @return the value of field 'rpNuevo'.
     */
    public java.lang.String getRpNuevo()
    {
        return this._rpNuevo;
    } //-- java.lang.String getRpNuevo() 

    /**
     * Method hasPropNuevo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPropNuevo()
    {
        return this._has_propNuevo;
    } //-- boolean hasPropNuevo() 

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
     * Sets the value of field 'propNuevo'.
     * 
     * @param propNuevo the value of field 'propNuevo'.
     */
    public void setPropNuevo(long propNuevo)
    {
        java.lang.Object oldPropNuevo = new java.lang.Long(this._propNuevo);
        this._propNuevo = propNuevo;
        this._has_propNuevo = true;
        notifyPropertyChangeListeners("_propNuevo", oldPropNuevo, new java.lang.Long(this._propNuevo));
    } //-- void setPropNuevo(long) 

    /**
     * Sets the value of field 'rpNuevo'.
     * 
     * @param rpNuevo the value of field 'rpNuevo'.
     */
    public void setRpNuevo(java.lang.String rpNuevo)
    {
        java.lang.Object oldRpNuevo = this._rpNuevo;
        this._rpNuevo = rpNuevo;
        notifyPropertyChangeListeners("_rpNuevo", oldRpNuevo, this._rpNuevo);
    } //-- void setRpNuevo(java.lang.String) 

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
