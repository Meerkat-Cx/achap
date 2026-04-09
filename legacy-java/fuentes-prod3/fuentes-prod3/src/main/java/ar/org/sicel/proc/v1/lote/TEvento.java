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
import java.util.Date;
import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Superclase de todos los eventos
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvento implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Es una referencia a otro evento. En el caso de BajaEv, el
     * evento a dar de baja. En todos los demás, el evento a
     * modificar.
     */
    private ar.org.sicel.proc.v1.lote.ModificaOBaja _modificaOBaja;

    /**
     * Field _IDEvt
     */
    private long _IDEvt;

    /**
     * keeps track of state for field: _IDEvt
     */
    private boolean _has_IDEvt;

    /**
     * Field _idEvtSicel
     */
    private long _idEvtSicel;

    /**
     * keeps track of state for field: _idEvtSicel
     */
    private boolean _has_idEvtSicel;

    /**
     * http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html
     */
    private java.util.Date _fecha;

    /**
     * si bien el evt alta o semen no lleva el anim, luego del
     * proceso deberia tener el nuevo anim o el anim del semen
     */
    private long _IDAnim;

    /**
     * keeps track of state for field: _IDAnim
     */
    private boolean _has_IDAnim;

    /**
     * Field _rdos
     */
    private ar.org.sicel.proc.v1.lote.Rdos _rdos;

    /**
     * Field _comentarios
     */
    private java.lang.String _comentarios;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvento() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TEvento()


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
     * Method deleteIDAnim
     * 
     */
    public void deleteIDAnim()
    {
        this._has_IDAnim= false;
        notifyPropertyChangeListeners("_IDAnim", new java.lang.Long(this._IDAnim), null);
    } //-- void deleteIDAnim() 

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
     * Method deleteIdEvtSicel
     * 
     */
    public void deleteIdEvtSicel()
    {
        this._has_idEvtSicel= false;
        notifyPropertyChangeListeners("_idEvtSicel", new java.lang.Long(this._idEvtSicel), null);
    } //-- void deleteIdEvtSicel() 

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
        
        if (obj instanceof TEvento) {
        
            TEvento temp = (TEvento)obj;
            if (this._modificaOBaja != null) {
                if (temp._modificaOBaja == null) return false;
                else if (!(this._modificaOBaja.equals(temp._modificaOBaja))) 
                    return false;
            }
            else if (temp._modificaOBaja != null)
                return false;
            if (this._IDEvt != temp._IDEvt)
                return false;
            if (this._has_IDEvt != temp._has_IDEvt)
                return false;
            if (this._idEvtSicel != temp._idEvtSicel)
                return false;
            if (this._has_idEvtSicel != temp._has_idEvtSicel)
                return false;
            if (this._fecha != null) {
                if (temp._fecha == null) return false;
                else if (!(this._fecha.equals(temp._fecha))) 
                    return false;
            }
            else if (temp._fecha != null)
                return false;
            if (this._IDAnim != temp._IDAnim)
                return false;
            if (this._has_IDAnim != temp._has_IDAnim)
                return false;
            if (this._rdos != null) {
                if (temp._rdos == null) return false;
                else if (!(this._rdos.equals(temp._rdos))) 
                    return false;
            }
            else if (temp._rdos != null)
                return false;
            if (this._comentarios != null) {
                if (temp._comentarios == null) return false;
                else if (!(this._comentarios.equals(temp._comentarios))) 
                    return false;
            }
            else if (temp._comentarios != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'comentarios'.
     * 
     * @return String
     * @return the value of field 'comentarios'.
     */
    public java.lang.String getComentarios()
    {
        return this._comentarios;
    } //-- java.lang.String getComentarios() 

    /**
     * Returns the value of field 'fecha'. The field 'fecha' has
     * the following description:
     * http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html
     * 
     * @return Date
     * @return the value of field 'fecha'.
     */
    public java.util.Date getFecha()
    {
        return this._fecha;
    } //-- java.util.Date getFecha() 

    /**
     * Returns the value of field 'IDAnim'. The field 'IDAnim' has
     * the following description: si bien el evt alta o semen no
     * lleva el anim, luego del proceso deberia tener el nuevo anim
     * o el anim del semen
     * 
     * @return long
     * @return the value of field 'IDAnim'.
     */
    public long getIDAnim()
    {
        return this._IDAnim;
    } //-- long getIDAnim() 

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
     * Returns the value of field 'idEvtSicel'.
     * 
     * @return long
     * @return the value of field 'idEvtSicel'.
     */
    public long getIdEvtSicel()
    {
        return this._idEvtSicel;
    } //-- long getIdEvtSicel() 

    /**
     * Returns the value of field 'modificaOBaja'. The field
     * 'modificaOBaja' has the following description: Es una
     * referencia a otro evento. En el caso de BajaEv, el evento a
     * dar de baja. En todos los demás, el evento a modificar.
     * 
     * @return ModificaOBaja
     * @return the value of field 'modificaOBaja'.
     */
    public ar.org.sicel.proc.v1.lote.ModificaOBaja getModificaOBaja()
    {
        return this._modificaOBaja;
    } //-- ar.org.sicel.proc.v1.lote.ModificaOBaja getModificaOBaja() 

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
     * Method hasIDAnim
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDAnim()
    {
        return this._has_IDAnim;
    } //-- boolean hasIDAnim() 

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
     * Method hasIdEvtSicel
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIdEvtSicel()
    {
        return this._has_idEvtSicel;
    } //-- boolean hasIdEvtSicel() 

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
     * Sets the value of field 'comentarios'.
     * 
     * @param comentarios the value of field 'comentarios'.
     */
    public void setComentarios(java.lang.String comentarios)
    {
        java.lang.Object oldComentarios = this._comentarios;
        this._comentarios = comentarios;
        notifyPropertyChangeListeners("_comentarios", oldComentarios, this._comentarios);
    } //-- void setComentarios(java.lang.String) 

    /**
     * Sets the value of field 'fecha'. The field 'fecha' has the
     * following description:
     * http://www.iso.org/iso/en/prods-services/popstds/datesandtime.html
     * 
     * @param fecha the value of field 'fecha'.
     */
    public void setFecha(java.util.Date fecha)
    {
        java.lang.Object oldFecha = this._fecha;
        this._fecha = fecha;
        notifyPropertyChangeListeners("_fecha", oldFecha, this._fecha);
    } //-- void setFecha(java.util.Date) 

    /**
     * Sets the value of field 'IDAnim'. The field 'IDAnim' has the
     * following description: si bien el evt alta o semen no lleva
     * el anim, luego del proceso deberia tener el nuevo anim o el
     * anim del semen
     * 
     * @param IDAnim the value of field 'IDAnim'.
     */
    public void setIDAnim(long IDAnim)
    {
        java.lang.Object oldIDAnim = new java.lang.Long(this._IDAnim);
        this._IDAnim = IDAnim;
        this._has_IDAnim = true;
        notifyPropertyChangeListeners("_IDAnim", oldIDAnim, new java.lang.Long(this._IDAnim));
    } //-- void setIDAnim(long) 

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
     * Sets the value of field 'idEvtSicel'.
     * 
     * @param idEvtSicel the value of field 'idEvtSicel'.
     */
    public void setIdEvtSicel(long idEvtSicel)
    {
        java.lang.Object oldIdEvtSicel = new java.lang.Long(this._idEvtSicel);
        this._idEvtSicel = idEvtSicel;
        this._has_idEvtSicel = true;
        notifyPropertyChangeListeners("_idEvtSicel", oldIdEvtSicel, new java.lang.Long(this._idEvtSicel));
    } //-- void setIdEvtSicel(long) 

    /**
     * Sets the value of field 'modificaOBaja'. The field
     * 'modificaOBaja' has the following description: Es una
     * referencia a otro evento. En el caso de BajaEv, el evento a
     * dar de baja. En todos los demás, el evento a modificar.
     * 
     * @param modificaOBaja the value of field 'modificaOBaja'.
     */
    public void setModificaOBaja(ar.org.sicel.proc.v1.lote.ModificaOBaja modificaOBaja)
    {
        java.lang.Object oldModificaOBaja = this._modificaOBaja;
        this._modificaOBaja = modificaOBaja;
        notifyPropertyChangeListeners("_modificaOBaja", oldModificaOBaja, this._modificaOBaja);
    } //-- void setModificaOBaja(ar.org.sicel.proc.v1.lote.ModificaOBaja) 

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
