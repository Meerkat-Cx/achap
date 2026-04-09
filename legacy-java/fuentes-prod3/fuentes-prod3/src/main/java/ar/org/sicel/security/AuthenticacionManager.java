package ar.org.sicel.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase con la funcionalidad de encriptar cadenas mediante MD5
 * @author 
 *
 */
public class AuthenticacionManager {
	
	
	/**
	 * Dada una cadena la encripta
	 * @param password
	 * @return
	 */
	public static String encriptar(String cadena) {
		String password = new String(cadena);
        String encoded = null;
        MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			
	        algorithm.reset();
	        algorithm.update(password.getBytes());

	        byte[] messageDigest = algorithm.digest();

	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < messageDigest.length; i++) {
	            String hex = Integer.toHexString(0xFF & messageDigest[i]);

	            if (hex.length() == 1) {
	                hexString.append('0');
	            }

	            hexString.append(hex);
	        }
	        encoded = hexString + "";       
	        return encoded;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encoded;
    }

}
