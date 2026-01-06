package com.ivoautomativo.usuario.api.converter;

import com.ivoautomativo.usuario.api.response.UsuarioResponseDTO;
import com.ivoautomativo.usuario.infrastructure.entity.EnderecoEntity;
import com.ivoautomativo.usuario.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "usuario.id")
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "documento", source = "usuario.documento")
    @Mapping(target = "endereco", source = "enderecoEntity")
    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario, EnderecoEntity enderecoEntity);

}
