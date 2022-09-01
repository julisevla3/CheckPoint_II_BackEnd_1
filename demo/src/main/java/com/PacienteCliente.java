package com;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class PacienteCliente {

    public Paciente salvar(Paciente paciente){
        HttpResponse<String> response = null;
        Paciente paciente1 = null;

        try{
            response = Unirest.post("http://localhost:8080/produto/salvar")//vai pegar a url lá do postman da outra aplicação BackEnd
                    .header("Content-Type","application/json")//configurando o json
                    .body(JSonUtil.asJsonString(paciente)).asString();//converti o meu objeto para json
            paciente1 = JSonUtil.objectFromString(Paciente.class,response.getBody());
            //vai fazer a conversão de json para o objeto, passo a classe e o que vai ser convertido

        }catch(Exception e){
            e.printStackTrace();
        }
        return paciente1;
    }

    public Paciente buscarPorId(Integer id){
        HttpResponse<String> response = null;
        Paciente produto1 = null;

        try{
            response = Unirest.get("http://localhost:8080/produto/todos").asString();//vai pegar a url lá do postman da outra aplicação BackEnd

            produto1 = JSonUtil.objectFromString(Paciente.class,response.getBody());
            //vai fazer a conversão de json para o objeto, passo a classe e o que vai ser convertido

        }catch(Exception e){
            e.printStackTrace();
        }
        return produto1;
    }

    public Paciente alterar(Paciente produto){
        HttpResponse<String> response = null;
        Paciente produto1 = null;

        try{
            response = Unirest.put("http://localhost:8080/produto/alterar")//vai pegar a url lá do postman da outra aplicação BackEnd
                    .header("Content-Type","application/json")//configurando o json
                    .body(JSonUtil.asJsonString(produto)).asString();//converti o meu objeto para json
            produto1 = JSonUtil.objectFromString(Paciente.class,response.getBody());
            //vai fazer a conversão de json para o objeto, passo a classe e o que vai ser convertido

        }catch(Exception e){
            e.printStackTrace();
        }
        return produto1;
    }

    public String excluir(Integer id){
        HttpResponse<String> response = null;
        Paciente produto1 = null;

        try{
            response = Unirest.delete("http://localhost:8080/produto/"+id).asString();//vai pegar a url lá do postman da outra aplicação BackEnd



        }catch(Exception e){
            e.printStackTrace();
        }
        return response != null ? String.valueOf(response.getStatus()): null;
    }


}
