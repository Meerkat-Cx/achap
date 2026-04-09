<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<html:form action="/visualizarReportesAchaAction.do"  >
		<!-- Titulo de la pagina -->
		
		  <html:hidden property="mensaje" value="${requestScope.mensaje}"/>
		  <input type="hidden" name="popUp"	value="<c:out value="${popUp}"/>" />	
		
<div class="justify">
    <display:table name="fichas" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/visualizarReportesAchaAction.do?method=initLotes" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Lotes</font>
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
			<br>
		</display:caption>
    	 <display:column align="left" title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.tiFinProc}" />
	    	 </display:column>    
    	
        <display:column property="numLote" title="Numero de Lote" />
        <display:column property="eclo.id" title="Eclo"/>
        <display:column property="sistema.id" title="Sistema"/>
         <display:column property="centroComputo.nombre" title="Centro"/>
        
		 <display:column align="center" title="Fichas categoria PURAS" href="visualizarReportesAchaAction.do?method=downloadFichaLoteRCD&hoja=A4&loteId" paramId="loteId" paramProperty="id"><img border="0" src="img/PDF.jpg" title="Descargar"/></display:column>
		 <display:column align="center" title="Fichas otra categoria" href="visualizarReportesAchaAction.do?method=downloadFichaLoteOtros&hoja=A4&loteId" paramId="loteId" paramProperty="id"><img border="0" src="img/PDF.jpg" title="Descargar"/></display:column>
		<display:column align="center" title="Borrar fichas" href="visualizarReportesAchaAction.do?method=borrarFichaLote&loteId" paramId="loteId" paramProperty="id"><img border="0" src="img/papelera2.jpg" WIDTH=26 HEIGHT=27 title="Borrar"/></display:column>	
	
        <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">
            				<c:out value="${requestScope.mensaje}"/>	
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>

	<script language="Javascript">
		function cargarPagina(){
			if (document.forms[0].elements["popUp"] != null &&
				document.forms[0].elements["popUp"].value != "")
				alert(document.forms[0].elements["popUp"].value);
			initializeMenus();
		}
	</script>  	
	</script>  
	<script type="text/javascript">
   		window.onload = cargarPagina;  
 	</script>

</html:form>
