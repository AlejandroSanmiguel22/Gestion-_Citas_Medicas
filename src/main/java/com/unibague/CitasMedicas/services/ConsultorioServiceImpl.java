package com.unibague.CitasMedicas.services;

import java.util.*;
import java.util.stream.Collectors;

import com.unibague.CitasMedicas.model.Consultorio;
import org.springframework.stereotype.Service;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    private final Map<String, Consultorio> consultorios = new HashMap<>();

    @Override
    public Consultorio crearConsultorio(Consultorio consultorio) {
        consultorios.put(consultorio.getId(), consultorio);
        return consultorio;
    }

    @Override
    public Consultorio obtenerConsultorioPorId(String id) {
        return consultorios.get(id);
    }

    @Override
    public List<Consultorio> obtenerTodosConsultorios() {
        return new ArrayList<>(consultorios.values());
    }

    @Override
    public Consultorio actualizarConsultorio(String id, Consultorio consultorio) {
        if (consultorios.containsKey(id)) {
            consultorio.setId(id);
            consultorios.put(id, consultorio);
            return consultorio;
        }
        return null;
    }

    @Override
    public void eliminarConsultorio(String id) {
        consultorios.remove(id);
    }
}



