<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/davis-tags" prefix="davisjsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/buscarAnim.do" method="post" enctype="multipart/form-data" >
	<head>
		<title>
			Ficha de Animal
		</title>
		<script language="javascript">
				function MM_openBrWindow(theURL,winName,features) { //v2.0
					window.open(theURL,winName,features);
				}
		</script>
	</head>
	<body>
		<input type="hidden"  name="method" value="<c:out value="${requestScope.action}"/>">
		<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    		<tr> 
        		<td class=Titulo>
            		<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>            	
	                   		&nbsp;Ficha del Animal
	            	</font>
    	        </td>
            	<td>
            		<div align=right>
            		</div>
            	</td>
        	</tr>
        	<tr> 
        		<td class=texto4 colSpan=2>
            		<div align=right> 
                		<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    		<tr> 
                        		<td width="30%" bgColor=#529b28>
                        			<img height=2 src="pages/assets/images/pixel.gif" width=2>
                        		</td>
                            	<td width="70%">
                            		<IMG height=2 src="pages/assets/images/pixel.gif" width=2>
                            	</td>
                        	</tr>
                    	</table>
               		</div>
            	</td>
        	</tr>
		</table>
		<table>
			<tr>
				<td class="TextoVerde" align="left" height="10">
				</td>
				<td class="TextoVerde" colspan= "2" align="left">
				</td>
			</tr>
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
    		   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        			<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Información General
	            </td>
    	    </tr>
        </table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center">
			
			<tr>
				<td class="TextoVerde" align="left" height="10" width="70">
					<b>
						Nombre&nbsp;Completo:&nbsp;
					</b>
				</td>
          		<td class="TextoVerde" align="left">
          			<bean:write name="animalForm" property="animal.nombre"/>
          		</td>
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
						<td class="TextoVerde" align="left" height="10" width="70"><b>Categoría:</b></td>
						<td class="TextoVerde" colspan= "2" align="left"><bean:write name="animalForm" property="animal.categoria"/></td>
						
				</tr>  	
		
			
					
				<tr>
						
						<td class="TextoVerde" align="left" height="10" width="70"><b>RP:</b></td>
									<td class="TextoVerde" colspan= "2" align="left"><bean:write name="animalForm" property="animal.RP"/></td>
								</tr>
								
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Sexo:</b></td>
									<td class="TextoVerde" colspan= "2" align="left">
										<c:if test="${animalForm.esHembra == true}">Hembra</c:if>
										<c:if test="${animalForm.esHembra == false}">Macho</c:if>
										
								</tr>
								
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Fecha&nbsp;de&nbsp;Nacimiento:</b></td>
									<td class="TextoVerde" colspan= "2" align="left">
										<bean:define id="_animal" name="animalForm" property="animal" />                    
											<% String fechaDeNacimiento= String.format("%tF", new Object[]{((ar.org.sicel.persistence.Animal)_animal).getFechaNac()}); %>                    
                							<%=fechaDeNacimiento%>         	 
									</td>
								</tr>
								
								<tr>
									 <td class="TextoVerde" align="left" height="10" width="70"><b>Dado&nbsp;de&nbsp;Baja?</b></td>
									 <td class="TextoVerde" colspan= "2" align="left"><html:checkbox disabled="true" name="animalForm" property="animal.esBaja" /></td>
								</tr> 
								<td class="TextoVerde" align="left" height="10" width="70"><b>Tara Genética:</b></td>
									<td class="TextoVerde" colspan= "2" align="left"><bean:write name="animalForm" property="animal.taraG"/></td>
								</tr>         	
								
								
		</table>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        											
			          			<tr>
          							<td class="TextoVerde" align="left" height="10"><b>Propietario</b><spacer type="block" height="1" width="1"></td>
          							<td class="TextoVerde" align="left">
				          				<logic:present name="animalForm" property="animal.propietario">
				          						
				          						<bean:write name="animalForm" property="animal.propietario.id"/> 
                    				            <bean:write name="animalForm" property="animal.propietario.nombreContacto"/>
				                        </logic:present>
                        				<logic:notPresent name="animalForm" property="animal.propietario">
				                            No Disponible
                				        </logic:notPresent>
                				 	</td>
          						</tr>          				
								      			
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Tambo</b><spacer type="block" height="1" width="1"></td>
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.establecimiento">
												<bean:write name="animalForm" property="animal.establecimiento.id"/>
												-
												<bean:write name="animalForm" property="animal.establecimiento.nombreContacto"/>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.establecimiento">
											No Disponible
										</logic:notPresent> 
									</td>         				
								</tr>
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Establecimiento</b><spacer type="block" height="1" width="1"></td>
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.estancia">
												<bean:write name="animalForm" property="animal.estancia.id"/>
												-
												<bean:write name="animalForm" property="animal.estancia.nombreContacto"/>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.estancia">
											No Disponible
										</logic:notPresent> 
									</td>         				
								</tr>
								      			
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Eclo</b><spacer type="block" height="1" width="1"></td>
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.establecimiento.eclo">
												<bean:write name="animalForm" property="animal.establecimiento.eclo.id"/>
												-
												<bean:write name="animalForm" property="animal.establecimiento.eclo.nombreContacto"/>
										</logic:present>
										<logic:notPresent name="animalForm" property="animal.establecimiento.eclo">
											No Disponible
										</logic:notPresent> 
									</td>         				
								</tr>
</table>

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        				
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Padre</b><spacer type="block" height="1" width="1"></td>          				
									<td class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.padre" >
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.padre.id" >                                
												<bean:write name="animalForm" property="animal.padre.regOrigen"/>
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
									<td class="TextoVerde" align="left" height="10"><b>Madre Genética</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">                 				
										<logic:present name="animalForm" property="animal.madreGenetica">
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.madreGenetica.id" >
												<bean:write name="animalForm" property="animal.madreGenetica.regOrigen"/>
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
									<td class="TextoVerde" align="left" height="10"><b>Madre Parto</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">
										<logic:present name="animalForm" property="animal.madreParto">
											<html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="animalForm" paramProperty="animal.madreParto.id" >                                
												<bean:write name="animalForm" property="animal.madreParto.regOrigen"/>
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

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">								        				          														       
								<tr>
									<td class="TextoVerde" align="left" height="10"><b>Composición Genealógica</b><spacer type="block" height="1" width="1"></td>
									<td colspan="2" class="TextoVerde" align="left">
										<display:table name="animalForm.animal.composicionRacial.asCollection" align="center" class="its" width="100%" id="table1" >
											<display:caption>
												<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
													<TR>
														<TD>Raza: </TD>
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
											<display:column property="key.nombre" title="Composicion" />
											<display:column property="value" title="Valor" />
											<display:setProperty name="basic.msg.empty_list" >
											</display:setProperty>
										</display:table>
									</td>
								</tr>             	
								
</table>

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">
								<tr>
									<td class="TextoVerde" align="left" height="10" width="70"><b>Registros</b></td>
									<td colspan="2" class="TextoVerde" align="center">       	              
										<display:table name="animalForm.animal.registros" align="center" class="its" id="table2" >
											<display:caption>
												<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
													<TR>
														<td class="travelinfo" align="left">Nacional: </TD>
														<td>
															<logic:present name="animalForm" property="animal.regOrigen" >
																<bean:write name="animalForm" property="animal.regOrigen"/>
															</logic:present>
															<logic:notPresent name="animalForm" property="animal.regOrigen" >
																<bean:message key="dataNotFound" />
															</logic:notPresent>
															<c:if test="${animalForm.rolAdmin == animalForm.rol}">
																<td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
																<input type="button" value="cambiar registro" class="botones" onClick="window.location='cambioRegistroAction.do?method=init&id=<bean:write name="animalForm" property="animal.id" />'" >
																	</TD>
															</c:if>
														</TD>
													</TR>			
													<TR>
														<td class="travelinfo" align="left">Internacional: </TD>
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
											<display:column property="codigoBaja" title="Cod. Baja" />
											<display:setProperty name="basic.msg.empty_list" >
												<h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
											</display:setProperty>
										</display:table>
									</td>
								</tr>			
</table>
<table>
	<tr>
		<td class="TextoVerde" align="left" height="10">
		</td>
		<td class="TextoVerde" colspan= "2" align="left">
		</td>
	</tr>
</table> 

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
				<a align="right" href="javascript:MM_openBrWindow('buscarAnim.do?method=mostrarEventos&animalId=<bean:write name="animalForm" property="animal.id" />','','scrollbars=yes,resizable=yes,width=900,height=600,top=100,left=100')">Eventos del Animal</a>
            </td>
        </tr>
 </table>
		
<table>
	<tr>
		<td class="TextoVerde" align="left" height="10">
		</td>
		<td class="TextoVerde" colspan= "2" align="left">
		</td>
	</tr>
</table> 
		
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >		
			<tr> 
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Calificaciones del Animal
				</td>
			</tr>
		</table>
		
		
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
				
				<tr>
					<td class="TextoVerde" colspan="2">
						<display:table name="animalForm.animal.calificacions" align="center" class="its" width="100%" id="table4" >					
							<display:column property="id"
								title="ID"/>
							<display:column property="fecha" title="Fecha" />
							<display:column property="catPuntaje" title="Cat. Puntaje" />
							<display:column property="puntaje" title="Puntaje" />
							<display:setProperty name="basic.msg.empty_list" >
								<tr>
									<td class="celdaInput">El animal no tiene calificaciones</td>        		
								</tr>
							</display:setProperty>
						</display:table>
					</td>
				</tr>									
		</table>
		</table>
		


		<% String browser = request.getHeader("User-Agent");  %>
		<davisjsp:tree level="-1" text="" script="true" browser="<%=browser%>" iconPath="pages/assets/images/tree_menu/"/>

		<%@ page import="ar.org.sicel.web.altaEdicion.forms.AnimalForm" %>
		<%@ page import="ar.org.sicel.persistence.Animal" %>
		<%@ page import="ar.org.sicel.persistence.Macho" %>
		<%@ page import="java.util.Iterator" %>
		<%@ page import="java.util.Collection" %>

		<%@ page import="ar.org.sicel.persistence.AnimalDAO" %>


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
		<table>
			<tr>
				<td class="TextoVerde" align="left" height="10">
				</td>
				<td class="TextoVerde" colspan= "2" align="left">
				</td>
			</tr>
		</table> 
		

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
									
									
									 <%String a1 ="0 - "+animal.getNombreLabel() + " (Cat: "+ animal.getCategoria()+")";%>
									<%String a1a ="buscarAnim.do?method=porId&animalId="+animal.getId();%>
									<davisjsp:tree level="0" text="<%=a1%>" leaf="false" style="WIN" link="<%=a1a%>" >

<%--rama del padre--%>
<logic:present name="animalForm" property="animal.padre">
<%String a2 ="1 - "+animal.getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getCategoria()+")";%>
<%String a2a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getId();%>
<davisjsp:tree level="1" text="<%=a2%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a2a%>" >			

	<!-- abuelo de la vaca-->
	<logic:present name="animalForm" property="animal.padre.padre">
	<%String a3 ="2 - "+animal.getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getCategoria()+")";%>
	<%String a3a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getId();%>
	<davisjsp:tree level="2" text="<%=a3%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a3a%>" >							 
		<logic:present name="animalForm" property="animal.padre.padre.padre">
		<%String a4 ="3 - "+animal.getPadre().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getCategoria()+")";%>
		<%String a4a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getId();%>
		<davisjsp:tree level="3" text="<%=a4%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a4a%>" >									
				<logic:present name="animalForm" property="animal.padre.padre.padre.padre">
				<%String a5 ="4 - "+animal.getPadre().getPadre().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getPadre().getCategoria()+")";%>
				<%String a5a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getPadre().getId();%>
				<davisjsp:tree level="4" text="<%=a5%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a5a%>" >
				</davisjsp:tree>  
				</logic:present> 									
				<logic:present name="animalForm" property="animal.padre.padre.padre.madreGenetica">
				<%String a6 ="4 - "+animal.getPadre().getPadre().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getPadre().getMadreGenetica().getCategoria()+")";%>
				<%String a6a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getPadre().getMadreGenetica().getId();%>
				<davisjsp:tree level="4" text="<%=a6%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a6a%>" >
				</davisjsp:tree>  
				</logic:present> 
		</davisjsp:tree>
		</logic:present> 	
		<logic:present name="animalForm" property="animal.padre.padre.madreGenetica">
		<%String a7 ="3 - "+animal.getPadre().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getCategoria()+")";%>
		<%String a7a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getId();%>
		<davisjsp:tree level="3" text="<%=a7%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a7a%>" >							
				<logic:present name="animalForm" property="animal.padre.padre.madreGenetica.padre">
				<%String a41 ="4 - "+animal.getPadre().getPadre().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getPadre().getCategoria()+")";%>
				<%String a41a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getPadre().getId();%>
				<davisjsp:tree level="4" text="<%=a41%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a41a%>" >
				</davisjsp:tree> 
				</logic:present>
				<logic:present name="animalForm" property="animal.padre.padre.madreGenetica.madreGenetica">
				<%String a8 ="4 - "+animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
				<%String a8a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getPadre().getMadreGenetica().getMadreGenetica().getId();%>
				<davisjsp:tree level="4" text="<%=a8%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a8a%>" >
				</davisjsp:tree>  							
				</logic:present>
		</davisjsp:tree> 														
		</logic:present>
	</davisjsp:tree>
	</logic:present> 					

	<!-- abuela de la vaca-->
	<logic:present name="animalForm" property="animal.padre.madreGenetica">
	<%String a9 ="2 - "+animal.getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getCategoria()+")";%>
	<%String a9a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getId();%>
	<davisjsp:tree level="2" text="<%=a9%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a9a%>" >					
		<logic:present name="animalForm" property="animal.padre.madreGenetica.padre">
		<%String a10 ="3 - "+animal.getPadre().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getCategoria()+")";%>
		<%String a10a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getId();%>
		<davisjsp:tree level="3" text="<%=a10%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a10a%>" >									
				<logic:present name="animalForm" property="animal.padre.madreGenetica.padre.padre">
					<%String a11 ="4 - "+animal.getPadre().getMadreGenetica().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getPadre().getCategoria()+")";%>
					<%String a11a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getPadre().getId();%>
					<davisjsp:tree level="4" text="<%=a11%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a11a%>" >
					</davisjsp:tree>  
				</logic:present> 									
				<logic:present name="animalForm" property="animal.padre.madreGenetica.padre.madreGenetica">
					<%String a12 ="4 - "+animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")";%>
					<%String a12a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getPadre().getMadreGenetica().getId();%>
					<davisjsp:tree level="4" text="<%=a12%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a12a%>" >
					</davisjsp:tree>  
				</logic:present> 
		</davisjsp:tree>
		</logic:present> 

		<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica">
		<%String a13 ="3 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
		<%String a13a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getId();%>
		<davisjsp:tree level="3" text="<%=a13%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a13a%>" >							
				<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica.padre">
					<%String a14 ="4 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")";%>
					<%String a14a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getPadre().getId();%>
					<davisjsp:tree level="4" text="<%=a14%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a14a%>" >
					</davisjsp:tree> 
				</logic:present>
				<logic:present name="animalForm" property="animal.padre.madreGenetica.madreGenetica.madreGenetica">
					<%String a15 ="4 - "+animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
					<%String a15a ="buscarAnim.do?method=porId&animalId="+animal.getPadre().getMadreGenetica().getMadreGenetica().getMadreGenetica().getId();%>
					<davisjsp:tree level="4" text="<%=a15%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a15a%>" >
					</davisjsp:tree>  							
				</logic:present>
		</davisjsp:tree> 														
		</logic:present> 
	</davisjsp:tree>
	</logic:present> 

</davisjsp:tree>
</logic:present > 	



	
	<%--rama de la madre--%>
<logic:present name="animalForm" property="animal.madreGenetica">
<%String a16 ="1 - "+animal.getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getCategoria()+")";%>
<%String a16a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getId();%>
<davisjsp:tree level="1" text="<%=a16%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a16a%>" >


	<logic:present name="animalForm" property="animal.madreGenetica.padre">
	<%String a17 ="2 - "+animal.getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getCategoria()+")";%>
	<%String a17a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getId();%>
	<davisjsp:tree level="2" text="<%=a17%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a17a%>" >					
					
		<logic:present name="animalForm" property="animal.madreGenetica.padre.padre">
		<%String a40 ="3 - "+animal.getMadreGenetica().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getCategoria()+")";%>
		<%String a40a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getId();%>
		<davisjsp:tree level="3" text="<%=a40%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a40a%>" >									
			<logic:present name="animalForm" property="animal.madreGenetica.padre.padre.padre">
			<%String a18 ="4 - "+animal.getMadreGenetica().getPadre().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getPadre().getCategoria()+")";%>
			<%String a18a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getPadre().getId();%>
			<davisjsp:tree level="4" text="<%=a18%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a18a%>" >
			</davisjsp:tree>  
			</logic:present> 									

			<logic:present name="animalForm" property="animal.madreGenetica.padre.padre.madreGenetica">
			<%String a19 ="4 - "+animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getCategoria()+")";%>
			<%String a19a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getPadre().getMadreGenetica().getId();%>
			<davisjsp:tree level="4" text="<%=a19%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a19a%>" >
			</davisjsp:tree>  
			</logic:present> 
		</davisjsp:tree>
		</logic:present> 

		<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica">
		<%String a20 ="3 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")";%>
		<%String a20a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getId();%>
		<davisjsp:tree level="3" text="<%=a20%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a20a%>" >							
			<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica.padre">
			<%String a21 ="4 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getCategoria()+")";%>
			<%String a21a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getPadre().getId();%>
			<davisjsp:tree level="4" text="<%=a21%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a21a%>" >
			</davisjsp:tree> 
			</logic:present>

			<logic:present name="animalForm" property="animal.madreGenetica.padre.madreGenetica.madreGenetica">
			<%String a22 ="4 - "+animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
			<%String a22a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getPadre().getMadreGenetica().getMadreGenetica().getId();%>
			<davisjsp:tree level="4" text="<%=a22%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a22a%>" >
			</davisjsp:tree>  							
			</logic:present>
		</davisjsp:tree> 														
		</logic:present> 


	</davisjsp:tree>
	</logic:present> 					

	<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica">
	<%String a23 ="2 - "+animal.getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
	<%String a23a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getId();%>
	<davisjsp:tree level="2" text="<%=a23%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a23a%>" >

			<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre">
			<%String a24 ="3 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")";%>
			<%String a24a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getId();%>
			<davisjsp:tree level="3" text="<%=a24%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a24a%>" >									
				<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre.padre">
				<%String a25 ="4 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getCategoria()+")";%>
				<%String a25a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getPadre().getId();%>
				<davisjsp:tree level="4" text="<%=a25%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a25a%>" >
				</davisjsp:tree>  
				</logic:present> 									

				<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.padre.madreGenetica">
				<%String a26 ="4 - "+animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getCategoria()+")";%>
				<%String a26a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getPadre().getMadreGenetica().getId();%>
				<davisjsp:tree level="4" text="<%=a26%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a26a%>" >
				</davisjsp:tree>  
				</logic:present> 
			</davisjsp:tree>
			</logic:present> 

			<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica">
			<%String a27 ="3 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
			<%String a27a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getId();%>
			<davisjsp:tree level="3" text="<%=a27%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a27a%>" >							
				<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica.padre">
				<%String a28 ="4 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getCategoria()+")";%>
				<%String a28a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getPadre().getId();%>
				<davisjsp:tree level="4" text="<%=a28%>" icon="mars50.gif" leaf="false" style="WIN" link="<%=a28a%>" >
				</davisjsp:tree> 
				</logic:present>

				<logic:present name="animalForm" property="animal.madreGenetica.madreGenetica.madreGenetica.madreGenetica">
				<%String a29 ="4 - "+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getNombreLabel() + " (Cat: "+ animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getCategoria()+")";%>
				<%String a29a ="buscarAnim.do?method=porId&animalId="+animal.getMadreGenetica().getMadreGenetica().getMadreGenetica().getMadreGenetica().getId();%>
				<davisjsp:tree level="4" text="<%=a29%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a29a%>" >
				</davisjsp:tree>
				</logic:present>
			</davisjsp:tree> 														
			</logic:present> 
							
	</davisjsp:tree>
	</logic:present> 
				
</davisjsp:tree>
</logic:present > 	
			
</davisjsp:tree>  <!-- de la raiz mas externa-->
									</td>
								</tr>   				      		
								
								
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>        

<table>
	<tr>
		<td class="TextoVerde" align="left" height="10">
		</td>
		<td class="TextoVerde" colspan= "2" align="left">
		</td>
	</tr>
</table> 

<c:if test="${animalForm.mostrarDescendencia == true}">
<div>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >		
			<tr> 
					<%	if(animal.esHembra()) {%>										
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Arbol de Descendencia del Animal (Hembra -> 5 Niveles)
				</td>

					<% } else {%>
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Arbol de Descendencia del Animal (Macho -> 1 Nivel)
				</td>

					<% } %>
			</tr>
		</table>
		<table>
	<tr>
		<td class="TextoVerde" align="left" height="10">
		</td>
		<td class="TextoVerde" colspan= "2" align="left">
		</td>
	</tr>
</table> 
		
		
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
<%String a30 ="0 - "+animal.getNombreLabel() + " (Cat: "+ animal.getCategoria()+")";%>
				<%String a30a ="buscarAnim.do?method=porId&animalId="+animal.getId();%>
				
<davisjsp:tree level="0" text="<%=a30%>" leaf="false" style="WIN" link="<%=a30a%>" >

<%	Collection hijas1 = AnimalDAO.getHijosGeneticos(animal);

Iterator it1 = hijas1.iterator();
	while(it1.hasNext()) {
		Animal hija1 = (Animal)it1.next();
		if(!hija1.esHembra()) {
%>		
		<%String a31 ="1 - "+hija1.getNombreLabel()+ " (Cat: "+ hija1.getCategoria()+")";%>
		<%String a31a ="buscarAnim.do?method=porId&animalId="+hija1.getId();%>
		<davisjsp:tree level="1" text="<%=a31%>" icon="mars50.gif" leaf="true" style="WIN" link="<%=a31a%>" ></davisjsp:tree>
		<% } else { %>
		<%String a31 ="1 - "+hija1.getNombreLabel()+ " (Cat: "+ hija1.getCategoria()+")";%>
		<%String a31a ="buscarAnim.do?method=porId&animalId="+hija1.getId();%>
		<davisjsp:tree level="1" text="<%=a31%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a31a%>" >
		<%	Collection hijas2 = AnimalDAO.getHijosGeneticos(hija1);
			Iterator it2 = hijas2.iterator();
			while(it2.hasNext()) {
				Animal hija2 = (Animal)it2.next();
				
				if(!hija2.esHembra()) {
		%>
					<%String a32 ="2 - "+hija2.getNombreLabel()+ " (Cat: "+ hija2.getCategoria()+")";%>
				    <%String a32a ="buscarAnim.do?method=porId&animalId="+hija2.getId();%>
					<davisjsp:tree level="2" text="<%=a32%>" icon="mars50.gif" leaf="true" style="WIN" link="<%=a32a%>" ></davisjsp:tree>
				
				<% } else { %>
					<%String a32 ="2 - "+hija2.getNombreLabel()+ " (Cat: "+ hija2.getCategoria()+")";%>
				    <%String a32a ="buscarAnim.do?method=porId&animalId="+hija2.getId();%>
					<davisjsp:tree level="2" text="<%=a32%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a32a%>" >
					
					<%	Collection hijas3 = AnimalDAO.getHijosGeneticos(hija2);
						Iterator it3 = hijas3.iterator();
						while(it3.hasNext()) {
							Animal hija3 = (Animal)it3.next();
							
							if(!hija3.esHembra()) {
								%>
											  <%String a33 ="3 - "+hija3.getNombreLabel()+ " (Cat: "+ hija3.getCategoria()+")";%>
											  <%String a33a ="buscarAnim.do?method=porId&animalId="+hija3.getId();%>
											  <davisjsp:tree level="3" text="<%=a33%>" icon="mars50.gif" leaf="true" style="WIN" link="<%=a33a%>" ></davisjsp:tree>
										
										<% } else { %>
												<%String a33 ="3 - "+hija3.getNombreLabel()+ " (Cat: "+ hija3.getCategoria()+")";%>
												<%String a33a ="buscarAnim.do?method=porId&animalId="+hija3.getId();%>
												<davisjsp:tree level="3" text="<%=a33%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a33a%>" >
																		
												<%	Collection hijas4 = AnimalDAO.getHijosGeneticos(hija3);
														Iterator it4 = hijas4.iterator();
														while(it4.hasNext()) {
															Animal hija4 = (Animal)it4.next();
											
															if(!hija4.esHembra()) {
								            %>
																<%String a34 ="4 - "+hija4.getNombreLabel()+ " (Cat: "+ hija4.getCategoria()+")";%>
																<%String a34a ="buscarAnim.do?method=porId&animalId="+hija4.getId();%>
																<davisjsp:tree level="4" text="<%=a34%>" icon="mars50.gif" leaf="true" style="WIN" link="<%=a34a%>" ></davisjsp:tree>																
																<% } else { %>
																<%String a34 ="4 - "+hija4.getNombreLabel()+ " (Cat: "+ hija4.getCategoria()+")";%>
																<%String a34a ="buscarAnim.do?method=porId&animalId="+hija4.getId();%>
																<davisjsp:tree level="4" text="<%=a34%>" icon="venus50.gif" leaf="false" style="WIN" link="<%=a34a%>" ></davisjsp:tree>
																<% } %>	
														<% } %>		
												</davisjsp:tree>
											<% } %>			
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
</div>
</c:if>   






	</body>
</html:form>
<script>
function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
</script>


