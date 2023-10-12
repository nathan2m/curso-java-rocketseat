package _projetos.todolist.tarefa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITarefaRepository extends JpaRepository<TarefaModel, UUID> {
    
}
