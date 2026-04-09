package ar.org.sicel.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @hibernate.joined-subclass table="Ev_Establecimiento" lazy="true"
 * 
 * @hibernate.joined-subclass-key column="id" foreign-key="FK_EvEstab_Evento"
 * 
 */
public abstract class EvtEstablecimiento extends
        ar.org.sicel.persistence.Evento {
    
	
	
	
	// --------------- attributes ---------------------
    private ar.org.sicel.persistence.ProcEvtEst procEvtEst;

    protected EvtEstablecimiento() {
    }
    
    protected EvtEstablecimiento(Establecimiento est, Date fecha) {
    	super(est,fecha);
    }

    // ------------- relations ------------------

    /**
     * 
     * @hibernate.one-to-one outer-join="auto" property-ref="evtEstablecimiento"
     * 
     */
    public ar.org.sicel.persistence.ProcEvtEst getProcEvtEst() {
        return this.procEvtEst;
    }

    protected void setProcEvtEst(ar.org.sicel.persistence.ProcEvtEst procEvtEst) {
        this.procEvtEst = procEvtEst;
    }

    // ---------------- business methods ----------------------

    public Set getAnimalesModificados() {
        return new HashSet();
    }

    public void addAnimalesModificados(Set animalesModificados) {

    }

    protected String getResumenEncab() {
        String result = super.getResumenEncab();
        result = result + Animal.SECA_VACIA + "[_ni_nf_] ";
        return result;
    }

    protected String getResumenProceso() {
        String result = "";
        if (this.getProcEvtEst() == null)
            return result;
        Set msgs = this.getProcEvtEst().getProcMsgsses();
        if (msgs.size() == 0)
            return result;
        result = result + "\n";
        for (Iterator iter = msgs.iterator(); iter.hasNext();) {
            result = result + "\t";
            ProcMsg procMsg = (ProcMsg) iter.next();
            result = result + procMsg.getCodigoMsg().getId();
            if(!procMsg.getInformacion().contains(procMsg.getCodigoMsg().getDescripcion()))//+".").equalsIgnoreCase(procMsg.getInformacion()))
            	result = result + " (" + procMsg.getCodigoMsg().getDescripcion() + " ) ";
            result = result + " : "+ procMsg.getInformacion();
            if (iter.hasNext())
                result = result + "\n";
        }
        return result;
    }
    
    
    public String getLinkAProc() {
        return "/sicel3/buscarProcEstablecimiento.do?procEstablecimientoId="+this.getProcEvtEst().getProcEstablecimiento().getId();
    }
    /*public boolean equals(Object eve){
    	if(eve!=null){
    	if(!(eve instanceof EvtEstablecimiento))
			return false;
		EvtEstablecimiento ev = (EvtEstablecimiento)eve;
    	
    		if((ev.getId()!=null && this.getId()!=null)&&(ev.getId().equals(this.getId())))
    			return true;
    	}
		return false;
    	
    }*/

}
