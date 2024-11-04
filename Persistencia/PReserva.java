package Persistencia;

import Dominio.Habitacion;
import Dominio.Hotel;
import Dominio.Huesped;
import Dominio.Reserva;
import Utils.AppSQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PReserva {

    private static Conexion conexion = new Conexion();

    public static boolean agregarReserva(Reserva pReserva) {
        String sql = "INSERT INTO Reserva(idReserva, idHuesped, idHotel, idHabitacion, cantidadPersonas, fechaInicio, fechaFin, montoReserva, montoTotal, pagadaCompletamente, fechaRes, observacion) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pReserva.getIdReserva(),
                pReserva.getHuesped().getIdHuesped(),
                pReserva.getHotel().getIdHotel(),
                pReserva.getHabitacion().getIdHabitacion(),
                pReserva.getCantidadPersonas(),
                pReserva.getFechaInicio(),
                pReserva.getFechaFin(),
                pReserva.getMontoReserva(),
                pReserva.getMontoTotal(),
                pReserva.isPagadaCompletamente(),
                pReserva.getFechaRes(),
                pReserva.getObservacion()
        ));
        try {
            return conexion.consulta(sql, parametros);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static boolean eliminarReserva(int pIdReserva) {
        String sql = "DELETE FROM Reserva WHERE idReserva = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdReserva));
        try {
            return conexion.consulta(sql, parametros);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Reserva conseguirReserva(int pIdReserva) {
        String sql = "SELECT idReserva, huespedId, hotelId, habitacionId, cantidadPersonas, fechaInicio, fechaFin, montoReserva, montoTotal, pagadaCompletamente, fechaRes, observacion FROM Reserva WHERE idReserva = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdReserva));

        List<List<Object>> resultado = null;
        try {
            resultado = conexion.seleccion(sql, parametros);
            if (resultado.isEmpty()) {
                return null;
            }

            int idReserva = (int) resultado.get(0).get(0);
            int huespedId = (int) resultado.get(0).get(1);
            int hotelId = (int) resultado.get(0).get(2);
            int habitacionId = (int) resultado.get(0).get(3);
            int cantidadPersonas = (int) resultado.get(0).get(4);
            LocalDate fechaInicio = LocalDate.parse(String.valueOf(resultado.get(0).get(5)));

            LocalDate fechaFin = LocalDate.parse(String.valueOf(resultado.get(0).get(6)));
            int montoReserva = (int) resultado.get(0).get(7);
            int montoTotal= (int) resultado.get(0).get(8);
            boolean pagadaCompletamente = (boolean) resultado.get(0).get(9);
            LocalDate fechaRes = LocalDate.parse((String.valueOf( resultado.get(0).get(10))));
            String observacion = String.valueOf(resultado.get(0).get(11));

            Huesped unHuesped= PHuesped.conseguirHuesped(huespedId);
            Hotel unHotel= PHotel.conseguirHotel(hotelId);
            Habitacion unaHabitacion= PHabitacion.conseguirHabitacion(habitacionId);

            return new Reserva(idReserva, unHuesped, unHotel, unaHabitacion, cantidadPersonas, fechaInicio, fechaFin, montoReserva,montoTotal, pagadaCompletamente, fechaRes, observacion);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Reserva> listarReservas() {
        String sql = "SELECT * FROM Reserva";
        List<List<Object>> registros = null;
        try {
            registros = conexion.seleccion(sql, null);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        ArrayList<Reserva> reservas = new ArrayList<>();
        for (List<Object> registro : registros) {
            int idReserva = (int) registro.get(0);
            int huespedId = (int) registro.get(1);
            int hotelId = (int) registro.get(2);
            int habitacionId = (int) registro.get(3);
            int cantidadPersonas = (int) registro.get(4);
            LocalDate fechaInicio = LocalDate.parse(String.valueOf(registro.get(5)));

            LocalDate fechaFin = LocalDate.parse(String.valueOf(registro.get(6)));
            int montoReserva = (int) registro.get(7);
            int montoTotal = (int) registro.get(8);
            boolean pagadaCompletamente = (boolean) registro.get(9);
            LocalDate fechaRes = LocalDate.parse((String.valueOf( registro.get(10))));
            String observacion = String.valueOf(registro.get(11));

            Huesped unHuesped = PHuesped.conseguirHuesped(huespedId);
            Hotel unHotel = PHotel.conseguirHotel(hotelId);
            Habitacion unaHabitacion = PHabitacion.conseguirHabitacion(habitacionId);

            reservas.add(new Reserva(idReserva, unHuesped, unHotel, unaHabitacion, cantidadPersonas, fechaInicio, fechaFin, montoReserva,montoTotal, pagadaCompletamente, fechaRes, observacion));
        }
        return reservas;
    }
}
