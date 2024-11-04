package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Fechas {

    public static LocalDate validarFecha(String pFecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha;

        try {
            fecha = LocalDate.parse(pFecha, formatter);

        } catch (DateTimeParseException e) {

            return null;
        }

        return fecha;
    }
}
