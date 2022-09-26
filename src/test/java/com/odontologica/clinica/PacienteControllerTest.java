package com.odontologica.clinica;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odontologica.clinica.entity.PacienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; // transforma o objeto em json

    @Test
    @DisplayName("Salvando paciente com sucesso")
    public void salvarPacienteTest() throws Exception {
        PacienteEntity paciente = new PacienteEntity("Nicholas", "Rua", "Bairro 22, casa 38", "123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/salvar")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                        .andDo(print())
                        .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Buscando paciente com sucesso")
    void buscarTodosPacientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscando paciente por Id")
	void buscarPacientePorId() throws Exception {
        PacienteEntity paciente = new PacienteEntity(1L,"Edimilson","Braz","Rua das Flores", "32587942",null);

		mockMvc.perform(MockMvcRequestBuilders.get("/paciente/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

    @Test
    @DisplayName("Buscando pacientes não existentes")
    void buscarLinguagensNaoExistentesPorTipoAPI() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/{4}","4")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
