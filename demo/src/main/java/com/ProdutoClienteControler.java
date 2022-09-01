package com;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ProdutoClienteControler {
    private PacienteCliente pacienteCliente = new PacienteCliente();

    @PatchMapping("/salvar")
    public Paciente salvar(@RequestBody Paciente paciente ){
        return pacienteCliente.salvar(paciente);
    }


    @GetMapping ("/todos")
    public Paciente buscarTodos (@RequestBody Integer id ){
        return pacienteCliente.buscarPorId(id);
    }

    @PutMapping ("/alterar")
    public Paciente alterar (@RequestBody Paciente paciente ){

        return pacienteCliente.alterar(paciente);
    }


    @DeleteMapping ("/excluir")
    public String excluir (@PathVariable Integer id){
        return pacienteCliente.excluir(id);
    }

}
