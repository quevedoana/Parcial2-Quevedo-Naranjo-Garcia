package parcial2eda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Anitabonita
 */
public class Parcial2Eda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arbol torneo = new Arbol();
        List<Participante> lista_jugadores = new ArrayList<>();
        Nodo partido = new Nodo();

        int cant_jugadores = 0;
        String nombre_jugador;
        int ranking_jugador;
        String nacionalidad_jugador = null;
        boolean torneoCreado = false;
        int opcion;

        do {
            System.out.println("Menu");
            System.out.println("1. Ingresar cantidad de jugadores");
            System.out.println("2. Ingresar jugadores inscriptos");
            System.out.println("3. Armar primera rueda del troneo");
            System.out.println("4. Cargar resultados de las fases");
            System.out.println("5. Mostrar resultados de las fases");
            System.out.println("6. Mostrar campeón");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la cantidad de jugadores para el torneo:");
                    cant_jugadores = sc.nextInt();
                    while (!torneo.cant_participantes(cant_jugadores)) {
                        System.out.println("La cantidad de jugadores ingresada no es valida. Ingrese nuevamente:");
                        cant_jugadores = sc.nextInt();
                    }
                    torneo.crear_torneo(cant_jugadores);
                    torneoCreado = true;
                    System.out.println("Torneo creado para " + cant_jugadores + " jugadores");
                    break;
                case 2:
                    int i = lista_jugadores.size() + 1;
                    if (cant_jugadores == 0) {
                        System.out.println("Tiene que indicar primero la cantidad de jugadores participantes del torneo");
                        break;
                    }
                    while (i <= cant_jugadores) {
                        System.out.println("/// Jugador nro " + i + " ///");
                        System.out.println("Ingrese el nombre completo del jugador: ");
                        nombre_jugador = sc.nextLine();
                        System.out.println("Su nacionalidad: ");
                        nacionalidad_jugador = sc.nextLine();
                        System.out.println("Ahora su puesto en el ranking: ");
                        ranking_jugador = sc.nextInt();
                        sc.nextLine();
                        lista_jugadores.add(new Participante(nombre_jugador, nacionalidad_jugador, ranking_jugador));
                        i++;
                    }

                    System.out.println("Los jugadores han sido inscriptos");
                    break;
                case 3:
                    if (!torneoCreado) {
                        System.out.println("Primero cree el troneo (opcion 1)");
                        break;
                    }
                    if (lista_jugadores.size() < cant_jugadores) {
                        System.out.println("Faltan cargar jugadores: "
                                + lista_jugadores.size() + " de " + cant_jugadores
                                + " ingresados (opcion 2)");
                        break;
                    }
                    Collections.sort(lista_jugadores);  //ordenamos los jugadores por menor a mayor por ranking
                    boolean crearRueda = torneo.armarPrimeraRueda(lista_jugadores);
                    if (crearRueda) {
                        torneo.mostrarPrimeraRonda();
                    }
                    break;
                case 4:

                    List<Nodo> partidos = torneo.obtenerPartidosPendientes();

                    for (int j = 0; j < partidos.size(); j++) {

                        Nodo partidoActual = partidos.get(j);

                        System.out.println("\nPartido " + (j + 1));
                        System.out.println("1 - " + partidoActual.getParticipante1().getNombre_completo());
                        System.out.println("2 - " + partidoActual.getParticipante2().getNombre_completo());

                        int opcGanador;

                        do {
                            System.out.print("Ingrese ganador (1 o 2): ");
                            opcGanador = sc.nextInt();
                        } while (opcGanador != 1 && opcGanador != 2);

                        torneo.cargarResultado(partidoActual, opcGanador);
                    }

                    torneo.siguienteFase();

                    System.out.println("Resultados cargados correctamente.");
                    torneo.siguienteFase();

                    System.out.println("\nSIGUIENTE FASE");
                    torneo.mostrarSiguienteFase();

                    if (partidos.isEmpty()) {
                        System.out.println("El torneo ya finalizo");
                        break;
                    }

                    break;
                case 5:
                    System.out.println("=== RESULTADOS DEL TORNEO ===");
                    torneo.mostrarResultado();

                    break;
                case 6:

                    Participante campeon = torneo.obtenerCampeon();

                    if (campeon == null) {
                        System.out.println("El torneo aun no ha finalizado");
                    } else {
                        System.out.println("\n=== CAMPEON DEL TORNEO ===");
                        System.out.println("Nombre: " + campeon.getNombre_completo());
                        System.out.println("Nacionalidad: " + campeon.getNacionalidad());
                        System.out.println("Ranking: " + campeon.getRanking());
                    }

                    break;
            }
        } while (opcion != 7);
    }

}
