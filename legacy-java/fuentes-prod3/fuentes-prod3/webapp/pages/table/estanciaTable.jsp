<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<html:form action="/listarEstancias.do" method="post" onsubmit="return validate();">		
	<html:javascript formName="estanciaForm"/>
		<input type="hidden" name="method" value="buscar"/>
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda de Establecimientos</FONT>
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
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<TR> 
            	<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
            		<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Filtros
            	</TD>
            </TR>	
			<TR>
				<TD class="celdaLabelBusqueda">Id:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="id" styleClass="Input100porc" styleId="nombre"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Nombre Establecimiento:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombre" styleClass="Input100porc" styleId="nombre"/></TD>
			</TR>
			<!--<TR>
				<TD class="celdaLabelBusqueda">Nombre de la Eclo:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombreEclo" styleClass="Input100porc" styleId="nombreEclo"/></TD>
			</TR>	-->
			<TR>
				<TD class="celdaLabelBusqueda">Nombre del Propietario:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombrePropietario" styleClass="Input100porc" styleId="nombrePropietario"/></TD>
			</TR>	
		</table>

		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
            	<td align="center">
					<html:submit styleClass="botones">
						<bean:message key="menu.find"/>
					</html:submit>
				</TD>
				<td align="center">
					<input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick="limpiar()">
				</td>
			</TR>	
		</table>
		<br/>


<div class="justify">
    <display:table name="listaEstancias" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/listarEstancias.do?method=buscar" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Establecimientos</font>
					</td>
					<td><div align=right></div></td>
				</tr>
				<tr> 
					<td class=texto4 colSpan=2> <div align=right> 
						<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
				            cellPadding=0 width="100%" border=0>
							<tr> 
								<td width="30%" bgColor=#529b28><img height=2 
				                  src="pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                        </div></td>
				</tr>
			</table>
			<br>
		</display:caption>
        <display:column align="left" title="ID" property="id" href="editarEstanciaValidate.do?method=initmod" paramId="id" paramProperty="id"/>
        <display:column align="left" title="Nombre" property="nombreContacto"/>
	   
   		<display:column align="left" title="Propietario" property="propietario.nombreContacto"/>
   		<display:column title="Editar Lugares de Contacto" align="center">
		        <a class="orange" href="javascript:editarLugaresContacto('<c:out value="${item.id}"/>');">
		        <img border="1" src="pages/assets/images/btn_modify.gif">
		        </a>
        </display:column>

        	<display:setProperty name="basic.msg.empty_list" >
        		<tr>
	            	<td class="celdaInput"><bean:message key="displayTag.basic.msg.empty_list" /></td>        		
	        	</tr>
	        </display:setProperty>
    </display:table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="addUsers" onclick="javascript:agregarEstancia()"  styleClass="botones">
					<bean:message key="createEstancia"/>
				</html:button>
			</td>
		</tr>	
	</table>
</div>
</html:form>
<script language="JavaScript">
	var urlEditar = "<html:rewrite page='/editarEstanciaValidate.do'/>";
	
	function agregarEstancia() {
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
	function editarLugaresContacto(valor) {
		var mUrl = "edicionUbicacion.do?method=listarUbicaciones&contactoId="+valor+"&actionBack=/listarEstancias.do?";		
      	window.location.href = mUrl;
	}
	
	function MM_openBrWindow(theURL,winName,features) { //v2.0
				window.open(theURL,winName,features);
	}

	function limpiar() {
		var nomElem = document.getElementById("nombre");
		//var nomEclo = document.getElementById("nombreEclo");
		var nomPropietario = document.getElementById("nombrePropietario");
		nomElem.value = "";
		//nomEclo.value = "";
		nomPropietario.value = "";
	}
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				alert("El campo Identificador debe ser un numero")  
				return false
			}
		}
		return true
	}
	function validate() {
		return isPosInteger(document.forms[0].id.value);
	}			
	
	
</script>	
	

