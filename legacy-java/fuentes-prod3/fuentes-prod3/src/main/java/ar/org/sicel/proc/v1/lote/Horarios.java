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
 * Class Horarios.
 * 
 * @version $Revision$ $Date$
 */
public class Horarios implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _horarioList
     */
    private java.util.Vector _horarioList;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Horarios() {
        super();
        _horarioList = new Vector();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Horarios()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addHorario
     * 
     * 
     * 
     * @param vHorario
     */
    public void addHorario(ar.org.sicel.proc.v1.lote.Horario vHorario)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_horarioList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _horarioList.addElement(vHorario);
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
    } //-- void addHorario(ar.org.sicel.proc.v1.lote.Horario) 

    /**
     * Method addHorario
     * 
     * 
     * 
     * @param index
     * @param vHorario
     */
    public void addHorario(int index, ar.org.sicel.proc.v1.lote.Horario vHorario)
        throws java.lang.IndexOutOfBoundsException
    {
        if (!(_horarioList.size() < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _horarioList.insertElementAt(vHorario, index);
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
    } //-- void addHorario(int, ar.org.sicel.proc.v1.lote.Horario) 

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
     * Method enumerateHorario
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateHorario()
    {
        return _horarioList.elements();
    } //-- java.util.Enumeration enumerateHorario() 

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
        
        if (obj instanceof Horarios) {
        
            Horarios temp = (Horarios)obj;
            if (this._horarioList != null) {
                if (temp._horarioList == null) return false;
                else if (!(this._horarioList.equals(temp._horarioList))) 
                    return false;
            }
            else if (temp._horarioList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getHorario
     * 
     * 
     * 
     * @param index
     * @return Horario
     */
    public ar.org.sicel.proc.v1.lote.Horario getHorario(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _horarioList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Horario) _horarioList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Horario getHorario(int) 

    /**
     * Method getHorario
     * 
     * 
     * 
     * @return Horario
     */
    public ar.org.sicel.proc.v1.lote.Horario[] getHorario()
    {
        int size = _horarioList.size();
        ar.org.sicel.proc.v1.lote.Horario[] mArray = new ar.org.sicel.proc.v1.lote.Horario[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Horario) _horarioList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Horario[] getHorario() 

    /**
     * Method getHorarioCount
     * 
     * 
     * 
     * @return int
     */
    public int getHorarioCount()
    {
        return _horarioList.size();
    } //-- int getHorarioCount() 

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
     * Method removeAllHorario
     * 
     */
    public void removeAllHorario()
    {
        _horarioList.removeAllElements();
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
    } //-- void removeAllHorario() 

    /**
     * Method removeHorario
     * 
     * 
     * 
     * @param index
     * @return Horario
     */
    public ar.org.sicel.proc.v1.lote.Horario removeHorario(int index)
    {
        java.lang.Object obj = _horarioList.elementAt(index);
        _horarioList.removeElementAt(index);
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
        return (ar.org.sicel.proc.v1.lote.Horario) obj;
    } //-- ar.org.sicel.proc.v1.lote.Horario removeHorario(int) 

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
     * Method setHorario
     * 
     * 
     * 
     * @param index
     * @param vHorario
     */
    public void setHorario(int index, ar.org.sicel.proc.v1.lote.Horario vHorario)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _horarioList.size())) {
            throw new IndexOutOfBoundsException();
        }
        if (!(index < 3)) {
            throw new IndexOutOfBoundsException();
        }
        _horarioList.setElementAt(vHorario, index);
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
    } //-- void setHorario(int, ar.org.sicel.proc.v1.lote.Horario) 

    /**
     * Method setHorario
     * 
     * 
     * 
     * @param horarioArray
     */
    public void setHorario(ar.org.sicel.proc.v1.lote.Horario[] horarioArray)
    {
        //-- copy array
        _horarioList.removeAllElements();
        for (int i = 0; i < horarioArray.length; i++) {
            _horarioList.addElement(horarioArray[i]);
        }
        notifyPropertyChangeListeners("_horarioList", null, _horarioList);
    } //-- void setHorario(ar.org.sicel.proc.v1.lote.Horario) 

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
        return (ar.org.sicel.proc.v1.lote.Horarios) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Horarios.class, reader);
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
