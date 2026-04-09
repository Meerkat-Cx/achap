package ar.org.sicel.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import ar.org.sicel.persistence.excepciones.ExcepcionIntegridad;


/**
 *
 */
public class ComposicionRacial {
    // --------------- attributes ---------------------
    private ar.org.sicel.persistence.Animal animal;
    private ar.org.sicel.persistence.Raza razaDeclarada;
    private java.util.Map razas;
    //se cambio la forma de calcular la raza
    //public static final double PUREZA_RACIAL = 15.0/16.0;
    public static final double PUREZA_RACIAL = 0.875;

    /**
     *
     * @hibernate.parent
     *
     */
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     *
     * @hibernate.many-to-one
     *     column="razaDeclara"
     *     not-null="false"
     *     lazy="true"
     *     outer-join="auto"
     *     foreign-key="FK_Anim_RazaDecla"
     *
     */
    public ar.org.sicel.persistence.Raza getRazaDeclarada() {
        return this.razaDeclarada;
    }

    public void setRazaDeclarada(
        ar.org.sicel.persistence.Raza razaDeclarada) {
        this.razaDeclarada = razaDeclarada;
    }

    /**
     *
     * @hibernate.map
     *     table="An_Comp_Racial"
     *     lazy="true"
     *     cascade="none"
     * @hibernate.collection-key
     *     column="animal"
     *     foreign-key="FK_Cora_Anim"
     * @hibernate.index-many-to-many
     *     column="raza"
     *     class="ar.org.sicel.persistence.Raza"
     *     foreign-key="FK_Cora_Raza"
     * @hibernate.collection-element
     *     column="valor"
     *     type="float"
     *     not-null="true"
     *
     */
    protected java.util.Map getRazas() {
        return this.razas;
    }

    private void setRazas(java.util.Map razas) {
        this.razas = razas;
    }

    public String toString()
    {
    	
    	return this.razas.toString() + " -> " + getRazaCalculada().getId() + " ; " +  getRazaDeclarada().getId();
    }
    
    
    // ---------------- business methods  ----------------------

    public Map getInmutableRazas() {
    	return razas;
    	//return MapUtils.unmodifiableMap(razas);
    }

    @SuppressWarnings("unchecked")
	private static ComposicionRacial mitad(Especie especie, ComposicionRacial comp) {
    	ComposicionRacial resultado = null;
    	if (comp == null) {
    		resultado = nuevaInstancia(especie.getDesconocida());
    		resultado.razas.put(especie.getDesconocida(),new Float(0.5));
    		
    	} else {
    		resultado = nuevaInstancia(comp.getRazaDeclarada());
    		Iterator razasComp = comp.getRazas().entrySet().iterator();
    		while (razasComp.hasNext()) {
    			Map.Entry entry = (Map.Entry )razasComp.next();
    			Float valor = (Float) entry.getValue();
    			Float nuevoValor = valor /2;
    			resultado.razas.put(entry.getKey(),nuevoValor);
    		}
    	}
    	return resultado;
    }
    
    public void recalcular(Especie especie, ComposicionRacial padreGen, ComposicionRacial madreGen) {
    	 //cuando se da de alta un animal, se le setean los padres y se termina invocando este metodo,
    	// pero no hay ninguna composicion racial "previa", por eso el primer chequeo
    	//if (razas == null) 
    		razas = new HashMap();
    	//else
    		//razas.clear();
    	
    	ComposicionRacial mitadMadre = mitad(especie,madreGen);
    	ComposicionRacial mitadPadre = mitad(especie,padreGen);
    	/**
    	 * mitadPadre.add(mitadMadre) 
    	 */
    	add(mitadPadre);
    	add(mitadMadre);
    }
    
    private void add(ComposicionRacial aAgregar) {
    	Iterator razasAAgregar = aAgregar.getRazas().entrySet().iterator();
		while (razasAAgregar.hasNext()) {
			Map.Entry entry = (Map.Entry) razasAAgregar.next();
			add((Raza)entry.getKey(),(Float)entry.getValue());
		}
    }
    
    @SuppressWarnings({"unchecked","unchecked"})
	private void add(Raza raza, Float valorAAgregar) {
    	if (razas.containsKey(raza)) {
			Float valorAnterior = (Float)razas.get(raza);
			razas.put(raza,valorAnterior + valorAAgregar);
			} 
    	else {
    		razas.put(raza,valorAAgregar);
    	}
    }
    
    public static ComposicionRacial nuevaInstancia(Raza razaDeclarada) {
    	ComposicionRacial nueva = new ComposicionRacial();
    	nueva.setRazas(new HashMap());
    	nueva.setRazaDeclarada(razaDeclarada);
    	return  nueva;
    }
    
    public void  setearComposicionRacial(Map cr) throws ExcepcionIntegridad {
    	checkNoParents();
    	checkSumaPorcentajes(cr);
    	checkRazasMismaEspecie(cr);
    	this.setRazas(cr);
    	if (animal != null) //para los test de comp raciales sueltas
    		animal.propagarCambioCompRacial();
    }
    
    protected void checkNoParents() throws ExcepcionIntegridad {
    	if (animal == null) //para los test de compraciales sueltas
    		return;
    	
    	if (this.animal.getPadre() != null || animal.getMadreGenetica() != null) {
    		String regPadre = null;
    		String regMadre = null;
    		if (animal.getPadre() != null)
    			regPadre = animal.getPadre().getRegistroOrigen();
    		if (animal.getMadreGenetica() != null)
    			regMadre = animal.getMadreGenetica().getRegistroOrigen();    			
    		    			
    		throw new ExcepcionIntegridad(MENSAJES.NO_COMPRACIAL_SI_PADRES,new String[]{regPadre,regMadre});
    	}
    }
    
    
    protected void checkSumaPorcentajes(Map comp) throws ExcepcionIntegridad{
    	float suma = 0;
    	Iterator it = comp.values().iterator();
    	while (it.hasNext()) {
    		Float p = (Float) it.next();
    		suma += p;
    	}
    	if (suma != 1)
    		throw new ExcepcionIntegridad(MENSAJES.COMP_RACIAL_INVALIDA, new String[0]);
    }
    
    protected void checkRazasMismaEspecie(Map comp) throws ExcepcionIntegridad {
    	Especie esp = null;
    	Iterator it = comp.keySet().iterator();
    	while (it.hasNext()) {
    		Raza raza = (Raza) it.next();
    		if (esp == null)
    			esp = raza.getEspecie();
    		else
    			if (esp != raza.getEspecie())
    				throw new ExcepcionIntegridad(MENSAJES.COMP_RACIAL_INVALIDA, new String[0]); 
    	}
    }

    
    /**
     *  si es una sola es esa
     *	si tiene mas de 1 es cruza, salvo
     * que una tenga >= 15/16 (salvo que sea Desconocida, en cuyo caso es cruza)
     * @return
     */
    public Raza getRazaCalculada() {
    	if (razas.size() == 1)
    		return (Raza)razas.keySet().iterator().next(); //si es una sola,es esa (es 1 a 100%)
    	else
    	{
    		Iterator it = razas.entrySet().iterator();
    		while (it.hasNext()) {
    			Map.Entry entry = (Map.Entry)it.next();
    			Raza raza = (Raza)entry.getKey();
    			float valor = (Float)entry.getValue();
    			if ((!raza.getEsDesconocido()) && (valor >= PUREZA_RACIAL))
    				return raza;
    		}
    		return getEspecie().getCruza();    		 		    		
    	}
    }

    public Especie getEspecie() {
    	Raza raza =  (Raza)razas.keySet().iterator().next();
    	return raza.getEspecie();
    }
    
    public Collection getAsCollection() {
    	return razas.entrySet();
    }
    private boolean razasIguales(ComposicionRacial com){
    	Iterator it = razas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			Raza raza = (Raza)entry.getKey();
			float valor = (Float)entry.getValue();
			if(!(com.getRazas().containsKey(raza))&&(com.getRazas().get(raza).equals(valor)))
				return false;
		}
    	return true;
    }
    public boolean equals(Object ani){
    	if(!(ani instanceof ComposicionRacial))
			return false;
		ComposicionRacial co = (ComposicionRacial)ani;
		if(this.getRazaDeclarada()!= null && this.getRazaDeclarada().equals(co.getRazaDeclarada()) && (razasIguales(co)))
			return true;
		return false;
    }
}
