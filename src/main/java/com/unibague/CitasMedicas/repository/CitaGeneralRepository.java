package com.unibague.CitasMedicas.repository;

import com.unibague.CitasMedicas.model.CitaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaGeneralRepository extends JpaRepository<CitaGeneral, String> {
    CitaGeneral findByNumeroIdentificacion(String numeroIdentificacion);
    @Query("SELECT  FROM cita_general cg  WHERE nombre_paciente = :nombre AND tipo_cita  = :tipo AND costo BETWEEN :costoMinimo AND :costoMaximo AND numero_identificacion = :id")
    List<CitaGeneral> findByNombreTipoCostoId(String id, String nombre, String tipo, Double costoMinimo, Double costoMaximo);

}

