package br.com.pokemonapi.pokemon.repository;

import br.com.pokemonapi.pokemon.model.PokemonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel, Long> {
    public Page<PokemonModel> findAll(Pageable pageable);
}
