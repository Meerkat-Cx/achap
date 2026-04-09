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
					
					<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Registro
						</c:when>
						<c:otherwise>
									&nbsp;Modificaci&oacute;n de Registro
						</c:otherwise>
					</c:choose>    
					
					    	
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
						<td class="celdaLabel">cBaj:&nbsp;</td>
							<td class="celdaInput" colspan="3">
								<html:select property="coBaj" styleClass="formfields" style="width:100px">
										<html:option value="0"  >Sin cBaj</html:option>
										<html:option value="1"  >1</html:option>
										<html:option value="2"  >2</html:option>
										<html:option value="3"  >3</html:option>
										<html:option value="4"  >4</html:option>
										<html:option value="5"  >5</html:option>
										<html:option value="6"  >6</html:option>
										<html:option value="7"  >7</html:option>
										<html:option value="8"  >8</html:option>
										<html:option value="9"  >9</html:option>
										<html:option value="10"  >10</html:option>
										<html:option value="11"  >11</html:option>
										<html:option value="12"  >12</html:option>
										<html:option value="13"  >13</html:option>
										<html:option value="14"  >14</html:option>
										<html:option value="15"  >15</html:option>
										<html:option value="16"  >16</html:option>
										<html:option value="17"  >17</html:option>
										<html:option value="18"  >18</html:option>
										<html:option value="19"  >19</html:option>
										<html:option value="20"  >20</html:option>
									</html:select>
							</td>					
					</tr> 
					<TR>	
						<TD class="celdaLabel">
							regOri?: 
						</TD>
					
						<td class="celdaInput" colspan="3" >
										<html:checkbox property="esOri" value="true" />
						  				<input type="hidden" name="esOri" value="false">
				    	</td>
					
					</TR>
					<TR>	
						<TD class="celdaLabel">
							regId?: 
						</TD>
					
						<td class="celdaInput" colspan="3" >
										<html:checkbox property="esId" value="true" />
						  				<input type="hidden" name="esId" value="false">
				    	</td>
					
					</TR>
					<TR>
						<TD class="celdaLabel">tipo Registro:</TD>
							<c:choose>
						<c:when test="${action == 'add'}">
							<TD class="celdaInput">
								<html:select property="tipoReg" styleClass="formfields" style="width:160px">
										<html:options collection="tipos" property="id" labelProperty="id"/>
									</html:select>
									</td>	
							</c:when>
						<c:otherwise>
									<TD class="celdaLabel" ><c:out value="${pedigreeForm.tipoReg}"/>
						</c:otherwise>
					</c:choose>    

						<TD class="celdaLabel">	nº&nbsp;Registro&nbsp;&nbsp;</TD>
							<c:choose>
						<c:when test="${action == 'add'}">
							<TD class="celdaInput" >
							<html:text size="10" name="pedigreeForm" property="numReg"/>
		            		</TD>
							</c:when>
							<c:otherwise>
									<TD class="celdaLabel" ><c:out value="${pedigreeForm.numReg}"/>
						</c:otherwise>
					</c:choose>    
					</TR>
				</table>
			
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr>
					  
                        <td align="center">
					<c:choose>
						<c:when test="${action == 'add'}">
							<input type="button" value="Agregar" class="botones" onclick="agregar('addRegistro')">
					</c:when>
						<c:otherwise>
									<input type="button" value="Modifcar" class="botones" onclick="setMethod('updateRegistro')">
						</c:otherwise>
					</c:choose>    
				</TD>
			</TR>	
		</table>
				
		</html:form>
 <script>
	
		function limpiar() {
		}
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
		function agregar(valor){
			
			if (isEmpty(document.forms[0].elements["tipoReg"].value)) {
				alert("TipoReg es obligatorio");
				return;
			}
			
			if (isEmpty(document.forms[0].elements["numReg"].value)) {
				alert("numReg es obligatorio");
				return;
			}
			//if (!isPosInteger(document.forms[0].elements["numReg"].value)) {
			//	alert("numReg debe ser un numero");
			//	return;
			//}
			if (supera(document.forms[0].elements["numReg"].value)) {
				alert("numReg no debe superar los 12 digitos");
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
	function supera(inputVal){
		inputStr = inputVal.toString()
			if(inputStr.length >= 13)
				return true;
			return false;
	}
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				return false
			}
		}
		return true

	}

</script>
 
	


