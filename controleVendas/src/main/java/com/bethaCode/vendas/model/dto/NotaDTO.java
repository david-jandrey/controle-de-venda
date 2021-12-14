package com.bethaCode.vendas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NotaDTO {
    private Integer idAluno;
    private Integer idDisciplina;
    private String dataNota;
    private String nota;

    public NotaDTO(){

    }
}
