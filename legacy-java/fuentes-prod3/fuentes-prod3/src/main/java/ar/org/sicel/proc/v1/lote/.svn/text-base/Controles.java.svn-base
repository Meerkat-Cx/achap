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
 * Class Controles.
 * 
 * @version $Revision$ $Date$
 */
public class Controles implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _controlList
     */
    private java.util.Vector _controlList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Controles() {
        super();
        _controlList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Controles()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addControl
     * 
     * 
     * 
     * @param vControl
     */
    public void addControl(ar.org.sicel.proc.v1.lote.Control vControl)
        throws java.lang.IndexOutOfBoundsException
    {
        _controlList.addElement(vControl);
        notifyPropertyChangeListeners("_controlList", null, _controlList);
    } //-- void addControl(ar.org.sicel.proc.v1.lote.Control) 

    /**
     * Method addControl
     * 
     * 
     * 
     * @param index
     * @param vControl
     */
    public void addControl(int index, ar.org.sicel.proc.v1.lote.Control vControl)
        throws java.lang.IndexOutOfBoundsException
    {
        _controlList.insertElementAt(vControl, index);
        notifyPropertyChangeListeners("_controlList", null, _controlList);
    } //-- void addControl(int, ar.org.sicel.proc.v1.lote.Control) 

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
     * Method enumerateControl
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateControl()
    {
        return _controlList.elements();
    } //-- java.util.Enumeration enumerateControl() 

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
        
        if (obj instanceof Controles) {
        
            Controles temp = (Controles)obj;
            if (this._controlList != null) {
                if (temp._controlList == null) return false;
                else if (!(this._controlList.equals(temp._controlList))) 
                    return false;
            }
            else if (temp._controlList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getControl
     * 
     * 
     * 
     * @param index
     * @return Control
     */
    public ar.org.sicel.proc.v1.lote.Control getControl(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _controlList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Control) _controlList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Control getControl(int) 

    /**
     * Method getControl
     * 
     * 
     * 
     * @return Control
     */
    public ar.org.sicel.proc.v1.lote.Control[] getControl()
    {
        int size = _controlList.size();
        ar.org.sicel.proc.v1.lote.Control[] mArray = new ar.org.sicel.proc.v1.lote.Control[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Control) _controlList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Control[] getControl() 

    /**
     * Method getControlCount
     * 
     * 
     * 
     * @return int
     */
    public int getControlCount()
    {
        return _controlList.size();
    } //-- int getControlCount() 

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
     * Method removeAllControl
     * 
     */
    public void removeAllControl()
    {
        _controlList.removeAllElements();
        notifyPropertyChangeListeners("_controlList", null, _controlList);
    } //-- void removeAllControl() 

    /**
     * Method removeControl
     * 
     * 
     * 
     * @param index
     * @return Control
     */
    public ar.org.sicel.proc.v1.lote.Control removeControl(int index)
    {
        java.lang.Object obj = _controlList.elementAt(index);
        _controlList.removeElementAt(index);
        notifyPropertyChangeListeners("_controlList", null, _controlList);
        return (ar.org.sicel.proc.v1.lote.Control) obj;
    } //-- ar.org.sicel.proc.v1.lote.Control removeControl(int) 

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
     * Method setControl
     * 
     * 
     * 
     * @param index
     * @param vControl
     */
    public void setControl(int index, ar.org.sicel.proc.v1.lote.Control vControl)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _controlList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _controlList.setElementAt(vControl, index);
        notifyPropertyChangeListeners("_controlList", null, _controlList);
    } //-- void setControl(int, ar.org.sicel.proc.v1.lote.Control) 

    /**
     * Method setControl
     * 
     * 
     * 
     * @param controlArray
     */
    public void setControl(ar.org.sicel.proc.v1.lote.Control[] controlArray)
    {
        //-- copy array
        _controlList.removeAllElements();
        for (int i = 0; i < controlArray.length; i++) {
            _controlList.addElement(controlArray[i]);
        }
        notifyPropertyChangeListeners("_controlList", null, _controlList);
    } //-- void setControl(ar.org.sicel.proc.v1.lote.Control) 

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
        return (ar.org.sicel.proc.v1.lote.Controles) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Controles.class, reader);
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
