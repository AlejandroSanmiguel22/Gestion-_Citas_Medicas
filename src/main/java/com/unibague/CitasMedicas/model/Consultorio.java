package com.unibague.CitasMedicas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Consultorio {
    private String id;
    private String nombre;
    private List<CitaGeneral> citas;
}

