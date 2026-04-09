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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Rdo.
 * 
 * @version $Revision$ $Date$
 */
public class Rdo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nivelErr
     */
    private long _nivelErr;

    /**
     * keeps track of state for field: _nivelErr
     */
    private boolean _has_nivelErr;

    /**
     * Field _codigoMsg
     */
    private java.lang.String _codigoMsg;

    /**
     * Field _info
     */
    private java.lang.String _info;

    /**
     * Field _vals
     */
    private ar.org.sicel.proc.v1.lote.Vals _vals;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Rdo() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Rdo()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addPropertyChangeListener
     * 
     * Registers a PropertyChangeListener with this class.
     * 
     * @param pcl The PropertyChangeListener to register.
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl)
    {
        propertyChangeListeners.addElement(pcl);
    } //-- void addPropertyChangeListener(java.beans.PropertyChangeListener) 

    /**
     * Method deleteNivelErr
     * 
     */
    public void deleteNivelErr()
    {
        this._has_nivelErr= false;
        notifyPropertyChangeListeners("_nivelErr", new java.lang.Long(this._nivelErr), null);
    } //-- void deleteNivelErr() 

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
        
        if (obj instanceof Rdo) {
        
            Rdo temp = (Rdo)obj;
            if (this._nivelErr != temp._nivelErr)
                return false;
            if (this._has_nivelErr != temp._has_nivelErr)
                return false;
            if (this._codigoMsg != null) {
                if (temp._codigoMsg == null) return false;
                else if (!(this._codigoMsg.equals(temp._codigoMsg))) 
                    return false;
            }
            else if (temp._codigoMsg != null)
                return false;
            if (this._info != null) {
                if (temp._info == null) return false;
                else if (!(this._info.equals(temp._info))) 
                    return false;
            }
            else if (temp._info != null)
                return false;
            if (this._vals != null) {
                if (temp._vals == null) return false;
                else if (!(this._vals.equals(temp._vals))) 
                    return false;
            }
            else if (temp._vals != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'codigoMsg'.
     * 
     * @return String
     * @return the value of field 'codigoMsg'.
     */
    public java.lang.String getCodigoMsg()
    {
        return this._codigoMsg;
    } //-- java.lang.String getCodigoMsg() 

    /**
     * Returns the value of field 'info'.
     * 
     * @return String
     * @return the value of field 'info'.
     */
    public java.lang.String getInfo()
    {
        return this._info;
    } //-- java.lang.String getInfo() 

    /**
     * Returns the value of field 'nivelErr'.
     * 
     * @return long
     * @return the value of field 'nivelErr'.
     */
    public long getNivelErr()
    {
        return this._nivelErr;
    } //-- long getNivelErr() 

    /**
     * Returns the value of field 'vals'.
     * 
     * @return Vals
     * @return the value of field 'vals'.
     */
    public ar.org.sicel.proc.v1.lote.Vals getVals()
    {
        return this._vals;
    } //-- ar.org.sicel.proc.v1.lote.Vals getVals() 

    /**
     * Method hasNivelErr
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNivelErr()
    {
        return this._has_nivelErr;
    } //-- boolean hasNivelErr() 

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
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method notifyPropertyChangeListeners
     * 
     * Notifies all registered PropertyChangeListeners when a bound
     * property's value changes.
     * 
     * @param fieldName the name of the property that has changed.
     * @param newValue the new value of the property.
     * @param oldValue the old value of the property.
     */
    protected void notifyPropertyChangeListeners(java.lang.String fieldName, java.lang.Object oldValue, java.lang.Object newValue)
    {
        if (propertyChangeListeners == null) return;
        java.beans.PropertyChangeEvent event = new java.beans.PropertyChangeEvent(this, fieldName, oldValue, newValue);
        
        for (int i = 0; i < propertyChangeListeners.size(); i++) {
            ((java.beans.PropertyChangeListener) propertyChangeListeners.elementAt(i)).propertyChange(event);
        }
    } //-- void notifyPropertyChangeListeners(java.lang.String, java.lang.Object, java.lang.Object) 

    /**
     * Method removePropertyChangeListener
     * 
     * Removes the given PropertyChangeListener from this classes
     * list of ProperyChangeListeners.
     * 
     * @param pcl The PropertyChangeListener to remove.
     * @return boolean
     * @return true if the given PropertyChangeListener was removed.
     */
    public boolean removePropertyChangeListener(java.beans.PropertyChangeListener pcl)
    {
        return propertyChangeListeners.removeElement(pcl);
    } //-- boolean removePropertyChangeListener(java.beans.PropertyChangeListener) 

    /**
     * Sets the value of field 'codigoMsg'.
     * 
     * @param codigoMsg the value of field 'codigoMsg'.
     */
    public void setCodigoMsg(java.lang.String codigoMsg)
    {
        java.lang.Object oldCodigoMsg = this._codigoMsg;
        this._codigoMsg = codigoMsg;
        notifyPropertyChangeListeners("_codigoMsg", oldCodigoMsg, this._codigoMsg);
    } //-- void setCodigoMsg(java.lang.String) 

    /**
     * Sets the value of field 'info'.
     * 
     * @param info the value of field 'info'.
     */
    public void setInfo(java.lang.String info)
    {
        java.lang.Object oldInfo = this._info;
        this._info = info;
        notifyPropertyChangeListeners("_info", oldInfo, this._info);
    } //-- void setInfo(java.lang.String) 

    /**
     * Sets the value of field 'nivelErr'.
     * 
     * @param nivelErr the value of field 'nivelErr'.
     */
    public void setNivelErr(long nivelErr)
    {
        java.lang.Object oldNivelErr = new java.lang.Long(this._nivelErr);
        this._nivelErr = nivelErr;
        this._has_nivelErr = true;
        notifyPropertyChangeListeners("_nivelErr", oldNivelErr, new java.lang.Long(this._nivelErr));
    } //-- void setNivelErr(long) 

    /**
     * Sets the value of field 'vals'.
     * 
     * @param vals the value of field 'vals'.
     */
    public void setVals(ar.org.sicel.proc.v1.lote.Vals vals)
    {
        java.lang.Object oldVals = this._vals;
        this._vals = vals;
        notifyPropertyChangeListeners("_vals", oldVals, this._vals);
    } //-- void setVals(ar.org.sicel.proc.v1.lote.Vals) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Object
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (ar.org.sicel.proc.v1.lote.Rdo) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Rdo.class, reader);
    } //-- java.lang.Object unmarshal(java.io.Reader) 

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
