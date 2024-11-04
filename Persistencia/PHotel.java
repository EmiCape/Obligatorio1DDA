package Persistencia;

import Dominio.Hotel;
import Utils.AppSQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHotel {

    private static Conexion conexion = new Conexion();

    public static boolean agregarHotel(Hotel pHotel) {
        String sql = "INSERT INTO Hotel(idHotel, nombre, ciudad, pais, estrellas, direccion, zona) VALUES(?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pHotel.getIdHotel(),
                pHotel.getNombre(),
                pHotel.getCiudad(),
                pHotel.getPais(),
                pHotel.getEstrellas(),
                pHotel.getDireccion(),
                pHotel.getZona()
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

    public static boolean eliminarHotel(int pIdHotel) {
        String sql = "DELETE FROM Hotel WHERE idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHotel));
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

    public static boolean modificarHotel(Hotel h) {
        String sql = "UPDATE Hotel SET nombre = ?, ciudad = ?, pais = ?, estrellas = ?, direccion = ?, zona = ? WHERE idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getNombre(),
                h.getCiudad(),
                h.getPais(),
                h.getEstrellas(),
                h.getDireccion(),
                h.getZona(),
                h.getIdHotel()
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

    public static Hotel conseguirHotel(int pIdHotel) {
        String sql = "SELECT idHotel, nombre, ciudad, pais, estrellas, direccion, zona FROM Hotel WHERE idHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHotel));

        List<List<Object>> resultado = null;
        try {
            resultado = conexion.seleccion(sql, parametros);
            if (resultado.isEmpty()) {
                return null;
            }

            int idHotel = (int) resultado.get(0).get(0);
            String nombre = String.valueOf(resultado.get(0).get(1));
            String ciudad = String.valueOf(resultado.get(0).get(2));
            String pais = String.valueOf(resultado.get(0).get(3));
            int estrellas = (int) resultado.get(0).get(4);
            String direccion = String.valueOf(resultado.get(0).get(5));
            String zona = String.valueOf(resultado.get(0).get(6));

            return new Hotel(idHotel, nombre, ciudad, pais, estrellas, direccion, zona);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ArrayList<Hotel> listarHoteles() {
        String sql = "SELECT * FROM Hotel";
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

        ArrayList<Hotel> hoteles = new ArrayList<>();
        for (List<Object> registro : registros) {
            int idHotel = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String ciudad = String.valueOf(registro.get(2));
            String pais = String.valueOf(registro.get(3));
            int estrellas = (int) registro.get(4);
            String direccion = String.valueOf(registro.get(5));
            String zona = String.valueOf(registro.get(6));

            hoteles.add(new Hotel(idHotel, nombre, ciudad, pais, estrellas, direccion, zona));
        }
        return hoteles;
    }
}
