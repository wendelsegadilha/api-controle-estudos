package br.com.wendelsegadilha.ace.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wendelsegadilha.ace.expetion.AutorizacaoException;
import br.com.wendelsegadilha.ace.expetion.CadastroUsuarioException;
import br.com.wendelsegadilha.ace.expetion.ObjetoNaoEncontradoException;
import br.com.wendelsegadilha.ace.model.Usuario;
import br.com.wendelsegadilha.ace.repository.UsuarioRepository;
import br.com.wendelsegadilha.ace.security.UserSS;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Usuario buscarPorId(Integer id) {
		verificaUser(id);
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isPresent()) {
			throw new ObjetoNaoEncontradoException("Usuário inexistente.");
		}
		return usuario.get();
	}

	public Usuario salvar(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
			throw new CadastroUsuarioException("E-mail já cadastrado.");
		}
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(Usuario usuario) {
		verificaUser(usuario.getId());
		Usuario usuarioEntity = buscarPorId(usuario.getId());
		usuarioEntity.setNome(usuario.getNome());
		usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuarioEntity);
	}

	public void verificaUser(Integer id) {
		UserSS user = autenticado();
		if (user == null || !id.equals(user.getId())) {
			throw new AutorizacaoException("Acesso negado.");
		}
	}

	/*
	 * Retorna o usuário logado se houver
	 */
	public static UserSS autenticado() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
