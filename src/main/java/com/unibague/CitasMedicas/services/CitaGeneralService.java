package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;

import java.util.List;

import java.util.List;

public interface CitaGeneralService {
    CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral);
    List<CitaGeneral> filtrarCitasGenerales(String numeroIdentificacion, String nombrePaciente , Double costoMinimo, Double costoMaximo,String tipoCita);
    List<CitaGeneral> obtenerTodasCitasGenerales();
    CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral);
    void eliminarCitaGeneral(String numeroIdentificacion);
    CitaGeneral obtenerCitaGeneralPorId(String id);
    void asignarConsultorioACita(String idCita, String idConsultorio);
    List<CitaGeneral> obtenerCitasPorConsultorio(String idConsultorio);
}
