package parcial2eda;

/**
 *
 * @author Anitabonita
 */
public class Participante implements Comparable<Participante>{
    
    private String nombreCompleto;
    private String nacionalidad;
    private int ranking;
    
    public Participante(String nombre, String nacion, int rank) {
        nombreCompleto = nombre;
        nacionalidad = nacion;
        ranking = rank;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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
        return "Participante: " + "\nNombre: " + nombreCompleto + "\nNacionalidad: " + nacionalidad + "\nRanking: " + ranking;
    }
    public int compareTo(Participante otro) {
        return Integer.compare(this.ranking, otro.ranking);
    }
}
