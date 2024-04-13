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
        ResponseEntity<CitaGeneral> response = obtenerCita(id);

        if (response.getStatusCode() == HttpStatus.OK) {
            CitaGeneral citaActualizada = citaGeneralService.actualizarCitaGeneral(id, citaGeneral);
            return ResponseEntity.ok().body(citaActualizada);
        } else {
            return response;
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable String id) {
        ResponseEntity<CitaGeneral> response = obtenerCita(id);
        if (response.getStatusCode() == HttpStatus.OK) {
            citaGeneralService.eliminarCitaGeneral(id);
            return ResponseEntity.ok().body("Cita eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cita no se encontró");
        }
    }


}

