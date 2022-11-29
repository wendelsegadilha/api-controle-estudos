package br.com.wendelsegadilha.ace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wendelsegadilha.ace.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
	
	Usuario findByEmail(String email);

}
