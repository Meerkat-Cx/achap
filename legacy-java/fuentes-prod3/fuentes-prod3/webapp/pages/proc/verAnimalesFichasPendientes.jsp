<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<div class="justify">
    <display:table name="listaId" align="center"  class="its" >
        <display:caption>
            <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >

			<tr>
				<td class="Titulo" align="center">Ver datos del lote solicitado</td>
			</tr>
   		<!-- Fin Titulo de la pagina -->
			</table>
        </display:caption>
        <display:column property="ecloId" title="Eclo"  group="1" />
        <display:column property="loteId" title="Lote"  group="2" />
        <display:column property="animalId" title="Id Animal" />
	    <display:column property="fecha" title="Fecha de Creacion" />
	    <display:column align="center" title="Obtener Ficha del animal" href="visualizarReportesAchaAction.do?method=downloadFicha&id" paramId="id" paramProperty="id"><img border="0" src="img/PDF.jpg" title="Descargar"/></display:column>
		<display:footer>
           	<tr>
           		<td class="Titulo" bgcolor="#eff3e3" valign="center" colspan=4 align="center">Descargar ficha del Lote</td>           	
           		<td class="Titulo" bgcolor="#eff3e3" valign="center" align="center"><a href="visualizarReportesAchaAction.do?method=downloadFichaLote"><img border="0" src="img/PDF.jpg" title="DescargarFichaLote"/></a></td>
			</tr>
		</display:footer>
	</display:table>
	
</div>
