package ar.org.sicel.persistence.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.type.ImmutableType;


/**
 * <p>
 * A hibernate user type which converts a Blob into a byte[] and back again.
 * </p>
 */
public class HibernateByteBlobType extends ImmutableType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3158406991076857781L;

	@Override
	public Object get(ResultSet rs, String name) throws HibernateException, SQLException {
		//Reader reader = null;
		InputStream inputStream = null;
        try {
            // reader = rs.getCharacterStream(name);
        	//Clob clob = rs.getClob(name);
        	
        	//Blob blob = rs.getBlob(name);
        	
        	inputStream  = 	rs.getBinaryStream(name);
        	//InputStream inputStream = blob.getBinaryStream();
//        	reader = new InputStreamReader(inputStream);
        	//reader =  .getCharacterStream();
            
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
        	return null;
        }

        //if (reader == null) {
         //   return null;
        
        if (inputStream == null) {
             return null;            
        }

       try {
		return IOUtils.toByteArray(inputStream);
	} catch (IOException e) {
		throw new SQLException("No se puede construir el arreglo de bytes");
	}
	}

	@Override
	public void set(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		st.setBytes(index,(byte[])value);
		
	}

	@Override
	public int sqlType() {
		return Types.BLOB;
	}

	@Override
	public String toString(Object value) throws HibernateException {
		return value.toString();
	}

	@Override
	public Object fromStringValue(String xml) throws HibernateException {
		throw new HibernateException("No implementado");
	}

	public Class getReturnedClass() {
		return byte[].class;
	}

	public String getName() {
		return "BLOB" ;
	}
	
	
	
}



