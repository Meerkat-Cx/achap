<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:form action="/actualizacionSicelUno.do" method="post" >
	<input type="hidden" name="method" value="buscar">
	<!-- Titulo de la pagina -->
	<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<TR> 
        	<TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            	src="pages/assets/images/flecha_titulos3a.gif" 
            	width=11 align=absMiddle>Actualización de animales de Sicel 1</FONT>
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
			<TD class="celdaLabel">Número de registro (Tipo RC): </TD>
			<TD class="celdaInput">
                <html:text styleId="regInputId" size="20" name="actualizacionSicelUnoForm" title="RP del Animal" property="nroRegistro" />
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Raza: </TD>
			<TD class="celdaInput">
                <html:select property="raza" styleClass="formfields" style="width:160px">
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Sexo: </TD>
			<TD class="celdaInput">
                <html:select property="sexo" styleClass="formfields" style="width:160px">
					<html:option value="H"  >Hembra</html:option>
					<html:option value="M"  >Macho</html:option>
				</html:select>
            </TD>
		</TR>
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
		<tr> 
		    <td align="center">
			<!--<html:submit styleClass="botones"/>-->
		    	<td align="center"><input type="button" value="Buscar" class="botones" onclick="buscar()"></td>
				<td align="center"><input id="botonLimpiar" type="button" value="Limpiar" class="botones" onclick=""></td>
			</TD>
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
		<html:messages id="message2" message="true" property="eventosAnimalSicel1"/>          	
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
		<html:messages id="message3" message="true" property="animalNoSicelUno"/>          	
			<c:if test="${message3!='' and message3!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
					<tr>				
						<td class="TextoError">
							<c:out value="${message3}"/>
						</td>
					</tr>
		       	</table>
		       	<br>
			</c:if>
	</table>
	<script>
		function buscar(){
			var reg = document.getElementById("regInputId").value;
			if (reg == ""){
				alert("Debe ingresar un número de registro.")
				return;
			}
			if (!isPosInteger(reg)){
				alert("Debe ingresar un número válido.");
				return;
			}
			document.forms[0].submit();
    	}
    	function isPosInteger(inputVal) {
			if(inputVal !=null){
				var inputStr = inputVal.toString()
				for (var i = 0; i < inputStr.length; i++) {
					var oneChar = inputStr.charAt(i)
					if (oneChar < "0" || oneChar > "9") {
						return false
					}
				}
				return true;
			}
			return false;
		}
	</script>
</html:form>