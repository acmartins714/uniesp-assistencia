package br.com.uniesp.assistencia.internal.equipamento.entity;

import br.com.uniesp.assistencia.internal.cliente.entity.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "equipamentos")
public class EquipamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "", nullable = false , length = 20)
    private String tipo;

    @Column(nullable = false , length = 30)
    private String marca;

    @Column(nullable = false , length = 30)
    private String modelo;

    @Column(nullable = false , length = 15)
    private String numeroSerie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    public EquipamentoEntity(String tipo, String marca, String modelo, String numeroSerie) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
    }

    public void alteraTipo(String tipo) {
        this.tipo = tipo;
    }

    public void alteraMarca(String marca) {
        this.marca = marca;
    }

    public void alteraModelo(String modelo) {
        this.modelo = modelo;
    }

    public void alteraNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void associarCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

}
