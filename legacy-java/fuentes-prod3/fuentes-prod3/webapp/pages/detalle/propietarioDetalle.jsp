<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:html locale="true">
	<head>
    	
    
    	<title>Ficha de Propietario</title>
    	
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
    Ficha de Propietario 
    <logic:present name="propietarioForm" property="contacto.socio">
    	(Socio A.C.H.A)
    </logic:present>
</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd"><bean:write name="propietarioForm" property="contacto.nombreContacto"/></td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="propietarioForm" property="contacto.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody><tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Nombre Completo</b></td>
          				<td class="travelinfo" align="left"><bean:write name="propietarioForm" property="contacto.nombreContacto"/></td>        
          				<td class="travelgray" align="center" height="10" width="70"><b>Persona Fisica</b><html:checkbox disabled="true" name="propietarioForm" property="contacto.esPersonaFisica" /></td></tr>

        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
              				
					<tr>	
						<td class="travelgray" align="left" height="10"><b>Identificadores Externos</b><spacer type="block" height="10" width="1"></td>
			          	<td colspan="2" class="travelinfo" align="left">	
							<table class="contentgray" align="center" border="0" cellpadding="1" cellspacing="0" width="468" >
   								<tbody>
  									<tr>
	       	  							<td colspan="2" background="pages/assets/images/greystrip.jpg" height="10"><spacer type="block" height="8" width="1"></td>
		  	  						</tr>			
		  							<tr>
		  								<td align="center" bgcolor="#cccccc" height="100%" valign="top" width="100%">
            								<table bgcolor="#ffffff" border="0" cellpadding="5" cellspacing="0" height="100%" width="100%">
              									<tbody>
              										<tr height="130">
						       							<td valign="top">
                  											<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    											<tbody>                      											                  																			
							   		                 				<tr>
        	              												<td width="50" class="small"><b>#Socio ACHA</b></td>
            	          												<td class="small"><bean:write name="propietarioForm" property="contacto.socio" /><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td>            	          												
            	          											</tr>
                    												<tr>
                      													<td class="small"><b>#Holando Arg. Reg.</b></td>
                      													<td class="small"><bean:write name="propietarioForm" property="contacto.har" /><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    												<tr>
                      													<td class="small"><b>#Soc. Rural Arg.</b></td>
                      													<td class="small"><bean:write name="propietarioForm" property="contacto.sra" /><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                      												<tr>
                      													<td class="small"><b>#Antigua Denominación</b></td>
                      													<td class="small">
                      														<bean:write name="propietarioForm" property="contacto.s1Eclo"/> -
																	    	<bean:write name="propietarioForm" property="contacto.s1Prop"/>	
																	    	<spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
																	
																</tbody>
                  											</table>
           												</td>
           											</tr>
       											</tbody>
            								</table>
 										</td>
            						</tr>           										        										      												      					
			      				</tbody>
			      			</table>
						</td>
			      	</tr>           				      			
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
          				
        			<tr>
          				<td class="travelgray" align="left" height="10"><b>Comentarios</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">       <bean:write name="propietarioForm" property="contacto.comentario" /></td></tr>     
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

          							<logic:notEmpty name="propietarioForm" property="contacto.ubicacions">
	        							<logic:iterate name="propietarioForm" property="contacto.ubicacions" id="ubicacion">    	    	
	    	    							<tr>          										
          										<td align="center" bgcolor="#cccccc" height="100%" valign="top" width="100%">
            										<table bgcolor="#ffffff" border="0" cellpadding="5" cellspacing="0" height="100%" width="100%">
              											<tbody><tr height="130">
                											<td valign="top">
                  												<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    												<tbody><tr>
                      													<td colspan="2" class="small"><b>Ref: <bean:write name="ubicacion" property="nombre"/></b></td></tr>
   		                 											<tr>
        	              												<td class="small"><b>&nbsp;&nbsp;&nbsp;&nbsp;Dirección</b></td>
            	          												<td class="small"><bean:write name="ubicacion" property="direccion"/> - <bean:write name="ubicacion" property="ciudad"/> (CP <bean:write name="ubicacion" property="codigoPostal"/>) - <bean:write name="ubicacion" property="provinciaRegion"/> - <bean:write name="ubicacion" property="pais"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    												<tr>
                      													<td class="small"><b>&nbsp;&nbsp;&nbsp;&nbsp;Teléfono(s)</b></td>
                      													<td class="small"><bean:write name="ubicacion" property="telefono"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    												<tr>
                      													<td class="small"><b>&nbsp;&nbsp;&nbsp;&nbsp;eMail(s)</b></td>
                      													<td class="small"><bean:write name="ubicacion" property="mail"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                   													<tr align="right">
												                      	<td class="orange" colspan="3">
                        														<a class="orange" onclick="MM_openBrWindow('buscarUbicacion.do?esEdicion=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','','scrollbars=yes,resizable=yes,width=430,height=280')">Editar</a> |<spacer type="block" height="1" width="1">                        														
                        														<a class="orange" onclick="RedireccionarConfirmado('bajaUbicacion.do?redirectURI=buscarPropietario.do\?idActivo=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','¿Esta Ud. seguro de eliminar <bean:write name="ubicacion" property="nombre"/>?')">Borrar</a></td></tr>
                    												</tbody>
                  												</table></td></tr>
            											</tbody>
            										</table></td></tr>           				
						        		</logic:iterate>
			      					</logic:notEmpty>
			      					<tr><td align="right"><a class="orange" onclick="MM_openBrWindow('buscarUbicacion.do?contactoId=<bean:write name="propietarioForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=430,height=280')">Agregar</a><br><spacer type="block" height="1" width="1">      </td></tr>
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
  	<a class="orange" align="right" onclick="MM_openBrWindow('buscarPropietario.do?esEdicion=true&id=<bean:write name="propietarioForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=430,height=270')">Editar</a><br><spacer type="block" height="1" width="1"></td></tr>  	  	
</tbody></table>





 <div><br><br><br><br></div> 

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Establecimientos Asociados</td></tr>
  </tbody></table>

<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  <tbody>
  <tr>
    <td valign="top">    
      <table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        <tbody><tr>
          <td class="cmthd">
          	<bean:write name="propietarioForm" property="contacto.nombreContacto"/></td>
          <td class="cmthd" align="right">Identificador: <bean:write name="propietarioForm" property="contacto.id"/></td>
          </tr>
          </td>
        </tbody>
      </table>
	<tr>
	<td>
	
    <display:table name="propietarioForm.contacto.establecimientos" align="center" class="its" width="100%" id="table1" >
       
        <display:column property="id"
                        title="ID"
                        href="buscarEstablecimiento.do?idActivo=true"
                        paramId="id"
                        paramProperty="id" />
        <display:column property="nombreContacto" title="Nombre" />
        <display:setProperty name="basic.msg.empty_list" >
        </display:setProperty>
    </display:table>
</td></tr></tbody></table>	



 <div><br><br><br><br></div> 

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Animales
      
    </td>
  </tr>
</tbody></table>

<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  <tbody>
  <tr>
    <td valign="top">
    
      <table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        <tbody><tr>
          <td class="cmthd">
          	<bean:write name="propietarioForm" property="contacto.nombreContacto"/>            
          </td>

          <td class="cmthd" align="right">
          	Identificador: <bean:write name="propietarioForm" property="contacto.id"/>
          </td>
     </tr>	
	 </tbody>
	</table>
	</td>
	</tr>					        	
       	<tr>
	  	<td>
    <display:table name="propietarioForm.contacto.animals" pagesize="30" sort="page" align="center" class="its" width="100%" requestURI="/buscarPropietario.do" id="table2" >
  
        <display:column property="id"
                        title="ID"
                        sortable="true"
                        headerClass="sortable"
                        href="buscarAnim.do?method=porId"
                        paramId="animalId"
                        paramProperty="id" />
        <display:column property="nombre" title="Nombre" />
        <display:column property="RP"
                        title="RP"
                        sortable="true"
                        headerClass="sortable"
                        group="1" />
        <display:column property="fechaNac"
                        title="Fecha Nac."
                        group="2" />
        <display:setProperty name="basic.msg.empty_list" >
        </display:setProperty>
    </display:table>
</td></tr></tbody></table>	

 <div><br><br><br><br></div> 

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Eventos de Transferencias
      
    </td>
  </tr>
</tbody></table>

<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  <tbody>
  <tr>
    <td valign="top">
    
      <table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        <tbody><tr>
          <td class="cmthd">
          	<bean:write name="propietarioForm" property="contacto.nombreContacto"/>            
          </td>

          <td class="cmthd" align="right">
          	Identificador: <bean:write name="propietarioForm" property="contacto.id"/>
          </td>
        </tr>	
	  </tbody>
	</table>
	</td>
	</tr>					        	
       	<tr>
	      	<td>
    <display:table name="propietarioForm.contacto.evtTransferencias" align="center" class="its" width="100%" id="table3" >
       
        <display:column property="id"
                        title="ID"
                        href="construction.do"
                        paramId="evtTransferenciasId"
                        paramProperty="id" />
        <display:column property="id" title="ID" />
        <display:column property="fecha" title="Fecha" />
        <display:column property="nuevoRP" title="Nuevo RP" />
        <display:column property="nuevoProp" title="Nuevo Prop." />
        <display:column property="nuevoEstab" title="Nuevo Estab." />
        <display:column property="animal.id"
                        title="Animal ID"
                        href="animalDetalle.do"
                        paramId="animalId"
                        paramProperty="animal.id" />
        <display:setProperty name="basic.msg.empty_list" >        	
        </display:setProperty>
    </display:table>
</td></tr></tbody></table>	



</body>

</html:html>