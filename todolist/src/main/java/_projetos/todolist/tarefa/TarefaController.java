package _projetos.todolist.tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private ITarefaRepository tarefaRepository;
    
    @PostMapping("/")
    public TarefaModel criar(@RequestBody TarefaModel tarefaModel){
        System.out.println("Chegou no controller");
        var tarefa = this.tarefaRepository.save(tarefaModel);
        return tarefa;
    }
}
