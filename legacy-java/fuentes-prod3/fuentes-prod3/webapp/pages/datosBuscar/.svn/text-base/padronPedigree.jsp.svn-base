<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/edicionAnimalPedigreeAction.do" onsubmit="return validate();" > 
		<!-- Titulo de la pagina -->
		
		 <input type="hidden" name="method" value="descargarExportacionPDF">
		 <input type="hidden" name="hasta" value="<c:out value="${pedigreeForm.hasta}"/>">
		 <input type="hidden" name="comentario" value="<c:out value="${pedigreeForm.comentario}"/>">
		
		
	
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Actualizar&nbsp;Padron&nbsp;de&nbsp;Animales&nbsp;Machos&nbsp;con&nbsp;HBA </FONT></TD>
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
   	
   
   	
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
              		<tr>
						<td class="celdaLabel">HBA desde:&nbsp;</td>
						<td class="celdaInput">
							<html:select property="desde" styleClass="formfields" style="width:160px" onchange="change()">
								<html:option value="0"  >0</html:option>
								<html:option value="150000"  >150000</html:option>
								<html:option value="250000"  >250000</html:option>
								<html:option value="350000"  >350000</html:option>
								<html:option value="450000"  >450000</html:option>
								<html:option value="550000"  >550000</html:option>
								<html:option value="650000"  >650000</html:option>
								<html:option value="750000"  >750000</html:option>
								<html:option value="850000"  >850000</html:option>
								<html:option value="950000"  >950000</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="celdaLabel" >hasta:&nbsp;</td>
						<TD class="celdaLabelSinAlign" align="left" >
												<div id="propVisible" ><c:out value="${pedigreeForm.hasta}"/></div>
												
												</td>
						
					</tr> 
					<TR>
    			<TD class="celdaLabelSinAlign" align="center" colspan="2">Al presionar sobre la imagen se actulizará el excel correspondiente al rango seleccionado</TD>
				
			 	
			</TR>
              <TR>
    			<TD class="celdaLabel" >[actualizar excel] </TD>
				<TD  class="TextoNegro"><a href="javascript:setMethod()"><img border="0" src="img/excel.gif" title="Descargar"/></a></TD>	
			 	
			</TR>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
			<TD class="celdaLabelSinAlign" colspan="2" align="center" >
												<div id="visible" ><c:out value="${pedigreeForm.comentario}"/></div>
												
												</td>
			</tr>
			
			
	</table>
	
	<script language="JavaScript">
	
	
			
    function setMethod(){
   				var comentario = document.forms[0].elements["comentario"];
   				var elementoVisible = document.getElementById("visible");	
   				comentario.value = 'ESPERE MIENTRAS SE ACTUALIZA EL LISTADO';				
			var resultNode = document.createTextNode(comentario.value);
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
			else{
					
					elementoVisible.appendChild(resultNode);
				}
   	
   	var mUrl= "edicionAnimalPedigreeAction.do?method=generarPadron&numero="+document.forms[0].elements["desde"].value;
		window.location.href = mUrl;
	 
	}
	function change(){
	//alert(form.numReg.value);
			var hasta = document.forms[0].elements["hasta"];
			var desde = document.forms[0].elements["desde"];
			if(desde.value == '0')
				hasta.value = 150000
			if(desde.value == '150000')
				hasta.value = 250000
			if(desde.value == '250000')
				hasta.value = 350000
			if(desde.value == '350000')
				hasta.value = 450000
			if(desde.value == '450000')
				hasta.value = 550000
			if(desde.value == '550000')
				hasta.value = 650000
			if(desde.value == '650000')
				hasta.value = 750000
			if(desde.value == '750000')
				hasta.value = 850000
			if(desde.value == '850000')
				hasta.value = 950000
			if(desde.value == '950000')
				hasta.value = 1050000
			var elementoVisible = document.getElementById("propVisible");					
			var resultNode = document.createTextNode(hasta.value);
			var oldChild = elementoVisible.childNodes[0];
			if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
			else{
					
					elementoVisible.appendChild(resultNode);
				}
		
		
		
		
		//alert(document.forms[0].elements["desde"].value);
		//var pepe = document.forms[0].elements["hasta"];
		//pepe.value = "3000";
			
		//alert(pepe.value);
	 
	}
	
		
	</script>    
</html:form>