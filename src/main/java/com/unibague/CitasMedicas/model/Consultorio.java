package com.unibague.CitasMedicas.model;

import lombok.*;

import java.util.List;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
// cambios xd
public class Consultorio {
    private String id;
    private String nombre;
    private List<CitaGeneral> citas;
}


