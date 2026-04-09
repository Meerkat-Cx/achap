<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:form action="/buscarCentroDeComputo.do" method="post" focus="idC" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="ejecutar">
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>B&uacute;squeda de Centros de Computo</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                                <TD width="70%"><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                              </TR>
                            </TABLE>
                          </DIV></TD>

                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
</br>
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR> 
            <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="2"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Filtros</TD>
        </TR>			

	<TR>		
		<TD class="celdaLabelBusqueda">Identificador:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" name="busquedaCentroDeComputoForm" property="idC" styleClass="Input100porc"/></TD>
	</TR>

	<TR>		
		<TD class="celdaLabelBusqueda">Nombre:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" name="busquedaCentroDeComputoForm" property="nombre" styleClass="Input100porc"/></TD>
	</TR>

	<c:if test="${busquedaCentroDeComputoForm.rolAdmin == busquedaCentroDeComputoForm.rol}">	
	<TR>
		<TD class="celdaLabelBusqueda">Identificador Eclo:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" name="busquedaCentroDeComputoForm" property="idEclo"  styleClass="Input100porc"/></TD>
	</TR>
	</c:if>

	<tr>
		<td class="celdaLabel">
			Activo?:
		</td>
		<td class="celdaInput">
			<html:checkbox property="activo" value="true" />
			<input type="hidden" name="activo" value="false">				
	    </td>
	</tr>

</table>
	
<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    <tr> 
        <td align="center">
		<td align="center"><input type="button" value="Buscar" class="botones" onclick="setMethod('ejecutar')"></td>				
			<td align="center">
				<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiar()">
			</td>
		</TD>
	</TR>	
</table>
	

<div class="justify">
    <display:table name="centroData" align="center" pagesize="10" id="item" sort="page" class="its" width="100%" requestURI="/buscarCentroDeComputo.do?method=ejecutar"  >
       <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Centros de Computo</font>
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
       
		<display:column align="left" title="id">
	        <a align="right" href="javascript:MM_openBrWindow('buscarCentroDeComputo.do?method=initView&id=<bean:write name="item" property="id" />','','scrollbars=yes,resizable=yes,width=900,height=350,top=100,left=100')">
	        	<bean:write name="item" property="id"/>
	        </a>
        </display:column>

        <display:column property="nombre" title="Nombre" />        

        <display:setProperty name="basic.msg.empty_list" >
           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">Nada para mostrar
            				
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
			if (!isPosInteger(document.forms[0].elements["idC"].value)) {
				alert("el identificador debe ser un número");
				return;
			}
			else{
				document.forms[0].method.value = valor;
				document.forms[0].submit();
			}
    	}
    	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
	}
	function limpiar() {
		var idE = document.forms[0].elements["idC"];
		var nomElem = document.forms[0].elements["nombre"];
		var idElem = document.forms[0].elements["idEclo"];
		idE.value = "";
		nomElem.value = "";
		idElem.value = "";
	}
	</script>    
</html:form>


