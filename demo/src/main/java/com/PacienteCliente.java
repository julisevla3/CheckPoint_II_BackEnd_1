package com;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.security.PublicKey;

public class PacienteCliente {
    public Paciente salvar(Paciente paciente){
        HttpResponse<String> respose = null;
        Paciente paciente1 = null

        try{
            //Vai pegar a url lá do Postman da outra aplicaçao backend
            HttpResponse<String> response = Unirest.post("http://localhost:8080/paciente/salvar")

                    //Configurando o meu json
                    .header("Content-Type", "application/json")

                    //Converteu o objeto para Json
                    .body(JSonUtil.asJsonString(paciente)).asString();

            //Faz a conversão de Json para objeto, passando a classe e o que vai ser convertido
            paciente1 = JSonUtil.objectFromString(Paciente.class, respose.getBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return paciente1;
    }
    Public Paciente buscarPorId( Integer id){

        public Paciente salvar(Paciente paciente)HttpResponse<String> response;
        {
            HttpResponse<String> respose = null;
            Paciente paciente1 = null

            try{
                //Vai pegar a url lá do Postman da outra aplicaçao backend
                response = Unirest.get("http://localhost:8080/paciente/todos").asString();



                //Faz a conversão de Json para objeto, passando a classe e o que vai ser convertido
                paciente1 = JSonUtil.objectFromString(Paciente.class, respose.getBody);
            }catch (Exception e){
                e.printStackTrace();
            }
            return paciente1;

    }

    public Paciente alterar (Paciente paciente){
            public Paciente put(Paciente paciente){
                HttpResponse<String> respose = null;
                Paciente paciente1 = null

                try{
                    //Vai pegar a url lá do Postman da outra aplicaçao backend
                    response = Unirest.post("http://localhost:8080/paciente/alterar")

                            //Configurando o meu json
                            .header("Content-Type", "application/json")

                            //Converteu o objeto para Json
                            .body(JSonUtil.asJsonString(paciente)).asString();

                    //Faz a conversão de Json para objeto, passando a classe e o que vai ser convertido
                    paciente1 = JSonUtil.objectFromString(Paciente.class, respose.getBody);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return paciente1;
            }
        }

        public Strign excluir (Integer id){
            public Paciente salvar(Paciente paciente){
                HttpResponse<String> respose = null;
                Paciente paciente1 = null

                try{
                    //Vai pegar a url lá do Postman da outra aplicaçao backend
                    response = Unirest.delete("http://localhost:8080/paciente/"+id).asString();




                }catch (Exception e){
                    e.printStackTrace();
                }
                return response != null ? String.valueOf(response.getStatus(): null);
        }
}
