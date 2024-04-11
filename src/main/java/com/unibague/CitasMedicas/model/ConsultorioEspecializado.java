package com.unibague.CitasMedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultorioEspecializado {

    private String numeroConsultorio;
    private String seccion;

}
