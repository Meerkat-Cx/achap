<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/actualizarLactancias.do">
	<!-- Titulo de la pagina -->
	
	<input type="hidden" name="method" value="${method}" >
	<TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<TR> 
        	<TD class=Titulo>
        		<FONT color=#529b28>
        			<IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif"	width=11 >
            			Evaluaciones Geneticas
            	</FONT>
            </TD>
            <TD> 
            	<DIV align=right></DIV>
             </TD>
        </TR>
        <TR> 
        	<TD class=texto4 colSpan=2> 
        		<DIV align=right> 
	            	<TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
	                	<TR> 
	                     	<TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2>
	                     	</TD>
	                        <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2>
	                        </TD>
	                    </TR>
					</TABLE>
             	</DIV>
            </TD>
		</TR>
    </TABLE>
   	<!-- Fin Titulo de la pagina -->
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
				<TD class="celdaLabel">TOTAL </TD>
				<TD class="celdaLabel"> </TD>
			</TR>
		<tr> 
			<td align="center">
				<input type="button" value="Actualizar" class="botones" onclick="setMethod('ejecutar')">
			</td>
			<td align="center"><input type="button" value="Cancelar" class="botones" onclick="history.go(-1)"></td>
		</TR>
	</table>
	<br>
	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
	
			<TR>
				<TD class="celdaLabel">Desde Id. de Animal: </TD>
				<TD class="celdaInput">
            	    <html:text size="20" name="lactanciasGeneticasForm" title="ID del Animal"  property="idAnimal" /> 
	            </TD>
			</TR>
		
		<tr> 
			<td align="center">
				<input type="button" value="Actualizar" class="botones" onclick="setMethod2('ejecutar2')">
			</td>
			<td align="center"><input type="button" value="Cancelar" class="botones" onclick="history.go(-1)"></td>
		</TR>
	</table>
	
	<script>
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
		function setMethod(valor){
		alert('antes del if');
		alert(valor);
		
			alert('ANTES DE LLAMAR');
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	
    	function setMethod2(valor){
		alert('antes del if');
		alert(valor);
		alert('ES EJECUTAR 2');
				if (isEmpty(document.forms[0].elements["idAnimal"].value)) {
					alert("Id. de Animal es un campo obligatorio");
					return;
				}
				if (!isPosInteger(document.forms[0].elements["idAnimal"].value)) {
					alert("Id. de Animal debe ser un número");
					return;
				}
		
		alert('ANTES DE LLAMAR');
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
	</script>
</html:form>