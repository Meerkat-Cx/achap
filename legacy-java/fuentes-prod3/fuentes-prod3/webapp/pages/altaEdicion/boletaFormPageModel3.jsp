<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/altaCalificacionAction.do" method="post" focus="numBoleta" enctype="multipart/form-data" onsubmit="return validate();">
   <html:javascript formName="calificacionForm"/>
   <input type="hidden" name="method" value="<c:out value="${action}"/>">
	<html:hidden property="error" value="${requestScope.error}"/>
	<html:hidden property="error" value="${requestScope.errorFechaParto}"/>
<script language='javascript' src="calendar/popcalendar.js"></script>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
        <tr>
          <TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4">
		        <IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
              <c:choose>
                        <c:when test="${action == 'add'}">
                        	<c:if test="${calificacionForm.sexo == 'M'}">&nbsp;Alta de Calificación (Macho)</c:if>
	    					<c:if test="${calificacionForm.sexo == 'H'}">&nbsp;Alta de Calificación (Hembra)</c:if>
					                
                        </c:when>
				            <c:otherwise>
                                     &nbsp;Modificaci&oacute;n de Califcación&nbsp;&nbsp;&nbsp;&nbsp;Boleta nº:<c:out value="${calificacionForm.numBoleta}"/>
			               </c:otherwise>
              </c:choose>
          </td>
		</tr>
		<tr>
			<td class="SubTitulo" style="background-color: #F7F9F0" align="center" colspan="4">Datos de la Calificación</td>
		</tr>
		<c:if test="${requestScope.errorFechaParto != null && requestScope.errorFechaParto != ''}">
			<tr>
				<td class="SubTitulo" style="background-color: #F7F9F0;color: red" align="center" colspan="4">
					<strong><c:out value="${requestScope.errorFechaParto}"/></strong>
				</td>
			</tr>
		</c:if>
	    <tr>
			<td class="celdaLabelSinAlign" align="right">Boleta nº</td>
			<td class="celdaLabelSinAlign" colspan="3" align="left">
				<c:choose>
					<c:when test="${action == 'add'}">
						<html:text size="10" title="" name="calificacionForm" tabindex="1" property="numBoleta" />
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
							<input name="fechaPar" tabindex="1" type="text" id="fechaPar" value="<c:out value="${calificacionForm.fechaPar}"/>" onclick="popUpCalendar(this, form.fechaPar, 'dd/mm/yyyy');" size="10">
					</td>
					
				</c:if>
				<c:if test="${calificacionForm.sexo == 'M'}">
					<td class="celdaLabelSinAlign" align="right"></td>
					<td class="celdaLabelSinAlign" align="right"></td>
				</c:if>
		</tr>
		<tr>
	    <td class="celdaLabelSinAlign" align="right">Tipo y Nro. de Reg.:</td>
	    <td class="celdaLabelSinAlign" align="left">
	    	<c:out value="${calificacionForm.tipoReg}"/> <c:out value="${calificacionForm.numReg}"/>
        </td>
        <td class="celdaLabelSinAlign" align="right"></td>
		<td class="celdaLabelSinAlign" align="right"></td>
	    
	</tr>
		<tr>
				<td class="celdaLabelSinAlign" align="right">Puntaje Ant.:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.puntAnt}"/>
				</td>
				<c:if test="${calificacionForm.sexo != 'M'}">
					<td class="celdaLabelSinAlign" align="right">Nº Parto:</td>
					<td class="celdaLabelSinAlign" align="left">
						<html:text size="3" title="" name="calificacionForm" tabindex="3" property="numParto"/>
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
				<html:hidden name="calificacionForm" property="fecha"/>
				<TD class="celdaLabelSinAlign" align="right">Calificador:</TD>
				<td class="celdaLabelSinAlign" align="left">
					<c:out value="${calificacionForm.idCalif}"/> - <c:out value="${calificacionForm.nombreCalif}"/>
				</td>
		</tr>
	</table>
	<TR>
		<c:out value="${requestScope.error}"/>
	</TR>
	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
               <tr><td class="celdaLabel" >
                   <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					   <tr>
                           <td width="17%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Grupa</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
							                   <html:select property="boleta.valorGru" tabindex="4" style="width:50px">
                                                            <html:option value=""  ></html:option>
							                                <html:options collection="listaValores" property="id" labelProperty="nombre" styleClass="Input100porc"/>
                                               </html:select>
                                           </td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
					            </table>
					       </td>
                           <td width="53%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth">Declive Grupa&nbsp;&nbsp;&nbsp;</td>
		                                   <td class="celdaLabelSinWidth">alta&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracGru1" tabindex="8" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">baja</td>
    								   <td class="celdaLabelSinWidth">(5)&nbsp;&nbsp;&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Seq. Isquiones</td>
						                   <td class="celdaLabelSinWidth">juntos</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracGru2" tabindex="9" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">separados</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Fortaleza lomo</td>
						                   <td class="celdaLabelSinWidth">débil</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracGru3" tabindex="10" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">fuerte</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
                 			    </table>
                           </td>
					       <td width="30%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
									   <td class="celdaLabelSinWidth">Ano Adelantado&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeGru1" tabindex="29" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
									   <td class="celdaLabelSinWidth">Cola Adelantada</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeGru2" tabindex="30" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
									   <td class="celdaLabelSinWidth">Ins. Cola Baja</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeGru3" tabindex="31" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
									   <td class="celdaLabelSinWidth">Ins. Cola Alta</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeGru4" tabindex="32" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
									   <td class="celdaLabelSinWidth">Cola Torcida</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeGru5" tabindex="33" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
						       </table>
					       </td>
				       </tr>
                   </table>
               </td></tr>
    </table>


	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
               <tr><td class="celdaLabel" >
                   <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					   <tr>
                           <td width="17%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Sistema Mamario</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
							                   <html:select property="boleta.valorSis" tabindex="5" style="width:50px">
                                                            <html:option value=""  ></html:option>
							                                <html:options collection="listaValores" property="id" labelProperty="nombre" styleClass="Input100porc"/>
                                               </html:select>
                                           </td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
					            </table>
					       </td>
                           <td width="53%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth">Prof. ubre&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                                   <td class="celdaLabelSinWidth">profun.&nbsp;&nbsp;&nbsp;</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis1" tabindex="11" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">poco prof.</td>
    								   <td class="celdaLabelSinWidth">(5/7)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Textura ubre</td>
						                   <td class="celdaLabelSinWidth">carnuda</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis2" tabindex="12" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">plegable</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Lig. Medio</td>
						                   <td class="celdaLabelSinWidth">débil</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis3" tabindex="13" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">fuerte</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Inserción ant.</td>
						                   <td class="celdaLabelSinWidth">débil</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis4" tabindex="14" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">fuerte</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Coloc. pez. ant.</td>
						                   <td class="celdaLabelSinWidth">afuera</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis5" tabindex="15" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">adentro</td>
    								   <td class="celdaLabelSinWidth">(6)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Alt. ins. post.</td>
						                   <td class="celdaLabelSinWidth">baja</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis6" tabindex="16" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">alta</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Anch. ins. post.</td>
						                   <td class="celdaLabelSinWidth">angosta</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis7" tabindex="17" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">ancha</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Coloc. pez. post.</td>
						                   <td class="celdaLabelSinWidth">afuera</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis8" tabindex="18" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">adentro</td>
    								   <td class="celdaLabelSinWidth">(9-6)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Largo pezones</td>
						                   <td class="celdaLabelSinWidth">cortos</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracSis9" tabindex="19" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">largos</td>
    								   <td class="celdaLabelSinWidth">(5)</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
                 			    </table>
                           </td>
					       <td width="30%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
									   <td class="celdaLabelSinWidth">Oblicua&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis1" tabindex="34" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Oblicua al Revés</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis2" tabindex="35" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Anterior Abultada</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis3" tabindex="36" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Anterior Corta</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis4" tabindex="37" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Posterior Corta</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis5" tabindex="38" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Mala Forma</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis6" tabindex="39" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Cuarto Desbalanceado Posterior</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis7" tabindex="40" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
								   <tr>
 									   <td class="celdaLabelSinWidth">Cuarto Desbalanceado Anterior</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis21" tabindex="40" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Cuarto Ciego</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis8" tabindex="41" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Pezón Palmípedo</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis9" tabindex="42" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">Juntos Lateralmente</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis10" tabindex="43" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
    		                       <tr>
 									   <td class="celdaLabelSinWidth">P. Post. Muy Atrás</td>
									   <TD class="celdaLabelSinWidth">
										   <html:select property="boleta.defeSis11" tabindex="44" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
								       </td>
								   </tr>
						       </table>
					       </td>
				       </tr>
                   </table>
               </td></tr>
    </table>
 	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
               <tr><td class="celdaLabel" >
                   <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					   <tr>
                           <td width="17%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Patas y Pezuñas</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
							                   <html:select property="boleta.valorPat" tabindex="6" style="width:50px">
                                                            <html:option value=""  ></html:option>
							                                <html:options collection="listaValores" property="id" labelProperty="nombre" styleClass="Input100porc"/>
                                               </html:select>
                                           </td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
					            </table>
					       </td>
                           <td width="53%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth">Ángulo pez.</td>
		                                   <td class="celdaLabelSinWidth">baja&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracPat1" tabindex="20" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">empinado</td>
    								   <td class="celdaLabelSinWidth">(7)&nbsp;&nbsp;&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Calid. hueso</td>
						                   <td class="celdaLabelSinWidth">tosco</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracPat2" tabindex="21" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">plano</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Patas de costado</td>
						                   <td class="celdaLabelSinWidth">rectas</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracPat3" tabindex="22" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">curvas</td>
    								   <td class="celdaLabelSinWidth">(5)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Locomoción</td>
						                   <td class="celdaLabelSinWidth">mala</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracPat4" tabindex="23" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">buena</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                                       </tr>
                 			    </table>
                           </td>
					       <td width="30%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Dedo Tirabuzón</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat1" tabindex="45" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Cuartillas Débiles</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat2" tabindex="46" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Garrón Avejigado</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat3" tabindex="47" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Falta de Hueso</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat4" tabindex="48" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Calambres</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat5" tabindex="49" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Art. Coxofemoral Atrás</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat6" tabindex="50" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Pez. Ant. Hacia Fuera</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defePat7" tabindex="51" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
						       </table>
					       </td>
				       </tr>
                   </table>
               </td></tr>
    </table>
	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
               <tr><td class="celdaLabel" >
                   <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
					   <tr>
                           <td width="17%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Fortaleza Lechera</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
							                   <html:select property="boleta.valorFor" tabindex="7" style="width:50px">
                                                            <html:option value=""  ></html:option>
							                                <html:options collection="listaValores" property="id" labelProperty="nombre" styleClass="Input100porc"/>
                                               </html:select>
                                           </td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
					            </table>
					       </td>
                           <td width="53%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth">Estatura&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                                   <td class="celdaLabelSinWidth">baja</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracFor1" tabindex="24" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">alta</td>
    								   <td class="celdaLabelSinWidth">(7-9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Anch. pecho</td>
						                   <td class="celdaLabelSinWidth">ang.</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracFor2" tabindex="25" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">ancho</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Prof. cuerpo</td>
						                   <td class="celdaLabelSinWidth">poco porf.</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracFor3" tabindex="26" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">profundo</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Angulosidad</td>
						                   <td class="celdaLabelSinWidth">poco ang.</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracFor4" tabindex="27" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">angulosa</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth">Cond. corporal</td>
						                   <td class="celdaLabelSinWidth">baja</td>
							               <TD class="celdaLabelSinWidth">
						                       <html:select property="boleta.caracFor5" tabindex="28" styleClass="formfields" style="width:40px">
                                                            <html:option value=""></html:option>
						                                    <html:option value="1">1</html:option>
									                        <html:option value="2">2</html:option>
									                        <html:option value="3">3</html:option>
									                        <html:option value="4">4</html:option>
									                        <html:option value="5">5</html:option>
									                        <html:option value="6">6</html:option>
									                        <html:option value="7">7</html:option>
									                        <html:option value="8">8</html:option>
									                        <html:option value="9">9</html:option>
                                               </html:select>
                                           </td>
    		                           <td class="celdaLabelSinWidth">alta</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
                 			    </table>
                           </td>
					       <td width="30%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Cara Torcida</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor1" tabindex="52" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Mandíbula Malformada</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor2" tabindex="53" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Reg. Cardíaca Estrecha</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor3" tabindex="54" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Retroescápula Débil</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor4" tabindex="55" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Dorso Débil</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor5" tabindex="56" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Falta Arco Costal</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor6" tabindex="57" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Desarmónica</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor7" tabindex="58" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">Tren Anterior Bajo</td>
    								   <td class="celdaLabelSinWidth">
										   <html:select property="boleta.defeFor8" tabindex="59" styleClass="formfields" style="width:40px">
	                                                  <html:option value="" ></html:option>
													  <html:option value="1">L</html:option>
													  <html:option value="2">G</html:option>
								           </html:select>
                                       </td>
								   </tr>
						       </table>
					       </td>
				       </tr>
                   </table>
               </td></tr>
    </table>

    <table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
           <tr>
		       <td  class="celdaLabel">Pje.</td>
			   <td class="celdaInput" >
			       <html:select property="boleta.nivelPun" tabindex="60" style="width:50px">
				                <html:option value=""  ></html:option>
								<html:option value="EX">EX</html:option>
								<html:option value="MB">MB</html:option>
								<html:option value="BM">BM</html:option>
								<html:option value="B">B</html:option>
								<html:option value="R">R</html:option>
								<html:option value="M">M</html:option>
				   </html:select>
		        </td>
				<td class="celdaInput">
		            <html:text size="1" title="" name="calificacionForm" tabindex="61" property="boleta.valorPun" />
				</td>
                <td  class="celdaLabel">Comentarios:</td>
				<td class="celdaInputSinAlign" align="right">
				    <html:text size="40" title="" name="calificacionForm" tabindex="64" property="comentarios" />
				</td>
	            <td class="celdaLabel">Especial?:</td>
				<td class="celdaInput" >
				    <html:checkbox property="especial" tabindex="63" value="true" />
					<input type="hidden" name="especial" tabindex="65" value="false">
				</td>
		    </tr>
			<tr>
                <td class="SubTitulo" style="background-color: #F7F9F0" align="left" colspan="7">Datos de la Calificación</td>
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
							<c:out value="${calificacionForm.idEstanAn}"/>
				</td>
			</tr>
		    <tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">Tambo:</td>
				<td class="celdaLabelSinAlign" colspan = "5"align="left">
							<c:out value="${calificacionForm.idEstabAn}"/>
				</td>
				
			</tr>
			<tr>
				<td class="SubTitulo" style="background-color: #F7F9F0" align="left" colspan="1" width="100%">
				<td class="SubTitulo" style="background-color: #F7F9F0; color: #FF0000; font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 11px;font-weight: bold;text-transform: uppercase;" align="left" colspan="5" >
					<c:if test="${calificacionForm.idEstabAn == '' and calificacionForm.idProp != calificacionForm.idPropAn}" >
						NOTA: El animal no se encuentra en ningún tambo y el propietario del tambo seleccionado no es igual al del animal.
					</c:if>
				</td>
				<td  class="celdaLabel" colspan = "2"><input type="button"  tabindex="66" value="Grabar Boleta" class="botones" onclick="setMethod()"></td>
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
						  alert("El valor de fecha de Parto es un campo obligatorio");
						  return;
					    }
						
                       /*if(fechaMayorOIgualQue(document.forms[0].elements["fecha"],
                             document.forms[0].elements["fechaPar"])){
						   alert("La fecha de parto debe ser anterior a la fecha de la calificación");
						   return;
					    }*/
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
				if (isEmpty(document.forms[0].elements["boleta.valorGru"].value)) {
					alert("El valor de Grupa es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracGru1"].value)) {
					alert("El Declive Grupa de Grupa es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracGru2"].value)) {
					alert("La Seq. Isquiones de Grupa es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracGru3"].value)) {
					alert("La Fortaleza lomo de Grupa es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.valorSis"].value)) {
					alert("el valor del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis1"].value)) {
					alert("La Prof. ubre del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis2"].value)) {
					alert("La Textura ubre del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis3"].value)) {
					alert("El Lig. Medio del Sistema Mamario es un campo obligatorio");
					return;
				}

				if (isEmpty(document.forms[0].elements["boleta.caracSis4"].value)) {
					alert("La Inserción ant. del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis5"].value)) {
					alert("La Coloc. pez. ant. del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis6"].value)) {
					alert("El Alt. ins. post. del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis7"].value)) {
					alert("La Anch. ins. post. del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis8"].value)) {
					alert("La Colc. pez. post. del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracSis9"].value)) {
					alert("El Largo pezones del Sistema Mamario es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.valorPat"].value)) {
					alert("El valor de Patas y Pezuñas es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracPat1"].value)) {
					alert("El Ángulo pez. de Patas y Pezuñas es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracPat2"].value)) {
					alert("La Calid. hueso de Patas y Pezuñas es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracPat3"].value)) {
					alert("Las Patas de costado de Patas y Pezuñas es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracPat4"].value)) {
					alert("La Locomoción de Patas y Pezuñas es un campo obligatorio");
					return;
				}
   				if (isEmpty(document.forms[0].elements["boleta.valorFor"].value)) {
    				alert("El valor de la Fortaleza Lechera es un campo obligatorio");
	    			return;
		      	}
                if (isEmpty(document.forms[0].elements["boleta.caracFor1"].value)) {
					alert("La Estatura de la Fortaleza Lechera es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracFor2"].value)) {
					alert("El Anch. pecho de la Fortaleza Lechera es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracFor3"].value)) {
					alert("La Prof. cuerpo de la Fortaleza Lechera es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracFor4"].value)) {
					alert("La Angulosidad de la Fortaleza Lechera es un campo obligatorio");
					return;
				}
				if (isEmpty(document.forms[0].elements["boleta.caracFor5"].value)) {
					alert("La Cond. corporal de la Fortaleza Lechera es un campo obligatorio");
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
				if((document.forms[0].elements["boleta.nivelPun"].value=='EX')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,90,99))){
					alert("el valor del puntaje debe ser de 90");
					return;
				}
				else if((document.forms[0].elements["boleta.nivelPun"].value=='MB')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,85,89))){
					alert("el valor del puntaje debe estar entre 85 y 89");
					return;
				}
				else if((document.forms[0].elements["boleta.nivelPun"].value=='BM')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,80,84))){
					alert("el valor del puntaje debe estar entre 80 y 84");
					return;
				}
				else if((document.forms[0].elements["boleta.nivelPun"].value=='B')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,75,79))){
					alert("el valor del puntaje debe estar entre 75 y 79");
					return;
				}
				else if((document.forms[0].elements["boleta.nivelPun"].value=='R')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,65,74))){
					alert("el valor del puntaje debe estar entre 65 y 74");
					return;
				}
				else if((document.forms[0].elements["boleta.nivelPun"].value=='M')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,40,64))){
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

   function esDigito(sChr){
    var sCod = sChr.charCodeAt(0);
    return ((sCod > 47) && (sCod < 58)); 
   } 

   function valSep(oTxt){ 
    var bOk = false; 
    bOk = bOk || ((oTxt.value.charAt(2) == "-") && (oTxt.value.charAt(5) == "-")); 
    bOk = bOk || ((oTxt.value.charAt(2) == "/") && (oTxt.value.charAt(5) == "/")); 
    return bOk; 
   } 

   function finMes(oTxt){ 
    var nMes = parseInt(oTxt.value.substr(3, 2), 10); 
    var nAno = parseInt(oTxt.value.substr(6), 10); 
    var nRes = 0; 
    switch (nMes){ 
     case 1: nRes = 31; break; 
     case 2: nRes = 28; break; 
     case 3: nRes = 31; break; 
     case 4: nRes = 30; break; 
     case 5: nRes = 31; break; 
     case 6: nRes = 30; break; 
     case 7: nRes = 31; break; 
     case 8: nRes = 31; break; 
     case 9: nRes = 30; break; 
     case 10: nRes = 31; break; 
     case 11: nRes = 30; break; 
     case 12: nRes = 31; break; 
    } 
    return nRes + (((nMes == 2) && (nAno % 4) == 0)? 1: 0); 
   } 

   function valDia(oTxt){ 
    var bOk = false; 
    var nDia = parseInt(oTxt.value.substr(0, 2), 10); 
    bOk = bOk || ((nDia >= 1) && (nDia <= finMes(oTxt))); 
    return bOk; 
   } 

   function valMes(oTxt){ 
    var bOk = false; 
    var nMes = parseInt(oTxt.value.substr(3, 2), 10); 
    bOk = bOk || ((nMes >= 1) && (nMes <= 12)); 
    return bOk; 
   } 

   function valAno(oTxt){ 
    var bOk = true; 
    var nAno = oTxt.value.substr(6); 
    bOk = bOk && ((nAno.length == 2) || (nAno.length == 4)); 
    if (bOk){ 
     for (var i = 0; i < nAno.length; i++){ 
      bOk = bOk && esDigito(nAno.charAt(i)); 
     } 
    } 
    return bOk; 
   } 

   function valFecha(oTxt){ 
    var bOk = true; 
    if (oTxt.value != ""){ 
     bOk = bOk && (valAno(oTxt)); 
     bOk = bOk && (valMes(oTxt)); 
     bOk = bOk && (valDia(oTxt)); 
     bOk = bOk && (valSep(oTxt)); 
     return bOk; 
    } 
   } 

   function fechaMayorOIgualQue(fec0, fec1){
    var bRes = false; 
    var sDia0 = fec0.value.substr(0, 2); 
    var sMes0 = fec0.value.substr(3, 2); 
    var sAno0 = fec0.value.substr(6, 4); 
    var sDia1 = fec1.value.substr(0, 2); 
    var sMes1 = fec1.value.substr(3, 2); 
    var sAno1 = fec1.value.substr(6, 4);
    if (sAno0 > sAno1) bRes = true; 
    else { 
     if (sAno0 == sAno1){ 
      if (sMes0 > sMes1) bRes = true; 
      else { 
       if (sMes0 == sMes1) 
        if (sDia0 >= sDia1) bRes = true; 
      } 
     } 
    } 
    return bRes; 
   } 

   function valFechas(){ 
    var bOk = false; 
    if (valFecha(document.f1.fec0)){ 
     if (valFecha(document.f1.fec1)){ 
      if (fechaMayorOIgualQue(document.f1.fec1, document.f1.fec0)){ 
       bOk = true; 
       alert("Ok"); 
      } else { 
       alert("Rango inválido"); 
       document.f1.fec1.focus(); 
      } 
     } else { 
      alert("Fecha inválida"); 
      document.f1.fec1.focus(); 
     } 
    } else { 
     alert("Fecha inválida"); 
     document.f1.fec0.focus(); 
    } 
   }
      initializeMenus();	
    
</script>
</html:form>
