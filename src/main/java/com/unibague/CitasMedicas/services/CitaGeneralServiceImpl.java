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
    public CitaGeneral obtenerCitaGeneral(String numeroIdentificacion) {
        for (CitaGeneral cita : citas) {
            if (cita.getNumeroIdentificacion().equals(numeroIdentificacion)) {
                return cita;
            }
        }
        return null;
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
}


