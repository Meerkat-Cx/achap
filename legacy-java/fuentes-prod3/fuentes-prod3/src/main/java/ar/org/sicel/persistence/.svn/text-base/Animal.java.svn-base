package ar.org.sicel.persistence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.struts.util.LabelValueBean;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

/**
 * 
 * @hibernate.class table="An_Animal" lazy="true"
 * 
 * @hibernate.discriminator column="esHembra" type="boolean"
 * 
 */
public abstract class Animal {

	public static final String POSFIJOMAXDIAS_TR = "_MAX";

	public static final String POSFIJOMINDIAS_TR = "_MIN";

	public static final String SECA_VACIA = "SV";

	public static final String SECA_SERVIDA = "SS";

	public static final String SECA_PRENIADA = "SP";

	public static final String PRODUCCION_VACIA = "PV";

	public static final String PRODUCCION_SERVIDA = "PS";

	public static final String PRODUCCION_PRENIADA = "PP";

	public static final String BAJA = "BA";

	// Categorias posibles de animales//
	public static final String CAT_CR = "CR";
	public static final String CAT_PB = "PB";
	public static final String CAT_RC = "RC";
	public static final String CAT_PED = "PED";
	public static final String CAT_PU = "RCD"; // informacion no debe
	public static final String CAT_PURA_HOLANDO = "HAR";
	public static final String CAT_PURA_JERSEY = "JAR";
	public static final String CAT_PURA_SUECA = "SAR";
	public static final String CAT_PURA_PARDO = "PAR";
	public static final String CAT_PURA_AYSHIRE = "AAR";
	public static final String CAT_PURA_GUERNSEY = "GAR";
	public static final String CAT_PURA_NORMANDA = "NAR";
	public static final String CAT_PURA_MONTBELIARDE = "MAR";
	public static final String CAT_PURA_SHORTHORN = "TAR";
	public static final String CAT_PURA_BRAHMAN = "BAR";
	public static final String CAT_PURA_NELORE = "LAR";
	public static final String CAT_PURA_SINMENTAL = "IAR";
	public static final String CAT_PURA_BIOTIPOCARNE = "CAR";
	public static final String CAT_PURA_BUFALO = "BUR";
	public static final String CAT_OT = "MC"; // para los toros
	//TENER EN CUENTA QUE SI SE AGREGA UNA SE TIENE QUE AGREGAR AL "categorias()"
	
	
	//public static final String CAT_PBI = "PBI"; // para los animales de tambos que no realizan controles lecheros

	public static final long MAYOR_RC_SICEL1 = 4378413L;
	
	
	// --------------- attributes ---------------------

	private Establecimiento establecimientoCriador;
	private String categoria;
	private java.lang.Long id;
	private java.lang.String nombre;
	private java.lang.String rP;
	private String rpSenasa;
	private Integer codigoVerificador;
	private java.util.Date fechaNac;
	private java.lang.Boolean esBaja;
	private java.lang.String categoriaV3;
	private java.util.Date sRAFesb;
	private Foto foto;
	private ar.org.sicel.persistence.Registro regOrigen;
	private ar.org.sicel.persistence.Registro regIdentificador;
	private java.util.Set registros;
	private ar.org.sicel.persistence.Macho padre;
	private ar.org.sicel.persistence.Hembra madreGenetica;
	private ar.org.sicel.persistence.Hembra madreParto;
	private ar.org.sicel.persistence.Propietario propietario;
	private ar.org.sicel.persistence.Propietario propietarioCriador;
	private ar.org.sicel.persistence.Establecimiento establecimiento;
	private Estancia estancia;
	private java.util.Set calificacions;
	private ar.org.sicel.persistence.ComposicionRacial composicionRacial = new ComposicionRacial();
	private java.util.SortedSet evtAnimals;
	private java.util.Set evtClons;
	private Set<FichaAnimalPendiente> fichasAnimal;
	private Set<ProcAnimal> procsAnimal;
	private Integer nroLactancia; 
	private Integer nroLactInformado = 0;
	private Set<AnimalComentario> comentarios;
	private Integer asoc;
	private Integer asop;
	private Integer asom;
	private Integer dadorSemen;
	private Integer numeroTransf;
	private Integer numeroAnalADN;
	private String donante;
	private Integer mellizo;
	private String transferencia;
	private Integer tipoServicio;
	private Date fechaUltObs;
	private Date fechaTransf;
	private Date fechaServicio;
	private String apodo;
	private String rpti;
	private String taraG;
	protected java.lang.Boolean esSinonimo=false;
	
	//private EvtCria evCria;
	

public String getTaraG() {
		return taraG;
	}

	public void setTaraG(String taraG) {
		this.taraG = taraG;
	}

public static List categorias() {
		
		
		LabelValueBean[] valores = new LabelValueBean[] { 
				new LabelValueBean(Animal.CAT_PED,Animal.CAT_PED),
				new LabelValueBean(Animal.CAT_CR,Animal.CAT_CR),
				new LabelValueBean(Animal.CAT_PB,Animal.CAT_PB),
				new LabelValueBean(Animal.CAT_RC,Animal.CAT_RC),
				new LabelValueBean(Animal.CAT_PU,Animal.CAT_PU),
				new LabelValueBean(Animal.CAT_PURA_HOLANDO,Animal.CAT_PURA_HOLANDO),
				new LabelValueBean(Animal.CAT_PURA_JERSEY,Animal.CAT_PURA_JERSEY),
				new LabelValueBean(Animal.CAT_PURA_SUECA,Animal.CAT_PURA_SUECA),
				new LabelValueBean(Animal.CAT_PURA_PARDO,Animal.CAT_PURA_PARDO),
				new LabelValueBean(Animal.CAT_PURA_AYSHIRE,Animal.CAT_PURA_AYSHIRE),
				new LabelValueBean(Animal.CAT_PURA_GUERNSEY,Animal.CAT_PURA_GUERNSEY),
				new LabelValueBean(Animal.CAT_PURA_NORMANDA,Animal.CAT_PURA_NORMANDA),
				new LabelValueBean(Animal.CAT_PURA_MONTBELIARDE,Animal.CAT_PURA_MONTBELIARDE),
				new LabelValueBean(Animal.CAT_PURA_SHORTHORN,Animal.CAT_PURA_SHORTHORN),
				new LabelValueBean(Animal.CAT_PURA_BRAHMAN,Animal.CAT_PURA_BRAHMAN),
				new LabelValueBean(Animal.CAT_PURA_NELORE,Animal.CAT_PURA_NELORE),
				new LabelValueBean(Animal.CAT_PURA_SINMENTAL,Animal.CAT_PURA_SINMENTAL),
				new LabelValueBean(Animal.CAT_PURA_BIOTIPOCARNE,Animal.CAT_PURA_BIOTIPOCARNE),
				new LabelValueBean(Animal.CAT_PURA_BUFALO,Animal.CAT_PURA_BUFALO),
				new LabelValueBean(Animal.CAT_OT,Animal.CAT_OT)};
				// TODO Auto-generated method stub
		return Arrays.asList(valores);
	}

	public EvtCria getEvCria() {
		return EvtCriaDAO.findByAnimal(this);
		//return evCria;
	}

	/*public void setEvCria(EvtCria evCria) {
		this.evCria = evCria;
	}*/

	/**
	 * 
	 * @hibernate.id generator-class="native" column="id"
	 * @hibernate.generator-param name="sequence" value="gen_an_animal"
	 * 
	 */
	public java.lang.Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
		this.id = id;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @hibernate.property column="nombre"
	 * @hibernate.column name="nombre" length="60"
	 * 
	 */
	public java.lang.String getNombre() {
		return this.nombre;
	}
	
	public String getNombreLabel() {
		if (this.nombre != null) return this.nombre;
		return "Sin Nombre";
	}
	

	public void setNombre(java.lang.String nombre) {		
		this.nombre = nombre;
	}

	/**
	 * 
	 * @hibernate.property column="rP"
	 * @hibernate.column name="rP" length="12"
	 * 
	 */
	public java.lang.String getRP() {
		return this.rP;
	}

	public void setRP(java.lang.String rP) {
		this.rP = rP;
	}

	/**
	 * 
	 * @hibernate.property column="fnac"
	 * 
	 */
	public java.util.Date getFechaNac() {
		return this.fechaNac;
	}
	public String getFechaNacFormateada(){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");;
		return formato.format(this.fechaNac);
	}

	public void setFechaNac(java.util.Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	/**
	 * 
	 * @hibernate.property column="nroLactancia"
	 * @hibernate.column name="nroLactancia" not-null="false"
	 * 
	 */
	

	protected Animal() {
	}

	public java.lang.Boolean getEsActivo() {
		return !getEsBaja();
		// return this.esActivo;
	}

	// protected void setEsActivo(java.lang.Boolean esActivo) {
	// this.esActivo = esActivo;
	// }

	public java.lang.Boolean getEsBaja() {
		return "BA".equals(getEstadoActual());
		// return this.esBaja;
	}

	// protected void setEsBaja(java.lang.Boolean esBaja) {
	// this.esBaja = esBaja;
	// }

	/**
	 * 
	 * @hibernate.property column="categoriaV3"
	 * @hibernate.column name="categoriaV3" length="4"
	 * 
	 */
	public java.lang.String getCategoriaV3() {
		return this.categoriaV3;
	}

	protected void setCategoriaV3(java.lang.String categoriaV3) {
		this.categoriaV3 = categoriaV3;
	}

	/**
	 * 
	 * @hibernate.property column="SRAFesb"
	 * 
	 */
	public java.util.Date getSRAFesb() {
		return this.sRAFesb;
	}

	public void setSRAFesb(java.util.Date sRAFesb) {
		this.sRAFesb = sRAFesb;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="foto" not-null="false" outer-join="auto"
	 *                        foreign-key="FK_anim_foto"
	 * 
	 */

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	/**
	 * 
	 * @hibernate.component
	 * 
	 */
	public ar.org.sicel.persistence.ComposicionRacial getComposicionRacial() {
		return this.composicionRacial;
	}

	protected void setComposicionRacial(
			ar.org.sicel.persistence.ComposicionRacial composicionRacial) {
		this.composicionRacial = composicionRacial;
	}

	// ------------- relations ------------------

	/**
	 * 
	 * @hibernate.many-to-one column="regOri" outer-join="auto"
	 *                        foreign-key="FK_Anim_RegOri" not-null="false"
	 *                        unique="true"
	 * 
	 * @hibernate.column name="regOri" not-null="false" unique="true"
	 *                   unique-key="UN_An_Anim_regOrigen"
	 * 
	 */
	public ar.org.sicel.persistence.Registro getRegOrigen() {
		return this.regOrigen;
	}

	public void setRegOrigen(ar.org.sicel.persistence.Registro regOrigen) {
		this.regOrigen = regOrigen;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="regId" outer-join="auto"
	 *                        foreign-key="FK_Anim_RegId" not-null="false"
	 *                        unique="true"
	 * 
	 * @hibernate.column name="regId" not-null="false" unique="true"
	 *                   unique-key="UN_An_Anim_regIdentificador"
	 * 
	 */
	public ar.org.sicel.persistence.Registro getRegIdentificador() {
		return this.regIdentificador;
	}

	public void setRegIdentificador(
			ar.org.sicel.persistence.Registro regIdentificador) {
		this.regIdentificador = regIdentificador;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" cascade="all" inverse="true"
	 * @hibernate.collection-key column="animal"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.Registro"
	 * 
	 */
	public java.util.Set getRegistros() {
		return this.registros;
	}

	public void setRegistros(java.util.Set registros) {
		this.registros = registros;
	}
	public void addRegistros(java.util.Set registros) {
		if(registros !=null){
			Iterator it = registros.iterator();
			while(it.hasNext()){
				Registro r = (Registro)it.next();
				this.addRegistro(r);
				
			}
		}
		
	}

	/**
	 * 
	 * @hibernate.many-to-one column="padre" lazy="true" outer-join="auto"
	 *                        foreign-key="FK_Anim_Padre"
	 * 
	 */
	public ar.org.sicel.persistence.Macho getPadre() {
		return this.padre;
	}

	public void setPadre(ar.org.sicel.persistence.Macho padre) {
		this.padre = padre;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="madreGen" lazy="true" outer-join="auto"
	 *                        foreign-key="FK_Anim_MadreGen"
	 * 
	 */
	public ar.org.sicel.persistence.Hembra getMadreGenetica() {
		return this.madreGenetica;
	}

	public void setMadreGenetica(
			ar.org.sicel.persistence.Hembra madreGenetica) {
		this.madreGenetica = madreGenetica;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="madreParto" lazy="true" outer-join="auto"
	 *                        foreign-key="FK_Anim_MadreParto"
	 * 
	 */
	public ar.org.sicel.persistence.Hembra getMadreParto() {
		return this.madreParto;
	}

	public void setMadreParto(ar.org.sicel.persistence.Hembra madreParto) {
		this.madreParto = madreParto;
	}

	/**
	 * 
	 * @hibernate.many-to-one lazy="true" column="propietario" outer-join="auto"
	 *                        foreign-key="FK_Anim_Prop"
	 * 
	 */
	public ar.org.sicel.persistence.Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(
			ar.org.sicel.persistence.Propietario propietario) {
		this.propietario = propietario;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="estab" lazy="true" outer-join="auto"
	 *                        foreign-key="FK_Anim_Estab"
	 * 
	 */
	public ar.org.sicel.persistence.Establecimiento getEstablecimiento() {
		return this.establecimiento;
	}

	public void setEstablecimiento(
			ar.org.sicel.persistence.Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="animal"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.Calificacion"
	 * 
	 */
	public java.util.Set getCalificacions() {
		return this.calificacions;
	}

	public void setCalificacions(java.util.Set calificacions) {
		this.calificacions = calificacions;
	}

	/*
	 * 
	 * hibernate.set lazy="true" inverse="true" hibernate.collection-key
	 * column="animal" hibernate.collection-one-to-many
	 * class="ar.org.sicel.persistence.ProcAnimal"
	 * 
	 * 
	 * public java.util.Set getProcAnimals() { return this.procAnimals; }
	 * 
	 * protected void setProcAnimals(java.util.Set procAnimals) {
	 * this.procAnimals = procAnimals; }
	 */

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 *                sort="ar.org.sicel.persistence.EventosPorFechaYTipo"
	 * @hibernate.collection-key column="animal"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.EvtAnimal"
	 * 
	 */
	public java.util.SortedSet getEvtAnimals() {
		return this.evtAnimals;

		// TODO ver como poder hacer para poder recorrer FACIL ese
		// SortedSet en las 2 direcciones
	}

	public void setEvtAnimals(java.util.SortedSet evtAnimals) {
		this.evtAnimals = evtAnimals;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" inverse="true"
	 * @hibernate.collection-key column="donante"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.EvtClon"
	 * 
	 */
	public java.util.Set getEvtClons() {
		return this.evtClons;
	}

	protected void setEvtClons(java.util.Set evtClons) {
		this.evtClons = evtClons;
	}

	/*
	 * public void addProcAnimal(ProcAnimal proc) { this.procAnimals.add(proc); }
	 */

	// ---------------- business methods ----------------------
	public abstract boolean esHembra();

	public Especie getEspecie() {
		return this.composicionRacial.getEspecie();
	}

	/**
	 * Efectua el cambio, sacando al animal del establecimiento en el que estaba
	 * previamente, y colocandolo en el nuevo (si se puede). No registra ningun
	 * tipo de evento o procesamiento (son los eventos o procesamientos los que
	 * utilizan este metodo)
	 * 
	 * @param est
	 *            el nuevo establecimiento en el cual se encuentra
	 * @param nuevoRP
	 *            el nuevo RP asignado al animal
	 * 
	 */
	public void transferirAEstablecimiento(Establecimiento est, String nuevoRP,
			List mensajes, Date fechaNacimiento) throws ExcepcionIntegridad {
		// aca hay que fijarce primero antes de actualizar, porque si tocamos
		// algo
		// que hibernate se entere, seguramente lo va a guardar asi cambiado

		if ((est != getEstablecimiento()) || (nuevoRP != getRP())) {
			//Animal.checkUnicidadRPEnEstab(est, nuevoRP, mensajes,fechaNacimiento,getEstablecimiento(),getRegistroOrigen());
			Animal.checkUnicidadRPEnEstabForUpdate(this.getId(),est,nuevoRP,mensajes,fechaNacimiento,getEstablecimiento(),getRegistroOrigen(),this.getCategoria());

			// this.establecimiento.getAnimals().remove(this);
		//	this.setEstablecimientoCriador(this.getEstablecimiento());
			this.setEstablecimiento(est);
			if(est!=null)
				this.setEstancia(est.getEstancia());
			this.setRP(nuevoRP);
			// est.getAnimals().add(this);
		}
	}

	/**
	 * Transfiere el propietario, no realiza ningun tipo de control
	 * 
	 * @param p
	 * @param nuevoRP
	 */
	@SuppressWarnings("unchecked")
	public void transferirAPropietario(Propietario nuevoPropietario)
			throws ExcepcionIntegridad {
		if (nuevoPropietario != this.propietario) {
			//this.propietario.getAnimals().remove(this);
			this.setPropietario(nuevoPropietario);
			//nuevoPropietario.getAnimals().add(this);
		}
	}

	public void inicializarPadresGen(Especie esp, Hembra madre, Macho padre)
			throws ExcepcionIntegridad {
		checkProgenitorEspecie(esp, madre);
		checkProgenitorEspecie(esp, padre);
		setPadre(padre);
		setMadreGenetica(madre);
		calcularComposicionRacial(esp);
	}

	public void cambiarPadreGen(Macho padre) throws ExcepcionIntegridad {
		checkProgenitorEspecie(getEspecie(), padre);
		setPadre(padre);
		calcularComposicionRacial(getEspecie());
	}

	public void cambiarMadreGen(Hembra madre) throws ExcepcionIntegridad {
		checkProgenitorEspecie(getEspecie(), madre);
		setMadreGenetica(madre);
		calcularComposicionRacial(getEspecie());
	}
	
	public void calcularComposicionRacialTotal(Especie esp) {
		Long numReg = Long.valueOf(this.getRegOrigen().getNumero());
		if( (numReg <=Animal.MAYOR_RC_SICEL1 && AtributoDAO.findByNombre("RECALCULAR_RECATEGORIZAR_SICEL_1").getValorPorDefecto().getValor()=="1") || numReg >Animal.MAYOR_RC_SICEL1)//Si es animal de sicel 2 o es sicel 1 y admito recategorizar
			this.calcularComposicionRacial(esp);
		propagarCambioCompRacial();
	}
	public void calcularComposicionRacial(Especie esp) {
		Macho padreGen = getPadre();
		Hembra madreGen = getMadreGenetica();
		ComposicionRacial compPadre = null;
		ComposicionRacial compMadre = null;
		if (padreGen != null)
			compPadre = padreGen.getComposicionRacial();
		if (madreGen != null)
			compMadre = madreGen.getComposicionRacial();
		ComposicionRacial compAnt = new ComposicionRacial();
		compAnt= this.composicionRacial;
		this.composicionRacial.recalcular(esp, compPadre, compMadre);
		if(compAnt!= null && !compAnt.equals(this.getComposicionRacial()))
		//cambiarRazaDeclarada();
			propagarCambioCompRacial();
	}
	private void calcularComposicionRacialHijos(Especie esp) {
		Long numReg = Long.valueOf(this.getRegOrigen().getNumero());
		if( (numReg <=Animal.MAYOR_RC_SICEL1 && AtributoDAO.findByNombre("RECALCULAR_RECATEGORIZAR_SICEL_1").getValorPorDefecto().getValor()=="1") || numReg >Animal.MAYOR_RC_SICEL1)//Si es animal de sicel 2 o es sicel 1 y admito recategorizar
		{
			Macho padreGen = getPadre();
			Hembra madreGen = getMadreGenetica();
			ComposicionRacial compPadre = null;
			ComposicionRacial compMadre = null;
			if (padreGen != null)
				compPadre = padreGen.getComposicionRacial();
			if (madreGen != null)
				compMadre = madreGen.getComposicionRacial();
	
			ComposicionRacial compAnt = new ComposicionRacial();
			compAnt= this.composicionRacial;
			this.composicionRacial.recalcular(esp, compPadre, compMadre);
			cambiarRazaDeclarada();
			if(!compAnt.equals(this.getComposicionRacial()))
				propagarCambioCompRacial();
		}
		else
			propagarCambioCompRacial();
	}
	protected void propagarCambioCompRacial() {	
		Set hijos = new HashSet();
		try{
		hijos = getHijosGeneticos();
		}
		catch (Exception e) {
			hijos.addAll(AnimalDAO.getHijosGeneticos(this));
		}
		/*try{
			if((getHijosGeneticos()!=null)&&(!getHijosGeneticos().isEmpty()))
				hijos.addAll(getHijosGeneticos());
			
		}
		catch (Exception e) {
			hijos.addAll(AnimalDAO.getHijosGeneticos(this));
		}*/
		//if((getHijosGeneticos()!=null)&&(!getHijosGeneticos().isEmpty())){
		if((hijos!=null)&&(!hijos.isEmpty())){
			Iterator hijosI = hijos.iterator();
			while (hijosI.hasNext()) {
				Animal an = (Animal) hijosI.next();
				an.calcularComposicionRacialHijos(an.getEspecie());
				//an.cambiarRazaDeclarada();
				//propagarCambioCompRacial();
			}
		}
		/*if(!getEvtClons().isEmpty()){
		Iterator evClons = getEvtClons().iterator();
		while (evClons.hasNext()) {
			EvtClon evClon = (EvtClon) evClons.next();
			evClon.getAnimal();
			// TODO NOPRIMERAETAPA implementar los clones
			// NO HAY CLONAR
			
		}
		}*/
	}


	private void cambiarRazaDeclarada() {
		Macho padreGen = getPadre();
		Hembra madreGen = getMadreGenetica();
		if(padreGen!=null && madreGen!=null){
			if(padreGen.getRaza().equals(madreGen.getRaza())){
				this.getComposicionRacial().setRazaDeclarada(padreGen.getRaza());
			}
			else{
				if(this.getComposicionRacial().getRazaCalculada().getEsDesconocido())
					this.getComposicionRacial().setRazaDeclarada(this.getComposicionRacial().getRazaCalculada().getEspecie().getCruza());
				else
					this.getComposicionRacial().setRazaDeclarada(this.getComposicionRacial().getRazaCalculada());
				this.setearCategoria(true);
			}
		}
		else{
			if(madreGen!=null)
				this.getComposicionRacial().setRazaDeclarada(madreGen.getRaza());
			if(padreGen!=null)
				this.getComposicionRacial().setRazaDeclarada(padreGen.getRaza());
			
		}
		//if(this.getComposicionRacial().getRazaDeclarada().getEsCruza())
		
		
	}
	public void calcularComposicionRazaConEventos(){
		
		
		Long numReg = Long.valueOf(this.getRegOrigen().getNumero());
		if( (numReg <=Animal.MAYOR_RC_SICEL1 && AtributoDAO.findByNombre("RECALCULAR_RECATEGORIZAR_SICEL_1").getValorPorDefecto().getValor()=="1") || numReg >Animal.MAYOR_RC_SICEL1)//Si es animal de sicel 2 o es sicel 1 y admito recategorizar
		{
			calcularComposicionRacialTotal(getEspecie());
			EvtReproduccion rep =EvtReproduccionDAO.findNacimientoCria(this);
			Raza razaDeclarada = getRaza();
			if(rep!=null){
				if(rep.getEvtServicio()==null && getPadre()== null){//si no tengo padre
					 if (rep.getUsarRazaMadre()) 
							razaDeclarada = rep.getAnimal().getRaza();
						else
							razaDeclarada = rep.getAnimal().getRaza().getEspecie().getCruza();
				}
				else{//tengo servicio/padre
					if(rep.getAnimal().getRaza().getId().equals(getPadre().getRaza().getId())){
						razaDeclarada = rep.getAnimal().getRaza();
					}
				}
			}
			else{
				if(getMadreGenetica()!=null){
					if(getPadre()== null){//si no tengo padre
						 	razaDeclarada = getMadreGenetica().getRaza().getEspecie().getCruza();
					}
					else{//tengo servicio/padre
						if(getRaza().getId().equals(getPadre().getRaza().getId())){
							razaDeclarada = getRaza();
						}
					}
				}
			}
			getComposicionRacial().setRazaDeclarada(razaDeclarada);
			if((getMadreGenetica()!=null && getPadre()!=null)&&(!getMadreGenetica().getRaza().getId().equals(getPadre().getRaza().getId()))){
				if(!getComposicionRacial().getRazaCalculada().getEsDesconocido())
					getComposicionRacial().setRazaDeclarada(getComposicionRacial().getRazaCalculada());
				else
					getComposicionRacial().setRazaDeclarada(getMadreGenetica().getRaza().getEspecie().getCruza());
			}
			setearCategoria(true);
		}
		Set hijos = new HashSet();
		try{
		hijos = getHijosGeneticos();
		}
		catch (Exception e) {
			hijos.addAll(AnimalDAO.getHijosGeneticos(this));
		}
		if((hijos!=null)&&(!hijos.isEmpty())){
			Iterator hijosI = hijos.iterator();
			while (hijosI.hasNext()) {
				Animal ani = (Animal) hijosI.next();
				ani.calcularComposicionRazaConEventos();
				//AnimalDAO.updateAnimal(ani);
			}
		}
		
	}

	/**
	 * Devuelve todos los hijos geneticos (machos y hembras)
	 * 
	 */
	protected abstract Set getHijosGeneticos();
	

	public void checkProgenitorEspecie(Especie esp, Animal an)
			throws ExcepcionIntegridad {
		// si el progenitor es null, no hay que fijarce que sea de la misma
		// especie :-)
		if ((an != null) && !esp.equals(an.getEspecie())) {
			/*String tReg = getRegIdentificador().getTipoRegistro().getId();
			String nReg = getRegIdentificador().getNumero();
			String procTreg = an.getRegIdentificador().getTipoRegistro()
					.getId();
			String procNreg = an.getRegIdentificador().getNumero();*/
			String especieEsperada = getEspecie().getNombre();
			throw new ExcepcionIntegridad(MENSAJES.PROGENITOR_DISTINTA_ESPECIE,
					new String[] { getRegistroOrigen(),an.getRegistroOrigen(),
							especieEsperada });
		}
	}

	@SuppressWarnings("unchecked")
	public static void checkUnicidadRPEnEstab(Establecimiento estab, String rp,
			List mensajes, Date fechaNacimiento, Establecimiento estabViejo, String registroOriAnimal) throws ExcepcionIntegridad {
		Animal anims = null;
		if(org.apache.commons.lang.StringUtils.isNotEmpty(rp)&& fechaNacimiento!=null){
			rp = rp.trim();
			try {
				anims = AnimalDAO.findByRPyRe(null,null,estab, rp,fechaNacimiento);
			} catch (HibernateException e) {
				throw new ErrorFatal(e.getMessage());
			}
			if(anims!=null){
				if (Configuracion.getValorReglaProceso(CONF.RP_UNICO, new Date())){
					Calendar fechaNacOtroAnimal = new GregorianCalendar();
					fechaNacOtroAnimal.setTime(anims.getFechaNac());
					int anioOtroAnimal = fechaNacOtroAnimal.get(Calendar.YEAR);
					Calendar fechaNacimientoAnimal = new GregorianCalendar();
					fechaNacimientoAnimal.setTime(fechaNacimiento);
					int anioAnimal = fechaNacimientoAnimal.get(Calendar.YEAR);
					if (anioOtroAnimal == anioAnimal) {
						throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO,
								new String[] {rp,
								estab.getEstancia().getId().toString(),//estab.getId().toString(),
								//anims.getRegistroOrigen(),
								registroOriAnimal,
								//estabViejo.getId().toString(),
								estabViejo.getEstancia().getId().toString(),
								anims.getRegistroOrigen(),
								estab.getEstancia().getId().toString()});
					}
				}
				else {
					ProcMsg cod = ProcMsgDAO.create(MENSAJES.RP_NO_UNICO,
							ProcMsg.WARNING,
							new String[] {rp,
							estab.getEstancia().getId().toString(),//estab.getId().toString(),
							/*anims.getRegistroOrigen(),*/registroOriAnimal,
							estabViejo.getEstancia().getId().toString(),//estabViejo.getId().toString(),
							((Animal) (anims))
									.getRegistroOrigen(),
									estab.getEstancia().getId().toString()});//estab.getId().toString() });
					mensajes.add(cod);
				}
			}
		}
	}

	public static void checkUnicidadRPEnEstabForUpdate(Long id,Establecimiento estab, String rp,
			List mensajes, Date fechaNacimiento, Establecimiento estabViejo, String registroOriAnimal,String categoria) throws ExcepcionIntegridad,ErrorFatal {
		Animal anims = null;
		try {
			anims = AnimalDAO.findByRPyRe(id,null,estab, rp.trim(),fechaNacimiento);
		} catch (HibernateException e) {
			throw new ErrorFatal(e.getMessage());
		}
		if(anims!=null){
			rp = rp.trim();
			if (Configuracion.getValorReglaProceso(CONF.RP_UNICO, new Date())){
				Calendar fechaNacOtroAnimal = new GregorianCalendar();
				fechaNacOtroAnimal.setTime(anims.getFechaNac());
				int anioOtroAnimal = fechaNacOtroAnimal.get(Calendar.YEAR);
				Calendar fechaNacimientoAnimal = new GregorianCalendar();
				fechaNacimientoAnimal.setTime(fechaNacimiento);
				int anioAnimal = fechaNacimientoAnimal.get(Calendar.YEAR);
				if (anioOtroAnimal == anioAnimal) {
					if(!anims.getId().equals(id)){
						if(categoria !=null && categoria.equals(Animal.CAT_PED))
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO_PED,
									new String[] {rp,
									estab.getEstancia().getId().toString(),//estab.getId().toString(),
									registroOriAnimal,
									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getEstancia().getId().toString(),
											anims.getRegistroOrigen(),
											estab.getEstancia().getId().toString()});//estab.getId().toString()});
						
						else
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO,
								new String[] {rp,
									estab.getEstancia().getId().toString(),//estab.getId().toString(),
									registroOriAnimal, 
									estabViejo.getEstancia().getId().toString(),//estabViejo.getId().toString(),
									anims.getRegistroOrigen(),
									estab.getEstancia().getId().toString()});//estab.getId().toString()});
					}
				}
			}
			else {
				ProcMsg cod = ProcMsgDAO.create(MENSAJES.RP_NO_UNICO,
						ProcMsg.WARNING,
						new String[] {rp,
						estab.getEstancia().getId().toString(),//estab.getId().toString(),
						registroOriAnimal,
						estabViejo.getEstancia().getId().toString(),//estabViejo.getId().toString(),
						((Animal) (anims))
								.getRegistroOrigen(),
								estab.getEstancia().getId().toString() });//estab.getId().toString() });
				mensajes.add(cod);
			}
		}
	}
	public static void checkUnicidadRPSinonimo(Long id1,Long id2,Establecimiento estab, String rp,
			List mensajes, Date fechaNacimiento, Establecimiento estabViejo, String registroOriAnimal,String categoria) throws ExcepcionIntegridad,ErrorFatal {
		Animal anims = null;
		try {
			anims = AnimalDAO.findByRPyRe(id1,id2,estab, rp.trim(),fechaNacimiento);
			
		} catch (HibernateException e) {
			throw new ErrorFatal(e.getMessage());
		}
		if(anims!=null){
			rp = rp.trim();
			if (Configuracion.getValorReglaProceso(CONF.RP_UNICO, new Date())){
				Calendar fechaNacOtroAnimal = new GregorianCalendar();
				fechaNacOtroAnimal.setTime(anims.getFechaNac());
				int anioOtroAnimal = fechaNacOtroAnimal.get(Calendar.YEAR);
				Calendar fechaNacimientoAnimal = new GregorianCalendar();
				fechaNacimientoAnimal.setTime(fechaNacimiento);
				int anioAnimal = fechaNacimientoAnimal.get(Calendar.YEAR);
				if (anioOtroAnimal == anioAnimal) {
					if((!anims.getId().equals(id1))||(!anims.getId().equals(id2))){
						if(categoria !=null && categoria.equals(Animal.CAT_PED))
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO_PED,
									new String[] {rp,
									estab.getEstancia().getId().toString(),//estab.getId().toString(),
									registroOriAnimal,
									//estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getId().toString(),
									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getEstancia().getId().toString(),
											anims.getRegistroOrigen(),
											estab.getEstancia().getId().toString()});//estab.getId().toString()});
						
						else
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO,
								new String[] {rp,
									estab.getEstancia().getId().toString(),//estab.getId().toString(),
									registroOriAnimal,
//									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getId().toString(),
									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getEstancia().getId().toString(),
									anims.getRegistroOrigen(),
									estab.getEstancia().getId().toString()});//estab.getId().toString()});
					}{
						if(categoria !=null && categoria.equals(Animal.CAT_PED))
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO_PED,
									new String[] {rp,
									//estab.getId().toString(),
									estab.getEstancia().getId().toString(),
									registroOriAnimal,
									//estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getId().toString(),
									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getEstancia().getId().toString(),
											anims.getRegistroOrigen(),
											//estab.getId().toString()});
											estab.getEstancia().getId().toString()});
						
						else
							throw new ExcepcionIntegridad(MENSAJES.RP_NO_UNICO,
								new String[] {rp,
									//estab.getId().toString(),
									estab.getEstancia().getId().toString(),
									registroOriAnimal,
//									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getId().toString(),
									estabViejo == null ? "SIN TAMBO ANTERIORMENTE" : estabViejo.getEstancia().getId().toString(),
									anims.getRegistroOrigen(),
									//estab.getId().toString()});
									estab.getEstancia().getId().toString()});
					}
				}
			}
			else {
				ProcMsg cod = ProcMsgDAO.create(MENSAJES.RP_NO_UNICO,
						ProcMsg.WARNING,
						new String[] {rp,
						//estab.getId().toString(),
						estab.getEstancia().getId().toString(),
						registroOriAnimal, 
						//estabViejo.getId().toString(),
						estabViejo.getEstancia().getId().toString(),
						((Animal) (anims))
								.getRegistroOrigen(),
								//estab.getId().toString() });
								estab.getEstancia().getId().toString() });
				mensajes.add(cod);
			}
		}
	}
	public String getRegistroID() {
		if (getRegIdentificador() == null) {
			if (!getRegistros().isEmpty()) { // PARA la DEMO: si en la base
												// no esta seteado el registro
												// ID, tomar el primero de la
												// lista
				Registro primero = (Registro) getRegistros().iterator().next();
				return primero.toString();
			} else
				return "SINREGID";
		} else {
			return getRegIdentificador().toString();
			// String tReg = getRegIdentificador().getTipoRegistro().getId();
			// String nReg = getRegIdentificador().getNumero();
			// return tReg + nReg;
		}
	}
	
	public String getRegistroOrigen(){
		Registro regOri = getRegOrigen(); 
		if(regOri==null)
			return "SIN REGISTRO ORIGEN";
		else
			return regOri.toString();
	}

	public String getEstadoActual() {
		return getEstadoEnFecha(new Date(),null);
	}
	public String getEstadoPrevioAFecha(Evento ev) {
		String estado = "SV"; // por defecto Seca Vacia
		//Set evts = getEvtAnimals();
		//SortedSet evts = new TreeSet(new EventosPorFechaYTipo());
		//evts.addAll(getEvtAnimals());
		List evts = new ArrayList(getEvtAnimals());
		 Collections.sort(evts, new EventosPorFechaYTipo());
		
		  int desde = evts.size();
		  ListIterator iter = evts.listIterator(desde);
		if (evts.size() != 0) {
			
			while (iter.hasPrevious()) {
				EvtAnimal evt = (EvtAnimal) iter.previous();
				if(evt.getClass()!=EvtLactanciaMigrada.class && evt.getClass()!=EvtLactancia.class && evt.getClass()!=EvtTransferencia.class){
					if (evt.getFecha().before(ev.getFecha())) {
						estado = evt.getEstadoAnimal();
						break;
					} 
				}
			}
		}
		return estado;
	}
	public EvtAnimal getEventoEnFecha(Date fecha,EvtAnimal ev) {
		String estado = "SV"; // por defecto Seca Vacia
		//Set evts = getEvtAnimals();
		//SortedSet evts = new TreeSet(new EventosPorFechaYTipo());
		//evts.addAll(getEvtAnimals());
		List evts = new ArrayList(getEvtAnimals());
		 Collections.sort(evts, new EventosPorFechaYTipo());
		EvtAnimal evAnte = null; 
		if (evts.size() != 0) {
			Iterator eventos = this.getEvtAnimals().iterator();
			boolean fin = false;
			while (eventos.hasNext() && !fin) {
				EvtAnimal evt = (EvtAnimal) eventos.next();
				if(estado.equalsIgnoreCase("BA")){
					if(evt.getClass()!=EvtLactanciaMigrada.class){
						if (evt.getFecha().before(fecha)||((evt.getFecha().equals(fecha))&&((ev!=null && !ev.equals(evt))||(ev==null)))) {
							//estado = evt.getEstadoAnimal();
							evAnte = evt;
						} else
							fin = true;
					}
					
				}
				//if(evt.getClass()!=EvtLactanciaMigrada.class && evt.getClass()!=EvtLactancia.class 
						//&& evt.getClass()!=EvtTransferencia.class && evt.getClass()!=EvtAnimalModificacion.class){
				if(evt.getClass()!=EvtLactanciaMigrada.class && evt.getClass()!=EvtLactancia.class &&  evt.getClass()!=EvtAnimalModificacion.class){
					if (evt.getFecha().before(fecha)||((evt.getFecha().equals(fecha))&&((ev!=null && !ev.equals(evt))||(ev==null)))) {
						//estado = evt.getEstadoAnimal();
						evAnte = evt;
					} else
						fin = true;
				}
			}
		}
		return evAnte;
	}
	public String getEstadoEnFecha(Date fecha,EvtAnimal ev) {
		String estado = "SV"; // por defecto Seca Vacia
		//Set evts = getEvtAnimals();
		//SortedSet evts = new TreeSet(new EventosPorFechaYTipo());
		//evts.addAll(getEvtAnimals());
		List evts = new ArrayList(getEvtAnimals());
		 Collections.sort(evts, new EventosPorFechaYTipo());
		 
		if (evts.size() != 0) {
			Iterator eventos = this.getEvtAnimals().iterator();
			boolean fin = false;
			while (eventos.hasNext() && !fin) {
				EvtAnimal evt = (EvtAnimal) eventos.next();
				if(estado.equalsIgnoreCase("BA")){
					if(evt.getClass()!=EvtLactanciaMigrada.class){
						if (evt.getFecha().before(fecha)||((evt.getFecha().equals(fecha))&&((ev!=null && !ev.equals(evt))||(ev==null)))) {
							estado = evt.getEstadoAnimal();
						} else
							fin = true;
					}
					
				}
				//if(evt.getClass()!=EvtLactanciaMigrada.class && evt.getClass()!=EvtLactancia.class && evt.getClass()!=EvtTransferencia.class && evt.getClass()!=EvtAnimalModificacion.class){
				if(evt.getClass()!=EvtLactanciaMigrada.class && evt.getClass()!=EvtLactancia.class &&  evt.getClass()!=EvtAnimalModificacion.class){
					if (evt.getFecha().before(fecha)||((evt.getFecha().equals(fecha))&&((ev!=null && !ev.equals(evt))||(ev==null)))) {
						estado = evt.getEstadoAnimal();
					} else
						fin = true;
				}
			}
		}
		return estado;
	}
	/**
	 * Al informar un evento retroactivo se debe invocar a este metodo.
	 * Metodo que intenta segun la nueva insercion del evento setear el estado correcto del animal
	 * teniendo en cuenta todo el resto de los eventos.
	 * En caso de que no se puede generar el estado correctamente se emitira un mensaje de error 
	 * @throws ExcepcionIntegridad 
	 * @throws ExcepcionIntegridad 
	 *
	 */
	//public void setEstadoRetroactivo(EvtAnimal evt,List msgs) throws ExcepcionIntegridad {
	public void setEstadoRetroactivo(Date fechaEvt,List msgs, String tipo) throws ExcepcionIntegridad {
		  List<Object> listaEventos = new ArrayList<Object>();
	        listaEventos.addAll(this.getEvtAnimals());
	        Collections.sort(listaEventos, new EventosPorFechaYTipo());
		  int desde = listaEventos.size();
		  if(desde !=0){
		  ListIterator iter = listaEventos.listIterator(desde);
		  if(iter.hasPrevious()){
	      EvtAnimal evtAnimal = (EvtAnimal) iter.previous();
	      //if(evtAnimal.getFecha().after(evt.getFecha())){
	      if(evtAnimal.getFecha().after(fechaEvt)){
	    	  if(!listaEventos.isEmpty()){
					//Evento ant= (Evento) allEventos.first();
					Iterator it = listaEventos.iterator();
					boolean encontroLugar = false;
					try {
					while(it.hasNext()){
						Evento ev = (Evento)it.next();
						if(!encontroLugar){
							//if((evt.getFecha().equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC)))
							if((fechaEvt.equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC)))
							//if((evt.getFecha().equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_CONTROL_ANIMAL)))
								encontroLugar=true;
							if(ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))
								borrarLactanciasColgadas((EvtLactancia) ev);
							
						}
						else{
							if(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))
								actualizarEventosPosterioresARetroactivo(this,(EvtAnimal) ev, msgs);
							else
								borrarLactanciasColgadas((EvtLactancia) ev);

						}
						
					}
					} catch (ExcepcionIntegridad e) {
						
							throw new ExcepcionIntegridad(
									MENSAJES.NO_ES_POSIBLE_SETEO_RETROACTIVO, new String[] {
											tipo});
						
					}
			}
	}
		  }
	}
		
	}
	public boolean borrarLactanciasColgadas(EvtLactancia ev) throws ExcepcionIntegridad{
		EvtAnimal evFin = ev.getEventoFinalizaLactancia();
		if(evFin==null){//tengo que borrarla
				if(this.getEvtAnimals().contains(ev))
					this.getEvtAnimals().remove(ev);				
				if (ev.isEsOficial() && AnimalesLactanciasOficiales.getAnimales()!=null)
	        		AnimalesLactanciasOficiales.getAnimales().remove(this);
				if(ev.getId()!=null)
					ev.ejecutarBaja();
				AnimalDAO.updateAnimal(this);
				HibernateFactory.getSession().flush();
				return true;
				}
		return false;
		}
	
	
	/**
	 * Metodo que realiza el nuevo calculo de la transicion de estado del animal debido a que se informo un evento retroactivo.
	 * tambien en el caso si evento a evaluar finaliza lactancia se debe calcular nuevamente (en caso de que exista es borrada) y es agregada
	 * a la coleccion de evtAnimal del animal y persistida en base
	 * @param animal
	 * @param ev
	 * @param msgs
	 * @throws ExcepcionIntegridad
	 */
	//private void actualizarEventosPosterioresARetroactivo(Animal animal, EvtAnimal ev, List msgs) throws ExcepcionIntegridad {
	public  void actualizarEventosPosterioresARetroactivo(Animal animal, EvtAnimal ev, List msgs) throws ExcepcionIntegridad {
//en este me da error de null
		boolean finalizaLacAntesCalcularEstRetroac =ev.isFinalizaLactancia(); 
		MatrizTransiciones.calcularTransicionEv(this,ev, msgs);
		if (ev.isFinalizaLactancia()||finalizaLacAntesCalcularEstRetroac) {
			// si el ev finaliza una lactancia, hay q calcularla y persistirla
			// si bien puede ser que no haya ev prod entre el evIni y el evFin
			
					EvtLactancia lacti =null;
					Session session = HibernateFactory.getSession();
					SortedSet even = ev.getDependientes();
					//ArrayList arrayList2 = null;
					List<Object> arrayList2 = new ArrayList<Object>();
					arrayList2.addAll(this.getEvtAnimals());
			        Collections.sort(arrayList2, new EventosPorFechaYTipo());
					if(!even.isEmpty()){
						arrayList2.addAll(this.getEvtAnimals());
						for(Object lact : even){
							if(lact.getClass()==EvtLactancia.class){
								lacti = (EvtLactancia)lact;
								if(arrayList2.contains(lacti))//esto lo hago pq en dependientes puede quedar una lactancia q falta q hibernate la elimine y el arrayList2 ya no esta
									break;
								}
							}
					}
					//if(lacti!=null && arrayList2.contains(lacti)){
					if(lacti!=null){
						arrayList2.remove(ev);
						arrayList2.remove(lacti);
						this.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
						this.getEvtAnimals().addAll(arrayList2);
						this.getEvtAnimals().add(ev);
						
						if (lacti.isEsOficial()&& AnimalesLactanciasOficiales.getAnimales()!=null)
			        		AnimalesLactanciasOficiales.getAnimales().remove(this);
						if(lacti.getId()!=null){
							lacti.ejecutarBaja();
							this.getEvtAnimals().add(ev);
						}
						//else
						ev.getDependientes().remove(lacti);
						AnimalDAO.updateAnimal(this);
					}
			//	}
				if(ev.isFinalizaLactancia() ){
					EvtLactancia evtLactancia = EvtLactancia.calcularLactanciaCerrada(this, ev);
					if(evtLactancia!=null){
						

						try {
							session.save(evtLactancia);
							ArrayList arrayList = new ArrayList(this.getEvtAnimals());
							this.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
							arrayList.add(evtLactancia);
							this.getEvtAnimals().addAll(arrayList);
							//this.getEvtAnimals().add(evtLactancia);
							ev.addDependiente(evtLactancia);
							//session.flush();
							
						} catch (HibernateException e) {
							throw new ErrorFatal(
								"Error capa persistencia al agregar lactancia cerrada : "
										+ e);
						}
					}
				}
			}
	}
	@SuppressWarnings( { "unchecked", "unchecked" })
	public void addEventoAnimal(EvtAnimal ev, List msgs)
			throws ExcepcionIntegridad {
		// puede ser que no haya ningun elemento aca, si el animal se dio de alta, hay por lo menos 1: el alta,
		// pero si se informo como una reproduccion y no se informaron eventos despues sobre la cria, hay
		// que tomar como ultima fecha la fecha de nacimiento.boolean reproduccionRetro = false;
		Lactancia ultimaLactanciaCerrada = null;
		//int nroLactancia = 0;
		Integer cBaj = this.getRegOrigen().getCodigoBaja();
		if(cBaj!=null && cBaj.equals(new Integer(1))){
			throw new ExcepcionIntegridad(
					MENSAJES.ANIMAL_CODIGO_BAJA_EN_1_NO_PUEDE_RECIBIR_EVENTOS, new String[] {
							this.getRegistroOrigen()});
		}
		Date ultimaFecha = null;
		Evento ultimo = null;
		SortedSet allEventos = this.getAllEventosSinLactanciaAbiertaNiModifNiAlta();
		if(allEventos.isEmpty())
			ultimaFecha = getFechaNac();
		else{
			ultimo = (Evento) allEventos.last();
			ultimaFecha = ultimo.getFecha();
		}
		
		// chequeos Eventos de tipo servicio y eventos de tipo preñez
		if(ev.getNombreTipo().equals(Evento.EVT_TIPO_SVC)||	ev.getNombreTipo().equals(Evento.EVT_TIPO_PRE)){
	        List<Object> listaEventos = new ArrayList<Object>();
	        listaEventos.addAll(this.getEvtAnimals());
	        if(listaEventos.isEmpty())
	        	if(ultimaFecha.after(ev.getFecha()))
	        		if(ultimo!=null){
	        			if(ultimo.getClass() != EvtLactanciaMigrada.class )
	        				throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_ANTERIOR_A_ULTIMO, new String[] {StringUtils.formatDate(ev.getFecha()),StringUtils.formatDate(ultimaFecha) });
	        		}
	        		else//si es anterior al alta
	        			throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_ANTERIOR_A_ULTIMO, new String[] {StringUtils.formatDate(ev.getFecha()),StringUtils.formatDate(ultimaFecha) });
	        Collections.sort(listaEventos, new EventosPorFechaYTipo());
	        int desde = listaEventos.size();
	        for (ListIterator iter = listaEventos.listIterator(desde); desde != 0 && iter.hasPrevious();) {
	        	EvtAnimal evtAnimal = (EvtAnimal) iter.previous();
        		//if (evtAnimal.getClass() == EvtReproduccion.class && ev.getFecha().before(evtAnimal.getFecha())) {
	        	//se modifica ya que a partir de ahora se pueden informar servicios retro mmmmmmmmm
	        	if (ev.getNombreTipo().equals(Evento.EVT_TIPO_PRE) && evtAnimal.getClass() == EvtReproduccion.class && ev.getFecha().before(evtAnimal.getFecha())) {
        			//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
	        		if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
	        			throw new ExcepcionIntegridad(
			        			MENSAJES.FECHA_EVENTO_ANT_REP, new String[] {
			        			StringUtils.formatDate(evtAnimal.getFecha()),
			        			StringUtils.formatDate(ev.getFecha()) }); 
		        	}
	        	}
	        }	        
	        // TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
//	      se modifica ya que a partir de ahora se pueden informar servicios retro mmmmmmmmm
	        if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
		        if (ev.getFecha().before(DateUtils.menos(ultimaFecha, Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD))))) {
	        		throw new ExcepcionIntegridad(
							MENSAJES.FECHA_EVT_ANT_X_MESES, new String[] {this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD),
									StringUtils.formatDate(ultimaFecha),
									StringUtils.formatDate(ev.getFecha()) }); 
	        	}
	        }
		}else 
		 if (ev.getNombreTipo().equals(Evento.EVT_TIPO_REP)){
			ultimaLactanciaCerrada = buscarUltimaLactanciaCerrada(ev);
		}else 
			if(ev.getNombreTipo().equals(Evento.EVT_TIPO_CONTROL_ANIMAL)){
				// para el caso de los eventos control se chequea la retroactividad hasta la cant de días 
				// que indique la variable de configuración desde el último evento informado
				int cantDias = DateUtils.diasEntre(ev.getFecha(),ultimaFecha);
				
				//int cantDiasMax = 125;// no se por que estaba esto, estaba mal detectado 21/06/2012
				int cantDiasMax  = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD_CONTROL));
				if(cantDias > cantDiasMax)
					throw new ExcepcionIntegridad(MENSAJES.NO_RETROACTIVIDAD_CONTROL,
							new String[] {String.valueOf(this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD_CONTROL)),//this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD_CONTROL),
								StringUtils.formatDate(ultimaFecha),
								StringUtils.formatDate(ev.getFecha())});
				ultimaLactanciaCerrada = buscarUltimaLactanciaCerrada(ev);
			}
			else 
				if(ev.getNombreTipo().equals(Evento.EVT_TIPO_SEC)){
					// para el caso de los eventos secada se chequea la retroactividad hasta la cant de días 
					// que indique la variable de configuración desde el último evento informado
					int cantDias = DateUtils.diasEntre(ev.getFecha(),ultimaFecha);
					int cantDiasMax = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD_SECADA));
					if(cantDias > cantDiasMax)
						throw new ExcepcionIntegridad(MENSAJES.NO_RETROACTIVIDAD_SECADA,
								new String[] {this.getRaza().getParametro(Raza.DIAS_MAX_RETROACIVIDAD_SECADA),
									StringUtils.formatDate(ultimaFecha),
									StringUtils.formatDate(ev.getFecha())});
					ultimaLactanciaCerrada = buscarUltimaLactanciaCerrada(ev);
			}
				else
					if(ultimaFecha.after(ev.getFecha()))
						if(ultimo!=null && ev.getClass() == EvtTransferencia.class){
							EvtTransferencia evtT = (EvtTransferencia)ev;
							
		        			if(ultimo.getClass() != EvtLactanciaMigrada.class && !evtT.isImplicita())
		        				throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_ANTERIOR_A_ULTIMO, new String[] {StringUtils.formatDate(ev.getFecha()),StringUtils.formatDate(ultimaFecha) });
		        		}
		        		else
		        			throw new ExcepcionIntegridad(MENSAJES.FECHA_EVENTO_ANTERIOR_A_ULTIMO, new String[] {StringUtils.formatDate(ev.getFecha()),StringUtils.formatDate(ultimaFecha) });

		MatrizTransiciones.calcularTransicionEv(this, ev, msgs);
		
		if(ev.getClass() == EvtReproduccion.class){
			EvtReproduccion evtReprod = (EvtReproduccion) ev;
			if(!evtReprod.getAbortoLargo().booleanValue()&&evtReprod.getEvtCrias().isEmpty()&&evtReprod.isIniciaLactancia()){
    			throw new ExcepcionIntegridad(MENSAJES.ERROR_ABORTO_CORTO_NO_INICIA_LACTANCIA, new String[] {this.getRegistroOrigen()});        		
			}
		}
		 //modificacion para lanzar excepcion si el evento inicia lactancia y no fue informado el numero de lactancia		
        if((ev.getNombreTipo().equals(Evento.EVT_TIPO_EST)||ev.getNombreTipo().equals(Evento.EVT_TIPO_REP))&& ev.isIniciaLactancia()){
        	this.chequeosLactancias(ev,ultimaLactanciaCerrada,msgs);
        	
        }
        
		this.getEvtAnimals().add(ev);
		if (ev.isFinalizaLactancia()) {
			// si el ev finaliza una lactancia, hay q calcularla y persistirla
			// si bien puede ser que no haya ev prod entre el evIni y el evFin
			EvtLactancia evtLactancia = EvtLactancia.calcularLactanciaCerrada(this, ev);
			
				if(evtLactancia!=null){
					this.getEvtAnimals().add(evtLactancia);
					ev.addDependiente(evtLactancia);
					/*Session session = HibernateFactory.getSession();
					try {
						session.save(evtLactancia);
						session.flush();
					} catch (HibernateException e) {
						throw new ErrorFatal(
							"Error capa persistencia al agregar lactancia cerrada : "
									+ e);
					}*/
				}
			//}
		}

	}
	/**
	 * se chequea que el numero de lactancia sea valido
	 * chequeos:
	 * si numero de lactancia = 0 error
	 * 
	 * si el numero de lactancia informado es igual al ultimo numero de lactancia 
	 * (cerrada o en curso) error
	 * 
	 * si hay lactancia en curso si el numero de lactancia informado es menor al 
	 * de la lactancia en curso entonces si o si el informado debe ser mayor al 
	 * numero de la ultima lactancia cerrada. ejem: cerrada 4, en curso 6, informada 5 sino error
	 * si no hay lactancia en curso y el informado es menor al ultimo numero de la cerrada error
	 * 
	 * en caso de que (no haya lactancia en curso y si cerrada) o (haya lactancia en 
	 * curso y cerrada y el informado es menor que la en curso) ejem el mismo que el anterior
	 * se van a chequear los dias minimos entre inicios de lactancias en caso de que no de--> error
	 * 
	 * 
	 * @param eventoInicia
	 * @param ultimaCerrada
	 * @param msgs
	 * @throws ExcepcionIntegridad
	 */
	public void chequeosLactancias(EvtAnimal eventoInicia,Lactancia ultimaCerrada, List msgs) throws ExcepcionIntegridad{
		System.out.println("inicia lactancia");
		int numeroInformado =0;
		int ultimoNumeroLactanciaCerrada = (ultimaCerrada!=null)?ultimaCerrada.getNroLact():0;
		Date fechaInicioLact = new Date();
		if(eventoInicia.getNombreTipo().equals(Evento.EVT_TIPO_REP)){
			EvtReproduccion evr = (EvtReproduccion)eventoInicia;
			numeroInformado = evr.getNroLactancia();
			fechaInicioLact = evr.getFechaLactancia();//fecha de parto + 1
		}
		else
			if(eventoInicia.getNombreTipo().equals(Evento.EVT_TIPO_EST)){
				EvtEstado evr = (EvtEstado)eventoInicia;
				numeroInformado = evr.getNumeroLactancia();
				fechaInicioLact = evr.getFechaInicioLactancia();
			}
		Lactancia ultimaLac = AnimalDAO.getUltimaLactancia((Hembra) this);
		System.out.println("numero informado --> "+ numeroInformado);
		if(numeroInformado == 0)
    		throw new ExcepcionIntegridad(MENSAJES.NRO_LACT_NO_INF, new String[] {eventoInicia.getNombreTipo(),(ultimaLac!=null)?ultimaLac.getNroLact().toString():"[No informado aún]"});
		if(ultimaLac!=null){
			System.out.println("numero ultima lactancia (cerrada o curso) --> "+ ultimaLac.getNroLact().intValue());
		if(numeroInformado == ultimaLac.getNroLact().intValue())//ya existe una lactancia con el numero informado
			throw new ExcepcionIntegridad(MENSAJES.ERROR_NRO_LACT, new String[] {this.getRegistroOrigen(), (ultimaLac!=null)?ultimaLac.getNroLact().toString():""});
		}
		
		EvtLactancia lacCurso =null;
		if(((Hembra)this).getLactanciaEnCurso()!=null)
			lacCurso=	(EvtLactancia) ((Hembra)this).getLactanciaEnCurso();
		if(lacCurso!=null){//tiene lactancia en curso
			System.out.println("numero lactancia en curso --> "+ lacCurso.getNroLact().intValue());
			System.out.println("numero lactancia cerrada --> "+ ultimoNumeroLactanciaCerrada);
			if(numeroInformado < lacCurso.getNroLact().intValue() && !(numeroInformado > ultimoNumeroLactanciaCerrada))
				/*if(!(numeroInformado < lacCurso.getNroLact().intValue() && numeroInformado > ultimoNumeroLactanciaCerrada))*/
					throw new ExcepcionIntegridad(MENSAJES.ERROR_NRO_LACT, 
							new String[] {this.getRegistroOrigen(), (ultimaLac!=null)?ultimaLac.getNroLact().toString():""});
		}
		else{
			if(numeroInformado < ultimoNumeroLactanciaCerrada)//ya existe una lactancia con el numero informado
					throw new ExcepcionIntegridad(MENSAJES.ERROR_NRO_LACT, 
							new String[] {this.getRegistroOrigen(), String.valueOf(ultimoNumeroLactanciaCerrada)});
		}
		if((lacCurso == null && ultimaCerrada!=null)||(lacCurso !=null && numeroInformado < lacCurso.getNroLact().intValue() && ultimaCerrada!=null)){
			int cantLact = (int)(numeroInformado - ultimoNumeroLactanciaCerrada);
			Integer diasMin = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MIN_ENTRE_LACT));
			//Integer diasMin = new Integer(77);//aux);
			//if(DateUtils.mas(ultimaCerrada.getFechaInicio(), cantLact*diasMin).after(eventoInicia.getFecha())){
			if(DateUtils.mas(ultimaCerrada.getFechaInicio(), cantLact*diasMin).after(fechaInicioLact)){
				System.out.println("error por dias entre inicio ");
				throw new ExcepcionIntegridad(MENSAJES.DIAS_ENTRE_LACT, new String[]{Integer.toString(diasMin), DateUtils.format(ultimaCerrada.getFechaInicio(), null)});
			}
		}
		else{//problematico
			if(lacCurso!=null && numeroInformado > lacCurso.getNroLact().intValue()){
				int cantLact = (int)(numeroInformado - lacCurso.getNroLact().intValue());
				int aux = Integer.parseInt(this.getRaza().getParametro(Raza.DIAS_MIN_ENTRE_LACT));
				Integer diasMin = new Integer(aux);
				//if(DateUtils.mas(lacCurso.getFechaInicio(), cantLact*diasMin).after(eventoInicia.getFecha())){
				if(DateUtils.mas(lacCurso.getFechaInicio(), cantLact*diasMin).after(fechaInicioLact)){
					System.out.println("error por dias entre inicio ");
					throw new ExcepcionIntegridad(MENSAJES.DIAS_ENTRE_LACT, new String[]{Integer.toString(diasMin), DateUtils.format(ultimaLac.getFechaInicio(), null)});	
				}
			}
			
		}
		//ProcMsg msg = this.chequearNumLactanciaYEdad(numeroInformado,eventoInicia.getFecha());
		ProcMsg msg = this.chequearNumLactanciaYEdad(numeroInformado,fechaInicioLact);
		if(msg!=null)
			msgs.add(msg);
		System.out.println("numeracion ok ");
		
	}
	/**
	 * metodo que busca la ultima lactancia del animal y chequea que el evento informado sea valido.
	 * no es valido cuando se informa un evento anterior a la ultima lactancia cerrada.
	 * "FECHA_EVT_ANT_SEGUNDA_LACTANCIA" ocurre cuando se informa un evento que afecta a una lactancia 
	 * anterior a la ultima cerrada y esto no se permite
	 * "FECHA_EVT_ANT_A_INICIO_UNICA_LACTANCIA" ocurre cuando se informa un evento anterior a la ultima lactancia cerrada
	 * @param ev(parto, control o secada)
	 * @return
	 * @throws ExcepcionIntegridad
	 */
	private Lactancia buscarUltimaLactanciaCerrada(EvtAnimal ev) throws ExcepcionIntegridad {
    	List lactancias = ((Hembra)this).getLactanciasCerradas(); 
    	
    	Collections.sort(lactancias, new EventosPorFechaYTipo());
	    int desdes = lactancias.size();
	    int cantidad = 0;
	    Lactancia lact=null;
	    boolean reproduccionRetro = false;
	    Lactancia lactanciaUltimaCerrada = null;
	    for (ListIterator iter = lactancias.listIterator(desdes); desdes != 0 && 
	        			iter.hasPrevious();) {
	        	Lactancia lactancia = (Lactancia) iter.previous();
	        	if(lactanciaUltimaCerrada == null)//seria la ultima lactancia
        			lactanciaUltimaCerrada = lactancia;
	        	if(ev.getFecha().before(lactancia.getFecha())){
	        		cantidad ++;
	        		reproduccionRetro=true;
	        		//nroLactancia = lactancia.getNroLact();
	        		lact=lactancia;
	        		/*if(cantidad==1){
	        			lactanciaUltimaCerrada = lactancia;
	        		}*/
	        	}
	        	if(cantidad>1){
	        		throw new ExcepcionIntegridad(
    					MENSAJES.FECHA_EVT_ANT_SEGUNDA_LACTANCIA, new String[] {ev.getNombreTipo(),DateUtils.format(ev.getFecha(),null),DateUtils.format(lactancia.getFecha(),null) }); 
	        	}
	        }
	        if(lact!=null && reproduccionRetro){
	        	if(ev.getFecha().before(lact.getFechaInicio()))
	        		throw new ExcepcionIntegridad(
    					MENSAJES.FECHA_EVT_ANT_A_INICIO_UNICA_LACTANCIA, new String[] {ev.getNombreTipo(),DateUtils.format(ev.getFecha(),null),DateUtils.format(lact.getFechaInicio(),null) });
	        }
		return lactanciaUltimaCerrada;
	}

	/**
	 * metodo que borra la laqctancia del animal con la fecha de inicio pasada como parametro
	 * @param fechaEvtLact
	 * @throws ExcepcionIntegridad 
	 */
	@SuppressWarnings("unchecked")
	private void borrarLactanciaConFechaDeInicio(Date fechaEvtLact) throws ExcepcionIntegridad {
		List lactancias = this.getEventos(Evento.EVT_TIPO_LAC);
		EvtLactancia lact= null;
    	Collections.sort(lactancias, new EventosPorFechaYTipo());
	        int desdes = lactancias.size();
	       
	        for (ListIterator iter = lactancias.listIterator(desdes); desdes != 0 && 
	        			iter.hasPrevious();) {
	        	EvtLactancia lactancia = (EvtLactancia) iter.previous();
	        	Date fecha = lactancia.getFechaInicio().getClass() == Timestamp.class ? new java.util.Date(lactancia.getFechaInicio().getTime()) : lactancia.getFechaInicio();
				 
				if(fecha.compareTo(fechaEvtLact)==0)
					lact =lactancia;
	        }
		
		if(lact!=null){
			if (lact.isEsOficial())
        		AnimalesLactanciasOficiales.getAnimales().remove(this);
			//this.getEvtAnimals().remove(lact);
			lact.ejecutarBaja();
			
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Agrega todos los eventos relacionados con el animal mas el evento alta
	 */
	public SortedSet getAllEventosSinLactanciaAbierta() {
		// Eventos de animal
		SortedSet result = new TreeSet(new EventosPorFechaYTipo());
		result.addAll(getEvtAnimals());
		// Alta (se supone solo una)
		try {
			EvtAlta a = EvtAltaDAO.findByAnimal(this.getId());
			if (a != null)
				result.add(a);
		} catch (HibernateException he) {
			throw new ErrorFatal(
					"Imposible calcular eventos. Error de capa de persistencia: "
							+ he.toString());
		}
		return result;
	}
	public SortedSet getAllEventosSinLactanciaAbiertaNiModifNiAlta() {
		// Eventos de animal
		SortedSet result = new TreeSet(new EventosPorFechaYTipo());
		
		if(!getEvtAnimals().isEmpty()){
		Iterator it = getEvtAnimals().iterator();
		while(it.hasNext()){
			EvtAnimal ev = (EvtAnimal)it.next();
			if(!(ev instanceof EvtAnimalModificacion)) 
				result.add(ev);
			}
		}
		return result;
	
	}
	/**
	 * metodo que retorna un sortedset con todos los eventos animal mas el evento alta menos 
	 * el evento modificacion
	 * @return
	 */
	public SortedSet getAllEventosSinLactanciaAbiertaNiModif() {
		// Eventos de animal
		SortedSet result = new TreeSet(new EventosPorFechaYTipo());
		
		if(!getEvtAnimals().isEmpty()){
		Iterator it = getEvtAnimals().iterator();
		while(it.hasNext()){
			EvtAnimal ev = (EvtAnimal)it.next();
			if(!(ev instanceof EvtAnimalModificacion)) {
				result.add(ev);
				
			}
		}
		//result.addAll(getEvtAnimals());
	}
		// Alta (se supone solo una)
		EvtAlta a = EvtAltaDAO.findByAnimal(this.getId());
			if (a != null)
				result.add(a);
		return result;
	}

	/**
	 * Devuelve todos los eventos persistidos + la lactancia Actual
	 * 
	 * @return
	 */
	public Set getAllEventos() {
		// Eventos de animal y lactancia abierta
		SortedSet result = getAllEventosSinLactanciaAbierta();
		agregarLactancia(result);
		return result;
	}

	protected abstract void agregarLactancia(SortedSet result);

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("( ");
		result.append(getId().toString());
		result.append(" ) ");
		result.append(String.format("%tF", new Object[] { getFechaNac() }));
		// displaytag hace el tostring de cada animal y hibernate levanta los
		// registros de cada animal, y es muy lento
		// result.append(" ");
		// result.append(this.getRegistroID().toString());
		return result.toString();
	}

	/**
	 * agrega el registro y setea el animal del registro
	 * 
	 * @param reg
	 */
	@SuppressWarnings("unchecked")
	public void addRegistro(Registro reg) {
		this.registros.add(reg);
		reg.setAnimal(this);

	}

	/**
	 * Devuelve la raza declarada. Si no tiene ninguna raza declarada, entonces
	 * la raza calculada
	 * 
	 * @return
	 */
	public Raza getRaza() {
		if (this.composicionRacial.getRazaDeclarada() != null)
			return this.composicionRacial.getRazaDeclarada();
		else
			return this.composicionRacial.getRazaCalculada();

	}

	public int getEdadEnDiasAl(Date fecha) {
		return DateUtils.diasEntre(fechaNac, fecha);
	}

	/**
	 * Retorna el evento de tipo tipoEv que se produjo en la fecha dada, null si
	 * en esa fecha no se produjo ningun evento de ese tipo. Notar que los
	 * eventos son unicos por dia y tipo (clave candidata seria
	 * animal-anio-mes-dia-tipoEvento)
	 * 
	 * @param fecha
	 * @param tipoEv
	 * @return
	 */
	public EvtAnimal getEventoEnFecha(Date fecha, String tipoEv) {
		EvtAnimal resultado = null;
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext() && resultado == null) {
			EvtAnimal evt = (EvtAnimal) it.next();
//			Date fechaEvt = evt.getFecha();
			if (DateUtils.mismoDia(fecha, evt.getFecha())
					&& evt.getNombreTipo().equals(tipoEv)) {
				resultado = evt;
			}
		}
		return resultado;
	}
	/**
	 * metodo que retorna todos los eventos que finalizan lactancia del animal
	 * @return
	 */
	public List getEventoFinalizanLactancia() {
		List resultado = new ArrayList();
		
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
//			Date fechaEvt = evt.getFecha();
			if (evt.isFinalizaLactancia()) {
				resultado.add(evt);
			}
		}
		return resultado;
	}
	public EvtAnimal getEventoEnFechaHora(Date fecha, String tipoEv) {
		EvtAnimal resultado = null;
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext() && resultado == null) {
			EvtAnimal evt = (EvtAnimal) it.next();
//			Date fechaEvt = evt.getFecha();
			if (fecha.equals(evt.getFecha())
					&& evt.getNombreTipo().equals(tipoEv)) {
				resultado = evt;
			}
		}
		return resultado;
	}
	public EvtAnimal getEventoEnFecha(Date fecha, List tipoEventos) {
		EvtAnimal resultado = null;
		if(!tipoEventos.isEmpty()){
			Iterator it = this.getEvtAnimals().iterator();
			while (it.hasNext() && resultado == null) {
				EvtAnimal evt = (EvtAnimal) it.next();
	//			Date fechaEvt = evt.getFecha();
				if (DateUtils.mismoDia(fecha, evt.getFecha())
						//&& evt.getNombreTipo().equals(tipoEv)) {
						&& tipoEventos.contains(evt.getNombreTipo())) {
					resultado = evt;
				}
			}
		}
		return resultado;
	}

	public Establecimiento getEstablecimientoCriador() {
		/*if (this.establecimientoCriador == null) {
			// Establecimiento criador = null;
			EvtAlta alta = EvtAltaDAO.findByAnimal(id);
			if (alta != null)
				this.establecimientoCriador = alta.getEstablecimiento();
			else {
				EvtReproduccion repr = EvtReproduccionDAO
						.findNacimientoDe(this);
				if (repr != null)
					this.establecimientoCriador = repr.getEstablecimiento();
			}
			if (this.establecimientoCriador == null) // si el criador no esta
														// seteado todabia
				if (getEvtAnimals().isEmpty())
					this.establecimientoCriador = getEstablecimiento();
				else
					this.establecimientoCriador = ((EvtAnimal) getEvtAnimals()
							.first()).getEstablecimiento();
		}*/
		return this.establecimientoCriador;
		
	}

	@SuppressWarnings("unchecked")
	public List getEventos(String tipoEv) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getNombreTipo().equals(tipoEv))
				resultado.add(evt);

		}
		return resultado;
	}

	public String getCategoria() {
		return this.categoria;
	}
	/**
	 * Obtiene una lista de eventos que se produjeron en este animal entre las
	 * fechas dadas.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getEventosEntre(Date fechaInicio, Date fechaFin) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (DateUtils.entre(evt.getFecha(), fechaInicio, fechaFin))
				resultado.add(evt);
		}
		return resultado;
	}
	public Evento getEventoAnteriorFecha(Date fechaInicio) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		EvtAnimal evtAnterior = null;
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getFecha().after(fechaInicio))
				return evtAnterior;
			evtAnterior = evt;
		}
		return null;
	}
	public Evento getEventoAnteriorFecha(Date fechaInicio,String tipo) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		EvtAnimal evtAnterior = null;
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getFecha().after(fechaInicio))
				return evtAnterior;
			if((evt.getNombreTipo().equals(tipo)))
				evtAnterior = evt;
		}
		return null;
	}
	public Evento getEventoPosteriorFecha(Date fechaInicio) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getFecha().after(fechaInicio))
				return evt;
		}
		return null;
	}
	public Evento getEventoPosteriorFecha(Date fechaInicio,String tipo) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.getFecha().after(fechaInicio) && evt.getNombreTipo().equals(tipo))
				return evt;
		}
		return null;
	}
	/**
	 * Obtiene una lista de eventos que se produjeron en este animal entre las
	 * fechas dadas.
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getEventosEntre(Date fechaInicio, Date fechaFin,String tipoEvt) {
		List resultado = new LinkedList();
		Iterator it = this.getEvtAnimals().iterator();
		while (it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (DateUtils.entre(evt.getFecha(), fechaInicio, fechaFin)&& evt.getNombreTipo().equals(tipoEvt))
				resultado.add(evt);
		}
		return resultado;
	}
	/**
	 * Devuelve todas las hijas geneticas (solo hembras, para uso web)
	 * 
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public Collection getHijasGeneticas() {
		Collection hijas = new ArrayList();
		Iterator it = this.getHijosGeneticos().iterator();
		while (it.hasNext()) {
			Animal a = ((Animal) it.next());
			if (a.esHembra())
				hijas.add(a);
		}
		return hijas;
	}

	public Registro getRegistro(String reg) {
		Iterator it = this.getRegistros().iterator();
		while (it.hasNext()) {
			Registro registro = (Registro) it.next();
			if (registro.getTipoRegistro().getId().trim().equals(reg))
				return registro;
		}
		return null;
	}
	
	public boolean equals(Object ani){
		if(ani instanceof HibernateProxy){
			if(this.getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(ani))){
				Animal an = (Animal)ani;
				if((an.getRegOrigen()!=null)&&(this.getRegOrigen()!=null)&&(an.getRaza()!=null)&&(this.getRaza()!=null))
					if(an.esHembra()==this.esHembra())
						return an.getRegOrigen().equals(this.getRegOrigen())&&(an.getRaza().equals(this.getRaza()));
					else
						return false;
				else
					return false;
			}
		}
		if(!(ani instanceof Animal))
			return false;
		Animal an = (Animal)ani;
		if((an.getRegOrigen()!=null)&&(this.getRegOrigen()!=null)&&(an.getRaza()!=null)&&(this.getRaza()!=null))
			if(an.esHembra()==this.esHembra())
				return an.getRegOrigen().equals(this.getRegOrigen())&&(an.getRaza().equals(this.getRaza()));
			else
				return false;
		else
			return false;
		/*if((an.getRegIdentificador() != null)&& (an.getRegIdentificador().getTipoRegistro() != null)&&
	        	(an.getRegIdentificador().getTipoRegistro().getId()!= null)){
		
			if((((an.getRegIdentificador().getTipoRegistro().getId()).equals(this.getRegIdentificador().getTipoRegistro().getId()))&&
			((an.getRegIdentificador().getNumero()).equals(this.getRegIdentificador().getNumero())))&&(an.getRaza().equals(this.getRaza())))
				return true;
		}
		return false;*/
	}
	
	abstract public Calificacion getUltimaCalificacion();

	/**
	 * Devuelve el valor adecuado de un parametro de raza para este animal. Si
	 * el animal no es cruza, entonces devuelve el valor para la raza del
	 * animal. Si el animal es cruza, pero ninguna raza "domina" (mas del 75%)
	 * la composicion racial, entonces devuelve un valor producto del promedio
	 * "pesado" de las razas que lo componen, es decir, tiene en cuenta el % de
	 * composicion racial de cada raza en el calculo. Si el animal es cruza,
	 * pero con una raza "dominante", entonces devuelve el valor para dicha raza
	 * dominante.
	 * 
	 * TODO Ojo!!, para el calculo se supone que el atributo es de tipo float (o
	 * puede ser convertido a float). Obviamente no funciona si son Strings u
	 * otro tipo. Verificar que siempre los parametros de Raza sean numericos y
	 * promediables. (Req. Daniel).
	 * 
	 * 
	 * 
	 * @param at
	 *            atributo
	 * @return
	 */
	public String getParametroRaza(String at) {
		Raza raza = getRaza();
		return raza.getParametro(at);
		/*if (raza.getEsCruza()) {
			Iterator it = getComposicionRacial().getInmutableRazas().entrySet()
					.iterator();
			Raza razaDominante = null;
			while (it.hasNext() && (razaDominante == null)) {
				Map.Entry entry = (Map.Entry) it.next();
				Float value = (Float) entry.getValue();
				if (value > 0.75)
					razaDominante = (Raza) entry.getKey();
			}
			if (razaDominante != null)
				return razaDominante.getParametro(at);
			else { // es Cruza y sin Raza Dominante, hay que sacar el promedio
					// "pesado"
				it = getComposicionRacial().getInmutableRazas().entrySet()
						.iterator();
				Float valor = 0f;
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					Float porcentajeRaza = (Float) entry.getValue();
					//Raza r = (Raza) entry.getValue();
					Raza r = (Raza) entry.getKey();
					valor = valor
							+ (porcentajeRaza * Float.parseFloat(r
									.getParametro(at)));
				}
				return valor.toString();
			}
		} else {
			return raza.getParametro(at);
		}
		*/
	}

	public Integer getParametroRazaAsInteger(String at) {
		return Integer.parseInt(getParametroRaza(at));
	}

	public Float getParametroRazaAsFloat(String at) {
		return Float.parseFloat(getParametroRaza(at));
	}

	public void setearCategoria(boolean conGenealogia){
		Long numReg = Long.valueOf(this.getRegOrigen().getNumero());
		if( (numReg <=Animal.MAYOR_RC_SICEL1 && AtributoDAO.findByNombre("RECALCULAR_RECATEGORIZAR_SICEL_1").getValorPorDefecto().getValor()=="1") || numReg >Animal.MAYOR_RC_SICEL1)//Si es animal de sicel 2 o es sicel 1 y admito recategorizar
		{
			if(conGenealogia)
			{
					this.setCategoria(this.getCategoriaCalculada());
			}
			else
				this.setCategoria(this.getCategoriaCalculadaSinGenealogia());
		}
	}

	/**
	 * Calcula ahora que categoria tendria el animal, puede no coincidir con la
	 * categoria que se le calculo al momento del nacimiento o del alta.
	 */
	public abstract String getCategoriaCalculada();

	public void setEstablecimientoCriador(Establecimiento establecimientoCriador) {
		this.establecimientoCriador = establecimientoCriador;
	}
	public abstract String getCategoriaCalculadaSinGenealogia();
	/**
	 * 
	 * @hibernate.set lazy="true" cascade="all" inverse="true"
	 * @hibernate.collection-key column="animal"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.FichaAnimalPendiente"
	 * 
	 */
	
	
	public java.util.Set getFichasAnimal() {
		return this.fichasAnimal;
	}

	protected void setFichasAnimal(Set<FichaAnimalPendiente> fichasAnimal) {
		this.fichasAnimal = fichasAnimal;
	}

	
	
	
	/*public FichaAnimalPendiente getUltimaFichaAnimal(){
		Long max = 0l;
		for (FichaAnimalPendiente ficha:fichasAnimal){
			if (ficha.getLoteId()>max)
				max= ficha.getId();
		}		
		return ultimaFicha(max);
	}
	private FichaAnimalPendiente ultimaFicha (Long numero){
		for (FichaAnimalPendiente ficha:fichasAnimal){
			if (ficha.getId() == numero)
				return ficha;
		}
		return null;
	}*/
	
	public void agregarFichaAnimal(FichaAnimalPendiente ficha){
		this.fichasAnimal.add(ficha);
	}
	
	public Set<AnimalComentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(Set<AnimalComentario> comentarios) {
		this.comentarios=comentarios;
	}
	public void addComentarios(Set<AnimalComentario> comentarios) {
		Iterator it = comentarios.iterator();
		while (it.hasNext()){
			AnimalComentario co = (AnimalComentario)it.next();
			this.addComentario(co);
		}
		
	}

	private void addComentario(AnimalComentario co) {
		if(this.getComentarios()==null)
			this.setComentarios(new HashSet());
		comentarios.add(co);
		co.setAnimal(this);
		
	}
	public abstract EvtServicio getUltimoServicio();
	
	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Integer getAsoc() {
		return asoc;
	}

	public void setAsoc(Integer asoc) {
		this.asoc = asoc;
	}

	public Integer getAsom() {
		return asom;
	}

	public void setAsom(Integer asom) {
		this.asom = asom;
	}

	public Integer getAsop() {
		return asop;
	}

	public void setAsop(Integer asop) {
		this.asop = asop;
	}

	public Integer getDadorSemen() {
		return dadorSemen;
	}

	public void setDadorSemen(Integer dadorSemen) {
		this.dadorSemen = dadorSemen;
	}

	public String getDonante() {
		return donante;
	}

	public void setDonante(String donante) {
		this.donante = donante;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public Date getFechaTransf() {
		return fechaTransf;
	}

	public void setFechaTransf(Date fechaTransf) {
		this.fechaTransf = fechaTransf;
	}

	public Date getFechaUltObs() {
		return fechaUltObs;
	}

	public void setFechaUltObs(Date fechaUltObs) {
		this.fechaUltObs = fechaUltObs;
	}

	public Integer getMellizo() {
		return mellizo;
	}

	public void setMellizo(Integer mellizo) {
		this.mellizo = mellizo;
	}

	public Integer getNumeroAnalADN() {
		return numeroAnalADN;
	}

	public void setNumeroAnalADN(Integer numeroAnalADN) {
		this.numeroAnalADN = numeroAnalADN;
	}

	public Integer getNumeroTransf() {
		return numeroTransf;
	}

	public void setNumeroTransf(Integer numeroTransf) {
		this.numeroTransf = numeroTransf;
	}

	public Integer getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(Integer tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(String transferencia) {
		this.transferencia = transferencia;
	}

	public ar.org.sicel.persistence.Propietario getPropietarioCriador() {
		return propietarioCriador;
	}

	public void setPropietarioCriador(
			ar.org.sicel.persistence.Propietario propietarioCriador) {
		this.propietarioCriador = propietarioCriador;
	}

	

	public Integer getCodigoVerificador() {
		return codigoVerificador;
	}

	public void setCodigoVerificador(Integer codigoVerificador) {
		this.codigoVerificador = codigoVerificador;
	}

	public String getRpSenasa() {
		return rpSenasa;
	}

	public void setRpSenasa(String rpSenasa) {
		this.rpSenasa = rpSenasa;
	}

	public java.lang.Integer getNroLactancia() {
		return nroLactancia;
	}

	public void setNroLactancia(java.lang.Integer nroLactancia) {
		this.nroLactancia = nroLactancia;
	}

	public Integer getNroLactInformado() {
		return nroLactInformado;
	}

	public void setNroLactInformado(Integer nroLactInformado) {
		this.nroLactInformado = nroLactInformado;
	}
	public abstract ProcMsg chequearNumLactanciaYEdad(int numeroLac,Date fechaEvento);

	public String getRpti() {
		return rpti;
	}

	public void setRpti(String rpti) {
		this.rpti = rpti;
	}

	public void setEsBaja(java.lang.Boolean esBaja) {
		this.esBaja = esBaja;
	}
	
	public void setEsSinonimo(java.lang.Boolean esSinonimo) {
//		if(!esSinonimo)
//			this.esSinonimo = 0;
//		else
//			this.esSinonimo = 1;
		this.esSinonimo=esSinonimo;
			
	}

	public boolean isSinonimo() {
//		if(esSinonimo > 0)
//			return true;
//		else
//			return false;
		return this.esSinonimo;
	}
	
	public Boolean getEsSinonimo(){
		return esSinonimo;
	}
	
	public Set<ProcAnimal> getProcsAnimal() {
		return procsAnimal;
	}

	public void setProcsAnimal(Set<ProcAnimal> procsAnimal) {
		this.procsAnimal = procsAnimal;
	}

	public Estancia getEstancia() {
		return estancia;
	}

	public void setEstancia(Estancia estancia) {
		this.estancia = estancia;
	}
	public void borrarEventoAnimal(EvtAnimal evt) {
		if(this.getEvtAnimals().contains(evt))
			this.getEvtAnimals().remove(evt);
		else{
			ArrayList arrayList2 =null;
			if(!this.getEvtAnimals().isEmpty()){
					arrayList2 = new ArrayList();
					//evt.getAnimal().setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
				Iterator it = this.getEvtAnimals().iterator();
				EvtAnimal evAux = null;
				while(it.hasNext()){
					EvtAnimal ev = (EvtAnimal)it.next();
					if(!ev.equals(evt))
						arrayList2.add(ev);
				}
				this.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
				this.getEvtAnimals().addAll(arrayList2);
			}
		}	
	}
	public void setEstadoRetroactivoParticular(Date fechaEvt,List msgs, String tipo) throws ExcepcionIntegridad {
		  List<Object> listaEventos = new ArrayList<Object>();
	        listaEventos.addAll(this.getEvtAnimals());
	        Collections.sort(listaEventos, new EventosPorFechaYTipo());
		  int desde = listaEventos.size();
		  if(desde !=0){
		  ListIterator iter = listaEventos.listIterator(desde);
		  if(iter.hasPrevious()){
	      EvtAnimal evtAnimal = (EvtAnimal) iter.previous();
	      //if(evtAnimal.getFecha().after(evt.getFecha())){
	      if(evtAnimal.getFecha().after(fechaEvt)){
	    	  if(!listaEventos.isEmpty()){
					//Evento ant= (Evento) allEventos.first();
					Iterator it = listaEventos.iterator();
					boolean encontroLugar = false;
					try {
					while(it.hasNext()){
						Evento ev = (Evento)it.next();
						if(!encontroLugar){
							//if((evt.getFecha().equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC)))
							if((fechaEvt.equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC)))
							//if((evt.getFecha().equals(ev.getFecha()))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))&&(!ev.getNombreTipo().equals(Evento.EVT_TIPO_CONTROL_ANIMAL)))
								encontroLugar=true;
							
						}
						else{
							if(!ev.getNombreTipo().equals(Evento.EVT_TIPO_LAC))
								actualizarEventosPosterioresARetroactivoParticular(this,(EvtAnimal) ev, msgs);
							else
								borrarLactanciasColgadas((EvtLactancia) ev);

						}
						
					}
					} catch (ExcepcionIntegridad e) {
						
							throw new ExcepcionIntegridad(
									MENSAJES.NO_ES_POSIBLE_SETEO_RETROACTIVO, new String[] {
											tipo});
						
					}
			}
	}
		  }
	}
		
	}
	public  void actualizarEventosPosterioresARetroactivoParticular(Animal animal, EvtAnimal ev, List msgs) throws ExcepcionIntegridad {

		boolean finalizaLacAntesCalcularEstRetroac =ev.isFinalizaLactancia(); 
		MatrizTransiciones.calcularTransicionEv(this,ev, msgs);
		if (ev.isFinalizaLactancia()||finalizaLacAntesCalcularEstRetroac) {
			// si el ev finaliza una lactancia, hay q calcularla y persistirla
			// si bien puede ser que no haya ev prod entre el evIni y el evFin
			
					EvtLactancia lacti =null;
					Session session = HibernateFactory.getSession();
					SortedSet even = ev.getDependientes();
					//ArrayList arrayList2 = null;
					List<Object> arrayList2 = new ArrayList<Object>();
					arrayList2.addAll(this.getEvtAnimals());
			        Collections.sort(arrayList2, new EventosPorFechaYTipo());
					if(!even.isEmpty()){
						arrayList2.addAll(this.getEvtAnimals());
						for(Object lact : even){
							if(lact.getClass()==EvtLactancia.class){
								lacti = (EvtLactancia)lact;
								if(arrayList2.contains(lacti))//esto lo hago pq en dependientes puede quedar una lactancia q falta q hibernate la elimine y el arrayList2 ya no esta
									break;
								}
							}
					}
					//if(lacti!=null && arrayList2.contains(lacti)){
					if(lacti!=null){
						arrayList2.remove(ev);
						arrayList2.remove(lacti);
						this.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
						this.getEvtAnimals().addAll(arrayList2);
						this.getEvtAnimals().add(ev);
						
						if (lacti.isEsOficial()&& AnimalesLactanciasOficiales.getAnimales()!=null)
			        		AnimalesLactanciasOficiales.getAnimales().remove(this);
						if(lacti.getId()!=null){
							lacti.ejecutarBaja();
							this.getEvtAnimals().add(ev);
						}
						//else
						ev.getDependientes().remove(lacti);
						AnimalDAO.updateAnimal(this);
					}
			//	}
				if(ev.isFinalizaLactancia() ){
					EvtLactancia evtLactancia = EvtLactancia.calcularLactanciaCerrada(this, ev);
					if(evtLactancia!=null){
						
						ArrayList arrayList = new ArrayList(this.getEvtAnimals());
						this.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
						arrayList.add(evtLactancia);
						this.getEvtAnimals().addAll(arrayList);
						//this.getEvtAnimals().add(evtLactancia);
						ev.addDependiente(evtLactancia);
						try {
							session.save(evtLactancia);
							session.update(ev);
							session.update(this);
							//session.flush();
							
						} catch (HibernateException e) {
							throw new ErrorFatal(
								"Error capa persistencia al agregar lactancia cerrada : "
										+ e);
						}
					}
				}
			}
		/*}/*else{
			if(ev.getClass()==EvtReproduccion.class)
				ev.getDependientes().clear();
		}*/
		
	}
	
	/**
	 * 
	 * @param rp
	 * @return
	 * Aqui se valida que el rp del animal tenga el formato [A-B]{0-9}* y la letra sea diferente de I y de O
	 * Esto es: solo puede tener una letra. Si la tiene, va al principio y tiene que ser diferente de I y de O.
	 * Si no tiene letra al comienzo, deberá ser todo númerico.
	 * Por medio del XSD, ya se valida que sea [A-B]{0-9}*. Aquí se valida nuevamente, pero se agrega lo de diferente de I y de O.
	 */
	public static boolean validarRP(String rp){
		try{
			/*lo paso a numero, si no falla es que esta bien*/
			Integer valorNumerico = new Integer(rp);
			if (rp.charAt(0) == '0')
				return false;
			return true;
		} catch (Exception e){
			try{
				/*me fijo que tenga formato letra-numero y letra != O y != de I*/
				Integer valorNumerico = new Integer(rp.substring(1));
				if (rp.charAt(0) == 'i' || rp.charAt(0) == 'o' || rp.charAt(0) == 'I' || rp.charAt(0) == 'O')
					return false;
				return true;
			} catch (Exception e2){
				return false;
			}
		}
	}

}
