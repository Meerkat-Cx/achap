<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:form action="/editarUsuarioValidate.do" method="post" focus="nombreContacto"> 
	<html:hidden name="usuarioForm" property="id"/>
	<input type="hidden" name="method" value="buscarContactos">
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle src="pages/assets/images/flecha_titulos3a.gif" >            	
            	Filtro para Busqueda de Contactos
            </td>
            <td><div align=right></div></td>
        </tr>
        <tr> 
        	<td class=texto4 colSpan=2>
            	<div align=right> 
                	<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    	<tr> 
                        	<td width="30%" bgColor=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            <td width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></td>
                        </tr>
                    </table>
               </div>
            </td>
        </tr>
	</table>
	<br/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel">
				Nombre del Contacto: 
			</td>
			<td class="celdaInput">
				<html:text size="45" maxlength="100"  property="nombreContacto" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel"><strong>Tipo del Contacto:&nbsp;</strong></td>
				<td class="celdaInput">
					<c:out value="${requestScope.rolNombre}"/>
				</td>					
		</tr> 
		<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<input type="button" value="Buscar Contacto" class="botones" onclick="setMethod('buscarContactos')">            
			</td>
		</tr>	
	</table>
	<br/>

	<display:table name="listaContactos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/editarUsuarioValidate.do?method=buscarContactos">
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Contactos</font>
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
			<br>
		</display:caption>
	    <display:column align="left" title="ID del Contacto" property="id" />
        <display:column align="left" title="Nombre del Contacto" property="nombreContacto" href="editarUsuarioValidate.do?method=setContacto" paramId="idContacto" paramProperty="id"/>

	</display:table>

		
	</table>
</html:form>

<script>

	function validate() {
		return validateUsuarioForm(document.forms[0]);
	}
	
	function setMethod(valor){
		document.forms[0].method.value = valor;
    	document.forms[0].submit();
    }


</script>
