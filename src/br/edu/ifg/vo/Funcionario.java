package br.edu.ifg.vo;

public class Funcionario extends Pessoa {
    private Setor setor;
    
    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
    public Setor getSetor() {
        return this.setor;
    }

}