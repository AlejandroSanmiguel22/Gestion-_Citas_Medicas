package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.repository.CitaGeneralRepository;
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
    public List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, String tipo, Double costoMinimo, Double costoMaximo) {
        if (id != null && nombre != null && tipo != null && costoMinimo != null && costoMaximo != null) {
            return citaGeneralRepository.findByNombreTipoCostoId(id, nombre, tipo, costoMinimo, costoMaximo);
        } else {
            // Si alguno de los parámetros es null, devuelve todas las citas
            return citaGeneralRepository.findAll();
        }
    }


    @Override
    public List<CitaGeneral> obtenerTodasCitasGenerales() {
        return citaGeneralRepository.findAll();
    }

    @Override
    public CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral) {
        CitaGeneral citaExistente = citaGeneralRepository.findByNumeroIdentificacion(numeroIdentificacion);
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
