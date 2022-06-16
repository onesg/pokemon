package br.com.pokemonapi.pokemon.repository;

import br.com.pokemonapi.pokemon.model.TreinadorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinadorRepository extends JpaRepository<TreinadorModel, Long> {
    public Page<TreinadorModel> findAll(Pageable pageable);
}
