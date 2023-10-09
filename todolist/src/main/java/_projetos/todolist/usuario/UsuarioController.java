package _projetos.todolist.usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @PostMapping("/")
    public void criar(@RequestBody UsuarioModel usuarioModel){
        System.out.println(usuarioModel.nome);
    }

    @GetMapping("/")
    public String teste(){
        return "Funcionou";
    }
}
