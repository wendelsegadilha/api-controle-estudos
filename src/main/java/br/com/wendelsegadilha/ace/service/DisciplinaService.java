package br.com.wendelsegadilha.ace.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wendelsegadilha.ace.expetion.AutorizacaoException;
import br.com.wendelsegadilha.ace.expetion.ObjetoNaoEncontradoException;
import br.com.wendelsegadilha.ace.model.Disciplina;
import br.com.wendelsegadilha.ace.model.Usuario;
import br.com.wendelsegadilha.ace.model.dto.DisciplinaDTO;
import br.com.wendelsegadilha.ace.repository.DisciplinaRepository;
import br.com.wendelsegadilha.ace.security.UserSS;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	private UserSS usuario;
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ModelMapper mapper;

	public List<Disciplina> listar() {
		usuario = verificaUserLogado();
		return disciplinaRepository.listar(usuario.getId());
	}

	public Disciplina buscarPorId(Integer id) {
		usuario = verificaUserLogado();
		Optional<Disciplina> disciplina = disciplinaRepository.buscarPorId(id, usuario.getId());
		if (!disciplina.isPresent()) {
			throw new ObjetoNaoEncontradoException("Disciplina inexistente.");
		}
		return disciplina.get();
	}

	public Disciplina salvar(DisciplinaDTO disciplinaDTO) {
		Usuario usuarioEntity = usuarioService.buscarPorId(disciplinaDTO.getUsuarioId());
		UsuarioService.verificaUser(usuarioEntity.getId());
		Disciplina disciplina = mapper.map(disciplinaDTO, Disciplina.class);
		disciplina.setUsuario(usuarioEntity);
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina atualizar(Disciplina disciplina) {
		Disciplina disciplinaEntity = buscarPorId(disciplina.getId());
		UsuarioService.verificaUser(disciplinaEntity.getUsuario().getId());
		disciplinaEntity.setNome(disciplina.getNome());
		disciplinaEntity.setSigla(disciplina.getSigla());
		return disciplinaRepository.save(disciplinaEntity);
	}

	public void excluir(Integer id) {
		buscarPorId(id);
		disciplinaRepository.deleteById(id);
	}
	
	private UserSS verificaUserLogado() {
		UserSS usuario = UsuarioService.autenticado();
		if (UsuarioService.autenticado() == null) {
			throw new AutorizacaoException("Acesso negado.");
		}
		return usuario;
	}


}
