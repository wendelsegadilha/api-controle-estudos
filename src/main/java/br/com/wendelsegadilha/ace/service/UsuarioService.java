package br.com.wendelsegadilha.ace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wendelsegadilha.ace.expetion.CadastroUsuarioException;
import br.com.wendelsegadilha.ace.model.Usuario;
import br.com.wendelsegadilha.ace.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
			throw new CadastroUsuarioException("E-mail já cadastrado.");
		}
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

}
