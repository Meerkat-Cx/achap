<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:form action="/actualizacionSicelUno.do" method="post" >
	<input type="hidden" name="method" >
	<!-- Titulo de la pagina -->
	<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<TR> 
        	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            	src="pages/assets/images/flecha_titulos3a.gif" 
            	width=11 align=absMiddle>Cambio de Madre</FONT>
            </TD>
            <TD> <DIV align=right></DIV></TD>
        </TR>
        <TR> 
        	<TD class=texto4 colSpan=2> <DIV align=right> 
            	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                	<TR> 
                    	<TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                        <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                    </TR>
                </TABLE>
            </DIV></TD>
		</TR>
	</TABLE>
   	<!-- Fin Titulo de la pagina -->
   	<br/>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Tipo de registro: </TD>
			<TD class="celdaInput">
                <html:select property="tipoRegOriMadre" styleClass="formfields" style="width:160px">
					<html:options collection="tiposRegistros" property="id" labelProperty="id"/>
				</html:select>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Número de registro: </TD>
			<TD class="celdaInput">
                <html:text size="20" name="actualizacionSicelUnoForm" title="Número de registro" property="regOriMadre" />
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Raza: </TD>
			<TD class="celdaInput">
                <html:select property="razaMadre" styleClass="formfields" style="width:160px">
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
            </TD>
		</TR>
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
		<tr> 
		    <td align="center"></td>
	    	<td align="center"><input type="button" value="Buscar" class="botones" onclick="asignar()"></td>
			<td align="center"><input id="botonVolver" type="button" value="Volver" class="botones" onclick="volver()"></td>
			
		</TR>	
	</table>
	<table>
		<html:messages id="message1" message="true" property="animalNoExiste"/>          	
			<c:if test="${message1!='' and message1!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
					<tr>				
						<td class="TextoError">
							<c:out value="${message1}"/>
						</td>
					</tr>
		       	</table>
		       	<br>
			</c:if>
		<html:messages id="message2" message="true" property="fechaErronea"/>          	
			<c:if test="${message2!='' and message2!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
					<tr>				
						<td class="TextoError">
							<c:out value="${message2}"/>
						</td>
					</tr>
		       	</table>
		       	<br>
			</c:if>
	</table>
	<script>
		function asignar(){
			document.forms[0].method.value = "asignarMadre";
			document.forms[0].submit();
    	}
    	function volver(){
    		var mUrl="actualizacionSicelUno.do?method=volverCambioMadre";
    		window.location.href = mUrl;
			/*document.forms[0].method.value = "volver";
			document.forms[0].submit();*/
    	}
	</script>
</html:form>