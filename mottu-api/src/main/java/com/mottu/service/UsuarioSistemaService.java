package com.mottu.service;

import com.mottu.entity.UsuarioSistema;
import com.mottu.entity.PerfilUsuario;
import com.mottu.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioSistemaService implements UserDetailsService {

    @Autowired
    private UsuarioSistemaRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UsuarioSistema> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<UsuarioSistema> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioSistema> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioSistema> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioSistema salvar(UsuarioSistema usuario) {
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    public UsuarioSistema alterarSenha(Long usuarioId, String novaSenha) {
        Optional<UsuarioSistema> usuarioOpt = buscarPorId(usuarioId);
        if (usuarioOpt.isPresent()) {
            UsuarioSistema usuario = usuarioOpt.get();
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}
