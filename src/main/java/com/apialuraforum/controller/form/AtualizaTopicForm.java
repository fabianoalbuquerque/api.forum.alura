package com.apialuraforum.controller.form;

import com.apialuraforum.modelo.Topico;
import com.apialuraforum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizaTopicForm {
    @NotNull
    @NotEmpty
    @Length(min = 5, max = 20)
    private String titulo;

    @NotNull
    @NotEmpty
    @Length(min = 5, max = 250)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicoRepository topicRepository) {
        Topico topico = topicRepository.getById(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}

