<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    
    <META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
		<title>Edición de Responsable de Eclo</title>
  </head>
  
  <body>
	<html:form action="/edicionResponsableEclo.do" method="post" enctype="multipart/form-data" >
	<html:hidden name="personaForm" property="id"/>
	<html:hidden name="personaForm" property="esEdicion"/>

		<table border="1" class="ingresarDatos" cellSpacing="0" align="center" >
		<tr><td>
		<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
			<tr>
				<td class="tablecaption" colspan="1">
    	            Responsable de Eclo: (Id:<bean:write name="personaForm" property="contacto.id"/>)
				</td>
			</tr>			
			<TR>
				<TD>
					Apellido: <span class="required">*</span>
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
					<html:text size="45" name="personaForm" property="contacto.tipoDocumento"/>
					
            	</TD>
			</TR>
			<TR>
				<TD>
					Número de Documento: 
				</TD>
				<TD>
					<html:text size="45" name="personaForm" property="contacto.documento"/>
					
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
			<TR>
				<TD>
					Foto: 
				</TD>				
				<TD><html:file size="36" property="foto"/></td>	
			</TR>
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

