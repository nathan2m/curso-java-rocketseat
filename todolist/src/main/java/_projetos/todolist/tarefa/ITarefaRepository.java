package _projetos.todolist.tarefa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITarefaRepository extends JpaRepository<TarefaModel, UUID> {
    List<TarefaModel> findByIdUsuario(UUID idUsuario);
}
