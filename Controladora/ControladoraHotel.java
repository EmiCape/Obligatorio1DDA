package Controladora;

import Dominio.Hotel;
import Persistencia.PHotel;
import Utils.AppException;

import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraHotel {
    private static Scanner escaner = new Scanner(System.in);

    public void agregarHotel() {
        System.out.println("Agregar Hotel");

        int id;
        do {
            System.out.println("Ingrese id del hotel:");
            try {
                id = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                id = 0;
                System.out.println(e.getMessage());
            }
        } while (id == 0);


        String nombre;
        do{
            System.out.println("Ingrese nombre del hotel");
            nombre= escaner.nextLine();
        }
        while (nombre.isEmpty());

        String ciudad;
        do {
            System.out.println("Ingrese ciudad del hotel:");
            ciudad = escaner.nextLine();
        } while (ciudad.isEmpty());

        String pais;
        do {
            System.out.println("Ingrese país del hotel:");
            pais = escaner.nextLine();
        } while (pais.isEmpty());

        int estrellas;
        do {
            System.out.println("Ingrese cantidad de estrellas del hotel (1-5):");
            try {
                estrellas = Integer.parseInt(escaner.nextLine());
                if (estrellas < 1 || estrellas > 5) {
                    throw new AppException("Las estrellas deben estar entre 1 y 5.");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
                estrellas = 0;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                estrellas = 0;
            }
        } while (estrellas == 0);

        String direccion;
        do {
            System.out.println("Ingrese dirección del hotel:");
            direccion = escaner.nextLine();
        } while (direccion.isEmpty());

        String zona;
        do {
            System.out.println("Ingrese zona del hotel:");
            zona = escaner.nextLine();
        } while (zona.isEmpty());

        Hotel unHotel = new Hotel(id, nombre, ciudad, pais, estrellas, direccion, zona);
        if (PHotel.agregarHotel(unHotel)) {
            System.out.println("Se agregó el hotel con éxito.");
        } else {
            System.out.println("Hubo un problema al agregar el hotel.");
        }
    }

    public void eliminarHotel() {
        System.out.println("Eliminar Hotel");

        System.out.println("Ingrese el id del hotel:");
        int id = Integer.parseInt(escaner.nextLine());

        if (PHotel.eliminarHotel(id)) {
            System.out.println("Se eliminó el hotel con éxito.");
        } else {
            System.out.println("No se pudo eliminar el hotel.");
        }
    }

    public void modificarHotel() {
        System.out.println("Modificar Hotel");

        System.out.println("Ingrese el id del hotel a modificar:");
        int id = Integer.parseInt(escaner.nextLine());

        Hotel h = buscarHotel(id);

        if (h == null) {
            System.out.println("No se encontró el hotel con el ID proporcionado.");
            return;
        }

        System.out.println("Ingrese el nombre del hotel (" + h.getNombre() + "):");
        String nombre = escaner.nextLine();
        if (!nombre.isEmpty()) {
            h.setNombre(nombre);
        }

        System.out.println("Ingrese la ciudad del hotel (" + h.getCiudad() + "):");
        String ciudad = escaner.nextLine();
        if (!ciudad.isEmpty()) {
            h.setCiudad(ciudad);
        }

        System.out.println("Ingrese el país del hotel (" + h.getPais() + "):");
        String pais = escaner.nextLine();
        if (!pais.isEmpty()) {
            h.setPais(pais);
        }

        System.out.println("Ingrese la cantidad de estrellas del hotel entre 1 y 5 (" + h.getEstrellas() + "):");
        String estrellasInput = escaner.nextLine();
        if (!estrellasInput.isEmpty()) {
            int estrellas = Integer.parseInt(estrellasInput);
            h.setEstrellas(estrellas);
        }

        System.out.println("Ingrese la dirección del hotel (" + h.getDireccion() + "):");
        String direccion = escaner.nextLine();
        if (!direccion.isEmpty()) {
            h.setDireccion(direccion);
        }

        System.out.println("Ingrese la zona del hotel (" + h.getZona() + "):");
        String zona = escaner.nextLine();
        if (!zona.isEmpty()) {
            h.setZona(zona);
        }

        if (PHotel.modificarHotel(h)) {
            System.out.println("Se modificó el hotel con éxito.");
        } else {
            System.out.println("Hubo un problema al modificar el hotel.");
        }
    }

    private Hotel buscarHotel(int pId) {
        return PHotel.conseguirHotel(pId);
    }

    public void listarHoteles() {
        System.out.println("Listado de Hoteles");
        ArrayList<Hotel> hoteles = PHotel.listarHoteles();
        for (Hotel h : hoteles) {
            System.out.println(h.toString());
        }
    }

    public void conseguirHotel() {
        System.out.println("Ingrese Id del hotel:");
        int id = Integer.parseInt(escaner.nextLine());
        Hotel h = buscarHotel(id);
        if (h != null) {
            System.out.println(h.toString());
        } else {
            System.out.println("No se encontró el hotel con el ID proporcionado.");
        }
    }

    public void verHabitaciones(){

        System.out.println("Ingrese id del hotel");
        int id= Integer.parseInt(escaner.nextLine());
        Hotel.verHabitaciones(id);
    }
}
