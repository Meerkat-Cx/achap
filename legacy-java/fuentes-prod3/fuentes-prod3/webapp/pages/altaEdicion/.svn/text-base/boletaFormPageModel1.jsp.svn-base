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
					Modelo&nbsp;1&nbsp;Visualizaci&oacute;n de Califcacion&nbsp;&nbsp;&nbsp;&nbsp;Boleta nº:<c:out value="${calificacionForm.numBoleta}"/>
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
													<html:text size="10" title="" name="calificacionForm" property="numBoleta" />
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

				<td class="celdaLabelSinAlign" align="right">RP:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.rp}"/>
				</td>
		   </tr>
		   <tr>
				<td class="celdaLabelSinAlign" align="right">Propietario:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idProp}"/>,<c:out value="${calificacionForm.nombreProp}"/>
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
						<tr>
						<td  class="celdaLabelSinAlign" align="center">PARTES</td>
						<td  class="celdaLabelSinAlign" align="center">CARACTERISTICAS</td>
						<td  class="celdaLabelSinAlign" align="center">DEFECTOS</td>
						</tr>
						</TABLE>
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
						
					 <td width="30%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Carac.Generales</td>
									<td  class="celdaLabelSinAlign" align="left">A</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
						 </table> 
					</td>
					
						 
					

					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinAlign" align="left">Alzada</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGen1}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">tamaño</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGen2}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Estilo</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGen3}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Aspecto&nbsp;Lechero</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGen4}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Tren Anterior</td>
									<td  class="celdaLabelSinAlign" align="left">B</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
						</table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinAlign" align="left">Cabeza</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracTre1}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>1:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre1}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>3:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre3}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>5:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre5}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>7:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre7}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabelSinWidth" width="16%" align="right"><strong>2:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre2}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>4:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre4}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>6:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre6}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>8:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeTre8}"/></td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="2" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Tronco&nbsp;Capacidad</td>
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
					
					<td width="35%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabelSinAlign" align="left">Pecho</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst1}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Lomo</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracEst2}"/></td>
								 </tr>
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>9:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst1}"/></td>
									<td  class="celdaLabelSinWidth" width="12%" align="right"><strong>11:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst3}"/></td>
									<td  class="celdaLabelSinWidth" width="12%" align="right"><strong>14:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst6}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>17:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst9}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>10:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst2}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>12:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst4}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>15:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst7}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>18:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst10}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabelSinWidth" width="16%" align="right"><strong></strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>13:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst5}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>16:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeEst8}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong></strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"></td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel">
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td class="celdaLabelSinAlign" align="left">Amplitud</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGru1}"/></td>
								</tr>
								 <tr>
									<td class="celdaLabelSinAlign" align="left">Colocacion&nbsp;isquiones</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracGru2}"/></td>
								 </tr>	
								
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>19:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru1}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>22:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru4}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>25:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru7}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>28:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru10}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>20:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru2}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>23:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru5}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>26:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru8}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>29:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru11}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>21:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru3}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>24:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru6}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>27:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru9}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>30:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeGru12}"/></td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
					<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td class="celdaLabelSinAlign" align="left">Talones&nbsp;posteriores</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat1}"/></td>
								</tr>
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Hueso</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat2}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Aplomos&nbsp;Posteriores</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracPat3}"/></td>
								</tr>
								
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>31:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat1}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>34:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat4}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>37:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat7}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>39:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat9}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>32:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat2}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>35:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat5}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>38:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat8}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>40:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat10}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>33:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat3}"/></td>
									<td  class="celdaLabelSinWidth" width="12%" align="right"><strong>36:</strong></td>
									<td   class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defePat6}"/></td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td class="celdaLabelSinAlign" align="left">Textura</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis1}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Insercion&nbsp;anterior</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis2}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Insercion&nbsp;Posterior</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis3}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Ligamento&nbsp;Medio</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis4}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Colocacion&nbsp;Pezones&nbsp;Anteriores</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis5}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinAlign" align="left">Colocacion&nbsp;Pezones&nbsp;Posteriores</td>
									<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.boleta.caracSis6}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>41:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis1}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>46:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis6}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>51:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis11}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>56:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis16}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>42:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis2}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>47:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis7}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>52:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis12}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>57:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis17}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>43:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis3}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>48:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis8}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>53:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis13}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>58:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis18}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>44:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis4}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>49:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis9}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>54:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis14}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>59:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis19}"/></td>
								</tr>
								<tr>
									<td class="celdaLabelSinWidth" width="16%" align="right"><strong>45:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis5}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right"><strong>50:</strong></td>
									<td class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis10}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>55:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis15}"/></td>
									<td class="celdaLabelSinWidth" width="12%" align="right" ><strong>60:</strong></td>
									<td  class="celdaLabelSinWidth" width="12%" align="left"><c:out value="${calificacionForm.boleta.defeSis20}"/></td>
								</tr>
						</table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td width="20%" class="celdaLabel">&nbsp;</td>
								</tr>
						</table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td class="celdaLabel">&nbsp;</td>
								</tr>
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
								<tr>
									<td  class="celdaLabelSinAlign" align="left">Aspecto Generales</td>
									<td  class="celdaLabelSinAlign" align="left">C</td>
								 </tr>
								 <tr>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
									<td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
								 </tr>
								<tr>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelAsp}"/></td>
									<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.valorAsp}"/></td>
								</tr>
						 </table> 
					</td>
					<td width="35%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								<tr>
									<td width="20%" class="celdaLabel">&nbsp;</td>
								</tr>
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
				<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td width="20%" class="celdaLabel">&nbsp;</td>
								</tr>
						 </table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%" cellSpacing="0" cellPadding="0" align="center" >
								
						 </table> 
					</td>
					</table>
					</td>
				</tr>
				<tr>
					<td class="celdaLabel" >
				<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					<td width="20%" class="celdaLabel">
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
									<td width="20%" class="celdaLabel">&nbsp;</td>
								</tr>
						</table> 
					</td>
					<td width="45%" class="celdaLabel">
						<table width="100%"  cellSpacing="0" cellPadding="0" align="center" >
								
						 </table> 
					</td>
					</table>
					</td>
				</tr>
</table>
<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
		
			<tr>
									<td  class="celdaLabel">Pje.</td>
									<c:choose>
										<c:when test="${action == 'view'}">
											<td  class="celdaLabelSinAlign" align="left"><c:out value="${calificacionForm.boleta.nivelPun}"/></td>
										</c:when>
											<c:otherwise>
											<td class="celdaInput" >
																<html:select property="boleta.nivelPun"  style="width:50px">
																	<html:option value="EX"  >EX</html:option>
																	<html:option value="MB"  >MB</html:option>
																	<html:option value="BM"  >BM</html:option>
																	<html:option value="B"  >B</html:option>
																	<html:option value="R"  >R</html:option>
																	<html:option value="M"  >M</html:option>
																</html:select>
												</td>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${action == 'view'}">
											<td  class="celdaLabelSinAlign" align="left">valor:<c:out value="${calificacionForm.boleta.valorPun}"/></td>
										</c:when>
											<c:otherwise>
												<TD class="celdaInput">
													<html:text size="1" title="" name="calificacionForm" property="boleta.valorPun" />
												</TD>
										</c:otherwise>
									</c:choose>

										<td  class="celdaLabel">Comentarios:</td>
										<c:choose>
										<c:when test="${action == 'view'}">
											<td  class="celdaLabelSinAlign" align="right"><c:out value="${calificacionForm.comentarios}"/></td>
										</c:when>
											<c:otherwise>
												<TD class="celdaInputSinAlign" align="right">
													<html:text size="40" title="" name="calificacionForm" property="comentarios" />
												</TD>
												</c:otherwise>
										</c:choose>
										<TD class="celdaLabel">Especial?:</TD>
										<c:choose>
										<c:when test="${action == 'view'}">
																	<c:choose>
																		<c:when test="${calificacionForm.especial == 'true'}">
																			<td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
																		</c:when>
																		<c:otherwise>
																			<td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
																		</c:otherwise>
																		</c:choose>
										</c:when>
											<c:otherwise>
												<td class="celdaInput" >
																	<html:checkbox property="especial" value="true" />
																	<input type="hidden" name="especial" value="false">
												</td>
										</c:otherwise>
									</c:choose>
			</tr>
			<c:choose>
						<c:when test="${action == 'view'}">
	                   		<TD class="celdaLabelSinAlign"  align="left" colspan = "7">Animal de la Boleta</TD>
						</c:when>
						<c:otherwise>
							<TD class="celdaLabelSinAlign"  align="left" colspan = "7">Animal a GRABAR</TD>
						</c:otherwise>
					</c:choose>    
			
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
			<c:choose>
						<c:when test="${action == 'view'}">
	                   		<td  class="celdaLabel" colspan = "2"><input type="button" value="Aceptar" class="botones" onclick="setMethod()"></td>	
						</c:when>
						<c:otherwise>
							<td  class="celdaLabel" colspan = "2"><input type="button" value="Grabar Boleta" class="botones" onclick="setMethod()"></td>	
						</c:otherwise>
					</c:choose>        
				
			
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


