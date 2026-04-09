package ar.org.sicel.persistence;

import java.util.Date;

public class LactanciaGenetica {
	
	public static Integer TIPO_INICIO_ABORTO_LARGO = new Integer(2);
	public static Integer TIPO_INICIO_PARTO = new Integer(1);
	public static Integer TIPO_INICIO_ESTADO = new Integer(3);
	public static Integer TIPO_LACTANCIA_OFICIAL = new Integer(1);
	public static Integer TIPO_LACTANCIA_NO_OFICIAL = new Integer(2);
	public static Integer DIAS_SI_NO_HAY_SERVICIO = 283;
	public static Integer TIPO_LACTANCIA_EN_CURSO = new Integer(2);
	public static Integer TIPO_LACTANCIA_CERRADA = new Integer(1);
	public static Integer TIPO_LACTANCIA_SICEL1 = new Integer(1);
	public static Integer TIPO_LACTANCIA_SICEL3 = new Integer(3);

	private Integer id; //ID - OK
	private Long idLactancia;//ID_LACTANCIA - OK
	private Integer nroLactanciaInformado;//NLAC
	private Animal animal;//ID_ANIMAL
	private Eclo eclo; //ECLO - OK
	private Propietario prop; //PROP - OK
	private Establecimiento tambo; //TAMBO - OK
	private Integer cantOrdenie; //CANTORD - ok
	private Integer mesInicioLactancia;//MPAR - ok
	private Integer edadMesesInicioLactancia;//EPAR - ok
	//EPPAR la variable FechaInicio = fecha parto + 1 y para 
	//EPPAR se esta tomando la cantidad como fecha de inicio a fechaInicio - 1 por lo tanto la fecha del evento
	private Integer edadMesesInicioLactanciaUno;//EPPAR
	private Integer mesNacimiento;//MNAC
	private Integer anioNacimiento;//ANAC
	private Integer anioInicioLactancia;//APAR
	private Integer diasEntreServicioLacAnt;//DIASA
	private Integer diasDuracionLac;//DIAS
	private Integer diasEntreLactanciaAnterior;//IPAR
	private String animalRegistro; /* ANIMAL: si tiene HBA --> P000300000, si tiene RC --> C000300000 */
	private String rpAnimal;//RP
	private Float leche305;//LECHE
	private Float grasa305;//GRASA
	private Float porGrasa305;//PGRASA
	private String categoria;//CAT 
	private Float proteinas305;//PROT
	private Float porProteinas305;//PPROT
	private Float cel; //CEL
	private Integer tipoLac;//TIPOLAC
	private Integer tipoInicioLactancia;//ILAC
	private Integer  dcon;//DCON
	private Integer tlac;//TLAC
	private Integer eac; //EAC
	private Integer cnor;//CNOR
	private String iso;//ISO
	private Integer elac;//ELAC
	private Float lecheReal;//LECREAL
	private Float grasaReal;//GRASAREAL
	private Float porGrasaReal;//PORGRASAREAL
	private Float proteinasReal;//PROTREAL
	private Float porProteinasReal;//PORPROTREAL
	private Date fechaNacimiento;//FNAC
	private Date fechaParto;//FPAR
	private Date fechaFinalizacionLactancia;//FFL
	private Date fechaPrimerServicio;//FPS
	private Date fechaUltimoServicio;//FUS
	private Integer intervaloPartoPrimerServicio;//IPPS
	private Integer intervaloPrimerServicioUltimoServicio;//IPSUS
	private Integer numerosDeServicio;//NS
	private Integer baja;//BAJA
	private Integer motivoBaja;//MOTIVOBAJA
	private Date fechaUltimoControl;//FUC
	private Date fechaUltimoControlEstancia;//FUCE
	private String ge;//NO VA MAS
	private String geEstab;//GESTAB
	private String geEstabDos;//NO VA MAS
	private Integer diasEntreServicioLacAntDos;//DIASADOS
    private Integer diasEntreLactanciaAnteriorDos;//IPARDOS
    private Integer intervaloPartoPrimerServicioDos;//IPPSDOS
	private Integer intervaloPrimerServicioUltimoServicioDos;//IPSUSDOS
	private Date fechaInicioPartoSiguiente;//FIPS
	private Long estabPril;//EPL
	private Long estanciaUltimoControl;//EUC
	private String isop;/*char 14*///ISOP
	private String isom;/*char 14*///ISOM
	private String isoam;/*char 14*///ISOAM
	private Date fechaPrimerServicioDos;/*como diasADos*///FPSDOS
	private Date fechaUltimoServicioDos;/*como diasADos*///FUSDOS
	private Integer sistemaSicel;//SSICEL
	private Estancia estancia; // ESTANCIA - OK
	private Integer numerosDeServicioDos;//NSDOS
	private Date fechaInicioPartoAnterior;
	
	private String geDosA;
	private String geCP;
	private String geCR;
	
	public String getGeDosA() {
		return geDosA;
	}
	public void setGeDosA(String geDosA) {
		this.geDosA = geDosA;
	}
	public String getGeCP() {
		return geCP;
	}
	public void setGeCP(String geCP) {
		this.geCP = geCP;
	}
	public String getGeCR() {
		return geCR;
	}
	public void setGeCR(String geCR) {
		this.geCR = geCR;
	}
	public Estancia getEstancia() {
		return estancia;
	}
	public void setEstancia(Estancia estancia) {
		this.estancia = estancia;
	}
	public Integer getSistemaSicel() {
		return sistemaSicel;
	}
	public void setSistemaSicel(Integer sistemaSicel) {
		this.sistemaSicel = sistemaSicel;
	}
	public Integer getIntervaloPartoPrimerServicioDos() {
		return intervaloPartoPrimerServicioDos;
	}
	public void setIntervaloPartoPrimerServicioDos(
			Integer intervaloPartoPrimerServicioDos) {
		this.intervaloPartoPrimerServicioDos = intervaloPartoPrimerServicioDos;
	}
	public Integer getIntervaloPrimerServicioUltimoServicioDos() {
		return intervaloPrimerServicioUltimoServicioDos;
	}
	public void setIntervaloPrimerServicioUltimoServicioDos( /*si no hay servicio asociado, tomo el ultimo restando 283*/
			Integer intervaloPrimerServicioUltimoServicioDos) {
		this.intervaloPrimerServicioUltimoServicioDos = intervaloPrimerServicioUltimoServicioDos;
	}
	public Date getFechaInicioPartoSiguiente() {
		return fechaInicioPartoSiguiente;
	}
	public void setFechaInicioPartoSiguiente(Date fechaInicioPartoSiguiente) {
		this.fechaInicioPartoSiguiente = fechaInicioPartoSiguiente;
	}
	public Long getEstabPril() {
		return estabPril;
	}
	public void setEstabPril(Long estabPril) {
		this.estabPril = estabPril;
	}
	public Long getEstanciaUltimoControl() {
		return estanciaUltimoControl;
	}
	public void setEstanciaUltimoControl(Long estanciaUltimoControl) {
		this.estanciaUltimoControl = estanciaUltimoControl;
	}
	public LactanciaGenetica(){
	}
	public Eclo getEclo() {
		return eclo;
	}
	public void setEclo(Eclo eclo) {
		this.eclo = eclo;
	}
	public Propietario getProp() {
		return prop;
	}
	public void setProp(Propietario prop) {
		this.prop = prop;
	}
	public Establecimiento getTambo() {
		return tambo;
	}
	public void setTambo(Establecimiento tambo) {
		this.tambo = tambo;
	}
	public Integer getCantOrdenie() {
		return cantOrdenie;
	}
	public void setCantOrdenie(Integer cantOrdenie) {
		this.cantOrdenie = cantOrdenie;
	}
	public Integer getMesInicioLactancia() {
		return mesInicioLactancia;
	}
	public void setMesInicioLactancia(Integer mesInicioLactancia) {
		this.mesInicioLactancia = mesInicioLactancia;
	}
	public Integer getEdadMesesInicioLactancia() {
		return edadMesesInicioLactancia;
	}
	public void setEdadMesesInicioLactancia(Integer edadMesesInicioLactancia) {
		this.edadMesesInicioLactancia = edadMesesInicioLactancia;
	}
	public Integer getEdadMesesInicioLactanciaUno() {
		return edadMesesInicioLactanciaUno;
	}
	public void setEdadMesesInicioLactanciaUno(Integer edadMesesInicioLactanciaUno) {
		this.edadMesesInicioLactanciaUno = edadMesesInicioLactanciaUno;
	}
	public Integer getMesNacimiento() {
		return mesNacimiento;
	}
	public void setMesNacimiento(Integer mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public Integer getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(Integer anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	public Integer getAnioInicioLactancia() {
		return anioInicioLactancia;
	}
	public void setAnioInicioLactancia(Integer anioInicioLactancia) {
		this.anioInicioLactancia = anioInicioLactancia;
	}
	public Integer getDiasEntreServicioLacAnt() {
		return diasEntreServicioLacAnt;
	}
	public void setDiasEntreServicioLacAnt(Integer diasEntreServicioLacAnt) {
		this.diasEntreServicioLacAnt = diasEntreServicioLacAnt;
	}
	public Integer getDiasDuracionLac() {
		return diasDuracionLac;
	}
	public void setDiasDuracionLac(Integer diasDuracionLac) {
		this.diasDuracionLac = diasDuracionLac;
	}
	public Integer getDiasEntreLactanciaAnterior() {
		return diasEntreLactanciaAnterior;
	}
	public void setDiasEntreLactanciaAnterior(Integer diasEntreLactanciaAnterior) {
		this.diasEntreLactanciaAnterior = diasEntreLactanciaAnterior;
	}
	public String getAnimalRegistro() {
		return animalRegistro;
	}
	public void setAnimalRegistro(String animalRegistro) {
		this.animalRegistro = animalRegistro;
	}
	public String getRpAnimal() {
		return rpAnimal;
	}
	public void setRpAnimal(String rpAnimal) {
		this.rpAnimal = rpAnimal;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Float getCel() {
		return cel;
	}
	public void setCel(Float cel) {
		this.cel = cel;
	}
	public Integer getTipoLac() {
		return tipoLac;
	}
	public void setTipoLac(Integer tipoLac) {
		this.tipoLac = tipoLac;
	}
	public Integer getTipoInicioLactancia() {
		return tipoInicioLactancia;
	}
	public void setTipoInicioLactancia(Integer tipoInicioLactancia) {
		this.tipoInicioLactancia = tipoInicioLactancia;
	}
	public Integer getDcon() {
		return dcon;
	}
	public void setDcon(Integer dcon) {
		this.dcon = dcon;
	}
	public Integer getTlac() {
		return tlac;
	}
	public void setTlac(Integer tlac) {
		this.tlac = tlac;
	}
	public Integer getEac() {
		return eac;
	}
	public void setEac(Integer eac) {
		this.eac = eac;
	}
	public Integer getCnor() {
		return cnor;
	}
	public void setCnor(Integer cnor) {
		this.cnor = cnor;
	}
	public String getIso() {
		return iso;
	}
	public void setIso(String iso) {
		this.iso = iso;
	}
	public Integer getElac() {
		return elac;
	}
	public void setElac(Integer elac) {
		this.elac = elac;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNroLactanciaInformado() {
		return nroLactanciaInformado;
	}
	public void setNroLactanciaInformado(Integer nroLactanciaInformado) {
		this.nroLactanciaInformado = nroLactanciaInformado;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Float getLecheReal() {
		return lecheReal;
	}
	public void setLecheReal(Float lecheReal) {
		this.lecheReal = lecheReal;
	}
	public Float getGrasaReal() {
		return grasaReal;
	}
	public void setGrasaReal(Float grasaReal) {
		this.grasaReal = grasaReal;
	}
	public Float getPorGrasaReal() {
		return porGrasaReal;
	}
	public void setPorGrasaReal(Float porGrasaReal) {
		this.porGrasaReal = porGrasaReal;
	}
	public Float getProteinasReal() {
		return proteinasReal;
	}
	public void setProteinasReal(Float proteinasReal) {
		this.proteinasReal = proteinasReal;
	}
	public Float getPorProteinasReal() {
		return porProteinasReal;
	}
	public void setPorProteinasReal(Float porProteinasReal) {
		this.porProteinasReal = porProteinasReal;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaParto() {
		return fechaParto;
	}
	public void setFechaParto(Date fechaParto) {
		this.fechaParto = fechaParto;
	}
	public Date getFechaPrimerServicio() {
		return fechaPrimerServicio;
	}
	public void setFechaPrimerServicio(Date fechaPrimerServicio) {
		this.fechaPrimerServicio = fechaPrimerServicio;
	}
	public Date getFechaUltimoServicio() {
		return fechaUltimoServicio;
	}
	public void setFechaUltimoServicio(Date fechaUltimoServicio) {
		this.fechaUltimoServicio = fechaUltimoServicio;
	}
	public Integer getIntervaloPartoPrimerServicio() {
		return intervaloPartoPrimerServicio;
	}
	public void setIntervaloPartoPrimerServicio(Integer intervaloPartoPrimerServicio) {
		this.intervaloPartoPrimerServicio = intervaloPartoPrimerServicio;
	}
	public Integer getIntervaloPrimerServicioUltimoServicio() {
		return intervaloPrimerServicioUltimoServicio;
	}
	public void setIntervaloPrimerServicioUltimoServicio(
			Integer intervaloPrimerServicioUltimoServicio) {
		this.intervaloPrimerServicioUltimoServicio = intervaloPrimerServicioUltimoServicio;
	}
	public Integer getNumerosDeServicio() {
		return numerosDeServicio;
	}
	public void setNumerosDeServicio(Integer numerosDeServicio) {
		this.numerosDeServicio = numerosDeServicio;
	}
	public Integer getBaja() {
		return baja;
	}
	public void setBaja(Integer baja) {
		this.baja = baja;
	}
	public Integer getMotivoBaja() {
		return motivoBaja;
	}
	public void setMotivoBaja(Integer motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	public Long getIdLactancia() {
		return idLactancia;
	}
	public void setIdLactancia(Long idLactancia) {
		this.idLactancia = idLactancia;
	}
	public Float getLeche305() {
		return leche305;
	}
	public void setLeche305(Float leche305) {
		this.leche305 = leche305;
	}
	public Float getGrasa305() {
		return grasa305;
	}
	public void setGrasa305(Float grasa305) {
		this.grasa305 = grasa305;
	}
	public Float getPorGrasa305() {
		return porGrasa305;
	}
	public void setPorGrasa305(Float porGrasa305) {
		this.porGrasa305 = porGrasa305;
	}
	public Float getProteinas305() {
		return proteinas305;
	}
	public void setProteinas305(Float proteinas305) {
		this.proteinas305 = proteinas305;
	}
	public Float getPorProteinas305() {
		return porProteinas305;
	}
	public void setPorProteinas305(Float porProteinas305) {
		this.porProteinas305 = porProteinas305;
	}
	public Date getFechaFinalizacionLactancia() {
		return fechaFinalizacionLactancia;
	}
	public void setFechaFinalizacionLactancia(Date fechaFinalizacionLactancia) {
		this.fechaFinalizacionLactancia = fechaFinalizacionLactancia;
	}
	public Date getFechaUltimoControl() {
		return fechaUltimoControl;
	}
	public void setFechaUltimoControl(Date fechaUltimoControl) {
		this.fechaUltimoControl = fechaUltimoControl;
	}
	public Date getFechaUltimoControlEstancia() {
		return fechaUltimoControlEstancia;
	}
	public void setFechaUltimoControlEstancia(Date fechaUltimoControlEstancia) {
		this.fechaUltimoControlEstancia = fechaUltimoControlEstancia;
	}
	public String getGe() {
		return ge;
	}
	public void setGe(String g) {
		this.ge = g;
	}
	public String getGeEstab() {
		return geEstab;
	}
	public void setGeEstab(String estab) {
		geEstab = estab;
	}
	public String getGeEstabDos() {
		return geEstabDos;
	}
	public void setGeEstabDos(String geEstabDos) {
		this.geEstabDos = geEstabDos;
	}
	public Integer getDiasEntreServicioLacAntDos() {
		return diasEntreServicioLacAntDos;
	}
	public void setDiasEntreServicioLacAntDos(Integer diasEntreServicioLacAntDos) {
		this.diasEntreServicioLacAntDos = diasEntreServicioLacAntDos;
	}
	public Integer getDiasEntreLactanciaAnteriorDos() {
		return diasEntreLactanciaAnteriorDos;
	}
	public void setDiasEntreLactanciaAnteriorDos(
			Integer diasEntreLactanciaAnteriorDos) {
		this.diasEntreLactanciaAnteriorDos = diasEntreLactanciaAnteriorDos;
	}
	public String getIsop() {
		return isop;
	}
	public void setIsop(String isop) {
		this.isop = isop;
	}
	public String getIsoam() {
		return isoam;
	}
	public void setIsoam(String isoam) {
		this.isoam = isoam;
	}
	public Date getFechaPrimerServicioDos() {
		return fechaPrimerServicioDos;
	}
	public void setFechaPrimerServicioDos(Date fechaPrimerServicioDos) {
		this.fechaPrimerServicioDos = fechaPrimerServicioDos;
	}
	public Date getFechaUltimoServicioDos() {
		return fechaUltimoServicioDos;
	}
	public void setFechaUltimoServicioDos(Date fechaUltimoServicioDos) {
		this.fechaUltimoServicioDos = fechaUltimoServicioDos;
	}
	public Integer getNumerosDeServicioDos() {
		return numerosDeServicioDos;
	}
	public void setNumerosDeServicioDos(Integer numerosDeServicioDos) {
		this.numerosDeServicioDos = numerosDeServicioDos;
	}
	public String getIsom() {
		return isom;
	}
	public void setIsom(String isom) {
		this.isom = isom;
	}
	public Date getFechaInicioPartoAnterior() {
		return fechaInicioPartoAnterior;
	}
	public void setFechaInicioPartoAnterior(Date fechaInicioPartoAnterior) {
		this.fechaInicioPartoAnterior = fechaInicioPartoAnterior;
	}

} 