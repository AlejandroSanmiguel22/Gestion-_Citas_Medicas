package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;

import java.util.List;

import java.util.List;

public interface CitaGeneralService {
    CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral);
    List<CitaGeneral> filtrarCitasGenerales(Long numeroIdentificacion, String nombrePaciente , Double costoMinimo, Double costoMaximo,String tipoCita);
    List<CitaGeneral> obtenerTodasCitasGenerales();
    CitaGeneral actualizarCitaGeneral(Long numeroIdentificacion, CitaGeneral citaGeneral);
    void eliminarCitaGeneral(Long numeroIdentificacion);
    CitaGeneral obtenerCitaGeneralPorId(Long id);
    void asignarConsultorioACita(Long idCita, Long idConsultorio);
    List<CitaGeneral> obtenerCitasPorConsultorio(Long idConsultorio);
}
