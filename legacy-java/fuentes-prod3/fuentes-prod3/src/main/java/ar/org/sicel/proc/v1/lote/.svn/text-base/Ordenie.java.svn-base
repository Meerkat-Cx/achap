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

import ar.org.sicel.proc.v1.lote.types.STRechazo;
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
 * cant=cant de horarios
 * 
 * @version $Revision$ $Date$
 */
public class Ordenie implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rechazo
     */
    private ar.org.sicel.proc.v1.lote.types.STRechazo _rechazo;

    /**
     * Field _valoresOrdenie
     */
    private ar.org.sicel.proc.v1.lote.ValoresOrdenie _valoresOrdenie;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Ordenie() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Ordenie()


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
        
        if (obj instanceof Ordenie) {
        
            Ordenie temp = (Ordenie)obj;
            if (this._rechazo != null) {
                if (temp._rechazo == null) return false;
                else if (!(this._rechazo.equals(temp._rechazo))) 
                    return false;
            }
            else if (temp._rechazo != null)
                return false;
            if (this._valoresOrdenie != null) {
                if (temp._valoresOrdenie == null) return false;
                else if (!(this._valoresOrdenie.equals(temp._valoresOrdenie))) 
                    return false;
            }
            else if (temp._valoresOrdenie != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'rechazo'.
     * 
     * @return STRechazo
     * @return the value of field 'rechazo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STRechazo getRechazo()
    {
        return this._rechazo;
    } //-- ar.org.sicel.proc.v1.lote.types.STRechazo getRechazo() 

    /**
     * Returns the value of field 'valoresOrdenie'.
     * 
     * @return ValoresOrdenie
     * @return the value of field 'valoresOrdenie'.
     */
    public ar.org.sicel.proc.v1.lote.ValoresOrdenie getValoresOrdenie()
    {
        return this._valoresOrdenie;
    } //-- ar.org.sicel.proc.v1.lote.ValoresOrdenie getValoresOrdenie() 

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
     * Sets the value of field 'rechazo'.
     * 
     * @param rechazo the value of field 'rechazo'.
     */
    public void setRechazo(ar.org.sicel.proc.v1.lote.types.STRechazo rechazo)
    {
        java.lang.Object oldRechazo = this._rechazo;
        this._rechazo = rechazo;
        notifyPropertyChangeListeners("_rechazo", oldRechazo, this._rechazo);
    } //-- void setRechazo(ar.org.sicel.proc.v1.lote.types.STRechazo) 

    /**
     * Sets the value of field 'valoresOrdenie'.
     * 
     * @param valoresOrdenie the value of field 'valoresOrdenie'.
     */
    public void setValoresOrdenie(ar.org.sicel.proc.v1.lote.ValoresOrdenie valoresOrdenie)
    {
        java.lang.Object oldValoresOrdenie = this._valoresOrdenie;
        this._valoresOrdenie = valoresOrdenie;
        notifyPropertyChangeListeners("_valoresOrdenie", oldValoresOrdenie, this._valoresOrdenie);
    } //-- void setValoresOrdenie(ar.org.sicel.proc.v1.lote.ValoresOrdenie) 

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
        return (ar.org.sicel.proc.v1.lote.Ordenie) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Ordenie.class, reader);
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
