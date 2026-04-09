package ar.org.sicel.persistence;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.Info;


/**
 *
 * @hibernate.joined-subclass
 *    table="Ev_Info"
 *     lazy="true"
 *
 * @hibernate.joined-subclass-key
 *    column="id"
 *    foreign-key="FK_EvInfo_EvAnim"
 *
 */
public class EvtInfo extends ar.org.sicel.persistence.EvtAnimal {
	
	static Logger log = Logger.getLogger(EvtInfo.class);
	
    // --------------- attributes ---------------------
    private java.lang.String valor;
    private java.lang.String info;

    protected EvtInfo() {
    }
    
    protected EvtInfo(Establecimiento est, Date fecha, Animal an, String info, String valor,List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,an,msgs,Evento.EVT_TIPO_INF);
    	setInfo(info);
    	setValor(valor); 
    	an.addEventoAnimal(this,msgs);
    }

    /**
     *
     * @hibernate.property
     *     column="info"
     * @hibernate.column
     *     name="info"
     *     not-null="true"
     *     length="20"
     *
     */
    public java.lang.String getInfo() {
        return this.info;
    }

    protected void setInfo(java.lang.String info) {
        this.info = info;
    }

    /**
     *
     * @hibernate.property
     *     column="valor"
     *
     */
    public java.lang.String getValor() {
        return this.valor;
    }

    protected void setValor(java.lang.String valor) {
        this.valor = valor;
    }

	/* (non-Javadoc)
	 * @see ar.org.sicel.persistence.Evento#getResumen()
	 */
	public String getResumen() {
		return null;
	}

	/*
	 * @see ar.org.sicel.persistence.Evento#getNombreTipo()
	 */
	public String getNombreTipo() {
		return EVT_TIPO_INF;
	}


    // ------------- relations ------------------
    // ---------------- business methods  ----------------------
	/*
	public String getClazzParaTransicion() {
		return EvtInfo.class.getSimpleName(); 
	}
	*/
	
	// by kjacobsen
	public boolean validarBaja() throws ExcepcionIntegridad {
		//no hay que validar nada de nada, da siempre verdadero, me encanta este evento !!!
		return true;
	}
	
	public void ejecutarBaja() throws ExcepcionIntegridad {
		if(validarBaja()){
			super.ejecutarBaja();
    		Session session = HibernateFactory.getSession();
    		try {
    			session.delete(this);
    		} catch (HibernateException he){
                ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.EVT_ELIM,
                        new String[] { String.valueOf(getId()), he.toString() });
                throw e;
    		}
    	}
	}

	public EvtAnimalModificacion ejecutarModificacion(Info info, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad {
		
		if(!this.getAnimal().getId().equals(objAnimal.getId())){
			log.debug("El animal informado no posee el evento informado como evento reproducción asociado " + info.getModificaOBaja().getIDEvt());
			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.ERROR_ANIMAL_INFO,
                    new String[] { this.getAnimal().getRegistroOrigen(), objAnimal.getRegistroOrigen()});
            throw e;
		}
		this.setFecha(info.getFecha());
		//se cambia esto para que se pueda modificar el evento informacion
		if(info.getPesoAnimal()!=null){
			this.setValor(String.valueOf(info.getPesoAnimal().getValor()));
			this.setInfo("PESO");
		}
		else{
			String vss = "";
			if(info.getOtraInfo().getValores()!=null){
				Enumeration vs = info.getOtraInfo().getValores().enumerateValor();
				while (vs.hasMoreElements()) {
					String valor = (String) vs.nextElement();
					vss = vss + " " + valor;
				}
			}
			this.setValor(vss);
			this.setInfo(info.getOtraInfo().getDescrip());
			
			}
		/*
		Enumeration vs = info.getValores().enumerateValor();
		String vss = "";
		while (vs.hasMoreElements()) {
			String valor = (String) vs.nextElement();
			vss = vss + " " + valor;
		}
		this.setValor(vss);
		this.setInfo(info.getInfo());*/
		
		EvtAnimalModificacion eventoModificacion = EvtAnimalModificacionDAO.create(objAnimal.getEstablecimiento(), new Date(), objAnimal, this);
		this.addModificaciones(eventoModificacion);
		EvtInfoDAO.updateEventoInfo(this);
		return eventoModificacion;
		
	}

	

}
