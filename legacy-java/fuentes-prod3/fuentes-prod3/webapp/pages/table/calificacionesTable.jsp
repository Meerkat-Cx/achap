<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>


<%@page import="ar.org.sicel.persistence.Hembra"%>
<%@page import="ar.org.sicel.persistence.Calificacion"%>
<html:form action="/altaCalificacionAction.do" method="post" enctype="multipart/form-data" onsubmit="return validate();">
<div class="justify">
	<html:hidden property="rol" value="${requestScope.rol}"/>

	
    <display:table name="calif" align="center"  class="its" sort="list" id="item" pagesize="10" requestURI="/filtrarCalificacionAction.do?method=porFiltro">
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Calificaciones</font>
					</td>
					<td><div align=right></div></td>
				</tr>
				<tr> 
					<td class=texto4 colSpan=2> <div align=right> 
						<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
				            cellPadding=0 width="100%" border=0>
							<tr> 
								<td width="30%" bgColor=#529b28><img height=2 
				                  src="pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                        </div></td>
				</tr>
			</table>
			<br>
		</display:caption>       
        <display:column align="left" title="NºBoleta" property="boleta"/>
	    <display:column align="left" title="Fecha" property="fechaFormateada"/>
	    <display:column align="left" title="Modelo" property="modeloCalificacion.id"/>
	    <display:column align="left" title="RP" property="animal.RP"/>
	    <display:column align="left" title="Sexo" ><%= ((Calificacion)item).getAnimal().esHembra() ? "Hembra" : "Macho" %></display:column>
	    <display:column align="left" title="Eclo" property="eclo.id"/>
	    <display:column align="left" title="Propietario" property="propietario.id"/>
	    <display:column align="left" title="Estab." property="estancia.id"/>
	    <display:column align="left" title="Tambo" property="establecimiento.id"/>
	    <display:column align="left" title="Calificador" property="calificador.nombreCompleto"/>
	    <display:column align="left" title="Fecha Nac." property="animal.fechaNacFormateada"/>
		 <c:if test="${(item.modeloCalificacion.id == 2 || item.modeloCalificacion.id == 3)&& (rol != 'GENERAL')&& (rol != 'ADMINISTRADOR')&& (rol != 'PROPIETARIO')&&(rol != 'SRA')}">
				<display:column align="left" title="" href="altaCalificacionAction.do?method=initUpdate" paramId="id" paramProperty="id">Editar
		                </display:column>
		 </c:if>
		 <c:if test="${(item.modeloCalificacion.id == 2 || item.modeloCalificacion.id == 3)&& (rol != 'DATAENTRY' && rol != 'SRA') }">
		 <display:column align="left" title="" href="altaCalificacionAction.do?method=descargarReporte" paramId="id" paramProperty="id">Reporte
		                </display:column>
		 </c:if>
		 <c:if test="${item.modeloCalificacion.id == 1}">
				 <display:column align="left" title="" > </display:column>
		</c:if>
		<c:if test="${item.modeloCalificacion.id != 98  }">
	    	<display:column align="left" title="" href="altaCalificacionAction.do?method=initView" paramId="id" paramProperty="id">Visualizar
		  	</display:column>
		 </c:if>
		  <c:if test="${item.modeloCalificacion.id == 98}">
				 <display:column align="left" title="" > </display:column>
		</c:if>
		 <c:if test="${rol == 'TECNICA' && item.modeloCalificacion.id != 98}">
				<display:column align="left" title="" href="altaCalificacionAction.do?method=initBaja" paramId="id" paramProperty="id">Eliminar
		 </display:column>
		 </c:if>
		 <c:if test="${rol != 'TECNICA' || rol != 'GENERAL' }">
				 <display:column align="left" title="" > </display:column>
		</c:if>
   		<display:setProperty name="basic.msg.empty_list" >
           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Calificaciones
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>
<c:if test="${(rol != 'DATAENTRY' && rol != 'SRA')&&(tam!=0) }">
<tr>
</tr>
      <table width="60%" border="0" align="center" cellpadding="5" cellspacing="0">
	<tr>
	<td align="center" colspan="2">cantidad de boletas imprimibles (modelo 2 y 3): <c:out value="${requestScope.tam}"/>
            			</td>
	</TR>
	<tr>
    	<td align="right" >
							                   <html:select property="rango" tabindex="1" style="width:100px">
                                                           
							                                <html:options collection="listaValores" property="id" labelProperty="nombre" styleClass="Input100porc"/>
                                               </html:select>
                                           </td>
    	<td align="left">
			<input type="button" value="Imprimir rango" class="botones" onclick="reportesMasivos('<c:out value="${requestScope.ids}"/>')">
		</TD>
	</TR>
	
 </table>
</c:if>
<script language="JavaScript">
	function reportesMasivos(lista){
	var rango = document.forms[0].elements["rango"];
			
		
		var mUrl = "altaCalificacionAction.do?method=descargarReporte&rango="+rango.value;
		window.location.href = mUrl;
   	}
</script>
</html:form>