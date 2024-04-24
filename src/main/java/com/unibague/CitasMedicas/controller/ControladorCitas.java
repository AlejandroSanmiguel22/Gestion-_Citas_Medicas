package com.unibague.CitasMedicas.controller;

import com.unibague.CitasMedicas.model.CitaGeneral;
import com.unibague.CitasMedicas.model.Consultorio;
import com.unibague.CitasMedicas.services.CitaGeneralService;
import com.unibague.CitasMedicas.services.ConsultorioService;
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
    @Autowired
    private ConsultorioService consultorioService;
    @RequestMapping(value = "/healthcheck")
    public String healthCheck(){
        return "Service status fine!";
    }

    @PostMapping
    public ResponseEntity<CitaGeneral> crearCita(@RequestBody CitaGeneral citaGeneral) {
        // Verificar si se proporcionó un ID de consultorio
        if (citaGeneral.getIdConsultorio() != null) {
            // Asignar el consultorio a la cita
            String idConsultorio = citaGeneral.getIdConsultorio();
            String idCita = citaGeneral.getNumeroIdentificacion();
            citaGeneralService.asignarConsultorioACita(idCita, idConsultorio);
        }

        // Crear la cita general
        CitaGeneral nuevaCita = citaGeneralService.crearCitaGeneral(citaGeneral);

        // Devolver la respuesta con la nueva cita
        return ResponseEntity.ok().body(nuevaCita);
    }




    @GetMapping
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


    @GetMapping("/todas-las-citas")
    public ResponseEntity<List<CitaGeneral>> listarCitas() {
        List<CitaGeneral> citas = citaGeneralService.obtenerTodasCitasGenerales();
        return ResponseEntity.ok().body(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaGeneral> actualizarCita(@PathVariable String id, @RequestBody CitaGeneral citaGeneral) {
        CitaGeneral citaActualizada = citaGeneralService.actualizarCitaGeneral(id, citaGeneral);
        if (citaActualizada != null) {
            return ResponseEntity.ok().body(citaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCita(@PathVariable String id) {
        // Obtener la cita que se va a eliminar
        CitaGeneral cita = citaGeneralService.obtenerCitaGeneralPorId(id);

        if (cita != null) {
            // Verificar si la cita tiene un consultorio asignado
            String idConsultorio = cita.getIdConsultorio();
            if (idConsultorio != null) {
                // Obtener el consultorio correspondiente
                Consultorio consultorio = consultorioService.obtenerConsultorioPorId(idConsultorio);

                if (consultorio != null) {
                    // Eliminar la cita del consultorio
                    consultorio.getCitas().removeIf(c -> c.getNumeroIdentificacion().equals(id));
                    // Actualizar el consultorio en el servicio
                    consultorioService.actualizarConsultorio(idConsultorio, consultorio);
                }
            }

            // Eliminar la cita de la lista de citas generales
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

