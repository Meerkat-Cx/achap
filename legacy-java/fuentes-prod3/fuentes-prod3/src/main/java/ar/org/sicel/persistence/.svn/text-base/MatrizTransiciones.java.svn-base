/*
 * Created on 15/06/2005
 */
package ar.org.sicel.persistence;

import static ar.org.sicel.persistence.Animal.SECA_VACIA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;

/**
 * @author pablo
 */
public class MatrizTransiciones {

    public static Transicion getTransicion(String estadoOrigen, EvtAnimal evento)throws ExcepcionIntegridad {
//        List trans = null;
        Transicion result = null;
        try {
            if (evento.getClass() == EvtReproduccion.class) {
                EvtReproduccion evtReprod = (EvtReproduccion) evento;
                //MODIF si informan un aborto largo o alguna cria, viva o muerta, entonces inicia lactancia
                boolean iniciaLactancia;
                if(evtReprod.isIniciaLactancia())
                	iniciaLactancia = true;
                else
                	iniciaLactancia = evtReprod.getGestacionIniciaLactancia(evtReprod.getAbortoLargo());
                // result = TransicionDAO.findByOriEvtIniciaLact(estadoOrigen,
                //        evento.getClazzParaTransicion(), iniciaLactancia);
                result = TransicionDAO.findByOriEvtIniciaLact(estadoOrigen,evento.getNombreTipo(), iniciaLactancia);
            } else {
            	if (evento.getClass() == EvtEstado.class) {
            		EvtEstado evEstado  = (EvtEstado) evento;
            		result = TransicionDAO.findByEventoEstado(estadoOrigen,evEstado.getEstado());
            	} else {
            		if ((evento.getClass() == EvtPrenez.class)) {
            			Boolean esPositivo = ((EvtPrenez)evento).getEsPositivo();
            			result = TransicionDAO.findByEvtPrenez(estadoOrigen,esPositivo);
            		} else {
            	
            			// 	result = TransicionDAO.findByOriEvt(estadoOrigen, evento
            			//		.getClazzParaTransicion());
            			result = TransicionDAO.findByOriEvt(estadoOrigen, evento.getNombreTipo());
            		}
            	} 
            }
        } catch (HibernateException e) {
            throw new ErrorFatal("No se pudo leer de la tabla de Transiciones",
                    e);
        }
        return result;
    }

    /**
     * Calcular estado previo. Aqui solo deberia tomarse el estado del ultimo
     * evento, pero hay casos especiales. Se recorre hacia atras el array de
     * eventos del animal, buscando el que tenga un estado a partir del cual
     * calcular el nuevo. Si el algoritmo llega a una lactancia migrada no se
     * usa ya que es legacy y no tiene un estado confiable.
     * 
     * Ahora tiene en cuenta tambien la fecha, por lo que devuelve el estado
     * previo a una determinada fecha cualquiera
     * 
     * @param animal
     * @return ultimo estado del animal
     */
    public static EvtAnimal estadoPrevio(Animal animal, Date fecha,EvtAnimal ev) {
        if (animal.getEvtAnimals().size()<1)
            //return SECA_VACIA;
        	return null;
       // EvtAnimal ultimoEvt = (EvtAnimal) animal.getEvtAnimals().last();
        List<Object> listaEventos = new ArrayList<Object>();
        listaEventos.addAll(animal.getEvtAnimals());
        Collections.sort(listaEventos, new EventosPorFechaYTipo());
        int desde = listaEventos.size();
        ListIterator iter = listaEventos.listIterator(desde);
        EvtAnimal ultimoEvt = (EvtAnimal) iter.previous();
//      para el caso qeu es una transferencia implicita,primero se inserta el evento normal y despues la transferencia
        //pero la transferencia se genero antes por lo tanto puede no tener el estado que origino el otro evento.
        //ej:evennto repro que generar un cambio de estado, y a vi vez una trans implicita. primero se registra la trans
        //trans -- SV -->SV y despues rep SV---PV pero se almacenan primero la repr y despues la tran.
        if(ultimoEvt != null && ((ultimoEvt.getClass()==EvtTransferencia.class && ((EvtTransferencia)ultimoEvt).isImplicita()) || ultimoEvt.getClass() == EvtAnimalModificacion.class)){
        	if(iter.hasPrevious()){
        		ultimoEvt = (EvtAnimal) iter.previous();
        	}
        }
        if (ultimoEvt == null || ultimoEvt.getClass()==EvtLactanciaMigrada.class)
            //return SECA_VACIA;
        	return null;
        //comienzo codigo nuevo
        if (ultimoEvt.getFecha().after(fecha)||ultimoEvt.getFecha().compareTo(fecha)==0) { 
        	//si el evento ultimo es posterior a la fecha que quiero buscar, no sirve, tengo que buscar el adecuado
        	//return animal.getEstadoEnFecha(fecha,ev);
        	return animal.getEventoEnFecha(fecha,ev);
        }
        //fin codigo nuevo
        //return ultimoEvt.getEstadoAnimal();
        return ultimoEvt;
    }

    /**
     * @param animal
     * @param ev
     * @param msgs 
     * @return
     * @throws ExcepcionIntegridad
     */
    @SuppressWarnings("unchecked")
	public static void calcularTransicionEv(Animal animal, EvtAnimal ev, List msgs) throws ExcepcionIntegridad {
        if(ev.getClass() != EvtAnimalModificacion.class){
        	//String estadoPrevio = estadoPrevio(animal,ev.getFecha(),ev);
        	EvtAnimal evtt =estadoPrevio(animal,ev.getFecha(),ev);
        	String estadoPrevio =(evtt!=null)?evtt.getEstadoAnimal():SECA_VACIA;
        	// transicion
	        Transicion transicion = MatrizTransiciones.getTransicion(estadoPrevio, ev);
	        List evts = animal.getEventos(Evento.EVT_TIPO_BAJ);
	        /*if(estadoPrevio.equals(Animal.BAJA)&&transicion == null&&!evts.isEmpty()){
	        	EvtBaja evtBaja = (EvtBaja)evts.get(evts.size()-1);
	        	if(!evtBaja.getDestino().equals("VETA"))
	        		throw new ExcepcionIntegridad(MENSAJES.ANIMAL_DE_BAJA, new String[] { animal.getRegistroOrigen(),ev.getNombreTipo() });        	
	        }*/
	        if (transicion == null) {
	        	throw new ExcepcionIntegridad(MENSAJES.TRANSICION_NO_HALLADA, new String[] {estadoPrevio,ev.getNombreTipo()});
	            //ErrorFatal ef = new ErrorFatal(MENSAJES.TRANSICION_NO_HALLADA, new String[] {estadoPrevio,ev.getNombreTipo()});
	            //throw new ErrorFatal(ef.getInfo());
	        }
	       /* if(estadoPrevio.equals(Animal.BAJA)&&!evts.isEmpty()){
	        	EvtBaja evtBaja = (EvtBaja)evts.get(evts.size()-1);
	        	if(evtBaja.getDestino().equals("MUER"))
	        		throw new ExcepcionIntegridad(MENSAJES.ANIMAL_DE_BAJA, new String[] { animal.getRegistroOrigen(),ev.getNombreTipo() });        	
	        }*/
	        if(ev.getNombreTipo().equals(Evento.EVT_TIPO_TRA) && estadoPrevio.equals(Animal.BAJA) &&!evts.isEmpty()){
	        	//EvtBaja evtBaja = (EvtBaja)evts.get(evts.size()-1);
	        	EvtBaja evtBaja = (EvtBaja)evtt;
	        	if(evtBaja.getDestino().equals("MUER"))
	        		throw new ExcepcionIntegridad(MENSAJES.ANIMAL_DE_BAJA, new String[] { animal.getRegistroOrigen(),ev.getNombreTipo() });
	        	//String estadoAnteriorABaja = estadoPrevio(animal,evtBaja.getFecha(),ev);
	        	String estadoAnteriorABaja= animal.getEstadoPrevioAFecha(evtBaja);
	        	ev.setEstadoAnimal(estadoAnteriorABaja);
	        } 
	        else{
	        	ev.setEstadoAnimal(transicion.getEstadoDestino());
	        }
	        /*if(ev.getNombreTipo().equals(Evento.EVT_TIPO_TRA)&&estadoPrevio.equals(Animal.BAJA)&&!evts.isEmpty()){
	        	EvtBaja evtBaja = (EvtBaja)evts.get(evts.size()-1);
	        	if(!evtBaja.getDestino().equals("VETA"))
	        		throw new ExcepcionIntegridad(MENSAJES.ANIMAL_DE_BAJA, new String[] { animal.getRP(),animal.getRegistroOrigen(),ev.getNombreTipo() });
	        } */               
	        // devolver lista de mensajes con los errores o warning o mensajes de
	        // informacion al efectuar la transicion
	        if (transicion.getNivelError() != null)
	            // si la transicion es un error
	            if (transicion.getNivelError() >= ProcMsg.ERROR)
	                throw new ExcepcionIntegridad(transicion.getCodigoMensaje()
	                        .getId(), new String[] { estadoPrevio,
	                        transicion.getEvento(), transicion.getEstadoDestino() });
	            else {
	                String cm = transicion.getCodigoMensaje().getId();
	                Byte ne = transicion.getNivelError();
	                String gev = transicion.getEvento();
	                String ed = transicion.getEstadoDestino();
	                String[] vals = new String[] { estadoPrevio, gev, ed };
	                ProcMsg msg = ProcMsgDAO.create(cm, ne, vals);
	                //if (result == null)
	                 //   result = new LinkedList<ProcMsg>();
	                //result.add(msg);
	                msgs.add(msg);
	            }
	        // modificar el ev de parametro con el estado calculado
	        calcularIniciaFinalizaLactancia(ev, transicion);
        }
    //    return result;
    }
    private static boolean estaEnProduccion(Animal an,EvtAnimal ev){
    	//String previo = estadoPrevio(an,fecha,null);
    	//String previo =estadoPrevio(an,ev.getFecha(),ev);
    	EvtAnimal evtt =estadoPrevio(an,ev.getFecha(),ev);
    	String previo =(evtt!=null)?evtt.getEstadoAnimal():SECA_VACIA;
    	
    	if(previo.equals("PV")||previo.equals("PS")||previo.equals("PP"))
    		return true;
    	return false;
    }
    /**
     * Se calcula si finaliza o inicia lactancia la transicion y se coloca en el
     * evento. Normalmente la info de ini fin lact está en la transicion, pero
     * el evento transferencia depende de reglas que se deben controlar aparte,
     * por lo cual se generaliza y se calcula siempre aparte para salvar futuras
     * ampliaciones. Se toma en cuenta que el evento NO FUE agregado aún al
     * animal para determinar cual es el establecimiento anterior.
     * NUEVO: se agregro la condicion para cuando nace una cria muerta, el animal
     * @param evento
     *            El evento que está siendo agregado en el animal
     * @param transicion
     *            La transición que disparará el agregado del evento (puede
     *            fallar si hay errores indicados en el codigo de mensaje en la
     *            transición)
     * @throws ExcepcionIntegridad 
     */
    public static void calcularIniciaFinalizaLactancia(EvtAnimal evento,
            Transicion transicion) throws ExcepcionIntegridad {
        boolean finalizaLactancia = false;
        boolean iniciaLactancia = false;
        
        finalizaLactancia = transicion.getFinalizaLactancia();
        
        //Agregado para que la baja por transferencia no cierre lactancia
        if(finalizaLactancia && evento.getNombreTipo().equals(Evento.EVT_TIPO_BAJ)){
        	EvtBaja evtBaja = (EvtBaja)evento;
        	if((evtBaja.getDestino().equals("MUER")) && (estaEnProduccion(evtBaja.getAnimal(),evento)))
        		finalizaLactancia = true;
        	else
        		finalizaLactancia = false;
        	/*if(evtBaja.getDestino().equals("VETA")||(evtBaja.getDestino().equals("MUER") && (!estaEnProduccion(evtBaja.getAnimal(),evento.getFecha()))))
        		finalizaLactancia = false;*/
        }
                
        iniciaLactancia = transicion.getIniciaLactancia();
        //MODIF NUNCA la transferencia cierra lactancia.
//        if (evento.getClass() == EvtTransferencia.class) {
//            EvtTransferencia evtTransferencia = (EvtTransferencia) evento;  
//            // si finaliza o no lact depende de si el estab y prop es el mismo
//            /*
//            prop<> estab<> propestab<> transf
//            0       0       n/a         0       
//            0       1       0           0
//            0       1       1           1 conf TrFinLactEstabDifProp
//            1       0       n/a         0 conf TrFinLactDifPropMismoEstab
//            1       1       0           0 conf TrFinLactDifPropDifEstabMismoPropEstab
//            1       1       1           1
//            */
//            Establecimiento     establecimientoAnterior = evento.getAnimal().getEstablecimiento();
//            Propietario         propietarioAnterior     = evento.getAnimal().getPropietario();
//            Establecimiento     establecimientoNuevo    = evtTransferencia.getNuevoEstab();
//            Propietario         propietarioNuevo        = evtTransferencia.getNuevoProp();
//            Propietario         propEstabAnterior       = establecimientoAnterior.getPropietario();  
//            Propietario         propEstabNuevo          = establecimientoNuevo.getPropietario();
//            
//            boolean             difProp       = propietarioNuevo        !=  propietarioAnterior ;
//            boolean             difEstab      = establecimientoNuevo    !=  establecimientoAnterior ;
//            boolean             difPropEstab  = propEstabNuevo          !=  propEstabAnterior ;
//
//            boolean TrFinLactEstabDifProp                   = Configuracion.getValorReglaProceso(CONF.TrFinLactEstabDifProp,new Date()); 
//            boolean TrFinLactDifPropMismoEstab              = Configuracion.getValorReglaProceso(CONF.TrFinLactDifPropMismoEstab,new Date());
//            boolean TrFinLactDifPropDifEstabMismoPropEstab  = Configuracion.getValorReglaProceso(CONF.TrFinLactDifPropDifEstabMismoPropEstab,new Date());
//            
//            if (
//                    ( !difProp && difEstab && difPropEstab && TrFinLactEstabDifProp ) ||
//                    ( difProp && !difEstab && TrFinLactDifPropMismoEstab ) ||
//                    ( difProp && !difEstab && !difPropEstab && TrFinLactDifPropDifEstabMismoPropEstab ) ||
//                    ( difProp && difEstab && difPropEstab )
//                )
//            {
//                finalizaLactancia = true;
//                iniciaLactancia = true;
//            }
//        }
        evento.setFinalizaLactancia(finalizaLactancia);
        evento.setIniciaLactancia(iniciaLactancia);
    }

}
