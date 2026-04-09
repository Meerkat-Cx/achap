<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/editarPropietarioValidate.do" method="post" >
		<!-- Titulo de la pagina -->
		
		 
					
 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
								Cambiar Estado &nbsp;SRA&nbsp;de&nbsp;Propietario</FONT>
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
   	<br/>
   	
   	<!-- datos de entrada -->
   <div class="justify">
    <display:table name="expds" align="center"  class="its" sort="list" id="item" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;SRA's</font>
					</td>
					<td><div align=right></div></td>
				</tr>
				<tr> 
					<td class=texto4 colSpan=2> <div align=right> 
						<table style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 
				            cellPadding=0 width="100%" border=0>
							<tr> 
								<td width="30%" bgColor=#529b28><img height=2 
				                  src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
								<td width="70%"><img height=2 src="/web-1.0/pages/assets/images/pixel.gif" width=2></td>
                            </tr>
						</table>
                        </div></td>
				</tr>
			</table>
			<br>
		</display:caption>
		<display:column align="left" title="Raza" property="raza.nombre" />
		



		<display:column align="left" title="Numero">        
		        <a class="orange" href="javascript:ejecutar('<c:out value="${item.numero}"/>','<c:out value="${item.raza}"/>');">
		        	<c:out value="${item.numero}"/>
		        </a>
        </display:column>				
	    <display:column align="left" title="Estado">
			<c:if test="${item.fechaBaja == null}">
				<a class="orange" href="javascript:ejecutar('<c:out value="${item.numero}"/>','<c:out value="${item.raza}"/>');">
		        	<c:out value="Activo"/>
		        </a>
			</c:if>
			<c:if test="${item.fechaBaja != null}">
				<a class="orange" href="javascript:ejecutar('<c:out value="${item.numero}"/>','<c:out value="${item.raza}"/>');">
			        	<c:out value="Inactivo"/>
			    </a>
			</c:if>
		</display:column>	
		
		
		
        <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">
            				<c:out value="${requestScope.error}"/>	
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
    
</div>
<TR>
						<td class="celdaLabelSinWidth" align="center" colspan="6">
							<input type="button" value="volver" 
								class="botones" onclick="volver()">
								
						</td>
						
						
							
					</TR> 	
	
		<script language="JavaScript">
		
		function ejecutar(numero,raza){
		
		var mUrl = "editarPropietarioValidate.do?method=cambiarEstadoSRA&numero="+numero+"&raza="+raza;
		window.location.href = mUrl;
		}
		function volver() {
		var mUrl = "editarPropietarioValidate.do?method=volver";
		window.location.href = mUrl;
	}

		
	</script>    
	
</html:form>