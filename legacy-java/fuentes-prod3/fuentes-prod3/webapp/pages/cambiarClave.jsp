<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<script type="text/javascript" src="<html:rewrite forward='md5JS'/>"></script>

<html:form action="/cambiarClaveAction.do" method="post"  onsubmit="return validate();">
		<html:hidden property="method" value="cambiarClave"/> 
		<html:javascript formName="cambiarClaveForm"/>	

		<table width="100%" class="bordeGris"" cellSpacing="0" cellPadding="0" align="center">
			<TR>
				<TD colspan="2" class="SubTitulo">
					Cambio de Contraseña: Usuario <c:out value="${sessionScope.CURRENTUSER.nombre}"/>
				</TD class="celdaInput">
			</TR>
			<tr><td colspan="2" class="linea" >&nbsp;</td></tr>
			
			<tr>
			<td colspan="2">
    		    <html:messages id="message" message="true" property="loginInvalid"/> 
					<span align="center" class="TextoError"><c:out value="${message}"/></span>
			</td>
    		</tr>
			
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">Password Actual</TD>
				<TD class="celdaInput">
					<html:password property="passwdOld"/>					
				</TD>
			</TR>
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">Password Nuevo</TD>
				<TD class="celdaInput">
					<html:password property="passwdNew1"/>					
				</TD>
			</TR>
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">Confirmacion Password Nuevo</TD>
				<TD class="celdaInput">
					<html:password property="passwdNew2"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">E-mail Actual:</TD>
				<TD class="celdaInput">
					<c:out value="${cambiarClaveForm.emailActual}"/>
				</TD>
			</TR>
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">Nuevo E-mail</TD>
				<TD class="celdaInput">
					<html:text size="45" name="cambiarClaveForm" property="email" styleClass="Input10porc"/>					
				</TD>
			</TR>
			<TR>
				<TD ALIGN="RIGHT" class="celdaLabel">Confirmacion de E-mail</TD>
				<TD class="celdaInput">
					<html:text size="45" name="cambiarClaveForm" property="emailConfirmacion" styleClass="Input10porc"/>					
				</TD>
			</TR>
			
		</TABLE>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
						</td>
					
				
			</TR>	
		</table>
</html:form>

<script language="JavaScript">
	
	function validarMail(){
		if (document.forms[0].elements["email"].value!=document.forms[0].elements["emailConfirmacion"].value)
			alert("Los mails son distintos, por favor corrija el error");
	}
	function validate() {
		if (document.forms[0].elements["email"].value!=document.forms[0].elements["emailConfirmacion"].value){
			alert("Los mails son distintos, por favor corrija el error");
			return false;
			}
		else{
			//alert("primer else");	
			//if (validateCambiarClaveForm(document.forms[0])) {
				if (document.forms[0].elements["passwdOld"].value!="" && document.forms[0].elements["passwdNew1"].value!="" && document.forms[0].elements["passwdNew2"].value!=""){
							//alert("al if");	
							if (validateCambiarClaveForm(document.forms[0])) {
								document.forms[0].passwdOld.value = hex_md5(document.forms[0].passwdOld.value);
								document.forms[0].passwdNew1.value = hex_md5(document.forms[0].passwdNew1.value);
								document.forms[0].passwdNew2.value = hex_md5(document.forms[0].passwdNew2.value);
								return true;
							}
							else	
								return validateCambiarClaveForm(document.forms[0]);					
				}
				else{
					//alert("segundo else");	
					if(document.forms[0].elements["passwdOld"].value=="" && document.forms[0].elements["passwdNew1"].value=="" && document.forms[0].elements["passwdNew2"].value==""){
						//alert("otro if");	
						return validateCambiarClaveForm(document.forms[0]);	
						}	
					else{
						alert("Falta informar alguna de las claves");
						return false;
						}
				}
				
			//}
			//else
				//return false;
		}
	}
</script>