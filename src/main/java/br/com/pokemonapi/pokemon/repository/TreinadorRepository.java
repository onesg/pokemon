package br.com.pokemonapi.pokemon.repository;

import br.com.pokemonapi.pokemon.model.TreinadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinadorRepository extends JpaRepository<TreinadorModel, Long> {
}
