package parcial2eda;

/**
 *
 * @author Anitabonita
 */
public class Participante {
    
    private String nombre_completo;
    private String nacionalidad;
    private int ranking;
    
    public Participante(String nombre, String nacion, int rank) {
        nombre_completo = nombre;
        nacionalidad = nacion;
        ranking = rank;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    @Override
    public String toString() {
        return "Participante: " + "\nNombre: " + nombre_completo + "\nNacionalidad: " + nacionalidad + "\nRanking: " + ranking;
    }
}
