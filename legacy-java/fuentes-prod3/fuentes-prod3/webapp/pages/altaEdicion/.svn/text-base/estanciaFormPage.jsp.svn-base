<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:form action="/editarEstanciaValidate.do" method="post" onsubmit="return validate();"> 
<script language='javascript' src="calendar/popcalendar.js"></script>
	<html:hidden name="estanciaForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	
	<html:javascript formName="estanciaForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	
            		<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Establecimiento
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Establecimiento
						</c:otherwise>
					</c:choose>            	
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
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Establecimiento
            </td>
        </tr>
        
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
        
       <tr>
			<td class="celdaLabel">
				Id:				
			</td>
			<td class="celdaInput">
				<c:out value="${estanciaForm.id}"/>
						
			</td>
		</tr>	       
					
		<tr>
			<td class="celdaLabel">
				Nombre: <span class="required">*</span>
			</td>
			<td class="celdaInput">
				<html:text size="45" maxlength="100" name="estanciaForm" property="nombre" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>	
		<TR>
			<TD class="celdaLabel">
				Cuit/Cuil: 
			</TD>
			<TD class="celdaInput" colspan="2">
				<html:text size="45" name="estanciaForm" property="cuit" styleClass="Input100porc"/>
			</td>		       
		</TR>
		<TR>
			<TD class="celdaLabel">
				Cuig: 
			</TD>
			<TD class="celdaInput" colspan="2">
				<html:text size="45" name="estanciaForm" property="cuig" styleClass="Input100porc"/>
			</td>		       
		</TR>
		<TR>
			<TD class="celdaLabel">
				Renspa: 
			</TD>
			<TD class="celdaInput" colspan="2">
				<html:text size="45" name="estanciaForm" property="renspa" styleClass="Input100porc"/>
			</td>		       
		</TR>
		<tr>
			<td class="celdaLabel">
				Activo?: <span class="required">*</span>
			</td>
			
			
			<html:hidden name="estanciaForm" property="activoPropietario"/>
		
												<c:choose>
													<c:when test="${estanciaForm.activoPropietario == 'false'}">
														<td class="celdaLabelSinAlign" colspan="2" align="left">Inactiva por la baja de su propietario</td>
													</c:when>
													<c:otherwise>
															<td class="celdaInput" colspan="2">
																	<html:checkbox property="activo" value="true" />
													  				<input type="hidden" name="activo" value="false">
																	</td>
															
													</c:otherwise>
												</c:choose>
		</tr>
		<tr>
			<td class="celdaLabel">
				Propietario: <span class="required">*</span> 	
			<td class="celdaInput">
				<html:select property="idPropietario" styleClass="Input100porc">
					<html:options collection="propietarios" property="id" labelProperty="idNombre" />
				</html:select>	
			</td>
		</tr>
		<tr>
        	<c:if test="${action == 'add'}">	
							<td class="celdaLabel">Fecha de Alta</td>
						</c:if>
				<c:if test="${action == 'update'}">	
							<td class="celdaLabel">Fecha de Modificación</td>
						</c:if>
							<td class="celdaInput">
								<input name="fechaLog" type="text" id="fechaLog" value="<c:out value="${estanciaForm.fechaLog}"/>" onclick="popUpCalendar(this, form.fechaLog, 'dd/mm/yyyy');" size="10">
					    	</td>
					</tr>	
		<tr>
				<td class="celdaLabel" align="center"><strong>Lugares de Contacto:</strong></td>
				<td class="celdaLabel" align="center"></td>
		</tr>		 
		<tr>
				<td colspan="2">		
					<display:table name="ubicaciones" align="center" class="its" id="ubicacion" >				
					        <display:column align="left"  property="nombre" title="Nombre"/>
					        <display:column align="left"  property="provinciaRegion" title=" Provincia Region"/>
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

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:submit styleClass="botones">
					<bean:message key="submit"/>
				</html:submit>
				<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel>
			</td>
		</tr>	
	</table>
</html:form>

<script>
	function validate() {
		return validateEstanciaForm(document.forms[0]);
	}
	
	initializeMenus();
</script>


