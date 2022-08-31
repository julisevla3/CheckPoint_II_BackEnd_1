package com;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ProdutoClienteControler {
    private PacienteCliente pacienteCliente = new PacienteCliente();

    @PatchMapping("/salvar")
    public Paciente salvar ("RequestBody Paciente paciente "){
        return pacienteCliente.salvar(paciente);
    }


    @GetMapping Mapping("/todos")
    public Paciente buscarTodos (@RequestBody Integer id ){
        return pacienteCliente.salvar(paciente);
    }

    @PutMapping Mapping("/alterar")
    public Paciente alterar (@RequestBody Paciente paciente ){
        return pacienteCliente.alterar(paciente);
    }


    @DeleteMapping Mapping("/excluir")
    public String excluir (@PathVariable Integer id){
        return pacienteCliente.excluir(paciente);
    }

}
