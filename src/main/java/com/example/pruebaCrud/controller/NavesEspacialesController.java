package com.example.pruebaCrud.controller;

import com.example.pruebaCrud.exceptions.NaveNotFoundException;
import com.example.pruebaCrud.model.NaveEspacial;
import com.example.pruebaCrud.service.NaveEspacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/naves-espaciales")
public class NavesEspacialesController {
    @Autowired
    private NaveEspacialService naveEspacialService;

    @GetMapping
    public List<NaveEspacial> consultaTodasLasNaves() {
        return (List<NaveEspacial>) naveEspacialService.obtenerNavesConPaginacion(0,10);
    }

    @GetMapping("/{id}")
    public Optional<NaveEspacial> consultaNavePorId(@PathVariable Long id) {
        try {
            return naveEspacialService.obtenerNaveEspacialId(id);
        } catch (NaveNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al buscar nave por id", e);
        }
    }

    @GetMapping("/buscar-por-nombre")
    public List<NaveEspacial> consultaNavesPorNombre(@RequestParam String valor) {
        try {
            return naveEspacialService.obtenerNavesPorNombreContent(valor);
        } catch (NaveNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al buscar naves por nombre", e);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crearNave(@RequestBody NaveEspacial nuevaNave) {
        try {
            NaveEspacial naveGuardada = naveEspacialService.guardarNuevaNave(nuevaNave);
            String mensajeExito = "Nave creada exitosamente. Detalles: " + naveGuardada.toString();
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeExito);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarNave(@PathVariable Long id, @RequestBody NaveEspacial naveModificada) {
        try {
            NaveEspacial naveActualizada = naveEspacialService.modificarNaveExistente(id, naveModificada);
            return ResponseEntity.ok(naveActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarNave(@PathVariable Long id) {
        try {
            NaveEspacial naveEliminada = naveEspacialService.borrarNavePorId(id);
            return ResponseEntity.ok("Nave con ID " + id + " eliminada correctamente. Información: " + naveEliminada.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
