package ar.org.sicel.excel;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DataItem {
	
	private int fila;
	
	private Integer asoc = null; //campo en animal
	private String hbae = ""; // numero de registro (del tipo HBA)
	private String rpex = ""; // rp del animal
	private String nomb = ""; // nombre en animal
	private Integer cbaj = null; // en el registro del animal, vamos a ponerle 0 en caso de qeu no venga informado en el excel
	private Integer dado = null; // en el animalç
	private Long prop = null; // numero de propietario 
	private Long cria = null; // numero del propietario criador
	private Integer tran = null; // en el animal
	private Integer anls = null; // en el animal
	private String dona = "";// en el animal
	private Integer mell = null; // en el animal
	private String trns = "";// en el animal
	//private String tser = "";// en el animal
	private Integer tser ;// en el animal
	private Integer asop = null; // en el animal
	private String hbap = null; // hba del padre del animal
	private Integer asom = null; // campo en animal
	private String hbam = null; // hba de la madre del animal
	private Date fnac = null; // fecha de nacimiento
	private Date fbaj = null; // en ek registro
	private Date fesb = null; // fecha de asoc rural, sRAFesb del animal
	private Date fobs = null; // fecha de ultima observacion  timestamp en animal
	private Date ftrf = null; // fecha en animal;
	private Date fser = null; // fecha en animal
	private String apdo = ""; // en animal
	private String rpti=""; // en el animal
	private String rc = null;
	private String hba = null;
	private Long tambo=null;
	private String rpViejo = "";
	private String rpNuevo = "";
	
	public Long getTambo() {
		return tambo;
	}
	public void setTambo(Long tambo) {
		this.tambo = tambo;
	}
	public String getHba() {
		return hba;
	}
	public void setHba(String hba) {
		this.hba = hba;
	}
	public String getRc() {
		return rc;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	public Integer getAnls() {
		return anls;
	}
	public void setAnls(Integer anls) {
		this.anls = anls;
	}
	public String getApdo() {
		return apdo;
	}
	public void setApdo(String apdo) {
		this.apdo = apdo;
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
	public Integer getCbaj() {
		return cbaj;
	}
	public void setCbaj(Integer cbaj) {
		this.cbaj = cbaj;
	}
	public Long getCria() {
		return cria;
	}
	public void setCria(Long cria) {
		this.cria = cria;
	}
	public Integer getDado() {
		return dado;
	}
	public void setDado(Integer dado) {
		this.dado = dado;
	}
	public String getDona() {
		return dona;
	}
	public void setDona(String dona) {
		this.dona = dona;
	}
	public Date getFbaj() {
		return fbaj;
	}
	public void setFbaj(Date fbaj) {
		this.fbaj = fbaj;
	}
	public Date getFesb() {
		return fesb;
	}
	public void setFesb(Date fesb) {
		this.fesb = fesb;
	}
	public Date getFnac() {
		return fnac;
	}
	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}
	public Date getFobs() {
		return fobs;
	}
	public void setFobs(Date fobs) {
		this.fobs = fobs;
	}
	public Date getFser() {
		return fser;
	}
	public void setFser(Date fser) {
		this.fser = fser;
	}
	public Date getFtrf() {
		return ftrf;
	}
	public void setFtrf(Date ftrf) {
		this.ftrf = ftrf;
	}
	public String getHbae() {
		return hbae;
	}
	public void setHbae(String hbae) {
		this.hbae = hbae;
	}
	
	public Integer getMell() {
		return mell;
	}
	public void setMell(Integer mell) {
		this.mell = mell;
	}
	public String getNomb() {
		return nomb;
	}
	public void setNomb(String nomb) {
		this.nomb = nomb;
	}
	public Long getProp() {
		return prop;
	}
	public void setProp(Long prop) {
		this.prop = prop;
	}

	public Integer getTran() {
		return tran;
	}
	public void setTran(Integer tran) {
		this.tran = tran;
	}
	public String getTrns() {
		return trns;
	}
	public void setTrns(String trns) {
		this.trns = trns;
	}	
	public Integer getAsoc() {
		return asoc;
	}
	public void setAsoc(Integer asoc) {
		this.asoc = asoc;
	}	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public String getHbam() {
		return hbam;
	}
	public void setHbam(String hbam) {
		this.hbam = hbam;
	}
	public String getHbap() {
		return hbap;
	}
	public void setHbap(String hbap) {
		this.hbap = hbap;
	}
	public String getRpex() {
		return rpex;
	}
	public void setRpex(String rpex) {
		this.rpex = rpex;
	}
	public Integer getTser() {
		return tser;
	}
	public void setTser(Integer tser) {
		this.tser = tser;
	}
	/**
	 * si todos los campos estan vacios entonces devuelve true
	 * en caso contrario devuelve false
	 * @return
	 */
	public boolean isEmpty() {
		if (asoc == null &&	StringUtils.isEmpty(hbae) && StringUtils.isEmpty(rpex) &&
			StringUtils.isEmpty(nomb) && cbaj == null && dado == null &&
			prop == null && cria == null && tran == null &&	anls == null &&
			StringUtils.isEmpty(dona) && mell == null && StringUtils.isEmpty(trns) &&
			tser == null &&	asop == null &&	hbap == null &&	asom == null &&
			hbam == null &&	fnac == null &&	fbaj == null &&	fesb == null &&
			fobs == null &&	ftrf == null &&	fser == null &&	StringUtils.isEmpty(apdo))
			return true;
		
		return false;
	}
	public String getRpti() {
		return rpti;
	}
	public void setRpti(String rpti) {
		this.rpti = rpti;
	}
	public String getRpNuevo() {
		return rpNuevo;
	}
	public void setRpNuevo(String rpNuevo) {
		this.rpNuevo = rpNuevo;
	}
	public String getRpViejo() {
		return rpViejo;
	}
	public void setRpViejo(String rpViejo) {
		this.rpViejo = rpViejo;
	}
	
	
}
