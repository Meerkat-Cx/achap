<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

	<html:form  action="/uploadFileAction" enctype="multipart/form-data">
	<input type="hidden" name="method" value="uploadZIP">
		
		<table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="4" align="center" >
		<tr>
			<td class="textoCentrado" colspan="2">
            	Seleccione un archivo para subir. El tama&ntilde;o maximo del archivo es de 512 Kb.
			</td>
		</tr>
     			<TR><TD width="40%" ALIGN="right" class="celdaLabel"><b>Archivo</b></TD>
				<TD width="60%" ALIGN="left" class="celdaInput" ><html:file property="fileUpload" styleClass="boton" /></TD></TR>
			<TR>
				<TD ALIGN="right" class="celdaLabel"><b>Observaciones</b></TD>
				<TD   ALIGN="center" class="celdaInput">
						
							<html:textarea styleId="observacion" property="comments"  styleClass="textBox" rows="4" cols="30" />
						</TD>
			</TR>
			
            <tr>
			
			<TD colspan="2"  ALIGN="center">
				<input  type="button" value="Subir Archivo"  onmouseout="this.style.color='#333';"  onmouseover="this.style.color='#fbe249';" class="Botones" onclick="process()">
			
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</tr>
		</TABLE>
		
		<script language="JavaScript">
		
		
		
		function imposeMaxLength(Object, MaxLen){
  			return (Object.value.length <= MaxLen);
		}
		
		function subir(valor){
					var obs = document.getElementById("observacion");
					if (!imposeMaxLength(obs,255)){
						obs.value = obs.value.substring(0,255);
					}
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
					subir('uploadZIP');
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
	
