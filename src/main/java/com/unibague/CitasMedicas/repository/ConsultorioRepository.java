package com.unibague.CitasMedicas.repository;


import com.unibague.CitasMedicas.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {

    @Query("SELECT c FROM Consultorio c WHERE c.nombre LIKE %:nombre%")
    List<Consultorio> findByNombreContaining(@Param("nombre") String nombre);

    @Query("SELECT c FROM Consultorio c JOIN FETCH c.citas WHERE c.id = :id")
    Consultorio findByIdWithCitas(@Param("id") Long id);
}
