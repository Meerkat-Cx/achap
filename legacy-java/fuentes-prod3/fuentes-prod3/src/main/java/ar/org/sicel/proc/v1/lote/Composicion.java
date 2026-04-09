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
 * Class Composicion.
 * 
 * @version $Revision$ $Date$
 */
public class Composicion implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items
     */
    private java.util.Vector _items;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Composicion() {
        super();
        _items = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Composicion()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addComposicionItem
     * 
     * 
     * 
     * @param vComposicionItem
     */
    public void addComposicionItem(ar.org.sicel.proc.v1.lote.ComposicionItem vComposicionItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.addElement(vComposicionItem);
        notifyPropertyChangeListeners("_items", null, _items);
    } //-- void addComposicionItem(ar.org.sicel.proc.v1.lote.ComposicionItem) 

    /**
     * Method addComposicionItem
     * 
     * 
     * 
     * @param index
     * @param vComposicionItem
     */
    public void addComposicionItem(int index, ar.org.sicel.proc.v1.lote.ComposicionItem vComposicionItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _items.insertElementAt(vComposicionItem, index);
        notifyPropertyChangeListeners("_items", null, _items);
    } //-- void addComposicionItem(int, ar.org.sicel.proc.v1.lote.ComposicionItem) 

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
     * Method enumerateComposicionItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateComposicionItem()
    {
        return _items.elements();
    } //-- java.util.Enumeration enumerateComposicionItem() 

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
        
        if (obj instanceof Composicion) {
        
            Composicion temp = (Composicion)obj;
            if (this._items != null) {
                if (temp._items == null) return false;
                else if (!(this._items.equals(temp._items))) 
                    return false;
            }
            else if (temp._items != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getComposicionItem
     * 
     * 
     * 
     * @param index
     * @return ComposicionItem
     */
    public ar.org.sicel.proc.v1.lote.ComposicionItem getComposicionItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.ComposicionItem) _items.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.ComposicionItem getComposicionItem(int) 

    /**
     * Method getComposicionItem
     * 
     * 
     * 
     * @return ComposicionItem
     */
    public ar.org.sicel.proc.v1.lote.ComposicionItem[] getComposicionItem()
    {
        int size = _items.size();
        ar.org.sicel.proc.v1.lote.ComposicionItem[] mArray = new ar.org.sicel.proc.v1.lote.ComposicionItem[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.ComposicionItem) _items.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.ComposicionItem[] getComposicionItem() 

    /**
     * Method getComposicionItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getComposicionItemCount()
    {
        return _items.size();
    } //-- int getComposicionItemCount() 

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
     * Method removeAllComposicionItem
     * 
     */
    public void removeAllComposicionItem()
    {
        _items.removeAllElements();
        notifyPropertyChangeListeners("_items", null, _items);
    } //-- void removeAllComposicionItem() 

    /**
     * Method removeComposicionItem
     * 
     * 
     * 
     * @param index
     * @return ComposicionItem
     */
    public ar.org.sicel.proc.v1.lote.ComposicionItem removeComposicionItem(int index)
    {
        java.lang.Object obj = _items.elementAt(index);
        _items.removeElementAt(index);
        notifyPropertyChangeListeners("_items", null, _items);
        return (ar.org.sicel.proc.v1.lote.ComposicionItem) obj;
    } //-- ar.org.sicel.proc.v1.lote.ComposicionItem removeComposicionItem(int) 

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
     * Method setComposicionItem
     * 
     * 
     * 
     * @param index
     * @param vComposicionItem
     */
    public void setComposicionItem(int index, ar.org.sicel.proc.v1.lote.ComposicionItem vComposicionItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _items.size())) {
            throw new IndexOutOfBoundsException();
        }
        _items.setElementAt(vComposicionItem, index);
        notifyPropertyChangeListeners("_items", null, _items);
    } //-- void setComposicionItem(int, ar.org.sicel.proc.v1.lote.ComposicionItem) 

    /**
     * Method setComposicionItem
     * 
     * 
     * 
     * @param composicionItemArray
     */
    public void setComposicionItem(ar.org.sicel.proc.v1.lote.ComposicionItem[] composicionItemArray)
    {
        //-- copy array
        _items.removeAllElements();
        for (int i = 0; i < composicionItemArray.length; i++) {
            _items.addElement(composicionItemArray[i]);
        }
        notifyPropertyChangeListeners("_items", null, _items);
    } //-- void setComposicionItem(ar.org.sicel.proc.v1.lote.ComposicionItem) 

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
        return (ar.org.sicel.proc.v1.lote.Composicion) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Composicion.class, reader);
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
