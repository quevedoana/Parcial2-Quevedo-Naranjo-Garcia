package parcial2eda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Parcial2Eda {

    public static void main(String[] args) {

        Arbol torneo = new Arbol();
        List<Participante> listaJugadores = new ArrayList<>();

        int cantJugadores = 0;
        String nombreJugador;
        String nacionalidadJugador = null;
        boolean torneoCreado = false;
        int opcion;

        do {

            opcion = Prints.imprimirMenu();

            switch (opcion) {

                case 1:

                    try {

                        cantJugadores = Integer.parseInt(
                                JOptionPane.showInputDialog(
                                        "Ingrese la cantidad de jugadores para el torneo:"
                                )
                        );

                        while (!torneo.cantParticipantes(cantJugadores)) {

                            cantJugadores = Integer.parseInt(
                                    JOptionPane.showInputDialog(
                                            "Cantidad inválida.\nIngrese nuevamente:"
                                    )
                            );
                        }

                        torneo.crearTorneo(cantJugadores);
                        torneoCreado = true;

                        Prints.torneoCreado(cantJugadores);

                    } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Debe ingresar un número válido"
                        );
                    }

                    break;

                case 2:

                    int i = listaJugadores.size() + 1;

                    if (!torneoCreado || cantJugadores == 0) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Debe crear primero el torneo (opción 1)"
                        );

                        break;
                    }

                    while (i <= cantJugadores) {

                        nombreJugador = JOptionPane.showInputDialog(
                                "Jugador " + i
                                + "\nIngrese el nombre completo:"
                        );

                        nacionalidadJugador = JOptionPane.showInputDialog(
                                "Ingrese la nacionalidad:"
                        );

                        int rankingJugador = 0;
                        boolean rankingValido = false;

                        while (!rankingValido) {

                            try {

                                rankingJugador = Integer.parseInt(
                                        JOptionPane.showInputDialog(
                                                "Ingrese el ranking:"
                                        )
                                );

                                if (rankingJugador <= 0) {

                                    JOptionPane.showMessageDialog(
                                            null,
                                            "El ranking debe ser positivo"
                                    );

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

                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Ese ranking ya está asignado a otro jugador"
                                    );

                                } else {

                                    rankingValido = true;
                                }

                            } catch (NumberFormatException e) {

                                JOptionPane.showMessageDialog(
                                        null,
                                        "Ingrese un número válido"
                                );
                            }
                        }

                        listaJugadores.add(
                                new Participante(
                                        nombreJugador,
                                        nacionalidadJugador,
                                        rankingJugador
                                )
                        );

                        i++;
                    }

                    Prints.jugadoresInscriptos();

                    break;

                case 3:

                    if (!torneoCreado) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Primero cree el torneo (opción 1)"
                        );

                        break;
                    }

                    if (listaJugadores.size() < cantJugadores) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Faltan cargar jugadores.\n"
                                + "Ingresados: "
                                + listaJugadores.size()
                                + " de "
                                + cantJugadores
                        );

                        break;
                    }

                    Collections.sort(listaJugadores);

                    boolean crearRueda = torneo.armarPrimeraRueda(listaJugadores);

                    if (crearRueda) {
                        torneo.mostrarPrimeraRonda();
                    }

                    break;

                case 4:

                    List<Nodo> partidos = torneo.obtenerPartidosPendientes();

                    if (partidos.isEmpty()) {

                        Prints.torneoFinalizado();

                        break;
                    }

                    for (int j = 0; j < partidos.size(); j++) {

                        Nodo partidoActual = partidos.get(j);

                        Prints.mostrarPartido(j + 1, partidoActual);

                        int opcGanador;

                        do {

                            try {

                                opcGanador = Integer.parseInt(
                                        JOptionPane.showInputDialog(
                                                "Ingrese ganador:\n"
                                                + "1 = "
                                                + partidoActual.getParticipante1().getNombreCompleto()
                                                + "\n2 = "
                                                + partidoActual.getParticipante2().getNombreCompleto()
                                        )
                                );

                            } catch (NumberFormatException e) {

                                opcGanador = 0;
                            }

                        } while (opcGanador != 1 && opcGanador != 2);

                        torneo.cargarResultado(partidoActual, opcGanador);
                    }

                    torneo.siguienteFase();

                    Prints.resultadosCargados();

                    torneo.mostrarSiguienteFase();

                    break;

                case 5:

                    Prints.mostrarTituloResultados();
                    torneo.mostrarResultado();

                    break;

                case 6:

                    Participante campeon = torneo.obtenerCampeon();

                    if (campeon == null) {

                        Prints.torneoNoFinalizado();

                    } else {

                        Prints.mostrarCampeon(campeon);
                    }

                    break;

                case 7:

                    JOptionPane.showMessageDialog(
                            null,
                            "Gracias por usar el sistema"
                    );

                    break;

                default:

                    JOptionPane.showMessageDialog(
                            null,
                            "Opción inválida"
                    );
            }

        } while (opcion != 7);
    }
}