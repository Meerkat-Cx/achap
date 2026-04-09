<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html:form action="/buscarProcProces.do">
		<!-- Titulo de la pagina -->
		
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                    <TR> 
                      <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>&nbsp;Ficha de Proceso</FONT></TD>
                      <TD> <DIV align=right></DIV></TD>
                    </TR>
                    <TR> 
                      <TD class=texto4 colSpan=2> <DIV align=right> 
                          <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
            cellPadding=0 width="100%" border=0>

                            <TR> 
                              <TD width="30%" bgColor=#529b28><IMG height=2 
                  src="/web-1.0/pages/assets/images/pixel.gif" 
                  width=2></TD>
                              <TD width="70%"><IMG height=2 
                  src="/web-1.0/pages/assets/images/pixel.gif" 
                  width=2></TD>
                            </TR>
                          </TABLE>
                        </DIV></TD>
                    </TR>
                  </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>

<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
  
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
  		<tr>
    		<td valign="top">
      			
      			<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
        			<tr>
          				<td class="celdaLabel" align="left" height="10" width="70"><b>Identificador</b></td>
          				<td class="celdaLabelSinAlign" colspan="2" align="left"><bean:write name="procProcesForm" property="procProces.id"/></td>
          				
      				<tr>
          				<td class="celdaLabel" align="left" height="10" width="70"><b>Usuario Responsable</b></td>
          				<td class="celdaLabelSinAlign" colspan="2" align="left">
          					<bean:write name="procProcesForm" property="procProces.usuario.nombre"/>
          						<c:out value=" "/>
          					<bean:write name="procProcesForm" property="procProces.usuario.apellido"/>
          				</td>
				     </tr>   
				    <tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>   				
          			
          			<tr>
          				<td class="celdaLabel" align="left" height="10"><b>Número de Lote</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          				<logic:present name="procProcesForm" property="procProces.procLote" >
                           <bean:write name="procProcesForm" property="procProces.procLote.numLote"/>
                        </logic:present>
                        <logic:notPresent name="procProcesForm" property="procProces.procLote" >
                            <bean:message key="dataNotFound" />
                        </logic:notPresent>
          			</tr>          				
        			<tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>
        			
        			<tr>
          				<td class="celdaLabel" align="left" height="10"><b>Eclo Procesada</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          					<logic:present name="procProcesForm" property="procProces.procLote.eclo" >
								<bean:write name="procProcesForm" property="procProces.procLote.eclo.id"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote.eclo" >
								<bean:message key="dataNotFound" />
							</logic:notPresent>
						</td>
					</tr>
					<tr>
          				<td class="celdaLabel" align="left" height="10"><b>Sistema</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          					<logic:present name="procProcesForm" property="procProces.procLote.sistema" >
								<bean:write name="procProcesForm" property="procProces.procLote.sistema.nombre"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote.sistema" >
								<bean:message key="dataNotFound" />
							</logic:notPresent>
						</td>
					</tr>
					<tr>
          				<td class="celdaLabel" align="left" height="10"><b>Centro de cómputo</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          					<logic:present name="procProcesForm" property="procProces.procLote.centroComputo" >
								<bean:write name="procProcesForm" property="procProces.procLote.centroComputo.nombre"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote.centroComputo" >
								<bean:message key="dataNotFound" />
							</logic:notPresent>
						</td>
					</tr>
        			<tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>		          	
			          	
			        <tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
        			
        			
	                 <tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>
			          	
			        <tr>
          				<td class="celdaLabel" align="left" height="10"><b>Fecha de Envío</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          				<logic:present name="procProcesForm" property="procProces.procLote" >
								<bean:write name="procProcesForm"  property="procProces.procLote.TEnvio"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote" >
								<bean:message key="dataNotFound" />
							</logic:notPresent></td>
	                        
        			<tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>  	
			          	
			        <tr>
          				<td class="celdaLabel" align="left" height="10"><b>Fecha de Entrada a ACHA</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
	                       
								<bean:write name="procProcesForm" property="procProces.fechaEntrada"/>
							</td>
	                        

        			<tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr> 
			          	
			        <tr>
          				<td class="celdaLabel" align="left" height="10"><b>Inicio de Procesamiento</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
          				<logic:present name="procProcesForm" property="procProces.procLote" >
								<bean:write name="procProcesForm" property="procProces.procLote.TInicioProc"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote" >
								<bean:message key="dataNotFound" />
							</logic:notPresent></td>
	                        
	                <tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr> 	                
	                
	                <tr>
          				<td class="celdaLabel" align="left" height="10"><b>Fin de Procesamiento</b><spacer type="block" height="1" width="1"></td>
          				<td class="celdaLabelSinAlign" align="left">
						<logic:present name="procProcesForm" property="procProces.procLote" >
								<bean:write name="procProcesForm" property="procProces.procLote.tiFinProc"/>
							</logic:present>
							<logic:notPresent name="procProcesForm" property="procProces.procLote" >
								<bean:message key="dataNotFound" />
							</logic:notPresent></td>
	                        
	                <tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr> 
	                        
	               
							
        			<tr>
          				<td class="celdaLabel" align="left" height="10"></td>
			          	<td class="celdaLabelSinAlign" align="left"></td></tr>

					
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
          				
          			<tr>
          				<td class="celdaLabel" align="left" height="10"><b>Tambos Procesados</b><spacer type="block" height="1" width="1"></td>          				
          				<td colspan="2" class="celdaLabelSinAlign" align="left">
          				
          					<div class="justify">
								<display:table name="procProcesForm.procProces.procLote.procEstablecimientos" align="center" class="its" width="100%" id="table4" >					
								<display:column property="establecimiento.id" title="ID" />
								<display:column property="establecimiento.nombreContacto"
									title="Tambo"/>									
								<display:setProperty name="basic.msg.empty_list" >
								</display:setProperty>
								</display:table>
							</div>
          				</td>
                    </tr>    				
                    
      				
               
                </table>
                
                </td></tr>
     </table>           
    

  <div><br><br></div> 


<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
  <tr valign="bottom">
    <td class="titulo" align="left">Mensajes Obtenidos del Proceso
	</td>
	</tr>
	</table>

	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
  		<tr>
    		</tr>					        	
         	<tr>
	          	<td>
	          	
							<display:table name="procProcesForm.mensajes" align="center" class="its" width="100%" id="table3" >
							
								<display:column property="id" title="ID" />
								<display:column property="nivelError" title="Nivel de Error" />
								<display:column property="informacion" title="Información" />
								<display:setProperty name="basic.msg.empty_list" >
								</display:setProperty>
							</display:table>
					
      		</td>
			</tr>
      	
      </table>
     </table>
</html:form>























