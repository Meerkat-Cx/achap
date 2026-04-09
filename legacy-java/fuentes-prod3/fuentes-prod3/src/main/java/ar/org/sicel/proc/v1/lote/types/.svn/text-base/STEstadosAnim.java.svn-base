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
 * Estado porductivo y reproductivo de un animal
 * 
 * @version $Revision$ $Date$
 */
public class STEstadosAnim implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The SV type
     */
    public static final int SV_TYPE = 0;

    /**
     * The instance of the SV type
     */
    public static final STEstadosAnim SV = new STEstadosAnim(SV_TYPE, "SV");

    /**
     * The SS type
     */
    public static final int SS_TYPE = 1;

    /**
     * The instance of the SS type
     */
    public static final STEstadosAnim SS = new STEstadosAnim(SS_TYPE, "SS");

    /**
     * The SP type
     */
    public static final int SP_TYPE = 2;

    /**
     * The instance of the SP type
     */
    public static final STEstadosAnim SP = new STEstadosAnim(SP_TYPE, "SP");

    /**
     * The PV type
     */
    public static final int PV_TYPE = 3;

    /**
     * The instance of the PV type
     */
    public static final STEstadosAnim PV = new STEstadosAnim(PV_TYPE, "PV");

    /**
     * The PS type
     */
    public static final int PS_TYPE = 4;

    /**
     * The instance of the PS type
     */
    public static final STEstadosAnim PS = new STEstadosAnim(PS_TYPE, "PS");

    /**
     * The PP type
     */
    public static final int PP_TYPE = 5;

    /**
     * The instance of the PP type
     */
    public static final STEstadosAnim PP = new STEstadosAnim(PP_TYPE, "PP");

    /**
     * The BA type
     */
    public static final int BA_TYPE = 6;

    /**
     * The instance of the BA type
     */
    public static final STEstadosAnim BA = new STEstadosAnim(BA_TYPE, "BA");

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

    private STEstadosAnim(int type, java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.types.STEstadosAnim(int, java.lang.String)


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
     * STEstadosAnim
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
     * Returns the type of this STEstadosAnim
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
        members.put("SV", SV);
        members.put("SS", SS);
        members.put("SP", SP);
        members.put("PV", PV);
        members.put("PS", PS);
        members.put("PP", PP);
        members.put("BA", BA);
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
     * Returns the String representation of this STEstadosAnim
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
     * Returns a new STEstadosAnim based on the given String value.
     * 
     * @param string
     * @return STEstadosAnim
     */
    public static ar.org.sicel.proc.v1.lote.types.STEstadosAnim valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid STEstadosAnim";
            throw new IllegalArgumentException(err);
        }
        return (STEstadosAnim) obj;
    } //-- ar.org.sicel.proc.v1.lote.types.STEstadosAnim valueOf(java.lang.String) 

}
