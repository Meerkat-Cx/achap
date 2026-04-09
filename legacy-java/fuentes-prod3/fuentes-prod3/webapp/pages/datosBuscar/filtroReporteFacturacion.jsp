<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/reportesAchaAction.do"  method="post" > 
		<!-- Titulo de la pagina -->
	 <input type="hidden" name="method" value="ejecutar">
	<input type="hidden" name="anio" value="<c:out value="${reporteAchaForm.anio}"/>">
	<input type="hidden" name="anioMax" value="<c:out value="${reporteAchaForm.anioMax}"/>">
	
		<TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Reporte&nbsp;Facturacion</FONT></TD>
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
   	
   
   	<table width="100%" class="bordeGris"  cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabelBusqueda">Mes:</TD>
			
			<td class="celdaInput" colspan= "2" >
				<html:select   property="mes" >
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
				</html:select>	
			</td>	
		</TR>
		<TR>
			<TD class="celdaLabelBusqueda">anio:</TD>
			<td class="celdaInput" colspan= "2" >
				<select name="sel" id="selectA" onchange="javascript:setAnio()" property="anio" >
				</select>	
			</td>
		</TR>
		<tr>
			<td class="celdaLabel">
				Regional: 
			<td class="celdaInput" colspan= "2" >
			
				<html:select onchange="javascript:setRegional('filtrarEclos')" property="idRegional" >
					<html:option value=""  ></html:option>
					<html:options collection="regionales"  property="id" labelProperty="nombreContacto" />
				</html:select>	
			</td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Eclo: 
			<td class="celdaInput" colspan= "2" >
			
				<html:select onchange="javascript:setEclo()" property="idEclo" >
					<html:option value=""  ></html:option>
					<html:options collection="eclos" property="id" labelProperty="nombreContacto" />
				</html:select>	
			</td>
		</tr>
		<TR>
			<TD class="celdaLabelBusqueda" align="right" >Propietario:	 </TD>
			<TD class="celdaLabelBusqueda" align="center">
			<html:hidden name="reporteAchaForm" property="idProp"/>
			<html:hidden name="reporteAchaForm" property="nombreProp"/>
			<div  id="propietarioVisible">
				
                 <c:out value="${reporteAchaForm.idProp}"/>
              	 <c:out value="${reporteAchaForm.nombreProp}"/>
	        </div>
	        </td>
	       <TD class="celdaLabelBusqueda" align="center">
		  
	            <input type="button"  value="Buscar Propietario" class="botones"  name="buscarP"  onClick="javascript:setBuscar('initBuscarPropietario')" >   
			
            </TD>
	 </tr>
    </table>	
   	
	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
              <TR>
    			<TD class="celdaLabel"  >[Facturacion] </TD>
			<!-- 	<TD  class="TextoNegro"><a href="generarReportesEclo.do?method=descargarExportacionPDF&id=<c:out value="${requestScope.idEs}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	-->
			<TD  class="TextoNegro" ><a href="javascript:setMethod('reporteFacturacion')"><img border="0" src="img/EXCEL.jpg" title="Descargar"/></a></TD>	
			</TR>
			
		
	</table>
	
	<script language="JavaScript">
	window.onload();
	var anM = document.forms[0].anioMax.value;
	function onload(){
		var selectA=document.getElementById("selectA"); 
		rellenar_anio(selectA);
		var an = document.forms[0].anio.value;
		if(document.forms[0].anio.value!='')
				document.forms[0].sel.value=an;
	}
	function setAnio(){
	
		var valor =document.forms[0].sel.value;
		document.forms[0].anio.value = valor;
    	//	document.forms[0].submit();
	}
	 function setMes(){
	
		var valor =document.forms[0].selM.value;
		document.forms[0].mes.value = valor;
    	//	document.forms[0].submit();
	}
	
	function aniadeOpcion(elSelect, texto, valor) {  
			var laOpcion=document.createElement("OPTION");  
			laOpcion.appendChild( document.createTextNode(texto) );  
			laOpcion.setAttribute("value",valor);  
			elSelect.appendChild(laOpcion);  
		} 


	function rellenar_anio(elSelect) { 
			
			for(var a=document.forms[0].anioMax.value;a>=1980;a--) 
				aniadeOpcion(elSelect, a.toString(), a.toString() ); 
		} 

	function setRegional(valor){
		limpiar();
		document.forms[0].method.value = valor;
    		document.forms[0].submit();
    
	}
	function setEclo(){
		limpiar();
		var valor = 'cambioEclo'
		document.forms[0].method.value = valor;
    		document.forms[0].submit();
	}
	function limpiar() {
		if(document.getElementById("nombreProp")!=null){
			var nomElem = document.getElementById("nombreProp");
			nomElem.value = "";
		}
		
		if(document.getElementById("idProp")!=null){
			var idElem = document.getElementById("idProp");
			idElem.value = "";
		}
		}
	function setMethod(valor){
    	document.forms[0].method.value = valor;
    		document.forms[0].submit();
    }
    function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
    function setBuscar(valor){
    if ((isEmpty(document.forms[0].idEclo.value))&&(!(isEmpty(document.forms[0].idRegional.value)))) {
				alert("si selecciona una REGIONAL es obligatorio seleccionar una ECLO para poder seleccionar un propietario");
				return;
			}
	else{
    	document.forms[0].method.value = valor;
    		document.forms[0].submit();
    		}
    }
			
    	
	</script>    
</html:form>