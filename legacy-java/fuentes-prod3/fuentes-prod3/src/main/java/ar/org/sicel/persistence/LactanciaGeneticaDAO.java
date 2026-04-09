package ar.org.sicel.persistence;

import java.util.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.proc.v1.lote.types.STDestino;
import ar.org.sicel.proc.v1.lote.types.STMotivo;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.util.StringUtils;

public class LactanciaGeneticaDAO {
	
	@SuppressWarnings({ "unchecked","deprecation"  })
	public static void insertNew(EvtLactancia lac,Hembra hembra,EvtLactancia enCurso,List<Lactancia> listLactancias){
		Session session = HibernateFactory.getSession();
		LactanciaGenetica nuevaLac = new LactanciaGenetica();
		nuevaLac.setIdLactancia(lac.getId());
		EvtAnimal eventoInicioLactancia = ((EvtLactancia)lac).getEventoIniciaLactancia();
		if(eventoInicioLactancia == null){
			EvtAnimal ev = EvtAnimalDAO.findPrimerEvento(hembra);
		if(ev!=null)
			try {
				hembra.setEstadoRetroactivo(ev.getFecha(), new ArrayList(), null);
			} catch (ExcepcionIntegridad e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (lac.isEsCerrada()){
			nuevaLac.setTambo(lac.getEstablecimiento());
			nuevaLac.setEclo(nuevaLac.getTambo().getEclo());
			nuevaLac.setProp(nuevaLac.getTambo().getPropietario());
			nuevaLac.setEstancia(nuevaLac.getTambo().getEstancia());
			nuevaLac.setCantOrdenie(lac.getOrdenies());
			nuevaLac.setFechaFinalizacionLactancia(DateUtils.mas(lac.getFechaInicio(), lac.getDias()));
			nuevaLac.setDiasDuracionLac(((EvtLactancia)lac).getDias());
			ArrayList<EvtControlAnimal> controles = new ArrayList<EvtControlAnimal>(hembra.getEventosEntre(lac.getFechaInicio(), DateUtils.mas(lac.getFechaInicio(),lac.getDias()),Evento.EVT_TIPO_CONTROL_ANIMAL));
			if (controles.isEmpty()){
				nuevaLac.setFechaUltimoControl(null);
				nuevaLac.setEstanciaUltimoControl(null);
				Date fecha = EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(lac.getEstablecimiento().getEstancia());
				if (fecha != null)
					nuevaLac.setFechaUltimoControlEstancia(fecha);/*tener en cuenta migradas*/
				else
					nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimaLactCerroEnEstancia(lac.getEstablecimiento().getEstancia()));/*tener en cuenta migradas*/
			}
			else{
				EvtControlAnimal ultimoC =controles.get(controles.size()-1);
				nuevaLac.setFechaUltimoControl(ultimoC.getFecha());
				nuevaLac.setEstanciaUltimoControl(ultimoC.getEstablecimiento().getEstancia().getId());
				Date fecha = EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(ultimoC.getEstablecimiento().getEstancia());
				if (fecha != null)// a mi entender nunca seria nulo ya que minimamente esta el ultimo control de la lactancia
					nuevaLac.setFechaUltimoControlEstancia(fecha);/*tener en cuenta migradas*/
				else
					nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimaLactCerroEnEstancia(ultimoC.getEstablecimiento().getEstancia()));/*tener en cuenta migradas*/
			}
		}
		else{
			ArrayList<EvtControlAnimal> controles = new ArrayList<EvtControlAnimal>(hembra.getControlesUltimaLactanciaAbierta()); 
			if (controles.isEmpty()){ 
				//aca seria cero o nulo?
				nuevaLac.setCantOrdenie(lac.getOrdenies());
				nuevaLac.setFechaUltimoControl(null);
				nuevaLac.setEstanciaUltimoControl(null);
				Date fecha = EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(eventoInicioLactancia.getEstablecimiento().getEstancia());
				if (fecha != null)
					nuevaLac.setFechaUltimoControlEstancia(fecha);/*tener en cuenta migradas*/
				else
					nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimaLactCerroEnEstancia(lac.getEstablecimiento().getEstancia()));/*tener en cuenta migradas*/
				//nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(eventoInicioLactancia.getEstablecimiento().getEstancia()));
				nuevaLac.setTambo(eventoInicioLactancia.getEstablecimiento());
				nuevaLac.setEclo(nuevaLac.getTambo().getEclo());
				nuevaLac.setEstancia(nuevaLac.getTambo().getEstancia());
				nuevaLac.setProp(nuevaLac.getTambo().getPropietario());
				nuevaLac.setDiasDuracionLac(null);
			} 
			else{ 
				EvtControlAnimal ultimo = null; 
				for(EvtControlAnimal evtCtrl : controles){ 
					if (ultimo == null || evtCtrl.getFecha().after(ultimo.getFecha())) 
						ultimo = evtCtrl;
				} 
				nuevaLac.setTambo(ultimo.getEstablecimiento());
				nuevaLac.setEstancia(nuevaLac.getTambo().getEstancia());
				nuevaLac.setProp(nuevaLac.getTambo().getPropietario());
				nuevaLac.setEclo(nuevaLac.getTambo().getEclo());
				if (!ultimo.isCalostro()) 
					nuevaLac.setCantOrdenie(lac.getOrdenies()); 
				else 
					nuevaLac.setCantOrdenie(new Integer(2));
				nuevaLac.setDiasDuracionLac(DateUtils.diasEntre(lac.getFechaInicio(),ultimo.getFecha()));
				nuevaLac.setFechaUltimoControl(ultimo.getFecha());
				nuevaLac.setEstanciaUltimoControl(ultimo.getEstablecimiento().getEstancia().getId());
				Date fecha = EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(ultimo.getEstablecimiento().getEstancia());
				if (fecha != null)
					nuevaLac.setFechaUltimoControlEstancia(fecha);
				else
					nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimaLactCerroEnEstancia(ultimo.getEstablecimiento().getEstancia()));
			}
			nuevaLac.setFechaFinalizacionLactancia(null);
		} 
		nuevaLac.setAnimal(hembra); 
		nuevaLac.setMesInicioLactancia(new Integer(DateUtils.menos(lac.getFechaInicio(), 1).getMonth())+1); 
		nuevaLac.setEdadMesesInicioLactancia(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),lac.getFechaInicio()))); 
		nuevaLac.setNroLactanciaInformado(lac.getNroLact()); 
		nuevaLac.setMesNacimiento(new Integer(hembra.getFechaNac().getMonth())+1); 
		nuevaLac.setAnioNacimiento(new Integer(hembra.getFechaNac().getYear())+1900); 
		nuevaLac.setAnioInicioLactancia(new Integer(lac.getFechaInicio().getYear())+1900);
		Lactancia lacAnterior = ((Hembra)((EvtLactancia)lac).getAnimal()).getLactanciaNumeroSinFiltroLeche(((EvtLactancia)lac).getNroLact()-1);
		Lactancia uno = ((Hembra)((EvtLactancia)lac).getAnimal()).getLactanciaNumeroSinFiltroLeche(1);
		if (uno != null){
			if (uno instanceof EvtLactancia)
				nuevaLac.setEdadMesesInicioLactanciaUno(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),DateUtils.menos(uno.getFechaInicio(),1))));
			else
				nuevaLac.setEdadMesesInicioLactanciaUno(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),uno.getFechaInicio())));
		}
		else{
				if (enCurso != null && enCurso.getNroLact().equals(new Integer(1))){
					uno = enCurso;
					nuevaLac.setEdadMesesInicioLactanciaUno(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),DateUtils.menos(uno.getFechaInicio(),1))));
				}
				else
					nuevaLac.setEdadMesesInicioLactanciaUno(null);
		}
		//la variable 'eventoIniciaLactancia' y 'repro' son iguales, aunque repro esta declarada del 
		//tipo EvtReproduccion. Entones, si 'repro' es null, quiere decir que 'eventoIniciaLactancia' 
		//es del tipo EvtEstado (en el sistema todavia no hay eventos del tipo EvEstado)
		EvtReproduccion repro = eventoInicioLactancia.getNombreTipo().equals(Evento.EVT_TIPO_REP) ? EvtReproduccionDAO.findByPrimaryKey(eventoInicioLactancia.getId()) : null;
		if (eventoInicioLactancia != null){ 
			EvtServicio servicio = repro != null ? repro.getEvtServicio() : null; //por si es estado
			//////////////////
			if(repro!=null && repro.getEvtServicio()!=null ){
				int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
				int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
					Date diaMasLejano = DateUtils.menos(repro.getFecha(), gMax);
					Date diaMasCercano = DateUtils.menos(repro.getFecha(), gMin);
					if (!DateUtils.entre(repro.getEvtServicio().getFecha(), diaMasLejano,diaMasCercano)){
						servicio = null;
					}
			}
			//////////////////
			Date fechaPivote = null;
			if (servicio != null){ 
				if (lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)){
					if (lacAnterior instanceof EvtLactanciaMigrada)
						fechaPivote = lacAnterior.getFechaInicio();
					else
						fechaPivote = DateUtils.menos(lacAnterior.getFechaInicio(),1);
					nuevaLac.setDiasEntreServicioLacAnt(DateUtils.diasEntre(fechaPivote, servicio.getFecha())); 
				} 
				else 
					nuevaLac.setDiasEntreServicioLacAnt(null); 
			} 
			else{
				//quiere decir que no habia servicio para el parto, o era un evento Estado
				if (lacAnterior != null){
					if (lacAnterior instanceof EvtLactanciaMigrada)
						fechaPivote = lacAnterior.getFechaInicio();
					else
						fechaPivote = DateUtils.menos(lacAnterior.getFechaInicio(),1);
					if (lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)) 
						nuevaLac.setDiasEntreServicioLacAnt(DateUtils.diasEntre(fechaPivote,DateUtils.menos(repro.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO))); 
					else 
						nuevaLac.setDiasEntreServicioLacAnt(null);
				}else
					nuevaLac.setDiasEntreServicioLacAnt(null);
			} 
		}
		nuevaLac.setDiasEntreLactanciaAnterior(lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)? (DateUtils.diasEntre(lacAnterior.getFechaInicio(),((EvtLactancia)lac).getFechaInicio())) : null); 
		nuevaLac.setAnimalRegistro(completarDigitos(hembra)); 
		nuevaLac.setRpAnimal(lac.getAnimal().getRP());
		if (lac.getDias() >= 305){
			EvtLactancia auxLac = lac.getLactancia(305);//cargamos la lactancia a 305 dias
			nuevaLac.setLeche305(redondear(auxLac.getLeche()));
			nuevaLac.setGrasa305(redondear(auxLac.getGrasaAbsoluto()));
			nuevaLac.setPorGrasa305(redondearPorcentajes(auxLac.getPorcentajeGrasa()));
			nuevaLac.setProteinas305(redondear(auxLac.getProteinasAbsoluto()));
			nuevaLac.setPorProteinas305(redondearPorcentajes(auxLac.getPorcentajeProteinas()));
		}
		else{
			//14/06/2012 hacer como migradas, cargar los reales que son < a 305 dias
			nuevaLac.setLeche305(redondear(lac.getLeche()));
			nuevaLac.setGrasa305(redondear(lac.getGrasaAbsoluto()));
			nuevaLac.setPorGrasa305(redondearPorcentajes(lac.getPorcentajeGrasa()));
			nuevaLac.setProteinas305(redondear(lac.getProteinasAbsoluto()));
			nuevaLac.setPorProteinas305(redondearPorcentajes(lac.getPorcentajeProteinas()));
			/*nuevaLac.setLeche305(null);
			nuevaLac.setGrasa305(null);
			nuevaLac.setPorGrasa305(null);
			nuevaLac.setProteinas305(null);
			nuevaLac.setPorProteinas305(null);*/
		}
		nuevaLac.setCategoria(lac.getAnimal().getCategoria()); 
		nuevaLac.setCel(null); 
		nuevaLac.setTipoLac(lac.isEsOficial() ? LactanciaGenetica.TIPO_LACTANCIA_OFICIAL : LactanciaGenetica.TIPO_LACTANCIA_NO_OFICIAL); 
		nuevaLac.setTipoInicioLactancia(eventoInicioLactancia.getNombreTipo().equals(Evento.EVT_TIPO_REP) ? ( 
											repro.getAbortoLargo() ? LactanciaGenetica.TIPO_INICIO_ABORTO_LARGO : 
												LactanciaGenetica.TIPO_INICIO_PARTO) : LactanciaGenetica.TIPO_INICIO_ESTADO);
		nuevaLac.setDcon(null); 
		nuevaLac.setTlac(null); 
		nuevaLac.setEac(null); 
		nuevaLac.setCnor(null); 
		nuevaLac.setIso(completarDigitosISO(hembra)); 
		nuevaLac.setElac(lac.isEsCerrada() ? LactanciaGenetica.TIPO_LACTANCIA_CERRADA : LactanciaGenetica.TIPO_LACTANCIA_EN_CURSO);
		nuevaLac.setLecheReal(redondear(lac.getLeche()));
		nuevaLac.setGrasaReal(redondear(lac.getGrasaAbsoluto()));
		nuevaLac.setPorGrasaReal(redondearPorcentajes(lac.getPorcentajeGrasa()));
		nuevaLac.setProteinasReal(redondear(lac.getProteinasAbsoluto()));
		nuevaLac.setPorProteinasReal(redondearPorcentajes(lac.getPorcentajeProteinas()));
		nuevaLac.setFechaNacimiento(lac.getAnimal().getFechaNac());
		nuevaLac.setFechaParto(repro != null ? repro.getFecha() : null);
		EvtServicio ultimoService = repro != null ? repro.getEvtServicio() : null;
		////
		if(repro!=null && repro.getEvtServicio()!=null ){
			int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
			int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
				Date diaMasLejano = DateUtils.menos(repro.getFecha(), gMax);
				Date diaMasCercano = DateUtils.menos(repro.getFecha(), gMin);
				if (!DateUtils.entre(repro.getEvtServicio().getFecha(), diaMasLejano,diaMasCercano)){
					ultimoService = null;
				}
		}
		////
		EvtReproduccion reproAnterior = null;
		Date fechaPivote = null;
		if (lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)){
			if (lacAnterior instanceof EvtLactancia){
				reproAnterior = (EvtReproduccion)((EvtLactancia)lacAnterior).getEventoIniciaLactancia();
				fechaPivote = reproAnterior.getFecha();
			}
			else
				fechaPivote = lacAnterior.getFechaInicio();
			nuevaLac.setFechaInicioPartoAnterior(fechaPivote);
			List<EvtServicio> servicios = null;
			//if (repro.getEvtServicio() != null)
			if (ultimoService != null)
				servicios = lac.getAnimal().getEventosEntre(fechaPivote,ultimoService.getFecha(),Evento.EVT_TIPO_SVC);
			else
				servicios = lac.getAnimal().getEventosEntre(fechaPivote, DateUtils.menos(eventoInicioLactancia.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO),Evento.EVT_TIPO_SVC);
			if(servicios != null && !servicios.isEmpty()){
				nuevaLac.setFechaPrimerServicio(servicios.get(0).getFecha());
				nuevaLac.setIntervaloPartoPrimerServicio(DateUtils.diasEntre(fechaPivote,nuevaLac.getFechaPrimerServicio()));
			}
			else{
				nuevaLac.setFechaPrimerServicio(null);
				nuevaLac.setIntervaloPartoPrimerServicio(null);
			}
			
			//nuevaLac.setFechaPrimerServicio(servicios != null && !servicios.isEmpty() ?  servicios.get(0).getFecha() : null);//paso arriba
			if (ultimoService == null){
				nuevaLac.setFechaUltimoServicio(DateUtils.menos(DateUtils.menos(lac.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
				nuevaLac.setIntervaloPrimerServicioUltimoServicio((nuevaLac.getFechaPrimerServicio()==null)?null:(DateUtils.diasEntre(nuevaLac.getFechaPrimerServicio(), DateUtils.menos(DateUtils.menos(lac.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO))));
				nuevaLac.setNumerosDeServicio(servicios != null && !servicios.isEmpty() ? servicios.size()+1 : null);
			}
			else{
				nuevaLac.setFechaUltimoServicio(ultimoService.getFecha());
				nuevaLac.setIntervaloPrimerServicioUltimoServicio((nuevaLac.getFechaPrimerServicio()==null)?null:(DateUtils.diasEntre(nuevaLac.getFechaPrimerServicio(),ultimoService.getFecha())));
				nuevaLac.setNumerosDeServicio(servicios != null && !servicios.isEmpty() ? servicios.indexOf(ultimoService)+1 : null);
			}
			/*if (servicios != null && !servicios.isEmpty())
				nuevaLac.setIntervaloPartoPrimerServicio(DateUtils.diasEntre(fechaPivote,nuevaLac.getFechaPrimerServicio()));
			else
				nuevaLac.setIntervaloPartoPrimerServicio(null);*///paso arriba
			/*if (ultimoService != null)
				nuevaLac.setNumerosDeServicio(servicios != null && !servicios.isEmpty() ? servicios.indexOf(ultimoService)+1 : null);
			else
				nuevaLac.setNumerosDeServicio(servicios != null && !servicios.isEmpty() ? servicios.size()+1 : null);*///pasado arriba
			//nuevaLac.setFechaInicioPartoAnterior(fechaPivote);
		}
		else{
			if(lacAnterior == null){
				List<EvtServicio> servicios = null;
				if(ultimoService!=null)
					servicios = lac.getAnimal().getEventosEntre(nuevaLac.getFechaNacimiento(),ultimoService.getFecha(),Evento.EVT_TIPO_SVC);
				nuevaLac.setFechaPrimerServicio(servicios != null && !servicios.isEmpty() ?  servicios.get(0).getFecha() : null);
			}
			nuevaLac.setIntervaloPrimerServicioUltimoServicio(null);
			nuevaLac.setNumerosDeServicio(null);
			nuevaLac.setIntervaloPartoPrimerServicio(null);
			nuevaLac.setFechaInicioPartoAnterior(null);
		}
		/*if (ultimoService == null)
			nuevaLac.setFechaUltimoServicio(DateUtils.menos(DateUtils.menos(lac.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
		else
			nuevaLac.setFechaUltimoServicio(ultimoService.getFecha());*///pasado arriba
		/*String gE = null;
		Establecimiento estab = nuevaLac.getTambo();
		gE = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		String gEstab = null;
		gEstab = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getEstancia().getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getEstancia().getId().toString(),"0",new Integer(5-estab.getEstancia().getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		nuevaLac.setGeEstab(gEstab + (lac.getFecha().getMonth() < 7 ? "1" : "2"));
		nuevaLac.setGe(gE);
		nuevaLac.setGeEstabDos(gEstab);*/
		String gE = null;
		Establecimiento estab = nuevaLac.getTambo();
		gE = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		String gEstab = null;
		gEstab = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		String epoca1 =lac.getFechaInicio().getMonth() <= 6 ? "1" : "2";//si es menor inclusive que julio = 1 sino 2
		String epoca2 =(lac.getFechaInicio().getMonth() >=2 &&  lac.getFechaInicio().getMonth() <=7 ) ? "1" : "2"; //si esta entre marzo y agosto inclusive = 1 sino 2
		
		nuevaLac.setGeEstab(gEstab + epoca1);
		nuevaLac.setGe(null);
		nuevaLac.setGeEstabDos(null);
		nuevaLac.setGeDosA(gE+epoca2 );
		String cccr = completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
			new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		
		nuevaLac.setGeCP(cccr + epoca2);
		nuevaLac.setGeCR(cccr + epoca1);
		
		List<EvtBaja> bajas = hembra.getEventos(Evento.EVT_TIPO_BAJ);
		EvtBaja baja = null;
		if (bajas != null && !bajas.isEmpty()){
			EvtBaja primera = bajas.get(0);
			if (primera.getDestino().equals(STDestino.MUER.toString()))
				baja = primera;
			else
				baja = bajas.get(bajas.size()-1);
		}
		if (baja != null){
			if (baja.getDestino().equals(STDestino.MUER.toString()))
				nuevaLac.setBaja(new Integer(1));
			else if (baja.getDestino().equals(STDestino.VETA.toString()))
				nuevaLac.setBaja(new Integer(2));
			else
				nuevaLac.setBaja(new Integer(3));
			if (baja.getMotivo().equals(STMotivo.REPR.toString()))
				nuevaLac.setMotivoBaja(new Integer(1));
			else if (baja.getMotivo().equals(STMotivo.SANI.toString()))
				nuevaLac.setMotivoBaja(new Integer(2));
			else if (baja.getMotivo().equals(STMotivo.PROD.toString()))
				nuevaLac.setMotivoBaja(new Integer(3));
			else if (baja.getMotivo().equals(STMotivo.UBRE.toString()))
				nuevaLac.setMotivoBaja(new Integer(4));
			else	
				nuevaLac.setMotivoBaja(new Integer(5));
		}
		Lactancia lacPosterior = null;
		/*si es en curso, no hay posterior*/
		if (lac.isEsCerrada()){
			lacPosterior = ((Hembra)((EvtLactancia)lac).getAnimal()).getLactanciaNumeroSinFiltroLeche(((EvtLactancia)lac).getNroLact()+1);
			if (lacPosterior == null)
				if (enCurso != null && (lac.getNroLact() - enCurso.getNroLact() == -1))
					lacPosterior = enCurso;
				else
					lacPosterior = null;
		}
		if (lacPosterior != null){
			eventoInicioLactancia = ((EvtLactancia)lacPosterior).getEventoIniciaLactancia();
			EvtReproduccion reproPosterior = eventoInicioLactancia.getNombreTipo().equals(Evento.EVT_TIPO_REP) ? EvtReproduccionDAO.findByPrimaryKey(eventoInicioLactancia.getId()) : null;
			EvtServicio servicioPost = reproPosterior != null ? reproPosterior.getEvtServicio() : null;
			////
			if(reproPosterior!=null && reproPosterior.getEvtServicio()!=null ){
				int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
				int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
					Date diaMasLejano = DateUtils.menos(reproPosterior.getFecha(), gMax);
					Date diaMasCercano = DateUtils.menos(reproPosterior.getFecha(), gMin);
					if (!DateUtils.entre(reproPosterior.getEvtServicio().getFecha(), diaMasLejano,diaMasCercano)){
						servicioPost = null;
					}
			}
			////
			
			if (eventoInicioLactancia != null){ 
				 //por si es estado 
				if (servicioPost != null) 
					nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(DateUtils.menos(lac.getFechaInicio(),1),servicioPost.getFecha())); 
				else//quiere decir que no habia servicio para el parto, o era un evento Estado 
					nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(DateUtils.menos(lac.getFechaInicio(),1),DateUtils.menos(reproPosterior.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO))); 
			}
			nuevaLac.setDiasEntreLactanciaAnteriorDos(DateUtils.diasEntre(((EvtLactancia)lac).getFechaInicio(),lacPosterior.getFechaInicio()));
			fechaPivote = reproPosterior.getFecha();
			List<EvtServicio> serviciosDos = null;
			if (servicioPost != null)
				serviciosDos = lac.getAnimal().getEventosEntre(repro.getFecha(),servicioPost.getFecha(),Evento.EVT_TIPO_SVC);
			else
				serviciosDos = lac.getAnimal().getEventosEntre(repro.getFecha(),DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO),Evento.EVT_TIPO_SVC);
			//nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty() ? DateUtils.diasEntre(serviciosDos.get(0).getFecha(),serviciosDos.get(servicios.size()-1).getFecha()) : null);
			if (servicioPost == null)
				nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty()? 
																		DateUtils.diasEntre(serviciosDos.get(0).getFecha(),DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO)) 
																		: null);
			else
				nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(serviciosDos.get(0).getFecha(),servicioPost.getFecha()) : null);
			nuevaLac.setFechaInicioPartoSiguiente(reproPosterior != null ? reproPosterior.getFecha() : null);
			nuevaLac.setFechaPrimerServicioDos(serviciosDos != null && !serviciosDos.isEmpty() ?  serviciosDos.get(0).getFecha() : null);
			if (servicioPost != null)
				nuevaLac.setFechaUltimoServicioDos(servicioPost.getFecha());
			else
				nuevaLac.setFechaUltimoServicioDos(DateUtils.menos(reproPosterior.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
			nuevaLac.setIntervaloPartoPrimerServicioDos(repro != null && serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(repro.getFecha(),serviciosDos.get(0).getFecha()) : null);
			
			if (servicioPost != null)
				nuevaLac.setNumerosDeServicioDos(serviciosDos != null && !serviciosDos.isEmpty() ? serviciosDos.indexOf(servicioPost)+1 : null);
			else
				nuevaLac.setNumerosDeServicioDos(serviciosDos != null && !serviciosDos.isEmpty() ? serviciosDos.size()+1 : null);
		}
		else{
			nuevaLac.setDiasEntreServicioLacAntDos(null);
			nuevaLac.setDiasEntreLactanciaAnteriorDos(null);
			nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(null);
			nuevaLac.setFechaInicioPartoSiguiente(null);
			nuevaLac.setFechaPrimerServicioDos(null);
			nuevaLac.setFechaUltimoServicioDos(null);
			nuevaLac.setNumerosDeServicioDos(null);
		}
		nuevaLac.setEstabPril(!listLactancias.isEmpty() && listLactancias.get(0) != null ? ((Evento)listLactancias.get(0)).getEstablecimiento().getEstancia().getId() : null);
		nuevaLac.setIsop(completarDigitosISO(hembra.getPadre()));
		nuevaLac.setIsoam(completarDigitosISO(hembra.getMadreGenetica() != null ? hembra.getMadreGenetica().getPadre() : null));
		nuevaLac.setIsom(completarDigitosISO(hembra.getMadreGenetica()));
		nuevaLac.setSistemaSicel(LactanciaGenetica.TIPO_LACTANCIA_SICEL3);
		session.save(nuevaLac);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void insertMigradaNew(EvtLactanciaMigrada lac,Hembra hembra,EvtLactancia enCurso,List<Lactancia> listLactancias){
		Session session = HibernateFactory.getSession();
		LactanciaGenetica nuevaLac = new LactanciaGenetica();
		nuevaLac.setIdLactancia(lac.getId());
		nuevaLac.setTambo(lac.getEstablecimiento());
		nuevaLac.setEclo(nuevaLac.getTambo().getEclo());
		nuevaLac.setEstancia(nuevaLac.getTambo().getEstancia());
		nuevaLac.setProp(nuevaLac.getTambo().getPropietario());
		nuevaLac.setCantOrdenie(lac.getOrdenies());
		nuevaLac.setFechaFinalizacionLactancia(DateUtils.mas(lac.getFechaInicio(), lac.getDias()));
		nuevaLac.setDiasDuracionLac(lac.getDias());
		Date fechaUltimoControlEstancia = EvtOrdenieAnimalDAO.fechaUltimoControlEnEstancia(lac.getEstablecimiento().getEstancia());
		if (fechaUltimoControlEstancia != null)
			nuevaLac.setFechaUltimoControlEstancia(fechaUltimoControlEstancia);
		else
			nuevaLac.setFechaUltimoControlEstancia(EvtOrdenieAnimalDAO.fechaUltimaLactCerroEnEstancia(lac.getEstablecimiento().getEstancia())); /*la mas cercana a la fecha actual*/
		nuevaLac.setFechaUltimoControl(DateUtils.mas(lac.getFechaInicio(), lac.getDias()));
		nuevaLac.setAnimal(hembra); 
		nuevaLac.setMesInicioLactancia(new Integer(DateUtils.menos(lac.getFechaInicio(), 1).getMonth())+1); 
		nuevaLac.setEdadMesesInicioLactancia(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),lac.getFechaInicio())));
		nuevaLac.setNroLactanciaInformado(lac.getNroLact()); 
		nuevaLac.setMesNacimiento(new Integer(hembra.getFechaNac().getMonth())+1); 
		nuevaLac.setAnioNacimiento(new Integer(hembra.getFechaNac().getYear())+1900); 
		nuevaLac.setAnioInicioLactancia(new Integer(lac.getFechaInicio().getYear())+1900);
		Lactancia lacAnterior = ((Hembra)lac.getAnimal()).getLactanciaNumeroSinFiltroLeche(lac.getNroLact()-1);
		Lactancia uno = ((Hembra)lac.getAnimal()).getLactanciaNumeroSinFiltroLeche(1);
		if (uno != null) /*la uno se tiene en cuenta con o sin leche, grasa y prot NROLAC = 1*/
			nuevaLac.setEdadMesesInicioLactanciaUno(new Integer(DateUtils.mesesEntre(((Animal)hembra).getFechaNac(),uno.getFechaInicio())));
		else
			nuevaLac.setEdadMesesInicioLactanciaUno(null);
		if (lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)){ 
			nuevaLac.setDiasEntreServicioLacAnt(DateUtils.diasEntre(lacAnterior.getFechaInicio(),DateUtils.menos(lac.getFechaInicio(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO)));
			nuevaLac.setFechaInicioPartoAnterior(lacAnterior.getFechaInicio());
		}
		else{ 
			nuevaLac.setDiasEntreServicioLacAnt(null);
			nuevaLac.setFechaInicioPartoAnterior(null);
		}
		nuevaLac.setFechaUltimoServicio(DateUtils.menos(lac.getFechaInicio(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
		nuevaLac.setDiasEntreLactanciaAnterior(lacAnterior != null && (lac.getNroLact() - lacAnterior.getNroLact() == 1)? (DateUtils.diasEntre(lacAnterior.getFechaInicio(), lac.getFechaInicio())) : null); 
		nuevaLac.setAnimalRegistro(completarDigitos(hembra)); 
		nuevaLac.setRpAnimal(lac.getAnimal().getRP());
		nuevaLac.setLeche305(redondear(lac.getLeche()));
		nuevaLac.setGrasa305(redondear(lac.getGrasaAbsoluto()));
		nuevaLac.setPorGrasa305(redondearPorcentajes(lac.getPorcentajeGrasa()));
		nuevaLac.setProteinas305(redondear(lac.getProteinasAbsoluto()));
		nuevaLac.setPorProteinas305(redondearPorcentajes(lac.getPorcentajeProteinas()));
		nuevaLac.setCategoria(lac.getAnimal().getCategoria()); 
		nuevaLac.setCel(null); 
		nuevaLac.setTipoLac(lac.getDcon() == null || lac.getDcon().equals(new Integer(18)) ? LactanciaGenetica.TIPO_LACTANCIA_OFICIAL : LactanciaGenetica.TIPO_LACTANCIA_NO_OFICIAL); 
		nuevaLac.setTipoInicioLactancia(lac.getIlac().equals(new Integer(15)) ? LactanciaGenetica.TIPO_INICIO_ABORTO_LARGO : 
												LactanciaGenetica.TIPO_INICIO_PARTO);
		nuevaLac.setDcon(null); 
		nuevaLac.setTlac(null); 
		nuevaLac.setEac(null); 
		nuevaLac.setCnor(null); 
		nuevaLac.setIso(completarDigitosISO(hembra)); 
		//nuevaLac.setAnimal(lac.getAnimal()); 
		nuevaLac.setElac(LactanciaGenetica.TIPO_LACTANCIA_CERRADA);
		/*nuevaLac.setLecheReal(redondear(lac.getLeche()));
		nuevaLac.setGrasaReal(redondear(lac.getGrasaAbsoluto()));
		nuevaLac.setPorGrasaReal(redondearPorcentajes(lac.getPorcentajeGrasa()));
		nuevaLac.setProteinasReal(redondear(lac.getProteinasAbsoluto()));
		nuevaLac.setPorProteinasReal(redondearPorcentajes(lac.getPorcentajeProteinas()));*/
		nuevaLac.setFechaNacimiento(lac.getAnimal().getFechaNac());
		nuevaLac.setFechaParto(lac.getFechaInicio());
		nuevaLac.setFechaPrimerServicio(null);
		nuevaLac.setIntervaloPrimerServicioUltimoServicio(null);
		nuevaLac.setIntervaloPartoPrimerServicio(null);
		String gE = null;
		Establecimiento estab = nuevaLac.getTambo();
		/*gE = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(lac.getFechaInicio().getYear()+1900).toString();
		String gEstab = null;
		gEstab = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getEstancia().getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getEstancia().getId().toString(),"0",new Integer(5-estab.getEstancia().getId().toString().length())) +
						 				new Integer(lac.getFechaInicio().getYear()+1900).toString();
		nuevaLac.setGeEstab(gEstab + (lac.getFechaInicio().getMonth() < 7 ? "1" : "2"));
		nuevaLac.setGe(gE);
		nuevaLac.setGeEstabDos(gEstab);*/
		gE = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		String gEstab = null;
		gEstab = completeToLeft(estab.getEclo().getId().toString(),"0",new Integer(5-estab.getEclo().getId().toString().length())) +
				 completeToLeft(estab.getPropietario().getId().toString(),"0",new Integer(5-estab.getPropietario().getId().toString().length())) +
				 completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
						 				new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		String epoca1 =lac.getFechaInicio().getMonth() <= 6 ? "1" : "2";//si es menor inclusive que julio = 1 sino 2
		String epoca2 =(lac.getFechaInicio().getMonth() >=2 &&  lac.getFechaInicio().getMonth() <=7 ) ? "1" : "2"; //si esta entre marzo y agosto inclusive = 1 sino 2
		
		nuevaLac.setGeEstab(gEstab + epoca1);
		nuevaLac.setGe(null);
		nuevaLac.setGeEstabDos(null);
		nuevaLac.setGeDosA(gE+epoca2 );
		String cccr = completeToLeft(estab.getId().toString(),"0",new Integer(5-estab.getId().toString().length())) +
		new Integer(DateUtils.menos(lac.getFechaInicio(),1).getYear()+1900).toString();
		nuevaLac.setGeCP(cccr + epoca2);
		nuevaLac.setGeCR(cccr + epoca1);
		if (lac.getTlac().equals(8))
			nuevaLac.setBaja(new Integer(1));
		else if (lac.getTlac().equals(10))
			nuevaLac.setBaja(new Integer(2));
		else
			nuevaLac.setBaja(new Integer(3));
			/*if (baja.getMotivo().equals(STMotivo.REPR.toString()))
				nuevaLac.setMotivoBaja(new Integer(1));
			else if (baja.getMotivo().equals(STMotivo.SANI.toString()))
				nuevaLac.setMotivoBaja(new Integer(2));
			else if (baja.getMotivo().equals(STMotivo.PROD.toString()))
				nuevaLac.setMotivoBaja(new Integer(3));
			else if (baja.getMotivo().equals(STMotivo.UBRE.toString()))
				nuevaLac.setMotivoBaja(new Integer(4));
			else	
				nuevaLac.setMotivoBaja(new Integer(5));*/
		nuevaLac.setMotivoBaja(null);
		Lactancia lacPosterior = null;
		if (enCurso != null && (lac.getNroLact() - enCurso.getNroLact() == -1))
			lacPosterior = enCurso;
		else
			lacPosterior = ((Hembra)lac.getAnimal()).getLactanciaNumeroSinFiltroLeche(lac.getNroLact()+1);
		if (lacPosterior != null && (lac.getNroLact() - lacPosterior.getNroLact() == -1)){
			if (lacPosterior instanceof EvtLactanciaMigrada)
				nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(lac.getFechaInicio(),DateUtils.menos(lacPosterior.getFechaInicio(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO)));/*se resta 283 porque no hay servicio para migradas*/
			else{
				EvtAnimal eventoInicioLactancia = ((EvtLactancia)lacPosterior).getEventoIniciaLactancia();
				if (eventoInicioLactancia != null){ 
					EvtReproduccion repro = eventoInicioLactancia.getNombreTipo().equals(Evento.EVT_TIPO_REP) ? EvtReproduccionDAO.findByPrimaryKey(eventoInicioLactancia.getId()) : null;
					EvtServicio servicioPost = repro != null ? repro.getEvtServicio() : null; //por si es estado 
					//
					if(repro!=null && repro.getEvtServicio()!=null ){
						int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
						int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
							Date diaMasLejano = DateUtils.menos(repro.getFecha(), gMax);
							Date diaMasCercano = DateUtils.menos(repro.getFecha(), gMin);
							if (!DateUtils.entre(repro.getEvtServicio().getFecha(), diaMasLejano,diaMasCercano)){
								servicioPost = null;
							}
					}
					//
					List<EvtServicio> serviciosDos = null;
					if (servicioPost != null){
						
						//serviciosDos = lac.getAnimal().getEventosEntre(repro.getFecha(),servicioPost.getFecha(),Evento.EVT_TIPO_SVC);
						serviciosDos = lac.getAnimal().getEventosEntre(lac.getFechaInicio(),servicioPost.getFecha(),Evento.EVT_TIPO_SVC);
						nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(lac.getFechaInicio(),servicioPost.getFecha())); 
						////cambiar repro por FI de la migrada
						//nuevaLac.setIntervaloPartoPrimerServicioDos(repro != null && serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(repro.getFecha(),serviciosDos.get(0).getFecha()) : null);
						nuevaLac.setIntervaloPartoPrimerServicioDos(lac.getFechaInicio() != null && serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(lac.getFechaInicio(),serviciosDos.get(0).getFecha()) : null);
						nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(serviciosDos.get(0).getFecha(),servicioPost.getFecha()) : null);
					}
					else{
						Date fechaServicioFicticio = DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO);
						//serviciosDos = lac.getAnimal().getEventosEntre(repro.getFecha(),fechaServicioFicticio,Evento.EVT_TIPO_SVC);
						serviciosDos = lac.getAnimal().getEventosEntre(lac.getFechaInicio(),fechaServicioFicticio,Evento.EVT_TIPO_SVC);
						//cambiar repro por FI de la migrada
						//nuevaLac.setIntervaloPartoPrimerServicioDos(repro != null && serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(repro.getFecha(),serviciosDos.get(0).getFecha()) : null);
						nuevaLac.setIntervaloPartoPrimerServicioDos(lac.getFechaInicio() != null && serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(lac.getFechaInicio(),serviciosDos.get(0).getFecha()) : null);
						nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(lac.getFechaInicio(),DateUtils.menos(repro.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO)));
						nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty()? 
								DateUtils.diasEntre(serviciosDos.get(0).getFecha(),DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO)) 
								: null);
					}
					
					/*if (servicioPost != null) {
						nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(lac.getFechaInicio(),servicioPost.getFecha())); 
						nuevaLac.setIntervaloPrimerServicioUltimoServicioDos(serviciosDos != null && !serviciosDos.isEmpty()? DateUtils.diasEntre(serviciosDos.get(0).getFecha(),servicioPost.getFecha()) : null);
					}
					else//quiere decir que no habia servicio para el parto, o era un evento Estado 
						nuevaLac.setDiasEntreServicioLacAntDos(DateUtils.diasEntre(lac.getFechaInicio(),DateUtils.menos(repro.getFecha(),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO))); 
				*/
				}
				
				
				
				
				
			}
		}
		else{
			nuevaLac.setDiasEntreServicioLacAntDos(null);
			lacPosterior = null;
		}
		if (lacPosterior != null && (lac.getNroLact() - lacPosterior.getNroLact() == -1))
			nuevaLac.setDiasEntreLactanciaAnteriorDos(DateUtils.diasEntre(lac.getFechaInicio(),lacPosterior.getFechaInicio()));
		else
			nuevaLac.setDiasEntreLactanciaAnteriorDos(null);
		/*para las variables "dos", la posterior no importa si no tiene grasa o leche o proteinas*/
		if (lacPosterior != null && (lac.getNroLact() - lacPosterior.getNroLact() == -1)){
			if (lacPosterior instanceof EvtLactanciaMigrada)
				nuevaLac.setFechaInicioPartoSiguiente(lacPosterior.getFechaInicio());
			else
				nuevaLac.setFechaInicioPartoSiguiente(DateUtils.menos(lacPosterior.getFechaInicio(),1));
			}
		else
			nuevaLac.setFechaInicioPartoSiguiente(null);
		nuevaLac.setEstabPril(listLactancias != null && !listLactancias.isEmpty() ? ((Evento)listLactancias.get(0)).getEstablecimiento().getEstancia().getId() : null);
		nuevaLac.setIsop(completarDigitosISO(hembra.getPadre()));
		nuevaLac.setIsoam(completarDigitosISO(hembra.getMadreGenetica() != null  ? hembra.getMadreGenetica().getPadre() : null));
		if (lacPosterior != null && (lac.getNroLact() - lacPosterior.getNroLact() == -1)){
			if (lacPosterior instanceof EvtLactanciaMigrada){
				nuevaLac.setFechaPrimerServicioDos(null);
				nuevaLac.setFechaUltimoServicioDos(DateUtils.menos(lacPosterior.getFechaInicio(), LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
				nuevaLac.setNumerosDeServicioDos(null);
			}
			else{
				EvtAnimal eventoInicioLactancia = ((EvtLactancia)lacPosterior).getEventoIniciaLactancia();
				EvtReproduccion repro = eventoInicioLactancia.getNombreTipo().equals(Evento.EVT_TIPO_REP) ? EvtReproduccionDAO.findByPrimaryKey(eventoInicioLactancia.getId()) : null;
				EvtServicio servicio = repro != null ? repro.getEvtServicio() : null; //por si es estado
				//
				if(repro!=null && repro.getEvtServicio()!=null ){
					int gMin = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MINIMA));
					int gMax = Integer.parseInt(hembra.getRaza().getParametro(Raza.GESTACION_MAXIMA));
						Date diaMasLejano = DateUtils.menos(repro.getFecha(), gMax);
						Date diaMasCercano = DateUtils.menos(repro.getFecha(), gMin);
						if (!DateUtils.entre(repro.getEvtServicio().getFecha(), diaMasLejano,diaMasCercano)){
							servicio = null;
						}
				}
				//
				List<EvtServicio> servicios = null;
				if (servicio != null)
					servicios = lac.getAnimal().getEventosEntre(lac.getFechaInicio(),servicio.getFecha(),Evento.EVT_TIPO_SVC);
				else
					servicios = lac.getAnimal().getEventosEntre(lac.getFechaInicio(),DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO),Evento.EVT_TIPO_SVC);
				nuevaLac.setFechaPrimerServicioDos(servicios != null && !servicios.isEmpty() ?  servicios.get(0).getFecha() : null);	
				if (eventoInicioLactancia != null){ 
					if (servicio == null)
						nuevaLac.setFechaUltimoServicioDos(DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
					else
						nuevaLac.setFechaUltimoServicioDos(servicio.getFecha());
				}
				else
					nuevaLac.setFechaUltimoServicioDos(DateUtils.menos(DateUtils.menos(lacPosterior.getFechaInicio(),1),LactanciaGenetica.DIAS_SI_NO_HAY_SERVICIO));
				if (servicio != null)
					nuevaLac.setNumerosDeServicioDos(servicios != null && !servicios.isEmpty() ? servicios.indexOf(servicio)+1 : null);
				else{
					 if (servicios == null || servicios.isEmpty())
						 nuevaLac.setNumerosDeServicioDos(null);
					 else
						nuevaLac.setNumerosDeServicioDos(servicios != null && !servicios.isEmpty() ? servicios.size() + 1 : null); //se le suma el servicio promedio
				}
			}
		}
		
		nuevaLac.setNumerosDeServicio(null);
		nuevaLac.setIsom(completarDigitosISO(hembra.getMadreGenetica()));
		nuevaLac.setSistemaSicel(LactanciaGenetica.TIPO_LACTANCIA_SICEL1);
		session.save(nuevaLac);
	}
	 
	private static String completarDigitos(Hembra a){ 
		String result = a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ? "P" : (a.getRegOrigen().getTipoRegistro().getId().equals("RC") ? "C" : null); 
		int cant = 9-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
		for(int i = 0;i < cant;i++) 
			result = result + "0"; 
		result = result + a.getRegOrigen().getNumero(); 
		return result; 
	} 
 	
	private static String completarDigitosISOAnterior(Animal a){
		if (a != null){
			if (a.esHembra()){
				String result = a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ? "ARGP" : (a.getRegOrigen().getTipoRegistro().getId().equals("RC") ? "ARGC" : null); 
				int cant = 10-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
				for(int i = 0;i < cant;i++) 
					result = result + "0"; 
				result = result + a.getRegOrigen().getNumero();
				return result;
			}else{
				String result = a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ? "ARGT" : (a.getRegOrigen().getTipoRegistro().getId().equals("RC") ? "ARGR" : null); 
				int cant = 10-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
				for(int i = 0;i < cant;i++) 
					result = result + "0"; 
				result = result + a.getRegOrigen().getNumero();
				return result;
			}
		}
		return null;
	}
	private static String completarDigitosISO(Animal a){
		if (a != null){
			if(a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ||a.getRegOrigen().getTipoRegistro().getId().equals("RC") ){//si es nacional
				if (a.esHembra()){

					String result = (a.getRaza().getId().substring(0,3)) + (a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ? "ARGFP" : (a.getRegOrigen().getTipoRegistro().getId().equals("RC") ? "ARGFC" : null)); 
					int cant = 11-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
					//int cant = 9-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C

					for(int i = 0;i < cant;i++) 
						result = result + "0"; 
					result = result + a.getRegOrigen().getNumero();
					return result;
				}else{


					String result = (a.getRaza().getId().substring(0,3))+ (a.getRegOrigen().getTipoRegistro().getId().equals("HBA") ? "ARGMP" : (a.getRegOrigen().getTipoRegistro().getId().equals("RC") ? "ARGMC" : null)); 
					int cant = 11-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
					//int cant = 9-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C


					for(int i = 0;i < cant;i++) 
						result = result + "0"; 
					result = result + a.getRegOrigen().getNumero();
					return result;
				}
			}
			else{//es internacional
				String reg = a.getRegOrigen().getTipoRegistro().getId();
				char ultimaLetra =reg.charAt(reg.length() -1);
				if(ultimaLetra == 'T'){


					String result = (a.getRaza().getId().substring(0,3))+(reg.substring(0, reg.length() -1) + 'M');
					int cant = 12-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
					//int cant = 10-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C

					for(int i = 0;i < cant;i++) 
						result = result + "0"; 
					result = result + a.getRegOrigen().getNumero();
				}
				else{


					String result = (a.getRaza().getId().substring(0,3))+(reg.substring(0, reg.length() -1) + 'F');
					int cant = 12-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C 
					//int cant = 10-a.getRegOrigen().getNumero().length();//es mas 1 por el caracter de la linea anterior P o C


					for(int i = 0;i < cant;i++) 
						result = result + "0"; 
					result = result + a.getRegOrigen().getNumero();
				}
			}
		}
		return null;
	}
	
	private static String completeToLeft(String source,String character,Integer count){
		int c = count;
		String aux = new String(source);
		while (c > 0){
			aux = character + aux;
			c--;
		}
		return aux;
	}
	
	private static Float redondear(Float f){
		if (f == null)
			return null;
		Float sinDecimales = new Float(Math.ceil(f));
		if (f-Math.ceil(f) > 0.5)
			return sinDecimales+1;
		else
			return sinDecimales;
	}
	
	private static Float redondearPorcentajes(Float f){
		if (f == null)
			return null;
		DecimalFormat df = new DecimalFormat("0.00");
		String value = df.format(f.doubleValue());
		value = value.replace(",",".");
		return new Float(value);
	}
}