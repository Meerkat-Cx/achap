<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<div class="justify">
	<input type="hidden" value=<c:out value="${requestScope.idContacto}"/> id="idContacto"/>
	<input type="hidden" value=<c:out value="${requestScope.actionBack}"/> id="idActionBack"/>
		
    <display:table name="listaUbicaciones" align="center" class="its" id="ubicacion">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                <tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Lugares de Contacto</font>
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
				                  src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                    </div>
                    </td>
				</tr>
             </TABLE>
		  <br>
        </display:caption>
        <display:column align="left" title="Nombre">
	         <a href="javascript:actualizarUbicacion('<c:out value="${ubicacion.id}"/>');">
	        	<c:out value="${ubicacion.nombre}"/>	        	
	        </a>
	    </display:column>
        <display:column align="left" property="provinciaRegion" title="Provincia Region"/>        
		<display:column align="left" property="ciudad" title="Ciudad"/>
        <display:column align="left" property="pais" title="Pais"/>
        <display:column align="left" property="direccion" title="Direccion"/>
        <display:column align="left" property="codigoPostal" title="Codigo Postal"/>
        <display:column align="left" property="mail" title="Mail"/>
        <display:column align="left" property="telefono" title="Telefono"/>
		
        <display:setProperty name="basic.msg.empty_list">
            <h1 class="TextoVerde"><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>

    </display:table>
    
    <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="addEntidad" onclick="javascript:agregarUbicacion()"  styleClass="botones">
					<bean:message key="createUbicacion"/>
				</html:button>
				<html:button property="addEntidad" onclick="javascript:cancelEdicionUbicacion()"  styleClass="botones">
					<bean:message key="cancel"/>
				</html:button>

			</td>
		</tr>	
	</table>
</div>

<script language="JavaScript">
	
	var urlEditar = "<html:rewrite page='/editarEntidadRegionalValidate.do'/>";
	function agregarUbicacion() {
		var actionBackElement = document.getElementById("idActionBack").value;
		var idContactoElemento = document.getElementById("idContacto");
		var mUrl = "edicionUbicacion.do?method=initAddUbicacion&idContacto="+idContactoElemento.value+"&actionBack="+actionBackElement;
		window.location.href = mUrl;
	}
	
	function actualizarUbicacion(valor) {
		var actionBackElement = document.getElementById("idActionBack").value;
		var idContactoElemento = document.getElementById("idContacto");
		var mUrl = "edicionUbicacion.do?method=initUpdateUbicacion&idUbicacion="+valor+"&idContacto="+idContactoElemento.value+"&actionBack="+actionBackElement;
      	window.location.href = mUrl;
	}
	
	var urlInit = "<html:rewrite page=''/>";
	
	function cancelEdicionUbicacion(){
		var actionBackElement = document.getElementById("idActionBack").value;
		var accionEjecutar = actionBackElement+"method=listar";
		var aux =urlInit+accionEjecutar;
      	window.location.href = aux;
	}
	
	
</script>	

