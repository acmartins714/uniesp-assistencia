package br.com.uniesp.assistencia.internal.ordemServico.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "ordensservico")
public class OrdemServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", columnDefinition = "SMALLINT" )
    @Range(min = 0, max = 5)
    private int status;

    @Column(name = "prioridade", nullable = false, columnDefinition = "SMALLINT")
    @Range(min = 0, max = 5)
    private int prioridade;

    @Column(name="dataabertura", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate dataAbertura;

    @Column(name="dataconclusao", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate dataConclusao;

    @Column(name="descricaodefeito", nullable = false, columnDefinition = "TEXT", length = 250)
    private String descricaoDefeito;

    public OrdemServicoEntity(int status, int prioridade, LocalDate dataAbertura, LocalDate dataConclusao, String descricaoDefeito) {
        this.status = status;
        this.prioridade = prioridade;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = dataConclusao;
        this.descricaoDefeito = descricaoDefeito;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }
}
