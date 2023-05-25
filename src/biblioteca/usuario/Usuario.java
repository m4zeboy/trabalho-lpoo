package biblioteca.usuario;

import biblioteca.Emprestimo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Usuario {
  private int id;
  protected String nome;
  protected String cpf;

  public Usuario(String nome, String cpf) {
    this.id = new Random().nextInt(10000);
    this.nome = nome;
    this.cpf = cpf;
  }

  public int getId() {
    return id;
  }
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String toString() {
    String saida = "Usuario #" + this.id +"\n";
    saida += "Nome: " + this.nome + "\n";
    saida += "CPF: " + this.cpf + "\n";
    return saida;
  }

  public boolean temEmprestimo(ArrayList<Emprestimo> emprestimos) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(this)) {
        return true;
      }
    }
    return false;
  }
  public boolean temEmprestimoEmAtraso(ArrayList<Emprestimo> emprestimos) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(this)) {
        if(emprestimo.getStatus().equals("Em atraso")) return true;
      }
    }
    return false;
  }
}
