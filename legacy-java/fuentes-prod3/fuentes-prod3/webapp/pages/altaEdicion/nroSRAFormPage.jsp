<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/editarPropietarioValidate.do" method="post">

			<html:hidden name="propietarioForm" property="idProp" styleId="idProp"/>
			
		<html:hidden name="propietarioForm" property="actionBack"/>
		
		<html:hidden name="propietarioForm" property="method" value="agregarSRA"/>

			

		<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Numero de S.R.A.</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                                <TD width="70%"><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                              </TR>
                            </TABLE>
                          </DIV></TD>

                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   		
   		
   		<br/>
   		
   		<table>
   		<tr>
        	<td colspan="3">
        	<html:messages id="message1" message="true" property="expdExistente"/>          	
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
   		
				
			<br/>
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >							
			
					<TR>
						<TD class="celdaLabel">
							Numero: <span class="required">*</span>
						</TD>
						<TD class="celdaInput" colspan="2">
							<html:text size="22" name="propietarioForm" property="nroExpd" styleClass="Input100porc" styleId="nroExpd"/>
							
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Estab: 
						</TD>
						<TD class="celdaInput" colspan="2">
							<html:text size="22" name="propietarioForm" property="estab" styleClass="Input100porc" styleId="estab"/>							
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Cuig: 
						</TD>
						<TD class="celdaInput" colspan="2">
							<html:text size="22" name="propietarioForm" property="cuig" styleClass="Input100porc" styleId="cuig"/>							
		            	</TD>
					</TR>
					<tr>
						<td class="celdaLabel"><strong>Raza:&nbsp;</strong></td>
						<td class="celdaInput">
						<html:select property="raza" styleClass="formfields" style="width:160px">
								
								<html:options collection="razas" property="id" labelProperty="nombre"/>
							</html:select>
							</td>					
					</tr>
				</table>

		
			 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
							<input type="button" value="Aceptar" class="botones" onclick="setMethod('agregarSRA')">
						</td>
						<td align="center">
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
			</TR>	
		</table>
				
		</html:form>
 <script>
 
	function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
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
 
 
  
 	function cancelar() {
		var idPropValue = document.getElementById("idProp").value;
		if (isEmpty(idPropValue)) {
			bCancel = true;
			var mUrl = "editarPropietarioValidate.do?method=agregarSRA";
			//var mUrl = "editarPropietarioValidate.do?method=initAdd";
		}
		else
			var mUrl = "editarPropietarioValidate.do?method=initmod&id="+idPropValue;
		window.location.href = mUrl;
 	}
 	
 	function setMethod(valor) {
 		var elemExpd = document.getElementById("nroExpd");
		if (isEmpty(elemExpd.value)) {
			alert("El campo Numero es obligatorio");
			return;
		}
		if (!isPosInteger(elemExpd.value)) {
			alert("El campo Numero debe ser un entero positivo");
			return;
		}	
			
 		document.forms[0].method.value = valor; 		
 		document.forms[0].submit();
 	}

</script>
 
	


