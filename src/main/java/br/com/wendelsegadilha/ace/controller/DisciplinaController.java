package br.com.wendelsegadilha.ace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wendelsegadilha.ace.model.Disciplina;
import br.com.wendelsegadilha.ace.model.dto.DisciplinaDTO;
import br.com.wendelsegadilha.ace.service.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping
	public ResponseEntity<List<Disciplina>> listar() {
		List<Disciplina> disciplinas = disciplinaService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(disciplinas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id) {
		Disciplina disciplina = disciplinaService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(disciplina);
	}

	@PostMapping
	public ResponseEntity<Disciplina> salvar(@RequestBody DisciplinaDTO disciplinaDTO) {
		var disciplina = disciplinaService.salvar(disciplinaDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Disciplina> salvar(@PathVariable Integer id, @RequestBody Disciplina disciplina) {
		disciplina.setId(id);
		disciplina = disciplinaService.atualizar(disciplina);
		return ResponseEntity.status(HttpStatus.OK).body(disciplina);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		disciplinaService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
