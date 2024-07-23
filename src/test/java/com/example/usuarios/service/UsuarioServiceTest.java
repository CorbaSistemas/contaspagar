package com.example.usuarios.service;

import com.example.usuarios.domain.Usuario;
import com.example.usuarios.infrastructure.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    public UsuarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        assertEquals(usuario, savedUsuario);
    }

    @Test
    public void testGetUsuarioByUsername() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByUsername("username")).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.getUsuarioByUsername("username");
        assertEquals(usuario, result);
    }
}
