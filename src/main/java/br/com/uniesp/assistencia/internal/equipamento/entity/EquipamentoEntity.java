package br.com.uniesp.assistencia.internal.equipamento.entity;

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

    @Column(nullable = false , length = 20)
    private String tipo;

    @Column(nullable = false , length = 30)
    private String marca;

    @Column(nullable = false , length = 30)
    private String modelo;

    @Column(nullable = false , length = 15)
    private String numeroSerie;

    public EquipamentoEntity(String tipo, String marca, String modelo, String numeroSerie) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
    }

    public void alteraNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

}
