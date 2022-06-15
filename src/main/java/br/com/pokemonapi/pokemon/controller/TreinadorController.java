package br.com.pokemonapi.pokemon.controller;

import br.com.pokemonapi.pokemon.model.TreinadorModel;
import br.com.pokemonapi.pokemon.service.TreinadorService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinador")
public class TreinadorController {

    @Autowired
    private TreinadorService service;

    @ApiOperation(value = "Pesquisar treinador pelo ID.")
    @GetMapping(
            value = "/{id}",
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public TreinadorModel findById(@PathVariable("id") long id) throws Exception {
        TreinadorModel treinadorModel = service.findById(id);
        buildEntityLink(treinadorModel);
        return treinadorModel;
    }

    @ApiOperation(value = "Listar todos os treinadores.")
    @GetMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"})
    public CollectionModel<TreinadorModel> findAll() throws Exception {
        CollectionModel<TreinadorModel> treinadorModels = CollectionModel.of(service.findAll());
        for (final TreinadorModel treinadorModel : treinadorModels) {
            buildEntityLink(treinadorModel);
        }
        buildCollectionLink(treinadorModels);
        return treinadorModels;
    }

    @ApiOperation(value = "Cadastrar novo treinador.")
    @PostMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public TreinadorModel save(@RequestBody TreinadorModel treinadorModel) {
        return service.save(treinadorModel);
    }

    @ApiOperation(value = "Atualizar um treinador.")
    @PutMapping(
            produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public TreinadorModel update(@RequestBody TreinadorModel treinadorModel) throws Exception {
        return service.update(treinadorModel);
    }

    @ApiOperation(value = "Deletar um treinador pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    private void buildEntityLink(TreinadorModel treinadorModel) throws Exception {
        treinadorModel.add(
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(TreinadorController.class)
                                .findById(treinadorModel.getIdTreinador())
                        ).withSelfRel()
        );
    }

    private void buildCollectionLink(CollectionModel<TreinadorModel> treinadorModels) throws Exception {
        treinadorModels.add(
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(TreinadorController.class)
                                .findAll()
                        ).withRel(IanaLinkRelations.COLLECTION)
        );
    }

}
