<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<div class="justify">
    <display:table name="listaAtributos" align="center" class="its" id="item" pagesize="8" requestURI="/listarConjuntoAtributos.do?method=detalle">

        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<TR> 
                	<TD class=Titulo>
                		<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
                		width=11 align=absMiddle>&nbsp;Atributos de <c:out value="${conjuntoAtributosForm.nombre}"/></FONT>
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
			<br>
        </display:caption>
        <display:column align="left" title="Nombre" property="nombre" href="edicionConjuntoAtributos.do?method=editAtributo" paramId="idValor" paramProperty="id"/>
	    <display:column align="left" title="Valor Por Defecto" property="valorPorDefecto.valor"/>
		<display:column title="Redefinidos" align="center">
			<a class="orange" href="javascript:listadoPorAtributo('<c:out value="${item.id}"/>','<c:out value="${item.nombre}"/>');">
		    	<img border="1" src="pages/assets/images/btn_modify.gif">
		    </a>
        </display:column>
       	<display:setProperty name="basic.msg.empty_list" >
       		<tr>
            	<td class="celdaInput"><bean:message key="displayTag.basic.msg.empty_list" /></td>        		
        	</tr>
        </display:setProperty>
    </display:table>
</div>

<html:form action="/edicionConjuntoAtributos.do" method="post">	
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
       		<td align="center">
				<input id="listar" type="button" value="Cancelar" class="botones" onclick="cancelar()">				
			</td>
		</tr>	
	</table>
</html:form>	
<script language="JavaScript">
	function listadoPorAtributo(id,nombre){
		var mUrl = "listarConjuntoAtributos.do?method=listaRedefinidos&idAtributo="+id+"&nombreAtributo="+nombre;		
      	window.location.href = mUrl;
	}
	function cancelar(){
		var mUrl = "listarConjuntoAtributos.do?method=init";
      	window.location.href = mUrl;
	
	}
</script>	