package com.ivoautomativo.usuario.business;

import com.ivoautomativo.usuario.infrastructure.entity.EnderecoEntity;
import com.ivoautomativo.usuario.infrastructure.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoEntity salvarEndereco(EnderecoEntity enderecoEntity){
        return enderecoRepository.save(enderecoEntity);
    }

    public EnderecoEntity findByUsuarioId(String id){
        return enderecoRepository.findByUsuarioId(id);
    }

    public void deleteByUsuarioId(String usuarioId){
        enderecoRepository.deleteByUsuarioId(usuarioId);
    }

}
