package com.unibague.CitasMedicas.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cita_general")
public class CitaGeneral extends CitaMedica {

    @Column(name = "nombre_generalista", nullable = false)
    private String nombreGeneralista;

    private String observacion;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;

    private Long idConsultorio;
    public CitaGeneral(Long numeroIdentificacion, String nombrePaciente, LocalDate fecha, double costo, String tipoCita, String nombreGeneralista, String observacion, Consultorio consultorio) {
        super(numeroIdentificacion, nombrePaciente, fecha, costo, tipoCita);
        this.nombreGeneralista = nombreGeneralista;
        this.observacion = observacion;
        this.consultorio = consultorio;
    }

    public String getNombreGeneralista() {
        return nombreGeneralista;
    }

    public void setNombreGeneralista(String nombreGeneralista) {
        this.nombreGeneralista = nombreGeneralista;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Long getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Long idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
}
