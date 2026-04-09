<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>



<head>
	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
	<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
	<META http-equiv=Cache-Control content=no-cache>
	<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
	<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
</head>



<html:form action="/editarCentroDeComputoAction.do" method="post" onsubmit="return validate();">		
	
		<input type="hidden" name="method" value="listar"/>
		<!-- Titulo de la pagina -->

		<body>

		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda de Centros de Computo</FONT>
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
				<TD class="celdaLabelBusqueda">Identificador:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="id" styleClass="Input100porc" styleId="idEst"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Nombre de Centro:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombreFind" styleClass="Input100porc" styleId="nombre"/></TD>
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

<div class="justify" id="displayCentrosDeComputo">
    <display:table name="listaCentros" align="center"  class="its" id="item"  pagesize="8" requestURI="/editarCentroDeComputoAction.do?method=listar">
		<display:setProperty name="basic.msg.empty_list" >
		 	       <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Centros de Computo Asociados
            			</td>
            		</TR>
            	</table>
		</display:setProperty>
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Centros de Computos</font>
					</td>
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
		</display:caption>
        <display:column align="left" title="Id" property="id" href="editarCentroDeComputoAction.do?method=initmod" paramId="id" paramProperty="id" />
        <display:column align="left" title="Nombre" property="nombre"/>
        <display:column align="left" title="Ciudad" property="ciudad"/>
      </display:table>
     </div>
     
     
     <br>

	<table align="center">
		<tr>
			<td align="center">
				<input type="button" value="Nuevo Centro" onclick="javascript:agregarCentro();" class="Botones" />
			</td>
		</tr>
	</table>

     
     
</body>
</html:form>   

<script>

	function agregarCentro() {
		var mUrl = "editarCentroDeComputoAction.do?method=initAdd";
		window.location.href = mUrl;
	}
	
	function limpiar() {
		var nomElem = document.getElementById("nombre");
		var id = document.getElementById("id");
		nomElem.value = "";
		id.value = "";
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
		return isPosInteger(document.forms[0].idEst.value);
	}			

</script>
  