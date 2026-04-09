<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<html:form action="/editarUsuarioValidate.do" method="post"> 
<input type="hidden" name="method" value="<c:out value='${action}'/>">

<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
	<tr> 
    	<td class=Titulo>
        	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	&nbsp;Error durante el envío de correo electrónico en el alta de un usuario
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
<!-- Fin Titulo de la pagina -->
<br>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
        <tr>
        	<td colspan="4">
        	<html:messages id="message1" message="true" property="errorEmail"/>          	
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
</table>
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	<tr> 
    	<td align="center">
    		<html:cancel styleClass="botones" onclick="bCancel=true;document.forms[0].submit();">
				<bean:message key="cancel"/>
			</html:cancel>
		</td>
	</tr>
</table>
</html:form>		

