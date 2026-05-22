package edu.folhaPgto.entity;

import java.time.LocalDate;

public class FolhaPagamento {

    private Long id;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private double valorTotal;

    public FolhaPagamento() {
    }

    public FolhaPagamento(
            Long id,
            Funcionario funcionario,
            LocalDate dataPagamento,
            double valorTotal
    ) {
        this.id = id;
        this.funcionario = funcionario;
        this.dataPagamento = dataPagamento;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}