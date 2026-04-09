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
 * Class TEvtAltaChoice.
 * 
 * @version $Revision$ $Date$
 */
public class TEvtAltaChoice implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _empadronar
     */
    private ar.org.sicel.proc.v1.lote.Empadronar _empadronar;

    /**
     * Field _padres
     */
    private ar.org.sicel.proc.v1.lote.Padres _padres;

    /**
     * Field _compoRacial
     */
    private ar.org.sicel.proc.v1.lote.CompoRacial _compoRacial;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtAltaChoice() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TEvtAltaChoice()


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
        
        if (obj instanceof TEvtAltaChoice) {
        
            TEvtAltaChoice temp = (TEvtAltaChoice)obj;
            if (this._empadronar != null) {
                if (temp._empadronar == null) return false;
                else if (!(this._empadronar.equals(temp._empadronar))) 
                    return false;
            }
            else if (temp._empadronar != null)
                return false;
            if (this._padres != null) {
                if (temp._padres == null) return false;
                else if (!(this._padres.equals(temp._padres))) 
                    return false;
            }
            else if (temp._padres != null)
                return false;
            if (this._compoRacial != null) {
                if (temp._compoRacial == null) return false;
                else if (!(this._compoRacial.equals(temp._compoRacial))) 
                    return false;
            }
            else if (temp._compoRacial != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'compoRacial'.
     * 
     * @return CompoRacial
     * @return the value of field 'compoRacial'.
     */
    public ar.org.sicel.proc.v1.lote.CompoRacial getCompoRacial()
    {
        return this._compoRacial;
    } //-- ar.org.sicel.proc.v1.lote.CompoRacial getCompoRacial() 

    /**
     * Returns the value of field 'empadronar'.
     * 
     * @return Empadronar
     * @return the value of field 'empadronar'.
     */
    public ar.org.sicel.proc.v1.lote.Empadronar getEmpadronar()
    {
        return this._empadronar;
    } //-- ar.org.sicel.proc.v1.lote.Empadronar getEmpadronar() 

    /**
     * Returns the value of field 'padres'.
     * 
     * @return Padres
     * @return the value of field 'padres'.
     */
    public ar.org.sicel.proc.v1.lote.Padres getPadres()
    {
        return this._padres;
    } //-- ar.org.sicel.proc.v1.lote.Padres getPadres() 

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
     * Sets the value of field 'compoRacial'.
     * 
     * @param compoRacial the value of field 'compoRacial'.
     */
    public void setCompoRacial(ar.org.sicel.proc.v1.lote.CompoRacial compoRacial)
    {
        java.lang.Object oldCompoRacial = this._compoRacial;
        this._compoRacial = compoRacial;
        notifyPropertyChangeListeners("_compoRacial", oldCompoRacial, this._compoRacial);
    } //-- void setCompoRacial(ar.org.sicel.proc.v1.lote.CompoRacial) 

    /**
     * Sets the value of field 'empadronar'.
     * 
     * @param empadronar the value of field 'empadronar'.
     */
    public void setEmpadronar(ar.org.sicel.proc.v1.lote.Empadronar empadronar)
    {
        java.lang.Object oldEmpadronar = this._empadronar;
        this._empadronar = empadronar;
        notifyPropertyChangeListeners("_empadronar", oldEmpadronar, this._empadronar);
    } //-- void setEmpadronar(ar.org.sicel.proc.v1.lote.Empadronar) 

    /**
     * Sets the value of field 'padres'.
     * 
     * @param padres the value of field 'padres'.
     */
    public void setPadres(ar.org.sicel.proc.v1.lote.Padres padres)
    {
        java.lang.Object oldPadres = this._padres;
        this._padres = padres;
        notifyPropertyChangeListeners("_padres", oldPadres, this._padres);
    } //-- void setPadres(ar.org.sicel.proc.v1.lote.Padres) 

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
        return (ar.org.sicel.proc.v1.lote.TEvtAltaChoice) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.TEvtAltaChoice.class, reader);
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
