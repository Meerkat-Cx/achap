<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 
<html:form action="/edicionConjuntoAtributos.do" method="post" onsubmit="return validate();">		
	<html:javascript formName="atributoForm"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	<!-- Titulo de la pagina -->
	<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<TR> 
        	<TD class=Titulo>
        		<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
        		width=11 align=absMiddle>&nbsp;Edicion de atributo</FONT>
            </TD>
            <TD> <DIV align=right></DIV></TD>
        </TR>
        <TR> 
         	<TD class=texto4 colSpan=2> <DIV align=right> 
              	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                   	<TR> 
                       	<TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                        <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                    </TR>
                </TABLE>
            </DIV></TD>
    	</TR>
	</TABLE>
   	<!-- Fin Titulo de la pagina -->
	<br/>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel">Atributo:</td>
			<td class="celdaLabelSinAlign" colspan="2" align="left"><c:out value="${atributoForm.nombre}"/></td>
		</tr>
		<TR>
		<TD class="celdaLabelBusqueda">Valor por defecto:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" property="valorAdmitido" styleClass="Input100porc" styleId="valorAdmitido"/></TD>
		</TR>
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
<script language="JavaScript">
	function validate() {
		//return validateAtributoForm(document.forms[0]);
		if(!bCancel){
			inputStr= document.forms[0].valorAdmitido.value.toString();
			if (inputStr.length == 0){
				alert("El campo Valor por defecto es obligatorio"); 
				return false
			}
		}
		return true
	}
</script>	