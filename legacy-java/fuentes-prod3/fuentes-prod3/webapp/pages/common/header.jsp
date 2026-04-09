<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>

 <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
         	<html:img page="/pages/assets/images/header.jpg"
             width="998" height="139" border="0"/>
        </td>
     </tr>
     <tr>
		<TD colspan="2" class="SubTitulo" align="RIGHT">
			<c:out value="${sessionScope.CURRENTUSER.username}"/>/<c:out value="${sessionScope.CURRENTUSER.rol.nombre}"/>
		</TD>          
     </tr>
 </table>

