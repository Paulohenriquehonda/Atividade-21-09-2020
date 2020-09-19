package App.Pokemon;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PokemonService {
	@Autowired
	private PokemonRepository repo;
	

	public void deleteById(String id) {
		repo.deleteById(id);
	}

	public Pokemon getById(String id) {
		return repo.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public List<Pokemon> getAll() {
		return repo.findAll();
	}
	
	public String save(Pokemon pokemon) {
		return this.repo.save(pokemon).getId();
	}
	
}
