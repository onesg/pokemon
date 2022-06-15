package br.com.pokemonapi.pokemon.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pokemon")
public class PokemonModel extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPokemon;

    @Column(name = "nome_pokemon", nullable = false, length = 64)
    private String nomePokemon;

    @Column(name = "tipo_pokemon", nullable = false, length = 64)
    private String tipoPokemon;

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = false)
    private TreinadorModel treinadorModel;

    public PokemonModel() {
    }

    public PokemonModel(long idPokemon, String nomePokemon, String tipoPokemon, TreinadorModel treinadorModel) {
        this.idPokemon = idPokemon;
        this.nomePokemon = nomePokemon;
        this.tipoPokemon = tipoPokemon;
        this.treinadorModel = treinadorModel;
    }

    public long getIdPokemon() {
        return idPokemon;
    }

    public String getNomePokemon() {
        return nomePokemon;
    }

    public String getTipoPokemon() {
        return tipoPokemon;
    }

    public TreinadorModel getTreinadorModel() {
        return treinadorModel;
    }

    public void setIdPokemon(long idPokemon) {
        this.idPokemon = idPokemon;
    }

    public void setNomePokemon(String nomePokemon) {
        this.nomePokemon = nomePokemon;
    }

    public void setTipoPokemon(String tipoPokemon) {
        this.tipoPokemon = tipoPokemon;
    }

    public void setTreinadorModel(TreinadorModel treinadorModel) {
        this.treinadorModel = treinadorModel;
    }

}
