package br.com.projeto.model;

public class Fornecedores extends Clientes{
    
    //Atributos
    
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        
        return this.getNome();
    }
     
    
    
}
