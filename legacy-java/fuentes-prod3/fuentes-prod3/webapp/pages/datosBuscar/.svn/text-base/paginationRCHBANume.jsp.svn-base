<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/generarReportesEclo.do" method="post" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarPropietario">
		<html:hidden property="error" value="${requestScope.error}"/>
		
		 <input type="hidden" name="unEst" value="<c:out value="${unEst}"/>"/>
		 <input type="hidden" name="unPro" value="<c:out value="${unPro}"/>"/>
		 <input type="hidden" name="unEstTodos"	value="<c:out value="${unEstTodos}"/>" />
		 <input type="hidden" name="unEstPropios"	value="<c:out value="${unEstPropios}"/>" />
		 <input type="hidden" name="unEcl" value="<c:out value="${unEcl}"/>"/>
		 <input type="hidden" name="tambos" value="<c:out value="${tambos}"/>"/>
		 <input type="hidden" name="animales" value="<c:out value="${animales}"/>"/>
		 <input type="hidden" name="calificados" value="<c:out value="${calificados}"/>"/>
		 <input type="hidden" name="orden" value="<c:out value="${orden}"/>"/>
		 <input type="hidden" name="propId" value="<c:out value="${propId}"/>"/>
		 <input type="hidden" name="ecloId" value="<c:out value="${ecloId}"/>"/>
		 <input type="hidden" name="tamboId" value="<c:out value="${tamboId}"/>"/>
		 <input type="hidden" name="tipoLogin" value="<c:out value="${tipoLogin}"/>"/>
		 <input type="hidden" name="pagina" value="<c:out value="${pagina}"/>"/>
		 <input type="hidden" name="eclosOrPropietarios" value="<c:out value="${reportesEcloForm.eclosOrPropietarios}"/>"/>
		 <input type="hidden" name="indiceCombo" value="<c:out value="${reportesEcloForm.indiceCombo}"/>"/>
		 <input type="hidden" name="paginacion" value="<c:out value="${paginacion}"/>"/>
		 <input type="hidden" name="paginacion" value="<c:out value="${cantidadTotalPaginas}"/>"/>

 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
							Paginado del RC-HBANUME</FONT>
						</TD>
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
   	<TABLE width="100%" class="bordeGris" cellPadding=2 cellSpacing=0 bgcolor="#ffffff">
	   	<tr>
			<td class="celdaLabelSinAlign" bgcolor="ffffff">
				En resultado devolvió <c:out value="${cantidadTotalPaginas}"></c:out> animales. El tamaño del reporte supera el límite máximo. Por favor, elija un rango para imprimir: 
			</td>
		</tr>
	   	<c:forEach var="item" items="${paginacion}" >
			<tr>
				<td class="celdaLabelSinAlign" bgcolor="ffffff">
					<input type="radio" name="paginacion" onclick="seleccionarPaginas(<c:out value="${item.id}"/>)" id="${item.id}">
						<c:out value="${item}"/>
				</td>
			</tr>
	   	</c:forEach>
	 </TABLE>
	 <br>
	 <table width="100%" border="0" class="bordeGris" cellpadding="10" cellspacing="0">
		<TR>
			<TD class="celdaLabel">[reporte PDF]</TD>
			<TD class="TextoNegro"><a href="javascript:setMethod('pdf')"><img
				border="0" src="img/PDF.jpg" title="Descargar" /></a></TD>
			<TD class="celdaLabel">[reporte TXT]</TD>
			<TD class="TextoNegro"><a href="javascript:setMethod('txt')"><img
				border="0" src="img/TXT.jpg" title="Descargar" /></a></TD>
		</TR>
	</table>
<script>
				function setMethod(valor){
					if(document.forms[0]["pagina"].value == ""){
						alert("Por favor, elija un rango para imprimir.");
						return;
					}	
					if(valor=='pdf'){
							var mUrl="generarReportesEclo.do?method=descargarExportacionPDF"+
					    			 "&unPro="+document.forms[0].elements["unPro"].value+
					    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
					    			 "&tambos="+document.forms[0].elements["tambos"].value+
					    			 "&propId="+document.forms[0].elements["propId"].value+
					    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
					    			 "&unEst="+document.forms[0].elements["unEst"].value+
					    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
   			 						 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
					    			 "&animales="+document.forms[0].elements["animales"].value+
					    			 "&calificados="+document.forms[0].elements["calificados"].value+
					    			 "&orden="+document.forms[0].elements["orden"].value+
					    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
					    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
					    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
					    			 "&pagina="+document.forms[0].elements["pagina"].value+
									 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
					    	window.location.href = mUrl;
						}
					else{
						var mUrl="generarReportesEclo.do?method=descargarExportacionTXT"+
					    			 "&unPro="+document.forms[0].elements["unPro"].value+
					    			 "&unEcl="+document.forms[0].elements["unEcl"].value+ 
					    			 "&tambos="+document.forms[0].elements["tambos"].value+
					    			 "&propId="+document.forms[0].elements["propId"].value+
					    			 "&ecloId="+document.forms[0].elements["ecloId"].value+
					    			 "&unEst="+document.forms[0].elements["unEst"].value+
					    			 "&unEstTodos="+document.forms[0].elements["unEstTodos"].value+
    			 					 "&unEstPropios="+document.forms[0].elements["unEstPropios"].value+
					    			 "&animales="+document.forms[0].elements["animales"].value+
					    			 "&calificados="+document.forms[0].elements["calificados"].value+
					    			 "&orden="+document.forms[0].elements["orden"].value+
					    			 "&tamboId="+document.forms[0].elements["tamboId"].value+
					    			 "&eclosOrPropietarios="+document.forms[0].elements["eclosOrPropietarios"].value+
					    			 "&indiceCombo="+document.forms[0].elements["indiceCombo"].value+
					    			 "&pagina="+document.forms[0].elements["pagina"].value+
					    			 "&tipoLogin="+document.forms[0].elements["tipoLogin"].value;
					    window.location.href = mUrl;
						
					}	      	
			}
			
			function seleccionarPaginas(idPagina){
				document.forms[0]["pagina"].value=idPagina;
			}
</script>
</html:form>