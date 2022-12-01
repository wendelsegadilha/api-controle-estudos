package br.com.wendelsegadilha.ace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.wendelsegadilha.ace.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

	@Query("SELECT d FROM Disciplina d WHERE d.id = :disciplinaId  AND d.usuario.id = :usuarioId")
	Optional<Disciplina> buscarPorId(@Param("disciplinaId") Integer disciplinaId,
			@Param("usuarioId") Integer usuarioId);

	@Query("SELECT d FROM Disciplina d WHERE d.usuario.id = :usuarioId")
	List<Disciplina> listar(@Param("usuarioId") Integer usuarioId);

	@Query("DELETE FROM Disciplina d WHERE d.id = :disciplinaId  AND d.usuario.id = :usuarioId")
	void excluir(@Param("disciplinaId") Integer disciplinaId, @Param("usuarioId") Integer usuarioId);

}
