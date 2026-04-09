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
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

/**
 * Evento reproductivo (partos/abortos)
 * 
 * @version $Revision$ $Date$
 */
public abstract class TEvtReprod extends ar.org.sicel.proc.v1.lote.TEvento 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _criaList
     */
    private java.util.Vector _criaList;

    /**
     * si es un Reproduccion sin Servicio, se puede declarar que
     * los hijos tengan la misma comp. racial y raza de la madre.
     * Sino, se computa el 50% desconocido del padre
     */
    private boolean _usarRazaMadre;

    /**
     * keeps track of state for field: _usarRazaMadre
     */
    private boolean _has_usarRazaMadre;

    /**
     * Field _fechaServicio
     */
    private java.util.Date _fechaServicio;

    /**
     * Field _abortoLargo
     */
    private boolean _abortoLargo;

    /**
     * keeps track of state for field: _abortoLargo
     */
    private boolean _has_abortoLargo;

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

    public TEvtReprod() {
        super();
        _criaList = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.TEvtReprod()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addCria
     * 
     * 
     * 
     * @param vCria
     */
    public void addCria(ar.org.sicel.proc.v1.lote.Cria vCria)
        throws java.lang.IndexOutOfBoundsException
    {
        _criaList.addElement(vCria);
        notifyPropertyChangeListeners("_criaList", null, _criaList);
    } //-- void addCria(ar.org.sicel.proc.v1.lote.Cria) 

    /**
     * Method addCria
     * 
     * 
     * 
     * @param index
     * @param vCria
     */
    public void addCria(int index, ar.org.sicel.proc.v1.lote.Cria vCria)
        throws java.lang.IndexOutOfBoundsException
    {
        _criaList.insertElementAt(vCria, index);
        notifyPropertyChangeListeners("_criaList", null, _criaList);
    } //-- void addCria(int, ar.org.sicel.proc.v1.lote.Cria) 

    /**
     * Method deleteAbortoLargo
     * 
     */
    public void deleteAbortoLargo()
    {
        this._has_abortoLargo= false;
        notifyPropertyChangeListeners("_abortoLargo", (this._abortoLargo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE), null);
    } //-- void deleteAbortoLargo() 

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
     * Method deleteUsarRazaMadre
     * 
     */
    public void deleteUsarRazaMadre()
    {
        this._has_usarRazaMadre= false;
        notifyPropertyChangeListeners("_usarRazaMadre", (this._usarRazaMadre ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE), null);
    } //-- void deleteUsarRazaMadre() 

    /**
     * Method enumerateCria
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateCria()
    {
        return _criaList.elements();
    } //-- java.util.Enumeration enumerateCria() 

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
        
        if (obj instanceof TEvtReprod) {
        
            TEvtReprod temp = (TEvtReprod)obj;
            if (this._criaList != null) {
                if (temp._criaList == null) return false;
                else if (!(this._criaList.equals(temp._criaList))) 
                    return false;
            }
            else if (temp._criaList != null)
                return false;
            if (this._usarRazaMadre != temp._usarRazaMadre)
                return false;
            if (this._has_usarRazaMadre != temp._has_usarRazaMadre)
                return false;
            if (this._fechaServicio != null) {
                if (temp._fechaServicio == null) return false;
                else if (!(this._fechaServicio.equals(temp._fechaServicio))) 
                    return false;
            }
            else if (temp._fechaServicio != null)
                return false;
            if (this._abortoLargo != temp._abortoLargo)
                return false;
            if (this._has_abortoLargo != temp._has_abortoLargo)
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
     * Returns the value of field 'abortoLargo'.
     * 
     * @return boolean
     * @return the value of field 'abortoLargo'.
     */
    public boolean getAbortoLargo()
    {
        return this._abortoLargo;
    } //-- boolean getAbortoLargo() 

    /**
     * Method getCria
     * 
     * 
     * 
     * @param index
     * @return Cria
     */
    public ar.org.sicel.proc.v1.lote.Cria getCria(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _criaList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Cria) _criaList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Cria getCria(int) 

    /**
     * Method getCria
     * 
     * 
     * 
     * @return Cria
     */
    public ar.org.sicel.proc.v1.lote.Cria[] getCria()
    {
        int size = _criaList.size();
        ar.org.sicel.proc.v1.lote.Cria[] mArray = new ar.org.sicel.proc.v1.lote.Cria[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Cria) _criaList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Cria[] getCria() 

    /**
     * Method getCriaCount
     * 
     * 
     * 
     * @return int
     */
    public int getCriaCount()
    {
        return _criaList.size();
    } //-- int getCriaCount() 

    /**
     * Returns the value of field 'fechaServicio'.
     * 
     * @return Date
     * @return the value of field 'fechaServicio'.
     */
    public java.util.Date getFechaServicio()
    {
        return this._fechaServicio;
    } //-- java.util.Date getFechaServicio() 

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
     * Returns the value of field 'usarRazaMadre'. The field
     * 'usarRazaMadre' has the following description: si es un
     * Reproduccion sin Servicio, se puede declarar que los hijos
     * tengan la misma comp. racial y raza de la madre. Sino, se
     * computa el 50% desconocido del padre
     * 
     * @return boolean
     * @return the value of field 'usarRazaMadre'.
     */
    public boolean getUsarRazaMadre()
    {
        return this._usarRazaMadre;
    } //-- boolean getUsarRazaMadre() 

    /**
     * Method hasAbortoLargo
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAbortoLargo()
    {
        return this._has_abortoLargo;
    } //-- boolean hasAbortoLargo() 

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
     * Method hasUsarRazaMadre
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasUsarRazaMadre()
    {
        return this._has_usarRazaMadre;
    } //-- boolean hasUsarRazaMadre() 

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
     * Method removeAllCria
     * 
     */
    public void removeAllCria()
    {
        _criaList.removeAllElements();
        notifyPropertyChangeListeners("_criaList", null, _criaList);
    } //-- void removeAllCria() 

    /**
     * Method removeCria
     * 
     * 
     * 
     * @param index
     * @return Cria
     */
    public ar.org.sicel.proc.v1.lote.Cria removeCria(int index)
    {
        java.lang.Object obj = _criaList.elementAt(index);
        _criaList.removeElementAt(index);
        notifyPropertyChangeListeners("_criaList", null, _criaList);
        return (ar.org.sicel.proc.v1.lote.Cria) obj;
    } //-- ar.org.sicel.proc.v1.lote.Cria removeCria(int) 

    /**
     * Sets the value of field 'abortoLargo'.
     * 
     * @param abortoLargo the value of field 'abortoLargo'.
     */
    public void setAbortoLargo(boolean abortoLargo)
    {
        java.lang.Object oldAbortoLargo = (this._abortoLargo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
        this._abortoLargo = abortoLargo;
        this._has_abortoLargo = true;
        notifyPropertyChangeListeners("_abortoLargo", oldAbortoLargo, (this._abortoLargo ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE));
    } //-- void setAbortoLargo(boolean) 

    /**
     * Method setCria
     * 
     * 
     * 
     * @param index
     * @param vCria
     */
    public void setCria(int index, ar.org.sicel.proc.v1.lote.Cria vCria)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _criaList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _criaList.setElementAt(vCria, index);
        notifyPropertyChangeListeners("_criaList", null, _criaList);
    } //-- void setCria(int, ar.org.sicel.proc.v1.lote.Cria) 

    /**
     * Method setCria
     * 
     * 
     * 
     * @param criaArray
     */
    public void setCria(ar.org.sicel.proc.v1.lote.Cria[] criaArray)
    {
        //-- copy array
        _criaList.removeAllElements();
        for (int i = 0; i < criaArray.length; i++) {
            _criaList.addElement(criaArray[i]);
        }
        notifyPropertyChangeListeners("_criaList", null, _criaList);
    } //-- void setCria(ar.org.sicel.proc.v1.lote.Cria) 

    /**
     * Sets the value of field 'fechaServicio'.
     * 
     * @param fechaServicio the value of field 'fechaServicio'.
     */
    public void setFechaServicio(java.util.Date fechaServicio)
    {
        java.lang.Object oldFechaServicio = this._fechaServicio;
        this._fechaServicio = fechaServicio;
        notifyPropertyChangeListeners("_fechaServicio", oldFechaServicio, this._fechaServicio);
    } //-- void setFechaServicio(java.util.Date) 

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
     * Sets the value of field 'usarRazaMadre'. The field
     * 'usarRazaMadre' has the following description: si es un
     * Reproduccion sin Servicio, se puede declarar que los hijos
     * tengan la misma comp. racial y raza de la madre. Sino, se
     * computa el 50% desconocido del padre
     * 
     * @param usarRazaMadre the value of field 'usarRazaMadre'.
     */
    public void setUsarRazaMadre(boolean usarRazaMadre)
    {
        java.lang.Object oldUsarRazaMadre = (this._usarRazaMadre ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE);
        this._usarRazaMadre = usarRazaMadre;
        this._has_usarRazaMadre = true;
        notifyPropertyChangeListeners("_usarRazaMadre", oldUsarRazaMadre, (this._usarRazaMadre ? java.lang.Boolean.TRUE : java.lang.Boolean.FALSE));
    } //-- void setUsarRazaMadre(boolean) 

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
