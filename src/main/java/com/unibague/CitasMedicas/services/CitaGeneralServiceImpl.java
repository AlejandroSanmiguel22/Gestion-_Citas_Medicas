package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.interfaz.CitaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CitaGeneralServiceImpl implements CitaGeneralService {

    @Autowired
    private CitaGeneralRepository citaGeneralRepository;

    @Override
    public CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral) {
        return citaGeneralRepository.save(citaGeneral);
    }

    @Override
    public List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, Double costoMinimo, Double costoMaximo, String tipo) {
        if (nombre != null && tipo != null) {
            return citaGeneralRepository.findAll();
        } else {
            return Collections.emptyList(); // Devuelve una lista vacía si los parámetros son nulos
        }
    }

    @Override
    public List<CitaGeneral> obtenerTodasCitasGenerales() {
        return citaGeneralRepository.findAll();
    }

    @Override
    public CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral) {
        CitaGeneral citaExistente = obtenerCitaGeneralPorId(numeroIdentificacion);
        if (citaExistente != null) {
            // Copiar los valores actualizados a la cita existente
            citaExistente.setNombrePaciente(citaGeneral.getNombrePaciente());
            citaExistente.setFecha(citaGeneral.getFecha());
            citaExistente.setCosto(citaGeneral.getCosto());
            citaExistente.setTipoCita(citaGeneral.getTipoCita());
            citaExistente.setNombreGeneralista(citaGeneral.getNombreGeneralista());
            citaExistente.setObservacion(citaGeneral.getObservacion());
            citaExistente.setIdConsultorio(citaGeneral.getIdConsultorio());

            // Guardar la cita existente actualizada en la base de datos
            return citaGeneralRepository.save(citaExistente);
        }
        return null;
    }

    @Override
    public void eliminarCitaGeneral(String numeroIdentificacion) {
        citaGeneralRepository.deleteById(numeroIdentificacion);
    }

    @Override
    public CitaGeneral obtenerCitaGeneralPorId(String numeroIdentificacion) {
        return citaGeneralRepository.findById(numeroIdentificacion).orElse(null);
    }

    @Override
    public void asignarConsultorioACita(String idCita, String idConsultorio) {
        CitaGeneral cita = obtenerCitaGeneralPorId(idCita);
        if (cita != null) {
            cita.setIdConsultorio(idConsultorio);
            citaGeneralRepository.save(cita);
        }
    }

    @Override
    public List<CitaGeneral> obtenerCitasPorConsultorio(String idConsultorio) {
        // Implementa la lógica para obtener citas por consultorio utilizando métodos del repositorio si es necesario
        return null;
    }
}
