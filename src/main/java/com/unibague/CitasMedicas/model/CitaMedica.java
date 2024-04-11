package com.unibague.CitasMedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CitaMedica {
    private String  numeroIdentificacion;
    private String nombrePaciente;
    private LocalDate fecha;
    private  double costo;
    private  String  tipoCita;
}
