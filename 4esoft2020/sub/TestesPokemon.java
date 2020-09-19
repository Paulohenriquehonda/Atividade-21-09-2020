package App;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest
@AutoConfigureMockMvc
class TestesComApiDePokemon {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PokemonController controller;
	
	@MockBean
	private PokemonService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	

	@Test
	void testandoGetByIdComDadoInexistente() throws Exception {
		when(service.getById("9000")).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/api/pokemons/9000")).andExpect(status().isNotFound());
	}
	
	@Test
	void testandoGetByIdComDadoExistente() throws Exception {
		Pokemon existente = new Pokemon("151", "Mew", 30);		
		when(service.getById("151")).thenReturn(existente);
		
		mockMvc.perform(get("/api/pokemons/151"))
		.andExpect(jsonPath("$.id").value("151"))
		.andExpect(jsonPath("$.nome").value("Mew"))
		.andExpect(jsonPath("$.level").value(30))
		.andExpect(status().isOk());
	}
	
	@Test
	void testandoGetAll() throws Exception {
		Pokemon a = new Pokemon("1", "bulbasaur", 12);		
		Pokemon b = new Pokemon("4", "charmander", 10);		
		Pokemon c = new Pokemon("7", "squirtle", 5);		
		when(service.getAll()).thenReturn(Arrays.asList(a,b,c));
		
		mockMvc.perform(get("/api/pokemons"))
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$.[0].id").value("1"))
		.andExpect(jsonPath("$.[1].id").value("4"))
		.andExpect(jsonPath("$.[2].id").value("7"))
		.andExpect(jsonPath("$.[0].nome").value("bulbasaur"))
		.andExpect(jsonPath("$.[1].nome").value("charmander"))
		.andExpect(jsonPath("$.[2].nome").value("squirtle"))
		.andExpect(jsonPath("$.[0].level").value("12"))
		.andExpect(jsonPath("$.[1].level").value("10"))
		.andExpect(jsonPath("$.[2].level").value("5"))
		.andExpect(status().isOk());
	}
	@Test
	void testandoPost() throws Exception {
		when(service.save(ArgumentMatchers.any(Pokemon.class))).thenReturn("99");
		
		Map<String, String> pokemon  = new HashMap<String, String>() {{
		    put("id", "100");
		    put("nome", "voltorb");
		    put("level", "18");
		}};
		
		String pokemonComoJson = objectMapper.writeValueAsString(pokemon);
		
		mockMvc.perform(post("/api/pokemons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pokemonComoJson))
		.andExpect(status().isCreated())
		.andExpect(content().string("99"));
	}
	

}







