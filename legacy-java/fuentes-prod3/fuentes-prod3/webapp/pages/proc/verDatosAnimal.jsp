<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/visualizarReportesAchaAction.do" >
		<!-- Titulo de la pagina -->
		
		
<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >

			<tr>
				<td class="Titulo" align="center">Ver datos del animal</td>
			</tr>
   		<!-- Fin Titulo de la pagina -->
   	<br/>
		<html:messages id="message1" message="true" property="error"/>   
		<input type="hidden" name="chartData" value="<c:out value="${chartData}"/>">       	
	<c:if test="${message1!='' and message1!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message1}"/>
				</td>
			</tr>
	    </table>
	    <br>
	    
	</c:if>
	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
		<tr>
			<td class="celdaLabel">RP:&nbsp;</td>
			<td class="TextoNegro" colspan="5" ><bean:write  name="animalForm" property="rp"/></td>
				<!-- 	<html:text property="rp" styleClass="formfields" size="50" maxlength="100" /></td>-->
		</tr>
		<TR>
			<TD class="celdaLabel">Tipo de registro: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="tipoReg"/></td>
			<!-- <TD class="celdaInput">
                <html:text size="45" name="animalForm" title="Tipo del Animal(RC/HBA)" property="tipoReg" styleClass="Input100porc"/>
            </TD>-->
		</TR>

		<TR>
			<TD class="celdaLabel">Numero de registro: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="numReg"/></td>
		</TR>
		<TR>
			<TD class="celdaLabel">Raza del animal: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="raza"/></td>
		</TR>
		<TR>
			<TD class="celdaLabel">Categoría: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="categoria"/></td>
		</TR>
		<TR>
			<TD class="celdaLabel">Sexo del animal: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="sexo"/></td>
		</TR>
		<TR>
			<TD class="celdaLabel">Tambo: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="nombreEstablecimiento"/></td>
		</TR>
		<TR>
			<TD class="celdaLabel">Eclo: </TD>
			<td class="TextoNegro" colspan="5"><bean:write  name="animalForm" property="nombreEclo"/></td>
		</TR>
		<tr>
		<html:hidden name="animalForm" property="tipoReg"/>
		</tr>
		<TR>
		
			<TD class="celdaLabel">recategorizar</TD>
			<c:choose>
				<c:when test="${animalForm.tipoReg != 'RC'}">
					<TD  class="TextoNegro" colspan="5">El animal no posee registro RC</TD>	
				</c:when>
					<c:otherwise>
							<TD  class="TextoNegro" colspan="5"> <a href="javascript:recategorizar(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/recalcular.JPG" title="Recategorizar"/></a></TD>			
							
					</c:otherwise>
			 </c:choose>
		</TR>
		<TR>
		
			<TD class="celdaLabel">recalcular Composición Genealogica</TD>
			<c:choose>
				<c:when test="${animalForm.tipoReg != 'RC'}">
					<TD  class="TextoNegro" colspan="5">El animal no posee registro RC</TD>	
				</c:when>
					<c:otherwise>
							<TD  class="TextoNegro" colspan="5"> <a href="javascript:recalcular(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/recalcular.JPG" title="Recalcular Composición"/></a></TD>			
							
					</c:otherwise>
			 </c:choose>
		</TR>
		<TR>
		
			<TD class="celdaLabel">[descargar ficha] </TD>
			<c:choose>
				<c:when test="${animalForm.tipoReg != 'RC'}">
					<TD  class="TextoNegro" colspan="5">El animal no posee registro RC</TD>	
				</c:when>
					<c:otherwise>
							
							
							<TD  class="TextoNegro" colspan="5"><a href="visualizarReportesAchaAction.do?method=downloadFicha&hoja=A4&id=<c:out value="${requestScope.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar Hoja A4"/></a></TD>
					</c:otherwise>
			 </c:choose>
		</TR>
		<c:choose>
				<c:when test="${animalForm.sexo == 'Hembra'}">
					<TR>
			<TD class="celdaLabel">[descargar Constancia de Lactancia en Curso] </TD>
			<TD  class="TextoNegro" ><a href="visualizarReportesAchaAction.do?method=descargarLactanciaCurso&id=<c:out value="${requestScope.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
			<TD class="celdaLabelSinAlign" align="rigth" >descargar Informacion de la Lactancia Nº: </TD>
			<TD class="celdaInputSinWidth" align="rigth">
                <html:text size="10" name="animalForm" title="numero de lactancia"  property="numLactDescarga" />
			
			</TD>
			<!-- <TD  class="TextoNegro"><a href="visualizarReportesAchaAction.do?method=descargarLactanciaNumero&id=<c:out value="${requestScope.id}"/>&numLactDescarga=<c:out value="${animalForm.numLactDescarga}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	-->
			<TD  class="celdaLabelSinAlign" align="center" ><a href="javascript:lactancia(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>
			<TD  class="celdaLabelSinAlign" align="center" >recalcular   <a href="javascript:lactanciaRe(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/recalcular.JPG" title="Recalcular"/></a></TD>
		</TR>
				</c:when>
					
			 </c:choose>
		
		<TR>
			<TD class="celdaLabel">[descargar Genealogia] </TD>
			<TD  class="TextoNegro" colspan="5"><a href="visualizarReportesAchaAction.do?method=descargarGenealogico&id=<c:out value="${requestScope.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
		</TR>
		<tr>
		</tr>	
		<TR>
			<TD class="celdaLabel">Destinatario</TD>
			<TD class="celdaInput" colspan="5" align="rigth">
                <html:text size="40" name="animalForm" title="destinatario"  property="destinatario" />
			
			</TD>
		</TR>	
		<TR>
			<TD class="celdaLabel">Firmante</TD>
			<TD class="celdaInput" colspan="5" align="rigth">
                <html:text size="40" name="animalForm" title="firma"  property="firmante" />
			
			</TD>
		</TR>	
		<TR>
			<TD class="celdaLabel">[descargar Certificado Exportacion] </TD>
			<!-- <TD  class="TextoNegro" colspan="5"><a href="visualizarReportesAchaAction.do?method=descargarExportacion&id=<c:out value="${requestScope.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	-->
			
			<TD  class="TextoNegro" colspan="5"><a href="javascript:exportacionA4(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/PDF.jpg" title="Descargar A4"/></a></TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Recalcular eventos del animal: </TD>
			<TD  class="TextoNegro" colspan="5"><a href="javascript:recalcularEventos(<c:out value="${id}"/>)"><img border="0" src="img/recalcular.JPG" title="Recalcular"/></a></TD>
		</TR>	
		
	</table>


	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                     
                      <tr> 
						<td align="center"><input type="button" class="Botones" onClick="window.location='visualizarReportesAchaAction.do?method=init' " value="volver"/></td>
			</tr>	
		</table>
		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                     
                      <tr> 
						<td align="center"><input type="button" class="Botones" onClick="javascript:graficar(<c:out value="${requestScope.id}"/>)" value="graficar Lactancia"/></td>
			</tr>	
		</table>
		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                     
                      <tr> 
						<td align="center"><input type="button" class="Botones" onClick="javascript:graficarTodos(<c:out value="${requestScope.id}"/>)" value="graficar todas lactancias"/></td>
			</tr>	
		</table>
</table>
  <body onload="javascript:drawChart(<c:out value="${chartData}"/>)">
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
   <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script language="JavaScript">
	  
     
       Array.prototype.reduce = undefined;
	   google.load("visualization", "1", {packages:["corechart"]});
	  
        
    
     function drawChart(valor) {
   		//alert(valor);
			    	var chartData2 = google.visualization.arrayToDataTable(valor);
		            var options = {
			          height: 500,
			          chartArea:{right:"5%",width:"80%",top:"10%"},
			          legend: 'bottom',
			        };
			        
       				//chartData2.sort([{column: 1, desc: true}]);
        			//tableData.sort([{column: 2, desc: true}]);
					
					var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
					
			        //chart.draw(chartData2, options);
			        chart.draw(chartData2, {curveType: "function", width: 900, height: 400,vAxis: {maxValue: 60}});
			       // table.draw(tableData);
			      return;  														        
			      
 }
	
	function graficar(valor){
	if (isEmpty(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia es un campo obligatorio");
						return;
					}
	if (!isPosInteger(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia debe ser un número");
						return;
					}
		var mUrl = "visualizarReportesAchaAction.do?method=graficarLactancia&id="+valor+"&numLactDescarga="+document.forms[0].elements["numLactDescarga"].value;
      	window.location.href = mUrl;
	}
	function graficarTodos(valor){
	
		var mUrl = "visualizarReportesAchaAction.do?method=graficarTodasLactancias&id="+valor;
      	window.location.href = mUrl;
	}
	
	
	function lactancia(valor){
	if (isEmpty(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia es un campo obligatorio");
						return;
					}
	if (!isPosInteger(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia debe ser un número");
						return;
					}
	
	      var mUrl = "visualizarReportesAchaAction.do?method=descargarLactanciaNumero&id="+valor+"&numLactDescarga="+document.forms[0].elements["numLactDescarga"].value;
      	window.location.href = mUrl;
	
	}
	function lactanciaRe(valor){
	if (isEmpty(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia es un campo obligatorio");
						return;
					}
	if (!isPosInteger(document.forms[0].elements["numLactDescarga"].value)) {
						alert("el valor del numero de lactancia debe ser un número");
						return;
					}
	var mUrl = "visualizarReportesAchaAction.do?method=recalcularLactancia&id="+valor+"&numLactDescarga="+document.forms[0].elements["numLactDescarga"].value;
      	window.location.href = mUrl;
	
	}
	function recategorizar(valor){
	
	var mUrl = "visualizarReportesAchaAction.do?method=recategorizar&id="+valor;
      	window.location.href = mUrl;
	
	}
	function recalcular(valor){
	
	var mUrl = "visualizarReportesAchaAction.do?method=recalcularComposicion&id="+valor;
      	window.location.href = mUrl;
	
	}
	function exportacion(valor){
	if (isEmpty(document.forms[0].elements["destinatario"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el destinatario");
						return;
					}
	if (isEmpty(document.forms[0].elements["firmante"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el firmante");
						return;
					}
	var mUrl = "visualizarReportesAchaAction.do?method=descargarExportacion&id="+valor+"&destinatario="+document.forms[0].elements["destinatario"].value+"&firmante="+document.forms[0].elements["firmante"].value;
      	window.location.href = mUrl;
	
	}
	function exportacionA4(valor){
	if (isEmpty(document.forms[0].elements["destinatario"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el destinatario");
						return;
					}
	if (isEmpty(document.forms[0].elements["firmante"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el firmante");
						return;
					}
	var mUrl = "visualizarReportesAchaAction.do?method=descargarExportacion&hoja=A4&id="+valor+"&destinatario="+document.forms[0].elements["destinatario"].value+"&firmante="+document.forms[0].elements["firmante"].value;
      	window.location.href = mUrl;
	
	}
	function recalcularEventos(valor){
		var mUrl = "visualizarReportesAchaAction.do?method=recalcularEventos&id="+valor;
      		window.location.href = mUrl;
	
	}
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
		</script>
		
</html:form>