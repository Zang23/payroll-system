package edu.folhaPgto.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class DiaTrabalhado {
    
    private Long id;
    private Long folhaPagamentoId;
    private String nomeProjeto;
    private LocalDate dataServico;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private boolean feriado;
    private boolean viagem;
    private double valorRecebido;

    public DiaTrabalhado(){

    }

    public DiaTrabalhado(Long id, Long folhaPagamentoId, String nomeProjeto, LocalDate dataServico, LocalTime horaInicio, LocalTime horaFim, boolean feriado, boolean viagem, double valorRecebido){

        this.id = id;
        this.folhaPagamentoId = folhaPagamentoId;
        this.nomeProjeto = nomeProjeto;
        this.dataServico = dataServico;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.feriado = feriado;
        this.viagem = viagem;
        this.valorRecebido = valorRecebido;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
         return id;
    }

    public void setFolhaPagamentoId(Long folhaPagamentoId) {
        this.folhaPagamentoId = folhaPagamentoId;
    }

    public Long getFolhaPagamentoId() {
        return folhaPagamentoId;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }
    public LocalDate getDataServico() {
        return dataServico;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public void setFeriado(boolean feriado) {
        this.feriado = feriado;
    }
    
    public boolean isFeriado() {
        return feriado;
    }

    public void setViagem(boolean viagem) {
        this.viagem = viagem;
    }

    public boolean isViagem() {
        return viagem;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }



}
