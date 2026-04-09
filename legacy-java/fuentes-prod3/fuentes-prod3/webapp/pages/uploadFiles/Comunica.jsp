<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<table width="70%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="18" align="center" >
			<tr>
				<td class="Titulo" width="70%" align="right">Carga de Archivos -- Sicel 3</td>
				<td align="right"><a href="img/ayuda.pdf"><img src="img/help.gif" border="0" title="Ayuda"/></td>
			</tr>
			
			<tr>
				
					<td colspan="2" align="center" class="textoCentrado">	<c:out value="${requestScope.mensaje}"/></td>
			</tr>
			<tr>		
				<td colspan="2" align="center">	
				<input type="button" class="Botones" onClick="window.location= <c:out value="${requestScope.urlRetorno}"/>;" value="Aceptar"/>
				</td>
						
			</tr>
				
			
</table>
