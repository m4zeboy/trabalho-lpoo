package biblioteca.emprestimo;

import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.Random;

public class Emprestimo {
  public static double valorPorDiaDeAtraso = 2.50;
  private int id;
  private Usuario usuario;
  private Exemplar exemplar;
  private LocalDate dataEmprestimo;
  private LocalDate vencimento;
  private LocalDate dataDevolucao;

  public Emprestimo(Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo) {
    this.id = new Random().nextInt(10000);
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataEmprestimo = dataEmprestimo;
    if(usuario instanceof Aluno) {
      this.vencimento = dataEmprestimo.plusDays(Aluno.tempoDeEmprestimo);
    } else if(usuario instanceof Servidor) {
      this.vencimento = dataEmprestimo.plusDays(Servidor.tempoDeEmprestimo);
    }
  }
  public int getId() {
      return id;
  }
  public Usuario getUsuario() {
    return usuario;
  }

  public Exemplar getExemplar() {
    return exemplar;
  }

  public LocalDate getVencimento() {
    return vencimento;
  }

  public LocalDate getDataDevolucao() {
    return dataDevolucao;
  }

  public Multa devolver() {
    if(dataDevolucao == null) {
      dataDevolucao = LocalDate.now();
      if(getStatus().equals("Devolvido com atraso")) {
        return new Multa(this);
      }
    }
    return null;
  }

  public boolean renovar() {
    if(getStatus().equals("Aguardando Devolução")) {
      if(usuario instanceof Aluno) {
        this.vencimento = vencimento.plusDays(Aluno.tempoDeEmprestimo);
      } else if(usuario instanceof Servidor) {
        this.vencimento = vencimento.plusDays(Servidor.tempoDeEmprestimo);
      }
      return true;
    }
    return false;
  }
  public String getStatus() {
    LocalDate hoje = LocalDate.now();
   if(dataDevolucao != null) {
     if(dataDevolucao.isAfter(vencimento)) return "Devolvido com atraso";
     else return "Devolvido no prazo";
   } else {
     if(hoje.isAfter(vencimento)) return "Em atraso";
     else return "Aguardando devolução";
   }


  }
  public String toString() {
    String saida = "Empréstimo #" + id + "\n";
    saida += usuario + "\n";
    saida += exemplar + "\n";
    saida += "Status: " + getStatus() + "\n";
    saida += "Data do Empréstimo: " + dataEmprestimo + "\n";
    saida += "Vencimento: " + vencimento + "\n";
    if(dataDevolucao != null) {
      saida += "Data da devolução: "+ dataDevolucao + "\n";
    }
    return saida;
  }

}
