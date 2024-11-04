package Controladora;

import Dominio.*;
import Persistencia.PHabitacion;
import Persistencia.PHotel;
import Persistencia.PHuesped;
import Persistencia.PReserva;
import Utils.AppException;
import Utils.Fechas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraReserva {
    private static Scanner scanner = new Scanner(System.in);

    public void agregarReserva() {
        System.out.println("Agregar Reserva");

        int idReserva;
        do {
            System.out.println("Ingrese ID de la reserva:");
            try {
                idReserva = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                idReserva = 0;
                System.out.println("ID inválido. Intente de nuevo.");
            }
        } while (idReserva == 0);


        Huesped huesped = null;
        do {
            System.out.println("Ingrese id del huesped");
            try {
                int id = Integer.parseInt(scanner.nextLine());
                huesped = PHuesped.conseguirHuesped(id);
            } catch (Exception e) {
                System.out.println("El huesped no existe");
            }
        } while (huesped == null);

        Hotel hotel = null;
        do {
            System.out.println("Ingrese ID del hotel:");
            try {
                int idHotel = Integer.parseInt(scanner.nextLine());
                hotel = PHotel.conseguirHotel(idHotel);
            } catch (Exception e) {
                System.out.println("El hotel no existe.");
            }
        } while (hotel == null);
        Hotel.verHabitaciones(hotel.getIdHotel());
        Habitacion habitacion = null;
        do {
            System.out.println("Ingrese ID de la habitación:");
            try {
                int idHabitacion = Integer.parseInt(scanner.nextLine());
                habitacion = PHabitacion.conseguirHabitacion(idHabitacion);
            } catch (Exception e) {
                System.out.println("La habitacion no existe");
            }
        } while (habitacion == null);

        int cantidadPersonas;
        do {
            System.out.println("Ingrese la cantidad de personas:");
            try {
                cantidadPersonas = Integer.parseInt(scanner.nextLine());
                if (cantidadPersonas <= 0) {
                    throw new AppException("La cantidad de personas debe ser mayor a 0.");
                }
            } catch (Exception e) {
                cantidadPersonas = 0;
                System.out.println("Cantidad inválida. Intente de nuevo.");
            }
        } while (cantidadPersonas <= 0);

        LocalDate fechaInicio = null;
        do {
            System.out.println("Ingrese la fecha del inicio de la reserva (YYYY/MM/DD):");
            String fechaFinStr = scanner.nextLine();

            try {
                fechaInicio = Fechas.validarFecha(fechaFinStr);
                if (fechaInicio == null ) {
                    throw new AppException("Fecha de inicio invalida");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaInicio == null);

        LocalDate fechaFin = null;
        do {
            System.out.println("Ingrese la fecha de fin de la reserva (YYYY/MM/DD):");
            String fechaFinStr = scanner.nextLine();

            try {
                fechaFin = Fechas.validarFecha(fechaFinStr);
                if (fechaFin == null || fechaFin.isBefore(fechaInicio)) {
                    throw new AppException("Fecha de fin inválida o anterior a la fecha de inicio");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaFin == null);

        if (existeReservaEnFecha(habitacion.getIdHabitacion(), fechaInicio, fechaFin)) {
            System.out.println("Ya existe una reserva para esta habitación en el rango de fechas solicitado.");
            return;
        }

        int montoReserva;
        int monto;
        do {
           monto = Tarifa.devolverTarifa(fechaInicio);
            montoReserva = (int) (monto * 0.2);
            System.out.println("El Monto de la reserva sera: $" + montoReserva);
            System.out.println("El Monto total a pagar es : $" + monto);
        } while (montoReserva <= 0);

        boolean pagadoTotal;
        String entrada;
        do {
            System.out.println("¿Pagó completamente la reserva? (si/no)");
            entrada = scanner.nextLine().toLowerCase();
            pagadoTotal = entrada.equals("si") ? true : false;
        } while (!entrada.equals("si") && !entrada.equals("no"));

        LocalDate fechaRes = LocalDate.now();

        String observaciones;
        do{
            System.out.println("Ingrese observaciones");
            observaciones= scanner.nextLine();
        }
        while (observaciones.isEmpty());

        Reserva nuevaReserva = new Reserva(idReserva, huesped, hotel, habitacion, cantidadPersonas, fechaInicio, fechaFin, montoReserva,monto, pagadoTotal, fechaRes, observaciones );
        if (PReserva.agregarReserva(nuevaReserva)) {
            System.out.println("Reserva agregada con éxito.");
        } else {
            System.out.println("Hubo un problema al agregar la reserva.");
        }
    }

    public void eliminarReserva() {
        System.out.println("Eliminar Reserva");

        System.out.println("Ingrese el ID de la reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());

        if (PReserva.eliminarReserva(idReserva)) {
            System.out.println("Reserva eliminada con éxito.");
        } else {
            System.out.println("No se pudo eliminar la reserva.");
        }
    }



    public void listarReservas() {
        System.out.println("Listado de Reservas");
        ArrayList<Reserva> reservas = PReserva.listarReservas();
        for (Reserva r : reservas) {
            System.out.println(r.toString());
        }
    }

    public void conseguirReserva() {
        System.out.println("Ingrese ID de la reserva:");
        int idReserva = Integer.parseInt(scanner.nextLine());
        Reserva reserva = buscarReserva(idReserva);
        if (reserva != null) {
            System.out.println(reserva.toString());
        } else {
            System.out.println("No se encontró la reserva con el ID proporcionado.");
        }
    }
    private Reserva buscarReserva(int idReserva) {
        return PReserva.conseguirReserva(idReserva);
    }



    public static boolean existeReservaEnFecha(int idHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Reserva> reservas = PReserva.listarReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().getIdHabitacion() == idHabitacion) {

                if ((fechaInicio.isBefore(reserva.getFechaFin()) && fechaFin.isAfter(reserva.getFechaInicio())) ||
                        fechaInicio.equals(reserva.getFechaInicio()) || fechaFin.equals(reserva.getFechaFin())) {
                    return true;
                }
            }
        }
        return false;
    }

}
