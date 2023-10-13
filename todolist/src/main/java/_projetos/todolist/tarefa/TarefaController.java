package _projetos.todolist.tarefa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _projetos.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private ITarefaRepository tarefaRepository;

    @PostMapping("/")
    public ResponseEntity criar(@RequestBody TarefaModel tarefaModel, HttpServletRequest request) {
        var idUsuario = request.getAttribute("idUsuario");
        tarefaModel.setIdUsuario((UUID) idUsuario);

        var dataAtual = LocalDateTime.now();
        if (dataAtual.isAfter(tarefaModel.getInicioEm()) || dataAtual.isAfter(tarefaModel.getFimEm())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Data de início e fim devem ser maiores que a data atual");
        }

        if (tarefaModel.getInicioEm().isAfter(tarefaModel.getFimEm())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de início deve ser menor que data de fim");
        }

        var tarefa = this.tarefaRepository.save(tarefaModel);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/")
    public List<TarefaModel> listar(HttpServletRequest request) {
        var idUsuario = request.getAttribute("idUsuario");
        var tarefas = this.tarefaRepository.findByIdUsuario((UUID) idUsuario);
        return tarefas;
    }

    @PutMapping("/{id}")
    public TarefaModel atualizar(@RequestBody TarefaModel tarefaModel, HttpServletRequest request,
            @PathVariable UUID id) {
        
        var tarefa = this.tarefaRepository.findById(id).orElse(null);
        Utils.copyNonNullProperties(tarefaModel, tarefa);
        
        return this.tarefaRepository.save(tarefa);
    }
}
