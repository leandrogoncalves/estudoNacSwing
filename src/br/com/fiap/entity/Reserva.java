/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fiap.entity;

/**
 *
 * @author root
 */
public class Reserva {
    private String cpf;
    private String nome;
    private int numeroPassagem;
    private String destino;

    public Reserva(String cpf, String nome, int numeroPassagem, String destino) {
        this.cpf = cpf;
        this.nome = nome;
        this.numeroPassagem = numeroPassagem;
        this.destino = destino;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroPassagem() {
        return numeroPassagem;
    }

    public void setNumeroPassagem(int numeroPassagem) {
        this.numeroPassagem = numeroPassagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Reserva{" + "cpf=" + cpf + ", nome=" + nome + ", numeroPassagem=" + numeroPassagem + ", destino=" + destino + '}';
    }
    
    
    
}
