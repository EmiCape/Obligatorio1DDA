package Controladora;

import Dominio.Huesped;
import Persistencia.PHuesped;
import Utils.AppException;
import Utils.Fechas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraHuesped {
    private static Scanner escaner = new Scanner(System.in);

    public void agregarHuesped() {
        System.out.println("Agregar Huesped");

        int id;
        do {
            System.out.println("Ingrese id del huesped:");
            try {
                id = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                id = 0;
                System.out.println(e.getMessage());
            }

        }
        while (id == 0);

        String nombre;
        do {
            System.out.println("Ingrese nombre del huesped");
            nombre = escaner.nextLine();

            try {

                if (nombre.length() < 1) {
                    throw new AppException("El nombre no debe ser vacío");
                }
                if (nombre.length() > 30) {
                    throw new AppException("El nombre debe contener máximo 30 caracteres");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Problema asignando valores.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (nombre.isEmpty());

        String apellidoPaterno;
        do {
            System.out.println("Ingrese el primer apellido del huesped");
            apellidoPaterno = escaner.nextLine();
        }
        while (apellidoPaterno.isEmpty());

        String apellidoMaterno;
        do {
            System.out.println("Ingrese el segundo apellido del huesped");
            apellidoMaterno = escaner.nextLine();
        }
        while (apellidoMaterno.isEmpty());

        String tipoDocumento;
        do {
            System.out.println("Ingrese el tipo de documento del huesped");
            tipoDocumento = escaner.nextLine();
        }
        while (tipoDocumento.isEmpty());

        String documento;
        do {
            System.out.println("Ingrese numero de documento del huesped");
            documento = escaner.nextLine();

            try {

                if (documento.length() < 1) {
                    throw new AppException("El documento no debe ser vacío");
                }
                if (nombre.length() > 30) {
                    throw new AppException("El numero de documento debe tener máximo 30 caracteres");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Problema asignando valores.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (documento.isEmpty())
                ;

        String fechaNacimiento;
        LocalDate fechaDate = null;

        do {
            System.out.println("Ingrese fecha de inscripción (YYYY/MM/DD)");
            fechaNacimiento = escaner.nextLine();

            try {
                fechaDate = Fechas.validarFecha(fechaNacimiento);
                if (fechaDate == null) {
                    throw new AppException("Hubo un error en la conversión de la fecha");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }

        }
        while (fechaDate == null);
        String telefono;
        do {
            System.out.println("Ingrese el telefono del huesped");
            telefono = escaner.nextLine();
        }
        while (telefono.isEmpty());

        String pais;
        do {
            System.out.println("Ingrese el pais del huesped");
            pais = escaner.nextLine();
        }
        while (pais.isEmpty());


        Huesped unHuesped = new Huesped(id, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, documento, fechaDate, telefono, pais);
        if (PHuesped.agregarHuesped(unHuesped))
            System.out.println("Se agregó el Huesped con éxito");
        else
            System.out.println("Hubo un problema al agregar el Huesped");

    }

    public void eliminarHuesped() {
        System.out.println("Eliminar Huesped");

        System.out.println("Ingrese el id");
        int id = Integer.parseInt(escaner.nextLine());

        if (PHuesped.eliminarHuesped(id)) {
            System.out.println("Se eliminó con éxito");
        } else {
            System.out.println("No se pudo eliminar");
        }
    }

    public void modificarHuesped() {
        System.out.println("Modificar Huesped");

        System.out.println("Ingrese el id del huesped a modificar");
        int id = Integer.parseInt(escaner.nextLine());

        Huesped h = buscarHuesped(id);

        if (h == null) {
            System.out.println("No se encontró el huesped con el ID proporcionado.");
            return;
        }

        System.out.println("Ingrese el nombre del huesped (" + h.getNombre() + ")");
        String nombre = escaner.nextLine();
        if (!nombre.isEmpty()) {
            h.setNombre(nombre);
        }

        System.out.println("Ingrese el primer apellido del huesped (" + h.getApellidoPaterno() + ")");
        String apellidoPaterno = escaner.nextLine();
        if (!apellidoPaterno.isEmpty()) {
            h.setApellidoPaterno(apellidoPaterno);
        }

        System.out.println("Ingrese el segundo apellido del huesped (" + h.getApellidoMaterno() + ")");
        String apellidoMaterno = escaner.nextLine();
        if (!apellidoMaterno.isEmpty()) {
            h.setApellidoMaterno(apellidoMaterno);
        }

        System.out.println("Ingrese el tipo de documento del huesped (" + h.getTipoDocumento() + ")");
        String tipoDocumento = escaner.nextLine();
        if (!tipoDocumento.isEmpty()) {
            h.setTipoDocumento(tipoDocumento);
        }

        System.out.println("Ingrese el numero de documento del huesped (" + h.getNumeroDocumento() + ")");
        String numeroDocumento = escaner.nextLine();
        if (!numeroDocumento.isEmpty()) {
            h.setNumeroDocumento(numeroDocumento);
        }

        System.out.println("Ingrese fecha de nacimiento (YYYY/MM/DD) (" + h.getFechaNacimiento() + ")");
        String fechaNacimiento = escaner.nextLine();
        LocalDate fechaDate = null;
        if (!fechaNacimiento.isEmpty()) {
            try {
                fechaDate = Fechas.validarFecha(fechaNacimiento);
                if (fechaDate != null) {
                    h.setFechaNacimiento(fechaDate);
                } else {
                    System.out.println("La fecha ingresada no es válida. No se actualizó la fecha de nacimiento.");
                }
            } catch (Exception e) {
                System.out.println("Error al procesar la fecha: " + e.getMessage());
            }
        }

        System.out.println("Ingrese el telefono del huesped (" + h.getTelefono() + ")");
        String telefono = escaner.nextLine();
        if (!telefono.isEmpty()) {
            h.setTelefono(telefono);
        }

        System.out.println("Ingrese el pais del huesped (" + h.getPais() + ")");
        String pais = escaner.nextLine();
        if (!pais.isEmpty()) {
            h.setPais(pais);
        }

        if (PHuesped.modificarHuesped(h)) {
            System.out.println("Se modificó el huesped con éxito.");
        } else {
            System.out.println("Hubo un problema al modificar el huesped.");
        }
    }


    private Huesped buscarHuesped(int pId) {
        return PHuesped.conseguirHuesped(pId);
    }

    public void conseguirHuesped() {
        System.out.println("Ingrese Id");
        int id = Integer.parseInt(escaner.nextLine());
        System.out.println(buscarHuesped(id).toString());
    }

    public void listarHuespedes() {
        System.out.println("Listado de Huespedes");
        for (Huesped h : PHuesped.listarHuespedes()) {
            System.out.println(h.toString());
        }
    }
}

