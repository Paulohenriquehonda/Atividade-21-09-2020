package App.Pokemon;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pokemon {
	@Id
	private String id;
	
	private String nome;
	
	private int level;

	public Pokemon(String id, String nome, int level) {		
		this.id = id;
		this.nome = nome;
		this.level = level;
	}

	public Pokemon() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getLevel() {
		return level;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
