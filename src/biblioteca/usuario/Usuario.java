package biblioteca.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public abstract class Usuario implements Serializable {
  private int id;
  protected String nome;
  protected String cpf;
  protected ArrayList<Reserva> reservas;
  /* Construtor usado para semear o programa */
  public Usuario(int id, String nome, String cpf) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
  }
  public Usuario(String nome, String cpf) {
    this.id = new Random().nextInt(10000);
    this.nome = nome;
    this.cpf = cpf;
    this.reservas = new ArrayList<>();
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

}
