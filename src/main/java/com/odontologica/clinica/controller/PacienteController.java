package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<PacienteEntity> salvaPaciente(@RequestBody PacienteEntity pacienteEntity) throws BadRequestException {
        try {
            return ResponseEntity.ok(pacienteService.salvar(pacienteEntity));

        }catch (Exception e) {
            throw new BadRequestException("Não foi possível salvar paciente !");
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterarPaciente(@RequestBody PacienteEntity pacienteEntity) throws BadRequestException {
        return ResponseEntity.ok(pacienteService.alterar(pacienteEntity));
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<PacienteEntity> buscarTodos() {
        return pacienteService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PacienteEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Optional<PacienteEntity> pacienteEntity = pacienteService.buscarPorId(id);
            if (pacienteEntity != null && pacienteEntity.isPresent()) {
                return ResponseEntity.ok(pacienteEntity);
            }
            throw new ResourceNotFoundException("Não foi encontrado o dentista " + id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao buscar o dentista " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            pacienteService.excluir(id);
            return ResponseEntity.ok("Paciente deletado !");
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente com id " + id);
        }

    }
}
