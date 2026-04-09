/**
 * Attention: Generated source (HibernateEntity.vsl)! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import java.sql.Blob;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;

/**
 * 
 * @hibernate.class table="Pr_Proces" lazy="true"
 * 
 */
public class ProcProces {
    // --------------- attributes ---------------------
    private java.lang.Long id;

   // private java.lang.String usuario;
    private Usuario usuario;

    private java.util.Date fecha;

    private java.util.Set procMsgsses;

    private ar.org.sicel.persistence.ProcLote procLote;

    private Date fechaSalida;
	private String observacionSalida;
	private Usuario usuarioProcesador;
	private String observaciones;
//	private Long id;
	private Date fechaEntrada;
	private boolean descargadaEclo;
	private boolean descargadaAdmin;
	private String estado;
	private Blob contenidoEntrada;
	private Blob contenidoSalida;

	private String nombreArchivoEntrada;
    
	public ProcProces(){
		this.fechaSalida=null;
		this.descargadaEclo=false;
		this.descargadaAdmin=false;
	}
	
	/**
     * 
     * @hibernate.id generator-class="native"
     * @hibernate.generator-param name="sequence" value="gen_pr_proces"
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
     * @hibernate.property column="usuario"
     * @hibernate.column name="usuario" not-null="true"
     * 
     */
   /* public java.lang.String getUsuario() {
        return this.usuario;
    }

    protected void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }*/

    /**
     * 
     * @hibernate.property column="fecha"
     * @hibernate.column name="fecha" not-null="true"
     * 
     */
    public java.util.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    // ------------- relations ------------------

    /**
     * 
     * @hibernate.set lazy="true" table="Pr_MsgProces" cascade="all"
     * @hibernate.collection-key column="proces"
     *                           foreign-key="FK_Pr_mproc_proces"
     * @hibernate.collection-many-to-many column="msg"
     *                                    class="ar.org.sicel.persistence.ProcMsg"
     *                                    foreign-key="FK_Pr_mproc_msg"
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
     * @hibernate.many-to-one column="pr_lote" outer-join="auto"
     *                        foreign-key="FK_Pr_proces_lote" unique="true"
     *                        cascade = "all"
     * @hibernate.column name="pr_lote" unique="true"
     *                   unique-key="UN_Pr_Proes_lote"
     * 
     */
    public ar.org.sicel.persistence.ProcLote getProcLote() {
        return this.procLote;
    }

    protected void setProcLote(ar.org.sicel.persistence.ProcLote procLote) {
        this.procLote = procLote;
    }

    // ---------------- business methods ----------------------
    public void addProcLote(ProcLote procLote) {
        this.setProcLote(procLote);
        procLote.setProcProces(this);
    }

    @SuppressWarnings("unchecked")
	public void addMsg(ProcMsg msg) {
        this.procMsgsses.add(msg);
    }

    public static void elimProcesoYEventos(int ecloNum,
            int numeroLoteParaInformante) throws ExcepcionIntegridad {
        elimProcesoYEventos(new Long(ecloNum), new Long(
                numeroLoteParaInformante));
    }

    public static void elimProcesoYEventos(int idLoteSicel)
            throws ExcepcionIntegridad, HibernateException {
        elimProcesoYEventos(ProcLoteDAO.findByPrimaryKey(new Long(idLoteSicel)));
    }

    public static void elimProcesoYEventos(Long ecloNum,
            Long numeroLoteParaInformante) throws ExcepcionIntegridad {
        Eclo eclo = null;
        try {
            eclo = EcloDAO.findByPrimaryKey(ecloNum);
        } catch (HibernateException e1) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(
                    MENSAJES.PROC_LOTE_NOEX, new String[] {
                            String.valueOf(numeroLoteParaInformante),
                            String.valueOf(eclo.getId()) });
            throw e;
        }
        ProcLote procLote = ProcLoteDAO.findByECLO(eclo,
                numeroLoteParaInformante);
        if (procLote == null || eclo == null) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(
                    MENSAJES.PROC_LOTE_NOEX, new String[] {
                            String.valueOf(numeroLoteParaInformante),
                            String.valueOf(eclo.getId()) });
            throw e;
        }
        elimProcesoYEventos(procLote);
    }

    /**
     * Elimina los eventos del proceso. Se utiliza en principio sólo en los test
     * para eliminar el efecto colateral de la ejecución del test.
     * 
     * @throws ExcepcionIntegridad
     */
    
	@SuppressWarnings("unchecked")
	public static void elimProcesoYEventos(ProcLote procLote)
            throws ExcepcionIntegridad {
        // Depende del orden de borrado de los
        // eventos, que pueden ser dependientes entre sí. Hay q borrar en orden
        // inverso de inserción, que se puede
        // resolver con un sort en inverso del ID asignado, que se supone
        // creciente.
        // Primero borrar eventos de animal, luego de establecimiento, que es al
        // reves de como viene en el lote.
        try {
            Session session = HibernateFactory.getSession();
            Set prestabsSinOrden = procLote.getProcEstablecimientos();

            List prestabsOrdenado = new java.util.ArrayList();
            prestabsOrdenado.addAll(prestabsSinOrden);
            Collections.sort(prestabsOrdenado,
                    new Comparator<ProcEstablecimiento>() {
                        public int compare(ProcEstablecimiento o1,
                                ProcEstablecimiento o2) {
                            return o2.getId().compareTo(o1.getId());
                        }
                    });

            for (Iterator iter_prestabs = prestabsOrdenado.iterator(); iter_prestabs
                    .hasNext();) {
                ProcEstablecimiento prestab = (ProcEstablecimiento) iter_prestabs
                        .next();
                Set pranimsSinOrden = prestab.getProcAnimals();
                List pranimsOrdenado = new java.util.ArrayList();
                pranimsOrdenado.addAll(pranimsSinOrden);
                Collections.sort(pranimsOrdenado, new Comparator<ProcAnimal>() {
                    public int compare(ProcAnimal o1, ProcAnimal o2) {
                        return o2.getId().compareTo(o1.getId());
                    }
                });
                for (Iterator iter_pranims = pranimsOrdenado.iterator(); iter_pranims
                        .hasNext();) {
                    ProcAnimal pranimal = (ProcAnimal) iter_pranims.next();
                    Set prevanimsSinOrden = pranimal.getProcEvtAnimals();

                    List prevanimsOrdenado = new java.util.ArrayList();
                    prevanimsOrdenado.addAll(prevanimsSinOrden);
                    Collections.sort(prevanimsOrdenado,
                            new Comparator<ProcEvtAnimal>() {
                                public int compare(ProcEvtAnimal o1,
                                        ProcEvtAnimal o2) {
                                    return o2.getId().compareTo(o1.getId());
                                }
                            });
                    for (Iterator iter_prevanims = prevanimsOrdenado.iterator(); iter_prevanims
                            .hasNext();) {
                        ProcEvtAnimal prevanim = (ProcEvtAnimal) iter_prevanims
                                .next();
                        EvtAnimal evanim = prevanim.getEvtAnimal();
                        session.delete(prevanim);
                        evanim.eliminarYColaterales();
                    }
                    session.delete(pranimal);
                }
                Set prevestabsSinOrden = prestab.getProcEvtEsts();
                List prevestabsOrdenado = new java.util.ArrayList();
                prevestabsOrdenado.addAll(prevestabsSinOrden);
                Collections.sort(prevestabsOrdenado,
                        new Comparator<ProcEvtEst>() {
                            public int compare(ProcEvtEst o1, ProcEvtEst o2) {
                                return o2.getId().compareTo(o1.getId());
                            }
                        });
                for (Iterator iter_prevestabs = prevestabsOrdenado.iterator(); iter_prevestabs
                        .hasNext();) {
                    ProcEvtEst prevestab = (ProcEvtEst) iter_prevestabs.next();
                    EvtEstablecimiento evestab = prevestab
                            .getEvtEstablecimiento();
                    session.delete(prevestab);
                    evestab.eliminarYColaterales();
                }
                session.delete(prestab);
                procLote.getEclo().removeLote(procLote);
            }
            if (procLote.getProcProces() != null)
                session.delete(procLote.getProcProces());
            else
                session.delete(procLote);
        } catch (HibernateException he) {
            ExcepcionIntegridad e = new ExcepcionIntegridad(MENSAJES.PROC_ELIM,
                    new String[] { String.valueOf(procLote.getNumLote()),
                            String.valueOf(procLote.getEclo().getId()),
                            he.toString() });
            throw e;
        }
    }

    public Set getAnimalesModificados() {
        if (this.getProcLote() == null)
            return new HashSet();
        return this.getProcLote().getAnimalesModificados();
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservacionSalida() {
		return observacionSalida;
	}

	public void setObservacionSalida(String observacionSalida) {
		this.observacionSalida = observacionSalida;
	}

	public Blob getContenidoEntrada() {
		return contenidoEntrada;
	}

	public void setContenidoEntrada(Blob contenidoEntrada) {
		this.contenidoEntrada = contenidoEntrada;
	}

	public Blob getContenidoSalida() {
		return contenidoSalida;
	}

	public void setContenidoSalida(Blob contenidoSalida) {
		this.contenidoSalida = contenidoSalida;
	}

	public String getNombreArchivoEntrada() {
		return nombreArchivoEntrada;
	}

	public void setNombreArchivoEntrada(String nombreArchivoEntrada) {
		this.nombreArchivoEntrada = nombreArchivoEntrada;
	}

	public Usuario getUsuarioProcesador() {
		return usuarioProcesador;
	}

	public void setUsuarioProcesador(Usuario usuarioProcesador) {
		this.usuarioProcesador = usuarioProcesador;
	}

	public boolean isDescargadaAdmin() {
		return descargadaAdmin;
	}

	public void setDescargadaAdmin(boolean descargadaAdmin) {
		this.descargadaAdmin = descargadaAdmin;
	}

	public boolean isDescargadaEclo() {
		return descargadaEclo;
	}

	public void setDescargadaEclo(boolean descargadaEclo) {
		this.descargadaEclo = descargadaEclo;
	}

}
