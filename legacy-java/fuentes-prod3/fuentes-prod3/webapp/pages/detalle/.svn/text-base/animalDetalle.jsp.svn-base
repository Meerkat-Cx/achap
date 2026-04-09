<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/davis-tags" prefix="davisjsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:html locale="true">
	<head>
    	
    
    	<title>Ficha de Animal</title>
    	
		<script language="javascript">
			function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
		</script>
    </head>

	<body>
	
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>            	
            		
	                   		&nbsp;Ficha del Animal
            	</font>
            </td>
            <td><div align=right></div></td>
        </tr>
        <tr> 
        	<td class=texto4 colSpan=2>
            	<div align=right> 
                	<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    	<tr> 
                        	<td width="30%" bgColor=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            <td width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></td>
                        </tr>
                    </table>
               </div>
            </td>
        </tr>
	</table>
	<br/>
	
		

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Información General
            </td>
        </tr>
        </table>

	<br/>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">
  				<tr>
         				<td class="TextoVerde"><bean:write name="animalForm" property="animal.nombre"/></td>
			          	<td class="TextoVerde" align="right">Identificador: <bean:write name="animalForm" property="animal.id"/></td>
		       </tr>
		</table>

		<br/>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">
				<tr>
          							<td class="TextoVerde" align="left" height="10" width="70"><b>Nombre Completo</b></td>
          							<td class="TextoVerde" align="left"><bean:write name="animalForm" property="animal.nombre"/></td>
          				
          							<td class="TextoVerde" align="right">
          								<logic:notPresent name="animalForm" property="animal.foto">
				          					<html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
          								</logic:notPresent>          				
				          				<logic:present name="animalForm" property="animal.foto">
          									<a class="orange" align="right" onclick="MM_openBrWindow('imagen.do?id=<bean:write name="animalForm" property="animal.foto.id" />','','scrollbars=yes,resizable=yes,width=512,height=383')">
          										<img width="99" height="99" src='imagen.do?id=<bean:write name="animalForm" property="animal.foto.id" /> '/>
          									</a>          									
								        </logic:present>
							        </td>
				</tr>
 
				<tr>
						<td class="TextoVerde" align="left" height="10" width="70"><b>Categoría</b></td>
						<td class="TextoVerde" align="left"><bean:write name="animalForm" property="animal.categoria"/></td>
          				
						<td class="TextoVerde" align="right">
									<a class="orange" align="right" onclick="MM_openBrWindow('buscarAnim.do?method=edicion&animalId=<bean:write name="animalForm" property="animal.id" />','','scrollbars=yes,resizable=yes,width=280,height=110')">Editar Foto</a>
						</td>								
				</tr>  	
		</table>
<br/>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">																
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>RP</b></td>
									<td class="TextoVerde" align="left"><bean:write name="animalForm" property="animal.RP"/></td>
								</tr>
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Fecha de Nacimiento</b></td>
									<td class="TextoVerde" align="left">
										<bean:define id="_animal" name="animalForm" property="animal" />                    
											<% String fechaDeNacimiento= String.format("%tF", new Object[]{((ar.org.sicel.persistence.Animal)_animal).getFechaNac()}); %>                    
                							<%=fechaDeNacimiento%>         	 
									</td>
								</tr>
								<tr>	
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>
								<tr>
									 <td class="TextoVerde" align="left" height="10" width="70"><b>Dado de Baja?</b></td>
									 <td class="TextoVerde" align="left"><html:checkbox disabled="true" name="animalForm" property="animal.esBaja" /></td>
								</tr>          	
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Categoría V3</b></td>
									<td class="TextoVerde" align="left"><bean:write name="animalForm" property="animal.categoriaV3"/></td>
								</tr>          	
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>
		</table>
<br/>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        											
			          			<tr>
          							<td class="TextoVerde" align="left" height="10"><b>Propietario</b><spacer type="block" height="1" width="1"></td>
          							<td class="TextoVerde" align="left">
				          				<logic:present name="animalForm" property="animal.propietario">
                				            <html:link page="/buscarPropietario.do?idActivo=true" paramId="id" paramName="animalForm" paramProperty="animal.propietario.id" >
                    				            <bean:write name="animalForm" property="animal.propietario.nombreContacto"/>
				                            </html:link>
				                        </logic:present>
                        				<logic:notPresent name="animalForm" property="animal.propietario">
				                            <td colspan="3" class="TextoVerde" align="left">No Disponible</td>
                				        </logic:notPresent>
                				 	</td>
          						</tr>          				
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>       			
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Establecimiento</b><spacer type="block" height="1" width="1"></td>
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.establecimiento">
											<html:link page="/buscarEstablecimiento.do?idActivo=true" paramId="id" paramName="animalForm" paramProperty="animal.establecimiento.id" >
												<bean:write name="animalForm" property="animal.establecimiento.id"/>
												-
												<bean:write name="animalForm" property="animal.establecimiento.nombreContacto"/>
											</html:link>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.establecimiento">
											<td class="TextoVerde" align="left">No Disponible</td>
										</logic:notPresent> 
									</td>         				
								</tr>
</table>
<br/>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        				
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Padre</b><spacer type="block" height="1" width="1"></td>          				
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.padre" >
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.padre.id" >                                
												<bean:write name="animalForm" property="animal.padre.regIdentificador"/>
												-
												<bean:write name="animalForm" property="animal.padre.nombre"/>
											</html:link>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.padre" >
											<bean:message key="dataNotFound" />
										</logic:notPresent>
									</td>
								</tr>    				                    
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>			          
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Madre Genética</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">                 				
										<logic:present name="animalForm" property="animal.madreGenetica">
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.madreGenetica.id" >
												<bean:write name="animalForm" property="animal.madreGenetica.regIdentificador"/>
												-
												<bean:write name="animalForm" property="animal.madreGenetica.nombre"/>
											</html:link>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.madreGenetica">
											<bean:message key="dataNotFound" />
										</logic:notPresent>
									</td>
								</tr>     
								<tr>
									<td class="TextoVerde" align="left" height="10"></td>
									<td class="TextoVerde" align="left"></td>
								</tr>       			          				
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Madre Parto</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.madreParto">
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.madreParto.id" >                                
												<bean:write name="animalForm" property="animal.madreParto.regIdentificador"/>
												-
												<bean:write name="animalForm" property="animal.madreParto.nombre"/>
											</html:link>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.madreParto">
											<bean:message key="dataNotFound" />
										</logic:notPresent>
									</td>
								</tr>     
</table>
<br/>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        				          														       
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Composición Racial</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">
										<display:table name="animalForm.animal.composicionRacial.asCollection" align="center" class="its" width="100%" id="table1" >
											<display:caption>
												<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
													<TR>
														<TD>Raza Declarada: </TD>
														<TD>
															<logic:present name="animalForm" property="animal.composicionRacial.razaDeclarada" >
																<bean:write name="animalForm" property="animal.composicionRacial.razaDeclarada.nombre"/>
															</logic:present>
															<logic:notPresent name="animalForm" property="animal.composicionRacial.razaDeclarada.nombre" >
																<bean:message key="dataNotFound" />
															</logic:notPresent>
														</TD>
														</TR>
												</table>
											</display:caption>
											<display:column property="key.nombre" title="Raza" />
											<display:column property="value" title="Valor" />
											<display:setProperty name="basic.msg.empty_list" >
											</display:setProperty>
										</display:table>
									</td>
								</tr>             	
								
</table>
<br/>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Registros</b></td>
									<td colspan="2" class="TextoVerde" align="center">       	              
										<display:table name="animalForm.animal.registros" align="center" class="its" id="table2" >
											<display:caption>
												<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
													<TR>
														<td class="travelinfo" align="left">Origen: </TD>
														<td>
															<logic:present name="animalForm" property="animal.regOrigen" >
																<bean:write name="animalForm" property="animal.regOrigen"/>
															</logic:present>
															<logic:notPresent name="animalForm" property="animal.regOrigen" >
																<bean:message key="dataNotFound" />
															</logic:notPresent>
														</TD>
													</TR>			
													<TR>
														<td class="travelinfo" align="left">Identificador: </TD>
														<TD>
															<logic:present name="animalForm" property="animal.regIdentificador" >
																<bean:write name="animalForm" property="animal.regIdentificador"/>
															</logic:present>
															<logic:notPresent name="animalForm" property="animal.regIdentificador" >
																<bean:message key="dataNotFound" />
															</logic:notPresent>
														</TD>		
													</TR>
												</table>
											</display:caption>
											<display:column property="tipoRegistro.descripcion" title="Tipo" />
											<display:column property="numero" title="Numero" />
											<display:setProperty name="basic.msg.empty_list" >
												<h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
											</display:setProperty>
										</display:table>
									</td>
								</tr>			
</table>
<br/>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
			<tr>
					<td class="TextoVerde" align="left" height="10" width="70"><b>S.R.A.</b></td>
					<td class="TextoVerde" align="left">             	          	 
					<table>
							<TR>
								<TD class="TextoVerde">Propietario: </TD>
								<TD  class="TextoVerde">
									<logic:present name="animalForm" property="animal.SRAProp">
											<bean:write name="animalForm" property="animal.SRAProp"/>
									</logic:present>
									<logic:notPresent name="animalForm" property="animal.SRAProp">
											<bean:message key="dataNotFound" />
									</logic:notPresent>
								</TD>
							</TR>
							<TR>
								<TD  class="TextoVerde">Creado: </TD>
								<TD>
									<html:checkbox disabled="true" name="animalForm" property="animal.SRAEsCreado" />
								</TD>
							</TR>
							<TR>
								<TD  class="TextoVerde">De baja: </TD>
								<TD  class="TextoVerde">
									<logic:present name="animalForm" property="animal.SRACbaj">
										<bean:write name="animalForm" property="animal.SRACbaj"/>
									</logic:present>
									<logic:notPresent name="animalForm" property="animal.SRACbaj">
										<bean:message key="dataNotFound" />
									</logic:notPresent>
								</TD>
							</TR>
							<TR>
								<TD  class="TextoVerde">Fecha SB: </TD>
								<TD  class="TextoVerde">
									<logic:present name="animalForm" property="animal.SRAFesb">
										<bean:write name="animalForm" property="animal.SRAFesb"/>
									</logic:present>
									<logic:notPresent name="animalForm" property="animal.SRAFesb">
										<bean:message key="dataNotFound" />
									</logic:notPresent>
								</TD>
							</TR>			
						</table>
						</td>
				</tr>
		</table>
   
<br/><br/><br/>

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;
			<a align="right" onclick="MM_openBrWindow('buscarAnim.do?method=mostrarEventos&animalId=<bean:write name="animalForm" property="animal.id" />','','scrollbars=yes,resizable=yes,width=900,height=600,top=100,left=100')">Eventos del Animal</a>
            </td>
        </tr>
 </table>
		
<br/><br/><br/>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >		
			<tr> 
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Calificaciones del Animal
				</td>
			</tr>
		</table>
		<br/>
		
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
				<tr>
						<td class="TextoVerde"><bean:write name="animalForm" property="animal.nombre"/></td>
						<td class="TextoVerde" align="right">
							Identificador: <bean:write name="animalForm" property="animal.id"/>
						</td>
				</tr>
				<tr>
					<td class="TextoVerde" colspan="2">
						<display:table name="animalForm.animal.calificacions" align="center" class="its" width="100%" id="table4" >					
							<display:column property="id"
								title="ID"
								href="construction.do"
								paramId="calificacionId"
								paramProperty="id" />
							<display:column property="fecha" title="Fecha" />
							<display:column property="catPuntaje" title="Cat. Puntaje" />
							<display:setProperty name="basic.msg.empty_list" >
							</display:setProperty>
						</display:table>
					</td>
				</tr>									
		</table>
		
<br/><br/><br/>


		<% String browser = request.getHeader("User-Agent");  %>
		<davisjsp:tree level="-1" text="" script="true" browser="<%=browser%>" iconPath="pages/assets/images/tree_menu/"/>

		<%@ page import="ar.org.sicel.web.altaEdicion.forms.AnimalForm" %>
		<%@ page import="ar.org.sicel.persistence.Animal" %>
		<%@ page import="ar.org.sicel.persistence.Macho" %>
		<%@ page import="java.util.Iterator" %>
		<%@ page import="java.util.Collection" %>
		<% 	AnimalForm a = (AnimalForm) request.getAttribute("animalForm");
			Animal animal = a.getAnimal(); 
		%>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >		
			<tr> 
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Arbol de Ascendencia del Animal (5 Niveles)
				</td>
			</tr>
		</table>
		<br/>

		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="600">
			<tbody>
				<tr>
					<td valign="top">
						<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
							<tbody>
								<tr>
									<td class="TextoVerde"><bean:write name="animalForm" property="animal.nombre"/></td>
									<td class="TextoVerde" align="right">Identificador: <bean:write name="animalForm" property="animal.id"/></td>
								</tr> 
								<tr>  
									<td colspan="2" class="TextoVerde" align="left">
									
									
									
									<davisjsp:tree level="0" text="<%="0 - "+animal.getNombre() + " (Cat: "+ animal.getCategoria()+")"%>" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getId()%>" >

									<%--rama de la madre--%>
										<logic:present name="animalForm" property="animal.madreGenetica">
											<davisjsp:tree level="1" text="<%="1 - "+animal.getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getId()%>" >
												<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica">
													<davisjsp:tree level="2" text="<%="2 - "+animal.getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getId()%>" >
														<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica">
															<davisjsp:tree level="3" text="<%="3 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getId()%>" >							
																<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica.madreGenetica">
																	<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getId()%>" ></davisjsp:tree>
																</logic:present>
																<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica.padre">
																	<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getId()%>" ></davisjsp:tree> 
																</logic:present>
															</davisjsp:tree> 														
														</logic:present> 
						
														<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre">
															<davisjsp:tree level="3" text="<%="3 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getId()%>" >									
																<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre.madreGenetica">
																	<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getId()%>" ></davisjsp:tree>  
																</logic:present> 
																<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre.padre">
																	<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getId()%>" ></davisjsp:tree>  
																</logic:present> 									
															</davisjsp:tree>
														</logic:present> 
													</davisjsp:tree>
												</logic:present> 
												<logic:present name="animalForm" property="animal.madreGenetica.padre">
													<davisjsp:tree level="2" text="<%="2 - "+animal.getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getId()%>" >					
														<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica">
															<davisjsp:tree level="3" text="<%="3 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getId()%>" >							
																<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica.madreGenetica">
																	<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getId()%>" ></davisjsp:tree>  							</logic:present>
																		<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica.padre">
																			<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getId()%>" ></davisjsp:tree> 
																		</logic:present>
																	</davisjsp:tree> 														
																</logic:present> 
						
																<logic:present name="animalForm" property="animal.madreGenetica.padre.padre">
																	<davisjsp:tree level="3" text="<%="3 - "+animal.getMadreGenetica().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getId()%>" >									
																		<logic:present name="animalForm" property="animal.madreGenetica.padre.padre.madreGenetica">
																			<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getId()%>" ></davisjsp:tree>  
																		</logic:present> 
																		<logic:present name="animalForm" property="animal.madreGenetica.padre.padre.padre">
																			<davisjsp:tree level="4" text="<%="4 - "+animal.getMadreGenetica().getPadre().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getPadre().getId()%>" ></davisjsp:tree>  
																		</logic:present> 									
																	</davisjsp:tree>
																</logic:present> 
															</davisjsp:tree>
														</logic:present> 					
													</davisjsp:tree>
												</logic:present > 	
			
	
	
												<%--rama del padre--%>
												<logic:present name="animalForm" property="animal.padre">
													<davisjsp:tree level="1" text="<%="1 - "+animal.getPadre().getNombre() + " (Cat: "+ animal.getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getId()%>" >			
														<logic:present name="animalForm" property="animal.padre.madreGenetica">
															<davisjsp:tree level="2" text="<%="2 - "+animal.getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getId()%>" >					
																<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica">
																	<davisjsp:tree level="3" text="<%="3 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getId()%>" >							
																		<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica.madreGenetica">
																			<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getId()%>" ></davisjsp:tree>  							</logic:present>
																				<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica.padre">
																					<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getId()%>" ></davisjsp:tree> 
																				</logic:present>
																			</davisjsp:tree> 														
																		</logic:present> 
						
																		<logic:present name="animalForm" property="animal.padre.madreGenetica.padre">
																			<davisjsp:tree level="3" text="<%="3 - "+animal.getPadre().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getId()%>" >									
																				<logic:present name="animalForm" property="animal.padre.madreGenetica.padre.madreGenetica">
																					<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getId()%>" ></davisjsp:tree>  
																				</logic:present> 
																				<logic:present name="animalForm" property="animal.padre.madreGenetica.padre.padre">
																					<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getMadreGenetica().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getPadre().getId()%>" ></davisjsp:tree>  
																				</logic:present> 									
																			</davisjsp:tree>
																		</logic:present> 
																	</davisjsp:tree>
																</logic:present> 
																<logic:present name="animalForm" property="animal.padre.padre">
																	<davisjsp:tree level="2" text="<%="2 - "+animal.getPadre().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getId()%>" >					
																		<logic:present name="animalForm" property="animal.padre.padre.madreGenetica">
																			<davisjsp:tree level="3" text="<%="3 - "+animal.getPadre().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getId()%>" >							
																				<logic:present name="animalForm" property="animal.padre.padre.madreGenetica.madreGenetica">
																					<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getId()%>" ></davisjsp:tree>  							</logic:present>
																						<logic:present name="animalForm" property="animal.padre.padre.madreGenetica.padre">
																							<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getPadre().getMadreGenetica().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getPadre().getId()%>" ></davisjsp:tree> 
																						</logic:present>
																					</davisjsp:tree> 														
																				</logic:present> 
							
																				<logic:present name="animalForm" property="animal.padre.padre.padre">
																					<davisjsp:tree level="3" text="<%="3 - "+animal.getPadre().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getId()%>" >									
																						<logic:present name="animalForm" property="animal.padre.padre.padre.madreGenetica">
																							<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getPadre().getPadre().getMadreGenetica().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getMadreGenetica().getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getMadreGenetica().getId()%>" ></davisjsp:tree>  
																						</logic:present> 
																						<logic:present name="animalForm" property="animal.padre.padre.padre.padre">
																							<davisjsp:tree level="4" text="<%="4 - "+animal.getPadre().getPadre().getPadre().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getPadre().getId()%>" ></davisjsp:tree>  
																						</logic:present> 									
																					</davisjsp:tree>
																				</logic:present> 	
																			</davisjsp:tree>
																		</logic:present> 					
																	</davisjsp:tree>
																</logic:present > 	

   
									</davisjsp:tree>
									</td>
								</tr>   				      		
								
								
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>        


<br/><br/><br/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >		
			<tr> 
					<%	if(animal.esHembra()) {%>										
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Arbol de Descendencia del Animal (Hembra -> 5 Niveles)
				</td>

					<% } else {%>
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Arbol de Descendencia del Animal (Macho -> 3 Niveles)
				</td>

					<% } %>
			</tr>
		</table>
		<br/>
		
		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="600">
			<tbody>
				<tr>
					<td valign="top">
						<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
							<tbody>
								<tr>
									<td class="TextoVerde"><bean:write name="animalForm" property="animal.nombre"/></td>
									<td class="TextoVerde" align="right">Identificador: <bean:write name="animalForm" property="animal.id"/></td>
								</tr>      				      		
								<tr>  
									<td colspan="2" class="TextoVerde" align="left">


<%--descendientes hembras del animal--%>
<davisjsp:tree level="0" text="<%="0 - "+animal.getNombre() + " (Cat: "+ animal.getCategoria()+")"%>" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getId()%>" >

<%	Collection hijas1 = animal.getHijasGeneticas();
	Iterator it1 = hijas1.iterator();
	while(it1.hasNext()) {
		Animal hija1 = (Animal)it1.next();
%>
		<davisjsp:tree level="1" text="<%="1 - "+hija1.getNombre()+ " (Cat: "+ hija1.getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+hija1.getId()%>" >

		<%	Collection hijas2 = hija1.getHijasGeneticas();
			Iterator it2 = hijas2.iterator();
			while(it2.hasNext()) {
				Animal hija2 = (Animal)it2.next();
				
				if(!animal.esHembra()) {
		%>
					<davisjsp:tree level="2" text="<%="2 - "+hija2.getNombre()+ " (Cat: "+ hija2.getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+hija2.getId()%>" ></davisjsp:tree>
					<davisjsp:tree level="2" text="<%="2 - "+animal.getPadre().getPadre().getNombre() + " (Cat: "+ animal.getPadre().getPadre().getCategoria()+")"%>" icon="mars50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getId()%>" >				
				<% } else { %>
					<davisjsp:tree level="2" text="<%="2 - "+hija2.getNombre()+ " (Cat: "+ hija2.getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+hija2.getId()%>" >
					
					<%	Collection hijas3 = hija2.getHijasGeneticas();
						Iterator it3 = hijas3.iterator();
						while(it3.hasNext()) {
							Animal hija3 = (Animal)it3.next();
					%>
							<davisjsp:tree level="3" text="<%="3 - "+hija3.getNombre()+ " (Cat: "+ hija3.getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+hija3.getId()%>" >
													
							<%	Collection hijas4 = hija3.getHijasGeneticas();
									Iterator it4 = hijas4.iterator();
									while(it4.hasNext()) {
										Animal hija4 = (Animal)it4.next();
							%>
								<davisjsp:tree level="4" text="<%="4 - "+hija4.getNombre()+ " (Cat: "+ hija4.getCategoria()+")"%>" icon="venus50.gif" leaf="false" style="WIN" link="<%="buscarAnim.do?method=porId&animalId="+hija4.getId()%>" ></davisjsp:tree>
								<% } %>		
							</davisjsp:tree>
						<% } %>			
					</davisjsp:tree>
				<% } %>
					
		<%
			}
		%>	
		</davisjsp:tree>
			
		<%
		}		
	%>
	
	
	
</davisjsp:tree>

</td>
</tr>


							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>        






	</body>
</html:html>


