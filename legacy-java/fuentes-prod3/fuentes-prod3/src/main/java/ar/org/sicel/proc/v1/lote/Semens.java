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
 * Class Semens.
 * 
 * @version $Revision$ $Date$
 */
public class Semens implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _semenList
     */
    private java.util.Vector _semenList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Semens() {
        super();
        _semenList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Semens()


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
     * Method addSemen
     * 
     * 
     * 
     * @param vSemen
     */
    public void addSemen(ar.org.sicel.proc.v1.lote.Semen vSemen)
        throws java.lang.IndexOutOfBoundsException
    {
        _semenList.addElement(vSemen);
        notifyPropertyChangeListeners("_semenList", null, _semenList);
    } //-- void addSemen(ar.org.sicel.proc.v1.lote.Semen) 

    /**
     * Method addSemen
     * 
     * 
     * 
     * @param index
     * @param vSemen
     */
    public void addSemen(int index, ar.org.sicel.proc.v1.lote.Semen vSemen)
        throws java.lang.IndexOutOfBoundsException
    {
        _semenList.insertElementAt(vSemen, index);
        notifyPropertyChangeListeners("_semenList", null, _semenList);
    } //-- void addSemen(int, ar.org.sicel.proc.v1.lote.Semen) 

    /**
     * Method enumerateSemen
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateSemen()
    {
        return _semenList.elements();
    } //-- java.util.Enumeration enumerateSemen() 

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
        
        if (obj instanceof Semens) {
        
            Semens temp = (Semens)obj;
            if (this._semenList != null) {
                if (temp._semenList == null) return false;
                else if (!(this._semenList.equals(temp._semenList))) 
                    return false;
            }
            else if (temp._semenList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getSemen
     * 
     * 
     * 
     * @param index
     * @return Semen
     */
    public ar.org.sicel.proc.v1.lote.Semen getSemen(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _semenList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Semen) _semenList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Semen getSemen(int) 

    /**
     * Method getSemen
     * 
     * 
     * 
     * @return Semen
     */
    public ar.org.sicel.proc.v1.lote.Semen[] getSemen()
    {
        int size = _semenList.size();
        ar.org.sicel.proc.v1.lote.Semen[] mArray = new ar.org.sicel.proc.v1.lote.Semen[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Semen) _semenList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Semen[] getSemen() 

    /**
     * Method getSemenCount
     * 
     * 
     * 
     * @return int
     */
    public int getSemenCount()
    {
        return _semenList.size();
    } //-- int getSemenCount() 

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
     * Method removeAllSemen
     * 
     */
    public void removeAllSemen()
    {
        _semenList.removeAllElements();
        notifyPropertyChangeListeners("_semenList", null, _semenList);
    } //-- void removeAllSemen() 

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
     * Method removeSemen
     * 
     * 
     * 
     * @param index
     * @return Semen
     */
    public ar.org.sicel.proc.v1.lote.Semen removeSemen(int index)
    {
        java.lang.Object obj = _semenList.elementAt(index);
        _semenList.removeElementAt(index);
        notifyPropertyChangeListeners("_semenList", null, _semenList);
        return (ar.org.sicel.proc.v1.lote.Semen) obj;
    } //-- ar.org.sicel.proc.v1.lote.Semen removeSemen(int) 

    /**
     * Method setSemen
     * 
     * 
     * 
     * @param index
     * @param vSemen
     */
    public void setSemen(int index, ar.org.sicel.proc.v1.lote.Semen vSemen)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _semenList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _semenList.setElementAt(vSemen, index);
        notifyPropertyChangeListeners("_semenList", null, _semenList);
    } //-- void setSemen(int, ar.org.sicel.proc.v1.lote.Semen) 

    /**
     * Method setSemen
     * 
     * 
     * 
     * @param semenArray
     */
    public void setSemen(ar.org.sicel.proc.v1.lote.Semen[] semenArray)
    {
        //-- copy array
        _semenList.removeAllElements();
        for (int i = 0; i < semenArray.length; i++) {
            _semenList.addElement(semenArray[i]);
        }
        notifyPropertyChangeListeners("_semenList", null, _semenList);
    } //-- void setSemen(ar.org.sicel.proc.v1.lote.Semen) 

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
        return (ar.org.sicel.proc.v1.lote.Semens) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Semens.class, reader);
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
