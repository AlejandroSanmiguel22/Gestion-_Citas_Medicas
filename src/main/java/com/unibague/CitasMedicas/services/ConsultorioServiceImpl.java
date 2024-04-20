package com.unibague.CitasMedicas.services;


import com.unibague.CitasMedicas.model.Consultorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    private final List<Consultorio> consultorios = new ArrayList<>();

    @Override
    public Consultorio crearConsultorio(Consultorio consultorio) {
        consultorios.add(consultorio);
        return consultorio;
    }

    @Override
    public Consultorio obtenerConsultorioPorId(String id) {
        for (Consultorio consultorio : consultorios) {
            if (consultorio.getId().equals(id)) {
                return consultorio;
            }
        }
        return null;
    }

    @Override
    public List<Consultorio> obtenerTodosConsultorios() {
        return new ArrayList<>(consultorios);
    }

    @Override
    public Consultorio actualizarConsultorio(String id, Consultorio consultorio) {
        for (int i = 0; i < consultorios.size(); i++) {
            Consultorio c = consultorios.get(i);
            if (c.getId().equals(id)) {
                consultorio.setId(id);
                consultorios.set(i, consultorio);
                return consultorio;
            }
        }
        return null;
    }

    @Override
    public void eliminarConsultorio(String id) {
        consultorios.removeIf(consultorio -> consultorio.getId().equals(id));
    }
}





