package com.apialuraforum.controller;

import com.apialuraforum.controller.form.AtualizaTopicForm;
import com.apialuraforum.controller.form.TopicForm;
import com.apialuraforum.dto.DetalharTopicoDTO;
import com.apialuraforum.dto.TopicoDTO;
import com.apialuraforum.modelo.Topico;
import com.apialuraforum.repository.CursoRepository;
import com.apialuraforum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converse(cursoRepository);
        topicRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalharTopicoDTO detalhar(@PathVariable Long id) {
        Topico topico = topicRepository.getById(id);

        return new DetalharTopicoDTO(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizaTopicForm form) {
        Topico topico = form.atualizar(id, topicRepository);

        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> remover(@PathVariable Long id) {
        topicRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
