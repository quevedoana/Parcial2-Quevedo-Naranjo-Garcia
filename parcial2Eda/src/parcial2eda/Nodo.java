package parcial2eda;

/**
 *Representa un partido dentro del arbol binario del torneo.
 * 
 * Cada nodo almacena a los dos participantes que juegan el partido,
 * al ganador que avanza a la siguiente fase y el resultado del encuentro.
 * Los punteros izq y der apuntan a los partidos anteriores
 * de donde provienen cada uno de los dos jugadores.
 * 
 * Los nodos hoja (sin hijos) representan los partidos de primera ronda,
 * donde los jugadores provienen directamente de la inscripcion y no de
 * haber ganado una ronda previa.
 *
 * @author Anitabonita
 */
public class Nodo {

    private Participante participante1;
    private Participante participante2;
    private Participante ganador; // el ganador que sube de ronda
    private String resultado; // el resultado que luego el usuario los setea en el menu
    private Nodo izq; // apunta a la ronda de donde salio el p1
    private Nodo der; // apunta a la ronda de donde salio el p2
    // nodo izq y der apuntan a null si son hojas, seria el comienzo del torneo

 
    public Nodo() {
        participante1 = null;
        participante2 = null;
        ganador = null;
        resultado = null;
        izq = null;
        der = null;
    }

    public Nodo getIzq() {
        return this.izq;
    }

    public Nodo getDer() {
        return this.der;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public Participante getParticipante1() {
        return participante1;
    }

    public Participante getParticipante2() {
        return participante2;
    }

    public Participante getGanador() {
        return ganador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setParticipante1(Participante p) {
        participante1 = p;
    }

    public void setParticipante2(Participante p) {
        participante2 = p;
    }

    public void setGanador(Participante g) {
        ganador = g;
    }

    public void setResultado(String r) {
        resultado = r;
    }

    /**
     * Determina si este nodo es una hoja del arbol
     * Un nodo hoja no tiene hijos (izq y der son null),
     * lo que indica que representa un partido de primera ronda,
     * donde los jugadores vienen de la inscripcion directamente
     * @return true si el nodo no tiene hijos, false en caso contrario
     */
    public boolean esHoja() {
        return izq == null && der == null;
    }

    /**
     * Determina si el partido ya tiene los dos jugadores asignados
     * Se usa para verificar que la primera ronda fue armada correctamente
     * antes de mostrarla o cargar resultados
     * @return true si ambos participantes estan asignados
     */
    public boolean estaCompleto() {
        return participante1 != null && participante2 != null;
    }

    @Override
    public String toString() {
        String p1 = (participante1 != null)
                ? participante1.getNombreCompleto() + " (rank " + participante1.getRanking() + ")"
                : "";
        String p2 = (participante2 != null)
                ? participante2.getNombreCompleto() + " (rank " + participante2.getRanking() + ")"
                : "";
        String g = (ganador != null) ? "  =  Ganador: " + ganador.getNombreCompleto() : "";
        return p1 + "  vs  " + p2 + g;
    }
}
