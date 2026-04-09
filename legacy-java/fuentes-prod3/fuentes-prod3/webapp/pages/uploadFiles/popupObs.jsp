<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<html:form  action="/processFileAction" enctype="multipart/form-data">
	<input type="hidden" name="method" value="process">
	<input type="hidden" name="indice" value="<c:out value="${indice}"/>" />
	<input type="hidden" name="ecloId2" value="<c:out value="${ecloId2}"/>" />
	<html:hidden name="uploadFileForm" property="idP" />
	<html:hidden name="uploadFileForm" property="indiceCombo" />
	<html:hidden name="uploadFileForm" property="ecloId" />
				<TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
                        	Procesamiento de Archivos</FONT>
                        </TD>
                        <td class=Titulo><a href="img/ayuda.pdf"><img src="img/help.gif" border="0" title="Ayuda"/></a></td>
                      </TR>
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

				
<table width="94%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="4" align="center" >
			<TR>
					
					<TD  ALIGN="center" class="celdaLabel"><b>Observaciones</b></TD>
						<TD   ALIGN="center" class="celdaInput">
						
							<html:textarea property="comments"  styleClass="textBox" rows="4" cols="30" onkeypress="return imposeMaxLength(this, 255);"/>
						</TD>
				</TR>
		</TABLE>

<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center">
					<td align="center"><input type="button" value="Aceptar" class="botones" onclick="setMethod('process','<c:out value="${requestScope.id}"/>')"></td>				
					<td align="center"><input type="button" value="Cancelar" class="botones" onclick="cancelar()"></td>
			</TR>	
		</table>
		
		
		<script language="JavaScript">
		
		function imposeMaxLength(Object, MaxLen)
			{
  			return (Object.value.length <= MaxLen);
		}
		
		
		
		function setMethod(valor,otro){
			
				document.forms[0].idP.value = otro;
				document.forms[0].method.value = valor;
				document.forms[0].indiceCombo.value = document.forms[0].elements["indice"].value;
				document.forms[0].ecloId.value = document.forms[0].elements["ecloId2"].value;
    			//document.parentWindow.location.href = '<html:rewrite page="/pages/uploadFiles/ConfirmarProceso.jsp"/>';
    			document.forms[0].submit();
    			//window.close();
    		
    	}
    	
    	function cancelar(){
    		var mUrl = "processFileAction.do?method=init&indice="+document.forms[0].elements["indice"].value+"&ecloId2="+
															document.forms[0].elements["ecloId2"].value;
			window.location.href = mUrl;
    	}
    	
	</script>  
</html:form>


	
