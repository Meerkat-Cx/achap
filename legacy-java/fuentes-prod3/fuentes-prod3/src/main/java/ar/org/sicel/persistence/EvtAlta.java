package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.proc.services.impl.RazaUtil;
import ar.org.sicel.proc.services.impl.TipoRegistroUtil;
import ar.org.sicel.proc.v1.lote.Alta;
import ar.org.sicel.proc.v1.lote.CompoRacial;
import ar.org.sicel.proc.v1.lote.ComposicionItem;
import ar.org.sicel.proc.v1.lote.Padres;
import ar.org.sicel.proc.v1.lote.types.STSexo;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;
import ar.org.sicel.persistence.ProcMsgDAO;

/**
 * @author adrian
 *
 * @hibernate.joined-subclass
 *    table="Ev_Alta"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvAlta_EvEstab"
 *
 */
public class EvtAlta extends ar.org.sicel.persistence.EvtEstablecimiento {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.Animal animal;
    
	private static Logger log = Logger.getLogger(EvtAlta.class);

    protected EvtAlta() {
    }
    
    protected EvtAlta(Establecimiento est, Date fecha) {
    	super(est,fecha);
    	
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.many-to-one
     *     column="animal"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_EvAlta_Anim"
     *
     */
    public ar.org.sicel.persistence.Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(ar.org.sicel.persistence.Animal animal) {
        this.animal = animal;
    }
    
    // ---------------- business methods  ----------------------

	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
		return getAnimal().getNombre();
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_ALT;
	}

	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#eliminarColaterales()
	 */
	protected void eliminarColateralesPost() throws ExcepcionIntegridad {
		// buscar el animal y borrarlo, cracial y regs estan en cascada
		// TODO colaterales los ev del animal no los borro desde aca por ahora
        Session session = HibernateFactory.getSession();
        Animal animal=this.getAnimal();
        try {
            session.delete(animal);
        } catch (HibernateException he) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                    new String[] { String.valueOf(getId()),he.toString() });
            throw e;
        }
	}
    
    @SuppressWarnings("unchecked")
	public Set getAnimalesModificados() {
        Set animalesModificados = super.getAnimalesModificados();
        animalesModificados.add(this.animal);
        return animalesModificados;
    }

    @SuppressWarnings("unchecked")
	public void addAnimalesModificados(Set animalesModificados) {
        super.addAnimalesModificados(animalesModificados);
        animalesModificados.add(this.animal);
    }

	/**
	 * Se recolectan todos los del animal que se dio de alta.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet recolectarTodosEventosDependientes() {
		SortedSet resultado = new TreeSet(new EventosPorFechaYTipo());
		resultado.add(this);
		Iterator eventos = this.getAnimal().getEvtAnimals().iterator();
		while (eventos.hasNext()) {
			EvtAnimal evtAnimal = (EvtAnimal) eventos.next();
			evtAnimal.recolectarEventosDependientesEn(resultado);
			}
		return resultado;
	}
    
    public boolean validarBaja(List mensajes) throws ExcepcionIntegridad {
    	//List eventosPosteriores = darProximoEvento();
    	
    	SortedSet eventosPosteriores = this.getAnimal().getEvtAnimals();    	
    	if(eventosPosteriores.isEmpty()){//eventosPosteriores==null)				
    		//if(this.getAnimal() instanceof HibernateProxy)
    		Animal ani = null;	
    		if (this.getAnimal() instanceof HibernateProxy){
   	         HibernateProxy proxy = (HibernateProxy) this.getAnimal();
   	         ani= (Animal) proxy.getHibernateLazyInitializer().getImplementation();
   	      }
    		else ani = this.getAnimal();
    		
    		EvtCria evc =ani.getEvCria(); //EvtCriaDAO.findByAnimal(ani);
    		
    		if(evc!=null)
        		mensajes.add(ProcMsgDAO.create(MENSAJES.EVENTO_CRIA_ASOCIADO,ProcMsg.WARNING_SPECIAL,new String[]{}));
    		return true;
    	}
    	else{
    		Iterator it_eventosPost = eventosPosteriores.iterator();
    		Evento evento = null;
    		String listEventos = "";
    		while(it_eventosPost.hasNext()){
    			evento = (Evento)it_eventosPost.next();
    			listEventos = listEventos.concat("Id evento: "+evento.getId()+" Tipo del evento: "+evento.getNombreTipo()+";\n");
    		}
    		ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_BAJA_ALTA_WITHDEP,
                    new String[] {this.toString(), listEventos });
            throw e;
    	}    		
    }
    private void actualizar(Raza razaDeclaradaNueva,Hembra madreGen,Macho padreGen,String nombre,String rpSe,Integer dig) throws ExcepcionIntegridad{
    	Especie especie = null;
		if (razaDeclaradaNueva != null)  
			especie = razaDeclaradaNueva.getEspecie();
		else
			if (madreGen != null)	
				especie = madreGen.getEspecie();
			else
				if (padreGen != null)
					especie = padreGen.getEspecie();
				else
					throw new ExcepcionIntegridad(MENSAJES.IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL,new String [0]);
		if(nombre!=null)
			this.getAnimal().setNombre(nombre);
		this.getAnimal().setRpSenasa(rpSe);
		this.getAnimal().setCodigoVerificador(dig);
		this.getAnimal().inicializarPadresGen(especie,madreGen,padreGen);
		this.getAnimal().getComposicionRacial().setRazaDeclarada(razaDeclaradaNueva);
		if((madreGen!=null && padreGen!=null)&&(!madreGen.getRaza().getId().equals(padreGen.getRaza().getId()))&&(razaDeclaradaNueva ==null)){
			this.getAnimal().getComposicionRacial().setRazaDeclarada(this.getAnimal().getComposicionRacial().getRazaCalculada());
		}
		if(padreGen!=null){
			if(!madreGen.getRaza().getId().equals(padreGen.getRaza().getId())){
			
				if(this.getAnimal().getComposicionRacial().getRazaCalculada().getEsDesconocido()&&(!this.getAnimal().getComposicionRacial().getRazaDeclarada().getEsCruza()))
					throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,new String[] {});
				
				if ((!this.getAnimal().getComposicionRacial().getRazaCalculada().equals(
						this.getAnimal().getComposicionRacial().getRazaDeclarada())) 
						&&(!this.getAnimal().getComposicionRacial().getRazaCalculada().getEsDesconocido())){
					throw new ExcepcionIntegridad(
							MENSAJES.RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA,
							new String[] {});
				}
			}
			}	
    }
    public void ejecutarBaja(List mensajes) throws ExcepcionIntegridad {
    	if(validarBaja(mensajes)){
    		Session session = HibernateFactory.getSession();
    		try {
    			if(!mensajes.isEmpty()){
    				EvtReproduccionDAO.eliminarCria(this.getAnimal());
    			}
    			//session.delete(this);
    			EvtEstablecimientoDAO.deleteEvtEstab(this);
    		} catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
    	}
    }

	/**
	 * Realiza el chequeo de las condiciones que se deben cumplir para aceptar la modificación del evento alta
	 * y ejecuta dicha modificación si es posible
	 * Si se cambia la fecha de Alta se chequea q no exista ningun evento informado anterior a esa fecha.
	 * Si se cambia de sexo se chequea q no haya eventos informados sobre el animal
	 * Para el caso de que la modificación del evento alta realice un cambio en la fecha de nacimiento del animal que informa,
	 * se debe chequear que dicho animal no haya recibido eventos servicios dentro del año y eventos reproducción dentro de los
	 * 16 meses desde la nueva fecha de nacimiento que se informa. Ademas re realiza el chequeo de unidad por si cambio de año
	 * En caso de que sea un empadronar, si se cambia la raza se realiza la propagacion en las crias que puedan 
	 * afectar a la composicion genealogica.
	 * En caso de que sea composicion racil, si se cambia la composicion se realiza la propagacion en las crias que puedan 
	 * afectar a la composicion genealogica.
	 * @param alta - Alta
	 * @param mensajes - List
	 * @param establecimiento - Establecimiento
	 * @param procLote - ProcLote
	 * @return EvtEstModificacion
	 * @throws ExcepcionIntegridad
	 */
    public EvtEstModificacion ejecutarModificacion(Alta alta, List mensajes, Establecimiento establecimiento,ProcLote procLote) throws ExcepcionIntegridad {		
		Date fechaNueva = alta.getFecha();	
		System.out.println("Fecha Nueva "+fechaNueva);
		
		Set allEventos = this.getAnimal().getAllEventos();		
		if (!fechaNueva.equals(this.getFecha())) {// si se modifica la fecha nueva					
			Iterator i = allEventos.iterator();
			String listEventos = "";
			boolean eventoAnterior = false;
			while (i.hasNext()) {
				Evento e = (Evento) i.next();
				if ((e.getId()!=null) && (!e.getId().equals(this.getId())) && e.getFecha().before(fechaNueva)) {		
					listEventos = listEventos.concat("Id evento: "+e.getId()+" Tipo del evento: "+e.getNombreTipo()+"\n");
					eventoAnterior = true;
					// VERIFICO QUE LA FECHA NUEVA DEL EVENTO ESTE ANTES DE TODOS LOS EVENTOS DEL ANIMAL
				}	
			}
			if (eventoAnterior) {
				log.error("Hay por lo menos un evento anterior a la fecha nueva, llego por esta rama");
				throw new ExcepcionIntegridad(MENSAJES.EVENTOS_ANTERIORES, new String[] {"Modificacion de evento alta", listEventos});
			}
		}
		this.setFecha(fechaNueva);// modifico la fecha del evento alta
		
		// VERIFICO QUE LA RAZA NUEVA INFORMADA EXISTA EN LA BASE DE DATOS
		String razaNueva = RazaUtil.getRazaEnBase(alta.getRaza());// es obligatoria
		if(razaNueva == null) {
			log.error("La raza no existe");
			throw new ExcepcionIntegridad(MENSAJES.NO_SETEA_RAZA, new String[]{});
		}
			
		Raza razaDeclaradaNueva = RazaDAO.findByPrimaryKey(razaNueva);		
		/*if(this.getAnimal().getMadreGenetica()!=null && ((alta.getTEvtAltaChoice().getEmpadronar() != null)||(alta.getTEvtAltaChoice().getCompoRacial() != null)))
			//si era alta con padres y ahora informa empadronar o composicion ERROR
			throw new ExcepcionIntegridad(MENSAJES.ELIMINA_ASCENDENCIA,new String []{this.getAnimal().getRegistroOrigen()});
		if(alta.getTEvtAltaChoice().getPadres()!=null){
			if((this.getAnimal().getMadreGenetica()!=null) && (alta.getTEvtAltaChoice().getPadres().getMadreGen()==null ))
				throw new ExcepcionIntegridad(MENSAJES.ELIMINA_ASCENDENCIA,new String []{this.getAnimal().getRegistroOrigen()});
			if((this.getAnimal().getPadre()!=null) && (alta.getTEvtAltaChoice().getPadres().getPadreGen()==null ))
				throw new ExcepcionIntegridad(MENSAJES.ELIMINA_ASCENDENCIA,new String []{this.getAnimal().getRegistroOrigen()});
		}*/
		boolean esHembra = alta.getSexo().equals(STSexo.F);
		
		// VERIFICO QUE SI SE MODIFICA EL SEXO DEL ANIMAL NO HAYA EVENTOS SOBRE ESE ANIMAL, SALVO EL DE ALTA
		
		if (this.getAnimal().esHembra() != esHembra && allEventos.size() > 1){
			// se modifico el sexo, y hay por lo menos 2 eventos (el de alta y uno mas)
			String listEventos = "";			
			Iterator i = allEventos.iterator();
			while (i.hasNext()) {
				Evento e = (Evento) i.next();
				listEventos = listEventos.concat("Id evento: "+e.getId()+" Tipo del evento: "+e.getNombreTipo()+"\n");					
				// VERIFICO QUE LA FECHA NUEVA DEL EVENTO ESTE ANTES DE TODOS LOS EVENTOS DEL ANIMAL
					
			}
			
			log.error("Se cambia el sexo y hay varios eventos posteriores, paso por aca");
			throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_SEXO_EVENTOS, new String[] {listEventos});			
		}
		
		// VERIFICO QUE SI SE MODIFICA EL SEXO DEL ANIMAL NO HAYA EVENTOS SOBRE ESE ANIMAL, SALVO EL DE ALTA
		String rpSe = null;
		Integer dig = null;
		if(alta.getSenasa()!=null){
			rpSe = alta.getSenasa().getRpSenasa();
			dig = new Integer(alta.getSenasa().getDigitoVerficador());
		}
		boolean crear = false;	
		Animal nuevoAnimal=null;
		EvtReproduccion evtRep = null;
		Date fechaNacimientoNuevo = null;
		boolean fechaCorrecta = true;
		EvtCria evtCriaAux = null;
		Map composicionRacialNueva = new HashMap(); // el animal es 100% de la raza
		if (alta.getRp()!=null && !alta.getRp().equals(this.getAnimal().getRP())) //si cambia el rp
			if (Configuracion.getValorReglaProceso(CONF.VALIDACIONES_NUMEROS_DE_RP,
					this.getFecha()) && !Animal.validarRP(alta.getRp()))
				throw new ExcepcionIntegridad(MENSAJES.VALIDACIONES_NUMEROS_DE_RP,new String[]{alta.getRp()});
		if (alta.getTEvtAltaChoice().getEmpadronar() != null) {
			fechaNacimientoNuevo = alta.getTEvtAltaChoice().getEmpadronar().getFechaNacimiento();
			if(fechaNacimientoNuevo!=null)
				this.checkServicios(fechaNacimientoNuevo,esHembra);
			else
				fechaNacimientoNuevo=this.getAnimal().getFechaNac();
			
			if(this.getFecha().before(fechaNacimientoNuevo))
				throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
			
			Animal.checkUnicidadRPEnEstabForUpdate(this.getAnimal().getId(),this.getEstablecimiento(), alta.getRp(), mensajes, (fechaNacimientoNuevo!=null)?fechaNacimientoNuevo:this.getAnimal().getFechaNac(),this.getAnimal().getEstablecimiento(),this.getAnimal().getRegistroOrigen(),this.getAnimal().getCategoria());
			if (this.getAnimal().esHembra() != esHembra){//si cambio el sexo tengo que crearlo
				Animal animalOld = AnimalDAO.findByPrimaryKey(this.getAnimal().getId());
				Map comp = new HashMap(); 
				comp.put(razaDeclaradaNueva.getEspecie().getDesconocida(),new Float(1));
				ComposicionRacial compR = ComposicionRacial.nuevaInstancia(razaDeclaradaNueva);
				Establecimiento est = EstablecimientoDAO.findByPrimaryKey(animalOld.getEstablecimiento().getId());//this.getAnimal().getEstablecimiento();
				Propietario p = PropietarioDAO.findByPrimaryKey(animalOld.getPropietario().getId());//this.getAnimal().getPropietario();
				String nombre = animalOld.getNombre();//this.getAnimal().getNombre();
				nuevoAnimal = AnimalDAO.createCambioSexo(est, p, esHembra, alta.getRp(), compR, comp, mensajes, fechaNacimientoNuevo);
				String nuevoNombre = alta.getNombre();
				if (nuevoNombre != null)
						nuevoAnimal.setNombre(nuevoNombre);
				else
					nuevoAnimal.setNombre(nombre);
				nuevoAnimal.setRpSenasa(rpSe);
				nuevoAnimal.setCodigoVerificador(dig);
				nuevoAnimal.setFechaNac(fechaNacimientoNuevo);
				copiarRegistros(animalOld,nuevoAnimal);//this.getAnimal(),nuevoAnimal);
				animalOld.setRegistros(null);
				
				HibernateFactory.getSession().delete(animalOld);
				HibernateFactory.getSession().save(nuevoAnimal);
				this.setAnimal(nuevoAnimal);
				crear = true;
			}else
				this.empadronar(composicionRacialNueva,razaDeclaradaNueva,fechaNacimientoNuevo,alta);
			
		} 
		else {
			if (alta.getTEvtAltaChoice().getCompoRacial() != null) {				
				CompoRacial compoRacial = alta.getTEvtAltaChoice().getCompoRacial();
				if (compoRacial.getFechaNacimiento() != null){
					fechaNacimientoNuevo = compoRacial.getFechaNacimiento();
					this.checkServicios(fechaNacimientoNuevo,esHembra);
				}
				else
					fechaNacimientoNuevo=this.getAnimal().getFechaNac();
				
				if(this.getFecha().before(fechaNacimientoNuevo))
					throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
				
				Animal.checkUnicidadRPEnEstabForUpdate(this.getAnimal().getId(),this.getEstablecimiento(), alta.getRp(), mensajes, (fechaNacimientoNuevo!=null)?fechaNacimientoNuevo:this.getAnimal().getFechaNac(),this.getAnimal().getEstablecimiento(),this.getAnimal().getRegistroOrigen(),this.getAnimal().getCategoria());
				Enumeration e = alta.getTEvtAltaChoice().getCompoRacial().getComposicion().enumerateComposicionItem();
				while (e.hasMoreElements()) {
					//MIRAR No deberia verificarse que la suma de composiciones de 100% ??
					ComposicionItem item = (ComposicionItem) e.nextElement();
					String raza = RazaUtil.getRazaEnBase(item.getElemRacial()
							.getRaza());
					Float valor = item.getElemRacial().getValor();
					Raza r = RazaDAO.findByPrimaryKey(raza);
					if (r == null)
						throw new ExcepcionIntegridad(MENSAJES.RAZA_NO_EXISTE,
								new String[] { raza });
					composicionRacialNueva.put(r, valor);
				}
				if (this.getAnimal().esHembra() != esHembra){//si cambio el sexo tengo que crearlo
					Animal animalOld = AnimalDAO.findByPrimaryKey(this.getAnimal().getId());
					ComposicionRacial compR = ComposicionRacial.nuevaInstancia(razaDeclaradaNueva);
					Establecimiento est = EstablecimientoDAO.findByPrimaryKey(animalOld.getEstablecimiento().getId());//this.getAnimal().getEstablecimiento();
					Propietario p = PropietarioDAO.findByPrimaryKey(animalOld.getPropietario().getId());//this.getAnimal().getPropietario();
					String nombre = animalOld.getNombre();//this.getAnimal().getNombre();
					nuevoAnimal = AnimalDAO.createCambioSexo(est, p, esHembra, alta.getRp(), compR, composicionRacialNueva, mensajes, fechaNacimientoNuevo);
					String nuevoNombre = alta.getNombre();
					if (nuevoNombre != null)
							nuevoAnimal.setNombre(nuevoNombre);
					else
						nuevoAnimal.setNombre(nombre);
					nuevoAnimal.setFechaNac(fechaNacimientoNuevo);
					copiarRegistros(animalOld,nuevoAnimal);//this.getAnimal(),nuevoAnimal);
					animalOld.setRegistros(null);
					nuevoAnimal.setRpSenasa(rpSe);
					nuevoAnimal.setCodigoVerificador(dig);
					HibernateFactory.getSession().delete(animalOld);
					HibernateFactory.getSession().save(nuevoAnimal);
					this.setAnimal(nuevoAnimal);
					crear = true;
				}
				else{
					if (alta.getNombre() != null)
						this.getAnimal().setNombre(alta.getNombre());
					
					this.getAnimal().setPadre(null);
					this.getAnimal().setMadreGenetica(null);
					ComposicionRacial compR = ComposicionRacial.nuevaInstancia(razaDeclaradaNueva);
					this.getAnimal().setComposicionRacial(compR);
					compR.setAnimal(this.getAnimal());
					compR.setearComposicionRacial(composicionRacialNueva);
					if(!this.getAnimal().getComposicionRacial().getRazaCalculada().getEsDesconocido())
						this.getAnimal().getComposicionRacial().setRazaDeclarada(this.getAnimal().getComposicionRacial().getRazaCalculada());
					else
						this.getAnimal().getComposicionRacial().setRazaDeclarada(razaDeclaradaNueva.getEspecie().getCruza());
					this.getAnimal().setFechaNac(fechaNacimientoNuevo);
					this.getAnimal().setRpSenasa(rpSe);
					this.getAnimal().setCodigoVerificador(dig);
				}
			}
			else { // VIENE EL TAG CON LOS PADRES
				// aca no se tiene en cuenta si se modifican los datos de los padres
				Macho padreGen = null;
				Hembra madreGen = null;
				Padres padres = alta.getTEvtAltaChoice().getPadres();
				fechaNacimientoNuevo = alta.getTEvtAltaChoice().getPadres().getFechaNacimiento();
				if(fechaNacimientoNuevo!=null)
					this.checkServicios(fechaNacimientoNuevo,esHembra);
				
				if(this.getFecha().before((fechaNacimientoNuevo!=null)?fechaNacimientoNuevo:this.getAnimal().getFechaNac()))
					throw new ExcepcionIntegridad(MENSAJES.FECHA_NACIMIENTO_SUPERIOR_AL_ALTA, new String[] {});
				
				Animal.checkUnicidadRPEnEstabForUpdate(this.getAnimal().getId(),this.getEstablecimiento(), alta.getRp(), mensajes, (fechaNacimientoNuevo!=null)?fechaNacimientoNuevo:this.getAnimal().getFechaNac(),this.getAnimal().getEstablecimiento(),this.getAnimal().getRegistroOrigen(),this.getAnimal().getCategoria());
				if(padres.getMadreGen()==null && padres.getPadreGen()==null){//lo tomo como un empadronar
					if (this.getAnimal().esHembra() != esHembra){//si cambio el sexo tengo que crearlo
						Animal animalOld = AnimalDAO.findByPrimaryKey(this.getAnimal().getId());
						Map comp = new HashMap(); 
						comp.put(razaDeclaradaNueva.getEspecie().getDesconocida(),new Float(1));
						ComposicionRacial compR = ComposicionRacial.nuevaInstancia(razaDeclaradaNueva);
						Establecimiento est = EstablecimientoDAO.findByPrimaryKey(animalOld.getEstablecimiento().getId());//this.getAnimal().getEstablecimiento();
						Propietario p = PropietarioDAO.findByPrimaryKey(animalOld.getPropietario().getId());//this.getAnimal().getPropietario();
						String nombre = animalOld.getNombre();//this.getAnimal().getNombre();
						nuevoAnimal = AnimalDAO.createCambioSexo(est, p, esHembra, alta.getRp(), compR, comp, mensajes, fechaNacimientoNuevo);
						String nuevoNombre = alta.getNombre();
						if (nuevoNombre != null)
								nuevoAnimal.setNombre(nuevoNombre);
						else
							nuevoAnimal.setNombre(nombre);
						nuevoAnimal.setFechaNac(fechaNacimientoNuevo);
						copiarRegistros(animalOld,nuevoAnimal);//this.getAnimal(),nuevoAnimal);
						animalOld.setRegistros(null);
						nuevoAnimal.setRpSenasa(rpSe);
						nuevoAnimal.setCodigoVerificador(dig);
						HibernateFactory.getSession().delete(animalOld);
						HibernateFactory.getSession().save(nuevoAnimal);
						this.setAnimal(nuevoAnimal);
						crear = true;
					}else
						this.empadronar(composicionRacialNueva,razaDeclaradaNueva,fechaNacimientoNuevo,alta);
				}
				else{
					if (padres.getMadreGen() != null) {
							String tRegMadre = TipoRegistroUtil.getTipoRegistroEnBase(padres.getMadreGen().getTReg());
							String nRegMadre = padres.getMadreGen().getNReg();
							String raza = RazaUtil.getRazaEnBase(padres.getMadreGen().getRaza());
							madreGen = AnimalDAO.findExistentHembraByRegistry(tRegMadre, nRegMadre,raza);		
							
							if(padres.getPadreGen()!=null){//si informo padre y madre
								String tRegPadre = TipoRegistroUtil.getTipoRegistroEnBase(padres.getPadreGen().getTReg());
								String nRegPadre = padres.getPadreGen().getNReg();
								String razaP = RazaUtil.getRazaEnBase(padres.getPadreGen().getRaza());
								padreGen = AnimalDAO.findExistentMachoByRegistry(tRegPadre,nRegPadre,razaP);
								}
							evtRep = (EvtReproduccion)madreGen.getEventoEnFecha(fechaNacimientoNuevo,Evento.EVT_TIPO_REP);
							if(evtRep == null){//si no tiene EVT reproduccion en esa fecha error
								throw new ExcepcionIntegridad(MENSAJES.MADRE_SIN_REPROD,new String[] {fechaNacimientoNuevo.toString(), madreGen.getRegistroOrigen()});
								}
							else{
								
								if(evtRep.getEvtServicio()!=null){
									if((padreGen==null)||(!padreGen.equals(evtRep.getEvtServicio().getPadreGenetico())))
										throw new ExcepcionIntegridad(MENSAJES.NO_COINCIDE_PADRE_CON_REP,new String[] {alta.getRp(),padreGen==null?"NINGUNO":padreGen.getRegistroOrigen(),evtRep.getEvtServicio()==null?"NINGUNO":evtRep.getEvtServicio().getPadreGenetico().getRegistroOrigen()});
								}
								else{
									if(padreGen!=null)
										throw new ExcepcionIntegridad(MENSAJES.NO_COINCIDE_PADRE_CON_REP,new String[] {alta.getRp(), padreGen==null?"NINGUNO":padreGen.getRegistroOrigen(),evtRep.getEvtServicio()==null?"NINGUNO":evtRep.getEvtServicio().getPadreGenetico().getRegistroOrigen()});
								}
								boolean sexoCorrespondiente = false;
								for(Object obj : evtRep.getEvtCrias()){
									EvtCria evtCria = (EvtCria)obj;
									if((evtCria.getSexo().equals("F")&&esHembra)||(evtCria.getSexo().equals("M")&&!esHembra)){
									//if((evtCria.getEsHembra()&&esHembra)||(!evtCria.getEsHembra()&&!esHembra)){
										sexoCorrespondiente = true;
										evtCriaAux = evtCria;
										break;
									}
								}
								if(!sexoCorrespondiente)
									throw new ExcepcionIntegridad(MENSAJES.SEXO_NO_CORRESPONDIENTE,
										new String[] {esHembra?"HEMBRA":"MACHO"});
								Date fechaLoteRep = evtRep.getProcEvtAnimal().getLote().getTEnvio();
								int diasLoteEvt = Integer.parseInt(madreGen.getRaza().getParametro("Dias maximo entre fechas de evento y de lote"));
								fechaCorrecta = DateUtils.menos(fechaLoteRep, diasLoteEvt).before(evtRep.getFecha());
								if(!fechaCorrecta)
									mensajes.add(ProcMsgDAO.create(MENSAJES.SUPERA_TIEMPO_LIMITE_LOTE_EVT_REP,ProcMsg.WARNING,new String[]{}));							
							//}
							if(padreGen!=null){//si informo padre y madre
									String razaP = RazaUtil.getRazaEnBase(padres.getPadreGen().getRaza());
									if(raza.equals(razaP)){
										if(raza.equals(razaNueva)){//caso 1
												this.actualizar(razaDeclaradaNueva,madreGen,padreGen,alta.getNombre(),rpSe,dig);
										}
										else
											throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_PADRES, new String[]{alta.getRp()});
									}
									else//raza distinta entre padre y madre
										this.actualizar(razaDeclaradaNueva,madreGen,padreGen,alta.getNombre(),rpSe,dig);
								}
								else{//si informo madre sola
									if(raza.equals(razaNueva)){//caso 3
										this.actualizar(razaDeclaradaNueva,madreGen,padreGen,alta.getNombre(),rpSe,dig);
									}
									else
										throw new ExcepcionIntegridad(MENSAJES.RAZA_DECLARADA_DISTINTA_MADRE, new String[]{alta.getRp()});
								}
							}
					}
						if (padres.getPadreGen() != null && padres.getMadreGen()==null) 
								throw new ExcepcionIntegridad(MENSAJES.INFORMA_PADRE_Y_NO_MADRE, new String[]{alta.getRp()});
						
			}
		}
		}
		String rpNuevo = alta.getRp();
		if (!rpNuevo.equals(this.getAnimal().getRP())) {
			this.getAnimal().setRP(rpNuevo);
			//if(!this.getAnimal().getRegOrigen().getTipoRegistro().equals("HBA")){
			if(!animal.getRegistroOrigen().contains("HBA")){
				FichaAnimalPendiente fichaAnimal = new FichaAnimalPendiente();
				fichaAnimal.setAnimal(this.getAnimal());
				procLote.agregarFichaAnimal(fichaAnimal);
		}
		}
		this.getAnimal().setearCategoria(fechaCorrecta); // esto en alta y modificacion
		
		if(evtCriaAux!=null){
			for(Object obj : evtRep.getEvtCrias()){
					EvtCria evtCria = (EvtCria)obj;
					if(evtCria.equals(evtCriaAux))
						evtCria.setCria(this.getAnimal());
					}
				HibernateFactory.getSession().update(evtRep);
				}
		
		EvtEstModificacion eventoModificacion = EvtEstModificacionDAO.create(establecimiento, new Date(), this);
		EvtAltaDAO.updateEventoAlta(this);
		this.addModificaciones(eventoModificacion);		
		
		return eventoModificacion;
		
	}

	/**
	 * Para el caso de que la modificación del evento alta realice un cambio en la fecha de nacimiento del animal que informa,
	 * se debe chequear que dicho animal no haya recibido eventos servicios dentro del año y eventos reproducción dentro de los
	 * 16 meses desde la nueva fecha de nacimiento que se informa
	 * @param fechaNacimientoNuevo
	 * @param esHembra
	 * @throws ExcepcionIntegridad
	 */
	private void checkServicios(Date fechaNacimientoNuevo,boolean esHembra) throws ExcepcionIntegridad{
		if(esHembra){
			Date fechaHasta = DateUtils.mas(fechaNacimientoNuevo, 365); 
			Hembra hembra = HembraDAO.findByPrimaryKey(this.getAnimal().getId());
			List servicios = hembra.getEventosEntre(fechaNacimientoNuevo, fechaHasta, Evento.EVT_TIPO_SVC);
			if (servicios.size() > 0) {
				log.error("hay un servicio antes del año despues de la fecha nueva, se probo por afuera y por adentro");
				Iterator i = servicios.iterator();
				String listEventos = "";
				while (i.hasNext()) {
					Evento e = (Evento) i.next();
					listEventos = listEventos.concat("Id evento: "+e.getId()+" Tipo del evento: "+e.getNombreTipo()+"\n");
					
				}
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_SERVICIO_ANIO,
						new String[] { listEventos});
				
			}
			// VERIFICAR QUE NO HAYA EVENTOS REPRODUCCION ENTRE LA FECHA DE NACIMIENTO NUEVO Y 16 MESES MAS ALLA 
			Date fechaHastaAux = DateUtils.mas(fechaNacimientoNuevo, new Float(365.0f*(4.0f/3.0f)).intValue());
			List serviciosReproduccion = hembra.getEventosEntre(fechaNacimientoNuevo, fechaHastaAux, Evento.EVT_TIPO_REP);
			if (serviciosReproduccion.size() > 0) {
				log.error("hay una reproduccion antes del los 16 años de la fecha de nac nueva, SE PROBO");
				Iterator i = servicios.iterator();
				String listEventos = "";
				while (i.hasNext()) {
					Evento e = (Evento) i.next();
					listEventos = listEventos.concat("Id evento: "+e.getId()+" Tipo del evento: "+e.getNombreTipo()+"\n");
					
				}
				throw new ExcepcionIntegridad(MENSAJES.MODIFICACION_REPRODUCCION_16,
						new String[] { listEventos});
	
			}
		}
	}
	
	/**
	 * Calcula la composición racial del animal para el caso de una modificación de evento alta, donde las
	 * condiciones que se evalúan sobre los datos del evento resultan en un empadronar
	 * @param composicionRacialNueva
	 * @param razaDeclaradaNueva
	 * @param fechaNacimientoNuevo
	 * @param alta
	 * @throws ExcepcionIntegridad
	 */
	private void empadronar(Map composicionRacialNueva,Raza razaDeclaradaNueva,Date fechaNacimientoNuevo,Alta alta) throws ExcepcionIntegridad{
		composicionRacialNueva.put(razaDeclaradaNueva.getEspecie().getDesconocida(), new Float(1)); // la composicion nueva
		this.getAnimal().setPadre(null);
		this.getAnimal().setMadreGenetica(null);
		ComposicionRacial compR = ComposicionRacial.nuevaInstancia(razaDeclaradaNueva);
		this.getAnimal().setComposicionRacial(compR);
		compR.setAnimal(this.getAnimal());
		compR.setearComposicionRacial(composicionRacialNueva);
		this.getAnimal().setFechaNac(fechaNacimientoNuevo);
		if (alta.getNombre() != null)
			this.getAnimal().setNombre(alta.getNombre());
		String rpSe = null;
		Integer dig = null;
		if(alta.getSenasa()!=null){
			rpSe = alta.getSenasa().getRpSenasa();
			dig = new Integer(alta.getSenasa().getDigitoVerficador());
		}
		this.getAnimal().setRpSenasa(rpSe);
		this.getAnimal().setCodigoVerificador(dig);
		
		
	}
	
	/**
	 * Copia los registros del animal origen al nuevo animal y los persiste
	 * @param animal2
	 * @param nuevoAnimal
	 * @throws ExcepcionIntegridad
	 */
	private void copiarRegistros(Animal animal2, Animal nuevoAnimal) throws ExcepcionIntegridad {
		Registro ro = RegistroDAO.create(animal2.getRegOrigen().getTipoRegistro().getId(),animal2.getRegOrigen().getNumero(),animal2.getRegOrigen().getCodigoBaja()==null ? null:animal2.getRegOrigen().getCodigoBaja().toString(),animal2.getRegOrigen().getFechaBaja());
		HibernateFactory.getSession().delete(animal2.getRegOrigen());
		HibernateFactory.getSession().save(ro);
		Registro ri = RegistroDAO.create(animal2.getRegIdentificador().getTipoRegistro().getId(),animal2.getRegIdentificador().getNumero(),animal2.getRegIdentificador().getCodigoBaja()==null?null:animal2.getRegIdentificador().getCodigoBaja().toString(),animal2.getRegIdentificador().getFechaBaja());
		if(!ro.equals(ri)){
			HibernateFactory.getSession().delete(animal2.getRegIdentificador());
			HibernateFactory.getSession().save(ri);
			nuevoAnimal.setRegIdentificador(ri);
			nuevoAnimal.addRegistro(ri);
		}else{	
			nuevoAnimal.setRegIdentificador(ro);
		}	
		nuevoAnimal.setRegOrigen(ro);
		nuevoAnimal.addRegistro(ro);
		Set registros = new HashSet(animal2.getRegistros());
		Iterator it = registros.iterator();
		while(it.hasNext()){
			Registro r = (Registro)it.next();
			if(!r.equals(ro)&&!r.equals(ri)){
				Registro aux = RegistroDAO.create(r.getTipoRegistro().getId(),r.getNumero(),r.getCodigoBaja()==null?null:r.getCodigoBaja().toString(),r.getFechaBaja());
				HibernateFactory.getSession().delete(r);
				HibernateFactory.getSession().save(aux);
				nuevoAnimal.addRegistro(aux);
			}	
		}
	}

	@Override
	public void ejecutarBaja() throws ExcepcionIntegridad {
		// lo tuve que harcodear a que reciba el parametro de mensajes ya que debo enviar un warning
		
	}

	@Override
	public boolean validarBaja() throws ExcepcionIntegridad {
		// TODO Auto-generated method stub
		return false;
	}

}
