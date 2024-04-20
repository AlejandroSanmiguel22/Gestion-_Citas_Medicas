package com.unibague.CitasMedicas.services;

import com.unibague.CitasMedicas.model.Consultorio;

import java.util.List;

public interface ConsultorioService {
    Consultorio crearConsultorio(Consultorio consultorio);

    Consultorio obtenerConsultorioPorId(String id);

    List<Consultorio> obtenerTodosConsultorios();

    Consultorio actualizarConsultorio(String id, Consultorio consultorio);

    void eliminarConsultorio(String id);
}
