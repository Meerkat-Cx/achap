<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/filtrarCalificacionAction.do?method=porBoleta" method="post" >
<script language='javascript' src="calendar/popcalendar.js"></script>
		<!-- Titulo de la pagina -->
		 <TABLE width="100%" cellPadding=10 cellSpacing=0 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28>B&uacute;squeda de Boleta</FONT></TD>
                      </TR>
          </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Número de Boleta: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="filtroCalificacionForm" title="Nº de boleta" property="numeroBoleta" styleClass="Input100porc"/>
            </TD>
		</TR>
		
   </table>
	
	        	<html:messages id="message0" message="true" property="animalNoExistePorBoleta"/>          	
			<c:if test="${message0!='' and message0!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
				<tr>				
					<td class="TextoError">
						<c:out value="${message0}"/>
					</td>
				</tr>
	         	</table>
	          	<br>
			</c:if>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
					<input type="button" value="Aceptar" class="botones" onclick="buscarPorBoleta()">
					<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
						</html:cancel>
				</TD>
			</TR>	
		</table>
	
</html:form>
<c:if test="${filtroCalificacionForm.rol!='SRA'}">
	<html:form action="/filtrarCalificacionAction.do?method=porRPyEstab" method="post"  >
			<!-- Titulo de la pagina -->
			  <TABLE width="100%" cellPadding=10 cellSpacing=0 bgcolor="#eff3e3">
	                      <TR> 
	                        <TD class=Titulo><FONT color=#529b28>B&uacute;squeda de Animal para obtener sus boletas</FONT></TD>
	                      </TR>
	          </TABLE>
	   		<!-- Fin Titulo de la pagina -->
	   	<br/>
	
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR>
				<TD class="celdaLabel">RP: </TD>
				<TD class="celdaInput">
	                <html:text size="45" name="filtroCalificacionForm" title="RP del Animal" property="rp" styleClass="Input100porc"/>
	            </TD>
			</TR>
	
			<TR>
				<TD class="celdaLabel">Id. de Tambo: </TD>
				<TD class="celdaInput">
	                <html:text size="45" name="filtroCalificacionForm" title="ID del Tambo" value="" property="estabId" styleClass="Input100porc"/>
	            </TD>
			</TR>
			<TR>
				<TD class="celdaLabel">Año de Nacimiento: </TD>
				<TD class="celdaInput">
	                <html:text size="45" name="filtroCalificacionForm" title="Año de Nacimiento" value="" property="year" styleClass="Input100porc"/>
	            </TD>
			</TR>
			<tr>
					<td  class="celdaLabel" >
						Sexo: 
					</td>
					<td  class="celdaInput" align="left">
						<html:select name="filtroCalificacionForm" property="sexo">
							<html:option value="Hembra" key="HEMBRA"></html:option>
							<html:option value="Macho" key="MACHO"></html:option>
						</html:select>
					</td>
				</tr>
			
		</table>
		
		        	<html:messages id="message2" message="true" property="animalNoExiste"/>          	
				<c:if test="${message2!='' and message2!=null}">	
					<table width="90%" border="0" align="center" class="texto_error">
					<tr>				
						<td class="TextoError">
							<c:out value="${message2}"/>
						</td>
					</tr>
		         	</table>
		          	<br>
				</c:if>
		
	
	
		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	                      <tr> 
	                        <td align="center">
						<!--<html:submit styleClass="botones"/>-->
						<input type="button" value="Aceptar" class="botones" onclick="buscarPorRP()">
						<html:cancel styleClass="botones" onclick="bCancel=true;">
						<bean:message key="cancel"/>
					</html:cancel>
					</TD>
				</TR>	
			</table>
		
	</html:form>
</c:if>

<html:form action="/filtrarCalificacionAction.do?method=porTipoyNumero" method="post"  >	
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr>
			<td class="celdaLabel"><strong>Tipo de registro:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="tipoReg" styleClass="formfields" style="width:160px">
							<html:option value="RC"  >RC</html:option>
							<html:option value="HBA"  >HBA</html:option>
							
						</html:select>
			</td>
		</tr> 

		<TR>
			<TD class="celdaLabel">Número de registro: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="filtroCalificacionForm" title="Número de Registro del Animal" property="numReg" styleClass="Input100porc"/>
            </TD>
		</TR>
		
		<tr>
			<td class="celdaLabel"><strong>Raza del Animal:&nbsp;</strong></td>
			<td class="celdaInput">
			<html:select property="raza" styleClass="formfields" style="width:160px">
					
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>					
		</tr>
        <tr>
			<td class="celdaLabel">Sexo:&nbsp;</td>
			<td class="celdaInput">
				<html:select property="sexo" styleClass="formfields" style="width:160px">
					<html:option value="H"  >Hembra</html:option>
					<html:option value="M"  >Macho</html:option>
				</html:select>
			</td>					
		</tr> 
		
	</table>
	
			<html:messages id="message3" message="true" property="animalNoExistePorRegisro"/>          	
			<c:if test="${message3!='' and message3!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
				<tr>				
					<td class="TextoError">
						<c:out value="${message3}"/>
					</td>
				</tr>
	         	</table>
	          	<br>
			</c:if>

         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
	            <input type="button" value="Aceptar" class="botones" onclick="buscarPorHBA()">            
					<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel>
				</TD>
			</TR>	
		</table>	
	
</html:form>

<!-- html:form action="/filtrarCalificacionAction.do?method=porFechayEstancia" method="post"  -->
<c:if test="${filtroCalificacionForm.rol!='SRA'}">	
	<html:form action="/filtrarCalificacionAction.do" method="post"  >	
	 <input type="hidden" name="method" value="porFechayEstancia">
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			
			<TR>
				<TD class="celdaLabel">Fecha de la Calificación: </TD>
				<TD class="celdaInput">
	                <input name="fechaCalificacion" type="text" id="fechaCalificacion" onclick="popUpCalendar(this, form.fechaCalificacion, 'dd/mm/yyyy');" size="10">
	            </TD>
			</TR>
	
			<TR>
				<TD class="celdaLabel">Id. de Establecimiento: </TD>
				<TD class="celdaInput">
	                <html:text size="30" 
	                	name="filtroCalificacionForm" 
	                	title="ID del Establecimiento" 
	                	value="" 
	                	property="estanciaId"
	                	styleClass="Input10porc"/>
	               <!-- <input type="button" 
	                	value="Buscar Establecimiento" 
	                	class="botones" 
	                	onclick="setMethod('initBuscarEstancia')">-->
	            </TD>
			</TR>
			
			<tr>
				<td class="celdaLabel"><strong>Califcador:&nbsp;</strong></td>
				<td class="celdaInput" >
					<html:select property="calificadorId"
						title="Calificador"
						style="width:300px">
						<html:option value="" />
						<html:options collection="califcadores"  property="id" labelProperty="conjunto"/>
					</html:select>
				</td>					
			</tr>
			
			<tr>
				<td class="celdaLabel"><strong>Data Entry:&nbsp;</strong></td>
				<td class="celdaInput" >
					<html:select property="dataEntryId"
						title="Data Entry"
						style="width:300px">
						<html:option value="" />
						<html:options collection="dataEntries"  property="id" labelProperty="conjunto"/>
					</html:select>
				</td>					
			</tr>
			
	        
		</table>
	
				<html:messages id="message4" message="true" property="fechaErronea"/>          	
				<c:if test="${message4!='' and message4!=null}">	
					<table width="90%" border="0" align="center" class="texto_error">
					<tr>				
						<td class="TextoError">
							<c:out value="${message4}"/>
						</td>
					</tr>
		         	</table>
		          	<br>
				</c:if>
	
	         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	                      <tr> 
	                        <td align="center">
						<!--<html:submit styleClass="botones"/>-->
		            <input type="button" value="Aceptar" class="botones" onclick="buscarPorFechayEstancia()">            
						<html:cancel styleClass="botones" onclick="bCancel=true;">
						<bean:message key="cancel"/>
					</html:cancel>
					</TD>
				</TR>	
			</table>	
		
	</html:form>
</c:if>
<c:if test="${filtroCalificacionForm.rol=='SRA'}">
	<html:form styleId="form1" action="/filtrarCalificacionAction.do?method=entreDosFechasYModelo" method="post"  >	
	 	
	 	<script language='javascript' src="calendar/popcalendar.js"></script>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				<TR>
					<TD class="celdaLabel">Desde: </TD>
					<TD class="celdaInput">
		                <html:text size="30" 
		                	name="filtroCalificacionForm" 
		                	property="fechaDesde" 
		                	title="Fecha desde"
		                	styleClass="Input10porc"
		                	styleId="fechaDesde" 
		                	onclick="popUpCalendar(this, form1.fechaDesde, 'dd/mm/yyyy');" 
		                	/>
		            </TD>
				</TR>
				<TR>
					<TD class="celdaLabel">Hasta: </TD>
					<TD class="celdaInput">
		                <html:text size="30" 
		                	name="filtroCalificacionForm" 
		                	property="fechaHasta" 
		                	title="Fecha hasta"
		                	styleClass="Input10porc"
		                	styleId="fechaHasta" 
		                	onclick="popUpCalendar(this, form1.fechaHasta, 'dd/mm/yyyy');" 
		                	/>
		            </TD>
				</TR>
				<tr>
					<td class="celdaLabel"><strong>Modelo:&nbsp;</strong></td>
					<td class="celdaInput" >
						<html:select property="modelo"
							title="Calificador"
							style="width:300px">
							<html:option value="" />
							<html:options collection="modelos" property="id" labelProperty="id"/>
						</html:select>
					</td>					
				</tr>
		</table>
		<html:messages id="message5" message="true" property="errorFiltroSRA"/>          	
				<c:if test="${message5!='' and message5!=null}">	
					<table width="90%" border="0" align="center" class="texto_error">
						<tr>				
							<td class="TextoError">
								<c:out value="${message5}"/>
							</td>
						</tr>
		         	</table>
		          	<br>
				</c:if>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
			<tr> 
		    	<td align="center">
					<input type="button" value="Aceptar" class="botones" onclick="buscarEntreFechasYModelo()">            
						<html:cancel styleId="asdfsd" styleClass="botones" onclick="bCancel=true;">
							<bean:message key="cancel"/>
						</html:cancel>
				</TD>
			</TR>	
		</table>		
	</html:form>
</c:if>

<script language="JavaScript">
	function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		//var negativo = inputStr.charAt(i);
		//if(negativo =="-"){
		//	for (var i = 1; i < inputStr.length; i++) {
		//		var oneChar = inputStr.charAt(i)
		//		if (oneChar < "0" || oneChar > "9") {
		//			return false
		//		}
		//	}
		//}
		//else{
			for (var i = 0; i < inputStr.length; i++) {
				var oneChar = inputStr.charAt(i)
				if (oneChar < "0" || oneChar > "9") {
					return false
				}
			}
		//}
		return true
	}

    	function buscarPorBoleta(){
    		if (isEmpty(document.forms[0].elements["numeroBoleta"].value)) {
				alert("el numero de boleta es un campo obligatorio");
				return;
			}
			if (!isPosInteger(document.forms[0].elements["numeroBoleta"].value)) {
				alert("el numero de boleta debe ser un número");
				return;
			}
			document.forms[0].submit();
    	}
    	function buscarPorRP(){
    		if (isEmpty(document.forms[1].elements["rp"].value)) {
				alert("RP es un campo obligatorio");
				return;
			}
			if (isEmpty(document.forms[1].elements["estabId"].value)) {
				alert("Id. Establecimiento es un campo obligatorio");
				return;
			}
			if (isEmpty(document.forms[1].elements["year"].value)) {
				alert("Año de Nacimiento es un campo obligatorio");
				return;
			}
			if (!isPosInteger(document.forms[1].elements["estabId"].value)) {
				alert("Id. Establecimiento debe ser un número");
				return;
			}
			if (!isPosInteger(document.forms[1].elements["year"].value)) {
				alert("Año de Nacimiento debe ser un número");
				return;
			}
			
    		document.forms[1].submit();
    	}

		
		function buscarPorHBA(){
    		if (isEmpty(document.forms[2].elements["numReg"].value)) {
				alert("Número de Registro es un campo obligatorio");
				return;
			}			

    		document.forms[2].submit();
    	}
    	
		
		function buscarPorFechayEstancia(){
    		document.forms[3].submit();
    	}
    	
    	function buscarEntreFechasYModelo(){
    		document.forms[2].submit();
    	}
    	
		function setMethod(valor){
			document.forms[3].method.value = valor;
    		document.forms[3].submit();    		
    	}
    	
    	initializeMenus();
	</script> 