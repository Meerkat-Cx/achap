<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<script type="text/javascript" src="<html:rewrite forward='md5JS'/>"></script>

<html:form action="/login.do" method="post" focus="userName" onsubmit="return doSubmit();">
	<html:hidden property="method" value="login"/>
	<table width="100%" class="bordeGris"" cellSpacing="0" cellPadding="0" align="center" >
		<tr>
			<td class="SubTitulo" colspan="2">
            	<bean:message key="login.message"/>
			</td>
		</tr>
        <tr>
	        <td colspan="2">
         	<html:messages id="message" message="true" property="loginInvalid"/> 
				<span align="center" class="TextoError"><c:out value="${message}"/></span>
			</td>
        </tr>
		<tr>
			<td class="celdaLabel"><bean:message key="userId"/></td>
			<td class="celdaInput">
                <html:text
                        altKey="userIdAltKey"
                        titleKey="userIdTitleKey"
                        property="userName" />
            </td>
		</tr>
		<tr>
			<td class="celdaLabel"><bean:message key="password"/></td>
			<td class="celdaInput">
                <html:password altKey="passwordAltKey" titleKey="passwordTitleKey"
                        property="password" redisplay="false" />
            </td>
		</tr>

		<tr><td colspan="2" >&nbsp;</td></tr>

		<tr><td colspan="2" class="linea" >&nbsp;</td></tr>

        <tr>
            <td colspan="2" align="center">
             <html:submit
                        altKey="submitAltKey"
                        onmouseover="this.style.color='#fbe249';"
                        onmouseout="this.style.color='#333';"
                        styleClass="Botones"
                        property="submitButton"
                        titleKey="submitTitleKey"
                        accesskey="S"
                        onclick="bCancel=false;">
                    <bean:message key="submit"/>
                </html:submit>

                <html:cancel
                        altKey="cancelAltKey"
                        onmouseover="this.style.color='#fbe249';"
                        onmouseout="this.style.color='#333';"
                        styleClass="Botones"
                        titleKey="cancelTitleKey"
                        accesskey="C"
                        onclick="bCancel=true;">
                    <bean:message key="cancel"/>
                </html:cancel>

            </td>
        </tr>

	</table>
</html:form>

<script>
	function doSubmit() {			
			document.forms[0].password.value = hex_md5(document.forms[0].password.value);			
	}
</script>

