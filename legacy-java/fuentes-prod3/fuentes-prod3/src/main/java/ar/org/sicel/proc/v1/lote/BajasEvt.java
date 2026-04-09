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
 * Class BajasEvt.
 * 
 * @version $Revision$ $Date$
 */
public class BajasEvt implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _bajaEvtList
     */
    private java.util.Vector _bajaEvtList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public BajasEvt() {
        super();
        _bajaEvtList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.BajasEvt()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addBajaEvt
     * 
     * 
     * 
     * @param vBajaEvt
     */
    public void addBajaEvt(ar.org.sicel.proc.v1.lote.BajaEvt vBajaEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        _bajaEvtList.addElement(vBajaEvt);
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
    } //-- void addBajaEvt(ar.org.sicel.proc.v1.lote.BajaEvt) 

    /**
     * Method addBajaEvt
     * 
     * 
     * 
     * @param index
     * @param vBajaEvt
     */
    public void addBajaEvt(int index, ar.org.sicel.proc.v1.lote.BajaEvt vBajaEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        _bajaEvtList.insertElementAt(vBajaEvt, index);
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
    } //-- void addBajaEvt(int, ar.org.sicel.proc.v1.lote.BajaEvt) 

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
     * Method enumerateBajaEvt
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateBajaEvt()
    {
        return _bajaEvtList.elements();
    } //-- java.util.Enumeration enumerateBajaEvt() 

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
        
        if (obj instanceof BajasEvt) {
        
            BajasEvt temp = (BajasEvt)obj;
            if (this._bajaEvtList != null) {
                if (temp._bajaEvtList == null) return false;
                else if (!(this._bajaEvtList.equals(temp._bajaEvtList))) 
                    return false;
            }
            else if (temp._bajaEvtList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getBajaEvt
     * 
     * 
     * 
     * @param index
     * @return BajaEvt
     */
    public ar.org.sicel.proc.v1.lote.BajaEvt getBajaEvt(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _bajaEvtList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.BajaEvt) _bajaEvtList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.BajaEvt getBajaEvt(int) 

    /**
     * Method getBajaEvt
     * 
     * 
     * 
     * @return BajaEvt
     */
    public ar.org.sicel.proc.v1.lote.BajaEvt[] getBajaEvt()
    {
        int size = _bajaEvtList.size();
        ar.org.sicel.proc.v1.lote.BajaEvt[] mArray = new ar.org.sicel.proc.v1.lote.BajaEvt[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.BajaEvt) _bajaEvtList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.BajaEvt[] getBajaEvt() 

    /**
     * Method getBajaEvtCount
     * 
     * 
     * 
     * @return int
     */
    public int getBajaEvtCount()
    {
        return _bajaEvtList.size();
    } //-- int getBajaEvtCount() 

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
     * Method removeAllBajaEvt
     * 
     */
    public void removeAllBajaEvt()
    {
        _bajaEvtList.removeAllElements();
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
    } //-- void removeAllBajaEvt() 

    /**
     * Method removeBajaEvt
     * 
     * 
     * 
     * @param index
     * @return BajaEvt
     */
    public ar.org.sicel.proc.v1.lote.BajaEvt removeBajaEvt(int index)
    {
        java.lang.Object obj = _bajaEvtList.elementAt(index);
        _bajaEvtList.removeElementAt(index);
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
        return (ar.org.sicel.proc.v1.lote.BajaEvt) obj;
    } //-- ar.org.sicel.proc.v1.lote.BajaEvt removeBajaEvt(int) 

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
     * Method setBajaEvt
     * 
     * 
     * 
     * @param index
     * @param vBajaEvt
     */
    public void setBajaEvt(int index, ar.org.sicel.proc.v1.lote.BajaEvt vBajaEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _bajaEvtList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _bajaEvtList.setElementAt(vBajaEvt, index);
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
    } //-- void setBajaEvt(int, ar.org.sicel.proc.v1.lote.BajaEvt) 

    /**
     * Method setBajaEvt
     * 
     * 
     * 
     * @param bajaEvtArray
     */
    public void setBajaEvt(ar.org.sicel.proc.v1.lote.BajaEvt[] bajaEvtArray)
    {
        //-- copy array
        _bajaEvtList.removeAllElements();
        for (int i = 0; i < bajaEvtArray.length; i++) {
            _bajaEvtList.addElement(bajaEvtArray[i]);
        }
        notifyPropertyChangeListeners("_bajaEvtList", null, _bajaEvtList);
    } //-- void setBajaEvt(ar.org.sicel.proc.v1.lote.BajaEvt) 

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
        return (ar.org.sicel.proc.v1.lote.BajasEvt) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.BajasEvt.class, reader);
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
