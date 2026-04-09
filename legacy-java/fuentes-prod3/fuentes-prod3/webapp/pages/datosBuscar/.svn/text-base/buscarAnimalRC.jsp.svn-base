<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/generarReportesEclo.do"
	onsubmit="return validate();">

	<!-- Titulo de la pagina -->
	<input type="hidden" name="method" value="descargarExportacionPDF">

	<input type="hidden" name="unEst" value="<c:out value="${unEst}"/>" />
	<input type="hidden" name="unEstTodos"	value="<c:out value="${unEstTodos}"/>" />
	<input type="hidden" name="unEstPropios" value="<c:out value="${unEstPropios}"/>" />
	<input type="hidden" name="unPro" value="<c:out value="${unPro}"/>" />
	<input type="hidden" name="unEcl" value="<c:out value="${unEcl}"/>" />
	<input type="hidden" name="tambos" value="<c:out value="${tambos}"/>" />
	<input type="hidden" name="animales" value="<c:out value="${animales}"/>" />
	<input type="hidden" name="calificados"	value="<c:out value="${calificados}"/>" />
	<input type="hidden" name="orden" value="<c:out value="${orden}"/>" />
	<input type="hidden" name="propId" value="<c:out value="${propId}"/>" />
	<input type="hidden" name="ecloId" value="<c:out value="${ecloId}"/>" />
	<input type="hidden" name="tamboId" value="<c:out value="${tamboId}"/>" />
	<input type="hidden" name="tipoLogin" value="<c:out value="${tipoLogin}"/>" />
	<input type="hidden" name="eclosOrPropietarios"	value="<c:out value="${eclosOrPropietarios}"/>" />
	<input type="hidden"  name="indiceCombo" value="<c:out value="${indiceCombo}"/>"/>

	<TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
		<TR>
			<TD class=Titulo><FONT color=#529b28><IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>Reporte
			RC-HBANUME </FONT></TD>
			<TD>
			<DIV align=right></DIV>
			</TD>
		</TR>
		<TR>
			<TD class=texto4 colSpan=2>
			<DIV align=right>
			<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0
				cellPadding=0 width="100%" border=0>
				<TR>
					<TD width="30%" bgColor=#529b28><IMG height=2
						src="pages/assets/images/pixel.gif" width=2></TD>
					<TD width="70%"><IMG height=2
						src="pages/assets/images/pixel.gif" width=2></TD>
				</TR>
			</TABLE>
			</DIV>
			</TD>
		</TR>
	</TABLE>
	<!-- Fin Titulo de la pagina -->
	<br />

	<!-- Filtro 1: si entra como admin eligira eclos o propietarios -->
	<div id="filtro1" style="">
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">

		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Eclos/Propietarios: </FONT></TD>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" colspan="2">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3"
				align="right">
				<tr>
					<td class="celdaLabel"></td>
					<td class="celdaLabelSinAlign" colspan="2">
						<c:if test="${eclosOrPropietarios == 'eclos'}">
							<input type="radio" name="checkFiltro1" onclick="cambiarFiltro1()"	checked>una eclo
						</c:if>
						<c:if	test="${eclosOrPropietarios == '' || eclosOrPropietarios != 'eclos'}">
							<input type="radio" name="checkFiltro1" onclick="cambiarFiltro1()">una eclo
						</c:if>
					</td>
				
				
					<td class="celdaLabelSinAlign" colspan="1" align="left">
						<html:select onchange="changeComboEclos1()" value="ecloIdCombo" property="ecloIdCombo" styleId="comboEclos"  name="reportesEcloForm" styleClass="formfields" style="width:400px">
						<html:options collection="eclos" property="id" labelProperty="idNombre" />
						</html:select>
					</td>
				</tr>

				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2">
					<c:if test="${eclosOrPropietarios == 'propietarios'}">
						<input type="radio" name="checkFiltro1" onclick="cambiarFiltro1()" checked>un propietario
					</c:if> 
					<c:if test="${eclosOrPropietarios == '' || eclosOrPropietarios != 'propietarios'}">
						<input type="radio" name="checkFiltro1" onclick="cambiarFiltro1()">un propietario
					</c:if>
					<TD class="celdaLabelSinAlign">
						<input type="button" id="boton2" value="Buscar Propietario"	class="botones" name="buscarP" onClick="initBuscarPropietario()">
					</TD>
				</tr>
				
				<tr>
					<TD colspan="3" class="celdaLabel" align="left">
						Identificador del Propietario:
					</TD>
					<TD class="celdaLabelSinAlign"><html:hidden
						name="reportesEcloForm" property="idProp" />
						<div id="propietarioVisible">
							<c:out	value="${reportesEcloForm.idProp}" />
							<c:out	value="${reportesEcloForm.nombreProp}" />
						</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	</div>
	<!-- FIN Filtro 1 -->

	<!-- Filtro 2: entro como Eclo, elige uno o todos los propietarios -->
	<div id="filtro2" style="">
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">

		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Propietarios: </FONT></TD>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" colspan="3">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3"
				align="right">
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2">
					<c:if	test="${unPro == 'si'}">
						<input type="radio" name="checkFiltro2" onclick="cambiarFiltro2()" checked>un propietario
					</c:if> 
					<c:if test="${unPro == 'no'}">
						<input type="radio" name="checkFiltro2" onclick="cambiarFiltro2()">un propietario
					</c:if>
					</td>
				<tr>
				<tr>
					<td class="celdaLabel">
					</td>
					<td class="celdaLabelSinAlign" colspan="2">
					<c:if test="${unPro == 'si'}">
						<input type="radio" name="checkFiltro2" onclick="cambiarFiltro2()">todos los propietario
							
					</c:if>
					<c:if test="${unPro == 'no'}">
						<input type="radio" name="checkFiltro2" onclick="cambiarFiltro2()" checked>todos los propietario
					</c:if>
				</tr>
				<TR>
					<TD class="celdaLabel" align="right">Identificador del
					Propietario:</TD>
					<TD class="celdaLabelSinAlign"><html:hidden
						name="reportesEcloForm" property="idProp" />
					<div id="propietarioVisible2"><c:out
						value="${reportesEcloForm.idProp}" /> <c:out
						value="${reportesEcloForm.nombreProp}" /></div>
					</td>
					<TD class="celdaLabelSinAlign"><c:if test="${unPro == 'si'}">
						<input type="button" id="boton3" value="Buscar Propietario"
							class="botones" name="buscarP" onClick="initBuscarPropietario()">
					</c:if> <c:if test="${unPro == 'no'}">
						<input type="button" id="boton3" value="Buscar Propietario"
							class="botones" disabled="disabled" name="buscarP"
							onClick="initBuscarPropietario()">
					</c:if></TD>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	</div>
	<!-- FIN Filtro 2 -->
	<!-- Filtro 3: entró como Propietario, elige una o todas las eclos -->
	<div id="filtro3" style="">
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">

		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Eclos: </FONT></TD>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" colspan="3">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3"
				align="right">
				<tr>
					<td class="celdaLabel"></td>
					<td class="celdaLabelSinAlign" colspan="2"  >
						<c:if test="${unEcl == 'si'}">
							<input type="radio" name="checkFiltro3" onclick="cambiarFiltro3()" checked>una eclo	
						</c:if>
						<c:if test="${unEcl == 'no'}">
							<input type="radio" name="checkFiltro3" onclick="cambiarFiltro3()">una eclo
						</c:if>
					</td>
					<td class="celdaLabelSinAlign" colspan="1" align="left">
						<html:select onchange="changeComboEclos2()" property="ecloIdCombo2" styleId="comboEclos2"  name="reportesEcloForm" styleClass="formfields" style="width:400px">
						<html:options collection="eclos" property="id" labelProperty="idNombre" />
						</html:select>
					</td>
				<tr>
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2">
						<c:if test="${unEcl == 'si'}">
							<input type="radio" name="checkFiltro3" onclick="cambiarFiltro3()">todas las eclos
						</c:if>
						<c:if test="${unEcl == 'no'}">
							<input type="radio" name="checkFiltro3" onclick="cambiarFiltro3()" checked>todas las eclos
						</c:if>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	</div>
	<!-- FIN Filtro 3 -->
	<!-- Resto de los filtros  -->
	<div id="filtro4" style="">
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">
		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Animales: </FONT></TD>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2">
				<tr>
					<td class="celdaLabel" align="left" colspan="1"><c:if
						test="${tambos == 'propios'}">
						<input type="radio" name="checkFiltro4" onclick="cambiarFiltro4()">
					</c:if> <c:if test="${tambos == 'todos'}">
						<input type="radio" name="checkFiltro4" checked
							onclick="cambiarFiltro4()">
					</c:if> <strong>Propios sobre cualquier tambo:&nbsp;</strong></td>
					<td class="celdaLabel"></td>
					<td class="celdaLabel"></td>
				</tr>
				<tr>
					<td class="celdaLabel"></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${unEstTodos == 'si'}">
						<input type="radio" name="checkFiltro4A"
							onclick="cambiarFiltro4A()" checked>en un tambo
							</c:if> <c:if test="${unEstTodos == 'no'}">
						<input type="radio" name="checkFiltro4A"
							onclick="cambiarFiltro4A()">en un tambo	
							</c:if>
				</tr>
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${unEstTodos == 'si'}">
						<input type="radio" name="checkFiltro4A"
							onclick="cambiarFiltro4A()">todos los tambos
							</c:if> <c:if test="${unEstTodos == 'no'}">
						<input type="radio" name="checkFiltro4A"
							onclick="cambiarFiltro4A()" checked>todos los tambos
							</c:if>
				</tr>
				<TR>
					<TD class="celdaLabel" align="right">Identificador del Tambo:
					</TD>
					<TD class="celdaLabelSinAlign"><html:hidden
						name="reportesEcloForm" property="idEstablecimiento" />
					<div id="estabVisible" style=""><c:if
						test="${tambos == 'todos' && unEstTodos == 'si' && tamboId != ''}">
						<c:out value="${reportesEcloForm.idEstablecimiento}" />
						<c:out value="${reportesEcloForm.nombreEstablecimiento}" />
					</c:if> <c:if
						test="${!(tambos == 'todos' && unEstTodos == 'si' && tamboId != '')}">
						<c:out value="--" />
					</c:if></div>
					</td>
					<TD class="celdaLabelSinAlign"><c:if
						test="${unEstTodos == 'si'}">
						<input type="button" id="boton5" value="Buscar Tambo"
							class="botones" name="buscarP"
							onClick="initBuscarEstablecimientosTodos()">
					</c:if> <c:if test="${unEstTodos == 'no'}">
						<input type="button" id="boton5" value="Buscar Tambo"
							class="botones" disabled="disabled" name="buscarP"
							onClick="initBuscarEstablecimientosTodos()">
					</c:if></TD>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2">
				<tr>
					<td class="celdaLabel" align="left" colspan="1"><c:if
						test="${tambos == 'propios'}">
						<input type="radio" name="checkFiltro4" onclick="cambiarFiltro4()"
							checked>
					</c:if> <c:if test="${tambos == 'todos'}">
						<input type="radio" name="checkFiltro4" onclick="cambiarFiltro4()">
					</c:if> <strong>Propios sobre tambos propios:&nbsp;</strong></td>
					<td class="celdaLabel"></td>
					<td class="celdaLabel"></td>
				</tr>
				<tr>
					<td class="celdaLabelSinAlign" colspan="3">
					<table width="100%" class="" cellSpacing="0"
						cellPadding="3">
						<tr>
							<td class="celdaLabel"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2"><c:if
								test="${unEstPropios == 'si'}">
								<input type="radio" name="checkFiltro4B1"
									onclick="cambiarFiltro4B1()" checked>en un tambo
										</c:if> <c:if test="${unEstPropios == 'no'}">
								<input type="radio" name="checkFiltro4B1"
									onclick="cambiarFiltro4B1()">en un tambo
										</c:if>
						<tr>
						<tr>
							<td class="celdaLabel"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2"><c:if
								test="${unEstPropios == 'si'}">
								<input type="radio" name="checkFiltro4B1"
									onclick="cambiarFiltro4B1()">todos los tambos
										</c:if> <c:if test="${unEstPropios == 'no'}">
								<input type="radio" name="checkFiltro4B1"
									onclick="cambiarFiltro4B1()" checked>todos los tambos
										</c:if>
						</tr>
						<TR>
							<TD class="celdaLabel" align="right">Identificador del
							Tambo:</TD>
							<TD class="celdaLabelSinAlign"><html:hidden
								name="reportesEcloForm" property="idEstablecimiento" />
							<div id="estabVisible2" style=""><c:if
								test="${tambos == 'propios' && unEstPropios == 'si' && tamboId != ''}">
								<c:out value="${reportesEcloForm.idEstablecimiento}" />
								<c:out value="${reportesEcloForm.nombreEstablecimiento}" />
							</c:if> <c:if
								test="${!(tambos == 'propios' && unEstPropios == 'si' && tamboId != '')}">
								<c:out value="--" />
							</c:if></div>
							</td>
							<TD class="celdaLabelSinAlign"><c:if
								test="${unEstPropios == 'si'}">
								<input type="button" id="boton6" value="Buscar Tambo"
									class="botones" name="buscarP"
									onClick="initBuscarEstablecimientosPropios()">
							</c:if> <c:if test="${unEstPropios == 'no'}">
								<input type="button" id="boton6" value="Buscar Tambo"
									class="botones" disabled="disabled" name="buscarP"
									onClick="initBuscarEstablecimientosPropios()">
							</c:if></TD>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabelSinAlign" colspan="3">
					<table width="100%" class="" cellSpacing="0"
						cellPadding="3">
						<tr>
							<td class="celdaLabel" align="right"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2"><c:if
								test="${animales == 'propios'}">
								<input type="radio" name="checkFiltro4B2"
									onclick="cambiarFiltro4B2()" checked>animales propios
										</c:if> <c:if test="${animales == 'todos'}">
								<input type="radio" name="checkFiltro4B2"
									onclick="cambiarFiltro4B2()">animales propios
										</c:if>
						<tr>
						<tr>
							<td class="celdaLabel"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2"><c:if
								test="${animales == 'propios'}">
								<input type="radio" name="checkFiltro4B2"
									onclick="cambiarFiltro4B2()">todos los animales
										</c:if> <c:if test="${animales == 'todos'}">
								<input type="radio" name="checkFiltro4B2"
									onclick="cambiarFiltro4B2()" checked>todos los animales
										</c:if>
						</tr>
						<tr>
							<td class="celdaLabel"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2">
							<td class="celdaLabelSinAlign" colspan="2">
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">

		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Calificacion: </FONT></TD>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" colspan="3">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3"
				align="right">
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${calificados == 'si'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()"
							checked>animales calificados
								</c:if> <c:if test="${calificados == 'no'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">animales calificados
								</c:if> <c:if test="${calificados == 'todos'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">animales calificados
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				<tr>
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${calificados == 'si'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">animales no calificados
								</c:if> <c:if test="${calificados == 'no'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()"
							checked>animales no calificados
								</c:if> <c:if test="${calificados == 'todos'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">animales no calificados
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				</tr>
				<tr>
					<td class="celdaLabel"></strong></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${calificados == 'si'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">todos los animales
								</c:if> <c:if test="${calificados == 'no'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()">todos los animales
								</c:if> <c:if test="${calificados == 'todos'}">
						<input type="radio" name="checkFiltro5" onclick="cambiarFiltro5()"
							checked>todos los animales
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br>
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5">

		<tr>
			<TD class=Titulo><FONT color=#529b28> <IMG height=15
				hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11>
			Ordenamiento: </FONT></TD>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" colspan="3">
			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="3"
				align="right">
				<tr>
					<td class="celdaLabel">
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${orden == '0'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()"
							checked>RP
								</c:if> <c:if test="${orden == '1'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">RP
								</c:if> <c:if test="${orden == '2'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">RP
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				<tr>
				<tr>
					<td class="celdaLabel">
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${orden == '0'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">Fecha de nacimiento
								</c:if> <c:if test="${orden == '1'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()"
							checked>Fecha de nacimiento
								</c:if> <c:if test="${orden == '2'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">Fecha de nacimiento
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				</tr>
				<tr>
					<td class="celdaLabel"></td>
					<td class="celdaLabelSinAlign" colspan="2"><c:if
						test="${orden == '0'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">RP - Fecha de nacimiento
								</c:if> <c:if test="${orden == '1'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()">RP - Fecha de nacimiento
								</c:if> <c:if test="${orden == '2'}">
						<input type="radio" name="checkFiltro6" onclick="cambiarFiltro6()"
							checked>RP - Fecha de nacimiento
								</c:if>
					<td class="celdaLabelSinAlign" colspan="2">
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<table width="90%" border="0" cellpadding="10" cellspacing="0">
		<TR>
			<TD class="celdaLabel">[reporte PDF]</TD>
			<TD class="TextoNegro"><a href="javascript:setMethod('pdf')"><img
				border="0" src="img/PDF.jpg" title="Descargar" /></a></TD>
			<TD class="celdaLabel">[reporte TXT]</TD>
			<TD class="TextoNegro"><a href="javascript:setMethod('txt')"><img
				border="0" src="img/TXT.jpg" title="Descargar" /></a></TD>
		</TR>
	</table>
	</div>
	<!-- FIN Resto de los filtros  -->




	<script language="JavaScript">
	
	function initBuscarEstablecimientosTodos(){
		document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstTodos"].value;
		var mUrl="generarReportesEclo.do?method=initBuscarEstablecimiento"+
    			 "&unPro="+document.forms[0].elements["unPro"].value+
    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
    			 "&tambos="+document.forms[0].elements["tambos"].value+
    			 "&propId="+document.forms[0].elements["propId"].value+
    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
    			 "&unEst="+document.forms[0].elements["unEst"].value+
    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
    			 "&animales="+document.forms[0].elements["animales"].value+
    			 "&calificados="+document.forms[0].elements["calificados"].value+
    			 "&orden="+document.forms[0].elements["orden"].value+
    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
    	window.location.href = mUrl;
    }
    
   function initBuscarEstablecimientosPropios(){
	   	document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstPropios"].value;
    	var mUrl="generarReportesEclo.do?method=initBuscarEstablecimiento"+
    			 "&unPro="+document.forms[0].elements["unPro"].value+
    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
    			 "&tambos="+document.forms[0].elements["tambos"].value+
    			 "&propId="+document.forms[0].elements["propId"].value+
    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
    			 "&unEst="+document.forms[0].elements["unEst"].value+
    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
    			 "&animales="+document.forms[0].elements["animales"].value+
    			 "&calificados="+document.forms[0].elements["calificados"].value+
    			 "&orden="+document.forms[0].elements["orden"].value+
    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
    	window.location.href = mUrl;
    }
    
    function initBuscarPropietario(){
    	
    	var mUrl="generarReportesEclo.do?method=initBuscarPropietario"+
    			 "&unPro="+document.forms[0].elements["unPro"].value+
    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
    			 "&tambos="+document.forms[0].elements["tambos"].value+
    			 "&propId="+document.forms[0].elements["propId"].value+
    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
    			 "&unEst="+document.forms[0].elements["unEst"].value+
    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
    			 "&animales="+document.forms[0].elements["animales"].value+
    			 "&calificados="+document.forms[0].elements["calificados"].value+
    			 "&orden="+document.forms[0].elements["orden"].value+
    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
    	window.location.href = mUrl;
    }			
    
    function initBuscarEclo(){
    	var mUrl="generarReportesEclo.do?method=initBuscarEclo"+
    			 "&unPro="+document.forms[0].elements["unPro"].value+
    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
    			 "&tambos="+document.forms[0].elements["tambos"].value+
    			 "&propId="+document.forms[0].elements["propId"].value+
    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
    			 "&unEst="+document.forms[0].elements["unEst"].value+
    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
    			 "&animales="+document.forms[0].elements["animales"].value+
    			 "&calificados="+document.forms[0].elements["calificados"].value+
    			 "&orden="+document.forms[0].elements["orden"].value+
    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
    	window.location.href = mUrl;
    }
    
    function setMethod(valor){		
		var mUrl="";
		if((document.forms[0].elements["unPro"].value=='si')&&(document.forms[0].elements["propId"].value==""))
    			alert("Debe Buscar un Propietario");
		else{
			if((document.forms[0].elements["unEst"].value=='si')&&(document.forms[0].elements["tamboId"].value==""))
					alert("Debe Buscar un Tambo");
			else{	
				if((document.forms[0].elements["unEcl"].value=='si')&&(document.forms[0].elements["ecloId"].value==""))
					alert("Debe Buscar una Eclo");
				else{
					if(valor=='pdf'){
							var mUrl="generarReportesEclo.do?method=descargarExportacionPDF"+
					    			 "&unPro="+document.forms[0].elements["unPro"].value+
					    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
					    			 "&tambos="+document.forms[0].elements["tambos"].value+
					    			 "&propId="+document.forms[0].elements["propId"].value+
					    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
					    			 "&unEst="+document.forms[0].elements["unEst"].value+
					    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
   			 						 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
					    			 "&animales="+document.forms[0].elements["animales"].value+
					    			 "&calificados="+document.forms[0].elements["calificados"].value+
					    			 "&orden="+document.forms[0].elements["orden"].value+
					    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
					    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
					    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
					    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
					    	window.location.href = mUrl;
						}
					else{
						var mUrl="generarReportesEclo.do?method=descargarExportacionTXT"+
					    			 "&unPro="+document.forms[0].elements["unPro"].value+
					    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
					    			 "&tambos="+document.forms[0].elements["tambos"].value+
					    			 "&propId="+document.forms[0].elements["propId"].value+
					    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
					    			 "&unEst="+document.forms[0].elements["unEst"].value+
					    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 					 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
					    			 "&animales="+document.forms[0].elements["animales"].value+
					    			 "&calificados="+document.forms[0].elements["calificados"].value+
					    			 "&orden="+document.forms[0].elements["orden"].value+
					    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
					    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
					    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
					    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
					    window.location.href = mUrl;
						
					}	      	
				}
			}
		}
	}
		
	function cambiarFiltro1(){
				if (document.forms[0].checkFiltro1[0].checked) {
					document.getElementById("filtro3").style.display="none";
					document.getElementById("filtro2").style.display="";
					document.getElementById("filtro4").style.display="";
					document.forms[0].elements["eclosOrPropietarios"].value="eclos";
					document.forms[0]["comboEclos"].disabled=false;
					document.forms[0].elements["ecloId"].value=document.forms[0].elements["ecloIdCombo"].value;
					changeComboEclos1();
					var boton2 = document.getElementById("boton2");
					boton2.disabled="disabled";
					boton2.style.backgroundColor = "gray";
				}
				else if (document.forms[0].checkFiltro1[1].checked){
					document.getElementById("filtro2").style.display="none";
					document.getElementById("filtro3").style.display="";
					document.getElementById("filtro4").style.display="";
					document.forms[0].elements["eclosOrPropietarios"].value="propietarios";
					document.forms[0]["comboEclos"].disabled=true;
					document.forms[0].elements["ecloId"].value="";
					var boton2 = document.getElementById("boton2");
					boton2.disabled="";
					boton2.style.backgroundColor = "529b28";
					cambiarFiltro3();
				}
	}
	
	function cambiarFiltro2(){
				if (document.forms[0].checkFiltro2[0].checked) {
					document.forms[0].elements["unPro"].value="si";
					var boton = document.getElementById("boton3");
					boton.disabled="";
					boton.style.backgroundColor="529b28";
					}
				else{
					if (document.forms[0].elements["idProp"]!=null)
						document.forms[0].elements["idProp"].value=null;
					if (document.forms[0].elements["nombreProp"]!=null)
						document.forms[0].elements["nombreProp"].value=null;
					document.forms[0].elements["unPro"].value="no";
					var elementoVisible = document.getElementById("propietarioVisible");
					var elementoVisible2 = document.getElementById("propietarioVisible2");
				    var resultNode1 = document.createTextNode("--");
				    var resultNode2 = document.createTextNode("--");
				    var oldChild1 = elementoVisible.childNodes[0];
				    var oldChild2 = elementoVisible2.childNodes[0];
					elementoVisible.replaceChild(resultNode1, oldChild1);
					elementoVisible2.replaceChild(resultNode2, oldChild2);
					var boton = document.getElementById("boton3");
					boton.disabled="disabled";
					boton.style.backgroundColor="gray";

				}
	}
	
	function cambiarFiltro3(){
	
				if (document.forms[0].checkFiltro3[0].checked) {
					document.forms[0].elements["comboEclos2"].disabled=false;
					document.forms[0].elements["unEcl"].value="si";
					changeComboEclos2();
					}
				else{
					if (document.forms[0].elements["ecloId"]!=null)
						document.forms[0].elements["ecloId"].value="";
					if (document.forms[0].elements["nombreEclo"]!=null)
						document.forms[0].elements["nombreEclo"].value=null;
					document.forms[0].elements["unEcl"].value="no";
					document.forms[0].elements["comboEclos2"].disabled=true;
				}
	}
	
	function cambiarFiltro4(){
	
				if (document.forms[0].checkFiltro4[0].checked) {
					document.forms[0].elements["tambos"].value="todos";
					var boton5 = document.getElementById("boton5");
					if (document.forms[0].elements["unEstTodos"].value == "si"){
						boton5.disabled="";
						boton5.style.backgroundColor = "529b28";
					}
					var boton6 = document.getElementById("boton6");
					boton6.disabled="disabled";
					boton6.style.backgroundColor = "gray";
					var elementoVisible = document.getElementById("estabVisible2");
					var resultNode1 = document.createTextNode("--");
				    var oldChild1 = elementoVisible.childNodes[0];
					elementoVisible.replaceChild(resultNode1, oldChild1);
					document.forms[0].elements["tamboId"].value = "";
					document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstTodos"].value;
				}
				else{
					document.forms[0].elements["tambos"].value="propios";
					var boton5 = document.getElementById("boton5");
					boton5.disabled="disabled";
					boton5.style.backgroundColor = "gray";
					var boton6 = document.getElementById("boton6");
					if (document.forms[0].elements["unEstPropios"].value == "si"){
						boton6.disabled="";
						boton6.style.backgroundColor = "529b28";
					}
					var elementoVisible = document.getElementById("estabVisible");
					var resultNode1 = document.createTextNode("--");
				    var oldChild1 = elementoVisible.childNodes[0];
					elementoVisible.replaceChild(resultNode1, oldChild1);
					document.forms[0].elements["tamboId"].value = "";
					document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstPropios"].value;
				}
	}
	
	function cambiarFiltro4A(){
					if (document.forms[0].checkFiltro4A[0].checked) {
						var boton = document.getElementById("boton5");
						if (document.forms[0].elements["tambos"].value == "todos"){
							boton.disabled="";
							boton.style.backgroundColor = "529b28";
						}
						document.forms[0].elements["unEstTodos"].value="si";
						document.forms[0].elements["tamboId"].value = "";
						}
					else{
						if (document.forms[0].elements["idEstablecimiento"]!=null)
							document.forms[0].elements["idEstablecimiento"].value=null;
						if (document.forms[0].elements["nombreEstablecimiento"]!=null)
							document.forms[0].elements["nombreEstablecimiento"].value=null;
						var boton = document.getElementById("boton5");
						boton.disabled="disabled";
						boton.style.backgroundColor = "gray";
						var elementoVisible = document.getElementById("estabVisible");
						var resultNode1 = document.createTextNode("--");
					    var oldChild1 = elementoVisible.childNodes[0];
						elementoVisible.replaceChild(resultNode1, oldChild1);
						document.forms[0].elements["unEstTodos"].value="no";
						document.forms[0].elements["tamboId"].value = "";
					}
				if (document.forms[0].checkFiltro4[0].checked){
					document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstTodos"].value;
				}
	}
	
	function prueba(){
		var boton = document.getElementById("boton5");
		boton.disabled="disabled";
		boton.style.backgroundColor = "gray";	
	}
	
	function cambiarFiltro4B1(){
					if (document.forms[0].checkFiltro4B1[0].checked) {
						var boton = document.getElementById("boton6");
						if (document.forms[0].elements["tambos"].value == "propios"){
							boton.disabled="";
							boton.style.backgroundColor = "529b28";
						}
						document.forms[0].elements["unEstPropios"].value="si";
						document.forms[0].elements["tamboId"].value = "";
						}
					else{
						if (document.forms[0].elements["idEstablecimiento"]!=null)
							document.forms[0].elements["idEstablecimiento"].value=null;
						if (document.forms[0].elements["nombreEstablecimiento"]!=null)
							document.forms[0].elements["nombreEstablecimiento"].value=null;
						var boton = document.getElementById("boton6");
						boton.disabled="disabled";
						boton.style.backgroundColor = "gray";
						var elementoVisible2 = document.getElementById("estabVisible2");
					    var resultNode2 = document.createTextNode("--");
					    var oldChild2 = elementoVisible2.childNodes[0];
						elementoVisible2.replaceChild(resultNode2, oldChild2);
						document.forms[0].elements["unEstPropios"].value="no";
						document.forms[0].elements["tamboId"].value = "";
					}
				if (document.forms[0].checkFiltro4[1].checked){
					document.forms[0].elements["unEst"].value=document.forms[0].elements["unEstPropios"].value;
				}
	}
	
	function cambiarFiltro4B2(){
	
				if (document.forms[0].checkFiltro4B2[0].checked) {
					document.forms[0].elements["animales"].value="propios";
					}
				else{
					document.forms[0].elements["animales"].value="todos";
				}
	}
	
	function validarReporte(){
		
	
	}
	
	function cambiarFiltro5(){
				if (document.forms[0].checkFiltro5[0].checked) {
					document.forms[0]["calificados"].value="si";
					
				}
				else if (document.forms[0].checkFiltro5[1].checked) {
						document.forms[0]["calificados"].value="no";
						
					}
				else{
						document.forms[0]["calificados"].value="todos";
						
				}
	}
	
	function cambiarFiltro6(){
				if (document.forms[0].checkFiltro6[0].checked) {
					document.forms[0]["orden"].value="0";
					
				}
				else if (document.forms[0].checkFiltro6[1].checked) {
						document.forms[0]["orden"].value="1";
						
					}
				else{
						document.forms[0]["orden"].value="2";
				}
				
    }
    
    function cargarPagina(){
    
    if (document.forms[0]["tipoLogin"].value=="PROVEEDOR") {
    				document.getElementById("filtro1").style.display="none";
					document.getElementById("filtro3").style.display="none";
	} else if (document.forms[0]["tipoLogin"].value=="PROPIETARIO") {
					document.getElementById("filtro1").style.display="none";
					document.getElementById("filtro2").style.display="none";
	} else {
				document.getElementById("filtro2").style.display="none";
				document.getElementById("filtro3").style.display="none";
				document.getElementById("filtro4").style.display="none";
		}
	if (document.forms[0].elements["tambos"].value == "todos"){
			var boton5 = document.getElementById("boton5");
			if (document.forms[0].elements["unEstTodos"].value == "si"){
				boton5.disabled="";
				boton5.style.backgroundColor = "529b28";
			} else {
					boton5.disabled="disabled";
					boton5.style.backgroundColor = "gray";
			}
			
			var boton6 = document.getElementById("boton6");
			boton6.disabled="disabled";
			boton6.style.backgroundColor = "gray";
			}
	else{
			var boton6 = document.getElementById("boton6");
			if (document.forms[0].elements["unEstPropios"].value == "si"){
				boton6.disabled="";
				boton6.style.backgroundColor = "529b28";
			} else {
					boton6.disabled="disabled";
					boton6.style.backgroundColor = "gray";
			}
			
			var boton5 = document.getElementById("boton5");
			boton5.disabled="disabled";
			boton5.style.backgroundColor = "gray";
	}
	if (document.forms[0].elements["eclosOrPropietarios"].value == ""){
			document.forms[0]["comboEclos"].disabled=true;
			var boton2 = document.getElementById("boton2");
			boton2.disabled="disabled";
			boton2.style.backgroundColor = "gray";
			document.forms[0].checkFiltro1[0].checked=false;
			document.forms[0].checkFiltro1[1].checked=false;
    } else if (document.forms[0].elements["eclosOrPropietarios"].value == "eclos"){
    		var boton = document.getElementById("boton2");
    		boton.disabled="disabled";
			boton.style.backgroundColor = "gray";
			document.forms[0].checkFiltro1[0].checked=true;
	} else {
			document.forms[0]["comboEclos"].disabled=true;
			document.forms[0].checkFiltro1[1].checked=true;
	}
	
	var combo = document.getElementById("comboEclos");
	if (combo.disabled == false){
    	var indice = document.forms[0]["indiceCombo"].value;
		combo.selectedIndex = indice;
    }
    
    var combo2 = document.getElementById("comboEclos2");
	if (combo2.disabled == false){
    	var indice2 = document.forms[0]["indiceCombo"].value;
    	combo2.selectedIndex = indice2;
    }
    
    if (document.forms[0]["tipoLogin"].value=="ADMINISTRADOR" || document.forms[0]["tipoLogin"].value=="GENERAL") {
		cambiarFiltro1();
	}
	initializeMenus();
	
	}
	
	function changeComboEclos1(){
		document.forms[0].elements["ecloId"].value=document.forms[0].elements["ecloIdCombo"].value;
		var combo = document.getElementById("comboEclos");
		document.forms[0]["indiceCombo"].value = combo.selectedIndex;
    }
    
    function changeComboEclos2(){
		document.forms[0].elements["ecloId"].value=document.forms[0].elements["ecloIdCombo2"].value;
		var combo = document.getElementById("comboEclos2");
		document.forms[0]["indiceCombo"].value = combo.selectedIndex;
    }
	</script>
	<script type="text/javascript">  
   		window.onload = cargarPagina;  
 	</script>

</html:form>
