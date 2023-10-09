package _projetos.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}


// @RestController //http://localhost:8080/ -----
// @RequestMapping("/primeiraRota")
// public class MinhaPrimeiraController {
    
//     /*
//     Métodos de acesso do HTTP
//      * GET - Buscar uma informação
//      * POST - Adicionar um dado/informação
//      * PUT - Alterar um dado/informação
//      * DELETE - Remover um dado
//      * PATCH - Alterar somente uma parte da informação/dado
//      */

//     //Método (Funcionalidade) de uma classe
//     @GetMapping("/primeiroMetodo")
//     public String primeiraMensagem(){
//         return "Funcionou";
//     }
// }