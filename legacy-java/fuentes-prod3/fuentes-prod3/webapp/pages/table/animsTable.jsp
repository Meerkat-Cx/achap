<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<div class="justify">
    <display:table name="animsData" align="center" pagesize="30" sort="page" class="its" requestURI="/buscarAnim.do" id="animal" >
		<input type="hidden"  name="method" value="<c:out value="${requestScope.metodoUri}"/>">
        <display:caption>
            <h1><bean:message key="menu.verAnims" /></h1>
        </display:caption>
        <display:column property="id"
                        title="ID"
                        sortable="true"
                        headerClass="sortable"
                        href="buscarAnim.do?method=porId"
                        paramId="animalId"
                        paramProperty="id" />
        <display:column property="nombre" title="Nombre" />
        <display:column property="RP" title="RP" />
        <display:column  title="Fecha Nac."><fmt:formatDate pattern="dd/MM/yyyy" value="${animal.fechaNac}" />
        </display:column>   
        <display:column property="raza.nombre" title="Raza" />
        <display:setProperty name="basic.msg.empty_list" >
            <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1>
        </display:setProperty>
    </display:table>
</div>
