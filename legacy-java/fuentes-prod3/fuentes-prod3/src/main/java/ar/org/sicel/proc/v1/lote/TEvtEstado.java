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

import ar.org.sicel.proc.v1.lote.types.STEstadosAnim;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento estado
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtEstado extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _estado
     */
    private ar.org.sicel.proc.v1.lote.types.STEstadosAnim _estado;

    /**
     * Field _cantLactTerm
     */
    private int _cantLactTerm;

    /**
     * keeps track of state for field: _cantLactTerm
     */
    private boolean _has_cantLactTerm;

    /**
     * fecha de inicio de l aultima lactancia del animal que estuvo
     * en el periodo de no informacion de evento spor
     * inhabilitacion u otro motivo.
     */
    private org.exolab.castor.types.Date _fechaIniUltLactancia;

    /**
     * Field _numLactancia
     */
    private int _numLactancia;

    /**
     * keeps track of state for field: _numLactancia
     */
    private boolean _has_numLactancia;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtEstado() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtEstado()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteCantLactTerm
     * 
     */
    public void deleteCantLactTerm()
    {
        this._has_cantLactTerm= false;
        notifyPropertyChangeListeners("_cantLactTerm", new java.lang.Integer(this._cantLactTerm), null);
    } //-- void deleteCantLactTerm() 

    /**
     * Method deleteNumLactancia
     * 
     */
    public void deleteNumLactancia()
    {
        this._has_numLactancia= false;
        notifyPropertyChangeListeners("_numLactancia", new java.lang.Integer(this._numLactancia), null);
    } //-- void deleteNumLactancia() 

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
        
        if (obj instanceof TEvtEstado) {
        
            TEvtEstado temp = (TEvtEstado)obj;
            if (this._estado != null) {
                if (temp._estado == null) return false;
                else if (!(this._estado.equals(temp._estado))) 
                    return false;
            }
            else if (temp._estado != null)
                return false;
            if (this._cantLactTerm != temp._cantLactTerm)
                return false;
            if (this._has_cantLactTerm != temp._has_cantLactTerm)
                return false;
            if (this._fechaIniUltLactancia != null) {
                if (temp._fechaIniUltLactancia == null) return false;
                else if (!(this._fechaIniUltLactancia.equals(temp._fechaIniUltLactancia))) 
                    return false;
            }
            else if (temp._fechaIniUltLactancia != null)
                return false;
            if (this._numLactancia != temp._numLactancia)
                return false;
            if (this._has_numLactancia != temp._has_numLactancia)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'cantLactTerm'.
     * 
     * @return int
     * @return the value of field 'cantLactTerm'.
     */
    public int getCantLactTerm()
    {
        return this._cantLactTerm;
    } //-- int getCantLactTerm() 

    /**
     * Returns the value of field 'estado'.
     * 
     * @return STEstadosAnim
     * @return the value of field 'estado'.
     */
    public ar.org.sicel.proc.v1.lote.types.STEstadosAnim getEstado()
    {
        return this._estado;
    } //-- ar.org.sicel.proc.v1.lote.types.STEstadosAnim getEstado() 

    /**
     * Returns the value of field 'fechaIniUltLactancia'. The field
     * 'fechaIniUltLactancia' has the following description: fecha
     * de inicio de l aultima lactancia del animal que estuvo en el
     * periodo de no informacion de evento spor inhabilitacion u
     * otro motivo.
     * 
     * @return Date
     * @return the value of field 'fechaIniUltLactancia'.
     */
    public org.exolab.castor.types.Date getFechaIniUltLactancia()
    {
        return this._fechaIniUltLactancia;
    } //-- org.exolab.castor.types.Date getFechaIniUltLactancia() 

    /**
     * Returns the value of field 'numLactancia'.
     * 
     * @return int
     * @return the value of field 'numLactancia'.
     */
    public int getNumLactancia()
    {
        return this._numLactancia;
    } //-- int getNumLactancia() 

    /**
     * Method hasCantLactTerm
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasCantLactTerm()
    {
        return this._has_cantLactTerm;
    } //-- boolean hasCantLactTerm() 

    /**
     * Method hasNumLactancia
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNumLactancia()
    {
        return this._has_numLactancia;
    } //-- boolean hasNumLactancia() 

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
     * Sets the value of field 'cantLactTerm'.
     * 
     * @param cantLactTerm the value of field 'cantLactTerm'.
     */
    public void setCantLactTerm(int cantLactTerm)
    {
        java.lang.Object oldCantLactTerm = new java.lang.Integer(this._cantLactTerm);
        this._cantLactTerm = cantLactTerm;
        this._has_cantLactTerm = true;
        notifyPropertyChangeListeners("_cantLactTerm", oldCantLactTerm, new java.lang.Integer(this._cantLactTerm));
    } //-- void setCantLactTerm(int) 

    /**
     * Sets the value of field 'estado'.
     * 
     * @param estado the value of field 'estado'.
     */
    public void setEstado(ar.org.sicel.proc.v1.lote.types.STEstadosAnim estado)
    {
        java.lang.Object oldEstado = this._estado;
        this._estado = estado;
        notifyPropertyChangeListeners("_estado", oldEstado, this._estado);
    } //-- void setEstado(ar.org.sicel.proc.v1.lote.types.STEstadosAnim) 

    /**
     * Sets the value of field 'fechaIniUltLactancia'. The field
     * 'fechaIniUltLactancia' has the following description: fecha
     * de inicio de l aultima lactancia del animal que estuvo en el
     * periodo de no informacion de evento spor inhabilitacion u
     * otro motivo.
     * 
     * @param fechaIniUltLactancia the value of field
     * 'fechaIniUltLactancia'.
     */
    public void setFechaIniUltLactancia(org.exolab.castor.types.Date fechaIniUltLactancia)
    {
        java.lang.Object oldFechaIniUltLactancia = this._fechaIniUltLactancia;
        this._fechaIniUltLactancia = fechaIniUltLactancia;
        notifyPropertyChangeListeners("_fechaIniUltLactancia", oldFechaIniUltLactancia, this._fechaIniUltLactancia);
    } //-- void setFechaIniUltLactancia(org.exolab.castor.types.Date) 

    /**
     * Sets the value of field 'numLactancia'.
     * 
     * @param numLactancia the value of field 'numLactancia'.
     */
    public void setNumLactancia(int numLactancia)
    {
        java.lang.Object oldNumLactancia = new java.lang.Integer(this._numLactancia);
        this._numLactancia = numLactancia;
        this._has_numLactancia = true;
        notifyPropertyChangeListeners("_numLactancia", oldNumLactancia, new java.lang.Integer(this._numLactancia));
    } //-- void setNumLactancia(int) 

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
