<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="visualizarReportesAchaAction.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="method" value="<c:out value="${action}"/>">
		<input type="hidden" name="estado" value="1"/>
		<input type="hidden" name="tipoBusqueda" value="eclo"/>
		<input type="hidden" name="duenio" value="tambos"/>
		<body onLoad="cambiarPropietarios('downloadReportePropietariosPorEclo')">
		<input type="hidden" name="usuario"	value="<c:out value="${usuario}"/>" />
		<TABLE width="100%" cellPadding=1 cellSpacing=2 bgcolor="#eff3e3">
		<TR> 
			  <TD class=Titulo>
					<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>                 
								Listado de Propietarios					
					</FONT>
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

    <c:if test="${usuario == 'ADMINISTRADOR'}">
		<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5" align="center" >
			<br></br>
			<tr>
				<TD class=Titulo>
					<FONT color=#529b28> <IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>                
						Filtro por propietarios					
					</FONT>
				</TD>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan="1" align="left" >
					<input type="radio" name="checkPropietarios" onclick="cambiarPropietarios('downloadReportePropietariosPorEclo')"  checked="checked" ><label>Eclo</label>
				</td> 
				<td class="celdaLabelSinAlign" colspan="1" align="left">
					<html:select property="ecloId" styleId="comboEclos"  styleClass="formfields" style="width:400px">
						<html:options collection="eclos" property="id" labelProperty="idNombre"/>
					</html:select>
				</td>
			</tr>
			
			<tr>
				<td class="celdaLabelSinAlign" colspan="1" align="left">
					<input type="radio" name="checkPropietarios" onclick="cambiarPropietarios('downloadReportePropietariosPorRegional')"   ><label>Regional</label>
				</td>
				<td class="celdaLabelSinAlign" colspan="1" align="left"> 
					<html:select property="idRegional" styleId="comboRegionales" styleClass="formfields" style="width:400px">
						<html:options collection="regionales" property="id" labelProperty="nombreContacto"/>
					</html:select>
				</td>
			</tr>
			
			<tr>
				<td class="celdaLabelSinAlign" colspan="2" align="left">
					<input type="radio" name="checkPropietarios" onclick="cambiarPropietarios('downloadReportePropietariosTodos')"   ><label>Todos</label> 
				</td>
			</tr>
								
		
		</table>	
	</c:if>

	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5" align="center" >
		<br></br>
		<tr>
			<TD class=Titulo>
				<FONT color=#529b28> <IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>                
					Estado de los propietarios					
				</FONT>
			</TD>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkEstados" onclick="cambiarEstados()"  checked ><label>Activos</label> 
			</td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkEstados" onclick="cambiarEstados()"   ><label>Inactivos</label> 
			</td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkEstados" onclick="cambiarEstados()"   ><label>Todos</label> 
			</td>
		</tr>
	</table>
	
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5" align="center" >
		<br></br>
		<tr>
			<TD class=Titulo>
				<FONT color=#529b28> <IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>                
					Propietarios de:					
				</FONT>
			</TD>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkPropietarioDe" onclick="cambiarPropietarioDe()"  checked ><label>Tambos</label> 
			</td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkPropietarioDe" onclick="cambiarPropietarioDe()"   ><label>Animales</label> 
			</td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
				<input type="radio" name="checkPropietarioDe" onclick="cambiarPropietarioDe()"   ><label>Nada (sin animales ni tambos)</label> 
			</td>
		</tr>
	</table>
	
	<table width="100%" cellspacing="0" cellpadding="5" align="center">
		<tr>
			<td class=Titulo colspan="1" align="center">
				<FONT color=#529b28>                 
					Descargar					
				</FONT>
			</td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" colspan="1" align="center">
				<img  src="img/EXCEL.jpg" onmouseover="cambiarFormaMouse()" onmouseout="cambiarFormaMouse2()" onclick="buscar()"/>
			</td>
		</tr>
	</table>
	
	
	
 	
<script language="JavaScript">

	function cambiarFormaMouse(){
		document.body.style.cursor='pointer';
	}
	
	function cambiarFormaMouse2(){
		document.body.style.cursor='default';
	}
	
	function cambiarPropietarios(valor) {
				
				if (document.forms[0].checkPropietarios[0].checked) {
					document.forms[0]["comboEclos"].disabled=false;
					document.forms[0]["comboRegionales"].disabled=true;
					document.forms[0]["tipoBusqueda"].value="eclo";
				}
				else if (document.forms[0].checkPropietarios[1].checked) {
						document.forms[0]["comboEclos"].disabled=true;
						document.forms[0]["comboRegionales"].disabled=false;
						document.forms[0]["tipoBusqueda"].value="regional";
					}
				else{
						document.forms[0]["comboEclos"].disabled=true;
						document.forms[0]["comboRegionales"].disabled=true;
						document.forms[0]["tipoBusqueda"].value="todos";
				}
			
	}
	
	function buscar(){
		document.forms[0].method.value="downloadReportePropietariosPorEclo";
		document.forms[0].submit();
	}
	
	function cambiarEstados(){
				if (document.forms[0].checkEstados[0].checked) {
					document.forms[0]["estado"].value="1";
				}
				else if (document.forms[0].checkEstados[1].checked) {
						document.forms[0]["estado"].value="0";
					}
				else{
						document.forms[0]["estado"].value="todos";
				}
	}
	
	function cambiarPropietarioDe(){
				if (document.forms[0].checkPropietarioDe[0].checked)
					document.forms[0]["duenio"].value="tambos";
				else if (document.forms[0].checkPropietarioDe[1].checked)
					document.forms[0]["duenio"].value="animales";
				else if (document.forms[0].checkPropietarioDe[2].checked)
					document.forms[0]["duenio"].value="nada";
				/*else if (document.forms[0].checkPropietarioDe[3].checked)
					document.forms[0]["duenio"].value="todos";*/
	}
		initializeMenus();
	
</script>
</html:form>