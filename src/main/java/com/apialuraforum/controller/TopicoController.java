package com.apialuraforum.controller;

import com.apialuraforum.controller.form.TopicForm;
import com.apialuraforum.dto.TopicoDTO;
import com.apialuraforum.modelo.Topico;
import com.apialuraforum.repository.CursoRepository;
import com.apialuraforum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> listar(String nomeCurso){
        if(nomeCurso == null) {
            List<Topico> topicos = topicRepository.findAll();
            return TopicoDTO.converse(topicos) ;
        }else {
            List<Topico> topicos = topicRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converse(topicos) ;
        }
    }
    @PostMapping
    public void cadastrar(@RequestBody TopicForm form) {
        Topico topico = form.converse(cursoRepository);
        topicRepository.save(topico);
    }
}
