package br.com.uniesp.assistencia.internal.historicoStatus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "historicosstatus")
public class HistoricoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="statusanterior", columnDefinition = "INT")
    @Range(min = 0, max = 5)
    private int statusAnterior;

    @Column(name="statusnovo", columnDefinition = "INT")
    @Range(min = 0, max = 5)
    private int statusNovo;

    @Column(name="data", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate dataConclusao;

    public HistoricoStatus(int statusAnterior, int statusNovo, LocalDate dataConclusao) {
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataConclusao = dataConclusao;
    }

    public void setStatusAnterior(int statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public void setStatusNovo(int statusNovo) {
        this.statusNovo = statusNovo;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

}
