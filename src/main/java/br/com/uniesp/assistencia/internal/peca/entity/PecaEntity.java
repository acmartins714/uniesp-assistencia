package br.com.uniesp.assistencia.internal.peca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "pecas")
public class PecaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false , columnDefinition = "TEXT", length = 60)
    private String nome;

    @Column(name="codigo", columnDefinition = "LONG")
    @Range(min = 1)
    private int codigo;

    @Column(name="preco", columnDefinition = "NUMERIC(12,2) DEFAULT 0.00", nullable = false)
    private BigDecimal preco;

    @Column(name="estoque", columnDefinition = "LONG")
    @Range(min = 0)
    private int estoque;

    public PecaEntity(String nome, int codigo, BigDecimal preco, int estoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

}
