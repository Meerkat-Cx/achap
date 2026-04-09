<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ taglib uri="/tags/displaytag" prefix="display" %>

<html:form action="/listarEclos.do" method="post" onsubmit="return validate();">		
	<html:javascript formName="ecloBuscarForm"/>
		<input type="hidden" name="method" value="buscar"/>
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda de Eclos</FONT>
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
				<TD class="celdaInputBusqueda"><html:text size="45" property="identificador" styleClass="Input100porc" styleId="nombre"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Nombre de Eclo:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombre" styleClass="Input100porc" styleId="nombre"/></TD>
			</TR>
			<TR>
				<TD class="celdaLabelBusqueda">Nombre de Regional:</TD>
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombreRegional" styleClass="Input100porc" styleId="nombreRegional"/></TD>
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
    <display:table name="listaEclos" align="center" class="its" id="eclo" pagesize="8" requestURI="/listarEclos.do?method=buscar">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                <tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Eclos</font>
					</td>
					<td><div align=right></div>
					</td>
				</tr>
				
				<tr> 
					<td class=texto4 colSpan=2> 
					<div align=right> 
						<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
				            cellPadding=0 width="100%" border=0>
							<tr> 
								<td width="30%" bgColor=#529b28><img height=2 
				                  src="pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                    </div>
                    </td>
				</tr>
             </TABLE>
		  <br>
        </display:caption>
        <display:column property="id" title="Id" />
        <display:column align="left" property="nombreContacto" title="Nombre de Eclo"
                        href="editarEcloValidate.do?method=initUpdate"
                        paramId="id"
                        paramProperty="id" />
		 <display:column title="Foto">
		        <c:choose>	
					<c:when test="${eclo.foto.id == null}">
				        <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
					</c:when>
					<c:otherwise>
					        <a class="orange" align="right">
							<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${eclo.foto.id}"/> '/>
          					</a>   
					</c:otherwise>
				</c:choose>        		
        </display:column>
        <display:column align="left" property="regional.nombreContacto" title="Regional" />
		<display:column title="Editar Lugares de Contacto" align="center">
		        <a class="orange" href="javascript:editarLugaresContacto('<c:out value="${eclo.id}"/>');">
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
				<html:button property="addEntidad" onclick="javascript:agregarEclo()"  styleClass="botones">
					<bean:message key="createEclo"/>
				</html:button>
			</td>
		</tr>	
	</table>
</div>
</html:form>

<script language="JavaScript">
	
	var urlEditar = "<html:rewrite page='/editarEcloValidate.do'/>";
		
	function limpiar() {
		var nomElem = document.getElementById("nombre");
		var nomRegionalElem = document.getElementById("nombreRegional");
		nomElem.value = "";
		nomRegionalElem.value = "";
	}

	function agregarEclo() {
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
	function editarLugaresContacto(valor) {
		var mUrl = "edicionUbicacion.do?method=listarUbicaciones&contactoId="+valor+"&actionBack=/listarEclos.do?";
      	window.location.href = mUrl;
	}
	function validate() {
		return validateEcloBuscarForm(document.forms[0]);
	}	
	
	
</script>	
