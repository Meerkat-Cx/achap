<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<script type="text/javascript" src="<html:rewrite forward='extremeComponentsJS'/>"></script>

<html:form action="/generarReporteEventosAction.do" method="post">

	<!-- Titulo de la pagina -->
	<input type="hidden" name="method" value="buscarEclos">
	<html:hidden property="error" value="${requestScope.error}" />

	<TABLE width="100$%" cellPadding="2" cellSpacing="2" bgcolor="#eff3e3">
		<TR>
			<TD class="Titulo"><FONT color="#529b28"><IMG height="15" hspace="2"
				src="pages/assets/images/flecha_titulos3a.gif" width="11"
				align="absMiddle" alt=""> B&uacute;squeda de Eclos</FONT></TD>
			<TD>
			<DIV align="right"></DIV>
			</TD>
		</TR>
		<TR>
			<TD class="texto4" colSpan="2">
			<DIV align="right">
			<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing="0"
				cellPadding="0" width="100%" border="0">
				<TR>
					<TD width="30%" bgColor="#529b28"><IMG height="2"
						src="pages/assets/images/pixel.gif" width="2" alt=""></TD>
					<TD width="70%"><IMG height="2" src="pages/assets/images/pixel.gif"
						width="2" alt=""></TD>
				</TR>
			</TABLE>
			</DIV>
			</TD>
		</TR>
	</TABLE>
	<!-- Fin Titulo de la pagina -->
	<br />
	<div class="justify">
		<ec:table items="listaEclos"
			action="generarReporteEventosAction.do?method=buscarEclos"
			view="compact"
			imagePath="${pageContext.request.contextPath}/img/table/*.gif"
			rowsDisplayed="8" autoIncludeParameters="false"
			form="reporteEventosForm">

			<ec:row>
				<ec:column alias="checkbox" title=" " width="5px" filterable="false"
					sortable="false" viewsAllowed="compact" cell="org.extremecomponents.table.cell.SelectedEcloCell"/>
				<ec:column  property="id" title="Id" />
				<ec:column property="nombreContacto" title="Nombre" />
	
			</ec:row>

		</ec:table>
	</div>
	<input type="button" name="sel" class="botones" value="Aceptar"
		onclick="setMethod('seleccionarEclos')" />

	<c:out value="${requestScope.error}" />

	<script language="JavaScript" type="text/javascript">
		
		 function setEcloState(chkbx) {
		 	//make sure that always know the state of the checkbox
                if (chkbx.checked) {
                	eval('document.reporteEventosForm.chkbx_' + chkbx.name).value='SELECTED';
                 } else {
               		eval('document.reporteEventosForm.chkbx_' + chkbx.name).value='UNSELECTED';
                }
             }
         function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
		
		
	</script>


</html:form>

