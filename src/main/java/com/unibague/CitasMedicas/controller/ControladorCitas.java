package com.unibague.CitasMedicas.controller;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.services.CitaGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class ControladorCitas {

    @Autowired
    private CitaGeneralService citaGeneralService;

    @RequestMapping(value = "/healthcheck")
    public String healthCheck() {
        return "Service status fine!";
    }

    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CitaGeneral citaGeneral) {
        try {
            if (citaGeneral.getIdConsultorio() != null) {
                String idConsultorio = citaGeneral.getIdConsultorio();
                String idCita = citaGeneral.getNumeroIdentificacion();
                citaGeneralService.asignarConsultorioACita(idCita, idConsultorio);
            }
            CitaGeneral nuevaCita = citaGeneralService.crearCitaGeneral(citaGeneral);
            return ResponseEntity.ok().body(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la cita: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CitaGeneral>> obtenerCitas(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "costoMinimo", required = false) Double costoMinimo,
            @RequestParam(value = "costoMaximo", required = false) Double costoMaximo,
            @RequestParam(value = "tipo", required = false) String tipo) {

        // Verifica si todos los parámetros son nulos
        if (id == null && nombre == null && tipo == null && costoMinimo == null && costoMaximo == null) {
            // Si todos son nulos, devuelve todas las citas
            List<CitaGeneral> todasLasCitas = citaGeneralService.obtenerTodasCitasGenerales();
            return ResponseEntity.ok().body(todasLasCitas);
        }

        // Filtra las citas basadas en los parámetros proporcionados
        List<CitaGeneral> citasFiltradas = citaGeneralService.filtrarCitasGenerales(id, nombre, tipo, costoMinimo, costoMaximo);

        if (!citasFiltradas.isEmpty()) {
            return ResponseEntity.ok().body(citasFiltradas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/todas-las-citas")
    public ResponseEntity<List<CitaGeneral>> listarCitas() {
        List<CitaGeneral> citas = citaGeneralService.obtenerTodasCitasGenerales();
        return ResponseEntity.ok().body(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaGeneral> actualizarCita(@PathVariable String id, @RequestBody CitaGeneral citaGeneral) {
        CitaGeneral citaExistente = citaGeneralService.obtenerCitaGeneralPorId(id);
        if (citaExistente != null) {
            // Conserva el consultorio asociado
            citaGeneral.setIdConsultorio(citaExistente.getIdConsultorio());
            // Actualiza la cita
            CitaGeneral citaActualizada = citaGeneralService.actualizarCitaGeneral(id, citaGeneral);
            if (citaActualizada != null) {
                return ResponseEntity.ok().body(citaActualizada);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable String id) {
        CitaGeneral cita = citaGeneralService.obtenerCitaGeneralPorId(id);

        if (cita != null) {
            citaGeneralService.eliminarCitaGeneral(id);
            return ResponseEntity.ok().body("Cita eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaGeneral> obtenerCitaPorId(@PathVariable String id) {
        CitaGeneral cita = citaGeneralService.obtenerCitaGeneralPorId(id);
        if (cita != null) {
            return ResponseEntity.ok().body(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
