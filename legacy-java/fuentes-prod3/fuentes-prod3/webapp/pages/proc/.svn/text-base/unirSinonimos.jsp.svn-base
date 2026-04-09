<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/realizarUnionSinonimosAction.do" method="post">
						
			<input type="hidden" name="method" value="unir">
			<html:hidden name="sinonimosForm" property="rp" value=""/>
			<input type="hidden"  name="rpAni1" value="<c:out value="${sinonimosForm.animal1.RP}"/>">
			<input type="hidden"  name="rpAni2" value="<c:out value="${sinonimosForm.animal2.RP}"/>">
	
			
			<html:hidden name="sinonimosForm" property="categoria" value=""/>
			<input type="hidden"  name="catAni1" value="<c:out value="${sinonimosForm.animal1.categoria}"/>">
			<input type="hidden"  name="catAni2" value="<c:out value="${sinonimosForm.animal2.categoria}"/>">
			<html:hidden name="sinonimosForm" property="nombre" value=""/>
			<input type="hidden"  name="nombreAni1" value="<c:out value="${sinonimosForm.animal1.nombre}"/>">
			<input type="hidden"  name="nombreAni2" value="<c:out value="${sinonimosForm.animal2.nombre}"/>">
			<html:hidden name="sinonimosForm" property="fnac" value=""/>
			<input type="hidden"  name="fnacAni1" value="<c:out value="${sinonimosForm.animal1.fechaNac}"/>">
			<input type="hidden"  name="fnacAni2" value="<c:out value="${sinonimosForm.animal2.fechaNac}"/>">
			<html:hidden name="sinonimosForm" property="regOri" value=""/>
			<input type="hidden"  name="regOriAni1" value="<c:out value="${sinonimosForm.animal1.registroOrigen}"/>">
			<input type="hidden"  name="regOriAni2" value="<c:out value="${sinonimosForm.animal2.registroOrigen}"/>">
			<html:hidden name="sinonimosForm" property="regId" value=""/>
			<input type="hidden"  name="regIDAni1" value="<c:out value="${sinonimosForm.animal1.registroID}"/>">
			<input type="hidden"  name="regIDAni2" value="<c:out value="${sinonimosForm.animal2.registroID}"/>">
			<html:hidden name="sinonimosForm" property="tambo" value=""/>
			<input type="hidden"  name="tamboAni1" value="<c:out value="${sinonimosForm.animal1.establecimiento.id}"/>">
			<input type="hidden"  name="tamboAni2" value="<c:out value="${sinonimosForm.animal2.establecimiento.id}"/>">
			<html:hidden name="sinonimosForm" property="prop" value=""/>
			<input type="hidden"  name="propAni1" value="<c:out value="${sinonimosForm.animal1.propietario.id}"/>">
			<input type="hidden"  name="propAni2" value="<c:out value="${sinonimosForm.animal2.propietario.id}"/>">
			<html:hidden name="sinonimosForm" property="tamboCri" value=""/>
			<input type="hidden"  name="tamboCriAni1" value="<c:out value="${sinonimosForm.animal1.establecimientoCriador.id}"/>">
			<input type="hidden"  name="tamboCriAni2" value="<c:out value="${sinonimosForm.animal2.establecimientoCriador.id}"/>">
			<html:hidden name="sinonimosForm" property="propCri" value=""/>
			<input type="hidden"  name="propCriAni1" value="<c:out value="${sinonimosForm.animal1.propietarioCriador.id}"/>">
			<input type="hidden"  name="propCriAni2" value="<c:out value="${sinonimosForm.animal2.propietarioCriador.id}"/>">
			<html:hidden name="sinonimosForm" property="padres" value=""/>
			<input type="hidden"  name="padreAni1" value="<c:out value="${sinonimosForm.animal1.padre.regOrigen}"/>">
			<input type="hidden"  name="padreAni2" value="<c:out value="${sinonimosForm.animal2.padre.regOrigen}"/>">
			<html:hidden name="sinonimosForm" property="madreGenetica" value=""/>
			<input type="hidden"  name="madreGeneticaAni1" value="<c:out value="${sinonimosForm.animal1.madreGenetica.regOrigen}"/>">
			<input type="hidden"  name="madreGeneticaAni2" value="<c:out value="${sinonimosForm.animal2.madreGenetica.regOrigen}"/>">
			
			<html:hidden name="sinonimosForm" property="rpSenasa" value=""/>
			<input type="hidden"  name="rpSenasaAni1" value="<c:out value="${sinonimosForm.animal1.rpSenasa}"/>">
			<input type="hidden"  name="rpSenasaAni2" value="<c:out value="${sinonimosForm.animal2.rpSenasa}"/>">
			<html:hidden name="sinonimosForm" property="codigoVerificador" value=""/>
			<input type="hidden"  name="codigoVerificadorAni1" value="<c:out value="${sinonimosForm.animal1.codigoVerificador}"/>">
			<input type="hidden"  name="codigoVerificadorAni2" value="<c:out value="${sinonimosForm.animal2.codigoVerificador}"/>">
			<html:hidden name="sinonimosForm" property="asoc" value=""/>
			<input type="hidden"  name="asocAni1" value="<c:out value="${sinonimosForm.animal1.asoc}"/>">
			<input type="hidden"  name="asocAni2" value="<c:out value="${sinonimosForm.animal2.asoc}"/>">
			<html:hidden name="sinonimosForm" property="asop" value=""/>
			<input type="hidden"  name="asopAni1" value="<c:out value="${sinonimosForm.animal1.asop}"/>">
			<input type="hidden"  name="asopAni2" value="<c:out value="${sinonimosForm.animal2.asop}"/>">
			<html:hidden name="sinonimosForm" property="asom" value=""/>
			<input type="hidden"  name="asomAni1" value="<c:out value="${sinonimosForm.animal1.asom}"/>">
			<input type="hidden"  name="asomAni2" value="<c:out value="${sinonimosForm.animal2.asom}"/>">
			<html:hidden name="sinonimosForm" property="dadorSemen" value=""/>
			<input type="hidden"  name="dadorSemenAni1" value="<c:out value="${sinonimosForm.animal1.dadorSemen}"/>">
			<input type="hidden"  name="dadorSemenAni2" value="<c:out value="${sinonimosForm.animal2.dadorSemen}"/>">
			<html:hidden name="sinonimosForm" property="numeroAnalADN" value=""/>
			<input type="hidden"  name="numeroAnalADNAni1" value="<c:out value="${sinonimosForm.animal1.numeroAnalADN}"/>">
			<input type="hidden"  name="numeroAnalADNAni2" value="<c:out value="${sinonimosForm.animal2.numeroAnalADN}"/>">
			<html:hidden name="sinonimosForm" property="donante" value=""/>
			<input type="hidden"  name="donanteAni1" value="<c:out value="${sinonimosForm.animal1.donante}"/>">
			<input type="hidden"  name="donanteAni2" value="<c:out value="${sinonimosForm.animal2.donante}"/>">
			<html:hidden name="sinonimosForm" property="mellizo" value=""/>
			<input type="hidden"  name="mellizoAni1" value="<c:out value="${sinonimosForm.animal1.mellizo}"/>">
			<input type="hidden"  name="mellizoAni2" value="<c:out value="${sinonimosForm.animal2.mellizo}"/>">
			<html:hidden name="sinonimosForm" property="transferencia" value=""/>
			<input type="hidden"  name="transferenciaAni1" value="<c:out value="${sinonimosForm.animal1.transferencia}"/>">
			<input type="hidden"  name="transferenciaAni2" value="<c:out value="${sinonimosForm.animal2.transferencia}"/>">
			<html:hidden name="sinonimosForm" property="tipoServicio" value=""/>
			<input type="hidden"  name="tipoServicioAni1" value="<c:out value="${sinonimosForm.animal1.tipoServicio}"/>">
			<input type="hidden"  name="tipoServicioAni2" value="<c:out value="${sinonimosForm.animal2.tipoServicio}"/>">
			<html:hidden name="sinonimosForm" property="apodo" value=""/>
			<input type="hidden"  name="apodoAni1" value="<c:out value="${sinonimosForm.animal1.apodo}"/>">
			<input type="hidden"  name="apodoAni2" value="<c:out value="${sinonimosForm.animal2.apodo}"/>">
			
			<html:hidden name="sinonimosForm" property="razaElejida" value=""/>
			<input type="hidden"  name="raza1" value="<c:out value="${sinonimosForm.animal1.raza}"/>">
			<input type="hidden"  name="raza2" value="<c:out value="${sinonimosForm.animal2.raza}"/>">
			<html:hidden name="sinonimosForm" property="animalSelec" value=""/>
			<html:hidden name="sinonimosForm" property="idAnimal1" />
			<html:hidden name="sinonimosForm" property="idAnimal2" />

		

			
		
		<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Unir Sinonimos</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                                <TD width="70%"><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                              </TR>
                            </TABLE>
                          </DIV></TD>

                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
				
			
			
			<br>
				<html:messages id="message1" message="true" property="animalNoExiste"/>          	
	<c:if test="${message1!='' and message1!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message1}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	
	
	
	
	
	
	
<table width="100%"  class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >	

		<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Registros/Id: </FONT></TD>
	<tr>
		<td class="celdaLabelSinAlign" colspan="2" >
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="right">
					<tr>
						<td class="celdaLabelSinAlign" colspan="1"><input type="radio" name="checkFiltro1" checked>Por Registro</td>
					</TR>
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
							<tr>
								<td class="celdaLabel">Tipo de registro:&nbsp;</td>
								<td class="celdaInput">
									<html:select property="tipoReg" styleClass="formfields" style="width:100px" >
										<html:options collection="tipos" property="id" labelProperty="id"/>
									</html:select>
								</td>
							</TR>
							<TR>
								<TD class="celdaLabel">Número de registro: </TD>
								<TD class="celdaInput">
				                	<html:text size="20" name="sinonimosForm" title="Número de Registro del Animal" property="numReg" />
				            	</TD>
							</TR>
							<tr>
								<td class="celdaLabel">Raza del Animal:&nbsp;</td>
								<td class="celdaInput">
									<html:select property="raza" styleClass="formfields" style="width:160px" >
										<html:options collection="razas" property="id" labelProperty="nombre"/>
									</html:select>
								</td>					
							</tr>
							<tr>
								<td class="celdaLabel">Sexo:&nbsp;</td>
								<td class="celdaInput" >
									<html:select property="sexo" styleClass="formfields" style="width:100px" >
										<html:option value="H"  >Hembra</html:option>
										<html:option value="M"  >Macho</html:option>
									</html:select>
								</td>	
							</tr>
					</table>
				<table width="100%" class="bordeGris" cellSpacing="2" cellPadding="2" align="right">
				<tr>
					<td class="celdaLabelSinAlign" colspan="1"><input type="radio" name="checkFiltro1">Por Id</TD>
				</tr>
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<TD class="celdaLabel">Id. de Animal: </TD>
					<TD class="celdaInput">
	                	<html:text size="20" name="sinonimosForm" title="ID del Animal"  property="animalId" /> 
		       	 	</TD>
				</table>
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3" align="right">
				<tr class="celdaLabelSinAlign" >
			    	<td align="center"><input type="button" value="Cargar Animal 1" class="botones" onclick="cargarAnimal('1')"></td>
					<td align="center"><input type="button" value="Cargar Animal 2" class="botones" onclick="cargarAnimal('2')"></td>
				</tr>
				</table> 
			</table>



<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<c:if test="${requestScope.mensajeOK != null}">	
			<tr>
				 <td class="celdaLabelSinAlign" colspan="3" align="center"><strong><c:out value="${requestScope.mensajeOK}"/></td>
			</tr>
		</c:if>	
	<c:if test="${sinonimosForm.animal1 != null || sinonimosForm.animal2 !=null }">	
		<html:messages id="message2" message="true" property="error"/>          	
		<c:if test="${message2!='' and message2!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message2}"/>
				</td>
			</tr>
	    </table>
	    <br>
		</c:if>
		<c:if test="${requestScope.mensaje != null }">	
			<tr>
				 <td class="celdaLabelSinAlign" colspan="3" align="center"><strong><c:out value="${requestScope.mensaje}"/></td>
			</tr>
		</c:if>	

			
		<table width="100%"  class="bordeGris" cellSpacing="2" cellPadding="0" align="center" >
	<c:if test="${requestScope.mensajeOK == null}">	
						<tr>
				            <td class="celdaLabel" align="right"><strong>&nbsp;</strong></td>
											
							<td class="celdaLabelSinAlign"  align="left"><strong>Animal 1</strong></td>
							
							<td class="celdaLabelSinAlign"  align="left"><strong>Animal 2</strong></td>								
						</TR>
						<tr></tr>
					
					
					<tr>
				         <td class="celdaLabel" align="right"><strong>RP:&nbsp;</strong></td>
				         <c:if test="${requestScope.mostrarUnir == true }">
				    			<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkRp" checked ><c:out value="${sinonimosForm.animal1.RP}"/> </td>
								<td class="celdaLabelSinAlign" align="left">
								<input type="radio" name="checkRp"><c:out value="${sinonimosForm.animal2.RP}"/></td>
						</c:if>
						 <c:if test="${requestScope.mostrarUnir != true }">
						 		<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal1">
											<bean:write name="sinonimosForm" property="animal1.RP"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
						 		</td>
						 		<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal2">
											<bean:write name="sinonimosForm" property="animal2.RP"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
						 		</td>
						</c:if>
					</TR>
						<tr>
				            <td class="celdaLabel" align="right"><strong>Raza:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkRaza" checked ><c:out value="${sinonimosForm.animal1.raza}"/> </td>
								<td class="celdaLabelSinAlign" align="left">
								<input type="radio" name="checkRaza"><c:out value="${sinonimosForm.animal2.raza}"/></td>	
							</c:if>				
							 <c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal1">
											<bean:write name="sinonimosForm" property="animal1.raza"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
						 		</td>
						 		<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal2">
											<bean:write name="sinonimosForm" property="animal2.raza"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
						 		</td>
							</c:if>					
						</TR>
						<tr>
				            <td class="celdaLabel" align="right"><strong>Categoría:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkCat" checked ><c:out value="${sinonimosForm.animal1.categoria}"/> </td>
								<td class="celdaLabelSinAlign" align="left">
								<input type="radio" name="checkCat"><c:out value="${sinonimosForm.animal2.categoria}"/></td>	
							</c:if>				
							 <c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal1">
											<bean:write name="sinonimosForm" property="animal1.categoria"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
						 		</td>		
						 		<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal2">
											<bean:write name="sinonimosForm" property="animal2.categoria"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
						 		</td>						 
							</c:if>					
						</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Nombre:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkNom"  checked><c:out value="${sinonimosForm.animal1.nombre}"/> </td>
								<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkNom"><c:out value="${sinonimosForm.animal2.nombre}"/></td>	
							</c:if>
							 <c:if test="${requestScope.mostrarUnir != true }">
							 	<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal1">
											<bean:write name="sinonimosForm" property="animal1.nombre"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
						 		</td>	
						 		<td class="celdaLabelSinAlign"  align="left">
						 			<logic:present name="sinonimosForm" property="animal2">
											<bean:write name="sinonimosForm" property="animal2.nombre"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
						 		</td>	
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Fecha Nac.:&nbsp;</strong></td>
				            <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkFnac" checked>
									<logic:present name="sinonimosForm" property="animal1.fechaNac">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${sinonimosForm.animal1.fechaNac}" />
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.fechaNac">	No Disponible </logic:notPresent>
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkFnac">
									<logic:present name="sinonimosForm" property="animal2.fechaNac">
										<fmt:formatDate pattern="dd/MM/yyyy" value="${sinonimosForm.animal2.fechaNac}" />
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.fechaNac"> No Disponible </logic:notPresent>
								</td>
							</c:if>
							 <c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
											<logic:present name="sinonimosForm" property="animal1.fechaNac">
												<fmt:formatDate pattern="dd/MM/yyyy" value="${sinonimosForm.animal1.fechaNac}" />
											</logic:present>
											<logic:notPresent name="sinonimosForm" property="animal1.fechaNac">	No Disponible </logic:notPresent>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1">	No Disponible </logic:notPresent>
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.fechaNac">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${sinonimosForm.animal2.fechaNac}"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.fechaNac"> No Disponible </logic:notPresent>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent>
								</td>
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Registro Ori.:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkRO" checked>
									<logic:present name="sinonimosForm" property="animal1.registroOrigen">
											<bean:write name="sinonimosForm" property="animal1.registroOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.registroOrigen"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkRO"  >
									<logic:present name="sinonimosForm" property="animal2.registroOrigen">
											<bean:write name="sinonimosForm" property="animal2.registroOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.registroOrigen"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
							 <c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:present name="sinonimosForm" property="animal1.registroOrigen">
											<bean:write name="sinonimosForm" property="animal1.registroOrigen"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal1.registroOrigen"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.registroOrigen">
											<bean:write name="sinonimosForm" property="animal2.registroOrigen"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.registroOrigen"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Registro ID.:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkRI" checked >
									<logic:present name="sinonimosForm" property="animal1.registroID">
											<bean:write name="sinonimosForm" property="animal1.registroID"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.registroID"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkRI"  >
									<logic:present name="sinonimosForm" property="animal2.registroID">
											<bean:write name="sinonimosForm" property="animal2.registroID"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.registroID"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
							<c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:present name="sinonimosForm" property="animal1.registroID">
											<bean:write name="sinonimosForm" property="animal1.registroID"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal1.registroID"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.registroID">
												<bean:write name="sinonimosForm" property="animal2.registroID"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.registroID"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Tambo:&nbsp;</strong></td>
				            <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkTam" checked>
									<logic:present name="sinonimosForm" property="animal1.establecimiento">
										 <bean:write name="sinonimosForm" property="animal1.establecimiento.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.establecimiento"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkTam"  >
									<logic:present name="sinonimosForm" property="animal2.establecimiento">
										<bean:write name="sinonimosForm" property="animal2.establecimiento.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.establecimiento"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
							 <c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:present name="sinonimosForm" property="animal1.establecimiento">
											 <bean:write name="sinonimosForm" property="animal1.establecimiento.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal1.establecimiento"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.establecimiento">
											<bean:write name="sinonimosForm" property="animal2.establecimiento.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.establecimiento"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Propietario:&nbsp;</strong></td>
				             <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkPro" checked>
									<logic:present name="sinonimosForm" property="animal1.propietario">
											<bean:write name="sinonimosForm" property="animal1.propietario.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.propietario"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkPro"  >
									<logic:present name="sinonimosForm" property="animal2.propietario">
										<bean:write name="sinonimosForm" property="animal2.propietario.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.propietario"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
							<c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:present name="sinonimosForm" property="animal1.propietario">
											<bean:write name="sinonimosForm" property="animal1.propietario.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal1.propietario"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.propietario">
											<bean:write name="sinonimosForm" property="animal2.propietario.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.propietario"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
								</td>
							</c:if>
					</TR>
					<tr>
				            <td class="celdaLabel" align="right"><strong>Tambo Criador:&nbsp;</strong></td>
				           	 <c:if test="${requestScope.mostrarUnir == true }">
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkTamC" checked >
									<logic:present name="sinonimosForm" property="animal1.establecimientoCriador">
											<bean:write name="sinonimosForm" property="animal1.establecimientoCriador.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.establecimientoCriador"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<input type="radio" name="checkTamC"  >
									<logic:present name="sinonimosForm" property="animal2.establecimientoCriador">
										<bean:write name="sinonimosForm" property="animal2.establecimientoCriador.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.establecimientoCriador">No Disponible</logic:notPresent> 
								</td>
							</c:if>
							<c:if test="${requestScope.mostrarUnir != true }">
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:present name="sinonimosForm" property="animal1.establecimientoCriador">
											<bean:write name="sinonimosForm" property="animal1.establecimientoCriador.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal1.establecimientoCriador"> No Disponible </logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:present name="sinonimosForm" property="animal2.establecimientoCriador">
											<bean:write name="sinonimosForm" property="animal2.establecimientoCriador.id"/>
										</logic:present>
										<logic:notPresent name="sinonimosForm" property="animal2.establecimientoCriador">No Disponible</logic:notPresent> 
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2">No Disponible</logic:notPresent> 
								</td>
							</c:if>
					</TR>	
					<tr>
				        <td class="celdaLabel" align="right"><strong>Propietario Criador:&nbsp;</strong></td>
				        <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkPropC" checked>
								<logic:present name="sinonimosForm" property="animal1.propietarioCriador">
									<bean:write name="sinonimosForm" property="animal1.propietarioCriador.id"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.propietarioCriador"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkPropC"  >
								<logic:present name="sinonimosForm" property="animal2.propietarioCriador">
									<bean:write name="sinonimosForm" property="animal2.propietarioCriador.id"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.propietarioCriador"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.propietarioCriador">
										<bean:write name="sinonimosForm" property="animal1.propietarioCriador.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.propietarioCriador"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.propietarioCriador">
										<bean:write name="sinonimosForm" property="animal2.propietarioCriador.id"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.propietarioCriador"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>	
					<tr>
				       	<td class="celdaLabel" align="right"><strong>Padre:&nbsp;</strong></td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.padre">
										<bean:write name="sinonimosForm" property="animal1.padre.regOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.padre"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.padre">
										<bean:write name="sinonimosForm" property="animal2.padre.regOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.padre"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
					</TR>
					<tr>
				        <td class="celdaLabel" align="right"><strong>Madre Genetica:&nbsp;</strong></td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.madreGenetica">
										<bean:write name="sinonimosForm" property="animal1.madreGenetica.regOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.madreGenetica"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.madreGenetica">
										<bean:write name="sinonimosForm" property="animal2.madreGenetica.regOrigen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.madreGenetica"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
					</TR>
					<tr>
						 <c:if test="${requestScope.mostrarUnir == true }">
					        <td class="celdaLabel" align="right"><strong>Padres:&nbsp;</strong></td>
								<td class="celdaLabelSinAlign"  align="left"><input type="radio" name="checkPAD" checked></td>
								<td class="celdaLabelSinAlign"  align="left"><input type="radio" name="checkPAD" ></td>
						</c:if>
					</TR>
					
					<tr>
				        <td class="celdaLabel" align="right"><strong>RP Senasa:&nbsp;</strong></td>
				         <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkRPS"  checked>
									<logic:present name="sinonimosForm" property="animal1.rpSenasa">
										<bean:write name="sinonimosForm" property="animal1.rpSenasa"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.rpSenasa"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkRPS" >
									<logic:present name="sinonimosForm" property="animal2.rpSenasa">
										<bean:write name="sinonimosForm" property="animal2.rpSenasa"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.rpSenasa"> No Disponible </logic:notPresent> 

							</td>
						</c:if>
						 <c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.rpSenasa">
										<bean:write name="sinonimosForm" property="animal1.rpSenasa"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.rpSenasa"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.rpSenasa">
										<bean:write name="sinonimosForm" property="animal2.rpSenasa"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.rpSenasa"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>
					<tr>
						<td class="celdaLabel" align="right"><strong>Codigo&nbsp;Verificador:&nbsp;</strong></td>
						 <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkCV"  checked>
								<logic:present name="sinonimosForm" property="animal1.codigoVerificador">
									<bean:write name="sinonimosForm" property="animal1.codigoVerificador"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.codigoVerificador"> No Disponible </logic:notPresent> 
							</td>				
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkCV">
								<logic:present name="sinonimosForm" property="animal2.codigoVerificador">
									<bean:write name="sinonimosForm" property="animal2.codigoVerificador"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.codigoVerificador"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.codigoVerificador">
										<bean:write name="sinonimosForm" property="animal1.codigoVerificador"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.codigoVerificador"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>				
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.codigoVerificador">
										<bean:write name="sinonimosForm" property="animal2.codigoVerificador"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.codigoVerificador"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Asoc:&nbsp;</strong></td>
				    	<c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOC"  checked>
								<logic:present name="sinonimosForm" property="animal1.asoc">
									<bean:write name="sinonimosForm" property="animal1.asoc"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.asoc"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOC">
								<logic:present name="sinonimosForm" property="animal2.asoc">
									<bean:write name="sinonimosForm" property="animal2.asoc"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.asoc"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.asoc">
										<bean:write name="sinonimosForm" property="animal1.asoc"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.asoc"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.asoc">
										<bean:write name="sinonimosForm" property="animal2.asoc"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.asoc"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Asop:&nbsp;</strong></td>
				    	<c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOP"  checked >
								<logic:present name="sinonimosForm" property="animal1.asop">
									<bean:write name="sinonimosForm" property="animal1.asop"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.asop"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOP">
								<logic:present name="sinonimosForm" property="animal2.asop">
									<bean:write name="sinonimosForm" property="animal2.asop"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.asop"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.asop">
										<bean:write name="sinonimosForm" property="animal1.asop"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.asop"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.asop">
										<bean:write name="sinonimosForm" property="animal2.asop"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.asop"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Asom:&nbsp;</strong></td>
				    	<c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOM" checked >
								<logic:present name="sinonimosForm" property="animal1.asom">
									<bean:write name="sinonimosForm" property="animal1.asom"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.asom"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkASOM">
								<logic:present name="sinonimosForm" property="animal2.asom">
									<bean:write name="sinonimosForm" property="animal2.asom"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.asom"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.asom">
										<bean:write name="sinonimosForm" property="animal1.asom"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.asom"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.asom">
										<bean:write name="sinonimosForm" property="animal2.asom"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.asom"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Dador&nbsp;Semen:&nbsp;</strong></td>
				    	<c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkDAD" checked>
								<logic:present name="sinonimosForm" property="animal1.dadorSemen">
									<bean:write name="sinonimosForm" property="animal1.dadorSemen"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.dadorSemen"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkDAD">
								<logic:present name="sinonimosForm" property="animal2.dadorSemen">
									<bean:write name="sinonimosForm" property="animal2.dadorSemen"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.dadorSemen"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.dadorSemen">
										<bean:write name="sinonimosForm" property="animal1.dadorSemen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.dadorSemen"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.dadorSemen">
										<bean:write name="sinonimosForm" property="animal2.dadorSemen"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.dadorSemen"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>numero&nbsp;Analisis&nbsp;ADN&nbsp;</strong></td>
				    	<c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkNAA" checked >
								<logic:present name="sinonimosForm" property="animal1.numeroAnalADN">
									<bean:write name="sinonimosForm" property="animal1.numeroAnalADN"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.numeroAnalADN"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkNAA">
								<logic:present name="sinonimosForm" property="animal2.numeroAnalADN">
									<bean:write name="sinonimosForm" property="animal2.numeroAnalADN"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.numeroAnalADN"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.numeroAnalADN">
										<bean:write name="sinonimosForm" property="animal1.numeroAnalADN"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.numeroAnalADN"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.numeroAnalADN">
										<bean:write name="sinonimosForm" property="animal2.numeroAnalADN"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.numeroAnalADN"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
					</TR>
					<tr>
				        <td class="celdaLabel" align="right"><strong>Donante&nbsp;</strong></td>
				        <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkDO" checked >
								<logic:present name="sinonimosForm" property="animal1.donante">
									<bean:write name="sinonimosForm" property="animal1.donante"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.donante"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkDO">
								<logic:present name="sinonimosForm" property="animal2.donante">
									<bean:write name="sinonimosForm" property="animal2.donante"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.donante"> No Disponible </logic:notPresent> 
							</td>		
						</c:if>	
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.donante">
										<bean:write name="sinonimosForm" property="animal1.donante"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.donante"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.donante">
										<bean:write name="sinonimosForm" property="animal2.donante"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.donante"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>		
						</c:if>	
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Mellizo&nbsp;</strong></td>
				    	 <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign" align="left">
								<input type="radio" name="checkME" checked>
								<logic:present name="sinonimosForm" property="animal1.mellizo">
									<bean:write name="sinonimosForm" property="animal1.mellizo"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.mellizo"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkME">
								<logic:present name="sinonimosForm" property="animal2.mellizo">
									<bean:write name="sinonimosForm" property="animal2.mellizo"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.mellizo"> No Disponible </logic:notPresent> 
							</td>
						</c:if>	
						 <c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign" align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.mellizo">
										<bean:write name="sinonimosForm" property="animal1.mellizo"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.mellizo"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.mellizo">
										<bean:write name="sinonimosForm" property="animal2.mellizo"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.mellizo"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>
						</c:if>	
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Transferencia&nbsp;</strong></td>
				    	 <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkTR"  checked >
								<logic:present name="sinonimosForm" property="animal1.transferencia">
									<bean:write name="sinonimosForm" property="animal1.transferencia"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.transferencia"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkTR">
								<logic:present name="sinonimosForm" property="animal2.transferencia">
									<bean:write name="sinonimosForm" property="animal2.transferencia"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.transferencia"> No Disponible </logic:notPresent> 
							</td>	
						 </c:if>
						 <c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.transferencia">
										<bean:write name="sinonimosForm" property="animal1.transferencia"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.transferencia"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.transferencia">
										<bean:write name="sinonimosForm" property="animal2.transferencia"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.transferencia"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>	
						 </c:if>
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Tipo&nbsp;Servicio&nbsp;</strong></td>
				    	 <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkTS" checked >
								<logic:present name="sinonimosForm" property="animal1.tipoServicio">
									<bean:write name="sinonimosForm" property="animal1.tipoServicio"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.tipoServicio"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign" align="left">
								<input type="radio" name="checkTS">
								<logic:present name="sinonimosForm" property="animal2.tipoServicio">
									<bean:write name="sinonimosForm" property="animal2.tipoServicio"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.tipoServicio"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.tipoServicio">
										<bean:write name="sinonimosForm" property="animal1.tipoServicio"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.tipoServicio"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign" align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.tipoServicio">
										<bean:write name="sinonimosForm" property="animal2.tipoServicio"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.tipoServicio"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
					</TR>
					<tr>
				    	<td class="celdaLabel" align="right"><strong>Apodo&nbsp;</strong></td>
				    	 <c:if test="${requestScope.mostrarUnir == true }">
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkAP" checked >
								<logic:present name="sinonimosForm" property="animal1.apodo">
									<bean:write name="sinonimosForm" property="animal1.apodo"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1.apodo"> No Disponible 
												</logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<input type="radio" name="checkAP">
								<logic:present name="sinonimosForm" property="animal2.apodo">
									<bean:write name="sinonimosForm" property="animal2.apodo"/>
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2.apodo"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
						<c:if test="${requestScope.mostrarUnir != true }">
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal1">
									<logic:present name="sinonimosForm" property="animal1.apodo">
										<bean:write name="sinonimosForm" property="animal1.apodo"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1.apodo"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent> 
							</td>
							<td class="celdaLabelSinAlign"  align="left">
								<logic:present name="sinonimosForm" property="animal2">
									<logic:present name="sinonimosForm" property="animal2.apodo">
										<bean:write name="sinonimosForm" property="animal2.apodo"/>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2.apodo"> No Disponible </logic:notPresent> 
								</logic:present>
								<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent> 
							</td>	
						</c:if>	
					</TR>
					<c:if test="${sinonimosForm.animal1.esSinonimo == true  || sinonimosForm.animal2.esSinonimo == true}">
						<tr>
					    	<td class="celdaLabel" align="right"><strong>Ya es Sinónimo&nbsp;</strong></td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal1">
										<logic:equal name="sinonimosForm" property="animal1.esSinonimo" value="true">
											Si
										</logic:equal>
										<logic:notEqual name="sinonimosForm" property="animal1.esSinonimo" value="true">
										No
										</logic:notEqual>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal1"> No Disponible </logic:notPresent>
								</td>
								<td class="celdaLabelSinAlign"  align="left">
									<logic:present name="sinonimosForm" property="animal2">
										<logic:equal name="sinonimosForm" property="animal2.esSinonimo" value="true">
										Si
										</logic:equal>
										<logic:notEqual name="sinonimosForm" property="animal2.esSinonimo" value="true">
										No
										</logic:notEqual>
									</logic:present>
									<logic:notPresent name="sinonimosForm" property="animal2"> No Disponible </logic:notPresent>
								</td>	
						</TR>
					</c:if>	
					<c:if test="${requestScope.mostrarChequear == true && requestScope.mensajeOK == null}">	
						<tr>
							<td class="celdaLabelSinAlign" colspan="3" align="center">
								<input type="button" value="Chequear" class="botones" onclick="chequear()">
							</td>	
						</tr>
					</c:if>	
					<c:if test="${requestScope.mostrarUnir == true && requestScope.mensajeOK == null}">	
						<tr>
							<td class="celdaLabelSinAlign" colspan="3" align="center">
								<input type="button" value="Unir" class="botones" onclick="unir()">
							</td>	
						</tr>
					</c:if>	
			</c:if>	
		</table>
</c:if>	
</table>			
</html:form>

 <script><!--

	
	function cargarAnimal(dato){
		var mUrl;
		if(document.forms[0].checkFiltro1[1].checked)
		{
				document.forms[0].elements["numReg"].value = "";
				if (isEmpty(document.forms[0].elements["animalId"].value)) {
					alert("Id animal es un campo obligatorio");
					return;
				}
				if (!isPosInteger(document.forms[0].elements["animalId"].value)) {
					alert("El campo id Animal debe ser un número");
					return;
				}
				if(dato=='1'){ // Se quiere cargar el animal 1
					if(document.forms[0].elements["animalId"].value == document.forms[0].elements["idAnimal2"].value)//verifico que el id cargado no es el mismo q ya estaba cargado
					{
						alert("El animal que se quiere cargar es igual al otro ya cargado");
						return;
					}
				}
				else{
					if(document.forms[0].elements["animalId"].value == document.forms[0].elements["idAnimal1"].value)
					{
						alert("El animal que se quiere cargar es igual al otro ya cargado");
						return;
					}
				}
				//mUrl = "realizarUnionSinonimosAction.do?method=buscarAnimalPorId&animalSelec="+dato;
				mUrl = "buscarAnimalPorId";
				document.forms[0].elements["numReg"].value="";
		}
		else
		{
				document.forms[0].elements["animalId"].value="";
				if (isEmpty(document.forms[0].elements["numReg"].value)) {
					alert("Número de Registro es un campo obligatorio");
					return;
				}
				if (!isPosInteger(document.forms[0].elements["numReg"].value)) {
					alert("Número de Registro debe ser un número");
					return;
				}	
				var aux = document.forms[0].elements["tipoReg"].value + " " + document.forms[0].elements["numReg"].value;
				if(dato=='1'){ // Se quiere cargar el animal 1
					if(document.forms[0].elements["regOriAni2"].value == aux)//verifico que el id cargado no es el mismo q ya estaba cargado
					{
						alert("El animal que se quiere cargar es igual al otro ya cargado");
						return;
					}
				}
				else{
					if(document.forms[0].elements["regOriAni1"].value == aux)
					{
						alert("El animal que se quiere cargar es igual al otro ya cargado");
						return;
					}
				}
				mUrl = "buscarAnimalPorRegistro";
				document.forms[0].elements["animalId"].value="";
		}
		document.forms[0].elements["animalSelec"].value = dato;
		document.forms[0].method.value = mUrl;
		document.forms[0].submit();
		//	window.location.href = mUrl;
	}

	
	
	function chequear(){
		var mUrl = "realizarUnionSinonimosAction.do?method=chequear";
		window.location.href = mUrl;
//			document.forms[0].method.value = "chequear";
			//document.forms[0].submit();
	}
	
	
	function unir(){
			if (document.forms[0].checkRp[0].checked) {
					var a1 = document.forms[0].elements["rp"];
					var a2 = document.forms[0].elements["rpAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["rp"];
					var a2 = document.forms[0].elements["rpAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkCat[0].checked) {
					var a1 = document.forms[0].elements["categoria"];
					var a2 = document.forms[0].elements["catAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["categoria"];
					var a2 = document.forms[0].elements["catAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkNom[0].checked) {
					var a1 = document.forms[0].elements["nombre"];
					var a2 = document.forms[0].elements["nombreAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["nombre"];
					var a2 = document.forms[0].elements["nombreAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkFnac[0].checked) {
					var a1 = document.forms[0].elements["fnac"];
					var a2 = document.forms[0].elements["fnacAni1"];
					a1.value = "1";
			}
			else {
					var a1 = document.forms[0].elements["fnac"];
					var a2 = document.forms[0].elements["fnacAni2"];
					a1.value = "2";
			}
			if (document.forms[0].checkRO[0].checked) {
					var a1 = document.forms[0].elements["regOri"];
					var a2 = document.forms[0].elements["regOriAni1"];
					//a1.value = a2.value;
					a1.value = "1";
			}
			else {
					var a1 = document.forms[0].elements["regOri"];
					var a2 = document.forms[0].elements["regOriAni2"];
					//a1.value = a2.value;
					a1.value = "2";
			}
			if (document.forms[0].checkRI[0].checked) {
					var a1 = document.forms[0].elements["regId"];
					var a2 = document.forms[0].elements["regIDAni1"];
					//a1.value = a2.value;
					a1.value = "1";
			}
			else {
					var a1 = document.forms[0].elements["regId"];
					var a2 = document.forms[0].elements["regIDAni2"];
					//a1.value = a2.value;
					a1.value = "2";
			}
			if (document.forms[0].checkTam[0].checked) {
					var a1 = document.forms[0].elements["tambo"];
					var a2 = document.forms[0].elements["tamboAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["tambo"];
					var a2 = document.forms[0].elements["tamboAni2"];
					a1.value = a2.value;
				
			}
			if (document.forms[0].checkPro[0].checked) {
					var a1 = document.forms[0].elements["prop"];
					var a2 = document.forms[0].elements["propAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["prop"];
					var a2 = document.forms[0].elements["propAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkTamC[0].checked) {
					var a1 = document.forms[0].elements["tamboCri"];
					var a2 = document.forms[0].elements["tamboCriAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["tamboCri"];
					var a2 = document.forms[0].elements["tamboCriAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkPropC[0].checked) {
					var a1 = document.forms[0].elements["propCri"];
					var a2 = document.forms[0].elements["propCriAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["propCri"];
					var a2 = document.forms[0].elements["propCriAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkPAD[0].checked) {
					var a1 = document.forms[0].elements["padres"];
					
					//a1.value = a2.value;
					a1.value = "1";
			}
			else {
					var a1 = document.forms[0].elements["padres"];
					
					//a1.value = a2.value;
					a1.value = "2";
			}
			
			
			if (document.forms[0].checkRPS[0].checked) {
					var a1 = document.forms[0].elements["rpSenasa"];
					var a2 = document.forms[0].elements["rpSenasaAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["rpSenasa"];
					var a2 = document.forms[0].elements["reSenasaAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkCV[0].checked) {
					var a1 = document.forms[0].elements["codigoVerificador"];
					var a2 = document.forms[0].elements["codigoVerificadorAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["codigoVerificador"];
					var a2 = document.forms[0].elements["codigoVerificadorAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkASOC[0].checked) {
					var a1 = document.forms[0].elements["asoc"];
					var a2 = document.forms[0].elements["asocAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["asoc"];
					var a2 = document.forms[0].elements["asocAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkASOP[0].checked) {
					var a1 = document.forms[0].elements["asop"];
					var a2 = document.forms[0].elements["asopAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["asop"];
					var a2 = document.forms[0].elements["asopAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkASOM[0].checked) {
					var a1 = document.forms[0].elements["asom"];
					var a2 = document.forms[0].elements["asomAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["asom"];
					var a2 = document.forms[0].elements["asomAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkDAD[0].checked) {
					var a1 = document.forms[0].elements["dadorSemen"];
					var a2 = document.forms[0].elements["dadorSemenAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["dadorSemen"];
					var a2 = document.forms[0].elements["dadorSemenAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkNAA[0].checked) {
					var a1 = document.forms[0].elements["numeroAnalADN"];
					var a2 = document.forms[0].elements["numeroAnalADNAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["numeroAnalADN"];
					var a2 = document.forms[0].elements["numeroAnalADNAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkDO[0].checked) {
					var a1 = document.forms[0].elements["donante"];
					var a2 = document.forms[0].elements["donanteAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["donante"];
					var a2 = document.forms[0].elements["donanteAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkME[0].checked) {
					var a1 = document.forms[0].elements["mellizo"];
					var a2 = document.forms[0].elements["mellizoAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["mellizo"];
					var a2 = document.forms[0].elements["mellizoAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkTR[0].checked) {
					var a1 = document.forms[0].elements["transferencia"];
					var a2 = document.forms[0].elements["transferenciaAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["transferencia"];
					var a2 = document.forms[0].elements["transferenciaAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkTS[0].checked) {
					var a1 = document.forms[0].elements["tipoServicio"];
					var a2 = document.forms[0].elements["tipoServicioAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["tipoServicio"];
					var a2 = document.forms[0].elements["tipoServicioAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkAP[0].checked) {
					var a1 = document.forms[0].elements["apodo"];
					var a2 = document.forms[0].elements["apodoAni1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["apodo"];
					var a2 = document.forms[0].elements["apodoAni2"];
					a1.value = a2.value;
			}
			if (document.forms[0].checkRaza[0].checked) {
					var a1 = document.forms[0].elements["razaElejida"];
					var a2 = document.forms[0].elements["raza1"];
					a1.value = a2.value;
			}
			else {
					var a1 = document.forms[0].elements["razaElejida"];
					var a2 = document.forms[0].elements["raza2"];
					a1.value = a2.value;
			}
			
			document.forms[0].method.value = "unir";
			document.forms[0].submit();
			
		}
	
	function isEmpty(inputStr) {
			if (inputStr == null || inputStr == "") {
				return true
			}
			return false
		}
	
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				return false
			}
		}
		return true
	}
	

--></script>
 
	


