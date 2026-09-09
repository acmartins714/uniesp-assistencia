package br.com.uniesp.assistencia.internal.tecnico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tecnicos")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false , columnDefinition = "TEXT", length = 60)
    private String nome;

    @Column(name="especialidade", nullable = false, columnDefinition = "TEXT", length = 30)
    private String especialidade;

    @Column(name = "ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean status;

    public Tecnico(String nome, String especialidade, boolean status) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.status = status;
    }

    public void alteraNome(String nome) {
        this.nome = nome;
    }

    public void alteraEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void statusAtivo(boolean status) {
        this.status = true;
    }

    public void statusInativo(boolean status) {
        this.status = false;
    }

}
