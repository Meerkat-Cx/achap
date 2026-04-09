<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarEntidadRegionalValidate.do" method="post" onsubmit="return validate();" enctype="multipart/form-data"> 
	<html:hidden name="entidadRegionalForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	
	<html:javascript formName="entidadRegionalForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2>
            		<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Regional
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Regional
						</c:otherwise>
					</c:choose>            	
            		
            	</font>
            </td>
            <td>
            	<div align=right></div>
            </td>
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
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de la Regional
            </td>
        </tr>
        
        <tr>
        	<td colspan="2">
        	<html:messages id="message1" message="true" property="uniqueConstraint"/>          	
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
        
        <tr>
        	<td class="celdaLabelSinAlign" colspan="2" align="right">
        	 <c:choose>	
					<c:when test="${entidadRegionalForm.idFoto == null}">
				        <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
					</c:when>
					<c:otherwise>
					        <a class="orange" align="right">
							<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${entidadRegionalForm.idFoto}"/> '/>
          					</a>   
					</c:otherwise>
				</c:choose>      
        	
        	</td>
        </tr>
        
        <tr>
			<td class="celdaLabel">
				<strong>Nombre: <span class="required">*</span></strong>
			</td>
			<td class="celdaInput">
				<c:choose>
					<c:when test="${action == 'add'}">
						<html:text size="45" maxlength="150" name="entidadRegionalForm" property="nombre" styleClass="Input100porc"/>	            
					</c:when>
					<c:otherwise>
						<c:out value="${entidadRegionalForm.nombre}"/>			
						<html:hidden name="entidadRegionalForm" property="nombre"/>			
					</c:otherwise>
				</c:choose>				
		    </td>
		</tr>   	
		<br/>
		<tr>
			<td class="celdaLabel">
				<strong>Email:</strong>
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="30" maxlength="50" name="entidadRegionalForm" property="email" styleClass="Input100porc"/>
		    </td>
		</tr>
		
	
		<tr>
			<td class="celdaLabel"><strong>Imagen:</strong></td>
			<td class="celdaInput" ><html:file property="fileUpload" styleClass="boton"/></td>
		</tr>	
					
		<tr>
				<td class="celdaLabel"><strong>Comentario:</strong></td>
				<td class="celdaInput">
						<html:textarea property="comentario" name="entidadRegionalForm"  styleClass="textBox" rows="4" cols="50"/>
				</td>
		</tr>			
		
		<tr>
				<td class="celdaLabel" align="center"><strong>Lugares de Contacto:</strong></td>
				<td class="celdaLabel" align="center"></td>
		</tr>		 
		<tr>
				<td colspan="2">		
					<display:table name="ubicaciones" align="center" class="its" id="ubicacion" >				
					        <display:column align="left"  property="nombre" title="Nombre"/>
					        <display:column align="left"  property="provinciaRegion" title=" Provincia Region"/>
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

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:submit styleClass="botones">
					<bean:message key="submit"/>
				</html:submit>
				<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel>
			</td>
		</tr>	
	</table>
</html:form>

<script>
	function validate() {
		if (bCancel) 
			return true; 
		return limitAttach();
	}	
	
	function limitAttach() {
			var extArray = new Array("jpg");
			var file = document.forms[0].fileUpload.value;
			if(file == "") {
//				alert("Debe ingresar un archivo."); no se mete archivo
				return validateEntidadRegionalForm(document.forms[0]);				
			}
			else{	
				allowSubmit = false;
				if (!file) return;
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
					return validateEntidadRegionalForm(document.forms[0]);
				else{
					alert("Solo puede subir archivos del tipo: " 
					+ (extArray.join(" ")) + "\nPor favor, seleccione un nuevo archivo.");
					
					document.forms[0].reset();
					document.forms[0].fileUpload.focus();
					return false;
				}
			}
		}	
</script>


