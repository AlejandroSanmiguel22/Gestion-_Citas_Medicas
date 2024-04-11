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
public class CitaEspecialista extends CitaMedica {
    private String especialidad;
    private String nombreEspecialista;
    private ConsultorioEspecializado consultorio;


}

