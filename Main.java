import Controladora.*;
import Utils.Filtros;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ControladoraHabitacion.actualizarEstado();
        boolean salir = false;

        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Gestionar Hotel");
            System.out.println("2. Gestionar Habitación");
            System.out.println("3. Gestionar Huésped");
            System.out.println("4. Gestionar Tarifa");
            System.out.println("5. Gestionar Reservas");
            System.out.println("6. Filtros");
            System.out.println("7. Salir");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionarHotel();
                    break;
                case 2:
                    gestionarHabitacion();
                    break;
                case 3:
                    gestionarHuesped();
                    break;
                case 4:
                    gestionarTarifa();
                    break;
                case 5:
                    gestionarReserva();
                    break;
                case 6:
                    gestionarFiltros();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void gestionarHotel() {
        boolean salir = false;
        ControladoraHotel CH = new ControladoraHotel();
        while (!salir) {
            System.out.println("\n=== Gestionar Hotel ===");
            System.out.println("1. Agregar Hotel");
            System.out.println("2. Modificar Hotel");
            System.out.println("3. Eliminar Hotel");
            System.out.println("4. Listar Hoteles");
            System.out.println("5. Ver habitaciones de un hotel");
            System.out.println("6. Volver al Menú Principal");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                  CH.agregarHotel();
                    break;
                case 2:
                   CH.modificarHotel();
                    break;
                case 3:
                 CH.eliminarHotel();
                    break;
                case 4:
                    CH.listarHoteles();
                    break;
                case 5:
                    CH.verHabitaciones();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void gestionarHabitacion() {
        boolean salir = false;
        ControladoraHabitacion CH= new ControladoraHabitacion();
        while (!salir) {
            System.out.println("\n=== Gestionar Habitación ===");
            System.out.println("1. Agregar Habitación");
            System.out.println("2. Modificar Habitación");
            System.out.println("3. Eliminar Habitación");
            System.out.println("4. Listar Habitaciones");
            System.out.println("5. Volver al Menú Principal");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                   CH.agregarHabitacion();
                    break;
                case 2:
                  CH.modificarHabitacion();
                    break;
                case 3:
                 CH.eliminarHabitacion();
                    break;
                case 4:
                  CH.listarHabitaciones();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void gestionarHuesped() {
        boolean salir = false;
        ControladoraHuesped CH= new ControladoraHuesped();
        while (!salir) {
            System.out.println("\n=== Gestionar Huésped ===");
            System.out.println("1. Agregar Huésped");
            System.out.println("2. Modificar Huésped");
            System.out.println("3. Eliminar Huésped");
            System.out.println("4. Listar Huéspedes");
            System.out.println("5. Volver al Menú Principal");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    CH.agregarHuesped();
                    break;
                case 2:
                   CH.modificarHuesped();
                    break;
                case 3:
                   CH.eliminarHuesped();
                    break;
                case 4:
                 CH.listarHuespedes();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void gestionarTarifa() {
        boolean salir = false;
        ControladoraTarifa CT= new ControladoraTarifa();
        while (!salir) {
            System.out.println("\n=== Gestionar Tarifa ===");
            System.out.println("1. Agregar Tarifa");
            System.out.println("2. Modificar Tarifa");
            System.out.println("3. Eliminar Tarifa");
            System.out.println("4. Listar Tarifas");
            System.out.println("5. Volver al Menú Principal");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                   CT.agregarTarifa();
                    break;
                case 2:
                  CT.modificarTarifa();
                    break;
                case 3:
                 CT.eliminarTarifa();
                    break;
                case 4:
                   CT.listarTarifas();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
        private static void gestionarReserva() {
            boolean salir = false;
            ControladoraReserva CR= new ControladoraReserva();
            while (!salir) {
                System.out.println("\n=== Gestionar Reserva ===");
                System.out.println("1. Agregar Reserva");
                System.out.println("2. Modificar Reserva");
                System.out.println("3. Eliminar Reserva");
                System.out.println("4. Listar Reservas");
                System.out.println("5. Volver al Menú Principal");

                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                       CR.agregarReserva();
                        break;
                    case 2:
                        System.out.println("Modificar Tarifa (implementa la lógica aquí)");
                        break;
                    case 3:
                  CR.eliminarReserva();
                        break;
                    case 4:
                     CR.listarReservas();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
    }
    private static void gestionarFiltros() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú de Filtros ===");
            System.out.println("1. Filtrar Hoteles por Ciudad");
            System.out.println("2. Filtrar Hoteles por Nombre");
            System.out.println("3. Filtrar Hoteles por Estrellas");
            System.out.println("4. Filtrar Habitaciones con reserva a futuro");
            System.out.println("5. Volver al Menú Principal");

            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la ciudad para filtrar: ");
                    String ciudad = scanner.nextLine();
                    Filtros.FiltrarHotelPorCiudad(ciudad);
                    break;
                case 2:
                    System.out.print("5" +
                            "Ingrese el nombre del hotel para filtrar: ");
                    String nombreHotel = scanner.nextLine();
                    Filtros.FiltrarHotelPorNombre(nombreHotel);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad de estrellas para filtrar: ");
                    int estrellas = scanner.nextInt();
                    Filtros.FiltrarHotelPorEstrellas(estrellas);
                    break;
                case 4:
                    Filtros.FiltrarHabitaciones();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
