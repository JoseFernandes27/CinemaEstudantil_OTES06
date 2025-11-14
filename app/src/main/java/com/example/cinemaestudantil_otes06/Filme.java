package com.example.cinema_estudantiltrabalhofinal;

public class Filme {
    private int id_filme;
    private String titulo;
    private String genero;
    private int duracao;
    private int classificacao;

    public Filme(int id_filme, String titulo, String genero, int duracao, int classificacao) {
        this.id_filme = id_filme;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.classificacao = classificacao;
    }

    public int getId() {
        return id_filme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + 
               " | Duração: " + duracao + "min" +
               " | Classificação: " + classificacao + " anos";
    }

    public String exibirInfoFilme() {
        return "Título: " + titulo + "\n" +
               "Gênero: " + genero + "\n" +
               "Duração: " + duracao + " minutos\n" +
               "Classificação: " + classificacao + " anos";
    }
}
