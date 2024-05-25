package com.unibague.CitasMedicas.services;


import com.unibague.CitasMedicas.model.Consultorio;
import com.unibague.CitasMedicas.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Override
    public Consultorio crearConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    @Override
    public Consultorio obtenerConsultorioPorId(Long id) {
        return consultorioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Consultorio> obtenerTodosConsultorios() {
        return consultorioRepository.findAll();
    }

    @Override
    public Consultorio actualizarConsultorio(Long id, Consultorio consultorioActualizado) {
        Optional<Consultorio> optionalConsultorio = consultorioRepository.findById(id);
        if (optionalConsultorio.isPresent()) {
            Consultorio consultorioExistente = optionalConsultorio.get();
            consultorioExistente.setNombre(consultorioActualizado.getNombre());
            consultorioExistente.setCitas(consultorioActualizado.getCitas());
            return consultorioRepository.save(consultorioExistente);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarConsultorio(Long id) {
        consultorioRepository.deleteById(id);
    }
}





