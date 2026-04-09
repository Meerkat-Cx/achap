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
 * Evento productivo, con mediciones
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtInfo extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _pesoAnimal
     */
    private ar.org.sicel.proc.v1.lote.PesoAnimal _pesoAnimal;

    /**
     * Field _otraInfo
     */
    private ar.org.sicel.proc.v1.lote.OtraInfo _otraInfo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtInfo() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtInfo()


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
        
        if (obj instanceof TEvtInfo) {
        
            TEvtInfo temp = (TEvtInfo)obj;
            if (this._pesoAnimal != null) {
                if (temp._pesoAnimal == null) return false;
                else if (!(this._pesoAnimal.equals(temp._pesoAnimal))) 
                    return false;
            }
            else if (temp._pesoAnimal != null)
                return false;
            if (this._otraInfo != null) {
                if (temp._otraInfo == null) return false;
                else if (!(this._otraInfo.equals(temp._otraInfo))) 
                    return false;
            }
            else if (temp._otraInfo != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'otraInfo'.
     * 
     * @return OtraInfo
     * @return the value of field 'otraInfo'.
     */
    public ar.org.sicel.proc.v1.lote.OtraInfo getOtraInfo()
    {
        return this._otraInfo;
    } //-- ar.org.sicel.proc.v1.lote.OtraInfo getOtraInfo() 

    /**
     * Returns the value of field 'pesoAnimal'.
     * 
     * @return PesoAnimal
     * @return the value of field 'pesoAnimal'.
     */
    public ar.org.sicel.proc.v1.lote.PesoAnimal getPesoAnimal()
    {
        return this._pesoAnimal;
    } //-- ar.org.sicel.proc.v1.lote.PesoAnimal getPesoAnimal() 

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
     * Sets the value of field 'otraInfo'.
     * 
     * @param otraInfo the value of field 'otraInfo'.
     */
    public void setOtraInfo(ar.org.sicel.proc.v1.lote.OtraInfo otraInfo)
    {
        java.lang.Object oldOtraInfo = this._otraInfo;
        this._otraInfo = otraInfo;
        notifyPropertyChangeListeners("_otraInfo", oldOtraInfo, this._otraInfo);
    } //-- void setOtraInfo(ar.org.sicel.proc.v1.lote.OtraInfo) 

    /**
     * Sets the value of field 'pesoAnimal'.
     * 
     * @param pesoAnimal the value of field 'pesoAnimal'.
     */
    public void setPesoAnimal(ar.org.sicel.proc.v1.lote.PesoAnimal pesoAnimal)
    {
        java.lang.Object oldPesoAnimal = this._pesoAnimal;
        this._pesoAnimal = pesoAnimal;
        notifyPropertyChangeListeners("_pesoAnimal", oldPesoAnimal, this._pesoAnimal);
    } //-- void setPesoAnimal(ar.org.sicel.proc.v1.lote.PesoAnimal) 

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
