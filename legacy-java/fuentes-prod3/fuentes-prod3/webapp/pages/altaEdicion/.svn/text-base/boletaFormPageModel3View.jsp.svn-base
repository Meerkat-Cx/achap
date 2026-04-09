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
	     <td height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2
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
	    	</td>
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
	    <td class="celdaLabelSinAlign" align="right">Tipo y Nro. de Reg.:</td>
	    <td class="celdaLabelSinAlign" align="left">
	        <c:out value="${calificacionForm.tipoReg}"/> <c:out value="${calificacionForm.numReg}"/>
        </td>
        <td class="celdaLabelSinAlign" align="right"></td>
		<td class="celdaLabelSinAlign" align="right"></td>
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
	        	<c:out value="${calificacionForm.numParto}"/>
            </td>
         </c:if>
         <c:if test="${calificacionForm.sexo == 'M'}">
			<td class="celdaLabelSinAlign" align="right"></td>
			<td class="celdaLabelSinAlign" align="right"></td>
		</c:if>
	</tr>

        <tr>
	    <td class="celdaLabelSinAlign" align="right">Fecha:</td>
            <td class="celdaLabelSinAlign" align="left">
	        <c:out value="${calificacionForm.fecha}"/>
            </td>
	    <td class="celdaLabelSinAlign" align="right">Calificador:</td>
            <td class="celdaLabelSinAlign" align="left">
	        <c:out value="${calificacionForm.idCalif}"/> - <c:out value="${calificacionForm.nombreCalif}"/>
	    </td>
	</tr>
	
        </table>

	<tr>
	    <c:out value="${requestScope.error}"/>
	</tr>

	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >
        
        <tr>
            <td class="celdaLabel" >
                <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
                
                <tr>
                    <td width="20%">
		        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >

                        <tr>
                            <td  class="celdaLabelSinAlign" align="left">Grupa</td>
                        </tr>

                        <tr>
			    <td  class="celdaLabelSinWidth" align="left">&nbsp;</td>
                        </tr>
			
                        <tr>
			    <td class="celdaInput" >
                    	        <c:out value="${calificacionForm.boleta.valorGru}"/>
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
                    
                    <td width="40%">
		        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
			
                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Declive Grupa</td>
		            <td class="celdaLabelSinWidth" width="30%">alta</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracGru1}"/>
                            </td>
                        </tr>

                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Seq. Isquiones</td>
			    <td class="celdaLabelSinWidth" width="30%">juntos</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracGru2}"/>
                            </td>
                        </tr>

                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Fortaleza lomo</td>
			    <td class="celdaLabelSinWidth" width="30%">débil</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracGru3}"/>
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
		    <td width="40%">
		        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		    
                        <tr>
    		            <td class="celdaLabelSinWidth">baja</td>
    			    <td class="celdaLabelSinWidth">(5)&nbsp;&nbsp;&nbsp;</td>
			    <td class="celdaLabelSinWidth">Ano Adelantado</td>
			    <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeGru1 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeGru1 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
			    </td>
			</tr>

                        <tr>
    		            <td class="celdaLabelSinWidth">separados</td>
    			    <td class="celdaLabelSinWidth">(7)</td>
			    <td class="celdaLabelSinWidth">Cola Adelantada</td>
			    <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeGru2 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeGru2 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                            </td>
			</tr>

                        <tr>
    		            <td class="celdaLabelSinWidth">fuerte</td>
    			    <td class="celdaLabelSinWidth">(9)</td>
			    <td class="celdaLabelSinWidth">Ins. Cola Baja</td>
			    <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeGru3 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeGru3 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                            </td>
			</tr>

                        <tr>
    		            <td class="celdaLabelSinWidth">&nbsp;</td>
    			    <td class="celdaLabelSinWidth">&nbsp;</td>
			    <td class="celdaLabelSinWidth">Ins. Cola Alta</td>
			    <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeGru4 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeGru4 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                            </td>
			</tr>

                        <tr>
    		            <td class="celdaLabelSinWidth">&nbsp;</td>
    			    <td class="celdaLabelSinWidth">&nbsp;</td>
			    <td class="celdaLabelSinWidth">Cola Torcida</td>
			    <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeGru5 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeGru5 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                            </td>
			</tr>

                        </table>
                    </td>
		</tr>

                </table>
            </td>
        </tr>

        </table>

	<table width="80%" class="bordeGris" cellSpacing="0" cellPadding="0" align="center" >

        <tr>
            <td class="celdaLabel" >
                <table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >

                <tr>
                    <td width="20%">
		        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >

                        <tr>
                            <td  class="celdaLabelSinAlign" align="left">Sistema Mamario</td>
                        </tr>

                        <tr>
			    <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                        </tr>

                        <tr>
			    <td class="celdaInput" >
                    	        <c:out value="${calificacionForm.boleta.valorSis}"/>
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
			
                        </table>
                    </td>
                    <td width="40%">
		        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >

                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Prof. ubre</td>
		            <td class="celdaLabelSinWidth" width="30%">profun.</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracSis1}"/>
                            </td>
                        </tr>
                        
                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Textura ubre</td>
			    <td class="celdaLabelSinWidth" width="30%">carnuda</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracSis2}"/>
                            </td>
                        </tr>

                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Lig. Medio</td>
			    <td class="celdaLabelSinWidth" width="30%">débil</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracSis3}"/>
                            </td>
                        </tr>

                        <tr>
			    <td class="celdaLabelSinWidth" width="40%">Inserción ant.</td>
			    <td class="celdaLabelSinWidth" width="30%">débil</td>
			    <td class="celdaLabelSinWidth" width="30%">
                    	        <c:out value="${calificacionForm.boleta.caracSis4}"/>
                            </td>
                        </tr>
				<tr>
					<td class="celdaLabelSinWidth" width="40%">Coloc. pez. ant.</td>
					<td class="celdaLabelSinWidth" width="30%">afuera</td>
					<TD class="celdaLabelSinWidth" width="30%">
	           	        <c:out value="${calificacionForm.boleta.caracSis5}"/>
                     </td>
                </tr>

                <tr>
	               <td class="celdaLabelSinWidth" width="40%">Alt. ins. post.</td>
	               <td class="celdaLabelSinWidth" width="30%">baja</td>
	               <TD class="celdaLabelSinWidth" width="30%">
	           	        <c:out value="${calificacionForm.boleta.caracSis6}"/>
                                           </td>
                                       </tr>

               <tr>
                       <td class="celdaLabelSinWidth" width="40%">Anch. ins. post.</td>
	               <td class="celdaLabelSinWidth" width="30%">angosta</td>
	               <TD class="celdaLabelSinWidth" width="30%">
	           	        <c:out value="${calificacionForm.boleta.caracSis7}"/>
                                           </td>
                                       </tr>

               <tr>
	              <td class="celdaLabelSinWidth" width="40%">Coloc. pez. post.</td>
	              <td class="celdaLabelSinWidth" width="30%">afuera</td>
		      <TD class="celdaLabelSinWidth" width="30%">
                          <c:out value="${calificacionForm.boleta.caracSis8}"/>
                                           </td>
                                       </tr>

               <tr>
                      <td class="celdaLabelSinWidth" width="40%">Largo pezones</td>
		      <td class="celdaLabelSinWidth" width="30%">cortos</td>
		      <TD class="celdaLabelSinWidth" width="30%">
                          <c:out value="${calificacionForm.boleta.caracSis9}"/>
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
           <td width="40%">
               <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    	
                   <tr>
    	               <td class="celdaLabelSinWidth">poco prof.</td>
    	               <td class="celdaLabelSinWidth">(5/7)&nbsp;&nbsp;</td>
		       <td class="celdaLabelSinWidth">Oblicua</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis1 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis1 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">plegable</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Oblicua al Revés</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis2 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis2 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">fuerte</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Anterior Abultada</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis3 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis3 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">fuerte</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Anterior Corta</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis4 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis4 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">adentro</td>
                       <td class="celdaLabelSinWidth">(6)</td>
 		       <td class="celdaLabelSinWidth">Posterior Corta</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis5 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis5 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">alta</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Mala Forma</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis6 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis6 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

                   <tr>
                       <td class="celdaLabelSinWidth">ancha</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Cuarto Desbalanceado Posterior</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis7 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis7 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>
				<tr>
                       <td class="celdaLabelSinWidth">ancha</td>
                       <td class="celdaLabelSinWidth">(9)</td>
 		       <td class="celdaLabelSinWidth">Cuarto Desbalanceado Anterior</td>
		       <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis21 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis21 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

    		                       <tr>
   		                           <td class="celdaLabelSinWidth">adentro</td>
    								   <td class="celdaLabelSinWidth">(9-6)</td>
 									   <td class="celdaLabelSinWidth">Cuarto Ciego</td>
									   <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis8 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis8 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

    		                       <tr>
    		                           <td class="celdaLabelSinWidth">largos</td>
    								   <td class="celdaLabelSinWidth">(5)</td>
 									   <td class="celdaLabelSinWidth">Pezón Palmípedo</td>
									   <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis9 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis9 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
 									   <td class="celdaLabelSinWidth">Juntos Lateralmente</td>
									   <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis10 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis10 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
								       </td>
								   </tr>

    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
 									   <td class="celdaLabelSinWidth">P. Post. Muy Atrás</td>
									   <TD class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeSis11 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeSis11 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
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
                           <td width="20%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Patas y Pezuñas</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
                                                                               <c:out value="${calificacionForm.boleta.valorPat}"/>
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
                           <td width="40%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Ángulo pez.</td>
		                                   <td class="celdaLabelSinWidth" width="30%">baja</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracPat1}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Calid. hueso</td>
						                   <td class="celdaLabelSinWidth" width="30%">tosco</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracPat2}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Patas de costado</td>
						                   <td class="celdaLabelSinWidth" width="30%">rectas</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracPat3}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Locomoción</td>
						                   <td class="celdaLabelSinWidth" width="30%">mala</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracPat4}"/>
                                           </td>
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
					       <td width="40%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">empinado</td>
    								   <td class="celdaLabelSinWidth">(7)&nbsp;&nbsp;&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Dedo Tirabuzón</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat1 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat1 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">plano</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
    		                           <td class="celdaLabelSinWidth">Cuartillas Débiles</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat2 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat2 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">curvas</td>
    								   <td class="celdaLabelSinWidth">(5)</td>
    		                           <td class="celdaLabelSinWidth">Garrón Avejigado</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat3 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat3 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">buena</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
    		                           <td class="celdaLabelSinWidth">Falta de Hueso</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat4 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat4 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Calambres</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat5 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat5 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Art. Coxofemoral Atrás</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat6 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat6 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Pez. Ant. Hacia Fuera</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defePat7 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defePat7 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
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
                           <td width="20%">
						        <table width="100%" cellSpacing="0" cellPadding="2" align="left" >
                                       <tr>
                                           <td  class="celdaLabelSinAlign" align="left">Fortaleza Lechera</td>
                                       </tr>
						               <tr>
						                   <td  class="celdaLabelSinAlign" align="left">&nbsp;</td>
                                       </tr>
							           <tr >
							               <td class="celdaInput" >
                                                                               <c:out value="${calificacionForm.boleta.valorFor}"/>
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
                           <td width="40%">
						        <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Estatura</td>
		                                   <td class="celdaLabelSinWidth" width="30%">baja</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracFor1}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Anch. pecho</td>
						                   <td class="celdaLabelSinWidth" width="30%">ang.</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracFor2}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Prof. cuerpo</td>
						                   <td class="celdaLabelSinWidth" width="30%">poco porf.</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracFor3}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Angulosidad</td>
						                   <td class="celdaLabelSinWidth" width="30%">poco ang.</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracFor4}"/>
                                           </td>
                                       </tr>
						               <tr>
						                   <td class="celdaLabelSinWidth" width="40%">Cond. corporal</td>
						                   <td class="celdaLabelSinWidth" width="30%">baja</td>
							               <TD class="celdaLabelSinWidth" width="30%">
                                                                               <c:out value="${calificacionForm.boleta.caracFor5}"/>
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
                 			    </table>
                           </td>
					       <td width="40%">
					           <table width="100%"  cellSpacing="0" cellPadding="0" align="left" >
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">alta</td>
    								   <td class="celdaLabelSinWidth">(7-9)</td>
    		                           <td class="celdaLabelSinWidth">Cara Torcida</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor1 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor1 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">ancho</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
    		                           <td class="celdaLabelSinWidth">Mandíbula Malformada</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor2 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor2 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">profundo</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
    		                           <td class="celdaLabelSinWidth">Reg. Cardíaca Estrecha</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor3 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor3 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">angulosa</td>
    								   <td class="celdaLabelSinWidth">(9)</td>
    		                           <td class="celdaLabelSinWidth">Retroescápula Débil</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor4 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor4 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">alta</td>
    								   <td class="celdaLabelSinWidth">(7)</td>
    		                           <td class="celdaLabelSinWidth">Dorso Débil</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor5 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor5 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Falta Arco Costal</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor6 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor6 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Desarmónica</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor7 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor7 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
                                       </td>
								   </tr>
    		                       <tr>
    		                           <td class="celdaLabelSinWidth">&nbsp;</td>
    								   <td class="celdaLabelSinWidth">&nbsp;</td>
    		                           <td class="celdaLabelSinWidth">Tren Anterior Bajo</td>
    								   <td class="celdaLabelSinWidth">
				<c:choose>
				      <c:when test="${calificacionForm.boleta.defeFor8 == ''}"></c:when>
				      <c:when test="${calificacionForm.boleta.defeFor8 == '1'}">L</c:when>
 				      <c:otherwise>G</c:otherwise>
				</c:choose>
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
                               <c:out value="${calificacionForm.boleta.nivelPun}"/>
		        </td>
				<td class="celdaInput">
                                   <c:out value="${calificacionForm.boleta.valorPun}"/>
				</td>
                <td  class="celdaLabel">Comentarios:</td>
				<td class="celdaInputSinAlign" align="right">
                                   <c:out value="${calificacionForm.comentarios}"/>
				</td>
	            <td class="celdaLabel">Especial?:</td>
                    <c:choose>
		             <c:when test="${calificacionForm.especial == 'true'}">
		                   <td class="celdaLabelSinAlign" colspan="2" align="left">si</td>
                             </c:when>
			     <c:otherwise>
                                   <td class="celdaLabelSinAlign" colspan="2" align="left">no</td>
                             </c:otherwise>
                    </c:choose>
               </tr>
			<tr>
                <td class="celdaLabelSinAlign"  align="left" colspan = "7">Animal de la Boleta</td>
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
				if((document.forms[0].elements["boleta.nivelPun"].value=='EX')&&(noEstaEntre(document.forms[0].elements["boleta.valorPun"].value,90,90))){
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
	 initializeMenus();	
</script>
</html:form>
