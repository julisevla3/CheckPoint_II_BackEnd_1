package com.odontologica.clinica.controller;

import com.odontologica.clinica.entity.DentistaEntity;
import com.odontologica.clinica.exceptions.BadRequestException;
import com.odontologica.clinica.exceptions.ResourceNotFoundException;
import com.odontologica.clinica.service.impl.DentistaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    private DentistaServiceImpl dentistaService;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<DentistaEntity> salvarDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException {
        try {
            return ResponseEntity.ok(dentistaService.salvar(dentistaEntity));
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível salvar dentista !");
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<DentistaEntity> alterarDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException{
        try {
            return ResponseEntity.ok(dentistaService.alterar(dentistaEntity));
        }catch (Exception e) {
            throw new BadRequestException("Não foi possível alterar dentista.");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<DentistaEntity> buscarTodos() {

        return dentistaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DentistaEntity>> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(dentistaService.buscarPorId(id));
        }catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado dentista com id " + id);
        }

    }

    @DeleteMapping("/dentista/delete/{id}")
    public ResponseEntity<String> excluirDentista(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            dentistaService.excluir(id);
            return ResponseEntity.ok("Dentista deletado !");
        }catch (Exception e){
            throw new ResourceNotFoundException("Não foi encontrado o dentista com id " + id);
        }
    }

}