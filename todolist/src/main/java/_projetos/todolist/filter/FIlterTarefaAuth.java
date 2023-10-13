package _projetos.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import _projetos.todolist.usuario.IUsuarioRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FIlterTarefaAuth extends OncePerRequestFilter {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if (servletPath.startsWith("/tarefas/")) {
            // Pegar a autenticação (Usuário e Senha)
            var auth = request.getHeader("Authorization");
            var authEncripted = auth.substring("Basic".length()).trim();
            String authDecripted = new String(Base64.getDecoder().decode(authEncripted));
            String[] credenciais = authDecripted.split(":");
            String usuario = credenciais[0];
            String senha = credenciais[1];

            // Validar usuário
            var usuarioValido = this.usuarioRepository.findByUsuario(usuario);
            if (usuarioValido == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            } else {
                // Validar senha
                var senhaValidada = BCrypt.verifyer().verify(senha.toCharArray(), usuarioValido.getSenha());
                if (senhaValidada.verified) {
                    request.setAttribute("idUsuario", usuarioValido.getId());
                    // Segue viagem
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpStatus.UNAUTHORIZED.value());
                }
            }
        } else {
            // Segue viagem
            filterChain.doFilter(request, response);
        }

    }

}
