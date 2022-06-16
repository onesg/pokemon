package br.com.pokemonapi.pokemon.service;

import br.com.pokemonapi.pokemon.model.PokemonModel;
import br.com.pokemonapi.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository repository;

    public PokemonModel findById(long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Pokemon não encontrado."));
    }

    public Page<PokemonModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PokemonModel save(PokemonModel pokemonModel) {
        return repository.save(pokemonModel);
    }

    public PokemonModel update(PokemonModel pokemonModel) throws Exception {
        PokemonModel pm = findById(pokemonModel.getIdPokemon());
        pm.setNomePokemon(pokemonModel.getNomePokemon());
        pm.setTipoPokemon(pokemonModel.getTipoPokemon());
        return repository.save(pm);
    }

    public void delete(long id) throws Exception {
        PokemonModel pm = findById(id);
        repository.delete(pm);
    }

}
