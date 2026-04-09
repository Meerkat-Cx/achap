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
 * Class Nuevo.
 * 
 * @version $Revision$ $Date$
 */
public class Nuevo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _servsemen
     */
    private ar.org.sicel.proc.v1.lote.Servsemen _servsemen;

    /**
     * Field _embrion
     */
    private ar.org.sicel.proc.v1.lote.Embrion _embrion;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Nuevo() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Nuevo()


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
     * Note: hashCode() has not been overriden
     * 
     * @param obj
     * @return boolean
     */
    public boolean equals(java.lang.Object obj)
    {
        if ( this == obj )
            return true;
        
        if (obj instanceof Nuevo) {
        
            Nuevo temp = (Nuevo)obj;
            if (this._servsemen != null) {
                if (temp._servsemen == null) return false;
                else if (!(this._servsemen.equals(temp._servsemen))) 
                    return false;
            }
            else if (temp._servsemen != null)
                return false;
            if (this._embrion != null) {
                if (temp._embrion == null) return false;
                else if (!(this._embrion.equals(temp._embrion))) 
                    return false;
            }
            else if (temp._embrion != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'embrion'.
     * 
     * @return Embrion
     * @return the value of field 'embrion'.
     */
    public ar.org.sicel.proc.v1.lote.Embrion getEmbrion()
    {
        return this._embrion;
    } //-- ar.org.sicel.proc.v1.lote.Embrion getEmbrion() 

    /**
     * Returns the value of field 'servsemen'.
     * 
     * @return Servsemen
     * @return the value of field 'servsemen'.
     */
    public ar.org.sicel.proc.v1.lote.Servsemen getServsemen()
    {
        return this._servsemen;
    } //-- ar.org.sicel.proc.v1.lote.Servsemen getServsemen() 

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
     * Sets the value of field 'embrion'.
     * 
     * @param embrion the value of field 'embrion'.
     */
    public void setEmbrion(ar.org.sicel.proc.v1.lote.Embrion embrion)
    {
        java.lang.Object oldEmbrion = this._embrion;
        this._embrion = embrion;
        notifyPropertyChangeListeners("_embrion", oldEmbrion, this._embrion);
    } //-- void setEmbrion(ar.org.sicel.proc.v1.lote.Embrion) 

    /**
     * Sets the value of field 'servsemen'.
     * 
     * @param servsemen the value of field 'servsemen'.
     */
    public void setServsemen(ar.org.sicel.proc.v1.lote.Servsemen servsemen)
    {
        java.lang.Object oldServsemen = this._servsemen;
        this._servsemen = servsemen;
        notifyPropertyChangeListeners("_servsemen", oldServsemen, this._servsemen);
    } //-- void setServsemen(ar.org.sicel.proc.v1.lote.Servsemen) 

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
        return (ar.org.sicel.proc.v1.lote.Nuevo) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Nuevo.class, reader);
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
