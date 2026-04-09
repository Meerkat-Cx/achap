<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<html:html>
<head>
	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
	<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
	<META http-equiv=Cache-Control content=no-cache>
	<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
	<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
</head>

<body>

	<html:form  action="/uploadFileAction" enctype="multipart/form-data">
	<input type="hidden" name="method" value="cambiarLote">
		 <html:hidden property="error" value="${requestScope.error}"/>	
		 <html:hidden property="cerrar" value="${requestScope.cerrar}"/>	
		<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="4" align="center" >
		<tr>
				<td class="celdaLabelSinAlign" colspan="2" align="left"><strong>DATOS DEL LOTE:</strong></td>
				
		   </tr>
		    <tr>
				<td class="celdaLabelSinAlign" align="right">Nombre:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${uploadFileForm.nombre}"/>
				</td>
		   </tr>
		    <tr>
				<td class="celdaLabelSinAlign" align="right">Usuario:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${uploadFileForm.username}"/>
				</td>
		   </tr>
		   <tr>
		   </tr>
		   <tr>
		   </tr>
		  
		 <c:if test="${cerrar == 'si' }">
		 <tr>
		 </tr>
		  <tr>
		 </tr>
		  <tr>
		 </tr>
		  <tr>
		 </tr>
		  <tr>
				<td class="celdaLabelSinAlign" colspan="2" align="center"><strong>LOTE CARGADO CORRECTAMENTE</strong></td>
		 </tr>
		 <tr>
			
			<TD colspan="2"  ALIGN="center">
				<input  type="button" value="cerrar"  onmouseout="this.style.color='#333';"  onmouseover="this.style.color='#fbe249';" class="Botones" onclick="clos()">
			</td>
			</tr>
		 
		 </c:if>
		 
		 <c:if test="${cerrar != 'si' }">
		<tr>
			<td class="textoCentrado" colspan="2">
            	Seleccione un archivo para subir. El tama&ntilde;o maximo del archivo es de 512 Kb.
			</td>
		</tr>
     			<TR><TD width="40%" ALIGN="right" class="celdaLabel"><b>Archivo</b></TD>
				<TD width="60%" ALIGN="left" class="celdaInput" ><html:file property="fileUpload" styleClass="boton" /></TD></TR>
			<TR>
				<TD ALIGN="right" class="celdaLabel"><b>Observaciones</b></TD>
				<TD ALIGN="left" class="celdaInput">
					<html:textarea property="comments" styleClass="textBox" rows="4" cols="30"/>
				</TD>
			</TR>
			
            <tr>
			
			<TD colspan="2"  ALIGN="center">
				<input  type="button" value="Subir Archivo"  onmouseout="this.style.color='#333';"  onmouseover="this.style.color='#fbe249';" class="Botones" onclick="process()">
			
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</tr>
			<TR>
			</TR>
			<TR>
			<TD class="TextoError" colspan="2" ><c:out value="${requestScope.error}"/> </td>
		</tr>
		</c:if>  
		</TABLE>
		
		<script language="JavaScript">
		
		function clos(){
				window.close();
	
		}
		
		function subir(valor){
					document.forms[0].method.value = valor;
    				document.forms[0].submit();
    				
		}
		function limpiar() {
		
		
		document.forms[0].reset();
		document.forms[0].fileUpload.focus();
	}
		
		function process(){
			limitAttach();
		}
			
		function limitAttach() {
			var extArray = new Array("zip");
			var file = document.forms[0].fileUpload.value;
			if(file == "")
				alert("Debe ingresar un archivo.");
			else{	
				allowSubmit = false;
				if (!file) return;
				while (file.indexOf("\\") != -1)
					file = file.slice(file.indexOf("\\") + 1);
				ext=file;
				while (ext.indexOf(".") != -1)
					ext = ext.slice(ext.indexOf(".") + 1);
				ext = ext.toLowerCase();
				for (var i = 0; i < extArray.length; i++) {
					if (extArray[i] == ext) { allowSubmit = true; break; }
				}
				if (allowSubmit) 
					//document.forms[0].submit();
					subir('cambiarLote');
				else{
					alert("Solo puede subir archivos del tipo: " 
					+ (extArray.join(" ")) + "\nPor favor, seleccione un nuevo archivo.");
					
					document.forms[0].reset();
					document.forms[0].fileUpload.focus();
					}
				}
		}

	</script>
</html:form>		

</body>
</html:html>
