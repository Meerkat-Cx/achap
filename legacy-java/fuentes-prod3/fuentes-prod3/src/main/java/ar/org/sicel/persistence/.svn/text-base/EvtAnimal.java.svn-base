package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Animal" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvAnim_Evento"
 * 
 */
public abstract class EvtAnimal extends ar.org.sicel.persistence.Evento {

	// --------------- attributes ---------------------
	private ar.org.sicel.persistence.Animal animal;

	private ar.org.sicel.persistence.ProcEvtAnimal procEvtAnimal;

	private String estadoAnimal;

	private boolean finalizaLactancia;

	private boolean iniciaLactancia;

	/**
	 * @hibernate.property column = "estado" length = "2"
	 */
	public String getEstadoAnimal() {
		return estadoAnimal;
	}

	/**
	 * @param estadoAnimal
	 *            The estadoAnimal to set.
	 */
	public void setEstadoAnimal(String estadoAnimal) {
		this.estadoAnimal = estadoAnimal;
	}

	/**
	 * @hibernate.property column = "finalizaLactancia"
	 */
	public boolean isFinalizaLactancia() {
		return finalizaLactancia;
	}

	/**
	 * @param finalizaLactancia
	 *            The finalizaLactancia to set.
	 */
	public void setFinalizaLactancia(boolean finalizaLactancia) {
		this.finalizaLactancia = finalizaLactancia;
	}

	/**
	 * @hibernate.property column = "iniciaLactancia"
	 */
	public boolean isIniciaLactancia() {
		return iniciaLactancia;
	}

	/**
	 * @param iniciaLactancia
	 *            The iniciaLactancia to set.
	 */
	public void setIniciaLactancia(boolean iniciaLactancia) {
		this.iniciaLactancia = iniciaLactancia;
	}

	protected EvtAnimal() {
	}

	protected EvtAnimal(Establecimiento est, Date fecha, Animal animal,
			List msgs, String tipo) throws ExcepcionIntegridad {
		super(est, fecha);
		this.animal = animal;
		reglasAnimalesEst(msgs, fecha);
		if (tipo != null)
			mismoDiaTipo(fecha, tipo);

		// El control de que la fecha este bien no se hace aca, se hace en
		// animal.addEventoAnimal
		// no se llama a addEventoAnimal() directamente desde aca, porque el
		// evento tiene que estar
		// ya cargado con datos a veces antes de eso (por ejemplo en las
		// reproducciones)

	}

	private void mismoDiaTipo(Date fecha, String tipo)
			throws ExcepcionIntegridad {
		EvtAnimal ea = this.getAnimal().getEventoEnFecha(fecha, tipo);
		if (ea != null) {
			if ((tipo.equals(Evento.EVT_TIPO_TRA))
					&& (!ea.getFecha().equals(fecha))
					&& ((EvtTransferencia) ea).isImplicita())
				return;
			throw new ExcepcionIntegridad(MENSAJES.FECHA_IGUAL_TIPO,
					new String[] { StringUtils.formatDate(fecha) });
		}
		// TODO Auto-generated method stub

	}

	// ------------- relations ------------------

	/**
	 * 
	 * @throws ExcepcionIntegridad
	 * @hibernate.many-to-one column="animal" not-null="true" outer-join="auto"
	 *                        foreign-key="FK_EvAnim_Anim"
	 * 
	 */
	public ar.org.sicel.persistence.Animal getAnimal() {
		return this.animal;
	}

	public void setAnimal(ar.org.sicel.persistence.Animal animal) {
		this.animal = animal;
	}

	/**
	 * 
	 * @hibernate.one-to-one outer-join="auto" property-ref="evtAnimal"
	 * 
	 */
	public ar.org.sicel.persistence.ProcEvtAnimal getProcEvtAnimal() {
		return this.procEvtAnimal;
	}

	protected void setProcEvtAnimal(
			ar.org.sicel.persistence.ProcEvtAnimal procEvtAnimal) {
		this.procEvtAnimal = procEvtAnimal;
	}

	// ---------------- business methods ----------------------

	/**
	 * Encabezamiento del resumen del Evento. Agrega el estado y si inicia o
	 * finaliza lactancia.
	 * 
	 * @return (ID) fecha tipo-ev estado [ini fin]
	 */
	protected String getResumenEncab() {
		String result = super.getResumenEncab();
		result = result
				+ (this.getEstadoAnimal() == null ? "" : this.getEstadoAnimal())
				+ "[";
		if (!isFinalizaLactancia() && !isIniciaLactancia())
			result = result + "_ni_nf_";
		if (!isFinalizaLactancia() && isIniciaLactancia())
			result = result + "__ini__";
		if (isFinalizaLactancia() && !isIniciaLactancia())
			result = result + "__fin__";
		if (isFinalizaLactancia() && isIniciaLactancia())
			result = result + "fin-ini";
		result = result + "] ";
		return result;
	}

    /**
     * Resumen del proceso de este evento, si hubiera.
     * @return los mensajes del proceso del evento
     */
    protected String getResumenProceso() {
        String result = "";
        if (this.getProcEvtAnimal() == null)
            return result;
        Set msgs = this.getProcEvtAnimal().getProcMsgsses();
        if (msgs.size() == 0)
            return result;
        result = result + "\n";
        for (Iterator iter = msgs.iterator(); iter.hasNext();) {
            result = result + "\n\t";
            ProcMsg procMsg = (ProcMsg) iter.next();
            result = result + procMsg.getCodigoMsg().getId() + ": ";    
            result = result + procMsg.getInformacion();
        }
        return result;
    }

	/*
	 * El getClass() no funciona, porque por ejemplo EvtNuevoInd y EvtClon y
	 * EvtTransfEmbrionaria todos son, a efectos de transiciones, un EvtServicio
	 * 
	 * return
	 */
	// public abstract Integer getClazzParaTransicion();
	@SuppressWarnings("unchecked")
	public Set getAnimalesModificados() {
		Set animalesModificados = new HashSet();
		animalesModificados.add(this.getAnimal());
		return animalesModificados;
	}

	@SuppressWarnings("unchecked")
	public void addAnimalesModificados(Set animalesModificados) {
		if ((this.getAnimal()) != null)
			animalesModificados.add(this.getAnimal());
		else
			return;
	}

	/**
	 * Ayuda a construir un link para la interface web.
	 * 
	 * @return un link con el id del proceso de este evento
	 */
	public String getLinkAProc() {
		if ((this.getProcEvtAnimal() == null)
				|| (this.getProcEvtAnimal().getProcAnimal() == null))
			return null;
		return "/sicel3/buscarProcAnimal.do?procAnimalId="
				+ this.getProcEvtAnimal().getProcAnimal().getId();
	}

	/**
	 * Recolecta todos los eventos posteriores a este (este incluido), junto con
	 * los eventos de los descendientes de este animal, en forma recursiva.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet recolectarTodosEventosDependientes() {
		SortedSet set = new TreeSet(new EventosPorFechaYTipo());
		set.addAll(this.getAnimal().getEvtAnimals().tailSet(this)); // se queda
																	// con los
																	// eventos
																	// posteriores

		SortedSet resultado = new TreeSet(new EventosPorFechaYTipo());
		Iterator eventos = set.iterator();

		Iterator dependientesDirectos = this.getDependientes().iterator();
		while (dependientesDirectos.hasNext()) {
			EvtAnimal evtAnimal = (EvtAnimal) dependientesDirectos.next();
			evtAnimal.recolectarEventosDependientesEn(resultado);
		}

		while (eventos.hasNext()) {
			EvtAnimal evtAnimal = (EvtAnimal) eventos.next();
			evtAnimal.recolectarEventosDependientesEn(resultado);
		}
		return resultado;
	}

	/**
	 * Recolecta los eventos dependientes. El caso general es que solamente se
	 * agregue este evento, pero en el caso de las reproducciones, se agregan
	 * tambien los eventos de las crias, de forma recursiva
	 */
	@SuppressWarnings("unchecked")
	public void recolectarEventosDependientesEn(SortedSet set) {
		set.add(this);
	}
	/**
	    *En este metodo se valida que el animal este en el tambo informado
	    *si los tambos son distintos
	    	*si ev tranferencia no hay problema return
	    	*si los tambos tienen el mismo propietario
	    		*si los tambos son administrados por distintas eclos 
	    			*si es carga inicial y se informa evento servicio, preñez,reproduccion, control o secada no se realiza ningun chequeo, no hay error, return
	    			*sino error (ej- baja...)
	    		*si no es carga inicial y la fecha informada supera los 60 dias de retroactividad al ultimo evt se informa el error
	    		 *sino supera los 60 dias de retroactividad debe haber en el rango de fechas del qeu se esta informando
	    		  a la fecha del evento mas reciente algun evento transferecia desde el tambo informado al tambo actual
	    		  caso contrario se informa el error
	    		*si llega hasta aca se realiza la tranfencia implicita
	    	*si los tambos no tienen el mismo propietario
	    		*si es carga inicial y se informa evento servicio, preñez,reproduccion, control o secada no se realiza ningun chequeo, no hay error,retunr
	    		*si los tambos son administrados por distintas eclos error
	    		*si no es carga inicial y la fecha informada supera los 60 dias de retroactividad al ultimo evt se informa el error
	    		 *sino supera los 60 dias de retroactividad debe haber en el rango de fechas del qeu se esta informando
	    		  a la fecha del evento mas reciente algun evento transferecia desde el tambo informado al tambo actual
	    		  caso contrario se informa el error
	    		 * si llega hasta aca error
	    **/
	   
		public void reglasAnimalesEst2(List msgs, Date fechaEvento) throws ExcepcionIntegridad {
	    	Establecimiento viejoTambo = getAnimal().getEstablecimiento();
	    	Establecimiento nuevoTambo = getEstablecimiento(); 
	    if(viejoTambo!=null)	{
	    	if (!nuevoTambo.equals(viejoTambo)) {
	    		SortedSet set = getAnimal().getEvtAnimals();
				Date ultimaFecha = new Date();
				if(!set.isEmpty()){
					EvtAnimal es = (EvtAnimal) set.last();
					ultimaFecha = es.getFecha();
				}
					if (this.getClass() == EvtTransferencia.class) {
						return;
						
					}
					if(viejoTambo.getPropietario().equals(nuevoTambo.getPropietario())){
		    							
						if(!viejoTambo.getEclo().equals(nuevoTambo.getEclo())){
							//ACORDARSEEEEEEEEEEEEEEEEEEEEEEEE DE LO DEL EVENTO SECADA
							if (!Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())&&
									(this.getClass() == EvtControlAnimal.class ||
											this.getClass() == EvtNuevoInd.class ||
											this.getClass() == EvtTransEmb.class ||
											this.getClass() == EvtReproduccion.class ||
											this.getClass() == EvtPrenez.class ||
											this.getClass() == EvtSecada.class)) 
								return;
							throw new ExcepcionIntegridad(MENSAJES.TAMBO_DISTINTA_ECLO,
									new String[] { getAnimal().getRegistroOrigen(), viejoTambo.getId().toString(),viejoTambo.getEclo().getId().toString(),nuevoTambo.getId().toString(),nuevoTambo.getEclo().getId().toString() });
						
						}
						if ((Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))&&(fechaEvento.before(ultimaFecha))){
							//seria si no es carga inicial
							int cantDias = DateUtils.diasEntre(fechaEvento,ultimaFecha);
							int cantDiasMax = Integer.parseInt(nuevoTambo.getParametro(nuevoTambo.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_OTRO_TAMBO));
							if(cantDias <=cantDiasMax){
								List transferencias = getAnimal().getEventosEntre(fechaEvento,ultimaFecha,EVT_TIPO_TRA);
								if(!transferencias.isEmpty()){
									Iterator it = transferencias.iterator();
									while(it.hasNext()){
										EvtTransferencia evt = (EvtTransferencia)it.next();
										if(evt.getAntiguoEstab().equals(nuevoTambo)&& (evt.getEstablecimiento().equals(viejoTambo)))
											return;
									}
								}						
							}
							throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_ESTABLECIMIENTO,
									new String[] { getAnimal().getRegistroOrigen(), getAnimal().getEstablecimiento().getId().toString(), getAnimal().getEstablecimiento().getPropietario().getId().toString()+" - "+getAnimal().getEstablecimiento().getPropietario().getNombreContacto(),getAnimal().getEstablecimiento().getEclo().getId().toString()});
						}
		    	    	//la tranferencia implicita tiene que entrar antes que el evento en cuestion por eso es q se le resta un segundo al evento
		    	    	EvtTransferencia implicita = EvtTransferenciaDAO.createTransferenciaImplicita(viejoTambo,getAnimal(),DateUtils.menosSegundos(fechaEvento, 1),nuevoTambo,getAnimal().getRP(),null,msgs,false);
		    	    	this.addDependiente(implicita);
		    	    	try {
		    	    		HibernateFactory.getSession().save(implicita);
		    	    		
		    	    	} catch (HibernateException he) {
		    	    		throw new ErrorFatal("Error al querer guardar la TransferenciaImplicita - ProcAnimalDAO",he);
		    	    	}
						//an.transferirAEstablecimiento(est,an.getRP(),msgs);
				 		ProcMsg msg = ProcMsgDAO.create(MENSAJES.TRANSFERENCIA_IMPLICITA_REALIZADA,ProcMsg.WARNING,new String[]{getAnimal().getRegistroOrigen(),viejoTambo.getId().toString(),nuevoTambo.getId().toString()});
						msgs.add(msg);
						
		    	    }
		    		else{
		    			//MODIF se lanza la exception en todos los casos menos en el
						// evento transferencia
						//ACORDARSEEEEEEEEEEEEEEEEEEEEEEEE DE LO DEL EVENTO SECADA
		    			if (!Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())&&
		    					(this.getClass() == EvtControlAnimal.class ||
										this.getClass() == EvtNuevoInd.class ||
										this.getClass() == EvtTransEmb.class ||
										this.getClass() == EvtReproduccion.class ||
										this.getClass() == EvtPrenez.class ||
										this.getClass() == EvtSecada.class)) 
		    				return;//si es carga inicial!
						
		    			if(!viejoTambo.getEclo().equals(nuevoTambo.getEclo()))
		    				throw new ExcepcionIntegridad(MENSAJES.TAMBO_DISTINTA_ECLO,
									new String[] { getAnimal().getRegistroOrigen(), viejoTambo.getId().toString(),viejoTambo.getEclo().getId().toString(),nuevoTambo.getId().toString(),nuevoTambo.getEclo().getId().toString() });
						
						
		    			if ((Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))&&(fechaEvento.before(ultimaFecha))){
							//seria si no es carga inicial
							int cantDias = DateUtils.diasEntre(fechaEvento,ultimaFecha);
							int cantDiasMax = Integer.parseInt(nuevoTambo.getParametro(nuevoTambo.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_OTRO_TAMBO));
							if(cantDias <=cantDiasMax){
								List transferencias = getAnimal().getEventosEntre(fechaEvento,ultimaFecha,EVT_TIPO_TRA);
								if(!transferencias.isEmpty()){
									Iterator it = transferencias.iterator();
									while(it.hasNext()){
										EvtTransferencia evt = (EvtTransferencia)it.next();
										if(evt.getAntiguoEstab().equals(nuevoTambo)&& (evt.getEstablecimiento().equals(viejoTambo)))
											return;
									}
								}						
							}
							throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_ESTABLECIMIENTO,
									new String[] { getAnimal().getRegistroOrigen(), getAnimal().getEstablecimiento().getId().toString(), getAnimal().getEstablecimiento().getPropietario().getId().toString()+" - "+getAnimal().getEstablecimiento().getPropietario().getNombreContacto(),getAnimal().getEstablecimiento().getEclo().getId().toString()});
						}
						this.manejoDeExceptionNoTransferencia(getAnimal()
								.getRegistroOrigen(), viejoTambo);
		    		}
	    	}
	    			
	    }
	    
	    }
		public void reglasAnimalesEst(List msgs, Date fechaEvento) throws ExcepcionIntegridad {
	    	Establecimiento viejoTambo = getAnimal().getEstablecimiento();//tambo del animal actualmente
	    	Establecimiento nuevoTambo = getEstablecimiento();//tambo del evento actual
	    if(viejoTambo!=null)	{
	    	Date ultimaFecha = null;
	    	Evento ultimo = null;
	    	SortedSet allEventos = getAnimal().getAllEventosSinLactanciaAbiertaNiModifNiAlta();
			if(allEventos.isEmpty())
				ultimaFecha = getAnimal().getFechaNac();
			else{
				ultimo = (Evento) allEventos.last();
				ultimaFecha = ultimo.getFecha();
			}
			boolean esRetroactivo = false;
			if(ultimo!=null && fechaEvento.before(ultimaFecha))
				esRetroactivo = true;
			if (this.getClass() == EvtTransferencia.class)
				return;
			if(!esRetroactivo){
			    	if (!nuevoTambo.equals(viejoTambo)) {//si los tambos son distintos y no es un evento retroactivo entonces puede haber un transferencia implicita
			    		if(viejoTambo.getPropietario().equals(nuevoTambo.getPropietario())){//si son del mismo propietario entonces transferencia
				    			
				    	    	//la tranferencia implicita tiene que entrar antes que el evento en cuestion por eso es q se le resta un segundo al evento
				    	    	EvtTransferencia implicita = EvtTransferenciaDAO.createTransferenciaImplicita(viejoTambo,getAnimal(),DateUtils.menosSegundos(fechaEvento, 1),nuevoTambo,getAnimal().getRP(),null,msgs,false);
				    	    	this.addDependiente(implicita);
				    	    	/*try {
				    	    		HibernateFactory.getSession().save(implicita);
				    	    	} catch (HibernateException he) {
				    	    		throw new ErrorFatal("Error al querer guardar la TransferenciaImplicita - ProcAnimalDAO",he);
				    	    	}*/
								//an.transferirAEstablecimiento(est,an.getRP(),msgs);
						 		ProcMsg msg = ProcMsgDAO.create(MENSAJES.TRANSFERENCIA_IMPLICITA_REALIZADA,ProcMsg.WARNING,new String[]{getAnimal().getRegistroOrigen(),viejoTambo.getId().toString(),nuevoTambo.getId().toString()});
								msgs.add(msg);
								
				    	    }
				    		else{		    			
									throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_ESTABLECIMIENTO,
											new String[] { nuevoTambo.getId().toString(),nuevoTambo.getPropietario().getId().toString(),getAnimal().getRegistroOrigen(), getAnimal().getEstablecimiento().getId().toString(), getAnimal().getEstablecimiento().getPropietario().getId().toString()+" - "+getAnimal().getEstablecimiento().getPropietario().getNombreContacto(),getAnimal().getEstablecimiento().getEclo().getId().toString()});
							}
			    	}
			}
			else{//si es retroactivo
	    		
	    		Evento evAnterior = getAnimal().getEventoAnteriorFecha(fechaEvento);
	    		Establecimiento tamboAnterior = (evAnterior==null)?null:evAnterior.getEstablecimiento();
				Evento evPosterior = getAnimal().getEventoPosteriorFecha(fechaEvento);
				Establecimiento tamboPosterior = (evPosterior==null)?null:evPosterior.getEstablecimiento();			
				if ((Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))){
					//seria si no es carga inicial
					int cantDias = DateUtils.diasEntre(fechaEvento,ultimaFecha);
					int cantDiasMax = Integer.parseInt(nuevoTambo.getParametro(nuevoTambo.DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_OTRO_TAMBO));
					if(cantDias <=cantDiasMax){
						List transferencias = getAnimal().getEventosEntre(fechaEvento,ultimaFecha,EVT_TIPO_TRA);
						if(!transferencias.isEmpty()){
							Iterator it = transferencias.iterator();
							while(it.hasNext()){
								EvtTransferencia evt = (EvtTransferencia)it.next();
								if(evt.getAntiguoEstab().equals(nuevoTambo)&& (evt.getEstablecimiento().equals(viejoTambo)))
									return;
							}
						}						
					}
					throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_ESTABLECIMIENTO,
							new String[] {  nuevoTambo.getId().toString(),nuevoTambo.getPropietario().getId().toString(),getAnimal().getRegistroOrigen(), getAnimal().getEstablecimiento().getId().toString(), getAnimal().getEstablecimiento().getPropietario().getId().toString()+" - "+getAnimal().getEstablecimiento().getPropietario().getNombreContacto(),getAnimal().getEstablecimiento().getEclo().getId().toString()});
				}
				//si el evento anterior es una tranferencia implicita y es del tambo X1 al tambo X2 y el 
				//evento que informo es en X1 entonces no tiene sentido hacer otra transferencia, seria borrar la de X1 a X2 y list
				//ejem ev tambo X1               ev tran X1-X2                 ev YYY tambo X2
				//entra un ev Y en tambo X1 entre tran y ev YYY
				// quedaria mal asi       ev tambo X1 ev tran X1- X2  ev tran X2-X1   ev Y X1  ev tran X1-X2  ev YYY tambo X2
				//quedaria bien asi       ev tambo X1     ev Y X1 ev tran X1-X2 ev YYY tambo X2
				//entonces borrar la transferencia anterior y no generar ninguna (por lo menos la primera)
				EvtTransferencia primera = null;
				if(tamboAnterior!=null && !tamboAnterior.equals(nuevoTambo)){
					Evento evtAnterior = this.getAnimal().getEventoAnteriorFecha(fechaEvento);
					if((evtAnterior!=null)&& evtAnterior.getNombreTipo().equals(Evento.EVT_TIPO_TRA) 
							&& (((EvtTransferencia)evtAnterior).isImplicita())
							&& (((EvtTransferencia)evtAnterior).getAntiguoEstab().equals(nuevoTambo))){
						((EvtTransferencia)evtAnterior).ejecutarBajaSinValidacion();
					}
					else{
						primera = EvtTransferenciaDAO.createTransferenciaImplicita(tamboAnterior,getAnimal(),DateUtils.menosSegundos(fechaEvento, 1),nuevoTambo,getAnimal().getRP(),null,msgs,true);
						System.out.println("transferencia retro 1");
						this.addDependiente(primera);
					}
				}
				EvtTransferencia segunda = null;
				if(tamboPosterior !=null && !tamboPosterior.equals(nuevoTambo)){
					segunda = EvtTransferenciaDAO.createTransferenciaImplicita(nuevoTambo,getAnimal(),DateUtils.masSegundos(fechaEvento, 1),tamboPosterior,getAnimal().getRP(),null,msgs,true);
					System.out.println("transferencia retro 2");
					this.addDependiente(segunda);
				}
		    	if(primera !=null){
		    		ProcMsg msg = ProcMsgDAO.create(MENSAJES.TRANSFERENCIA_IMPLICITA_REALIZADA,ProcMsg.WARNING,new String[]{getAnimal().getRegistroOrigen(),tamboAnterior.getId().toString(),nuevoTambo.getId().toString()});
		    		msgs.add(msg);
		    	}
		    	if(segunda !=null){
		    		ProcMsg msg = ProcMsgDAO.create(MENSAJES.TRANSFERENCIA_IMPLICITA_REALIZADA,ProcMsg.WARNING,new String[]{getAnimal().getRegistroOrigen(),tamboPosterior.getId().toString(),nuevoTambo.getId().toString()});
		    		msgs.add(msg);
		    	}
				
			}
	    }
	    else{//de la unica forma que se acepte un evento para un animal que no tiene tambo es que el propietario del animal sea el mismo propietario del tambo donde se informa el evento 
	    	// o que sea un evento transferencia
	    	if(!getAnimal().getPropietario().equals(this.getEstablecimiento().getPropietario())
	    			&& !(this.getClass() == EvtTransferencia.class))
	    		throw new ExcepcionIntegridad(MENSAJES.SIN_TAMBO_ASIGNADO_INFORMAR_TRANSFERENCIA,
	    				new String[] { getAnimal().getRegistroOrigen(),getAnimal().getPropietario().getId().toString(),getEstablecimiento().getPropietario().getId().toString(), getEstablecimiento().getId().toString() });
	    }
	    
	    }
	       
	//  MODIF lanzo la exception, ya que este evento no permite distintos
		// establecimientos con distintos dueños
		public void manejoDeExceptionNoTransferencia(String regId, Establecimiento tambo) throws ExcepcionIntegridad {
			
			throw new ExcepcionIntegridad(MENSAJES.ANIMAL_NO_ESTABLECIMIENTO,
					new String[] { regId, tambo.getId().toString(), tambo.getPropietario().getId().toString()+" - "+tambo.getPropietario().getNombreContacto(),tambo.getEclo().getId().toString() });
		}
	public Date getFechaLactancia() {

		return DateUtils.mas(this.getFecha(), 1);
	}

	/**
	 * Obtiene el evento anterior al evento animal actual, se tiene en cuenta el
	 * evento alta
	 * 
	 * @return Evento
	 */
	@SuppressWarnings("unchecked")
	public Evento findEventoAnterior() {
		SortedSet setEventos = this.getAnimal()
				.getAllEventosSinLactanciaAbierta(); // todos los eventos
														// animal incluyendo el
														// alta del animal
		if (!setEventos.isEmpty()) {
			SortedSet masChicos = setEventos.headSet(this);
			if (masChicos != null && !masChicos.isEmpty()) {
				Object[] arrayEventos = masChicos.toArray();
				if ((this.getId().equals(((Evento) masChicos.last()).getId())))
					if (masChicos.size() > 1
							&& arrayEventos[masChicos.size() - 2] != null)
						return (Evento) arrayEventos[masChicos.size() - 2];
				/*
				 * else return null; else return (Evento)arrayEventos[0];
				 */

				// if(masChicos!=null && !masChicos.isEmpty())
				// return (Evento)masChicos.last();
			}
		}
		return null;
	}

	/**
	 * Obtiene el evento posterior al evento animal actual, se tiene en cuenta
	 * el evento alta
	 * 
	 * @return Evento
	 */
	@SuppressWarnings("unchecked")
	public Evento findEventoPosterior() {
		SortedSet setEventos = this.getAnimal()
				.getAllEventosSinLactanciaAbierta(); // todos los eventos
														// animal incluyendo el
														// alta del animal
		SortedSet masGrandes = setEventos.tailSet(this);
		if (masGrandes != null && (!masGrandes.isEmpty())
				&& (Evento) masGrandes.first() != null) {
			Object[] arrayEventos = masGrandes.toArray();
			if ((this.getId().equals(((Evento) masGrandes.first()).getId())))
				if (masGrandes.size() > 1)
					return (Evento) arrayEventos[1];
				else
					return null;
			else
				return (Evento) arrayEventos[0];

		}
		return null;
	}

	public void ejecutarBaja() throws ExcepcionIntegridad {
		if (!EvtAnimalDAO.tieneDependienteTipo(this, Evento.EVT_TIPO_LAC))
			this.getAnimal().borrarEventoAnimal(this);
	}

	public boolean equals(Object eve) {
		if (eve != null) {
			if (!(eve instanceof EvtAnimal))
				return false;
			EvtAnimal ev = (EvtAnimal) eve;
			if ((ev.getId() != null && this.getId() != null)
					&& (ev.getId().equals(this.getId())))
				return true;
			Date fecha1 = this.getFecha();
			Date fecha2 = ev.getFecha();
			String tipo1 = this.getNombreTipo();
			String tipo2 = ev.getNombreTipo();
			if (tipo1.equals(tipo2))
				return DateUtils.mismoDia(fecha1, fecha2);
			return false;

		}
		return false;

	}

}
