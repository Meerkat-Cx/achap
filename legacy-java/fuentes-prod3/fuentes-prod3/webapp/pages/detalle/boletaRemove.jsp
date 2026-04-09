<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/altaCalificacionAction.do" method="post" enctype="multipart/form-data" onsubmit="return validate();">
	<html:javascript formName="calificacionForm"/>
			
	
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	 <html:hidden property="error" value="${requestScope.error}"/>	
	 
		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
    			<tr> 
					<TD height="25" bgColor=#eff3e3 class=SubTitulo colspan="4"><IMG height=15 hspace=2 
				            src="pages/assets/images/flecha_titulos3a.gif" 
					           width=11 align=absMiddle>
					Visualizaci&oacute;n de Califcacion&nbsp;&nbsp;&nbsp;&nbsp;Boleta nº:<c:out value="${calificacionForm.numBoleta}"/>
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
				<td class="celdaLabelSinAlign" align="right">Fecha Parto:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.fechaPar}"/>
				</td>
		   </tr>
		    <tr>
				<td class="celdaLabelSinAlign" align="right">Puntaje Ant.:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.puntAnt}"/>
				</td>
				<td class="celdaLabelSinAlign" align="right">Nº Parto:</td>
				<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.numParto}"/>
				</td>
		   </tr>
		   
		   <tr>

				<TD class="celdaLabelSinAlign" align="right">Fecha:</TD>
						<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.fecha}"/>
						</td>
					

				<TD class="celdaLabelSinAlign" align="right">Califcador:</TD>
						<td class="celdaLabelSinAlign" align="left">
							<c:out value="${calificacionForm.idCalif}"/>
						</td>
			 </tr>
	
		<tr>
			<TD class="celdaLabelSinAlign"  align="left" colspan = "7">Animal de la Boleta</TD>
		</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2"  align="right">ID:</td>
				<td class="celdaLabelSinAlign" colspan = "5" align="left">
							<c:out value="${calificacionForm.idAninal}"/>
				</td>
			</tr>
			<tr>
				<td class="celdaLabelSinAlign" colspan = "2" align="right">RP:</td>
				<td class="celdaLabelSinAlign" colspan = "5"   align="left">
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
				<td class="celdaLabelSinAlign" colspan = "5"align="left">
							<c:out value="${calificacionForm.idEstabAn}"/>
				</td>
		 
			</tr>
</table>
<TR>
            			<td class="TextoNegro">
            			<c:out value="${requestScope.error}"/>	
            			</td>
            		</TR>
		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
				
	            <input type="button" value="Ejecutar Baja" class="botones" onclick="setMethod('remove')">            
				 <input name="button" type="button" class="Botones" onClick="window.location='filtrarCalificacionAction.do?method=initBuscar'" value="Cancelar"/>
				</TD>
			</TR>	
		</table>	

<script language="JavaScript">
		function setMethod(valor){
			document.forms[0].method.value = valor;
			document.forms[0].submit();
		}
		

    	
    	
	</script> 
	
	</html:form>


