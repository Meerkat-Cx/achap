<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html:form action="/generarReportesEclo.do">
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscar">
		 
	 	
	 	
	 	
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 >B&uacute;squeda de Animal</FONT></TD>
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
	<table width="95%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel">Tipo de registro:&nbsp;</td>
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
                <html:text size="20" name="reportesEcloForm" title="Número de Registro del Animal" property="numReg" />
            </TD>
		</TR>	
		<tr>
			<td class="celdaLabel">Raza del Animal:&nbsp;</td>
			<td class="celdaInput">
			<html:select property="raza" styleClass="formfields" style="width:160px">		
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
			</td>					
		</tr>	
		
	</table>
	
   	
	<html:messages id="message1" message="true" property="error"/>          	
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
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
		<tr> 
			<td align="center">
				<input type="button" value="Buscar" class="botones" onclick="ejecutar('buscar')">
			</td>
			<td align="center"><input type="button" value="Cancelar" class="botones" onclick="history.go(-1)"></td>
		</TR>
	</table>
		
		
		<c:choose>
        		<c:when test="${requestScope.animal == null}">
					<table width="95%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
        				<tr>
            				<td class="TextoNegro" colspan="4">No se ha encontrado animal</td>
            			</tr>
            		</table>
            	</c:when>
            	<c:otherwise>
            		<table width="95%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
        				<tr>
            				<td class="TextoNegro" colspan="4">Animal Encontrado</td>
            			</tr>
            			<TR>
							<TD class="celdaLabel" width="70%" >Raza del animal: </TD>
							<td class="TextoNegro" width="30%" colspan="3"><c:out value="${requestScope.animal.composicionRacial.razaDeclarada}"/></td>
						</TR>
						
						<TR>
							<TD class="celdaLabel">Eclo: </TD>
							<td class="TextoNegro" colspan="3"><c:out value="${requestScope.animal.establecimiento.eclo.nombreContacto}"/></td>
						</TR>
						<c:if test="${requestScope.enCurso != null}">
							<TR>
								<TD class="celdaLabel">[Descargar Constancia de lactancia en Curso] </TD>
								<TD  class="TextoNegro" colspan="3"><a href="generarReportesEclo.do?method=descargarLactanciaCurso&id=<c:out value="${requestScope.animal.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
							</TR>	
						</c:if>
						<c:if test="${requestScope.enCurso == null}">
							<TR>
								<TD class="TextoNegro" colspan="4" align="center">Este animal no tiene Lactancia en Curso </TD>	
							</TR>	
						</c:if>
					</table>
					<br>
 				
			      <display:table name="lactancias" align="center" class="its" id="lactancia">
				        <display:caption>
				           <TABLE width="100%" cellSpacing="2" cellPadding="2" bgcolor="#eff3e3" align="center" >
				                    <TR> 
				                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Lactancias Cerradas</FONT></TD>
				                      <TD> <DIV align=right></DIV>
				                      </TD>
				                    </TR>
				                    <TR> 
				                      <TD class=texto4 colSpan=2> <DIV align=right> 
				                          <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
				                            <TR> 
				                              <TD width="20%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
				                              <TD width="80%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
				                            </TR>
				                          </TABLE>
				                        </DIV>
				                       </TD>
				                    </TR>
				        	</TABLE>
				       </display:caption> 
				        
					        <display:column align="left" width="10%" property="nroLact" title="Nº Lactancia"/>
					        <display:column align="left" width="10%" title="Fecha Inicio Lactancia"><fmt:formatDate pattern="dd/MM/yyyy" value="${lactancia.fechaInicio}" /></display:column>
					        <display:column align="left" width="10%" property="dias" title="Cantidad Días"/>    
							<display:column align="center" width="10%" title="Descargar"><a href="javascript:lactancia(<c:out value="${requestScope.animal.id}"/>,<c:out value="${lactancia.nroLact}"/>)"><img border="0" src="img/PDF.jpg" title="Descargar"/></a>
							 </display:column>
							<display:column align="center" width="10%" title="Graficar"><a href="javascript:graficarLactancia(<c:out value="${requestScope.animal.id}"/>,<c:out value="${lactancia.nroLact}"/>)"><img border="0" src="img/line-graph.jpg" title="Descargar"/></a>
					        </display:column>
					        <display:setProperty name="basic.msg.empty_list" >
					           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
				            		<TR>
				            			<td class="TextoNegro">No hay Lactancias Cerradas para mostrar
				            			</td>
				            		</TR>
	            				</table>
					        </display:setProperty>

				    </display:table>
              </c:otherwise>
        </c:choose>
         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                     
                      <tr> 
						<td align="center"><input type="button" class="Botones" onClick="javascript:graficarTodos(<c:out value="${requestScope.animal.id}"/>)" value="graficar todas lactancias"/></td>
			</tr>	
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
					
			        chart.draw(chartData2, options);
			        //chart.draw(chartData2, {curveType: "function", width: 900, height: 400,vAxis: {maxValue: 60}});
			       // table.draw(tableData);
			      return;  														        
			      
 }
	function graficarLactancia(valor, valor2){
//			if (isEmpty(document.forms[0].elements["numLactDescarga"].value)) {
//								alert("el valor de numero de lactancia es un campo obligatorio");
//								return;
//							}
//			if (!isPosInteger(document.forms[0].elements["numLactDescarga"].value)) {
//								alert("el valor del numero de lactancia debe ser un número");
//								return;
//							}
			var mUrl = "generarReportesEclo.do?method=graficarLactancia&id="+valor+"&numLactDescarga="+valor2;
		      	window.location.href = mUrl;
	
	}
	
	function graficarTodos(valor){
	
		var mUrl = "generarReportesEclo.do?method=graficarTodasLactancias&id="+valor;
      	window.location.href = mUrl;
	}
		
			
		function ejecutar(valor){
				if (isEmpty(document.forms[0].elements["numReg"].value)) {
					alert("El numero de registro es un campo obligatorio");	
					return;					
				}
				else
					if (!(isPosInteger(document.forms[0].elements["numReg"].value))){				
							alert("El numero de registro debe ser un numero");
							return;
					}
				return setMethod(valor);
		}
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	
    	function anioValido(inputVal) {
		var fecha=new Date();
		var anio=fecha.getFullYear();
		if(inputVal < 1900 || inputVal > anio)
			return false;
		return true;
		
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
    	function lactancia(valor, valor2){
//			if (isEmpty(document.forms[0].elements["numLactDescarga"].value)) {
//								alert("el valor de numero de lactancia es un campo obligatorio");
//								return;
//							}
//			if (!isPosInteger(document.forms[0].elements["numLactDescarga"].value)) {
//								alert("el valor del numero de lactancia debe ser un número");
//								return;
//							}
			var mUrl = "generarReportesEclo.do?method=descargarLactanciaNumero&id="+valor+"&numLactDescarga="+valor2;
		      	window.location.href = mUrl;
	
	}
    	
	</script>    
	
</html:form>