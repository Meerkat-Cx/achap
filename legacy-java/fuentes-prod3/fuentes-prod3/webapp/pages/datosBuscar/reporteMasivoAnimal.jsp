<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:form action="/visualizarReportesAchaAction.do" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="mostrar">
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>B&uacute;squeda de Reporte por Animal</FONT></TD>
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
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel"><strong>Tipo de registro:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="tipoReg" styleClass="formfields" style="width:160px">
							<html:option value="RC"  >RC</html:option>
							<html:option value="HBA"  >HBA</html:option>
							
						</html:select>
					</td>
		</tr> 
		<TR>
			<TD class="celdaLabel"><strong>Numero de registro:</strong> </TD>
			<TD class="celdaInput">
                <html:text size="45" name="animalForm" title="numero de registro"  property="numReg" styleClass="Input10porc"/>
            </TD>
		</TR>
		<tr>
			<td class="celdaLabel"><strong>Raza del Animal:&nbsp;</strong></td>
			<td class="celdaInput">
			<html:select property="raza" styleClass="formfields" style="width:160px">
					
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>					
		</tr>
		<tr>
			<td class="celdaLabel"><strong>Sexo:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="sexo" styleClass="formfields" style="width:160px">
							<html:option value="H"  >Hembra</html:option>
							<html:option value="M"  >Macho</html:option>
							
						</html:select>
					</td>
					
		</tr> 		
	</table>
	<html:messages id="message1" message="true" property="error"/>          	
		<c:if test="${message1!='' and message1!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message1}"/>
				</td>
			</tr>
	    </table>
		</c:if>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
            <tr> 
					<td align="center"><input type="button" value="Agregar" class="botones" onclick="setMethod('mostrar')"></td>					
			</TR>	
		</table>
	<div class="justify">
	    <display:table name="listaAnimal" align="center" pagesize="10"  class="its" requestURI="/visualizarReportesAchaAction.do?method=mostrar" >
	        <display:caption>
	            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
	            	<tr> 
	                	<td class=Titulo>
	                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
					            width=11 align=absMiddle>&nbsp;Animales cargados</font>
						</td>
						<td><div align=right></div></td>
					</tr>
					<tr> 
						<td class=texto4 colSpan=2> <div align=right> 
							<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
					            cellPadding=0 width="100%" border=0>
								<tr> 
									<td width="30%" bgColor=#529b28><img height=2 
					                  src="pages/assets/images/pixel.gif" width=2></td>
									<td width="70%"><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
	                            </tr>
							</table>
	                        </div></td>
					</tr>
				</table>
	        </display:caption>
			<display:column property="regOrigen" title="Tipo y numero de registro" align="center"/>
			<display:column property="raza.nombre" title="Raza" align="center"/>				
	    </display:table>
		<br></br>		
		<html:hidden name="animalForm" property="tipoReg"/>
				<c:if  test="${animalForm.existeHBA==true}">	
					<table width="90%" border="0" align="center" class="texto_error">
						<td class="TextoError">[Los animales con registro HBA no seran generados en los reportes de fichas]</td>				
					</table>	
					<br></br>
				</c:if>	
		<table width="95%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >	
	            <TR>
				<TD class="celdaLabel">[descargar Fichas] </TD>			
				<TD class="TextoNegro" colspan="4"><a href="visualizarReportesAchaAction.do?method=downloadFichaMasivas&id=<c:out value="${requestScope.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>
				</TR>	
				<TR>
						<TD class="celdaLabel">Destinatario</TD>
						<TD class="celdaInput" colspan="4" align="rigth">
						<html:text size="40" name="animalForm" title="destinatario"  property="destinatario" /></TD>
				</TR>	
				<TR>
					<TD class="celdaLabel">Firmante</TD>
					<TD class="celdaInput" colspan="4" align="rigth">
						<html:text size="40" name="animalForm" title="firma"  property="firmante" /></TD>
				</TR>
				<TR>
					<TD class="celdaLabel">[descargar Certificado Exportacion] </TD>			
					<TD  class="TextoNegro" colspan="4"><a href="javascript:exportacion(<c:out value="${requestScope.id}"/>)"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>
				</TR>	
		</table>	
		
	</div>
<script language="JavaScript">
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	function exportacion(valor){
		if (isEmpty(document.forms[0].elements["destinatario"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el destinatario");
						return;
					}
		if (isEmpty(document.forms[0].elements["firmante"].value)) {
						alert("Para poder descargar el certificado de exportacion es obligatorio informar el firmante");
						return;
					}	
		var mUrl = "visualizarReportesAchaAction.do?method=descargarExportacionMasiva&id="+valor+"&destinatario="+document.forms[0].elements["destinatario"].value+"&firmante="+document.forms[0].elements["firmante"].value;
			window.location.href = mUrl;	
		}
		
		function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
</script>    
</html:form>