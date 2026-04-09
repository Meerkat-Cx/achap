<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:html locale="true">
	<head>
    	
    
    	<title>Ficha de Sistema Informático</title>
    	
    	<STYLE type="text/css">
			A:link {COLOR: red; TEXT-DECORATION: none}
			A:visited {COLOR: gray; TEXT-DECORATION: none}
			A:active {TEXT-DECORATION: none}
			A:hover {COLOR: blue; TEXT-DECORATION: underline} -->
		</STYLE>
		
		<script language="javascript">
			function RedireccionarConfirmado(theURL, theLabel) {
				var respuesta = confirm(theLabel)
				if (respuesta)
					window.location=theURL
			}

			function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
		</script>
		
    </head>

	<body>

	<h1>Ficha de Sistema Informático</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd"><bean:write name="sistemaForm" property="sistema.nombre"/></td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="sistemaForm" property="sistema.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody><tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Nombre del Sistema</b></td>
          				<td class="travelinfo" align="left"><bean:write name="sistemaForm" property="sistema.nombre"/> (Versión
          													<bean:write name="sistemaForm" property="sistema.version"/>)</td></tr>        
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
        			
        			<tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Responsable del Sistema</b></td>
						<td class="travelinfo" align="left"><html:link action="/buscarResponsableSistema.do" paramId="id" paramName="sistemaForm" paramProperty="sistema.responsable.id" >
							<bean:write name="sistemaForm" property="sistema.responsable.nombrePersona"/>
							<bean:write name="sistemaForm" property="sistema.responsable.apellido"/></html:link></td></tr>        
					<tr>
          				<td class="travelgray" align="left" height="10"></td>
          				<td align="right"><a class="orange" align="right" onclick="MM_openBrWindow('buscarResponsableSistema.do?esEdicion=true&id=<bean:write name="sistemaForm" property="sistema.responsable.id" />','','scrollbars=yes,resizable=yes,width=450,height=280')">Editar</a><br><spacer type="block" height="1" width="1"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
					
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
        			
					<tr>        			
          				<td class="travelgray" align="left" height="10"><b>Comentarios</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">       <bean:write name="sistemaForm" property="sistema.comentario" /></td></tr>     
       				<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>      
      			</tbody></table>           
    			</td>
    		</tr>
  			<tr align="right">
			    <td class="orange" colspan="3">
				  	<a class="orange" align="right" onclick="MM_openBrWindow('buscarSistema.do?esEdicion=<%=new Boolean(true)%>&id=<bean:write name="sistemaForm" property="sistema.id" />','','scrollbars=yes,resizable=yes,width=450,height=200')">Editar</a><br><spacer type="block" height="1" width="1"></td></tr>  	
		</tbody>
		</table>
	</body>



</html:html>
