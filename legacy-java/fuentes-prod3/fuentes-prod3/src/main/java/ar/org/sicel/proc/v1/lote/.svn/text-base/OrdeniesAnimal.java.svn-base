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
 * Class OrdeniesAnimal.
 * 
 * @version $Revision$ $Date$
 */
public class OrdeniesAnimal implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _ordenieAnimalList
     */
    private java.util.Vector _ordenieAnimalList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public OrdeniesAnimal() {
        super();
        _ordenieAnimalList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.OrdeniesAnimal()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addOrdenieAnimal
     * 
     * 
     * 
     * @param vOrdenieAnimal
     */
    public void addOrdenieAnimal(ar.org.sicel.proc.v1.lote.OrdenieAnimal vOrdenieAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        _ordenieAnimalList.addElement(vOrdenieAnimal);
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
    } //-- void addOrdenieAnimal(ar.org.sicel.proc.v1.lote.OrdenieAnimal) 

    /**
     * Method addOrdenieAnimal
     * 
     * 
     * 
     * @param index
     * @param vOrdenieAnimal
     */
    public void addOrdenieAnimal(int index, ar.org.sicel.proc.v1.lote.OrdenieAnimal vOrdenieAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        _ordenieAnimalList.insertElementAt(vOrdenieAnimal, index);
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
    } //-- void addOrdenieAnimal(int, ar.org.sicel.proc.v1.lote.OrdenieAnimal) 

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
     * Method enumerateOrdenieAnimal
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateOrdenieAnimal()
    {
        return _ordenieAnimalList.elements();
    } //-- java.util.Enumeration enumerateOrdenieAnimal() 

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
        
        if (obj instanceof OrdeniesAnimal) {
        
            OrdeniesAnimal temp = (OrdeniesAnimal)obj;
            if (this._ordenieAnimalList != null) {
                if (temp._ordenieAnimalList == null) return false;
                else if (!(this._ordenieAnimalList.equals(temp._ordenieAnimalList))) 
                    return false;
            }
            else if (temp._ordenieAnimalList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getOrdenieAnimal
     * 
     * 
     * 
     * @param index
     * @return OrdenieAnimal
     */
    public ar.org.sicel.proc.v1.lote.OrdenieAnimal getOrdenieAnimal(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ordenieAnimalList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.OrdenieAnimal) _ordenieAnimalList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.OrdenieAnimal getOrdenieAnimal(int) 

    /**
     * Method getOrdenieAnimal
     * 
     * 
     * 
     * @return OrdenieAnimal
     */
    public ar.org.sicel.proc.v1.lote.OrdenieAnimal[] getOrdenieAnimal()
    {
        int size = _ordenieAnimalList.size();
        ar.org.sicel.proc.v1.lote.OrdenieAnimal[] mArray = new ar.org.sicel.proc.v1.lote.OrdenieAnimal[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.OrdenieAnimal) _ordenieAnimalList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.OrdenieAnimal[] getOrdenieAnimal() 

    /**
     * Method getOrdenieAnimalCount
     * 
     * 
     * 
     * @return int
     */
    public int getOrdenieAnimalCount()
    {
        return _ordenieAnimalList.size();
    } //-- int getOrdenieAnimalCount() 

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
     * Method removeAllOrdenieAnimal
     * 
     */
    public void removeAllOrdenieAnimal()
    {
        _ordenieAnimalList.removeAllElements();
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
    } //-- void removeAllOrdenieAnimal() 

    /**
     * Method removeOrdenieAnimal
     * 
     * 
     * 
     * @param index
     * @return OrdenieAnimal
     */
    public ar.org.sicel.proc.v1.lote.OrdenieAnimal removeOrdenieAnimal(int index)
    {
        java.lang.Object obj = _ordenieAnimalList.elementAt(index);
        _ordenieAnimalList.removeElementAt(index);
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
        return (ar.org.sicel.proc.v1.lote.OrdenieAnimal) obj;
    } //-- ar.org.sicel.proc.v1.lote.OrdenieAnimal removeOrdenieAnimal(int) 

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
     * Method setOrdenieAnimal
     * 
     * 
     * 
     * @param index
     * @param vOrdenieAnimal
     */
    public void setOrdenieAnimal(int index, ar.org.sicel.proc.v1.lote.OrdenieAnimal vOrdenieAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _ordenieAnimalList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _ordenieAnimalList.setElementAt(vOrdenieAnimal, index);
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
    } //-- void setOrdenieAnimal(int, ar.org.sicel.proc.v1.lote.OrdenieAnimal) 

    /**
     * Method setOrdenieAnimal
     * 
     * 
     * 
     * @param ordenieAnimalArray
     */
    public void setOrdenieAnimal(ar.org.sicel.proc.v1.lote.OrdenieAnimal[] ordenieAnimalArray)
    {
        //-- copy array
        _ordenieAnimalList.removeAllElements();
        for (int i = 0; i < ordenieAnimalArray.length; i++) {
            _ordenieAnimalList.addElement(ordenieAnimalArray[i]);
        }
        notifyPropertyChangeListeners("_ordenieAnimalList", null, _ordenieAnimalList);
    } //-- void setOrdenieAnimal(ar.org.sicel.proc.v1.lote.OrdenieAnimal) 

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
        return (ar.org.sicel.proc.v1.lote.OrdeniesAnimal) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.OrdeniesAnimal.class, reader);
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
