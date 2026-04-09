<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:form action="/buscarPropietario.do">
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="ejecutar">
		 <input type="hidden" name="role" value="<c:out value="${rol}"/>"/>
		  <html:hidden property="error" value="${requestScope.error}"/>
		  <html:hidden property="rol" value="${requestScope.rol}"/>		
		  <html:hidden property="metodo" value="${requestScope.metodo}"/>
		  <html:hidden name="propietarioForm" property="delSistema" />
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>B&uacute;squeda&nbsp;de&nbsp;Propietarios</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                                <TD width="70%"><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                              </TR>
                            </TABLE>
                          </DIV></TD>

                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
</br>


<c:if test="${requestScope.rol != 'externo' && requestScope.rol != 'admin' }">
	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR> 
            <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            	<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Ubicación de los propietarios
            </TD>
    	</TR>
    	<tr>
    		<td class="celdaLabelBusqueda">
				<table width="70%" cellSpacing="0" cellPadding="2" align="center" >
					<tr>
						<td class="celdaInputBusqueda" align="right">
							<c:if test="${requestScope.rol == 'regional'}">
								<input type="radio" name="radioUbicacion" onclick="selectUbicacion()">en la Regional
							</c:if>
							<c:if test="${requestScope.rol != 'regional'}">
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
	        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3"><IMG height=15 
	            hspace=2 
	            src="pages/assets/images/flecha_titulos3a.gif" 
	            width=11 align=absMiddle>&nbsp;Filtros</TD>
	    </TR>			
		<TR>
			<TD class="celdaLabelBusqueda">Id Propietario:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="idProp"  styleClass="Input100porc" styleId="nombre"/></TD>
		</TR>
		<TR>		
			<TD class="celdaLabelBusqueda">Nombre del Propietario:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="nombre" styleClass="Input100porc" styleId="nombre"/></TD>
		</TR>	
		<TR>		
			<TD class="celdaLabelBusqueda">Identificador del Tambo:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="idEstablecimiento" styleClass="Input100porc" styleId="idEstablecimiento"/></TD>
		</TR>	
		<TR>		
			<TD class="celdaLabelBusqueda">Nombre del Establecimiento:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="nombreEstancia" styleClass="Input100porc" styleId="nombreEstancia"/></TD>
		</TR>
	</table>
	
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
           <tr> 
            <td align="center"></td>
			<td align="center">
				<input type="button" value="Buscar" class="botones" onclick="setMethod('ejecutar')">
			</td>				
			<td align="center">
				<input id="botonLimpiar" type="button" value="Limpiar" 
					class="botones" onclick="limpiar()">
			</td>
		</TR>	
	</table>


<c:if test="${requestScope.rol == 'externo'}">		
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
	<TR> 
                        <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Filtros</TD>
    </TR>			
	<TR>
		<TD class="celdaLabelBusqueda">Cuit/Cuil:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="cuit"  styleClass="Input100porc" styleId="nombre"/></TD>
	</TR>
	<TR>		
		<TD class="celdaLabelBusqueda">renspa:</TD>
		<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="renspa" styleClass="Input100porc" styleId="nombre"/></TD>
	</TR>	
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
           <tr> 
                    <td align="center">
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="setMethod('ejecutar')"></td>				
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">
					</td>
			</TR>	
		</table>	
</c:if>

<c:if test="${requestScope.rol == 'admin' || requestScope.rol == 'tecnica' || requestScope.rol == 'eclo'}">	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR> 
            <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3"><IMG height=15 
            hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Filtro por SRA
            </TD>
    	</TR>
		<TR>
			<TD class="celdaLabelBusqueda">SRA Propietario:</TD>
			<TD class="celdaInputBusqueda"><html:text size="45" name="propietarioForm" property="raza"  styleClass="Input100porc" styleId="nombreRaza"/></TD>
		</TR>
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        <tr> 
        	<td align="center">
			<td align="center">
				<input type="button" value="Buscar" class="botones" onclick="buscarPorSRA('ejecutarSRA')">
			</td>				
			<td align="center">
				<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiarSRA()">
			</td>
		</TR>	
	</table>
</c:if>

<c:if test="${requestScope.metodo == 'ejecutar'}">
	<div class="justify">
	    <display:table name="propietariosData" align="center" pagesize="10" id="item" class="its" requestURI="/buscarPropietario.do?method=ejecutar" >
	        <display:caption>
	            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
	            	<tr> 
	                	<td class=Titulo>
	                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
					            width=11 align=absMiddle>&nbsp;Propietarios</font>
						</td>
						<td><div align=right></div></td>
					</tr>
					<tr> 
						<td class=texto4 colSpan=2> <div align=right> 
							<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
					            cellPadding=0 width="100%" border=0>
								<tr> 
									<td width="30%" bgColor=#529b28><img height=2 
					                  src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
									<td width="70%"><img height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
	                            </tr>
							</table>
	                        </div></td>
					</tr>
				</table>
	        </display:caption>
	        <display:column align="left" title="id">
		        <a align="right" href="javascript:MM_openBrWindow('buscarPropietario.do?method=initView&id=<bean:write name="item" property="id" />','','scrollbars=yes,resizable=yes,width=900,height=400,top=100,left=100')">
		        	<bean:write name="item" property="id"/>
		        </a>
	        </display:column>
	        <display:column property="nombreContacto" title="Nombre de Propietario" />        
	        <display:setProperty name="basic.msg.empty_list" >
	           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	            		<TR>
	            			<td class="TextoNegro">
	            				<c:out value="${requestScope.error}"/>	            				
	            			</td>
	            		</TR>
	            	</table>
	        </display:setProperty>
	    </display:table>
	</div>
</c:if>
<c:if test="${requestScope.metodo == 'ejecutarSRA'}">
	<div class="justify">
	    <display:table name="propietariosData" align="center" pagesize="10" id="item" class="its" requestURI="/buscarPropietario.do?method=ejecutarSRA" >
	        <display:caption>
	            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
	            	<tr> 
	                	<td class=Titulo>
	                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
					            width=11 align=absMiddle>&nbsp;Propietarios</font>
						</td>
						<td><div align=right></div></td>
					</tr>
					<tr> 
						<td class=texto4 colSpan=2> <div align=right> 
							<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
					            cellPadding=0 width="100%" border=0>
								<tr> 
									<td width="30%" bgColor=#529b28><img height=2 
					                  src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
									<td width="70%"><img height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
	                            </tr>
							</table>
	                        </div></td>
					</tr>
				</table>
	        </display:caption>
	        <display:column align="left" title="propietario.id">
		        <a align="right" href="javascript:MM_openBrWindow('buscarPropietario.do?method=initView&id=<bean:write name="item" property="propietario.id" />','','scrollbars=yes,resizable=yes,width=900,height=400,top=100,left=100')">
		        	<bean:write name="item" property="propietario.id"/>
		        </a>
	        </display:column>
	        <display:column property="raza.nombre" title="Raza" />
	        <display:column property="propietario.nombreContacto" title="Nombre de Propietario" />        
	        <display:setProperty name="basic.msg.empty_list" >
	           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	            		<TR>
	            			<td class="TextoNegro">
	            				<c:out value="${requestScope.error}"/>	            				
	            			</td>
	            		</TR>
	            	</table>
	        </display:setProperty>
	    </display:table>
	</div>
</c:if>
	<script language="JavaScript">
	
		function trim(stringToTrim) {
			return stringToTrim.replace(/^\s+|\s+$/g,"");
	}
		function isEmpty(paraTrim) {
			var inputStr= trim(paraTrim);

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
		function setMethod(valor){
			
			if(document.forms[0].elements["role"].value == 'admin' || document.forms[0].elements["role"].value == 'regional' 
				|| document.forms[0].elements["role"].value == 'eclo'){
			
				var idProp = (document.forms[0].elements["idProp"].value);
				if (!isPosInteger(idProp)) {
					alert("El campo Identificador de Propietario tiene que ser un entero positivo");
					return;
				}
				var elemIdEstablecimiento = document.getElementById("idEstablecimiento").value;
				if (!isEmpty(elemIdEstablecimiento) && !isPosInteger(elemIdEstablecimiento)) {
					alert("El campo Identificador de Tambo tiene que ser un entero positivo");
					return;
				}
			}
			if(document.forms[0].elements["role"].value == 'externo'){
			
				var cuit = (document.forms[0].elements["cuit"].value);
				var renspa = (document.forms[0].elements["renspa"].value);
				if (!isEmpty(cuit) && !isEmpty(renspa) ) {
						alert("debe completar solamente un campo");
						return;
				}
				if (isEmpty(cuit) && isEmpty(renspa) ) {
						alert("debe completar un campo");
						return;
				}
				
			}
			document.forms[0].method.value = valor;
			document.forms[0].submit();
		}
	
		function MM_openBrWindow(theURL,winName,features) { //v2.0
			window.open(theURL,winName,features);
		}
	
		function limpiar() {		
			
			var campo1 = document.forms[0].elements["idProp"];		
			var campo2 = document.forms[0].elements["nombre"];		
			var campo3 = document.forms[0].elements["idEstablecimiento"];		
			var campo4 = document.forms[0].elements["nombreEstancia"];
			var campo5 = document.forms[0].elements["cuit"];		
			var campo6 = document.forms[0].elements["renspa"];
			campo5.value = "";
			campo6.value = "";
			campo1.value = "";
			campo2.value = "";
			campo3.value = "";
			campo4.value = "";
		}
		
		function limpiarSRA(){
			var campo1 = document.forms[0].elements["nombreRaza"];
			campo1.value = "";
		}
		
		function buscarPorSRA(valor){
			var campo1 = document.forms[0].elements["nombreRaza"];
			if (campo1.value=="")
				alert("Debe ingresar un numero de SRA.");
			else if (!isPosInteger(campo1.value))
					alert("El SRA debe ser un numero.");
			else{
				document.forms[0].method.value = valor;
				document.forms[0].submit();
			}
		}
    	function selectUbicacion(){
    		var campo =  document.forms[0].elements["delSistema"];
    		if (document.forms[0].radioUbicacion[0].checked)
    			campo.value = "no";
    		else
    			campo.value = "si";
    	}
    	function cargarPagina(){
    		if (document.forms[0].elements["role"].value == 'regional' || document.forms[0].elements["role"].value == 'eclo'){
		   		var campo = document.forms[0].elements["delSistema"];
		   		if (campo.value == "si")
		   			document.forms[0].radioUbicacion[1].checked = true;
		   		else
		   			document.forms[0].radioUbicacion[0].checked = true;
		   	}
    		initializeMenus();
    	}
    	window.onload = cargarPagina;  
	</script>    
</html:form>