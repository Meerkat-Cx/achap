/*
 * Created on 09-jun-2005
 */
package ar.org.sicel.persistence.util;

import java.lang.reflect.Field;

import org.hibernate.HibernateException;

import ar.org.sicel.persistence.MENSAJES;
import ar.org.sicel.persistence.ProcCodMsg;
import ar.org.sicel.persistence.ProcCodMsgDAO;

/**
 * Controla que los mensages declarados en la clase de mensages esten en la BD y
 * si no están lso inserta a traves de hibernate. Nótese que el atributo Formato
 * es l amisma variable con sufijo _fmt
 *
 * @author ala
 */
public class ControlarEInsertarMensages {

    public static void main(String[] args) throws HibernateException {
        StandaloneHibernateStrategy.getInstance().openNewSession();
        IterarSobreVariablesDeClase(MENSAJES.class);
        StandaloneHibernateStrategy.getInstance().commitCurrentSession();
      //  StandaloneHibernateStrategy.getInstance().rollbackCurrentSession();
    }

    /**
     * @param name
     * @throws HibernateException
     */
    private static void IterarSobreVariablesDeClase(Class clase)
            throws HibernateException {
        // ver todas las variables de clase de una clase
        Field[] fields = clase.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ProcesarVariable(field);
        }
    }

    /**
     * @param field
     * @throws HibernateException
     */
private static void ProcesarVariable(Field field) throws HibernateException {
//        int modifiers = field.getModifiers();
        // if ( ! modifiers || Modifier.STATIC ) return;
        String fieldName = field.getName();
        if (fieldName.endsWith("_FMT"))
            return;
        if (fieldName.endsWith("_DESC"))
            return;
        Class fieldClass = field.getType();
        Class StringClass = String.class;
        if (fieldClass != StringClass)
            return;
        String fieldValue = null;
        try {
            fieldValue = (String) (field.get(null));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String formato = "*NO HALLADO*";
        try {
            formato = (String) (field.getDeclaringClass().getField(
                    field.getName() + "_FMT").get(null));
        } catch (IllegalArgumentException e1) {
        } catch (SecurityException e1) {
        } catch (IllegalAccessException e1) {
        } catch (NoSuchFieldException e1) {
        }
        String descripcion = "*Sin descripcion*";
        try {
            descripcion = (String) (field.getDeclaringClass().getField(
                    field.getName() + "_DESC").get(null));
        } catch (IllegalArgumentException e1) {
        } catch (SecurityException e1) {
        } catch (IllegalAccessException e1) {
        } catch (NoSuchFieldException e1) {
        }
        String comentario = "*Sin comentario*";
        try {
            comentario = (String) (field.getDeclaringClass().getField(
                    field.getName() + "_COM").get(null));
        } catch (IllegalArgumentException e1) {
        } catch (SecurityException e1) {
        } catch (IllegalAccessException e1) {
        } catch (NoSuchFieldException e1) {
        }
        System.out.println(fieldValue + " = " + fieldName + " , FMT: " + formato+ " , DESC: " + descripcion);
        // ver si el mensage con el valor de clave existe

        //ProcCodMsg procCodMsg = ProcCodMsgDAO.findByPrimaryKey(fieldName);
        ProcCodMsg procCodMsg = ProcCodMsgDAO.findByPrimaryKey(fieldValue);
        if (procCodMsg==null)
        {
            procCodMsg = ProcCodMsgDAO.create(fieldValue,descripcion,comentario,formato);
            HibernateFactory.getSession().save(procCodMsg); // si es nuevo hay que asociarlo con la session para que hibernate lo guarde
        }
        else{
            procCodMsg.setFormato(formato); 
            procCodMsg.setDescripcion(descripcion);
        }
        
    }
}
