/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.6</a>, using an XML
 * Schema.
 * $Id$
 */

package ar.org.sicel.proc.v1.lote.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Ev Prod
 * 
 * @version $Revision$ $Date$
 */
public class STMetodoControl implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The A4 type
     */
    public static final int A4_TYPE = 0;

    /**
     * The instance of the A4 type
     */
    public static final STMetodoControl A4 = new STMetodoControl(A4_TYPE, "A4");

    /**
     * The A4SM type
     */
    public static final int A4SM_TYPE = 1;

    /**
     * The instance of the A4SM type
     */
    public static final STMetodoControl A4SM = new STMetodoControl(A4SM_TYPE, "A4SM");

    /**
     * The C4 type
     */
    public static final int C4_TYPE = 2;

    /**
     * The instance of the C4 type
     */
    public static final STMetodoControl C4 = new STMetodoControl(C4_TYPE, "C4");

    /**
     * The C4SM type
     */
    public static final int C4SM_TYPE = 3;

    /**
     * The instance of the C4SM type
     */
    public static final STMetodoControl C4SM = new STMetodoControl(C4SM_TYPE, "C4SM");

    /**
     * The A6 type
     */
    public static final int A6_TYPE = 4;

    /**
     * The instance of the A6 type
     */
    public static final STMetodoControl A6 = new STMetodoControl(A6_TYPE, "A6");

    /**
     * The A6SM type
     */
    public static final int A6SM_TYPE = 5;

    /**
     * The instance of the A6SM type
     */
    public static final STMetodoControl A6SM = new STMetodoControl(A6SM_TYPE, "A6SM");

    /**
     * The C6 type
     */
    public static final int C6_TYPE = 6;

    /**
     * The instance of the C6 type
     */
    public static final STMetodoControl C6 = new STMetodoControl(C6_TYPE, "C6");

    /**
     * The C6SM type
     */
    public static final int C6SM_TYPE = 7;

    /**
     * The instance of the C6SM type
     */
    public static final STMetodoControl C6SM = new STMetodoControl(C6SM_TYPE, "C6SM");

    /**
     * Field _memberTable
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type
     */
    private int type = -1;

    /**
     * Field stringValue
     */
    private java.lang.String stringValue = null;

    /**
     * Field propertyChangeListeners
     */
    private java.util.Vector propertyChangeListeners;


      //----------------/
     //- Constructors -/
    //----------------/

    private STMetodoControl(int type, java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.types.STMetodoControl(int, java.lang.String)


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
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * STMetodoControl
     * 
     * @return Enumeration
     */
    public static java.util.Enumeration enumerate()
    {
        return _memberTable.elements();
    } //-- java.util.Enumeration enumerate() 

    /**
     * Method getType
     * 
     * Returns the type of this STMetodoControl
     * 
     * @return int
     */
    public int getType()
    {
        return this.type;
    } //-- int getType() 

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
     * Method init
     * 
     * 
     * 
     * @return Hashtable
     */
    private static java.util.Hashtable init()
    {
        Hashtable members = new Hashtable();
        members.put("A4", A4);
        members.put("A4SM", A4SM);
        members.put("C4", C4);
        members.put("C4SM", C4SM);
        members.put("A6", A6);
        members.put("A6SM", A6SM);
        members.put("C6", C6);
        members.put("C6SM", C6SM);
        return members;
    } //-- java.util.Hashtable init() 

    /**
     * Method readResolve
     * 
     *  will be called during deserialization to replace the
     * deserialized object with the correct constant instance.
     * <br/>
     * 
     * @return Object
     */
    private java.lang.Object readResolve()
    {
        return valueOf(this.stringValue);
    } //-- java.lang.Object readResolve() 

    /**
     * Method toString
     * 
     * Returns the String representation of this STMetodoControl
     * 
     * @return String
     */
    public java.lang.String toString()
    {
        return this.stringValue;
    } //-- java.lang.String toString() 

    /**
     * Method valueOf
     * 
     * Returns a new STMetodoControl based on the given String
     * value.
     * 
     * @param string
     * @return STMetodoControl
     */
    public static ar.org.sicel.proc.v1.lote.types.STMetodoControl valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid STMetodoControl";
            throw new IllegalArgumentException(err);
        }
        return (STMetodoControl) obj;
    } //-- ar.org.sicel.proc.v1.lote.types.STMetodoControl valueOf(java.lang.String) 

}
