package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * 
 * @hibernate.class table="Pr_Lote" lazy="true"
 * 
 */
public class ProcLote {
	protected static Logger log = Logger.getLogger(ProcLote.class);

	// --------------- attributes ---------------------
	private java.lang.Long id;

	private java.lang.Long numLote;

	private java.util.Date tCreacion;

	private java.util.Date tEnvio;

	private java.util.Date tRecep;

	private java.util.Date tInicioProc;

	private java.util.Date tiFinProc;

	private java.util.Date tRetorno;

	private java.util.Set procMsgsses;

	private ar.org.sicel.persistence.Eclo eclo;

	private ar.org.sicel.persistence.Sistema sistema;

	private java.util.Set procEstablecimientos;

	private ar.org.sicel.persistence.ProcProces procProces;

	private Set<FichaAnimalPendiente> fichasAnimal;
	
	private CentroDeComputo centroComputo;
	
	public CentroDeComputo getCentroComputo() {
		return centroComputo;
	}

	public void setCentroComputo(CentroDeComputo centroComputo) {
		this.centroComputo = centroComputo;
	}

	/**
	 * Unicamente lo usa hibernate
	 * 
	 */
	protected ProcLote() {
	}

	/**
	 * 
	 */
	// protected ProcLote(Eclo ecloInformante, Long numLote) throws
	// ExcepcionIntegridad{
	// checkNumLoteUnico(ecloInformante,numLote);
	// this.eclo = ecloInformante;
	// this.numLote = numLote;
	// }
	/**
	 * 
	 * @hibernate.id generator-class="native"
	 * @hibernate.generator-param name="sequence" value="gen_pr_lote"
	 * 
	 */
	public java.lang.Long getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(java.lang.Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @hibernate.property column="numLote"
	 * @hibernate.column name="numLote" not-null="true"
	 *                   unique-key="numLote_ECLO"
	 * 
	 */
	public java.lang.Long getNumLote() {
		return this.numLote;
	}

	protected void setNumLote(java.lang.Long numLote)
			throws ExcepcionIntegridad {
		this.numLote = numLote;
	}

	/**
	 * 
	 * @hibernate.property column="creacion"
	 * 
	 */
	public java.util.Date getTCreacion() {
		return this.tCreacion;
	}

	protected void setTCreacion(java.util.Date tCreacion) {
		this.tCreacion = tCreacion;
	}

	/**
	 * 
	 * @hibernate.property column="envio"
	 * @hibernate.column name="envio" not-null="true"
	 * 
	 */
	public java.util.Date getTEnvio() {
		return this.tEnvio;
	}

	protected void setTEnvio(java.util.Date tEnvio) {
		this.tEnvio = tEnvio;
	}

	/**
	 * 
	 * @hibernate.property column="recep"
	 * 
	 */
	public java.util.Date getTRecep() {
		return this.tRecep;
	}

	protected void setTRecep(java.util.Date tRecep) {
		this.tRecep = tRecep;
	}

	/**
	 * 
	 * @hibernate.property column="inicioProc"
	 * 
	 */
	public java.util.Date getTInicioProc() {
		return this.tInicioProc;
	}

	protected void setTInicioProc(java.util.Date tInicioProc) {
		this.tInicioProc = tInicioProc;
	}

	

	/**
	 * 
	 * @hibernate.property column="retorno"
	 * 
	 */
	public java.util.Date getTRetorno() {
		return this.tRetorno;
	}

	protected void setTRetorno(java.util.Date tRetorno) {
		this.tRetorno = tRetorno;
	}

	// ------------- relations ------------------

	/**
	 * 
	 * @hibernate.set lazy="true" table="Pr_MsgLote" cascade="all"
	 * @hibernate.collection-key column="lote" foreign-key="FK_Pr_mlot_lote"
	 * @hibernate.collection-many-to-many column="msg"
	 *                                    class="ar.org.sicel.persistence.ProcMsg"
	 *                                    foreign-key="FK_Pr_mlot_msg"
	 * 
	 */
	public java.util.Set getProcMsgsses() {
		return this.procMsgsses;
	}

	protected void setProcMsgsses(java.util.Set procMsgsses) {
		this.procMsgsses = procMsgsses;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="eclo" outer-join="auto"
	 *                        foreign-key="FK_Pr_lote_eclo" not-null="true"
	 * @hibernate.column name="eclo" unique-key="numLote_ECLO"
	 * 
	 */
	public ar.org.sicel.persistence.Eclo getEclo() {
		return this.eclo;
	}

	protected void setEclo(ar.org.sicel.persistence.Eclo eclo)
			throws ExcepcionIntegridad {
		this.eclo = eclo;
	}

	/**
	 * 
	 * @hibernate.many-to-one column="sistema" outer-join="auto"
	 *                        foreign-key="FK_Pr_lote_sist" not-null="true"
	 * 
	 */
	public ar.org.sicel.persistence.Sistema getSistema() {
		return this.sistema;
	}

	protected void setSistema(ar.org.sicel.persistence.Sistema sistema) {
		this.sistema = sistema;
	}

	/**
	 * 
	 * @hibernate.set lazy="true" cascade="all" inverse="true"
	 * @hibernate.collection-key column="lote"
	 * @hibernate.collection-one-to-many class="ar.org.sicel.persistence.ProcEstablecimiento"
	 * 
	 */
	public java.util.Set getProcEstablecimientos() {
		return this.procEstablecimientos;
	}

	protected void setProcEstablecimientos(java.util.Set procEstablecimientos) {
		this.procEstablecimientos = procEstablecimientos;
	}

	/**
	 * 
	 * @hibernate.one-to-one outer-join="auto" property-ref="procLote"
	 * 
	 */
	public ar.org.sicel.persistence.ProcProces getProcProces() {
		return this.procProces;
	}

	protected void setProcProces(ar.org.sicel.persistence.ProcProces procProces) {
		this.procProces = procProces;
	}

	@SuppressWarnings("unchecked")
	public void addProcEstablecimiento(ProcEstablecimiento procEstab) {
		this.procEstablecimientos.add(procEstab);
		procEstab.setProcLote(this);
	}

	@SuppressWarnings("unchecked")
	public void addMsg(ProcMsg msg) {
		this.procMsgsses.add(msg);
	}

	// ---------------- business methods ----------------------

	/*public void setIdLoteEclo(Eclo nuevaEclo, Long nuevoNumero)
			throws ExcepcionIntegridad {
		checkNumLoteUnico(nuevaEclo, nuevoNumero);
		if (eclo != null)
			eclo.removeLote(this);
		nuevaEclo.addLote(this);
		this.eclo = nuevaEclo;
		this.numLote = nuevoNumero;
	}*/
	public void setIdLoteEclo(Eclo nuevaEclo,Sistema sistema,CentroDeComputo centro, Long nuevoNumero)
	throws ExcepcionIntegridad {
		checkNumLoteUnico(nuevaEclo,sistema,centro, nuevoNumero);
		//if (eclo != null)
		//	eclo.removeLote(this);
		//nuevaEclo.addLote(this);
		this.eclo = nuevaEclo;
		this.numLote = nuevoNumero;
}

	/** --------- BR ----- * */

	/**
	 * Chequea que el numero de lote sea unico para la eclo
	 */
	protected void checkNumLoteUnico(Eclo eclo,Sistema sistema,CentroDeComputo centro, Long numLote)
			throws ExcepcionIntegridad {
		if (Configuracion.getValorReglaProceso(CONF.LOTE_UNICO,new Date())){
    		ProcLote otro = ProcLoteDAO.findByEcloSistemaCentro(eclo,sistema,centro,numLote);
    		if ((otro!= null) && (otro != this)) {
    			//otro != this es por si cambio el numLote o la Eclo a la misma que estaba,
    			//	o sea que no cambio nada, por lo tanto se encuentra a si mismo
    			ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.LOTE_REPETIDO,new String[]{String.valueOf(numLote),String.valueOf(eclo.getId()),String.valueOf(sistema.getNombre()),String.valueOf(centro==null?"[ninguno]" : centro.getId())});
    			throw e;
    		}
    	}
	}

    public Set getAnimalesModificados() {
        Set animalesModificados = new HashSet();
        for (Iterator iter = this.getProcEstablecimientos().iterator(); iter.hasNext();) {
            ProcEstablecimiento procEstablecimiento = (ProcEstablecimiento) iter.next();
            for (Iterator iterator = procEstablecimiento.getProcEvtEsts().iterator(); iterator
                    .hasNext();) {
                ProcEvtEst procEvtEst = (ProcEvtEst) iterator.next();
                procEvtEst.getEvtEstablecimiento().addAnimalesModificados(animalesModificados);
            }
            for (Iterator iterator = procEstablecimiento.getProcAnimals().iterator(); iterator
                    .hasNext();) {
                ProcAnimal procAnimal = (ProcAnimal) iterator.next();
                for (Iterator iteratorEvtAnims = procAnimal.getProcEvtAnimals().iterator(); iteratorEvtAnims
                        .hasNext();) {
                    ProcEvtAnimal procEvtAnimal = (ProcEvtAnimal) iteratorEvtAnims.next();
                    procEvtAnimal.getEvtAnimal().addAnimalesModificados(animalesModificados);
                }
            }
        }
        return animalesModificados;
    }
    public boolean equals(Object lote){
		if(!(lote instanceof ProcLote))
			return false;
		ProcLote p = (ProcLote)lote;
		if(p.getId().equals(this.getId()))
			return true;
		return false;
		
		
	}

	public java.util.Date getTiFinProc() {
		return tiFinProc;
	}

	public void setTiFinProc(java.util.Date finProc) {
		tiFinProc = finProc;
	}
	public java.util.Set getFichasAnimal() {
		return this.fichasAnimal;
	}

	protected void setFichasAnimal(Set<FichaAnimalPendiente> fichasAnimal) {
		this.fichasAnimal = fichasAnimal;
	}

	
	
	
	public FichaAnimalPendiente getUltimaFichaAnimal(){
		Long max = 0l;
		for (FichaAnimalPendiente ficha:fichasAnimal){
			
			if (ficha.getProcLote().getNumLote()>max)
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
	}
	
	public void agregarFichaAnimal(FichaAnimalPendiente ficha){
		this.fichasAnimal.add(ficha);
	}

	

}
