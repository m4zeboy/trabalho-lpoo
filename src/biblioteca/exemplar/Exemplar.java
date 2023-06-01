package biblioteca.exemplar;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Exemplar {
  protected int id;
  protected String titulo;

  protected ArrayList<Categoria> categorias;
  public Exemplar(int id,String titulo) {
    this.id = id;
    this.titulo = titulo;
    this.categorias = new ArrayList<>();
  }

  public Exemplar(String titulo) {
    this.id = new Random().nextInt(10000);
    this.titulo = titulo;
    this.categorias = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public ArrayList<Categoria> getCategorias() {
    return categorias;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void adicionarCategoria(Categoria categoria) {
    categorias.add(categoria);
  }
  public Emprestimo getEmprestimoAtual(ArrayList<Emprestimo> emprestimos) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getExemplar().equals(this)) {
        if(emprestimo.getStatus().equals("Aguardando devolução") || emprestimo.getStatus().equals("Em atraso")) {
          return emprestimo;
        }
      }
    }
    return null;
  }
  public boolean temEmprestimos(ArrayList<Emprestimo> emprestimos) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getExemplar().equals(this)) {
        return true;
      }
    }
    return false;
  }
  public boolean temReservas(ArrayList<Reserva> reservas) {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(this)) {
        return true;
      }
    }
    return false;
  }
  public boolean temReservasAtivas(ArrayList<Reserva> reservas) {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(this) && reserva.estaAtiva()) {
        return true;
      }
    }
    return false;
  }
  public Reserva getUltimaReserva(ArrayList<Reserva> reservas) {
    ArrayList<Reserva> reservasAtivas = new ArrayList<>();
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(this) && reserva.estaAtiva()) {
        reservasAtivas.add(reserva);
      }
     }
    if(reservasAtivas.size() > 0) return reservasAtivas.get(reservasAtivas.size() - 1);
    return null;
  }
  public Reserva getProximaReserva(ArrayList<Reserva> reservas) {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(this) && reserva.estaAtiva()) {
        return reserva;
      }
    }
    return null;
  }
  public String getStatus(ArrayList<Emprestimo> emprestimos) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getExemplar().equals(this)) {
        if(emprestimo.getStatus().equals("Aguardando devolução") || emprestimo.getStatus().equals("Em atraso")) {
          return "Emprestado";
        }
      }
    }
    return "Disponível";
  }
  public boolean estaDisponivel(ArrayList<Emprestimo> emprestimos) {
    return getStatus(emprestimos).equals("Disponível");
  }
  public LocalDate calcularDataDeExpiracao(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    if(estaDisponivel(emprestimos) && !temReservasAtivas(reservas)) {
      return LocalDate.now().plusDays(1);
    }
    else if(estaDisponivel(emprestimos) && temReservasAtivas(reservas)) {
      Reserva ultima = getUltimaReserva(reservas);
      return ultima.getDataExpiracao().plusDays(1);
    }
    else if(!estaDisponivel(emprestimos) && !temReservasAtivas(reservas)) {
      return getEmprestimoAtual(emprestimos).getVencimento().plusDays(1);
    }
    else {
      Reserva ultima = getUltimaReserva(reservas);
      return ultima.getDataExpiracao().plusDays(1);
    }
  }
  public String toString() {
    String saida = "Exemplar #" + this.id + "\n";
    saida += "Título: " + this.titulo + "\n";
    saida += "Categorias: ";
    for(Categoria categoria: this.categorias) {
      saida += categoria.getNome() + ", ";
    }
    saida += "\n";
    return saida;
  }

}
