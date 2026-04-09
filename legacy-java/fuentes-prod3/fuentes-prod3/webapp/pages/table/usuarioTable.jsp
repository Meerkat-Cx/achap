<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>



<html:form action="/listarUsuario.do" method="post" enctype="multipart/form-data">		
<html:hidden name="usuarioForm" property="indiceCombo" />
		<input type="hidden" name="method" value="<c:out value="${method}"/>"/>
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda de Usuarios</FONT>
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
				<TD class="celdaLabelBusqueda">Nombre:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombre" styleClass="Input100porc" title="Nombre" name="usuarioForm"/></TD> 
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Apellido:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="apellido" styleClass="Input100porc" title="Apellido" name="usuarioForm"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Rol:</TD>
				<TD class="celdaInputBusqueda"><html:select value="rolId" styleId="comboRoles" property="rolId" name="usuarioForm" styleClass="formfields" style="width:400px" >
					<html:option value="">Seleccione</html:option><html:options collection="roles" property="id" labelProperty="nombre" /></html:select>	
				</td>
		</table>

		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
        	<tr> 
				<td align="center">
					<input id="buscarTodos" type="button" value="Buscar" class="botones" onclick="buscar()">
				</td>
			</TR>	
		</table>
		<br/>
		

    <display:table name="listaUsuarios" align="center" defaultsort="6" class="its" sort="list" id="item" pagesize="8" requestURI="/listarUsuario.do?method=buscar" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Usuarios</font>
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
        <display:column align="left" title="Nombre" property="nombre" href="editarUsuarioValidate.do?method=initmod" paramId="id" paramProperty="id" sortable="true"/>
	    <display:column align="left" title="Apellido" property="apellido" sortable="true"/>
        <display:column align="left" title="Usuario" property="username" sortable="true"/>
        <display:column align="left" title="Activo?" sortable="true">
        	<c:if test='${item.activo == true}'>
        		<bean:message key="si"/>
			</c:if>
			<c:if test='${item.activo == false}'>
				<bean:message key="no"/>
			</c:if>
   		</display:column> 		
   		<display:column align="left" title="Email" property="email" sortable="true"/>
        <display:column align="left" title="Rol" property="rol.nombre" sortable="true"/>
        <display:setProperty name="basic.msg.empty_list" >
	            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
	            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	            		<TR>
	            			<td class="TextoNegro">No hay Usuarios para mostrar
	            			</td>
	            		</TR>
	            	</table>
	    </display:setProperty>
        <display:setProperty name="css.th.sorted"  value="background-color: #eff3e3"></display:setProperty>
       	<display:setProperty name="css.th.sortable"  value="background-color: #eff3e3" ></display:setProperty>
		<display:setProperty name="css.th.ascending"  value="background-color: #eff3e3"></display:setProperty>
		<display:setProperty name="css.th.descending"  value="background-color: #eff3e3"></display:setProperty>
    </display:table>
  <c:if test="${listaUsuarios != null}">
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="addUsers" onclick="javascript:agregarUsuario()"  styleClass="botones">
					<bean:message key="createUser"/>
				</html:button>
			</td>
		</tr>	
	</table>
   </c:if>
</html:form>
<script language="JavaScript">
	var urlEditar = "<html:rewrite page='/editarUsuarioValidate.do'/>";
	
	function agregarUsuario(){
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
	function buscar(){
		var combo = document.getElementById("comboRoles");
		document.forms[0].indiceCombo.value = combo.selectedIndex;
		var nombre=document.forms[0].elements["nombre"].value;
		var apellido = document.forms[0].elements["apellido"].value;
		var mUrl = "listarUsuario.do?method=buscar&apellido="+apellido+"&nombre="+nombre+"&index="+combo.selectedIndex;
		window.location.href = mUrl;
	}
	
	
	
	
	
</script>	

	

