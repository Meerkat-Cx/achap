<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/edicionAnimalPedigreeAction.do" method="post" enctype="multipart/form-data" >
	
	<input type="hidden"  name="method" value="<c:out value="${requestScope.action}"/>">
	 <html:hidden property="error" value="${requestScope.error}"/>	
	
	
<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					&nbsp;Alta de Comentario
					 </td>
            </tr>
        </TABLE>
   	<TR>
			<TD class="TextoError" colspan="4"> 	<c:out value="${requestScope.error}"/>		
						        </td>
		</tr>
			
			<br/>
			<table width="90%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				
					<tr>
					   <td align="center" class="celdaLabelSinAlign" align="center">
						<html:textarea property="comentario" styleClass="formfields" rows="7" cols="70"/>
					</td>
				 </tr>       
					
					
				</table>
			
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr>
					   <td align="center">
						<input type="button" value="Agregar" class="botones" onclick="agregar('addComentario')">
						</TD>
				<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</TR>	
		</table>
				
		</html:form>
 <script>
	
		function limpiar() {
				var nomElem = document.getElementById("comentario");
			nomElem.value = "";
		}
		
		function agregar(valor){
			
			if (isEmpty(document.forms[0].elements["comentario"].value)) {
				alert("Debe insertar un comentario");
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
	

</script>
 
	


