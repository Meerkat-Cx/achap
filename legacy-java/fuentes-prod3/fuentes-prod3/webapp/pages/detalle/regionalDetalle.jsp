<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>

<html:html locale="true">
<html:javascript formName="entidadRegionalForm"/>
	<head>
    	
    
    	<title>Ficha de Regional</title>
    	
    	<STYLE type="text/css">
			A:link {COLOR: red; TEXT-DECORATION: none}
			A:visited {COLOR: gray; TEXT-DECORATION: none}
			A:active {TEXT-DECORATION: none}
			A:hover {COLOR: blue; TEXT-DECORATION: underline} -->
		</STYLE>

		<html:javascript formName="entidadRegionalForm"/>
		
		<script language="javascript">
			function RedireccionarConfirmado(theURL, theLabel) {
				var respuesta = confirm(theLabel)
				if (respuesta)
					window.location=theURL
			}

			function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
			function actualizar()  {				
				return validateEntidadRegionalForm(document.forms[0]);
			}

			function cancelar() {
				var mUrl = "buscarRegional.do?validar=false";
				window.location.href = mUrl;						
			}
		</script>
		
    </head>

	<body>

		<h1 class="Titulo">Ficha de Regional</h1>

		<html:form action="/altaEntidadRegional.do" method="post" enctype="multipart/form-data" onsubmit="return actualizar();">

			<html:hidden name="entidadRegionalForm" property="id"/>
			<html:hidden name="entidadRegionalForm" property="esEdicion" value="true"/>
			<html:hidden property="method" value="updateEntidadRegional"/>


		<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
			<tbody>
				<tr valign="bottom">
					<td class="SubTitulo" align="left">Información General</td>
				</tr>
			</tbody>
		</table>

		<table align="center" class="bordeGris" cellpadding="1" cellspacing="0" width="468">
  			<tbody>
  				<tr>
		    		<td valign="top">
		      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
		        			<tbody>
		        				<tr>
			          				<td class="TextoNegro"><bean:write name="entidadRegionalForm" property="contacto.nombreContacto"/></td>
							       	<td class="TextoNegro" align="right">Identificador: <bean:write name="entidadRegionalForm" property="contacto.id"/></td>
							    </tr>
		      				</tbody>
		      			</table>
		      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        					<tbody>
        						<tr>
			          				<td class="celdaLabel" align="left" height="10" width="70"><b>Nombre Completo</b></td>
			          				<td class="celdaInput" align="left" colspan="2">
			          					<!-- input de nombre de la entidad regional -->
			          					<html:text name="entidadRegionalForm" property="contacto.nombreContacto" />
			          				</td>
								</tr>
								
								<tr>
			          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>
								
								<tr> 
								<TD class="celdaLabel">
									<b>Foto/Escudo:</b>
								</TD>				
								<TD class="celdaInput" ><html:file  property="foto" size="36" styleClass="Input100porc"/></td>
								<td  align="left" class="celdaInput">
          								<logic:notPresent name="entidadRegionalForm" property="contacto.foto">
          									<html:img align="right" border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
				          				</logic:notPresent>
				          				<logic:present name="entidadRegionalForm" property="contacto.foto">
			    	      					<img align="right" width="99" height="99" src='imagen.do?id=<bean:write name="entidadRegionalForm" property="contacto.foto.id" /> '/>
								        </logic:present>
							    </td>
								
								
								</tr>	
			        			
				        		<tr>
			          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>
			        			<tr height="100">
			          				<td class="celdaLabel" align="left" height="10"><b>Comentarios</b><spacer type="block" height="1" width="1"></td>
			          				<td colspan="2" class="celdaInput" align="left" height="100">       
			          					<!-- entrada para el comentario -->
			          					<html:textarea name="entidadRegionalForm" property="contacto.comentario" rows="5" cols="70"/>
			          				</td>
			          			</tr>     
			       				<tr>
			          				<td class="celdaLabel" align="left" height="10"></td>
						          	<td class="celdaLabel" align="left"></td>
						          	<td class="celdaLabel"></td>
						        </tr>
						        <tr>
			          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>
			        			<tr>
			          				<td colspan="3" align="left" class="celdaLabel" height="1"><spacer type="block" height="1" width="1"></td>
			          			</tr>	        
			        			<tr>	
						          	<td class="celdaLabel" align="left" height="10"><b>Lugares de <br> Contacto</b><spacer type="block" height="10" width="1"></td>
			          				<td colspan="2" class="celdaLabel" align="left">			          
					
										<table class="bordeGris" align="center" border="0" cellpadding="1" cellspacing="0" width="468" >
    	    								<tbody>
    	    									<tr>
			        	  							<td colspan="2" background="pages/assets/images/greystrip.jpg" height="10"><spacer type="block" height="8" width="1"></td>
			        	  						</tr>			
			        	  							<tr>	
					         							<logic:notEmpty name="entidadRegionalForm" property="contacto.ubicacions">
	        												<logic:iterate name="entidadRegionalForm" property="contacto.ubicacions" id="ubicacion">    	    	
	    	    												<tr>          										
					          										<td align="center" bgcolor="#cccccc" height="100%" valign="top" width="100%">
            															<table bgcolor="#ffffff" border="0" cellpadding="5" cellspacing="0" height="100%" width="100%">
              																<tbody>
              																	<tr height="130">
						                											<td valign="top">
                  																		<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
                    																		<tbody>
                    																			<tr>
					                      															<td colspan="2" class="celdaLabel"><b>Ref: <bean:write name="ubicacion" property="nombre"/></b></td>
					                      														</tr>
							   		                 											<tr>
        	              																			<td class="celdaLabel"><b>&nbsp;&nbsp;&nbsp;&nbsp;Dirección</b></td>
            	          																			<td class="celdaInput"><bean:write name="ubicacion" property="direccion"/> - <bean:write name="ubicacion" property="ciudad"/> (CP <bean:write name="ubicacion" property="codigoPostal"/>) - <bean:write name="ubicacion" property="provinciaRegion"/> - <bean:write name="ubicacion" property="pais"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    																			<tr>
                      																				<td class="celdaLabel"><b>&nbsp;&nbsp;&nbsp;&nbsp;Teléfono(s)</b></td>
                      																				<td class="celdaInput"><bean:write name="ubicacion" property="telefono"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                    																			<tr>
                      																				<td class="celdaLabel"><b>&nbsp;&nbsp;&nbsp;&nbsp;eMail(s)</b></td>
                      																				<td class="celdaInput"><bean:write name="ubicacion" property="mail"/><spacer type="block" height="1" width="1"> <spacer type="block" height="1" width="1"></td></tr>
                   																				<tr align="right">
												                      								<td class="orange" colspan="2">
                        																				<a class="Botones" onclick="MM_openBrWindow('buscarUbicacion.do?esEdicion=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','','scrollbars=yes,resizable=yes,width=430,height=280')">Editar</a> |<spacer type="block" height="1" width="1">                        														
                        																				<a class="Botones" onclick="RedireccionarConfirmado('bajaUbicacion.do?redirectURI=buscarRegional.do\?idActivo=true&ubicacionId=<bean:write name="ubicacion" property="id"/>','¿Esta Ud. seguro de eliminar <bean:write name="ubicacion" property="nombre"/>?')">Borrar</a><br><spacer type="block" height="1" width="1"></td></tr>
                    																		</tbody>
                  																		</table>
                  																	</td>
                  																</tr>
            																</tbody>
            															</table>
            														</td>
            													</tr>           				
						        							</logic:iterate>
			      										</logic:notEmpty>			      									
				      								</tr>
			      								<tr>
			      									<td align="right" class="celdaLabel"><a class="Botones" onclick="MM_openBrWindow('buscarUbicacion.do?contactoId=<bean:write name="entidadRegionalForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=530,height=380')">Agregar</a><br><spacer type="block" height="1" width="1"></td>
			      								</tr>
			      							</tbody>
			      						</table>
			      					</td>
			      				</tr>      	 						

								<%-- 
						        <tr>
						        	<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>					       
        						<tr>
						        	<td class="travelgray" align="left" height="10"><b>Propiedades</b><spacer type="block" height="1" width="1"></td>
						          	<td colspan="2" class="travelinfo" align="left">Links para agregar/editar/eliminar Propiedades</td>
						        </tr>               
						        --%>
							</tbody>
						</table>           
    				</td>
  				</tr>
  				<tr>
			    		<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td>
			    </tr>
  				
  				<tr align="right" class="celdaLabel">
    				<td class="orange" colspan="3" align="left">
  						<!-- <a class="Botones" align="left" onclick="MM_openBrWindow('buscarRegional.do?esEdicion=true&id=<bean:write name="entidadRegionalForm" property="contacto.id" />','','scrollbars=yes,resizable=yes,width=500,height=300')">Editar</a><br><spacer type="block" height="1" width="1"></td> -->
  						<!--<input type="Button" class="Botones" name="Actualizar" onclick="actualizar();" value="Actualizar"/>-->
						<html:submit styleClass="botones" />
						<input type="button" class="Botones" value="Cancelar" onClick="cancelar();"/>
					</td>
					

  					</tr>  	
			</tbody>
		</table>





  		<div><br><br></div> 

		<table align="center" class="bordeGris" cellpadding="0" cellspacing="0" width="468">
  			<tbody>
  				<tr valign="bottom">
				    <td class="SubTitulo" align="left">Entidades de Control Lechero Asociadas</td>
  				</tr>
			</tbody>
		</table>
		<table align="center" class="bordeGris" cellpadding="1" cellspacing="0" width="468">
			<tbody>
				
					       		<tr>
						        	<td class="celdaLabel">
							          	<bean:write name="entidadRegionalForm" property="contacto.nombreContacto"/>            
          							</td>
          							<td class="celdaLabel" align="right">
							          	Identificador: <bean:write name="entidadRegionalForm" property="contacto.id"/>
							        </td>
						        </tr>
        						<tr><td colspan="2">
								
								    <display:table name="entidadRegionalForm.contacto.eclos" align="center" class="its" width="100%" id="table1" >
								        <display:column property="id"
					                        title="ID" />                        
								        <display:column property="nombreContacto" 
				        					title="Nombre" 
        									href="buscaEclo.do?idActivo=true" 
					        				paramId="id"
					                        paramProperty="id" />
								        <display:setProperty name="basic.msg.empty_list" >
								        </display:setProperty>
								    </display:table>
								
								</td></tr>							
			</tbody>
		</table>

	</html:form>	

	</body>
</html:html>

