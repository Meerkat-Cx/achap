<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<html:html locale="true">
	<head>
    	
	   	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
		<META http-equiv=Cache-Control content=no-cache>
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
		
		
		
		
		<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='menuExpandable'/>">
    
    	<title>Alta/Edición de Ubicación del Contacto</title>
    </head>

	<body>

		<html:form action="/modificarUbicacion.do" method="post" focus="ubicacion.nombre" onsubmit="return validateUbicacionForm(this)">
		    <html:hidden name="ubicacionForm" property="ubicacionId" />
		    <html:hidden name="ubicacionForm" property="redirectURI" />
		    <html:hidden name="ubicacionForm" property="contactoId"  />
			<html:hidden name="ubicacionForm" property="esEdicion"/>

			<table border="1" class="ingresarDatos" cellSpacing="0" align="center" >
			<tr><td>
			<table class="ingresarDatos" cellSpacing="0" cellPadding="2" align="center" >
				<tr>
					<td class="tablecaption" colspan="2">
		                Referencia de Ubicación (Id:<bean:write name="ubicacionForm" property="contactoId"/>)
					</td>					
				</tr>				
				<TR>
					<TD>Lugar de Referencia: <span class="required">*</span></TD>
					<TD>
        		        <html:text size="45" name="ubicacionForm" property="ubicacion.nombre" />
		            </TD>
				</TR>

				<TR>
					<TD>Ciudad: 
					<TD>
        		        <html:text size="45" name="ubicacionForm" property="ubicacion.ciudad" />
		            </TD>
				</TR>

				<TR>
					<TD>Provincia/Región: <span class="required">*</span></TD>
					<TD>
	                	<html:text size="45" name="ubicacionForm" property="ubicacion.provinciaRegion" />
	    	        </TD>
				</TR>
				<TR>
					<TD>País: 
					<TD>
		                <html:text size="45" name="ubicacionForm" property="ubicacion.pais" />
		            </TD>
				</TR>
				<TR>
					<TD>Dirección Completa: </TD>
					<TD>
                		<html:text size="45" name="ubicacionForm" property="ubicacion.direccion" />
            		</TD>
				</TR>
				<TR>
					<TD>Código Postal: </TD>
					<TD>
           		    	<html:text size="22" name="ubicacionForm" property="ubicacion.codigoPostal" />
            		</TD>
				</TR>

				<TR>
					<TD>E-Mail: </TD>
					<TD>
                		<html:text size="45" name="ubicacionForm" property="ubicacion.mail" />
            		</TD>
				</TR>

				<TR>
					<TD>Teléfonos: </TD>
					<TD>
                		<html:text size="45" name="ubicacionForm" property="ubicacion.telefono" />
            		</TD>
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

