package ar.org.sicel.persistence;

import java.io.Serializable;


/**
 */
public class CatPuntajeValor implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2755704922420199058L;
	private String categoriaPuntaje;
    private Integer valor;

    /**
     *
     * @hibernate.property
     *     column="cat_puntaje"
     * @hibernate.column
     *     name="cat_puntaje"
     *     not-null="true"
     *
     */
    public String getCategoriaPuntaje() {
        return categoriaPuntaje;
    }

    public void setCategoriaPuntaje(String categoriaPuntaje) {
        this.categoriaPuntaje = categoriaPuntaje;
    }

    /**
     *
     * @hibernate.property
     *     column="valor"
     * @hibernate.column
     *     name="valor"
     *     not-null="true"
     *
     */
    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    // ********************** Common Methods ********************** //
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CatPuntajeValor)) {
            return false;
        }

        final CatPuntajeValor puntajeValor = (CatPuntajeValor) o;

        if (!categoriaPuntaje.equals(puntajeValor.categoriaPuntaje)) {
            return false;
        }

        if (!valor.equals(puntajeValor.valor)) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = valor.hashCode();
        result = (29 * result) + categoriaPuntaje.hashCode();

        return result;
    }

    public String toString() {
        return "Value: '" + getCategoriaPuntaje() + "', " + "Currency: '" +
        getValor() + "'";
    }

    public int compareTo(Object o) {
        if (o instanceof CatPuntajeValor) {
            // TODO: This would actually require some currency conversion magic
            return this.getValor().compareTo(((CatPuntajeValor) o).getValor());
        }

        return 0;
    }
}
