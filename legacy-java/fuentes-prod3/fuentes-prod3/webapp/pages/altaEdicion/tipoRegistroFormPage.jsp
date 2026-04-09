<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/listarTiposRegistroAction.do" method="post"onsubmit="return validate();"> 
	
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	
	<html:javascript formName="tiposRegistroForm"/>
			
	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=2 src="pages/assets/images/pixel.gif" width=2>
            	
            	<c:choose>
						<c:when test="${action == 'add'}">
	                   		&nbsp;Alta de Tipo de Registro
						</c:when>
						<c:otherwise>
							&nbsp;Modificaci&oacute;n de Tipo de Registro
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
        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos del Tipo de Registro
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
				Identificador:<c:if test='${action != "update"}'><span class="required">*</span></c:if>
			</td>
			<td class="celdaInput" colspan="3">
				<c:if test='${action == "update"}'>
					<c:out value="${tiposRegistroForm.id}"/>
					<html:hidden name="tiposRegistroForm" property="id"/>
				</c:if>
				<c:if test='${action == "add"}'>
				<html:text size="45" maxlength="100" name="tiposRegistroForm" property="id"/>
				</c:if>
				<br/>
		    </td>
		</tr>
		<tr>
			<td class="celdaLabel">
				Descripcion: <span class="required">*</span>				
			</td>
			<td class="celdaInput" colspan="3">
				<html:text size="45" maxlength="150" name="tiposRegistroForm" property="descrip" />
				<br/>
		    </td>
		</tr>
		<tr>
			
			<c:if test='${action == "update"}'>
            
          		
	            <c:choose>
						<c:when test="${tiposRegistroForm.tiene==1}">
						
							<td class="celdaLabelSinAlign" align="center"  colspan ="4">existen animales con este tipo de registro, no es posible ejecutar la baja
							<input id="botonSeleccionarContacto" type="button" value="ejecutar baja" 
								class="botones" onclick="setMethod('baja')" disabled="true">
								</TD>
						</c:when>
						<c:otherwise>
						<td class="celdaLabelSinAlign" align="center"  colspan ="4">
							<input id="botonSeleccionarContacto" type="button" value="ejecutar baja" 
								class="botones" onclick="setMethod('baja')">
								</TD>	
						</c:otherwise>
				</c:choose>	
				</c:if>	                        
  	       
		</tr>
	</table>

	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	
        		<td align="center">
        					<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
        		</td>			
				<td align="center"><html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel></td>
			
		</tr>	
	</table>
</html:form>

<script>
	function validate() {
		
		return validateTiposRegistroForm(document.forms[0]);
	}
	
	function setMethod(valor){
		document.forms[0].method.value = valor;
    	document.forms[0].submit();
    }
    
	


</script>


