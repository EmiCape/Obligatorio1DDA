package Persistencia;

import Dominio.Habitacion;
import Dominio.Hotel;
import Dominio.Huesped;
import Utils.AppSQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHabitacion {

    private static Conexion conexion = new Conexion();

    public static boolean agregarHabitacion(Habitacion pHabitacion) {
        String sql = "INSERT INTO Habitacion(idHabitacion, idHotel, capacidad, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pHabitacion.getIdHabitacion(),
                pHabitacion.getHotel().getIdHotel(),
                pHabitacion.getCapacidad(),
                pHabitacion.isCamaMatrimonial(),
                pHabitacion.isAireAcondicionado(),
                pHabitacion.isBalcon(),
                pHabitacion.isVista(),
                pHabitacion.getAmenities(),
                pHabitacion.isOcupada()
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

    public static boolean modificarHabitacion(Habitacion h) {
        String sql = "UPDATE Habitacion SET idHotel = ?, capacidad = ?, camaMatrimonial = ?, aireAcondicionado = ?, balcon = ?, vista = ?, amenities = ?, ocupada = ? WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getHotel().getIdHotel(),
                h.getCapacidad(),
                h.isCamaMatrimonial(),
                h.isAireAcondicionado(),
                h.isBalcon(),
                h.isVista(),
                h.getAmenities(),
                h.isOcupada(),
                h.getIdHabitacion()
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

    public static Habitacion conseguirHabitacion(int pIdHabitacion) {
        String sql = "SELECT idHabitacion, idHotel, capacidad, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada FROM Habitacion WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHabitacion));

        List<List<Object>> resultado = null;
        try {
            resultado = conexion.seleccion(sql, parametros);
            if (resultado.isEmpty()) {
                return null;
            }

            int idHabitacion = (int) resultado.get(0).get(0);
            int idHotel = (int) resultado.get(0).get(1);
            int capacidad = (int) resultado.get(0).get(2);
            boolean camaMatrimonial = (boolean) resultado.get(0).get(3);
            boolean aireAcondicionado = (boolean) resultado.get(0).get(4);
            boolean balcon = (boolean) resultado.get(0).get(5);
            boolean vista = (boolean) resultado.get(0).get(6);
            String amenities = String.valueOf(resultado.get(0).get(7));
            boolean ocupada = (boolean) resultado.get(0).get(8);


            Hotel hotel = PHotel.conseguirHotel(idHotel);
            return new Habitacion(idHabitacion, hotel, capacidad, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada);
        } catch (AppSQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean eliminarHabitacion(int pIdHabitacion) {
        String sql = "DELETE FROM Habitacion WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHabitacion));
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

    public static ArrayList<Habitacion> listarHabitaciones() {
        String sql = "SELECT * FROM Habitacion";
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

        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        for (List<Object> registro : registros) {
            int idHabitacion = (int) registro.get(0);
            int idHotel = (int) registro.get(1);
            int capacidad = (int) registro.get(2);
            boolean camaMatrimonial = (boolean) registro.get(3);
            boolean aireAcondicionado = (boolean) registro.get(4);
            boolean balcon = (boolean) registro.get(5);
            boolean vista = (boolean) registro.get(6);
            String amenities = String.valueOf(registro.get(7));
            boolean ocupada = (boolean) registro.get(8);
            int idResponsable;
            Huesped hu=null;
            try {
                idResponsable= (int) registro.get(9);
               hu = PHuesped.conseguirHuesped(idResponsable);
            }
            catch (Exception e){}



            Hotel hotel = PHotel.conseguirHotel(idHotel);
            Habitacion h= new Habitacion(idHabitacion, hotel, capacidad, camaMatrimonial, aireAcondicionado, balcon, vista, amenities, ocupada);
            h.setOcupada(ocupada);
            h.setResponsable(hu);
            habitaciones.add(h);

        }
        return habitaciones;
    }

    public static boolean estaOcupada(int pIdHabitacion, int pIdHuesped) {
        String sql = "UPDATE Habitacion SET  ocupada = true, idResponsable= ? WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHuesped, pIdHabitacion));
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
    public static boolean noEstaOcupada(int pIdHabitacion) {
        String sql = "UPDATE Habitacion SET  ocupada = false, idResponsable= null WHERE idHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHabitacion));
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
}
