<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<table width="70%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="18" align="center" >
			<tr>
				<td width="89%"  align="center" class="Titulo">Descarga de reportes por lotes</td>
				<td width="11%" align="right"><a href="img/ayuda.pdf"><img src="img/help.gif" border="0" title="Ayuda"/></td>
			</tr>
			
			<tr>
				<td width="89%"  align="center" class="textoCentrado">	<c:out value="${requestScope.mensaje}"/></td>
			</tr>
			<tr>		
			  <td align="center">	
				<input type="button" class="Botones" onClick="window.location='construction.do'" value="Aceptar"/>
				</td>
				<td  align="left">&nbsp;	
				</td>		
			</tr>
			
				
			
</table>