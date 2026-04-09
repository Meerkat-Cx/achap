<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<div class="justify">
    <display:table name="listadoConjunto" align="center" class="its" id="item" pagesize="8" requestURI="/listarConjuntoAtributos.do?method=listaRedefinidos">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<TR> 
                	<TD class=Titulo>
                		<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
                		width=11 align=absMiddle>&nbsp;<c:out value="${conjuntoAtributosForm.nombre}"/> del atributo <c:out value="${conjuntoAtributosForm.nombreAtributo}"/></FONT>
                	</TD>
                    <TD> <DIV align=right></DIV></TD>
                </TR>
                <TR> 
                	<TD class=texto4 colSpan=2> <DIV align=right> 
                    	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
	                        <TR> 
    	                        <TD width="30%" bgColor=#529b28><IMG height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></TD>
        		                <TD width="70%"><IMG height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></TD>
                            </TR>
                         </TABLE>
                    </DIV></TD>
               	</TR>
         	</TABLE>
			<br>
        </display:caption>
	    <display:column align="left" title="Id">
	    	<c:out value="${item.idEntidad}"/>
	    </display:column>
        <!-- display:column align="left" title="Tipo" property="nombreEntidad" href="edicionAtributosRedefinidos.do?method=editValor" paramId="idValor" paramProperty="id"/-->
        <display:column align="left" title="Nombre">        
        		<c:choose>
        			<c:when test="${conjuntoAtributosForm.esIntervalo &&  item.fin!=null}">
		        		<c:out value="${item.nombreEntidad}"/>
		        	</c:when>
		        	<c:otherwise>
				        <a class="orange" href="javascript:editarValor('<c:out value="${item.id}"/>','<c:out value="${item.idEntidad}"/>','<c:out value="${item.nombreEntidad}"/>',<c:out value="${conjuntoAtributosForm.id}"/>,'<c:out value="${conjuntoAtributosForm.esIntervalo}"/>');">
				        	<c:out value="${item.nombreEntidad}"/>
		        		</a>
		        	</c:otherwise>
        		</c:choose>
        </display:column>
	    <display:column align="left" title="Valor Admitido">
	    	<c:out value="${item.valorAdmAtr.valor}"/>
	    </display:column>
		<c:choose>
			<c:when test="${conjuntoAtributosForm.esIntervalo}">
				<display:column align="left" title="Inicio">
					<fmt:formatDate pattern="dd/MM/yyyy" value="${item.inicio}" />
				</display:column>
				<display:column align="left" title="Fin">
					<fmt:formatDate pattern="dd/MM/yyyy" value="${item.fin}" />
				</display:column>
			</c:when>
			<c:otherwise>
				<display:column title="Borrar" align="center">
					<a class="orange" href="javascript:borrarValor('<c:out value="${item.id}"/>');">
				    	<img border="1" src="pages/assets/images/btn_modify.gif">
		    		</a>
		        </display:column>
		    </c:otherwise>
		</c:choose>
       	<display:setProperty name="basic.msg.empty_list" >
       		<tr>
            	<td class="celdaInput"><bean:message key="displayTag.basic.msg.empty_list" /></td>        		
        	</tr>
        </display:setProperty>
    </display:table>
   	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
   		<tr> 
       		<td align="center">
				<input id="listar" type="button" value="Agregar" class="botones" onclick="agregarValor(<c:out value="${conjuntoAtributosForm.idAtributo}"/>,'<c:out value="${conjuntoAtributosForm.nombreAtributo}"/>',<c:out value="${conjuntoAtributosForm.id}"/>,'<c:out value="${conjuntoAtributosForm.esIntervalo}"/>')">				
				<input id="listar" type="button" value="Cancelar" class="botones" onclick="cancelar()">				
			</td>
		</tr>	
	</table>
</div>

<script language="JavaScript">
	function editarValor(id,idEntidad,nombreEntidad,idConjunto,esIntervalo) {
		var mUrl = "edicionAtributosRedefinidos.do?method=editValor&id="+id+"&idEntidad="+idEntidad
		+"&nombreEntidad="+nombreEntidad+"&esIntervalo="+esIntervalo+"&idConjunto="+idConjunto;		
      	window.location.href = mUrl;
	}
	function agregarValor(idAtributo,nombreAtributo,idConjunto,esIntervalo){
		var mUrl = "edicionAtributosRedefinidos.do?method=insertValor&idAtributo="+idAtributo+
					"&nombreAtributo="+nombreAtributo+"&idConjunto="+idConjunto+"&esIntervalo="+esIntervalo;
      	window.location.href = mUrl;
	}
	function borrarValor(id) {
		var mUrl = "edicionAtributosRedefinidos.do?method=delete&idValor="+id;		
      	window.location.href = mUrl;
	}
	function cancelar(){
		var mUrl = "listarConjuntoAtributos.do?method=detalle";
      	window.location.href = mUrl;
	}
</script>	