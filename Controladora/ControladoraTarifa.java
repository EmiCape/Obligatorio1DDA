package Controladora;

import Dominio.Tarifa;
import Persistencia.PTarifa;
import Utils.AppException;
import Utils.Fechas;

import java.time.LocalDate;
import java.util.Scanner;

public class ControladoraTarifa {
    private static Scanner escaner = new Scanner(System.in);

    public void agregarTarifa() {
        System.out.println("Agregar Tarifa");

        LocalDate fechaInicio = null;
        do {
            System.out.println("Ingrese la fecha de inicio (YYYY/MM/DD):");
            String fechaInicioStr = escaner.nextLine();

            try {
                fechaInicio = Fechas.validarFecha(fechaInicioStr);
                if (fechaInicio == null) {
                    throw new AppException("Fecha de inicio inválida");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaInicio == null);

        LocalDate fechaFin = null;
        do {
            System.out.println("Ingrese la fecha de fin (YYYY/MM/DD):");
            String fechaFinStr = escaner.nextLine();

            try {
                fechaFin = Fechas.validarFecha(fechaFinStr);
                if (fechaFin == null || fechaFin.isBefore(fechaInicio)) {
                    throw new AppException("Fecha de fin inválida o anterior a la fecha de inicio");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        } while (fechaFin == null);

        int monto = 0;
        do {
            System.out.println("Ingrese el monto:");
            try {
                monto = Integer.parseInt(escaner.nextLine());
                if (monto <= 0) {
                    throw new AppException("El monto debe ser mayor que 0");
                }
            } catch (AppException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("El monto debe ser un número válido.");
            }
        } while (monto <= 0);

        Tarifa nuevaTarifa = new Tarifa(fechaInicio, fechaFin, monto);
        if (PTarifa.agregarTarifa(nuevaTarifa))
            System.out.println("Tarifa agregada con éxito.");
        else
            System.out.println("Hubo un problema al agregar la tarifa.");
    }

    public void eliminarTarifa() {
        System.out.println("Eliminar Tarifa");

        System.out.println("Ingrese la fecha de inicio de la tarifa a eliminar (YYYY/MM/DD):");
        LocalDate fechaInicio = Fechas.validarFecha(escaner.nextLine());

        if (PTarifa.eliminarTarifa(fechaInicio)) {
            System.out.println("Tarifa eliminada con éxito.");
        } else {
            System.out.println("No se pudo eliminar la tarifa.");
        }
    }

    public void modificarTarifa() {
        System.out.println("Modificar Tarifa");

        System.out.println("Ingrese la fecha de inicio de la tarifa a modificar (YYYY/MM/DD):");
        LocalDate fechaInicio = Fechas.validarFecha(escaner.nextLine());

        Tarifa tarifa = buscarTarifa(fechaInicio);

        if (tarifa == null) {
            System.out.println("No se encontró la tarifa con la fecha de inicio proporcionada.");
            return;
        }

        System.out.println("Ingrese la nueva fecha de fin (actual: " + tarifa.getFechaFin() + ") (YYYY/MM/DD):");
        String fechaFinStr = escaner.nextLine();
        if (!fechaFinStr.isEmpty()) {
            LocalDate nuevaFechaFin = Fechas.validarFecha(fechaFinStr);
            if (nuevaFechaFin != null && !nuevaFechaFin.isBefore(tarifa.getFechaInicio())) {
                tarifa.setFechaFin(nuevaFechaFin);
            } else {
                System.out.println("La fecha de fin ingresada no es válida.");
            }
        }

        System.out.println("Ingrese el nuevo monto (actual: " + tarifa.getMonto() + "):");
        String montoStr = escaner.nextLine();
        if (!montoStr.isEmpty()) {
            try {
                int nuevoMonto = Integer.parseInt(montoStr);
                if (nuevoMonto > 0) {
                    tarifa.setMonto(nuevoMonto);
                } else {
                    System.out.println("El monto debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El monto ingresado no es válido.");
            }
        }

        if (PTarifa.modificarTarifa(tarifa)) {
            System.out.println("Tarifa modificada con éxito.");
        } else {
            System.out.println("Hubo un problema al modificar la tarifa.");
        }
    }

    private Tarifa buscarTarifa(LocalDate fechaInicio) {
        return PTarifa.conseguirTarifa(fechaInicio);
    }

    public void conseguirTarifa() {
        System.out.println("Ingrese la fecha de inicio de la tarifa (YYYY/MM/DD):");
        LocalDate fechaInicio = Fechas.validarFecha(escaner.nextLine());
        Tarifa tarifa = buscarTarifa(fechaInicio);
        if (tarifa != null) {
            System.out.println(tarifa.toString());
        } else {
            System.out.println("No se encontró la tarifa.");
        }
    }

    public void listarTarifas() {
        System.out.println("Listado de Tarifas:");
        for (Tarifa tarifa : PTarifa.listarTarifas()) {
            System.out.println(tarifa.toString());
        }
    }
}
