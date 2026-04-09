<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:form action="/buscarCentroDeComputo.do" method="post" focus="idC" >
		<!-- Titulo de la pagina -->
		 <input type="hidden" name="method" value="ejecutar">
		 <TABLE width="100$%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 
            src="pages/assets/images/flecha_titulos3a.gif" 
            width=11 align=absMiddle>Descarga&nbsp;de&nbsp;Padrones</FONT></TD>
                        <TD> <DIV align=right></DIV></TD>
                      </TR>

        </TABLE>
   		<!-- Fin Titulo de la pagina -->
</br>
	

	

<div class="justify">
    <display:table name="archivos" align="center" pagesize="20" id="item" sort="page" class="its" width="100%" requestURI="/buscarCentroDeComputo.do?method=ejecutar"  >
       <display:caption>
            <table width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
            	<tr> 
                	<td class=Titulo>
                		<font color=#529b28><img height=15 hspace=2 src="/web-1.0/pages/assets/images/flecha_titulos3a.gif" 
				            width=11 align=absMiddle>&nbsp;Padrones</font>
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
		<display:column title="nombre" property="name" href="edicionAnimalPedigreeAction.do?method=descargarArchivo" paramId="name" paramProperty="name"/>
		<display:column title="ultima modificación"  property="modificacion"/>
 		<display:setProperty name="basic.msg.empty_list" >
           <table width="80%" bgcolor="#eff3e3" class="bordeGris" cellSpacing="0" cellPadding="10" align="center" >
            		<TR>
            			<td class="TextoNegro">Nada para mostrar
            				
            			</td>
            		</TR>
            	</table>
        </display:setProperty>
    </display:table>
</div>	
	<script language="JavaScript">
	
	
	</script>    
</html:form>