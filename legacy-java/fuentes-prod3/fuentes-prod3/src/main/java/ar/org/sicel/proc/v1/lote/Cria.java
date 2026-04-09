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

import ar.org.sicel.proc.v1.lote.types.STSexoD;
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
 * Class Cria.
 * 
 * @version $Revision$ $Date$
 */
public class Cria implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _sexo
     */
    private ar.org.sicel.proc.v1.lote.types.STSexoD _sexo;

    /**
     * Field _dificultad
     */
    private int _dificultad;

    /**
     * keeps track of state for field: _dificultad
     */
    private boolean _has_dificultad;

    /**
     * estado se refiere a Estado Perinatal
     */
    private boolean _estadoEsVivo;

    /**
     * keeps track of state for field: _estadoEsVivo
     */
    private boolean _has_estadoEsVivo;

    /**
     * tamaño al nacer, peq med gde
     */
    private java.lang.String _tamano;

    /**
     * peso en kg al nacer. el peso en otro estadio de la vida del
     * animal se puede informar en el evento info
     */
    private float _peso;

    /**
     * keeps track of state for field: _peso
     */
    private boolean _has_peso;

    /**
     * Field _inscribir
     */
    private boolean _inscribir;

    /**
     * keeps track of state for field: _inscribir
     */
    private boolean _has_inscribir;

    /**
     * en ev reprod el rp es unico por estab, es decir, no puede
     * haber un macho y una hembra con = rp
     */
    private java.lang.String _rp;

    /**
     * Field _senasa
     */
    private ar.org.sicel.proc.v1.lote.Senasa _senasa;

    /**
     * Field _nombre
     */
    private java.lang.String _nombre;

    /**
     * Field _idAnimalNuevo
     */
    private long _idAnimalNuevo;

    /**
     * keeps track of state for field: _idAnimalNuevo
     */
    private boolean _has_idAnimalNuevo;

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

    public Cria() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Cria()


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
     * Method deleteDificultad
     * 
     */
    public void deleteDificultad()
    {
        this._has_dificultad= false;
        notifyPropertyChangeListeners("_dificultad", new java.lang.Integer(this._dificultad), null);
    } //-- void deleteDificultad() 

    /**
     * Method deleteEstadoEsVivo
     * 
     */
    public void deleteEstadoEsVivo()
    {
        this._has_estadoEsVivo= false;
        notifyPropertyChangeListeners("_estadoEsVivo", (this._estadoEsVivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE), null);
    } //-- void deleteEstadoEsVivo() 

    /**
     * Method deleteIdAnimalNuevo
     * 
     */
    public void deleteIdAnimalNuevo()
    {
        this._has_idAnimalNuevo= false;
        notifyPropertyChangeListeners("_idAnimalNuevo", new java.lang.Long(this._idAnimalNuevo), null);
    } //-- void deleteIdAnimalNuevo() 

    /**
     * Method deleteInscribir
     * 
     */
    public void deleteInscribir()
    {
        this._has_inscribir= false;
        notifyPropertyChangeListeners("_inscribir", (this._inscribir ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE), null);
    } //-- void deleteInscribir() 

    /**
     * Method deletePeso
     * 
     */
    public void deletePeso()
    {
        this._has_peso= false;
        notifyPropertyChangeListeners("_peso", new java.lang.Float(this._peso), null);
    } //-- void deletePeso() 

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
        
        if (obj instanceof Cria) {
        
            Cria temp = (Cria)obj;
            if (this._sexo != null) {
                if (temp._sexo == null) return false;
                else if (!(this._sexo.equals(temp._sexo))) 
                    return false;
            }
            else if (temp._sexo != null)
                return false;
            if (this._dificultad != temp._dificultad)
                return false;
            if (this._has_dificultad != temp._has_dificultad)
                return false;
            if (this._estadoEsVivo != temp._estadoEsVivo)
                return false;
            if (this._has_estadoEsVivo != temp._has_estadoEsVivo)
                return false;
            if (this._tamano != null) {
                if (temp._tamano == null) return false;
                else if (!(this._tamano.equals(temp._tamano))) 
                    return false;
            }
            else if (temp._tamano != null)
                return false;
            if (this._peso != temp._peso)
                return false;
            if (this._has_peso != temp._has_peso)
                return false;
            if (this._inscribir != temp._inscribir)
                return false;
            if (this._has_inscribir != temp._has_inscribir)
                return false;
            if (this._rp != null) {
                if (temp._rp == null) return false;
                else if (!(this._rp.equals(temp._rp))) 
                    return false;
            }
            else if (temp._rp != null)
                return false;
            if (this._senasa != null) {
                if (temp._senasa == null) return false;
                else if (!(this._senasa.equals(temp._senasa))) 
                    return false;
            }
            else if (temp._senasa != null)
                return false;
            if (this._nombre != null) {
                if (temp._nombre == null) return false;
                else if (!(this._nombre.equals(temp._nombre))) 
                    return false;
            }
            else if (temp._nombre != null)
                return false;
            if (this._idAnimalNuevo != temp._idAnimalNuevo)
                return false;
            if (this._has_idAnimalNuevo != temp._has_idAnimalNuevo)
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
     * Returns the value of field 'dificultad'.
     * 
     * @return int
     * @return the value of field 'dificultad'.
     */
    public int getDificultad()
    {
        return this._dificultad;
    } //-- int getDificultad() 

    /**
     * Returns the value of field 'estadoEsVivo'. The field
     * 'estadoEsVivo' has the following description: estado se
     * refiere a Estado Perinatal
     * 
     * @return boolean
     * @return the value of field 'estadoEsVivo'.
     */
    public boolean getEstadoEsVivo()
    {
        return this._estadoEsVivo;
    } //-- boolean getEstadoEsVivo() 

    /**
     * Returns the value of field 'idAnimalNuevo'.
     * 
     * @return long
     * @return the value of field 'idAnimalNuevo'.
     */
    public long getIdAnimalNuevo()
    {
        return this._idAnimalNuevo;
    } //-- long getIdAnimalNuevo() 

    /**
     * Returns the value of field 'inscribir'.
     * 
     * @return boolean
     * @return the value of field 'inscribir'.
     */
    public boolean getInscribir()
    {
        return this._inscribir;
    } //-- boolean getInscribir() 

    /**
     * Returns the value of field 'nombre'.
     * 
     * @return String
     * @return the value of field 'nombre'.
     */
    public java.lang.String getNombre()
    {
        return this._nombre;
    } //-- java.lang.String getNombre() 

    /**
     * Returns the value of field 'peso'. The field 'peso' has the
     * following description: peso en kg al nacer. el peso en otro
     * estadio de la vida del animal se puede informar en el evento
     * info
     * 
     * @return float
     * @return the value of field 'peso'.
     */
    public float getPeso()
    {
        return this._peso;
    } //-- float getPeso() 

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
     * Returns the value of field 'rp'. The field 'rp' has the
     * following description: en ev reprod el rp es unico por
     * estab, es decir, no puede haber un macho y una hembra con =
     * rp
     * 
     * @return String
     * @return the value of field 'rp'.
     */
    public java.lang.String getRp()
    {
        return this._rp;
    } //-- java.lang.String getRp() 

    /**
     * Returns the value of field 'senasa'.
     * 
     * @return Senasa
     * @return the value of field 'senasa'.
     */
    public ar.org.sicel.proc.v1.lote.Senasa getSenasa()
    {
        return this._senasa;
    } //-- ar.org.sicel.proc.v1.lote.Senasa getSenasa() 

    /**
     * Returns the value of field 'sexo'.
     * 
     * @return STSexoD
     * @return the value of field 'sexo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STSexoD getSexo()
    {
        return this._sexo;
    } //-- ar.org.sicel.proc.v1.lote.types.STSexoD getSexo() 

    /**
     * Returns the value of field 'tamano'. The field 'tamano' has
     * the following description: tamaño al nacer, peq med gde
     * 
     * @return String
     * @return the value of field 'tamano'.
     */
    public java.lang.String getTamano()
    {
        return this._tamano;
    } //-- java.lang.String getTamano() 

    /**
     * Method hasDificultad
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasDificultad()
    {
        return this._has_dificultad;
    } //-- boolean hasDificultad() 

    /**
     * Method hasEstadoEsVivo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasEstadoEsVivo()
    {
        return this._has_estadoEsVivo;
    } //-- boolean hasEstadoEsVivo() 

    /**
     * Method hasIdAnimalNuevo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIdAnimalNuevo()
    {
        return this._has_idAnimalNuevo;
    } //-- boolean hasIdAnimalNuevo() 

    /**
     * Method hasInscribir
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasInscribir()
    {
        return this._has_inscribir;
    } //-- boolean hasInscribir() 

    /**
     * Method hasPeso
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasPeso()
    {
        return this._has_peso;
    } //-- boolean hasPeso() 

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
     * Sets the value of field 'dificultad'.
     * 
     * @param dificultad the value of field 'dificultad'.
     */
    public void setDificultad(int dificultad)
    {
        java.lang.Object oldDificultad = new java.lang.Integer(this._dificultad);
        this._dificultad = dificultad;
        this._has_dificultad = true;
        notifyPropertyChangeListeners("_dificultad", oldDificultad, new java.lang.Integer(this._dificultad));
    } //-- void setDificultad(int) 

    /**
     * Sets the value of field 'estadoEsVivo'. The field
     * 'estadoEsVivo' has the following description: estado se
     * refiere a Estado Perinatal
     * 
     * @param estadoEsVivo the value of field 'estadoEsVivo'.
     */
    public void setEstadoEsVivo(boolean estadoEsVivo)
    {
        java.lang.Object oldEstadoEsVivo = (this._estadoEsVivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
        this._estadoEsVivo = estadoEsVivo;
        this._has_estadoEsVivo = true;
        notifyPropertyChangeListeners("_estadoEsVivo", oldEstadoEsVivo, (this._estadoEsVivo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE));
    } //-- void setEstadoEsVivo(boolean) 

    /**
     * Sets the value of field 'idAnimalNuevo'.
     * 
     * @param idAnimalNuevo the value of field 'idAnimalNuevo'.
     */
    public void setIdAnimalNuevo(long idAnimalNuevo)
    {
        java.lang.Object oldIdAnimalNuevo = new java.lang.Long(this._idAnimalNuevo);
        this._idAnimalNuevo = idAnimalNuevo;
        this._has_idAnimalNuevo = true;
        notifyPropertyChangeListeners("_idAnimalNuevo", oldIdAnimalNuevo, new java.lang.Long(this._idAnimalNuevo));
    } //-- void setIdAnimalNuevo(long) 

    /**
     * Sets the value of field 'inscribir'.
     * 
     * @param inscribir the value of field 'inscribir'.
     */
    public void setInscribir(boolean inscribir)
    {
        java.lang.Object oldInscribir = (this._inscribir ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
        this._inscribir = inscribir;
        this._has_inscribir = true;
        notifyPropertyChangeListeners("_inscribir", oldInscribir, (this._inscribir ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE));
    } //-- void setInscribir(boolean) 

    /**
     * Sets the value of field 'nombre'.
     * 
     * @param nombre the value of field 'nombre'.
     */
    public void setNombre(java.lang.String nombre)
    {
        java.lang.Object oldNombre = this._nombre;
        this._nombre = nombre;
        notifyPropertyChangeListeners("_nombre", oldNombre, this._nombre);
    } //-- void setNombre(java.lang.String) 

    /**
     * Sets the value of field 'peso'. The field 'peso' has the
     * following description: peso en kg al nacer. el peso en otro
     * estadio de la vida del animal se puede informar en el evento
     * info
     * 
     * @param peso the value of field 'peso'.
     */
    public void setPeso(float peso)
    {
        java.lang.Object oldPeso = new java.lang.Float(this._peso);
        this._peso = peso;
        this._has_peso = true;
        notifyPropertyChangeListeners("_peso", oldPeso, new java.lang.Float(this._peso));
    } //-- void setPeso(float) 

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
     * Sets the value of field 'rp'. The field 'rp' has the
     * following description: en ev reprod el rp es unico por
     * estab, es decir, no puede haber un macho y una hembra con =
     * rp
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
     * Sets the value of field 'senasa'.
     * 
     * @param senasa the value of field 'senasa'.
     */
    public void setSenasa(ar.org.sicel.proc.v1.lote.Senasa senasa)
    {
        java.lang.Object oldSenasa = this._senasa;
        this._senasa = senasa;
        notifyPropertyChangeListeners("_senasa", oldSenasa, this._senasa);
    } //-- void setSenasa(ar.org.sicel.proc.v1.lote.Senasa) 

    /**
     * Sets the value of field 'sexo'.
     * 
     * @param sexo the value of field 'sexo'.
     */
    public void setSexo(ar.org.sicel.proc.v1.lote.types.STSexoD sexo)
    {
        java.lang.Object oldSexo = this._sexo;
        this._sexo = sexo;
        notifyPropertyChangeListeners("_sexo", oldSexo, this._sexo);
    } //-- void setSexo(ar.org.sicel.proc.v1.lote.types.STSexoD) 

    /**
     * Sets the value of field 'tamano'. The field 'tamano' has the
     * following description: tamaño al nacer, peq med gde
     * 
     * @param tamano the value of field 'tamano'.
     */
    public void setTamano(java.lang.String tamano)
    {
        java.lang.Object oldTamano = this._tamano;
        this._tamano = tamano;
        notifyPropertyChangeListeners("_tamano", oldTamano, this._tamano);
    } //-- void setTamano(java.lang.String) 

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
        return (ar.org.sicel.proc.v1.lote.Cria) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Cria.class, reader);
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
