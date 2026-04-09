package ar.org.sicel.persistence.util;


public class CargarAtributosEdadesServicio {
	
	public static void main(String args[]) throws Exception {
		//No lo borro por las dudas, pero ahora todo esto quedo centralizado
		//en ar.org.sicel.util.CargarAtributos , que los busca en Raza,Especie y CONF y
		//los va metiendo o actualizando en la base.
		
		/*
		StandaloneHibernateStrategy.getInstance().openNewSession();
		ConjuntoAtributos ca = ConjuntoAtributosDAO.findByName("Raza");
		Atributo hembraEdadMinimaServArt = AtributoDAO.create(ca,Raza.EDAD_MINIMA_SERVICIO_ARTIFICIAL);
		Atributo hembraEdadMinimaServNat = AtributoDAO.create(ca,Raza.EDAD_MINIMA_SERVICIO_NATURAL);
		Atributo hembraEdadMinimaProdEmbriones = AtributoDAO.create(ca,Raza.EDAD_MINIMA_PRODUCIR_EMBRIONES);
		Atributo toroEdadMinimaMonta = AtributoDAO.create(ca,Raza.EDAD_MINIMA_MONTA);
		Atributo toroEdadMinimaProdSemen = AtributoDAO.create(ca,Raza.EDAD_MINIMA_PRODUCIR_SEMEN);
		
		
		HibernateFactory.getSession().save(hembraEdadMinimaServArt);
		HibernateFactory.getSession().save(hembraEdadMinimaServNat);
		HibernateFactory.getSession().save(hembraEdadMinimaProdEmbriones);
		HibernateFactory.getSession().save(toroEdadMinimaMonta);
		HibernateFactory.getSession().save(toroEdadMinimaProdSemen);
		
		ValorAdmAtr hva1 = ValorAdmAtrDAO.create(hembraEdadMinimaServArt,"1095");
		ValorAdmAtr hva2 = ValorAdmAtrDAO.create(hembraEdadMinimaServArt,"2190");
		ValorAdmAtr hva3 = ValorAdmAtrDAO.create(hembraEdadMinimaServNat,"2190");
		ValorAdmAtr hva4 = ValorAdmAtrDAO.create(hembraEdadMinimaServNat,"4380");
		ValorAdmAtr hva5 = ValorAdmAtrDAO.create(hembraEdadMinimaProdEmbriones,"2190");
		ValorAdmAtr hva6 = ValorAdmAtrDAO.create(hembraEdadMinimaProdEmbriones,"4380");
		
		ValorAdmAtr tva1 = ValorAdmAtrDAO.create(toroEdadMinimaProdSemen,"1095");
		ValorAdmAtr tva2 = ValorAdmAtrDAO.create(toroEdadMinimaProdSemen,"2190");
		ValorAdmAtr tva3 = ValorAdmAtrDAO.create(toroEdadMinimaMonta,"2190");
		ValorAdmAtr tva4 = ValorAdmAtrDAO.create(toroEdadMinimaMonta,"4380");
		
		
		hembraEdadMinimaServArt.setValorPorDefecto(hva1);
		hembraEdadMinimaServNat.setValorPorDefecto(hva3);
		hembraEdadMinimaProdEmbriones.setValorPorDefecto(hva5);
		
		toroEdadMinimaProdSemen.setValorPorDefecto(tva1);
		toroEdadMinimaMonta.setValorPorDefecto(tva3);
		
		Raza holando = RazaDAO.findByPrimaryKey("HOLA");
		holando.getAtrVariables().setValor(hembraEdadMinimaServArt,hva1);
		holando.getAtrVariables().setValor(hembraEdadMinimaServNat,hva3);
		holando.getAtrVariables().setValor(hembraEdadMinimaProdEmbriones,hva5);
		holando.getAtrVariables().setValor(toroEdadMinimaProdSemen,tva1);
		holando.getAtrVariables().setValor(toroEdadMinimaMonta,tva3);
		
		
		Raza jy = RazaDAO.findByPrimaryKey("JY");
		jy.getAtrVariables().setValor(hembraEdadMinimaServArt,hva2);
		jy.getAtrVariables().setValor(hembraEdadMinimaServNat,hva4);
		jy.getAtrVariables().setValor(hembraEdadMinimaProdEmbriones,hva6);
		jy.getAtrVariables().setValor(toroEdadMinimaProdSemen,tva2);
		jy.getAtrVariables().setValor(toroEdadMinimaMonta,tva4);
		
		
		StandaloneHibernateStrategy.getInstance().commitCurrentSession();
		*/
	}
}
