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
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Meds.
 * 
 * @version $Revision$ $Date$
 */
public class Meds implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _medicionList
     */
    private java.util.Vector _medicionList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Meds() {
        super();
        _medicionList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Meds()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMedicion
     * 
     * 
     * 
     * @param vMedicion
     */
    public void addMedicion(ar.org.sicel.proc.v1.lote.Medicion vMedicion)
        throws java.lang.IndexOutOfBoundsException
    {
        _medicionList.addElement(vMedicion);
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
    } //-- void addMedicion(ar.org.sicel.proc.v1.lote.Medicion) 

    /**
     * Method addMedicion
     * 
     * 
     * 
     * @param index
     * @param vMedicion
     */
    public void addMedicion(int index, ar.org.sicel.proc.v1.lote.Medicion vMedicion)
        throws java.lang.IndexOutOfBoundsException
    {
        _medicionList.insertElementAt(vMedicion, index);
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
    } //-- void addMedicion(int, ar.org.sicel.proc.v1.lote.Medicion) 

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
     * Method enumerateMedicion
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMedicion()
    {
        return _medicionList.elements();
    } //-- java.util.Enumeration enumerateMedicion() 

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
        
        if (obj instanceof Meds) {
        
            Meds temp = (Meds)obj;
            if (this._medicionList != null) {
                if (temp._medicionList == null) return false;
                else if (!(this._medicionList.equals(temp._medicionList))) 
                    return false;
            }
            else if (temp._medicionList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getMedicion
     * 
     * 
     * 
     * @param index
     * @return Medicion
     */
    public ar.org.sicel.proc.v1.lote.Medicion getMedicion(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _medicionList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Medicion) _medicionList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Medicion getMedicion(int) 

    /**
     * Method getMedicion
     * 
     * 
     * 
     * @return Medicion
     */
    public ar.org.sicel.proc.v1.lote.Medicion[] getMedicion()
    {
        int size = _medicionList.size();
        ar.org.sicel.proc.v1.lote.Medicion[] mArray = new ar.org.sicel.proc.v1.lote.Medicion[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Medicion) _medicionList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Medicion[] getMedicion() 

    /**
     * Method getMedicionCount
     * 
     * 
     * 
     * @return int
     */
    public int getMedicionCount()
    {
        return _medicionList.size();
    } //-- int getMedicionCount() 

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
     * Method removeAllMedicion
     * 
     */
    public void removeAllMedicion()
    {
        _medicionList.removeAllElements();
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
    } //-- void removeAllMedicion() 

    /**
     * Method removeMedicion
     * 
     * 
     * 
     * @param index
     * @return Medicion
     */
    public ar.org.sicel.proc.v1.lote.Medicion removeMedicion(int index)
    {
        java.lang.Object obj = _medicionList.elementAt(index);
        _medicionList.removeElementAt(index);
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
        return (ar.org.sicel.proc.v1.lote.Medicion) obj;
    } //-- ar.org.sicel.proc.v1.lote.Medicion removeMedicion(int) 

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
     * Method setMedicion
     * 
     * 
     * 
     * @param index
     * @param vMedicion
     */
    public void setMedicion(int index, ar.org.sicel.proc.v1.lote.Medicion vMedicion)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _medicionList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _medicionList.setElementAt(vMedicion, index);
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
    } //-- void setMedicion(int, ar.org.sicel.proc.v1.lote.Medicion) 

    /**
     * Method setMedicion
     * 
     * 
     * 
     * @param medicionArray
     */
    public void setMedicion(ar.org.sicel.proc.v1.lote.Medicion[] medicionArray)
    {
        //-- copy array
        _medicionList.removeAllElements();
        for (int i = 0; i < medicionArray.length; i++) {
            _medicionList.addElement(medicionArray[i]);
        }
        notifyPropertyChangeListeners("_medicionList", null, _medicionList);
    } //-- void setMedicion(ar.org.sicel.proc.v1.lote.Medicion) 

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
        return (ar.org.sicel.proc.v1.lote.Meds) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Meds.class, reader);
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
