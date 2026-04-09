<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<div class="justify">
    <display:table name="listaValoresAdmitidos" align="center" class="its" >
        <display:caption>
            <h1><bean:message key="menu.verValoresAdmitidos" /></h1>
        </display:caption>
        <display:column property="id" title="ID" />
        <display:column property="valor" title="Valor" />
        <display:column property="atributo.nombre" title="Atributo" />
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
</div>
