package br.com.wendelsegadilha.ace;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.wendelsegadilha.ace.model.Disciplina;
import br.com.wendelsegadilha.ace.model.Usuario;
import br.com.wendelsegadilha.ace.repository.DisciplinaRepository;
import br.com.wendelsegadilha.ace.repository.UsuarioRepository;

@SpringBootApplication
public class Startup implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(1, "Wendel Segadilha", "wendelsegadilha99@gmail.com", "123");
		
		Disciplina d1 = new Disciplina(1, "Matemática", "MAT", u1);
		Disciplina d2 = new Disciplina(2, "Português", "POR", u1);
		Disciplina d3 = new Disciplina(3, "Física", "FIS", u1);
		Disciplina d4 = new Disciplina(4, "Química", "QUI", u1);
		
		//u1.getDisciplinas().addAll(Arrays.asList(d1, d2, d3, d3, d4));
		
		usuarioRepository.save(u1);
		disciplinaRepository.saveAll(Arrays.asList(d1, d2, d3, d4));
		
		
	}

}
