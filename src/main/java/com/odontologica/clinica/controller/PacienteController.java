package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.PacienteEntity;
import com.odontologica.clinica.service.IClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IClinicaService service;

    @PostMapping
    public PacienteEntity salvaPaciente(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        return (PacienteEntity) service.salvar(pacienteEntity);
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
    public PacienteEntity buscarPorId(@RequestParam("id") Long id) throws SQLException {
        return service.buscarPorId(id).isEmpty() ? new PacienteEntity() : (PacienteEntity) service.buscarPorId(id).get();
    }


    @DeleteMapping
    public void excluir(@RequestParam("id") Long id) throws SQLException {
        service.excluir(id);
    }
}
