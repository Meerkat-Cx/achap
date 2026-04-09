<?xml version="1.0"?>
<xsl:stylesheet xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fox="http://xml.apache.org/fop/extensions" version="1.0">
	<xsl:template match="lote">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="first" page-height="29.7cm" page-width="21cm" margin-top="1cm" margin-bottom="2cm" margin-left="2.0cm + 		0.5cm" 		margin-right="(5cm * 1cm) div 2cm">
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
					     SICEL - Resúmen
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
							<xsl:apply-templates select="rdos"/>
							<xsl:apply-templates select="IDLoteInte"/>
							<xsl:apply-templates select="IDLoteSicel"/>
							<xsl:apply-templates select="tiempos"/>
							<xsl:call-template name="tablaDetalleEvt"/>
						</fo:block>
						<xsl:apply-templates select="estabs"/>

						<!--
						<xsl:call-template name="testEstilos"/>
-->
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
				Resúmen de proceso de lote
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
	<xsl:template match="rdos">
		<fo:block>
			<xsl:for-each select="rdo">
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

	<xsl:template name="tablaDetalleEvt">
		<xsl:variable name="cantEvEstab" select="count(descendant::evtsEstab/descendant::alta)"/>

<!--		<xsl:variable name="cantEvEstab" select="$cantEvEstab+count(descendant::evtsEstab/descendant::semen)"/>  
fuera total todabia no hay semens. 
Si lo dejo tira un error ahora por estar declarada la variable dos veces, igualmente hay que arreglarlo
para poder meter los ordenies
-->
		<xsl:variable name="cantEvAnim" select="count(descendant::animal/descendant::evt)"/>
		<fo:block >
			<fo:table width="300pt" table-layout="fixed" >
				<fo:table-column column-width="130pt" column-number="1"/>
				<fo:table-column column-width="170pt" column-number="2"/>
				<fo:table-body start-indent="1cm"  text-align="start">
					<fo:table-row >
						<fo:table-cell>
							<fo:block>
								<xsl:call-template name="Estilo_Nombre_Valor">
									<xsl:with-param name="nombre">
										Establecimientos: 
									</xsl:with-param>
									<xsl:with-param name="valor" select="count(descendant::estab)"/>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>
								<xsl:call-template name="Estilo_Nombre_Valor">
									<xsl:with-param name="nombre">
										Eventos Establecimientos: 
									</xsl:with-param>
									<xsl:with-param name="valor" select="$cantEvEstab"/>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row >
						<fo:table-cell>
							<fo:block>
								<xsl:call-template name="Estilo_Nombre_Valor">
									<xsl:with-param name="nombre">
										Animales: 
									</xsl:with-param>
									<xsl:with-param name="valor" select="count(descendant::animal)"/>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>
								<xsl:call-template name="Estilo_Nombre_Valor">
									<xsl:with-param name="nombre">
										Eventos Animales: 
									</xsl:with-param>
									<xsl:with-param name="valor" select="$cantEvAnim"/>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row >
						<fo:table-cell number-columns-spanned="2">
							<fo:block>
								<xsl:call-template name="Estilo_Nombre_Valor">
									<xsl:with-param name="nombre">
										Total de Eventos Informados: 
									</xsl:with-param>
									<xsl:with-param name="valor" select="$cantEvEstab + $cantEvAnim"/>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>

	<xsl:template match="estabs">
		<fo:block space-before="10pt">
			<fo:table width="100%" table-layout="fixed" border="solid 1px black">
				<fo:table-column column-width="proportional-column-width(1)" column-number="1"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="2"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="3"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="4"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="5"/>
				<fo:table-header>
					<fo:table-row>
						<fo:table-cell border="solid black 1px" number-columns-spanned="5">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_1">
									<xsl:with-param name="text">
										Resumen Eventos
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
										Tipo Evento
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
										Enviados
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
										Aceptados
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
										Rechazados
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
										Observados
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
				<fo:table-body start-indent="0pt" text-align="start">
<!--
					<xsl:call-template name="tablaDetalleCadaEvt">
						<xsl:with-param name="texto">
							alta
						</xsl:with-param>
						<xsl:with-param name="background-color">
							#def
						</xsl:with-param>
					</xsl:call-template>
-->					
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Altas
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta) - count(descendant::evtsEstab/descendant::alta/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="alta">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Semens
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen) - count(descendant::evtsEstab/descendant::semen/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="semen">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Baja
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja) - count(descendant::animal/descendant::evt/descendant::baja/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Estado
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado) - count(descendant::animal/descendant::evt/descendant::estado/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Info
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info) - count(descendant::animal/descendant::evt/descendant::info/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Prod
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod) - count(descendant::animal/descendant::evt/descendant::prod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Reprod
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod) - count(descendant::animal/descendant::evt/descendant::reprod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Serv
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv) - count(descendant::animal/descendant::evt/descendant::serv/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Trans
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans) - count(descendant::animal/descendant::evt/descendant::trans/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
		<xsl:for-each select="estab">
			<xsl:call-template name="mostrarEvtEstab"/>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="mostrarEvtEstab">
		<fo:block break-before="page"/>
		<xsl:variable name="cantEvEstab" select="count(descendant::evtsEstab/descendant::alta)"/>
		<!-- 
<xsl:variable name="cantEvEstab" select="$cantEvEstab + count(descendant::evtsEstab/descendant::semen)"/>
explicado en el comentario de lo mismo mas arriba
-->
		<xsl:variable name="cantEvAnim" select="count(descendant::animal/descendant::evt)"/>
		<fo:block space-before="10pt">
			<fo:table width="100%" table-layout="fixed" border="solid 1px black">
				<fo:table-column column-width="proportional-column-width(1)" column-number="1"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="2"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="3"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="4"/>
				<fo:table-column column-width="proportional-column-width(1)" column-number="5"/>
				<fo:table-header>
					<fo:table-row>
						<fo:table-cell number-columns-spanned="5">
							<fo:block text-align="center" >
								<xsl:choose>
									<xsl:when test="contains(descendant-or-self::rdo/codigoMsg, 'NESTAB')">
										<xsl:for-each select="rdos/rdo">
											<xsl:if test="codigoMsg = 'NESTAB'">
												<xsl:call-template name="Estilo_1">
													<xsl:with-param name="text">
														Establecimiento Nº: 
														<xsl:value-of select="../../IDEstab"/>
														- Nombre:
														<xsl:value-of select="vals/val[1]"/>
													</xsl:with-param>
												</xsl:call-template>
											</xsl:if>
										</xsl:for-each>
									</xsl:when>
									<xsl:otherwise>
										<xsl:call-template name="Estilo_1">
											<xsl:with-param name="text">
												Establecimiento Nº: 
												<xsl:value-of select="IDEstab"/>
												- Nombre: Sin Nombre
											</xsl:with-param>
										</xsl:call-template>
									</xsl:otherwise>
								</xsl:choose>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row>
						<fo:table-cell border="solid black 1px">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text">
										Tipo Evento
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
										Enviados
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
										Aceptados
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
										Rechazados
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
										Observados
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
				<fo:table-body start-indent="0pt" text-align="start">
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Altas
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta) - count(descendant::evtsEstab/descendant::alta/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="alta">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Semens
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen) - count(descendant::evtsEstab/descendant::semen/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="semen">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Baja
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::baja) - count(descendant::animal/descendant::evt/descendant::baja/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Estado
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::estado) - count(descendant::animal/descendant::evt/descendant::estado/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Info
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::info) - count(descendant::animal/descendant::evt/descendant::info/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Prod
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::prod) - count(descendant::animal/descendant::evt/descendant::prod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Reprod
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::reprod) - count(descendant::animal/descendant::evt/descendant::reprod/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Serv
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::serv) - count(descendant::animal/descendant::evt/descendant::serv/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row background-color="#def">
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										Trans
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:value-of select="count(descendant::animal/descendant::evt/descendant::trans) - count(descendant::animal/descendant::evt/descendant::trans/IdEvtSicel)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text">
										<xsl:variable name="aux" select="0"/>
										<xsl:for-each select="baja">
											<xsl:if test="rdos/descendant::rdo/nivelErr &lt; 80">
												<xsl:variable name="aux" select="$aux + 1"/>
											</xsl:if>
										</xsl:for-each>
										<xsl:value-of select="$aux"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>
	<xsl:template name="testEstilos">
		<fo:block>
			<xsl:call-template name="Estilo_1">
				<xsl:with-param name="text">
					Estilo 1
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_2">
				<xsl:with-param name="text">
					Estilo 2
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_3">
				<xsl:with-param name="text">
					Estilo 3
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_4">
				<xsl:with-param name="text">
					Estilo 4
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text">
					Estilo 5
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_6">
				<xsl:with-param name="text">
					Estilo 6
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_8">
				<xsl:with-param name="text">
					Estilo 8
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_10">
				<xsl:with-param name="text">
					Estilo 10
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
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
