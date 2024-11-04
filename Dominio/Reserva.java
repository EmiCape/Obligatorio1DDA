package Dominio;

import java.time.LocalDate;

public class Reserva {
    private int idReserva;
    private Huesped huesped;
    private Hotel hotel;
    private Habitacion habitacion;
    private int cantidadPersonas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double montoReserva;
    private int montoTotal;
    private boolean pagadaCompletamente;
    private LocalDate fechaRes;
    private String observacion;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int monto) {
        this.montoTotal = monto;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoReserva() {
        return montoReserva;
    }

    public void setMontoReserva(double montoReserva) {
        this.montoReserva = montoReserva;
    }

    public boolean isPagadaCompletamente() {
        return pagadaCompletamente;
    }

    public void setPagadaCompletamente(boolean pagadaCompletamente) {
        this.pagadaCompletamente = pagadaCompletamente;
    }

    public LocalDate getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(LocalDate fechaRes) {
        this.fechaRes = fechaRes;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Reserva(int idReserva, Huesped huesped, Hotel hotel, Habitacion habitacion,
                   int cantidadPersonas, LocalDate fechaInicio, LocalDate fechaFin,
                   double montoReserva,int montoTotal, boolean pagadaCompletamente, LocalDate fechaRes, String observacion) {
        this.idReserva = idReserva;
        this.huesped = huesped;
        this.hotel = hotel;
        this.habitacion = habitacion;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.pagadaCompletamente = pagadaCompletamente;
        this.montoReserva = montoReserva;
        this.montoTotal=montoTotal;
        this.fechaRes = fechaRes;
        this.observacion = observacion;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", huesped=" + huesped.getNombre() +
                ", hotel=" + hotel.getNombre() +
                ", habitacion=" + habitacion.getIdHabitacion() +
                ", cantidadPersonas=" + cantidadPersonas +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", montoReserva=" + montoReserva +
                ", montoTotal=" + montoTotal +
                ", pagadaCompletamente=" + pagadaCompletamente +
                ", fechaRes=" + fechaRes +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
