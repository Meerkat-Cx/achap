<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html:form action="/listarControlAnimalAction.do" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="listar">
		 <input type="hidden" name="mensaje" value="${requestScope.mensaje}">
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                       <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	B&uacute;squeda&nbsp;de&nbsp;Control&nbsp;Animal&nbsp;por&nbsp;animal</FONT>
                        </TD>
                       
                     
                      <TR> 
                        <TD class=texto4 colSpan=2> <DIV align=right> 
                            <TABLE style="BORDER-TOP: #529b28 1px solid" cellSpacing=0 cellPadding=0 width="100%" border=0>
                              <TR> 
                                <TD width="50%" bgColor=#529b28><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                                <TD width="50%"><IMG height=2 src="pages/assets/images/pixel.gif" width=2></TD>
                              </TR>
                            </TABLE>
                          	</DIV>
                        </TD>
                      </TR>
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>
	<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		<tr>
			<td class="celdaLabel"><strong>Tipo de registro:&nbsp;</strong></td>
				<td class="celdaInput">
					<html:select property="tipoReg" styleClass="formfields" style="width:160px">
							<html:option value="RC"  >RC</html:option>
							<html:option value="HBA"  >HBA</html:option>
							
						</html:select>
					</td>
		</tr> 

		<TR>
			<TD class="celdaLabel"><strong>Numero de registro:</strong> </TD>
			<TD class="celdaInput">
                <html:text size="45" name="animalForm" title="numero de registro"  property="numReg" styleClass="Input10porc"/>
            </TD>
		</TR>
		<tr>
			<td class="celdaLabel"><strong>Raza del Animal:&nbsp;</strong></td>
			<td class="celdaInput">
			<html:select property="raza" styleClass="formfields" style="width:160px">
					
					<html:options collection="razas" property="id" labelProperty="nombre"/>
				</html:select>
				</td>					
		</tr>
		
	</table>
	
	 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center"><input type="button" value="Buscar" class="botones" onclick="setMethod('listar')"></td>
					<td align="center">
							<input id="botonLimpiar" type="button" value="Limpiar" 
								class="botones" onclick="limpiar()">

						</td>
			</TR>	
		</table>
<div class="justify">
    <display:table name="controles" align="center"  class="its" sort="list" id="item" pagesize="8" requestURI="/listarControlAnimalAction.do?method=listar" >
        <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Controles Animal</font>
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
        <display:column align="left" title="ID sicel3" property="id" href="bajaControlAnimalAction.do?method=verControl" paramId="id" paramProperty="id"/>
         <display:column align="left" title="id ordenie" property="idOrdenie" />
	      <display:column  title="Fecha"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.fecha}" />
        </display:column>   
	    
         <display:setProperty name="basic.msg.empty_list" >
            <!-- <h1><bean:message key="displayTag.basic.msg.empty_list" /></h1> -->
            	<table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">
            				 <!-- <bean:message key="displayTag.basic.msg.empty_list"/>-->
            				 <c:out value="${requestScope.mensaje}"/>	
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
		function limpiar() {
			var nomElem = document.getElementById("numReg");
			nomElem.value = "";
		}
    	
	</script>    
	
</html:form>

