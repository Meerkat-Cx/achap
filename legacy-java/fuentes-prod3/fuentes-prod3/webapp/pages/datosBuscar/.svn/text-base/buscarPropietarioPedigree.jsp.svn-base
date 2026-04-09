<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/edicionAnimalPedigreeAction.do" method="post" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarPropietario">
		<html:hidden property="error" value="${requestScope.error}"/>
		 <input type="hidden" name="prop" value="<c:out value="${requestScope.prop}"/>">
					
 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
							<c:choose>
						<c:when test="${prop == 'prop'}">
								B&uacute;squeda&nbsp;de&nbsp;Propietario</FONT>
						</c:when>
						<c:otherwise>
									B&uacute;squeda&nbsp;de&nbsp;Propietario&nbsp;Criador</FONT>
						</c:otherwise>
					</c:choose>    
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
			<TD class="celdaLabel">Nombre Propietario: </TD>
			<TD class="celdaInput">
				<c:choose>
						<c:when test="${prop == 'prop'}">
							<html:text size="45" name="pedigreeForm" property="nombreProp" styleClass="Input10porc"/>
						</c:when>
						<c:otherwise>
									<html:text size="45" name="pedigreeForm" property="nombrePropCri" styleClass="Input10porc"/>
						</c:otherwise>
					</c:choose>    
			</TD>
		</TR>
		
		<TR>
			<TD class="celdaLabel">Identificador Propietario: </TD>
			<TD class="celdaInput">
				<c:choose>
						<c:when test="${prop == 'prop'}">
							<html:text size="45" name="pedigreeForm" property="idProp" styleClass="Input10porc"/>
					</c:when>
						<c:otherwise>
									<html:text size="45" name="pedigreeForm" property="idPropCri" styleClass="Input10porc"/>
						</c:otherwise>
					</c:choose>    
			 </TD>
		</TR>
</table>


		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center">
					<td align="center">
					<c:choose>
						<c:when test="${prop == 'prop'}">
							<input type="button" value="Buscar" class="botones" onclick="setMethod('buscarPropietario')">
							</c:when>
						<c:otherwise>
									<input type="button" value="Buscar" class="botones" onclick="setMethod('buscarPropietarioCri')">
						</c:otherwise>
					</c:choose>    
					</td>				
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</TR>	
		</table>
		
		
		
				<div class="justify">
    <display:table name="propietarios" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/edicionAnimalPedigreeAction.do?method=buscarPropietario" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Propietarios</font>
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
		<c:choose>
						<c:when test="${prop == 'prop'}">
								<display:column align="left" title="Identificador" property="id" href="edicionAnimalPedigreeAction.do?method=seleccionarPropietario" paramId="id" paramProperty="id"/>
						</c:when>
						<c:otherwise>
									<display:column align="left" title="Identificador" property="id" href="edicionAnimalPedigreeAction.do?method=seleccionarPropietarioCri" paramId="id" paramProperty="id"/>
						</c:otherwise>
					</c:choose>    
						
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
			if(document.forms[0].elements["idProp"]!=null){
				if (!isPosInteger(document.forms[0].elements["idProp"].value)) {
					alert("Identificador debe ser un número");
					return;
				}
			}
			if(document.forms[0].elements["idPropCri"]!=null){
				if (!isPosInteger(document.forms[0].elements["idPropCri"].value)) {
					alert("Identificador debe ser un número");
					return;
				}
			}
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    		
    	}
		function limpiar() {
			if(document.getElementById("nombreProp")!=null){
				var nomElem = document.forms[0].elements["nombreProp"];
				nomElem.value = "";
			}
			
			if(document.forms[0].elements["idProp"]!=null){
				var idElem = document.forms[0].elements["idProp"];
				idElem.value = "";
			}
			if(document.forms[0].elements["nombrePropCri"]!=null){
				var nomElem = document.forms[0].elements["nombrePropCri"];
				nomElem.value = "";
			}
			
			if(document.forms[0].elements["idPropCri"]!=null){
				var idElem = document.forms[0].elements["idPropCri"];
				idElem.value = "";
			}
	}
    	
	</script>    
	
</html:form>