package parcial2eda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anitabonita
 */
public class Arbol {

    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }

    /**
     * Metodo para validar que la cantidad de participantes sea la correcta para
     * que el arbol sea simetrico
     *
     * @param participantes Numero de participantes
     * @return True si la cantidad de participantes es correcta, false si no lo
     * es
     */
    public boolean cant_participantes(int participantes) {
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
     *
     * @param participantes
     * @return Cantidad de etapas
     */
    private int cant_etapas(int participantes) {
        int cont = 0;
        while (participantes > 1 && participantes % 2 == 0) {
            participantes = participantes / 2;
            cont++;
        }
        return cont;
    }

    /**
     * Metodo privado para construir el arbol (torneo) y cada nodo (partido) de
     * este
     *
     * @param etapas Cantidad de etapas que va a tener el torneo
     * @return Partido creado
     */
    private Nodo crear_torneo_recursivo(int etapas) {
        if (etapas == 0) {
            return null;
        }
        Nodo partido = new Nodo();

        partido.setIzq(crear_torneo_recursivo(etapas - 1));
        partido.setDer(crear_torneo_recursivo(etapas - 1));

        return partido;
    }

    /**
     * Metodo que inicializa el torneo y los partidos de este
     *
     * @param participantes Cantidad de participantes
     */
    public void crear_torneo(int participantes) {
        int etapas = this.cant_etapas(participantes);
        this.raiz = crear_torneo_recursivo(etapas);
    }

    /*recorre el arbol y junta las hojas que son nodos sin hijos, 
    que representan un partido de la primera ronda. Recorremos de izq a der*/
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

    /*armamos la primera rueda asoganandole jugadores a las hojas*/
    public boolean armarPrimeraRueda(List<Participante> jugadores) {
        if (raiz == null) {
            System.out.println("El torneo no fue creado todavia");
            return false;
        }

        //juntamos las hojas en orden
        List<Nodo> hojas = new ArrayList<>();
        recolectarHojas(raiz, hojas);   //recorre el arbol en preorden

        int n = jugadores.size();
        int mitad = n / 2;

        if (hojas.size() != mitad) {
            System.out.println("El arbol no cincide con la cantidad de jugadores");
            return false;
        }

        //asignamos hoja[i] = jugador[i]  vs  jugador[mitad + i]
        for (int i = 0; i < mitad; i++) {
            hojas.get(i).setParticipante1(jugadores.get(i));
            hojas.get(i).setParticipante2(jugadores.get(mitad + i));
        }

        return true;
    }

    /*muestra los cruces de la primera ronda*/
    public void mostrarPrimeraRonda() {
        if (raiz == null) {
            System.out.println("El torneo no fue creado todavia.");
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
            System.out.println("Todavia no se armo la ronda");
            return;
        }

        System.out.println("          CRUCES");
        for (int i = 0; i < hojas.size(); i++) {
            System.out.printf("  Partido %2d: %s%n", i + 1, hojas.get(i));
        }
        System.out.println();
    }

    public Nodo getRaiz() {
        return raiz;
    }


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

    public void siguienteFase() {
        siguienteFasePrivado(raiz);
    }

    public void cargarResultado(Nodo partido, int ganador) {
        partido.setGanador(ganador == 1 ? partido.getParticipante1() : partido.getParticipante2());
    }

    public void mostrarResultado() {
        mostrarResultadoPrivado(raiz);
    }

    private void mostrarResultadoPrivado(Nodo n) {
        if (n == null) {
            return;
        }

        mostrarResultadoPrivado(n.getIzq());
        mostrarResultadoPrivado(n.getDer());

        if (n.getGanador() != null) {
            System.out.println(n);
        }
    }

    public void mostrarSiguienteFase() {
        mostrarSiguienteFasePrivado(raiz);
    }

    private void mostrarSiguienteFasePrivado(Nodo n) {

        if (n == null) {
            return;
        }

        if (!n.esHoja()
                && n.getParticipante1() != null
                && n.getParticipante2() != null
                && n.getGanador() == null) {

            System.out.println(n);
        }

        mostrarSiguienteFasePrivado(n.getIzq());
        mostrarSiguienteFasePrivado(n.getDer());
    }

    public List<Nodo> obtenerPartidosPendientes() {
        List<Nodo> partidos = new ArrayList<>();
        obtenerPartidosPendientesPrivado(raiz, partidos);
        return partidos;
    }

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

    public Participante obtenerCampeon() {
        if (raiz != null) {
            return raiz.getGanador();
        }
        return null;
    }

}
