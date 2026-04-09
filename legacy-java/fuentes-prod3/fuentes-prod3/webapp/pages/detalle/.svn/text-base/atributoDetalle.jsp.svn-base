<%@ page import="ar.org.sicel.persistence.ValorAdmAtr" %>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:html locale="true">
	<head>
    	
    
    	<title>Ficha de Animal</title>
    	
    	<STYLE type="text/css">
			A:link {COLOR: red; TEXT-DECORATION: none}
			A:visited {COLOR: gray; TEXT-DECORATION: none}
			A:active {TEXT-DECORATION: none}
			A:hover {COLOR: blue; TEXT-DECORATION: underline} -->
		</STYLE>
		<script language="javascript">
			function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
			
			function RedireccionarConfirmado(theURL, theLabel) {
				var respuesta = confirm(theLabel)
				if (respuesta)
					window.location=theURL
			}
			
		</script>
    </head>

	<body>
	
	
<h1>
    Ficha de Atributo
</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd"><bean:write name="atributoForm" property="atributo.nombre"/></td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="atributoForm" property="atributo.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody><tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Nombre del Atributo</b></td>
          				<td class="travelinfo" align="left"><bean:write name="atributoForm" property="atributo.nombre"/></td></tr>        
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
			        <tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Valor por Defecto</b></td>
          				<td class="travelinfo" align="left"><bean:write name="atributoForm" property="atributo.valorPorDefecto.valor"/></td></tr>        
			        <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>

          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Descripción</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left"><bean:write name="atributoForm" property="atributo.descripcion"/> </td></tr>          				
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
          				
          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Valores Admitidos</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
  							
  							<div class="justify">
    							<display:table id="row" name="atributoForm.atributo.valoresAdmitidos" align="center" class="its" >
        						
        						<display:column property="id" title="ID" />
        						<display:column property="valor" title="Valor" />       						
        						<display:column title="Acción"><a class="orange" onclick="RedireccionarConfirmado('bajaValorAdmitido.do?id=<bean:write name="atributoForm" property="id" />&valorAdmitidoId=<%= ((ValorAdmAtr)row).getId()%>','¿Esta Ud. seguro de eliminar ?')">Borrar</a><br><spacer type="block" height="1" width="1"></td></tr>
        						</display:column>
        						<display:setProperty name="basic.msg.empty_list" >
            						<bean:message key="displayTag.basic.msg.empty_list" />
        						</display:setProperty>
    							</display:table>
							</div>				
  							
  							</td></tr>
          			<tr>
          				<td class="travelgray" align="left" height="10"><spacer type="block" height="1" width="1"></td>
          				<td colspan="2" class="travelinfo" align="center">          					
	          				<html:form action="/altaValorAdmitido.do" method="get">
          						<html:hidden name="atributoForm" property="id"/>   
          						<b>Valor:</b>       						
          						<html:text name="atributoForm" property="valorAdmitido"/>
          						<html:submit/>
							</html:form>          				
          				</td></tr>	
          				
      </tbody></table>           
    </td>
  </tr>    
  <tr align="right">
	<td class="orange" colspan="3">
	<a class="orange" align="right" onclick="MM_openBrWindow('buscarAtributo.do?esEdicion=true&id=<bean:write name="atributoForm" property="atributo.id"/>','','scrollbars=yes,resizable=yes,width=420,height=200')">Editar</a><br><spacer type="block" height="1" width="1"></td></tr>  	  	
</tbody></table>

</body>

</html:html>

