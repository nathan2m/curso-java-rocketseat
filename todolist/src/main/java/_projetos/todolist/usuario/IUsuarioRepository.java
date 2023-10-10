package _projetos.todolist.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, UUID>{
    UsuarioModel findByUsuario(String usuario);
}
