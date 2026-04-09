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

import ar.org.sicel.proc.v1.lote.types.STTipoServicio;
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
 * Class Servsemen.
 * 
 * @version $Revision$ $Date$
 */
public class Servsemen implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * TAnimReg
     */
    private ar.org.sicel.proc.v1.lote.PadreGen _padreGen;

    /**
     * Field _tipoServ
     */
    private ar.org.sicel.proc.v1.lote.types.STTipoServicio _tipoServ;

    /**
     * Fin de la estancia en el corral del macho donante del semen.
     * El inicio es la fecha del evento.
     */
    private org.exolab.castor.types.Date _finCorral;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Servsemen() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Servsemen()


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
        
        if (obj instanceof Servsemen) {
        
            Servsemen temp = (Servsemen)obj;
            if (this._padreGen != null) {
                if (temp._padreGen == null) return false;
                else if (!(this._padreGen.equals(temp._padreGen))) 
                    return false;
            }
            else if (temp._padreGen != null)
                return false;
            if (this._tipoServ != null) {
                if (temp._tipoServ == null) return false;
                else if (!(this._tipoServ.equals(temp._tipoServ))) 
                    return false;
            }
            else if (temp._tipoServ != null)
                return false;
            if (this._finCorral != null) {
                if (temp._finCorral == null) return false;
                else if (!(this._finCorral.equals(temp._finCorral))) 
                    return false;
            }
            else if (temp._finCorral != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'finCorral'. The field
     * 'finCorral' has the following description: Fin de la
     * estancia en el corral del macho donante del semen. El inicio
     * es la fecha del evento.
     * 
     * @return Date
     * @return the value of field 'finCorral'.
     */
    public org.exolab.castor.types.Date getFinCorral()
    {
        return this._finCorral;
    } //-- org.exolab.castor.types.Date getFinCorral() 

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
     * Returns the value of field 'tipoServ'.
     * 
     * @return STTipoServicio
     * @return the value of field 'tipoServ'.
     */
    public ar.org.sicel.proc.v1.lote.types.STTipoServicio getTipoServ()
    {
        return this._tipoServ;
    } //-- ar.org.sicel.proc.v1.lote.types.STTipoServicio getTipoServ() 

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
     * Sets the value of field 'finCorral'. The field 'finCorral'
     * has the following description: Fin de la estancia en el
     * corral del macho donante del semen. El inicio es la fecha
     * del evento.
     * 
     * @param finCorral the value of field 'finCorral'.
     */
    public void setFinCorral(org.exolab.castor.types.Date finCorral)
    {
        java.lang.Object oldFinCorral = this._finCorral;
        this._finCorral = finCorral;
        notifyPropertyChangeListeners("_finCorral", oldFinCorral, this._finCorral);
    } //-- void setFinCorral(org.exolab.castor.types.Date) 

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
     * Sets the value of field 'tipoServ'.
     * 
     * @param tipoServ the value of field 'tipoServ'.
     */
    public void setTipoServ(ar.org.sicel.proc.v1.lote.types.STTipoServicio tipoServ)
    {
        java.lang.Object oldTipoServ = this._tipoServ;
        this._tipoServ = tipoServ;
        notifyPropertyChangeListeners("_tipoServ", oldTipoServ, this._tipoServ);
    } //-- void setTipoServ(ar.org.sicel.proc.v1.lote.types.STTipoServicio) 

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
        return (ar.org.sicel.proc.v1.lote.Servsemen) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Servsemen.class, reader);
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
