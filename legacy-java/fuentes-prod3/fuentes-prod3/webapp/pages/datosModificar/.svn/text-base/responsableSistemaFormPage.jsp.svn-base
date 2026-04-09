<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
 
<html:html locale="true">
	<head>
    	
	   	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
    
    	<title>Edición de Responsable de Sistema Informático</title>
    </head>
 
	<body>
		<html:form action="/edicionResponsableSistema.do" >
			<html:hidden name="personaForm" property="id"/>
			<html:hidden name="personaForm" property="esEdicion"/>

			<table border="1" class="ingresarDatos" cellSpacing="0" align="center" >
			<tr><td>
				<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
					<tr>
						<td class="tablecaption" colspan="2">
							Responsable del Sistema: (Id:<bean:write name="personaForm" property="contacto.id"/>)
						</td>
					</tr>			
					<TR>
						<TD>
							Empresa de Software: <span class="required">*</span>
						</TD>
						<TD>
							<html:text size="45" name="personaForm" property="contacto.nombreContacto"/>
		            	</TD>
					</TR>
					<TR>
						<TD>
							Apellido: 
						</TD>
						<TD>
							<html:text size="45" name="personaForm" property="contacto.apellido"/>
		            	</TD>
					</TR>
					<TR>
						<TD>
							Nombres: 
						</TD>
						<TD>
							<html:text size="45" name="personaForm" property="contacto.nombrePersona"/>
						</TD>
					</TR>
					<TR>
						<TD>
							Tipo de Documento: 
						</TD>
						<TD>
							<html:text size="22" name="personaForm" property="contacto.tipoDocumento"/>
	    	        	</TD>
					</TR>
					<TR>
						<TD>
							Número de Documento: 
						</TD>
						<TD>
							<html:text size="22" name="personaForm" property="contacto.documento"/>
		            	</TD>
					</TR>			
					<tr>	
						<td>
							Comentarios: 
						</td>
						<td>
							<html:textarea rows="4" cols="44" name="personaForm" property="contacto.comentario"/>
						</td>			
					</tr>
					<TR><TD><br></td></tr>
					<TR>
						<td colspan="2" align="center" >
							<html:submit/>
							<html:cancel/>
						</TD>
					</TR>	
				</table>			
			</td></tr></table>
		</html:form>

	</body>
</html:html>
