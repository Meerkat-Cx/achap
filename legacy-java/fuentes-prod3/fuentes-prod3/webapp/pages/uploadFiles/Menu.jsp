<jsp:include page="bordeArriba.jsp"/>
<FORM>
	<span class="mensaje">Seleccione la operaci&oacute;n que desea realizar.</span>
	<CENTER>
	<TABLE CELLPADDING="1" style="margin-top: 14px; margin-bottom: 5px;">
		<TR><TD><div style="height: 12px" class="boton" align="center" valign="bottom" onclick="window.location='ArchivosCargadosAllUsers.jsp'">Administrar Entradas/Salidas</div></TD></TR>
		<TR><TD><div style="height: 12px" class="boton" align="center" valign="bottom" onclick="window.location='AltaUsuario.jsp'">Agregar Nuevo Usuario</div></TD></TR>
		<TR><TD><div style="height: 12px" class="boton" align="center" valign="bottom" onclick="window.location='../CerrarSession'">Cerrar Sesi&oacute;n</div></TD></TR>
	</TABLE>
	</CENTER>
</FORM>
<jsp:include page="bordeAbajo.jsp"/>