package Persistencia;

import Dominio.Tarifa;
import Utils.AppSQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PTarifa {

    private static Conexion conexion = new Conexion();

    public static boolean agregarTarifa(Tarifa pTarifa) {
        String sql = "INSERT INTO Tarifa(fechaInicio, fechaFin, monto) VALUES(?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pTarifa.getFechaInicio(), pTarifa.getFechaFin(), pTarifa.getMonto()));
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

    public static boolean eliminarTarifa(LocalDate pFechaInicio) {
        String sql = "DELETE FROM Tarifa WHERE fechaInicio = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pFechaInicio));
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

    public static boolean modificarTarifa(Tarifa t) {
        String sql = "UPDATE Tarifa SET fechaFin = ?, monto = ? WHERE fechaInicio = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(t.getFechaFin(), t.getMonto(), t.getFechaInicio()));
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


    public static Tarifa conseguirTarifa(LocalDate pFechaInicio) {
        String sql = "SELECT fechaInicio, fechaFin, monto FROM Tarifa WHERE fechaInicio = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pFechaInicio));

        List<List<Object>> resultado = null;
        try {
            resultado = conexion.seleccion(sql, parametros);
            System.out.println("Resultado de la consulta: " + resultado);
            if (resultado.isEmpty()) {
                return null;
            }


            LocalDate fechaInicio = LocalDate.parse(String.valueOf(resultado.get(0).get(0)));
            LocalDate fechaFin = LocalDate.parse(String.valueOf(resultado.get(0).get(1)));
            int monto = (int) resultado.get(0).get(2);

            return new Tarifa(fechaInicio, fechaFin, monto);
        } catch (AppSQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }



    public static ArrayList<Tarifa> listarTarifas() {
        String sql = "SELECT * FROM Tarifa";
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

        ArrayList<Tarifa> tarifas = new ArrayList<>();
        for (List<Object> registro : registros) {
            LocalDate fechaInicio = LocalDate.parse(String.valueOf(registro.get(1)));
            LocalDate fechaFin = LocalDate.parse(String.valueOf(registro.get(2)));
            int monto = (int) registro.get(3);

            tarifas.add(new Tarifa(fechaInicio, fechaFin, monto));
        }
        return tarifas;
    }
}
