/**
 * Attention: Generated source! Do not modify by hand!
 */
package ar.org.sicel.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import ar.org.sicel.persistence.excepciones.ErrorFatal;
import ar.org.sicel.persistence.util.HibernateFactory;
import ar.org.sicel.util.StringUtils;

/**
 * <p>
 * Factory class. Is able to find and create objects of type ProcMsgs. Hibernate
 * inheritance class Those can be described as follows:
 * </p>
 * 
 * @see ar.org.sicel.persistence.ProcMsg
 */
public abstract class ProcMsgDAO {
	// ---------------- create method --------------------

	/**
	 * Crea el mensaje, seteandole los valores y aplicandole el formato
	 * especificado en el codigo de mensaje.
	 * 
	 * @param session
	 * @param codigo
	 * @param nivelError
	 * @param info
	 * @param valores
	 *            los valores del mensaje
	 * @return
	 * @throws ErrorFatal
	 */
	public static ProcMsg create(String codigo, java.lang.Byte nivelError,
			String[] valores) throws ErrorFatal {
		ProcCodMsg cod = null;
		try {
			cod = ProcCodMsgDAO.findByPrimaryKey(codigo);
		} catch (HibernateException e) {
			throw new ErrorFatal(e);
		}
		ProcMsg object = new ProcMsg();
		object.setNivelError(nivelError);
		try {
			try{
			object.setInformacion(StringUtils.format(cod.getFormato(),
					(Object[]) valores));
			}catch(Exception e){
				if (cod == null)
					System.out.println("cod es null");
				System.out.println(codigo);
				System.out.println(valores);
			}
			
		} catch (java.util.IllegalFormatConversionException e) {
			object.setInformacion(cod.getFormato());
		}
		
		String mensa = cod.getDescripcion() +". " + object.getInformacion();
		 String info = (mensa.length()>=2000)?mensa.substring(0, 1999):mensa;
		object.setInformacion(info );
       object.setCodigoMsg(cod);
		object.setValores(valores);
		return object;
	}

	// ---------------- finder methods ----------------------

	/**
	 * 
	 * Finds ProcMsgs object by its primary key. In Hibernate, this is just a
	 * call to get().
	 * 
	 */
	public static ProcMsg findByPrimaryKey(java.lang.Long id)
			throws org.hibernate.HibernateException {
		Session session = HibernateFactory.getSession();
		ProcMsg object = (ProcMsg) session.get(ProcMsg.class, id);

		return object;
	}
}
