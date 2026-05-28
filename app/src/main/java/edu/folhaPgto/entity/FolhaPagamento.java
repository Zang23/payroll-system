package edu.folhaPgto.entity;

import java.time.LocalDate;

public class FolhaPagamento {

    private Long id;
    private Long funcionarioId;
    private double valorHora;
    private int totalDiasTrabalhados;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private double valorTotal;

    public FolhaPagamento() {

    }

    public FolhaPagamento(
        Long id,
        Long funcionarioId,
        double valorHora,
        int totalDiasTrabalhados,
        LocalDate dataInicial,
        LocalDate dataFinal,
        double valorTotal
    ) {

        this.id = id;
        this.funcionarioId = funcionarioId;
        this.valorHora = valorHora;
        this.totalDiasTrabalhados = totalDiasTrabalhados;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public int getTotalDiasTrabalhados() {
        return totalDiasTrabalhados;
    }

    public void setTotalDiasTrabalhados(int totalDiasTrabalhados) {
        this.totalDiasTrabalhados = totalDiasTrabalhados;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}