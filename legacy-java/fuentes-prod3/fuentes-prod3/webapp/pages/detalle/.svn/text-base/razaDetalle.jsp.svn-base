<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/davis-tags" prefix="davisjsp" %>

<html:html locale="true">
	<head>
    	
    
    	<title>Ficha de Raza</title>
    	
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
		</script>
    </head>

	<body>
		<h1>Ficha de Raza</h1>
		
		<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
			<tbody>
				<tr valign="bottom">
					<td class="segmenthd" align="left">Información General</td>
				</tr>
			</tbody>
		</table>

		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  			<tbody>
  				<tr>
		    		<td valign="top">
		      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
		        			<tbody>
		        				<tr>
			          				<td class="cmthd"><bean:write name="razaForm" property="raza.nombre"/></td>
							       	<td class="cmthd" align="right">Identificador: <bean:write name="razaForm" property="raza.id"/></td>
							    </tr>
		      				</tbody>
		      			</table>
		      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        					<tbody>
        						<tr>
			          				<td class="travelgray" align="left" height="10" width="70"><b>Nombre de Raza</b></td>
			          				<td class="travelinfo" align="left"><bean:write name="razaForm" property="raza.nombre"/></td>
			          				<td class="travelinfo" align="left">
          								<logic:notPresent name="razaForm" property="raza.foto">
          									<html:img align="right" border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
				          				</logic:notPresent>
				          				<logic:present name="razaForm" property="raza.foto">
			    	      					<img align="right" width="99" height="99" src='imagen.do?id=<bean:write name="razaForm" property="raza.foto.id" /> '/>
								        </logic:present>
							        </td>
								</tr>
			        			
				        		<tr>
			          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>
			        			<tr>
			          				<td class="travelgray" align="left" height="10"><b>Especie</b><spacer type="block" height="1" width="1"></td>
			          				<td colspan="2" class="travelinfo" align="left">       <bean:write name="razaForm" property="raza.especie.nombre" /></td>
			          			</tr>     
			       				<tr>
			          				<td class="travelgray" align="left" height="10"></td>
						          	<td class="terminalinfo" align="left"></td>
						        </tr>
						        <tr>
			          				<td class="travelgray" align="left" height="10"><b>Es Cruza?</b><spacer type="block" height="1" width="1"></td>
			          				<td colspan="2" class="travelinfo" align="left">       <html:checkbox disabled="true" name="razaForm" property="raza.esCruza" /></td>
			          			</tr>     
			       				<tr>
			          				<td class="travelgray" align="left" height="10"></td>
						          	<td class="terminalinfo" align="left"></td>
						        </tr>
								<tr>
			          				<td class="travelgray" align="left" height="10"><b>Es Desconocido?</b><spacer type="block" height="1" width="1"></td>
			          				<td colspan="2" class="travelinfo" align="left">       <html:checkbox disabled="true" name="razaForm" property="raza.esDesconocido" /></td>
			          			</tr>     
			       				<tr>
			          				<td class="travelgray" align="left" height="10"></td>
						          	<td class="terminalinfo" align="left"></td>
						        </tr>						        
						        
						        
			        			<tr>
			          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>	        
			        			<%-- 
        						<tr>
						        	<td class="travelgray" align="left" height="10"><b>Propiedades</b><spacer type="block" height="1" width="1"></td>
						          	<td colspan="2" class="travelinfo" align="left">Links para agregar/editar/eliminar Propiedades</td>
						        </tr>               --%>
							</tbody>
						</table>           
    				</td>
  				</tr>
  				<tr align="right">
    				<td class="orange" colspan="3">
  						<a class="orange" align="right" onclick="MM_openBrWindow('buscarRaza.do?method=edicion&id=<bean:write name="razaForm" property="raza.id" />','','scrollbars=yes,resizable=yes,width=410,height=210')">Editar</a><br><spacer type="block" height="1" width="1"></td>
  					</tr>  	
			</tbody>
		</table>
		
		
	</body>
</html:html>