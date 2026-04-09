package ar.org.sicel.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.ResultadosUtil;
import ar.org.sicel.proc.v1.lote.OrdenieAnimal;
import ar.org.sicel.proc.v1.lote.types.STProdMedObj;

/**
 * @author pablo
 * 
 * @hibernate.class table="EV_Ordenie_animal" lazy="true"
 * 
 * 
 */
public class EvtOrdenieAnimal { // extends ar.org.sicel.persistence.EvtAnimal {

	public final static String POSFIJOMAX = "_MAX";

	public final static String POSFIJOMIN = "_MIN";

	// --------------- attributes ---------------------

	private java.util.Map mediciones;

	private boolean esRechazo;

	private String causaRechazo;

	private Long id;

	private EvtControlAnimal controlAnimal;
	
	private Date horaIniOrdenie;
	
	private Date horaFinOrdenie;
	
	protected EvtOrdenieAnimal() {

	}

	/**
	 * 
	 * @hibernate.id generator-class="native" column="id"
	 * @hibernate.generator-param name="sequence" value="gen_evt_ordenie_animal"
	 * 
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	protected EvtOrdenieAnimal(OrdenieAnimal ordenieAnimal, Map mediciones, List msgs, Animal animal)
			throws ExcepcionIntegridad {
		this.mediciones = new HashMap();
		Iterator it = mediciones.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			addMedicion((String) entry.getKey(), (Float) entry.getValue(),animal);
		}
	}

	// ------------- relations ------------------

	/**
	 * 
	 * @hibernate.map table="Ev_Mediciones" lazy="true" cascade="all"
	 * @hibernate.collection-key column="produccion"
	 *                           foreign-key="FK_EvMedicion_EvProduc"
	 * @hibernate.collection-index column="objeto" type="string" length="30"
	 * @hibernate.collection-element column="valor" type="float" not-null="true"
	 * 
	 */
	public java.util.Map getMediciones() {
		return this.mediciones;
	}

	protected void setMediciones(java.util.Map mediciones) {
		this.mediciones = mediciones;
	}

	/**
	 * 
	 * @hibernate.property column="causa_rechazo"
	 * 
	 */
	public String getCausaRechazo() {
		return causaRechazo;
	}

	public void setCausaRechazo(String causaRechazo) {
		this.causaRechazo = causaRechazo;
	}

	/**
	 * 
	 * @hibernate.property column="es_rechazo"
	 * 
	 */
	public boolean getEsRechazo() {
		return esRechazo;
	}

	public void setEsRechazo(boolean esRechazo) {
		this.esRechazo = esRechazo;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="evt_control" outer-join="auto"
	 *                        not-null="true" foreign-key="FK_Raza_Especie"
	 * 
	 */
	public EvtControlAnimal getControlAnimal() {
		return this.controlAnimal;
	}

	public void setControlAnimal(EvtControlAnimal control) {
		this.controlAnimal = control;
	}

	// ---------------- business methods ----------------------

	public EvtOrdenieAnimal addMed(String objeto, float valor, Animal animal)
			throws ExcepcionIntegridad {
		addMedicion(objeto, valor,animal);
		return this;
	}

	@SuppressWarnings("unchecked")
	public void addMedicion(String objeto, float valor, Animal animal)
			throws ExcepcionIntegridad {

		if (mediciones.containsKey(objeto)) {
			throw new ExcepcionIntegridad(MENSAJES.MED_OBJ_REPETIDO,
					new String[] { animal.getRegistroOrigen(), objeto });
		}
		if(!Float.isNaN(valor))
			this.mediciones.put(objeto, valor);
	}
	
	public void removeMedicion(String objeto, Animal animal) {
		this.mediciones.remove(objeto);
	}
	

	public static float[] getMaxMinObjetoMedicion(String objetoMedicion,
			Animal animal) throws ExcepcionIntegridad {
		float[] result = new float[2];
		result[0] = animal.getParametroRazaAsFloat(objetoMedicion + POSFIJOMAX);
		result[1] = animal.getParametroRazaAsFloat(objetoMedicion + POSFIJOMIN);
		return result;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
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
		return "Producción. " +
		// "Metodo: "+getMetodoControl()+" "+
				// "Ordeñes: "+getNumOrdenies()+" "+
				// "CtrlAM: "+getControlEsAM()+" "+
				// "Muestreo: "+getTipoMuestreo()+" "+
				// "MuestreoAM: "+getMuestreoEsAM()+" "+
				"\n\t\t" + getResumenMediciones();
	}

	/**
	 * Valida una produccion. En este caso tanto la validacion como la
	 * revalidacion son exactamente iguales.
	 */
	public void validar(Date fecha, Animal animal, OrdenieAnimal ordAnimal, List msgs)
			throws ExcepcionIntegridad {
		Iterator it = mediciones.entrySet().iterator();
		boolean chequearValoresMaxMin = Configuracion.getValorReglaProceso(
				CONF.MED_MAX_MIN, fecha);
		List objetosBorrar = new ArrayList();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String objeto = (String) entry.getKey();
			Float valor = (Float) entry.getValue();

			try {
				STProdMedObj.valueOf(objeto);
			} catch (IllegalArgumentException e) {
				throw new ExcepcionIntegridad(MENSAJES.EvProdObjeto,
						new String[] { objeto });
			}

			//MIRAR Que pasa si no tengo valores minimos o maximos para controlar del objeto de medicion ?
			if (chequearValoresMaxMin
					&& animal.getParametroRaza(objeto + POSFIJOMAX) != null
					&& animal.getParametroRaza(objeto + POSFIJOMIN) != null) {
				float maxMin[] = getMaxMinObjetoMedicion(objeto, animal);
				//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
				if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date())) {	
					if (valor > maxMin[0] || valor < maxMin[1])
						throw new ExcepcionIntegridad(MENSAJES.MED_ERR,
							new String[] { objeto, String.valueOf(valor),
									String.valueOf(maxMin[1]),
									String.valueOf(maxMin[0]) });
				}else{
					if("LE".equals(objeto)){
						if (valor > maxMin[0] || valor < maxMin[1])
							throw new ExcepcionIntegridad(MENSAJES.MED_ERR,
								new String[] { objeto, String.valueOf(valor),
										String.valueOf(maxMin[1]),
										String.valueOf(maxMin[0]) });
					}else{
						if (valor > maxMin[0] || valor < maxMin[1]){
							ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_ERR, ProcMsg.WARNING_SPECIAL, new String[] { objeto, String.valueOf(valor),
									String.valueOf(maxMin[1]),
									String.valueOf(maxMin[0])});
							msgs.add(msg);
							msg.setEstaAgregado(true);
							ResultadosUtil.addResultado(ordAnimal,msg);
							if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))
								objetosBorrar.add(objeto);
						}
					}	
				}
			}
		}
		if ((Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))&&
				(!objetosBorrar.isEmpty())){
			Iterator it_objetosBorrar = objetosBorrar.iterator();
			while(it_objetosBorrar.hasNext()){
				String objeto  = (String)it_objetosBorrar.next();
				this.removeMedicion(objeto,animal);
			}
		}
	}

	/**
	 * Valida una produccion. En este caso tanto la validacion como la
	 * revalidacion son exactamente iguales.
	 * @param fecha - Date
	 * @param animal - Animal
	 * @param msgs - List
	 * @return int - Indica 1: sin componente, 2: con componente con error, 3: con componente correcto
	 */
	public int validarTipoMuestreo(Date fecha, Animal animal, OrdenieAnimal ordAnimal, List msgs)
			throws ExcepcionIntegridad {
		Iterator it = mediciones.entrySet().iterator();
		boolean chequearValoresMaxMin = Configuracion.getValorReglaProceso(
				CONF.MED_MAX_MIN, fecha);
		int conComponente = 1;
		List objetosBorrar = new ArrayList();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String objeto = (String) entry.getKey();
			Float valor = (Float) entry.getValue();

			try {
				STProdMedObj.valueOf(objeto);
			} catch (IllegalArgumentException e) {
				throw new ExcepcionIntegridad(MENSAJES.EvProdObjeto,
						new String[] { objeto });
			}
			//MIRAR Que pasa si no tengo valores minimos o maximos para controlar del objeto de medicion ?
			
			if (chequearValoresMaxMin
					&& animal.getParametroRaza(objeto + POSFIJOMAX) != null
					&& animal.getParametroRaza(objeto + POSFIJOMIN) != null) {
				float maxMin[] = getMaxMinObjetoMedicion(objeto, animal);
				if("LE".equals(objeto)){
					if (valor > maxMin[0] || valor < maxMin[1])
						throw new ExcepcionIntegridad(MENSAJES.MED_ERR,
							new String[] { objeto, String.valueOf(valor),
									String.valueOf(maxMin[1]),
									String.valueOf(maxMin[0]) });
				}else{
					if (valor > maxMin[0] || valor < maxMin[1]){
						ProcMsg msg = ProcMsgDAO.create(MENSAJES.MED_ERR, ProcMsg.WARNING_SPECIAL, new String[] { objeto, String.valueOf(valor),
								String.valueOf(maxMin[1]),
								String.valueOf(maxMin[0])});
						msgs.add(msg);
						msg.setEstaAgregado(true);
						ResultadosUtil.addResultado(ordAnimal,msg);
						//TODO acordarse de cambiar las configuracion una vez terminadas las cargas iniciales
						if (Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))
							objetosBorrar.add(objeto);
						conComponente = 2;
					}else if(conComponente!=2){
						conComponente = 3;
					}	
				}	
			}
		}
		if ((Configuracion.getValorReglaProceso(CONF.CARGA_INICIAL_NO_CHEQUEO_REGLAS,new Date()))&&
				(!objetosBorrar.isEmpty())){
			Iterator it_objetosBorrar = objetosBorrar.iterator();
			while(it_objetosBorrar.hasNext()){
				String objeto  = (String)it_objetosBorrar.next();
				this.removeMedicion(objeto,animal);
			}
		}
		return conComponente;
	}

	/**
	 * 
	 * @return true si este ordenie contiene informacion de analisis ademas de
	 *         produccion de leche ( por ejemplo %de grasa)
	 */
	public boolean tieneAnalisis() {
		if ((getMediciones().containsKey(STProdMedObj.GR.toString()))&&(getMediciones().containsKey(STProdMedObj.PR.toString())))
		return true;
			/*if (getMediciones().containsKey(STProdMedObj.GR.toString()))
			return true;
		if (getMediciones().containsKey(STProdMedObj.CE.toString()))
			return true;
		if (getMediciones().containsKey(STProdMedObj.LA.toString()))
			return true;
		if (getMediciones().containsKey(STProdMedObj.PR.toString()))
			return true;
		if (getMediciones().containsKey(STProdMedObj.ST.toString()))
			return true;
		if (getMediciones().containsKey(STProdMedObj.UR.toString()))
			return true;*/
		return false;
	}

	public Date getHoraFinOrdenie() {
		return horaFinOrdenie;
	}

	public void setHoraFinOrdenie(Date horaFinOrdenie) {
		this.horaFinOrdenie = horaFinOrdenie;
	}

	public Date getHoraIniOrdenie() {
		return horaIniOrdenie;
	}

	public void setHoraIniOrdenie(Date horaIniOrdenie) {
		this.horaIniOrdenie = horaIniOrdenie;
	}

	
}
