<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>


<html:html>
<head>
	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
	<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
	<META http-equiv=Cache-Control content=no-cache>
	<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
	<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
</head>

<body>

<div class="justify" id="displayEstablecimientos">
<!-- TABLA CON LO QUE PARA NOSOTROS SON LOS ESTABLECIMIENTOS, PARA LOS TAMBEROS SON LOS TAMBOS-->
    <display:table name="listaEstablecimientos" align="center"  class="its" id="item"  pagesize="8" requestURI="/editarPropietarioValidate.do?method=mostrarEstablecimientos">
		<display:setProperty name="basic.msg.empty_list" >
		 	       <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Tambos Asociados
            			</td>
            		</TR>
            	</table>
		</display:setProperty>
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Tambos</font>
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
        <display:column align="left" title="Id" property="id"/>
        <display:column align="left" title="Nombre" property="nombreContacto"/>
        <display:column align="left" title="Eclo" property="eclo.nombreContacto"/>
        <display:column align="left" title="Metodo de Control" property="metodoControl.codigo"/>

      </display:table>
     </div>
     
     
     <br>

<table align="center">
	<tr>
		<td align="center">
			<input type="button" value="Cerrar" onclick="javascript:cerrar();" class="Botones" />
		</td>
	</tr>
</table>

<script>

	function cerrar() {
		window.close();
	}
</script>
     
     
</body>
</html:html>     