package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;

import java.util.List;

public interface CitaGeneralService {
    CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral);
    CitaGeneral obtenerCitaGeneral(String numeroIdentificacion);
    List<CitaGeneral> obtenerTodasCitasGenerales();
    CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral);
    void eliminarCitaGeneral(String numeroIdentificacion);
}