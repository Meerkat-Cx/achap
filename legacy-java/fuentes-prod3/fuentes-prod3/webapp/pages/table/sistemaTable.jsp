<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<div class="justify">
    <display:table name="listaSistemas" align="center" class="its" id="sistema" pagesize="8" requestURI="/listarSistemas.do?method=listar">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
			            width=11 align=absMiddle>&nbsp;Sistemas Informáticos</FONT></TD>
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
                       </TD>
                    </TR>
                  </TABLE>
				  <br>
        </display:caption>
        <display:column property="id" title="ID" />
        <display:column align="left" property="nombre" title="Nombre del Sistema" 
                        href="editarSistemaValidate.do?method=initUpdate"
                        paramId="id"
                        paramProperty="id" />
        <display:column align="left" property="version" title="Version" />
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
    
    <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="addEntidad" onclick="javascript:agregarEclo()"  styleClass="botones">
					<bean:message key="createSistema"/>
				</html:button>
			</td>
		</tr>	
	</table>
    
</div>


<script language="JavaScript">
	
	var urlEditar = "<html:rewrite page='/editarSistemaValidate.do'/>";

	function agregarEclo() {
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
	function editarLugaresContacto(valor) {
		var mUrl = "edicionUbicacion.do?method=listarUbicaciones&contactoId="+valor+"&actionBack=/listarSistemas.do?";
      	window.location.href = mUrl;
	}
	
	
	
</script>	