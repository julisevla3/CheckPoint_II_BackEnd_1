package com.odontologica.clinica.controller;


import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    DentistaService service;

    @PostMapping
    public DentistaEntity salvaDentista(@RequestBody DentistaEntity dentistaEntity) throws SQLException {
        return service.salvar(dentistaEntity);
    }

    @PutMapping
    public void alterar(@RequestBody DentistaEntity dentistaEntity) throws SQLException {
        System.out.println();
        service.alterar(dentistaEntity);
    }

    @GetMapping
    public List<DentistaEntity> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }

    @RequestMapping(value = "/buscarId")
    public DentistaEntity buscarPorId(@RequestParam("id") int id) throws SQLException {
        return service.buscarPorId(id).isEmpty() ? new DentistaEntity() : service.buscarPorId(id).get();
    }

    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException {
        service.excluir(id);
    }

}
