/*
 * Created on 22/05/2005
 *
 */
package ar.org.sicel.persistence;

/**
 * @author pablo
 */
public interface MENSAJES {

	String XML_NO_VALIDO = "XmlInv";

	String ECLO_NO_VALIDA = "ECLOInv";

	String ESTABLECIMIENTO_NO_EXISTE = "ESTINEX";

	String ESTABLECIMIENTO_NO_EN_ECLO = "ESTECLO";

	String ESTABLECIMIENTOS_OK = "ESTOK";

	String ESTABLECIMIENTOS_ERROR = "ESTERROR";

	String EVT_EST_OK = "EVESTOK";

	String EVT_EST_ERROR = "EVESTERR";

	String EVT_FUERA_ORDEN = "EVTNOORD";

	String LOTE_REPETIDO = "LOTEREP";

	String INSCRIBIR_MACHO_ACEPTA = "INSMACHO";

	String INSCRIBIR_HEMBRA_OBLIGA = "INSHEMBR";

	String ANIMALES_OK = "ANIMOK";

	String ANIMALES_ERROR = "AnimErr";

	String EVENTO_NO_PROCESADO = "EVTNOPR";

	String ANIMAL_NO_EXISTE = "ANIMINEX";

	String RP_NO_COINCIDE = "RPNOMACH";

	String TRANSFERENCIA_IMPLICITA_REALIZADA = "TRANIMPL";

	String EVT_ANIMAL_OK = "EVANOK";

	String EVT_ANIMAL_ERROR = "EVANER";

	String NOMBRE_ECLO = "NECLO";

	String NOMBRE_ESTAB = "NESTAB";

	/**
	 * esta hay que sacarla despues, la uso para tener algo que no signifique
	 * nada..y poder tirar esta excepcion en las partes que no estan
	 * implementadas
	 */
	String NOT_IMPLEMENTED = "NOTIMPL";

	String CANTIDAD_RAZAS_DESC_NO1 = "RADESCN1";
	
	String CANTIDAD_RAZAS_CRUZA_NO1 = "RACRUZA1";

	String PROGENITOR_DISTINTA_ESPECIE = "PRODIESP";

	String TIPO_REGISTRO_NO_EXISTE = "TIREGINX";

	String COMP_RACIAL_INVALIDA = "COMPRINV";

	String NO_COMPRACIAL_SI_PADRES = "NOCRPADR";

	String RP_NO_UNICO = "RPNOUNI";
	
	String RP_NO_UNICO_PED = "RPNOUNIP";

	String VALOR_NO_ATR = "VALNOATR";

	String MED_OBJ_REPETIDO = "MEOBJREP";

	String MED_ERR = "MEDERR";

	String ERROR_FATAL = "ERRFATAL";

	String ANIMAL_NO_ESTABLECIMIENTO = "ANNOEST";

	String ANIMAL_NO_EST_NO_DUENIO = "ANNOESDU";

	String TRANSICION_NO_HALLADA = "TRAN_NO";

	String DOS_EN_UNO = "2En1";

	String AboCorto = "AboCorto";

	String CriaVaci = "CriaVaci";

	String FinPre = "FinPre";

	String InteTran = "InteTran";

	String NoRepro = "NoRepro";

	String NoSvc = "NoSvc";

	String ProdSeca = "ProdSeca";

	String SvcPre = "SvcPre";

	String VacPre = "VacPre";

	String YaBaja = "YaBaja";

	String PROC_LOTE_NOEX = "NoLote";

	String PROC_ELIM = "NoEliPro";

	String EVT_ELIM = "NoEliEv";

	String YaEstado = "YaEstado";

	String YaSvc = "YaSvc";

	String EvProdMetodoControl = "PrMC";

	String EvProdNumOrdenies = "PrNumOrd";

	String EvProdTipoMuestreo = "PrTMue";

	String EvProdObjeto = "PrObj";

	String NO_ACEPTA_COMP_RACIAL_ALTA = "NOCOMPAL";

	String NO_ACEPTA_RAZA_DECLARADA_ALTA = "NODECLAL";

	String RAZA_NO_EXISTE = "RAZAINEX";

	String IMPOSIBLE_RESOLVER_ESPECIE_ANIMAL = "IMPESPAN";

	String REGISTRO_ASIGNADO = "REGASIGN";

	String NO_MACHO = "NOMACHO";

	String NO_HEMBRA = "NOHEMBRA";

	String EDAD_MINIMA_INVALIDA_SERVICIO = "EDSERVIN";

	String EDAD_MINIMA_INVALIDA_SERVICIO_TORO = "EDSTORIN";

	String ANIMALES_NO_EST_PROP_PARA_SERVICIO = "SERVNOEP";

	String EDAD_MINIMA_INVALIDA_PRODUCCION_EMBRIONES = "IEDPREMB";
	
	String NO_ACEPTA_USAR_RAZA_MADRE_PARTO = "NORAMAPA";

	String RAZA_MADRE_CON_SERVICIO_VALIDO = "RAMASEVA";

	String PARTO_SIN_SERVICIO = "PASINSER";

	String POSIBLE_SERVICIO_DESCARTADO = "SERVDESC";

	String SERVICIO_UTILIZADO = "SERVUSAD";

	String SERVICIO_SOLAPADO = "SERVSOPD";

	String NO_ACEPTA_FECHA_SERVICIO_EN_PARTO = "NOFSERPA";

	String SERVICIO_ESPECIFICADO_NO_EXISTE = "SERVINEX";

	String SERVICIO_ESPECIFICADO_FUERA_DE_PERIODO = "SERVINV";

	String SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO = "SERVNOAP";

	String NO_ESPECIFICA_FECHA_SERVICIO = "NOESSERV";

	String INSCRIBIR_CRIA_MUERTA = "INCRMUER";

	String INSCRIBIR_CRIA_SIN_RP = "CRIASRP";

	String LARGO_EXISTE_CORTO = "LAREXCOR";

	String PROPIETARIO_NO_EXISTE = "PROPINEX";

	String FECHA_LOTE_INVALIDA = "FELOINV";

	String FECHA_EVENTO_INVALIDA = "FEEVINV";

	String FECHA_EVENTO_ANTERIOR_A_ULTIMO = "FEEVANT";

	String EDAD_MAX_FERTIL = "EDMAXFER";

	String CRIA_SIN_SEXO = "CRSINSEX";

	String SERVICIO_EN_PERIODO_DESCANZO = "SERVENDE";

	String PERIODO_PARTOS_INVALIDO = "PEPARINV";

	String CRIA_PROP = "PRESTMAP";

	String PRODUCCION_SIN_ORDENIE = "PRSINORD";

	String ID_ORDENIE_INCORRECTO = "IDORDINC";

	String TAMANIO_FOTO_INCORRECTO = "FOTOINC";

	String FOTO_NO_LEIDA = "FOTONOLE";

	String NUM_ORDENIES_NO_COINCIDE = "ORDINV";

	String NO_ACEPTA_ABORTO_LARGO_LUEGO_DE_X_DIAS = "MAXAL";

	// No se aceptan abortos largos despues de %s[dias] dias de gestación. Debe
	// informarse las crias muertas o vivas.";

	// Si el evento estado dice que inicia lactancia, entonces me tiene que
	// informar la fecha de inicio de lactancia, sino lo rechazo
	String ESTADO_INICIA_LACTANCIA_NO_INFORMA_FECHA = "EILNIF";

	String REG_ORIGEN_NO_SOPORTADO = "REGORNO";
	
	String NO_SETEA_RAZA = "NOSETRAZ";

	String RAZA_DECLARADA_NO_COINCIDE_RAZA_CALCULADA = "RAZANCCC";
	
	String EVT_ORDENIE_OK = "ORDOK";
	
	String EVT_ORDENIE_MODIF_OK = "ORDMODOK";
	
	// Error cuando se realiza la baja del evento alta y existen eventos posteriores al evento alta
	// que se desea eliminar
	String EVT_BAJA_ALTA_WITHDEP = "HAYEVPOS";
	
	// Error al dar de baja un evt reproduccion, alguna de las crias tiene ya algun evento asociado
	String EVT_BAJA_REP_CRIAS = "HCRIAEVT";
	
	// Error de tipo de evento informado
	String ERROR_TIPO_EVT = "ERTIPOEV";
	
	// Error intentando modificar el animal informado para el evento
	String ERROR_ANIMAL_INFO = "ERANINF";
	
	// Error al intentar modificar la fecha de un evento que afecta lactancia posteriores
	String ERROR_EVT_LACTANCIA_POST = "ERLACPOS";
	
	// Error al intentar modificar las crías informadas bajo un evento reproducción
	String ERROR_MODIF_CRIAS = "ERMODCRI";
	
	// Error al intentar modificar la fecha del servicio durante un evento reproducción
	String ERROR_MODIF_FECHA_SERVICIO = "ERMODFSV";
	
	// Error al intentar modificar la información de si fue o no un aborto largo
	String ERROR_MODIF_ABORTO_LARGO = "ERMODABL";
	
	/**
	 * Cuando la eclo informada no coincide con la que genero el evento
	 */	
	String MODIFICACION_BAJA_ECLO_NO_COINCIDE = "ECLONo";
	
	/**
	 * Mensaje para cuando haya eventos anteriores, EN MODIFICACION
	 */
	String EVENTOS_ANTERIORES = "EVTAnt";
	

	/**
	 * Cuando se quiere modificar el sexo de un animal pero hay eventos posteriores
	 */
	String MODIFICACION_SEXO_EVENTOS = "SEXOMod";

	/**
	 * Existen por lo menos un servicio antes del año despues de la fecha de nacimiento nueva.
	 */	
	String MODIFICACION_SERVICIO_ANIO = "SERVanio";

	/**
	 * hay una reproduccion antes del los 16 años de la fecha de nac nueva, SE PROBO
	 */
	String MODIFICACION_REPRODUCCION_16 = "REPRO16";
	
	// Error al intentar modificar la fecha de un evento y esta es menor que la del evento anterior al que se quiere modificar
	String ERROR_EVENTO_ANT = "EREVANT";
	
	// Error al intentar modificar la fecha de un evento y esta es mayor que la del evento posterior al que se quiere modificar
	String ERROR_EVENTO_POST = "EREVPOST";
	
	
	/**
	 * Para modificacion de control establecimiento, != cantidad de ordenies animales informados
	 */
	String ERROR_CANTIDAD_ORDENIES_ANIMAL = "ERNROOR";
	
	String ERROR_CANTIDAD_ORDENIES = "ERNRORAN";

	/**
	 * Para modificacion de control establecimiento cuando ponen distintos rp a los existentes
	 */
	String ERROR_DISTINTOS_RP = "ERDISRP";

	/**
	 * Para cuando dentro de un control establecimiento no se genero ningun control animal
	 */
	String ERROR_CONTROL_ANIMAL_CERO = "ERCOANI0";

	
	/*
	 * Para cuando se quiere modificar el propietario o el establecimiento, y no esta permitido (Evt Transferencia)
	 */
	String MODIF_PROP_O_ESTAB = "MOPROEST";
	
	/*
	 * Para cuando se quiere modificar el estado en una modificacion del evt estado
	 */
	String MODIF_ESTADO_INFORMADO = "ETDODTOS";

	String ERROR_INSCRIBIR_MUERTO = "ERINSMUE";
	
	/*
	 * Para cuando se quiere dar de baja un evento estado
	 */
	String BAJA_ESTADO = "BAJAETDO";
	//	Error intentando modificar un servicio, no se puede hacer un cambio de servicio de semen a TE o viceversa
	String ERROR_CAMBIO_SERVICIO = "ESVCTIP";
	// Error intentando modificar un servicio de TE, si el servicio tiene una reproduccion asociada no se puede modificar la cant de dias de gestacion del embrion
	String ERROR_CAMBIO_DIAS_TE = "ESVTTEDG";

	/*
	 * Para cuando se quiere modificar un evento y el animal ya tiene la lactancia cerrada
	 */
	String LACTANCIA_CERRADA = "LACTCERR";
	
	/*
	 * Para cuando se quiere modificar la fecha de inicio de la ultima lactancia en el evento estado y no se informo 
	 * ninguna fecha en el evento estado original
	 */
	String FECHA_NO_INFORMADA = "FECNOINF";
	
	/*
	 * Para cuando se quiere poner como fecha en un evt servicio o preñez una fecha anterior
	 * a la de la ultima reproduccion
	 */
	String FECHA_EVENTO_ANT_REP = "FEANTREP";
	
	/*
	 * Para cuando se quiere poner como fecha en un evt servicio o preñez una fecha anterior
	 * a X meses desde el ultimo evento. La cantidad de meses está dada por la variable de configuración del sistema 
	 */
	String FECHA_EVT_ANT_X_MESES = "FEANT4ME";
	
	/* Para tipo de muestreo SM (sin muestra) sólo se debe informar medición de tipo leche */
	String MED_SOLO_LECHE = "MEDEQLE";
	
	/*
	 * Para cuando se le quiere hacer un evento a un animal que esta dado de baja
	 */
	String ANIMAL_DE_BAJA = "ANIBAJA";
	
	/*
	 * Para cuando se quiere dar de baja algun animal que no esta permitido
	 */
	String BAJA_IMPOSIBLE = "BAJAIMP";
	
	/*
	 * Para cuando se quiere informar una madre genetica y no se encuentra un evento 
	 * reproduccion en la fecha de nacimiento informada 
	 */
	String MADRE_SIN_REPROD = "MADSINRE";
	
	/*
	 * Para cuando se quiere dar de alta un animal informando los padres y la madre no tuvo
	 * ninguna cria del sexo informado en el alta en esa fecha 
	 */
	String SEXO_NO_CORRESPONDIENTE = "SEXNOCOR";
	
	/*
	 * Para cuando se quiere informar una reproduccion con retroactividad y se encuentra 
	 * un evento con fecha posterior que cerro una lactancia
	 */
	String FECHA_EVT_ANT_CIERRE_LACT = "FECANTLA";
	
	/*
	 * Para cuando se informa un evento que inicia lactancia y no se informa el numero de lactancia que inicia
	 */
	String NRO_LACT_NO_INF = "NRONOINF";
	
	/*
	 * Para cuando se quiere informar un evento que inicia lactancia, se informa el numero de lactancia que inicia
	 * y no se respeta los dias minimos entre inicios de lactancia 
	 */
	String DIAS_ENTRE_LACT = "DIASELAC";
	
	/*
	 * Para cuando se quiere informar un evento que inicia lactancia, se informa 
	 * numero de lactancia, y es igual o inferior que la numero de lactancia en la base 
	 */
	String ERROR_NRO_LACT = "NLACMAL";
	
	
	
	/*
	 * Para cuando se informa un evento alta o reproduccion con mas dias de diferencia
	 * que los permitidos entre la fecha del lote y la del evento
	 */
	String SUPERA_TIEMPO_LIMITE_LOTE = "DIASLOTE";
	
    /*
     * Paraq cuando se informa un alta con padres y el evento reproduccion asociado no cumple con los dias
     * minimos entre fechas de evento y de lotes
     */
	String SUPERA_TIEMPO_LIMITE_LOTE_EVT_REP = "DLOTEREP";
	
	String FICHA_INEXISTENTE = "FICHINEX";
	
	String NO_HAY_FICHAS = "NOFICHEX";
	/*
	 * Establecimiento dado de baja
	 */
	String ESTABLECIMIENTO_INACTIVO ="ESTABAJA";
	/*
	 * propietario dado de baja
	 */
	String PROPIETARIO_INACTIVO ="PROPBAJA";
	/*
	 * eclo dada de baja
	 */
	String ECLO_INACTIVO ="ECLOBAJA";
	/*
	 * Estancia dada de baja
	 */
	String ESTANCIA_INACTIVA="ESTBAJA";
	

	/**
	 * La cria de la reproduccion existe en la base de datos pero los datos de la
	 * reproduccion no coinciden con los de la base de datos 
	 */
	String CRIA_EXISTENTE_NO_COINCIDE = "RECRINCO";
	
	String LACTANCIA_MIGRADA_EXISTENTE = "LACMIGEX";
	/*
	 * 
	 */
	String RAZA_DECLARADA_DISTINTA_PADRES = "RADISPAD";
	
	String RAZA_DECLARADA_DISTINTA_MADRE = "RADISMAD";
	
	
	
	String INFORMA_PADRE_Y_NO_MADRE = "NOPADRE";
	
	String NO_EXISTE_EVENTO_A_DAR_DE_BAJA = "NOEXIEV";
	
	/**
	 * Para cuando un establecimiento FACT informa un evento control
	 */
	String ESTABLECIMIENTO_FACT = "EST_FACT";
	
	/**
	 * Para cuando un establecimiento informa un evento control con metodo de control distinto al que tiene asociado
	 */
	String DISTINTO_METODO_INFORMADO = "MECODIST";

	
	String RAZA_DECLARADA_DISTINTA_PADRE = "La raza declara del padre no se corresponde con la raza declara del animal";
	String MADRE_NO_MAYOR = "La madre debe ser como minimo 15 meses mayor que el animal";
	String PADRE_NO_MAYOR = "El padre debe ser como minimo 15 meses mayor que el animal";
	String FECHA_INCORRECTA = "La fecha es incorrecta";
	String ANIMAL_IGUAL_A_LA_MADRE = "La vaca seleccionada como madre es la vaca que esta tratando de modficar";
	String ANIMAL_IGUAL_A_LA_PADRE = "El toro seleccionado como padre es el toro que esta tratando de modficar";
	
	String REG_ORI_YA_ASIGNADO = "El registro origen se encuentra asignado a otro animal como registro origen";
	
	String ANIMAL_CODIGO_BAJA_EN_1_NO_PUEDE_RECIBIR_EVENTOS = "NOEVREGB";

	
	// El método de control no existe
	String ERROR_GET_METODO_CONTROL = "MECONOEX";
	
	// Ya existe un control animal para la fecha informada para ese animal y ese establecimiento
	String CONTROL_REPETIDO_ANIMAL = "COREPANI";
	
	// Indica que el método de control que se calculará en la lactancia es distinto al informado
	String DISTINTO_MET_CONT_LACT = "DISMETCO";
	
	// Indica que el método de control que se calculará en la lactancia es distinto al informado debido a que
	//el tipo de muestreo es SM y el metodo es A4-C4-A6-C6
	String DISTINTO_MET_CONT_LACT1 = "DISMECOM";
	
	// Advertencia para indicar que no se respetan los días entre controles
	String ERROR_DIAS_ENTRE_CONTROLES = "ERDIASCO";
	
	String TORO_INACTIVO = "SERVTOIN";
	
	String TORO_NO_PUEDE_FORMAR_PARTE_ESTA_BAJA = "SERVTOBA";
	
	String NO_INFORMA_EVENTO_PARA_DAR_BAJA = "NOINFBAJ";
	
	String ELIMINA_ASCENDENCIA = "NOELIMAS";
	
	String CAMBIA_DE_MADRE = "NOCAMMAD";
	
	String CAMBIA_DE_PADRE = "NOCAMPAD";
	
	String CAMBIA_FECHA_NAC_ASOC_REPRODUCCION = "NOCAFNAC";
	
	String ERROR_EVT_CONTROL_POST = "REPEVCOP";

	String ERROR_ABORTO_CORTO_NO_INICIA_LACTANCIA = "ABCONOIL";
	
	//Mensaje de error lanzado en caso de que se quiera informar una reproduccion con retroactividad y que afecte a una lactancia 
	//anterior a la ultima lactancia cerrada
	String FECHA_EVT_ANT_SEGUNDA_LACTANCIA = "EVANT2LA";
	//lanzado en el caso de que se quiere informar una reproduccion retroactiva al evento reproduccion que inicio la unica lactancia
	String FECHA_EVT_ANT_A_INICIO_UNICA_LACTANCIA = "EVANINUL";

	String NO_ES_POSIBLE_SETEO_RETROACTIVO = "NOSETRET";
	
	String NO_RETROACTIVIDAD_CONTROL = "FEANTCO";
	
	//se informa una cria inscribir con sexo desconocido
	String CRIA_INSCRIBIR_SEXO_DESCONOCIDO = "CRINSEDE";
	
	String INFORMO_PARTE_INFORMACION_SENASA = "NORPSENA";
	
	String NO_RETROACTIVIDAD_SECADA = "FEANTSE";
	
	String MODIFICA_BAJA_EVT_CAMBIO_RP = "NMODBARP";
	
	String NO_COINCIDE_PADRE_CON_REP="NOPADREP";
	
	String SERVICIO_CAMPO_SOLAPADO ="SERCASOL";
	
	String NO_ABORTO_LARGO_DIAS_LACTANDO = "NOALLACT";
	
	String NO_ACEPTA_SERVICIO_PARA_DOS_PARTOS = "NOSER2PA";
	
	String NO_INFORMA_NUMERO_DE_ORDENIE = "NOINNUOR";
	
	String NO_EXISTE_ORDENIE_INFORMADO = "NOEXNUOR";
	
	String NUMERO_LACTANCIA_NO_CORRESPONDE_EDAD = "NONRLAED";
	
	String SERVICIO_ESPECIFICADO_PROBLEMAS_IGUAL_ACEPTA = "SVREPPRO";

	String SERVICIO_POSTERIOR_NO_CUMPLE_FECHA = "SVPOSUPF";

	String SERVICIO_ESPECIFICADO_FUERA_PERIODO_IGUAL_ACEPTA = "SVFPERAC";

	String CENTRO_DE_COMPUTO_NO_EXISTE = "NOEXCENT";

	String CENTRO_NO_CORRESPONDE = "NOTAMCEN";

	String TAMBO_DISTINTA_ECLO = "ATAMDIEC";
	
	/*
	 * centro de Computo dada de baja
	 */
	String CENTRO_INACTIVO="CENTBAJA";
	
	String TAMBO_INACTIVO="TAMBINAC";
	
	String NO_SE_PUEDE_DAR_DE_BAJA_EVENTOS="BAJAEVTS";
	
	String INFORMA_MISMO_ANIMAL_EN_MISMO_PARTO = "IGUANPAR";
	
	String ECLO_SIN_SISTEMA = "ECLONOSI";
	
	String MAS_ORDENIES_QUE_CONTROLES = "ORDRELOT";
	
	String EVENTO_CRIA_ASOCIADO = "EVREPASO";
	
	String CRIA_TIENE_CALIFICACION = "CRIACALI";

	String CAMBIO_SEXO_Y_TIENE_EVENTOS = "CASEXEVT";

	String CAMBIO_ESTADO_VIVO_MUERTO_TIENE_EVENTOS = "CAESVMTE";

	String NO_MISMA_CANTIDAD_CRIAS = "DISCACRI";

	String NO_COINCIDE_RP_CRIA = "NOCORPCR";

	String NO_ASIGNACION_MAS_DE_UNA_CRIA_RP = "NACRSIRP";

	String MAS_DE_UNA_CRIA_Y_MODIFICA_SIN_RP = "MACRMORP";

	String NO_BAJA_LACTANCIA = "NOBAJLAC";

	String NO_BAJA_ORDEÑE = "NOBAJORD";

	String ES_OBLIGATORIO_INFORMAR_CRIAS = "OBLICRIA";

	String CRIA_EXISTENTE = "CRIAEXIS";

	String CRIA_EXISTENTE_ESTAB = "CREXESTA";
	
	String EVENTO_NO_CONTROL = "NOEVTCON";

	String NO_BAJA_MODFICACION = "NOBAJMOD";

	String OLBLIGATORIO_INFORMAR_EMBRION = "OBLIEMBR";

	//String SERVICIO_REPETIDO = "SERENFEC";

	String SERVICIO_REPETIDO_FECHA_HORA = "SEREFEHO";

	String NO_BAJA_BAJA_EVENTO = "NOBAJBEV";
	
	String NO_BAJA_BAJA_EVENTO_CONTROL = "NOBAJCON";

	String FECHA_IGUAL_TIPO = "NOEVFETI";
	
	String VALIDACIONES_NUMEROS_DE_RP = "RPNOVAL";

	String NO_BAJA_HBA = "NOBAJHBA";

	String NO_ACEPTA_PADRE_CON_RC = "NOPCONRC";

	String NO_RESPETA_PERIODO_SERVICIO_PARTO = "PESERPAR";
	
	String NO_BAJA_CRIA_EVENTOS_AOSCIADOS = "ANNOBAJ";
String SIN_TAMBO_ASIGNADO_INFORMAR_TRANSFERENCIA = "SINTAMIT";
String FECHA_NACIMIENTO_SUPERIOR_AL_ALTA = "FNACALTA";

String YA_SE_ENCUENTRA_INSCRIPTA = "CRIAINSC";


String MODIFICACION_NUMERO_LACTANCIA_MAL_ANT = "MODNUMMA";
String MODIFICACION_NUMERO_LACTANCIA_MAL_POST = "MODNUMMP";

String MODIFICACION_NUMERO_LACTANCIA_LAC_ANTERIOR = "MODNUANT";

String MODIFICACION_NUMERO_LACTANCIA_LAC_POSTERIOR = "MODNUPOS";
}
