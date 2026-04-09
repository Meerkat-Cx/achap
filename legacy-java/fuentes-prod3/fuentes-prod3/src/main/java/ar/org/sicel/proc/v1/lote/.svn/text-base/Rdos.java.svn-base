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

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Rdos.
 * 
 * @version $Revision$ $Date$
 */
public class Rdos extends ar.org.sicel.proc.v1.lote.TRdo 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _rdoList
     */
    private java.util.Vector _rdoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Rdos() {
        super();
        _rdoList = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.Rdos()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addRdo
     * 
     * 
     * 
     * @param vRdo
     */
    public void addRdo(ar.org.sicel.proc.v1.lote.Rdo vRdo)
        throws java.lang.IndexOutOfBoundsException
    {
        _rdoList.addElement(vRdo);
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
    } //-- void addRdo(ar.org.sicel.proc.v1.lote.Rdo) 

    /**
     * Method addRdo
     * 
     * 
     * 
     * @param index
     * @param vRdo
     */
    public void addRdo(int index, ar.org.sicel.proc.v1.lote.Rdo vRdo)
        throws java.lang.IndexOutOfBoundsException
    {
        _rdoList.insertElementAt(vRdo, index);
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
    } //-- void addRdo(int, ar.org.sicel.proc.v1.lote.Rdo) 

    /**
     * Method enumerateRdo
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateRdo()
    {
        return _rdoList.elements();
    } //-- java.util.Enumeration enumerateRdo() 

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
        
        if (obj instanceof Rdos) {
        
            Rdos temp = (Rdos)obj;
            if (this._rdoList != null) {
                if (temp._rdoList == null) return false;
                else if (!(this._rdoList.equals(temp._rdoList))) 
                    return false;
            }
            else if (temp._rdoList != null)
                return false;
            return true;
        }
        return false;
    } //-- boolean equals(java.lang.Object) 

    /**
     * Method getRdo
     * 
     * 
     * 
     * @param index
     * @return Rdo
     */
    public ar.org.sicel.proc.v1.lote.Rdo getRdo(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _rdoList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (ar.org.sicel.proc.v1.lote.Rdo) _rdoList.elementAt(index);
    } //-- ar.org.sicel.proc.v1.lote.Rdo getRdo(int) 

    /**
     * Method getRdo
     * 
     * 
     * 
     * @return Rdo
     */
    public ar.org.sicel.proc.v1.lote.Rdo[] getRdo()
    {
        int size = _rdoList.size();
        ar.org.sicel.proc.v1.lote.Rdo[] mArray = new ar.org.sicel.proc.v1.lote.Rdo[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (ar.org.sicel.proc.v1.lote.Rdo) _rdoList.elementAt(index);
        }
        return mArray;
    } //-- ar.org.sicel.proc.v1.lote.Rdo[] getRdo() 

    /**
     * Method getRdoCount
     * 
     * 
     * 
     * @return int
     */
    public int getRdoCount()
    {
        return _rdoList.size();
    } //-- int getRdoCount() 

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
     * Method removeAllRdo
     * 
     */
    public void removeAllRdo()
    {
        _rdoList.removeAllElements();
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
    } //-- void removeAllRdo() 

    /**
     * Method removeRdo
     * 
     * 
     * 
     * @param index
     * @return Rdo
     */
    public ar.org.sicel.proc.v1.lote.Rdo removeRdo(int index)
    {
        java.lang.Object obj = _rdoList.elementAt(index);
        _rdoList.removeElementAt(index);
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
        return (ar.org.sicel.proc.v1.lote.Rdo) obj;
    } //-- ar.org.sicel.proc.v1.lote.Rdo removeRdo(int) 

    /**
     * Method setRdo
     * 
     * 
     * 
     * @param index
     * @param vRdo
     */
    public void setRdo(int index, ar.org.sicel.proc.v1.lote.Rdo vRdo)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _rdoList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _rdoList.setElementAt(vRdo, index);
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
    } //-- void setRdo(int, ar.org.sicel.proc.v1.lote.Rdo) 

    /**
     * Method setRdo
     * 
     * 
     * 
     * @param rdoArray
     */
    public void setRdo(ar.org.sicel.proc.v1.lote.Rdo[] rdoArray)
    {
        //-- copy array
        _rdoList.removeAllElements();
        for (int i = 0; i < rdoArray.length; i++) {
            _rdoList.addElement(rdoArray[i]);
        }
        notifyPropertyChangeListeners("_rdoList", null, _rdoList);
    } //-- void setRdo(ar.org.sicel.proc.v1.lote.Rdo) 

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
        return (ar.org.sicel.proc.v1.lote.Rdos) Unmarshaller.unmarshal(ar.org.sicel.proc.v1.lote.Rdos.class, reader);
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
