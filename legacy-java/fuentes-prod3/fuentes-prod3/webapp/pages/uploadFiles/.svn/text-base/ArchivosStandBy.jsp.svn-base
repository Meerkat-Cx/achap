<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/processFileAction" enctype="multipart/form-data">


			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	Archivos con errores
							
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
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	<tr>
	<div class="justify">
    <display:table name="procesos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/downloadFileAction.do">
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
        <display:column align="justify" width="20%" title="Obs. al subir" property="observaciones"/>
        <display:column align="centre" width="7%" title="Entrada"  ><a href="downloadFileAction.do?method=downloadZIP&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
        </display:column>
		<display:column align="center" title="Nombre de archivo" width="10%">
				<c:out value="${item.nombreArchivoEntrada}"/>
		</display:column>	
	    <display:column align="centre" width="22%" title="Fecha de Procesamiento">
				<fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaSalida}" />
			</display:column>        
			<display:column align="justify" width="20%"  title="Obs. al procesar" property="observacionSalida"/>
			<display:column align="center" width="7%" title="Salida"  >
				<a href="downloadFileAction.do?method=downloadZIPOut&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
			</display:column>	
			<display:column align="center" title="procesar" width="10%" ><a href="javascript:setMethod('<c:out value="${item.id}"/>')"><img border="0" src="img/procesar.jpg"  title="Procesar"/></a>
        </display:column>
	    <display:column align="center" title="eliminar" width="10%" ><a href="javascript:eliminar('<c:out value="${item.id}"/>')"><img border="0" src="img/papelera.jpg"  title="Eliminar"/></a>
        </display:column>
         <display:column align="center" title="" width="10%" >
        <a align="right" href="javascript:MM_openBrWindow('uploadFileAction.do?method=initCambiarLote&loteId=<c:out value="${item.id}"/>','','scrollbars=yes,resizable=yes,width=700,height=250,top=100,left=100')">reemplazar</a>
         
        
        </display:column>
		

	   	    
        <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay lotes con errores
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>
	
	</tr>			
					
					
</table>
<script language="JavaScript">
function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
			}
	function setMethod(valor){
			var mUrl = "processFileAction.do?method=process&idP="+valor;
		window.location.href = mUrl;	
    		
    	}
	function eliminar(valor){
		var mUrl = "processFileAction.do?method=eliminar&idP="+valor;
		window.location.href = mUrl;	
	}
	function subir(valor){
		var mUrl = "processFileAction.do?method=pop&idP="+valor;
		window.location.href = mUrl;	
    }
</script>
</html:form>