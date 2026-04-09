<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/ReporteNombreHBAAction"  enctype="multipart/form-data">

	<input type="hidden" name="method" value="process">

		<TABLE width="100%" cellPadding=2 cellSpacing=4 bgcolor="#eff3e3">
			    <TR> 
					<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
						Reporte Nombres de Animales HBA</FONT>
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
    <br>
    
    <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="5" align="center" >
    	<tr>
			<TD class="celdaLabel" align="center" >Raza</TD>
			<td class="celdaInput" colSpan=4>
			<html:select property="raza" styleClass="formfields" style="width:160px">
					<html:option value="TODAS" />
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>		
		</tr>
		<tr>
			<td class="celdaLabel"><strong>Sexo:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="sexo" styleClass="formfields" style="width:160px">
							<html:option value="H"  >Hembra</html:option>
							<html:option value="M"  >Macho</html:option>
							<html:option value="A"  >Ambos</html:option>
					</html:select>
				</td>
		</tr> 		
		
		<TR>
			<TD class="celdaLabel">[Descargar Reporte Nombre HBA] </TD>
			<TD  class="celdaLabelSinAlign" align="left" colSpan=4><a href="javascript:setMethod('descargarReporte')"><img border="0" src="img/TXT.jpg" title="Descargar"/></a></TD>
		</TR>
	</table>
	<br>
	<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
	<tr>
	
	
	</tr>			
						
</table>
<script language="JavaScript">


	function setMethod(valor,vale){
		document.forms[0].method.value = valor;
		document.forms[0].submit();
	}
	
	initializeMenus();
	
</script>

</html:form>


					
