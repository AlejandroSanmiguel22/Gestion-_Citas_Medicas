package com.unibague.CitasMedicas.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CitaGeneral extends CitaMedica {
    private String nombreGeneralista;
    private String observacion;

    public CitaGeneral(String numeroIdentificacion, String nombrePaciente, LocalDate fecha, double costo, String tipoCita, String nombreGeneralista, String observacion) {
        super(numeroIdentificacion, nombrePaciente, fecha, costo, tipoCita);
        this.nombreGeneralista = nombreGeneralista;
        this.observacion = observacion;
    }

}
