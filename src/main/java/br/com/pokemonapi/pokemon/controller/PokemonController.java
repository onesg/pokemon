package br.com.pokemonapi.pokemon.controller;

import br.com.pokemonapi.pokemon.model.PokemonModel;
import br.com.pokemonapi.pokemon.service.PokemonService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PagedModel<PokemonModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<PokemonModel> assembler) throws Exception {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "idPokemon"));
        Page<PokemonModel> pokemonModels = service.findAll(pageable);
        for (PokemonModel pokemonModel : pokemonModels) {
            buildEntityLink(pokemonModel);
        }
        return new ResponseEntity(assembler.toModel(pokemonModels), HttpStatus.OK);
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

}
