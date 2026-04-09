<?xml version="1.0"?>
<xsl:stylesheet xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:template match="lote">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="bookpage" margin="1.5cm" page-width="21cm" page-height="29.7cm">
					<fo:region-body region-name="bookpage-body" margin-bottom="0.5mm" margin-top="0.5mm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="bookpage">
				<fo:title>ACHA - SICEL - Resumen de proceso de lote</fo:title>
				<fo:flow flow-name="bookpage-body">
					<fo:block >
						<xsl:call-template name="titulo"/>
						<xsl:apply-templates select="informante" />
						<xsl:apply-templates select="IDLoteInte" />
						<xsl:apply-templates select="IDLoteSicel" />
						<xsl:apply-templates select="tiempos" />
						<xsl:apply-templates select="estabs" />
						<fo:block space-after="25pt"/>
						<xsl:call-template name="testEstilos" />
					</fo:block>
					
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>


	<xsl:template name="titulo">

		<xsl:call-template name="Estilo_Titulo" >
			<xsl:with-param name="texto">
				ACHA - SICEL
			</xsl:with-param>
		</xsl:call-template>
		
		<xsl:call-template name="Estilo_Subtitulo" >
			<xsl:with-param name="texto">
				Resúmen de proceso de lote
			</xsl:with-param>
		</xsl:call-template>
		
		<fo:block text-align-last="justify">
			<fo:leader leader-pattern="rule" color="red"/>
		</fo:block>
		
	</xsl:template>

	<xsl:template match="informante" >
		<fo:block >
			<xsl:call-template name="Estilo_Nombre_Valor" >
				<xsl:with-param name="nombre" >
						Informante (ECLO) : 
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()" />
			</xsl:call-template>
		</fo:block>
	</xsl:template>

	<xsl:template match="ECLO">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					ECLO: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>

	<xsl:template match="IDLoteInte">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Número de Lote:
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>

	<xsl:template match="IDLoteSicel">
		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					ID del lote en SICEL: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="text()"/>
			</xsl:call-template>
		</fo:block>
	</xsl:template>
	
	<xsl:template match="tiempos">
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text" >
					Fecha de Recepción:
				</xsl:with-param>
			</xsl:call-template>
			<xsl:choose>
				<xsl:when test="tRece">
					<xsl:call-template name="Estilo_6">
								<xsl:with-param name="text" >
									<xsl:value-of select="tRece"/>
								</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="Estilo_6">
								<xsl:with-param name="text" >
									No Existente.
								</xsl:with-param>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text" >
					Fecha de Proceso:
				</xsl:with-param>
			</xsl:call-template>
			<xsl:choose>
				<xsl:when test="tRece">
					<xsl:call-template name="Estilo_6">
								<xsl:with-param name="text" >
									<xsl:value-of select="tProcFin"/>
								</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="Estilo_6">
								<xsl:with-param name="text" >
									No Existente.
								</xsl:with-param>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
	</xsl:template>

	<xsl:template match="estabs">

		<xsl:variable name="cantEvEstab" select="count(descendant::evtsEstab/descendant::alta)"/>
		<xsl:variable name="cantEvEstab" select="$cantEvEstab + count(descendant::evtsEstab/descendant::semen)"/>

		<xsl:variable name="cantEvAnim" select="count(descendant::animal/descendant::evt)"/>

		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Establecimientos: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="count(descendant::estab)"/>
			</xsl:call-template>

			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Eventos Establecimientos: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="$cantEvEstab"/>
			</xsl:call-template>
		</fo:block>

		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Animales: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="count(descendant::animal)"/>
			</xsl:call-template>

			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Eventos Animales: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="$cantEvAnim"/>
			</xsl:call-template>
		</fo:block>

		<fo:block>
			<xsl:call-template name="Estilo_Nombre_Valor">
				<xsl:with-param name="nombre" >
					Total de Eventos Informados: 
				</xsl:with-param>
				<xsl:with-param name="valor" select="$cantEvEstab + $cantEvAnim"/>
			</xsl:call-template>
		</fo:block>

		<fo:block space-before="10pt">
			<xsl:call-template name="Estilo_1">
				<xsl:with-param name="text" >
					Eventos Establecimientos
				</xsl:with-param>
			</xsl:call-template>

			 <fo:table width="100%" table-layout="fixed" border="solid 1px black"  >
		
				<fo:table-column column-width="proportional-column-width(5)" column-number="1" />
				<fo:table-column column-width="proportional-column-width(5)" column-number="2"/>
				<fo:table-column column-width="proportional-column-width(5)" column-number="3"/>
				<fo:table-column column-width="proportional-column-width(5)" column-number="4"/>
				<fo:table-column column-width="proportional-column-width(5)" column-number="5"/>

				<fo:table-header>
					<fo:table-row>
						<fo:table-cell border="solid black 1px" > 
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text" >
										Tipo Evento
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid black 1px" > 
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text" >
										Enviados
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid black 1px" > 
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text" >
										Aceptados
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid black 1px" > 
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text" >
										Rechazados
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid black 1px" > 
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_5">
									<xsl:with-param name="text" >
										Observados
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>

				<fo:table-body start-indent="0pt" text-align="start">
					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										Altas
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										<xsl:value-of select="count(descendant::evtsEstab/descendant::alta)"/>
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<fo:table-row>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										Semens
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										?????
									</xsl:with-param>
								</xsl:call-template>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell border="solid 1px black">
							<fo:block text-align="center">
								<xsl:call-template name="Estilo_6">
									<xsl:with-param name="text" >
										<xsl:value-of select="count(descendant::evtsEstab/descendant::semen)"/>
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
				<xsl:with-param name="text" >
					Estilo 1
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_2">
				<xsl:with-param name="text" >
					Estilo 2
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_3">
				<xsl:with-param name="text" >
					Estilo 3
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_4">
				<xsl:with-param name="text" >
					Estilo 4
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_5">
				<xsl:with-param name="text" >
					Estilo 5
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_6">
				<xsl:with-param name="text" >
					Estilo 6
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_8">
				<xsl:with-param name="text" >
					Estilo 8
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
		<fo:block>
			<xsl:call-template name="Estilo_10">
				<xsl:with-param name="text" >
					Estilo 10
				</xsl:with-param>
			</xsl:call-template>
		</fo:block>
	</xsl:template>

<!--
	<xsl:template match="estabs" >
			<fo:block >
				 <fo:table width="400pt" table-layout="fixed" border="solid 1px black"  >
			
					<fo:table-column column-width="100pt" column-number="1" >
					</fo:table-column>
					<fo:table-column column-width="100pt" column-number="2">
					</fo:table-column>
					<fo:table-column column-width="100pt" column-number="3">
					</fo:table-column>
					<fo:table-column column-width="100pt" column-number="4">
					</fo:table-column>
	
					<fo:table-header>
						<fo:table-row>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">IDEstab</fo:block>
							</fo:table-cell>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Nivel Error</fo:block>
							</fo:table-cell>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Cod. Mensaje</fo:block>
							</fo:table-cell>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Info</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-header>
	
					<fo:table-body start-indent="0pt" text-align="start">
						<xsl:for-each select="estab">
							<fo:table-row>
								<fo:table-cell border="solid 1px black">
									<fo:block>
											<xsl:apply-templates select="IDEstab"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
			  </fo:table>
			</fo:block>

	
	</xsl:template>

	<xsl:template match="rdos" >
			<fo:block >
				 <fo:table width="300pt" table-layout="fixed" border="solid 1px black"  >
			
					<fo:table-column column-width="100pt" column-number="1" >
					</fo:table-column>
					<fo:table-column column-width="100pt" column-number="2">
					</fo:table-column>
					<fo:table-column column-width="100pt" column-number="3">
					</fo:table-column>
	
					<fo:table-header>
						<fo:table-row>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Nivel Error</fo:block>
							</fo:table-cell>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Cod. Mensaje</fo:block>
							</fo:table-cell>
							<fo:table-cell border="solid black 1px" padding="5pt" > 
								<fo:block text-align="center" font-weight="bold">Info</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-header>
	
					<fo:table-body start-indent="0pt" text-align="start">
						<xsl:for-each select="rdo">
							<fo:table-row>
								<fo:table-cell border="solid 1px black">
									<fo:block>
											<xsl:apply-templates select="nivelErr"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid 1px black">
									<fo:block>
											<xsl:apply-templates select="codigoMsg"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell border="solid 1px black">
									<fo:block>
											<xsl:apply-templates select="info"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
			  </fo:table>
			</fo:block>

	
	</xsl:template>

-->


	<!--
#########################################################
#  rubro: Template para el tipo rubro
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="rubro" name="rubro">
		<xsl:for-each select="sec">
			<xsl:call-template name="sec"/>
			<fo:block break-after="page"/>
		</xsl:for-each>
	</xsl:template>
	<!--
#########################################################
#  sec: Template para el tipo sec
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="sec" name="sec">
		<xsl:if test="count(dadorn) > 0">
			<xsl:apply-templates select="dadorn"/>
		</xsl:if>
		<xsl:if test="count(secs) > 0">
			<xsl:apply-templates select="secs"/>
		</xsl:if>
	</xsl:template>
	<!--
#########################################################
#  secs: Template para el tipo seccs
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="secs">
		<xsl:choose>
			<xsl:when test="count(sec) >0">
				<!-- Aqui escribe los titulos por seccion  y los concatena -->
				<xsl:if test="@desc = 'Zona' or @desc='Provincia' or @desc='Pais'">
					<fo:block space-after.optimum="0.2cm" space-before.optimum="0.3cm" background-color="darkblue" color="white" text-align="center" span="all">
						<fo:inline font-weight="bold">
							<xsl:if test="count(sec/dadorn/disp) > 0">
								<xsl:value-of select="translate(concat(ancestor::rubro/@desc, ' - ',sec/dadorn/disp), 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
								<xsl:value-of select="'&#32;'"/>
								<xsl:if test="count(sec/dadorn/ads/txt) > 0 and sec/dadorn/ads/txt/@pos = 'cont'">
									<xsl:value-of select="sec/dadorn/ads/txt"/>
								</xsl:if>
							</xsl:if>
							<xsl:if test="count(sec/dadorn/disp) = 0">
								<xsl:value-of select="translate(ancestor::rubro/@desc, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
								<xsl:value-of select="'&#32;'"/>
								<xsl:if test="count(sec/dadorn/ads/txt) > 0 and sec/dadorn/ads/txt/@pos = 'cont'">
									<xsl:value-of select="sec/dadorn/ads/txt"/>
								</xsl:if>
							</xsl:if>
						</fo:inline>
					</fo:block>
				</xsl:if>
				<xsl:apply-templates select="sec"/>
			</xsl:when>
			<xsl:when test="count(ocurrs) > 0">
				<xsl:apply-templates select="ocurrs"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!--
#########################################################
#  dadorn: Template para el tipo  DAdornable
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="dadorn">
		<xsl:call-template name="daxSup"/>
		<xsl:if test="count(disp) > 0">
			<xsl:apply-templates select="disp"/>
		</xsl:if>
		<xsl:call-template name="daxInf"/>
	</xsl:template>
	<xsl:template name="dadornx">
		<xsl:if test="count(ads) > 0">
			<xsl:apply-templates select="ads"/>
		</xsl:if>
	</xsl:template>
	<!--
#########################################################
#  ads: Template para el tipo  ads
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="ads">
		<xsl:apply-templates select="txt"/>
	</xsl:template>
	<xsl:template name="daxSup">
		<xsl:if test="ads/txt/@pos = 'sup' and ads/txt/@lyt != 0">
			<fo:block>
				<xsl:call-template name="dadornx"/>
			</fo:block>
		</xsl:if>
	</xsl:template>
	<xsl:template name="daxInf">
		<xsl:if test="ads/txt/@pos = 'inf' and ads/txt/@lyt != 0">
			<fo:block>
				<xsl:call-template name="dadornx"/>
			</fo:block>
		</xsl:if>
		<xsl:if test="ads/txt/@pos = 'cont' and ads/txt/@lyt != 0">
			<xsl:value-of select="'&#32;'"/>
			<xsl:call-template name="dadornx"/>
		</xsl:if>
	</xsl:template>
	<xsl:template name="adsSup">
		<xsl:if test="dadorn/ads/txt/@pos = 'sup' and ads/txt/@lyt != 0">
			<fo:block>
				<xsl:apply-templates select="dadorn"/>
			</fo:block>
		</xsl:if>
	</xsl:template>
	<xsl:template name="adsInf">
		<xsl:if test="dadorn/ads/txt/@pos = 'inf' and ads/txt/@lyt != 0">
			<fo:block>
				<xsl:apply-templates select="dadorn"/>
			</fo:block>
		</xsl:if>
		<xsl:if test="dadorn/ads/txt/@pos = 'cont' and ads/txt/@lyt != 0">
			<xsl:value-of select="'&#32;'"/>
			<xsl:apply-templates select="dadorn"/>
		</xsl:if>
	</xsl:template>
	<xsl:template match="txt">
		<xsl:choose>
			<xsl:when test="@stl = 1">
				<xsl:call-template name="Estilo_1"/>
			</xsl:when>
			<xsl:when test="@stl = 2">
				<xsl:call-template name="Estilo_2"/>
			</xsl:when>
			<xsl:when test="@stl = 3">
				<xsl:call-template name="Estilo_3"/>
			</xsl:when>
			<xsl:when test="@stl = 4">
				<xsl:call-template name="Estilo_4"/>
			</xsl:when>
			<xsl:when test="@stl = 5">
				<xsl:call-template name="Estilo_5"/>
			</xsl:when>
			<xsl:when test="@stl = 6">
				<xsl:call-template name="Estilo_6"/>
			</xsl:when>
			<xsl:when test="@stl = 7">
				<xsl:call-template name="Estilo_7"/>
			</xsl:when>
			<xsl:when test="@stl = 8">
				<xsl:call-template name="Estilo_8"/>
			</xsl:when>
			<xsl:when test="@stl = 9">
				<xsl:call-template name="Estilo_9"/>
			</xsl:when>
			<xsl:when test="@stl = 10">
				<xsl:call-template name="Estilo_10"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!--
#########################################################
#  ads: Template para el tipo  ads
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="disp">
		<xsl:call-template name="dispx"/>
	</xsl:template>
	<!--
#########################################################
#  TipoDAdorn: Template para el TipoDAdorn
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="dispx">
		<xsl:choose>
			<xsl:when test="@shw = 'true' and @stl = 1">
				<xsl:call-template name="Estilo_1"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 2">
				<xsl:call-template name="Estilo_2"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 3">
				<xsl:call-template name="Estilo_3"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 4">
				<xsl:call-template name="Estilo_4"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 5">
				<xsl:call-template name="Estilo_5"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 6">
				<xsl:call-template name="Estilo_6"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 7">
				<xsl:call-template name="Estilo_7"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 8">
				<xsl:call-template name="Estilo_8"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 9">
				<xsl:call-template name="Estilo_9"/>
			</xsl:when>
			<xsl:when test="@shw = 'true' and @stl = 10">
				<xsl:call-template name="Estilo_10"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!--
#########################################################
#  ocurrs: Template para el tipo ocurrs
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="ocurrs">
		<xsl:for-each select="ocurr">
			<fo:block space-after.optimum=".3cm" span="none" keep-together.within-column="always">
				<!-- Debo verificar si posee adornos y en que posicion -->
				<xsl:if test="count(adis/icon) > 0">
					<fo:table table-layout="fixed">
						<fo:table-column column-width="0.6cm"/>
						<fo:table-column column-width="4.2cm"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell vertical-align="top">
									<fo:block>
										<xsl:apply-templates select="adis"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<xsl:call-template name="ocurrInt"/>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</xsl:if>
				<xsl:if test="count(adis/icon) = 0">
					<xsl:call-template name="ocurrInt"/>
				</xsl:if>
				<!-- para procesar el resto -->
			</fo:block>
		</xsl:for-each>
	</xsl:template>
	<!--
# Prestadores
-->
	<xsl:template match="prest">
		<xsl:call-template name="dispx"/>
	</xsl:template>
	<!--
# nombFant
-->
	<xsl:template match="nombFant">
		<xsl:call-template name="dispx"/>
	</xsl:template>
	<!--
# Ocurrencia Interior
-->
	<xsl:template name="ocurrInt">
		<fo:block padding-top=".1cm">
			<xsl:call-template name="adsSup"/>
			<fo:block span="none" line-height=".3cm">
				<xsl:apply-templates select="prest"/>
				<xsl:apply-templates select="nombFant"/>
				<xsl:call-template name="adsInf"/>
			</fo:block>
			<xsl:if test="count(cvs) > 0">
				<xsl:apply-templates select="cvs"/>
			</xsl:if>
			<xsl:if test="count(doms) > 0">
				<fo:block span="none" line-height=".35cm">
					<xsl:apply-templates select="doms"/>
				</fo:block>
			</xsl:if>
		</fo:block>
	</xsl:template>
	<!--
# Ocurrencia Iconos
-->
	<xsl:template match="adis">
		<xsl:apply-templates select="icon"/>
	</xsl:template>
	<xsl:template match="icon">
		<fo:block>
			<fo:external-graphic>
				<xsl:attribute name="src"><xsl:value-of select="@path"/></xsl:attribute>
			</fo:external-graphic>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
#  cvs: Template para el tipo cvs
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="cvs">
		<fo:block font-size=".3cm">
			<xsl:apply-templates select="cv"/>
		</fo:block>
	</xsl:template>
	<xsl:template match="cv">
		<xsl:apply-templates select="disp"/>
	</xsl:template>
	<!--
#########################################################
#  doms: Template para el tipo doms
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="doms">
		<xsl:for-each select="dom">
			<xsl:if test="count(equipo) >0">
				<xsl:apply-templates select="equipo"/>
			</xsl:if>
			<xsl:if test="count(dadorn) >0 ">
				<xsl:apply-templates select="dadorn"/>
			</xsl:if>
			<xsl:if test="count(codsCom) >0">
				<xsl:apply-templates select="codsCom"/>
			</xsl:if>
			<xsl:if test="count(idms) >0">
				<xsl:apply-templates select="idms"/>
			</xsl:if>
			<xsl:if test="count(hors) >0">
				<xsl:apply-templates select="hors"/>
			</xsl:if>
			<xsl:if test="count(emails) >0">
				<xsl:apply-templates select="emails"/>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	<!--
#########################################################
#  idms: Template para el tipo idms
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="idms">
		<fo:block font-size=".3cm">
 			Idiomas:<xsl:value-of select="'&#32;'"/>
			<xsl:apply-templates select="idm"/>
		</fo:block>
	</xsl:template>
	<xsl:template match="idm">
		<xsl:apply-templates select="disp"/>
	</xsl:template>
	<!--
#########################################################
#  codsCom: Template para el tipo codsCom
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="codsCom">
		<fo:block font-size=".3cm">
			<fo:inline font-weight="normal">
				<xsl:value-of select="'&#32;'"/>
				<xsl:apply-templates select="codCom"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<xsl:template match="codCom">
		<fo:block>
			<xsl:call-template name="daxSup"/>
			<xsl:apply-templates select="disp"/>
			<xsl:call-template name="daxInf"/>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
#  hors: Template para el tipo hors
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="hors">
		<fo:block font-size=".3cm">
			<fo:inline font-weight="normal">
				<xsl:value-of select="'&#32;'"/>
				<xsl:apply-templates select="hor"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<xsl:template match="hor">
		<fo:block>
			<xsl:apply-templates select="disp"/>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
#  emails: Template para el tipo emails
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="emails">
		<fo:block font-size=".3cm">
			<fo:inline font-weight="normal">
				<xsl:value-of select="'&#32;'"/>
				<xsl:apply-templates select="email"/>
			</fo:inline>
		</fo:block>
	</xsl:template>
	<xsl:template match="email">
		<fo:block>
			<xsl:apply-templates select="disp"/>
		</fo:block>
	</xsl:template>
	<!--
#########################################################
#  equipo: Template para el tipo equipo
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template match="equipo">
		<xsl:if test="count(fn) > 0">
			<fo:block font-size=".3cm">
				<fo:inline text-align="left" font-weight="normal">
					<xsl:value-of select="fn"/>
				</fo:inline>
			</fo:block>
		</xsl:if>
		<xsl:apply-templates select="integs"/>
	</xsl:template>
	<xsl:template match="integs">
		<xsl:for-each select="integ">
			<fo:block>
				<xsl:if test="count(dadorn) >0">
					<xsl:apply-templates select="dadorn"/>
				</xsl:if>
				<xsl:if test="count(cvs) >0">
					<xsl:apply-templates select="cvs"/>
				</xsl:if>
				<xsl:if test="count(idms) >0">
					<xsl:apply-templates select="idms"/>
				</xsl:if>
			</fo:block>
		</xsl:for-each>
	</xsl:template>
	<!--
#########################################################
# Comienzan Estilos
######################################################### 

#########################################################
# Estilos 1
#
#  Autor: Aldo  De Biase <aldo@de.biase.com.ar>
# Fecha: 24 / 06 / 2003 13:41 GTM -3 
######################################################### 
 -->
	<xsl:template name="Estilo_1">
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
		<fo:block font-size=".3cm" font-family="sans-serif" line-height=".3cm" background-color="darkblue" color="white" text-align="center" padding-top="3pt" span="none" border="solid 1px black" >
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
		<xsl:param name="text"><xsl:value-of select="text()" /></xsl:param>
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
				<xsl:value-of select="translate($texto, 'abcdefghijklmnñopqrstuvwxyzáéíóú','ABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ')"/>
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
			<xsl:with-param name="text" >
				<xsl:value-of select="$nombre"/>
			</xsl:with-param>
		</xsl:call-template>
		<xsl:call-template name="Estilo_6">
			<xsl:with-param name="text" >
				<xsl:value-of select="$valor"/>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>

</xsl:stylesheet>
