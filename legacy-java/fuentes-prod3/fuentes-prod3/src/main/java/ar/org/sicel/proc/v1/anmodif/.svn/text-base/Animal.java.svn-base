/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.6</a>, using an XML
 * Schema.
 * $Id$
 */

package ar.org.sicel.proc.v1.anmodif;

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
 * Class Animal.
 * 
 * @version $Revision$ $Date$
 */
public class Animal implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * si bien el evt alta o semen no lleva el anim, luego del
     * proceso deberia tener el nuevo anim o el anim del semen
     */
    private long _IDAnim;

    /**
     * keeps track of state for field: _IDAnim
     */
    private boolean _has_IDAnim;

    /**
     * Field _reg
     */
    private ar.org.sicel.proc.v1.anmodif.Reg _reg;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Animal() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.anmodif.Animal()


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
     * Method deleteIDAnim
     * 
     */
    public void deleteIDAnim()
    {
        this._has_IDAnim= false;
        notifyPropertyChangeListeners("_IDAnim", new java.lang.Long(this._IDAnim), null);
    } //-- void deleteIDAnim() 

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
        
        if (obj instanceof Animal) {
        
            Animal temp = (Animal)obj;
            if (this._IDAnim != temp._IDAnim)
                return false;
            if (this._has_IDAnim != temp._has_IDAnim)
                return false;
            if (this._reg != null) {
                if (temp._reg == null) return false;
                else if (!(this._reg.equals(temp._reg))) 
                    return false;
            }
            else if (temp._reg != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'IDAnim'. The field 'IDAnim' has
     * the following description: si bien el evt alta o semen no
     * lleva el anim, luego del proceso deberia tener el nuevo anim
     * o el anim del semen
     * 
     * @return long
     * @return the value of field 'IDAnim'.
     */
    public long getIDAnim()
    {
        return this._IDAnim;
    } //-- long getIDAnim() 

    /**
     * Returns the value of field 'reg'.
     * 
     * @return Reg
     * @return the value of field 'reg'.
     */
    public ar.org.sicel.proc.v1.anmodif.Reg getReg()
    {
        return this._reg;
    } //-- ar.org.sicel.proc.v1.anmodif.Reg getReg() 

    /**
     * Method hasIDAnim
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDAnim()
    {
        return this._has_IDAnim;
    } //-- boolean hasIDAnim() 

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
     * Sets the value of field 'IDAnim'. The field 'IDAnim' has the
     * following description: si bien el evt alta o semen no lleva
     * el anim, luego del proceso deberia tener el nuevo anim o el
     * anim del semen
     * 
     * @param IDAnim the value of field 'IDAnim'.
     */
    public void setIDAnim(long IDAnim)
    {
        java.lang.Object oldIDAnim = new java.lang.Long(this._IDAnim);
        this._IDAnim = IDAnim;
        this._has_IDAnim = true;
        notifyPropertyChangeListeners("_IDAnim", oldIDAnim, new java.lang.Long(this._IDAnim));
    } //-- void setIDAnim(long) 

    /**
     * Sets the value of field 'reg'.
     * 
     * @param reg the value of field 'reg'.
     */
    public void setReg(ar.org.sicel.proc.v1.anmodif.Reg reg)
    {
        java.lang.Object oldReg = this._reg;
        this._reg = reg;
        notifyPropertyChangeListeners("_reg", oldReg, this._reg);
    } //-- void setReg(ar.org.sicel.proc.v1.anmodif.Reg) 

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
        return (ar.org.sicel.proc.v1.anmodif.Animal) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.anmodif.Animal.class, reader);
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
