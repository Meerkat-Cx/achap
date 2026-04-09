<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/altaCalificacionAction.do" method="post" enctype="multipart/form-data" onsubmit="return validate();">
	<html:javascript formName="calificacionForm"/>
			
	<script language='javascript' src="calendar/popcalendar.js"></script>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	 <html:hidden property="error" value="${requestScope.error}"/>	
	 <html:hidden property="sexo" value="${calificacionForm.sexo}"/>
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
    			<tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					
					<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Calificacion
						</c:when>
						<c:otherwise>
									&nbsp;Modificaci&oacute;n de Califcacion&nbsp;&nbsp;&nbsp;&nbsp;Boleta nº:<c:out value="${calificacionForm.numBoleta}"/>
						</c:otherwise>
					</c:choose>    
					
					    	
		            </td>
            </tr>
            <tr>
        		<c:if test="${calificacionForm.sexo == 'M'}">
        			<td class="SubTitulo" style="background-color: #F7F9F0" align="center" colspan="4">Macho</td>
        		</c:if>
	    		<c:if test="${calificacionForm.sexo == 'H'}">
        			<td class="SubTitulo" style="background-color: #F7F9F0" align="center" colspan="4">Hembra</td>
        		</c:if>
			</tr>
	      <tr>
									<td class="celdaLabelSinAlign" align="right">Boleta nº</td>
										<td class="celdaLabelSinAlign" colspan="3" align="left">
											<c:choose>
												<c:when test="${action == 'add'}">
													<html:text size="10" title="" name="calificacionForm"  property="numBoleta" />
												</c:when>
												<c:otherwise>
													<c:out value="${calificacionForm.numBoleta}"/>
												</c:otherwise>
											</c:choose>      
										</TD>

			</tr>
				
           <tr>
				<td class="celdaLabelSinAlign" align="right">ECLO:</td>
				<td class="celdaLabelSinAlign"  align="left">
					<c:out value="${calificacionForm.idEclo}"/>,<c:out value="${calificacionForm.nombreEclo}"/>
				</td>
				<td class="celdaLabelSinAlign" align="right"></td>
				<td class="celdaLabelSinAlign" align="left"></td>
		   </tr>
		   <tr>
				<td class="celdaLabelSinAlign" align="right">Propietario:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idProp}"/>,<c:out value="${calificacionForm.nombreProp}"/>
				</td>
				<td class="celdaLabelSinAlign" align="right">RP:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.rp}"/>
				</td>
		   </tr>
	<tr>
	    <td class="celdaLabelSinAlign" align="right">Tipo y Nro. de Reg.:</td>
	    <td class="celdaLabelSinAlign" align="left" >
	        <c:out value="${calificacionForm.tipoReg}"/> <c:out value="${calificacionForm.numReg}"/> 
        </td>
        <td class="celdaLabelSinAlign" align="right">Fecha Nac.:</td>
		<td class="celdaLabelSinAlign" align="left">
			<c:out value="${calificacionForm.fechaNac}"/>
		</td>
	</tr>	
			<tr>
				<td class="celdaLabelSinAlign" align="right">Establecimiento:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idEstancia}"/>,<c:out value="${calificacionForm.nombreEstancia}"/>
				</td>
				<td class="celdaLabelSinAlign" align="right"></td>
				<td class="celdaLabelSinAlign" align="right"></td>
		   </tr>
		    <tr>
				<td class="celdaLabelSinAlign" align="right">Tambo:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idEstab}"/>,<c:out value="${calificacionForm.nombreEstab}"/>
				</td>
				<c:if test="${calificacionForm.sexo != 'M'}">
					<td class="celdaLabelSinAlign" align="right">Fecha Parto:</td>
					<td class="celdaInput" align="left">
						<input name="fechaPar" type="text" id="fechaPar" value="<c:out value="${calificacionForm.fechaPar}"/>" onclick="popUpCalendar(this, form.fechaPar, 'dd/mm/yyyy');" size="10">
					</td>
				</c:if>
				<c:if test="${calificacionForm.sexo == 'M'}">
					<td class="celdaLabelSinAlign" align="right"></td>
					<td class="celdaLabelSinAlign" align="right"></td>
				</c:if>
		   </tr>
		    <tr>
				<td class="celdaLabelSinAlign" align="right">Puntaje Ant.:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.puntAnt}"/>
				</td>
				<c:if test="${calificacionForm.sexo != 'M'}">
					<td class="celdaLabelSinAlign" align="right">Nº Parto:</td>
					<td class="celdaLabelSinAlign" align="left">
							<html:text size="3" title="" name="calificacionForm" property="numParto"/>			
					</td>
				</c:if>
				<c:if test="${calificacionForm.sexo == 'M'}">
					<td class="celdaLabelSinAlign" align="right"></td>
					<td class="celdaLabelSinAlign" align="right"></td>
				</c:if>
		   </tr>
		   <tr>
				<TD class="celdaLabelSinAlign" align="right">Fecha:</TD>
						<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.fecha}"/>
						</td>
				
				<TD class="celdaLabelSinAlign" align="right">Calificador:</TD>
						<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idCalif}"/> - <c:out value="${calificacionForm.nombreCalif}"/> 
						</td>
				<td class="celdaLabelSinAlign" align="right"></td>
	    		<td class="celdaLabelSinAlign" align="left">
			</tr>
		
	</table>
	<TR>
            			
            			<c:out value="${requestScope.error}"/>	
            				
            			
            		</TR>
	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td  class="celdaLabel" >
						<table width="100%" cellSpacing="0" cellPadding="2" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Estructura&nbsp;Capacidad</td>
									<td  class="celdaLabelSinAlign" align="left">E</td>
								 </tr>
								<tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr >
									<td class="celdaInput" >
														<html:select property="boleta.nivelEst" tabindex="1" style="width:50px">
															<html:option value=""  ></html:option>
															<html:option value="EX"  >EX</html:option>
															<html:option value="MB"  >MB</html:option>
															<html:option value="BM"  >BM</html:option>
															<html:option value="B"  >B</html:option>
															<html:option value="R"  >R</html:option>
															<html:option value="M"  >M</html:option>
														</html:select>
													</td>	
										<td class="celdaInput" >
																	<html:select property="boleta.valorEst" tabindex="1" style="width:30px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
										
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					
					<td  class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabel">Estatura</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracEst1" tabindex="15" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td  class="celdaLabel">Tren&nbsp;Anterior</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracEst2" tabindex="16" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Tamaño</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.caracEst3" tabindex="17" styleClass="formfields" style="width:40px">
																			<html:option value=""  ></html:option>
																			<html:option value="1"  >1</html:option>
																			<html:option value="2"  >2</html:option>
																			<html:option value="3"  >3</html:option>
																			<html:option value="4"  >4</html:option>
																			<html:option value="5"  >5</html:option>
																			<html:option value="6"  >6</html:option>
																			<html:option value="7"  >7</html:option>
																			<html:option value="8"  >8</html:option>
																			<html:option value="9"  >9</html:option>
																		</html:select>
														</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Ancho&nbsp;Pecho</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracEst4" tabindex="18" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Profund.&nbsp;del&nbsp;cuerpo</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracEst5" tabindex="19" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Fortaleza&nbsp;de&nbsp;lomo</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracEst6" tabindex="20" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								 </tr>
						 </table> 
					</td>
					<td class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabel">Cara&nbsp;torcida</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeEst1" tabindex="38" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cabeza&nbsp;indeseable</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeEst2" tabindex="39" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Retroescápula&nbsp;débil</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst3" tabindex="40" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Línea dorsal&nbsp;débil</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst4" tabindex="41" styleClass="formfields" style="width:40px">
																	<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Falta&nbsp;de&nbsp;armonía</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst5" tabindex="42" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Falta&nbsp;arco&nbsp;costal</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst6" tabindex="43" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Lomo&nbsp;bajo</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst7" tabindex="44" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Reg.&nbsp;card.&nbsp;estrecha</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst8" tabindex="45" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Frágil</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeEst9" tabindex="46" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel">
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Grupa</td>
									<td  class="celdaLabelSinAlign" align="left">F</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																	<html:select property="boleta.nivelGru" tabindex="3" style="width:50px">
																		<html:option value=""  ></html:option>
																		<html:option value="EX"  >EX</html:option>
																		<html:option value="MB"  >MB</html:option>
																		<html:option value="BM"  >BM</html:option>
																		<html:option value="B"  >B</html:option>
																		<html:option value="R"  >R</html:option>
																		<html:option value="M"  >M</html:option>
																	</html:select>
													</td>	
									<td class="celdaInput" >
																	<html:select property="boleta.valorGru" tabindex="4" styleClass="formfields" style="width:30px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
										
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
							<tr>
									<td class="celdaLabel">Colocacion&nbsp;isquiones</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracGru1" tabindex="21" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
							</tr>
								 <tr>
									<td class="celdaLabel">Separacion&nbsp;isquiones</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracGru2" tabindex="22" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>	
								
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Ano&nbsp;adelantado</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeGru1"  tabindex="47" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;baja</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeGru2" tabindex="48" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;alta</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeGru3" tabindex="49" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;adelantada</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeGru4" tabindex="50" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cola&nbsp;torcida</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeGru5" tabindex="51" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Art.&nbsp;demasiado&nbsp;atrás</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeGru6" tabindex="52" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Patas&nbsp;y&nbsp;Pezuñas</td>
									<td  class="celdaLabelSinAlign" align="left">G</td>
								 </tr>
								  <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																		<html:select property="boleta.nivelPat" tabindex="5" style="width:50px">
																			<html:option value=""  ></html:option>
																			<html:option value="EX"  >EX</html:option>
																			<html:option value="MB"  >MB</html:option>
																			<html:option value="BM"  >BM</html:option>
																			<html:option value="B"  >B</html:option>
																			<html:option value="R"  >R</html:option>
																			<html:option value="M"  >M</html:option>
																		</html:select>
														</td>	
									<td class="celdaInput" >
																<html:select property="boleta.valorPat" tabindex="6" styleClass="formfields" style="width:30px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																</html:select>
												</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								
								<tr>
									<td class="celdaLabel">Angulo&nbsp;de&nbsp;pezuñas</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracPat1" tabindex="23" styleClass="formfields" style="width:40px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td  class="celdaLabel">Profundidad&nbsp;del&nbsp;talon</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracPat2" tabindex="24" styleClass="formfields" style="width:40px">
     																	<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td  class="celdaLabel">Calidad&nbsp;de&nbsp;hueso</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracPat3" tabindex="25" styleClass="formfields" style="width:40px">
     																	<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td  class="celdaLabel">C.&nbsp;Patas&nbsp;trs.&nbsp;costado)</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracPat4" tabindex="26" styleClass="formfields" style="width:40px">
	    															<html:option value=""  ></html:option>
    																<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td  class="celdaLabel">C.&nbsp;Patas&nbsp;trs.&nbsp;de&nbsp;atras)</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracPat5" tabindex="27" styleClass="formfields" style="width:40px">
 																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Cuartillas&nbsp;débiles</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat1" tabindex="53" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Calambres</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat2" tabindex="54" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Garrones&nbsp;toscos</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat3" tabindex="55" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Dedos&nbsp;abiertos</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat4" tabindex="56" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Postura&nbsp;indeseable</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat5" tabindex="57" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Falta&nbsp;de&nbsp;hueso</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat6" tabindex="58" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pez.&nbsp;ant.&nbsp;hacia afuera</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePat7" tabindex="59" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Sistema Mamario</td>
									<td  class="celdaLabelSinAlign" align="left">H</td>
								 </tr>
								  <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																<html:select property="boleta.nivelSis" tabindex="7" style="width:50px">
																	<html:option value=""  ></html:option>
																	<html:option value="EX"  >EX</html:option>
																	<html:option value="MB"  >MB</html:option>
																	<html:option value="BM"  >BM</html:option>
																	<html:option value="B"  >B</html:option>
																	<html:option value="R"  >R</html:option>
																	<html:option value="M"  >M</html:option>
																</html:select>
												</td>	
									<td class="celdaInput" >
																<html:select property="boleta.valorSis" tabindex="8" styleClass="formfields" style="width:30px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
									
																</html:select>
												</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Profundidad&nbsp;de&nbsp;Ubre</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracSis1" tabindex="28" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Textura</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracSis2" tabindex="29" styleClass="formfields" style="width:40px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Ligamento&nbsp;medio</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracSis3" tabindex="30" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Cuarteada</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeSis1" tabindex="60" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Oblicua</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeSis2" tabindex="61" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pesada&nbsp;adelante</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeSis3" tabindex="62" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Ubre Anterior</td>
									<td  class="celdaLabelSinAlign" align="left">I</td>
								 </tr>
								  <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																	<html:select property="boleta.nivelAnt" tabindex="9" style="width:50px">
																		<html:option value=""  ></html:option>
																		<html:option value="EX"  >EX</html:option>
																		<html:option value="MB"  >MB</html:option>
																		<html:option value="BM"  >BM</html:option>
																		<html:option value="B"  >B</html:option>
																		<html:option value="R"  >R</html:option>
																		<html:option value="M"  >M</html:option>
																	</html:select>
													</td>
										<td class="celdaInput" >
														<html:select property="boleta.valorAnt"  tabindex="10" styleClass="formfields" style="width:30px">
															<html:option value=""  ></html:option>
															<html:option value="1"  >1</html:option>
															<html:option value="2"  >2</html:option>
															<html:option value="3"  >3</html:option>
							
														</html:select>
														</td>
									</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Insercion&nbsp;Anterior</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracAnt1" tabindex="31" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Colocacion&nbsp;del&nbsp;pezon</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracAnt2" tabindex="32" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Largo&nbsp;del&nbsp;pezon</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.caracAnt3" tabindex="33" styleClass="formfields" style="width:40px">
																		<html:option value=""  ></html:option>
																		<html:option value="1"  >1</html:option>
																		<html:option value="2"  >2</html:option>
																		<html:option value="3"  >3</html:option>
																		<html:option value="4"  >4</html:option>
																		<html:option value="5"  >5</html:option>
																		<html:option value="6"  >6</html:option>
																		<html:option value="7"  >7</html:option>
																		<html:option value="8"  >8</html:option>
																		<html:option value="9"  >9</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Abultada</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeAnt1" tabindex="63" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pesada</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeAnt2" tabindex="64" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Desbalanceada</td>
									<TD class="celdaInputAlignRight">
																		<html:select property="boleta.defeAnt3" tabindex="65" styleClass="formfields" style="width:40px">
																			<html:option value=""  > </html:option>
																			<html:option value="1"  >L</html:option>
																			<html:option value="2"  >G</html:option>
																		</html:select>
														</td>
								</tr>
								<tr>
									<td class="celdaLabel">Corta</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeAnt4" tabindex="66" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezones&nbsp;desviados</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeAnt5" tabindex="67" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezón&nbsp;palmípedo</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeAnt6" tabindex="68" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
										
											
								</tr>
								<tr>
									<td class="celdaLabel">Cuarto&nbsp;ciego</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeAnt7" tabindex="69" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Ubre Psoterior</td>
									<td  class="celdaLabelSinAlign" align="left">J</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																	<html:select property="boleta.nivelPos" tabindex="11" style="width:50px">
																		<html:option value=""  ></html:option>
																		<html:option value="EX"  >EX</html:option>
																		<html:option value="MB"  >MB</html:option>
																		<html:option value="BM"  >BM</html:option>
																		<html:option value="B"  >B</html:option>
																		<html:option value="R"  >R</html:option>
																		<html:option value="M"  >M</html:option>
																	</html:select>
										</td>	
									<td class="celdaInput" >
														<html:select property="boleta.valorPos" tabindex="12" styleClass="formfields" style="width:30px">
															<html:option value=""  ></html:option>
															<html:option value="1"  >1</html:option>
															<html:option value="2"  >2</html:option>
															<html:option value="3"  >3</html:option>
							
														</html:select></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
									<tr>
									<td width="20%" class="celdaLabel">Altura&nbsp;de&nbsp;insercion</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracPos1" tabindex="34" styleClass="formfields" style="width:40px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Ancho&nbsp;de&nbsp;insercion</td>
									<TD class="celdaInputAlignRight">
														<html:select property="boleta.caracPos2" tabindex="35" styleClass="formfields" style="width:40px">
															<html:option value=""  ></html:option>
															<html:option value="1"  >1</html:option>
															<html:option value="2"  >2</html:option>
															<html:option value="3"  >3</html:option>
															<html:option value="4"  >4</html:option>
															<html:option value="5"  >5</html:option>
															<html:option value="6"  >6</html:option>
															<html:option value="7"  >7</html:option>
															<html:option value="8"  >8</html:option>
															<html:option value="9"  >9</html:option>
														</html:select>
										</td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Colocacion&nbsp;de&nbsp;pezones</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracPos3" tabindex="36" styleClass="formfields" style="width:40px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
										
									
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Desbalanceada</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.defePos1" tabindex="70" styleClass="formfields" style="width:40px">
																	<html:option value=""  > </html:option>
																	<html:option value="1"  >L</html:option>
																	<html:option value="2"  >G</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td class="celdaLabel">Corta</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePos2" tabindex="71" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezones&nbsp;desviados</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.defePos3" tabindex="72" styleClass="formfields" style="width:40px">
																	<html:option value=""  > </html:option>
																	<html:option value="1"  >L</html:option>
																	<html:option value="2"  >G</html:option>
																</html:select>
												</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pez.&nbsp;demasiado&nbsp;atrás</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePos4" tabindex="73" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezón&nbsp;palmípedo</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defePos5" tabindex="74" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cuarto&nbsp;ciego</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.defePos6" tabindex="75" styleClass="formfields" style="width:40px">
																	<html:option value=""  > </html:option>
																	<html:option value="1"  >L</html:option>
																	<html:option value="2"  >G</html:option>
																</html:select>
												</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="30%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Carac.Lecheras</td>
									<td  class="celdaLabelSinAlign" align="left">D</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td class="celdaInput" >
																	<html:select property="boleta.nivelCar" tabindex="13"  style="width:50px">
																		<html:option value=""  ></html:option>
																		<html:option value="EX"  >EX</html:option>
																		<html:option value="MB"  >MB</html:option>
																		<html:option value="BM"  >BM</html:option>
																		<html:option value="B"  >B</html:option>
																		<html:option value="R"  >R</html:option>
																		<html:option value="M"  >M</html:option>
																	</html:select>
													</td>
									<td class="celdaInput" >
														<html:select property="boleta.valorCar" tabindex="14" styleClass="formfields" style="width:30px">
															<html:option value=""  ></html:option>
															<html:option value="1"  >1</html:option>
															<html:option value="2"  >2</html:option>
															<html:option value="3"  >3</html:option>
							
														</html:select></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Aspecto&nbsp;Lechero</td>
									<TD class="celdaInputAlignRight">
																<html:select property="boleta.caracCar1" tabindex="37" styleClass="formfields" style="width:40px">
																	<html:option value=""  ></html:option>
																	<html:option value="1"  >1</html:option>
																	<html:option value="2"  >2</html:option>
																	<html:option value="3"  >3</html:option>
																	<html:option value="4"  >4</html:option>
																	<html:option value="5"  >5</html:option>
																	<html:option value="6"  >6</html:option>
																	<html:option value="7"  >7</html:option>
																	<html:option value="8"  >8</html:option>
																	<html:option value="9"  >9</html:option>
																</html:select>
												</td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Costillas&nbsp;juntas</td>
									<TD class="celdaInputAlignRight">
																	<html:select property="boleta.defeCar1" tabindex="76" styleClass="formfields" style="width:40px">
																		<html:option value=""  > </html:option>
																		<html:option value="1"  >L</html:option>
																		<html:option value="2"  >G</html:option>
																	</html:select>
													</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				

</table>
<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
		
			<tr>
									<td  class="celdaLabel">Pje.</td>
									<td class="celdaInput" >
																<html:select property="boleta.nivelPun" tabindex="77" style="width:50px">
																	<html:option value=""  ></html:option>
																	<html:option value="EX"  >EX</html:option>
																	<html:option value="MB"  >MB</html:option>
																	<html:option value="BM"  >BM</html:option>
																	<html:option value="B"  >B</html:option>
																	<html:option value="R"  >R</html:option>
																	<html:option value="M"  >M</html:option>
																</html:select>
												</td>
										<TD class="celdaInput">
													<html:text size="1" title="" name="calificacionForm" tabindex="78" property="boleta.valorPun" />
												</TD>
										
									<td  class="celdaLabel">Comentarios:</td>
									<TD class="celdaInputSinAlign" align="right">
													<html:text size="40" title="" name="calificacionForm" tabindex="79" property="comentarios" />
												</TD>
												
										
										<TD class="celdaLabel">Especial?:</TD>
										<td class="celdaInput" >
																	<html:checkbox property="especial" tabindex="80" value="true" />
																	<input type="hidden" name="especial" tabindex="80" value="false">
												</td>
			</tr>
			
						<TD class="celdaLabelSinAlign"  align="left" colspan = "7">Animal a GRABAR</TD>
			<tr>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2"  align="right">ID:</td>
				<td class="celdaLabelSinAlign" colspan = "1" align="left">
							<c:out value="${calificacionForm.idAninal}"/>
				</td>
				<td class="celdaLabelSinAlign"  align="right">RP:</td>
				<td class="celdaLabelSinAlign" colspan="3"  align="left">
							<c:out value="${calificacionForm.rp}"/>
				</td>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">ECLO:</td>
				<td class="celdaLabelSinAlign"  colspan = "5" align="left">
							<c:out value="${calificacionForm.idEcloAn}"/>
				</td>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">Propietario:</td>
				<td class="celdaLabelSinAlign" colspan = "5" align="left">
							<c:out value="${calificacionForm.idPropAn}"/>
				</td>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">Establecimiento:</td>
				<td class="celdaLabelSinAlign" colspan = "5" align="left">
							<c:out value="${calificacionForm.idEstancia}"/>
				</td>
		   </tr>
		<tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">Tambo:</td>
				<td class="celdaLabelSinAlign" colspan = "3"align="left">
							<c:out value="${calificacionForm.idEstabAn}"/>
				</td>
				<td  class="celdaLabel" colspan = "2"><input type="button"  tabindex="81" value="Grabar Boleta" class="botones" onclick="setMethod()"></td>	
				</tr>
				<html:hidden name="calificacionForm" property="sexo"/>
			
</table>

		

<script language="JavaScript">

		function setMethod(){
			if(document.forms[0].elements["method"].value!='view'){
				if(document.forms[0].elements["method"].value!='update'){
					
					if (isEmpty(document.forms[0].elements["numBoleta"].value)) {
						alert("el valor de numero de boleta es un campo obligatorio");
						return;
					}
					if (!isPosInteger(document.forms[0].elements["numBoleta"].value)) {
						alert("el valor de numero de boleta debe ser un número");
						return;
					}
					if ((document.forms[0].elements["sexo"].value)== "H") {
						
					if (isEmpty(document.forms[0].elements["fechaPar"].value)) {
						alert("el valor de fecha de Parto es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["numParto"].value)) {
						alert("El valor del numero de parto es un campo obligatorio");
						return;
					}
					if (!isPosInteger(document.forms[0].elements["numParto"].value)) {
						alert("El valor del numero de parto debe ser un número");
						return;
					}
					}
				}
					//Sebastian Garcia
					if (isEmpty(document.forms[0].elements["boleta.nivelEst"].value)) {
						alert("el nivel de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorEst"].value)) {
						alert("el valor de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst1"].value)) {
						alert("la estatura de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst2"].value)) {
						alert("el tren anterior de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst3"].value)) {
						alert("el tamaño de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst4"].value)) {
						alert("el ancho pecho de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst5"].value)) {
						alert("la profund. del cuerpo de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracEst6"].value)) {
						alert("la fortaleza de lomo de Estructura Capacidad es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelGru"].value)) {
						alert("el nivel de Grupa es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorGru"].value)) {
						alert("el valor de Grupa es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracGru1"].value)) {
						alert("la colocacion isquiones de Grupa es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracGru2"].value)) {
						alert("la separacion isquiones de Grupa es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelPat"].value)) {
						alert("el nivel de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorPat"].value)) {
						alert("el valor de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPat1"].value)) {
						alert("el angulo de pezuñas de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPat2"].value)) {
						alert("la profundidad del talon de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPat3"].value)) {
						alert("la calidad de hueso de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPat4"].value)) {
						alert("la C. Patas trs. costado de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPat5"].value)) {
						alert("la C. Patas trs. de atras de Patas y Pezuñas es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelSis"].value)) {
						alert("el nivel del Sistema Mamario es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorSis"].value)) {
						alert("el valor del Sistema Mamario es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracSis1"].value)) {
						alert("la profundidad de ubre del Sistema Mamario es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracSis2"].value)) {
						alert("la textura del Sistema Mamario es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracSis3"].value)) {
						alert("el ligamento medio del Sistema Mamario es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelAnt"].value)) {
						alert("el nivel del Ubre Anterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorAnt"].value)) {
						alert("el valor del Ubre Anterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracAnt1"].value)) {
						alert("la insercion anterior del Ubre Anterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracAnt2"].value)) {
						alert("la colocacion del pezon del Ubre Anterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracAnt3"].value)) {
						alert("el largo del pezon del Ubre Anterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelPos"].value)) {
						alert("el nivel del Ubre Psoterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorPos"].value)) {
						alert("el valor del Ubre Psoterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPos1"].value)) {
						alert("la altura de insercion del Ubre Psoterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPos2"].value)) {
						alert("el ancho de insercion del Ubre Psoterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracPos3"].value)) {
						alert("la colocacion de pezones del Ubre Psoterior es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelCar"].value)) {
						alert("el nivel de Carac.Lecheras es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.valorCar"].value)) {
						alert("el valor de Carac.Lecheras es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.caracCar1"].value)) {
						alert("el aspecto lechero de Carac.Lecheras es un campo obligatorio");
						return;
					}
					if (isEmpty(document.forms[0].elements["boleta.nivelPun"].value)) {
						alert("el nivel del Puntaje es un campo obligatorio");
						return;
					}
					//

					if (isEmpty(document.forms[0].elements["boleta.valorPun"].value)) {
						alert("el valor del puntaje es un campo obligatorio");
						return;
					}
					if (!isPosInteger(document.forms[0].elements["boleta.valorPun"].value)) {
						alert("el valor del puntaje debe ser un número");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='EX')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,90,90))){
						alert("el valor del puntaje debe ser de 90");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='MB')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,85,89))){
						alert("el valor del puntaje debe estar entre 85 y 89");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='BM')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,80,84))){
						alert("el valor del puntaje debe estar entre 80 y 84");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='B')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,75,79))){
						alert("el valor del puntaje debe estar entre 75 y 79");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='R')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,65,74))){
						alert("el valor del puntaje debe estar entre 65 y 74");
						return;
					}
					if((document.forms[0].elements["boleta.nivelPun"].value=='M')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,40,64))){
						alert("el valor del puntaje debe estar entre 40 y 64");
						return;
					}
					
				}
			
			document.forms[0].submit();
			
			

    		
    	}
		function noEstaEntre(valor,primero,segundo){
			if((valor >= primero)&&(valor <= segundo))
				return false;
			return true;
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
 initializeMenus();	
   </script> 
	
	</html:form>
