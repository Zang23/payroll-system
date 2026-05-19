package edu.folhaPgto.entity;

public class FolhaPagamento {

    private Long id;

    private String data;

    private Double salario;

    public FolhaPagamento(
            Long id,
            String data,
            Double salario
    ) {

        this.id = id;
        this.data = data;
        this.salario = salario;

    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Double getSalario() {
        return salario;
    }

}