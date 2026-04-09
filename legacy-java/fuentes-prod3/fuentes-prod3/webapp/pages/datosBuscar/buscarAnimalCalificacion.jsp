<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/altaCalificacionAction.do?method=porRPyEstab" method="post" focus="rp" >
		<!-- Titulo de la pagina -->
		  <html:hidden property="error1" value="${requestScope.error1}"/>	
		   <html:hidden property="error2" value="${requestScope.error2}"/>	
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>B&uacute;squeda de Animal</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                                <TD width="70%"><IMG height=2 
                  src="pages/assets/images/pixel.gif" 
                  width=2></TD>
                              </TR>
                            </TABLE>
                          </DIV></TD>

                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">RP: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="calificacionForm" title="RP del Animal" property="rp" styleClass="Input100porc"/>
            </TD>
		</TR>
		<tr>
				<html:hidden name="calificacionForm" property="idEstab"/>
				<td class="celdaLabel">Id. de Establecimiento</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idEstab}"/>
				</td>
				
		   </tr>
		
		<TR>
			<TD class="celdaLabel">Año de Nacimiento: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="calificacionForm" title="Año de Nacimiento" value="" property="year" styleClass="Input100porc"/>
            </TD>
		</TR>
		<tr>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="right" colspan="1">
						Sexo: 
					</td>
					<td  class="celdaLabelSinAlignSoloColoresYFuentes" align="left" colspan="9">
						<html:select name="calificacionForm" property="sexo">
							<html:option value="Hembra" key="HEMBRA"></html:option>
							<html:option value="Macho" key="MACHO"></html:option>
						</html:select>
					</td>
				</tr>
		
	</table>
	<c:out value="${requestScope.error1}"/>
	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
					<input type="button" value="Aceptar" class="botones" onclick="buscarPorRP()">
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar1()">

						</td>
				</TD>
			</TR>	
		</table>
	
</html:form>

<html:form action="/altaCalificacionAction.do?method=porTipoyNumero" method="post"  >	
<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr>
			<td class="celdaLabel"><strong>Tipo de registro:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="tipoReg" styleClass="formfields" style="width:160px">
							<html:option value="RC"  >RC</html:option>
							<html:option value="HBA"  >HBA</html:option>
							
						</html:select>
			</td>
		</tr> 

		<TR>
			<TD class="celdaLabel">Número de registro: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="calificacionForm" title="Número de Registro del Animal" property="numReg" styleClass="Input100porc"/>
            </TD>
		</TR>
		
		<tr>
			<td class="celdaLabel"><strong>Raza del Animal:&nbsp;</strong></td>
			<td class="celdaInput">
			<html:select property="raza" styleClass="formfields" style="width:160px">
					
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>					
		</tr>
		<tr>
			<td class="celdaLabel">Sexo:&nbsp;</td>
			<td class="celdaInput">
				<html:select property="sexo" styleClass="formfields" style="width:160px">
					<html:option value="H"  >Hembra</html:option>
					<html:option value="M"  >Macho</html:option>
				</html:select>
			</td>					
		</tr> 
        
		
	</table>
	<c:out value="${requestScope.error2}"/>
         <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
	            <input type="button" value="Aceptar" class="botones" onclick="buscarPorHBA()">            
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar2()">

						</td>
				</TD>
			</TR>	
		</table>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<td align="center">	
				<input type="button" class="Botones" onClick="window.location='altaCalificacionAction.do?method=initSetValores' " value="Cambiar Valores Generales"/>
		</td>
		</table>
	
</html:form>

<script language="JavaScript">
	function isEmpty(inputStr) {
		if (inputStr == null || inputStr == "") {
			return true
		}
		return false
	}
	
	function isPosInteger(inputVal) {
		inputStr = inputVal.toString()
		for (var i = 0; i < inputStr.length; i++) {
			var oneChar = inputStr.charAt(i)
			if (oneChar < "0" || oneChar > "9") {
				return false
			}
		}
		return true
	}

    	function buscarPorRP(){
    		if (isEmpty(document.forms[0].elements["rp"].value)) {
				alert("RP es un campo obligatorio");
				return;
			}
			if (isEmpty(document.forms[0].elements["idEstab"].value)) {
				alert("Id. Establecimiento es un campo obligatorio");
				return;
			}
			if (isEmpty(document.forms[0].elements["year"].value)) {
				alert("Año de Nacimiento es un campo obligatorio");
				return;
			}
			if (!isPosInteger(document.forms[0].elements["idEstab"].value)) {
				alert("Id. Establecimiento debe ser un número");
				return;
			}
			if (!isPosInteger(document.forms[0].elements["year"].value)) {
				alert("Año de Nacimiento debe ser un número");
				return;
			}
			
    		document.forms[0].submit();
    	}

		
		function buscarPorHBA(){
    		if (isEmpty(document.forms[1].elements["numReg"].value)) {
				alert("Número de Registro es un campo obligatorio");
				return;
			}			

    		document.forms[1].submit();
    	}
    	function limpiar1() {
			var campo1 = document.forms[0].elements["rp"];		
			var campo2 = document.forms[0].elements["year"];		
			campo1.value = "";
			campo2.value = "";
		}
		function limpiar2() {
			var campo3 = document.forms[1].elements["numReg"];		
			campo3.value = "";
		}
    	
    	
    	
	</script> 
