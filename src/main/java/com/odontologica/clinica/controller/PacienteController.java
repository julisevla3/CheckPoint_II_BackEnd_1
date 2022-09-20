package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.PacienteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PacienteController {
    private PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/paciente/salvar")
    public ResponseEntity<PacienteEntity> salvaPaciente(@RequestBody PacienteEntity pacienteEntity) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(pacienteService.salvar(pacienteEntity));
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível cadastrar um novo paciente.");

        }
    }

    @PutMapping("/paciente/alterar")
    public ResponseEntity<PacienteEntity> alterarPaciente(@RequestBody PacienteEntity pacienteEntity) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(pacienteService.alterar(pacienteEntity));
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível alterar paciente.");
        }
    }

    @RequestMapping(value = "/pacientes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<PacienteEntity>> buscarTodos() throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(pacienteService.buscarTodos());
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado a lista de pacientes " );
        }
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<Optional<PacienteEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(pacienteService.buscarPorId(id));
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado paciente com id " + id);
        }
    }

    @DeleteMapping("/paciente/delete/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            pacienteService.excluir(id);
            return ResponseEntity.ok("Paciente deletado !");
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente com id " + id);
        }

    }
}
