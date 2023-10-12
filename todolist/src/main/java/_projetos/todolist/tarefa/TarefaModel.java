package _projetos.todolist.tarefa;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tarefas")
public class TarefaModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;

    @Column(length = 50)
    private String titulo;
    private LocalDateTime inicioEm;
    private LocalDateTime fimEm;
    private String prioridade;
    
    
    private UUID idUsuario;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
