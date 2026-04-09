<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/informeProcesosAction"  enctype="multipart/form-data" onsubmit="return validate();">
	<script language='javascript' src="calendar/popcalendar.js"></script>
	<input type="hidden" name="method" value="process">
	<html:hidden name="informeProcesoForm" property="rolUser" />

			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
			    <TR> 
					<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						Informe de Procesos</FONT>
					</TD>
				</TR>
				<TR> 
					<TD class=texto4 colSpan=2> <DIV align=right> 
					<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
						  <TR> 
							<TD width="50%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
							<TD width="50%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
						  </TR>
						</TABLE>
						</DIV>
					</TD>
				</TR>
        </TABLE>
    <br>
    
    <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
    	<TR>
				<TD class="celdaLabel" align="center" >Fecha Desde  </TD>
				<td class="celdaLabelSinAlign" align="left" > 			             		
					 <input name="fechaDesde" type="text" id="fechaDesde" onclick="popUpCalendar(this, form.fechaDesde, 'dd/mm/yyyy');" size="10">
				</td>
		</TR>
		<tr>
				<TD class="celdaLabel" align="center" >Fecha Hasta</TD>
				<td class="celdaLabelSinAlign" align="left" > 		             		
					 <input name="fechaHasta" type="text" id="fechaHasta" onclick="popUpCalendar(this, form.fechaHasta, 'dd/mm/yyyy');" size="10">
				</td>
		</tr>
    	<TR>
			<TD class="celdaLabel">[descargar informe] </TD>
			<TD  class="celdaLabelSinAlign" align="left"><a href="javascript:setMethod('descargarReporte')"><img border="0" src="img/EXCEL.jpg" title="Descargar"/></a></TD>
				
		</TR>
		<TR>
			<TD class="celdaLabel">[descargar informe] </TD>
			<TD  class="celdaLabelSinAlign" align="left"><a href="javascript:setMethod('descargarReportePDF')"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
		</TR>
		 <tr>
				<TD class="celdaLabel">[descargar informe con crias inscriptas] </TD>
				<TD  class="celdaLabelSinAlign" align="left"><a href="javascript:setMethod('descargarReporteCrias')"><img border="0" src="img/EXCEL.jpg" title="Descargar"/></a></TD>
				
			</TR>
			<TR>
				<TD class="celdaLabel">[descargar informe con crias inscriptas] </TD>
				<TD  class="celdaLabelSinAlign" align="left"><a href="javascript:setMethod('descargarReportePDFCrias')"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
			</TR>

		</table>
	<br>
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	<tr>
	
	
	</tr>			
						
</table>
<script language="JavaScript">
	function setMethod(valor,vale){
   		if(document.forms[0].fechaDesde.value == '')
			   					alert("La Fecha Desde no puede ser vacia");
		else{
			   if(document.forms[0].fechaHasta.value == '')
			   					alert("La Fecha Hasta no puede ser vacia");
			   else{	
					  			document.forms[0].method.value = valor;
			    				document.forms[0].submit();
	    			}
			  }
			    		
	    		
	    		
    	}


function cambiarCheckRegional(){

			if (document.forms[0].checkFiltro1[0].checked) {
			document.forms[0].elements["unaRegional"].value="si";
			alert('si')
			}
			if (document.forms[0].checkFiltro1[1].checked) {
			document.forms[0].elements["unaRegional"].value="no";
			alert('no');
			}
			}
	function subir(valor){
		var mUrl = "processFileAction.do?method=pop&idP="+valor+"&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;	
    }
	function avanzar(valor){    	
    	var mUrl='<html:rewrite page="/pages/uploadFiles/popupObs.jsp"/>'+'?id='+valor ;
    	window.location.href = mUrl;
    }
	function MM_openBrWindow(valor) {
		var theURL = '<html:rewrite page="/pages/uploadFiles/popupObs.jsp"/>';
		var features = 'scrollbars=yes,resizable=yes,width=500,height=200,top=100,left=100';
		window.open(theURL+'?id='+valor,'',features);
	}
	function eliminar(valor){
	if(confirm('¿Confirma la eliminacion del lote?')){ 
		var mUrl = "processFileAction.do?method=eliminar&idP="+valor+"&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;
		}	
	}
	function filtrarProcesosPorEclo(){
		var mUrl = "processFileAction.do?method=init&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;
	}
	function cambiarEclo(){
	    var combo = document.getElementById("comboEclos");
		document.forms[0].elements["indice"].value = combo.selectedIndex;
		document.forms[0].elements["ecloId2"].value = combo.value;
	}	
	function cargarPagina(){
		var combo = document.getElementById("comboEclos");
		if (document.forms[0].elements["indice"].value != "")
			combo.selectedIndex = document.forms[0].elements["indice"].value;
		else{
			combo.selectedIndex = document.forms[0].indiceCombo.value;
			document.forms[0].elements["indice"].value = document.forms[0].indiceCombo.value;
		}
		document.forms[0].elements["ecloId2"].value = combo.value;
		
	}
	initializeMenus();
	init();
	window.onload = cargarPagina;  
	

	
</script>

</html:form>


					
