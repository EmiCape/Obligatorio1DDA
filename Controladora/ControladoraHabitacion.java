package Controladora;

import Dominio.Habitacion;
import Dominio.Hotel;
import Dominio.Reserva;
import Persistencia.PHabitacion;
import Persistencia.PHotel;
import Persistencia.PReserva;
import Utils.AppException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraHabitacion {
    private static Scanner escaner = new Scanner(System.in);

    public void agregarHabitacion() {
        System.out.println("Agregar Habitación");

        int id;
        do {
            System.out.println("Ingrese id de la habitación:");
            try {
                id = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                id = 0;
                System.out.println(e.getMessage());
            }
        } while (id == 0);

        Hotel unHotel = null;
        do{
            System.out.println("Ingrese id del hotel al que pertenece");
            int idHotel=Integer.parseInt(escaner.nextLine());

            try{
                unHotel = PHotel.conseguirHotel(idHotel);
            }
            catch (Exception e){

            }
        }while (unHotel==null);

        int capacidad;
        do {
            System.out.println("Ingrese capacidad de la habitación:");
            try {
                capacidad = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                capacidad = 0;
                System.out.println("Capacidad debe ser un número válido.");
            }
        } while (capacidad <= 0);

        boolean camaMatrimonial;
        String entrada;
        do {
            System.out.println("¿La habitación tiene cama matrimonial? (si/no)");
             entrada = escaner.nextLine().toLowerCase();
             camaMatrimonial = entrada.equals("si") ? true : false;
        } while (!entrada.equals("si") && !entrada.equals("no"));

        boolean aire;
        String entrada2;
        do {
            System.out.println("¿La habitación tiene aire acondicionado? (si/no)");
            entrada2 = escaner.nextLine().toLowerCase();
            aire = entrada2.equals("si") ? true : false;
        } while (!entrada2.equals("si") && !entrada2.equals("no"));

        boolean balcon;
        String entradaBalcon;
        do {
            System.out.println("¿La habitación tiene balcón? (si/no)");
            entradaBalcon = escaner.nextLine().toLowerCase();
            balcon = entradaBalcon.equals("si") ? true : false;
        } while (!entradaBalcon.equals("si") && !entradaBalcon.equals("no"));

        boolean vista;
        String entradaVista;
        do {
            System.out.println("¿La habitación tiene vista? (si/no)");
            entradaVista = escaner.nextLine().toLowerCase();
            vista = entradaVista.equals("si") ? true : false;
        } while (!entradaVista.equals("si") && !entradaVista.equals("no"));

        String amenities;
        do {
            System.out.println("Ingrese amenities de la habitación:");
            amenities = escaner.nextLine();
        } while (amenities.isEmpty());


        boolean ocupada = false;

        Habitacion unaHabitacion = new Habitacion(id, unHotel, capacidad, camaMatrimonial, aire, balcon, vista, amenities, ocupada);
        if (PHabitacion.agregarHabitacion(unaHabitacion)) {
            System.out.println("Se agregó la habitación con éxito");
        } else {
            System.out.println("Hubo un problema al agregar la habitación");
        }
    }

    public void eliminarHabitacion() {
        System.out.println("Eliminar Habitación");

        System.out.println("Ingrese el id de la habitación:");
        int id = Integer.parseInt(escaner.nextLine());

        if (PHabitacion.eliminarHabitacion(id)) {
            System.out.println("Se eliminó con éxito la habitación.");
        } else {
            System.out.println("No se pudo eliminar la habitación.");
        }
    }

    public void modificarHabitacion() {
        System.out.println("Modificar Habitación");

        System.out.println("Ingrese el id de la habitación a modificar:");
        int id = Integer.parseInt(escaner.nextLine());

        Habitacion h = buscarHabitacion(id);

        if (h == null) {
            System.out.println("No se encontró la habitación con el ID proporcionado.");
            return;
        }

        System.out.println("Ingrese capacidad de la habitación (" + h.getCapacidad() + "):");
        String capacidadInput = escaner.nextLine();
        if (!capacidadInput.isEmpty()) {
            h.setCapacidad(Integer.parseInt(capacidadInput));
        }


       System.out.println("¿La habitación tiene cama matrimonial? (si/no) (" + (h.isCamaMatrimonial() ? "si" : "no") + "):");
       String camaInput = escaner.nextLine().toLowerCase();
        if (!camaInput.equals("si") || !camaInput.equals("no")) {
           h.setCamaMatrimonial(camaInput.equals("si"));
     }


        System.out.println("¿La habitación tiene aire acondicionado? (si/no) (" + (h.isAireAcondicionado() ? "si" : "no") + "):");
        String aireInput = escaner.nextLine().toLowerCase();
        if (!aireInput.equals("si") || !aireInput.equals("no")) {
            h.setAireAcondicionado(aireInput.equals("si"));
        }


        System.out.println("¿La habitación tiene balcón? (si/no) (" + (h.isBalcon() ? "si" : "no") + "):");
        String balconInput = escaner.nextLine().toLowerCase();
        if (!balconInput.equals("si") || !balconInput.equals("no")) {
            h.setBalcon(balconInput.equals("si"));
        }


        System.out.println("¿La habitación tiene vista? (si/no) (" + (h.isVista() ? "si" : "no") + "):");
        String vistaInput = escaner.nextLine().toLowerCase();
        if (!vistaInput.equals("si") || !vistaInput.equals("no")) {
            h.setVista(vistaInput.equals("si"));
        }

        System.out.println("Ingrese amenities de la habitación (" + h.getAmenities() + "):");
        String amenitiesInput = escaner.nextLine();
        if (!amenitiesInput.isEmpty()) {
            h.setAmenities(amenitiesInput);
        }

        if (PHabitacion.modificarHabitacion(h)) {
            System.out.println("Se modificó la habitación con éxito.");
        } else {
            System.out.println("Hubo un problema al modificar la habitación.");
        }
    }

    private Habitacion buscarHabitacion(int pId) {
        return PHabitacion.conseguirHabitacion(pId);
    }

    public void conseguirHabitacion() {
        System.out.println("Ingrese Id de la habitación:");
        int id = Integer.parseInt(escaner.nextLine());
        Habitacion habitacion = buscarHabitacion(id);
        if (habitacion != null) {
            System.out.println(habitacion.toString());
        } else {
            System.out.println("No se encontró la habitación con el ID proporcionado.");
        }
    }

    public void listarHabitaciones() {
        System.out.println("Listado de Habitaciones");
        for (Habitacion h : PHabitacion.listarHabitaciones()) {
            System.out.println(h.toString());
        }
    }

    public static void actualizarEstado(){
        LocalDate hoy = LocalDate.now();
        ArrayList<Reserva> reservas = PReserva.listarReservas();
        for(Reserva r : reservas){
            if(r.getFechaInicio().isBefore(hoy) && r.getFechaFin().isAfter(hoy) ||
                r.getFechaInicio().equals(hoy) || r.getFechaFin().equals(hoy)){
                PHabitacion.estaOcupada(r.getHabitacion().getIdHabitacion(), r.getHuesped().getIdHuesped());

            }
            else{
                PHabitacion.noEstaOcupada(r.getHabitacion().getIdHabitacion());

            }
        }
    }
}
