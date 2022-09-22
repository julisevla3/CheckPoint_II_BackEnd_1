package com.odontologica.clinica;

import com.odontologica.clinica.controller.LinguagemController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)

@WebMvcTest (LinguagemController.class)

class ClinicaApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	void buscarTodasLinguagensAPI() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/linguagens")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	@Test
	void buscarLinguagensPorTipoAPI() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/linguagens/{tipo}", "Java")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}



	@Test
	void buscarLinguagensNaoExistentePorTipoAPI() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/linguagens/{tipo}", "Linguagem C")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}


	@Test
	void contextLoads() {
	}

}
