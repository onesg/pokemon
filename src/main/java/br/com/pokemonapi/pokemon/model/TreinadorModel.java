package br.com.pokemonapi.pokemon.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "treinador")
public class TreinadorModel extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTreinador;

    @Column(name = "nome_treinador", nullable = false, length = 64)
    private String nomeTreinador;

    @Column(name = "idade_treinador", nullable = false, length = 2)
    private int idadeTreinador;

    public TreinadorModel() {
    }

    public TreinadorModel(long idTreinador, String nomeTreinador, int idadeTreinador) {
        this.idTreinador = idTreinador;
        this.nomeTreinador = nomeTreinador;
        this.idadeTreinador = idadeTreinador;
    }

    public long getIdTreinador() {
        return idTreinador;
    }

    public String getNomeTreinador() {
        return nomeTreinador;
    }

    public int getIdadeTreinador() {
        return idadeTreinador;
    }

    public void setIdTreinador(long idTreinador) {
        this.idTreinador = idTreinador;
    }

    public void setNomeTreinador(String nomeTreinador) {
        this.nomeTreinador = nomeTreinador;
    }

    public void setIdadeTreinador(int idadeTreinador) {
        this.idadeTreinador = idadeTreinador;
    }
}
