<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/EstadisticoLactanciaAction"  enctype="multipart/form-data">
	<script language='javascript' src="calendar/popcalendar.js"></script>
	<input type="hidden" name="method" value="process">
	<input type="hidden" name="conPed" value="<c:out value="${estadisticoLactanciaForm.conPed}"/>">
	<input type="hidden" name="sinPed" value="<c:out value="${estadisticoLactanciaForm.sinPed}"/>">
	<input type="hidden" name="todas" value="<c:out value="${estadisticoLactanciaForm.todas}"/>">
	<input type="hidden" name="porCat" value="<c:out value="${estadisticoLactanciaForm.porCat}"/>">
		<TABLE width="100%" cellPadding=2 cellSpacing=4 bgcolor="#eff3e3">
			    <TR> 
					<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						Reporte Estadístico de Lactancia</FONT>
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
    
    <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="5" align="center" >
    	<TR>
				<TD class="celdaLabel" align="center" >Fecha Desde  </TD>
				<td class="celdaLabelSinAlign" align="left" colSpan=4> 			             		
					 <input name="fechaDesde" type="text" id="fechaDesde" onclick="popUpCalendar(this, form.fechaDesde, 'dd/mm/yyyy');" size="10">
				</td>
		</TR>
		<tr>
				<TD class="celdaLabel" align="center" >Fecha Hasta</TD>
				<td class="celdaLabelSinAlign"align="left" colSpan=4 > 		             		
					 <input name="fechaHasta" type="text" id="fechaHasta" onclick="popUpCalendar(this, form.fechaHasta, 'dd/mm/yyyy');" size="10">			
				</td>
		</tr>
		<tr>
			<TD class="celdaLabel" align="center" >Raza</TD>
			<td class="celdaInput" colSpan=4>
			<html:select property="raza" styleClass="formfields" style="width:160px">
					<html:option value="TODAS" />
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>		
		</tr>

		 		<tr>
		 			<td class="celdaLabelSinAlign" colspan="1" align="left"/>
					<td class="celdaLabelSinAlign" colspan="4" align="left" ><input type="radio"
						name="checkAccion" >Registro HBA</td>
				</TR>
				<tr>
					<td class="celdaLabelSinAlign" colspan="1" align="left"/>
					<td class="celdaLabelSinAlign" colspan="4" align="left"><input type="radio"
						name="checkAccion" >Registro de Crías</td>
				</TR>
				<tr>
					<td class="celdaLabelSinAlign" colspan="1" align="left"/>
					<td class="celdaLabelSinAlign" colspan="4" align="left"><input type="radio"
						name="checkAccion" >Por Categoría</td>
				</TR>
				<tr>
					<td class="celdaLabelSinAlign" colspan="1" align="left"/>
					<td class="celdaLabelSinAlign" colspan="4" align="left"><input type="radio"
						name="checkAccion" >Todas las Categorias</td>
				</TR>
		<TR>
			<TD class="celdaLabel">[Descargar Reporte Estadístico] </TD>
			<TD  class="celdaLabelSinAlign" align="left" colSpan=4><a href="javascript:setMethod('descargarReporte')"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>
		</TR>
		<TR>
			<TD class="celdaLabel">[Descargar Reporte Estadístico] </TD>
			<TD  class="celdaLabelSinAlign" align="left" colSpan=4><a href="javascript:setMethod('descargarReporteXLS')"><img border="0" src="img/EXCEL.jpg" title="Descargar"/></a></TD>
		</TR>

	</table>
	<br>
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
	<tr>
	
	
	</tr>			
						
</table>
<script language="JavaScript">
window.onload = inicializarCheck(); 
		function inicializarCheck(){
			var a=document.forms[0].elements["todas"].value;
			if(a=="true")
				document.forms[0].checkAccion[3].checked=true;
		}
		
	function setMethod(valor,vale){
		if(document.forms[0].fechaDesde.value == ''){
			   		alert("La Fecha Desde no puede ser vacia");
			   		return;		
			 }
		 //Verificacion de formato de fecha correcta desde
		if(!esFechaValida(document.forms[0].fechaDesde, 'Desde'))
				return;	
		if(document.forms[0].fechaHasta.value == ''){
			   		alert("La Fecha Hasta no puede ser vacia");
			   		return;		
			 }
		 //Verificacion de formato de fecha correcta Hasta
		if(!esFechaValida(document.forms[0].fechaHasta, 'Hasta'))
				return;	
		
		document.forms[0].elements["conPed"].value=document.forms[0].checkAccion[0].checked;
		document.forms[0].elements["sinPed"].value=document.forms[0].checkAccion[1].checked;
		document.forms[0].elements["porCat"].value=document.forms[0].checkAccion[2].checked;		
		document.forms[0].elements["todas"].value=document.forms[0].checkAccion[3].checked;		
		
		document.forms[0].method.value = valor;
		document.forms[0].submit();
	}
	
	function esFechaValida(fecha, valor){
		if (fecha != undefined && fecha.value != "" ){//verifica formato correcto
		    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha.value)){
		        alert("Formato de fecha "+valor+" no válido (dd/mm/aaaa)");
		        return false;
		    }
		    // A partir de aca verifica que dia mes y año sean datos correctos (ej: dia no sea mayor a 31)
		    var dia  =  parseInt(fecha.value.substring(0,2),10);
		    var mes  =  parseInt(fecha.value.substring(3,5),10);
		    var anio =  parseInt(fecha.value.substring(6),10);
		 
		    switch(mes){
		        case 1:
		        case 3:
		        case 5:
		        case 7:
		        case 8:
		        case 10:
		        case 12:
		            numDias=31;
		            break;
		        case 4: case 6: case 9: case 11:
		            numDias=30;
		            break;
		        case 2:
		            if (comprobarSiBisisesto(anio)){ numDias=29 }else{ numDias=28};
		            break;
		        default:
		            alert("Fecha "+valor+" introducida errónea");
		            return false;
		    }
		 
		        if (dia>numDias || dia==0){
		            alert("Fecha "+valor+" introducida errónea");
		            return false;
		        }
			  //Valido q la fecha no sea mayor al dia de hoy
			  var f1 = new Date();
			  var f2 = new Date(mes+"/"+dia+"/"+anio);
			  if (f1<f2){
			     alert("Fecha "+valor+" introducida mayor al día actual");
			     return false;
			 }   
		   return true;
		 }
	}
 
	function comprobarSiBisisesto(anio){
		if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {
		    return true;
		}
		else {
		    return false;
		}
	}
		
	
	initializeMenus();
	init();
</script>

</html:form>
