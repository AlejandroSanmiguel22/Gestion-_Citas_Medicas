package com.unibague.CitasMedicas.controller;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.model.Consultorio;
import com.unibague.CitasMedicas.services.CitaGeneralService;
import com.unibague.CitasMedicas.services.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultorios")
@CrossOrigin(origins = "*")
public class ControladorConsultorio {

    @Autowired
    private ConsultorioService consultorioService;
    @Autowired
    private CitaGeneralService citaGeneralService;

    @PostMapping
    public ResponseEntity<Consultorio> crearConsultorio(@RequestBody Consultorio consultorio) {
        Consultorio nuevoConsultorio = consultorioService.crearConsultorio(consultorio);
        return ResponseEntity.ok().body(nuevoConsultorio);
    }

    @PostMapping("/{idConsultorio}/asignar-cita")
    public ResponseEntity<?> asignarCitaAConsultorio(@PathVariable String idConsultorio, @RequestParam String idCita) {
        Consultorio consultorio = consultorioService.obtenerConsultorioPorId(idConsultorio);
        CitaGeneral cita = citaGeneralService.obtenerCitaGeneralPorId(idCita);

        if (consultorio != null && cita != null) {
            if (consultorio.getCitas() == null) {
                consultorio.setCitas(new ArrayList<>());
            }

            // Verificar si la cita ya está asignada a algún consultorio
            if (cita.getIdConsultorio() != null) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "La cita ya está asignada a otro consultorio.");
                return ResponseEntity.badRequest().body(responseBody);
            }

            consultorio.getCitas().add(cita);
            cita.setIdConsultorio(idConsultorio); // Asignar el consultorio a la cita

            consultorioService.actualizarConsultorio(idConsultorio, consultorio);

            return ResponseEntity.ok().body(consultorio);
        } else {
            if (consultorio == null) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "El consultorio especificado no existe");
                return ResponseEntity.notFound().build();
            } else {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "La cita especificada no existe");
                return ResponseEntity.badRequest().body(responseBody);
            }
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> obtenerConsultorioPorId(@PathVariable String id) {
        Consultorio consultorio = consultorioService.obtenerConsultorioPorId(id);
        if (consultorio != null) {
            return ResponseEntity.ok().body(consultorio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Consultorio>> obtenerTodosConsultorios() {
        List<Consultorio> consultorios = consultorioService.obtenerTodosConsultorios();
        return ResponseEntity.ok().body(consultorios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> actualizarConsultorio(@PathVariable String id, @RequestBody Consultorio consultorioActualizado) {
        Consultorio consultorioExistente = consultorioService.obtenerConsultorioPorId(id);
        if (consultorioExistente != null) {
            consultorioActualizado.setCitas(consultorioExistente.getCitas());
            Consultorio consultorioActualizadoFinal = consultorioService.actualizarConsultorio(id, consultorioActualizado);
            return ResponseEntity.ok().body(consultorioActualizadoFinal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarConsultorio(@PathVariable String id) {
        consultorioService.eliminarConsultorio(id);
        return ResponseEntity.ok().body("Consultorio eliminado correctamente");
    }
}

