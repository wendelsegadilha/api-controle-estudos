package br.com.wendelsegadilha.ace.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wendelsegadilha.ace.model.Usuario;
import br.com.wendelsegadilha.ace.model.dto.UsuarioDTO;
import br.com.wendelsegadilha.ace.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Integer id) {
		Usuario usuario = usuarioService.buscarPorId(id);
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
		usuario = usuarioService.salvar(usuario);
		usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);
		Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
		usuario = usuarioService.atualizar(usuario);
		usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}
}
