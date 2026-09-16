package br.com.uniesp.assistencia.internal.cliente.mapper;

import br.com.uniesp.assistencia.internal.cliente.clienteDTO.ClienteDTO;
import br.com.uniesp.assistencia.internal.cliente.entity.ClienteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteEntity toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, ClienteEntity.class);
    }

    public ClienteDTO toDTO(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, ClienteDTO.class);
    }

}
