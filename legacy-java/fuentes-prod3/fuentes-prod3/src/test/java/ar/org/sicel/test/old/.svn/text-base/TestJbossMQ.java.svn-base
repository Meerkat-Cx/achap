//package ar.org.sicel.test.old;
//
//
//import java.io.File;
//
//import javax.jms.JMSException;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
//import javax.jms.TextMessage;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
///*
// * Created on 10/04/2005
// */
//
///**
// * @author pablo
// */
//public class TestJbossMQ {
//
//	  QueueConnection conn = null;
//	  QueueSession session = null;
//	  Queue queue = null;
//	    
//	    public void setupPubSub()
//	        throws JMSException, NamingException
//	    {
//	        InitialContext iniCtx = new InitialContext();
//	        Object tmp = iniCtx.lookup("ConnectionFactory");
//	        QueueConnectionFactory tcf = (QueueConnectionFactory) tmp;
//	        conn = tcf.createQueueConnection();
//	        queue = (Queue) iniCtx.lookup("queue/Lotes");
//	        session = conn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
//	        conn.start();
//	    }
//	    
//	    public void sendAsync(String text)
//	        throws JMSException, NamingException
//	    {
//	        System.out.println("Begin sendAsync");
//	        // Setup the pub/sub connection, session
//	        setupPubSub();
//	        // Send a text msg
//	        QueueSender send = session.createSender(queue);
//	        TextMessage tm = session.createTextMessage(text);
//	        send.send(tm);
//	        System.out.println("sendAsync, sent text=" +  tm.getText());
//	        send.close();
//	        System.out.println("End sendAsync");
//	    }
//	    
//	    public void stop() 
//	        throws JMSException
//	    {
//	        conn.stop();
//	        session.close();
//	        conn.close();
//	    }
//	    
//	    public static void main(String args[]) 
//	        throws Exception
//	    {
//	        System.out.println("Begin TopicSendClient, now=" + 
//			                   System.currentTimeMillis());
//	        TestJbossMQ client = new TestJbossMQ();
//	        File f = new File("xml/instance.xml");
//	        
//		    client.sendAsync("A text msg, now="+System.currentTimeMillis());
//	        client.stop();
//	        System.out.println("End TopicSendClient");
//	        System.exit(0);
//	    }
//}
