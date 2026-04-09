<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<html:form action="/intercambiarEntrePropietariosAction.do" > 
	
	<input type="hidden" name="method" value="<c:out value="${action}"/>">

	<table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
    	<tr> 
        	<td class=Titulo>
            	<font color=#529b28><img height=15 hspace=2 width=11 align=absMiddle>
            		&nbsp;Intercambio de Animales entre Propietarios
					
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
			<td class="celdaLabel">
				Propietario 1:	
			<td class="celdaInput">
				<html:select property="idProp1" styleClass="Input100porc">
					<html:options collection="propietarios" property="id" labelProperty="idNombre" />
				</html:select>	
			</td>
		</tr>
		
		<tr>
			<td class="celdaLabel">
				Propietario 2: 	
			<td class="celdaInput">
				<html:select property="idProp2" styleClass="Input100porc">
					<html:options collection="propietarios" property="id" labelProperty="idNombre" />
				</html:select>	
			</td>
		</tr>
		<tr>
				<td class="celdaLabelSinAlign" colspan="2" align="center"><strong>Al presionar Aceptar todos los animales del propietario 1 pasaran a ser del propietario 2</strong></td>
				
		</tr>
	</table>
	<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
    	<tr> 
        	<td align="center">
				<html:submit styleClass="botones">
					<bean:message key="submit"/>
				</html:submit>
				<html:cancel styleClass="botones" onclick="bCancel=true;">
					<bean:message key="cancel"/>
				</html:cancel>
			</td>
		</tr>	
	</table>
</html:form>