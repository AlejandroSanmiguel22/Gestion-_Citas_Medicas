package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.services.ConsultorioService;
import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.model.Consultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
import java.util.ArrayList;

@Service
public class CitaGeneralServiceImpl implements CitaGeneralService {

    private List<CitaGeneral> citas = new ArrayList<>();
    @Autowired
    private ConsultorioService consultorioService;

    @Override
    public CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral) {
        citas.add(citaGeneral);
        return citaGeneral;
    }

    @Override
    public List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, Double costoMinimo, Double costoMaximo, String tipo) {
        List<CitaGeneral> citasFiltradas = new ArrayList<>();
        for (CitaGeneral cita : citas) {
            if ((id == null || cita.getNumeroIdentificacion().equals(id)) &&
                    (nombre == null || cita.getNombrePaciente().equals(nombre)) &&
                    (costoMinimo == null || cita.getCosto() >= costoMinimo) &&
                    (costoMaximo == null || cita.getCosto() <= costoMaximo) &&
                    (tipo == null || cita.getTipoCita().equals(tipo))) {
                citasFiltradas.add(cita);
            }
        }
        return citasFiltradas;
    }

    @Override
    public List<CitaGeneral> obtenerTodasCitasGenerales() {
        // Obtener todas las citas
        List<CitaGeneral> citas = new ArrayList<>(this.citas);

        // Agregar información del consultorio a cada cita
        for (CitaGeneral cita : citas) {
            String idConsultorio = cita.getIdConsultorio();
            if (idConsultorio != null) {
                Consultorio consultorio = consultorioService.obtenerConsultorioPorId(idConsultorio);
                if (consultorio != null) {
                    cita.setIdConsultorio(idConsultorio);
                }
            }
        }

        return citas;
    }

    @Override
    public CitaGeneral actualizarCitaGeneral(String numeroIdentificacion, CitaGeneral citaGeneral) {
        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).getNumeroIdentificacion().equals(numeroIdentificacion)) {
                citas.set(i, citaGeneral);
                return citaGeneral;
            }
        }
        return null;
    }

    @Override
    public void eliminarCitaGeneral(String numeroIdentificacion) {
        citas.removeIf(cita -> cita.getNumeroIdentificacion().equals(numeroIdentificacion));
    }

    @Override
    public CitaGeneral obtenerCitaGeneralPorId(String id) {
        for (CitaGeneral cita : citas) {
            if (cita.getNumeroIdentificacion().equals(id)) {
                return cita;
            }
        }
        return null;
    }

    @Override
    public void asignarConsultorioACita(String idCita, String idConsultorio) {
        CitaGeneral cita = obtenerCitaGeneralPorId(idCita);
        if (cita != null) {
            // Aquí se asigna el ID del consultorio a la cita en lugar del objeto completo del consultorio
            cita.setIdConsultorio(idConsultorio);
        }
    }
    @Override
    public List<CitaGeneral> obtenerCitasPorConsultorio(String idConsultorio) {
        List<CitaGeneral> citasPorConsultorio = new ArrayList<>();
        for (CitaGeneral cita : citas) {
            if (cita.getIdConsultorio() != null && cita.getIdConsultorio().equals(idConsultorio)) {
                citasPorConsultorio.add(cita);
            }
        }
        return citasPorConsultorio;
    }

}



