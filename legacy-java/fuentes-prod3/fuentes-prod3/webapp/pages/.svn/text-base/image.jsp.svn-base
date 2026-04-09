<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.io.*"%>
<%
byte[] image  = (byte[])session.getAttribute("image") ;
if ( image!=null ) 
  {
  ServletOutputStream out1 = response.getOutputStream();
  out1.write(image);
  }
%>
 