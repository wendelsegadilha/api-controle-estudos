package br.com.wendelsegadilha.ace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wendelsegadilha.ace.model.Disciplina;

@Repository
public interface DisciplinaRepository  extends JpaRepository<Disciplina, Integer>{

}
