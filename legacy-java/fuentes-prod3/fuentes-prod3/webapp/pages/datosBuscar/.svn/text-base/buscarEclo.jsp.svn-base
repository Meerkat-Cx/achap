<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="/tags/displaytag" prefix="display" %>

<html:form action="/buscaEclo.do" method="post" focus="identificador" onsubmit="return validate();">		

	<html:javascript formName="ecloBuscarForm"/>

		<input type="hidden" name="method" value="buscar"/>
		
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                      		&nbsp;Busqueda de Entidades de Control Lechero</FONT>
                      </TD>
                      <TD> <DIV align=right></DIV>
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
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
                        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Filtros</TD>
                      </TR>			

	<TR>
		<TD class="celdaLabelBusqueda">Identificador:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" property="identificador"  styleClass="Input100porc" styleId="identificador"/></TD>
	</TR>
	<TR>
		<TD class="celdaLabelBusqueda">Nombre de Eclo:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" property="nombre" styleClass="Input100porc" styleId="nombre"/></TD>
	</TR>
	<TR>
		<TD class="celdaLabelBusqueda">Nombre de Regional:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" property="nombreRegional" styleClass="Input100porc" styleId="nombreRegional"/></TD>
	</TR>	
	</table>
	
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="menu.find"/>
							</html:submit>
						</TD>
						<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</TR>	
		</table>
		
		<br/>
		
		
		<div class="justify">
    <display:table name="listaEclos" align="center" class="its" id="eclo" 	pagesize="8" requestURI="/buscaEclo.do?method=buscar">
        <display:caption>
           <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Resultado</FONT></TD>
                      <TD> <DIV align=right></DIV>
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
			<br>
			
        </display:caption>
        <display:column align="left" property="id" title="ID"/>
        <display:column align="left" title="Nombre">
	        <a align="right" href="javascript:MM_openBrWindow('buscaEclo.do?method=mostrarDetalle&ecloId=<bean:write name="eclo" property="id" />','','scrollbars=yes,resizable=yes,width=900,height=600,top=100,left=100')">
	        	<bean:write name="eclo" property="nombreContacto"/>
	        </a>
        </display:column>
        <display:column align="left" property="regional.nombreContacto" title="Regional"/>
        <display:setProperty name="basic.msg.empty_list" >
        	<tr>
	            <td class="celdaInput"><bean:message key="displayTag.basic.msg.empty_list" /></td>        		
        	</tr>

        </display:setProperty>
    </display:table>
</div>
		
		
		
</html:form>
		

<script>
	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
	}

	function limpiar() {
		var campo1 = document.forms[0].elements["identificador"];		
		var campo2 = document.forms[0].elements["nombre"];		
		var campo3 = document.forms[0].elements["nombreRegional"];		
		campo1.value = "";
		campo2.value = "";
		campo3.value = "";
	}
	
	function validate() {
		return validateEcloBuscarForm(document.forms[0]);
	}	
</script>
