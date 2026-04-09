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
import org.exolab.castor.types.Time;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class ValoresOrdenie.
 * 
 * @version $Revision$ $Date$
 */
public class ValoresOrdenie implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _horaIniCtrl
     */
    private org.exolab.castor.types.Time _horaIniCtrl;

    /**
     * Field _horaFinCtrl
     */
    private org.exolab.castor.types.Time _horaFinCtrl;

    /**
     * Field _meds
     */
    private ar.org.sicel.proc.v1.lote.Meds _meds;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public ValoresOrdenie() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.ValoresOrdenie()


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
        
        if (obj instanceof ValoresOrdenie) {
        
            ValoresOrdenie temp = (ValoresOrdenie)obj;
            if (this._horaIniCtrl != null) {
                if (temp._horaIniCtrl == null) return false;
                else if (!(this._horaIniCtrl.equals(temp._horaIniCtrl))) 
                    return false;
            }
            else if (temp._horaIniCtrl != null)
                return false;
            if (this._horaFinCtrl != null) {
                if (temp._horaFinCtrl == null) return false;
                else if (!(this._horaFinCtrl.equals(temp._horaFinCtrl))) 
                    return false;
            }
            else if (temp._horaFinCtrl != null)
                return false;
            if (this._meds != null) {
                if (temp._meds == null) return false;
                else if (!(this._meds.equals(temp._meds))) 
                    return false;
            }
            else if (temp._meds != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'horaFinCtrl'.
     * 
     * @return Time
     * @return the value of field 'horaFinCtrl'.
     */
    public org.exolab.castor.types.Time getHoraFinCtrl()
    {
        return this._horaFinCtrl;
    } //-- org.exolab.castor.types.Time getHoraFinCtrl() 

    /**
     * Returns the value of field 'horaIniCtrl'.
     * 
     * @return Time
     * @return the value of field 'horaIniCtrl'.
     */
    public org.exolab.castor.types.Time getHoraIniCtrl()
    {
        return this._horaIniCtrl;
    } //-- org.exolab.castor.types.Time getHoraIniCtrl() 

    /**
     * Returns the value of field 'meds'.
     * 
     * @return Meds
     * @return the value of field 'meds'.
     */
    public ar.org.sicel.proc.v1.lote.Meds getMeds()
    {
        return this._meds;
    } //-- ar.org.sicel.proc.v1.lote.Meds getMeds() 

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
     * Sets the value of field 'horaFinCtrl'.
     * 
     * @param horaFinCtrl the value of field 'horaFinCtrl'.
     */
    public void setHoraFinCtrl(org.exolab.castor.types.Time horaFinCtrl)
    {
        java.lang.Object oldHoraFinCtrl = this._horaFinCtrl;
        this._horaFinCtrl = horaFinCtrl;
        notifyPropertyChangeListeners("_horaFinCtrl", oldHoraFinCtrl, this._horaFinCtrl);
    } //-- void setHoraFinCtrl(org.exolab.castor.types.Time) 

    /**
     * Sets the value of field 'horaIniCtrl'.
     * 
     * @param horaIniCtrl the value of field 'horaIniCtrl'.
     */
    public void setHoraIniCtrl(org.exolab.castor.types.Time horaIniCtrl)
    {
        java.lang.Object oldHoraIniCtrl = this._horaIniCtrl;
        this._horaIniCtrl = horaIniCtrl;
        notifyPropertyChangeListeners("_horaIniCtrl", oldHoraIniCtrl, this._horaIniCtrl);
    } //-- void setHoraIniCtrl(org.exolab.castor.types.Time) 

    /**
     * Sets the value of field 'meds'.
     * 
     * @param meds the value of field 'meds'.
     */
    public void setMeds(ar.org.sicel.proc.v1.lote.Meds meds)
    {
        java.lang.Object oldMeds = this._meds;
        this._meds = meds;
        notifyPropertyChangeListeners("_meds", oldMeds, this._meds);
    } //-- void setMeds(ar.org.sicel.proc.v1.lote.Meds) 

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
        return (ar.org.sicel.proc.v1.lote.ValoresOrdenie) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.ValoresOrdenie.class, reader);
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
