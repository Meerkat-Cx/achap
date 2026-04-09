<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/buscarAnim.do?method=porRPyEstab" method="post" focus="rp" >
	<!-- Titulo de la pagina -->
	<html:hidden name="animalForm" property="delSistema" />
	<html:hidden name="animalForm" property="rol" />
	<TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<TR> 
        	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            	src="pages/assets/images/flecha_titulos3a.gif" 
            	width=11 align=absMiddle>B&uacute;squeda de Animales</FONT>
            </TD>
            <TD> <DIV align=right></DIV></TD>
        </TR>
        <TR> 
        	<TD class=texto4 colSpan=2> <DIV align=right> 
            	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                	<TR> 
                    	<TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                        <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                    </TR>
                </TABLE>
            </DIV></TD>
		</TR>
	</TABLE>
   	<!-- Fin Titulo de la pagina -->
   	<br/>
   	
   	<c:if test="${animalForm.rol != 'GENERAL' && animalForm.rol != 'PROPIETARIO' && animalForm.rol != 'TECNICA' && animalForm.rol != 'EXTERNO' && animalForm.rol != 'ADMINISTRADOR' }">
   		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
	            <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
	            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Ubicación de los animales
	            </TD>
	    	</TR>
	    	<tr>
	    		<td class="celdaLabelBusqueda">
					<table width="70%" cellSpacing="0" cellPadding="2" align="center" >
						<tr>
							<td class="celdaInputBusqueda" align="right">
								<c:if test="${animalForm.rol == 'REGIONAL'}">
									<input type="radio" name="radioUbicacion" onclick="selectUbicacion()">en la Regional
								</c:if>
								<c:if test="${animalForm.rol != 'REGIONAL'}">
									<input type="radio" name="radioUbicacion" onclick="selectUbicacion()">en la Entidad
								</c:if>
							</td>
							<td class="celdaInputBusqueda" align="left">
								<input type="radio" name="radioUbicacion" onclick="selectUbicacion()">Todos
							</td>
							
						</tr>
					</table>
				</td>
	    	</tr>
		</table>
		<br>
	</c:if>
	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">RP: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="animalForm" title="RP del Animal" property="rp" />
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Id. de Establecimiento: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="animalForm" title="ID del Tambo" value="" property="estabId" />
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Año de Nacimiento: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="animalForm" title="Año de Nacimiento" value="" property="year" />
            </TD>
		</TR>
	</table>
   	<html:messages id="message1" message="true" property="animalNoExiste"/>          	
	<c:if test="${message1!='' and message1!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message1}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
			<!--<html:submit styleClass="botones"/>-->
				<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorRP()"></td>
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF1()">
				</td>
			</TD>
		</TR>	
	</table>

</html:form>

<html:form action="/buscarAnim.do?method=porTipoyNumero" method="post">
	<html:hidden name="animalForm" property="delSistema" />
	<html:hidden name="animalForm" property="rol" />	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel">Tipo de registro:&nbsp;</td>
			<td class="celdaInput">
				<html:select property="tipoReg" styleClass="formfields" style="width:160px">
					<html:option value="RC"  >RC</html:option>
					<html:option value="HBA"  >HBA</html:option>
				</html:select>
			</td>
		</tr> 
		<TR>
			<TD class="celdaLabel">Número de registro: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="animalForm" title="Número de Registro del Animal" property="numReg" />
            </TD>
		</TR>
		<tr>
			<td class="celdaLabel">Raza del Animal:&nbsp;</td>
			<td class="celdaInput">
				<html:select property="raza" styleClass="formfields" style="width:160px">
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
			</td>					
		</tr>
		<tr>
			<td class="celdaLabel">Sexo:&nbsp;</td>
			<td class="celdaInput">
				<html:select property="sexo" styleClass="formfields" style="width:160px">
					<html:option value="H"  >Hembra</html:option>
					<html:option value="M"  >Macho</html:option>
				</html:select>
			</td>					
		</tr> 
	</table>
	<html:messages id="message2" message="true" property="animalNoExistePorRegisro"/>          	
	<c:if test="${message2!='' and message2!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">	<c:out value="${message2}"/></td>
			</tr>
       	</table>
       	<br>
	</c:if>
    <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
		<tr> 
		    <td align="center">
			<!--<html:submit styleClass="botones"/>-->
		    	<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorHBA()"></td>
				<td align="center"><input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF2()"></td>
			</TD>
		</TR>	
	</table>	

</html:form>

<c:if test="${animalForm.rolAdmin == animalForm.rol || animalForm.rolGeneral == animalForm.rol}">
	<html:form action="/buscarAnim.do" method="post">
		 <input type="hidden" name="method" value="porRPyPropiet">
		 <html:hidden name="animalForm" property="delSistema" />
		 <html:hidden name="animalForm" property="rol" />
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
				<TD class="celdaLabel">Id. de Eclo: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Propietario" property="idEclo"/>
            	     <input type="button" value="Buscar Eclo" class="botones" onclick="setMethod('initBuscarEclo')">      
	            </TD>
			</TR>
			<TR>
				<TD class="celdaLabel">Id. de Propietario: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Propietario" property="propietId"/>
            	     <input type="button" value="Buscar Propietario" class="botones" onclick="setMethod('initBuscarPropietario')">      
	            </TD>
			</TR>
			<TR>
				<TD class="celdaLabel">Id. de Tambo: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Tambo"  property="tamboId" />
            	     <input type="button" value="Buscar Tambo" class="botones" onclick="setMethod('initBuscarTambo')">      
	            </TD>
			</TR>
			<TR>
				<TD class="celdaLabel">RP: </TD>
				<TD class="celdaInput">
                	<html:text size="20" name="animalForm" title="RP del Animal" property="rp1" />
	            </TD>
			</TR>
		</table>
	   	<html:messages id="message3" message="true" property="animalNoExistePorPropietario"/>          	
		<c:if test="${message3!='' and message3!=null}">	
			<table width="90%" border="0" align="center" class="texto_error">
				<tr>				
					<td class="TextoError">
						<c:out value="${message3}"/>
					</td>
				</tr>
	       	</table>
	       	<br>
		</c:if>
	 	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
            	<td align="center">
					<!--<html:submit styleClass="botones"/>-->
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorPropiet()"></td>
					<td align="center">
						<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF3()">
					</td>
				</TD>
			</TR>	
		</table>
	</html:form>
</c:if>


<c:if test="${animalForm.rolProv == animalForm.rol}">
	<html:form action="/buscarAnim.do" method="post">
		 <input type="hidden" name="method" value="porRazaySexo">
		 <html:hidden name="animalForm" property="delSistema" />
		 <html:hidden name="animalForm" property="rol" />
		 
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR>
				<TD class="celdaLabel">Id. de Propietario: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Propietario" property="propietId"/>
            	     <input type="button" value="Buscar Propietario" class="botones" onclick="setMethod('initBuscarPropietario')">      
	            </TD>
			</TR>
			<TR>
				<TD class="celdaLabel">Id. de Tambo: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Tambo"  property="tamboId" />
            	     <input type="button" value="Buscar Tambo" class="botones" onclick="setMethod('initBuscarTambo')">      
	            </TD>
			</TR>
			<tr>
				<td class="celdaLabel">Raza del Animal:&nbsp;</td>
				<td class="celdaInput">
					<html:select property="razaEclo" styleClass="formfields" style="width:160px">
						<html:options collection="razas" property="id" labelProperty="nombre"/>
					</html:select>
				</td>					
			</tr>
			<tr>
				<td class="celdaLabel">Sexo:&nbsp;</td>
				<td class="celdaInput">
					<html:select property="sexoEclo" styleClass="formfields" style="width:160px">
						<html:option value="H"  >Hembra</html:option>
						<html:option value="M"  >Macho</html:option>
					</html:select>
				</td>					
			</tr> 
		</table>
	   	<html:messages id="message4" message="true" property="animalNoExistePorRazaySexo"/>          	
		<c:if test="${message4!='' and message4!=null}">	
			<table width="90%" border="0" align="center" class="texto_error">
				<tr>				
					<td class="TextoError">
						<c:out value="${message4}"/>
					</td>
				</tr>
	       	</table>
	       	<br>
		</c:if>
	<html:messages id="message5" message="true" property="animalNoExistePorIdTambo"/>          	
	<c:if test="${message5!='' and message5!=null}">	
		<table width="90%" border="0" align="center" class="texto_error">
			<tr>				
				<td class="TextoError">
					<c:out value="${message5}"/>
				</td>
			</tr>
	    </table>
	    <br>
	</c:if>
	 	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
            	<td align="center">
					<!--<html:submit styleClass="botones"/>-->
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorRazaySexo()"></td>
					<td align="center">
						<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF4()">
					</td>
				</TD>
			</TR>	
		</table>
	</html:form>
</c:if>



<c:if test="${animalForm.rolAdmin == animalForm.rol || animalForm.rol == 'TECNICA'}">
	<html:form action="/buscarAnim.do?method=porId" method="post">
		 <html:hidden name="animalForm" property="delSistema" />
		 <html:hidden name="animalForm" property="rol" />
			 
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR>
				<TD class="celdaLabel">Id. de Animal: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="animalForm" title="ID del Animal"  property="animalId" /> 
	            </TD>
			</TR>
		</table>
		
		<html:messages id="message6" message="true" property="animalPorIdNoExiste"/>          	
		<c:if test="${message6!='' and message6!=null}">	
			<table width="90%" border="0" align="center" class="texto_error">
				<tr>				
					<td class="TextoError">
						<c:out value="${message6}"/>
					</td>
				</tr>
		    </table>
		    <br>
		</c:if>
	<c:if test="${animalForm.rolAdmin == animalForm.rol}">	
	 	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
            	<td align="center">
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorIdAdmin()"></td>
					<td align="center"><input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF5Admin()"></td>
				</TD>
			</TR>	
		</table>
	</c:if>
	<c:if test="${animalForm.rol == 'TECNICA'}">	
	 	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
            	<td align="center">
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscarPorIdTecnica()"></td>
					<td align="center"><input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarF5Tecnica()"></td>
				</TD>
			</TR>	
		</table>
	</c:if>
	
	</html:form>
</c:if>




<script language="JavaScript">
	
	function isEmpty(inputStr) {
		
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				return false
			}
		}
		return true
	}
	function anioValido(inputVal) {
		var fecha=new Date();
		var anio=fecha.getFullYear();
		if(inputVal < 1900 || inputVal > anio)
			return false;
		return true;
		
	}
    
	function setMethod(valor){
			document.forms[2].method.value = valor;
    		document.forms[2].submit();
    		
    	}
	function limpiarF1() {
		var nomElem = document.forms[0].elements["rp"];
		var idElem = document.forms[0].elements["year"];
		var idElem2 = document.forms[0].elements["estabId"];
		idElem.value = "";
		idElem2.value = "";
		nomElem.value = "";
	}
	function limpiarF2() {		
	
		var idElem2 = document.forms[1].elements["numReg"];		
		idElem2.value = "";
	}
	function limpiarF3() {
		var idElem = document.forms[2].elements["idEclo"];
		var idElem2 = document.forms[2].elements["propietId"];
		var idElem3 = document.forms[2].elements["tamboId"];
		var nomElem = document.forms[2].elements["rp1"];
		idElem.value = "";
		idElem2.value = "";
		idElem3.value = "";
		nomElem.value = "";
	}

	function limpiarF4() {
		var idElem2 = document.forms[2].elements["propietId"];
		var idElem3 = document.forms[2].elements["tamboId"];
		idElem2.value = "";
		idElem3.value = "";
	}
	
	function limpiarF5Admin() {
		var idElem2 = document.forms[3].elements["animalId"];
		idElem2.value = "";
	}
	
	function limpiarF5Tecnica() {
		var idElem2 = document.forms[2].elements["animalId"];
		idElem2.value = "";
	}
	

   	function buscarPorRP(){
   		if (isEmpty(document.forms[0].elements["rp"].value)) {
			alert("RP es un campo obligatorio");
			return;
		}
		if (isEmpty(document.forms[0].elements["estabId"].value)) {
			alert("Id. de Tambo es un campo obligatorio");
			return;
		}
		if (isEmpty(document.forms[0].elements["year"].value)) {
			alert("Año de Nacimiento es un campo obligatorio");
			return;
		}
		if (!isPosInteger(document.forms[0].elements["estabId"].value)) {
			alert("Id. Tambo debe ser un número");
			return;
		}
		if (!isPosInteger(document.forms[0].elements["year"].value)) {
			alert("Año de Nacimiento debe ser un número");
			return;
		}
		if (!anioValido(document.forms[0].elements["year"].value)) {
			alert("Año de Nacimiento debe estar comprendido entre 1900 y el año actual");
			return;
		}
   		document.forms[0].submit();
   	}

	function buscarPorHBA(){
   		if (isEmpty(document.forms[1].elements["numReg"].value)) {
			alert("Número de Registro es un campo obligatorio");
			return;
		}			
   		document.forms[1].submit();
   	}
   	
   	function buscarPorIdAdmin(){
   			if (isEmpty(document.forms[3].elements["animalId"].value)) {
				alert("Id. de Animal es un campo obligatorio");
				return;
			}
			if ((!isEmpty(document.forms[3].elements["animalId"].value)) &&(!isPosInteger(document.forms[3].elements["animalId"].value))){
				alert("Id. de Animal debe ser un número");
				return;
			}						
   			document.forms[3].submit();
   	}
   	
   	   	function buscarPorIdTecnica(){
   			if (isEmpty(document.forms[2].elements["animalId"].value)) {
				alert("Id. de Animal es un campo obligatorio");
				return;
			}
			if ((!isEmpty(document.forms[2].elements["animalId"].value)) &&(!isPosInteger(document.forms[2].elements["animalId"].value))){
				alert("Id. de Animal debe ser un número");
				return;
			}						
   			document.forms[2].submit();
   	}
    	
	function buscarPorPropiet(){
			
		if ((isEmpty(document.forms[2].elements["tamboId"].value))&&(isEmpty(document.forms[2].elements["propietId"].value)) &&(isEmpty(document.forms[2].elements["idEclo"].value)) ) {
			alert("Es obligatorio informar el propietario, eclo o tambo");
			return;
		}
		if ((!isEmpty(document.forms[2].elements["propietId"].value))&&(!isPosInteger(document.forms[2].elements["propietId"].value))) {
			alert("Id. de Propietario debe ser un número");
			return;
		}
		if ((!isEmpty(document.forms[2].elements["idEclo"].value))&&(!isPosInteger(document.forms[2].elements["idEclo"].value))) {
			alert("Id. de Eclo debe ser un número");
			return;
		}
		if ((!isEmpty(document.forms[2].elements["tamboId"].value))&&(!isPosInteger(document.forms[2].elements["tamboId"].value))) {
			alert("Id. de Tambo debe ser un número");
			return;
		}
   		document.forms[2].submit();
   	}

	function buscarPorRazaySexo(){
		
		if ((isEmpty(document.forms[2].elements["propietId"].value)) &&(isEmpty(document.forms[2].elements["tamboId"].value)) ) {
			alert("Es obligatorio informar el propietario o el tambo");
			return;
		}
		if ((!isEmpty(document.forms[2].elements["propietId"].value))&&(!isPosInteger(document.forms[2].elements["propietId"].value))) {
			alert("Id. de Propietario debe ser un número");
			return;
		}
		if ((!isEmpty(document.forms[2].elements["tamboId"].value))&&(!isPosInteger(document.forms[2].elements["tamboId"].value))) {
			alert("Id. de Tambo debe ser un número");
			return;
		}
   		document.forms[2].submit();
   	}
   	function selectUbicacion(){
    		var campo =  document.forms[0].elements["delSistema"];
    		var campo2 =  document.forms[1].elements["delSistema"];
    		var campo3;
    		if (document.forms[2] != null)
    			campo3 = document.forms[2].elements["delSistema"];
    		else
    		    campo3 = null;
    		if (document.forms[0].radioUbicacion[0].checked){
    			campo.value = "no";
    			campo2.value = "no";
    			if (campo3 != null)
    				campo3.value = "no";
    			}
    		else{
    			campo.value = "si";
    			campo2.value = "si";
    			if (campo3 != null)
    				campo3.value = "si";
    		}
    }
   	function cargarPagina(){
   			var campo = document.forms[0].elements["delSistema"];
   			if (document.forms[0].elements["rol"].value == 'REGIONAL' || document.forms[0].elements["rol"].value == 'PROVEEDOR'){
		   		if (campo.value == "si")
		   			document.forms[0].radioUbicacion[1].checked = true;
		   		else
		   			document.forms[0].radioUbicacion[0].checked = true;
		   	}
    		initializeMenus();
    	}
    window.onload = cargarPagina;
</script> 

