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
    
    public Nodo getIzq(){
        return this.izq;
    }
    public Nodo getDer(){
        return this.izq;
    }
    
    public void setIzq(Nodo izq){
        this.izq = izq;
    }
    
    public void setDer(Nodo der){
        this.der = der;
    }
    
    @Override
    public String toString() {
        return "Partido" + "\nParticipante 1: " + participante1 + "\nParticipante2: " + participante2 + "\n Ganador: " + ganador + "\nResultado: " + resultado + "\nResultado anterior participante 1: " + izq + "\nResultado anterior participante 2: " + der;
    }
}
