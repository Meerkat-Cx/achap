<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/reportesPropietariosAchaAction.do" method="post" >
		<!-- Titulo de la pagina -->
					
 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
							Datos del Propietario</FONT>
						</TD>
                        <TD> 
                        	<DIV align=right></DIV>
                        </TD>
                      </TR>
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="30%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                                <TD width="70%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                              </TR>
                            </TABLE>
                          	</DIV>
                        </TD>
                      </TR>
        </TABLE>
        
   	<!-- Fin Titulo de la pagina -->
   	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
			<tr> 
				<td class="celdaLabelSinAlign" >
					Id del Propietario: <label class="TextoNegro"> <c:out value="${propietarioForm.idProp}"/></label>
				</td> 
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Nombre y Apellido: <label class="TextoNegro"> <c:out value="${propietarioForm.nombre}"/></label>
				</td> 
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Cuit/Cuil: <label class="TextoNegro"><c:out value="${propietarioForm.cuit}"/></label>
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Cuig: <label class="TextoNegro"><c:out value="${propietarioForm.cuig}"/></label>
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Renspa: <label class="TextoNegro"><c:out value="${propietarioForm.renspa}"/></label>
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Prefijo: <label class="TextoNegro"><c:out value="${propietarioForm.prefijo}"/></label>
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					HAR: <label class="TextoNegro"><c:out value="${propietarioForm.har}"/></label>
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Persona fisica: <label class="TextoNegro">
					<c:if test="${propietarioForm.esPersonaFisica == true}">
						<c:out value="SI"/></label>
					</c:if>
					<c:if test="${propietarioForm.esPersonaFisica == false}">
						<c:out value="NO"/></label>
					</c:if> 
				</td>
			</tr>
			<tr> 
				<td class="celdaLabelSinAlign" >
					Estado: <label class="TextoNegro">
					<c:if test="${propietarioForm.activo == true}">
						<c:out value="Activo"/></label>
					</c:if>
					<c:if test="${propietarioForm.activo == false}">
						<c:out value="Inactivo"/></label>
					</c:if>
				</td>
			</tr>
	 </table>
	 <br>
	 
	<div class="justify" id="displayTambosProìetario">
		<display:table width="100%" align="right" name="listaEstablecimientos" class="its" id="item"  pagesize="8" requestURI="/reportesPropietariosAchaAction.do?method=initBusquedaTambosPropietario">
			<display:setProperty name="basic.msg.empty_list" >
			 	       <table width="100%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
	            		<TR>
	            			<td class="TextoNegro">El propietario no tiene tambos
	            			</td>
	            		</TR>
	            	</table>
			</display:setProperty>
			        
	        <display:column align="left" title="Id Eclo"  property="eclo.id"/>
	        <display:column align="left" title="Id Establecimiento"  property="estancia.id"/>
	        <display:column align="left" headerClass="TextoVerdeNegrita"  title="Id Tambo" class="TextoNegroNegrita" property="id"/>
	        <display:column align="left" headerClass="TextoVerdeNegrita" title="Activo" >
	            <html:checkbox disabled="true" name="item" property="activoEstablecimiento"  />
	        </display:column>
	        <display:column align="left" title="Nombre" property="nombreContacto"/>
	      </display:table>
     </div>
     
    <br>	
     <tr>
     <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
            <TR>
            	
				<TD  class="TextoNegro" >
					<img border="0" src="pages\assets\images\ico_file_excel.png" title="Descargar"/>
					<a href="reportesPropietariosAchaAction.do?method=downloadReportesTambos">Descargar Reporte sobre Tambos
					</a>
				</TD>
			</TR>
			
		
	</table>
	</tr>
	  
     
     
</html:form>