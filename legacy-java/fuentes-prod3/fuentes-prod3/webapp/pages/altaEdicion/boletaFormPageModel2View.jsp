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
	 
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
    			<tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif"
					           width=11 align=absMiddle>
					&nbsp;Visualizaci&oacute;n de Califcacion&nbsp;&nbsp;&nbsp;&nbsp;Boleta nº:<c:out value="${calificacionForm.numBoleta}"/>
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
											<c:out value="${calificacionForm.numBoleta}"/>
										</TD>

			</tr>
				
          <tr>
				<td class="celdaLabelSinAlign" align="right">ECLO:</td>
				<td class="celdaLabelSinAlign"  align="left">
							<c:out value="${calificacionForm.idEclo}"/>,<c:out value="${calificacionForm.nombreEclo}"/>
				</td>
				<td class="celdaLabelSinAlign" align="right"></td>
				<td class="celdaLabelSinAlign"  align="left"></td>
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
	    <td class="celdaLabelSinAlign" align="left">
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
						<input name="fechaPar" tabindex="1" type="text" id="fechaPar" value="<c:out value="${calificacionForm.fechaPar}"/>" onclick="popUpCalendar(this, form.fechaPar, 'dd/mm/yyyy');" size="10">
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
				<TD class="celdaLabelSinAlign" align="right">Califcador:</TD>
						<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idCalif}"/> - <c:out value="${calificacionForm.nombreCalif}"/>
						</td>
		</tr>
		
	</table>
	<TR>
            			<td class="TextoNegro">
            			<c:out value="${requestScope.error}"/>	
            				
            			</td>
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
										<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelEst}"/></td>
										<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorEst}"/></td>
								</tr>
						 </table> 
					</td>
					
					<td  class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabel">Estatura</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst1}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabel">Tren&nbsp;Anterior</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst2}"/></td>
								</tr>
								 <tr>
									<td class="celdaLabel">Tamaño</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst3}"/></td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Ancho&nbsp;Pecho</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst4}"/></td>
								</tr>
								 <tr>
									<td class="celdaLabel">Profund.&nbsp;del&nbsp;cuerpo</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst5}"/></td>
								</tr>
								 <tr>
									<td class="celdaLabel">Fortaleza&nbsp;de&nbsp;lomo</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst6}"/></td>
								</tr>
						 </table> 
					</td>
					<td class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabel">Cara&nbsp;torcida</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst1 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst1 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								<tr>
									<td class="celdaLabel">Cabeza&nbsp;indeseable</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst2 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst2 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Retroescápula&nbsp;débil</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst3 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst3 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Línea dorsal&nbsp;débil</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst4 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst4 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Falta&nbsp;de&nbsp;armonía</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst5 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst5 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								 <tr>
									<td class="celdaLabel">Falta&nbsp;arco&nbsp;costal</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst6 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst6 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Lomo&nbsp;bajo</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst7 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst7 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Reg.&nbsp;card.&nbsp;estrecha</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst8 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst8 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								 </tr>
								 <tr>
									<td class="celdaLabel">Frágil</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeEst9 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeEst9 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelGru}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorGru}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
							<tr>
									<td class="celdaLabel">Colocacion&nbsp;isquiones</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGru1}"/></td>
								</tr>
								 <tr>
									<td class="celdaLabel">Separacion&nbsp;isquiones</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGru2}"/></td>
								 </tr>	
								
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Ano&nbsp;adelanatado</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru1 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru1 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;baja</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru2 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru2 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;alta</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru3 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru3 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Ins.&nbsp;Cola&nbsp;adelantada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru4 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru4 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cola&nbsp;torcida</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru5 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru5 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Art.&nbsp;demasiado&nbsp;atrás</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeGru6 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeGru6 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelPat}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorPat}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								
								<tr>
									<td class="celdaLabel">Angulo&nbsp;de&nbsp;pezuñas</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat1}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabel">Profundidad&nbsp;del&nbsp;talon</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat2}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabel">Calidad&nbsp;de&nbsp;hueso</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat3}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabel">C.&nbsp;Patas&nbsp;trs.&nbsp;costado)</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat4}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabel">C.&nbsp;Patas&nbsp;trs.&nbsp;de&nbsp;atras)</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat5}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Cuartillas&nbsp;débiles</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat1 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat1 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Calambres</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat2 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat2 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Garrones&nbsp;toscos</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat3 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat3 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Dedos&nbsp;abiertos</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat4 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat4 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Postura&nbsp;indeseable</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat5 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat5 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Falta&nbsp;de&nbsp;hueso</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat6 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat6 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pez.&nbsp;ant.&nbsp;hacia afuera</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defePat7 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defePat7 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelSis}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorSis}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Profundidad&nbsp;de&nbsp;Ubre</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis1}"/></td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Textura</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis2}"/></td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Ligamento&nbsp;medio</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis3}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Cuarteada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeSis1 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeSis1 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Oblicua</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeSis2 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeSis2 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pesada&nbsp;adelante</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
                                             				      <c:when test="${calificacionForm.boleta.defeSis3 == ''}"></c:when>
                                                       			      <c:when test="${calificacionForm.boleta.defeSis3 == '1'}">L</c:when>
                                                                	      <c:otherwise>G</c:otherwise>
                                                                       	</c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelAnt}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorAnt}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Insercion&nbsp;Anterior</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracAnt1}"/></td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Colocacion&nbsp;del&nbsp;pezon</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracAnt2}"/></td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Largo&nbsp;del&nbsp;pezon</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracAnt3}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Abultada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt1 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt1 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pesada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt2 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt2 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Desbalanceada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt3 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt3 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Corta</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt4 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt4 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezones&nbsp;desviados</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt5 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt5 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezón&nbsp;palmípedo</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt6 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt6 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cuarto&nbsp;ciego</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeAnt7 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeAnt7 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelPos}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorPos}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
									<tr>
									<td width="20%" class="celdaLabel">Altura&nbsp;de&nbsp;insercion</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPos1}"/></td>
									</tr>
								<tr>
									<td width="20%" class="celdaLabel">Ancho&nbsp;de&nbsp;insercion</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPos2}"/></td>
								</tr>
								<tr>
									<td width="20%" class="celdaLabel">Colocacion&nbsp;de&nbsp;pezones</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPos3}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Desbalanceada</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos1 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos1 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Corta</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos2 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos2 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezones&nbsp;desviados</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos3 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos3 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pez.&nbsp;demasiado&nbsp;atrás</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos4 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos4 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Pezón&nbsp;palmípedo</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos5 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos5 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
                                                                        </td>
                                                                       	</td>
								</tr>
								<tr>
									<td class="celdaLabel">Cuarto&nbsp;ciego</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defePos6 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defePos6 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelCar}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorCar}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">Aspecto&nbsp;Lechero</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracCar1}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">Costillas&nbsp;juntas</td>
									<td  class="celdaLabelSinAlign" align="right">
                                         				<c:choose>
				                                              <c:when test="${calificacionForm.boleta.defeCar1 == ''}"></c:when>
                                                      			      <c:when test="${calificacionForm.boleta.defeCar1 == '1'}">L</c:when>
                                                                       	      <c:otherwise>G</c:otherwise>
                                                                        </c:choose>
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
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelPun}"/></td>
									<td  class="celdaLabelSinAlign" align="left">valor:<c:out value="${calificacionForm.boleta.valorPun}"/></td>
									<td  class="celdaLabel">Comentarios:</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.comentarios}"/></td>
									<TD class="celdaLabel">Especial?:</TD>
									<c:choose>
											<c:when test="${calificacionForm.especial == 'true'}">
												<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
											</c:when>
									<c:otherwise>
												<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
									</c:otherwise>
									</c:choose>
			</tr>
				<TD class="celdaLabelSinAlign"  align="left" colspan = "7">Animal de la Boleta</TD>
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
			<td  class="celdaLabel" colspan = "2"><input type="button"  value="Aceptar" class="botones" onclick="setMethod()"></td>
			</tr>
			
</table>

		

<script language="JavaScript">
		function setMethod(){
			if(document.forms[0].elements["method"].value!='view'){
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

    	function buscarPorHBA(){
    		if (isEmpty(document.forms[2].elements["numReg"].value)) {
				alert("Número de Registro es un campo obligatorio");
				return;
			}			

    		document.forms[2].submit();
    	}
    	 initializeMenus();	
	</script> 
	
	</html:form>


