package ar.org.sicel.persistence;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.DateUtils;



/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type ProcEstablecimiento.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.ProcEstablecimiento
 */
public abstract class ProcEstablecimientoDAO {
	
	
    // ---------------- create method --------------------

    /**
     * Este metodo a parte de crear el procEstablecimiento hace una seria de chequeos por la inactividad de
     * las entidad que participan del procesamiento, tambo, establecimiento propietario.
     * Ademas para el chequeo del tambo en caso de que este inactivo se puede llegar a aceptar el procesmiento
     * siempre que se cumpla con los requisitos, una vez inactivado el tambo tiene un tiempo de 90 dias para poder enviar
     * la informacion hasta la fecha de baja, luego no se aceptaran mas los eventos lo mismo si quiere enviar
     * informacion de eventos ocurridos luego de la desactivacion
     */
    public static ProcEstablecimiento create(Establecimiento est, Eclo eclo,CentroDeComputo centro,Date fechaEntradaSicel,Date fechaBaja) throws ExcepcionIntegridad{
    	//Establecimiento est = EstablecimientoDAO.findExistentByPrimaryKey(idEstablecimiento);
    	CentroDeComputo centroEs = est.getCentroComputo();    	
    	if((!est.getActivoEstablecimiento())&& fechaBaja!=null){
    		int diasPos = Integer.parseInt(est.getParametro(Establecimiento.TIEMPO_POST_BAJA_TAMBO_PARA_INFORMAR_EVENTOS_ANTERIOR_A_BAJA));
    		Date feh = DateUtils.mas(fechaBaja,diasPos);
    		if(feh.before(fechaEntradaSicel))//si se paso con los dias
    			throw new ExcepcionIntegridad(MENSAJES.ESTABLECIMIENTO_INACTIVO, new String[] {est.getId().toString(),DateUtils.format(fechaBaja,"dd/MM/yyyy"),String.valueOf(diasPos)});
    		/*else
    			if(fechaEnvioLote.after(fechaBaja))//si la fecha que figura en el lote (Tenvio)
    				throw new ExcepcionIntegridad(MENSAJES.ESTABLECIMIENTO_INACTIVO, new String[] {est.getId().toString()});
    		*/	
		}
    	if(!est.getPropietario().getActivo()){
    		throw new ExcepcionIntegridad(
					MENSAJES.PROPIETARIO_INACTIVO, new String[] {est.getPropietario().getId().toString(),est.getId().toString()});
		}	
    	if(!est.getEstancia().getActivo()){
    		throw new ExcepcionIntegridad(
					MENSAJES.ESTANCIA_INACTIVA, new String[] {est.getId().toString(),est.getEstancia().getId().toString()});
    	}
    		
    	if (est.getEclo() != eclo) {
    		String numEclo = String.valueOf(eclo.getId());
			String numEst = String.valueOf(est.getId());
			String numEcloCorrecta = String.valueOf(est.getEclo().getId());
			throw new ExcepcionIntegridad(MENSAJES.ESTABLECIMIENTO_NO_EN_ECLO,new String[] {numEst,numEclo,numEcloCorrecta});
    	}
    	if((centroEs==null && centro!=null)||(centroEs!=null && centro==null)||(centroEs!=null && centro !=null && !centroEs.equals(centro))){
    		throw new ExcepcionIntegridad(MENSAJES.CENTRO_NO_CORRESPONDE,
    				new String[] {
    				est.getId().toString(),centroEs==null?" no informa bajo centro de computo":"informa con el centro de computos: "+centroEs.getId().toString(),centro==null?" no se informó centro":" se informó el centro: "+centro.getId().toString()});
    	}
    	//si no se informa centro y el tambo no tiene centro, o son iguales los centro no hay problema y todo sigue
    	ProcEstablecimiento object = new ProcEstablecimiento();
        object.setProcAnimals(new HashSet());
        object.setProcEvtEsts(new HashSet());
        object.setProcMsgsses(new HashSet());
        object.setEstablecimiento(est);
        return object;
    }
   
    


    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds ProcEstablecimiento object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static ProcEstablecimiento findByPrimaryKey(
         java.lang.Long id)
        throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        ProcEstablecimiento object = (ProcEstablecimiento) session.get(ProcEstablecimiento.class,
                id);

        return object;
    }
}
