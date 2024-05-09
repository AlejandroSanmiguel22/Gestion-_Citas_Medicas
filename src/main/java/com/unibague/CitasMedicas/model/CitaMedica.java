package com.unibague.CitasMedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class CitaMedica {
    @Id
    private String  numeroIdentificacion;
    private String nombrePaciente;
    private LocalDate fecha;
    private  double costo;
    private  String  tipoCita;
}
