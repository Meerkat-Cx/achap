<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-nested" prefix="nested"%>


<html:form action="/editarRazaValidate.do" method="post" enctype="multipart/form-data" > 
			<html:javascript formName="razaForm"/>
			<input type="hidden" name="method" value="init">
			
			
		<!-- Titulo de la pagina -->
	<table width="100%" cellSpacing="0" cellPadding="2" align="center" bgcolor="#eff3e3">
    	<tr> 
            <TD class=Titulo><FONT color=#529b28><IMG height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>
	                        	Creación de Raza</FONT>
	        </TD>
            <td><div align=right></div></td>
        </tr>
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
	</table>
		<br/>

		<table width="100%" class="bordeGris" cellSpacing="0" cellPadding="2" align="center" >
		
			<tr> 
	    	   	<td height="25" bgColor=#eff3e3 class=SubTitulo colspan="2">
	        		<img height=15 hspace=2 src="pages/assets/images/flecha_titulos3a.gif" width=11 align=absMiddle>&nbsp;Datos de la Raza
	            </td>
	        </tr>						
				<TR>
							<TD class="celdaLabel">
								Id. de Raza: <span class="required">*</span>
							</TD>
							<TD class="celdaInput">
								<html:text size="4" name="razaForm" property="id" styleClass="Input100porc" onkeyup="verifStr(this)"/><br/>
			            	</TD>
				</TR>			
				<TR>
						<TD class="celdaLabel">
							Nombre de Raza: <span class="required">*</span>
						</TD>
						<TD class="celdaInput">
							<html:text size="45" name="razaForm" property="nombre" styleClass="Input100porc"/><br/>
		            	</TD>
				</TR>
				<TR>
						<TD class="celdaLabel">
							Especie:<span class="required">*</span>
						</TD>
						<TD class="celdaInput">
							<html:select name="razaForm" property="idEspecie" size="1" styleClass="Input100porc">
								<html:options collection="listaEspecies" property="id" labelProperty="nombre" />
							</html:select>	
						</TD>
				</TR>
				<TR>
						<TD class="celdaLabel">
							Es Cruza?: 							
						</TD>
						<TD class="celdaInput">
							<html:checkbox property="escruza"/>
							<br/>
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Es Desconocida?: 
						</TD>
						<TD class="celdaInput">
							<html:checkbox  property="esdesconocido"/>
							<br/>
		            	</TD>
					</TR>
					<TR>
						<TD class="celdaLabel">
							Foto/Escudo: 
						</TD>				
						<TD class="celdaInput"><html:file size="36" property="foto" styleClass="Input100porc"/></td>	
					</TR>
				<TR>
						<TD class="celdaLabel">
							Categoria Pura: <span class="required">*</span>
						</TD>
						<TD class="celdaInput">
							<html:text size="3" name="razaForm" property="categoriaPura" styleClass="Input100porc" onkeyup="verifStr(this)"/><br/>
		            	</TD>
				</TR>
			</table>
			<table width="90%" border="0" align="center" cellpadding="10" cellspacing="0">
		    	<tr> 
		        	<td align="center">
		        		<input id="aceptar" type="button" value="Aceptar" class="botones" onclick="agregar()">
					</td>
					<td align="center">
		        		<input id="cancelar" type="button" value="Cancelar" class="botones" onclick="cancelar()">
					</td>
				</tr>	
			</table>
		</tr>	
	</table>
	</html:form>


<script>


	function agregar() {
	
		var ident=document.forms[0].elements["id"].value;
		if(ident == ""){
			alert("El id no puede ser vacio. Es un requisito obligatorio");
			 return;
		}
		
		if(ident.length > 4){
			alert("Es id no puede exceder los 4 caracteres");
			return;
		}
		if(!document.forms[0].elements["escruza"].checked && !document.forms[0].elements["esdesconocido"].checked){
			var cat=document.forms[0].elements["categoriaPura"].value;
			if( cat == ""){
				alert("La categoria no puede ser vacia. Es un requisito obligatorio");
				 return;
			}
			if(cat.length > 3){
				alert("La categoria no puede exceder los 3 caracteres");
				return;
			}
		}
		
		if( document.forms[0].elements["nombre"].value == ""){
			alert("El nombre no puede ser vacio. Es un requisito obligatorio");
			 return;
		}
		
  		document.forms[0].method.value = "aceptar";
			document.forms[0].submit();
	}
	
	function cancelar() {
		var mUrl = "editarRazaValidate.do?method=cancelar";
		window.location.href = mUrl;
	}
	
	function verifStr(n){
		permitidos=/[^a-z, A-Z]/;
		if(permitidos.test(n.value)){
		alert("Sólo se puede ingresar letras sin caracteres especiales ni números");
		n.value="";
		n.focus();
		}
	
	}
</script>

