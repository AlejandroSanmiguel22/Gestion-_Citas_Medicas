package com.unibague.CitasMedicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hospital {

    private static Hospital hospital;
    private String nombre;
    private String nit;

}
