
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >

			<tr>
				<td class="Titulo" width="70%" align="right">Lista de Fichas de animales</td>
				<td align="right"><a href="img/ayuda.pdf"><img src="img/help.gif" border="0" title="Ayuda"/></a></td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="textoCentrado">	La siguiente es una lista de ficha de animales.<br/>Si desea descargar el archivo PDF haga clic en el ícono correspondiente en la columna Ficha de Animal.<br/></td>
			</tr>

	
	<tr>
	<td colspan="2" >
	<TABLE align="center" border="1px solid #CCCCCC" bgcolor="#F7F9F0"  cellSpacing="0" cellPadding="2">
		<TR>
			<TH  NOWRAP="TRUE">RC/HBA</TH>
			<TH NOWRAP="TRUE">Establecimiento</TH>
			<TH NOWRAP="TRUE">ECLO</TH>
			<TH NOWRAP="TRUE">Fecha</TH>
			<TH NOWRAP="TRUE">Ficha del Animal</TH>
			<TH NOWRAP="TRUE">Remover Ficha </TH>
			
		</TR>
	<c:forEach items="${requestScope.fichas}" var="ficha">
			<TR>
				<td align="center"><c:out value="${ficha.animal.regIdentificador.numero}"/></td>
				<td align="center"><c:out value="${ficha.animal.establecimiento.nombreContacto}"/></td>
				<td align="center"><c:out value="${ficha.animal.establecimiento.eclo.nombreContacto}"/></td>
				<td align="center"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${ficha.fecha}" /></td>
				<TD align="center"><a href="procesarFichaAnimalAction.do?method=downloadFicha&id=<c:out value="${ficha.id}"/>"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>
				<TD align="center"><a href="procesarFichaAnimalAction.do?method=removeFicha&id=<c:out value="${ficha.id}"/>"><img border="0" src="img/papelera.jpg" title="Remover"/></a></TD>
			
			</TR>
	</c:forEach>
	</TABLE>
	</td>
	</tr>
	<tr>		
				<td colspan="2" align="center">	
				<input type="button" class="Botones" onClick="window.location='construction.do'" value="Cerrar Sesi&oacute;n"/>
				</td>
						
			</tr>
	
					
					
					
</table>
	