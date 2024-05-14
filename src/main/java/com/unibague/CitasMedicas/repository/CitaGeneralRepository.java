package com.unibague.CitasMedicas.repository;

import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaGeneralRepository extends JpaRepository<CitaGeneral, String> {
    CitaGeneral findByNumeroIdentificacion(String numeroIdentificacion);
}

