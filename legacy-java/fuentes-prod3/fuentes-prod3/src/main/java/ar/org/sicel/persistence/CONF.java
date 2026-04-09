/*
 * Created on 10/06/2005
 *
 */
package ar.org.sicel.persistence;

/**
 * @author pablo
 *
 */
public interface CONF {

		//el primer valor es el valor del arreglo es el valor por defecto
		
		String MED_MAX_MIN = "MED_MAX_MIN_ACTIVA";

		String MED_MAX_MIN_DESC ="Controlar que las mediciones de produccion esten dentro del rango valido";
		
		String TRANSF_IMPLICITA = "TRANSF_IMPLICITA";

		String TRANSF_IMPLICITA_DESC= "Realizar Transferencias Implicitas";
		
		
		String CREACION_AUTOMATICA_VALORES_AT = "CREA_VALORES_ATR"; 

		String CREACION_AUTOMATICA_VALORES_AT_DESC = "Crear valores permitidos si no existen para el atributo al setearlo";
		
		
		String ESTABLECIMIENTOS_DISTINTOS = "ESTAB_DISTINTOS";

		String ESTABLECIMIENTOS_DISTINTOS_DESC = "Rechazar si se informen eventos para un animal desde otro establecimiento";
		
		
		String PROPIETARIOS_DISTINTOS = "PROP_DISTINTOS";

		String PROPIETARIOS_DISTINTOS_DESC = "Rechazar si se informen eventos para un animal desde un establecimiento de distinto dueño que el animal";
		
		
		String RP_UNICO = "RP_UNICO"; 

		String RP_UNICO_DESC = "Controlar que los RP sean únicos por establecimiento";

        String USAR_FECHA_EVENTO_AL_COMPROBAR_REGLAS = "UsarFechaEventoReglas";

        String USAR_FECHA_EVENTO_AL_COMPROBAR_REGLAS_DESC = "Utilizar la configuracion existente al momento de la fecha de evento";


        String INSCRIBIR_MACHO_ACEPTA = "InscribirMachoAcepta";

        String INSCRIBIR_MACHO_ACEPTA_DESC = "Aceptar que se inscriban crias de sexo masculino";

        String INSCRIBIR_HEMBRA_OBLIGA = "InscribirHembraObliga";

        String INSCRIBIR_HEMBRA_OBLIGA_DESC = "Obligar a inscribir las crias de sexo femenino";
        
        
		String ID_EVT_ECLO_UNICO = "EVT_ECLO_UNICO";

		String ID_EVT_ECLO_UNICO_DESC = "Controlar que los ID de evento sean únicos para cada ECLO";
		
		
		String LOTE_UNICO = "ID_LOTE_UNICO";

		String LOTE_UNICO_DESC = "Controlar que los ID de lote sean únicos para cada ECLO";
		

		String SE_ACEPTA_COMP_RACIAL_EN_ALTA = "COMP_RAC_EN_ALTA";

		String SE_ACEPTA_COMP_RACIAL_EN_ALTA_DESC = "Aceptar que se especifique la composicion racial al realizar un alta";
		
		
		
//		String SE_ACEPTA_RAZA_DECLARADA_EN_ALTA = "RAZA_DCL_EN_ALTA";
		
//		String SE_ACEPTA_RAZA_DECLARADA_EN_ALTA_DESC = "Aceptar que se de una raza declarada al realizar un alta";
		
		
		String CREAR_REGISTRO_SIEMPRE = "CREAR_REG_SIEMP";
		
		String CREAR_REGISTRO_SIEMPRE_DESC = "Crear y asignar siempre un RC al dar de alta un animal aunque este ya posea otro Registro";
		
		

		String EDAD_MINIMA_PARA_SERVICIO_NATURAL = "REGLA_EDAD_SERV_NAT";
		
		String EDAD_MINIMA_PARA_SERVICIO_NATURAL_DESC = "Controlar edad minima para recivir servicios naturales";
		
		
		
		String EDAD_MINIMA_PARA_SERVICIO_ARTIFICIAL = "REGLA_EDAD_SERV_ART";
		
		String EDAD_MINIMA_PARA_SERVICIO_ARTIFICIAL_DESC = "Controlar edad minima para recivir servicios artificiales"; // (inseminacion, transferencias embrionarias)
		
		
		 
		// (la edad minima al controlar depende de la raza del toro y de si el servicio es con o sin monta) 
		String REGLA_EDAD_MINIMA_SERVICIO_TORO = "REGLA_EDAD_SERVICIO_TORO";
		
		String REGLA_EDAD_MINIMA_SERVICIO_TORO_DESC = "Controlar la edad minima de un toro para participar en un Servicio";
		
		
		
		String SERVICIO_MISMO_PROP_EST = "REGLA_MISMO_EST_PROP_SERVICIO";
		
		String SERVICIO_MISMO_PROP_EST_DESC = "Controlar que los animales que intervienen en un servicio de tipo Corral o Campo pertenezcan al mismo propietario o esten en el mismo establecimiento";

		
		
		String REGLA_EDAD_MINIMA_PRODUCIR_EMBRIONES = "REGLA_EDAD_MINIMA_PROD_EMBRIONES";
		
		String REGLA_EDAD_MINIMA_PRODUCIR_EMBRIONES_DESC = "Controlar la edad minima de una hembra para poder haber dado un embrion en una transferencia hembrionaria";
		
		
		
		String ACEPTA_USAR_RAZA_MADRE_EN_PARTO = "ACEPTAR_RAZA_MADRE_EN_PARTO";
		
		String ACEPTA_USAR_RAZA_MADRE_EN_PARTO_DESC = "Aceptar que se especifique usar la raza de la madre para las crias";
		
		
		
		String ACEPTA_PARTO_SIN_SERVICIO = "ACEPTA_PARTO_SIN_SERVICIO";
		
		String ACEPTA_PARTO_SIN_SERVICIO_DESC = "Aceptar que se informen partos sin un servicio válido";
		
		
		String ACEPTA_SERVICIOS_SOLAPADOS = "ACEPTA_SERVICIO_SOLAPADO";
		
		String ACEPTA_SERVICIOS_SOLAPADOS_DESC = "Aceptrar que se informen servicios solapados";

		
		String ACEPTA_FECHA_SERVICIO_EN_PARTO = "ACEPTA_FECHA_SERVICIO_PARTO";
	
		
		String ACEPTA_FECHA_SERVICIO_EN_PARTO_DESC = "Aceptar que se especifique la fecha del servicio en el evento reproducción";
		
				
		String ACEPTA_SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO = "ACEPTA_SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO";
		
		
		String ACEPTA_SERVICIO_ESPECIFICADO_NO_MAS_APROPIADO_DESC = "Aceptar que se encuentre otro servicio con fecha mas cercana al promedio de gestacion que el del servicio especificado en un parto";
		
				
		String OBLIGA_FECHA_SERVICIO_EN_PARTO = "OBLIGA_FECHA_SERVICIO_EN_PARTO";
		
		
		String OBLIGA_FECHA_SERVICIO_EN_PARTO_DESC = "Obligar a que se especifique la fecha de servicio en el evento reproducción";
		
		
		//se acepta que no se especifique la fecha de servicio y que se encuentren mas de 1 servicio posible para ese parto
		String ACEPTA_SERVICIOS_AMBIGUOS_EN_PARTO = "ACEPTA_SERVICIO_AMBIGUO_EN_PARTO";
		
		
		String ACEPTA_SERVICIOS_AMBIGUOS_EN_PARTO_DESC = "Aceptar se encuentre mas de un servicio posible para un parto";
		
		
        String TrFinLactEstabDifProp = "TrFLEstabDProp";
        
        
        String TrFinLactDifPropMismoEstab = "TrFLDPropMEstab";
        
        
        String TrFinLactDifPropDifEstabMismoPropEstab = "TrFLDPropDEstabMPrEst";
        

        String ACEPTA_LARGO_EXISTE_CORTO = "ACEPTA_LARGO_EXISTE_CORTO";

		String CARGA_INICIAL_NO_CHEQUEO_REGLAS = "CARGA_INICIAL_NO_CHEQUEO_REGLAS";
		
		 
		String CARGA_MASIVA_CHEQUEOS_NACIONALES = "CARGA_MASIVA_VALIDA_NACIONALES";
		
		String ACEPTA_SERVICIOS_MISMO_DIA_Y_HORA = "ACEPTA_SERVICIOS_MISMO_DIA_Y_HORA";
		String ACEPTA_MACHO_CON_RC_PARA_SERVICIO = "ACEPTA_MACHO_CON_RC_PARA_SERVICIO";

		String VALIDACIONES_NUMEROS_DE_RP = "VALIDACIONES_NUMEROS_DE_RP";
		
		String ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL = "ACEPTA_ECLO_BUSCAR_CUALQUIER_ANIMAL";

		//String DIAS_MAXIMOS_RETROACTIVADAD_EVENTO_OTRO_TAMBO = "Dias maximos de retroactividad que un evento se informa en otro tambo";
		
/*
		String EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA = "EDAD_MAXIMA_SIN_LAC_PURA";
		String EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA_VALS[] = {String.valueOf(42*30)}; //42 meses
		String EDAD_MAXIMA_MADRE_SIN_LACTANCIA_CAT_PURA_DESC = "Edad maxima para que una hembra sin lactancia pueda ser progenitora de una hembra de categoria PURA";
*/      
}
