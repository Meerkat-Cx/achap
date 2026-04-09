<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarSistemaValidate.do" method="post" onsubmit="return validate();">
	<html:javascript formName="sistemaForm"/>
	<html:hidden name="sistemaForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
    			<tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="2"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta Sistemas Inform&aacute;ticos
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Sistemas Inform&aacute;ticos
						</c:otherwise>
					</c:choose>            	
		            </td>
            </tr>
		</table>
		
		<br/>
		
		<!-- MENSAJE DE ERROR  -->
		<html:messages id="message1" message="true" property="uniqueConstraint"/>          	
		<c:if test="${message1 != '' and message1 != null}">	
			<table width="90%" border="0" align="center" class="texto_error">
				<tr >				
					<td class="TextoError">
						<c:out value="${message1}"/>
					</td>
				</tr>
         	</table>
          	<br>
		</c:if>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >			
			<TR>
				<TD class="celdaLabel">
					Nombre: <span class="required">*</span>
				</TD>
				<TD class="celdaInput">
					<html:text size="45" name="sistemaForm" property="nombre" styleClass="Input100porc" maxlength="60"/>
            	</TD>
			</TR>
			<TR>
				<TD class="celdaLabel">
					Versión: <span class="required">*</span>				
				</TD>
				<TD class="celdaInput">
					<html:text size="22" name="sistemaForm" property="version" styleClass="Input100porc" maxlength="20"/>
				</TD>
			</TR>
			<tr>
				<td class="celdaLabel">
					Comentarios del Sistema: 
				</td>
				<td class="celdaInput">
					<html:textarea rows="4" cols="44" name="sistemaForm" property="comentario" styleClass="Input100porc"/>
				</td>			
			</tr>
		</table>

		<br/>

			
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
                 <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="2"><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
		            width=11 align=absMiddle>&nbsp;Datos Generales del Responsable del Sistema Inform&aacute;tico</TD>
                      </TR>			
			<TR>
				<TD class="celdaLabel">
					Empresa de Software: <span class="required">*</span>
				</TD>
				<TD class="celdaInput">
					<html:text size="45" name="sistemaForm" property="nombreEmpresa" styleClass="Input100porc" maxlength="60"/>
					
            	</TD>
			</TR>
			<TR>
				<TD class="celdaLabel">
					Apellido: <span class="required">*</span>
				</TD>
				<TD class="celdaInput">
					<html:text size="45" name="sistemaForm" property="apellido" styleClass="Input100porc" maxlength="60"/>
					
            	</TD>
			</TR>
			<TR>
				<TD class="celdaLabel">
					Nombres: <span class="required">*</span>
				</TD>
				<TD class="celdaInput">
					<html:text size="45" name="sistemaForm" property="nombreResponsable" styleClass="Input100porc" maxlength="60"/>					
            	</TD>
			</TR>
			
			<TR>
				<TD class="celdaLabel">
					Tipo de Documento: 
				</TD>
				<TD class="celdaInput">
								<html:select property="tipoDocumento" styleClass="Input100porc">
								    <html:option value="LC">LC</html:option>
   								    <html:option value="LE">LE</html:option>
								    <html:option value="DNI">DNI</html:option>
								    <html:option value="CI">CI</html:option>								       								   
								</html:select>	
				</TD>
			</TR>
			<TR>
				<TD class="celdaLabel">
					Número de Documento:	
				</TD>
				<TD class="celdaInput">
					<html:text size="22" name="sistemaForm" property="numeroDocumento" styleClass="Input100porc"/>					
            	</TD>
			</TR>	
			<tr>
				<td class="celdaLabel">
					Comentarios del Responsable:
				</td>
				<td class="celdaInput">
					<html:textarea rows="4" cols="44" name="sistemaForm" property="comentarioResponsable" styleClass="Input100porc" />
				</td>			
			</tr>
			</table>
								
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
               <tr> 
                    <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
							<html:cancel styleClass="botones" onclick="bCancel=true;" >
								<bean:message key="cancel"/>
							</html:cancel>
					</TD>
				</TR>	
		</table>			
			

</html:form>
<script>

	function validate() {
		return validateSistemaForm(document.forms[0]);
	}
</script>

