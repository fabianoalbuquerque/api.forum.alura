package com.apialuraforum.controller.form;

import com.apialuraforum.modelo.Curso;
import com.apialuraforum.modelo.Topico;
import com.apialuraforum.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class TopicForm {
    @NotNull @NotEmpty @Length(min = 5, max = 20)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 5, max = 250)
    private String mensagem;

    @NotNull @NotEmpty @Length(min = 5, max = 100)
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converse(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
