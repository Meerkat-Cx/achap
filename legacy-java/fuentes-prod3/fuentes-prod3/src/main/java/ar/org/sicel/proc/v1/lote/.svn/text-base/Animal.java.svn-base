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
 * Class Animal.
 * 
 * @version $Revision$ $Date$
 */
public class Animal implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rp
     */
    private java.lang.String _rp;

    /**
     * Field _reg
     */
    private ar.org.sicel.proc.v1.lote.Reg _reg;

    /**
     * Field _rdos
     */
    private ar.org.sicel.proc.v1.lote.Rdos _rdos;

    /**
     * Field _evts
     */
    private ar.org.sicel.proc.v1.lote.Evts _evts;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Animal() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Animal()


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
     * Note: hashCode() has not been overriden
     * 
     * @param obj
     * @return boolean
     */
    public boolean equals(java.lang.Object obj)
    {
        if ( this == obj )
            return true;
        
        if (obj instanceof Animal) {
        
            Animal temp = (Animal)obj;
            if (this._rp != null) {
                if (temp._rp == null) return false;
                else if (!(this._rp.equals(temp._rp))) 
                    return false;
            }
            else if (temp._rp != null)
                return false;
            if (this._reg != null) {
                if (temp._reg == null) return false;
                else if (!(this._reg.equals(temp._reg))) 
                    return false;
            }
            else if (temp._reg != null)
                return false;
            if (this._rdos != null) {
                if (temp._rdos == null) return false;
                else if (!(this._rdos.equals(temp._rdos))) 
                    return false;
            }
            else if (temp._rdos != null)
                return false;
            if (this._evts != null) {
                if (temp._evts == null) return false;
                else if (!(this._evts.equals(temp._evts))) 
                    return false;
            }
            else if (temp._evts != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'evts'.
     * 
     * @return Evts
     * @return the value of field 'evts'.
     */
    public ar.org.sicel.proc.v1.lote.Evts getEvts()
    {
        return this._evts;
    } //-- ar.org.sicel.proc.v1.lote.Evts getEvts() 

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
     * Returns the value of field 'reg'.
     * 
     * @return Reg
     * @return the value of field 'reg'.
     */
    public ar.org.sicel.proc.v1.lote.Reg getReg()
    {
        return this._reg;
    } //-- ar.org.sicel.proc.v1.lote.Reg getReg() 

    /**
     * Returns the value of field 'rp'.
     * 
     * @return String
     * @return the value of field 'rp'.
     */
    public java.lang.String getRp()
    {
        return this._rp;
    } //-- java.lang.String getRp() 

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
     * Sets the value of field 'evts'.
     * 
     * @param evts the value of field 'evts'.
     */
    public void setEvts(ar.org.sicel.proc.v1.lote.Evts evts)
    {
        java.lang.Object oldEvts = this._evts;
        this._evts = evts;
        notifyPropertyChangeListeners("_evts", oldEvts, this._evts);
    } //-- void setEvts(ar.org.sicel.proc.v1.lote.Evts) 

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
     * Sets the value of field 'reg'.
     * 
     * @param reg the value of field 'reg'.
     */
    public void setReg(ar.org.sicel.proc.v1.lote.Reg reg)
    {
        java.lang.Object oldReg = this._reg;
        this._reg = reg;
        notifyPropertyChangeListeners("_reg", oldReg, this._reg);
    } //-- void setReg(ar.org.sicel.proc.v1.lote.Reg) 

    /**
     * Sets the value of field 'rp'.
     * 
     * @param rp the value of field 'rp'.
     */
    public void setRp(java.lang.String rp)
    {
        java.lang.Object oldRp = this._rp;
        this._rp = rp;
        notifyPropertyChangeListeners("_rp", oldRp, this._rp);
    } //-- void setRp(java.lang.String) 

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
        return (ar.org.sicel.proc.v1.lote.Animal) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Animal.class, reader);
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
