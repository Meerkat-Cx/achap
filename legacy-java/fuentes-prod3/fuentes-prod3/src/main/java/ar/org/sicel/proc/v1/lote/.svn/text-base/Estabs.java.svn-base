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
 * Class Estabs.
 * 
 * @version $Revision$ $Date$
 */
public class Estabs implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _estabList
     */
    private java.util.Vector _estabList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Estabs() {
        super();
        _estabList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Estabs()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addEstab
     * 
     * 
     * 
     * @param vEstab
     */
    public void addEstab(ar.org.sicel.proc.v1.lote.Estab vEstab)
        throws java.lang.IndexOutOfBoundsException
    {
        _estabList.addElement(vEstab);
        notifyPropertyChangeListeners("_estabList", null, _estabList);
    } //-- void addEstab(ar.org.sicel.proc.v1.lote.Estab) 

    /**
     * Method addEstab
     * 
     * 
     * 
     * @param index
     * @param vEstab
     */
    public void addEstab(int index, ar.org.sicel.proc.v1.lote.Estab vEstab)
        throws java.lang.IndexOutOfBoundsException
    {
        _estabList.insertElementAt(vEstab, index);
        notifyPropertyChangeListeners("_estabList", null, _estabList);
    } //-- void addEstab(int, ar.org.sicel.proc.v1.lote.Estab) 

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
     * Method enumerateEstab
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateEstab()
    {
        return _estabList.elements();
    } //-- java.util.Enumeration enumerateEstab() 

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
        
        if (obj instanceof Estabs) {
        
            Estabs temp = (Estabs)obj;
            if (this._estabList != null) {
                if (temp._estabList == null) return false;
                else if (!(this._estabList.equals(temp._estabList))) 
                    return false;
            }
            else if (temp._estabList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getEstab
     * 
     * 
     * 
     * @param index
     * @return Estab
     */
    public ar.org.sicel.proc.v1.lote.Estab getEstab(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _estabList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Estab) _estabList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Estab getEstab(int) 

    /**
     * Method getEstab
     * 
     * 
     * 
     * @return Estab
     */
    public ar.org.sicel.proc.v1.lote.Estab[] getEstab()
    {
        int size = _estabList.size();
        ar.org.sicel.proc.v1.lote.Estab[] mArray = new ar.org.sicel.proc.v1.lote.Estab[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Estab) _estabList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Estab[] getEstab() 

    /**
     * Method getEstabCount
     * 
     * 
     * 
     * @return int
     */
    public int getEstabCount()
    {
        return _estabList.size();
    } //-- int getEstabCount() 

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
     * Method removeAllEstab
     * 
     */
    public void removeAllEstab()
    {
        _estabList.removeAllElements();
        notifyPropertyChangeListeners("_estabList", null, _estabList);
    } //-- void removeAllEstab() 

    /**
     * Method removeEstab
     * 
     * 
     * 
     * @param index
     * @return Estab
     */
    public ar.org.sicel.proc.v1.lote.Estab removeEstab(int index)
    {
        java.lang.Object obj = _estabList.elementAt(index);
        _estabList.removeElementAt(index);
        notifyPropertyChangeListeners("_estabList", null, _estabList);
        return (ar.org.sicel.proc.v1.lote.Estab) obj;
    } //-- ar.org.sicel.proc.v1.lote.Estab removeEstab(int) 

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
     * Method setEstab
     * 
     * 
     * 
     * @param index
     * @param vEstab
     */
    public void setEstab(int index, ar.org.sicel.proc.v1.lote.Estab vEstab)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _estabList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _estabList.setElementAt(vEstab, index);
        notifyPropertyChangeListeners("_estabList", null, _estabList);
    } //-- void setEstab(int, ar.org.sicel.proc.v1.lote.Estab) 

    /**
     * Method setEstab
     * 
     * 
     * 
     * @param estabArray
     */
    public void setEstab(ar.org.sicel.proc.v1.lote.Estab[] estabArray)
    {
        //-- copy array
        _estabList.removeAllElements();
        for (int i = 0; i < estabArray.length; i++) {
            _estabList.addElement(estabArray[i]);
        }
        notifyPropertyChangeListeners("_estabList", null, _estabList);
    } //-- void setEstab(ar.org.sicel.proc.v1.lote.Estab) 

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
        return (ar.org.sicel.proc.v1.lote.Estabs) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Estabs.class, reader);
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
