<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>


<html:form action="/cargaMasivaAction.do" method="post" enctype="multipart/form-data" onsubmit="return procesar();">

	<input type="hidden" name="method" value="cargar">
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr>
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle src="pages/assets/images/flecha_titulos3a.gif" >
            	Filtro para Carga Masiva
            </td>
            <td><div align=right></div></td>
        </tr>
        <tr>
        	<td class=texto4 colSpan=2>
            	<div align=right>
                	<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    	<tr>
                        	<td width="30%" bgColor=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            <td width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></td>
                        </tr>
                    </table>
               </div>
            </td>
        </tr>
	</table>
	<br/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		
		<tr>
			<td class="celdaLabelSinAlign" align="left">
				<strong>Accion</strong>
			</td>
			<td class="celdaInput">
				<html:select property="tipoActualizacion"  onchange="javascript:cambioTipo2();">
					<html:option value="altas">Altas</html:option>
					<html:option value="modificaciones">Modificaciones</html:option>
				</html:select>

		    </td>
		    
		    
		    
		    
		    
			<td class="celdaLabelSinAlign" align="left">
			<div id="modificacionVisible"  align="center">
				
					<table width="40%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<tr>
					   <td class="celdaLabelSinAlign" align="center">
							<strong>Modificaci&oacute;n&nbsp;de:</strong>
						</td>
						</tr>
						<tr>
						<td class="celdaInput" >
											<html:checkbox property="cambioRaza" value="true" />
							  				<input type="hidden" name="cambioRaza" value="false">
					    	</td>
						<td class="celdaLabelSinAlign" align="left">
							<strong>Raza anterior</strong>
						</td>
						<td class="celdaInput">
							<html:select property="razaAnterior">
								<html:options collection="razas" property="id" labelProperty="nombre"/>
							</html:select>
						</td>
					</tr>
			
					<tr>
							<td class="celdaInput" >
											<html:checkbox property="cambioSexo" value="true" />
							  				<input type="hidden" name="cambioSexo" value="false">
					    	</td>
							<td class="celdaLabelSinAlign" align="left">
							<strong>Sexo</strong>
							</td>
					</tr>
				  </table>
				 
			  </div>
			</td>

		</tr>
		<tr>
			<td class="celdaLabelSinAlign" align="left">
				<strong>Raza</strong>
			</td>
			<td class="celdaInput">
				<html:select property="raza">
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
		    </td>
		    <td class="celdaLabelSinAlign" align="left">
		    </td>
		</tr>
		<tr>
			<td class="celdaLabelSinAlign" align="left">
				<strong>Sexo</strong>
			</td>
			<td class="celdaInput">
				<html:select property="sexo">
					<html:option value="macho">Macho</html:option>
					<html:option value="hembra">Hembra</html:option>
				</html:select>
		    </td>
		    <td class="celdaLabelSinAlign" align="left">
		    </td>
		</tr>

		<tr>
			<td class="celdaLabelSinAlign" align="left">
				<strong>Origen Animal</strong>
			</td>
			<td class="celdaInput">
				<html:select property="origen">
					<html:option value="Nacional">Nacional</html:option>
					<html:option value="Internacional">Internacional</html:option>
				</html:select>
		    </td>
		    <td class="celdaLabelSinAlign" align="left">
		    </td>
		</tr>

		<TR>
			<TD class="celdaLabelSinAlign" align="left">
				<strong>Archivo carga Masiva:</strong>
			</TD>
			<TD class="celdaInput">
				<html:file size="36" property="archivoExcel" styleId="archivoExcel"/>
			</td>
			<td class="celdaLabelSinAlign" align="left">
		    </td>
		</TR>
		</td>
		
		
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
               <tr>
                    <td style=Titulo align="center">
					<font color=#993333>
					El resultado de la carga tambien sera enviado por mail
					</font>
					</TD>
				</TR>
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
               <tr>
                    <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
					</TD>
				</TR>
	</table>
	<c:if test="${!empty requestScope.errores}">
				<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
			    	<tr>
			        	<td class=Titulo>
			            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle src="pages/assets/images/flecha_titulos3a.gif" >
			            	Errores Encontrados en la Carga Masiva
			            </td>
			            <td><div align=right></div></td>
			        </tr>
			        <tr>
			        	<td class=texto4 colSpan=2>
			            	<div align=right>
			                	<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
			                    	<tr>
			                        	<td width="30%" bgColor=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
			                            <td width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></td>
			                        </tr>
			                    </table>
			               </div>
			            </td>
			        </tr>
				</table>
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<tr>
						<td class="celdaLabelSinWidth" width="25%"><strong>Fila</strong>
						</td>
						<td class="celdaLabelSinWidth" width="75%"><strong>Mensaje de Error</strong>
						</td>
					</tr>
					<c:forEach items="${requestScope.errores}" var="currentError">
					<tr>
						<td class="celdaLabelSinWidth">
							<c:out value="${currentError.fila}"/>
						</td>
						<td class="celdaLabelSinWidth">
							<c:out value="${currentError.mensajeError}"/>
						</td>
					</tr>
					</c:forEach>
				</table>
		</c:if>
		<br>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr>
				<td class="celdaLabelSinWidth" width="25%"><strong>Resultado</strong>
				</td>
				<td class="celdaLabelSinWidth" width="75%"><c:out value="${requestScope.resultado}"/>
				</td>
			</tr>
		</table>
		<br></br>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
        <tr>
        	<td colspan="4">
        	<html:messages id="message1" message="true" property="errorEmail"/>          	
			<c:if test="${message1!='' and message1!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
				<tr >				
					<td class="TextoError" colspan="3">
						<c:out value="${message1}"/>
					</td>
				</tr>
	         	</table>
	          	<br>
			</c:if>
        	</td>
        </tr>
</table>
</html:form>
<script>
	function aparecer(id) {
		var d = document.getElementById(id);
		d.style.display = "block";
		d.style.visibility = "visible";
	}
	function ocultar(id) {
		var d = document.getElementById(id);
		d.style.display = "none";
		d.style.visibility = "hidden";
	}
	window.onload = function () {
		//Al cargar la página se oculta el div de consulta
		ocultar("modificacionVisible");
		initializeMenus();
	}
	
	
	
	function cambioTipo2(){
	        if(document.forms[0].tipoActualizacion.value == 'altas'){
	         ocultar("modificacionVisible");
	        }
	        else
	        	aparecer("modificacionVisible");
	}
     
	function limitAttach() {
			var extArray = new Array("xls");
			var file = document.forms[0].archivoExcel.value;

			if(file == "") {
				return validate();
			}
			else{
				allowSubmit = false;
				if (!file) return validate();
				while (file.indexOf("\\") != -1)
					file = file.slice(file.indexOf("\\") + 1);
				ext=file;
				while (ext.indexOf(".") != -1)
					ext = ext.slice(ext.indexOf(".") + 1);
				ext = ext.toLowerCase();
				for (var i = 0; i < extArray.length; i++) {
					if (extArray[i] == ext) { allowSubmit = true; break; }
				}
				if (allowSubmit)
					return validate();
				else{
					alert("Solo puede subir archivos del tipo: "
					+ (extArray.join(" ")) + "\nPor favor, seleccione un nuevo archivo.");

					document.forms[0].reset();
					document.forms[0].archivoExcel.focus();
					return false;
				}
			}
		}
		function validate() {
			var fotoRes = document.getElementById("archivoExcel");
			if (fotoRes.value == null || fotoRes.value == "") {
				alert("Se debe seleccionar un archivo para la carga masiva");
				return false;
			}
			return true;
		}
	function procesar() {
		aux = limitAttach();
		return aux;
	}
</script>
