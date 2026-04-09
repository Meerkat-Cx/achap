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
 * Evento compra de semen por un estab
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtSemen extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * TAnimReg
     */
    private ar.org.sicel.proc.v1.lote.Donante _donante;

    /**
     * Field _cantDosis
     */
    private long _cantDosis;

    /**
     * keeps track of state for field: _cantDosis
     */
    private boolean _has_cantDosis;

    /**
     * Field _proveedor
     */
    private long _proveedor;

    /**
     * keeps track of state for field: _proveedor
     */
    private boolean _has_proveedor;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtSemen() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtSemen()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteCantDosis
     * 
     */
    public void deleteCantDosis()
    {
        this._has_cantDosis= false;
        notifyPropertyChangeListeners("_cantDosis", new java.lang.Long(this._cantDosis), null);
    } //-- void deleteCantDosis() 

    /**
     * Method deleteProveedor
     * 
     */
    public void deleteProveedor()
    {
        this._has_proveedor= false;
        notifyPropertyChangeListeners("_proveedor", new java.lang.Long(this._proveedor), null);
    } //-- void deleteProveedor() 

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
        
        if (obj instanceof TEvtSemen) {
        
            TEvtSemen temp = (TEvtSemen)obj;
            if (this._donante != null) {
                if (temp._donante == null) return false;
                else if (!(this._donante.equals(temp._donante))) 
                    return false;
            }
            else if (temp._donante != null)
                return false;
            if (this._cantDosis != temp._cantDosis)
                return false;
            if (this._has_cantDosis != temp._has_cantDosis)
                return false;
            if (this._proveedor != temp._proveedor)
                return false;
            if (this._has_proveedor != temp._has_proveedor)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'cantDosis'.
     * 
     * @return long
     * @return the value of field 'cantDosis'.
     */
    public long getCantDosis()
    {
        return this._cantDosis;
    } //-- long getCantDosis() 

    /**
     * Returns the value of field 'donante'. The field 'donante'
     * has the following description: TAnimReg
     * 
     * @return Donante
     * @return the value of field 'donante'.
     */
    public ar.org.sicel.proc.v1.lote.Donante getDonante()
    {
        return this._donante;
    } //-- ar.org.sicel.proc.v1.lote.Donante getDonante() 

    /**
     * Returns the value of field 'proveedor'.
     * 
     * @return long
     * @return the value of field 'proveedor'.
     */
    public long getProveedor()
    {
        return this._proveedor;
    } //-- long getProveedor() 

    /**
     * Method hasCantDosis
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCantDosis()
    {
        return this._has_cantDosis;
    } //-- boolean hasCantDosis() 

    /**
     * Method hasProveedor
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasProveedor()
    {
        return this._has_proveedor;
    } //-- boolean hasProveedor() 

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
     * Sets the value of field 'cantDosis'.
     * 
     * @param cantDosis the value of field 'cantDosis'.
     */
    public void setCantDosis(long cantDosis)
    {
        java.lang.Object oldCantDosis = new java.lang.Long(this._cantDosis);
        this._cantDosis = cantDosis;
        this._has_cantDosis = true;
        notifyPropertyChangeListeners("_cantDosis", oldCantDosis, new java.lang.Long(this._cantDosis));
    } //-- void setCantDosis(long) 

    /**
     * Sets the value of field 'donante'. The field 'donante' has
     * the following description: TAnimReg
     * 
     * @param donante the value of field 'donante'.
     */
    public void setDonante(ar.org.sicel.proc.v1.lote.Donante donante)
    {
        java.lang.Object oldDonante = this._donante;
        this._donante = donante;
        notifyPropertyChangeListeners("_donante", oldDonante, this._donante);
    } //-- void setDonante(ar.org.sicel.proc.v1.lote.Donante) 

    /**
     * Sets the value of field 'proveedor'.
     * 
     * @param proveedor the value of field 'proveedor'.
     */
    public void setProveedor(long proveedor)
    {
        java.lang.Object oldProveedor = new java.lang.Long(this._proveedor);
        this._proveedor = proveedor;
        this._has_proveedor = true;
        notifyPropertyChangeListeners("_proveedor", oldProveedor, new java.lang.Long(this._proveedor));
    } //-- void setProveedor(long) 

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
