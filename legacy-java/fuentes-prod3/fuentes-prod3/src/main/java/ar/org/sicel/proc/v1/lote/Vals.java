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
 * Class Vals.
 * 
 * @version $Revision$ $Date$
 */
public class Vals implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _valList
     */
    private java.util.Vector _valList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Vals() {
        super();
        _valList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Vals()


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
     * Method addVal
     * 
     * 
     * 
     * @param vVal
     */
    public void addVal(java.lang.String vVal)
        throws java.lang.IndexOutOfBoundsException
    {
        _valList.addElement(vVal);
        notifyPropertyChangeListeners("_valList", null, _valList);
    } //-- void addVal(java.lang.String) 

    /**
     * Method addVal
     * 
     * 
     * 
     * @param index
     * @param vVal
     */
    public void addVal(int index, java.lang.String vVal)
        throws java.lang.IndexOutOfBoundsException
    {
        _valList.insertElementAt(vVal, index);
        notifyPropertyChangeListeners("_valList", null, _valList);
    } //-- void addVal(int, java.lang.String) 

    /**
     * Method enumerateVal
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateVal()
    {
        return _valList.elements();
    } //-- java.util.Enumeration enumerateVal() 

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
        
        if (obj instanceof Vals) {
        
            Vals temp = (Vals)obj;
            if (this._valList != null) {
                if (temp._valList == null) return false;
                else if (!(this._valList.equals(temp._valList))) 
                    return false;
            }
            else if (temp._valList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getVal
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String getVal(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _valList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (String)_valList.elementAt(index);
    } //-- java.lang.String getVal(int) 

    /**
     * Method getVal
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String[] getVal()
    {
        int size = _valList.size();
        java.lang.String[] mArray = new java.lang.String[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (String)_valList.elementAt(index);
        }
        return mArray;
    } //-- java.lang.String[] getVal() 

    /**
     * Method getValCount
     * 
     * 
     * 
     * @return int
     */
    public int getValCount()
    {
        return _valList.size();
    } //-- int getValCount() 

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
     * Method removeAllVal
     * 
     */
    public void removeAllVal()
    {
        _valList.removeAllElements();
        notifyPropertyChangeListeners("_valList", null, _valList);
    } //-- void removeAllVal() 

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
     * Method removeVal
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String removeVal(int index)
    {
        java.lang.Object obj = _valList.elementAt(index);
        _valList.removeElementAt(index);
        notifyPropertyChangeListeners("_valList", null, _valList);
        return (String)obj;
    } //-- java.lang.String removeVal(int) 

    /**
     * Method setVal
     * 
     * 
     * 
     * @param index
     * @param vVal
     */
    public void setVal(int index, java.lang.String vVal)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _valList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _valList.setElementAt(vVal, index);
        notifyPropertyChangeListeners("_valList", null, _valList);
    } //-- void setVal(int, java.lang.String) 

    /**
     * Method setVal
     * 
     * 
     * 
     * @param valArray
     */
    public void setVal(java.lang.String[] valArray)
    {
        //-- copy array
        _valList.removeAllElements();
        for (int i = 0; i < valArray.length; i++) {
            _valList.addElement(valArray[i]);
        }
        notifyPropertyChangeListeners("_valList", null, _valList);
    } //-- void setVal(java.lang.String) 

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
        return (ar.org.sicel.proc.v1.lote.Vals) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Vals.class, reader);
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
