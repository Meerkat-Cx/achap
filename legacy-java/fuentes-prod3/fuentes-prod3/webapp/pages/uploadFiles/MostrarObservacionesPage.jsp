<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<html:html>
	<html:form  action="/uploadFileAction" enctype="multipart/form-data">
		<input type="hidden" name="obs"	value="<c:out value="${obs}"/>" />
		<head>
			<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
			<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
			<META http-equiv=Cache-Control content=no-cache>
			<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
			<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		</head>
		<body>
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				<tr> 
	    	   		<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
	        			<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;
							Observaciones del Lote
	            	</td>
	        	</tr>
	        </table>
	        <br>
			<textarea rows="15" cols="50" readonly="readonly">
				<c:out value="${obs}">
				</c:out>
			</textarea>
		</body>
	</html:form>
</html:html>