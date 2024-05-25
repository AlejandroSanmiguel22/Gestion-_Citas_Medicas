package com.unibague.CitasMedicas.repository;


import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaGeneralRepository extends JpaRepository<CitaGeneral, Long> {
    List<CitaGeneral> findByConsultorioId(Long consultorioId);
}

