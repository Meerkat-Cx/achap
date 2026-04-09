<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>



<html:form action="/edicionAnimalPedigreeAction.do?"  >	

 <input type="hidden" name="method" value="<c:out value="${requestScope.action}"/>">
		   <html:hidden property="error2" value="${requestScope.error2}"/>	
	
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					&nbsp;Asignacion&nbsp;de&nbsp;Animal
					 </td>
            </tr>
        </TABLE>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr>
			<td class="celdaLabel">Tipo de registro:&nbsp;</td>
				<td class="celdaInput">
					<html:select property="tipoReg" styleClass="formfields" style="width:100px">
								<html:options collection="tipos" property="id" labelProperty="id"/>
							
						</html:select>
			</td>


		<TR>
			<TD class="celdaLabel">Número de registro: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="pedigreeForm" title="Número de Registro del Animal" property="numReg" />
            </TD>
		</TR>
		
		<tr>
			<td class="celdaLabel">Raza del Animal:&nbsp;</td>
			<td class="celdaInput">
			<html:select property="razaP" styleClass="formfields" style="width:160px">
					
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>					
		</tr>
		
        
		
	</table>
	<c:out value="${requestScope.error2}"/>
         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
	            <input type="button" value="Aceptar" class="botones" onclick="buscar()">            
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar1()">

						</td>
				</TD>
			</TR>	
		</table>
		
	
</html:form>
<html:form action="/edicionAnimalPedigreeAction.do?"  >	
 <input type="hidden" name="method" value="<c:out value="${requestScope.action}"/>">
 
		   <html:hidden property="error2" value="${requestScope.error2}"/>	
		
		
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Nombre del Animal: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="pedigreeForm" title="Nombre" property="nombrePM" />
            </TD>
		</TR>
		
	</table>
	
         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
	            <input type="button" value="Aceptar" class="botones" onclick="buscarPorNombre()">            
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar2()">

						</td>
				</TD>
			</TR>	
		</table>
		
			<div class="justify">
			<c:choose>
						<c:when test="${requestScope.action == 'buscarPadre'}">
							<display:table name="requestScope.animales" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/edicionAnimalPedigreeAction.do?method=buscarPadre" >
								<display:column align="left" title="Identificador" property="id" href="edicionAnimalPedigreeAction.do?method=seleccionarAnimal" paramId="id" paramProperty="id"/>
								 <display:column align="left" title="RP" property="RP" />
								<display:column align="left" title="Nombre" property="nombre" />
								<display:setProperty name="basic.msg.empty_list" >
									<!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
										<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
											<TR>
												<td class="TextoNegro">
													<c:out value="${requestScope.error3}"/>	
												</td>
											</TR>
										</table>
								</display:setProperty>
							</display:table>
						</c:when>
						<c:otherwise>
									<display:table name="animales" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/edicionAnimalPedigreeAction.do?method=buscarMadre" >
									<display:column align="left" title="Identificador" property="id" href="edicionAnimalPedigreeAction.do?method=seleccionarAnimal" paramId="id" paramProperty="id"/>
								 <display:column align="left" title="RP" property="RP" />
								<display:column align="left" title="Nombre" property="nombre" />
								<display:setProperty name="basic.msg.empty_list" >
									<!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
										<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
											<TR>
												<td class="TextoNegro">
													<c:out value="${requestScope.error3}"/>	
												</td>
											</TR>
										</table>
								</display:setProperty>
							</display:table>
						</c:otherwise>
					</c:choose>    
       
		
</div>	
</html:form>

<script language="JavaScript">
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
    	function buscarPorNombre(){
    		if (isEmpty(document.forms[1].elements["nombrePM"].value)) {
				alert("El nombre es un campo obligatorio");
				return;
			}
			document.forms[1].submit();
    	}
		function buscar(){
    		if (isEmpty(document.forms[0].elements["numReg"].value)) {
				alert("Número de Registro es un campo obligatorio");
				return;
			}
			if (!isPosInteger(document.forms[0].elements["numReg"].value)) {
				alert("Número de Registro debe ser un número");
				return;
			}
    		document.forms[0].submit();
    	}
    	function limpiar1() {
			var nomRegionalElem = document.forms[0].elements["numReg"];
			nomRegionalElem.value = "";
		}
		function limpiar2() {
			var nombreAnimal = document.forms[1].elements["nombrePM"];
			nombreAnimal.value = "";
		}
    	
    	
	</script> 
