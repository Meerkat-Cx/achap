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
 * Eventos que conforman un lote que se envia como una unidad
 * 
 * @version $Revision$ $Date$
 */
public abstract class TLote implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _sistema
     */
    private ar.org.sicel.proc.v1.lote.Sistema _sistema;

    /**
     * Field _informante
     */
    private long _informante;

    /**
     * keeps track of state for field: _informante
     */
    private boolean _has_informante;

    /**
     * Field _centroDeComputo
     */
    private long _centroDeComputo;

    /**
     * keeps track of state for field: _centroDeComputo
     */
    private boolean _has_centroDeComputo;

    /**
     * Field _IDLoteInte
     */
    private long _IDLoteInte;

    /**
     * keeps track of state for field: _IDLoteInte
     */
    private boolean _has_IDLoteInte;

    /**
     * Field _IDLoteSicel
     */
    private long _IDLoteSicel;

    /**
     * keeps track of state for field: _IDLoteSicel
     */
    private boolean _has_IDLoteSicel;

    /**
     * Field _tiempos
     */
    private ar.org.sicel.proc.v1.lote.Tiempos _tiempos;

    /**
     * Field _rdos
     */
    private ar.org.sicel.proc.v1.lote.Rdos _rdos;

    /**
     * Field _estabs
     */
    private ar.org.sicel.proc.v1.lote.Estabs _estabs;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TLote() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TLote()


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
     * Method deleteCentroDeComputo
     * 
     */
    public void deleteCentroDeComputo()
    {
        this._has_centroDeComputo= false;
        notifyPropertyChangeListeners("_centroDeComputo", new java.lang.Long(this._centroDeComputo), null);
    } //-- void deleteCentroDeComputo() 

    /**
     * Method deleteIDLoteInte
     * 
     */
    public void deleteIDLoteInte()
    {
        this._has_IDLoteInte= false;
        notifyPropertyChangeListeners("_IDLoteInte", new java.lang.Long(this._IDLoteInte), null);
    } //-- void deleteIDLoteInte() 

    /**
     * Method deleteIDLoteSicel
     * 
     */
    public void deleteIDLoteSicel()
    {
        this._has_IDLoteSicel= false;
        notifyPropertyChangeListeners("_IDLoteSicel", new java.lang.Long(this._IDLoteSicel), null);
    } //-- void deleteIDLoteSicel() 

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
        
        if (obj instanceof TLote) {
        
            TLote temp = (TLote)obj;
            if (this._sistema != null) {
                if (temp._sistema == null) return false;
                else if (!(this._sistema.equals(temp._sistema))) 
                    return false;
            }
            else if (temp._sistema != null)
                return false;
            if (this._informante != temp._informante)
                return false;
            if (this._has_informante != temp._has_informante)
                return false;
            if (this._centroDeComputo != temp._centroDeComputo)
                return false;
            if (this._has_centroDeComputo != temp._has_centroDeComputo)
                return false;
            if (this._IDLoteInte != temp._IDLoteInte)
                return false;
            if (this._has_IDLoteInte != temp._has_IDLoteInte)
                return false;
            if (this._IDLoteSicel != temp._IDLoteSicel)
                return false;
            if (this._has_IDLoteSicel != temp._has_IDLoteSicel)
                return false;
            if (this._tiempos != null) {
                if (temp._tiempos == null) return false;
                else if (!(this._tiempos.equals(temp._tiempos))) 
                    return false;
            }
            else if (temp._tiempos != null)
                return false;
            if (this._rdos != null) {
                if (temp._rdos == null) return false;
                else if (!(this._rdos.equals(temp._rdos))) 
                    return false;
            }
            else if (temp._rdos != null)
                return false;
            if (this._estabs != null) {
                if (temp._estabs == null) return false;
                else if (!(this._estabs.equals(temp._estabs))) 
                    return false;
            }
            else if (temp._estabs != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'centroDeComputo'.
     * 
     * @return long
     * @return the value of field 'centroDeComputo'.
     */
    public long getCentroDeComputo()
    {
        return this._centroDeComputo;
    } //-- long getCentroDeComputo() 

    /**
     * Returns the value of field 'estabs'.
     * 
     * @return Estabs
     * @return the value of field 'estabs'.
     */
    public ar.org.sicel.proc.v1.lote.Estabs getEstabs()
    {
        return this._estabs;
    } //-- ar.org.sicel.proc.v1.lote.Estabs getEstabs() 

    /**
     * Returns the value of field 'IDLoteInte'.
     * 
     * @return long
     * @return the value of field 'IDLoteInte'.
     */
    public long getIDLoteInte()
    {
        return this._IDLoteInte;
    } //-- long getIDLoteInte() 

    /**
     * Returns the value of field 'IDLoteSicel'.
     * 
     * @return long
     * @return the value of field 'IDLoteSicel'.
     */
    public long getIDLoteSicel()
    {
        return this._IDLoteSicel;
    } //-- long getIDLoteSicel() 

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
     * Returns the value of field 'sistema'.
     * 
     * @return Sistema
     * @return the value of field 'sistema'.
     */
    public ar.org.sicel.proc.v1.lote.Sistema getSistema()
    {
        return this._sistema;
    } //-- ar.org.sicel.proc.v1.lote.Sistema getSistema() 

    /**
     * Returns the value of field 'tiempos'.
     * 
     * @return Tiempos
     * @return the value of field 'tiempos'.
     */
    public ar.org.sicel.proc.v1.lote.Tiempos getTiempos()
    {
        return this._tiempos;
    } //-- ar.org.sicel.proc.v1.lote.Tiempos getTiempos() 

    /**
     * Method hasCentroDeComputo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCentroDeComputo()
    {
        return this._has_centroDeComputo;
    } //-- boolean hasCentroDeComputo() 

    /**
     * Method hasIDLoteInte
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDLoteInte()
    {
        return this._has_IDLoteInte;
    } //-- boolean hasIDLoteInte() 

    /**
     * Method hasIDLoteSicel
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDLoteSicel()
    {
        return this._has_IDLoteSicel;
    } //-- boolean hasIDLoteSicel() 

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
     * Sets the value of field 'centroDeComputo'.
     * 
     * @param centroDeComputo the value of field 'centroDeComputo'.
     */
    public void setCentroDeComputo(long centroDeComputo)
    {
        java.lang.Object oldCentroDeComputo = new java.lang.Long(this._centroDeComputo);
        this._centroDeComputo = centroDeComputo;
        this._has_centroDeComputo = true;
        notifyPropertyChangeListeners("_centroDeComputo", oldCentroDeComputo, new java.lang.Long(this._centroDeComputo));
    } //-- void setCentroDeComputo(long) 

    /**
     * Sets the value of field 'estabs'.
     * 
     * @param estabs the value of field 'estabs'.
     */
    public void setEstabs(ar.org.sicel.proc.v1.lote.Estabs estabs)
    {
        java.lang.Object oldEstabs = this._estabs;
        this._estabs = estabs;
        notifyPropertyChangeListeners("_estabs", oldEstabs, this._estabs);
    } //-- void setEstabs(ar.org.sicel.proc.v1.lote.Estabs) 

    /**
     * Sets the value of field 'IDLoteInte'.
     * 
     * @param IDLoteInte the value of field 'IDLoteInte'.
     */
    public void setIDLoteInte(long IDLoteInte)
    {
        java.lang.Object oldIDLoteInte = new java.lang.Long(this._IDLoteInte);
        this._IDLoteInte = IDLoteInte;
        this._has_IDLoteInte = true;
        notifyPropertyChangeListeners("_IDLoteInte", oldIDLoteInte, new java.lang.Long(this._IDLoteInte));
    } //-- void setIDLoteInte(long) 

    /**
     * Sets the value of field 'IDLoteSicel'.
     * 
     * @param IDLoteSicel the value of field 'IDLoteSicel'.
     */
    public void setIDLoteSicel(long IDLoteSicel)
    {
        java.lang.Object oldIDLoteSicel = new java.lang.Long(this._IDLoteSicel);
        this._IDLoteSicel = IDLoteSicel;
        this._has_IDLoteSicel = true;
        notifyPropertyChangeListeners("_IDLoteSicel", oldIDLoteSicel, new java.lang.Long(this._IDLoteSicel));
    } //-- void setIDLoteSicel(long) 

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
     * Sets the value of field 'sistema'.
     * 
     * @param sistema the value of field 'sistema'.
     */
    public void setSistema(ar.org.sicel.proc.v1.lote.Sistema sistema)
    {
        java.lang.Object oldSistema = this._sistema;
        this._sistema = sistema;
        notifyPropertyChangeListeners("_sistema", oldSistema, this._sistema);
    } //-- void setSistema(ar.org.sicel.proc.v1.lote.Sistema) 

    /**
     * Sets the value of field 'tiempos'.
     * 
     * @param tiempos the value of field 'tiempos'.
     */
    public void setTiempos(ar.org.sicel.proc.v1.lote.Tiempos tiempos)
    {
        java.lang.Object oldTiempos = this._tiempos;
        this._tiempos = tiempos;
        notifyPropertyChangeListeners("_tiempos", oldTiempos, this._tiempos);
    } //-- void setTiempos(ar.org.sicel.proc.v1.lote.Tiempos) 

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
