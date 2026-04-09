/*
 * Created on 17/06/2005
 */
package ar.org.sicel.persistence;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.services.impl.AnimalesLactanciasOficiales;
import ar.org.sicel.proc.v1.lote.types.STProdMedObj;
import ar.org.sicel.util.DateUtils;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Lactancia" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id"
 *                                foreign-key="FK_EvLactancia_EvAnim"
 * 
 */
public class EvtLactancia extends EvtAnimal implements Lactancia {
	
	private Map produccionesLactancia;
	
	
    private Map mediciones;

    private Integer dias;

    Boolean esCerrada;

    String categoria;
    
	private Integer nroLact;
	private Date fechaInicio;
	
	private Integer ordenies;
	private String control;
	private Boolean esOficial;
	

	private static final String objetoDeMedicion_Leche = "LE";

    protected static final String tipoDeMedicion_Absoluta = "ABS";

    protected static final String tipoDeMedicion_Porcentual = "POR";

    private static final String Metodo_A4 = "A4";

    private static final String Metodo_A6 = "A6";

    private static final String Metodo_C4 = "C4";

    private static final String Metodo_C6 = "C6";

    private static final String Metodo_Error = "(problemas con los metodos de control)";
    private static final String Distancia_controles = "(la distancia entre controles supera los máximos)";

    private static final String Categoria_Abierta = "(abierta)";

    private static final String Categoria_No_Oficial = "No Oficial";

    private static final String Categoria_Oficial = "Oficial";

    private static final String Categoria_Oficial_Estimada = "Oficial Estimada";

    // --------------- attributes ---------------------

    // TODO La fecha de inicio es la fecha del evento en las migradas. Para
    // homogeneizar con este calculo debería ser la fecha final la fecha del
    // evento, pero estan legacy mal. Quizás cambiar en el DAO al leer (get).

    /**
     * @hibernate.property column = "categoria" length = "255"
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria
     *            The categoria to set.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @hibernate.property column = "diasTotales"
     */
    public Integer getDias() {
        return dias;
    }

    /**
     * @param diasTotales
     *            The diasTotales to set.
     */
    public void setDias(Integer diasTotales) {
        this.dias = diasTotales;
    }

    /**
     * @hibernate.property column = "esCerrada"
     */
    public Boolean isEsCerrada() {
        return esCerrada;
    }

    /**
     * @param esCerrada
     *            The esCerrada to set.
     */
    public void setEsCerrada(Boolean esCerrada) {
        this.esCerrada = esCerrada;
    }

    
    /**
     * @hibernate.property column = "esOficial"
     */
    public Boolean isEsOficial() {
        return esOficial;
    }

    /**
     * @param esCerrada
     *            The esCerrada to set.
     */
    public void setEsOficial(Boolean esOficial) {
        this.esOficial = esOficial;
    }
    
    
    /**
     * @hibernate.property column = "nroLact"
     */
    public Integer getNroLact() {
		return this.nroLact;
	}
    
    public void setNroLact(Integer nroLact) {
    	this.nroLact = nroLact;
    }
    
    
    /**
     * 
     * @hibernate.map table="Ev_Produccion_Lactancia" lazy="true" cascade="all"
     * @hibernate.collection-key column="lactancia"
     *                           foreign-key="FK_EvProd_EvLactancia"
     * @hibernate.collection-index column="nombre" type="string" length="30"
     * @hibernate.collection-composite-element class="ar.org.sicel.persistence.ProduccionLactancia"
     */
    public Map getProduccionesLactancia() {
		return produccionesLactancia;
	}

	public void setProduccionesLactancia(Map produccionesLactancia) {
		this.produccionesLactancia = produccionesLactancia;
	}
	
    
    /**
     * 
     * @hibernate.map table="Ev_Med_Lactancias" lazy="true" cascade="all"
     * @hibernate.collection-key column="lactancia"
     *                           foreign-key="FK_EvMedL_EvLactancia"
     * @hibernate.collection-index column="objeto" type="string" length="30"
     * @hibernate.collection-element column="valor" type="float" not-null="true"
     */
    public Map getMediciones() {
        return mediciones;
    }

    /**
     * @param mediciones
     *            The mediciones to set.
     */
    public void setMediciones(Map mediciones) {
        this.mediciones = mediciones;
    }
    
    
    /**
     * @hibernate.property column = "fechaInicio"
     */
	public Date getFechaInicio() {
		return fechaInicio; 
	}
	
	public void setFechaInicio(Date fecha) {
		this.fechaInicio = fecha;
	}
	
	/**
     * @hibernate.property column = "control"
     */
	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	/**
     * @hibernate.property column = "ordenies"
     */
	public Integer getOrdenies() {
		return ordenies;
	}

	public void setOrdenies(Integer ordenies) {
		this.ordenies = ordenies;
	}	

    // ---------------- business methods ----------------------

 

	public String getResumenMediciones() {
        String result = "{ ";
        Set meds = this.mediciones.keySet();
        for (Iterator iter = meds.iterator(); iter.hasNext();) {
            Object obj = iter.next();
            result = result + obj + " : " + mediciones.get(obj);
            if (iter.hasNext())
                result = result + " , ";
        }
        result = result + " }";
        return result;
    }

    /*
     * @see ar.org.sicel.persistence.Evento#getResumen()
     */
    public String getResumen() {
        return "Lactancia nº "+ getNroLact()+", "+ getDias() + " dias " + (getCategoria().trim())
                + "\n\t\t(por interpolación) " + getResumenMediciones();
    }

    /*
     * @see ar.org.sicel.persistence.Evento#getNombreTipo()
     */
    public String getNombreTipo() {
        return EVT_TIPO_LAC;
    }



    /**
     * Calcula una lactancia cerrada a partir del <code>evFin</code> hacia
     * atrás, buscando el <code>evIni</code>, y acumulando los eventos
     * Producción. La idea es que cuando un evento finalize una Lactancia, llame
     * a este metodo para obtener la lactancia y hacerla persistente, si bien
     * esto no es obligatorio y se puede usar el cálculo sin persistir, por
     * ejemplo en un test.
     * 
     * @param animal
     *            La <code>Hembra</code> sobre el que se calcula la lactancia.
     * @param evFin
     *            El evento del animal que finaliza la lactancia que se desea
     *            calcular.
     * 
     * @return un <code>EvtLactancia</code> sin persistir
     * 
     */
    public static EvtLactancia calcularLactanciaCerrada(Animal animal, EvtAnimal evFin) {
        EvtLactancia result = null;
        List evsProd = new ArrayList();
        EvtAnimal evIni = buscarIniLactAcumProdsDesde(animal, evFin, evsProd);
        //if (!(evIni == null || evsProd.size() == 0)) {
        if (!(evIni == null)) {
        	// aca tengo que ver si evFin es reproduccion
            // entonces fechaFin = fechaFin - 1 dia
        	Date fechaFin = null;
        	if (evFin.getNombreTipo().equals(Evento.EVT_TIPO_REP))
        		//fechaFin = evFin.getFecha();
        		fechaFin = DateUtils.menos(evFin.getFecha(), 1);
        	else
        		fechaFin = evFin.getFecha();
            result = CalcularLactancia(animal, evIni, fechaFin/*, evsProd*/, evFin,
                    true, 9999);
            if(result!=null)//esto lo hago pq del calcular lactancia puede venir la lactancia en null, por ejemplo si hay un solo control y este es el mismo dia del parto.
            	calcularProducciones(result);
        }
        
        return result;
        
    }
    public void recalcularLactancia(Animal ani,EvtLactancia evLa) throws ExcepcionIntegridad{
    	EvtAnimal evFin = evLa.getEventoFinalizaLactancia();
    	EvtLactancia lacti =null;
		Session session = HibernateFactory.getSession();
		
		//if (evtLactancia != null) { 
				SortedSet even = evFin.getDependientes();
				ArrayList arrayList2 =null;
				if(!even.isEmpty()){
					arrayList2 = new ArrayList(ani.getEvtAnimals());
				
				for(Object lact : even){
					if(lact.getClass()==EvtLactancia.class){
						lacti = (EvtLactancia)lact;
						if(arrayList2.contains(lacti))//esto lo hago pq en dependientes puede quedar una lactancia q falta q hibernate la elimine y el arrayList2 ya no esta
							break;
						}
					}
				}
				if(lacti!=null && arrayList2.contains(lacti)){
					//this.getEvtAnimals().remove(ev);
					//arrayList2.remove(evFin);
					//ev.setDependientes(new TreeSet(new EventosPorFechaYTipo()));
					
					
					
					//ArrayList arrayList = new ArrayList(this.getEvtAnimals());
					//arrayList.remove(lacti);
					arrayList2.remove(lacti);
					//this.getEvtAnimals().remove(lacti);
					ani.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
					//this.getEvtAnimals().addAll(arrayList);
					ani.getEvtAnimals().addAll(arrayList2);
					//this.getEvtAnimals().add(ev);
					
					if (lacti.isEsOficial()&&(AnimalesLactanciasOficiales.getAnimales()!=null))
		        		AnimalesLactanciasOficiales.getAnimales().remove(ani);
					if(lacti.getId()!=null)
						lacti.ejecutarBaja();
					else
						evFin.getDependientes().remove(lacti);
					AnimalDAO.updateAnimal(ani);
					}
		//	}
			if(evFin.isFinalizaLactancia() ){
				EvtLactancia evtLactancia = EvtLactancia.calcularLactanciaCerrada(ani, evFin);
				if(evtLactancia!=null){
					
					ArrayList arrayList = new ArrayList(ani.getEvtAnimals());
					ani.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
					arrayList.add(evtLactancia);
					ani.getEvtAnimals().addAll(arrayList);
					//this.getEvtAnimals().add(evtLactancia);
					evFin.addDependiente(evtLactancia);
					try {
						session.save(evtLactancia);
						//session.flush();
						
					} catch (HibernateException e) {
						throw new ErrorFatal(
							"Error capa persistencia al agregar lactancia cerrada : "
									+ e);
					}
				}
			}
    }
    public EvtLactancia recalcularLactanciaCerrada() {
        EvtLactancia result = null;
        List evsProd = new ArrayList();
       
        EvtAnimal evFin = getEventoFinalizaLactancia();
        EvtAnimal evIni = buscarIniLactAcumProdsDesde(this.getAnimal(), evFin, evsProd);
        if (evIni != null) {
        	Date fechaFin = null;
        	if (evFin.getNombreTipo().equals(Evento.EVT_TIPO_REP))
        		//fechaFin = evFin.getFecha();
        		fechaFin = DateUtils.menos(evFin.getFecha(), 1);
        	else
        		fechaFin = evFin.getFecha();
            result = CalcularLactancia(this.getAnimal(), evIni, fechaFin/*, evsProd*/, evFin,
                    true, 9999);
            calcularProducciones(result);
            //HibernateFactory.getSession().update(this);
           // HibernateFactory.getSession().flush();
            
        }
        
        return result;
        
    }

    /**
     * Calcula la ultima lactancia de un animal, que es abierta si existe al no
     * indicar un evento de finalizacion (si hay evento de finalización, se
     * debería llamar a <code>calcularLactanciaCerrada</code>). La idea es
     * que cuando se piden todos los eventos de una hembra, se llame a este
     * codigo que calcula un Lactancia que no es persistida hasta que se cierre.
     * Para el cálculo se toma al ultimo evento producción como el evento de
     * finalización, y la fecha será la de ese evento mas un dia.
     * 
     * @return un <code>EvtLactancia</code> abierta sin persistir
     * 
     */
    public static EvtLactancia calcularUltimaLactanciaAbierta(Animal animal) {
        EvtLactancia result = null;
        List evsProd = new ArrayList();
        EvtAnimal evIni = buscarIniLactAcumProdsDesde(animal, null, evsProd);
        if (!(evIni == null || evsProd.size() == 0)) {
            EvtAnimal evFin = (EvtAnimal) evsProd.get(0);
            Date fechaFin = DateUtils.mas(evFin.getFecha(), 1);
            result = CalcularLactancia(animal, evIni, fechaFin/*, evsProd*/, evFin,
                    false, 9999);
            if (result != null)
            	calcularProducciones(result);
        }
        return result;
    }

    /**
     * @param animal
     * @param inicio
     * @param evsProd
     * @return
     */
    @SuppressWarnings("unchecked")
	private static EvtAnimal buscarIniLactAcumProdsDesde(Animal animal,
            EvtAnimal evFin, List evsProd) {
    	
        EvtAnimal evIni = null;
        List<Object> listaEventos = new ArrayList<Object>();
        listaEventos.addAll(animal.getEvtAnimals());
        if(listaEventos.isEmpty())
        	return null;
        Collections.sort(listaEventos, new EventosPorFechaYTipo());
        int desde = -1;
        if (evFin == null){//este if es para el caso de las lactancias en curso
            desde = listaEventos.size();
            if(desde!=-1){
            	boolean esFinalizada = false;
        		for (ListIterator iter = listaEventos.listIterator(desde); iter
                	.hasPrevious()
                	&& evIni == null && !esFinalizada;) {
        			EvtAnimal evtAnimal = (EvtAnimal) iter.previous();
        			if (evtAnimal.getClass() == EvtControlAnimal.class) {
        				evsProd.add(evtAnimal); 
        			}
        			if (evtAnimal.isIniciaLactancia()) {
        				evIni = evtAnimal;
        			}
        			if (evtAnimal.isFinalizaLactancia()){
        				esFinalizada = true;
        			}
        		}
        	}
        }
        else {//este if es para el caso de las lactancias finalizadas
            	desde = listaEventos.indexOf(evFin);
            	if(desde!=-1){
            		for (ListIterator iter = listaEventos.listIterator(desde); iter
	                	.hasPrevious()
	                	&& evIni == null;) {
            			EvtAnimal evtAnimal = (EvtAnimal) iter.previous();
            			if (evtAnimal.getClass() == EvtControlAnimal.class) {
            				evsProd.add(evtAnimal); 
            			}
            			if (evtAnimal.isIniciaLactancia()) {
            				evIni = evtAnimal;
            			}
            		}
            	}
        }
        return evIni;
       
    }

    /**
     * Calcula y retorna un <code>EvtLactancia</code> dados todos los
     * parámetros que el llamador calculó, en particular el <code>evIni</code>
     * y el <code>evFin</code> y todos los eventos producción
     * <code>evsProd</code>. Nótese que la <code>fechaFin</code> no es
     * <code>evFin.getFecha()</code> ya que puede ser una lactancia abierta,
     * donde el evento de fin es la ultima producción y la fecha de fin es esa
     * producción más un día.
     * 
     * @param animal
     * @param evIni
     * @param fechaFin
     * @param evsProd
     * @param evFin
     * @param esCerrada
     * @return
     * @throws ParseException 
     */
    @SuppressWarnings("unchecked")
	private static EvtLactancia CalcularLactancia(Animal animal,
            EvtAnimal evIni, Date fechaFin/*, List evsControlAnimal*/, EvtAnimal evFin,
            boolean esCerrada, Integer aXDias) {
    	List evsControlAnimal = animal.getEventosEntre(DateUtils.mas(evIni.getFecha(),1), fechaFin, Evento.EVT_TIPO_CONTROL_ANIMAL);
        Collections.sort(evsControlAnimal, new EventosPorFechaYTipo());
        EvtLactancia result = new EvtLactancia();
        result.setProduccionesLactancia(new HashMap());
        Map medicionesLac = new HashMap();
        medicionesLac.put(objetoDeMedicion_Leche, 0f);
        // del periodo 1 al n-1, indice 0 a size-1
        EvtAnimal evAnt = evIni;
        EvtControlAnimal evt = null;
       /* if(evsControlAnimal.size()== 0)
        	return null;*/
        int i, diasEntre = 0, diasAcumulados = 0;        
        for (i = 0; i < evsControlAnimal.size(); i++) {
            evt = (EvtControlAnimal) evsControlAnimal.get(i);
            /*if(evAnt.equals(evIni))
            	diasEntre = DateUtils.diasEntre(DateUtils.menos(evAnt.getFecha(),1), evt.getFecha());
            	//si es el primer control se debe tomar desde el dia q inicia lactancia hasta el primer control
            	//de la otra manera toma desde el dia siguiente hasta el control
            else*/
            //if(evAnt!=null && evt!=null){
            	diasEntre = DateUtils.diasEntre(evAnt.getFecha(), evt.getFecha());
            	
            	//if(evAnt.)
            		//diasEntre = DateUtils.diasEntre(evAnt.getFecha(), evt.getFecha());
            	if((diasAcumulados+diasEntre) <= aXDias) {
	            	AcumularPeriodo(medicionesLac, i, evsControlAnimal, diasEntre);
	            	diasAcumulados += diasEntre;
	                if(!evt.isCalostro())//si no es calostro avanza normamente para la cantidad de dias, 
	                					//en cambio sies calostro evAnt debe seguir siendo el parto para 
	                					//poder tomar los dias correctos--->   parto - control(calostro) - control ---> dias entre controles tiene que ser entre el parto y el 2ºcontrol
	                	evAnt = evt;
	                
	            } else break;  
            //}
        
        }
        if(i!=evsControlAnimal.size()) {
        	AcumularPeriodo(medicionesLac, i, evsControlAnimal, aXDias-diasAcumulados);
        } else 
        	if(evt!=null){
	        	if ((diasAcumulados+DateUtils.diasEntre(evt.getFecha(), fechaFin)) > aXDias) {
	        		AcumularPeriodo(medicionesLac, evsControlAnimal.size(), evsControlAnimal, aXDias-diasAcumulados);
		        } else {
		        	AcumularPeriodo(medicionesLac, evsControlAnimal.size(), evsControlAnimal, DateUtils
		                    .diasEntre(evt.getFecha(), fechaFin));
	        }
        	/*try {
				AcumularPeriodo(medicionesLac, evsControlAnimal.size(), evsControlAnimal, DateUtils.diasEntre(DateUtils.parse(DateUtils.format(evt.getFecha(), "dd/MM/yyyy"),"dd/MM/yyyy"),DateUtils.parse(DateUtils.format(fechaFin, "dd/MM/yyyy"),"dd/MM/yyyy")));
			} catch (ParseException e) {
				AcumularPeriodo(medicionesLac, evsControlAnimal.size(), evsControlAnimal, DateUtils.diasEntre(evt.getFecha(), fechaFin));
				e.printStackTrace();
			}*/
        	//DateUtils.diasEntre(DateUtils.parse(DateUtils.format(evt.getFecha(), "dd/MM/yyyy"),"dd/MM/yyyy"),DateUtils.parse(DateUtils.format(fechaFin, "dd/MM/yyyy"),"dd/MM/yyyy"));
        }
        
        
        
        /*
        for (int i = 0; i < evsProd.size(); i++) {
            evt = (EvtControlAnimal) evsProd.get(i);
            int periodo = DateUtils.diasEntre(evAnt.getFecha(), evt.getFecha());
            AcumularPeriodo(medicionesLac, i, evsProd, periodo);
            evAnt = evt;
        }
        
        // ultimo periodo, numero=n, indice=size
        AcumularPeriodo(medicionesLac, evsProd.size(), evsProd, DateUtils
                .diasEntre(evt.getFecha(), fechaFin) - 1);
                */
        // para los objetos de medicion porcentual, el valor porcentual se
        // calcula al final, habiendose acumulado totales absolutos
        // (multiplicados por la leche) en cada periodo
        CalcularObjetosPorcentuales(medicionesLac);
        // preparar al evento para dejarlo en el animal
        result.setMediciones(medicionesLac);
       	result.setFecha(fechaFin);
       // result.setEstablecimiento(animal.getEstablecimiento());
        result.setEstablecimiento((evFin!=null)?((evFin.getEstablecimiento()!=null)?evFin.getEstablecimiento():animal.getEstablecimiento()):animal.getEstablecimiento());
        result.setAnimal(animal);
        result.setFinalizaLactancia(false);
        result.setIniciaLactancia(false);
        result.setDias(new Integer(DateUtils.diasEntre(evIni.getFecha(), fechaFin)));
       //result.setDias(new Integer(DateUtils.diasEntre(DateUtils.menos(evIni.getFecha(),1), fechaFin)));
        result.setEsCerrada(esCerrada);
        result.setFechaInicio(evIni.getFechaLactancia());
        if(evsControlAnimal.size()!= 0){
	        EvtControlAnimal ultimaProd = (EvtControlAnimal)evsControlAnimal.get(evsControlAnimal.size()-1);
	        result.setOrdenies(ultimaProd.getControlEstablecimiento().getNumOrdenies());
        }
        else
        	result.setOrdenies(null);
        
        if (evFin!=null)
        	result.setEstadoAnimal(evFin.getEstadoAnimal());
        
        result.setCategoria(calcularCategoria(result, animal, evIni, evsControlAnimal,
                esCerrada, fechaFin));
        
        //Se le setea el nro de lactancia como el anterior mas 1, si no hay lactancia anterior, entonces es
        //la primer lactancia
        int nroInformado = 1;
		
		if(evIni instanceof EvtEstado) {
			EvtEstado evEst = (EvtEstado)evIni;
        	nroInformado  =evEst.getNumeroLactancia();
		}else{
			if(evIni instanceof EvtReproduccion){
				EvtReproduccion evEst = (EvtReproduccion)evIni;
				nroInformado = evEst.getNroLactancia();
			}
			else{
				if(evt instanceof HibernateProxy){
					if(EvtReproduccion.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(evt))){
						EvtReproduccion evEst = (EvtReproduccion)evIni;
						nroInformado = evEst.getNroLactancia();
					}
					if(EvtEstado.class.equals(HibernateProxyHelper.getClassWithoutInitializingProxy(evt))){
						EvtEstado evEst = (EvtEstado)evIni;
						nroInformado  =evEst.getNumeroLactancia();
					}
				}
			}
		}
       /* if((evIni.getNombreTipo().equals(Evento.EVT_TIPO_EST))){
        	EvtEstado evEst = (EvtEstado)evIni;
        	nroInformado  =evEst.getNumeroLactancia();
        }
        if((evIni.getNombreTipo().equals(Evento.EVT_TIPO_REP))){
        	EvtReproduccion evEst = (EvtReproduccion)evIni;
        	nroInformado = evEst.getNroLactancia();
        }*/
        result.setNroLact(nroInformado);
        //comento el codigo de abajo ya que el numero informado en la lactancia es el que se debe almacenar
        /*List lactanciasAnteriores = ((Hembra)animal).getLactancias();
        if (lactanciasAnteriores.isEmpty())
        	result.setNroLact(1);
        else {
        	Lactancia lac = (Lactancia)lactanciasAnteriores.get(lactanciasAnteriores.size() - 1);        	
        	if (lac.getNroLact() != null)   //por las dudas de que haya lactancias sin nroLactancia
        		result.setNroLact(lac.getNroLact() + 1);
        	else //si no tenemos informacion, hago de adivino y le pongo la #de lactancias anteriores + 1
        		//(si se necesita mas porque este caso ocurre, se puede hacer esto mas inteligente, buscando para atras, etc)
        		result.setNroLact(lactanciasAnteriores.size() +1 );
        }   
        */
        return result;
    }

    private static void calcularProducciones(EvtLactancia result) {
    	ProduccionLactancia real = new ProduccionLactancia();
    	real.setLeche(result.getLeche());
    	real.setCelulas(0f);
    	real.setPorcentajeGrasa(result.getPorcentajeGrasa());
    	real.setPorcentajeProteinas(result.getPorcentajeProteinas());
    	real.setPorcentajeSolidosTotales(result.getPorcentajeSolidosTotales());
    	result.addProduccionLactancia("REAL",real);
    	
    	if(result.getDias()>305) {
    		Lactancia l305 = result.getLactancia(305);
    		ProduccionLactancia p305 = new ProduccionLactancia();
    		p305.setLeche(l305.getLeche());
    		p305.setCelulas(0f);
    		p305.setPorcentajeGrasa(l305.getPorcentajeGrasa());
    		p305.setPorcentajeProteinas(l305.getPorcentajeProteinas());
    		p305.setPorcentajeSolidosTotales(l305.getPorcentajeSolidosTotales());
    		result.addProduccionLactancia("305",p305);
    	}

    	if(result.getDias()>365) {
    		Lactancia l365 = result.getLactancia(365);
    		ProduccionLactancia p365 = new ProduccionLactancia();
    		p365.setLeche(l365.getLeche());
    		p365.setCelulas(0f);
    		p365.setPorcentajeGrasa(l365.getPorcentajeGrasa());
    		p365.setPorcentajeProteinas(l365.getPorcentajeProteinas());
    		p365.setPorcentajeSolidosTotales(l365.getPorcentajeSolidosTotales());
    		result.addProduccionLactancia("365",p365);
    	}
    	
    	if((result.getDias()>270) && (result.getAnimal().getRaza().getEspecie().getId().equals("BUFA"))) {
    		Lactancia l270 = result.getLactancia(270);
    		ProduccionLactancia p270 = new ProduccionLactancia();
    		p270.setLeche(l270.getLeche());
    		p270.setCelulas(0f);
    		p270.setPorcentajeGrasa(l270.getPorcentajeGrasa());
    		p270.setPorcentajeProteinas(l270.getPorcentajeProteinas());
    		p270.setPorcentajeSolidosTotales(l270.getPorcentajeSolidosTotales());
    		result.addProduccionLactancia("270",p270);
    	}
    	
		
	}

	private static void AcumularPeriodo(Map medicionesLac, int periodo,
            List evsProd, int diasperiodo) {
        // ("periodo " + (periodo + 1) + " longitud = " +
        // diasperiodo + "dias. Se suman = ");
        EvtControlAnimal evt = null;
        // el ultimo periodo no tiene evento, ya q va de ultimo prod a evFin
        if (periodo < evsProd.size())
            evt = (EvtControlAnimal) evsProd.get(periodo);
        else
            evt = (EvtControlAnimal) evsProd.get(evsProd.size() - 1);
        Map medicionesProd = evt.getMediciones();
        // TODO regla en evprod q no puede faltar la leche
        acumularObjeto(evsProd, evt, medicionesLac, objetoDeMedicion_Leche,
                tipoDeMedicion_Absoluta, periodo, diasperiodo);
        for (Iterator iter_meds = medicionesProd.keySet().iterator(); iter_meds
                .hasNext();) {
            String objetoDeLaMedicion = (String) iter_meds.next();
            if (objetoDeLaMedicion.compareTo(objetoDeMedicion_Leche) != 0)
                acumularObjeto(evsProd, evt, medicionesLac, objetoDeLaMedicion,
                        tipoDeMedicion_Porcentual, periodo, diasperiodo);
        }
        // System.out.println(".");

    }

    @SuppressWarnings({"unchecked","unchecked"})
	private static void acumularObjeto(List evsProd, EvtControlAnimal evt,
            Map medicionesLac, String objetoDeLaMedicion,
            String tipoDeMedicion, int periodo, int dias) {
        if(!evt.isCalostro()){
	        int periodoAjustadoUltimo = (periodo == evsProd.size() ? evsProd.size() - 1
	                : periodo);
	        
	        Map medicionesProd = evt.getMediciones();
	        // debido a que si es porcentual se debe acumular multiplicando por el
	        // valor de leche
	        // se pone 1 a valor leche para cdo se calcula absoluto
	        Float valorLeche = 0.0f;
	        if (tipoDeMedicion == tipoDeMedicion_Absoluta)
	            valorLeche = 1.0f;
	        if (tipoDeMedicion == tipoDeMedicion_Porcentual )
	            valorLeche = ((Float) ((EvtControlAnimal) evsProd.get(periodoAjustadoUltimo)).getMediciones().get(
	                    objetoDeMedicion_Leche)) / 100;
	        Float valorProd = (Float) medicionesProd.get(objetoDeLaMedicion);
	        valorProd = (valorLeche * valorProd);
	        if (periodo == 0 || periodo == evsProd.size()) // i=1 o i=n
	        {
	            valorProd = valorProd * dias;
	        }
	        if (1 <= periodo && periodo < evsProd.size()) // 1<i<n
	        {
	            EvtControlAnimal evAnte = (EvtControlAnimal) evsProd.get(periodo-1);
	        	if(evAnte.isCalostro())
	        		 valorProd = valorProd * dias;
	        	else{
		            //Float valorProdAnt = (Float) ((EvtControlAnimal) evsProd.get(periodo - 1)).getMediciones().get(objetoDeLaMedicion);
		            Float valorProdAnt = (Float) evAnte.getMediciones().get(objetoDeLaMedicion);
		            if(valorProdAnt==null)
		            	valorProdAnt = 0.0f;
		            Float valorLecheAnt = 0.0f;
		            if (tipoDeMedicion == tipoDeMedicion_Absoluta)
		                valorLecheAnt = 1.0f;
		            if (tipoDeMedicion == tipoDeMedicion_Porcentual)
		                valorLecheAnt = ((Float) ((EvtControlAnimal) evsProd
		                        .get(periodo - 1)).getMediciones().get(
		                        objetoDeMedicion_Leche)) / 100;
		            valorProdAnt = (valorLecheAnt * valorProdAnt);
		            Float valorProdProm = (valorProd + valorProdAnt) / 2;
		            valorProd = valorProdProm * dias;
	        	}
	        }
	        if (medicionesLac.containsKey(objetoDeLaMedicion)) {
	            Float valorLac = (Float) medicionesLac.get(objetoDeLaMedicion);
	            medicionesLac.put(objetoDeLaMedicion, valorLac + valorProd);
	        } else {
	            medicionesLac.put(objetoDeLaMedicion, valorProd);
	        }
	    }
        // System.out.print(" " + objetoDeLaMedicion + " = " + valorProd);    }
    @SuppressWarnings("unchecked")
    private static void CalcularObjetosPorcentuales(Map medicionesLac) {
        for (Iterator iter_meds = medicionesLac.keySet().iterator(); iter_meds
                .hasNext();) {
            String objetoDeLaMedicion = (String) iter_meds.next();
            if ((objetoDeLaMedicion.compareTo(objetoDeMedicion_Leche) != 0)&&(!((Float) medicionesLac.get(objetoDeMedicion_Leche)).equals(new Float(0.0)))) {
                // el valor para ese objeto es en relacion a la leche
                medicionesLac.put(objetoDeLaMedicion, ((Float) medicionesLac
                        .get(objetoDeLaMedicion) * 100f)
                        / ((Float) medicionesLac.get(objetoDeMedicion_Leche)));
            }
        }
    }

    private static String calcularCategoria(EvtLactancia evtLactancia,
            Animal animal, EvtAnimal evIni, List evsProd, boolean esCerrada,
            Date fechaFin) {
    	
    	evtLactancia.setEsOficial(false); //si se ve que es oficial, luego se cambia
    	
        String categoria = "";
        String motivo = "";
        String mc = calcularMetodoControl(evIni, evsProd,esCerrada,fechaFin,evtLactancia.getEstablecimiento().getMetodoControl().getCodigo());
        /*if (mc == null) {
        	evtLactancia.setControl(evtLactancia.getEstablecimiento().getMetodoControl().getCodigo().contains("SM")?
        			evtLactancia.getEstablecimiento().getMetodoControl().getCodigo():
        				evtLactancia.getEstablecimiento().getMetodoControl().getCodigo()+"SM");
            categoria = Categoria_No_Oficial;
            //motivo = Metodo_Error;
            motivo = Distancia_controles;
        } else {*/
        	evtLactancia.setControl(mc);  
            if (!esCerrada)
                categoria = Categoria_Abierta;
            float icmax = 0;
            float icmin = 0;
            float icmin4 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X4));
            float icmax6 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X6));
            float ncm = 0;
            if (mc.charAt(1) == '4') {
               // icmin = 22;
                //icmax = 37;
            	icmin = Integer.parseInt(animal.getRaza().getParametro("Dias minimos entre controles X4"));
    			icmax = Integer.parseInt(animal.getRaza().getParametro("Dias maximos entre controles X4"));
                ncm = 12;
            }
            if (mc.charAt(1) == '6') {
                //icmin = 38;
                //icmax = 53;
            	icmin = Integer.parseInt(animal.getRaza().getParametro("Dias minimos entre controles X6"));
    			icmax = Integer.parseInt(animal.getRaza().getParametro("Dias maximos entre controles X6"));
                ncm = 9;
            }
            float nc = evsProd.size();
            float ncl = evtLactancia.getDias() * ncm / 360;
            String controlIntervalos = ControlesTodosDentroMinMax(icmin4, icmax6,
                    evsProd, evIni.getFecha(), fechaFin);
            boolean controlesbien = controlIntervalos.compareTo("bien") == 0;
            String cantidadDeControles = null;
            if (nc < ncl * 0.85)
                cantidadDeControles = "< 85% de cant de ref = ";
            if (ncl * 0.85 <= nc && nc < ncl)
                cantidadDeControles = ">= a 85% de cant de ref = ";
            if (nc >= ncl)
                cantidadDeControles = ">= a cant de ref = ";
           /* if (controlesbien && (ncl * 0.85 <= nc && nc < ncl))
                categoria = categoria + Categoria_Oficial_Estimada;*/
            if (controlesbien && (ncl * 0.85 <= nc /*&& nc < ncl*/)){  //(nc >= ncl) {
            //se comento por que ahora aunque se informe un A4
            //if (ncl * 0.85 <= nc && nc < ncl){
                categoria = categoria + Categoria_Oficial;
                evtLactancia.setEsOficial(true);
            }
            if (!controlesbien || (nc < ncl * 0.85))
                categoria = categoria + Categoria_No_Oficial;
            motivo = "Oficialidad : cant de ctrls " + nc + cantidadDeControles
                    + ncl + "(" + evtLactancia.getDias() + "*" + ncm
                    + "/360) ; intervalos : " + controlIntervalos + " ) ";
        //}
        return categoria + ". Método=" + mc + "\n\t\t" + motivo;
    }

    /**
     * Verifica que los periodos en dias entre controles esten dentro de los
     * maximos y minimos pasados de parametro (que se relacionan con el metodo
     * de control). El primero y el ultimo periodo no se controlan contra el
     * minimo, pero si contra el maximo.
     * 
     * @param icmin
     *            intervalo entre controles minimo
     * @param icmax
     *            intervalo entre controles maximo
     * @param evsProd
     *            eventos produccion (controles de produccion)
     * @param ini
     *            fecha de inicio de la lactancia (para calcular primer periodo)
     * @param fin
     *            fecha de fin de la lactancia (para calcular ultimo periodo)
     * @return String vacio si todo bien, string con la descripcion del problema
     *         si algun periodo se fue de rango
     */
    private static String ControlesTodosDentroMinMax(float icmin, float icmax,
            List evsProd, Date ini, Date fin) {
//        String result = "";
        Date fAnt = ini;
      
        for (Iterator iter = evsProd.iterator(); iter.hasNext();) {
            EvtControlAnimal evtProduccion = (EvtControlAnimal) iter.next();
            int dias = DateUtils.diasEntre(fAnt, evtProduccion.getFecha());
            if (evsProd.indexOf(evtProduccion) == 0) {
                if (!(dias <= icmax)) {
                    return controlMal(icmin, icmax, fAnt, evtProduccion
                            .getFecha(), dias);
                }
            } else {
                if (!(icmin <= dias && dias <= icmax)) {
                    return controlMal(icmin, icmax, fAnt, evtProduccion
                            .getFecha(), dias);
                }
            }
            fAnt = evtProduccion.getFecha();
        }
        
        // el ultimo intervalo es desde el ultimo control al fin
        int dias = DateUtils.diasEntre(fAnt, fin);
        if (!(dias <= icmax)) {
            return controlMal(icmin, icmax, fAnt, fin, dias);
        }
        return "bien";
    }

    private static String controlMal(float icmin, float icmax, Date ini,
            Date fin, int dias) {
        String result = "control " + String.format("%tF", new Object[] { ini })
                + " al " + String.format("%tF", new Object[] { fin })
                + " tiene " + dias + " dias [" + icmin + ".." + icmax + "]";
        return result;
    }

    /**
     * 
     * Calcula el metodo de control que le corresponde a la lactancia de acuerdo
     * al que está en los controles. Si no coincide con el del establecimiento,
     * no se trata el problema aquí. Se supone que fue revisado en el alta del
     * evento producción, y allí se define si es err o warning que el metodo del
     * control (evprod) y el del estab no coincidan.
     * 
     * si es el primer control y no tiene mas, y el control es antes de los dias minimos 
     * para el primer control o calostro, el metodo debe ser el del tambo + SM, en cambio si es el primero pero supera los 
     * dias minimos primer control pero no alcanza para que sea un X4, entonces es el metodo de tambo + calculo y si cae en periodos validos
     * se calcula normalmente.
     * se pregunta si es primer control, 
     * si lo es:
     * si es antes de los dias minimos o calostro se carga metodoPrimerControl 
     * 
     * @param evIni
     * @param evFin
     * @param evsProd
     * @return
     */
private static String calcularMetodoControl(EvtAnimal evIni, List evsProd, boolean esCerrada,Date fechaFin,String metodoTambo){
    	
    	int icmin4 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X4));
    	int icmin6 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X6));
    	int icmax4 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X4));
    	int icmax6 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X6));

    	String metodoControl = null; 
    	String metodoPrimerControl = null;
    	 
    	String letraMetodo = null;
    	Date fAnt = evIni.getFecha();  
    	
    	String met ="";
        for (Iterator iter = evsProd.iterator(); iter.hasNext();) {
            EvtControlAnimal evtProduccion = (EvtControlAnimal) iter.next();
		            int dias = DateUtils.diasEntre(fAnt, evtProduccion.getFecha());
		            if (evsProd.indexOf(evtProduccion) == 0) {
		            	int minInicCon = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_PRIMER_CONTROL));
		            	if(dias<minInicCon || evtProduccion.isCalostro() )
		            		metodoPrimerControl = metodoTambo.contains("SM")?	metodoTambo:metodoTambo+"SM";
		            	else if (dias <= icmin4) {
		            		letraMetodo = String.valueOf(metodoTambo.charAt(0));
		                	letraMetodo += String.valueOf(metodoTambo.charAt(1));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		//metodoControl = letraMetodo.concat(met);
		            		metodoPrimerControl = letraMetodo.concat(met);
		                }
		            	else if (dias <= icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		                }else {// seria X6
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            		//break;
		                }
		            } else{
		            	
		            	if((metodoControl!=null && metodoControl.contains("6"))
		            			||(dias>=icmin6)){//si el primer control entro en buen lugar y se cargo como 6 mantengo el 6 y hago el calculo
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            	}
		            	else if (dias <= icmin4) {//mantengo el anterior y calculo
		            		if(metodoControl == null){
		            			letraMetodo = String.valueOf(metodoTambo.charAt(0));
		            			letraMetodo += String.valueOf(metodoTambo.charAt(1));
		            		}
		            		else{
		            			letraMetodo = String.valueOf(metodoControl.charAt(0));
			                	letraMetodo += String.valueOf(metodoControl.charAt(1));
		            		}
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		//metodoControl = letraMetodo.concat(met);
		            		metodoControl = letraMetodo.concat(met);
		                }
		            	else if (dias <= icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		            	
		            	}
		            }
		            	
		            	
		            	/*{
		                if (dias>=icmin4 && dias<=icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		               // }else if (dias>=icmin6 && dias<=icmax6) {
		                }else if (dias>=icmin6) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            		break;
		                }else{
		                	metodoControl = null;
		                	break;
		                }
		            }*/
            
            fAnt = evtProduccion.getFecha();
        }
        
        // el ultimo intervalo es desde el ultimo control al fin
        ////////////mmmmmmmmmmm Daniel dijo qeu habia que sacarlo10/06
       /* if(esCerrada){
        	int dias = DateUtils.diasEntre(fAnt, fechaFin);
        	if ((dias<4)||(dias>icmax4)||(dias>icmax6))
        		metodoControl = Metodo_Error;
        }	*/
        if(metodoControl == null){
        	if(metodoPrimerControl ==null)
        		return metodoTambo;
        	return metodoPrimerControl;
        }
    	return metodoControl;
    }

    private static String calcularMetodoControl2(EvtAnimal evIni, List evsProd, boolean esCerrada,Date fechaFin,String metodoTambo){
    	
    	int icmin4 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X4));
    	int icmin6 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_ENTRE_CONTROLES_X6));
    	int icmax4 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X4));
    	int icmax6 = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MAXIMOS_ENTRE_CONTROLES_X6));

    	String metodoControl = null;
    	String letraMetodo = null;
    	Date fAnt = evIni.getFecha();
    	String met ="";
        for (Iterator iter = evsProd.iterator(); iter.hasNext();) {
            EvtControlAnimal evtProduccion = (EvtControlAnimal) iter.next();
            if(!evtProduccion.isCalostro()){
		            int dias = DateUtils.diasEntre(fAnt, evtProduccion.getFecha());
		            if (evsProd.indexOf(evtProduccion) == 0) {
		            	int minInicCon = Integer.parseInt(evIni.getAnimal().getRaza().getParametro(Raza.DIAS_MINIMOS_PRIMER_CONTROL));
		            	if(dias<minInicCon){
		            		return null;
		            		//metodoControl = null;//lo da en null y luego cuando sale se le pone el del establecimiento
		            		
		            		//metodoControl = metodoTambo.contains("SM")?	metodoTambo:metodoTambo+"SM";
		            		//break;
		            	}else if (dias <= icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		                }else if (dias <= icmax6) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            		//break;
		                }else{
		                	letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		                	letraMetodo += String.valueOf(evtProduccion.getMetodoControl().charAt(1));
		                	met = calcularConComponente(evtProduccion);
		                	metodoControl = letraMetodo+met;
		                	if(letraMetodo.contains("6"))
		                		break;
		                }	             
		            } else
		            	if (dias>=icmin6) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            		break;
		                }
		            	if (dias<=icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		            	}
		            	/*{
		                if (dias>=icmin4 && dias<=icmax4) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("4").concat(met);
		               // }else if (dias>=icmin6 && dias<=icmax6) {
		                }else if (dias>=icmin6) {
		            		letraMetodo = String.valueOf(evtProduccion.getMetodoControl().charAt(0));
		            		if(!met.equals("SM"))
		            			met = calcularConComponente(evtProduccion);
		            		metodoControl = letraMetodo.concat("6").concat(met);
		            		break;
		                }else{
		                	metodoControl = null;
		                	break;
		                }
		            }*/
            }
            fAnt = evtProduccion.getFecha();
        }
        
        // el ultimo intervalo es desde el ultimo control al fin
        ////////////mmmmmmmmmmm Daniel dijo qeu habia que sacarlo10/06
       /* if(esCerrada){
        	int dias = DateUtils.diasEntre(fAnt, fechaFin);
        	if ((dias<4)||(dias>icmax4)||(dias>icmax6))
        		metodoControl = Metodo_Error;
        }	*/

    	return metodoControl;
    }
    
    /**
     * Realiza el cálculo para determinar si los ordeñes de una producción del animal poseen componentes de análisis
     * o solamente poseen componente leche
     * @param evtProduccion
     * @return String
     */
    private static String calcularConComponente(EvtControlAnimal evtProduccion){
    	String mc="SM";
    	Iterator it_ordenies = evtProduccion.getOrdeniesAnimal().iterator();
    	if(evtProduccion.getTipoMuestreo().equals("DOAN")){
    		int cantCM =0;
    		while(it_ordenies.hasNext()){
        		EvtOrdenieAnimal evtOrd = (EvtOrdenieAnimal)it_ordenies.next();
        		if(evtOrd.tieneAnalisis())
        			cantCM ++;
        	}
    		if(cantCM>=2)
    			mc="";
    	}
    	else{
	    	while(it_ordenies.hasNext()){
	    		EvtOrdenieAnimal evtOrd = (EvtOrdenieAnimal)it_ordenies.next();
	    		if(evtOrd.tieneAnalisis())//{
	    			mc="";
	    	}
	    }
    	return mc;
    }

	public Float getGrasaAbsoluto() {
		if ((getLeche() != null) && (getPorcentajeGrasa() != null))
			return getPorcentajeGrasa() * getLeche() / 100;
		else
			return null;
	}

	public Float getLeche() {
		return (Float)getMediciones().get(objetoDeMedicion_Leche);
	}

	

	public Float getProteinasAbsoluto() {
		if ((getLeche() != null) && (getPorcentajeProteinas() != null))
			return getPorcentajeProteinas() * getLeche() / 100;
		else
			return null;
		
	}

	public Integer getAnios() {
		int nroDias = this.getAnimal().getEdadEnDiasAl(getFechaInicio());
		return nroDias / 365;
	}
    
	public Integer getMeses() {
		int nroDias = this.getAnimal().getEdadEnDiasAl(getFechaInicio());
		int meses = ((nroDias) % 365) / 30;
		if (meses == 12) //son 4 o 5 dias...
			return 11;
		return meses;
	}
	
	public Float getPorcentajeGrasa() {
		return (Float)getMediciones().get(STProdMedObj.GR.toString());
	}

	public Float getPorcentajeProteinas() {
		return (Float)getMediciones().get(STProdMedObj.PR.toString());
	}
	
	public Float getPorcentajeSolidosTotales() {
		return (Float)getMediciones().get(STProdMedObj.ST.toString());
	}
	
	@SuppressWarnings("static-access")
	public EvtLactancia getLactancia(Integer dias) {
		Date fechaInicio = DateUtils.menos(this.getFechaInicio(),1);
		Date fechaFin = DateUtils.mas(this.getFechaInicio(), this.getDias());
		
		List evtsControlAnimal = ((Hembra)this.getAnimal()).getEventosEntre(fechaInicio, fechaFin, Evento.EVT_TIPO_CONTROL_ANIMAL);
		
		//Iterator iterator_evtsInicioLactancia = ((Hembra)this.getAnimal()).getEventosEntre(fechaInicio, fechaInicio).iterator();
		/*Iterator iterator_evtsInicioLactancia = ((Hembra)this.getAnimal()).getEventosEntre(fechaInicio, DateUtils.mas(fechaInicio,1)).iterator();
		EvtAnimal evtIniciaLactancia = null;
		while(iterator_evtsInicioLactancia.hasNext()) {
			evtIniciaLactancia = (EvtAnimal) iterator_evtsInicioLactancia.next();
			if (evtIniciaLactancia.isIniciaLactancia())
				break;
		}*/
		EvtLactancia evtLactanciaAXDias = null;
		//EvtAnimal evtIniciaLactancia = ((Hembra)this.getAnimal()).getEventoEnFecha(fechaInicio, Evento.EVT_TIPO_REP);
		EvtAnimal evtIniciaLactancia = this.getEventoIniciaLactancia();
		if(evtIniciaLactancia!=null){
		evtLactanciaAXDias = this.CalcularLactancia(this.getAnimal(), 
				evtIniciaLactancia, fechaFin/*, evtsControlAnimal*/, null, false, dias);
		}		
		return evtLactanciaAXDias;		
	}
	/**
	 * Metodo que retorna el evento que inicia la lactancia, puede ser un evento reproduccion o un evento estado
	 * como la lactancia inicia el dia despues al evento se pide el evento en la fecha de inicio menos un dia.
	 * @return
	 */
	public EvtAnimal getEventoIniciaLactancia(){
		Date fechaInicio = DateUtils.menos(this.getFechaInicio(),1);
		EvtAnimal evtIniciaLactancia = ((Hembra)this.getAnimal()).getEventoEnFecha(fechaInicio, Evento.EVT_TIPO_REP);
		if(evtIniciaLactancia == null)
			evtIniciaLactancia = ((Hembra)this.getAnimal()).getEventoEnFecha(fechaInicio, Evento.EVT_TIPO_EST);
		if(evtIniciaLactancia== null){
			 evtIniciaLactancia = ((Hembra)this.getAnimal()).getEventoEnFecha(this.getFechaInicio(), Evento.EVT_TIPO_REP);
			if(evtIniciaLactancia == null)
				evtIniciaLactancia = ((Hembra)this.getAnimal()).getEventoEnFecha(this.getFechaInicio(), Evento.EVT_TIPO_EST);
			
			
		}
		return evtIniciaLactancia;
		
	}
	/**
	 * Retorna el evento q finalizo lactancia, la finalizacion de la lactancia se da por un secada, un parto o una baja
	 * @return
	 */
	public EvtAnimal getEventoFinalizaLactancia(){
		
		List tiposEventos = new ArrayList();
		tiposEventos.add(Evento.EVT_TIPO_SEC);
		tiposEventos.add(Evento.EVT_TIPO_REP);
		EvtAnimal evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(this.getFecha(), Evento.EVT_TIPO_SEC);
		if((evtFinalizaLactancia == null)||
				((evtFinalizaLactancia != null) 
						&& !(evtFinalizaLactancia.getDependientes().contains(this)))){
			//evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(fechaInicio, Evento.EVT_TIPO_BAJ);
			evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(this.getFecha(), Evento.EVT_TIPO_BAJ);
			if((evtFinalizaLactancia == null)||((evtFinalizaLactancia != null) && !(evtFinalizaLactancia.getDependientes().contains(this))))
				//evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(fechaInicio, Evento.EVT_TIPO_REP);
				//la lactancia finaliza un dia antes del parto
				//evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(DateUtils.mas(this.getFecha(), 1), Evento.EVT_TIPO_REP);		
				
				evtFinalizaLactancia = this.getAnimal().getEventoEnFecha(DateUtils.mas(this.getFecha(), 1), tiposEventos);
			if(((evtFinalizaLactancia != null) 
					&& !(evtFinalizaLactancia.getDependientes().contains(this))))
					return null;
		}
		if(evtFinalizaLactancia == null)
			evtFinalizaLactancia = (EvtAnimal) EventoDAO.findEventoIdEventoDeDependiente(this.getAnimal(),this);
			
		
		
		return evtFinalizaLactancia;
		
	}
	public Float getSolidosTotalesAbsoluto() {
		if ((getLeche() != null) && (getPorcentajeSolidosTotales() != null))
			return getPorcentajeSolidosTotales() * getLeche() / 100;
		else
			return null;
	}

	public IProduccionLactancia getLactanciaA(String nombre) {
		return (IProduccionLactancia) produccionesLactancia.get(nombre);
	}

	
	@SuppressWarnings("unchecked")
	protected void addProduccionLactancia(String nombre, ProduccionLactancia pro ) {
		produccionesLactancia.put(nombre,pro);
	}

	public String getCodigoInicio() {
		//el primer evento que inicia lactancia anterior a esta lactancia
		/*Iterator it = this.getAnimal().getEvtAnimals().iterator();
		EvtAnimal evtInicia = null;
		boolean pasoLactancia = false;
		while((evtInicia == null) && it.hasNext() && !pasoLactancia) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.equals(this))
				pasoLactancia = true;
			if (evt.isIniciaLactancia())
				evtInicia = evt;
		}
		if (evtInicia != null)
			return evtInicia.getNombreTipo();
		else  // no se encontro el evento que inicio esta lactancia
			return "N/D";*/
		EvtAnimal evtInicia = this.getEventoIniciaLactancia();
		if (evtInicia != null)
			return evtInicia.getNombreTipo();
		else  // no se encontro el evento que inicio esta lactancia
			return "N/D";
	}

	public String getCausaFin() {
		//el primer evento que finaliza lactancia posterior a esta lactancia
		/*Iterator it = this.getAnimal().getEvtAnimals().iterator();
		EvtAnimal evtFin = null;
		boolean pasoLactancia = false;
		while((evtFin == null) && it.hasNext()) {
			EvtAnimal evt = (EvtAnimal) it.next();
			if (evt.equals(this))
				pasoLactancia = true;
			if (evt.isFinalizaLactancia() && pasoLactancia)
				evtFin = evt;
		}
		if (evtFin != null)
			return evtFin.getNombreTipo();
		else  // no se encontro el evento que finalizo esta lactancia
			return "N/D";*/
		EvtAnimal evtFin = this.getEventoFinalizaLactancia();
		
		if (evtFin != null)
			return evtFin.getNombreTipo();
		else  // no se encontro el evento que finalizo esta lactancia
			return "N/D";
		

	}
		
	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		Session session = HibernateFactory.getSession();
		try {
			super.ejecutarBaja();
			boolean bandera = true;
			EvtAnimal finali =this.getEventoFinalizaLactancia();
			if(finali!=null){
				if(!finali.getDependientes().isEmpty())
					finali.getDependientes().remove(this);
			if(finali.getId()!=null)			
				session.update(finali);
			if(finali.getDependientes().isEmpty())
				this.getAnimal().borrarEventoAnimal(finali);
			}

			session.delete(this);
			//if(bandera)
			//	session.flush();
		} catch (HibernateException he){
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                    new String[] { String.valueOf(getId()), he.toString() });
            throw e;
		}
	}
	public void ejecutarBajaParticular() throws ExcepcionIntegridad {
		Session session = HibernateFactory.getSession();
		try {
			super.ejecutarBaja();
			boolean bandera = true;
			EvtAnimal finali =this.getEventoFinalizaLactancia();
			if(finali!=null){
				if(!finali.getDependientes().isEmpty()){
					if(finali.getDependientes().size()>1)

						bandera = false;
					finali.getDependientes().remove(this);
				}

			session.update(finali);
			if(finali.getDependientes().isEmpty())
				this.getAnimal().borrarEventoAnimal(finali);
			}
			session.delete(this);
			if(bandera)
				session.flush();
		} catch (HibernateException he){
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                    new String[] { String.valueOf(getId()), he.toString() });
            throw e;
		}
	}
	public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtLactancia))
			return false;
		EvtLactancia ev = (EvtLactancia)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    		else{
    			 Date fecha1 = this.getFecha();
 		        Date fecha2 = ev.getFecha();
 		       if(fecha1.equals(fecha2))
		        	return true;
    		}
    	}
		return false;
    	
    }
	public void recalcularLactanciaParticular(Animal ani,EvtLactancia evLa) throws ExcepcionIntegridad{
    	EvtAnimal evFin = evLa.getEventoFinalizaLactancia();
    	EvtLactancia lacti =null;
		Session session = HibernateFactory.getSession();
		
		//if (evtLactancia != null) { 
				SortedSet even = evFin.getDependientes();
				ArrayList arrayList2 =null;
				if(!even.isEmpty()){
					arrayList2 = new ArrayList(ani.getEvtAnimals());
				
				for(Object lact : even){
					if(lact.getClass()==EvtLactancia.class){
						lacti = (EvtLactancia)lact;
						if(arrayList2.contains(lacti))//esto lo hago pq en dependientes puede quedar una lactancia q falta q hibernate la elimine y el arrayList2 ya no esta
							break;
						}
					}
				}
				if(lacti!=null && arrayList2.contains(lacti)){
					
					
					arrayList2.remove(evFin);
					arrayList2.remove(lacti);
					ani.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
					ani.getEvtAnimals().addAll(arrayList2);
					ani.getEvtAnimals().add(evFin);
					
					if (lacti.isEsOficial()&& AnimalesLactanciasOficiales.getAnimales()!=null)
		        		AnimalesLactanciasOficiales.getAnimales().remove(this);
					if(lacti.getId()!=null){
						lacti.ejecutarBaja();
						ani.getEvtAnimals().add(evFin);
					}
					//else
					evFin.getDependientes().remove(lacti);
					AnimalDAO.updateAnimal(ani);
					
					}
		//	}
			if(evFin.isFinalizaLactancia() ){
				EvtLactancia evtLactancia = EvtLactancia.calcularLactanciaCerrada(ani, evFin);
				if(evtLactancia!=null){
					
					ArrayList arrayList = new ArrayList(ani.getEvtAnimals());
					ani.setEvtAnimals(new TreeSet(new EventosPorFechaYTipo()));
					arrayList.add(evtLactancia);
					ani.getEvtAnimals().addAll(arrayList);
					//this.getEvtAnimals().add(evtLactancia);
					evFin.addDependiente(evtLactancia);
					try {
						session.save(evtLactancia);
						session.update(evFin);
						session.update(ani);
						//session.flush();
						
					} catch (HibernateException e) {
						throw new ErrorFatal(
							"Error capa persistencia al agregar lactancia cerrada : "
									+ e);
					}
				}
			}
    }
	 public static EvtLactancia calcularUltimaLactanciaAbiertaSinFiltroLeche(Animal animal) {
	        EvtLactancia result = null;
	        List evsProd = new ArrayList();
	        EvtAnimal evIni = buscarIniLactAcumProdsDesde(animal, null, evsProd);
	        if (!(evIni == null)) {
	            EvtAnimal evFin = evsProd.size() != 0 ? (EvtAnimal) evsProd.get(0) : null;
	            Date fechaFin = evFin != null ? DateUtils.mas(evFin.getFecha(), 1) : new Date();
	            result = CalcularLactancia(animal, evIni, fechaFin/*, evsProd*/, evFin,
	                    false, 9999);
	            if (result != null)
	            	calcularProducciones(result);
	        }
	        return result;
	    }
	 public List getControles(){
		 return this.getAnimal().getEventosEntre(this.getFechaInicio(),
					DateUtils.mas(this.getFechaInicio(),
							this.getDias()),
					EvtAnimal.EVT_TIPO_CONTROL_ANIMAL);
	 }

	public boolean isSicel3() {
		// TODO Auto-generated method stub
		return true;
	}
	 
}
