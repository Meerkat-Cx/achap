<?xml version="1.0"?>
<xsl:stylesheet xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fox="http://xml.apache.org/fop/extensions" version="1.0">
	<xsl:template match="lote">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first" page-height="29.7cm" page-width="21cm" margin-top="1cm" margin-bottom="2cm" margin-left="2.0cm + 		0.5cm" margin-right="(5cm * 1cm) div 2cm">
					<fo:region-body margin-top="1.5cm" margin-bottom="1.5cm"/>
					<fo:region-before extent="2.5cm"/>
					<fo:region-after extent="1.5cm"/>
				</fo:simple-page-master>
				<!-- layout for the other pages -->
				<fo:simple-page-master master-name="rest" page-height="abs(-30cm + .3cm)" page-width="(10cm * 2) + 1cm" margin-top="round(.5) * 1cm" margin-bottom="round(2.4) * 1cm" margin-left="2.5 * 1cm" margin-right="5.5cm - 3cm">
					<fo:region-body margin-top="1.5cm" margin-bottom="1.5cm"/>
					<fo:region-before extent="2.5cm"/>
					<fo:region-after extent="1.5cm"/>
				</fo:simple-page-master>
				<fo:page-sequence-master master-name="basicPSM">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference master-reference="first" page-position="first"/>
						<fo:conditional-page-master-reference master-reference="rest" page-position="rest"/>
						<!-- recommended fallback procedure -->
						<fo:conditional-page-master-reference master-reference="rest"/>
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="basicPSM">
				<fo:static-content flow-name="xsl-region-before">
					<fo:block text-align="start" font-size="10pt" font-family="serif" line-height="1em + 4pt">
					     SICEL - Mensajes
					</fo:block>
					<fo:block text-align-last="justify">
						<fo:leader leader-pattern="rule" color="red"/>
					</fo:block>
				</fo:static-content>
				<fo:static-content flow-name="xsl-region-after">
					<fo:block text-align-last="justify">
						<fo:leader leader-pattern="rule" color="red"/>
					</fo:block>
					<fo:block text-align="center" font-size="10pt" font-family="serif" line-height="1em + 4pt">
					     Página - <fo:page-number/>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:call-template name="titulo"/>
						<fo:block start-indent="1cm" space-after="25pt">
							<xsl:apply-templates select="informante"/>
							<xsl:call-template name="nombreECLO"/>
							<xsl:apply-templates select="IDLoteInte"/>
							<xsl:apply-templates select="IDLoteSicel"/>
							<xsl:apply-templates select="tiempos"/>
						</fo:block>
						<xsl:apply-templates select="rdos">
							<xsl:with-param name="texto">
								Lote
							</xsl:with-param>
						</xsl:apply-templates>
						<xsl:apply-templates select="estabs"/>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="titulo">
		<xsl:call-template name="Estilo_Titulo">
			<xsl:with-param name="texto">
				ACHA - SICEL
			</xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="Estilo_Subtitulo">
			<xsl:with-param name="texto">
				Observaciones de Eventos del Lote
			</xsl:with-param>
		</xsl:call-template>
		<fo:block text-align-last="justify">
			<fo:leader leader-pattern="rule" color="red"/>
		</fo:block>
	</xsl:template>
	<xsl:template match="informante">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre">
						Informante (ECLO) : 
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>
	<xsl:template name="nombreECLO">
		<fo:block>
			<xsl:for-each select="rdos/rdo">
				<xsl:if test="codigoMsg='NECLO'">
					<xsl:call-template name="Estilo_Nombre_Valor">
						<xsl:with-param name="nombre">
							ECLO: 
						</xsl:with-param>
						<xsl:with-param name="valor" select="vals/val[1]"/>
					</xsl:call-template>
				</xsl:if>
			</xsl:for-each>
		</fo:block>
	</xsl:template>
	<xsl:template match="IDLoteInte">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre">
					Número de Lote:
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>
	<xsl:template match="IDLoteSicel">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre">
					ID del lote en SICEL: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>
	<xsl:template match="tiempos">
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text">
					Fecha de Recepción:
				</xsl:with-param>
			</xsl:call-template>
			<xsl:choose>
				<xsl:when test="tEnvi">
					<xsl:call-template name="Estilo_6">
						<xsl:with-param name="text">
							<xsl:value-of select="tEnvi"/>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="Estilo_6">
						<xsl:with-param name="text">
									No Existente.
								</xsl:with-param>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text">
					Fecha de Proceso:
				</xsl:with-param>
			</xsl:call-template>
			<xsl:choose>
				<xsl:when test="tProcFin">
					<xsl:call-template name="Estilo_6">
						<xsl:with-param name="text">
							<xsl:value-of select="tProcFin"/>
						</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="Estilo_6">
						<xsl:with-param name="text">
									No Existente.
								</xsl:with-param>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
	</xsl:template>

	
	
	<xsl:template match="rdos">
		
		<xsl:param name="texto">No hay texto ingresado</xsl:param>
		<xsl:param name="start-indent">0cm</xsl:param>
		
		<xsl:for-each select="rdo">
		 <xsl:variable name="errores" select="nivelErr"/> 
			<xsl:if test="($errores)> 50">
				<fo:block space-before="10pt">
					<xsl:attribute name="start-indent"><xsl:value-of select="$start-indent"/></xsl:attribute>
					<fo:table width="100%" table-layout="fixed" border="solid 1px black">
						<fo:table-column column-width="proportional-column-width(1)" column-number="1"/>
						<fo:table-column column-width="proportional-column-width(1)" column-number="2"/>
						<fo:table-column column-width="proportional-column-width(3)" column-number="3"/>
						<fo:table-header>
							<fo:table-row>
								<fo:table-cell border="solid black 1px" number-columns-spanned="3">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_1">
											<xsl:with-param name="text">
												<xsl:value-of select="$texto"/>
											</xsl:with-param>
										</xsl:call-template>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell border="solid black 1px">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_5">
											<xsl:with-param name="text">
												Nivel del Error
											</xsl:with-param>
										</xsl:call-template>
										<fox:continued-label>
											<xsl:call-template name="Estilo_5">
												<xsl:with-param name="text">
													(cont.)
												</xsl:with-param>
											</xsl:call-template>
										</fox:continued-label>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid black 1px">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_5">
											<xsl:with-param name="text">
												Código Mensaje
											</xsl:with-param>
										</xsl:call-template>
										<fox:continued-label>
											<xsl:call-template name="Estilo_5">
												<xsl:with-param name="text">
													(cont.)
												</xsl:with-param>
											</xsl:call-template>
										</fox:continued-label>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid black 1px">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_5">
											<xsl:with-param name="text">
												Información Adicional
											</xsl:with-param>
										</xsl:call-template>
										<fox:continued-label>
											<xsl:call-template name="Estilo_5">
												<xsl:with-param name="text">
													(cont.)
												</xsl:with-param>
											</xsl:call-template>
										</fox:continued-label>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-header>
						<fo:table-body text-align="start">
							<fo:table-row>
								<xsl:if test="position() mod 2 = 0">
									<xsl:attribute name="background-color">#def</xsl:attribute>
								</xsl:if>
								<fo:table-cell border="solid 1px black">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_6">
											<xsl:with-param name="text">
												<xsl:value-of select="nivelErr"/>
											</xsl:with-param>
										</xsl:call-template>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid 1px black">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_6">
											<xsl:with-param name="text">
												<xsl:value-of select="codigoMsg"/>
											</xsl:with-param>
										</xsl:call-template>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid 1px black">
									<fo:block text-align="center">
										<xsl:call-template name="Estilo_6">
											<xsl:with-param name="text">
												<xsl:value-of select="info"/>
											</xsl:with-param>
										</xsl:call-template>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
			</xsl:if>
		</xsl:for-each>				
	</xsl:template>
	<xsl:template match="mierda">
			<fo:block break-before="page"/>
					<xsl:apply-templates select="rdos">
									<xsl:with-param name="text">
										<xsl:value-of select="texto"/>
									</xsl:with-param>						
					</xsl:apply-templates>
	</xsl:template>
	 
	<xsl:template match="estabs">
		<xsl:if test="contains(descendant-or-self, rdo)">
			<xsl:for-each select="estab">
				
				<xsl:apply-templates select="rdos">
					<xsl:with-param name="texto">
						Estab
						<xsl:value-of select="IDEstab"/>
					</xsl:with-param>
				</xsl:apply-templates>
				<xsl:for-each select="descendant::altas">
					<xsl:apply-templates select="rdos">
						<xsl:with-param name="texto">
							Evento Alta
							<xsl:value-of select="IDEvt"/>
						</xsl:with-param>
					</xsl:apply-templates>
				</xsl:for-each>
				<xsl:for-each select="descendant::semens">
					<xsl:apply-templates select="rdos">
						<xsl:with-param name="texto">
							Evento Semen
							<xsl:value-of select="IDEvt"/>
						</xsl:with-param>
					</xsl:apply-templates>
				</xsl:for-each>
				<xsl:for-each select="descendant::controles">
					<xsl:apply-templates select="rdos">
						<xsl:with-param name="texto">
							Evento Control
							<xsl:value-of select="IDEvt"/>
						</xsl:with-param>
					</xsl:apply-templates>
				</xsl:for-each>
				<xsl:apply-templates select="anims"/>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	<xsl:template match="anims">
		<xsl:for-each select="animal">
			<xsl:variable name="encabezado">
					Animal: 
					RP <xsl:value-of select="rp"/> - 
					Tipo <xsl:value-of select="reg/tReg"/> -
					Número <xsl:value-of select="reg/nReg"/>
			</xsl:variable>
			<xsl:apply-templates select="rdos">
				<xsl:with-param name="texto">
					<xsl:value-of select="$encabezado"/>
				</xsl:with-param>
			</xsl:apply-templates>
			<xsl:for-each select="evts/evt">
				<xsl:apply-templates select="descendant::rdos">
					<xsl:with-param name="texto">
						Evento <xsl:value-of select="name(descendant::rdos/..)"/>
						(<xsl:value-of select="descendant::IDEvt"/>)
					</xsl:with-param>
				</xsl:apply-templates>
			</xsl:for-each>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="Estilo_1">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block font-size=".3cm" font-family="sans-serif" line-height=".3cm" background-color="darkblue" color="white" text-align="center" padding-top="3pt" padding-bottom="3pt" span="none" border="solid 1px black">
			<fo:inline font-weight="bold">
				<xsl:value-of select="translate($text, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# Estilos 2
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_2">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block font-size=".3cm" font-family="sans-serif" line-height=".3cm" color="navy" text-align="center" padding-top="3pt" border="solid 1px black" span="all">
			<fo:inline font-weight="bold">
				<xsl:value-of select="translate($text, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# Estilos 3
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_3">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block font-family="sans-serif" space-after.optimum="0.2cm" space-before.optimum="0.3cm" color="navy" text-align="left" padding-top="3pt" span="none">
			<fo:inline font-weight="bold">
				<xsl:value-of select="translate($text, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# Estilos 4
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_4">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block space-after.optimum="0.2cm" space-before.optimum="0.2cm" text-align="center" color="navy" span="none">
			<fo:inline font-weight="bold" font-size=".3cm">
				<xsl:value-of select="translate($text, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# Estilos 5
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_5">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:inline font-size="9pt" font-family="sans-serif" font-weight="bold">
			<xsl:value-of select="$text"/>
		</fo:inline>
	</xsl:template>
	<!--
#########################################################
# Estilos 6
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_6">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:inline font-size=".3cm" font-family="sans-serif" padding-top="3pt" span="none">
			<xsl:value-of select="$text"/>
		</fo:inline>
	</xsl:template>
	<!--
#########################################################
# Estilos 8
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_8">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block font-size=".3cm" font-family="sans-serif" text-align="center" span="none">
			<fo:inline font-weight="normal">
				<xsl:value-of select="$text"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# Estilos 10
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_10">
		<xsl:param name="text">
			<xsl:value-of select="text()"/>
		</xsl:param>
		<fo:block space-after.optimum="0.2cm" space-before.optimum="0.2cm" text-align="center" color="navy" span="none">
			<fo:inline font-style="italic" font-size="9pt">
				<xsl:value-of select="translate($text, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
# FIN DE Estilos
######################################################### 
 -->
	<xsl:template name="Estilo_Titulo">
		<xsl:param name="texto">No hay texto ingresado</xsl:param>
		<fo:block font-size="1.3cm" font-family="sans-serif" line-height="1.3cm" color="blue" text-align="center" padding="3pt" span="none">
			<fo:inline font-weight="bold">
				<fo:external-graphic text-align="start" width="100px" src="file:../images/acha.jpg"/>
				<xsl:value-of select="translate($texto, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
				<fo:external-graphic text-align="end" height="45px" src="file:../images/sicel.jpg"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<xsl:template name="Estilo_Subtitulo">
		<xsl:param name="texto">No hay texto ingresado</xsl:param>
		<fo:block font-size="0.3cm" font-family="sans-serif" line-height="0.3cm" color="blue" text-align="center" padding="3pt" span="none">
			<fo:inline font-weight="bold">
				<xsl:value-of select="$texto"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<xsl:template name="Estilo_Nombre_Valor">
		<xsl:param name="nombre">No Existente</xsl:param>
		<xsl:param name="valor">No Existente</xsl:param>
		<xsl:call-template name="Estilo_5">
			<xsl:with-param name="text">
				<xsl:value-of select="$nombre"/>
			</xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="Estilo_6">
			<xsl:with-param name="text">
				<xsl:value-of select="$valor"/>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
</xsl:stylesheet>
