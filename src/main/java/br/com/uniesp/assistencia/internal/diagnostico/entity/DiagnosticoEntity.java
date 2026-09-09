package br.com.uniesp.assistencia.internal.diagnostico.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diagnosticos")
public class DiagnosticoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="descricao", nullable = false, columnDefinition = "TEXT", length = 250)
    private String descricao;

    @Column(name="observacoes", columnDefinition = "TEXT", length = 1024)
    private String observacoes;

    @Column(name="data", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate data;

    public DiagnosticoEntity(String descricao, String observacoes, LocalDate data) {
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

}
