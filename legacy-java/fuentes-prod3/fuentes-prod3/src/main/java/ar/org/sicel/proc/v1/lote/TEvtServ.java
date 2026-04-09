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

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento servicio y te
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtServ extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _clon
     */
    private ar.org.sicel.proc.v1.lote.Clon _clon;

    /**
     * Field _nuevo
     */
    private ar.org.sicel.proc.v1.lote.Nuevo _nuevo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtServ() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtServ()


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
        
        if (obj instanceof TEvtServ) {
        
            TEvtServ temp = (TEvtServ)obj;
            if (this._clon != null) {
                if (temp._clon == null) return false;
                else if (!(this._clon.equals(temp._clon))) 
                    return false;
            }
            else if (temp._clon != null)
                return false;
            if (this._nuevo != null) {
                if (temp._nuevo == null) return false;
                else if (!(this._nuevo.equals(temp._nuevo))) 
                    return false;
            }
            else if (temp._nuevo != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'clon'.
     * 
     * @return Clon
     * @return the value of field 'clon'.
     */
    public ar.org.sicel.proc.v1.lote.Clon getClon()
    {
        return this._clon;
    } //-- ar.org.sicel.proc.v1.lote.Clon getClon() 

    /**
     * Returns the value of field 'nuevo'.
     * 
     * @return Nuevo
     * @return the value of field 'nuevo'.
     */
    public ar.org.sicel.proc.v1.lote.Nuevo getNuevo()
    {
        return this._nuevo;
    } //-- ar.org.sicel.proc.v1.lote.Nuevo getNuevo() 

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
     * Sets the value of field 'clon'.
     * 
     * @param clon the value of field 'clon'.
     */
    public void setClon(ar.org.sicel.proc.v1.lote.Clon clon)
    {
        java.lang.Object oldClon = this._clon;
        this._clon = clon;
        notifyPropertyChangeListeners("_clon", oldClon, this._clon);
    } //-- void setClon(ar.org.sicel.proc.v1.lote.Clon) 

    /**
     * Sets the value of field 'nuevo'.
     * 
     * @param nuevo the value of field 'nuevo'.
     */
    public void setNuevo(ar.org.sicel.proc.v1.lote.Nuevo nuevo)
    {
        java.lang.Object oldNuevo = this._nuevo;
        this._nuevo = nuevo;
        notifyPropertyChangeListeners("_nuevo", oldNuevo, this._nuevo);
    } //-- void setNuevo(ar.org.sicel.proc.v1.lote.Nuevo) 

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
