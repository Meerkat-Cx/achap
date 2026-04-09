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

import ar.org.sicel.proc.v1.lote.types.STPais;
import ar.org.sicel.proc.v1.lote.types.STRaza;
import ar.org.sicel.proc.v1.lote.types.STSexo;
import ar.org.sicel.proc.v1.lote.types.STTReg;
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
 * Identificación de un animal por alguno de sus registros. Se debe
 * indicar Tipo de registro (ver STTReg), número, raza y sexo
 * 
 * @version $Revision$ $Date$
 */
public abstract class TAnimRegSex implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _raza
     */
    private ar.org.sicel.proc.v1.lote.types.STRaza _raza;

    /**
     * Field _pais
     */
    private ar.org.sicel.proc.v1.lote.types.STPais _pais;

    /**
     * Field _sexo
     */
    private ar.org.sicel.proc.v1.lote.types.STSexo _sexo;

    /**
     * Field _tReg
     */
    private ar.org.sicel.proc.v1.lote.types.STTReg _tReg;

    /**
     * Field _nReg
     */
    private java.lang.String _nReg;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public TAnimRegSex() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TAnimRegSex()


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
        
        if (obj instanceof TAnimRegSex) {
        
            TAnimRegSex temp = (TAnimRegSex)obj;
            if (this._raza != null) {
                if (temp._raza == null) return false;
                else if (!(this._raza.equals(temp._raza))) 
                    return false;
            }
            else if (temp._raza != null)
                return false;
            if (this._pais != null) {
                if (temp._pais == null) return false;
                else if (!(this._pais.equals(temp._pais))) 
                    return false;
            }
            else if (temp._pais != null)
                return false;
            if (this._sexo != null) {
                if (temp._sexo == null) return false;
                else if (!(this._sexo.equals(temp._sexo))) 
                    return false;
            }
            else if (temp._sexo != null)
                return false;
            if (this._tReg != null) {
                if (temp._tReg == null) return false;
                else if (!(this._tReg.equals(temp._tReg))) 
                    return false;
            }
            else if (temp._tReg != null)
                return false;
            if (this._nReg != null) {
                if (temp._nReg == null) return false;
                else if (!(this._nReg.equals(temp._nReg))) 
                    return false;
            }
            else if (temp._nReg != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'nReg'.
     * 
     * @return String
     * @return the value of field 'nReg'.
     */
    public java.lang.String getNReg()
    {
        return this._nReg;
    } //-- java.lang.String getNReg() 

    /**
     * Returns the value of field 'pais'.
     * 
     * @return STPais
     * @return the value of field 'pais'.
     */
    public ar.org.sicel.proc.v1.lote.types.STPais getPais()
    {
        return this._pais;
    } //-- ar.org.sicel.proc.v1.lote.types.STPais getPais() 

    /**
     * Returns the value of field 'raza'.
     * 
     * @return STRaza
     * @return the value of field 'raza'.
     */
    public ar.org.sicel.proc.v1.lote.types.STRaza getRaza()
    {
        return this._raza;
    } //-- ar.org.sicel.proc.v1.lote.types.STRaza getRaza() 

    /**
     * Returns the value of field 'sexo'.
     * 
     * @return STSexo
     * @return the value of field 'sexo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STSexo getSexo()
    {
        return this._sexo;
    } //-- ar.org.sicel.proc.v1.lote.types.STSexo getSexo() 

    /**
     * Returns the value of field 'tReg'.
     * 
     * @return STTReg
     * @return the value of field 'tReg'.
     */
    public ar.org.sicel.proc.v1.lote.types.STTReg getTReg()
    {
        return this._tReg;
    } //-- ar.org.sicel.proc.v1.lote.types.STTReg getTReg() 

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
     * Sets the value of field 'nReg'.
     * 
     * @param nReg the value of field 'nReg'.
     */
    public void setNReg(java.lang.String nReg)
    {
        java.lang.Object oldNReg = this._nReg;
        this._nReg = nReg;
        notifyPropertyChangeListeners("_nReg", oldNReg, this._nReg);
    } //-- void setNReg(java.lang.String) 

    /**
     * Sets the value of field 'pais'.
     * 
     * @param pais the value of field 'pais'.
     */
    public void setPais(ar.org.sicel.proc.v1.lote.types.STPais pais)
    {
        java.lang.Object oldPais = this._pais;
        this._pais = pais;
        notifyPropertyChangeListeners("_pais", oldPais, this._pais);
    } //-- void setPais(ar.org.sicel.proc.v1.lote.types.STPais) 

    /**
     * Sets the value of field 'raza'.
     * 
     * @param raza the value of field 'raza'.
     */
    public void setRaza(ar.org.sicel.proc.v1.lote.types.STRaza raza)
    {
        java.lang.Object oldRaza = this._raza;
        this._raza = raza;
        notifyPropertyChangeListeners("_raza", oldRaza, this._raza);
    } //-- void setRaza(ar.org.sicel.proc.v1.lote.types.STRaza) 

    /**
     * Sets the value of field 'sexo'.
     * 
     * @param sexo the value of field 'sexo'.
     */
    public void setSexo(ar.org.sicel.proc.v1.lote.types.STSexo sexo)
    {
        java.lang.Object oldSexo = this._sexo;
        this._sexo = sexo;
        notifyPropertyChangeListeners("_sexo", oldSexo, this._sexo);
    } //-- void setSexo(ar.org.sicel.proc.v1.lote.types.STSexo) 

    /**
     * Sets the value of field 'tReg'.
     * 
     * @param tReg the value of field 'tReg'.
     */
    public void setTReg(ar.org.sicel.proc.v1.lote.types.STTReg tReg)
    {
        java.lang.Object oldTReg = this._tReg;
        this._tReg = tReg;
        notifyPropertyChangeListeners("_tReg", oldTReg, this._tReg);
    } //-- void setTReg(ar.org.sicel.proc.v1.lote.types.STTReg) 

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
