/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial2eda;

import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Anitabonita
 */
public class Prints {

    public static final List<String> MENU = Arrays.asList(
            "Menu",
            "1. Ingresar cantidad de jugadores",
            "2. Ingresar jugadores inscriptos",
            "3. Armar primera rueda del torneo",
            "4. Cargar resultados de las fases",
            "5. Mostrar resultados de las fases",
            "6. Mostrar campeón",
            "7. Salir"
    );

    public static int imprimirMenu() {

    String menu = "";

    for (String linea : MENU) {
        menu += linea + "\n";
    }

    menu += "\nSeleccione una opción:";

    try {
        return Integer.parseInt(
                JOptionPane.showInputDialog(null, menu)
        );
    } catch (NumberFormatException e) {
        return 0;
    }
}

    public static void imprimirJugador(int numero) {
        JOptionPane.showMessageDialog(
                null,
                "/// Jugador nro " + numero + " ///\nIngrese el nombre completo del jugador:"
        );
    }

    public static void torneoCreado(int cantidad) {
        JOptionPane.showMessageDialog(
                null,
                "Torneo creado para " + cantidad + " jugadores"
        );
    }

    public static void jugadoresInscriptos() {
        JOptionPane.showMessageDialog(
                null,
                "Los jugadores han sido inscriptos"
        );
    }

    public static void resultadosCargados() {
        JOptionPane.showMessageDialog(
                null,
                "Resultados cargados correctamente."
        );
    }

    public static void mostrarTituloResultados() {
        JOptionPane.showMessageDialog(
                null,
                "=== RESULTADOS DEL TORNEO ==="
        );
    }

    public static void torneoNoFinalizado() {
        JOptionPane.showMessageDialog(
                null,
                "El torneo aun no ha finalizado"
        );
    }

    public static void torneoFinalizado() {
        JOptionPane.showMessageDialog(
                null,
                "El torneo ya finalizo o no empezo"
        );
    }

    public static void mostrarPartido(int numero, Nodo partido) {

        JOptionPane.showMessageDialog(
                null,
                "Partido " + numero
                + "\n1 - " + partido.getParticipante1().getNombreCompleto()
                + "\n2 - " + partido.getParticipante2().getNombreCompleto()
        );
    }

    public static void mostrarCampeon(Participante campeon) {

        JOptionPane.showMessageDialog(
                null,
                "=== CAMPEON DEL TORNEO ==="
                + "\nNombre: " + campeon.getNombreCompleto()
                + "\nNacionalidad: " + campeon.getNacionalidad()
                + "\nRanking: " + campeon.getRanking()
        );
    }

}
