<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>


<html:html>
<head>
	<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
	<META content="MSHTML 6.00.2800.1400" name=GENERATOR>
	<META http-equiv=Cache-Control content=no-cache>
	<LINK  rel="stylesheet" type="text/css" href="<html:rewrite forward='estilos'/>">
	<LINK rel="stylesheet" type="text/css" href="<html:rewrite forward='display'/>">
</head>

<body>


<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;
			Eventos del Animal
            </td>
        </tr>
 </table>

<BR/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center">          									
				<tr>
					<td class="TextoVerde">
						<c:out value="${animal.nombre}"/>
					</td>
					<td class="TextoVerde" align="right">
						Identificador: <c:out value="${animal.id}"/>
					</td>
				</tr>
		</table>       

<BR/>

	<display:table name="evtAnimals" align="center" class="tablaEventos" id="evento" >		            
			<display:column title="ID Evento" width="10%">
				<c:if test="${evento.nombreTipo == 'COA'}"> 
				</c:if>
				<c:if test="${evento.nombreTipo != 'COA'}">
					<pre><bean:write name="evento" property="id"  /></pre>
				</c:if>
			</display:column>
				<c:if test="${evento.nombreTipo == 'COA'}">
					<display:column title="Control" width="10%" >
						<c:out value="${evento.controlEstablecimiento.id}"/>
					</display:column>
				</c:if>
				<c:if test="${evento.nombreTipo != 'COA'}">
					<display:column title="Control" width="10%"> </display:column>
				</c:if>
				<c:if test="${evento.nombreTipo != 'COA'}">
					<display:column title="Ordenie" width="10%"> </display:column>
				</c:if>
				<c:if test="${evento.nombreTipo == 'COA'}">
					<display:column title="Ordenie" width="10%">
						<c:out value="${evento.idOrdenie}"/>
					</display:column>
				</c:if>
			<display:column title="Tipo Evento" width="10%">
					<pre><bean:write name="evento" property="nombreTipo"  /></pre>
			</display:column>
			<display:column title="Estado" width="10%">
					<logic:present name="evento" property="estadoAnimal" >
						<pre><bean:write name="evento" property="estadoAnimal"  /></pre>
					</logic:present>
			</display:column>
			
			<display:column title="Resumen" width="80%">
					<bean:write name="evento" property="resumenYEncab"  />
			</display:column>
			<display:setProperty name="basic.msg.empty_list" >
			</display:setProperty>
	</display:table>			

</body>
</html:html>
