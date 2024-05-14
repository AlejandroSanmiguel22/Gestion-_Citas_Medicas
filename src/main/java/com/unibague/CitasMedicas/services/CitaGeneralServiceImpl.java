package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.repository.CitaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaGeneralServiceImpl implements CitaGeneralService {

    @Autowired
    private CitaGeneralRepository citaGeneralRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral) {
        return citaGeneralRepository.save(citaGeneral);
    }

    @Override
    public List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, Double costoMinimo, Double costoMaximo, String tipo) {
        List<CitaGeneral> citas = citaGeneralRepository.findAll();

        if (id != null && !id.isEmpty()) {
            citas = citas.stream()
                    .filter(cita -> cita.getNumeroIdentificacion().contains(id))
                    .collect(Collectors.toList());
        }
        if (nombre != null && !nombre.isEmpty()) {
            citas = citas.stream()
                    .filter(cita -> cita.getNombrePaciente().contains(nombre))
                    .collect(Collectors.toList());
        }
        if (costoMinimo != null) {
            citas = citas.stream()
                    .filter(cita -> cita.getCosto() >= costoMinimo)
                    .collect(Collectors.toList());
        }
        if (costoMaximo != null) {
            citas = citas.stream()
                    .filter(cita -> cita.getCosto() <= costoMaximo)
                    .collect(Collectors.toList());
        }
        if (tipo != null && !tipo.isEmpty()) {
            citas = citas.stream()
                    .filter(cita -> cita.getTipoCita().contains(tipo))
                    .collect(Collectors.toList());
        }

        return citas;
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
