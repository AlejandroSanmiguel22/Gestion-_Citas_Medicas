package com.unibague.CitasMedicas.interfaz;

import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaGeneralRepository extends JpaRepository<CitaGeneral, Long> {
}
