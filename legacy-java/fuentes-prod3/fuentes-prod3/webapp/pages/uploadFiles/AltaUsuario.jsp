<jsp:include page="bordeArriba.jsp"/>
<FORM ACTION="../registro">
	<span class="mensaje">Formulario de alta de usuarios.</span>
	<CENTER>
	<TABLE style="margin-top: 14px; margin-bottom: 5px;">
		<TR>
			<TH ALIGN="RIGHT">email:</TH>
			<TD><INPUT TYPE="TEXT" NAME="email"/></TD>
		</TR>
		<TR>
			<TH ALIGN="RIGHT">nombre usuario:</TH>
			<TD><INPUT TYPE="TEXT" NAME="nombre"/></TD>
		</TR>
		<TR>
			<TH ALIGN="RIGHT">password:</TH>
			<TD><INPUT TYPE="password" NAME="passwd1"/></TD>
		</TR>
		<TR>
			<TH ALIGN="RIGHT">confirmar password:</TH>
			<TD><INPUT TYPE="password" NAME="passwd2"/></TD>
		</TR>
		<TR>
			<TH ALIGN="RIGHT">eclo:</TH>
			<TD><INPUT TYPE="text" NAME="eclo"/></TD>
		</TR>
		<TR>
			<TH ALIGN="RIGHT">administrador:</TH>
			<TD>
				<SELECT NAME="admin">
					<OPTION VALUE="N">No
					<OPTION VALUE="S">Si
				</SELECT>
			</TD>
		</TR>
	</TABLE>
	</CENTER>
		<div align="center">
			<input type="submit" value="Registrar" class="boton"/>
			<input type="button" value="Volver" class="boton" onclick="window.location='Menu.jsp'">			
		</div>
</FORM>
<jsp:include page="bordeAbajo.jsp"/>