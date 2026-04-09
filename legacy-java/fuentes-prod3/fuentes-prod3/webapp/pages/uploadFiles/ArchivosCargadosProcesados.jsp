<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/downloadFileAction" enctype="multipart/form-data">
	<html:hidden name="uploadFileForm" property="indiceCombo" />
	<input type="hidden" name="method" value="<c:out value="${method}"/>">
	<input type="hidden" name="method" value="<c:out value="${rol}"/>">
				<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	Archivos Procesados y No Descargados
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
   
   <c:if test="${rol != 'PROVEEDOR'}">
	   <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	    	<tr align="center">
	    		<td class="celdaLabelSinAlign" colspan="1" style="vertical-align: middle" align="left">
	    			Filtrar Procesos por Id de Eclo:
	    		</td>
				<td class="celdaLabelSinAlign" colspan="1" align="left">
					<html:select value="ecloId" onchange="cambiarEclo()" property="ecloId" styleId="comboEclos" name="uploadFileForm" styleClass="formfields" style="width:400px">
						<html:option  value="Todas las eclos"  />
						<html:options  collection="eclos" property="id" labelProperty="idNombre" />
					</html:select>
				</td>
				<TD class="celdaLabelSinAlign">
					<input type="button" id="botonFiltrar" value="Filtrar" class="botones"  onClick="filtrarProcesosPorEclo()">
				</TD>
			</tr>
		</table>
		<br>
	</c:if>		
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	<tr>
    <display:table name="procesos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/downloadFileAction.do?method=init">
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
        <display:column align="justify" width="20%" title="Obs. al subir" property="observaciones"/>
        <display:column align="centre" width="7%" title="Entrada"  ><a href="downloadFileAction.do?method=downloadZIP&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
        </display:column>
		<display:column align="center" title="Nombre de archivo" width="10%">
				<c:out value="${item.nombreArchivoEntrada}"/>
		</display:column>	
		<c:if test="${item.fechaSalida != null}">
			<display:column align="centre" width="22%" title="Fecha de Procesamiento">
				<fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaSalida}" />
			</display:column>
			<display:column maxLength="60" align="justify" width="20%" title="Obs. al procesar">
	        	<a href="javascript:MM_openBrWindow('downloadFileAction.do?method=mostrarObservaciones&obs=<c:out value="${item.observacionSalida}"/>','','scrollbars=yes,resizable=yes,width=300,height=300,top=100,left=100')">
	        		<c:out value="${item.observacionSalida}"/>
	        	</a>
        	</display:column>
			<display:column align="center" width="7%" title="Salida"  >
				<a href="downloadFileAction.do?method=downloadZIPOut&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
			</display:column>	
			<c:if test="${requestScope.rol == 'PROVEEDOR'}">				
				<c:if test="${item.estado == 'processed'  || (item.estado == 'downloaded' && item.descargadaEclo == false)}">
					<display:column align="justify" width="10%" title="Descargada" ><img border="0" src="img/nuevo4.gif"  />
					</display:column>					
				</c:if>
			</c:if>
			<c:if test="${requestScope.rol == 'ADMINISTRADOR'}">				
				<c:if test="${item.estado == 'processed' || (item.estado == 'downloaded' && item.descargadaAdmin == false)}">
					<display:column align="justify" width="10%" title="Descargada" ><img border="0" src="img/nuevo4.gif"  />
					</display:column>					
				</c:if>
			</c:if>
		</c:if>		
		<c:if test="${item.fechaSalida == null}">
			<TD colspan="5" align="center">No hay salida aun</TD>
		</c:if>
	   	    
        <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Entradas para ser Procesadas
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>

	
	</tr>			
					
					
</table>
<script language="JavaScript">
	
	function filtrarProcesosPorEclo(){
		document.forms[0].method.value = "init";
		document.forms[0].submit();
	}
	
	function cambiarEclo(){
	    var combo = document.getElementById("comboEclos");
		document.forms[0].indiceCombo.value = combo.selectedIndex;
	}	
	function cargarPagina(){
		var combo = document.getElementById("comboEclos");
		if (combo != null){
			combo.selectedIndex = document.forms[0].indiceCombo.value;
			combo.value = document.forms[0].ecloId.value;
		}
		initializeMenus();
	}
	
	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
	
	window.onload = cargarPagina;
</script>
</html:form>