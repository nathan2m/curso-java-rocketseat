package _projetos.todolist.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @PostMapping("/")
    public ResponseEntity criar(@RequestBody UsuarioModel usuarioModel){
        var usuario = this.usuarioRepository.findByUsuario(usuarioModel.getUsuario());
        if (usuario != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var senhaCriptografada = BCrypt.withDefaults().hashToString(12, usuarioModel.getSenha().toCharArray());
        usuarioModel.setSenha(senhaCriptografada);

        var usuarioCriado = this.usuarioRepository.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @GetMapping("/")
    public String teste(){
        return "Funcionou";
    }
}
