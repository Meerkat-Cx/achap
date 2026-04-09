<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/visualizarReportesAchaAction.do" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscar">
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>B&uacute;squeda de Reporte por Animal</FONT></TD>
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
			<TD class="celdaLabel"><strong>Numero de registro:</strong> </TD>
			<TD class="celdaInput">
                <html:text size="45" name="animalForm" title="numero de registro"  property="numReg" styleClass="Input10porc"/>
            </TD>
		</TR>
		<tr>
			<td class="celdaLabel"><strong>Raza del Animal:&nbsp;</strong></td>
			<td class="celdaInput">
			<html:select  property="raza" styleClass="formfields" style="width:160px">	
					<html:options collection="razas" property="id" labelProperty="nombre"/>
			</html:select>
			</td>					
		</tr>
		<tr>
			<td class="celdaLabel"><strong>Sexo:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="sexo" styleClass="formfields" style="width:160px">
							<html:option value="H"  >Hembra</html:option>
							<html:option value="M"  >Macho</html:option>
							
						</html:select>
					</td>
					
		</tr> 
		
		
	</table>
		<html:messages id="message1" message="true" property="error"/>          	
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
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center"><input type="button" value="Aceptar" class="botones" onclick="setMethod('buscar')"></td>
					<td align="left"><input type="button" value="Cancelar" class="botones" onclick="history.go()-1"></td>
			</TR>	
		</table>
	<br/>
	<script language="JavaScript">
		
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	
	</script>    
	
</html:form>