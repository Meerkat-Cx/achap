package ar.org.sicel.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ar.org.sicel.excel.excepciones.CargaMasivaException;
import ar.org.sicel.excel.excepciones.CellError;
import ar.org.sicel.excel.excepciones.FormatCellException;
import ar.org.sicel.excel.excepciones.InvalidDataItemException;
import ar.org.sicel.persistence.Animal;
import ar.org.sicel.persistence.AnimalDAO;
import ar.org.sicel.persistence.CONF;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.Establecimiento;
import ar.org.sicel.persistence.EstablecimientoDAO;
import ar.org.sicel.persistence.Evento;
import ar.org.sicel.persistence.EvtReproduccion;
import ar.org.sicel.persistence.EvtReproduccionDAO;
import ar.org.sicel.persistence.EvtServicio;
import ar.org.sicel.persistence.EvtServicioDAO;
import ar.org.sicel.persistence.Hembra;
import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.Macho;
import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;
import ar.org.sicel.persistence.ProcMsg;
import ar.org.sicel.persistence.ProcMsgDAO;
import ar.org.sicel.persistence.Propietario;
import ar.org.sicel.persistence.PropietarioDAO;
import ar.org.sicel.persistence.PropietarioExpd;
import ar.org.sicel.persistence.PropietarioExpdDAO;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.RazaDAO;
import ar.org.sicel.persistence.Registro;
import ar.org.sicel.persistence.RegistroDAO;
import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.persistence.util.StandaloneHibernateStrategy;
import ar.org.sicel.util.DateUtils;
import ar.org.sicel.web.altaEdicion.forms.PedigreeForm;


/**
 * Esta clase tiene como funcionalidad la carga (altas) masiva de animales de pedigree como 
 * asi tambien la modificacion masivas de animales de pedigree
 * @author 
 *
 */
public class CargaMasivaAccessor {
	
	static Logger log = Logger.getLogger(CargaMasivaAccessor.class);
	
	private PoiExcelAccessor accessor = null;
	private Map<String, Integer> mapping = new HashMap<String, Integer>();
	 
	
	public CargaMasivaAccessor(String directorio, byte[] fileData) throws CargaMasivaException  {
		String fileName = "/cargamasiva"+new Date().getTime();
		
		createFile(directorio+fileName, fileData);
		accessor = new PoiExcelAccessor(directorio+fileName);		
	}
	
	private void createFile(String fileName, byte[] fileData) throws CargaMasivaException{
		File file = new File(fileName);
		try {
			FileOutputStream os = new FileOutputStream(file);   
			os.write(fileData);
			os.close();
		}
		catch (Exception e) {
			throw new CargaMasivaException("Problemas con la creacion del archivo de carga", e);
		} 
	}
	
	/**
	 * 
	 * @param esHembra 
	 * @param raza 
	 * @param origen
	 * @param errores 
	 * @return - cantidad de altas correctamente realizadas
	 * @throws CargaMasivaException 
	 */
	public int realizarAltas(String origen, String raza, boolean esHembra, Set<CellError> errores, String sheetName) throws CargaMasivaException  {
		this.accessor.setSheetName(sheetName);
		this.crearMapping(false);		
		
		List<DataItem> dataItems = new ArrayList<DataItem>();
		getDataItems(dataItems, errores);
		Iterator iteratorDataItems = dataItems.iterator();
		int i = 0;
		while (iteratorDataItems.hasNext())  {
			DataItem dataItem = (DataItem) iteratorDataItems.next();
			try {
				procesarAlta(origen, raza, esHembra,dataItem);
				i++;
			} catch (InvalidDataItemException e) {				
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			}
		}
		return i;
	}
	
	private void procesarAlta(String origen, String raza, boolean esHembra, DataItem dataItem) throws InvalidDataItemException {
		// validar que el id del propietario este en la base de datos
		log.debug("procesando la fila "+dataItem.getFila());
		
		Long idProp = dataItem.getProp();
		
		if (idProp == null) {
			throw new InvalidDataItemException("El propietario del animal (PROP) es un campo obligatorio", dataItem.getFila());
		}
		PropietarioExpd propietario = null;
		Raza r = RazaDAO.findByPrimaryKey(raza);
		try {			
			propietario = PropietarioExpdDAO.findByNumeroyRaza(idProp,r);
			if (propietario == null) {
				throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());	
			}
		}
		catch (HibernateException e) {
			throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());
		}
		// validar que el propietario creador este en la base (solo si me informan el id del creador)
		Long idCriador = dataItem.getCria();
		PropietarioExpd propietarioCriador = null;
		if (idCriador != null) {
			try {
				propietarioCriador = PropietarioExpdDAO.findByNumeroyRaza(idCriador,r);
				if(propietarioCriador== null)
					propietarioCriador = propietario;
			}
			catch (HibernateException e) {
				throw new InvalidDataItemException("El propietario criador con EXPD "+idCriador+" (CRIA) no existe en la base de datos", dataItem.getFila());
			}
		}
		else { // si no me viene el propietatio criador es el mismo propietario
			propietarioCriador = propietario;
		}
		//validar que la padre este en la base solo si se informa en el excel
		String hbaPadre = dataItem.getHbap();
		Macho padre = null;
		if (StringUtils.isNotEmpty(hbaPadre)) {			
			try {
				padre = (Macho) AnimalDAO.findExistentMachoByRegistry("HBA", hbaPadre, raza);
			}
			catch (ExcepcionIntegridad e) {
				throw new InvalidDataItemException("El HBA "+hbaPadre+" del padre (HBAP) del animal no existe en la base de datos", dataItem.getFila());
			}
		}
		//valida que la madre este en la base
		String hbaMadre = dataItem.getHbam();
		Hembra madre = null;
		if (StringUtils.isNotEmpty(hbaMadre)) {			
			try {
				madre = (Hembra) AnimalDAO.findExistentHembraByRegistry("HBA", hbaMadre, raza);
				if (madre == null)
					throw new InvalidDataItemException("El HBA "+hbaMadre+" de la madre (HBAM) del animal no existe en la base de datos", dataItem.getFila());
			}
			catch (ExcepcionIntegridad e) {
				throw new InvalidDataItemException("El HBA "+hbaMadre+" de la madre (HBAM) del animal no existe en la base de datos", dataItem.getFila());
			}
		}		
		
		
		// le estoy agregando una validacion
//		 Si es alta nacional y la configuracion lo permite tendria que validar
				
		try {
			
			if (("Nacional".equalsIgnoreCase(origen))&&(Configuracion.getValorReglaProceso(CONF.CARGA_MASIVA_CHEQUEOS_NACIONALES, new Date()))){
				if (StringUtils.isEmpty(hbaPadre)) 
					throw new InvalidDataItemException("Es obligatorio para animales nacionales informar el padre", dataItem.getFila());
				if (StringUtils.isEmpty(hbaMadre)) 			
					throw new InvalidDataItemException("Es obligatorio para animales nacionales informar la madre", dataItem.getFila());
				Date fechaServicio = dataItem.getFser();
				if(fechaServicio == null)
					throw new InvalidDataItemException("Es obligatorio para animales nacionales informar la fecha de servicio", dataItem.getFila());
				Date fechaNacimiento = dataItem.getFnac();
				EvtReproduccion evtRep = (EvtReproduccion)madre.getEventoEnFecha(fechaNacimiento,Evento.EVT_TIPO_REP);
				if(evtRep == null)//si no tiene EVT reproduccion en esa fecha es un empadronar
					throw new InvalidDataItemException("No existe el evento reproduccion para la madre ["+madre.getRegistroOrigen()+"] en la fecha "+fechaNacimiento.toString(), dataItem.getFila());
				if(evtRep.getEvtServicio()!=null){
					if(!padre.equals(evtRep.getEvtServicio().getPadreGenetico()))
						throw new InvalidDataItemException("El evento servicio asociado al evento reproduccion de la madre ["+madre.getRegistroOrigen()+"] tiene asociado un padre que no coincide con el informado en el excel", dataItem.getFila());
				}
				else
					throw new InvalidDataItemException("El evento reproduccion de la madre ["+madre.getRegistroOrigen()+"] en la fecha "+fechaNacimiento.toString()+" no tiene asociado ningun servicio", dataItem.getFila());
					
			}
		} catch (ExcepcionIntegridad e1) {
			e1.printStackTrace();
			System.out.println("tengo que tirar una excepcion despues la tengo que capturar para informar ");
			throw new InvalidDataItemException(e1.getCodigoError(), dataItem.getFila());
		}
		
		//validar que el registro no este en la base (tiene que ser nuevo)
		String hbae = dataItem.getHbae();
		
		// validar que el registro no este siendo utilizado por otro animal
		Registro registro = RegistroDAO.find("HBA", hbae, raza, (esHembra)?"H":"M");
		
		if (registro != null) { // el registro ya esta siendo utilizado 
			throw new InvalidDataItemException("El Numero de Registro de HBA "+hbae+" ya esta siendo utilizado", dataItem.getFila());
		}
		
		Establecimiento tamboCriador = null;
		Establecimiento tamboPropietario = null;
		List msg = new ArrayList();
		
		Raza entidadRaza = RazaDAO.findByPrimaryKey(raza);
		
		Animal nuevoAnimal = null;
		Propietario pc = PropietarioDAO.findByPrimaryKey(propietarioCriador.getPropietario().getId());
		Propietario p = PropietarioDAO.findByPrimaryKey(propietario.getPropietario().getId());
		if (padre == null && madre == null) {// es un empadronar
			
			try {
				
				nuevoAnimal = AnimalDAO.createAnimalPedEmpadronar(tamboCriador, pc, entidadRaza, tamboPropietario, p, 
										null, esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb());
			} catch (ExcepcionIntegridad e) {
				log.debug("Tuvimos problemas con la creacion del animal de pedigree "+e.getCodigoError());
				throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());			
			}	
		}
		else { // es con padre y madre
			try {
				nuevoAnimal = AnimalDAO.createAnimalPedPadres(tamboCriador, pc, padre, madre,entidadRaza,
						tamboPropietario, p, null, esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb(),null,null);
			} catch (ExcepcionIntegridad e) {
				log.debug("Tuvimos problemas con la creacion del animal de pedigree");
				throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());
			}
		}
		// creo el registro HBA
		try {
			registro = RegistroDAO.create("HBA", dataItem.getHbae());
		} catch (ExcepcionIntegridad e) {
			log.debug("Tuvimos problemas con la creacion del regsitro del animal de pedigree");
			throw new InvalidDataItemException("No se pudo crear correctamente el registro del animal", dataItem.getFila());
		}
		registro.setCodigoBaja(dataItem.getCbaj());
		registro.setFechaBaja(dataItem.getFbaj());
		
		// seteo el regOri y el regId con registro tiene que ser el mismo HBA		
		nuevoAnimal.setRegistros(null);
		nuevoAnimal.setRegistros(new HashSet());
		//if ("Nacional".equals(origen)) // si es nacional el mismo registro
		nuevoAnimal.setRegIdentificador(registro);
		nuevoAnimal.setRegOrigen(registro);
		nuevoAnimal.addRegistro(registro);
		
		// setear los demas campos
		nuevoAnimal.setAsoc(dataItem.getAsoc());
		nuevoAnimal.setDadorSemen(dataItem.getDado());
		nuevoAnimal.setNumeroTransf(dataItem.getTran());
		nuevoAnimal.setNumeroAnalADN(dataItem.getAnls());
		nuevoAnimal.setMellizo(dataItem.getMell());
		nuevoAnimal.setTransferencia(dataItem.getTrns());
		nuevoAnimal.setTipoServicio(dataItem.getTser());
		nuevoAnimal.setAsop(dataItem.getAsop());
		nuevoAnimal.setAsom(dataItem.getAsom());
		nuevoAnimal.setSRAFesb(dataItem.getFesb());
		nuevoAnimal.setFechaUltObs(dataItem.getFobs());
		nuevoAnimal.setFechaTransf(dataItem.getFtrf());
		nuevoAnimal.setFechaServicio(dataItem.getFser());
		nuevoAnimal.setApodo(dataItem.getApdo());
		nuevoAnimal.setRpti(dataItem.getRpti());
		nuevoAnimal.setDonante(dataItem.getDona());
		
		HibernateFactory.getSession().save(nuevoAnimal);
	}

	/**
	 * En rta van a estar las filas del excel que pueden ser procesadas (no tienen error con el formato)
	 * En errores van a quedar los errores de las filas que estan mal formadas
	 * 
	 * @param rta
	 * @param errores
	 */
	private void getDataItems(List<DataItem> rta, Set<CellError> errores) {		
		int inicio = this.accessor.getFirstRowNum();
		int fin = this.accessor.getLastRowNum();
		for (int row = inicio+1; row <= fin; row++) { // recorro las filas
			log.debug("creando data item de la fila "+row);
			DataItem dataItem = null;
			try {				
				if (this.accessor.isEmpty(row))
					break;
				dataItem = createDataItem(row);				
				rta.add(dataItem);
			} catch (FormatCellException e) {				
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			} catch (POIException e) {
				CellError error = new CellError(row+1, e.getMessage());
				errores.add(error);
			}			
		}
	}

	
	/**
	 * Cuando hay un error tengo que informar el error, pero tiene que seguir  
	 * @param row
	 * @return
	 */
	private DataItem createDataItem(int row) throws FormatCellException {
		DataItem dataItem = new DataItem();
		dataItem.setFila(row); // le meto la fila de donde se obtuvo el dataItem
		int col = -1;
		
		
		col = (mapping.get("RPTI")!=null)?mapping.get("RPTI"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setRpti(accessor.getStringValue(row, col));
		
		col = (mapping.get("ANLS")!=null)?mapping.get("ANLS"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col)!= null) {
				dataItem.setAnls(accessor.getDoubleValue(row, col).toBigInteger().intValue());
			}
		} catch (FormatCellException e1) {
			throw new FormatCellException("El campo ANLS del archivo excel no es un valor entero", row, col);			
		}
		col = (mapping.get("APDO")!=null)?mapping.get("APDO"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setApdo(accessor.getStringValue(row, col));
		
		col = (mapping.get("DONA")!=null)?mapping.get("DONA"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setDona(accessor.getStringValue(row, col));
		
		col = (mapping.get("ASOC")!=null)?mapping.get("ASOC"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col)!=null)
				dataItem.setAsoc(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e1) {
			throw new FormatCellException("El campo ASOC del archivo excel no es un valor entero", row, col);			
		}
		
		col = (mapping.get("ASOM")!=null)?mapping.get("ASOM"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setAsom(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo ASOM del archivo excel no es un valor entero", row, col);
		}

		col = (mapping.get("ASOP")!=null)?mapping.get("ASOP"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setAsop(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo ASOP del archivo excel no es un entero", row, col);
		}
		
		col = (mapping.get("CBAJ")!=null)?mapping.get("CBAJ"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null) {
				Integer cbaj = accessor.getDoubleValue(row, col).toBigInteger().intValue();				
				dataItem.setCbaj(cbaj);

			}
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo CBAJ del archivo excel no es un valor entero valido ", row, col);
		}
		if (dataItem.getCbaj() != null && (dataItem.getCbaj().intValue() < 0 || dataItem.getCbaj().intValue() > 8))
			throw new FormatCellException("El campo CBAJ del archivo excel debe ser un entero entre 0 y 8", row, col);
		
		col = (mapping.get("CRIA")!=null)?mapping.get("CRIA"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setCria(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo CRIA del archivo excel no es un valor entero", row, col);
		}
		col = (mapping.get("DADO")!=null)?mapping.get("DADO"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setDado(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo DADO del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("FBAJ")!=null)?mapping.get("FBAJ"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFbaj(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FBAJ del archivo excel no es una fecha valida", row, col);
		}		
		
		col = (mapping.get("FESB")!=null)?mapping.get("FESB"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFesb(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FESB del archivo excel no es un valor entero", row, col);
		}
		col = (mapping.get("FNAC")!=null)?mapping.get("FNAC"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFnac(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FNAC del archivo excel no es una Fecha Valida", row, col);
		}
		if (dataItem.getFnac() == null)
			throw new FormatCellException("La fecha de Nacimiento del animal (FNAC) en el archivo excel es un campo obligatorio", row, col);
		
		col = (mapping.get("FOBS")!=null)?mapping.get("FOBS"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFobs(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FOBS del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("FSER")!=null)?mapping.get("FSER"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFser(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FSER del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("FTRF")!=null)?mapping.get("FTRF"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFtrf(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FTRF del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("HBAE")!=null)?mapping.get("HBAE"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col))) {			
			if (accessor.getStringValue(row, col).length() > 12)				
					throw new FormatCellException("El HBA del animal (HBAE) excede la cantidad de caracteres permitidos", row, col);
			dataItem.setHbae(accessor.getStringValue(row, col));			
		}
		if (StringUtils.isEmpty(dataItem.getHbae())) {
			throw new FormatCellException("El HBA del animal (HBAE) es un campo obligatorio ", row, col);
		}
		
		col = (mapping.get("HBAM")!=null)?mapping.get("HBAM"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setHbam(accessor.getStringValue(row, col));
		
		col = (mapping.get("HBAP")!=null)?mapping.get("HBAP"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setHbap(accessor.getStringValue(row, col));
		
		col = (mapping.get("MELL")!=null)?mapping.get("MELL"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setMell(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo MELL del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("NOMB")!=null)?mapping.get("NOMB"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setNomb(accessor.getStringValue(row, col));
		
		col = (mapping.get("PROP")!=null)?mapping.get("PROP"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setProp(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo PROP del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("RPEX")!=null)?mapping.get("RPEX"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setRpex(accessor.getStringValue(row, col));
		
		col = (mapping.get("TRAN")!=null)?mapping.get("TRAN"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setTran(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo TRAN del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("TRNS")!=null)?mapping.get("TRNS"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setTrns(accessor.getStringValue(row, col));
		
		col = (mapping.get("TSER")!=null)?mapping.get("TSER"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setTser(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo TSER del archivo excel no es un valor entero", row, col);
		}		
		
		return dataItem;
	}

	/**
	 * El mapping es el mismo tanto para altas como para modificaciones 
	 *
	 */
	private void crearMapping(boolean modiRP) throws CargaMasivaException {
		int firstRow = accessor.getFirstRowNum();		
		int i;
		try {
			i = accessor.getFirstCellNum(firstRow);
			int n = accessor.getLastCellNum(firstRow);		
			for (int k = i; k < n; k++) {			
				mapping.put(accessor.getStringValue(firstRow, k), k);
			}
			
			validateHeader(modiRP);
		
		} catch (POIException e) {
			throw new CargaMasivaException("El formato de la Cabecera del archivo excel (fila 0) no es correcto");
		}				
	}
	
	private void validateHeader(boolean modiRP) throws CargaMasivaException {
		String aux = "";
		if(!modiRP){
		///
		if (!mapping.containsKey("RPTI"))
			aux += "RPTI, ";
		//
		if (!mapping.containsKey("ASOC"))
			aux += "ASOC, ";
		if (!mapping.containsKey("HBAE"))
			aux += "HBAE, ";
		if (!mapping.containsKey("RPEX"))
			aux += "RPEX, ";
		if (!mapping.containsKey("NOMB"))
			aux += "NOMB, ";
		if (!mapping.containsKey("CBAJ"))
			aux += "CBAJ, ";
		if (!mapping.containsKey("DADO"))
			aux += "DADO, ";
		if (!mapping.containsKey("PROP"))
			aux += "PROP, ";
		if (!mapping.containsKey("CRIA"))
			aux += "CRIA, ";
		if (!mapping.containsKey("TRAN"))
			aux += "TRAN, ";
		if (!mapping.containsKey("ANLS"))
			aux += "ANLS, ";
		if (!mapping.containsKey("DONA"))
			aux += "DONA, ";
		if (!mapping.containsKey("MELL"))
			aux += "MELL, ";
		if (!mapping.containsKey("TRNS"))
			aux += "TRNS, ";
		if (!mapping.containsKey("TSER"))
			aux += "TSER, ";
		if (!mapping.containsKey("ASOP"))
			aux += "ASOP, ";
		if (!mapping.containsKey("HBAP"))
			aux += "HBAP, ";
		if (!mapping.containsKey("ASOM"))
			aux += "ASOM, ";
		if (!mapping.containsKey("HBAM"))
			aux += "HBAM, ";
		if (!mapping.containsKey("FNAC"))
			aux += "FNAC, ";
		if (!mapping.containsKey("FBAJ"))
			aux += "FBAJ, ";
		if (!mapping.containsKey("FESB"))
			aux += "FESB, ";
		if (!mapping.containsKey("FOBS"))
			aux += "FOBS, ";
		if (!mapping.containsKey("FTRF"))
			aux += "FTRF, ";
		if (!mapping.containsKey("FSER"))
			aux += "FSER, ";
		if (!mapping.containsKey("APDO"))
			aux += "APDO, ";
	}
	else{//si es cambio de rp
		if ((!mapping.containsKey("RC"))&&(!mapping.containsKey("HBA")))
			aux += "RC ó HBA ";
		if (!mapping.containsKey("RP.NUEVO"))
			aux += "RP.NUEVO, ";
		if (!mapping.containsKey("RP.VIEJO"))
			aux += "RP.VIEJO, ";
		if (!mapping.containsKey("FNAC"))
			aux += "FNAC, ";
		if (!mapping.containsKey("TAMBO"))
			aux += "TAMBO, ";
		
	}
	
		if (aux != "") // hay error
			throw new CargaMasivaException("Las siguientes columnas son obligatorias: "+aux);					
	}

	/**
	 * Metodo para indicar la finalizacion
	 * Se cierra el inputStream y se elimina el file temporal
	 * @throws CargaMasivaException 
	 * @throws CargaMasivaException
	 */
	public void close() throws CargaMasivaException {
		this.accessor.close();
	}
	
	/***
	 * Metodo que se encarga de realizar las modificacion de los animales de pedigri
	 * Este metodo se usa cuando se indica que la carga es de animales nacionales  
	 * @param origen
	 * @param raza
	 * @param esHembra
	 * @param errores
	 * @param sheetName
	 * @return
	 * @throws CargaMasivaException
	 */
	public int realizarModificaciones(String origen, String raza, boolean esHembra, Set<CellError> errores, String sheetName,boolean cambioRaza,boolean cambioSexo,String razaAnterior) throws CargaMasivaException {
		this.accessor.setSheetName(sheetName);
		this.crearMapping(false);		
		
		List<DataItem> dataItems = new ArrayList<DataItem>();
		getDataItemsParaModificacion(dataItems, errores,false);
		Iterator iteratorDataItems = dataItems.iterator();
		int i = 0;
		while (iteratorDataItems.hasNext())  {
			DataItem dataItem = (DataItem) iteratorDataItems.next();
			try {
				procesarModificacion(origen, raza, esHembra,dataItem,cambioRaza,cambioSexo,razaAnterior);
				StandaloneHibernateStrategy.getInstance().commitCurrentSession();
				i++;
			} catch (InvalidDataItemException e) {				
				StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
				log.debug("Error en la carga masiva");
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			}
		}
		return i;		
	}
	/**
	 * Metodo que realiza la modificacion de rp del animal con solamente informar tipo y numero de reg en el excel
	 * @param origen
	 * @param raza
	 * @param esHembra
	 * @param errores
	 * @param sheetName
	 * @param cambioRaza
	 * @param cambioSexo
	 * @param razaAnterior
	 * @return
	 * @throws CargaMasivaException
	 */
	public int realizarModificacionesRP(String raza, boolean esHembra, Set<CellError> errores, String sheetName) throws CargaMasivaException {
		this.accessor.setSheetName(sheetName);
		this.crearMapping(true);		
		
		List<DataItem> dataItems = new ArrayList<DataItem>();
		getDataItemsParaModificacion(dataItems, errores,true);
		Iterator iteratorDataItems = dataItems.iterator();
		int i = 0;
		while (iteratorDataItems.hasNext())  {
			DataItem dataItem = (DataItem) iteratorDataItems.next();
			try {
				procesarModificacionRP(raza, esHembra,dataItem);
				StandaloneHibernateStrategy.getInstance().commitCurrentSession();
				i++;
			} catch (InvalidDataItemException e) {				
				StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
				log.debug("Error en la carga masiva");
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			}
		}
		return i;		
	}

	/**
	 * No se le puede cambiar la raza
	 * @param origen
	 * @param raza
	 * @param esHembra
	 * @param dataItem
	 * @throws InvalidDataItemException
	 */
	private void procesarModificacion(String origen, String raza, boolean esHembra, DataItem dataItem,boolean cambioRaza,boolean cambioSexo,String razaAnterior) throws InvalidDataItemException {
		log.debug("procesando la fila "+dataItem.getFila());
		
		// validar que el registro no este en la base (tiene que ser nuevo)
		String hbae = dataItem.getHbae();
		
		// Busco con el hba, sexo, raza, el animal de cierto sexo con el rgOri hba
		Animal animalModificado = null;
		String aux="";
		try {
			//si cambio la raza busco con la raza anterio
			//si cambio el sexo busco por el sexo contrario al cargado en la pantalla
			//esto es para poder encontrar el animal en la base
			aux = "HBA: "+hbae+" sexo: "+((cambioSexo)? (esHembra?"Macho":"Hembra"):(esHembra?"Hembra":"Macho"))+" raza: "+((cambioRaza)? razaAnterior:raza);
			animalModificado = AnimalDAO.findExistentByRegistry("HBA", hbae, (cambioRaza)? razaAnterior:raza,(cambioSexo)? (esHembra?"M":"H"):(esHembra?"H":"M"));	
		} catch (ExcepcionIntegridad e1) {
			//String aux = "HBA: "+hbae+" sexo: "+((cambioSexo)? (esHembra?"Macho":"Hembra"):(esHembra?"Hembra":"Macho"))+" raza: "+((cambioRaza)? razaAnterior:raza);
			throw new InvalidDataItemException("No existe animal "+aux, dataItem.getFila());
		}
		if (animalModificado == null){
			//String aux = "HBA: "+hbae+" sexo: "+((cambioSexo)? (esHembra?"Macho":"Hembra"):(esHembra?"Hembra":"Macho"))+" raza: "+((cambioRaza)? razaAnterior:raza);
			throw new InvalidDataItemException("No existe Animal "+aux, dataItem.getFila());
		}
		if(cambioSexo || cambioRaza){
			this.validarCambioSexoYRaza(animalModificado,aux,dataItem.getFila());
		}
		
		// verifico que el propietario "nuevo" este en la base
		/* por pedido de Daniel no se tiene el cuenta el propietario que informan 05/11/2010
		Long idProp = dataItem.getProp();
		if (idProp == null) {
			throw new InvalidDataItemException("El propietario del animal (PROP) es un campo obligatorio", dataItem.getFila());
		}
		PropietarioExpd propietario = null;
		Raza r = RazaDAO.findByPrimaryKey(raza);
		try {			
			propietario = PropietarioExpdDAO.findByNumeroyRaza(idProp,r);
			if (propietario == null) {
				throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());	
			}
		}
		catch (HibernateException e) {
			throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());
		}
		// validar que el propietario creador este en la base (solo si me informan el id del creador)
		Long idCriador = dataItem.getCria();
		PropietarioExpd propietarioCriador = null;
		if (idCriador != null) {
			try {
				propietarioCriador = PropietarioExpdDAO.findByNumeroyRaza(idCriador,r);
				if(propietarioCriador== null)
					propietarioCriador = propietario;
			}
			catch (HibernateException e) {
				throw new InvalidDataItemException("El propietario criador con EXPD "+idCriador+" (CRIA) no existe en la base de datos", dataItem.getFila());
			}
		}
		else { // si no me viene el propietatio criador es el mismo propietario
			propietarioCriador = propietario;
		}
		*/				
		//validar que el padre este en la base solo si se informa en el excel
		String hbaPadre = dataItem.getHbap();
		Macho padre = null;
		if (StringUtils.isNotEmpty(hbaPadre)) {			
			try {
				padre = (Macho) AnimalDAO.findExistentMachoByRegistry("HBA", hbaPadre, raza);
			}
			catch (ExcepcionIntegridad e) {
				throw new InvalidDataItemException("El HBA "+hbaPadre+" del padre (HBAP) del animal no existe en la base de datos", dataItem.getFila());
			}
		}				
		
		//valida que la madre este en la base
		String hbaMadre = dataItem.getHbam();
		Hembra madre = null;
		if (StringUtils.isNotEmpty(hbaMadre)) {			
			try {
				madre = (Hembra) AnimalDAO.findExistentHembraByRegistry("HBA", hbaMadre, raza);
				if (madre == null)
					throw new InvalidDataItemException("El HBA "+hbaMadre+" de la madre (HBAM) del animal no existe en la base de datos", dataItem.getFila());
			}
			catch (ExcepcionIntegridad e) {
				throw new InvalidDataItemException("El HBA "+hbaMadre+" de la madre (HBAM) del animal no existe en la base de datos", dataItem.getFila());
			}
		}
		/* por pedido de Daniel no se tiene el cuenta el propietario que informan 05/11/2010
		Propietario pc = PropietarioDAO.findByPrimaryKey(propietarioCriador.getPropietario().getId());
		Propietario p = PropietarioDAO.findByPrimaryKey(propietario.getPropietario().getId());
		*/
		Establecimiento tamboCriador = null;
		Establecimiento tamboPropietario = null;
		List msg = new ArrayList();
				
		Raza entidadRaza = RazaDAO.findByPrimaryKey((cambioRaza)? razaAnterior:raza);		
		// solo se puede dar de baja y setear la fecha de baja
		if(cambioSexo){
			HibernateFactory.getSession().delete(animalModificado);
			Animal nuevoAnimal =null;
			if (padre == null && madre == null) {// es un empadronar
				
				try {
					
					nuevoAnimal = AnimalDAO.createAnimalPedEmpadronar(tamboCriador, animalModificado.getPropietarioCriador(), entidadRaza, tamboPropietario, animalModificado.getPropietario(), 
											null, esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb());
				} catch (ExcepcionIntegridad e) {
					log.debug("Tuvimos problemas con la creacion del animal de pedigree "+e.getCodigoError());
					throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());			
				}	
			}
			else { // es con padre y madre
				try {
					nuevoAnimal = AnimalDAO.createAnimalPedPadres(tamboCriador, animalModificado.getPropietarioCriador(), padre, madre,entidadRaza,
							tamboPropietario, animalModificado.getPropietario(), null, esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb(),null,null);
				} catch (ExcepcionIntegridad e) {
					log.debug("Tuvimos problemas con la creacion del animal de pedigree");
					throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());
				}
			}
			// creo el registro HBA
			Registro registro = null;
			try {
				registro = RegistroDAO.create("HBA", dataItem.getHbae());
			} catch (ExcepcionIntegridad e) {
				log.debug("Tuvimos problemas con la creacion del registro del animal de pedigree");
				throw new InvalidDataItemException("No se pudo crear correctamente el registro del animal", dataItem.getFila());
			}
			registro.setCodigoBaja(dataItem.getCbaj());
			registro.setFechaBaja(dataItem.getFbaj());
			
			// seteo el regOri y el regId con registro tiene que ser el mismo HBA		
			nuevoAnimal.setRegistros(null);
			nuevoAnimal.setRegistros(new HashSet());
			//if ("Nacional".equals(origen)) // si es nacional el mismo registro
			nuevoAnimal.setRegIdentificador(registro);
			nuevoAnimal.setRegOrigen(registro);
			nuevoAnimal.addRegistro(registro);
			this.setDemasValores(animalModificado,dataItem);
			HibernateFactory.getSession().save(nuevoAnimal);
			
		}
		else{
			Registro registro = animalModificado.getRegOrigen();
			if (dataItem.getCbaj() != null  && dataItem.getCbaj().intValue() == 1 && registro.getCodigoBaja()!=null && registro.getCodigoBaja().intValue() == 1)
				// quiero dar de baja un registro y el registro esta dado de baja
				throw new InvalidDataItemException("Se quiere dar inactivar el registro "+registro.getNumero()+" "+registro.getTipoRegistro().getId()+" que ya esta inactivo", dataItem.getFila());
			
			registro.setCodigoBaja(dataItem.getCbaj());
			registro.setFechaBaja(dataItem.getFbaj());
			
			// me parece que vamos a tener que hacer el udpate del registro
			
			if (padre == null && madre == null) {// es un empadronar
				try {
					AnimalDAO.actualizarAnimalPedEmpadronar(animalModificado, tamboCriador, animalModificado.getPropietarioCriador(), 
							entidadRaza, tamboPropietario, animalModificado.getPropietario(), registro, 
							esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb(),true);
				} catch (ExcepcionIntegridad e) {								
					ProcCodMsg cod = ProcCodMsgDAO.findByPrimaryKey(e.getCodigoError());
					throw new InvalidDataItemException(cod.getDescripcion(), dataItem.getFila());								
				}
			}
			else { // es con padre y madre
				try {
					AnimalDAO.actulizarAnimalPedPadres(animalModificado, tamboCriador, animalModificado.getPropietarioCriador(), padre,
								madre, entidadRaza, tamboPropietario, animalModificado.getPropietario(), registro, esHembra, msg, dataItem.getFnac(), dataItem.getRpex(), dataItem.getNomb(),true);
				} catch (ExcepcionIntegridad e) {
					throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());				
				}
			}
			this.setDemasValores(animalModificado,dataItem);
			//animalModificado.setNombre(dataItem.getNomb()); lo paso arriba para pruebas
			AnimalDAO.updateAnimal(animalModificado);
		}
		// setear los demas campos
		
		//HibernateFactory.getSession().update(animalModificado);
	}
	private void procesarModificacionRP(String raza, boolean esHembra, DataItem dataItem) throws InvalidDataItemException {
		log.debug("procesando la fila "+dataItem.getFila());
		String rc = dataItem.getRc();
		String hba = dataItem.getHba();
		// Busco con el hba, sexo, raza, el animal de cierto sexo con el rgOri hba
		Animal animalModificado = null;
		String aux="";
		try {
			//si cambio la raza busco con la raza anterio
			//si cambio el sexo busco por el sexo contrario al cargado en la pantalla
			//esto es para poder encontrar el animal en la base
			aux = "Registro: "+((rc==null)?"HBA":"RC")+" "+((rc==null)?hba:rc)+" sexo: "+(esHembra?"Hembra":"Macho")+" raza: "+raza +" Fecha Nacimiento: "+DateUtils.format(dataItem.getFnac(),"dd/MM/yyyy");
			animalModificado = AnimalDAO.findExistentByRegistry(((rc==null)?"HBA":"RC"), ((rc==null)?hba:rc), raza,(esHembra?"H":"M"));	
		} catch (ExcepcionIntegridad e1) {
			//String aux = "HBA: "+hbae+" sexo: "+((cambioSexo)? (esHembra?"Macho":"Hembra"):(esHembra?"Hembra":"Macho"))+" raza: "+((cambioRaza)? razaAnterior:raza);
			throw new InvalidDataItemException("No existe animal "+aux, dataItem.getFila());
		}
		if (animalModificado == null){
			//String aux = "HBA: "+hbae+" sexo: "+((cambioSexo)? (esHembra?"Macho":"Hembra"):(esHembra?"Hembra":"Macho"))+" raza: "+((cambioRaza)? razaAnterior:raza);
			throw new InvalidDataItemException("No existe Animal "+aux, dataItem.getFila());
		}
		if(!(DateUtils.mismoDia(animalModificado.getFechaNac(),dataItem.getFnac())))
			throw new InvalidDataItemException("No existe Animal "+aux, dataItem.getFila());
		if(dataItem.getRpViejo()!=null){
			if(!dataItem.getRpViejo().equals(animalModificado.getRP()))
				if(!dataItem.getRpNuevo().equals(animalModificado.getRP())){
					aux = ((rc==null)?"HBA":"RC")+" "+((rc==null)?hba:rc);
					throw new InvalidDataItemException("El animal "+aux+ " no tiene como RP al rp viejo:"+dataItem.getRpViejo()+ " ni al rp nuevo: "+dataItem.getRpNuevo()+", tiene como rp al valor: "+animalModificado.getRP(), dataItem.getFila());
				}
		}
		
		Establecimiento tamboNuevo = EstablecimientoDAO.findByPrimaryKey(dataItem.getTambo());	
		if(tamboNuevo==null)
			throw new InvalidDataItemException("No existe el tambo "+dataItem.getTambo(), dataItem.getFila());
		Propietario propAnimNuevo = null;
		if(dataItem.getProp()!=null){
			propAnimNuevo = PropietarioDAO.findByPrimaryKey(dataItem.getProp());	
			if(propAnimNuevo==null)
				throw new InvalidDataItemException("No existe el propietario "+dataItem.getProp(), dataItem.getFila());
		}
		
			try {
				Animal.checkUnicidadRPEnEstabForUpdate(animalModificado.getId(),tamboNuevo,dataItem.getRpNuevo(),new ArrayList(), animalModificado.getFechaNac(),animalModificado.getEstablecimiento(),animalModificado.getRegistroOrigen(),animalModificado.getCategoria());
			} catch (ErrorFatal e) {
				throw new InvalidDataItemException(e.getMessage(), dataItem.getFila());
			} catch (ExcepcionIntegridad e) {
				ProcCodMsg cod=null;
				try{
					cod = ProcCodMsgDAO.findByPrimaryKey(e.getCodigoError());
				}catch (Exception e2) {
					throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());
				}
				if(cod==null){
					throw new InvalidDataItemException(e.getCodigoError(), dataItem.getFila());
				}
				else{
						ProcMsg msg = ProcMsgDAO.create(e.getCodigoError(),ProcMsg.ERROR,e.getValores());
						throw new InvalidDataItemException(msg.getInformacion(), dataItem.getFila());
				}
			}
		animalModificado.setRP(dataItem.getRpNuevo());
		animalModificado.setEstablecimiento(tamboNuevo);
		if(tamboNuevo!=null)
			animalModificado.setEstancia(tamboNuevo.getEstancia());
		if(propAnimNuevo!=null)
			animalModificado.setPropietario(propAnimNuevo);
		AnimalDAO.updateAnimal(animalModificado);
		
		
	}
	private void validarCambioSexoYRaza(Animal anim,String aux,int fila) throws InvalidDataItemException {
		if(!anim.getAllEventosSinLactanciaAbierta().isEmpty()){//si tiene eventos
			throw new InvalidDataItemException("El animal "+aux+" no puede cambiar de sexo ni de raza por que tiene eventos asociados", fila);
		}
		if(anim.esHembra()){
			Hembra h = (Hembra)anim;
			if(!h.getCalificacions().isEmpty()){//si tiene calificaciones
				throw new InvalidDataItemException("La hembra "+aux+" no puede cambiar de sexo ni de raza por que tiene calificaciones asociados", fila);
			}
			if((!h.getHijosGeneticos().isEmpty())|| (!h.getHijosParto().isEmpty()))
				throw new InvalidDataItemException("La hembra "+aux+" no puede cambiar de sexo ni de raza por que es madre", fila);
		}
		else{
			Macho m = (Macho)anim;
			if(!m.getEvtNuevoInds().isEmpty()){
				throw new InvalidDataItemException("El macho "+aux+" no puede cambiar de sexo por que tiene servicios asociados", fila);
			}
			if(!m.getHijosGeneticos().isEmpty()){//si tiene hijos
				throw new InvalidDataItemException("El macho "+aux+" no puede cambiar de sexo por que tiene crias asociadas", fila);
			}
		}
		
	
}
	private void setDemasValores(Animal animalModificado, DataItem dataItem){
		animalModificado.setNombre(dataItem.getNomb());
		animalModificado.setAsoc(dataItem.getAsoc());
		animalModificado.setDadorSemen(dataItem.getDado());
		animalModificado.setNumeroTransf(dataItem.getTran());
		animalModificado.setNumeroAnalADN(dataItem.getAnls());
		animalModificado.setDonante(dataItem.getDona());
		animalModificado.setMellizo(dataItem.getMell());
		animalModificado.setTransferencia(dataItem.getTrns());
		animalModificado.setTipoServicio(dataItem.getTser());
		animalModificado.setAsop(dataItem.getAsop());
		animalModificado.setAsom(dataItem.getAsom());
		animalModificado.setSRAFesb(dataItem.getFesb());
		animalModificado.setFechaUltObs((dataItem.getFobs()!=null)?dataItem.getFobs():new Date());
		animalModificado.setFechaTransf(dataItem.getFtrf());
		animalModificado.setFechaServicio(dataItem.getFser());
		animalModificado.setApodo(dataItem.getApdo());
		animalModificado.setRpti(dataItem.getRpti());
	}
	/**
	 * 
	 * @param dataItems
	 * @param errores
	 */
	private void getDataItemsParaModificacion(List<DataItem> rta, Set<CellError> errores,boolean rp) {
		int inicio = this.accessor.getFirstRowNum();
		int fin = this.accessor.getLastRowNum();
		for (int row = inicio+1; row <= fin; row++) { // recorro las filas			
			DataItem dataItem = null;
			try {
				if (accessor.isEmpty(row))
					break;			
				dataItem = createDataItemModificacion(row,rp);				
				rta.add(dataItem);
			} catch (FormatCellException e) {				
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			} catch (POIException e) {
				CellError error = new CellError(row+1, e.getMessage());
				errores.add(error);
			}			
		}
	}

	/**
	 * 
	 * @param row
	 * @return
	 * @throws FormatCellException
	 */
	private DataItem createDataItemModificacion(int row,boolean rp) throws FormatCellException {
		DataItem dataItem = new DataItem();
		dataItem.setFila(row); // le meto la fila de donde se obtuvo el dataItem
		int col = -1;
		if(!rp){
		col = (mapping.get("ANLS")!=null)?mapping.get("ANLS"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col)!= null) {
				dataItem.setAnls(accessor.getDoubleValue(row, col).toBigInteger().intValue());
			}
		} catch (FormatCellException e1) {
			throw new FormatCellException("El campo ANLS del archivo excel no es un valor entero", row, col);			
		}
		col = (mapping.get("RPTI")!=null)?mapping.get("RPTI"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setRpti(accessor.getStringValue(row, col));
		
		col = (mapping.get("DONA")!=null)?mapping.get("DONA"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setDona(accessor.getStringValue(row, col));
		
		col = (mapping.get("APDO")!=null)?mapping.get("APDO"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setApdo(accessor.getStringValue(row, col));
		
		col = (mapping.get("ASOC")!=null)?mapping.get("ASOC"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col)!=null)
				dataItem.setAsoc(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e1) {
			throw new FormatCellException("El campo ASOC del archivo excel no es un valor entero", row, col);			
		}
		
		col = (mapping.get("ASOM")!=null)?mapping.get("ASOM"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setAsom(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo ASOM del archivo excel no es un valor entero", row, col);
		}

		col = (mapping.get("ASOP")!=null)?mapping.get("ASOP"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setAsop(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo ASOP del archivo excel no es un entero", row, col);
		}
		
		col = (mapping.get("CBAJ")!=null)?mapping.get("CBAJ"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null) {
				Integer cbaj = accessor.getDoubleValue(row, col).toBigInteger().intValue();				
				dataItem.setCbaj(cbaj);
			}
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo CBAJ del archivo excel no es un valor entero valido ", row, col);
		}
		if (dataItem.getCbaj() != null && (dataItem.getCbaj().intValue() < 0 || dataItem.getCbaj().intValue() > 8))
			throw new FormatCellException("El campo CBAJ del archivo excel debe ser un entero entre 0 y 8", row, col);
		
		col = (mapping.get("CRIA")!=null)?mapping.get("CRIA"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setCria(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo CRIA del archivo excel no es un valor entero", row, col);
		}
		col = (mapping.get("DADO")!=null)?mapping.get("DADO"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setDado(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo DADO del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("FBAJ")!=null)?mapping.get("FBAJ"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFbaj(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FBAJ del archivo excel no es una fecha valida", row, col);
		}		
		
		col = (mapping.get("FESB")!=null)?mapping.get("FESB"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFesb(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FESB del archivo excel no es un valor entero", row, col);
		}
		col = (mapping.get("FNAC")!=null)?mapping.get("FNAC"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFnac(accessor.getDateValue(row, col));			
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FNAC del archivo excel no es una Fecha Valida", row, col);
		}
		
		col = (mapping.get("FOBS")!=null)?mapping.get("FOBS"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFobs(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FOBS del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("FSER")!=null)?mapping.get("FSER"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFser(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FSER del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("FTRF")!=null)?mapping.get("FTRF"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFtrf(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FTRF del archivo excel no es una fecha valida", row, col);
		}
		
		col = (mapping.get("HBAE")!=null)?mapping.get("HBAE"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col))) {			
			if (accessor.getStringValue(row, col).length() > 12)				
					throw new FormatCellException("El HBA del animal (HBAE) excede la cantidad de caracteres permitidos", row, col);
			dataItem.setHbae(accessor.getStringValue(row, col));			
		}
		if (StringUtils.isEmpty(dataItem.getHbae())) {
			throw new FormatCellException("El HBA del animal (HBAE) es un campo obligatorio ", row, col);
		}
		col = (mapping.get("HBAM")!=null)?mapping.get("HBAM"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setHbam(accessor.getStringValue(row, col));
		
		col = (mapping.get("HBAP")!=null)?mapping.get("HBAP"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setHbap(accessor.getStringValue(row, col));
		
		col = (mapping.get("MELL")!=null)?mapping.get("MELL"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setMell(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo MELL del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("NOMB")!=null)?mapping.get("NOMB"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setNomb(accessor.getStringValue(row, col));
		
		col = (mapping.get("PROP")!=null)?mapping.get("PROP"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setProp(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo PROP del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("RPEX")!=null)?mapping.get("RPEX"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setRpex(accessor.getStringValue(row, col));
		
		col = (mapping.get("TRAN")!=null)?mapping.get("TRAN"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setTran(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo TRAN del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("TRNS")!=null)?mapping.get("TRNS"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setTrns(accessor.getStringValue(row, col));
		
		col = (mapping.get("TSER")!=null)?mapping.get("TSER"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setTser(accessor.getDoubleValue(row, col).toBigInteger().intValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo TSER del archivo excel no es un valor entero", row, col);
		}
		}
		else{
			col = (mapping.get("RC")!=null)?mapping.get("RC"):-1;
			if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col))) {			
				if (accessor.getStringValue(row, col).length() > 12)				
						throw new FormatCellException("El RC del animal (RC) excede la cantidad de caracteres permitidos", row, col);
				dataItem.setRc(accessor.getStringValue(row, col));			
			}
			col = (mapping.get("HBA")!=null)?mapping.get("HBA"):-1;
			if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col))) {			
				if (accessor.getStringValue(row, col).length() > 12)				
						throw new FormatCellException("El HBA del animal (HBA) excede la cantidad de caracteres permitidos", row, col);
				dataItem.setHba(accessor.getStringValue(row, col));			
			}
			if (StringUtils.isEmpty(dataItem.getRc())&&(StringUtils.isEmpty(dataItem.getHba()))) {
				throw new FormatCellException("El RC o HBA del animal es un campo obligatorio ", row, col);
			}
			if (StringUtils.isNotEmpty(dataItem.getRc())&&(StringUtils.isNotEmpty(dataItem.getHba()))) {
				throw new FormatCellException("Debe informarse solamente el HBA o el RC", row, col);
			}
			col = (mapping.get("TAMBO")!=null)?mapping.get("TAMBO"):-1;
			try {
				if (col != -1 && accessor.getDoubleValue(row, col) !=null)
					dataItem.setTambo(accessor.getDoubleValue(row, col).longValue());
			} catch (FormatCellException e) {
				throw new FormatCellException("El campo TAMBO del archivo excel no es un valor entero", row, col);
			}
			if(accessor.getDoubleValue(row, col)==null)
				throw new FormatCellException("El TAMBO del animal es un campo obligatorio ", row, col);
			
			col = (mapping.get("PROP.ANIM")!=null)?mapping.get("PROP.ANIM"):-1;
			try {
				if (col != -1 && accessor.getDoubleValue(row, col) !=null)
					dataItem.setProp(accessor.getDoubleValue(row, col).longValue());
			} catch (FormatCellException e) {
				throw new FormatCellException("El campo PROP.ANIM del archivo excel no es un valor entero", row, col);
			}
			
			
			col = (mapping.get("FNAC")!=null)?mapping.get("FNAC"):-1;
			try {
				if (col != -1 && accessor.getDateValue(row, col) != null)
					dataItem.setFnac(accessor.getDateValue(row, col));			
			} catch (FormatCellException e) {
				throw new FormatCellException("El campo FNAC del archivo excel no es una Fecha Valida", row, col);
			}
			if(accessor.getDateValue(row, col)==null)
				throw new FormatCellException("La fecha de nacimiento del animal es un campo obligatorio ", row, col);
			
			col = (mapping.get("RP.NUEVO")!=null)?mapping.get("RP.NUEVO"):-1;
			if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
				dataItem.setRpNuevo(accessor.getStringValue(row, col));
			if (StringUtils.isEmpty(dataItem.getRpNuevo())) {
				throw new FormatCellException("El RP NUEVO es un campo obligatorio ", row, col);
			}
			col = (mapping.get("RP.VIEJO")!=null)?mapping.get("RP.VIEJO"):-1;
			if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
				dataItem.setRpViejo(accessor.getStringValue(row, col));
			}
		return dataItem;
	}

	public int realizarModificacionesInternacionales(String raza, boolean esHembra, Set<CellError> errores, String sheetName,boolean cambioRaza,boolean cambioSexo,String razaAnterior)throws CargaMasivaException {
		this.accessor.setSheetName(sheetName);
		this.crearMappingParaModificacionInternacional();		
		
		List<DataItem> dataItems = new ArrayList<DataItem>();
		getDataItemsParaModificacionInternacional(dataItems, errores);
		Iterator iteratorDataItems = dataItems.iterator();
		int i = 0;
		while (iteratorDataItems.hasNext())  {
			DataItem dataItem = (DataItem) iteratorDataItems.next();
			try {
				procesarModificacionInternacional(raza, esHembra, dataItem);
				StandaloneHibernateStrategy.getInstance().commitCurrentSession();
				i++;
			} catch (InvalidDataItemException e) {				
				StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
				log.debug("Error en la carga masiva");
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			}
		}
		return i;
	}

	private void crearMappingParaModificacionInternacional() throws CargaMasivaException  {
		int firstRow = accessor.getFirstRowNum();		
		int i;
		try {
			i = accessor.getFirstCellNum(firstRow);
			int n = accessor.getLastCellNum(firstRow);		
			for (int k = i; k < n; k++) {			
				mapping.put(accessor.getStringValue(firstRow, k), k);
			}
			validateHeaderParaModificacion();
		} catch (POIException e) {
			throw new CargaMasivaException("El formato de la Cabecera del archivo excel (fila 0) no es correcto");
		}				
	}

	private void validateHeaderParaModificacion() throws CargaMasivaException {
			String aux = "";
			if (!mapping.containsKey("HBAE"))
				aux += "HBAE, "; // tiene que estar para poder identificar el bicho
			if (!mapping.containsKey("RPEX"))
				aux += "RPEX, "; // se puede modificar
			if (!mapping.containsKey("NOMB"))
				aux += "NOMB, "; // se puede modificar
			if (!mapping.containsKey("PROP"))
				aux += "PROP, "; //se puedo modificar
			if (!mapping.containsKey("CRIA"))
				aux += "CRIA, "; // se puede modificar
			if (!mapping.containsKey("FBAJ"))//
				aux += "FBAJ, ";
			if (!mapping.containsKey("FESB"))//
				aux += "FESB, ";
			if (!mapping.containsKey("FOBS"))//
				aux += "FOBS, ";
			if (!mapping.containsKey("FTRF"))//
				aux += "FTRF, ";
			if (!mapping.containsKey("FSER"))//
				aux += "FSER, ";
			if (aux != "") // hay error
				throw new CargaMasivaException("Las siguientes columnas son obligatorias: "+aux);					
		}

	

	private void procesarModificacionInternacional(String raza, boolean esHembra, DataItem dataItem) throws InvalidDataItemException {
		log.debug("procesando la fila "+dataItem.getFila());		
		// validar que el registro no este en la base (tiene que ser nuevo)
		String hbae = dataItem.getHbae();		
		// Busco con el hba, sexo, raza, el animal de cierto sexo con el rgOri hba
		Animal animalModificado = null;		
		try {
			animalModificado = AnimalDAO.findExistentByRegistry("HBA", hbae, raza, esHembra?"H":"M");			
		} catch (ExcepcionIntegridad e1) {
			String aux = "HBA: "+hbae+" sexo: "+((esHembra)?"Hembra":"Macho")+" raza: "+raza;
			throw new InvalidDataItemException("No existe animal "+aux, dataItem.getFila());
		}
		if (animalModificado == null){
			String aux = "HBA: "+hbae+" sexo: "+((esHembra)?"Hembra":"Macho")+" raza: "+raza;
			throw new InvalidDataItemException("No existe Animal "+aux, dataItem.getFila());
		}
		animalModificado.setNombre(dataItem.getNomb()); 
		// verifico que el propietario "nuevo" este en la base
		//por pedido de Daniel no se realiza mas el chequeo ni actualizacion del propietario 05/11/2010
		Long idProp = dataItem.getProp();
		if (idProp == null) {
			throw new InvalidDataItemException("El propietario del animal (PROP) es un campo obligatorio", dataItem.getFila());
		}
		
		PropietarioExpd propietario = null;
		Raza r = RazaDAO.findByPrimaryKey(raza);
		try {			
			propietario = PropietarioExpdDAO.findByNumeroyRaza(idProp,r);
			if (propietario == null) {
				throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());	
			}
		}
		catch (HibernateException e) {
			throw new InvalidDataItemException("El propietario con EXPD "+idProp+" (PROP) no existe en la base de datos", dataItem.getFila());
		}
		// validar que el propietario creador este en la base (solo si me informan el id del creador)
		Long idCriador = dataItem.getCria();
		PropietarioExpd propietarioCriador = null;
		if (idCriador != null) {
			try {
				propietarioCriador = PropietarioExpdDAO.findByNumeroyRaza(idCriador,r);
				if(propietarioCriador== null)
					propietarioCriador = propietario;
			}
			catch (HibernateException e) {
				throw new InvalidDataItemException("El propietario criador con EXPD "+idCriador+" (CRIA) no existe en la base de datos", dataItem.getFila());
			}
		}
		else { // si no me viene el propietatio criador es el mismo propietario
			propietarioCriador = propietario;
		}
		
		Propietario pc = PropietarioDAO.findByPrimaryKey(propietarioCriador.getPropietario().getId());
		Propietario p = PropietarioDAO.findByPrimaryKey(propietario.getPropietario().getId());
		Propietario pAnim = animalModificado.getPropietario();
		if(pAnim != null && !pAnim.equals(p))//si cambio el propietario, el tambo es null Daniel 5/11/2010
			animalModificado.setEstablecimiento(null);
		
		animalModificado.setPropietario(p);
		animalModificado.setPropietarioCriador(pc);

		Registro regOri = animalModificado.getRegOrigen();
		if (dataItem.getFbaj() != null) { // viene la fecha de baja, le pongo un 1 en el reg ori y la fecha de baja del registro
			regOri.setCodigoBaja(new Integer(1));
			regOri.setFechaBaja(dataItem.getFbaj());
		}
			
		animalModificado.setSRAFesb(dataItem.getFesb());
		animalModificado.setFechaUltObs((dataItem.getFobs()!=null)?dataItem.getFobs():new Date());
		animalModificado.setFechaTransf(dataItem.getFtrf());
		animalModificado.setFechaServicio(dataItem.getFser());
		
		AnimalDAO.updateAnimal(animalModificado);		
	}

	private void getDataItemsParaModificacionInternacional(List<DataItem> rta, Set<CellError> errores) {
		int inicio = this.accessor.getFirstRowNum();
		int fin = this.accessor.getLastRowNum();
		for (int row = inicio+1; row <= fin; row++) { // recorro las filas			
			DataItem dataItem = null;
			try {
				if (accessor.isEmpty(row))
					break;			
				dataItem = createDataItemModificacionInternacional(row);				
				rta.add(dataItem);
			} catch (FormatCellException e) {				
				CellError error = new CellError(e.getFila()+1, e.getMessage());
				errores.add(error);
			} catch (POIException e) {
				CellError error = new CellError(row+1, e.getMessage());
				errores.add(error);
			}			
		}
		
	}

	private DataItem createDataItemModificacionInternacional(int row) throws FormatCellException {
		DataItem dataItem = new DataItem();
		dataItem.setFila(row); // le meto la fila de donde se obtuvo el dataItem
		
		int col = -1;	
		
		col = (mapping.get("HBAE")!=null)?mapping.get("HBAE"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col))) {			
			if (accessor.getStringValue(row, col).length() > 12)				
				throw new FormatCellException("El HBA del animal (HBAE) excede la cantidad de caracteres permitidos", row, col);
			dataItem.setHbae(accessor.getStringValue(row, col));			
		}
		if (StringUtils.isEmpty(dataItem.getHbae())) {
			throw new FormatCellException("El HBA del animal (HBAE) es un campo obligatorio ", row, col);
		}
		
		col = (mapping.get("RPEX")!=null)?mapping.get("RPEX"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setRpex(accessor.getStringValue(row, col));

		col = (mapping.get("NOMB")!=null)?mapping.get("NOMB"):-1;
		if (col != -1 && StringUtils.isNotEmpty(accessor.getStringValue(row, col)))
			dataItem.setNomb(accessor.getStringValue(row, col));
		
		col = (mapping.get("PROP")!=null)?mapping.get("PROP"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setProp(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo PROP del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("CRIA")!=null)?mapping.get("CRIA"):-1;
		try {
			if (col != -1 && accessor.getDoubleValue(row, col) !=null)
				dataItem.setCria(accessor.getDoubleValue(row, col).longValue());
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo CRIA del archivo excel no es un valor entero", row, col);
		}
		
		col = (mapping.get("FBAJ")!=null)?mapping.get("FBAJ"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFbaj(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FBAJ del archivo excel no es una fecha valida", row, col);
		}		
		
		col = (mapping.get("FESB")!=null)?mapping.get("FESB"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFesb(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FESB del archivo excel no es un valor entero", row, col);
		}		
		
		col = (mapping.get("FOBS")!=null)?mapping.get("FOBS"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFobs(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FOBS del archivo excel no es una fecha valida", row, col);
		}	
		
		
		col = (mapping.get("FTRF")!=null)?mapping.get("FTRF"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFtrf(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FTRF del archivo excel no es una fecha valida", row, col);
		}		
		
		col = (mapping.get("FSER")!=null)?mapping.get("FSER"):-1;
		try {
			if (col != -1 && accessor.getDateValue(row, col) != null)
				dataItem.setFser(accessor.getDateValue(row, col));
		} catch (FormatCellException e) {
			throw new FormatCellException("El campo FSER del archivo excel no es una fecha valida", row, col);
		}	
						

		return dataItem;
	}
	

}
