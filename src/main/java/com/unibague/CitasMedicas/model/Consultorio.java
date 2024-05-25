package com.unibague.CitasMedicas.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "consultorio")
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CitaGeneral> citas;
    // Getters y setters si no usas Lombok
}



