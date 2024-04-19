package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CitaGeneralServiceImpl implements CitaGeneralService {

    private List<CitaGeneral> citas = new ArrayList<>();

    @Override
    public CitaGeneral crearCitaGeneral(CitaGeneral citaGeneral) {
        citas.add(citaGeneral);
        return citaGeneral;
    }

    @Override
    public List<CitaGeneral> filtrarCitasGenerales(String id, String nombre, Double costoMinimo, Double costoMaximo, String tipo) {
        List<CitaGeneral> citasFiltradas = new ArrayList<>();
        List<CitaGeneral> todasCitas = obtenerTodasCitasGenerales();
        for (CitaGeneral cita : todasCitas) {
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
        // Iterate through the citas list
        for (CitaGeneral cita : citas) {
            // Check if the cita's ID (or a unique identifier) matches the provided id
            if (cita.getNumeroIdentificacion().equals(id)) {
                // If a match is found, return the CitaGeneral object
                return cita;
            }
        }

        // If no match is found, return null
        return null;
    }
}


