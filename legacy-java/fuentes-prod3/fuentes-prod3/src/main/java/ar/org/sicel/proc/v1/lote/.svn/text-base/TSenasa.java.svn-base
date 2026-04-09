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
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Class TSenasa.
 * 
 * @version $Revision$ $Date$
 */
public abstract class TSenasa implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rpSenasa
     */
    private java.lang.String _rpSenasa;

    /**
     * Field _digitoVerficador
     */
    private int _digitoVerficador;

    /**
     * keeps track of state for field: _digitoVerficador
     */
    private boolean _has_digitoVerficador;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TSenasa() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TSenasa()


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
     * Method deleteDigitoVerficador
     * 
     */
    public void deleteDigitoVerficador()
    {
        this._has_digitoVerficador= false;
        notifyPropertyChangeListeners("_digitoVerficador", new java.lang.Integer(this._digitoVerficador), null);
    } //-- void deleteDigitoVerficador() 

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
        
        if (obj instanceof TSenasa) {
        
            TSenasa temp = (TSenasa)obj;
            if (this._rpSenasa != null) {
                if (temp._rpSenasa == null) return false;
                else if (!(this._rpSenasa.equals(temp._rpSenasa))) 
                    return false;
            }
            else if (temp._rpSenasa != null)
                return false;
            if (this._digitoVerficador != temp._digitoVerficador)
                return false;
            if (this._has_digitoVerficador != temp._has_digitoVerficador)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'digitoVerficador'.
     * 
     * @return int
     * @return the value of field 'digitoVerficador'.
     */
    public int getDigitoVerficador()
    {
        return this._digitoVerficador;
    } //-- int getDigitoVerficador() 

    /**
     * Returns the value of field 'rpSenasa'.
     * 
     * @return String
     * @return the value of field 'rpSenasa'.
     */
    public java.lang.String getRpSenasa()
    {
        return this._rpSenasa;
    } //-- java.lang.String getRpSenasa() 

    /**
     * Method hasDigitoVerficador
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasDigitoVerficador()
    {
        return this._has_digitoVerficador;
    } //-- boolean hasDigitoVerficador() 

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
     * Sets the value of field 'digitoVerficador'.
     * 
     * @param digitoVerficador the value of field 'digitoVerficador'
     */
    public void setDigitoVerficador(int digitoVerficador)
    {
        java.lang.Object oldDigitoVerficador = new java.lang.Integer(this._digitoVerficador);
        this._digitoVerficador = digitoVerficador;
        this._has_digitoVerficador = true;
        notifyPropertyChangeListeners("_digitoVerficador", oldDigitoVerficador, new java.lang.Integer(this._digitoVerficador));
    } //-- void setDigitoVerficador(int) 

    /**
     * Sets the value of field 'rpSenasa'.
     * 
     * @param rpSenasa the value of field 'rpSenasa'.
     */
    public void setRpSenasa(java.lang.String rpSenasa)
    {
        java.lang.Object oldRpSenasa = this._rpSenasa;
        this._rpSenasa = rpSenasa;
        notifyPropertyChangeListeners("_rpSenasa", oldRpSenasa, this._rpSenasa);
    } //-- void setRpSenasa(java.lang.String) 

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
