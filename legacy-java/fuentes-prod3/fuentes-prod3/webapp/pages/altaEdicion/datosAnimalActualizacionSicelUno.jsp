<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:form action="/actualizacionSicelUno.do" method="post" >
			<input type="hidden" name="method" value="${method}" >
			<html:hidden name="actualizacionSicelUnoForm" property="nroRegistro"/>
			<html:hidden name="actualizacionSicelUnoForm" property="raza"/>
			<html:hidden name="actualizacionSicelUnoForm" property="sexo"/>
			<html:hidden name="actualizacionSicelUnoForm" property="rp"/>
			<html:hidden name="actualizacionSicelUnoForm" property="nombre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="regOri"/>
			<html:hidden name="actualizacionSicelUnoForm" property="categoriaAnimal"/>
			<html:hidden name="actualizacionSicelUnoForm" property="rpMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="regOriMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="tipoRegOriMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="rpPadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="regOriPadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="tipoRegOriPadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="razaMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="razaPadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="activoRazaMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="mostrarCombo"/>
			<html:hidden name="actualizacionSicelUnoForm" property="fechaNacMadreActual"/>
			<html:hidden name="actualizacionSicelUnoForm" property="fechaNacPadreActual"/>
			<html:hidden name="actualizacionSicelUnoForm" property="borrarMadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="borrarPadre"/>
			<html:hidden name="actualizacionSicelUnoForm" property="usarRazaMadre"/>
			<!-- Titulo de la pagina -->
			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
		    	<TR> 
		        	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
		            	src="pages/assets/images/flecha_titulos3a.gif" 
		            	width=11 align=absMiddle>Datos del animal</FONT>
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
			<!-- Fin Titulo de la pagina -->
			<table class="bordegris" width="100%"  >	
				<TR>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						RP: 
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<c:out value=" ${actualizacionSicelUnoForm.rp}"></c:out> 
					</TD>
				</TR>
				<TR>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						Nombre: 
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<c:out value=" ${actualizacionSicelUnoForm.nombre}"></c:out> 
					</TD>
				</TR>
				<TR>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						Sexo: 
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<c:if test="${actualizacionSicelUnoForm.sexo == 'M'}">
							Macho
						</c:if>
						<c:if test="${actualizacionSicelUnoForm.sexo == 'H'}">
							Hembra
						</c:if>
					</TD>
				</TR>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						Raza del Animal: 
					</td>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<c:if test="${actualizacionSicelUnoForm.mostrarCombo != 'si'}">
							<c:out value=" ${actualizacionSicelUnoForm.raza}"></c:out>
						</c:if>
						<c:if test="${actualizacionSicelUnoForm.mostrarCombo == 'si'}">
							<html:select property="razaActual">
								<html:options style="celdaLabelSinAlignSoloColoresYFuentes" collection="razas" property="id" labelProperty="nombre"/>
							</html:select>
						</c:if>
					</td>
				</tr>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						Categoría: 
					</td>
					<td class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<input id="idCategoria" name="actualizacionSicelUnoForm" title="Categoría del Animal" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="10">
						Madre Actual:
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Rp: 
					</TD>
					<TD  id="labelRpMadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="8">
						<c:out value="${actualizacionSicelUnoForm.rpMadreActual}"></c:out>
					</td>
				</tr>
				<tr>
					<TD  class=celdaLabelSinAlignSoloColoresYFuentes width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Regori:
					</TD>
					<TD  id="regMadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.tipoRegOriMadreActual} ${actualizacionSicelUnoForm.regOriMadreActual}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Fecha de nacimiento:
					</TD>
					<TD  id="fechaNacMadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.fechaNacMadreActual}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="10">
						Padre Actual:
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Rp:
					</TD>
					<TD  id="labelRpPadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="8">
						<c:out value="${actualizacionSicelUnoForm.rpPadreActual}"></c:out>
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Regori:
					</TD>
					<TD  id="regPadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.tipoRegOriPadreActual} ${actualizacionSicelUnoForm.regOriPadreActual}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Fecha de nacimiento:
					</TD>
					<TD  id="fechaNacPadreActual" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.fechaNacPadreActual}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
			</table>
			<br>
			<table class="bordegris" width="100%"  >
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1" style="text-decoration: underline">
						Aclaración sobre la actualización de la raza del animal:
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
							La raza del animal puede ser actualizada de varias formas:<br>
								- <strong>Usar raza de la madre:</strong> para usar la raza de la madre, hay un check al final de la pantalla. Para que esté check este activo, el animal debe tener madre.
								  Si este check está activo, y se selecciona, la raza del animal será igual a la raza declarada de la madre. Si el check no se selecciona, o no esta activo, 
								  la raza resultante será la obtenida en el calculo de la raza.<br>
								- <strong>Elegir una raza:</strong> en caso de que el animal no tenga madre, aparecera un combo con todas las razas para poder asignarle una al animal. Inicialmente, este combo
								  estará cargado con la raza original del animal.<br>
								- <strong>Usar la raza calculada:</strong> en el resto de los casos, se usará la raza resultante del calculo entre los padres.  
					</TD>
				</tr>
			</TABLE>
			<br>
			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
		    	<TR> 
		        	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
		            	src="pages/assets/images/flecha_titulos3a.gif" 
		            	width=11 align=absMiddle>Cambio de padres</FONT>
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
			<table class="bordegris" width="100%"  >
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="10">
						Cambio de Madre:
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Rp: 
					</TD>
					<TD  id="labelRpMadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.rpMadre}"></c:out>
					</td>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						<input type="button" align="left" value="Cambiar Madre" class="botones" name="buscar"  onClick="cambiar('cambiarMadre')" >            
					</TD>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						<input id="checkEliminarMadre" type="checkbox" align="left" value="Eliminar Madre" class="botones" name="buscar">Eliminar Madre            
					</TD>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="5">
						<input type="button" align="left" value="Limpiar Madre" class="botones" name="buscar"  onClick="limpiarMadre()" >            
					</TD>
				</tr>
				<tr>
					<TD  class=celdaLabelSinAlignSoloColoresYFuentes width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Regori:
					</TD>
					<TD  id="regMadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.tipoRegOriMadre} ${actualizacionSicelUnoForm.regOriMadre}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Fecha de nacimiento:
					</TD>
					<TD  id="fechaNacMadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.fechaNacMadre}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="10">
						Cambio de Padre:
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Rp:
					</TD>
					<TD  id="labelRpPadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.rpPadre}"></c:out>
					</td>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						<input type="button" align="left" value="Cambiar Padre" class="botones" name="buscar"  onClick="cambiar('cambiarPadre')" >            
					</TD>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="1">
						<input id="checkEliminarPadre" type="checkbox" align="left" value="Eliminar Padre" name="buscar">Eliminar Padre            
					</TD>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="5">
						<input type="button" align="left" value="Limpiar Padre" class="botones" name="buscar"  onClick="limpiarPadre()" >            
					</TD>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Regori:
					</TD>
					<TD  id="regPadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.tipoRegOriPadre} ${actualizacionSicelUnoForm.regOriPadre}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1"></TD>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" width="10%" align="left" colspan="1">
						Fecha de nacimiento:
					</TD>
					<TD  id="fechaNacPadre" class="celdaLabelSinAlignSoloColoresYFuentes" width="20%" align="left"  colspan="1">
						<c:out value="${actualizacionSicelUnoForm.fechaNacPadre}"></c:out>
					</td>
					<TD  class="celdaLabelSinAlignSoloColoresYFuentes" align="left"  colspan="7">
					</td>
				</tr>
				<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="10">
						<c:if test="${actualizacionSicelUnoForm.activoRazaMadre == 'si' and actualizacionSicelUnoForm.usarRazaMadre == 'si'}">
							<input id="checkRaza" type="checkbox" align="left" value="Usar raza de la madre" class="botones" name="buscar" checked>Usar raza madre
						</c:if>
						<c:if test="${actualizacionSicelUnoForm.activoRazaMadre == 'si' and actualizacionSicelUnoForm.usarRazaMadre != 'si'}">
							<input id="checkRaza" type="checkbox" align="left" value="Usar raza de la madre" class="botones" name="buscar">Usar raza madre
						</c:if>
						<c:if test="${actualizacionSicelUnoForm.activoRazaMadre != 'si'}">
							<input id="checkRaza" type="checkbox" align="left" disabled="disabled" value="Usar raza de la madre" class="botones" name="buscar">Usar raza madre
						</c:if>
					</td>
				</TR>
			</table>
			<br>
			<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
				<tr> 
		    		<td align="center"><input type="button" value="Actualizar" class="botones" onclick="cambiar('actualizarDatos')"></td>
				</TR>	
			</table>
			<script>
				function cambiar(value){
					var check = document.getElementById("checkRaza");
					if (check.checked)
						document.forms[0].usarRazaMadre.value = "si";
					else
						document.forms[0].usarRazaMadre.value = "no";
					var checkPadre = document.getElementById("checkEliminarPadre");
					if (checkPadre.checked)
						eliminarPadre();
					else
						document.forms[0].borrarPadre.value = "no";
					var checkMadre = document.getElementById("checkEliminarMadre");
					if (checkMadre.checked)
						eliminarMadre();
					else
						document.forms[0].borrarMadre.value = "no";
					var cat = document.getElementById("idCategoria").value;
					document.forms[0].categoriaAnimal.value = cat;
					document.forms[0].method.value = value;
					document.forms[0].submit();
				}
				function limpiarMadre(){
					document.forms[0].rpMadre.value = "";
					var elementoVisible = document.getElementById("labelRpMadre");					
					var resultNode = document.createTextNode("");
					var oldChild = elementoVisible.childNodes[0];
					elementoVisible.replaceChild(resultNode, oldChild);
					document.forms[0].regOriMadre.value = "";
					var elementoVisible2 = document.getElementById("regMadre");					
					var resultNode2 = document.createTextNode("");
					var oldChild2 = elementoVisible2.childNodes[0];
					elementoVisible2.replaceChild(resultNode2, oldChild2);
					document.forms[0].tipoRegOriMadre.value = "";
					document.forms[0].razaMadre.value = "";
					document.forms[0].fechaNacMadreActual.value = "";
					var elementoVisible3 = document.getElementById("fechaNacMadre");					
					var resultNode3 = document.createTextNode("");
					var oldChild3 = elementoVisible3.childNodes[0];
					elementoVisible3.replaceChild(resultNode3, oldChild3);
				}
				function limpiarPadre(){
					document.forms[0].rpPadre.value = "";
					var elementoVisible = document.getElementById("labelRpPadre");					
					var resultNode = document.createTextNode("");
					var oldChild = elementoVisible.childNodes[0];
					elementoVisible.replaceChild(resultNode, oldChild);
					document.forms[0].regOriPadre.value = "";
					var elementoVisible2 = document.getElementById("regPadre");					
					var resultNode2 = document.createTextNode("");
					var oldChild2 = elementoVisible2.childNodes[0];
					elementoVisible2.replaceChild(resultNode2, oldChild2);
					document.forms[0].tipoRegOriPadre.value = "";
					document.forms[0].razaPadre.value = "";
					document.forms[0].fechaNacPadreActual.value = "";
					var elementoVisible3 = document.getElementById("fechaNacPadre");					
					var resultNode3 = document.createTextNode("");
					var oldChild3 = elementoVisible3.childNodes[0];
					elementoVisible3.replaceChild(resultNode3, oldChild3);
				}
				function cargarPagina(){
					var cat = document.getElementById("idCategoria");
					document.getElementById("idCategoria").value = document.forms[0].categoriaAnimal.value; 
					initializeMenus();					
				}
				function eliminarPadre(){
						limpiarPadre();
						document.forms[0].borrarPadre.value = "si";
				}
				function eliminarMadre(){
						limpiarMadre();
						document.forms[0].borrarMadre.value = "si";
				}
				window.onload = cargarPagina;
			</script>
</html:form>