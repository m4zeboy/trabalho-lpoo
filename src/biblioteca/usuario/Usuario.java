package biblioteca.usuario;

import java.util.Random;

public abstract class Usuario {
  protected int id;
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
}
