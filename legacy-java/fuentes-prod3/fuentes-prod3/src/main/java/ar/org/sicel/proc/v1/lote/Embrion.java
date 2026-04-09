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
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Embrion.
 * 
 * @version $Revision$ $Date$
 */
public class Embrion implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * TAnimReg
     */
    private ar.org.sicel.proc.v1.lote.MadreGen _madreGen;

    /**
     * TAnimReg
     */
    private ar.org.sicel.proc.v1.lote.PadreGen _padreGen;

    /**
     * Field _dias
     */
    private int _dias;

    /**
     * keeps track of state for field: _dias
     */
    private boolean _has_dias;

    /**
     * Field _idEmbrion
     */
    private java.lang.String _idEmbrion;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Embrion() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Embrion()


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
     * Method deleteDias
     * 
     */
    public void deleteDias()
    {
        this._has_dias= false;
        notifyPropertyChangeListeners("_dias", new java.lang.Integer(this._dias), null);
    } //-- void deleteDias() 

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
        
        if (obj instanceof Embrion) {
        
            Embrion temp = (Embrion)obj;
            if (this._madreGen != null) {
                if (temp._madreGen == null) return false;
                else if (!(this._madreGen.equals(temp._madreGen))) 
                    return false;
            }
            else if (temp._madreGen != null)
                return false;
            if (this._padreGen != null) {
                if (temp._padreGen == null) return false;
                else if (!(this._padreGen.equals(temp._padreGen))) 
                    return false;
            }
            else if (temp._padreGen != null)
                return false;
            if (this._dias != temp._dias)
                return false;
            if (this._has_dias != temp._has_dias)
                return false;
            if (this._idEmbrion != null) {
                if (temp._idEmbrion == null) return false;
                else if (!(this._idEmbrion.equals(temp._idEmbrion))) 
                    return false;
            }
            else if (temp._idEmbrion != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'dias'.
     * 
     * @return int
     * @return the value of field 'dias'.
     */
    public int getDias()
    {
        return this._dias;
    } //-- int getDias() 

    /**
     * Returns the value of field 'idEmbrion'.
     * 
     * @return String
     * @return the value of field 'idEmbrion'.
     */
    public java.lang.String getIdEmbrion()
    {
        return this._idEmbrion;
    } //-- java.lang.String getIdEmbrion() 

    /**
     * Returns the value of field 'madreGen'. The field 'madreGen'
     * has the following description: TAnimReg
     * 
     * @return MadreGen
     * @return the value of field 'madreGen'.
     */
    public ar.org.sicel.proc.v1.lote.MadreGen getMadreGen()
    {
        return this._madreGen;
    } //-- ar.org.sicel.proc.v1.lote.MadreGen getMadreGen() 

    /**
     * Returns the value of field 'padreGen'. The field 'padreGen'
     * has the following description: TAnimReg
     * 
     * @return PadreGen
     * @return the value of field 'padreGen'.
     */
    public ar.org.sicel.proc.v1.lote.PadreGen getPadreGen()
    {
        return this._padreGen;
    } //-- ar.org.sicel.proc.v1.lote.PadreGen getPadreGen() 

    /**
     * Method hasDias
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasDias()
    {
        return this._has_dias;
    } //-- boolean hasDias() 

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
     * Sets the value of field 'dias'.
     * 
     * @param dias the value of field 'dias'.
     */
    public void setDias(int dias)
    {
        java.lang.Object oldDias = new java.lang.Integer(this._dias);
        this._dias = dias;
        this._has_dias = true;
        notifyPropertyChangeListeners("_dias", oldDias, new java.lang.Integer(this._dias));
    } //-- void setDias(int) 

    /**
     * Sets the value of field 'idEmbrion'.
     * 
     * @param idEmbrion the value of field 'idEmbrion'.
     */
    public void setIdEmbrion(java.lang.String idEmbrion)
    {
        java.lang.Object oldIdEmbrion = this._idEmbrion;
        this._idEmbrion = idEmbrion;
        notifyPropertyChangeListeners("_idEmbrion", oldIdEmbrion, this._idEmbrion);
    } //-- void setIdEmbrion(java.lang.String) 

    /**
     * Sets the value of field 'madreGen'. The field 'madreGen' has
     * the following description: TAnimReg
     * 
     * @param madreGen the value of field 'madreGen'.
     */
    public void setMadreGen(ar.org.sicel.proc.v1.lote.MadreGen madreGen)
    {
        java.lang.Object oldMadreGen = this._madreGen;
        this._madreGen = madreGen;
        notifyPropertyChangeListeners("_madreGen", oldMadreGen, this._madreGen);
    } //-- void setMadreGen(ar.org.sicel.proc.v1.lote.MadreGen) 

    /**
     * Sets the value of field 'padreGen'. The field 'padreGen' has
     * the following description: TAnimReg
     * 
     * @param padreGen the value of field 'padreGen'.
     */
    public void setPadreGen(ar.org.sicel.proc.v1.lote.PadreGen padreGen)
    {
        java.lang.Object oldPadreGen = this._padreGen;
        this._padreGen = padreGen;
        notifyPropertyChangeListeners("_padreGen", oldPadreGen, this._padreGen);
    } //-- void setPadreGen(ar.org.sicel.proc.v1.lote.PadreGen) 

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
        return (ar.org.sicel.proc.v1.lote.Embrion) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Embrion.class, reader);
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
