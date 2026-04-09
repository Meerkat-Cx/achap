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
import java.util.Date;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class CompoRacial.
 * 
 * @version $Revision$ $Date$
 */
public class CompoRacial implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fechaNacimiento
     */
    private java.util.Date _fechaNacimiento;

    /**
     * Field _composicion
     */
    private ar.org.sicel.proc.v1.lote.Composicion _composicion;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public CompoRacial() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.CompoRacial()


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
        
        if (obj instanceof CompoRacial) {
        
            CompoRacial temp = (CompoRacial)obj;
            if (this._fechaNacimiento != null) {
                if (temp._fechaNacimiento == null) return false;
                else if (!(this._fechaNacimiento.equals(temp._fechaNacimiento))) 
                    return false;
            }
            else if (temp._fechaNacimiento != null)
                return false;
            if (this._composicion != null) {
                if (temp._composicion == null) return false;
                else if (!(this._composicion.equals(temp._composicion))) 
                    return false;
            }
            else if (temp._composicion != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'composicion'.
     * 
     * @return Composicion
     * @return the value of field 'composicion'.
     */
    public ar.org.sicel.proc.v1.lote.Composicion getComposicion()
    {
        return this._composicion;
    } //-- ar.org.sicel.proc.v1.lote.Composicion getComposicion() 

    /**
     * Returns the value of field 'fechaNacimiento'.
     * 
     * @return Date
     * @return the value of field 'fechaNacimiento'.
     */
    public java.util.Date getFechaNacimiento()
    {
        return this._fechaNacimiento;
    } //-- java.util.Date getFechaNacimiento() 

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
     * Sets the value of field 'composicion'.
     * 
     * @param composicion the value of field 'composicion'.
     */
    public void setComposicion(ar.org.sicel.proc.v1.lote.Composicion composicion)
    {
        java.lang.Object oldComposicion = this._composicion;
        this._composicion = composicion;
        notifyPropertyChangeListeners("_composicion", oldComposicion, this._composicion);
    } //-- void setComposicion(ar.org.sicel.proc.v1.lote.Composicion) 

    /**
     * Sets the value of field 'fechaNacimiento'.
     * 
     * @param fechaNacimiento the value of field 'fechaNacimiento'.
     */
    public void setFechaNacimiento(java.util.Date fechaNacimiento)
    {
        java.lang.Object oldFechaNacimiento = this._fechaNacimiento;
        this._fechaNacimiento = fechaNacimiento;
        notifyPropertyChangeListeners("_fechaNacimiento", oldFechaNacimiento, this._fechaNacimiento);
    } //-- void setFechaNacimiento(java.util.Date) 

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
        return (ar.org.sicel.proc.v1.lote.CompoRacial) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.CompoRacial.class, reader);
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
