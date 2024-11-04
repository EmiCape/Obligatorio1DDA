package Persistencia;

import Dominio.Huesped;
import Utils.AppSQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PHuesped {

    private static Conexion conexion = new Conexion();

    public static boolean agregarHuesped(Huesped pHuesped) {
        String sql = "INSERT INTO Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, pais) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pHuesped.getIdHuesped(), pHuesped.getNombre(), pHuesped.getApellidoPaterno(), pHuesped.getApellidoMaterno(), pHuesped.getTipoDocumento(), pHuesped.getNumeroDocumento(), pHuesped.getFechaNacimiento(), pHuesped.getTelefono(), pHuesped.getPais()));

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

    public static boolean eliminarHuesped(int pId) {
        String sql = "DELETE FROM Huesped WHERE idHuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pId));

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

    public static boolean modificarHuesped(Huesped h) {
        String sql = "UPDATE Huesped SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, tipoDocumento=?, numeroDocumento=?, fechaNacimiento=?, telefono=?, pais=? WHERE idHuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(h.getNombre(), h.getApellidoPaterno(), h.getApellidoMaterno(), h.getTipoDocumento(), h.getNumeroDocumento(), h.getFechaNacimiento(), h.getTelefono(), h.getPais(), h.getIdHuesped()));

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

    public static Huesped conseguirHuesped(int pId) {
        String sql = "SELECT idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, pais FROM Huesped WHERE idHuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pId));

        try {
            List<List<Object>> resultado = conexion.seleccion(sql, parametros);
            if (resultado.isEmpty()) {
                return null;
            }

            int idHuesped = (int) resultado.get(0).get(0);
            String nombre = String.valueOf(resultado.get(0).get(1));
            String apellidoPaterno = String.valueOf(resultado.get(0).get(2));
            String apellidoMaterno = String.valueOf(resultado.get(0).get(3));
            String tipoDocumento = String.valueOf(resultado.get(0).get(4));
            String numeroDocumento = String.valueOf(resultado.get(0).get(5));
            LocalDate fechaNacimiento = LocalDate.parse(String.valueOf(resultado.get(0).get(6)));
            String telefono = String.valueOf(resultado.get(0).get(7));
            String pais = String.valueOf(resultado.get(0).get(8));

            return new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, pais);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Huesped> listarHuespedes() {
        String sql = "SELECT * FROM Huesped";
        List<List<Object>> registros = null;
        ArrayList<Huesped> huespedes = new ArrayList<>();

        try {
            registros = conexion.seleccion(sql, null);
            for (List<Object> registro : registros) {
                int idHuesped = (int) registro.get(0);
                String nombre = String.valueOf(registro.get(1));
                String apellidoPaterno = String.valueOf(registro.get(2));
                String apellidoMaterno = String.valueOf(registro.get(3));
                String tipoDocumento = String.valueOf(registro.get(4));
                String numeroDocumento = String.valueOf(registro.get(5));
                LocalDate fechaNacimiento = LocalDate.parse(String.valueOf(registro.get(6)));
                String telefono = String.valueOf(registro.get(7));
                String pais = String.valueOf(registro.get(8));

                huespedes.add(new Huesped(idHuesped, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, pais));
            }
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return huespedes;
    }
}
