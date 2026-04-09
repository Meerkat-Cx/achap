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
 * Objetos de medicion admitidos en evento produccion
 * 
 * @version $Revision$ $Date$
 */
public class STProdMedObj implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The LE type
     */
    public static final int LE_TYPE = 0;

    /**
     * The instance of the LE type
     */
    public static final STProdMedObj LE = new STProdMedObj(LE_TYPE, "LE");

    /**
     * The GR type
     */
    public static final int GR_TYPE = 1;

    /**
     * The instance of the GR type
     */
    public static final STProdMedObj GR = new STProdMedObj(GR_TYPE, "GR");

    /**
     * The PR type
     */
    public static final int PR_TYPE = 2;

    /**
     * The instance of the PR type
     */
    public static final STProdMedObj PR = new STProdMedObj(PR_TYPE, "PR");

    /**
     * The LA type
     */
    public static final int LA_TYPE = 3;

    /**
     * The instance of the LA type
     */
    public static final STProdMedObj LA = new STProdMedObj(LA_TYPE, "LA");

    /**
     * The ST type
     */
    public static final int ST_TYPE = 4;

    /**
     * The instance of the ST type
     */
    public static final STProdMedObj ST = new STProdMedObj(ST_TYPE, "ST");

    /**
     * The UR type
     */
    public static final int UR_TYPE = 5;

    /**
     * The instance of the UR type
     */
    public static final STProdMedObj UR = new STProdMedObj(UR_TYPE, "UR");

    /**
     * The CE type
     */
    public static final int CE_TYPE = 6;

    /**
     * The instance of the CE type
     */
    public static final STProdMedObj CE = new STProdMedObj(CE_TYPE, "CE");

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

    private STProdMedObj(int type, java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
        propertyChangeListeners = new Vector();
    } //-- ar.org.sicel.proc.v1.lote.types.STProdMedObj(int, java.lang.String)


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
     * STProdMedObj
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
     * Returns the type of this STProdMedObj
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
        members.put("LE", LE);
        members.put("GR", GR);
        members.put("PR", PR);
        members.put("LA", LA);
        members.put("ST", ST);
        members.put("UR", UR);
        members.put("CE", CE);
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
     * Returns the String representation of this STProdMedObj
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
     * Returns a new STProdMedObj based on the given String value.
     * 
     * @param string
     * @return STProdMedObj
     */
    public static ar.org.sicel.proc.v1.lote.types.STProdMedObj valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid STProdMedObj";
            throw new IllegalArgumentException(err);
        }
        return (STProdMedObj) obj;
    } //-- ar.org.sicel.proc.v1.lote.types.STProdMedObj valueOf(java.lang.String) 

}
