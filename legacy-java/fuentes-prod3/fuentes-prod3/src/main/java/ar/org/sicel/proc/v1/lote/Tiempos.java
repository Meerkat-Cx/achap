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
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Date;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Tiempos.
 * 
 * @version $Revision$ $Date$
 */
public class Tiempos implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Fecha hora de creación por el informante
     */
    private java.util.Date _tCrea;

    /**
     * Fecha hora de envio a ACHA
     */
    private java.util.Date _tEnvi;

    /**
     * Fecha hora de recepcion en ACHA
     */
    private java.util.Date _tRece;

    /**
     * Fecha hora de inicio del proceso
     */
    private java.util.Date _tProcIni;

    /**
     * Fecha hora de fin de proceso
     */
    private java.util.Date _tProcFin;

    /**
     * Fecha hora de retorno de ACHA hacia el informante
     */
    private java.util.Date _tReto;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    public Tiempos() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Tiempos()


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
        
        if (obj instanceof Tiempos) {
        
            Tiempos temp = (Tiempos)obj;
            if (this._tCrea != null) {
                if (temp._tCrea == null) return false;
                else if (!(this._tCrea.equals(temp._tCrea))) 
                    return false;
            }
            else if (temp._tCrea != null)
                return false;
            if (this._tEnvi != null) {
                if (temp._tEnvi == null) return false;
                else if (!(this._tEnvi.equals(temp._tEnvi))) 
                    return false;
            }
            else if (temp._tEnvi != null)
                return false;
            if (this._tRece != null) {
                if (temp._tRece == null) return false;
                else if (!(this._tRece.equals(temp._tRece))) 
                    return false;
            }
            else if (temp._tRece != null)
                return false;
            if (this._tProcIni != null) {
                if (temp._tProcIni == null) return false;
                else if (!(this._tProcIni.equals(temp._tProcIni))) 
                    return false;
            }
            else if (temp._tProcIni != null)
                return false;
            if (this._tProcFin != null) {
                if (temp._tProcFin == null) return false;
                else if (!(this._tProcFin.equals(temp._tProcFin))) 
                    return false;
            }
            else if (temp._tProcFin != null)
                return false;
            if (this._tReto != null) {
                if (temp._tReto == null) return false;
                else if (!(this._tReto.equals(temp._tReto))) 
                    return false;
            }
            else if (temp._tReto != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Returns the value of field 'tCrea'. The field 'tCrea' has
     * the following description: Fecha hora de creación por el
     * informante
     * 
     * @return Date
     * @return the value of field 'tCrea'.
     */
    public java.util.Date getTCrea()
    {
        return this._tCrea;
    } //-- java.util.Date getTCrea() 

    /**
     * Returns the value of field 'tEnvi'. The field 'tEnvi' has
     * the following description: Fecha hora de envio a ACHA
     * 
     * @return Date
     * @return the value of field 'tEnvi'.
     */
    public java.util.Date getTEnvi()
    {
        return this._tEnvi;
    } //-- java.util.Date getTEnvi() 

    /**
     * Returns the value of field 'tProcFin'. The field 'tProcFin'
     * has the following description: Fecha hora de fin de proceso
     * 
     * @return Date
     * @return the value of field 'tProcFin'.
     */
    public java.util.Date getTProcFin()
    {
        return this._tProcFin;
    } //-- java.util.Date getTProcFin() 

    /**
     * Returns the value of field 'tProcIni'. The field 'tProcIni'
     * has the following description: Fecha hora de inicio del
     * proceso
     * 
     * @return Date
     * @return the value of field 'tProcIni'.
     */
    public java.util.Date getTProcIni()
    {
        return this._tProcIni;
    } //-- java.util.Date getTProcIni() 

    /**
     * Returns the value of field 'tRece'. The field 'tRece' has
     * the following description: Fecha hora de recepcion en ACHA
     * 
     * @return Date
     * @return the value of field 'tRece'.
     */
    public java.util.Date getTRece()
    {
        return this._tRece;
    } //-- java.util.Date getTRece() 

    /**
     * Returns the value of field 'tReto'. The field 'tReto' has
     * the following description: Fecha hora de retorno de ACHA
     * hacia el informante
     * 
     * @return Date
     * @return the value of field 'tReto'.
     */
    public java.util.Date getTReto()
    {
        return this._tReto;
    } //-- java.util.Date getTReto() 

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
     * Sets the value of field 'tCrea'. The field 'tCrea' has the
     * following description: Fecha hora de creación por el
     * informante
     * 
     * @param tCrea the value of field 'tCrea'.
     */
    public void setTCrea(java.util.Date tCrea)
    {
        java.lang.Object oldTCrea = this._tCrea;
        this._tCrea = tCrea;
        notifyPropertyChangeListeners("_tCrea", oldTCrea, this._tCrea);
    } //-- void setTCrea(java.util.Date) 

    /**
     * Sets the value of field 'tEnvi'. The field 'tEnvi' has the
     * following description: Fecha hora de envio a ACHA
     * 
     * @param tEnvi the value of field 'tEnvi'.
     */
    public void setTEnvi(java.util.Date tEnvi)
    {
        java.lang.Object oldTEnvi = this._tEnvi;
        this._tEnvi = tEnvi;
        notifyPropertyChangeListeners("_tEnvi", oldTEnvi, this._tEnvi);
    } //-- void setTEnvi(java.util.Date) 

    /**
     * Sets the value of field 'tProcFin'. The field 'tProcFin' has
     * the following description: Fecha hora de fin de proceso
     * 
     * @param tProcFin the value of field 'tProcFin'.
     */
    public void setTProcFin(java.util.Date tProcFin)
    {
        java.lang.Object oldTProcFin = this._tProcFin;
        this._tProcFin = tProcFin;
        notifyPropertyChangeListeners("_tProcFin", oldTProcFin, this._tProcFin);
    } //-- void setTProcFin(java.util.Date) 

    /**
     * Sets the value of field 'tProcIni'. The field 'tProcIni' has
     * the following description: Fecha hora de inicio del proceso
     * 
     * @param tProcIni the value of field 'tProcIni'.
     */
    public void setTProcIni(java.util.Date tProcIni)
    {
        java.lang.Object oldTProcIni = this._tProcIni;
        this._tProcIni = tProcIni;
        notifyPropertyChangeListeners("_tProcIni", oldTProcIni, this._tProcIni);
    } //-- void setTProcIni(java.util.Date) 

    /**
     * Sets the value of field 'tRece'. The field 'tRece' has the
     * following description: Fecha hora de recepcion en ACHA
     * 
     * @param tRece the value of field 'tRece'.
     */
    public void setTRece(java.util.Date tRece)
    {
        java.lang.Object oldTRece = this._tRece;
        this._tRece = tRece;
        notifyPropertyChangeListeners("_tRece", oldTRece, this._tRece);
    } //-- void setTRece(java.util.Date) 

    /**
     * Sets the value of field 'tReto'. The field 'tReto' has the
     * following description: Fecha hora de retorno de ACHA hacia
     * el informante
     * 
     * @param tReto the value of field 'tReto'.
     */
    public void setTReto(java.util.Date tReto)
    {
        java.lang.Object oldTReto = this._tReto;
        this._tReto = tReto;
        notifyPropertyChangeListeners("_tReto", oldTReto, this._tReto);
    } //-- void setTReto(java.util.Date) 

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
        return (ar.org.sicel.proc.v1.lote.Tiempos) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Tiempos.class, reader);
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
