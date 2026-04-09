<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/downloadFileAction" method="post" enctype="multipart/form-data">
	<script language='javascript' src="calendar/popcalendar.js"></script>
		<input type="hidden" name="method" value="<c:out value="${action}"/>">
		<input type="hidden" name="unEst" value="si"/>
		<TABLE width="100%" cellPadding=1 cellSpacing=2 bgcolor="#eff3e3">
		<TR> 
			  <TD class=Titulo>
					<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>                 
								Búsqueda de lotes procesados						
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
     
	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="5" align="center" >
		<br></br>
		<tr>
			<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<input type="radio" name="checkEs" onclick="cambiar()"  checked >listado </td>
		</tr>
							
		<TR align="center">
				<TD class="celdaLabel" align="center">
					Fecha Inicio:(*)
				</TD>
				<TD class="celdaInput" colspan="4" align="center">
					<input name="fechaInicio" type="text" id="fechaInicio" onclick="popUpCalendar(this, form.fechaInicio, 'dd/mm/yyyy');" size="10">
				</TD>		
		</TR>	
		
		<TR align="center">
				<TD class="celdaLabel" align="center">
					Fecha Fin:(*)
				</TD>
				<TD class="celdaInput" colspan="4" align="center">
					<input name="fechaFin" type="text" id="fechaFin" onclick="popUpCalendar(this, form.fechaFin, 'dd/mm/yyyy');" size="10">
				</TD>		
		</TR>
		<c:if test="${requestScope.rol == 'ADMINISTRADOR' || requestScope.rol == 'GENERAL' || requestScope.rol == 'REGIONAL'}">
		<TR>
			<TD class="celdaLabel">Identificador de Eclo: </TD>
			<TD class="celdaInput">
                <html:text size="6" name="uploadFileForm" property="ecloIdF" styleClass="Input10porc"/>
            </TD>
		</TR>
		</c:if>
		</table>	
		
		<c:if test="${requestScope.rol == 'ADMINISTRADOR' || requestScope.rol == 'GENERAL' || requestScope.rol == 'REGIONAL' || requestScope.rol == 'PROVEEDOR'}">
	   	<table width="100%" class="bordeGris" cellspacing="0" cellpadding="10" align="center" >
		<tr>
							
							<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<input type="radio" name="checkEs" onclick="cambiar()"  >lote</td>								
							
							
						</TR>
		<TR>
			<TD class="celdaLabel">Nro. Lote:(*) </TD>
			<TD class="celdaInput">
                <html:text size="6" name="uploadFileForm" property="loteNum" styleClass="Input10porc"/>
            </TD>
		</TR>
		
		<c:if test="${requestScope.rol == 'ADMINISTRADOR' || requestScope.rol == 'GENERAL' || requestScope.rol == 'REGIONAL'}">
			<TR>
				<TD class="celdaLabel">Identificador de Eclo:(*) </TD>
				<TD class="celdaInput">
	                <html:text size="6" name="uploadFileForm" property="ecloId" styleClass="Input10porc"/>
	            </TD>
			</TR>
		</c:if>
		
		<TR>
			<TD class="celdaLabel">Sistema: </TD>
			<TD class="celdaInput">
               <html:select property="sistema" styleClass="formfields" style="width:120px">
					
					<html:options collection="sistemas" property="id" labelProperty="nombre"/>
				</html:select>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Identificador del Centro de computos: </TD>
			<TD class="celdaInput">
                <html:text size="6" name="uploadFileForm" property="centroId" styleClass="Input10porc"/>
            </TD>
		</TR>
	</table>
	</c:if>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<input type="button" value="Buscar" class="botones" onclick="verificarFechas()">            
			</td>
		</tr>	
	</table>
	
	<html:messages id="message1" message="true" property="errorDate"/>          	
	<c:if test="${message1!='' and message1!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message1}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	
	<html:messages id="message2" message="true" property="errorEclo"/>          	
	<c:if test="${message2!='' and message2!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message2}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	
	<html:messages id="message3" message="true" property="errorEcloNoExiste"/>          	
	<c:if test="${message3!='' and message3!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message3}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	
	
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	<tr>
	<div class="justify">
	
	<display:table name="procesos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/downloadFileAction.do?method=buscarLotes">
	<input type="hidden"  name="method" value="<c:out value="${requestScope.method}"/>">
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Procesos</font>
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
		</display:caption>
		<display:column align="centre" title="Usuario" width="10%" property="usuario.username"/>
		<display:column align="centre" width="22%" title="Fecha de Subida"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaEntrada}" />
	    </display:column>        
        <display:column align="centre" width="20%" title="Obs. al subir" property="observaciones"/>
        <display:column align="centre" width="7%" title="Entrada"  ><a href="downloadFileAction.do?method=downloadZIP&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
        </display:column>
		<display:column align="center" title="Nombre de archivo" width="10%">
				<c:out value="${item.nombreArchivoEntrada}"/>
		</display:column>	
	    
		<c:if test="${item.fechaSalida != null}"> <!--o fue procesado o descargado-->
			<display:column align="centre" width="22%" title="Fecha de Procesamiento">
				<fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaSalida}" />
			</display:column>   
			<display:column align="centre" width="20%"  title="Obs. al procesar" property="observacionSalida"/>
			<display:column align="center" width="7%" title="Salida"  ><a href="downloadFileAction.do?method=downloadZIPOut&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
			</display:column>	
			<display:column align="centre" width="10%" title="Descargada" ><img border="0" src="img/ok.gif" />
			</display:column>	
		</c:if>		
		<c:if test="${item.fechaSalida == null}">
			<TD colspan="5" align="center">No hay salida aun</TD>
		</c:if>
    </display:table>
    </div>
    </tr>
<script language="JavaScript">

	function verificarFechas(){
   		if ((document.forms[0].unEst.value == 'si')) {
   		
	   		if (isEmpty(document.forms[0].elements["fechaInicio"].value)) {
				alert("La fecha de inicio es un campo obligatorio");
				return;
			}			
			if (isEmpty(document.forms[0].elements["fechaFin"].value)) {
				alert("La fecha de fin es un campo obligatorio");
				return;
			}
			if(document.forms[0].elements["ecloIdF"]!=null){
			if (!isEmpty(document.forms[0].elements["ecloIdF"].value)) {
				if (!isPosInteger(document.forms[0].elements["ecloIdF"].value)) {
					alert("Identificador de la eclo debe ser un numero");
					return;
			}
			}	
			}
		}	
		else{
			
			if ((isEmpty(document.forms[0].elements["loteNum"].value))) {
						alert("Numero de Lote es obligatorio");
						return;
					}
			
			if ((!isPosInteger(document.forms[0].elements["loteNum"].value))) {
				alert("Numero de Lote debe ser un número");
				return;
			}
			if(document.forms[0].elements["ecloId"] != null){
				if (isEmpty(document.forms[0].elements["ecloId"].value)) {
					alert("Identificador Eclo es obligatorio");
					return;
				}
				if (!isPosInteger(document.forms[0].elements["ecloId"].value)) {
					alert("Identificador Eclo debe ser un número");
					return;
				}
			}
			if (!isEmpty(document.forms[0].elements["centroId"].value)) {
				if (!isPosInteger(document.forms[0].elements["centroId"].value)) {
					alert("Identificador del centro de computos debe ser un numero");
					return;
			}
			}
		}
		
   		document.forms[0].method.value='buscarLotes';
		document.forms[0].submit();
   	}
	
	function isEmpty(inputStr) {
		
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	function limpiarLote() {
		
			var nomElem = document.forms[0].elements["fechaInicio"];
			var idElem = document.forms[0].elements["fechaFin"];
			var idElemF = document.forms[0].elements["ecloIdF"];
			
			idElem.value = "";
			idElemF.value = "";
			nomElem.value = "";
			
			}
	function limpiarlistado() {
		
			var nomElem = document.forms[0].elements["loteNum"];
			var idElem = document.forms[0].elements["ecloId"];
			var idEle = document.forms[0].elements["centroId"];
			
			idElem.value = "";
			nomElem.value = "";
			idEle.value = "";
			}
	function cambiar() {
				
				if (document.forms[0].checkEs[0].checked) {
					document.forms[0].unEst.value = 'si';
					limpiarlistado();
					
				}
				else {	
				document.forms[0].unEst.value = 'no';	
					limpiarLote();
									
				}
			
		}
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
	
	initializeMenus();
</script>
</html:form>
	
	
	
	
	
	