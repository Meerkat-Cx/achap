<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/generarReporteEventosAction.do"  onsubmit="return validate();">
	<script language='javascript' src="calendar/popcalendar.js"></script>
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="descargarReporteEventos">
		 <input type="hidden" name="unEst" value="si"/>
		 
		  <html:hidden property="inicio" value="${requestScope.inicio}"/>	
		 <html:hidden property="error" value="${requestScope.error}"/>	
		 <html:hidden property="mostrar" value="${requestScope.mostrar}"/>	
		  <html:hidden property="todas" value="${requestScope.todas}"/>
		  <html:hidden property="mostrarDescarga" value="${requestScope.mostrarDescarga}"/>	
		 <html:javascript formName="reporteEventosForm"/>

		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Reporte de padrones</FONT></TD>
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
   	
   	<table width="100%" class="bordeGris"  cellSpacing="0" cellPadding="2" align="center" >
   		
		<html:hidden name="reporteEventosForm" property="idEclos" styleId="ecl"/>
		<tr>
		 <td class="celdaLabelSinAlign" align="rigth"  colspan="3"><strong>El Reporte de Eventos es en base a:&nbsp;</strong></td>
		</tr>
		 <tr>
				           <td class="celdaLabel" align="rigth"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<c:choose>
								<c:when test="${requestScope.todas == 'no' }">
									<input type="radio" name="checkEclo" onclick="mostrarEclo()"  checked >una ECLO o un conjunto de ECLOS. </td>
								</c:when>
							<c:otherwise>
										<input type="radio" name="checkEclo" onclick="mostrarEclo()" >una ECLO o un conjunto de ECLOS. </td>
										</c:otherwise>
									</c:choose>
							 <TR>
								<c:choose>
								<c:when test="${requestScope.inicio == 'si' }">
												<td class="celdaLabelSinAlign" colspan="2" align="right" ></td>
										</c:when>
										<c:otherwise>
										<TD class="celdaLabel" width="20%" align="right" >Eclos	 </TD>
												<td>
												<table   class="bordeGris" align="center" cellpadding="10" cellspacing="0">
														<tr>
															<td width="4%" class="TextoVerde">Id</td>
															<td width="20%" class="TextoVerde">nombre</td>
														 </tr>
										                  <tr>
										                 <c:forEach items="${reporteEventosForm.listaEclos}" var="currentEclo">
																<tr>
																 	<td  class="TextoVerde" align="left"><bean:write name="currentEclo" property="id"/></td>
																	<td  class="TextoVerde" align="left"><bean:write name="currentEclo" property="nombreContacto"/></td>
																</tr>
																<tr>
																</tr>
														</c:forEach>
														
									        			</tr>       
												</table>
											</td>
										</c:otherwise>
									</c:choose>
									<td class="celdaLabelSinAlign"  align="center" width="20%">
								 <input align="middle"  type="button"  value="Buscar Eclos" class="botones" name="buscarE"  onClick="setBuscar()" >            
					           
					            </TD>
		 				</tr>
		</tr>
		<tr>
							<td class="celdaLabel" align="rigth"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<c:choose>
								<c:when test="${requestScope.todas == 'no' }">
										<input type="radio" name="checkEclo" onclick="mostrarEclo()" >todas los Eclos</td>		
										</c:when>
							<c:otherwise>
										<input type="radio" name="checkEclo" onclick="mostrarEclo()" checked >todas los Eclos</td>		
										</c:otherwise>
									</c:choose>						
		</TR>
		
    	<tr>
    	<td class="celdaLabel"  colspan="3" align="rigth"></strong></td>
      		</tr>
     
     
              <c:choose>
					<c:when test="${requestScope.mostrar == 'no' }">
							<td class="texto_simple"></td>
					</c:when>
					<c:otherwise>
						<tr>
				            <td class="celdaLabelSinAlign" align="rigth"><strong>Eventos de:&nbsp;</strong></td>
							<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<input type="radio" name="checkEs" onclick="mostrarEst()"  checked >un tambo </td>
							</tr>
							<tr>
							<td class="celdaLabel" align="rigth"></strong></td>
							<td class="celdaLabelSinAlign" colspan="2" align="rigth">
							<input type="radio" name="checkEs" onclick="mostrarEst()"  >todos los tambos</td>								
						</TR>
						 <TR>
								<TD class="celdaLabel" align="center" >Identificador del Tambo:</TD>
								<TD class="celdaLabel" align="center">
								<html:hidden name="reporteEventosForm" property="idEst"/>
								<div id="establecimientoVisible" align="center">
									
					                 <c:out value="${requestScope.idEs}"/>
					              	 <c:out value="${requestScope.nombreEstablecimiento}"/>
						        </div>
						        </td>
						         <td class="celdaLabelSinAlign"  align="center">
						            <input type="button" align="middle" value="Buscar Tambo" class="botones" name="buscar"  onClick="window.location='generarReporteEventosAction.do?method=initBuscarEstablecimiento' " >            
					           </TD>
					
					     </tr>
					           
					          
						
						
					</c:otherwise>	            		       	
				</c:choose>
        <tr>
    	<td class="celdaLabel"  colspan="3" align="rigth"></strong></td>
      		</tr>     
			
       <c:choose>
					<c:when test="${requestScope.mostrarDescarga == 'si'}">
							
					              
					              <TR>
					             	 <TD class="celdaLabel" align="center" >Eventos entre las fechas:  </TD>
					             	<td class="celdaLabelSinAlign" align="center" > 			             		
					             		Desde <input name="fechaInic" type="text" id="fechaInic" onclick="popUpCalendar(this, form.fechaInic, 'dd/mm/yyyy');" size="10">
					             	</td>
								 
					    			
									<TD  class="celdaLabel" ></TD>	
								<!-- <TD  class="TextoNegro" ><a href="javascript:setMethod('pdf')"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	-->
								</TR>
								<tr>
								<TD class="celdaLabel" align="center" ></TD>
					             	<td class="celdaLabelSinAlign" align="center" > 		             		
					             	 Hasta <input name="fechaFin" type="text" id="fechaFin" onclick="popUpCalendar(this, form.fechaFin, 'dd/mm/yyyy');" size="10">		
					             	</td>
								 
					    			
									<TD  class="celdaLabelSinAlign" align="center">[Ver Reporte]<a href="javascript:setMethod('descargarReporteEventos','<c:out value="${requestScope.inicio}"/>')"><img border="0" src="img/PDF.jpg" title="Descargar"/></a></TD>	
								
								</tr>
						
					</c:when>
					<c:otherwise>
					</c:otherwise>	            		       	
				</c:choose>
	<c:out value="${requestScope.error}"/>
   	
</table>	 
	 
	<script language="JavaScript">
	
		
	function setBuscar(){
    	
    	var mUrl="generarReporteEventosAction.do?method=initBuscarEclos" ;
    	window.location.href = mUrl;
    }
			
   	function setMethod(valor,vale){
   		
   		
   		if(vale=='si' && document.forms[0].checkEclo[0].checked)
   			alert("Debe Seleccionar una eclo")
	   	else{
			   		if((document.forms[0].idEst != null)&&(document.forms[0].idEst.value == '')&&(document.forms[0].unEst.value == 'si')){
			   			alert("Debe Seleccionar un Tambo");
			   			}
			   		else{
			   			if(document.forms[0].fechaInic.value == '')
			   					alert("La Fecha de inicio no puede ser vacia");
			   			else{
			   				if(document.forms[0].fechaFin.value == '')
			   					alert("La Fecha de Fin no puede ser vacia");
			   				else{	
					  			document.forms[0].method.value = valor;
			    				document.forms[0].submit();
	    						}
			    			}
			    		}
	    		
	    	}	
    	}
    	
    
		function mostrarEst() {
				
				if (document.forms[0].checkEs[0].checked) {
					document.forms[0].buscar.disabled = false;
					document.forms[0].unEst.value = 'si';
				}
				else {
					document.forms[0].buscar.disabled = true;
					document.forms[0].unEst.value = 'no';
					var elementoVisible = document.getElementById("establecimientoVisible");					
					var resultNode = document.createTextNode("--");
					var oldChild = elementoVisible.childNodes[0];
					
					if(oldChild != null)
						elementoVisible.replaceChild(resultNode, oldChild);
					
				}
			
		}
		function mostrarEclo() {
				
				if (document.forms[0].checkEclo[0].checked) {
				}
				else {
					var mUrl="generarReporteEventosAction.do?method=seleccionarEclos&todas=si" ;
    				window.location.href = mUrl;
					
				}
			
		}
    	initializeMenus();
	</script>    
</html:form>