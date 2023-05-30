package biblioteca.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public abstract class Usuario {
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
  public ArrayList<Reserva> getReservas(ArrayList<Reserva> reservas){
    ArrayList<Reserva> reservasAssociadas = new ArrayList<>();
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(this)) {
        reservasAssociadas.add(reserva);
      }
    }
    return reservasAssociadas;
  }
  public boolean temReservasAtivasNoPeriodo(ArrayList<Reserva> reservas,LocalDate inicio, LocalDate fim) {
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(this)) {
        if(reserva.getDataReserva().isEqual(inicio) && reserva.getDataExpiracao().isEqual(fim)) return true;
      }
    }
    return false;
  }
  public boolean temReservas(ArrayList<Reserva> reservas) { return getReservas(reservas).size() > 0; }
  public ArrayList<Emprestimo> getEmprestimos(ArrayList<Emprestimo> emprestimos) {
    ArrayList<Emprestimo> emprestimosAssociados = new ArrayList<>();
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(this)) {
        emprestimosAssociados.add(emprestimo);
      }
    }
    return emprestimosAssociados;
  }

  public boolean temEmprestimo(ArrayList<Emprestimo> emprestimos) {
    if(getEmprestimos(emprestimos).size() > 0) return true;
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

  public String toString() {
    String saida = "Usuario #" + this.id +"\n";
    saida += "Nome: " + this.nome + "\n";
    saida += "CPF: " + this.cpf + "\n";
    return saida;
  }

}
