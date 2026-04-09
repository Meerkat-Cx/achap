<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/cambioRegistroAction.do" method="post" enctype="multipart/form-data" >
	
	<input type="hidden"  name="method" value="<c:out value="${requestScope.action}"/>">
	 <html:hidden property="error" value="${requestScope.error}"/>	
	
	
<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					&nbsp;Alta de Registro
					 </td>
            </tr>
        </TABLE>
   		<TR>
			<TD class="TextoError" colspan="4"> 	<c:out value="${requestScope.error}"/>		
						        </td>
		</tr>
			
			<br/>
			<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				<TR>
						<TD class="celdaLabel">Registro&nbsp;Origen&nbsp;Actual:&nbsp;</TD>
						<TD class="celdaLabelSinAlign" colspan="3" align ="left" >
						<logic:present name="cambioRegistroForm" property="tipoR" >
								<bean:write name="cambioRegistroForm" property="tipoR"/>
						</logic:present>
						<logic:present name="cambioRegistroForm" property="numR" >
								<bean:write name="cambioRegistroForm" property="numR"/>
						</logic:present>
				</TR>

				<TR>
						<td class="celdaLabel">Tipo de registro:&nbsp;</td>
						<td class="celdaInput">
						<html:select property="tipoNew" styleClass="formfields" style="width:160px">
							<html:option value="RC"  >RC</html:option>
							<html:option value="HBA"  >HBA</html:option>
						</html:select>
						</td>
					</tr>
					<tr>
						<TD class="celdaLabel">	Numero&nbsp;de&nbsp;Registro:&nbsp;</TD>
							
							<TD class="celdaInput" >
							<html:text size="10" name="cambioRegistroForm" property="numNew"/>
		            		</TD>
				</TR>
			</table>
			
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr>
					  
							 <td align="center">
							 <input type="button" value="Cambiar" class="botones" onclick="setMethod('cambio')">
							</TD>
							 <td align="center">
							 <input type="button" value="Cancelar" class="botones" onclick="setMethod('cancelar')">
							</TD>
						</TR>	
			</table>
				
</html:form>
 <script>
	
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
		

</script>
 
	


