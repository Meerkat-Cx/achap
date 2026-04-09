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

import ar.org.sicel.proc.v1.lote.types.STRaza;
import ar.org.sicel.proc.v1.lote.types.STSexo;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento alta de animal, sin estado (SV por defecto) y sin treg,
 * que será asignado
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtAlta extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rp
     */
    private java.lang.String _rp;

    /**
     * por defecto holando
     */
    private ar.org.sicel.proc.v1.lote.types.STRaza _raza;

    /**
     * Field _senasa
     */
    private ar.org.sicel.proc.v1.lote.Senasa _senasa;

    /**
     * Field _nombre
     */
    private java.lang.String _nombre;

    /**
     * Field _sexo
     */
    private ar.org.sicel.proc.v1.lote.types.STSexo _sexo;

    /**
     * Field _TEvtAltaChoice
     */
    private ar.org.sicel.proc.v1.lote.TEvtAltaChoice _TEvtAltaChoice;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtAlta() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtAlta()


      //-----------/
     //- Methods -/
    //-----------/

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
        
        if (super.equals(obj)==false)
            return false;
        
        if (obj instanceof TEvtAlta) {
        
            TEvtAlta temp = (TEvtAlta)obj;
            if (this._rp != null) {
                if (temp._rp == null) return false;
                else if (!(this._rp.equals(temp._rp))) 
                    return false;
            }
            else if (temp._rp != null)
                return false;
            if (this._raza != null) {
                if (temp._raza == null) return false;
                else if (!(this._raza.equals(temp._raza))) 
                    return false;
            }
            else if (temp._raza != null)
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
            if (this._sexo != null) {
                if (temp._sexo == null) return false;
                else if (!(this._sexo.equals(temp._sexo))) 
                    return false;
            }
            else if (temp._sexo != null)
                return false;
            if (this._TEvtAltaChoice != null) {
                if (temp._TEvtAltaChoice == null) return false;
                else if (!(this._TEvtAltaChoice.equals(temp._TEvtAltaChoice))) 
                    return false;
            }
            else if (temp._TEvtAltaChoice != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

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
     * Returns the value of field 'raza'. The field 'raza' has the
     * following description: por defecto holando
     * 
     * @return STRaza
     * @return the value of field 'raza'.
     */
    public ar.org.sicel.proc.v1.lote.types.STRaza getRaza()
    {
        return this._raza;
    } //-- ar.org.sicel.proc.v1.lote.types.STRaza getRaza() 

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
     * @return STSexo
     * @return the value of field 'sexo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STSexo getSexo()
    {
        return this._sexo;
    } //-- ar.org.sicel.proc.v1.lote.types.STSexo getSexo() 

    /**
     * Returns the value of field 'TEvtAltaChoice'.
     * 
     * @return TEvtAltaChoice
     * @return the value of field 'TEvtAltaChoice'.
     */
    public ar.org.sicel.proc.v1.lote.TEvtAltaChoice getTEvtAltaChoice()
    {
        return this._TEvtAltaChoice;
    } //-- ar.org.sicel.proc.v1.lote.TEvtAltaChoice getTEvtAltaChoice() 

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
     * Sets the value of field 'raza'. The field 'raza' has the
     * following description: por defecto holando
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
    public void setSexo(ar.org.sicel.proc.v1.lote.types.STSexo sexo)
    {
        java.lang.Object oldSexo = this._sexo;
        this._sexo = sexo;
        notifyPropertyChangeListeners("_sexo", oldSexo, this._sexo);
    } //-- void setSexo(ar.org.sicel.proc.v1.lote.types.STSexo) 

    /**
     * Sets the value of field 'TEvtAltaChoice'.
     * 
     * @param TEvtAltaChoice the value of field 'TEvtAltaChoice'.
     */
    public void setTEvtAltaChoice(ar.org.sicel.proc.v1.lote.TEvtAltaChoice TEvtAltaChoice)
    {
        java.lang.Object oldTEvtAltaChoice = this._TEvtAltaChoice;
        this._TEvtAltaChoice = TEvtAltaChoice;
        notifyPropertyChangeListeners("_TEvtAltaChoice", oldTEvtAltaChoice, this._TEvtAltaChoice);
    } //-- void setTEvtAltaChoice(ar.org.sicel.proc.v1.lote.TEvtAltaChoice) 

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
