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

    @Override
    public String toString() {
        return "Participante: " + "\nNombre: " + nombre_completo + "\nNacionalidad: " + nacionalidad + "\nRanking: " + ranking;
    }
}
