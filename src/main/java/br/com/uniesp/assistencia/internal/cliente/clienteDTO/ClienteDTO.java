package br.com.uniesp.assistencia.internal.cliente.clienteDTO;

import br.com.uniesp.assistencia.internal.equipamento.entity.EquipamentoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotNull(message = "O nome do cliente deve ser preenchido!")
    private String nome;

    @NotNull(message = "O C.P.F. do cliente deve ser preenchido!")
    private String cpf;

    @NotNull(message = "O e-mail do cliente deve ser preenchido!")
    private String email;

    @NotNull(message = "O status do cliente deve ser marcado como ativo ou inativo!")
    private boolean ativo;

    private List<EquipamentoEntity> equipamentos;

}
