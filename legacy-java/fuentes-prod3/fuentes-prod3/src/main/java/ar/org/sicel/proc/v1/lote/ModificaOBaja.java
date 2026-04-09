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
 * Es una referencia a otro evento. En el caso de BajaEv, el evento
 * a dar de baja. En todos los demás, el evento a modificar.
 * 
 * @version $Revision$ $Date$
 */
public class ModificaOBaja implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _IDEvt
     */
    private long _IDEvt;

    /**
     * keeps track of state for field: _IDEvt
     */
    private boolean _has_IDEvt;

    /**
     * Field _idOrdenie
     */
    private long _idOrdenie;

    /**
     * keeps track of state for field: _idOrdenie
     */
    private boolean _has_idOrdenie;

    /**
     * Field _informante
     */
    private long _informante;

    /**
     * keeps track of state for field: _informante
     */
    private boolean _has_informante;

    /**
     * Field _rdos
     */
    private ar.org.sicel.proc.v1.lote.Rdos _rdos;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public ModificaOBaja() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.ModificaOBaja()


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
     * Method deleteIDEvt
     * 
     */
    public void deleteIDEvt()
    {
        this._has_IDEvt= false;
        notifyPropertyChangeListeners("_IDEvt", new java.lang.Long(this._IDEvt), null);
    } //-- void deleteIDEvt() 

    /**
     * Method deleteIdOrdenie
     * 
     */
    public void deleteIdOrdenie()
    {
        this._has_idOrdenie= false;
        notifyPropertyChangeListeners("_idOrdenie", new java.lang.Long(this._idOrdenie), null);
    } //-- void deleteIdOrdenie() 

    /**
     * Method deleteInformante
     * 
     */
    public void deleteInformante()
    {
        this._has_informante= false;
        notifyPropertyChangeListeners("_informante", new java.lang.Long(this._informante), null);
    } //-- void deleteInformante() 

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
        
        if (obj instanceof ModificaOBaja) {
        
            ModificaOBaja temp = (ModificaOBaja)obj;
            if (this._IDEvt != temp._IDEvt)
                return false;
            if (this._has_IDEvt != temp._has_IDEvt)
                return false;
            if (this._idOrdenie != temp._idOrdenie)
                return false;
            if (this._has_idOrdenie != temp._has_idOrdenie)
                return false;
            if (this._informante != temp._informante)
                return false;
            if (this._has_informante != temp._has_informante)
                return false;
            if (this._rdos != null) {
                if (temp._rdos == null) return false;
                else if (!(this._rdos.equals(temp._rdos))) 
                    return false;
            }
            else if (temp._rdos != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'IDEvt'.
     * 
     * @return long
     * @return the value of field 'IDEvt'.
     */
    public long getIDEvt()
    {
        return this._IDEvt;
    } //-- long getIDEvt() 

    /**
     * Returns the value of field 'idOrdenie'.
     * 
     * @return long
     * @return the value of field 'idOrdenie'.
     */
    public long getIdOrdenie()
    {
        return this._idOrdenie;
    } //-- long getIdOrdenie() 

    /**
     * Returns the value of field 'informante'.
     * 
     * @return long
     * @return the value of field 'informante'.
     */
    public long getInformante()
    {
        return this._informante;
    } //-- long getInformante() 

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
     * Method hasIDEvt
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDEvt()
    {
        return this._has_IDEvt;
    } //-- boolean hasIDEvt() 

    /**
     * Method hasIdOrdenie
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIdOrdenie()
    {
        return this._has_idOrdenie;
    } //-- boolean hasIdOrdenie() 

    /**
     * Method hasInformante
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasInformante()
    {
        return this._has_informante;
    } //-- boolean hasInformante() 

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
     * Sets the value of field 'IDEvt'.
     * 
     * @param IDEvt the value of field 'IDEvt'.
     */
    public void setIDEvt(long IDEvt)
    {
        java.lang.Object oldIDEvt = new java.lang.Long(this._IDEvt);
        this._IDEvt = IDEvt;
        this._has_IDEvt = true;
        notifyPropertyChangeListeners("_IDEvt", oldIDEvt, new java.lang.Long(this._IDEvt));
    } //-- void setIDEvt(long) 

    /**
     * Sets the value of field 'idOrdenie'.
     * 
     * @param idOrdenie the value of field 'idOrdenie'.
     */
    public void setIdOrdenie(long idOrdenie)
    {
        java.lang.Object oldIdOrdenie = new java.lang.Long(this._idOrdenie);
        this._idOrdenie = idOrdenie;
        this._has_idOrdenie = true;
        notifyPropertyChangeListeners("_idOrdenie", oldIdOrdenie, new java.lang.Long(this._idOrdenie));
    } //-- void setIdOrdenie(long) 

    /**
     * Sets the value of field 'informante'.
     * 
     * @param informante the value of field 'informante'.
     */
    public void setInformante(long informante)
    {
        java.lang.Object oldInformante = new java.lang.Long(this._informante);
        this._informante = informante;
        this._has_informante = true;
        notifyPropertyChangeListeners("_informante", oldInformante, new java.lang.Long(this._informante));
    } //-- void setInformante(long) 

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
        return (ar.org.sicel.proc.v1.lote.ModificaOBaja) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.ModificaOBaja.class, reader);
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
