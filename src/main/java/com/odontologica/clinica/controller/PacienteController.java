package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public PacienteEntity salvaPaciente(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        return service.salvar(pacienteEntity);
    }

    @PutMapping
    public void alterar(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        System.out.println();
        service.alterar(pacienteEntity);
    }

    @GetMapping
    public List<PacienteEntity> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }

    @RequestMapping(value = "/buscarId")
    public PacienteEntity buscarPorId(@RequestParam("id") int id) throws SQLException {
        return service.buscarPorId(id).isEmpty() ? new PacienteEntity() : service.buscarPorId(id).get();
    }


    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException {
        service.excluir(id);
    }
}
