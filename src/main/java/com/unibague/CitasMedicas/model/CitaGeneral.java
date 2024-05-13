package com.unibague.CitasMedicas.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "cita_general")
public class CitaGeneral extends CitaMedica {
    private String nombreGeneralista;
    private String observacion;
    private String idConsultorio;


    public CitaGeneral() {
    }

    public CitaGeneral(String numeroIdentificacion, String nombrePaciente, LocalDate fecha, double costo, String tipoCita, String nombreGeneralista, String observacion, String idConsultorio) {
        super(numeroIdentificacion, nombrePaciente, fecha, costo, tipoCita);
        this.nombreGeneralista = nombreGeneralista;
        this.observacion = observacion;
        this.idConsultorio = idConsultorio;
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

    public String getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(String idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
}
