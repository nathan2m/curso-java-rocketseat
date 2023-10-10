package _projetos.todolist.usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="tb_usuarios")
public class UsuarioModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(unique = true)
    private String usuario;
    private String nome;
    private String senha;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
