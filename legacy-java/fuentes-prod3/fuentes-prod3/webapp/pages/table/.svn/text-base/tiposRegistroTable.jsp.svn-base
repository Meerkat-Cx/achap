<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%-- <%@taglib uri="/tags/displaytag" prefix="display" %> --%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<div class="justify">
    <display:table name="tipos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/listarTiposRegistroAction.do?method=init" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Tipos de Registro</font>
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
        <display:column align="left" title="id" property="id" href="listarTiposRegistroAction.do?method=initEdit" paramId="id" paramProperty="id"/>
	    <display:column align="left" title="Descripcion" property="descripcion" />
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="agregarTipo" onclick="javascript:agregarTipo()"  styleClass="botones">
					<bean:message key="crearTipo"/>
				</html:button>
			</td>
		</tr>	
	</table>
</div>
<script language="JavaScript">
	var urlEditar = "<html:rewrite page='/listarTiposRegistroAction.do'/>";
	function agregarTipo() {
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
</script>	
	

