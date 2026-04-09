<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/visualizarReportesAchaAction.do" method="post" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarLote">
		  
	  <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Buscar&nbsp;Fichas&nbsp;de&nbsp;Lote</FONT></TD>
                        <TD> 
                        	<DIV align=right></DIV>
                        </TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                                <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                              </TR>
                            </TABLE>
                          	</DIV>
                        </TD>
                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>
  
    
            
   	<!-- datos de entrada -->
   	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Nro. Lote: </TD>
			<TD class="celdaInput">
                <html:text size="30" name="animalForm" property="loteNum" styleClass="Input10porc"/>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Id. Lote: </TD>
			<TD class="celdaInput">
                <html:text size="30" name="animalForm" property="loteId" styleClass="Input10porc"/>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Identificador de Eclo: </TD>
			<TD class="celdaInput">
                <html:text size="30" name="animalForm" property="ecloId" styleClass="Input10porc"/>
            </TD>
		</TR>
		
		<TR>
			<TD class="celdaLabel">Sistema: </TD>
			<TD class="celdaInput">
               <html:select property="sistema" styleClass="formfields" style="width:160px">
					
					<html:options collection="sistemas" property="id" labelProperty="nombre"/>
				</html:select>
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Identificador del Centro de computos: </TD>
			<TD class="celdaInput">
                <html:text size="30" name="animalForm" property="centroId" styleClass="Input10porc"/>
            </TD>
		</TR>
	</table>	
   
            				
            
 
	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<!--<html:submit styleClass="botones"/>-->
					<input type="button" value="Aceptar" class="botones" onclick="setMethod()">
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
				</TD>
			</TR>	
		</table>
        
		
		
		
		
		<script language="JavaScript">
		function limpiar() {
		
			var nomElem = document.getElementById("loteNum");
			var idElem = document.getElementById("ecloId");
			var idEle = document.getElementById("centroId");
			
			idElem.value = "";
			nomElem.value = "";
			idEle.value = "";
		
	}
		function setMethod(){
			if ((isEmpty(document.forms[0].elements["loteNum"].value))&&(isEmpty(document.forms[0].elements["loteId"].value))) {
				alert("Numero de Lote o Identificador de lote es obligatorio");
				return;
			}
			if ((!isEmpty(document.forms[0].elements["loteNum"].value))&&(!isEmpty(document.forms[0].elements["loteId"].value))) {
				alert("se debe informar solamente el numero de Lote o Identificador de lote");
				return;
			}
			if ((!isEmpty(document.forms[0].elements["loteNum"].value))&&(!isPosInteger(document.forms[0].elements["loteNum"].value))) {
				alert("Numero de Lote debe ser un número");
				return;
			}
			if ((!isEmpty(document.forms[0].elements["loteId"].value))&&(!isPosInteger(document.forms[0].elements["loteId"].value))) {
				alert("identificador de Lote debe ser un número");
				return;
			}
			if (isEmpty(document.forms[0].elements["ecloId"].value)) {
				alert("Identificador Eclo es obligatorio");
				return;
			}
			if (!isPosInteger(document.forms[0].elements["ecloId"].value)) {
				alert("Identificador Eclo debe ser un número");
				return;
			}
			if (!isEmpty(document.forms[0].elements["centroId"].value)) {
				if (!isPosInteger(document.forms[0].elements["centroId"].value)) {
				alert("Se debe informar el identificador del centro de computos");
				return;
			}
			}
			
    		document.forms[0].submit();
    	}
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
		
    	
	</script>    
	
</html:form>