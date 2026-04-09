<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/generarReportesEclo.do" method="post" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarEclos">
		<html:hidden property="error" value="${requestScope.error}"/>
		<input type="hidden" name="unEst" value="<c:out value="${unEst}"/>"/>
	     <input type="hidden" name="unEstTodos" value="<c:out value="${unEstTodos}"/>"/>
		 <input type="hidden" name="unEstPropios" value="<c:out value="${unEstPropios}"/>"/>
		 <input type="hidden" name="unPro" value="<c:out value="${unPro}"/>"/>
		 <input type="hidden" name="unEcl" value="<c:out value="${unEcl}"/>"/>
		 <input type="hidden" name="tambos" value="<c:out value="${tambos}"/>"/>
		 <input type="hidden" name="animales" value="<c:out value="${animales}"/>"/>
		 <input type="hidden" name="calificados" value="<c:out value="${calificados}"/>"/>
		 <input type="hidden" name="orden" value="<c:out value="${orden}"/>"/>
		 <input type="hidden" name="propId" value="<c:out value="${propId}"/>"/>
		 <input type="hidden" name="ecloId" value="<c:out value="${ecloId}"/>"/>
		 <input type="hidden" name="tamboId" value="<c:out value="${tamboId}"/>"/>
		 <input type="hidden" name="tipoLogin" value="<c:out value="${tipoLogin}"/>"/>
		 <input type="hidden" name="eclosOrPropietarios" value="<c:out value="${reportesEcloForm.eclosOrPropietarios}"/>"/>
		 <input type="hidden" name="indiceCombo" value="<c:out value="${reportesEcloForm.indiceCombo}"/>"/>	
		 
					
 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
							B&uacute;squeda&nbsp;de&nbsp;Eclo</FONT>
						</TD>
                        <TD> 
                        	<DIV align=right></DIV>
                        </TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                                <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                              </TR>
                            </TABLE>
                          	</DIV>
                        </TD>
                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>
   	
   	<!-- datos de entrada -->
   	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Identificador Eclo: </TD>
			<TD class="celdaInput"><html:text size="45" name="reportesEcloForm" property="idEclo" styleClass="Input10porc"/>
					
			 </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Nombre Eclo: </TD>
			<TD class="celdaInput"><html:text size="45" name="reportesEcloForm" property="nombreEclo" styleClass="Input10porc"/>
			</TD>
		</TR>
</table>


		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center">
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="setMethod('buscarEclos')">
							
					</td>				
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</TR>	
		</table>
		<div class="justify">
    <display:table name="listaEclos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/generarReportesEclo.do?method=buscarEclos" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Eclo</font>
					</td>
					<td><div align=right></div></td>
				</tr>
				<tr> 
					<td class=texto4 colSpan=2> <div align=right> 
						<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
				            cellPadding=0 width="100%" border=0>
							<tr> 
								<td width="30%" bgColor=#529b28><img height=2 
				                  src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                        </div></td>
				</tr>
			</table>
			<br>
		</display:caption>
		<display:column align="left" title="Identificador" property="id" href="generarReportesEclo.do?method=seleccionarEclo" paramId="id" paramProperty="id"/>
						
						
	    <display:column align="left" title="Nombre" property="nombreContacto" />
        <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">
            				<c:out value="${requestScope.error}"/>	
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>
	
		<script language="JavaScript">
		
		
	
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
	function setMethod(valor){
			if(document.forms[0].elements["idEclo"]!=null){
				if (!isPosInteger(document.forms[0].elements["idEclo"].value)) {
					alert("Identificador debe ser un número");
					return;
				}
			}
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
   	}
		function limpiar() {
		if(document.getElementById("nombreEclo")!=null){
			var nomElem = document.getElementById("nombreEclo");
			nomElem.value = "";
		}
		
		if(document.getElementById("idEclo")!=null){
			var idElem = document.getElementById("idEclo");
			idElem.value = "";
		}
	}
	</script>    
</html:form>