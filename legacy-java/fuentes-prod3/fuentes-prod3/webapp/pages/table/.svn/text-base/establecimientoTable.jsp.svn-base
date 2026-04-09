<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="/tags/displaytag" prefix="display" %>

<html:form action="/editarEstablecimientoValidate.do" method="post" onsubmit="return validate();">		
	<html:javascript formName="establecimientoForm"/>
		<input type="hidden" name="method" value="buscar"/>
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda de Tambos</FONT>
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
				<TD class="celdaInputBusqueda"><html:text size="45" property="idEst" styleClass="Input100porc" styleId="idEst"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Nombre de Tambo:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombre" styleClass="Input100porc" styleId="nombre"/></TD>
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
    <display:table name="listaEstablecimientos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/editarEstablecimientoValidate.do?method=buscar" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Tambo</font>
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
        <display:column align="left" title="cuig" property="cuig"/>
        <display:column align="left" title="Id" property="id" href="editarEstablecimientoValidate.do?method=initmod" paramId="id" paramProperty="id"/>
        
        <display:column align="left" title="Nombre" property="nombreContacto"/>
	    <display:column align="left" title="s1Tbo" property="s1Tbo"/>
	    <display:column align="left" title="s1Eclo" property="s1Eclo"/>
	    <display:column align="left" title="s1Prop" property="s1Prop"/>
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
				<html:button property="addUsers" onclick="javascript:agregarEstablecimiento()"  styleClass="botones">
					<bean:message key="createEstablecimiento"/>
				</html:button>
			</td>
		</tr>	
	</table>
	
</div>
</html:form>
<script language="JavaScript">
	
	
	function agregarEstablecimiento() {
		var mUrl = "editarEstablecimientoValidate.do?method=initAdd";
		window.location.href = mUrl;
	}
	function editarLugaresContacto(valor) {
		var mUrl = "edicionUbicacion.do?method=listarUbicaciones&contactoId="+valor+"&actionBack=/editarEstablecimientoValidate.do?";		
      	window.location.href = mUrl;
	}
	
	function limpiar() {
		var nomElem = document.getElementById("nombre");
		var idEst = document.getElementById("idEst");
		nomElem.value = "";
		idEst.value = "";
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
	

