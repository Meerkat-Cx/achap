<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<div class="justify">
    <display:table name="listaAtributos" align="center" class="its" >
        <display:caption>
            <h1><bean:message key="menu.verAtributos" /></h1>
        </display:caption>
        <display:column property="id" title="ID" />
        <display:column property="nombre" title="Nombre" />
        <display:column property="valorPorDefecto.valor" title="Valor por Defecto" />
        <display:column title="Atributos" href="buscarValoresAdmitidos.do" paramId="atributoId" paramProperty="id" >
            Valores Admitidos
        </display:column>
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
</div>
