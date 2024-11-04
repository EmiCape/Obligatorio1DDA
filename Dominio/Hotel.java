package Dominio;

import Persistencia.PHabitacion;

import java.util.ArrayList;

public class Hotel {
    private int idHotel;
    private String nombre;
    private String ciudad;
    private String pais;
    private int estrellas;
    private String direccion;
    private String zona;


    public Hotel(int idHotel, String nombre, String ciudad, String pais, int estrellas, String direccion, String zona) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.estrellas = estrellas;
        this.direccion = direccion;
        this.zona = zona;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", estrellas=" + estrellas +
                ", direccion='" + direccion + '\'' +
                ", zona='" + zona + '\'' +
                '}';
    }

    public static void verHabitaciones(int pId){
        ArrayList<Habitacion> habitaciones= PHabitacion.listarHabitaciones();
        ArrayList<Habitacion> habitacionHotel= new ArrayList<>();

        for(Habitacion h : habitaciones){
            if(h.getHotel().idHotel==pId){
                habitacionHotel.add(h);
            }
        }
        for(Habitacion h: habitacionHotel){
            System.out.println(h.toString());
        }

    }
}

