package Utils;

import Dominio.Habitacion;
import Dominio.Hotel;
import Dominio.Reserva;
import Persistencia.PHabitacion;
import Persistencia.PHotel;
import Persistencia.PReserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Handler;

public class Filtros {

    public static void FiltrarHotelPorCiudad(String pCiudad){

ArrayList<Hotel> hoteles = PHotel.listarHoteles();
ArrayList<Hotel> filtrados = new ArrayList<>();

    for(Hotel h : hoteles ){
    if(h.getCiudad().equals(pCiudad)){
        filtrados.add(h);
    }

    }
        System.out.println(filtrados);
    }


    public static  void FiltrarHotelPorNombre(String Nombre){

        ArrayList<Hotel> hoteles = PHotel.listarHoteles();
        ArrayList<Hotel> filtrados = new ArrayList<>();
        for(Hotel h : hoteles ){
            if(h.getNombre().equals(Nombre)){
                filtrados.add(h);
            }

        }
        System.out.println(filtrados);
    }

    public static  void FiltrarHotelPorEstrellas(int pEstrellas){

        ArrayList<Hotel> hoteles = PHotel.listarHoteles();
        ArrayList<Hotel> filtrados = new ArrayList<>();
        for(Hotel h : hoteles ){
            if(h.getEstrellas()==pEstrellas){
                filtrados.add(h);
            }

        }
        System.out.println(filtrados);
    }

    public static void FiltrarHabitaciones() {
        ArrayList<Habitacion> habitaciones = PHabitacion.listarHabitaciones(); // Lista completa de habitaciones
        ArrayList<Reserva> reservas = PReserva.listarReservas();
        ArrayList<Habitacion> conReserva = new ArrayList<>();
        ArrayList<Habitacion> sinReserva = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (Habitacion habitacion : habitaciones) {
            boolean reservada = false;

            for (Reserva reserva : reservas) {
                if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicio().isAfter(hoy)) {
                    conReserva.add(habitacion);
                    reservada = true;
                    break;
                }
            }

            if (!reservada) {
                sinReserva.add(habitacion);
            }
        }


        System.out.println("=== Habitaciones con Reserva Próxima ===");
        if (conReserva.isEmpty()) {
            System.out.println("No hay habitaciones reservadas.");
        } else {
            for (Habitacion habitacion : conReserva) {
                System.out.println(habitacion);
            }
        }


        System.out.println("\n=== Habitaciones sin Reserva Próxima ===");
        if (sinReserva.isEmpty()) {
            System.out.println("No hay habitaciones disponibles sin reserva.");
        } else {
            for (Habitacion habitacion : sinReserva) {
                System.out.println(habitacion);
            }
        }
    }




}





