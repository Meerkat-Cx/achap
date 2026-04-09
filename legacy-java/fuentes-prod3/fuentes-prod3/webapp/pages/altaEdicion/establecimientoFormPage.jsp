<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html:form action="/editarEstablecimientoValidate.do" method="post" onsubmit="return validate();"> 
<script language='javascript' src="calendar/popcalendar.js"></script>
	<html:hidden name="establecimientoForm" property="idEst"/>
	<html:hidden name="establecimientoForm" property="idEstancia"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	
	<html:javascript formName="establecimientoForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            	
            		<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Tambo
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${action == 'update'}">
			                   		&nbsp;Modificaci&oacute;n de Tambo
								</c:when>
								<c:otherwise>
									&nbsp;Datos de Tambo
								</c:otherwise>
							</c:choose>        
							
						</c:otherwise>
					</c:choose>            	
            	</font>
            </td>
            <td><div align=right></div></td>
        </tr>
        <tr> 
        	<td class=texto4 colSpan=2>
            	<div align=right> 
                	<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                    	<tr> 
                        	<td width="30%" bgColor=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2></td>
                            <td width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></td>
                        </tr>
                    </table>
               </div>
            </td>
        </tr>
	</table>
	
	<br/>	

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Tambo Sicel 3
            </td>
        </tr>
        
        <tr>
        	<td colspan="2">
        	<html:messages id="message1" message="true" property="uniqueConstraint"/>          	
			<c:if test="${message1!='' and message1!=null}">	
				<table width="90%" border="0" align="center" class="texto_error">
				<tr >				
					<td class="TextoError" colspan="3">
						<c:out value="${message1}"/>
					</td>
				</tr>
	         	</table>
	          	<br>
			</c:if>
        	</td>
        </tr>
        
        <tr>
			<c:choose>
					<c:when test="${action == 'add'}">
						<td class="celdaLabel" colspan="2"></td>
					</c:when>
					<c:otherwise>
						<td class="celdaLabel">Id: </td>
						<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.idEst}"/>
						</td>
						<html:hidden property="idEst"/>
					</c:otherwise>
				</c:choose>
				</br>
		   
		</tr>
       
		<tr>
			<td class="celdaLabel">
				Nombre: <span class="required">*</span>
			</td>
			<c:choose>
					<c:when test="${action == 'view'}">
			                <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.nombre}"/>
							</td>
					</c:when>
							<c:otherwise>
									<td class="celdaInput" colspan="2">
									<html:text size="45" name="establecimientoForm" property="nombre" styleClass="Input100porc"/>
									</td>
							</c:otherwise>
			</c:choose> 				
		</tr>
		<TR>
			<TD class="celdaLabel">
				Cuit/Cuil: 
			</TD>
			<TD class="celdaInput" colspan="2">
				<html:text size="45" name="establecimientoForm" property="cuit" styleClass="Input100porc"/>
			</TD>		
		</TR>	
		 <tr>
			<td class="celdaLabel">
				Cuig: 				
			</td>
			<td class="celdaInput" colspan="2">				
						<html:text size="45" name="establecimientoForm" property="cuig" styleClass="Input100porc"/>					
		    </td>
		</tr>	
		<TR>
			<TD class="celdaLabel">
				Renspa: 
			</TD>
			<TD class="celdaInput" colspan="2">
				<html:text size="45" name="establecimientoForm" property="renspa" styleClass="Input100porc"/>
			</td>		       
		</TR>
		<tr>
			<td class="celdaLabel">
				Activo?: <span class="required">*</span>
			</td>
			
			<html:hidden name="establecimientoForm" property="activoEclo"/>
			<html:hidden name="establecimientoForm" property="activoPropietario"/>
			<html:hidden name="establecimientoForm" property="activoEstancia"/>
			<c:choose>
					<c:when test="${establecimientoForm.activoEstancia == 'false'}">
							<td class="celdaLabelSinAlign" colspan="2" align="left">Inactivo por la baja del Establecimiento a la que pertenece </td>
					</c:when>
					<c:otherwise>
						<c:choose>
								<c:when test="${establecimientoForm.activoEclo == 'false'}">
									<td class="celdaLabelSinAlign" colspan="2" align="left">Inactivo por la baja de la entidad de control lechero al que pertenece </td>
								</c:when>
								<c:otherwise>
												<c:choose>
													<c:when test="${establecimientoForm.activoPropietario == 'false'}">
														<td class="celdaLabelSinAlign" colspan="2" align="left">Inactivo por la baja de su propietario</td>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${action == 'view'}">
																<c:choose>
																		<c:when test="${establecimientoForm.activo == 'true'}">
																			<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
																		</c:when>
																		<c:otherwise>
																			<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
																		</c:otherwise>
																 </c:choose>
															</c:when>
															<c:otherwise>
																	<td class="celdaInput" colspan="2">
																	<html:checkbox property="activo" value="true" />
													  				<input type="hidden" name="activo" value="false">
																	</td>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
						  		</c:otherwise>
						</c:choose>
			      </c:otherwise>
			</c:choose>
						
		</tr>
		<tr>
			<td class="celdaLabel" align="center"><strong>Lugares de Contacto:</strong></td>
			<td class="celdaLabel" align="center"></td>
			<td class="celdaLabel" align="center"></td>
		</tr> 		
		<tr>
			<td colspan="3">		
				<display:table name="ubicaciones" align="center" class="its" id="ubicacion" >				
						<display:column align="left"  property="nombre" title="Nombre"/>
						<display:column align="left"  property="provinciaRegion" title="Provincia Region"/>
						<display:column align="left"  property="ciudad" title="Ciudad"/>
						<display:column align="left"  property="pais" title="Pais"/>
						<display:column align="left"  property="direccion" title="Direccion"/>
						<display:column align="left"  property="codigoPostal" title="Codigo Postal"/>
						<display:column align="left"  property="mail" title="Mail"/>
						<display:column align="left"  property="telefono" title="Telefono"/>   					        
						<display:setProperty name="basic.msg.empty_list">
							<h1 class="TextoVerde" align="center"><bean:message key="displayTag.basic.msg.empty_list" /></h1>
						</display:setProperty>
				</display:table>
			</td>
		</tr>	
		<tr>
			<td class="celdaLabel" align="center"></td>
			<td class="celdaLabel" align="center"></td>
			<td class="celdaLabel" align="center"></td>
		</tr>
		
		<tr>
			
			<td class="celdaLabel">
				Nombre&nbsp;Establecimiento: <span class="required">*</span>
			</td>
			
			<c:choose>
					<c:when test="${action == 'view'}">
			                <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.establecimiento}"/>
							</td>
					</c:when>
							<c:otherwise>
									<td class="celdaInput">
												<html:text size="45" name="establecimientoForm" property="estancia" readonly="true" styleClass="Input100porc"/>
												<br/>
		    							</td>
		    						<td class="celdaLabel">
		    						<input type="button" value="Buscar Establecimiento" class="botones" onclick="setMethod('initBuscarEstancia')">            
									</td>
							</c:otherwise>
			</c:choose> 
		</tr>
		<tr>
			<td class="celdaLabel">Id Eclo: </td>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.idEclo}"/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel">Nombre Eclo: <span class="required">*</span></td>
			
			<html:hidden property="s1Eclo"/>
			<c:choose>
					<c:when test="${action == 'view'}">
			                <td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.nombreContactoEclo}"/>
							</td>
					</c:when>
							<c:otherwise>
									<td class="celdaInput">
												<html:text size="45" name="establecimientoForm" property="nombreContactoEclo" readonly="true" styleClass="Input100porc"/>
												<br/>
		    							</td>
		    						<td class="celdaLabel">
		    						<input type="button" value="Buscar Eclo" class="botones" onclick="setMethod('initBuscarEclo')">            
									</td>
							</c:otherwise>
			</c:choose> 
		</tr>
		<tr>
			<td class="celdaLabel">Id Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2"  align="left">
									<c:out value="${establecimientoForm.idPropietario}"/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel">Nombre&nbsp;Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.nombreContactoPropietario}"/>
			</td>
			<html:hidden property="s1Propietario"/>
		</tr>
		<tr>
			<td class="celdaLabel">Metodo de Control</td>
			<td class="celdaLabelSinAlign" align="left" >
				<html:select property="metodoControl" styleClass="Input100porc">
					<html:option value="">Seleccione</html:option>
					<html:options collection="metodos" property="codigo" labelProperty="codigo" />
				</html:select>	
			</td>
				<td class="celdaLabelSinAlign">
			</td>		
		</tr>
		<tr>
			<td class="celdaLabel">Centro de Computo </td>
			<td class="celdaLabelSinAlign" align="left" >
				<html:select property="idCentro" styleClass="Input100porc">
					<html:options collection="centros" property="id" labelProperty="idNombre" />
				</html:select>	
			</td>
				<td class="celdaLabelSinAlign">
			</td>		
		</tr>
		<tr>
        	
			<c:if test="${action == 'add'}">	
				<td class="celdaLabel">Fecha de Alta</td>
			</c:if>
			<c:if test="${action == 'update'}">	
				<td class="celdaLabel">Fecha de Modificación</td>
			</c:if>
				<td class="celdaInput">
					<input name="fechaLog" type="text" id="fechaLog" value="<c:out value="${establecimientoForm.fechaLog}"/>" onclick="popUpCalendar(this, form.fechaLog, 'dd/mm/yyyy');" size="10">
								<br/>
		    	</td>
			<td class="celdaLabelSinAlign">
			</td>		
        	
        </tr>
        <tr>
        
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Log de cambios
            </td>
        
        </tr>
        <tr>
        <TD colspan="3" align="center"> 
         						
		  <display:table name="bitacora" width="90%" class="its" id="item" scope="request">        
		    	    	<display:column align="left" title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy - HH:mm" value="${item.fecha}" />
	    	 			</display:column>    
			  	      	<display:column  title="Cambio">			  	    
							<c:out value="${item.accion}"/>
			  	      </display:column>
			  	      <display:column title="M.C. Anterior">
			  	      		<c:out value="${item.metodoControl.codigo}"/>
			  	      </display:column>
			  	      <display:column title="M.C. Asignado">
			  	      		<c:out value="${item.metodoControlNuevo.codigo}"/>
			  	      </display:column>
			  	      <display:column property="usuario.username" title="Usuario responsable"/>
			  	      <display:column title="Id Eclo">
			  	      	<c:if test="${item.eclo != null}">
			  	      		<c:out value="${item.eclo.id}"/>
			  	      	</c:if>
			  	      </display:column>
			  	      
		 	       <display:setProperty name="basic.msg.empty_list" >
		 	       <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">No hay Logs
            			</td>
            		</TR>
            	</table>
		 	       </display:setProperty>
		    	</display:table>

 
		
		 </TD>
        </tr>
        
        		<tr> 
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="3">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de Sicel 1
            </td>
        </tr>
		<tr>
			<td class="celdaLabel">Id Eclo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Eclo}"/>
			</td>
			<html:hidden property="s1Eclo"/>
		</tr>
		<tr>
			<td class="celdaLabel">Id Propietario: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Propietario}"/>
			</td>
			<html:hidden property="s1Propietario"/>
		</tr>
		<tr>
			<td class="celdaLabel">Id Tambo: </td>
			<td class="celdaLabelSinAlign" colspan="2" align="left">
							<c:out value="${establecimientoForm.s1Tambo}"/>
			</td>
			<html:hidden property="s1Tambo"/>
		</tr>
		
		<tr>
		</tr>
		
		
	</table>

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<input type="button" value="Aceptar" class="botones" onclick="verificar('true')">            
				<input type="button" value="Cancelar" class="botones" onclick="verificar('false')">
			</td>
		</tr>	
	</table>
</html:form>

<script>
	function verificar(cancelar){
		var a = document.forms[0].metodoControl;
		if (cancelar=='false'){
			var mUrl = "editarEstablecimientoValidate.do?method=init";
		    window.location.href = mUrl;
		}
		else
			if (a.value == ''){
				alert('Debe asignar un Metodo Control');
				return false;
			}
			else
				var todobien= validateEstablecimientoForm(document.forms[0]);
				if (todobien){
					document.forms[0].submit();
				}
					
	}
	function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    }
    
    initializeMenus();
</script>


