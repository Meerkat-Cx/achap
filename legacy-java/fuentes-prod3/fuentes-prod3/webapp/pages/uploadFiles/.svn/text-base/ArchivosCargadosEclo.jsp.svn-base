<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/davis-tags" prefix="davisjsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form  action="/processFileAction" enctype="multipart/form-data">


			<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	Orden de proceso de lotes</FONT>
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
    <display:table name="procesos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/downloadFileAction.do?method=initCargados" >
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
		 <display:column align="centre" title="usuario" width="15%" property="usuario.username"  />
		<display:column align="center" title="Nombre de archivo" width="20%">
				<c:out value="${item.nombreArchivoEntrada}"/>
		</display:column>	
		<display:column align="left" width="18%" title="Fecha de Subida"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fechaEntrada}" />
	    </display:column>        
        <display:column maxLength="75" align="justify" width="20%" title="Obs. al subir">
        	<a href="javascript:MM_openBrWindow('downloadFileAction.do?method=mostrarObservaciones&obs=<c:out value="${item.observaciones}"/>','','scrollbars=yes,resizable=yes,width=300,height=300,top=100,left=100')">
        		<c:out value="${item.observaciones}"/>
        	</a>
        </display:column>
        <display:column align="center" width="7%" title="Entrada"  ><a href="downloadFileAction.do?method=downloadZIP&id=<c:out value="${item.id}"/>"><img border="0" src="img/bajar.gif" title="Bajar"/></a>
        </display:column>
		
		<c:if test="${item.estado == 'uploaded'}">			
				<display:column align="center" title="Eliminar" width="10%" >
					<a href="javascript:eliminar('<c:out value="${item.id}"/>')">
						<img border="0" src="img/papelera.jpg"  title="Eliminar"></img>
					</a>
				</display:column>				
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
</div>
	
	</tr>			
					
					
</table>
<script language="JavaScript">
	function eliminar(valor){
		var mUrl = "uploadFileAction.do?method=eliminar&idP="+valor;
		window.location.href = mUrl;	
	}
	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
	}
</script>
</html:form>
	
	
	
	
	
	