package parcial2eda;

/**
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

    /*public Nodo(Participante p1, Participante p2) { Comentado, no se si luego se usaria
        participante1 = p1;
        participante2 = p2;
        ganador = null;
        resultado = null;
        izq = null;
        der = null;
    }*/
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

    /*si el nodo no tiene hijos es una hoja*/
    public boolean esHoja() {
        return izq == null && der == null;
    }

    /*la hoja ya tiene los dos jugadores asigados*/
    public boolean estaCompleto() {
        return participante1 != null && participante2 != null;
    }

    @Override
    public String toString() {
        String p1 = (participante1 != null)
                ? participante1.getNombre_completo() + " (rank " + participante1.getRanking() + ")"
                : "";
        String p2 = (participante2 != null)
                ? participante2.getNombre_completo() + " (rank " + participante2.getRanking() + ")"
                : "";
        String g = (ganador != null) ? "  =  Ganador: " + ganador.getNombre_completo() : "";
        return p1 + "  vs  " + p2 + g;
    }
}
