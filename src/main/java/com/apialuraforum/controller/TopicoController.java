package com.apialuraforum.controller;

import com.apialuraforum.dto.TopicoDTO;
import com.apialuraforum.modelo.Curso;
import com.apialuraforum.modelo.Topico;
import com.apialuraforum.modelo.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {
    @RequestMapping("/topicos")
    public List<TopicoDTO> listar(){
      Curso curso = new Curso("API com Java e Spring Boot","Formação Backend");
      Topico topico = new Topico("Dúvidas com DTO", "Não consigo entender...", curso);

      return TopicoDTO.converse(Arrays.asList(topico)) ;
    }
}
