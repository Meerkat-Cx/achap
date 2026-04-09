<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarEcloValidate.do" method="post" enctype="multipart/form-data" onsubmit="return procesar();">
	<html:javascript formName="ecloForm"/>
			
	<html:hidden name="ecloForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">

			
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
    			<tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="2"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Eclo
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Eclo
						</c:otherwise>
					</c:choose>            	
		            </td>
            </tr>
            
         <tr>
        	<td class="celdaLabelSinAlign" colspan="2" align="right">
        	 <c:choose>	
					<c:when test="${ecloForm.idFoto == null}">
				        <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
					</c:when>
					<c:otherwise>
					        <a class="orange" align="right">
							<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${ecloForm.idFoto}"/> '/>
          					</a>   
					</c:otherwise>
				</c:choose>      
        	
        	</td>
        </tr>
	        
					<TR>
						<TD class="celdaLabel">
							Nombre de Eclo: <span class="required">*</span>
						</TD>
						<TD class="celdaInput">
							<html:text size="45" title="Nombre de la Entidad de Control Lechero" name="ecloForm" property="nombre" styleClass="Input100porc"/>
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Cuit/Cuil: 
						</TD>
						<TD class="celdaInput" colspan="2">
							<html:text size="45" name="ecloForm" property="cuit" styleClass="Input100porc"/>
		            	</TD>		          
					</TR>					
					<tr>
						<td class="celdaLabel">
							Regional: <span class="required">*</span>				
						</td>
						<td class="celdaInput">
							<html:select property="entidadRegionalId" styleClass="Input100porc">
								<html:option value="">Seleccione</html:option>
							    <html:options collection="regionales" property="id" labelProperty="nombreContacto" styleClass="Input100porc"/>
							</html:select>				
						</td>			
					</tr>				
					<TR>	
						<TD class="celdaLabel">
							Activo?: 
						</TD>
					
						<td class="celdaInput" >
										<html:checkbox property="activo" value="true" />
						  				<input type="hidden" name="activo" value="false">
				    	</td>
					
					</TR>
					<tr>
							<td class="celdaLabel">
								Email:
							</td>
							<td class="celdaInput" colspan="3">
								<html:text size="30" maxlength="50" name="ecloForm" property="email" styleClass="Input100porc"/>
						    </td>
					</tr>
					<TR>
						<TD class="celdaLabel">
							Comentarios de Eclo: 
						</TD>
						<TD class="celdaInput">
								<html:textarea cols="44" rows="4" name="ecloForm" property="comentario" styleClass="Input100porc"/>					
						</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Foto de ECLO:
						</TD>				
						<TD class="celdaInput"><html:file size="36" property="foto" styleClass="Input100porc"/></td>	
					</TR>   
					<!-- AQUI SOLO LOS DATOS DE LA ECLO -->
					<tr>
					<td class="celdaLabel" align="center"><strong>Lugares de Contacto:</strong></td>
					<td class="celdaLabel" align="center"></td>
					</tr>		 						
					
					
					<tr>
				<td colspan="2">		
					<display:table name="ubicaciones" align="center" class="its" id="ubicacion" >				
					        <display:column align="left"  property="nombre" title="Nombre"/>
					        <display:column align="left"  property="provinciaRegion" title="Provincia Region"/>
					        <display:column align="left"  property="ciudad" title="Ciudad"/>
   					        <display:column align="left"  property="pais" title="Pais"/>
   					        <display:column align="left"  property="direccion" title="Direccion"/>
   					        <display:column align="left"  property="codigoPostal" title="Codigo Postal"/>
   					        <display:column align="left"  property="mail" title="Mail"/>
   					        <display:column align="left"  property="telefono" title="Telefono"/>   					        
							<display:setProperty name="basic.msg.empty_list">
					            <h1 class="TextoVerde" align="center"><bean:message key="displayTag.basic.msg.empty_list" /></h1>
					        </display:setProperty>
					</display:table>
				</td>
		</tr>							
	</table>						
				<br/>
				
				
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
				<TR> 
                        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="2"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>&nbsp;Datos Generales Del Responsable de ECLO</TD>
                      </TR>
                      <TR>
						<TD class="celdaLabel">
							Apellido: <span class="required">*</span>
						</TD>
							<TD class="celdaInput">
								<html:text size="45" name="ecloForm" property="apellidoResponsable" styleClass="Input100porc" styleId="idApellido"/>
						   	</TD>
						</TR>
						<TR>
							<TD class="celdaLabel">
								Nombres: <span class="required">*</span>
							</TD>
							<TD class="celdaInput">
								<html:text size="45" name="ecloForm" property="nombreResponsable" styleClass="Input100porc" styleId="idNombre"/>					
			            	</TD>
						</TR>			
						<TR>
							<TD class="celdaLabel">
								Tipo de Documento: 
							</TD>
							<TD class="celdaInput">
								<html:select property="tipoDocumentoResponsable" styleClass="Input100porc">
								    <html:option value="LC">LC</html:option>
   								    <html:option value="LE">LE</html:option>
								    <html:option value="DNI">DNI</html:option>
								    <html:option value="CI">CI</html:option>								       								   
								</html:select>	
							</TD>			
									            				
						</TR>
						<TR>
							<TD class="celdaLabel">
								Número de Documento: 
							</TD>
							<TD class="celdaInput">
								<html:text size="22" name="ecloForm" property="numeroDocumentoResponsable" styleClass="Input100porc" styleId="idNumeroDocumento"/>					
            				</TD>
            				
            			</TR>	
						<tr>
							<td class="celdaLabel">
								Comentarios del Responsable:
							</td>
							<td class="celdaInput">
								<html:textarea rows="4" cols="44" name="ecloForm" property="comentarioResponsable" styleClass="Input100porc" styleId="idComentario"/>
							</td>			
						</tr>
						<TR>
							<TD class="celdaLabel">
								Foto del Responsable: 
							</TD class="celdaInput">				
							<TD><html:file size="36" property="fotoResponsable" styleClass="Input100porc" styleId="idFoto"/></td>	
						</TR>					
		
	</table>
	
<br/>

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" bgColor=#F7F9F0>
	<TR> 
		<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="5"><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>&nbsp;Sistemas</TD>
	</TR>
	
	<tr>
						<th class="celdaLabelSinAlign" align="center">Disponibles</th>
						<th colspan="3">&nbsp;</th>
						<th class="celdaLabelSinAlign" align="center">Asignados</th>
					</tr>
					<tr>
						<td align="center">
							<html:select styleId="availables" multiple="true" property="availables" styleClass="formfields" style="width=250px;" size="6">
								<html:optionsCollection property="availables" label="nombre" value="id"/>
							</html:select>
						</td>
						<td>&nbsp;</td>
						<td>
							<table align="center">
								<tr><td align="center"><input class="formfields" type="button" value="&gt;&gt;" onclick="move(getAvailables(), getAdded())"></td></tr>
								<tr><td align="center"><input class="formfields" type="button" value="&lt;&lt;" onclick="move(getAdded(), getAvailables())"></td></tr>
							</table>
						</td>
						<td>&nbsp;</td>
						<td align="center">
							<html:select styleId="added" multiple="true" property="added" styleClass="formfields" style="width=250px;" size="6">
								<html:optionsCollection property="addedSet" label="nombre" value="id"/>
							</html:select>
						</td>
					</tr>
	
	
</table>
		

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
               <tr> 
                    <td align="center">
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
							<html:cancel styleClass="botones" onclick="bCancel=true;" >
								<bean:message key="cancel"/>
							</html:cancel>
					</TD>
				</TR>	
	</table>
		
</html:form>

<script>
	function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	
	function procesar() {		
		if (bCancel == true) return true;		
		var aux = limitAttach();
		return aux;
	}
	
	function validate() {			
		added = getAdded();
		for(i = 0; i < added.length; i++){
			added[i].selected = true;
		}
		var apellidoRes = document.getElementById("idApellido"); // obligatorio
		var nombreRes = document.getElementById("idNombre"); // obligatorio
		
		var numeroDocumentoRes = document.getElementById("idNumeroDocumento");
		var comentarioRes = document.getElementById("idComentario");
		var fotoRes = document.getElementById("idFoto");
		
		if (isEmpty(apellidoRes.value) && isEmpty(nombreRes.value) && isEmpty(numeroDocumentoRes.value) &&
			isEmpty(comentarioRes.value) && isEmpty(fotoRes.value)) { //todos vacios entonces pasa
			return validateEcloForm(document.forms[0]);
		}
		else {  //alguno de los campos viene con datos
			if (!isEmpty(apellidoRes.value) && !isEmpty(nombreRes.value)) { // vienen los datos obligatorios
				return validateEcloForm(document.forms[0]);
			}
			else {
				alert("Para el Alta/modificacion de un Responsable de ECLO, los campos Apellido y Nombres son obligatorios");
				return false;
			}
		}
	}	
	
	// javascript para la edicion de los sistemas
		function getAvailables(){ return document.getElementById('availables') }
		function getAdded(){ return document.getElementById('added') }
					
		function move(fbox, tbox) {
			var arrFbox = new Array();
			var arrTbox = new Array();
			var arrLookup = new Array();
			var arrFboxIndex = new Array();
			var arrTboxIndex = new Array();
			
			var i;
			for (i = 0; i < tbox.options.length; i++) {
				arrLookup[tbox.options[i].value] = tbox.options[i].value;
				arrTbox[i] = tbox.options[i].text;
				arrTboxIndex[i] = tbox.options[i].value;
			}
			var fLength = 0;
			var tLength = arrTbox.length;
			for(i = 0; i < fbox.options.length; i++) {
				arrLookup[fbox.options[i].value] = fbox.options[i].value;
				if (fbox.options[i].selected && fbox.options[i].value != "") {
					arrTbox[tLength] = fbox.options[i].text;
					arrTboxIndex[tLength] = fbox.options[i].value;
					tLength++;
				}
				else {
					arrFbox[fLength] = fbox.options[i].text;
					arrFboxIndex[fLength] = fbox.options[i].value;
					fLength++;
				}
			}
			//arrFbox.sort();
			//arrTbox.sort();
			fbox.length = 0;
			tbox.length = 0;
			var c;
			for(c = 0; c < arrFbox.length; c++) {
				var no = new Option();
				no.value = arrLookup[arrFboxIndex[c]];
				no.text = arrFbox[c];
				fbox[c] = no;
			}
			for(c = 0; c < arrTbox.length; c++) {
				var no = new Option();
				no.value = arrLookup[arrTboxIndex[c]];
				no.text = arrTbox[c];
				tbox[c] = no;
			}
		}	
		
		
		function limitAttach() {			
			var extArray = new Array("jpg");
			var file = document.forms[0].foto.value;			
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
					document.forms[0].foto.focus();
					return false;
				}
			}
		}	
	
</script>




