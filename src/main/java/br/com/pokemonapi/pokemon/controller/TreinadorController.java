package br.com.pokemonapi.pokemon.controller;

import br.com.pokemonapi.pokemon.model.TreinadorModel;
import br.com.pokemonapi.pokemon.service.TreinadorService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PagedModel<TreinadorModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<TreinadorModel> assembler) throws Exception {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "idTreinador"));
        Page<TreinadorModel> treinadorModels = service.findAll(pageable);
        for (TreinadorModel treinadorModel : treinadorModels) {
            buildEntityLink(treinadorModel);
        }
        return new ResponseEntity(assembler.toModel(treinadorModels), HttpStatus.OK);
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

}
