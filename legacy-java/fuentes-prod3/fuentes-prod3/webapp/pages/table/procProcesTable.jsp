<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>


<%@page import="ar.org.sicel.persistence.ProcLote"%>
<html:form action="/buscarProcesos.do"  >
<input type="hidden"  name="method" value="execute">
<html:javascript formName="buscarProcesosForm"/>
<div class="justify">
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Listado de Procesos</FONT>
                </TD>
                <TD> <DIV align=right></DIV></TD>
            </TR>
            <TR> 
            	<TD class=texto4 colSpan=2> <DIV align=right> 
                	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    	<TR> 
                        	<TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                            <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                        </TR>
                    </TABLE>
                 </DIV></TD>
            </TR>
         </TABLE>
         <br>
   		 <!-- Fin Titulo de la pagina -->
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
            	<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            		<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Número de Lote y Eclo
            	</TD>
            </TR>			
			<TR>
				<TD class="celdaLabelBusqueda">
					Número de lote:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="nroLote" styleClass="Input100porc" styleId="lote"/>
				</TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">
					Id Eclo:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="nroEclo1" styleClass="Input100porc" styleId="eclo1"/>
				</TD>
			</TR>
		</table>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	        <tr> 
	        	<td align="center">
				<td align="center">
					<input type="button" value="Buscar" class="botones" onclick="buscarPorNumeroLoteYEclo('buscarPorNumeroLoteYEclo')">
				</td>				
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarNumeroLoteYEclo()">
				</td>
			</TR>	
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
            	<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            		<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Id evento resultado
            	</TD>
            </TR>			
			<TR>
				<TD class="celdaLabelBusqueda">
					Id evento resultado:
				</TD>
				<TD class="celdaInputBusqueda">
				<html:text size="45" property="idEventoResultado" styleClass="Input100porc" styleId="eventoRes"/>	
				</TD>
			</TR>
		</table>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	        <tr> 
	        	<td align="center">
				<td align="center">
					<input type="button" value="Buscar" class="botones" onclick="buscarPorIdEventoResultado('buscarPorIdEventoResultado')">
				</td>				
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarIdEventoResultado()">
				</td>
			</TR>	
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
            	<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            		<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Id evento informado y Eclo
            	</TD>
            </TR>			
			<TR>
				<TD class="celdaLabelBusqueda">
					Id evento informado:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="idEventoInformado" styleClass="Input100porc" styleId="eventoInf"/>
				</TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">
					Id Eclo:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="nroEclo2" styleClass="Input100porc" styleId="eclo2"/>
				</TD>
			</TR>
		</table>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	        <tr> 
	        	<td align="center">
				<td align="center">
					<input type="button" value="Buscar" class="botones" onclick="buscarPorIdEventoInformadoYEclo('buscarPorIdEventoInformadoYEclo')">
				</td>				
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarIdEventoInformadoYEclo()">
				</td>
			</TR>	
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
            	<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            		<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Búsqueda de controles
            	</TD>
            </TR>			
			<TR>
				<TD class="celdaLabelBusqueda">
					Id ordeñe informado:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="ordenieInformado" styleClass="Input100porc" styleId="ordInf"/>
				</TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">
					Id control informado:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="controlInformado" styleClass="Input100porc" styleId="controlInf"/>
				</TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">
					Id evento resultado:
				</TD>
				<TD class="celdaInputBusqueda">
					<html:text size="45" property="idEventoResultado2" styleClass="Input100porc" styleId="eventoRes2"/>
				</TD>
			</TR>
		</table>
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
	        <tr> 
	        	<td align="center">
				<td align="center">
					<input type="button" value="Buscar" class="botones" onclick="buscarControles('buscarControles')">
				</td>				
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarControles()">
				</td>
			</TR>	
		</table>
    <display:table name="listaProcesos" pagesize="10" align="center" class="its" requestURI="/buscarProcesos.do?method=paginado" id="item">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Procesos</FONT></TD>
                      <TD> <DIV align=right></DIV></TD>
                    </TR>
                    <TR> 
                      <TD class=texto4 colSpan=2> <DIV align=right> 
                          <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>

                            <TR> 
                              <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="/web-1.0/pages/assets/images/pixel.gif" 
                  width=2></TD>
                              <TD width="70%"><IMG height=2 
                  src="/web-1.0/pages/assets/images/pixel.gif" 
                  width=2></TD>
                            </TR>
                          </TABLE>
                        </DIV></TD>
                    </TR>
                  </TABLE>
				  <br>
        </display:caption>
        <display:column property="procProces.id"
                        title="Id"
                        href="buscarProcProces.do"
                        paramId="procProcesId" 
                        paramProperty="procProces.id" />
        <display:column align="left" title="Usuario">
        	<%=((ProcLote)item).getProcProces().getUsuario().getNombre()+" "+((ProcLote)item).getProcProces().getUsuario().getApellido()%>
        </display:column>
        <display:column align="left" property="numLote" title="Nº Lote" />
        <display:column align="left" property="eclo.id" title="Eclo" />
        <display:column align="left" property="sistema.nombre" title="Sistema" />
        <display:column align="left" property="centroComputo.id" title="Centro" />

	      <display:column align="center" width="7%" title="Salida"  >
				<a href="buscarProcesos.do?method=downloadZIPOut&id=<%=((ProcLote)item).getProcProces().getId()%>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
			</display:column>	

        <display:setProperty name="basic.msg.empty_list" >
            <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Procesos
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>
</html:form>
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
	function limpiarNumeroLoteYEclo(){
			var campo1 = document.forms[0].elements["lote"];
			campo1.value = "";
			var campo2 = document.forms[0].elements["eclo1"];
			campo2.value = "";
	}
		
	function buscarPorNumeroLoteYEclo(valor){
		var campo1 = document.forms[0].elements["lote"];
		var campo2 = document.forms[0].elements["eclo1"];
		if (campo1.value == "" && campo2.value == "")
			alert("Al menos el número de Eclo debe estar cargado.");
		else if (campo1.value != "" && campo2.value == "")
			alert("Si busca por número de lote, el número de Eclo debe estar cargado.");
		else if (campo1.value != "" && !isPosInteger(campo1.value))
			alert("El número de lote debe ser un número.");
		else if (campo2.value != "" && !isPosInteger(campo2.value))
			alert("La Eclo debe ser un número.");
		else {
				document.forms[0].method.value = valor;
				document.forms[0].submit();
			}
	}
	
	function limpiarIdEventoResultado(){
			var campo1 = document.forms[0].elements["eventoRes"];
			campo1.value = "";
	}
		
	function buscarPorIdEventoResultado(valor){
		var campo1 = document.forms[0].elements["eventoRes"];
		if (campo1.value == "")
			alert("El identificador del evento resultado debe estar cargado.");
		else if (!isPosInteger(campo1.value))
			alert("El identificador del evento resultado debe ser un número.");
		else{
			document.forms[0].method.value = valor;
			document.forms[0].submit();
		}
	}
	
	function limpiarIdEventoInformadoYEclo(){
			var campo1 = document.forms[0].elements["eclo2"];
			campo1.value = "";
			var campo2 = document.forms[0].elements["eventoInf"];
			campo2.value = "";
	}
		
	function buscarPorIdEventoInformadoYEclo(valor){
		var campo1 = document.forms[0].elements["eclo2"];
		var campo2 = document.forms[0].elements["eventoInf"];
		if (campo1.value == "" || campo2.value == "")
			alert("Deben estar cargados ambos datos.");
		else if (campo1.value != "" && !isPosInteger(campo1.value))
			alert("La Eclo debe ser un número.");
		else if (campo2.value != "" && !isPosInteger(campo2.value))
			alert("El identificador del evento informado debe ser un número.");
		else {
				document.forms[0].method.value = valor;
				document.forms[0].submit();
		}
	}
	
	
	function limpiarControles(){
			var campo1 = document.forms[0].elements["ordInf"];
			campo1.value = "";
			var campo2 = document.forms[0].elements["controlInf"];
			campo2.value = "";
			var campo3 = document.forms[0].elements["eventoRes2"];
			campo3.value = "";
	}
	
	function buscarControles(valor){
		var campo1 = document.forms[0].elements["ordInf"];
		var campo2 = document.forms[0].elements["controlInf"];
		var campo3 = document.forms[0].elements["eventoRes2"];
		if (campo1.value == "" && campo3.value == "" /*|| campo3.value == ""*/)
			alert("Debe estar cargado al menos un dato");
		else if (campo1.value != "" && !isPosInteger(campo1.value))
			alert("EL número de ordeñe informado debe ser un número.");
		/*else if (campo2.value != "" && !isPosInteger(campo2.value))
			alert("El número de control informado debe ser un número.");*/
		else if (campo3.value != "" && !isPosInteger(campo3.value))
			alert("El número de control resultado debe ser un número.");
		else {
				document.forms[0].method.value = valor;
				document.forms[0].submit();
		}
	}
</script>
