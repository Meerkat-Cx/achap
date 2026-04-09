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

import ar.org.sicel.proc.v1.lote.types.STMetodoControl;
import ar.org.sicel.proc.v1.lote.types.STTipoMuestreo;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento control de produccion de todo el establecimiento
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtControl extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoMuestreo
     */
    private ar.org.sicel.proc.v1.lote.types.STTipoMuestreo _tipoMuestreo;

    /**
     * Field _metodoControl
     */
    private ar.org.sicel.proc.v1.lote.types.STMetodoControl _metodoControl;

    /**
     * Field _horarios
     */
    private ar.org.sicel.proc.v1.lote.Horarios _horarios;

    /**
     * Field _ordeniesAnimal
     */
    private ar.org.sicel.proc.v1.lote.OrdeniesAnimal _ordeniesAnimal;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtControl() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtControl()


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
        
        if (obj instanceof TEvtControl) {
        
            TEvtControl temp = (TEvtControl)obj;
            if (this._tipoMuestreo != null) {
                if (temp._tipoMuestreo == null) return false;
                else if (!(this._tipoMuestreo.equals(temp._tipoMuestreo))) 
                    return false;
            }
            else if (temp._tipoMuestreo != null)
                return false;
            if (this._metodoControl != null) {
                if (temp._metodoControl == null) return false;
                else if (!(this._metodoControl.equals(temp._metodoControl))) 
                    return false;
            }
            else if (temp._metodoControl != null)
                return false;
            if (this._horarios != null) {
                if (temp._horarios == null) return false;
                else if (!(this._horarios.equals(temp._horarios))) 
                    return false;
            }
            else if (temp._horarios != null)
                return false;
            if (this._ordeniesAnimal != null) {
                if (temp._ordeniesAnimal == null) return false;
                else if (!(this._ordeniesAnimal.equals(temp._ordeniesAnimal))) 
                    return false;
            }
            else if (temp._ordeniesAnimal != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'horarios'.
     * 
     * @return Horarios
     * @return the value of field 'horarios'.
     */
    public ar.org.sicel.proc.v1.lote.Horarios getHorarios()
    {
        return this._horarios;
    } //-- ar.org.sicel.proc.v1.lote.Horarios getHorarios() 

    /**
     * Returns the value of field 'metodoControl'.
     * 
     * @return STMetodoControl
     * @return the value of field 'metodoControl'.
     */
    public ar.org.sicel.proc.v1.lote.types.STMetodoControl getMetodoControl()
    {
        return this._metodoControl;
    } //-- ar.org.sicel.proc.v1.lote.types.STMetodoControl getMetodoControl() 

    /**
     * Returns the value of field 'ordeniesAnimal'.
     * 
     * @return OrdeniesAnimal
     * @return the value of field 'ordeniesAnimal'.
     */
    public ar.org.sicel.proc.v1.lote.OrdeniesAnimal getOrdeniesAnimal()
    {
        return this._ordeniesAnimal;
    } //-- ar.org.sicel.proc.v1.lote.OrdeniesAnimal getOrdeniesAnimal() 

    /**
     * Returns the value of field 'tipoMuestreo'.
     * 
     * @return STTipoMuestreo
     * @return the value of field 'tipoMuestreo'.
     */
    public ar.org.sicel.proc.v1.lote.types.STTipoMuestreo getTipoMuestreo()
    {
        return this._tipoMuestreo;
    } //-- ar.org.sicel.proc.v1.lote.types.STTipoMuestreo getTipoMuestreo() 

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
     * Sets the value of field 'horarios'.
     * 
     * @param horarios the value of field 'horarios'.
     */
    public void setHorarios(ar.org.sicel.proc.v1.lote.Horarios horarios)
    {
        java.lang.Object oldHorarios = this._horarios;
        this._horarios = horarios;
        notifyPropertyChangeListeners("_horarios", oldHorarios, this._horarios);
    } //-- void setHorarios(ar.org.sicel.proc.v1.lote.Horarios) 

    /**
     * Sets the value of field 'metodoControl'.
     * 
     * @param metodoControl the value of field 'metodoControl'.
     */
    public void setMetodoControl(ar.org.sicel.proc.v1.lote.types.STMetodoControl metodoControl)
    {
        java.lang.Object oldMetodoControl = this._metodoControl;
        this._metodoControl = metodoControl;
        notifyPropertyChangeListeners("_metodoControl", oldMetodoControl, this._metodoControl);
    } //-- void setMetodoControl(ar.org.sicel.proc.v1.lote.types.STMetodoControl) 

    /**
     * Sets the value of field 'ordeniesAnimal'.
     * 
     * @param ordeniesAnimal the value of field 'ordeniesAnimal'.
     */
    public void setOrdeniesAnimal(ar.org.sicel.proc.v1.lote.OrdeniesAnimal ordeniesAnimal)
    {
        java.lang.Object oldOrdeniesAnimal = this._ordeniesAnimal;
        this._ordeniesAnimal = ordeniesAnimal;
        notifyPropertyChangeListeners("_ordeniesAnimal", oldOrdeniesAnimal, this._ordeniesAnimal);
    } //-- void setOrdeniesAnimal(ar.org.sicel.proc.v1.lote.OrdeniesAnimal) 

    /**
     * Sets the value of field 'tipoMuestreo'.
     * 
     * @param tipoMuestreo the value of field 'tipoMuestreo'.
     */
    public void setTipoMuestreo(ar.org.sicel.proc.v1.lote.types.STTipoMuestreo tipoMuestreo)
    {
        java.lang.Object oldTipoMuestreo = this._tipoMuestreo;
        this._tipoMuestreo = tipoMuestreo;
        notifyPropertyChangeListeners("_tipoMuestreo", oldTipoMuestreo, this._tipoMuestreo);
    } //-- void setTipoMuestreo(ar.org.sicel.proc.v1.lote.types.STTipoMuestreo) 

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
