<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarUsuarioValidate.do" method="post" onsubmit="return setSubmit();"> 
	<html:hidden name="usuarioForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	
	<html:javascript formName="usuarioForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2>
            	
            	<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Usuario
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Usuario
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
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="4">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Usuario
            </td>
        </tr>
        
        <tr>
        	<td colspan="4">
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
				Nombre: <span class="required">*</span>
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="100" name="usuarioForm" property="nombre" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Apellido: <span class="required">*</span>				
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="usuarioForm" property="apellido" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Activo?:
			</td>
			<td class="celdaInput" colspan="3">
				<html:checkbox property="activo" value="true" />
  				<input type="hidden" name="activo" value="false">
				
				
		    </td>
		</tr>
		<c:if test='${action == "update"}'>
		<tr>
			<td class="celdaLabel">
				Regenerar Clave:
			</td>
			<td class="celdaInput" colspan="3">
				<html:checkbox property="regenerar" value="true" />
  				<input type="hidden" name="regenerar" value="false">
			</td>
		</tr>
		</c:if>
		<tr>
			<td class="celdaLabel">
				Usuario: <c:if test='${action != "update"}'><span class="required">*</span></c:if>				
			</td>
			<td class="celdaInput" colspan="3">
				<c:if test='${action == "update"}'>
					<c:out value="${usuarioForm.username}"/>
					<html:hidden name="usuarioForm" property="username"/>
				</c:if>
				<c:if test='${action == "add"}'>
					<html:text size="15" maxlength="20" name="usuarioForm" property="username" styleClass="Input100porc"/>
				</c:if>
				<br/>
					
		    </td>
		</tr>
		
		<tr>
			<td class="celdaLabel">
				Rol: <span class="required">*</span> 	
			<td class="celdaInput" colspan="3">
				<html:select styleId="comboRoles" property="rolId" styleClass="Input100porc" onchange="javascript:changeRol(this)">
					<html:options collection="roles" property="id" labelProperty="nombre" />
				</html:select>	
				<br/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Contacto:  	
			</td>
			<TD class="celdaInput" colspan="2">

			<div id="elementoContacto">
			    <input type="hidden" name="contactoHidden" value="(<c:out value="${usuarioForm.contacto.id}"/>) <c:out value="${usuarioForm.contacto.nombreContacto}"/>" id="contactoHidden">
                (<c:out value="${usuarioForm.contacto.id}"/>)  <c:out value="${usuarioForm.contacto.nombreContacto}"/>
            </div>
			</td>
            <td class="celdaLabel" width="30%">
          		<!-- si el usuario logueado es administrador entonces deshabilito el boton -->            
	            <c:choose>
						<c:when test="${usuarioForm.rolId==1 || usuarioForm.rolId==3 || usuarioForm.rolId==4 || usuarioForm.rolId==null || usuarioForm.rolId ==7 || usuarioForm.rolId==8 || usuarioForm.rolId==9}">
							<input id="botonSeleccionarContacto" type="button" value="Buscar Contacto" 
								class="botones" onclick="setMethod('initSeleccionarContacto')" disabled="true">
						</c:when>
						<c:otherwise>
							<input id="botonSeleccionarContacto" type="button" value="Buscar Contacto" 
								class="botones" onclick="setMethod('initSeleccionarContacto')">	
						</c:otherwise>
				</c:choose>		                        
  	        </TD>
		</tr>
		<tr>
			<td class="celdaLabel">
				Email: <span class="required">*</span>				
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="30" maxlength="50" name="usuarioForm" property="email" styleClass="Input100porc"/>
		    </td>
		</tr>
	</table>

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
        		<td align="center">
        					<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
        		</td>			
				<td align="center"><html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel></td>
			</td>
		</tr>	
	</table>
</html:form>

<script>

	function validate() {
		return validateUsuarioForm(document.forms[0]);
	}
	function setSubmit(){		
		if(((document.forms[0].rolId.value == 2)||(document.forms[0].rolId.value == 5)||(document.forms[0].rolId.value == 6))
			&&(document.forms[0].contactoHidden.value=='() ')){			
			alert('Debe Asignar un Contacto al usuario' )
			return false;
		}
		else {			
			return validateUsuarioForm(document.forms[0]);
		}
	}
	function setMethod(valor){
		document.forms[0].method.value = valor;
    	document.forms[0].submit();
    }
    
	function changeRol(obj) {
		var rolNombre = obj.options[obj.selectedIndex].text;		
		var botonSeleccionarContacto = document.getElementById("botonSeleccionarContacto");
		if (rolNombre == "ADMINISTRADOR" || rolNombre == "GENERAL" || rolNombre == "TECNICA" || rolNombre == "DATAENTRY" || rolNombre == "SRA" || rolNombre == "EXTERNO") {
			botonSeleccionarContacto.disabled = true;
			botonSeleccionarContacto.style.backgroundColor = "gray";
			var elem = document.getElementById("elementoContacto");
			var oldChild = elem.childNodes[0];
			var emptyChild = document.createTextNode("");
			elem.replaceChild(emptyChild, oldChild);		
		}
		else {
			botonSeleccionarContacto.disabled = false;
			botonSeleccionarContacto.style.backgroundColor = "529b28";
		}
		initializeMenus();
	}
	var botonSeleccionarContacto = document.getElementById("comboRoles");
	window.onload = changeRol(botonSeleccionarContacto);
</script>


