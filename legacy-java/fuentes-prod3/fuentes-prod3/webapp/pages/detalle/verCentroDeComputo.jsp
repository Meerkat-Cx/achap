<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:html locale="true">
	<head>
    	
	   	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
    
    	<title>Datos Centro de Computos</title>
    </head>

	<body>
		<html:form action="/buscarCentroDeComputo.do" > 
	  <html:hidden name="busquedaCentroDeComputoForm" property="idC"/>
	
	
	
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	&nbsp;Datos del Centro de Computo
								           	
            	</font>
            </td>
            <td><div align=right></div></td>
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
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Centro de Computo Sicel 3
            </td>
        </tr>
         <tr>
			<td class="celdaLabel">ID</td>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
						<c:out value="${busquedaCentroDeComputoForm.idC}"/>
			</td>
		</tr>
       		
		<tr>
			<td class="celdaLabel">
				Nombre: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.nombre}"/>
			</td>			
		</tr>	

		<tr>
			<td class="celdaLabel">
				Direccion: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.direccion}"/>
			</td>			
		</tr>
		
		<tr>
			<td class="celdaLabel">
				Ciudad: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.ciudad}"/>
			</td>			
		</tr>

		<tr>
			<td class="celdaLabel">
				Código Postal: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.codigoPostal}"/>
			</td>			
		</tr>

		<tr>
			<td class="celdaLabel">
				Teléfono: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.telefono}"/>
			</td>			
		</tr>

		<tr>
			<td class="celdaLabel">
				Mail: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${busquedaCentroDeComputoForm.mail}"/>
			</td>			
		</tr>

		
		<tr>
			<td class="celdaLabel">
				Activo?: 
			</td>
			
			<c:choose>
				<c:when test="${busquedaCentroDeComputoForm.activo == 'false'}">
					<td class="celdaLabelSinAlign" colspan="2" align="left">No </td>	
				</c:when>
				<c:otherwise>
					<td class="celdaLabelSinAlign" colspan="2" align="left">Si </td>
				</c:otherwise>
			</c:choose>			
		</tr>
	</table>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">     						
		  <TD class=Titulo width="30%"><FONT color=#529b28>
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Tambos asociados </FONT>
	            </TD>
	            <TD align="center"> 
	    		<display:table name="tambos" align="left" width="40%" class="its" id="item" scope="request">        
		    	      <display:column  title="Id">			  	    
							<c:out value="${item.id}"/>
			  	      </display:column>
			  	      <display:column title="Nombre">
			    		    <c:out value="${item.nombreContacto}"/>
			  	      </display:column>
			  	      
		 	       <display:setProperty name="basic.msg.empty_list" >
		 	       <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Tambos asociados
            			</td>
            		</TR>
            	</table>
		 	       </display:setProperty>
		    	</display:table>

	            </TD>
		</TR>

	</table>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">     						
		  <TD class=Titulo  width="30%"><FONT color=#529b28>
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Log de Activaciones</FONT>
	            </TD>
	            <TD> 
	    		<display:table name="bitacora" width="70%" class="its" id="item" scope="request">        
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

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center"  colspan="2">
				<input type="button" value="Aceptar" class="botones" onclick="javascript:window.close()">				
			</td>
		</tr>	
	</table>
</html:form>


	</body>

</html:html>