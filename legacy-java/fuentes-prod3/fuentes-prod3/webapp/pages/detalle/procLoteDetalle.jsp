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
		<title>Proceso de Lote</title>
    </head>

	<body>
		<h1>Ficha de Proceso de Lote</h1>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Información General</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Lote</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procLoteForm" property="procLote.id"/></td></tr>
      				</tbody></table>
      			<table align="center" bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="468">
        			<tbody>          				
          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Número de Lote</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
          					<bean:write name="procLoteForm" property="procLote.numLote"/>                        
          			</tr>  
          			 <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>   		
          			
          			<tr>
	          			<td class="travelgray" align="left" height="10" width="70"><b>Número de Proceso</b></td>
          				<td class="travelinfo" align="left">
	          				<logic:present name="procLoteForm" property="procLote.procProces" >
    	                        <html:link page="/buscarProcProces.do" paramId="procProcesId" paramName="procLoteForm" paramProperty="procLote.procProces.id" >
        	                        <bean:write name="procLoteForm" property="procLote.procProces.id"/>
            	                </html:link>
                	        </logic:present>
                    	    <logic:notPresent name="procLoteForm" property="procLote.procProces" >
                        	    <bean:message key="dataNotFound" />
	                        </logic:notPresent>          				
          				</td>
          				
          				<td class="travelinfo" align="right">         				
				        </td> </tr>   
				    <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>   				
          			
        			<tr>
          				<td class="travelgray" align="left" height="10"><b>Eclo Procesada</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
          					<logic:present name="procLoteForm" property="procLote.eclo" >
								<html:link page="/buscaEclo.do?idActivo=true" paramId="id" paramName="procLoteForm" paramProperty="procLote.eclo.id" >								
									<bean:write name="procLoteForm" property="procLote.eclo.nombreContacto"/>
								</html:link>
							</logic:present>
							<logic:notPresent name="procLoteForm" property="procLote.eclo" >
								<bean:message key="dataNotFound" />
							</logic:notPresent></td></tr>
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>		          	
			          	
			        <tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
        			
        			
          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Fecha de Creación</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TCreacion"/></td></tr>

        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>
			          	
			        <tr>
          				<td class="travelgray" align="left" height="10"><b>Fecha de Envío</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TEnvio"/></td></tr>
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>  	
			          	
			        <tr>
          				<td class="travelgray" align="left" height="10"><b>Fecha de Recepción</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TRecep"/></td></tr>

        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr> 
			          	
			        <tr>
          				<td class="travelgray" align="left" height="10"><b>Inicio de Procesamiento</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TInicioProc"/></td></tr>	                
	                <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr> 	                
	                
	                <tr>
          				<td class="travelgray" align="left" height="10"><b>Fin de Procesamiento</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TFinProc"/></td></tr>
	                <tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr> 
	                        
	                <tr>
          				<td class="travelgray" align="left" height="10"><b>Fecha de Retorno</b><spacer type="block" height="1" width="1"></td>
          				<td class="travelinfo" align="left">
	                        <bean:write name="procLoteForm" property="procLote.TRetorno"/></td></tr>
        			<tr>
          				<td class="travelgray" align="left" height="10"></td>
			          	<td class="terminalinfo" align="left"></td></tr>

					
        			<tr>
          				<td colspan="3" align="left" bgcolor="#999999" height="1"><spacer type="block" height="1" width="1"></td></tr>
          				
          			<tr>
          				<td class="travelgray" align="left" height="10"><b>Establecimientos Procesados</b><spacer type="block" height="1" width="1"></td>          				
          				<td colspan="2" class="travelinfo" align="left">
          				
          					<div class="justify">
								<display:table name="procLoteForm.procLote.procEstablecimientos" align="center" class="its2" width="100%" id="table4" >					
								<display:column property="id"
									title="ID"
									href="buscarProcEstablecimiento.do"
									paramId="procEstablecimientoId"
									paramProperty="id" />
								<display:column property="establecimiento.nombreContacto"
									title="Establecimiento"
									href="buscarEstablecimiento.do?idActivo=true"
			                        paramId="id"
            			            paramProperty="establecimiento.id" />									
								<display:setProperty name="basic.msg.empty_list" >
								</display:setProperty>
								</display:table>
							</div>
          				</td>
                    </tr>    				
                    
      				
                </tbody>
                </table>
                
                </td></tr>
      </tbody></table>           
    

  <div><br><br><br><br></div> 


<table align="center" border="0" cellpadding="0" cellspacing="0" width="468">
  <tbody><tr valign="bottom">
    <td class="segmenthd" align="left">Mensajes Obtenidos del Lote Procesado</td></tr></tbody></table>

	<table align="center" bgcolor="#666666" border="0" cellpadding="1" cellspacing="0" width="468">
  		<tbody><tr>
    		<td valign="top">
      			<table border="0" cellpadding="2" cellspacing="0" height="100%" width="100%">
        			<tbody><tr>
          				<td class="cmthd">Procesamiento</td>
			          	<td class="cmthd" align="right">Identificador: <bean:write name="procLoteForm" property="procLote.id"/></td>
			        </tr>	
					  </tbody>
				</table>
			</td>
			</tr>					        	
         	<tr>
	          	<td>
			         
							<display:table name="procLoteForm.procLote.procMsgsses" align="center" class="its2" width="100%" id="table3" >
							
								<display:column property="id" title="ID" />
								<display:column property="nivelError" title="Nivel de Error" />
								<display:column property="informacion" title="Información" />
								<display:setProperty name="basic.msg.empty_list" >
								</display:setProperty>
							</display:table>
					
      		</td></tr>
      	</tbody>
      </table>

</body>


</html:html>









