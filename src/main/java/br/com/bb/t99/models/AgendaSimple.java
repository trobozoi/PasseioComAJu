package br.com.bb.t99.models;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDateTime;

@Getter
@Setter
public class AgendaSimple {
    private Long pet;

    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;

    private int duracao;
}