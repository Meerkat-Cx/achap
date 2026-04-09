<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarCentroDeComputoAction.do" method="post" onsubmit="return setSubmit();"> 
	<html:hidden name="centroDeComputoForm" property="id"/>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">

	<html:javascript formName="centroDeComputoForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2>
            	
            	<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Centro de Computo
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Centro de Computo
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
    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="4">
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Centro de Computo
            </td>
        </tr>
        
        <tr>
        	<td colspan="4">
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
			<td class="celdaLabel">
				Nombre: <span class="required">*</span>
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="100" name="centroDeComputoForm" property="nombre" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Dirección: 			
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="centroDeComputoForm" property="direccion" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Ciudad:  			
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="centroDeComputoForm" property="ciudad" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Código Postal:   			
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="centroDeComputoForm" property="codigoPostal" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>		
		<tr>
			<td class="celdaLabel">
				Email: 				
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="30" maxlength="50" name="centroDeComputoForm" property="mail" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Teléfono:   			
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="centroDeComputoForm" property="telefono" styleClass="Input100porc"/>
				<br/>
		    </td>
		</tr>	
		<tr>
			<td class="celdaLabel">
				Activo?:
			</td>
			<td class="celdaInput" colspan="3">
				<html:checkbox property="activo" value="true" />
  				<input type="hidden" name="activo" value="false">				
		    </td>
		</tr>

		
		
	</table>

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
        		<td align="center">
        					<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
        		</td>			
				<td align="center"><html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel></td>
			</td>
		</tr>	
	</table>
</html:form>

<script>

	function validate() {
		return validateCentroDeComputoForm(document.forms[0]);
	}

	function setMethod(valor){
		validate();
		document.forms[0].method.value = valor;
    	document.forms[0].submit();
    }

	function setSubmit(){		
		return validateCentroDeComputoForm(document.forms[0]);
	}
</script>


