package ar.org.sicel.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
//import javax.mail.util.ByteArrayDataSource;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.Vector;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class EmailSender {

    private static final Logger log;

     static{
         log = Logger.getLogger(EmailSender.class);
     }

    private static Session m_session;


    /** Constructor */
    static {

        ResourceBundle res = ResourceBundle.getBundle ("Config");

        Properties props = new Properties();

        props.put( "mail.host", res.getString("email.host"));
        props.put( "mail.smtp.auth" , res.getString("email.auth"));
        props.put( "mail.smtp.from", res.getString("email.from"));
        
//        m_session = Session.getDefaultInstance( props, new Auth() );
		m_session = Session.getDefaultInstance( props);
    }




    /** Send the email
     *
     */
    public static void sendEmail( Email email ) throws AddressException, MessagingException  {
    	if (email.hasRecipients()) {
    		MimeMessage newMessage = new MimeMessage(m_session);
            newMessage.setHeader("X-Mailer", "sendhtml");

            // FROM
            ResourceBundle res = ResourceBundle.getBundle ("Config");
            if ("".equals(email.getFrom())) 
         	   
         	   email.setFrom(res.getString("email.from"));
         	   //email.setFrom("info_sicel@acha.org.ar");
            InternetAddress from = new InternetAddress( email.getFrom() );
            newMessage.setFrom( from );

            if (email.getReplyTo() != null && !email.getReplyTo().equals("")) {
            	InternetAddress replyTos[] = {new InternetAddress(email.getReplyTo())};
                newMessage.setReplyTo(replyTos);
            }

            // TO
            Vector toList = email.getToList();
            InternetAddress[] toAddr = new InternetAddress[ toList.size() ];
            for (int i=0; i < toList.size(); i++){
            	String emailAddress = (String) toList.get(i);
                if (validateEmail(emailAddress)) {
                	toAddr[i] = new InternetAddress(emailAddress);
                }
            }
            newMessage.setRecipients(Message.RecipientType.TO, toAddr);

            // CC
            Vector ccList = email.getCcList();
            InternetAddress[] ccAddr = new InternetAddress[ ccList.size() ];
            for (int i=0; i < ccList.size(); i++){
            	String emailAddress = (String) ccList.get(i);
                if (validateEmail(emailAddress)) {
                	ccAddr[i] = new InternetAddress(emailAddress);
                }
            }
            newMessage.setRecipients(Message.RecipientType.CC, ccAddr);

            // BCC
            Vector bccList = email.getBccList();
            InternetAddress[] bccAddr = new InternetAddress[ bccList.size() ];
            for (int i=0; i < bccList.size(); i++){
            	String emailAddress = (String) bccList.get(i);
                if (validateEmail(emailAddress)) {
                	bccAddr[i] = new InternetAddress(emailAddress);
                }
           }
           newMessage.setRecipients(Message.RecipientType.BCC, bccAddr);

           // SUBJECT
           newMessage.setSubject( email.getSubject() );
           newMessage.setSentDate( new java.util.Date() );

           // IF HAS ATTACHMENTS OR IS A HTML MESSAGE GENERATE A MULTIPART MESSAGE
           if ( email.isHTMLMode()) { // || email.hasAttachements() ) {
        	   MimeMultipart mp = new MimeMultipart();
               //BodyPart tp = new MimeBodyPart();
               //DataSource source1 = new ByteArrayDataSource(email.getTextBody(),"text/html");
               //DataSource source = new ByteArrayDataSource(email.getHtmlBody(),"text/html");
               //tp.setDataHandler(new DataHandler(source1));
               //tp.setText(email.getTextBody());
               //mp.addBodyPart(tp);
        	   BodyPart tp1 = new MimeBodyPart();
               DataSource source = new ByteArrayDataSource(email.getTextBody()+email.getHtmlBody(),"text/html");
               // DataSource source = new ByteArrayDataSource(email.getHtmlBody(),"text/html");
               tp1.setDataHandler(new DataHandler(source));
               // tp.setContent(email.getHtmlBody(), "text/html");
               mp.addBodyPart(tp1);
               mp.setSubType("alternative");
               /**
                 // BODY
                  Multipart multipart = new MimeMultipart();
                  BodyPart messageBodyPart = new MimeBodyPart();
                  if ( email.isHTMLMode() ){
                  	DataSource source = new ByteArrayDataSource(email.getHtmlBody(),"text/html");
                    messageBodyPart.setDataHandler(new DataHandler(source));
                  } else {
                  	messageBodyPart.setText( email.getTextBody() );
                  }
                  multipart.addBodyPart(messageBodyPart);
                  // if html mode, handle text only readers
                  if (email.isHTMLMode()){
                  	 BodyPart messageBodyPart2 = new MimeBodyPart();
                     DataSource source2 = new ByteArrayDataSource(email.getTextBody(),"text/text");
                     messageBodyPart2.setDataHandler(new DataHandler(source2));
                     multipart.addBodyPart(messageBodyPart2);
                  }
                */
                // ATTACHMENTS : functionality removed.
                /*if (email.hasAttachements()) {
                 	for (Object o : email.getAttachments()) {
                 		Attachment attachment = (Attachment) o;
                        BodyPart attachmentBodyPart = new MimeBodyPart();
                        DataSource source =
                                    new ByteArrayDataSource(attachment.getFileData(),
                                            attachment.getFileType());
                        attachmentBodyPart.setDataHandler(new DataHandler(source));
                        attachmentBodyPart.setFileName(attachment.getFilename());
                        multipart.addBodyPart(attachmentBodyPart);
                        log.debug("Attached file size = " + attachment.getFileData().length);
                    }
                 }*/

                 newMessage.setContent(mp);
           }
           else {
        	   	newMessage.setText(email.getTextBody());
           }

           Address[] to = newMessage.getRecipients(MimeMessage.RecipientType.TO);
           if (to == null || to.length == 0) {
        	   throw new MessagingException("NO RECIPIENT: " + email.toString());
           }

           Thread t = new Thread(new EmailSender.ThreadedSender(newMessage));
           t.start();
    	}
    }

    /** Send the email
    *
    */
   public void sendEmailForUser( Email email ) throws AddressException, MessagingException  {
	   if (email.hasRecipients()) {
   		MimeMessage newMessage = new MimeMessage(m_session);
           newMessage.setHeader("X-Mailer", "sendhtml");

           // FROM
           ResourceBundle res = ResourceBundle.getBundle ("Config");
           if ("".equals(email.getFrom())) 
        	   
        	   email.setFrom(res.getString("email.from"));
        	   //email.setFrom("info_sicel@acha.org.ar");
           InternetAddress from = new InternetAddress( email.getFrom() );
           newMessage.setFrom( from );

           if (email.getReplyTo() != null && !email.getReplyTo().equals("")) {
           	InternetAddress replyTos[] = {new InternetAddress(email.getReplyTo())};
               newMessage.setReplyTo(replyTos);
           }

           // TO
           Vector toList = email.getToList();
           InternetAddress[] toAddr = new InternetAddress[ toList.size() ];
           for (int i=0; i < toList.size(); i++){
           	String emailAddress = (String) toList.get(i);
               if (validateEmail(emailAddress)) {
               	toAddr[i] = new InternetAddress(emailAddress);
               }
           }
           newMessage.setRecipients(Message.RecipientType.TO, toAddr);

           // CC
           Vector ccList = email.getCcList();
           InternetAddress[] ccAddr = new InternetAddress[ ccList.size() ];
           for (int i=0; i < ccList.size(); i++){
           	String emailAddress = (String) ccList.get(i);
               if (validateEmail(emailAddress)) {
               	ccAddr[i] = new InternetAddress(emailAddress);
               }
           }
           newMessage.setRecipients(Message.RecipientType.CC, ccAddr);

           // BCC
           Vector bccList = email.getBccList();
           InternetAddress[] bccAddr = new InternetAddress[ bccList.size() ];
           for (int i=0; i < bccList.size(); i++){
           	String emailAddress = (String) bccList.get(i);
               if (validateEmail(emailAddress)) {
               	bccAddr[i] = new InternetAddress(emailAddress);
               }
          }
          newMessage.setRecipients(Message.RecipientType.BCC, bccAddr);

          // SUBJECT
          newMessage.setSubject( email.getSubject() );
          newMessage.setSentDate( new java.util.Date() );

          // IF HAS ATTACHMENTS OR IS A HTML MESSAGE GENERATE A MULTIPART MESSAGE
          if ( email.isHTMLMode()) { // || email.hasAttachements() ) {
       	   MimeMultipart mp = new MimeMultipart();
           BodyPart tp1 = new MimeBodyPart();
              DataSource source = new ByteArrayDataSource(email.getTextBody()+email.getHtmlBody(),"text/html");
              // DataSource source = new ByteArrayDataSource(email.getHtmlBody(),"text/html");
              tp1.setDataHandler(new DataHandler(source));
              // tp.setContent(email.getHtmlBody(), "text/html");
              mp.addBodyPart(tp1);
              mp.setSubType("alternative");
              newMessage.setContent(mp);
          }
          else {
       	   	newMessage.setText(email.getTextBody());
          }

          Address[] to = newMessage.getRecipients(MimeMessage.RecipientType.TO);
          if (to == null || to.length == 0) {
       	   throw new MessagingException("NO RECIPIENT: " + email.toString());
          }
          
          Transport transport = m_session.getTransport("smtp");
         transport.connect();
          transport.send(newMessage);
          transport.close();
   		}
   }


    private static boolean validateEmail(String email) {
        return !(email == null || "".equals(email) || email.indexOf('@') <= 0);
    }


    public static class ThreadedSender implements Runnable {

        MimeMessage newMessage;

        public ThreadedSender(MimeMessage newMessage) {
            this.newMessage = newMessage;
        }

        public void run() {
            try {
                Transport.send(newMessage);
            } catch (javax.mail.SendFailedException e) {
                /**
                 * the org
                 */
                log.error("EmailSender error", e);
            } catch (MessagingException e) {
                log.error("EmailSender error", e);
            }
        }

    }
}


    final class Auth extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            ResourceBundle res = ResourceBundle.getBundle ("Config");
            return new PasswordAuthentication(res.getString("email.user"),res.getString("email.password"));
        }
    }