package ar.org.sicel.persistence;

import ar.org.sicel.proc.v1.lote.Rdo;
import ar.org.sicel.proc.v1.lote.Vals;


/**
 *
 * @hibernate.class
 *     table="Pr_Msg"
 *     lazy="true"
 *
 */
public class ProcMsg {
    // ---------------- static --------------
        
    public static Byte DEBUG = new Byte("10");
    public static Byte INFO = new Byte("30");
    public static Byte INFO_WARNING = new Byte("45");
    public static Byte WARNING = new Byte("50");
    public static Byte WARNING_SPECIAL = new Byte("65");
    public static Byte ERROR = new Byte("80");
    public static Byte FATAL = new Byte("100");
    


    // --------------- attributes ---------------------
    private java.lang.Long id;
    private java.lang.Byte nivelError;
    private ar.org.sicel.persistence.ProcCodMsg codigoMsg;
    private java.lang.String informacion;
    private String[] valores;
    private boolean estaAgregado;

    protected ProcMsg() {
    }

    /**
     *
     * @hibernate.id
     *     generator-class="native"
     * @hibernate.generator-param
     *     name="sequence"
     *     value="gen_pr_msgs"
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
     * @hibernate.property
     *     column="nivelError"
     * @hibernate.column
     *     name="nivelError"
     *     not-null="true"
     *
     */
    public java.lang.Byte getNivelError() {
        return this.nivelError;
    }

    public void setNivelError(java.lang.Byte nivelError) {
        this.nivelError = nivelError;
    }

    /**
     *
     * @hibernate.property
     *     column="informacion"
     *
     */
    public java.lang.String getInformacion() {
        return this.informacion;
    }

    public void setInformacion(java.lang.String informacion) {
        this.informacion = informacion;
    }

    // ------------- relations ------------------

    /**
     *
     * @hibernate.array
     *     table = "PR_MSG_VALOR"
     * @hibernate.collection-key
     *     column = "MSG"
     *     foreign-key="FK_Pr_Vals_prmsg"
     * @hibernate.collection-index
     *     column = "POS"
     *     type = "integer"
     * @hibernate.collection-element
     *     type = "string"
     *     column = "VALOR"
     *
     */
	public String[] getValores() {
		return valores;
	}
	/**
	 * @param valores The valores to set.
	 */
	public void setValores(String[] valores) {
		this.valores = valores;
	}
	
    /**
     *
     * @hibernate.many-to-one
     *     column="codigoMensaje"
     *     not-null="true"
     *     outer-join="auto"
     *     foreign-key="FK_Pr_Msg_cmsg"
     *
     */
    public ar.org.sicel.persistence.ProcCodMsg getCodigoMsg() {
        return this.codigoMsg;
    }

    public void setCodigoMsg(
        ar.org.sicel.persistence.ProcCodMsg codigoMsg) {
        this.codigoMsg = codigoMsg;
    }

    // ---------------- business methods  ----------------------
    public Rdo getAsCastorRdo() {
    	//System.out.println("getAsCastorRdo");
        Rdo rdo = new Rdo();
        rdo.setInfo(this.informacion);
        rdo.setNivelErr(this.nivelError);
        rdo.setCodigoMsg(this.codigoMsg.getId());
        Vals xmlVals = new Vals();
        for (int i= 0; i< valores.length; i++)
        	xmlVals.addVal(i,valores[i]);
        rdo.setVals(xmlVals);
        return rdo;
    }
    
    public void setValor(int indice, String valor) {
    	valores[indice] = valor;
    }
    
    public String getValor(int indice) {
    	return valores[indice];
    }

    
    public boolean isEstaAgregado() {
		return estaAgregado;
	}

	public void setEstaAgregado(boolean estaAgregado) {
		this.estaAgregado = estaAgregado;
	}

	public String toString()
    {
        return this.getNivelError()+"-"+this.getCodigoMsg()+" : "+this.getInformacion();
    }

}
