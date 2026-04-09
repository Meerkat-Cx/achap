package ar.org.sicel.persistence.util;

import java.lang.reflect.Field;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.Atributo;
import ar.org.sicel.persistence.AtributoDAO;
import ar.org.sicel.persistence.CONF;
import ar.org.sicel.persistence.Configuracion;
import ar.org.sicel.persistence.ConjuntoAtributos;
import ar.org.sicel.persistence.ConjuntoAtributosDAO;
import ar.org.sicel.persistence.Especie;
import ar.org.sicel.persistence.Raza;
import ar.org.sicel.persistence.ValorAdmAtrDAO;

public class CargarAtributos {
	
	/**
	 * Carga los atributos encontrados en Especie, Raza y Configuracion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StandaloneHibernateStrategy.getInstance().openNewSession();
		try {
			IterarSobreVariablesDeClase(CONF.class,Configuracion.class.getSimpleName());
			IterarSobreVariablesDeClase(Raza.class,Raza.class.getSimpleName());
			IterarSobreVariablesDeClase(Especie.class,Especie.class.getSimpleName());
			StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		} catch (Exception e) {
			StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param name
	 * @throws HibernateException
	 */
	private static void IterarSobreVariablesDeClase(Class clase,String nombreConjAt)
	throws HibernateException {
		// ver todas las variables de clase de una clase
		ConjuntoAtributos conjuntoAt = ConjuntoAtributosDAO.findByName(nombreConjAt); 
		
		Field[] fields = clase.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			ProcesarVariable(field,conjuntoAt);
		}
	}
	
	/**
	 * @param field
	 * @throws HibernateException
	 */
	private static void ProcesarVariable(Field field,ConjuntoAtributos conjuntoAt) throws HibernateException {
		String fieldName = field.getName();
		if (fieldName.endsWith("_VALS"))
			return;
		
		Class fieldClass = field.getType();
		Class StringClass = String.class;
		if (fieldClass != StringClass) {
			System.out.println("No se procesa el atributo " + fieldName + " por ser del tipo " + fieldClass);
			return;
		}
		
		Field values = null;
		try {
			values = field.getDeclaringClass().getDeclaredField(fieldName + "_VALS");
		} catch (NoSuchFieldException e) {
			System.out.println("No se procesa el atributo " + fieldName + " por no encontrarse su _VALS correspondiente");
			return;
		}

		Field desc = null;
		try {
			desc = field.getDeclaringClass().getDeclaredField(fieldName + "_DESC");
		} catch (NoSuchFieldException e) {
			System.out.println("Sin descripcion para "  + fieldName);
		}
		
		String fieldValue = null;
		try {
			fieldValue = (String) (field.get(null));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String[] valores = new String[0];
		try {
			valores = (String[]) (values.get(null));
		} catch (IllegalArgumentException e1) {
		} catch (SecurityException e1) {
		} catch (IllegalAccessException e1) {
		}
		
		String descripcion = null;
		if (desc != null)
			try {
				descripcion = (String)desc.get(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		
		System.out.println("Procesando atr:" + fieldValue + " del conjunto " + conjuntoAt.getNombre());
		
		Atributo at = AtributoDAO.findByNombre(fieldValue);
		
		if (at == null) {
			System.out.println("Creando " +  fieldValue);
			at = AtributoDAO.create(conjuntoAt,fieldValue);
			//HibernateFactory.getSession().update(conjunto);
			HibernateFactory.getSession().save(at);
		} else {
			System.out.println("Limpiando " +  fieldValue);
			at.getValoresAdmitidos().clear(); //los valores admitidos los vuelvo a calcular
		}
		for (int i=0; i< valores.length; i++) {
			System.out.println("Agregando valor:" + valores[i]);
			if (i == 0)//esto crea el valor admitido y ademas lo agrega al atributo
				at.setValorPorDefecto(ValorAdmAtrDAO.create(at,valores[i]));
			else
				ValorAdmAtrDAO.create(at,valores[i]);
		}
		at.setDescripcion(descripcion);
		
	}
}




