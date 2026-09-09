package br.com.uniesp.assistencia.internal.anexo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "anexos")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false , columnDefinition = "TEXT", length = 60)
    private String nome;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name="tamanho", columnDefinition = "TEXT", length = 10)
    private int tamanho;

    @Column(name="storagekey", columnDefinition = "TEXT", nullable = false, length = 30)
    private String storagekey;

    public Anexo(String nome, String tipo, int tamanho, String storagekey) {
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.storagekey = storagekey;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void setStoragekey(String storagekey) {
        this.storagekey = storagekey;
    }

}
