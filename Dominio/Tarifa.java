package Dominio;


import Persistencia.PTarifa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Tarifa {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int monto;

    public Tarifa(LocalDate fechaInicio, LocalDate fechaFin, int monto) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
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

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", monto=" + monto +
                '}';
    }

    public static int devolverTarifa(LocalDate pFecha) {

        ArrayList<Tarifa> tarifas = PTarifa.listarTarifas();
        for (Tarifa t : tarifas) {
            if ((pFecha.isEqual(t.getFechaInicio()) || pFecha.isAfter(t.getFechaInicio())) &&
                    (pFecha.isEqual(t.getFechaFin()) || pFecha.isBefore(t.getFechaFin()))) {

                return t.getMonto();
            }
        }
        return 3000;
    }
}
