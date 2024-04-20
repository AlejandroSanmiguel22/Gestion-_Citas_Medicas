package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;

import java.util.List;

import java.util.List;

public interface CitaGeneralService {
    CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral);
    List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, Double costoMinimo, Double costoMaximo, String tipo);
    List<CitaGeneral> obtenerTodasCitasGenerales();
    CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral);
    void eliminarCitaGeneral(String numeroIdentificacion);
    CitaGeneral obtenerCitaGeneralPorId(String id);

    // Métodos relacionados con consultorios
    void asignarConsultorioACita(String idCita, String idConsultorio);
    List<CitaGeneral> obtenerCitasPorConsultorio(String idConsultorio);
}
