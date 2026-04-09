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
    
    	<title>Datos Tambos</title>
    </head>

	<body>
		<html:form action="/buscarEstablecimiento.do" > 
	  <html:hidden name="establecimientoForm" property="idEst"/>
	
	
	
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	&nbsp;Datos de Tambo
								           	
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
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Tambo Sicel 3
            </td>
        </tr>
         <tr>
			<td class="celdaLabel">ID</td>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
						<c:out value="${establecimientoForm.idEst}"/>
			</td>
		</tr>
		<TR>
			<TD class="celdaLabel">
				Cuit/Cuil: 
			</TD>
			 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.cuit}"/>
			</td>	
		</TR>	
        <tr>
			<td class="celdaLabel">
				Cuig: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.cuig}"/>
				</td>
		</tr>
		<TR>
			<TD class="celdaLabel">
				Renspa: 
			</TD>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.renspa}"/>
			</td>		       
		</TR>
		<tr>
			<td class="celdaLabel">
				Nombre: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.nombre}"/>
				</td>
				
			
			
		</tr>	
		<tr>
			<td class="celdaLabel">
				Id y nombre Establecimiento: 
			</td>
			 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.idEstancia}"/>,&nbsp;<c:out value="${establecimientoForm.estancia}"/>
							</td>
		</tr>
		<tr>
			<td class="celdaLabel">Id. y Nombre Eclo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.idEclo}"/>,<c:out value="${establecimientoForm.nombreContactoEclo}"/>
			</td>
			<html:hidden property="s1Eclo"/>
		</tr>
		<tr>
			<td class="celdaLabel">Id. y Nombre Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.idPropietario}"/>,<c:out value="${establecimientoForm.nombreContactoPropietario}"/>
			</td>
			<html:hidden property="s1Propietario"/>
		</tr>		
		<tr>
			<td class="celdaLabel">
				Activo?: <span class="required">*</span>
			</td>
			<html:hidden name="establecimientoForm" property="activo"/>
			<html:hidden name="establecimientoForm" property="activoEclo"/>
			<html:hidden name="establecimientoForm" property="activoPropietario"/>
			
					
						<c:choose>
								<c:when test="${establecimientoForm.activoEclo == 'false'}">
									<td class="celdaLabelSinAlign" colspan="2" align="left">Inactivo por la baja del control lechero al que pertenece </td>
								</c:when>
								<c:otherwise>
												<c:choose>
													<c:when test="${establecimientoForm.activoPropietario == 'false'}">
														<td class="celdaLabelSinAlign" colspan="2" align="left">Inactivo por la baja de su propietario</td>
													</c:when>
													<c:otherwise>
														<c:choose>
																		<c:when test="${establecimientoForm.activo == 'true'}">
																			<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
																		</c:when>
																		<c:otherwise>
																			<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
																		</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
						  		</c:otherwise>
						</c:choose>
		</tr>
		<tr>
			<td class="celdaLabel">Metodo de Control: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.metodoControl}"/>
			</td>
			<html:hidden property="s1Propietario"/>
		</tr>
		

		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de Sicel 1
            </td>
        </tr>
		<tr>
			<td class="celdaLabel">Id Eclo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Eclo}"/>
			</td>
			<html:hidden property="s1Eclo"/>
		</tr>
		<tr>
			<td class="celdaLabel">Id Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Propietario}"/>
			</td>
			<html:hidden property="s1Propietario"/>
		</tr>
		<tr>
			<td class="celdaLabel">Id Tambo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Tambo}"/>
			</td>
			<html:hidden property="s1Tambo"/>
		</tr>
		
		<tr>
		</tr>
		
		
	</table>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">     						
		  <TD class=Titulo><FONT color=#529b28>
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Log de Activaciones</FONT>
	            </TD>
	            <TD> 
	    		<display:table name="bitacora" width="90%" class="its" id="item" scope="request">        
		    	    	<display:column align="left" title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fecha}" />
	    	 			</display:column>    
			  	      	<display:column  title="Cambio">			  	    
							<c:out value="${item.accion}"/>
			  	      </display:column>
			  	      <display:column title="M.C. Anterior">
			  	      		<c:out value="${item.metodoControl.codigo}"/>
			  	      </display:column>
			  	      <display:column title="M.C. Asignado">
			  	      		<c:out value="${item.metodoControlNuevo.codigo}"/>
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