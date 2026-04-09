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
 * Eventos de un mismo rebanio
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEstab implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _IDEstab
     */
    private long _IDEstab;

    /**
     * keeps track of state for field: _IDEstab
     */
    private boolean _has_IDEstab;

    /**
     * Field _rdos
     */
    private ar.org.sicel.proc.v1.lote.Rdos _rdos;

    /**
     * Field _evtsEstab
     */
    private ar.org.sicel.proc.v1.lote.EvtsEstab _evtsEstab;

    /**
     * Field _anims
     */
    private ar.org.sicel.proc.v1.lote.Anims _anims;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEstab() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TEstab()


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
     * Method deleteIDEstab
     * 
     */
    public void deleteIDEstab()
    {
        this._has_IDEstab= false;
        notifyPropertyChangeListeners("_IDEstab", new java.lang.Long(this._IDEstab), null);
    } //-- void deleteIDEstab() 

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
        
        if (obj instanceof TEstab) {
        
            TEstab temp = (TEstab)obj;
            if (this._IDEstab != temp._IDEstab)
                return false;
            if (this._has_IDEstab != temp._has_IDEstab)
                return false;
            if (this._rdos != null) {
                if (temp._rdos == null) return false;
                else if (!(this._rdos.equals(temp._rdos))) 
                    return false;
            }
            else if (temp._rdos != null)
                return false;
            if (this._evtsEstab != null) {
                if (temp._evtsEstab == null) return false;
                else if (!(this._evtsEstab.equals(temp._evtsEstab))) 
                    return false;
            }
            else if (temp._evtsEstab != null)
                return false;
            if (this._anims != null) {
                if (temp._anims == null) return false;
                else if (!(this._anims.equals(temp._anims))) 
                    return false;
            }
            else if (temp._anims != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'anims'.
     * 
     * @return Anims
     * @return the value of field 'anims'.
     */
    public ar.org.sicel.proc.v1.lote.Anims getAnims()
    {
        return this._anims;
    } //-- ar.org.sicel.proc.v1.lote.Anims getAnims() 

    /**
     * Returns the value of field 'evtsEstab'.
     * 
     * @return EvtsEstab
     * @return the value of field 'evtsEstab'.
     */
    public ar.org.sicel.proc.v1.lote.EvtsEstab getEvtsEstab()
    {
        return this._evtsEstab;
    } //-- ar.org.sicel.proc.v1.lote.EvtsEstab getEvtsEstab() 

    /**
     * Returns the value of field 'IDEstab'.
     * 
     * @return long
     * @return the value of field 'IDEstab'.
     */
    public long getIDEstab()
    {
        return this._IDEstab;
    } //-- long getIDEstab() 

    /**
     * Returns the value of field 'rdos'.
     * 
     * @return Rdos
     * @return the value of field 'rdos'.
     */
    public ar.org.sicel.proc.v1.lote.Rdos getRdos()
    {
        return this._rdos;
    } //-- ar.org.sicel.proc.v1.lote.Rdos getRdos() 

    /**
     * Method hasIDEstab
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDEstab()
    {
        return this._has_IDEstab;
    } //-- boolean hasIDEstab() 

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
     * Sets the value of field 'anims'.
     * 
     * @param anims the value of field 'anims'.
     */
    public void setAnims(ar.org.sicel.proc.v1.lote.Anims anims)
    {
        java.lang.Object oldAnims = this._anims;
        this._anims = anims;
        notifyPropertyChangeListeners("_anims", oldAnims, this._anims);
    } //-- void setAnims(ar.org.sicel.proc.v1.lote.Anims) 

    /**
     * Sets the value of field 'evtsEstab'.
     * 
     * @param evtsEstab the value of field 'evtsEstab'.
     */
    public void setEvtsEstab(ar.org.sicel.proc.v1.lote.EvtsEstab evtsEstab)
    {
        java.lang.Object oldEvtsEstab = this._evtsEstab;
        this._evtsEstab = evtsEstab;
        notifyPropertyChangeListeners("_evtsEstab", oldEvtsEstab, this._evtsEstab);
    } //-- void setEvtsEstab(ar.org.sicel.proc.v1.lote.EvtsEstab) 

    /**
     * Sets the value of field 'IDEstab'.
     * 
     * @param IDEstab the value of field 'IDEstab'.
     */
    public void setIDEstab(long IDEstab)
    {
        java.lang.Object oldIDEstab = new java.lang.Long(this._IDEstab);
        this._IDEstab = IDEstab;
        this._has_IDEstab = true;
        notifyPropertyChangeListeners("_IDEstab", oldIDEstab, new java.lang.Long(this._IDEstab));
    } //-- void setIDEstab(long) 

    /**
     * Sets the value of field 'rdos'.
     * 
     * @param rdos the value of field 'rdos'.
     */
    public void setRdos(ar.org.sicel.proc.v1.lote.Rdos rdos)
    {
        java.lang.Object oldRdos = this._rdos;
        this._rdos = rdos;
        notifyPropertyChangeListeners("_rdos", oldRdos, this._rdos);
    } //-- void setRdos(ar.org.sicel.proc.v1.lote.Rdos) 

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
