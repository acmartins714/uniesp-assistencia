package br.com.uniesp.assistencia.internal.orcamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orcamentos")
public class OrcamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="valormaoobra", columnDefinition = "NUMERIC(12,2) DEFAULT 0.00", nullable = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorMaoObra;

    @Column(name="valorpecas", columnDefinition = "NUMERIC(12,2) DEFAULT 0.00", nullable = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorPecas;

    @Column(name = "status", columnDefinition = "SMALLINT" )
    @Range(min = 0, max = 5)
    private int status;

    @Column(name="validade", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate validade;

    public OrcamentoEntity(BigDecimal valorMaoObra, BigDecimal valorPecas, int status, LocalDate validade) {
        this.valorMaoObra = valorMaoObra;
        this.valorPecas = valorPecas;
        this.status = status;
        this.validade = validade;
    }

    public void setValorMaoObra(BigDecimal valorMaoObra) {
        this.valorMaoObra = valorMaoObra;
    }

    public void setValorPecas(BigDecimal valorPecas) {
        this.valorPecas = valorPecas;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

}
