package com.ivoautomativo.usuario.api;

import com.ivoautomativo.usuario.api.request.UsuarioRequestDTO;
import com.ivoautomativo.usuario.api.response.UsuarioResponseDTO;
import com.ivoautomativo.usuario.business.UsuarioService;
import com.ivoautomativo.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> gravaDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity.ok(usuarioService.gravarUsuario(usuarioRequestDTO));
    }

    @GetMapping
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam ("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarDadosUsuario(@RequestParam ("email") String email){
        usuarioService.deletarDadosUsuario(email);
        return ResponseEntity.accepted().build();
    }

}
