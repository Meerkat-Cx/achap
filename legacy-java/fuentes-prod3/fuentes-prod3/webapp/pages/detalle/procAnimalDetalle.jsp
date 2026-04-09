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
		<title>Proceso de Animal</title>
    </head>

	<body>
		<h1>Ficha de Proceso de Animal</h1>


		<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
			<tbody>
		  		<tr valign="bottom">
		    		<td class="segmenthd" align="left">Información General</td>
			    </tr>
			</tbody>
		</table>


		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
			<tbody>
				<tr>
		    		<td valign="top">
		      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
		        			<tbody>
		        				<tr>
		          					<td class="cmthd">Proceso de Animal</td>
						          	<td class="cmthd" align="right">Identificador: <bean:write name="procAnimalForm" property="procAnimal.id"/></td>
						        </tr>
		      				</tbody>
		      			</table>      		
		      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
		        			<tbody>
		        				<tr>
		          					<td class="travelgray" align="left" height="10" width="70"><b>Animal Procesado</b></td>
			          				<td class="travelinfo" align="left">         				
			          					<logic:present name="procAnimalForm" property="procAnimal.animal" >
			                            <html:link page="/buscarAnim.do?method=porId" paramId="animalId" paramName="procAnimalForm" paramProperty="procAnimal.animal.id" >                                
		    	                            <bean:write name="procAnimalForm" property="procAnimal.animal.nombre"/>
		        	                    </html:link>
		            	            	</logic:present>
		                	       		<logic:notPresent name="procAnimalForm" property="procAnimal.animal" >
		                    	        	<bean:message key="dataNotFound" />
		                        		</logic:notPresent>
		          					</td>          				
		          				</tr>   
							    <tr>
		    	      				<td class="travelgray" align="left" height="10"></td>
						          	<td class="terminalinfo" align="left"></td>
						        </tr>   			          				
		          				<tr>
			          				<td class="travelgray" align="left" height="10"><b>Número de Proceso</b><spacer type="block" height="1" width="1"></td>
		    	      				<td class="travelinfo" align="left">
				          				<logic:present name="procAnimalForm" property="procAnimal.procEstablecimiento" >
		        		                    <html:link page="/buscarProcEstablecimiento.do" paramId="procEstablecimientoId" paramName="procAnimalForm" paramProperty="procAnimal.procEstablecimiento.id" >
		                		                <bean:write name="procAnimalForm" property="procAnimal.procEstablecimiento.id"/>
		                        		    </html:link>
				                        </logic:present>
		        		                <logic:notPresent name="procAnimalForm" property="procAnimal.procEstablecimiento" >
		                		            <bean:message key="dataNotFound" />
		                        		</logic:notPresent>
			                        </td>
		    	      			</tr>          				      				
		                	</tbody>
        		        </table>
					</td>
				</tr>
			</tbody>
		</table>           
    

		<div><br><br><br><br></div> 




		<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
			<tbody>
				<tr valign="bottom">
		    		<td class="segmenthd" align="left">Eventos del Proceso del Animal</td>
   				</tr>
		   	</tbody>
		</table>
		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
	  		<tbody>
	  			<tr>
		    		<td valign="top">
      					<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        					<tbody>
        						<tr>
			          				<td class="cmthd">Proceso de Animal</td>
						          	<td class="cmthd" align="right">Identificador: <bean:write name="procAnimalForm" property="procAnimal.id"/></td>
						        </tr>	
						    </tbody>
						</table>
					</td>
				</tr>					        	
					          	<tr>
					          	<td>
								    <display:table name="procAnimalForm.procAnimal.procEvtAnimals" align="center" class="its2" width="100%" id="procEvtAnimal" >
									    <display:column property="id" title="ID"/>
								        <display:column property="idEvtECLO" title="idEvtECLO" />
					        			<display:column title="Evento"
					                        href="construction.do"
			        	        	        paramId="eventoId"
			            		            paramProperty="evtAnimal.id" >
								            <pre><bean:write name="procEvtAnimal" property="evtAnimal.id"  /></pre>
					    			    </display:column>
								        <display:column title="Mensajes">
								            <% String nestedName="procAnimalForm.procEvtAnimalArrayList.item[" + (procEvtAnimal_rowNum.intValue() -1)+ "].procMsgsses"; %>
					    			        <display:table name="<%=nestedName%>" id="child" class="its2"> 
				    	            			<display:column title="Nivel de Error" property="nivelError" />
							        	        <display:column title="Información" property="informacion"/>
								            </display:table>
					    			    </display:column>
								        <display:setProperty name="basic.msg.empty_list" >
								        </display:setProperty>
								    </display:table>
								</td>
								</tr>
      						</tbody>
		      			</table>
      				


  		<div><br><br><br><br></div> 


		<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
			<tbody>
				<tr valign="bottom">
				    <td class="segmenthd" align="left">Mensajes Obtenidos del Proceso del Animal</td>
				</tr>
			</tbody>
		</table>

		<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  			<tbody>
  				<tr>
    				<td valign="top">
      					<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        					<tbody>
        						<tr>
			          				<td class="cmthd">Proceso de Animal</td>
						          	<td class="cmthd" align="right">Identificador: <bean:write name="procAnimalForm" property="procAnimal.procEstablecimiento.id"/></td>
						        </tr>	
						    </tbody>
						</table>
					</td>
				</tr>					        	
					          	<tr>
					          	<td>
    								<display:table name="procAnimalForm.procAnimal.procMsgsses" align="center" class="its2" width="100%" id="table3" >
									    <display:column property="id" title="ID" />
								        <display:column property="nivelError" title="Nivel de Error" />
								        <display:column property="informacion" title="Informacion" />
					    			    <display:setProperty name="basic.msg.empty_list" >
								        </display:setProperty>
								    </display:table>
										          	
      						
      				</td>
      			</tr>
	      	</tbody>
    	</table>

	</body>
</html:html>
