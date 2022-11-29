package br.com.wendelsegadilha.ace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wendelsegadilha.ace.model.Disciplina;
import br.com.wendelsegadilha.ace.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public List<Disciplina> listar() {
		return disciplinaRepository.findAll();
	}

	public Disciplina buscarPorId(Integer id) {
		return disciplinaRepository.findById(id).get();
	}

	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public Disciplina atualizar(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public void excluir(Integer id) {
		disciplinaRepository.deleteById(id);
	}

}