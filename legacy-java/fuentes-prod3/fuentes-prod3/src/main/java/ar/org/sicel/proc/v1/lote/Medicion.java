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

import ar.org.sicel.proc.v1.lote.types.STProdMedObj;
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
 * Class Medicion.
 * 
 * @version $Revision$ $Date$
 */
public class Medicion implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _objeto
     */
    private ar.org.sicel.proc.v1.lote.types.STProdMedObj _objeto;

    /**
     * Field _valor
     */
    private float _valor;

    /**
     * keeps track of state for field: _valor
     */
    private boolean _has_valor;

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

    public Medicion() {
        super();
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Medicion()


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
     * Method deleteValor
     * 
     */
    public void deleteValor()
    {
        this._has_valor= false;
        notifyPropertyChangeListeners("_valor", new java.lang.Float(this._valor), null);
    } //-- void deleteValor() 

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
        
        if (obj instanceof Medicion) {
        
            Medicion temp = (Medicion)obj;
            if (this._objeto != null) {
                if (temp._objeto == null) return false;
                else if (!(this._objeto.equals(temp._objeto))) 
                    return false;
            }
            else if (temp._objeto != null)
                return false;
            if (this._valor != temp._valor)
                return false;
            if (this._has_valor != temp._has_valor)
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
     * Returns the value of field 'objeto'.
     * 
     * @return STProdMedObj
     * @return the value of field 'objeto'.
     */
    public ar.org.sicel.proc.v1.lote.types.STProdMedObj getObjeto()
    {
        return this._objeto;
    } //-- ar.org.sicel.proc.v1.lote.types.STProdMedObj getObjeto() 

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
     * Returns the value of field 'valor'.
     * 
     * @return float
     * @return the value of field 'valor'.
     */
    public float getValor()
    {
        return this._valor;
    } //-- float getValor() 

    /**
     * Method hasValor
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasValor()
    {
        return this._has_valor;
    } //-- boolean hasValor() 

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
     * Sets the value of field 'objeto'.
     * 
     * @param objeto the value of field 'objeto'.
     */
    public void setObjeto(ar.org.sicel.proc.v1.lote.types.STProdMedObj objeto)
    {
        java.lang.Object oldObjeto = this._objeto;
        this._objeto = objeto;
        notifyPropertyChangeListeners("_objeto", oldObjeto, this._objeto);
    } //-- void setObjeto(ar.org.sicel.proc.v1.lote.types.STProdMedObj) 

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
     * Sets the value of field 'valor'.
     * 
     * @param valor the value of field 'valor'.
     */
    public void setValor(float valor)
    {
        java.lang.Object oldValor = new java.lang.Float(this._valor);
        this._valor = valor;
        this._has_valor = true;
        notifyPropertyChangeListeners("_valor", oldValor, new java.lang.Float(this._valor));
    } //-- void setValor(float) 

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
        return (ar.org.sicel.proc.v1.lote.Medicion) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Medicion.class, reader);
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
