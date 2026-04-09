package ar.org.sicel.persistence;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.Cria;
import ar.org.sicel.proc.v1.lote.Senasa;


/**
 * <p>
 * Factory class.
 * Is able to find and create objects of type EvtCria.
 * Hibernate inheritance class
 * Those can be described as follows:
 * </p>
 * @see ar.org.sicel.persistence.EvtCria
 */
public abstract class EvtCriaDAO {
    // ---------------- create method --------------------
	public static EvtCria create(String nombre,Boolean estadoEsVivo,Boolean inscribir,String rp,String tamanio, Float peso,  Integer dificultad,   String sexo,Senasa se,Cria[] xmlCrias,int orden) throws ExcepcionIntegridad {
		
		if(!StringUtils.isEmpty(rp)){//hago esto pra determinar si informan como mellidos dos animales con el mismo rp
		for (int i=0;i<xmlCrias.length;i++) {
			if ((xmlCrias[i].getRp() !=null) && (xmlCrias[i].getRp().equals(rp)) && (i != orden) )
				throw new ExcepcionIntegridad(MENSAJES.INFORMA_MISMO_ANIMAL_EN_MISMO_PARTO,new String[] { rp,sexo });
		}
		}
		EvtCria cria = new EvtCria();
		
		cria.setEstadoPerinatalEsVivo(estadoEsVivo);
		cria.setPesoKg(peso);
		cria.setTamanio(tamanio);
		if (dificultad != null)
			cria.setDificultadNac(dificultad.toString());
		cria.setSexo(sexo);
		
		//estos no son persistentes (se usan para dar de alta el animal, de ser requerido)		
		cria.setInscribir(inscribir);
		cria.setNombre(nombre);
		cria.setRP(rp);
		if(se!=null){
			cria.setRpSenasa(se.getRpSenasa());
			cria.setCodigo(se.getDigitoVerficador());
		}
		
		return cria;
	}


    // ---------------- finder methods  ----------------------

    /**
     *
     * Finds EvtCria object by its primary key.
     * In Hibernate, this is just a call to get().
     *
     */
    public static EvtCria findByPrimaryKey(
        java.lang.Long id) throws org.hibernate.HibernateException {
    	Session session = HibernateFactory.getSession();
        EvtCria object = (EvtCria) session.get(EvtCria.class, id);

        return object;
    }
    /**
     * Como no esta mapeado directamente el evtCria, tengo q entrar por otro lado
     * @param animal
     * @return
     */
    
    public static EvtCria findByAnimal(Animal animal) throws org.hibernate.HibernateException {
    	EvtReproduccion rep = EvtReproduccionDAO.findNacimientoCria(animal);
    	if(rep!=null){
    		if(!rep.getEvtCrias().isEmpty()){
    			Iterator it = rep.getEvtCrias().iterator();
    			while(it.hasNext()){
    				EvtCria ec = (EvtCria)it.next();
    				if(ec.getCria()!=null && ec.getCria().equals(animal))
    					return ec;
    			}
    		}
    			
		}
    	return null;
	}
    

	
}
