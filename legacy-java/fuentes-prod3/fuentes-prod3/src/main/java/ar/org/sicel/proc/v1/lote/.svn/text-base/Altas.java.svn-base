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
 * Class Altas.
 * 
 * @version $Revision$ $Date$
 */
public class Altas implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _altaList
     */
    private java.util.Vector _altaList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Altas() {
        super();
        _altaList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Altas()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAlta
     * 
     * 
     * 
     * @param vAlta
     */
    public void addAlta(ar.org.sicel.proc.v1.lote.Alta vAlta)
        throws java.lang.IndexOutOfBoundsException
    {
        _altaList.addElement(vAlta);
        notifyPropertyChangeListeners("_altaList", null, _altaList);
    } //-- void addAlta(ar.org.sicel.proc.v1.lote.Alta) 

    /**
     * Method addAlta
     * 
     * 
     * 
     * @param index
     * @param vAlta
     */
    public void addAlta(int index, ar.org.sicel.proc.v1.lote.Alta vAlta)
        throws java.lang.IndexOutOfBoundsException
    {
        _altaList.insertElementAt(vAlta, index);
        notifyPropertyChangeListeners("_altaList", null, _altaList);
    } //-- void addAlta(int, ar.org.sicel.proc.v1.lote.Alta) 

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
     * Method enumerateAlta
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateAlta()
    {
        return _altaList.elements();
    } //-- java.util.Enumeration enumerateAlta() 

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
        
        if (obj instanceof Altas) {
        
            Altas temp = (Altas)obj;
            if (this._altaList != null) {
                if (temp._altaList == null) return false;
                else if (!(this._altaList.equals(temp._altaList))) 
                    return false;
            }
            else if (temp._altaList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getAlta
     * 
     * 
     * 
     * @param index
     * @return Alta
     */
    public ar.org.sicel.proc.v1.lote.Alta getAlta(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _altaList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Alta) _altaList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Alta getAlta(int) 

    /**
     * Method getAlta
     * 
     * 
     * 
     * @return Alta
     */
    public ar.org.sicel.proc.v1.lote.Alta[] getAlta()
    {
        int size = _altaList.size();
        ar.org.sicel.proc.v1.lote.Alta[] mArray = new ar.org.sicel.proc.v1.lote.Alta[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Alta) _altaList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Alta[] getAlta() 

    /**
     * Method getAltaCount
     * 
     * 
     * 
     * @return int
     */
    public int getAltaCount()
    {
        return _altaList.size();
    } //-- int getAltaCount() 

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
     * Method removeAllAlta
     * 
     */
    public void removeAllAlta()
    {
        _altaList.removeAllElements();
        notifyPropertyChangeListeners("_altaList", null, _altaList);
    } //-- void removeAllAlta() 

    /**
     * Method removeAlta
     * 
     * 
     * 
     * @param index
     * @return Alta
     */
    public ar.org.sicel.proc.v1.lote.Alta removeAlta(int index)
    {
        java.lang.Object obj = _altaList.elementAt(index);
        _altaList.removeElementAt(index);
        notifyPropertyChangeListeners("_altaList", null, _altaList);
        return (ar.org.sicel.proc.v1.lote.Alta) obj;
    } //-- ar.org.sicel.proc.v1.lote.Alta removeAlta(int) 

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
     * Method setAlta
     * 
     * 
     * 
     * @param index
     * @param vAlta
     */
    public void setAlta(int index, ar.org.sicel.proc.v1.lote.Alta vAlta)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _altaList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _altaList.setElementAt(vAlta, index);
        notifyPropertyChangeListeners("_altaList", null, _altaList);
    } //-- void setAlta(int, ar.org.sicel.proc.v1.lote.Alta) 

    /**
     * Method setAlta
     * 
     * 
     * 
     * @param altaArray
     */
    public void setAlta(ar.org.sicel.proc.v1.lote.Alta[] altaArray)
    {
        //-- copy array
        _altaList.removeAllElements();
        for (int i = 0; i < altaArray.length; i++) {
            _altaList.addElement(altaArray[i]);
        }
        notifyPropertyChangeListeners("_altaList", null, _altaList);
    } //-- void setAlta(ar.org.sicel.proc.v1.lote.Alta) 

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
        return (ar.org.sicel.proc.v1.lote.Altas) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Altas.class, reader);
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
