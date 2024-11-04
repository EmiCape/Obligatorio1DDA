package Dominio;

public class Habitacion {
    private int idHabitacion;
    private Hotel hotel;
    private int capacidad;
    private boolean camaMatrimonial;
    private boolean aireAcondicionado;
    private boolean balcon;
    private boolean vista;
    private String amenities;
    private boolean ocupada;
    private Huesped responsable;



    public Habitacion(int idHabitacion, Hotel pHotel, int capacidad, boolean camaMatrimonial,
                      boolean aireAcondicionado, boolean balcon, boolean vista, String amenities, boolean ocupada) {
        this.idHabitacion = idHabitacion;
        this.hotel= pHotel;
        this.capacidad = capacidad;
        this.camaMatrimonial = camaMatrimonial;
        this.aireAcondicionado = aireAcondicionado;
        this.balcon = balcon;
        this.vista = vista;
        this.amenities = amenities;
        this.ocupada = false;
        this.responsable = null;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isCamaMatrimonial() {
        return camaMatrimonial;
    }

    public void setCamaMatrimonial(boolean camaMatrimonial) {
        this.camaMatrimonial = camaMatrimonial;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isVista() {
        return vista;
    }

    public void setVista(boolean vista) {
        this.vista = vista;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Huesped getResponsable() {
        return responsable;
    }

    public void setResponsable(Huesped responsable) {
        this.responsable = responsable;
    }


    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + idHabitacion +
                ", capacidad=" + capacidad +
                ", camaMatrimonial=" + (camaMatrimonial ? "Si" : "No") +
                ", aireAcondicionado=" + (aireAcondicionado ? "Si" : "No") +
                ", balcon=" + (balcon ? "Si" : "No") +
                ", vista=" + (vista ? "Si" : "No") +
                ", amenities='" + amenities + '\'' +
                ", ocupada=" + (ocupada ? "Si" : "No") +
                ", Responsable='" +(responsable==null ? "No tiene" : responsable.getNombre()) + '\'' +
                '}';
    }


}

