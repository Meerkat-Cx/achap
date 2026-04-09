<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:form action="/edicionAtributosRedefinidos.do" method="post">		
	<html:javascript formName="valorForm"/>
		<input type="hidden" name="method" value="buscar"/>
		<!-- Titulo de la pagina -->
		<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        	<TR> 
            	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                     &nbsp;Busqueda para alta de Atributo</FONT>
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
				<TD class="celdaInputBusqueda"><html:text size="45" property="nombreEntidad" styleClass="Input100porc" styleId="nombreEntidad"/></TD>
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
		    <display:table name="listadoEntidades" align="center" class="its" id="item" pagesize="8" requestURI="/edicionAtributosRedefinidos.do?method=buscar">
        		<display:caption>
			    	<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
        		    	<TR> 
                		   	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Resultado</FONT></TD>
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
					<br>
        		</display:caption>
            <display:column align="left" title="Id" property="id"/>
	        <display:column align="left" title="Nombre">
		        <a class="orange" href="javascript:agregarValor('<c:out value="${item.id}"/>','<c:out value="${item.nombre}"/>');">
		        	<c:out value="${item.nombre}"/>
		        </a>
	        </display:column>
        	<display:setProperty name="basic.msg.empty_list" >
        		<tr>
	            	<td class="celdaInput"><bean:message key="displayTag.basic.msg.empty_list" /></td>        		
	        	</tr>
	        </display:setProperty>
    	</display:table>
	</div>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
       		<td align="center">
				<input id="listar" type="button" value="Cancelar" class="botones" onclick="cancelar()">				
			</td>
		</tr>	
	</table>
</html:form>
		
<script>
	function agregarValor(id,nombre){
		var mUrl = "edicionAtributosRedefinidos.do?method=select&idEntidad="+id+"&nombreEntidad="+nombre;
      	window.location.href = mUrl;
	
	}
	function limpiar() {
		var nomElem = document.getElementById("nombreEntidad");
		nomElem.value = "";
	}
	
	function cancelar(){
		var mUrl = "listarConjuntoAtributos.do?method=listaRedefinidos";
      	window.location.href = mUrl;
	}
</script>
