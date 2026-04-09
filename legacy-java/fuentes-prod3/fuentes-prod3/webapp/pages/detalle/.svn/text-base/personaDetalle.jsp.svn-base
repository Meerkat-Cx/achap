<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:html locale="true">
	<head>
    	
    	
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
	<bean:write name="personaForm" property="headerText"/>
</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd"><bean:write name="personaForm" property="contacto.nombreContacto"/></td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="personaForm" property="contacto.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody>
        			
        			<logic:equal name="personaForm" property="mostrarEmpresa" value="true">
        			<tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Empresa de Software</b></td>
          				<td class="travelinfo" align="left"><bean:write name="personaForm" property="contacto.nombreContacto"/></td></tr>
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
        			</logic:equal>
        			
        			
        			<tr>
          				<td class="travelgray" align="left" height="10"><b>Nombre Completo</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
          					<bean:write name="personaForm" property="contacto.nombrePersona" /> 
          					<bean:write name="personaForm" property="contacto.apellido" />         				
          				</td>  
          				
          				<logic:equal name="personaForm" property="mostrarEmpresa" value="false">
          					<td class="travelinfo" align="left">
          					<logic:notPresent name="personaForm" property="contacto.foto">
          						<html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
          					</logic:notPresent>
          					<logic:present name="personaForm" property="contacto.foto">
          						<img src='imagen.do?id=<bean:write name="personaForm" property="contacto.foto.id" /> '/>
				        	</logic:present>
				        	</td>
				        </logic:equal></tr>
          				
       				<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			
        			<tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Documento</b></td>
          				<td class="travelinfo" align="left">
          					<bean:write name="personaForm" property="contacto.tipoDocumento"/>
          					<bean:write name="personaForm" property="contacto.documento"/>
          				</td></tr>        
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>	
					<tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Comentarios</b></td>
          				<td class="travelinfo" align="left">
          					<bean:write name="personaForm" property="contacto.comentario"/> 				
          				</td></tr>        
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>	          				
          				
			        
        			<tr>
			          	<td class="travelgray" align="left" height="10"><b>Lugares de <br> Contacto</b><spacer type="block" height="10" width="1"></td>
          				<td colspan="2" class="travelinfo" align="left">

								<table class="contentgray" align="center" border="0" cellpadding="1" cellspacing="0" width="468" >
    	    						<tbody><tr>
        	  							<td colspan="3" background="pages/assets/images/greystrip.jpg" height="10"><spacer type="block" height="8" width="1"></td></tr>			

          							<logic:notEmpty name="personaForm" property="contacto.ubicacions">
	        							<logic:iterate name="personaForm" property="contacto.ubicacions" id="ubicacion">    	    	
	    	    							<tr>          										
          										<td align="center" bgcolor="#cccccc" height="100%" valign="top" width="100%">
            										<table bgcolor="#ffffff" border="0" cellpadding="5" cellspacing="0" height="100%" width="100%">
              											<tbody><tr height="130">
                											<td valign="top">
                  												<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    												<tbody><tr>
                      													<td class="small"><b><bean:write name="ubicacion" property="nombre"/></b></td></tr>
   		                 											<tr>
        	              												<td class="small"><b>Dirección</b></td>
            	          												<td class="small"><bean:write name="ubicacion" property="direccion"/> - <bean:write name="ubicacion" property="ciudad"/> (CP <bean:write name="ubicacion" property="codigoPostal"/>) - <bean:write name="ubicacion" property="provinciaRegion"/> - <bean:write name="ubicacion" property="pais"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    												<tr>
                      													<td class="small"><b>Teléfono(s)</b></td>
                      													<td class="small"><bean:write name="ubicacion" property="telefono"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    												<tr>
                      													<td class="small"><b>eMail(s)</b></td>
                      													<td class="small"><bean:write name="ubicacion" property="mail"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                   													<tr align="right">
												                      	<td class="orange" colspan="3">
												                      	
												                      		<a class="orange" onclick="MM_openBrWindow('buscarUbicacion.do?esEdicion=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','','scrollbars=yes,resizable=yes,width=430,height=280')">Editar</a><br><spacer type="block" height="1" width="1">                        														
												                      		
												                      		<logic:equal name="personaForm" property="mostrarEmpresa" value="true">                        														
                        														<a class="orange" onclick="RedireccionarConfirmado('bajaUbicacion.do?redirectURI=buscarResponsableSistema.do\?idActivo=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','¿Esta Ud. seguro de eliminar <bean:write name="ubicacion" property="nombre"/>?')">Borrar</a><br><spacer type="block" height="1" width="1">
                        													</logic:equal>	
                        													<logic:equal name="personaForm" property="mostrarEmpresa" value="false">
                        														<a class="orange" onclick="RedireccionarConfirmado('bajaUbicacion.do?redirectURI=buscarResponsableEclo.do\?idActivo=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','¿Esta Ud. seguro de eliminar <bean:write name="ubicacion" property="nombre"/>?')">Borrar</a><br><spacer type="block" height="1" width="1">
                        													</logic:equal>
                        														
                        												</td></tr>
                        														
                    												</tbody>
                  												</table></td></tr>
            											</tbody>
            										</table></td></tr>           				
						        		</logic:iterate>
			      					</logic:notEmpty>
			      					<tr><td align="right"><a class="orange" onclick="MM_openBrWindow('buscarUbicacion.do?contactoId=<bean:write name="personaForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=430,height=280')">Agregar</a><br><spacer type="block" height="1" width="1">      </td></tr>
			      					</tbody>
			      				</table>
        					
       
        <tr>
        	<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
		<%--	       
        <tr>
          <td class="travelgray" align="left" height="10"><b>Propiedades</b><spacer type="block" height="1" width="1"></td>
          <td class="travelinfo" align="left">
          	Links para agregar/editar/eliminar Propiedades</td></tr>               
          	--%>
      </tbody></table>           
    </td>
  </tr>
  <tr align="right">
    <td class="orange" colspan="3">
    <logic:equal name="personaForm" property="mostrarEmpresa" value="true">
	  	<a class="orange" align="right" onclick="MM_openBrWindow('buscarResponsableSistema.do?esEdicion=true&id=<bean:write name="personaForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=450,height=280')">Editar</a><br><spacer type="block" height="1" width="1"></td></tr>  	
	</logic:equal>
    <logic:equal name="personaForm" property="mostrarEmpresa" value="false">
	  	<a class="orange" align="right" onclick="MM_openBrWindow('buscarResponsableEclo.do?esEdicion=true&id=<bean:write name="personaForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=410,height=310')">Editar</a><br><spacer type="block" height="1" width="1"></td></tr>  	
	</logic:equal>	
</tbody></table>

</body>
</html:html>


