package br.com.uniesp.assistencia.internal.cliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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

    @Column(nullable = false, length = 11)
    private String cpf;

    private String email;
    @Column(nullable = false, length = 20)
    @Pattern(regexp = "ATIVA|INATIVA", message = "Este campo aceita apenas os seguintes valores: ATIVA | INATIVA")    private boolean ativo;

}
