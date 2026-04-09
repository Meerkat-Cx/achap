<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<div class="justify">
    <display:table name="listaConjuntoAtributos" align="center" class="its" id="conjunto">
        <display:caption>
            <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<TR> 
                	<TD class=Titulo>
        				<FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" 
                		width=11 align=absMiddle>&nbsp;Listado de Entidades</FONT>
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
        <display:column align="left" title="Entidad">        
		        <a class="orange" href="javascript:listarAtributos('<c:out value="${conjunto.id}"/>','<c:out value="${conjunto.nombre}"/>',<c:out value="${conjunto.esIntervalo}"/>);">
		        	<c:out value="${conjunto.nombre}"/>
		        </a>
        </display:column>
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
</div>
<script language="JavaScript">
	function listarAtributos(id,nombre,esIntervalo) {
		var mUrl = "listarConjuntoAtributos.do?method=detalle&id="+id+"&nombre="+nombre+"&esIntervalo="+esIntervalo;
		window.location.href = mUrl;
	}
</script>