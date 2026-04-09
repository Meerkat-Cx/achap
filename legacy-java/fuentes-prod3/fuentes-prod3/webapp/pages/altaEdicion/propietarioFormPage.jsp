<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarPropietarioValidate.do" method="post" onsubmit="return validate();">
<script language='javascript' src="calendar/popcalendar.js"></script>
			<html:javascript formName="propietarioForm"/>

			<html:hidden name="propietarioForm" property="idProp"/>				
			<html:hidden name="propietarioForm" property="actionBack" styleId="actionBack"/>	
				
			<input type="hidden" name="method" value="<c:out value="${propietarioForm.actionBack}"/>">
			
				<logic:notPresent name="propietarioForm" property="idProp">
		<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Alta de Propietario</FONT></TD>
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
				</logic:notPresent>
			<br/>
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				<logic:present name="propietarioForm" property="idProp">
				<TR> 
                        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="5"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Propietario </TD>
                 </TR>
				</logic:present>
				<logic:notPresent name="propietarioForm" property="idProp">
				<TR> 
                        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="5"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Datos del Propietario</TD>
                 </TR>

				</logic:notPresent>		
				
			</table>
			 <tr>
        	<td colspan="2">
        	<html:messages id="message1" message="true" property="uniqueConstraint"/>          	
			<c:if test="${message1!='' and message1!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
				<tr >				
					<td class="TextoError" colspan="3">
						<c:out value="${message1}"/>
					</td>
				</tr>
	         	</table>
	          	<br>
			</c:if>
        	</td>
        </tr>
			<br>
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >				
					<TR>
						<td class="celdaLabel">Id: </td>
						<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<bean:write name="propietarioForm" property="idProp"/>
						</td>
					</TR>

					<TR>
						<TD class="celdaLabel">
							Nombre: <span class="required">*</span>
						</TD>
						<TD class="celdaInput" colspan="4">
							<html:text size="45" name="propietarioForm" property="nombre" styleClass="Input100porc"/>
		            	</TD>
		            	
					</TR>
					<TR>
						<TD class="celdaLabel">
							Cuit/Cuil: 
						</TD>
						<TD class="celdaInput" colspan="4">
							<html:text size="45" name="propietarioForm" property="cuit" styleClass="Input100porc"/>
		            	</TD>
		            	
					</TR>
					<TR>
			<TD class="celdaLabel">
				Cuig: 
			</TD>
			<TD class="celdaInput" colspan="4">
				<html:text size="45" name="propietarioForm" property="cuig" styleClass="Input100porc"/>
			</td>		       
		</TR>
		<TR>
			<TD class="celdaLabel">
				Renspa: 
			</TD>
			<TD class="celdaInput" colspan="4">
				<html:text size="45" name="propietarioForm" property="renspa" styleClass="Input100porc"/>
			</td>		       
		</TR>
					<TR>
						<TD class="celdaLabel">
							Prefijo: 
						</TD>
						<TD class="celdaInput" colspan="4">
							<html:text maxlength="20 "size="45" name="propietarioForm" property="prefijo" styleClass="Input100porc"/>
		            	</TD>
		            	
					</TR>
					<TR>
						<TD class="celdaLabel">
							E-mail: 
						</TD>
						<TD class="celdaInput" colspan="4">
							<html:text size="45" name="propietarioForm" property="mail" styleClass="Input100porc"/>
		            	</TD>
		            	
					</TR>
					
					<TR>	
						<TD class="celdaLabel">
							Persona Física?: 
						</TD>
					
						<td class="celdaInput" colspan="4" >
										<html:checkbox property="esPersonaFisica" value="true" />
						  				<input type="hidden" name="esPersonaFisica" value="false">
				    	</td>
						
					</TR>
					<TR>	
						<TD class="celdaLabel">
							Activo?: 
						</TD>
					
						<td class="celdaInput" colspan="4">
										<html:checkbox property="activo" value="true" />
						  				<input type="hidden" name="activo" value="false">
				    	</td>
				    	
					
					</TR>
					<TR>
						<TD class="celdaLabel">
							# Socio A.C.H.A.: 
						</TD>
						
						<TD class="celdaInput" colspan="4">
							<html:text size="22" name="propietarioForm" property="socio" styleClass="Input100porc"/>
						</TD>
						
					</TR>
					<TR>
						<TD class="celdaLabel">
							# HAR: 
						</TD>
						<TD class="celdaInput" colspan="4">
							<html:text size="22" name="propietarioForm" property="har" styleClass="Input100porc"/>
						</TD>
						
						
					</TR>
					<tr>
        	
						<c:if test="${propietarioForm.actionBack == 'add'}">	
							<td class="celdaLabel">Fecha de Alta</td>
						</c:if>
						<c:if test="${propietarioForm.actionBack == 'update'}">	
							<td class="celdaLabel">Fecha de Modificación</td>
						</c:if>
							<td class="celdaInput">
									<input name="fechaLog" type="text" id="fechaLog" value="<c:out value="${propietarioForm.fechaLog}"/>" onclick="popUpCalendar(this, form.fechaLog, 'dd/mm/yyyy');" size="10">
											<br/>
					    	</td>
							<td class="celdaLabel" colspan="3"></td>
			        	
			        </tr>	
		<tr>
			<td class="celdaLabel" align="center"><strong>Lugares de Contacto:</strong></td>
			
			<td class="celdaLabel" align="center" colspan="5"></td>
		</tr> 		
		<tr>
			<td colspan="7">		
				<display:table name="ubicaciones" align="center" class="its" id="ubicacion" >				
						<display:column align="left"  property="nombre" title="Nombre"/>
						<display:column align="left"  property="provinciaRegion" title="Provincia Region"/>
						<display:column align="left"  property="ciudad" title="Ciudad"/>
						<display:column align="left"  property="pais" title="Pais"/>
						<display:column align="left"  property="direccion" title="Direccion"/>
						<display:column align="left"  property="codigoPostal" title="Codigo Postal"/>
						<display:column align="left"  property="mail" title="Mail"/>
						<display:column align="left"  property="telefono" title="Telefono"/>   					        
						<display:setProperty name="basic.msg.empty_list">
							<h1 class="TextoVerde" align="center"><bean:message key="displayTag.basic.msg.empty_list" /></h1>
						</display:setProperty>
				</display:table>
			</td>
		</tr>
		
					
					
	</table>

		<br>
				
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >

					
					<tr>
						<TD class="celdaInput" colspan="6"> 
						<strong>Numeros de S.R.A. Asigandos: </strong>
						</TD>					
					</tr>
					
					
					<tr>
						<td class="celdaLabelSinWidth" width="30%"><strong>Raza</strong>
						</td>
						<td class="celdaLabelSinWidth" width="15%"><strong>Numero</strong>
						</td>
						<td class="celdaLabelSinWidth" width="15%"><strong>Cuig</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Estab</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Fecha Alta</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Fecha Inactivacion</strong>
						</td>
						
					</tr>
						<c:forEach items="${requestScope.expds}" var="expdActual">
							<tr>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.raza.nombre}"/>
								</td>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.numero}"/>
								</td>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.cuig}"/>
								</td >
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.estab}"/>
								</td>
								<td class="celdaLabelSinWidth">
								<fmt:formatDate pattern="dd/MM/yyyy" value="${expdActual.fechaAlta}" />
								</td>
								<td class="celdaLabelSinWidth">
									<fmt:formatDate pattern="dd/MM/yyyy" value="${expdActual.fechaBaja}" />
								</td>
								
								
							</tr>
						</c:forEach>
						<tr>
						</tr>	
					<TR>
						<td class="celdaLabelSinWidth" align="center" colspan="6">
							<input id="botonAgregarSRA" type="button" value="Agregar SRA" 
								class="botones" onclick="agregarNroSRA()">
								<input id="botonEliminarSRA" type="button" value="Eliminar SRA" 
								class="botones" onclick="eliminarNroSRA()">
								<input id="botonCambiarEstadoSRA" type="button" value="Cambiar Estado SRA"
								class="botones" onclick="cambiarEstadoSRA()">
						</td>
						
						
							
					</TR> 										
				</table>
				
				</br>	

				
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
					<a align="right" href="javascript:MM_openBrWindow('editarPropietarioValidate.do?method=mostrarEstablecimientos&propietarioId=<c:out value="${propietarioForm.idProp}"/>','','scrollbars=yes,resizable=yes,width=900,height=350,top=100,left=100')">
					Tambos
					</a>
				</td>
			</tr>
	 </table>

	 </br>

	 </br>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
					<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
						<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						<a align="right" href="javascript:MM_openBrWindow('editarPropietarioValidate.do?method=mostrarEstancias&propietarioId=<c:out value="${propietarioForm.idProp}"/>','','scrollbars=yes,resizable=yes,width=900,height=350,top=100,left=100')">
						Establecimientos
						</a>
					</td>
			</tr>
	</table>	
				
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
						</td>
					<td align="center">
							<input id="botonLimpiar" type="button" value="Cancelar" 
								class="botones" onclick="cancelar()">

						</td>
				
			</TR>	
		</table>
				
		</html:form>
 <script>

	function cancelar() {
		var mUrl = "editarPropietarioValidate.do?method=init";
		window.location.href = mUrl;
	}

	function validate() {	
			
			return validatePropietarioForm(document.forms[0]);		
	}
		
		function setMethod(){			
			if(validatePropietarioForm(document.forms[0])==true){
				//var metodo = document.getElementById("actionBack").value;
				//document.forms[0].method.value = metodo;
	    		document.forms[0].submit();
			}
			else
				return false;
    	}

		function isEmpty(inputStr) {
			if (inputStr == null || inputStr == "") {
				return true;
			}
			return false;
		}
	
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				return false;
			}
		}
		return true;
	}
	
	function agregarNroSRA() {
		document.forms[0].method.value = "initAgregarNroSRA";
    	document.forms[0].submit();
	}
	function eliminarNroSRA() {
		document.forms[0].method.value = "initEliminarNroSRA";
    	document.forms[0].submit();
	}
	
	function cambiarEstadoSRA() {
		document.forms[0].method.value = "initCambiarEstadoSRA";
    	document.forms[0].submit();
	}
	function MM_openBrWindow(theURL,winName,features) { //v2.0
		window.open(theURL,winName,features);
	}
	initializeMenus();

</script>
 
	


