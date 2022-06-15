package br.com.pokemonapi.pokemon.controller;

import br.com.pokemonapi.pokemon.model.PokemonModel;
import br.com.pokemonapi.pokemon.service.PokemonService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @ApiOperation(value = "Pesquisar pokemon pelo ID.")
    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public PokemonModel findById(@PathVariable("id") long id) throws Exception {
        PokemonModel pokemonModel = service.findById(id);
        buildEntityLink(pokemonModel);
        return pokemonModel;
    }

    @ApiOperation(value = "Listar todos os pokemon.")
    @GetMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public CollectionModel<PokemonModel> findAll() throws Exception {
        CollectionModel<PokemonModel> pokemonModels = CollectionModel.of(service.findAll());
        for (final PokemonModel pokemonModel : pokemonModels) {
            buildEntityLink(pokemonModel);
        }
        buildCollectionLink(pokemonModels);
        return pokemonModels;
    }

    @ApiOperation(value = "Cadastrar novo pokemon.")
    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PokemonModel save(@RequestBody PokemonModel pokemonModel) {
        return service.save(pokemonModel);
    }

    @ApiOperation(value = "Atualizar um pokemon.")
    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public PokemonModel update(@RequestBody PokemonModel pokemonModel) throws Exception {
        return service.update(pokemonModel);
    }

    @ApiOperation(value = "Deletar um pokemon pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLink(PokemonModel pokemonModel) throws Exception {
        pokemonModel.add(
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(PokemonController.class)
                                .findById(pokemonModel.getIdPokemon())
                        ).withSelfRel()
        );
        if (!pokemonModel.getTreinadorModel().hasLinks()) {
            Link treinadorLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            TreinadorController.class).findById(pokemonModel.getTreinadorModel().getIdTreinador())
            ).withSelfRel();
            pokemonModel.getTreinadorModel().add(treinadorLink);
        }
    }

    private void buildCollectionLink(CollectionModel<PokemonModel> pokemonModels) throws Exception {
        pokemonModels.add(
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(PokemonController.class)
                                .findAll()
                        ).withRel(IanaLinkRelations.COLLECTION)
        );
    }

}
