package edu.folhaPgto.entity;

import java.time.LocalDate;

public class Feriado {

    private Long id;
    private LocalDate dataFeriado;
    private String descricao;


    public Feriado(Long id, LocalDate dataFeriado, String descricao){

        this.id = id;
        this.dataFeriado = dataFeriado;
        this.descricao = descricao;

    }

    public Feriado(){
        super();
    }

    public LocalDate getDataFeriado() {
        return dataFeriado;
    }

    public void setDataFeriado(LocalDate dataFeriado) {
        this.dataFeriado = dataFeriado;
    }
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}


