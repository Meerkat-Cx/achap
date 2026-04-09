<%@page import="com.grupomost.acha.upload.*"%>
<%@page import="com.grupomost.acha.upload.dao.*"%>
<%@page import="com.grupomost.acha.upload.bo.*"%>
<%@page import="java.util.*"%>
<jsp:include page="bordeArriba.jsp"/>

<FORM ACTION="Menu.jsp">
	<TABLE class="tabla" align="center" style="margin-top: 5px; margin-bottom: 5px;">
			<TR>
				<TH>Usuario</TH>
				<TH>Eclo</TH>
				<TH>Fecha Entrada</TH>
				<TH>Obs.</TH>
				<TH>Entrada</TH>
				<TH>Fecha Salida</TH>
				<TH>Obs.</TH>
				<TH>Salida</TH>
				<TH>Descargada</TH>
			</TR>
	<%  
		Collection procesos = (DAOs.getProcesoDAO(request)).loadAll();
		Iterator it = procesos.iterator();
		while (it.hasNext()) {
			Proceso proc= (Proceso)it.next();
			Usuario user=proc.getUsuario();
		
	%>
				<TR>
				<TD ><%=user.getNombre()%></TD>
				<TD><%=user.getEclo()%></TD>
				<TD NOWRAP="true"><%=proc.getFechaString() + " " + proc.getHoraString()%></TD>
				<TD><img border="0" src="../img/obs.gif" title="<%=proc.getObservaciones()%>"/></TD>
				<TD><a href="../download?id=<%=proc.getId()%>"><img border="0" src="../img/bajar.gif" title="Bajar"/></a></TD>
	<%
			if (proc.getFechaSalida()==null) {
	%>
				<TD colspan="4"><A href="SubirSalida.jsp?id=<%=proc.getId()%>"><img border="0" src="../img/attach.gif" title="Adjuntar Salida"/></A></TD>
			</TR>
	<%
			}	
			else {
	%>
				<TD NOWRAP="true"><%=proc.getFechaSalidaString() + " " + proc.getHoraSalidaString()%></TD>
				<TD><img border="0" src="../img/obs.gif" title="<%=proc.getObservacionSalida()%>"/></TD>
				<TD><a href="../downloadSalida?id=<%=proc.getId()%>"><img border="0" src="../img/bajar.gif" title="Bajar"/></a></TD>
	<%
				if (proc.getSalidaBajada()) {
	%>
					<TD><a href="HistorialDeDescargas.jsp?id=<%=proc.getId()%>"><img border="0" src="../img/ok.gif" title="Historial de descargas"/></a></TD>
					</TR>
	<%
				}
				else {
	%>
					<TD><img border="0" src="../img/nuevo4.gif" title="La salida aún no ha sido descargada por <%=user.getNombre()%>"/></TD>
					</TR>
	<%
				}
			}
		}
	%>
	</TABLE>
	<div align="center"><input type="submit" value="Volver" class="boton"/></div>
</FORM>

<jsp:include page="bordeAbajo.jsp"/>