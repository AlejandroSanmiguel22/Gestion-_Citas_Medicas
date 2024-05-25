package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.Consultorio;

import java.util.List;

public interface ConsultorioService {
    Consultorio crearConsultorio(Consultorio consultorio);

    Consultorio obtenerConsultorioPorId(Long id);

    List<Consultorio> obtenerTodosConsultorios();

    Consultorio actualizarConsultorio(Long id, Consultorio consultorio);

    void eliminarConsultorio(Long id);
}
