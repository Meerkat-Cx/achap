<%@page import="com.grupomost.acha.upload.*"%>
<%@page import="com.grupomost.acha.upload.dao.*"%>
<%@page import="com.grupomost.acha.upload.bo.*"%>
<%@page import="java.util.*"%>
<jsp:include page="bordeArriba.jsp"/>

<FORM ACTION="ArchivosCargadosAllUsers.jsp">
	<TABLE class="tabla" align="center" style="margin-top: 5px; margin-bottom: 5px;">
			<TR>
				<TH>Fecha</TH>
				<TH>Hora</TH>
			</TR>
	<%  
		int idProceso = Integer.parseInt(request.getParameter("id"));
		Proceso proc=(DAOs.getProcesoDAO(request)).load(idProceso);
		Collection bajadas = (DAOs.getBajadaDAO(request)).buscarPorProceso(proc);
		Iterator it = bajadas.iterator();
		while (it.hasNext()) {
			Bajada bajada= (Bajada)it.next();
	%>
			<TR>
				<TD><%=bajada.getFechaString()%></TD>
				<TD><%=bajada.getHoraString()%></TD>
			</TR>
	<%
		}	
	%>
	</TABLE>
	<div align="center">
		<input type="submit" value="Volver" class="boton"/>
	</div>

</FORM>

<jsp:include page="bordeAbajo.jsp"/>