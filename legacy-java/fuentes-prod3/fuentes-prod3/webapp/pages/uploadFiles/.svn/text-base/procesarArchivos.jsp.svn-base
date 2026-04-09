<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/processFileAction"  enctype="multipart/form-data" onsubmit="return validate();">
	<input type="hidden" name="method" value="process">
	<input type="hidden" name="indice" value="<c:out value="${indice}"/>" />
	<input type="hidden" name="ecloId2" value="<c:out value="${ecloId2}"/>" />
	<html:hidden name="uploadFileForm" property="indiceCombo" />
	<html:hidden name="uploadFileForm" property="rolUser" />
			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
			    <TR> 
					<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						Procesamiento de Archivos</FONT>
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
    <br>
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
				<input type="button" id="botonFiltrar" value="Filtrar" class="botones" onClick="filtrarProcesosPorEclo()">
			</TD>
		</tr>
	</table>
	<br>
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	<tr>
	<div class="justify">
    <display:table name="procesos" align="center"  class="its" sort="list" id="item" pagesize="10" requestURI="/processFileAction.do?method=init" >
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
        
        
	    <display:column align="center" title="usuario" property="usuario.username"  />
		<display:column align="center" title="Nombre de archivo" width="20%">
				<c:out value="${item.nombreArchivoEntrada}"/>
		</display:column>	
		 <display:column align="center" title="Entrada"  ><a href="downloadFileAction.do?method=downloadZIP&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
        </display:column>
       
	    <display:column align="center"  title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaEntrada}" />
	    </display:column>
	    <display:column align="center"  title="Obs. al subir" property="observaciones"/>
	    <c:if test="${uploadFileForm.rolUser == 'ADMINISTRADOR'}">
		    <display:column align="center" title="Procesar" ><a href="javascript:subir('<c:out value="${item.id}"/>')"><img border="0" src="img/procesar.jpg"  title="Procesar"/></a>
	        </display:column>
		    <display:column align="center" title="Eliminar" ><a href="javascript:eliminar('<c:out value="${item.id}"/>')"><img border="0" src="img/papelera.jpg"  title="Eliminar"/></a>
	        </display:column>
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
</div>
	
	</tr>			
					
					
</table>
<script language="JavaScript">
	function subir(valor){
		var mUrl = "processFileAction.do?method=pop&idP="+valor+"&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;	
    }
	function avanzar(valor){    	
    	var mUrl='<html:rewrite page="/pages/uploadFiles/popupObs.jsp"/>'+'?id='+valor ;
    	window.location.href = mUrl;
    }
	function MM_openBrWindow(valor) {
		var theURL = '<html:rewrite page="/pages/uploadFiles/popupObs.jsp"/>';
		var features = 'scrollbars=yes,resizable=yes,width=500,height=200,top=100,left=100';
		window.open(theURL+'?id='+valor,'',features);
	}
	function eliminar(valor){
	if(confirm('¿Confirma la eliminacion del lote?')){ 
		var mUrl = "processFileAction.do?method=eliminar&idP="+valor+"&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;
		}	
	}
	function filtrarProcesosPorEclo(){
		var mUrl = "processFileAction.do?method=init&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
		window.location.href = mUrl;
	}
	function cambiarEclo(){
	    var combo = document.getElementById("comboEclos");
		document.forms[0].elements["indice"].value = combo.selectedIndex;
		document.forms[0].elements["ecloId2"].value = combo.value;
	}	
	function cargarPagina(){
		var combo = document.getElementById("comboEclos");
		if (document.forms[0].elements["indice"].value != "")
			combo.selectedIndex = document.forms[0].elements["indice"].value;
		else{
			combo.selectedIndex = document.forms[0].indiceCombo.value;
			document.forms[0].elements["indice"].value = document.forms[0].indiceCombo.value;
		}
		document.forms[0].elements["ecloId2"].value = combo.value;
		initializeMenus();
	}
	
	window.onload = cargarPagina;  
</script>

</html:form>


	