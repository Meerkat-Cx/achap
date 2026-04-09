
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    
    <META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
		<title>Edición de Animal</title>
  </head>
  
  <body>
  	<html:form action="/edicionAnimal.do" method="post" enctype="multipart/form-data" > <%--onsubmit="return validateEntidadRegionalForm(this);">	--%>    	
    	<html:hidden name="animalForm" property="animalId"/>
    	<table border="1" class="ingresarDatos" cellSpacing="0" align="center" >
			<tr><td>
    		<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
				<tr>
					<td class="tablecaption" colspan="2">
    		            Animal: (Id:<bean:write name="animalForm" property="animal.id"/>)
					</td>
				</tr>
			
				<TR>
					<TD>
						Foto: 
					</TD>				
					<TD><html:file size="22" property="foto"/></td>	
				</TR>
				<TR><TD><br></td></tr>
				<TR>
					<td colspan="2" align="center" >
						<html:submit/>
						<html:cancel/>
					</TD>
				</TR>	
			</table>			
		</td></tr></table>
	</html:form>
  </body>
</html:html>
