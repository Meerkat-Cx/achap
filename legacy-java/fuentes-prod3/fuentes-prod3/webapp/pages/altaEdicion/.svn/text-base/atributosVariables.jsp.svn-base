<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<html:html locale="true">
	<head>
    	
	   	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
    
    	<title>Atributos Variables</title>
    </head>

	<body>
		<logic:present name="messages">
			<h3>Messages:</h3>	
			<logic:iterate id="msg" name="messages">
				<bean:write name="msg"/><br>
			</logic:iterate>
		</logic:present>
		
		<html:form action="/altaAtributosVariables.do" method="post" >
		<table border="1" class="ingresarDatos" cellSpacing="0" align="center" bgcolor="#eff3e3">
		<tr><td>
				<table class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >					


					<logic:iterate name="atributosVariablesForm" property="atributos" id="item" >
					    <html:multibox name="atributosVariablesForm" property="seleccionados">
					        <%--<bean:write name="item" property="item[1].atributo.nombre"/>--%>
					    </html:multibox>
					  <bean:write name="item[1]" property="atributos[1].nombre"/>
					</logic:iterate>

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


		