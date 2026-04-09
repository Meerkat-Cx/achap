<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:html locale="true">
	<head>
    	
    	
    	<STYLE type="text/css">
			A:link {COLOR: red; TEXT-DECORATION: none}
			A:visited {COLOR: gray; TEXT-DECORATION: none}
			A:active {TEXT-DECORATION: none}
			A:hover {COLOR: blue; TEXT-DECORATION: underline} -->
		</STYLE>
		<title>Proceso de Establecimiento</title>
    </head>

	<body>
		<h1>Ficha de Proceso de Establecimiento</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Proceso de Establecimiento</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procEstablecimientoForm" property="procEstablecimiento.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody><tr>
          				<td class="travelgray" align="left" height="10" width="70"><b>Establecimiento Procesado</b></td>
          				<td class="travelinfo" align="left">         				
	          				<logic:present name="procEstablecimientoForm" property="procEstablecimiento.establecimiento" >    	                       
    	                        <html:link page="/buscarEstablecimiento.do?idActivo=true" paramId="id" paramName="procEstablecimientoForm" paramProperty="procEstablecimiento.establecimiento.id" >
        	                        <bean:write name="procEstablecimientoForm" property="procEstablecimiento.establecimiento.id"/>
            	                    -
                	                <bean:write name="procEstablecimientoForm" property="procEstablecimiento.establecimiento.nombreContacto"/>
                    	        </html:link>
                        	</logic:present>
	                        <logic:notPresent name="procEstablecimientoForm" property="procEstablecimiento.establecimiento" >
    	                        <bean:message key="dataNotFound" />
        	                </logic:notPresent>
          				</td>
          				
          				<td class="travelinfo" align="right">         				
				        </td> </tr>   
				    <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>   				
          			
          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Número de Lote</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
          				<logic:present name="procEstablecimientoForm" property="procEstablecimiento.procLote" >
                            <html:link page="/buscarProcLote.do" paramId="procLoteId" paramName="procEstablecimientoForm" paramProperty="procEstablecimiento.procLote.id" >
                                <bean:write name="procEstablecimientoForm" property="procEstablecimiento.procLote.numLote"/>
                            </html:link>
                        </logic:present>
                        <logic:notPresent name="procEstablecimientoForm" property="procEstablecimiento.procLote" >
                            <bean:message key="dataNotFound" />
                        </logic:notPresent>
                        </td>
          			</tr>          				      				
                </tbody>
                </table>
                
                </td></tr>
      </tbody></table>           
    

  <div><br><br><br><br></div> 




<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Proceso de Animales</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Proceso de Establecimiento</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procEstablecimientoForm" property="procEstablecimiento.id"/></td>
						 </tr>	
					  </tbody>
				</table>
			</td>
			</tr>					        	
		         	<tr>
		          	<td>
			          	
		
    					<display:table name="procEstablecimientoForm.procEstablecimiento.procAnimals" align="center" class="its2" width="100%" id="table4" >
       
       						<display:column property="id"
	                	        title="ID"
	   	                	    href="buscarProcAnimal.do"
    	                    	paramId="procAnimalId"
	        	                paramProperty="id" />
					        <display:column property="animal.registroID"
    		                    title="Animal"
            		            href="buscarAnim.do?method=porId"
		                        paramId="animalId"
        		                paramProperty="animal.id" />
							<display:column property="animal.nombre"
		                        title="Nombre"
        		                href="buscarAnim.do?method=porId"
                		        paramId="animalId"
                        		paramProperty="animal.id" />                        
					        <display:setProperty name="basic.msg.empty_list" >
					        </display:setProperty>
					    </display:table>
	    		</td></tr>
      	</tbody>
      </table>


  <div><br><br><br><br></div> 


<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Eventos del Proceso de Establecimiento</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Proceso de Establecimiento</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procEstablecimientoForm" property="procEstablecimiento.id"/></td>
			          	 </tr>	
						    </tbody>
						</table>
					</td>
				</tr>					        	
		          	<tr>
		          	<td>
					    	<display:table name="procEstablecimientoForm.procEstablecimiento.procEvtEsts" align="center" class="its2" width="100%" id="table5" >
						        <display:column property="id" title="ID" />
						        <display:column property="idEvtECLO" title="idEvtECLO" />
						        <display:column title="Evento"
			                        href="construction.do"
			                        paramId="eventoId"
            			            paramProperty="evtEstablecimiento.id" >
						            <pre><bean:write name="table5" property="evtEstablecimiento.id" /></pre>
						        </display:column>
						        <display:column title="Mensajes">
						            <% String nestedName="procEstablecimientoForm.procEvtEstArrayList.item[" + (table5_rowNum.intValue() -1)+ "].procMsgsses"; %>
						            <display:table name="<%=nestedName%>" id="child" class="simple sublist">
						                <display:column title="ID" property="id" />
						                <display:column title="Nivel de Error" property="nivelError" />
						                <display:column title="Informacion" property="informacion" class="columnaNoventa" />
						            </display:table>
						        </display:column>
						        <display:setProperty name="basic.msg.empty_list" >
						        </display:setProperty>
						    </display:table>					
      		</td></tr>
      	</tbody>
      </table>



  <div><br><br><br><br></div> 



<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Mensajes Obtenidos del Proceso del Establecimiento</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Proceso de Establecimiento</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procEstablecimientoForm" property="procEstablecimiento.id"/></td>
			          </tr>	
						    </tbody>
						</table>
					</td>
				</tr>					        	
		          	<tr>
		          	<td>
	
						    <display:table name="procEstablecimientoForm.procEstablecimiento.procMsgsses" align="center" class="its2" width="100%" id="table3" >
		      
        						<display:column property="id" title="ID" />
						        <display:column property="nivelError" title="Nivel de Error" />
						        <display:column property="informacion" title="Informacion" />
						        <display:setProperty name="basic.msg.empty_list" >
						        </display:setProperty>
						    </display:table>						
      		</td></tr>
      	</tbody>
      </table>

</body>

</html:html>


