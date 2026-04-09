<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html:html>
<head>
    	
	   	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
    
    	<title>Datos Propietario</title>
    </head>

	<body>
		<html:form action="/buscarPropietario.do" > 
	  <html:hidden name="propietarioForm" property="idProp"/>
	
	
	
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	&nbsp;Datos del Propietario
								           	
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
	

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de Sicel 1
            </td>
        </tr>
		<tr>
			<td class="celdaLabel">Id Eclo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${propietarioForm.s1Eclo}"/>
			</td>
			
		</tr>
		<tr>
			<td class="celdaLabel">Id Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${propietarioForm.s1Prop}"/>
			</td>
			
		</tr>
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Propietario Sicel 3
            </td>
        </tr>
         <tr>
			<td class="celdaLabel">ID</td>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
						<c:out value="${propietarioForm.idProp}"/>
			</td>
		</tr>
        <tr>
			<td class="celdaLabel">
				Nombre Propietario: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.nombre}"/>
				</td>
		</tr>	
		<TR>
			<TD class="celdaLabel">
				Cuit/Cuil: 
			</TD>
			 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.cuit}"/>
			</td>	
		</TR>
		<TR>
			<TD class="celdaLabel">
				Cuig: 
			</TD>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.cuig}"/>
			</td>	       
		</TR>
		<TR>
			<TD class="celdaLabel">
				Renspa: 
			</TD>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.renspa}"/>
			</td>		       
		</TR>
        <tr>
			<td class="celdaLabel">
				Prefijo: 
			</td>
				 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.prefijo}"/>
				</td>
		</tr>	
		
		<tr>
			<td class="celdaLabel">
				Socio: 
			</td>
			 <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${propietarioForm.socio}"/>
							</td>
		</tr>
		<tr>
			<td class="celdaLabel">HAR: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${propietarioForm.har}"/>
			</td>
			
		</tr>
		<tr>
			<html:hidden name="propietarioForm" property="esPersonaFisica"/>
			<td class="celdaLabel">Es Persona Fisica?: </td>
			<c:choose>
					<c:when test="${propietarioForm.esPersonaFisica == 'true'}">
						<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
					</c:when>
					<c:otherwise>
						<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
						
					</c:otherwise>
				</c:choose>
				<br/>
		</tr>
		<tr>
			<html:hidden name="propietarioForm" property="activo"/>
			<td class="celdaLabel">Esta Activo?: </td>
			<c:choose>
					<c:when test="${propietarioForm.activo == 'true'}">
						<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
					</c:when>
					<c:otherwise>
						<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
						
					</c:otherwise>
				</c:choose>
				<br/>
		</tr>
	</table>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
		 <TR> 
	            <TD class=Titulo><FONT color=#529b28>
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

<br>
				
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >

					
					<tr>
						<TD class="celdaInput" colspan="6"> 
						<strong>Numeros de S.R.A. Asigandos: </strong>
						</TD>					
					</tr>
					
					
					<tr>
						<td class="celdaLabelSinWidth" width="30%"><strong>Raza</strong>
						</td>
						<td class="celdaLabelSinWidth" width="15%"><strong>Numero</strong>
						</td>
						<td class="celdaLabelSinWidth" width="15%"><strong>Cuig</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Estab</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Fecha Alta</strong>
						</td>
						<td class="celdaLabelSinWidth" width="20%"><strong>Fecha Baja</strong>
						</td>
					</tr>
						<c:forEach items="${requestScope.expds}" var="expdActual">
							<tr>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.raza.nombre}"/>
								</td>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.numero}"/>
								</td>
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.cuig}"/>
								</td >
								<td class="celdaLabelSinWidth">
									<c:out value="${expdActual.estab}"/>
								</td>
								<td class="celdaLabelSinWidth">
								<fmt:formatDate pattern="dd/MM/yyyy" value="${expdActual.fechaAlta}" />
								</td>
								<td class="celdaLabelSinWidth">
									<fmt:formatDate pattern="dd/MM/yyyy" value="${expdActual.fechaBaja}" />
								</td>
							</tr>
						</c:forEach>											
				</table>

<br>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
				<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
					<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
					<a align="right" href="javascript:MM_openBrWindow('editarPropietarioValidate.do?method=mostrarEstablecimientos&propietarioId=<c:out value="${propietarioForm.idProp}"/>',
					'','scrollbars=yes,resizable=yes,width=900,height=350,top=100,left=100')">
					Tambos
					</a>
				</td>
			</tr>
	 </table>

	 </br>

	 </br>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
					<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
						<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						<a align="right" href="javascript:MM_openBrWindow('editarPropietarioValidate.do?method=mostrarEstancias&propietarioId=<c:out value="${propietarioForm.idProp}"/>',
						'','scrollbars=yes,resizable=yes,width=900,height=350,top=100,left=100')">
						Establecimientos
						</a>
					</td>
			</tr>
	</table>	



				<br>	
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center"  colspan="2">
				<input type="button" value="Aceptar" class="botones" onclick="javascript:window.close()">				
			</td>
		</tr>	
	</table>
</html:form>

<script>
	function MM_openBrWindow(theURL,winName,features) { //v2.0
		window.open(theURL,winName,features);
	}
</script>

	</body>

</html:html>