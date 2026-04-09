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
 * Class Ordenies.
 * 
 * @version $Revision$ $Date$
 */
public class Ordenies implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * cant=cant de horarios
     */
    private java.util.Vector _ordenieList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Ordenies() {
        super();
        _ordenieList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Ordenies()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addOrdenie
     * 
     * 
     * 
     * @param vOrdenie
     */
    public void addOrdenie(ar.org.sicel.proc.v1.lote.Ordenie vOrdenie)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_ordenieList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _ordenieList.addElement(vOrdenie);
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
    } //-- void addOrdenie(ar.org.sicel.proc.v1.lote.Ordenie) 

    /**
     * Method addOrdenie
     * 
     * 
     * 
     * @param index
     * @param vOrdenie
     */
    public void addOrdenie(int index, ar.org.sicel.proc.v1.lote.Ordenie vOrdenie)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_ordenieList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _ordenieList.insertElementAt(vOrdenie, index);
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
    } //-- void addOrdenie(int, ar.org.sicel.proc.v1.lote.Ordenie) 

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
     * Method enumerateOrdenie
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateOrdenie()
    {
        return _ordenieList.elements();
    } //-- java.util.Enumeration enumerateOrdenie() 

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
        
        if (obj instanceof Ordenies) {
        
            Ordenies temp = (Ordenies)obj;
            if (this._ordenieList != null) {
                if (temp._ordenieList == null) return false;
                else if (!(this._ordenieList.equals(temp._ordenieList))) 
                    return false;
            }
            else if (temp._ordenieList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getOrdenie
     * 
     * 
     * 
     * @param index
     * @return Ordenie
     */
    public ar.org.sicel.proc.v1.lote.Ordenie getOrdenie(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ordenieList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Ordenie) _ordenieList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Ordenie getOrdenie(int) 

    /**
     * Method getOrdenie
     * 
     * 
     * 
     * @return Ordenie
     */
    public ar.org.sicel.proc.v1.lote.Ordenie[] getOrdenie()
    {
        int size = _ordenieList.size();
        ar.org.sicel.proc.v1.lote.Ordenie[] mArray = new ar.org.sicel.proc.v1.lote.Ordenie[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Ordenie) _ordenieList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Ordenie[] getOrdenie() 

    /**
     * Method getOrdenieCount
     * 
     * 
     * 
     * @return int
     */
    public int getOrdenieCount()
    {
        return _ordenieList.size();
    } //-- int getOrdenieCount() 

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
     * Method removeAllOrdenie
     * 
     */
    public void removeAllOrdenie()
    {
        _ordenieList.removeAllElements();
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
    } //-- void removeAllOrdenie() 

    /**
     * Method removeOrdenie
     * 
     * 
     * 
     * @param index
     * @return Ordenie
     */
    public ar.org.sicel.proc.v1.lote.Ordenie removeOrdenie(int index)
    {
        java.lang.Object obj = _ordenieList.elementAt(index);
        _ordenieList.removeElementAt(index);
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
        return (ar.org.sicel.proc.v1.lote.Ordenie) obj;
    } //-- ar.org.sicel.proc.v1.lote.Ordenie removeOrdenie(int) 

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
     * Method setOrdenie
     * 
     * 
     * 
     * @param index
     * @param vOrdenie
     */
    public void setOrdenie(int index, ar.org.sicel.proc.v1.lote.Ordenie vOrdenie)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ordenieList.size())) {
            throw new IndexOutOfBoundsException();
        }
        if (!(index < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _ordenieList.setElementAt(vOrdenie, index);
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
    } //-- void setOrdenie(int, ar.org.sicel.proc.v1.lote.Ordenie) 

    /**
     * Method setOrdenie
     * 
     * 
     * 
     * @param ordenieArray
     */
    public void setOrdenie(ar.org.sicel.proc.v1.lote.Ordenie[] ordenieArray)
    {
        //-- copy array
        _ordenieList.removeAllElements();
        for (int i = 0; i < ordenieArray.length; i++) {
            _ordenieList.addElement(ordenieArray[i]);
        }
        notifyPropertyChangeListeners("_ordenieList", null, _ordenieList);
    } //-- void setOrdenie(ar.org.sicel.proc.v1.lote.Ordenie) 

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
        return (ar.org.sicel.proc.v1.lote.Ordenies) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Ordenies.class, reader);
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
