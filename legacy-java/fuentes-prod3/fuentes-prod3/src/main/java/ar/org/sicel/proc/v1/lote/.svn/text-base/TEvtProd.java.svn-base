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


/**
 * Evento producción
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtProd extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * id del evento ordenie que corresponde a esta produccion, el
     * evento ordenie debe ser informado en el mismo lote, y de
     * all? se obtienen los valores de metodo, esam, etc.
     */
    private long _IDEvtOrdenie;

    /**
     * keeps track of state for field: _IDEvtOrdenie
     */
    private boolean _has_IDEvtOrdenie;

    /**
     * Field _meds
     */
    private ar.org.sicel.proc.v1.lote.Meds _meds;


      //----------------/
     //- Constructors -/
    //----------------/

    public TEvtProd() {
        super();
    } //-- ar.org.sicel.proc.v1.lote.TEvtProd()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteIDEvtOrdenie
     * 
     */
    public void deleteIDEvtOrdenie()
    {
        this._has_IDEvtOrdenie= false;
        notifyPropertyChangeListeners("_IDEvtOrdenie", new java.lang.Long(this._IDEvtOrdenie), null);
    } //-- void deleteIDEvtOrdenie() 

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
        
        if (obj instanceof TEvtProd) {
        
            TEvtProd temp = (TEvtProd)obj;
            if (this._IDEvtOrdenie != temp._IDEvtOrdenie)
                return false;
            if (this._has_IDEvtOrdenie != temp._has_IDEvtOrdenie)
                return false;
            if (this._meds != null) {
                if (temp._meds == null) return false;
                else if (!(this._meds.equals(temp._meds))) 
                    return false;
            }
            else if (temp._meds != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'IDEvtOrdenie'. The field
     * 'IDEvtOrdenie' has the following description: id del evento
     * ordenie que corresponde a esta produccion, el evento ordenie
     * debe ser informado en el mismo lote, y de all? se obtienen
     * los valores de metodo, esam, etc.
     * 
     * @return long
     * @return the value of field 'IDEvtOrdenie'.
     */
    public long getIDEvtOrdenie()
    {
        return this._IDEvtOrdenie;
    } //-- long getIDEvtOrdenie() 

    /**
     * Returns the value of field 'meds'.
     * 
     * @return Meds
     * @return the value of field 'meds'.
     */
    public ar.org.sicel.proc.v1.lote.Meds getMeds()
    {
        return this._meds;
    } //-- ar.org.sicel.proc.v1.lote.Meds getMeds() 

    /**
     * Method hasIDEvtOrdenie
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasIDEvtOrdenie()
    {
        return this._has_IDEvtOrdenie;
    } //-- boolean hasIDEvtOrdenie() 

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
     * Sets the value of field 'IDEvtOrdenie'. The field
     * 'IDEvtOrdenie' has the following description: id del evento
     * ordenie que corresponde a esta produccion, el evento ordenie
     * debe ser informado en el mismo lote, y de all? se obtienen
     * los valores de metodo, esam, etc.
     * 
     * @param IDEvtOrdenie the value of field 'IDEvtOrdenie'.
     */
    public void setIDEvtOrdenie(long IDEvtOrdenie)
    {
        java.lang.Object oldIDEvtOrdenie = new java.lang.Long(this._IDEvtOrdenie);
        this._IDEvtOrdenie = IDEvtOrdenie;
        this._has_IDEvtOrdenie = true;
        notifyPropertyChangeListeners("_IDEvtOrdenie", oldIDEvtOrdenie, new java.lang.Long(this._IDEvtOrdenie));
    } //-- void setIDEvtOrdenie(long) 

    /**
     * Sets the value of field 'meds'.
     * 
     * @param meds the value of field 'meds'.
     */
    public void setMeds(ar.org.sicel.proc.v1.lote.Meds meds)
    {
        java.lang.Object oldMeds = this._meds;
        this._meds = meds;
        notifyPropertyChangeListeners("_meds", oldMeds, this._meds);
    } //-- void setMeds(ar.org.sicel.proc.v1.lote.Meds) 

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
