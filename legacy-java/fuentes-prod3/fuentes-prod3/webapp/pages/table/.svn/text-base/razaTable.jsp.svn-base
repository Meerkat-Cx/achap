<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<div class="justify">
    <display:table name="listaRazas" align="center" class="its" id="raza">
        <display:caption>
           <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Razas</FONT></TD>
                      <TD> <DIV align=right></DIV>
                      </TD>
                    </TR>
                    <TR> 
                      <TD class=texto4 colSpan=2> <DIV align=right> 
                          <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                            <TR> 
                              <TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                              <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                            </TR>
                          </TABLE>
                        </DIV>
                       </TD>
                    </TR>
           </TABLE>
			<br>
        </display:caption>
        <display:column align="left" property="id" title="ID"/>
		 <display:column align="left" property="nombre" title="Nombre" />
		  <display:column align="left" property="categoriaPura" title="categoria pura" />
    <display:column property="especie.nombre" title="Especie"/>
                <display:column title="Foto">
		        <c:choose>	
					<c:when test="${raza.foto.id == null}">
				        <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
					</c:when>
					<c:otherwise>
					        <a class="orange">
							<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${raza.foto.id}"/> '/>
          					</a>   
					</c:otherwise>
				</c:choose>        		
        </display:column>
        
   
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
    <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
        		<input id="botonNueva" type="button" value="Nueva Raza" class="botones" onclick="agregarRaza()">
			</td>
		</tr>	
		<tr>
	</table>
</div>
<script language="JavaScript">
		function agregarRaza() {
			var mUrl = "listarRazas.do?method=agregar";
			window.location.href = mUrl;
		}
		
		
</script>	

