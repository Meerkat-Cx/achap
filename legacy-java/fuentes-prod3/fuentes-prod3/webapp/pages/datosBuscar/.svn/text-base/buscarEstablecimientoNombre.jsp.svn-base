<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

<html:form action="/altaCalificacionAction.do" method="post" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="buscarEstablecimientos">
		   <html:hidden property="error" value="${requestScope.error}"/>	
		  
	 
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	B&uacute;squeda de Tambo</FONT>
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
   	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<TR>
			<TD class="celdaLabel">Nombre Tambo: </TD>
			<TD class="celdaInput">
                <html:text size="45" name="calificacionForm" property="nombreEstab" styleClass="Input10porc"/>
            </TD>
		</TR>
</table>
	

		 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center">
					<td align="center"><input type="button" value="Aceptar" class="botones" onclick="setMethod('buscarEstablecimientos')"></td>				
					<td align="center"><input type="button" value="Cancelar" class="botones"onclick="volver()"></td>
			</TR>	
		</table>
		
		
				<div class="justify">
				
    <display:table name="establecimientos" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/altaCalificacionAction.do?method=buscarEstablecimientos" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Tambos</font>
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
        <display:column align="left" title="Identificador" property="id" href="altaCalificacionAction.do?method=seleccionarEstablecimiento" paramId="id" paramProperty="id"/>
	    <display:column align="left" title="Nombre" property="nombreContacto" />
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
		
		<script language="JavaScript">
		function setMethod(valor){
			document.forms[0].method.value = valor;
    		document.forms[0].submit();
    		
    	}
		function volver() {
	
		var mUrl = "altaCalificacionAction.do?method=initSetValores";
		window.location.href = mUrl;
    	}
	</script>    
	
</html:form>