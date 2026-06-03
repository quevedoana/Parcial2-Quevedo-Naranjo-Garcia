package parcial2eda;

import java.util.Scanner;

/**
 *
 * @author Anitabonita
 */
public class Parcial2Eda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Arbol torneo = new Arbol();
        int cant_jugadores;
        int opcion;
        do {
            System.out.println("Menu");
            System.out.println("1. Ingresar cantidad de jugadores");
            System.out.println("2. Ingresar jugadores inscriptos");
            System.out.println("3. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la cantidad de jugadores para el torneo:");
                    cant_jugadores = sc.nextInt();
                    while (!torneo.cant_participantes(cant_jugadores)) {
                        System.out.println("La cantidad de jugadores ingresada no es valida. Ingrese nuevamente:");
                        cant_jugadores = sc.nextInt();
                    }
                    torneo.crear_torneo(cant_jugadores);
                    System.out.println("Torneo creado");
                    break;
            }
        } while (opcion != 3);
    }

}
