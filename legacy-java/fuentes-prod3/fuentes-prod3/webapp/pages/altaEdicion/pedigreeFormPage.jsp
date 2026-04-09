<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/edicionAnimalPedigreeAction.do" method="post" enctype="multipart/form-data" >
<script language='javascript' src="calendar/popcalendar.js"></script>
	<input type="hidden"  name="metodo" value="<c:out value="${sessionScope.metodo}"/>">
	<input type="hidden"  name="method" value="<c:out value="${requestScope.action}"/>">
	
	<input type="hidden"  name="propietario" value="<c:out value="${pedigreeForm.idProp}"/>">
	
	<input type="hidden"  name="propietarioInt" value="<c:out value="${pedigreeForm.idPropInt}"/>">
	<input type="hidden"  name="nombrePropInt" value="<c:out value="${pedigreeForm.nombrePropInt}"/>">
	
	<input type="hidden"  name="propIntR" value="<c:out value="${sessionScope.idInter}"/>">
	<input type="hidden"  name="nomPropIntR" value="<c:out value="${sessionScope.nombreInter}"/>">
	
	<input type="hidden"  name="idTam" value="<c:out value="${pedigreeForm.idTam}"/>">
	<input type="hidden"  name="idPropCri" value="<c:out value="${pedigreeForm.idPropCri}"/>">
	<input type="hidden"  name="idTamCriador" value="<c:out value="${pedigreeForm.idTamCriador}"/>">
	<input type="hidden"  name="idMadre" value="<c:out value="${pedigreeForm.idMadre}"/>">
	<input type="hidden"  name="idPadre" value="<c:out value="${pedigreeForm.idPadre}"/>">

<input type="hidden"  name="nombreProp" value="<c:out value="${pedigreeForm.nombreProp}"/>">
	
	<input type="hidden"  name="nombreEstab" value="<c:out value="${pedigreeForm.nombreEstab}"/>">
	<input type="hidden"  name="nombrePropCri" value="<c:out value="${pedigreeForm.nombrePropCri}"/>">
	<input type="hidden"  name="nombreEstabCri" value="<c:out value="${pedigreeForm.nombreEstabCri}"/>">
	<input type="hidden"  name="madre" value="<c:out value="${pedigreeForm.madre}"/>">
	<input type="hidden"  name="padre" value="<c:out value="${pedigreeForm.padre}"/>">
	
	
	 <html:hidden property="error" value="${requestScope.error}"/>	
	
<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					
					<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Animal de Pedigree
						</c:when>
						<c:otherwise>
									&nbsp;Modificaci&oacute;n de Animal de Pedigree--&nbsp;&nbsp;Identificador:<c:out value="${pedigreeForm.idAnimal}"/>
						</c:otherwise>
					</c:choose>    
					
					    	
		            </td>
            </tr>
        </TABLE>
		 <TR>
			<TD class="TextoError" colspan="4"><c:out value="${requestScope.error}"/> </td>
		</tr>
		
		
			
			<br/>
			<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr>
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >	
					<TR>
						<TD class="celdaLabel">
							Nombre: 
						</TD>
						<TD class="celdaInput" colspan="3" >
							<html:text size="45" name="pedigreeForm" property="nombre" styleClass="Input10porc"/>
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Tara Genética: 
						</TD>
						<TD class="celdaInput" colspan="3" >
							<html:text size="45" name="pedigreeForm" property="taraG" styleClass="Input10porc"/>
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							RP: 
						</TD>
						<TD class="celdaInput" colspan="3">
							<html:text size="10" name="pedigreeForm" property="rp" styleClass="Input10porc"/>
		            	</TD>
					</TR>
					<tr>
						<td class="celdaLabel">Sexo:&nbsp;</td>
								<c:choose>
								<c:when test="${action == 'update'}">
									<c:if test="${pedigreeForm.sexo == 'H'}">
										<c:choose>
											<c:when test="${(pedigreeForm.tieneCalif == true)||(pedigreeForm.tieneEventos == true )}">
												 <td class="celdaLabelSinAlign" colspan="3"  align="center">Hembra&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modificar el sexo porque la hembra tiene eventos asociados o ya ha sido calificada
												</TD>
											</c:when>
										<c:otherwise>
											<td class="celdaInput" colspan="3">
												<html:select property="sexo" styleClass="formfields" style="width:100px">
														<html:option value="H"  >Hembra</html:option>
														<html:option value="M"  >Macho</html:option>
														
													</html:select>
											</td>		
										</c:otherwise>
										</c:choose>    

									</c:if>
									<c:if test="${pedigreeForm.sexo == 'M'}">
										<c:choose>
											<c:when test="${(pedigreeForm.tieneHijos == true)||(pedigreeForm.tieneEvt == true )}">
												 <td class="celdaLabelSinAlign" colspan="3"  align="center">Macho&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modificar el sexo porque este toro ha participado en un servicio
												</TD>
											</c:when>
										<c:otherwise>
											<td class="celdaInput" colspan="3">
												<html:select property="sexo" styleClass="formfields" style="width:100px">
														<html:option value="H"  >Hembra</html:option>
														<html:option value="M"  >Macho</html:option>
														
													</html:select>
											</td>		
										</c:otherwise>
										</c:choose>    
									</c:if>

								</c:when>
								<c:otherwise>
											<td class="celdaInput" colspan="3">
											<html:select property="sexo" styleClass="formfields" style="width:100px">
													<html:option value="H"  >Hembra</html:option>
													<html:option value="M"  >Macho</html:option>
													
												</html:select>
										</td>		
								</c:otherwise>
							</c:choose>    

										
					</tr> 
					
		             <TR>
								<TD class="celdaLabel">
									Fecha&nbsp;de&nbsp;Nacimiento&nbsp;:
								</TD>
								<TD class="celdaInput" colspan="3">
									<input name="fechaNac" type="text" id="fechaNac" value="<c:out value="${pedigreeForm.fechaNac}"/>" onclick="popUpCalendar(this, form.fechaNac, 'dd/mm/yyyy');" size="10">
								</TD>
						</TR>
						<TR>
						<TD class="celdaLabelSinAlign"  align="right">
							Foto:
						</TD>	
						<TD class="celdaInput" ><html:file size="36" property="foto" styleClass="Input10porc"/></td>	 
						 <c:choose>	
								<c:when test="${pedigreeForm.idFoto == null}">
										<td class="celdaLabelSinAlign" colspan="2" align="right">
										  <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/></td>
								</c:when>
								<c:otherwise>
										<td class="celdaLabelSinAlign" colspan="2" align="right">
											<a class="orange" align="right">
											<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${pedigreeForm.idFoto}"/> '/>
											</a>  
										</td>
								</c:otherwise>
							</c:choose>      
        					
					</TR>
					
					<tr>
						<td class="celdaLabel">Raza del Animal:</td>
						<td class="celdaInput" colspan="3">
												<html:select property="raza" styleClass="formfields" style="width:160px">
													<html:options collection="razas" property="id" labelProperty="nombre"/>
												</html:select>
										</td>		
					</tr>
					<tr>
						<td class="celdaLabel">Categoria:</td>
						<td class="celdaInput" colspan="3">
												<html:select property="categoria" styleClass="formfields" style="width:160px">
													<html:options collection="categorias" property="value"/>
												</html:select>
										</td>		
					</tr>
			
		</table>
				</td>		
			</tr>
		<tr>
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<TR>
						<TD class="celdaLabel">tipo RegORI:</TD>
						<TD class="celdaLabelSinAlign" align ="center" ><c:out value="${pedigreeForm.tipoRegOri}"/>
						<html:hidden name="pedigreeForm" property="tipoRegOri"/>
						        </td>			

						<TD class="celdaLabel">	nº&nbsp;RegORI&nbsp;&nbsp;</TD>
							
							<TD class="celdaLabelSinAlign" align ="center" ><c:out value="${pedigreeForm.numRegOri}"/>
						<html:hidden name="pedigreeForm" property="numRegOri"/>
						        </td>
					</TR>
					<TR>
						<TD class="celdaLabel">tipo RegID:</TD>
						<TD class="celdaLabelSinAlign" align ="center" ><c:out value="${pedigreeForm.tipoRegId}"/>
						<html:hidden name="pedigreeForm" property="tipoRegId"/>
						        </td>
						
						<TD class="celdaLabel">nº&nbsp;RegID&nbsp;&nbsp;</TD>
						<TD class="celdaLabelSinAlign" align ="center" ><c:out value="${pedigreeForm.numRegId}"/>
						<html:hidden name="pedigreeForm" property="numRegId"/>
						        </td>
							
		            	
					</TR>
					 <tr> 
                        <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
							<input type="button" value="registros" class="botones" onclick="setMethod('initRegistros')">
						</TD>
					</TR>	
				</table>
				</td>		
			</tr>
			<tr>
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					 <tr> 
                        <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
							<input type="button" value="comentarios" class="botones" onclick="setMethod('initComentarios')">
						</TD>
					</TR>	 
				</table>
			</td>		
			</tr>
			<tr>
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					 
					 <TR>
								<TD class="celdaLabel">Tambo:</TD>
									<c:choose>
								<c:when test="${pedigreeForm.tieneEventos == true}">
										<TD class="celdaLabelSinAlign" align="left"><div id="tamVisible" >	<c:out value="${pedigreeForm.nombreEstab}"/></div>
										</td>
										 <td class="celdaLabelSinAlign" colspan="2"  align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modifcar el tambo porque el animal tiene eventos asociados
									</TD>
								</c:when>
								<c:otherwise>
								<TD class="celdaLabelSinAlign" align="left"><div id="tamVisible" >	<c:out value="${pedigreeForm.nombreEstab}"/></div>
						        </td>
						         <td class="celdaLabelSinAlign"  align="right">
						            <input type="button" size="15" align="middle" value="Asignar" class="botones" name="buscar"  onClick="setMethod('initBuscarTambo')" >            
					           
					            </TD>
								<td class="celdaLabelSinAlign"   align="center">
						            <input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar"  onClick="limpiarTambo()" >            
					           
					            </TD>
									</c:otherwise>
							</c:choose>    
				</tr>
					 <TR>
								
								<TD class="celdaLabel">inter.
								
										<html:checkbox onclick="cambiarProp()" property="inter" value="true" styleId="che"/>
						  				<input type="hidden" onclick="cambiarProp()" name="inter" value="false">
				    		
								Propietario:</TD>
								<c:choose>
								<c:when test="${pedigreeForm.tieneEventos == true}">
									<TD class="celdaLabelSinAlign" align="left" >
									<div id="propVisible" >	<c:out value="${pedigreeForm.nombreProp}"/>  </div>
									
									</td>
									 <td class="celdaLabelSinAlign" colspan="2"  align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modificar el propietario porque el animal tiene eventos asociados
									</TD>
								</c:when>
								<c:otherwise>
											<TD class="celdaLabelSinAlign" align="left" >
												<div id="propVisible" >	<c:out value="${pedigreeForm.nombreProp}"/>  </div>
												
												</td>
												<c:choose>
													<c:when test="${pedigreeForm.inter == true}">
														 <td class="celdaLabelSinAlign"   align="right">
															<input type="button" size="15" align="middle" disabled="true" value="Asignar" class="botones" name="buscar"  id="bProp" onClick="setMethod('initBuscarPropietario')" >            
													   
														</TD>
														 <td class="celdaLabelSinAlign"   align="center">
															<input type="button" size="15" align="middle" disabled="true" value="Limpiar" class="botones" name="buscar" id="lProp" onClick="limpiarProp()" >            
													   
														</TD>
													</c:when>
													<c:otherwise>
													 <td class="celdaLabelSinAlign"   align="right">
													<input type="button" size="15" align="middle" value="Asignar" class="botones" name="buscar"  id="bProp" onClick="setMethod('initBuscarPropietario')" >            
											   
												</TD>
												 <td class="celdaLabelSinAlign"   align="center">
													<input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar" id="lProp" onClick="limpiarProp()" >            
											   
												</TD>
													</c:otherwise>
													</c:choose>
												
								</c:otherwise>
							</c:choose>    
					 </tr>
					
					 
					 <TR>
								<TD class="celdaLabel" >Tambo criador:</TD>
								<c:choose>
									<c:when test="${pedigreeForm.tieneEventos == true}">
										<TD class="celdaLabelSinAlign" align="left" ><div id="tamCriVisible" ><c:out value="${pedigreeForm.nombreEstabCri}"/></div>
										 <td class="celdaLabelSinAlign" colspan="2"  align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modifcar el tambo criador porque el animal tiene eventos asociados
									</TD>
								</c:when>
								<c:otherwise>
									<TD class="celdaLabelSinAlign" align="left" ><div id="tamCriVisible" ><c:out value="${pedigreeForm.nombreEstabCri}"/></div>
						        </td>
						         <td class="celdaLabelSinAlign"    align="right">
						            <input type="button" size="15" align="middle" value="Asignar" class="botones" name="buscar"  onClick="setMethod('initBuscarTamboCriador')" >            
					           </TD>
							   <td class="celdaLabelSinAlign"   align="center">
						            <input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar"  onClick="limpiarTamboCri()" >            
					           
					            </TD>
								</c:otherwise>
							</c:choose>    
					 </tr>
					  <TR>
								<TD class="celdaLabel">Propietario criador:</TD>
								<c:choose>
								<c:when test="${pedigreeForm.tieneEventos == true}">
									<TD class="celdaLabelSinAlign" align="left"  ><div id="propCriVisible" ><c:out value="${pedigreeForm.nombrePropCri}"/></div>
									 <td class="celdaLabelSinAlign" colspan="2"  align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No se puede modifcar el propietario criador porque el animal tiene eventos asociados
									</TD>
						        </td>
								</c:when>
								<c:otherwise>
										<TD class="celdaLabelSinAlign" align="left"  ><div id="propCriVisible" ><c:out value="${pedigreeForm.nombrePropCri}"/></div>
										</td>
										 <td class="celdaLabelSinAlign"   align="right">
											<input type="button" align="middle" value="Asignar" class="botones" name="buscar"  onClick="setMethod('initBuscarPropietarioCri')" >            
									   
										</TD>
										<td class="celdaLabelSinAlign"   align="center">
											<input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar"  onClick="limpiarPropCri()" >            
									   
										</TD>
								</c:otherwise>
							</c:choose>    
					 </tr>					
					 <TR>
								<TD class="celdaLabel" >Padre:</TD>
								<TD class="celdaLabelSinAlign" align="left"  ><div id="padreVisible" ><c:out value="${pedigreeForm.padre}"/></div>
						        </td>
						         <td class="celdaLabelSinAlign"   align="right">
						            <input type="button" align="middle" value="Asignar" class="botones" name="buscar"  onClick="setMethod('initBuscarPadre')" >            
					           </TD>
							   <td class="celdaLabelSinAlign"   align="center">
						            <input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar"  onClick="limpiarPadre()" >            
					           
					            </TD>
					</tr>
					<TR>
								<TD class="celdaLabel" >Madre:</TD>
								<TD class="celdaLabelSinAlign" align="left" ><div id="madreVisible" ><c:out value="${pedigreeForm.madre}"/></div>
						        </td>
						         <td class="celdaLabelSinAlign"   align="right">
						            <input type="button" align="middle" value="Asignar" class="botones" name="buscar"  onClick="setMethod('initBuscarMadre')" >            
					            </TD>
								<td class="celdaLabelSinAlign"   align="center">
						            <input type="button" size="15" align="middle" value="Limpiar" class="botones" name="buscar"  onClick="limpiarMadre()" >  </TD>
					 </tr>
					</table>
				</td>		
			</tr>
				<tr>
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >	
					<TR>
						<TD class="celdaLabel">dado: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="dado" />
						</TD>
						<TD class="celdaLabel">tserv: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="tserv" />
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">tran: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="tran" />
						</TD>
						<TD class="celdaLabel">fesb: </TD>
						<TD class="celdaInput" >
			           			<input name="fesb" type="text" id="fesb" value="<c:out value="${pedigreeForm.fesb}"/>" onclick="popUpCalendar(this, form.fesb, 'dd/mm/yyyy');" size="10">
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">anls: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="anls" />
						</TD>
						<TD class="celdaLabel">ftrf: </TD>
						<TD class="celdaInput" >
			           			<input name="ftrt" type="text" id="ftrt" value="<c:out value="${pedigreeForm.ftrt}"/>" onclick="popUpCalendar(this, form.ftrt, 'dd/mm/yyyy');" size="10">
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">dona: </TD>
						<TD class="celdaInput"  >
							<html:text size="10" name="pedigreeForm" property="dona" />
						</TD>
						<TD class="celdaLabel">fserv: </TD>
						<TD class="celdaInput" >
				         			<input name="fserv" type="text" id="fserv" value="<c:out value="${pedigreeForm.fserv}"/>" onclick="popUpCalendar(this, form.fserv, 'dd/mm/yyyy');" size="10">
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">mell: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="mell" />
						</TD>
						<TD class="celdaLabel">trns: </TD>
						<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="trns" />
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">apodo: </TD>
						<TD class="celdaInput"  >
							<html:text size="30" name="pedigreeForm" property="apodo" />
						</TD>
						<TD class="celdaLabel">rpti: </TD>
						<TD class="celdaInput"  >
							<html:text size="10" name="pedigreeForm" property="rpti" />
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">Fecha&nbsp;ultima&nbsp;obs.: </TD>
						<TD class="celdaLabelSinAlign" align="left" ><c:out value="${pedigreeForm.fobs}"/></td>
					</TR>
					</table>
				</td>		
			</tr>
			<tr>
			
			 <td align="center" class="celdaLabelSinAlign" align="center" colspan="4" >
			 <c:choose>
						<c:when test="${metodo != 'add'}">
				<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >	
					<TR>
						<TD class="celdaLabelSinAlign" align="left" colspan="4"><strong>Borrar Animal</strong></TD>
					</TR>
					<TR>
						<TD class="celdaLabelSinAlign" align="left" colspan="4">  - En caso que quiera inhabilitar este animal debe hacerlo presionando el boton "Registros" y allí podrá inhabilitar el registro</TD>
					</TR>
					<TR>
						<TD class="celdaLabelSinAlign" align="left" colspan="4">  - Si desea borrar el animal del sistema debe hacerlo presionando el boton "Borrar Animal" </TD>
					</TR>
					<TR>
						<TD class="celdaLabelSinAlign" align="left" colspan="4">  - Para que el animal pueda ser borrado no debe tener eventos asociados ni crias relacionadas </TD>
					</TR>
					
					<TR>
						 <td align="center">
						<input type="button" value="Borrar Animal" class="botones" onclick="borrar('eliminar')">
						</TD>
					</TR>
					
					</table>
					</c:when>
						<c:otherwise>
									
						</c:otherwise>
					</c:choose>    
				</td>		
			</tr>		
					
				</table>
			
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					
					<c:choose>
						<c:when test="${metodo == 'add'}">
	                   	<input type="button" value="Agregar" class="botones" onclick="agregar('add')">
						</c:when>
						<c:otherwise>
									<input type="button" value="Actualizar" class="botones" onclick="agregar('update')">
						</c:otherwise>
					</c:choose>    
				</TD>
			</TR>	
		</table>
				
		</html:form>
 <script>
	
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	function borrar(valor){
		
			if(confirm('¿Confirma la eliminacion del registro?')){ 
			
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    		}
    		
    	}
		function agregar(valor){
		
			
			if (isEmpty(document.forms[0].elements["numRegOri"].value)) {
				alert("numRegOri es obligatorio");
				return;
			}
			if(document.getElementById("che").checked == false){
				if (isEmpty(document.forms[0].elements["propietario"].value)) {
					alert("El propietario es obligatorio");
					return;
				}
			}
		
			if (!isPosInteger(document.forms[0].elements["numRegOri"].value)) {
				alert("numRegOri debe ser un número");
				return;
			}
			

			if (!isPosInteger2(document.forms[0].elements["dado"].value)) {
				alert("dado debe ser un número");
				return;
			}
			if (!isPosInteger2(document.forms[0].elements["tran"].value)) {
				alert("tran debe ser un número");
				return;
			}
			if (!isPosInteger2(document.forms[0].elements["anls"].value)) {
				alert("anls debe ser un número");
				return;
			}
			if (!isPosInteger2(document.forms[0].elements["tserv"].value)) {
				alert("tserv debe ser un número");
				return;
			}
			if (!isPosInteger2(document.forms[0].elements["mell"].value)) {
				alert("mell debe ser un número");
				return;
			}
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
		function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	
	function isPosInteger2(inputVal) {
		if(inputVal !=null){
			inputStr = inputVal.toString()
			for (var i = 0; i < inputStr.length; i++) {
				var oneChar = inputStr.charAt(i)
				if (oneChar < "0" || oneChar > "9") {
					return false
				}
			}
		}
		return true

	}
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString();
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				alert(oneChar);
				return false
			}
		}
		return true

	}
	function cambiarProp(){
	if(document.getElementById("che").checked == true){
		if (!isEmpty(document.forms[0].elements["propietario"].value)) {
			var idProp = document.forms[0].elements["propietario"];
			var idPropInt = document.forms[0].elements["propietarioInt"];
			if(idPropInt.value == '')
					idPropInt.value= document.forms[0].elements["propIntR"].value;
			idProp.value = idPropInt.value;
			var nomProp = document.forms[0].elements["nombreProp"];
			var nomPropInt = document.forms[0].elements["nombrePropInt"];
			if(nomPropInt.value =='')
				nomPropInt.value = document.forms[0].elements["nomPropIntR"].value;
			nomProp.value = nomPropInt.value;
			var elementoVisible = document.getElementById("propVisible");					
			var resultNode = document.createTextNode(nomPropInt.value);
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
			else{
					
					elementoVisible.appendChild(resultNode);
				}
			}
		else{
			if(document.forms[0].elements["metodo"].value == 'add'){
				var idProp = document.forms[0].elements["propietario"];
				var idPropInt = document.forms[0].elements["propIntR"];
				idProp.value = idPropInt.value;
				var nomProp = document.forms[0].elements["nombreProp"];
				var nomPropInt = document.forms[0].elements["nomPropIntR"];
				nomProp.value = nomPropInt.value;
				var elementoVisible = document.getElementById("propVisible");					
				var resultNode = document.createTextNode(nomPropInt.value);
				var oldChild = elementoVisible.childNodes[0];
				if(oldChild != null)
							elementoVisible.replaceChild(resultNode, oldChild);
				else{
					elementoVisible.appendChild(resultNode);
				}
				}
			else{
				var idProp = document.forms[0].elements["propietario"];
				var idPropInt = document.forms[0].elements["propietarioInt"];
				if(idPropInt.value == '')
					idPropInt.value= document.forms[0].elements["propIntR"].value;
				idProp.value = idPropInt.value;
				var nomProp = document.forms[0].elements["nombreProp"];
				var nomPropInt = document.forms[0].elements["nombrePropInt"];
				if(nomPropInt.value =='')
					nomPropInt.value = document.forms[0].elements["nomPropIntR"].value;
				nomProp.value = nomPropInt.value;
				var elementoVisible = document.getElementById("propVisible");					
				var resultNode = document.createTextNode(nomPropInt.value);
				var oldChild = elementoVisible.childNodes[0];
				if(oldChild != null)
							elementoVisible.replaceChild(resultNode, oldChild);
				else{
					elementoVisible.appendChild(resultNode);
				}
			}
		}
		document.getElementById("bProp").disabled = true;
		document.getElementById("lProp").disabled = true;
	  }
	  else{
	  	document.getElementById("bProp").disabled = false;
		document.getElementById("lProp").disabled = false;
	  }
	 
    }
	function limpiarPropCri() {
			var nomRegionalElem = document.forms[0].elements["idPropCri"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["nombrePropCri"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("propCriVisible");					
			var resultNode = document.createTextNode("");
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}
	function limpiarProp() {
			
			var nomRegionalElem = document.forms[0].elements["propietario"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["nombreProp"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("propVisible");					
					var resultNode = document.createTextNode("");
					var oldChild = elementoVisible.childNodes[0];
					
					if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}
	function limpiarTambo() {
			var nomRegionalElem = document.forms[0].elements["idTam"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["nombreEstab"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("tamVisible");					
			var resultNode = document.createTextNode("");
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}

	function limpiarTamboCri() {
			var nomRegionalElem = document.forms[0].elements["idTamCriador"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["nombreEstabCri"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("tamCriVisible");					
			var resultNode = document.createTextNode("");
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}
	function limpiarMadre() {
			var nomRegionalElem = document.forms[0].elements["idMadre"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["madre"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("madreVisible");					
			var resultNode = document.createTextNode("");
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}
	function limpiarPadre() {
			var nomRegionalElem = document.forms[0].elements["idPadre"];
			nomRegionalElem.value = "";
			var nomRegionalElem2 = document.forms[0].elements["padre"];
			nomRegionalElem2.value = "";
			var elementoVisible = document.getElementById("padreVisible");					
			var resultNode = document.createTextNode("");
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
	}
	    initializeMenus();	
</script>
 
	


