<jsp:include page="bordeArriba.jsp"/>
<%
	int id=Integer.parseInt(request.getParameter("id"));
%>
<FORM method="POST" enctype='multipart/form-data' action="../uploadSalida?id=<%=id%>">
	<span class="mensaje">Ingrese un archivo y una descripcion del resultado del procesamiento de los archivos.</span>
	<CENTER>
		<TABLE cellspacing="10">
			<TR><TD align="right"><b>Archivo</b></td><TD ALIGN="left"><input type="file" name="fichero" class="textBox"></TD></TR>
			<TR>
				<TD align="right"><b>Observaciones</b></td>
				<TD align="left"><TEXTAREA NAME="observaciones" ROWS="8" COLS="26" class="textBox"></TEXTAREA></TD>
			</TR>
			<TR>
				<TD ALIGN="CENTER" colspan="2">
					<input type="submit" value="Adjuntar salida" class="boton">
					<input type="button" value="Volver" class="boton" onclick="window.location='ArchivosCargadosAllUsers.jsp'">
				</TD>
			</TR>
		</TABLE>
	</CENTER>
</FORM>
<jsp:include page="bordeAbajo.jsp"/>