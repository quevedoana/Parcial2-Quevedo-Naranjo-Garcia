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
        List<Participante> listaJugadores = new ArrayList<>();

        int cantJugadores = 0;
        String nombreJugador;
        String nacionalidadJugador = null;
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
            System.out.println("7. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la cantidad de jugadores para el torneo:");
                    cantJugadores = sc.nextInt();
                    while (!torneo.cantParticipantes(cantJugadores)) {
                        System.out.println("La cantidad de jugadores ingresada no es valida. Ingrese nuevamente:");
                        cantJugadores = sc.nextInt();
                    }
                    torneo.crearTorneo(cantJugadores);
                    torneoCreado = true;
                    System.out.println("Torneo creado para " + cantJugadores + " jugadores");
                    break;
                case 2:
                    int i = listaJugadores.size() + 1;
                    if (!torneoCreado ||cantJugadores == 0) {
                        System.out.println("Tiene que indicar primero la cantidad de jugadores participantes del torneo (opcion 1)");
                        break;
                    }
                    while (i <= cantJugadores) {
                        System.out.println("/// Jugador nro " + i + " ///");
                        System.out.println("Ingrese el nombre completo del jugador: ");
                        nombreJugador = sc.nextLine();
                        System.out.println("Su nacionalidad: ");
                        nacionalidadJugador = sc.nextLine();
                        int rankingJugador = 0;
                        boolean rankingValido = false;
                        while (!rankingValido) {
                            System.out.println("Su ranking: ");
                            try {
                                rankingJugador = Integer.parseInt(sc.nextLine().trim());
                                if (rankingJugador <= 0) {
                                    System.out.println("El ranking debe ser un numero positivo");
                                    continue;
                                }
                                boolean repetido = false;
                                for (Participante p : listaJugadores) {
                                    if (p.getRanking() == rankingJugador) {
                                        repetido = true;
                                        break;
                                    }
                                }
                                if (repetido) {
                                    System.out.println("Ese ranking ya esta asignado a otro jugador. Ingrese otro:");
                                } else {
                                    rankingValido = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Ingrese un numero valido.");
                            }
                        }
                        listaJugadores.add(new Participante(nombreJugador, nacionalidadJugador, rankingJugador));
                        i++;
                    }

                    System.out.println("Los jugadores han sido inscriptos");
                    break;
                case 3:
                    if (!torneoCreado) {
                        System.out.println("Primero cree el troneo (opcion 1)");
                        break;
                    }
                    if (listaJugadores.size() < cantJugadores) {
                        System.out.println("Faltan cargar jugadores: "
                                + listaJugadores.size() + " de " + cantJugadores
                                + " ingresados (opcion 2)");
                        break;
                    }
                    Collections.sort(listaJugadores);  //ordenamos los jugadores por menor a mayor por ranking
                    boolean crearRueda = torneo.armarPrimeraRueda(listaJugadores);
                    if (crearRueda) {
                        torneo.mostrarPrimeraRonda();
                    }
                    break;
                case 4:

                    List<Nodo> partidos = torneo.obtenerPartidosPendientes();
                    if (partidos.isEmpty()) {
                        System.out.println("El torneo ya finalizo o no empezo");
                        break;
                    }

                    for (int j = 0; j < partidos.size(); j++) {

                        Nodo partidoActual = partidos.get(j);

                        System.out.println("\nPartido " + (j + 1));
                        System.out.println("1 - " + partidoActual.getParticipante1().getNombreCompleto());
                        System.out.println("2 - " + partidoActual.getParticipante2().getNombreCompleto());

                        int opcGanador;

                        do {
                            System.out.print("Ingrese ganador (1 o 2): ");
                            opcGanador = sc.nextInt();
                        } while (opcGanador != 1 && opcGanador != 2);
                        sc.nextLine();
                        torneo.cargarResultado(partidoActual, opcGanador);
                    }

                    torneo.siguienteFase();

                    System.out.println("Resultados cargados correctamente.");

                    System.out.println("\nSIGUIENTE FASE");
                    torneo.mostrarSiguienteFase();


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
                        System.out.println("Nombre: " + campeon.getNombreCompleto());
                        System.out.println("Nacionalidad: " + campeon.getNacionalidad());
                        System.out.println("Ranking: " + campeon.getRanking());
                    }

                    break;
            }
        } while (opcion != 7);
    }

}
