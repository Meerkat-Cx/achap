<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<div class="justify">
    <display:table name="listaEntidadesRegionales" align="center" class="its" id="entidadRegional" pagesize="5" requestURI="/listarEntidadRegional.do?method=listar">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                <tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Regionales</font>
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
        <display:column align="left"  property="id" title="ID" />
        <display:column  align="left" property="nombreContacto"
	         title="Nombre" 	         
	         href="editarEntidadRegionalValidate.do?method=initUpdate" 
             paramId="id"
             paramProperty="id">            
        </display:column>
        <display:column title="Foto">
		        <c:choose>	
					<c:when test="${entidadRegional.foto.id == null}">
				        <html:img border="1" src="pages/assets/images/ImagenNoDisponible.jpg"/>
					</c:when>
					<c:otherwise>
					        <a class="orange" align="right">
							<img width="99" height="99" border="1" src='imagen.do?id=<c:out value="${entidadRegional.foto.id}"/> '/>
          					</a>   
					</c:otherwise>
				</c:choose>        		
        </display:column>
        
        <display:column align="left"  property="comentario" title="Comentarios" >        
        </display:column>
        <display:column title="Editar Lugares de Contacto" align="center">
		        <a class="orange" href="javascript:editarLugaresContacto('<c:out value="${entidadRegional.id}"/>');">
		        <img border="1" src="pages/assets/images/btn_modify.gif">
		        </a>
        </display:column>
		
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>

    </display:table>
    
    <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:button property="addEntidad" onclick="javascript:agregarEntidadRegional()"  styleClass="botones">
					<bean:message key="createEntidadRegional"/>
				</html:button>
			</td>
		</tr>	
	</table>
</div>

<script language="JavaScript">
	
	var urlEditar = "<html:rewrite page='/editarEntidadRegionalValidate.do'/>";
	function agregarEntidadRegional() {
		var mUrl = urlEditar + "?method=initAdd";
		window.location.href = mUrl;
	}
	
	function editarLugaresContacto(valor) {
		var mUrl = "edicionUbicacion.do?method=listarUbicaciones&contactoId="+valor+"&actionBack=/listarEntidadRegional.do?";
      	window.location.href = mUrl;
	}
	
	
	
</script>	

