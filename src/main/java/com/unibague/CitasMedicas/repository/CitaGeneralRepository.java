package com.unibague.CitasMedicas.repository;

import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaGeneralRepository extends JpaRepository<CitaGeneral, String> {
    CitaGeneral findByNumeroIdentificacion(String numeroIdentificacion);
}

