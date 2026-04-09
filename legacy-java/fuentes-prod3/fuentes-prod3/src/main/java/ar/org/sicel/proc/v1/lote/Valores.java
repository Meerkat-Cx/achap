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
 * Class Valores.
 * 
 * @version $Revision$ $Date$
 */
public class Valores implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * por ej si es un evento de tomada de peso, los kg
     */
    private java.util.Vector _valorList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Valores() {
        super();
        _valorList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Valores()


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
     * Method addValor
     * 
     * 
     * 
     * @param vValor
     */
    public void addValor(java.lang.String vValor)
        throws java.lang.IndexOutOfBoundsException
    {
        _valorList.addElement(vValor);
        notifyPropertyChangeListeners("_valorList", null, _valorList);
    } //-- void addValor(java.lang.String) 

    /**
     * Method addValor
     * 
     * 
     * 
     * @param index
     * @param vValor
     */
    public void addValor(int index, java.lang.String vValor)
        throws java.lang.IndexOutOfBoundsException
    {
        _valorList.insertElementAt(vValor, index);
        notifyPropertyChangeListeners("_valorList", null, _valorList);
    } //-- void addValor(int, java.lang.String) 

    /**
     * Method enumerateValor
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateValor()
    {
        return _valorList.elements();
    } //-- java.util.Enumeration enumerateValor() 

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
        
        if (obj instanceof Valores) {
        
            Valores temp = (Valores)obj;
            if (this._valorList != null) {
                if (temp._valorList == null) return false;
                else if (!(this._valorList.equals(temp._valorList))) 
                    return false;
            }
            else if (temp._valorList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getValor
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String getValor(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _valorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (String)_valorList.elementAt(index);
    } //-- java.lang.String getValor(int) 

    /**
     * Method getValor
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String[] getValor()
    {
        int size = _valorList.size();
        java.lang.String[] mArray = new java.lang.String[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (String)_valorList.elementAt(index);
        }
        return mArray;
    } //-- java.lang.String[] getValor() 

    /**
     * Method getValorCount
     * 
     * 
     * 
     * @return int
     */
    public int getValorCount()
    {
        return _valorList.size();
    } //-- int getValorCount() 

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
     * Method removeAllValor
     * 
     */
    public void removeAllValor()
    {
        _valorList.removeAllElements();
        notifyPropertyChangeListeners("_valorList", null, _valorList);
    } //-- void removeAllValor() 

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
     * Method removeValor
     * 
     * 
     * 
     * @param index
     * @return String
     */
    public java.lang.String removeValor(int index)
    {
        java.lang.Object obj = _valorList.elementAt(index);
        _valorList.removeElementAt(index);
        notifyPropertyChangeListeners("_valorList", null, _valorList);
        return (String)obj;
    } //-- java.lang.String removeValor(int) 

    /**
     * Method setValor
     * 
     * 
     * 
     * @param index
     * @param vValor
     */
    public void setValor(int index, java.lang.String vValor)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _valorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _valorList.setElementAt(vValor, index);
        notifyPropertyChangeListeners("_valorList", null, _valorList);
    } //-- void setValor(int, java.lang.String) 

    /**
     * Method setValor
     * 
     * 
     * 
     * @param valorArray
     */
    public void setValor(java.lang.String[] valorArray)
    {
        //-- copy array
        _valorList.removeAllElements();
        for (int i = 0; i < valorArray.length; i++) {
            _valorList.addElement(valorArray[i]);
        }
        notifyPropertyChangeListeners("_valorList", null, _valorList);
    } //-- void setValor(java.lang.String) 

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
        return (ar.org.sicel.proc.v1.lote.Valores) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Valores.class, reader);
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
