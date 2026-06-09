package parcial2eda;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Representa el arbol binario completo del torneo Grand Slam.
 * 
 * Se eligio un arbol binario completo porque la estructura de un
 * torneo de eliminacion directa con 2 a la n jugadores se mapea
 * naturalmente a este tipo de arbol:
 * 
 * -Cada nodo representa un partido con exactamente dos participantes
 * -Las hojas son los partidos de primera ronda
 * -La raiz es la final del torneo
 * -La cantidad de niveles equivale a la cantidad de rondas (log₂ de jugadores)
 * 
 * @author Anitabonita
 */
public class Arbol {
    
    // Nodo raíz del arbol, representa la final del torneo
    private Nodo raiz;

    //Construye un arbol vacio sin torneo inicializado
    public Arbol() {
        raiz = null;
    }

    /**
     * Metodo para validar que la cantidad de participantes sea la correcta para
     * que el arbol sea simetrico
     * @param participantes Numero de participantes a validar
     * @return True si la cantidad de participantes es correcta, false si no lo
     * es
     */
    public boolean cantParticipantes(int participantes) {
        if (participantes <= 3) {
            return false;
        }
        while (participantes > 1 && participantes % 2 == 0) {
            participantes = participantes / 2;
        }
        boolean res = (participantes == 1) ? true : false;
        return res;
    }

    /**
     * Metodo para calcular la cantidad de etapas del torneo en base a la
     * cantidad de participantes
     * Por ejemplo: 8 jugadores -> 3 etapas (octavos, semis, final)
     * @param participantes cantidad de participantes (potencia de 2)
     * @return Cantidad de rondas del torneo
     */
    private int cantEtapas(int participantes) {
        int cont = 0;
        while (participantes > 1 && participantes % 2 == 0) {
            participantes = participantes / 2;
            cont++;
        }
        return cont;
    }

     /**
     * Metodo que construye el arbol del torneo de forma recursiva, de arriba hacia abajo.
     * Crea un nodo por cada partido posible del torneo. Cuando etapas
     * llega a 0, retorna null, lo que hace que los nodos del ultimo
     * nivel queden como hojas (sin hijos), representando la primera ronda
     * @param etapas cantidad de niveles restantes por construir
     * @return Nodo raiz del subarbol construido, o null si etapas es 0
     */
    private Nodo crearTorneoRecursivo(int etapas) {
        if (etapas == 0) {
            return null;
        }
        Nodo partido = new Nodo();

        partido.setIzq(crearTorneoRecursivo(etapas - 1));
        partido.setDer(crearTorneoRecursivo(etapas - 1));

        return partido;
    }

    /**
     * Metodo que inicializa el arbol del torneo con todos sus partidos vacios
     * Calcula la cantidad de etapas a partir de los participantes
     * y delega la construcción a crearTorneoRecursivo(int)
     * El arbol queda listo pero sin jugadores asignados.
     * @param participantes antidad de participantes (potencia de 2)
     */
    public void crearTorneo(int participantes) {
        int etapas = this.cantEtapas(participantes);
        this.raiz = crearTorneoRecursivo(etapas);
    }

    /**
     * Metodo que recorre el arbol en preorden y recolecta todas las hojas
     * en orden de izquierda a derecha
     * Las hojas son los nodos sin hijos, es decir, los partidos de primera ronda
     * Al recorrer siempre primero izquierda y luego derecha, las hojas
     * se obtienen en el orden correcto para asignarles los jugadores
     * segun el algoritmo de cruces por ranking.
     * @param nodo  nodo actual de la recursion
     * @param hojas lista donde se acumulan las hojas encontradas
     */
    private void recolectarHojas(Nodo nodo, List<Nodo> hojas) {
        if (nodo == null) {
            return;
        }

        if (nodo.esHoja()) {
            hojas.add(nodo);
        } else {
            recolectarHojas(nodo.getIzq(), hojas);
            recolectarHojas(nodo.getDer(), hojas);
        }
    }

    /**
     * Metodo que arma la primera rueda del torneo asignando jugadores a las hojas del arbol
     * El algoritmo de cruces sigue la logica: el mejor (1ro en el ranking)
     * enfrenta al jugador de la mitad del ranking, el segundo al de la mitad+1,
     * y asi sucesivamente. Esto garantiza que los mejores jugadores no se
     * eliminen entre si en las primeras rondas.
     * Precondicion: la lista debe estar ordenada por ranking ascendente
     * antes de llamar a este metodo.
     * @param jugadores lista de participantes ordenada por ranking
     * @return true si la asignacion fue exitosa, false si hubo error
     */
    public boolean armarPrimeraRueda(List<Participante> jugadores) {
        if (raiz == null) {
            JOptionPane.showMessageDialog(null,
                    "El torneo no fue creado todavía");
            return false;
        }

        List<Nodo> hojas = new ArrayList<>();
        recolectarHojas(raiz, hojas);

        int n = jugadores.size();
        int mitad = n / 2;

        if (hojas.size() != mitad) {
            JOptionPane.showMessageDialog(null,
                    "El árbol no coincide con la cantidad de jugadores");
            return false;
        }

        for (int i = 0; i < mitad; i++) {
            hojas.get(i).setParticipante1(jugadores.get(i));
            hojas.get(i).setParticipante2(jugadores.get(mitad + i));
        }

        return true;
    }

    /**
     * Muestra los cruces de la primera ronda
     * Recorre las hojas del arbol y muestra cada partido por separado.
     * Si la primera ronda no fue armada, muestra un mensaje de advertencia
     */
    public void mostrarPrimeraRonda() {

        if (raiz == null) {
            JOptionPane.showMessageDialog(null,
                    "El torneo no fue creado todavía.");
            return;
        }

        List<Nodo> hojas = new ArrayList<>();
        recolectarHojas(raiz, hojas);

        boolean hayPartidos = false;

        for (Nodo h : hojas) {
            if (h.estaCompleto()) {
                hayPartidos = true;
                break;
            }
        }

        if (!hayPartidos) {
            JOptionPane.showMessageDialog(null,
                    "Todavía no se armó la ronda");
            return;
        }

        for (int i = 0; i < hojas.size(); i++) {
            JOptionPane.showMessageDialog(
                    null,
                    "Partido " + (i + 1) + "\n" + hojas.get(i)
            );
        }
    }
    
    /**
     * Metodo que retorna el nodo raiz del arbol (la final del torneo)
     * Necesario para que metodos externos puedan recorrer el arbol
     * desde la cima hacia las hojas.
     * @return nodo raiz del arbol, o null si el torneo no fue creado
     */
    public Nodo getRaiz() {
        return raiz;
    }
    
    /**
     * Metodo privado que sube los ganadores al partido de la siguiente fase.
     * Recorre el arbol en postorden (izquierda, derecha, raiz),
     * lo que garantiza que los hijos sean procesados antes que el padre.
     * Cuando ambos hijos tienen ganador, los asigna como participantes
     * del nodo padre, armando asi el partido de la siguiente ronda.
     * @param n Nodo actual de la recursion
     */
    private void siguienteFasePrivado(Nodo n) {
        if (n == null) {
            return;
        }
        siguienteFasePrivado(n.getIzq());
        siguienteFasePrivado(n.getDer());

        if (!n.esHoja()) {
            Nodo izq = n.getIzq();
            Nodo der = n.getDer();

            if (izq.getGanador() != null && der.getGanador() != null) {
                n.setParticipante1(izq.getGanador());
                n.setParticipante2(der.getGanador());
            }
        }

    }
    
    /**
     * Metodo que actualiza el arbol subiendo los ganadores a la siguiente fase
     * Debe llamarse una unica vez después de cargar todos los resultados
     * de la fase actual
     */
    public void siguienteFase() {
        siguienteFasePrivado(raiz);
    }

    /**
     * Metodo que registra el ganador de un partido especifico.
     * @param partido  el nodo del partido al que se le carga el resultado
     * @param ganador  1 si gano el participante 1, 2 si gano el participante 2
     */
    public void cargarResultado(Nodo partido, int ganador) {
        partido.setGanador(ganador == 1 ? partido.getParticipante1() : partido.getParticipante2());
    }

    /**
     * Metodo que muestra todos los resultados cargados en el torneo hasta el momento
     * Recorre el arbol en postorden y muestra cada partido que ya tiene ganador
     * */
    
    public void mostrarResultado() {
        mostrarResultadoPrivado(raiz);
    }

    /**
     * Metodo privado recursivo para mostrar los resultados en postorden
     * @param n Nodo actual de la recursion
     */
    private void mostrarResultadoPrivado(Nodo n) {

        if (n == null) {
            return;
        }

        mostrarResultadoPrivado(n.getIzq());
        mostrarResultadoPrivado(n.getDer());

        if (n.getGanador() != null) {
            JOptionPane.showMessageDialog(null, n);
        }
    }

    /**
     * Metodo que muestra los partidos de la siguiente fase pendiente de jugarse
     * Muestra los nodos que tienen ambos participantes asignados pero
     * aun no tienen ganador, es decir, los partidos listos para jugarse
     */
    public void mostrarSiguienteFase() {
        mostrarSiguienteFasePrivado(raiz);
    }

    /**
     * Metodo privado recursivo para mostrar los partidos de la siguiente fase
     * @param n Nodo actual de la recursion
     */
    private void mostrarSiguienteFasePrivado(Nodo n) {

        if (n == null) {
            return;
        }

        if (!n.esHoja()
                && n.getParticipante1() != null
                && n.getParticipante2() != null
                && n.getGanador() == null) {

            JOptionPane.showMessageDialog(null, n);
        }

        mostrarSiguienteFasePrivado(n.getIzq());
        mostrarSiguienteFasePrivado(n.getDer());
    }

    /**
     * Metodo que retorna la lista de partidos pendientes de la fase actual
     * Un partido es "pendiente" si tiene ambos participantes asignados
     * pero aun no tiene ganador. Se usa en el menu para mostrar al usuario
     * solo los partidos que debe resolver en la fase actual
     * @return Lista de nodos con partidos pendientes de resultado
     */
    public List<Nodo> obtenerPartidosPendientes() {
        List<Nodo> partidos = new ArrayList<>();
        obtenerPartidosPendientesPrivado(raiz, partidos);
        return partidos;
    }

    /**
     * Metodo privado recursivo que recolecta los partidos pendientes en postorden
     * @param n       Nodo actual de la recursion
     * @param partidos Lista donde se acumulan los partidos pendientes
     */
    private void obtenerPartidosPendientesPrivado(Nodo n, List<Nodo> partidos) {

        if (n == null) {
            return;
        }

        obtenerPartidosPendientesPrivado(n.getIzq(), partidos);
        obtenerPartidosPendientesPrivado(n.getDer(), partidos);

        if (n.getParticipante1() != null
                && n.getParticipante2() != null
                && n.getGanador() == null) {

            partidos.add(n);
        }
    }

    /**
     * Me todo que retorna el campeon del torneo.
     * El campeon es el ganador del nodo raiz, que representa la final
     * Retorna null si el torneo todavía no finalizo.
     * @return El participante campeon, o null si el torneo no termino
     */
    public Participante obtenerCampeon() {
        if (raiz != null) {
            return raiz.getGanador();
        }
        return null;
    }

}
