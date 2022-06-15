package br.com.pokemonapi.pokemon.service;

import br.com.pokemonapi.pokemon.model.TreinadorModel;
import br.com.pokemonapi.pokemon.repository.TreinadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinadorService {

    @Autowired
    private TreinadorRepository repository;

    public TreinadorModel findById(long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Treinador não encontrado."));
    }

    public List<TreinadorModel> findAll() {
        return repository.findAll();
    }

    public TreinadorModel save(TreinadorModel treinadorModel) {
        return repository.save(treinadorModel);
    }

    public TreinadorModel update(TreinadorModel treinadorModel) throws Exception {
        TreinadorModel tm = findById(treinadorModel.getIdTreinador());
        tm.setNomeTreinador(treinadorModel.getNomeTreinador());
        tm.setIdadeTreinador(treinadorModel.getIdadeTreinador());
        return repository.save(tm);
    }

    public void delete(long id) throws Exception {
        TreinadorModel tm = findById(id);
        repository.delete(tm);
    }

}
