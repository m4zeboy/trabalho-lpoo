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
  protected ArrayList<Emprestimo> emprestimos;
  protected ArrayList<Reserva> reservas;
  public Usuario(String nome, String cpf) {
    this.id = new Random().nextInt(10000);
    this.nome = nome;
    this.cpf = cpf;
    this.emprestimos = new ArrayList<>();
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

  public void adicionarEmprestimo(Emprestimo emprestimo) { emprestimos.add(emprestimo); }
  public void adicionarReserva(Reserva reserva) { reservas.add(reserva); }
  public boolean temReservasAtivasNoPeriodo(LocalDate inicio, LocalDate fim) {
    for(Reserva reserva: reservas) {
      if(reserva.getDataReserva().isEqual(inicio) && reserva.getDataExpiracao().isEqual(fim)) return true;
    }
    return false;
  }
  public boolean temReservas() { return reservas.size() > 0; }
  public String toString() {
    String saida = "Usuario #" + this.id +"\n";
    saida += "Nome: " + this.nome + "\n";
    saida += "CPF: " + this.cpf + "\n";
    return saida;
  }

  public boolean temEmprestimo() {
    if(emprestimos.size() > 0) return true;
    return false;
  }
  public boolean temEmprestimoEmAtraso() {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(this)) {
        if(emprestimo.getStatus().equals("Em atraso")) return true;
      }
    }
    return false;
  }
}
