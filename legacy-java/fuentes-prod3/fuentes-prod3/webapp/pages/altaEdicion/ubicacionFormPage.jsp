<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>


<html:form action="/edicionUbicacion.do" method="post"  onsubmit="return validateUbicacionForm(this)" focus="nombre">
	<input type="hidden" name="method" value="<c:out value="${requestScope.metodo}"/>">
	
	<html:javascript formName="ubicacionForm"/>
	
	<input type="hidden" name="contactoId" value="<c:out value="${ubicacionForm.contactoId}"/>"/>
	<input type="hidden" name="id" value="<c:out value="${ubicacionForm.id}"/>"/>
	
	<html:hidden name="ubicacionForm" property="actionBack"/>
	
	
	
	
<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>            	
	                   		&nbsp;Alta de Ubicacion
            	</font>
            </td>
            <td>
            	<div align=right></div>
            </td>
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
    	   		<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de la Ubicacion
            </td>
        </tr>
				<tr>
					<td class="celdaInput" colspan="2">
		                Referencia de Ubicación (Id:<bean:write name="ubicacionForm" property="contactoId"/>)
					</td>					
				</tr>				
				<TR>
					<TD class="celdaLabel">Nombre de Referencia: <span class="required">*</span></TD>
					<TD class="celdaInput">
        		        <html:text size="45" name="ubicacionForm" property="nombre" />
		            </TD>
				</TR>

				<TR>
					<TD class="celdaLabel">Ciudad: 
					<TD class="celdaInput">
        		        <html:text size="45" name="ubicacionForm" property="ciudad" />
		            </TD>
				</TR>

				<TR>
					<TD class="celdaLabel">Provincia/Región: <span class="required">*</span></TD>
					<TD class="celdaInput">
	                	<!--<html:text size="45" name="ubicacionForm" property="provinciaRegion" />-->
	                	<html:select name="ubicacionForm" property="provinciaRegion">
							<html:option value="BA">Buenos Aires</html:option>
							<html:option value="CBA">Cordoba</html:option>
							<html:option value="CF">Capital Federal</html:option>
							<html:option value="CH">Chubut</html:option>
							<html:option value="CHA">Chaco</html:option>
							<html:option value="CO">Corrientes</html:option>							
							<html:option value="CTC">Catamarca</html:option>
							<html:option value="ER">Entre Rios</html:option>
							<html:option value="FOR">Formosa</html:option>
							<html:option value="JU">Jujuy</html:option>							
							<html:option value="LP">La Pampa</html:option>							
							<html:option value="LR">La Rioja</html:option>
							<html:option value="MI">Misiones</html:option>
							<html:option value="MZA">Mendoza</html:option>
							<html:option value="NEU">Neuquen</html:option>																				
							<html:option value="RN">Rio Negro</html:option>													
							<html:option value="SAL">Salta</html:option>							
							<html:option value="SC">Santa Cruz</html:option>																				
							<html:option value="SF">Santa Fe</html:option>
							<html:option value="SJ">San Juan</html:option>
							<html:option value="SL">San Luis</html:option>
							<html:option value="SGO">Santiago del Estero</html:option>
							<html:option value="TF">Tierra del Fuego</html:option>
							<html:option value="TU">Tucuman</html:option>
	                	</html:select>
	    	        </TD>
				</TR>
				<TR>
					<TD class="celdaLabel">País: 
					<TD class="celdaInput">
		                <html:text size="45" name="ubicacionForm" property="pais" />
		            </TD>
				</TR>
				<TR>
					<TD class="celdaLabel">Dirección Completa: </TD>
					<TD class="celdaInput">
                		<html:text size="45" name="ubicacionForm" property="direccion" />
            		</TD>
				</TR>
				<TR>
					<TD class="celdaLabel">Código Postal: </TD>
					<TD class="celdaInput">
           		    	<html:text size="22" name="ubicacionForm" property="codigoPostal"  maxlength="10"/>
            		</TD>
				</TR>

				<TR>
					<TD class="celdaLabel">E-Mail: </TD>
					<TD class="celdaInput">
                		<html:text size="45" name="ubicacionForm" property="mail" />
            		</TD>
				</TR>

				<TR>
					<TD class="celdaLabel">Teléfono: </TD>
					<TD class="celdaInput">
                		<html:text size="45" name="ubicacionForm" property="telefono" />
            		</TD>
				</TR>
			</table>		
			
			<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:submit styleClass="botones">
					Aceptar
				</html:submit>
				<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel>
			</td>
		</tr>	
	</table>	

</html:form>
		
	
