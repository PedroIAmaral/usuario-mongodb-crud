package com.ivoautomativo.usuario.business;

import com.ivoautomativo.usuario.api.converter.UsuarioConverter;
import com.ivoautomativo.usuario.api.converter.UsuarioMapper;
import com.ivoautomativo.usuario.api.request.UsuarioRequestDTO;
import com.ivoautomativo.usuario.api.response.UsuarioResponseDTO;
import com.ivoautomativo.usuario.infrastructure.entity.EnderecoEntity;
import com.ivoautomativo.usuario.infrastructure.entity.UsuarioEntity;
import com.ivoautomativo.usuario.infrastructure.exceptions.BusinessException;
import com.ivoautomativo.usuario.infrastructure.repository.EnderecoRepository;
import com.ivoautomativo.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioConverter usuarioConverter;
    private final EnderecoService enderecoService;
    private final UsuarioMapper usuarioMapper;


    public UsuarioEntity salvarUsuario(UsuarioEntity usuarioEntity){
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuario(UsuarioRequestDTO usuarioRequestDTO){
        notNull(usuarioRequestDTO, "Os dados do usuário são nulos");
        UsuarioEntity usuarioEntity = salvarUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
        EnderecoEntity enderecoEntity = enderecoService.salvarEndereco(usuarioConverter.paraEnderecoEntity(usuarioRequestDTO.getEndereco(), usuarioEntity.getId()));
        return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email){
        try{
            UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email);
            notNull(usuarioEntity, "Usuario não encontrado");
            EnderecoEntity enderecoEntity = enderecoRepository.findByUsuarioId(usuarioEntity.getId());

            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity, enderecoEntity);
        }catch (Exception ex){
            throw new BusinessException("Erro ao buscar usuário", ex);
        }

    }

    @Transactional
    public void deletarDadosUsuario(String email){
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(email);
        usuarioRepository.deleteByEmail(email);
        enderecoService.deleteByUsuarioId(usuarioEntity.getId());
    }


}
