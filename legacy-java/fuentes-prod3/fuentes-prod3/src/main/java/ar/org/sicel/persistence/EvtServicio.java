package ar.org.sicel.persistence;

import java.util.Date;
import java.util.List;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.proc.v1.lote.Serv;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Servicio" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvServicio_EvAnim"
 * 
 */
public abstract class EvtServicio extends ar.org.sicel.persistence.EvtAnimal {
    // --------------- attributes ---------------------

    private EvtReproduccion evtReproduccion;

    protected EvtServicio() {
    }
    
    protected EvtServicio(Establecimiento est, Date fecha, Hembra hem,List msgs) throws ExcepcionIntegridad {
    	super(est,fecha,hem,msgs,Evento.EVT_TIPO_SVC);
    }

    /**
     * @see ar.org.sicel.persistence.Evento#getNombreTipo()
     */
    public String getNombreTipo() {
        return EVT_TIPO_SVC;
    }

    // ------------- relations ------------------

    /**
     * 
     * @hibernate.one-to-one outer-join="auto" property-ref="evtServicio"
     * 
     */
    public ar.org.sicel.persistence.EvtReproduccion getEvtReproduccion() {
        return this.evtReproduccion;
    }

    protected void setEvtReproduccion(
            ar.org.sicel.persistence.EvtReproduccion evtReproduccion) {
        this.evtReproduccion = evtReproduccion;
    }

    // ---------------- business methods ----------------------
/*
    public String getClazzParaTransicion() {
        return EvtServicio.class.getSimpleName();
    }
    */

    /**
     * Retorna cual sera la madre Genetica de una cria nacida a partir de este
     * servicio. Varia dependiendo si es una inseminacion, TE, Clonacion, etc
     * 
     * @return la madre genetica
     */
    public abstract Hembra getMadreGenetica();

    /**
     * Retorna cual sera el padre Genetico de una cria nacida a partir de este
     * servicio. Varia dependiendo si es una inseminacion, TE, Clonacion, etc
     * 
     * @return el padre genetico
     */
    public abstract Macho getPadreGenetico();

    /**
     * Devuelve que cantidad de dias hay que descontarle a este servicio a la
     * hora de calcular el periodo de gestacion. Por ejemplo, en las
     * transferencias embrionarias hay que descontarle el tiempo de gestacion
     * que ya tiene el embrion a ser implantado.
     * 
     * @return los Dias de Gestacion A Descontar
     */
    public abstract int getCantidadDiasGestacionADescontar();

    public abstract EvtAnimalModificacion ejecutarModificacion(Serv servi, List<ProcMsg> mensajes, Animal objAnimal) throws ExcepcionIntegridad;
}

