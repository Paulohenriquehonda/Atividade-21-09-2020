package App.Pokemon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
	@Autowired
	private PokemonService service;
	
		
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
	public List<Pokemon> getAll() {
		return service.getAll();
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	public Pokemon getById(@PathVariable("id") String id) {
		Pokemon recuperado = service.getById(id);
		return recuperado;
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public String post(@RequestBody Pokemon novo) {
		return service.save(novo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		service.deleteById(id);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	public void put(@PathVariable("id") String id, @RequestBody Pokemon modificado) {
		if (!id.equals(modificado.getId())) {
			// throw exception;
		}
		service.save(modificado);
	}

}








