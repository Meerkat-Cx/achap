<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/altaCalificacionAction.do" method="post" onsubmit="return validate();">
	<script language='javascript' src="calendar/popcalendar.js"></script>
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarAnimal">
		   <html:hidden property="error" value="${requestScope.error}"/>	
	 
		<html:javascript formName="calificacionForm"/>
	 	
	 	
		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Valores Generales de Boletas</FONT></TD>
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
   	<br/>
  
    
            
   	<!-- datos de entrada -->
   	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Fecha: </TD>
			<TD class="celdaInput">
			 	<input name="fecha" type="text" id="fecha" value="<c:out value="${calificacionForm.fecha}"/>" onclick="popUpCalendar(this, form.fecha, 'dd/mm/yyyy');" size="10">
            </TD>
		</TR>
		<TR>
			<TD class="celdaLabel">Identificador de Tambo: </TD>
			<TD class="celdaInput">
                <html:text size="30" name="calificacionForm" property="idEstab" styleClass="Input10porc"/>
               
	            <input type="button" value="Buscar Tambo" class="botones" onclick="setMethod('initBuscarEstablecimiento')">            
            </TD>
		</TR>
		<tr>
			<td class="celdaLabel"><strong>Califcador:&nbsp;</strong></td>
			<td class="celdaInput" >
			<html:select property="idCalif"  style="width:300px">
					
					<html:options collection="califcadores"  property="id" labelProperty="conjunto"/>
				</html:select>
				</td>					
		</tr>
		<tr>
		<td class="celdaLabel"><strong>Modelo:&nbsp;</strong></td>
		<td class="celdaInput" >
							<html:select property="numModel" tabindex="1" style="width:40px">
									<html:option value="2"  >2</html:option>
									<html:option value="3"  >3</html:option>
										
							</html:select>
			</td>
		</tr>
	</table>	
   
            				
            
 <c:out value="${requestScope.error}"/>
	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                     <tr> 
                    <td align="center">
							
							<html:submit styleClass="botones">
								<bean:message key="submit"/>
							</html:submit>
							<html:cancel styleClass="botones" onclick="bCancel=true;" >
								<bean:message key="cancel"/>
							</html:cancel>
					</TD>
				</TR>	
		</table>
        
		
		
		
		
		<script language="JavaScript">
		
		
		function validate() {
			// tendria que setear el metodo por las dudas
			return validateCalificacionForm(document.forms[0]);
		}
		
		
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    	}
    	   initializeMenus();	
	</script>    
	
</html:form>