package biblioteca.exemplar;

import biblioteca.Categoria;
import biblioteca.emprestimo.Emprestimo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Exemplar {
  protected int id;
  protected String titulo;

  protected ArrayList<Categoria> categorias;
  protected ArrayList<Emprestimo> emprestimos;
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

  public boolean temEmprestmos() {
    if(emprestimos.size() > 0) return true;
    return false;
  }

}
