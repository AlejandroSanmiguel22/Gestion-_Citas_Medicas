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
    @Query("SELECT cg FROM cita_general cg " +
            "WHERE cg.nombrePaciente = :nombre " +
            "AND cg.tipoCita = :tipo " +
            "AND cg.costo BETWEEN :costoMinimo AND :costoMaximo " +
            "AND cg.numeroIdentificacion = :id")
    List<CitaGeneral> findByNombreTipoCostoId(@Param("id") String id,
                                              @Param("nombre") String nombre,
                                              @Param("tipo") String tipo,
                                              @Param("costoMinimo") Double costoMinimo,
                                              @Param("costoMaximo") Double costoMaximo);


}

