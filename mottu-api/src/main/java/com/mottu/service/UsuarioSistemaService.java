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
    
    @Transactional(readOnly = true)
    public List<UsuarioSistema> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioSistema> buscarPorPerfil(PerfilUsuario perfil) {
        return usuarioRepository.findByPerfil(perfil);
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioSistema> buscarUsuariosAtivos() {
        return usuarioRepository.findByAtivoTrue();
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioSistema> buscarUsuariosInativos() {
        return usuarioRepository.findByAtivoFalse();
    }
    
    @Transactional(readOnly = true)
    public List<UsuarioSistema> buscarUsuariosAtivosPorPerfil(PerfilUsuario perfil) {
        return usuarioRepository.findUsuariosAtivosPorPerfil(perfil);
    }
    
    @Transactional(readOnly = true)
    public long contarUsuariosPorPerfil(PerfilUsuario perfil) {
        return usuarioRepository.countByPerfil(perfil);
    }
    
    @Transactional(readOnly = true)
    public boolean emailJaExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioSistema salvar(UsuarioSistema usuario) {
        validarUsuario(usuario);
        
        // Criptografar senha se foi alterada
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public UsuarioSistema criarUsuario(UsuarioSistema usuario, String senhaPlana) {
        validarUsuario(usuario);
        usuario.setSenha(passwordEncoder.encode(senhaPlana));
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
    
    public UsuarioSistema ativarDesativarUsuario(Long usuarioId, boolean ativo) {
        Optional<UsuarioSistema> usuarioOpt = buscarPorId(usuarioId);
        if (usuarioOpt.isPresent()) {
            UsuarioSistema usuario = usuarioOpt.get();
            usuario.setAtivo(ativo);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuário não encontrado com ID: " + usuarioId);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    
    // Implementação do UserDetailsService para Spring Security
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
    
    private void validarUsuario(UsuarioSistema usuario) {
        // Validar email único (apenas para novos usuários ou mudança de email)
        if (usuario.getIdUsuario() == null || !usuario.getEmail().equals(buscarPorId(usuario.getIdUsuario()).map(UsuarioSistema::getEmail).orElse(null))) {
            if (emailJaExiste(usuario.getEmail())) {
                throw new RuntimeException("Já existe um usuário com este email: " + usuario.getEmail());
            }
        }
        
        // Validar nome
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }
        
        // Validar perfil
        if (usuario.getPerfil() == null) {
            throw new RuntimeException("Perfil é obrigatório");
        }
    }
}