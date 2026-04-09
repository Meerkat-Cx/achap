/*
 * Creado el 30/07/2005
 */
package ar.org.sicel.test.otr;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

/**
 * @author ala
 */
public class Mail extends TestCase {

    public class SAuthenticator extends Authenticator 
    {
    private String user;
    private String pass;
        public SAuthenticator(String user, String pass) {
            this.user=user;this.pass=pass;
        }
    public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user,pass);
        }
    }
    
    
    public void testSendEmail() throws Exception {
        String user1 = "asociacionholandoargentino";
        String host1 = "smtp.mail.yahoo.com.ar";
        String pass1 = "14861486";

//        String user2 = "adrianallende";
//        String host2 = "smtp.mail.yahoo.com.ar";
//        String pass2 = "turn873data880";
        
        String fromx = "asociacionholandoargentino@yahoo.com.ar";
        String tox = "0229315520090@personal-net.com.ar";
        // Get system properties
        Properties props = System.getProperties();
        // Setup mail server
        props.put("mail.smtp.user", user1);
        props.put("mail.smtp.host", host1);
        props.put("mail.smtp.auth", "true");
        // Get session
        Authenticator auth = new SAuthenticator(user1,pass1);
        Session session = Session.getDefaultInstance(props,auth);
        // Define message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromx));
        message
                .addRecipient(Message.RecipientType.TO,
                        new InternetAddress(tox));
        message.setHeader("X-Mailer", "JavaMailer");
        message.setSentDate(new Date());
        message.setSubject("Test javax.mail");
        message.setText("Hello world!");
        // Send message
        Transport.send(message);
    }

}
