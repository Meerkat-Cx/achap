<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<div class="justify">
    <display:table name="estabData" align="center" pagesize="30" sort="page" class="its" width="100%" requestURI="/buscarEstablecimiento.do" id="table2" >
        <display:caption>
            <h1>Establecimientos</h1>
        </display:caption>
        <display:column property="id"
                        title="ID"
                        sortable="true"
                        headerClass="sortable"
                        href="buscarEstablecimiento.do?idActivo=true"
                        paramId="id"
                        paramProperty="id" />
        <display:column property="nombreContacto" title="Nombre" />        
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
</div>
