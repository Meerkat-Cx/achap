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
 * Class Anims.
 * 
 * @version $Revision$ $Date$
 */
public class Anims implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _animalList
     */
    private java.util.Vector _animalList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Anims() {
        super();
        _animalList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Anims()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAnimal
     * 
     * 
     * 
     * @param vAnimal
     */
    public void addAnimal(ar.org.sicel.proc.v1.lote.Animal vAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        _animalList.addElement(vAnimal);
        notifyPropertyChangeListeners("_animalList", null, _animalList);
    } //-- void addAnimal(ar.org.sicel.proc.v1.lote.Animal) 

    /**
     * Method addAnimal
     * 
     * 
     * 
     * @param index
     * @param vAnimal
     */
    public void addAnimal(int index, ar.org.sicel.proc.v1.lote.Animal vAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        _animalList.insertElementAt(vAnimal, index);
        notifyPropertyChangeListeners("_animalList", null, _animalList);
    } //-- void addAnimal(int, ar.org.sicel.proc.v1.lote.Animal) 

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
     * Method enumerateAnimal
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateAnimal()
    {
        return _animalList.elements();
    } //-- java.util.Enumeration enumerateAnimal() 

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
        
        if (obj instanceof Anims) {
        
            Anims temp = (Anims)obj;
            if (this._animalList != null) {
                if (temp._animalList == null) return false;
                else if (!(this._animalList.equals(temp._animalList))) 
                    return false;
            }
            else if (temp._animalList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getAnimal
     * 
     * 
     * 
     * @param index
     * @return Animal
     */
    public ar.org.sicel.proc.v1.lote.Animal getAnimal(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _animalList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Animal) _animalList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Animal getAnimal(int) 

    /**
     * Method getAnimal
     * 
     * 
     * 
     * @return Animal
     */
    public ar.org.sicel.proc.v1.lote.Animal[] getAnimal()
    {
        int size = _animalList.size();
        ar.org.sicel.proc.v1.lote.Animal[] mArray = new ar.org.sicel.proc.v1.lote.Animal[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Animal) _animalList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Animal[] getAnimal() 

    /**
     * Method getAnimalCount
     * 
     * 
     * 
     * @return int
     */
    public int getAnimalCount()
    {
        return _animalList.size();
    } //-- int getAnimalCount() 

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
     * Method removeAllAnimal
     * 
     */
    public void removeAllAnimal()
    {
        _animalList.removeAllElements();
        notifyPropertyChangeListeners("_animalList", null, _animalList);
    } //-- void removeAllAnimal() 

    /**
     * Method removeAnimal
     * 
     * 
     * 
     * @param index
     * @return Animal
     */
    public ar.org.sicel.proc.v1.lote.Animal removeAnimal(int index)
    {
        java.lang.Object obj = _animalList.elementAt(index);
        _animalList.removeElementAt(index);
        notifyPropertyChangeListeners("_animalList", null, _animalList);
        return (ar.org.sicel.proc.v1.lote.Animal) obj;
    } //-- ar.org.sicel.proc.v1.lote.Animal removeAnimal(int) 

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
     * Method setAnimal
     * 
     * 
     * 
     * @param index
     * @param vAnimal
     */
    public void setAnimal(int index, ar.org.sicel.proc.v1.lote.Animal vAnimal)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _animalList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _animalList.setElementAt(vAnimal, index);
        notifyPropertyChangeListeners("_animalList", null, _animalList);
    } //-- void setAnimal(int, ar.org.sicel.proc.v1.lote.Animal) 

    /**
     * Method setAnimal
     * 
     * 
     * 
     * @param animalArray
     */
    public void setAnimal(ar.org.sicel.proc.v1.lote.Animal[] animalArray)
    {
        //-- copy array
        _animalList.removeAllElements();
        for (int i = 0; i < animalArray.length; i++) {
            _animalList.addElement(animalArray[i]);
        }
        notifyPropertyChangeListeners("_animalList", null, _animalList);
    } //-- void setAnimal(ar.org.sicel.proc.v1.lote.Animal) 

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
        return (ar.org.sicel.proc.v1.lote.Anims) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Anims.class, reader);
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
