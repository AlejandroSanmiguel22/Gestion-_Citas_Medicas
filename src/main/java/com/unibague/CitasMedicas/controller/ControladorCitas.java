package com.unibague.CitasMedicas.controller;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.services.CitaGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")

public class ControladorCitas {

    @Autowired
    private CitaGeneralService citaGeneralService;

    @RequestMapping(value = "/healthcheck")
    public String healthCheck(){
        return "Service status fine!";
    }

    @PostMapping("/crear")
    public ResponseEntity<CitaGeneral> crearCita(@RequestBody CitaGeneral citaGeneral) {
        CitaGeneral nuevaCita = citaGeneralService.crearCitaGeneral(citaGeneral);
        return ResponseEntity.ok().body(nuevaCita);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<CitaGeneral> obtenerCita(@PathVariable String id) {
        CitaGeneral cita = citaGeneralService.obtenerCitaGeneral(id);
        if (cita != null) {
            return ResponseEntity.ok().body(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CitaGeneral>> listarCitas() {
        List<CitaGeneral> citas = citaGeneralService.obtenerTodasCitasGenerales();
        return ResponseEntity.ok().body(citas);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CitaGeneral> actualizarCita(@PathVariable String id, @RequestBody CitaGeneral citaGeneral) {
        CitaGeneral citaActualizada = citaGeneralService.actualizarCitaGeneral(id, citaGeneral);
        if (citaActualizada != null) {
            return ResponseEntity.ok().body(citaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable String id) {
        citaGeneralService.eliminarCitaGeneral(id);
        return ResponseEntity.ok().body("Cita eliminada correctamente");
    }
}

