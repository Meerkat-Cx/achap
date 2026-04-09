<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html:html locale="true">
	<head>   	    
    	<title>Ficha de Eclo</title>    	
  		<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">    	
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
    </head>
	<body>		
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="top">
		<tr>
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2" align="center">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=left>&nbsp;
					Ficha de Entidad de Control Lechero
            </td>
        </tr>
	</table>		 
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
		 <TR> 
	            <TD class=Titulo><FONT color=#529b28>
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=left>&nbsp;Informacion General</FONT></TD>
	            <TD> 
	            	<DIV align=right></DIV>
	            </TD>
		</TR>
		<tr>
	          	<td class="TextoVerde"><b>Identificador</b></td>
				<td class="TextoVerde" align="left" colspan="2"><bean:write name="eclo" property="id"/></td>
		</tr>		
		<TR>
			<td class="TextoVerde"><b>Cuit/Cuil</b></td>
			<td class="TextoVerde" align="left" colspan="2"><bean:write name="eclo" property="cuit"/>				
			</td>	
		</TR>				
		<tr>
			<td class="TextoVerde" align="left" height="10" width="70"><b>Nombre Completo</b></td>
	        <td class="TextoVerde" align="left"><bean:write name="eclo" property="nombreContacto"/></td>          				
			<td class="TextoVerde" align="right">
          								<logic:notPresent name="eclo" property="foto">
				          					<html:img  border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
          								</logic:notPresent>          				
				          				<logic:present name="eclo" property="foto">
          									<a class="orange" align="right" onclick="MM_openBrWindow('imagen.do?id=<bean:write name="eclo" property="foto.id" />','','scrollbars=yes,resizable=yes,width=512,height=383')">
          										<img width="99" height="99" src='imagen.do?id=<bean:write name="eclo" property="foto.id" /> '/>
          									</a>          									
								        </logic:present>
			</td>		
		</tr>
		<tr>			
			<td class="TextoVerde"><b>Esta Activo?:</b></td>
			<c:choose>
					<c:when test="${eclo.activo == 'true'}">
						<td class="TextoVerde" align="left" colspan="2" >SI</td>
					</c:when>
					<c:otherwise>
						<td class="TextoVerde" align="left" colspan="2">NO</td>
						
					</c:otherwise>
				</c:choose>				
		</tr>
		<tr>
	          	<td class="TextoVerde"><b>Lugares de Contacto</b></td>
				<td class="TextoVerde" align="left">
					 <display:table name="eclo.ubicacions" align="left" class="its" id="ubicacion">
	        	    	        <display:column align="left" property="id" title="ID"/>
						        <display:column align="left" property="nombre" title="Nombre"/>
						        <display:column align="left" property="ciudad" title="Ciudad"/>	        	    	
						        <display:column align="left" property="provinciaRegion" title="Provincia/Region"/>
   						        <display:column align="left" property="pais" title="Pais"/>
	        	    </display:table>
				</td>						
				<td class="TextoVerde"></td>
		</tr>		
		<tr>
	          	<td class="TextoVerde"><b>Comentarios</b></td>
				<td class="TextoVerde" align="right" >
					<html:textarea cols="44" rows="4" name="eclo" property="comentario" styleClass="Input100porc" readonly="true"/>					
				</td>						
				<td class="TextoVerde">
				</td>
		</tr>		
	</table>		
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
			<TR> 
		            <TD class=Titulo><FONT color=#529b28>
		            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Regional</FONT>
		            </TD>
		            <TD> 
		            	<DIV align=right></DIV>
		            </TD>
			</TR>	
			<tr>
				<td class="TextoVerde" align="left" height="10" width="70"><b>Nombre Entidad:<b></td>
		        <td class="TextoVerde" align="left">
		        	<c:out value="${requestScope.eclo.regional.nombreContacto}"/>
		        </td>          	
			</tr>
		</table>
		
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
			<TR> 
		            <TD class=Titulo><FONT color=#529b28>
		            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Responsable de la Eclo</FONT>
		            </TD>
		            <TD> 
		            	<DIV align=right></DIV>
		            </TD>
			</TR>
			<tr>
				<td class="TextoVerde" align="left" height="10" width="70"><b>Nombre Responsable</b></td>
		        <td class="TextoVerde" align="left">
		        	<c:out value="${requestScope.eclo.responsable.apellido}"/> <c:out value="${requestScope.eclo.responsable.nombrePersona}"/>
		        </td>          	
			</tr>		
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
			<TR> 
		            <TD class=Titulo><FONT color=#529b28>
		            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Sistemas Informaticos</FONT>
		            </TD>
		            <TD> 
		            	<DIV align=right></DIV>
		            </TD>
			</TR>
			<tr>
				<td class="TextoVerde" align="left" height="10" width="70"><b>Sistemas</b></td>
		        <td class="TextoVerde" align="left">
		        	    <display:table name="eclo.sistemas" align="left" class="its" id="sistema">
		        	    	        <display:column align="left" property="id" title="ID"/>
							        <display:column align="left" property="nombre" title="Nombre"/>
							        <display:column align="left" property="version" title="Version"/>	        	    	
		        	    </display:table>
		        </td>          	
			</tr>
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">     						
				<TD class=Titulo><FONT color=#529b28>
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Log de Activaciones</FONT>
	            </TD>
	            <TD> 
					<display:table name="eclo.bitacora" width="70%" class="its" id="item" scope="request">        
			    	    	<display:column align="left" title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fecha}" />
		    	 			</display:column>    
				  	      	<display:column  title="Cambio">
				  	      		<c:out value="${item.accion}"/>			  	     	
							</display:column>
							<display:column property="usuario.username" title="usuario responsable"/>				  	      
				 	        <display:setProperty name="basic.msg.empty_list" >
							<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
			            		<TR>
			            			<td class="TextoNegro">No hay Logs
			            			</td>
			            		</TR>
							</table>
				 	        </display:setProperty>
					</display:table>
	            </TD>
		</TR>
		</table>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
			 <TD class=Titulo><FONT color=#529b28>
		            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absbottom >&nbsp;Tambos Asociados</FONT>
		            </TD>
			 <TR> 
		            
		            <TD> 
		    		<display:table name="establecimientos" align="center" class="its" width="100%" id="establecimiento">        
			    	    
						<display:column property="id" title="ID" align="center" />
				  	      <display:column property="nombreContacto" title="Nombre del Tambo" />
				  	      <display:column property="estancia.id" title="Id. de establecimiento" />
				  	      <display:column property="estancia.nombreContacto" title="Nombre del establecimiento" />
				  	      <display:column property="propietario.id" title="Id. de propietario" />
				  	      <display:column property="propietario.nombreContacto" title="Nombre de propietario" />
				  	      
			 	       <display:setProperty name="basic.msg.empty_list" >
			 	       </display:setProperty>
			    	</display:table>
		            </TD>
			</TR>
		</table>
</body>

<script>
	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
	}

	function limpiar() {
		alert("LIMPIAR CAMPOS");
	}
	
	function validate() {
		return validateEcloBuscarForm(document.forms[0]);
	}	
</script>

</html:html>


