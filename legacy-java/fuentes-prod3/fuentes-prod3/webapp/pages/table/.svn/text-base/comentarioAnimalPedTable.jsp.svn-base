<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<html:form action="/edicionAnimalPedigreeAction.do"  >
		<!-- Titulo de la pagina -->
		<input type="hidden" name="method" value="<c:out value="${requestScope.action}"/>">
		  <html:hidden property="mensaje" value="${requestScope.mensaje}"/>	

<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Comentarios</font>
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
		
		
		
<div class="justify">
    <display:table name="comentarios" align="center"  class="its" sort="list" id="item"  >
       <display:column align="left" width="18%" title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fecha}" />
	    </display:column>        
		<display:column property="comentario" title="comentario" />
     
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
  <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
			<td class="celdaLabelSinAlign"    align="center">
				<c:choose>
						<c:when test="${metodo == 'update'}">
	                   		 <input type="button" size="15" align="middle" value="volver" class="botones" name="buscar"  onClick="volverUpdate()" >    
						</c:when>
						<c:otherwise>
									 <input type="button" size="15" align="middle" value="volver" class="botones" name="buscar"  onClick="volver()" >    
						</c:otherwise>
					</c:choose>    
						                     
					           </TD>
        	 <td class="celdaLabelSinAlign"    align="center">
						            <input type="button" size="15" align="middle" value="nuevo Comentario" class="botones" name="buscar"  onClick="setMethod('initAddComentario')" >            
					           </TD>
		</tr>	
	</table>
</html:form>

<script language="JavaScript">
function setMethod(valor){
			var mUrl = "edicionAnimalPedigreeAction.do?method=initAddComentario";
		window.location.href = mUrl;
    	}
function volver(){
			var mUrl = "edicionAnimalPedigreeAction.do?method=initAddEspecial";
		window.location.href = mUrl;
    	}
	function volverUpdate(){
			var mUrl = "edicionAnimalPedigreeAction.do?method=initUpdateEspecial";
		window.location.href = mUrl;
    	}
</script>    

	
	
	
	
	