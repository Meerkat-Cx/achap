<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/tags/jstl-c" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>


<html:form action="/bajaControlAnimalAction.do" >

		<!-- Titulo de la pagina -->
		 
		 

		 <TABLE width="100%" cellPadding=2 cellSpacing=2 bgcolor="#eff3e3">
                      <TR> 
                        <td width="100%"  align="center" class="Titulo">Baja de Control Animal</td>
                      </TR>
                      
        </TABLE>
   		<!-- Fin Titulo de la pagina -->
   	<br/>
   	
   	<table width="100%" class="bordeGris"  cellSpacing="0" cellPadding="2" align="center" >
		<tr>
		<TD class="celdaLabel" align="center" >Id:</TD>
								<TD class="celdaLabelSinAlign" align="center">
								<html:hidden name="controlAnimalForm" property="id"/>
								<c:out value="${controlAnimalForm.id}"/>
						        </td>
		</tr>
		<tr>
		<TD class="celdaLabel" align="center" >Fecha del Control:</TD>
								<TD class="celdaLabelSinAlign" align="center">
								<html:hidden name="controlAnimalForm" property="fecha"/>
								<fmt:formatDate pattern="dd/MM/yyyy" value="${controlAnimalForm.fecha}" /></td>
								
								
						        </td>
		</tr>
		<tr>
		<TD class="celdaLabel" align="center" >RP del animal:</TD>
								<TD class="celdaLabelSinAlign" align="center">
								<html:hidden name="controlAnimalForm" property="rp"/>
								<c:out value="${controlAnimalForm.rp}"/>
						        </td>
		</tr>
		<tr>
		<TD class="celdaLabel" align="center" >Cantidad de ordenies:</TD>
								<TD class="celdaLabelSinAlign" align="center">
								<html:hidden name="controlAnimalForm" property="ordenies"/>
								<c:out value="${controlAnimalForm.ordenies}"/>
						        </td>
		</tr>
		
		<tr>
			<td class="celdaLabel" align="center">Tambo:</td>
			<td class="celdaLabelSinAlign" align="center">
				<c:out value="${controlAnimalForm.nombreTambo}"/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel" align="center">Establecimiento:</td>
			<td class="celdaLabelSinAlign" align="center">
				<c:out value="${controlAnimalForm.nombreEstablecimiento}"/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel" align="center">Propietario del Tambo:</td>
			<td class="celdaLabelSinAlign" align="center">
				<c:out value="${controlAnimalForm.nombrePropietario}"/>
			</td>
		</tr>
		<tr>
			<td class="celdaLabel" align="center">Cantidad Leche:</td>
			<td class="celdaLabelSinAlign" align="center">
				<c:out value="${controlAnimalForm.cantidadLeche}"/>
			</td>
		</tr>
		
		
		 
		 
 </table>	 
 <table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
                      <tr> 
                        <td align="center">
					<td align="center">
					<td align="center"><input type="button" value="Ejecutar Baja" class="botones" onclick="ejecutarBaja()"></td>				
					<td align="center"><input type="button" value="Cancelar" class="botones" onclick="window.location='listarControlAnimalAction.do?method=init'"></td>
			</TR>	
		</table>
	 
	<script language="JavaScript">
	
		
	function ejecutarBaja(){
    	var mUrl="bajaControlAnimalAction.do?method=bajaEvento&id="+document.forms[0].id.value ;
    	window.location.href = mUrl;
    }
			
   
		
    	
	</script>    
</html:form>