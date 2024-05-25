package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.model.Consultorio;
import com.unibague.CitasMedicas.repository.CitaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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
    public List<CitaGeneral> filtrarCitasGenerales(Long id, String nombre, Double costoMinimo, Double costoMaximo, String tipo) {
        List<CitaGeneral> citas = citaGeneralRepository.findAll();

        if (id != null) {
            citas = citas.stream()
                    .filter(cita -> cita.getNumeroIdentificacion().equals(id))
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
    public CitaGeneral actualizarCitaGeneral(Long numeroIdentificacion, CitaGeneral citaGeneral) {
        CitaGeneral citaExistente = citaGeneralRepository.findById(numeroIdentificacion).orElse(null);
        if (citaExistente != null) {
            // Copiar los valores actualizados a la cita existente
            citaExistente.setNombrePaciente(citaGeneral.getNombrePaciente());
            citaExistente.setFecha(citaGeneral.getFecha());
            citaExistente.setCosto(citaGeneral.getCosto());
            citaExistente.setTipoCita(citaGeneral.getTipoCita());
            citaExistente.setNombreGeneralista(citaGeneral.getNombreGeneralista());
            citaExistente.setObservacion(citaGeneral.getObservacion());
            citaExistente.setConsultorio(citaGeneral.getConsultorio());

            // Guardar la cita existente actualizada en la base de datos
            return citaGeneralRepository.save(citaExistente);
        }
        return null;
    }

    @Override
    public void eliminarCitaGeneral(Long numeroIdentificacion) {
        citaGeneralRepository.deleteById(numeroIdentificacion);
    }

    @Override
    public CitaGeneral obtenerCitaGeneralPorId(Long numeroIdentificacion) {
        return citaGeneralRepository.findById(numeroIdentificacion).orElse(null);
    }

    @Override
    public void asignarConsultorioACita(Long idCita, Long idConsultorio) {
        CitaGeneral cita = obtenerCitaGeneralPorId(idCita);
        if (cita != null) {
            Consultorio consultorio = new Consultorio();
            consultorio.setId(idConsultorio);
            cita.setConsultorio(consultorio);
            citaGeneralRepository.save(cita);
        }
    }

    @Override
    public List<CitaGeneral> obtenerCitasPorConsultorio(Long idConsultorio) {
        // Implementa la lógica para obtener citas por consultorio utilizando métodos del repositorio si es necesario
        return citaGeneralRepository.findByConsultorioId(idConsultorio);
    }
}

