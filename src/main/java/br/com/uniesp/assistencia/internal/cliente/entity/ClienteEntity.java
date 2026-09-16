package br.com.uniesp.assistencia.internal.cliente.entity;

import br.com.uniesp.assistencia.internal.equipamento.entity.EquipamentoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "clientes")

public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean ativo;

    @OneToMany(mappedBy = "cliente")
    private List<EquipamentoEntity> equipamentos;

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void adicionarEquipamento(EquipamentoEntity equipamento) {
        equipamentos.add(equipamento);
        equipamento.associarCliente(this);
    }

    public void alteraNome(String nome) {
        this.nome = nome;
    }

    public void alteraCpf(String cpf) {
        this.cpf = cpf;
    }

    public void alteraEmail(String email) {
        this.email = email;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

}
