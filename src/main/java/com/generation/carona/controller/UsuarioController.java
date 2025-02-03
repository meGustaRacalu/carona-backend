package com.generation.carona.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.generation.carona.model.Usuario;
import com.generation.carona.model.UsuarioLogin;
import com.generation.carona.repository.UsuarioRepository;
import com.generation.carona.security.JwtService;
import com.generation.carona.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        try {
            return usuarioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); 
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(usuarioRepository.save(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            return usuarioRepository.findById(usuario.getId())
                    .map(u -> {
                        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
                        return ResponseEntity.ok(usuarioRepository.save(usuario));
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/logar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody UsuarioLogin usuarioLogin) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findFirstByUsuario(usuarioLogin.getUsuario());
            if (usuario.isPresent()) {
                if (passwordEncoder.matches(usuarioLogin.getSenha(), usuario.get().getSenha())) {
                    String token = "Bearer " + jwtService.generateToken(usuario.get().getUsuario());
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no login.");
        }
    }
}

