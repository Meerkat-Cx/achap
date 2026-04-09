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
 * Class Horario.
 * 
 * @version $Revision$ $Date$
 */
public class Horario implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _horaIniOrdenie
     */
    private org.exolab.castor.types.Time _horaIniOrdenie;

    /**
     * Field _horaFinOrdenie
     */
    private org.exolab.castor.types.Time _horaFinOrdenie;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Horario() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Horario()


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
        
        if (obj instanceof Horario) {
        
            Horario temp = (Horario)obj;
            if (this._horaIniOrdenie != null) {
                if (temp._horaIniOrdenie == null) return false;
                else if (!(this._horaIniOrdenie.equals(temp._horaIniOrdenie))) 
                    return false;
            }
            else if (temp._horaIniOrdenie != null)
                return false;
            if (this._horaFinOrdenie != null) {
                if (temp._horaFinOrdenie == null) return false;
                else if (!(this._horaFinOrdenie.equals(temp._horaFinOrdenie))) 
                    return false;
            }
            else if (temp._horaFinOrdenie != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'horaFinOrdenie'.
     * 
     * @return Time
     * @return the value of field 'horaFinOrdenie'.
     */
    public org.exolab.castor.types.Time getHoraFinOrdenie()
    {
        return this._horaFinOrdenie;
    } //-- org.exolab.castor.types.Time getHoraFinOrdenie() 

    /**
     * Returns the value of field 'horaIniOrdenie'.
     * 
     * @return Time
     * @return the value of field 'horaIniOrdenie'.
     */
    public org.exolab.castor.types.Time getHoraIniOrdenie()
    {
        return this._horaIniOrdenie;
    } //-- org.exolab.castor.types.Time getHoraIniOrdenie() 

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
     * Sets the value of field 'horaFinOrdenie'.
     * 
     * @param horaFinOrdenie the value of field 'horaFinOrdenie'.
     */
    public void setHoraFinOrdenie(org.exolab.castor.types.Time horaFinOrdenie)
    {
        java.lang.Object oldHoraFinOrdenie = this._horaFinOrdenie;
        this._horaFinOrdenie = horaFinOrdenie;
        notifyPropertyChangeListeners("_horaFinOrdenie", oldHoraFinOrdenie, this._horaFinOrdenie);
    } //-- void setHoraFinOrdenie(org.exolab.castor.types.Time) 

    /**
     * Sets the value of field 'horaIniOrdenie'.
     * 
     * @param horaIniOrdenie the value of field 'horaIniOrdenie'.
     */
    public void setHoraIniOrdenie(org.exolab.castor.types.Time horaIniOrdenie)
    {
        java.lang.Object oldHoraIniOrdenie = this._horaIniOrdenie;
        this._horaIniOrdenie = horaIniOrdenie;
        notifyPropertyChangeListeners("_horaIniOrdenie", oldHoraIniOrdenie, this._horaIniOrdenie);
    } //-- void setHoraIniOrdenie(org.exolab.castor.types.Time) 

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
        return (ar.org.sicel.proc.v1.lote.Horario) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Horario.class, reader);
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
