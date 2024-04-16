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

    @GetMapping("/obtener")
    public ResponseEntity<List<CitaGeneral>> obtenerCitas(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "costoMinimo", required = false, defaultValue = "0") Double costoMinimo,
            @RequestParam(value = "costoMaximo", required = false) Double costoMaximo,
            @RequestParam(value = "tipo", required = false) String tipo) {
        
        List<CitaGeneral> citasFiltradas = citaGeneralService.filtrarCitasGenerales(id, nombre, costoMinimo, costoMaximo, tipo);

        if (!citasFiltradas.isEmpty()) {
            return ResponseEntity.ok().body(citasFiltradas);
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

