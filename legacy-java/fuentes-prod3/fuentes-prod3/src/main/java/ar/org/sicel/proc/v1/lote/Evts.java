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
 * Class Evts.
 * 
 * @version $Revision$ $Date$
 */
public class Evts implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _evtList
     */
    private java.util.Vector _evtList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Evts() {
        super();
        _evtList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Evts()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addEvt
     * 
     * 
     * 
     * @param vEvt
     */
    public void addEvt(ar.org.sicel.proc.v1.lote.Evt vEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        _evtList.addElement(vEvt);
        notifyPropertyChangeListeners("_evtList", null, _evtList);
    } //-- void addEvt(ar.org.sicel.proc.v1.lote.Evt) 

    /**
     * Method addEvt
     * 
     * 
     * 
     * @param index
     * @param vEvt
     */
    public void addEvt(int index, ar.org.sicel.proc.v1.lote.Evt vEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        _evtList.insertElementAt(vEvt, index);
        notifyPropertyChangeListeners("_evtList", null, _evtList);
    } //-- void addEvt(int, ar.org.sicel.proc.v1.lote.Evt) 

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
     * Method enumerateEvt
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateEvt()
    {
        return _evtList.elements();
    } //-- java.util.Enumeration enumerateEvt() 

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
        
        if (obj instanceof Evts) {
        
            Evts temp = (Evts)obj;
            if (this._evtList != null) {
                if (temp._evtList == null) return false;
                else if (!(this._evtList.equals(temp._evtList))) 
                    return false;
            }
            else if (temp._evtList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getEvt
     * 
     * 
     * 
     * @param index
     * @return Evt
     */
    public ar.org.sicel.proc.v1.lote.Evt getEvt(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _evtList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Evt) _evtList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Evt getEvt(int) 

    /**
     * Method getEvt
     * 
     * 
     * 
     * @return Evt
     */
    public ar.org.sicel.proc.v1.lote.Evt[] getEvt()
    {
        int size = _evtList.size();
        ar.org.sicel.proc.v1.lote.Evt[] mArray = new ar.org.sicel.proc.v1.lote.Evt[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Evt) _evtList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Evt[] getEvt() 

    /**
     * Method getEvtCount
     * 
     * 
     * 
     * @return int
     */
    public int getEvtCount()
    {
        return _evtList.size();
    } //-- int getEvtCount() 

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
     * Method removeAllEvt
     * 
     */
    public void removeAllEvt()
    {
        _evtList.removeAllElements();
        notifyPropertyChangeListeners("_evtList", null, _evtList);
    } //-- void removeAllEvt() 

    /**
     * Method removeEvt
     * 
     * 
     * 
     * @param index
     * @return Evt
     */
    public ar.org.sicel.proc.v1.lote.Evt removeEvt(int index)
    {
        java.lang.Object obj = _evtList.elementAt(index);
        _evtList.removeElementAt(index);
        notifyPropertyChangeListeners("_evtList", null, _evtList);
        return (ar.org.sicel.proc.v1.lote.Evt) obj;
    } //-- ar.org.sicel.proc.v1.lote.Evt removeEvt(int) 

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
     * Method setEvt
     * 
     * 
     * 
     * @param index
     * @param vEvt
     */
    public void setEvt(int index, ar.org.sicel.proc.v1.lote.Evt vEvt)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _evtList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _evtList.setElementAt(vEvt, index);
        notifyPropertyChangeListeners("_evtList", null, _evtList);
    } //-- void setEvt(int, ar.org.sicel.proc.v1.lote.Evt) 

    /**
     * Method setEvt
     * 
     * 
     * 
     * @param evtArray
     */
    public void setEvt(ar.org.sicel.proc.v1.lote.Evt[] evtArray)
    {
        //-- copy array
        _evtList.removeAllElements();
        for (int i = 0; i < evtArray.length; i++) {
            _evtList.addElement(evtArray[i]);
        }
        notifyPropertyChangeListeners("_evtList", null, _evtList);
    } //-- void setEvt(ar.org.sicel.proc.v1.lote.Evt) 

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
        return (ar.org.sicel.proc.v1.lote.Evts) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Evts.class, reader);
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
