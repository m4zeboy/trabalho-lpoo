package biblioteca.exemplar;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Exemplar {
  protected int id;
  protected String titulo;

  protected ArrayList<Categoria> categorias;
  protected ArrayList<Emprestimo> emprestimos;
  protected ArrayList<Reserva> reservas;
  public Exemplar(String titulo) {
    this.id = new Random().nextInt(10000);
    this.titulo = titulo;
    this.categorias = new ArrayList<>();
    this.emprestimos = new ArrayList<>();
    this.reservas = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public ArrayList<Categoria> getCategorias() {
    return categorias;
  }

  public void adicionarCategoria(Categoria categoria) {
    categorias.add(categoria);
  }

  public void adicionarEmprestimo(Emprestimo emprestimo) { emprestimos.add(emprestimo); }

  public void adcionarReserva(Reserva reserva) { reservas.add(reserva); }

  public boolean temReservas() { return reservas.size() > 0; }
  public Emprestimo getEmprestimoAtual() {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getStatus().equals("Aguardando Devolução")) {
        return emprestimo;
      }
    }
    return null;
  }

  public String toString() {
    String saida = "Exemplar #" + id + "\n";
    saida += "Título: " + titulo + "\n";
    saida += "Categorias: ";
    for(Categoria categoria: categorias) {
      saida += categoria.getNome() + ", ";
    }
    saida += "\n";
    return saida;
  }
  public String getStatus() {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getStatus().equals("Aguardando devolução") || emprestimo.getStatus().equals("Em atraso")) {
        return "Emprestado";
      }
    }
    return "Disponível";
  }
  public boolean estaDisponivel() {
    return getStatus().equals("Disponível");
  }
  public boolean temEmprestimos() {
    if(emprestimos.size() > 0) return true;
    return false;
  }
  public boolean temReservasAtivas() {
    for(Reserva reserva: reservas) {
      if(reserva.estaAtiva()) return true;
    }
    return false;
  }
  public Reserva getUltimaReserva() {
    ArrayList<Reserva> reservasAtivas = new ArrayList<>();
    for(Reserva reserva: reservas) {
      if(reserva.estaAtiva()) reservasAtivas.add(reserva);
    }
    return reservasAtivas.get(reservasAtivas.size()-1);
  }

  public Reserva getProximaReserva() {
    for(Reserva reserva: reservas) {
      if(reserva.estaAtiva()) {
        return reserva;
      }
    }
    return null;
  }

}
