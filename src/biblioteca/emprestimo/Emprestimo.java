package biblioteca.emprestimo;

import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.excecoes.EmprestimoJaDevolvidoException;
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

  public Emprestimo(int id,Usuario usuario, Exemplar exemplar) {
    this.id = id;
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataEmprestimo = LocalDate.now();
    if(usuario instanceof Aluno) {
      this.vencimento = dataEmprestimo.plusDays(Aluno.tempoDeEmprestimo);
    } else if(usuario instanceof Servidor) {
      this.vencimento = dataEmprestimo.plusDays(Servidor.tempoDeEmprestimo);
    }
  }
  public Emprestimo(Usuario usuario, Exemplar exemplar) {
    this.id = new Random().nextInt(10000);
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataEmprestimo = LocalDate.now();
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

  public Multa devolver() throws EmprestimoJaDevolvidoException {
    if(dataDevolucao != null) {
      throw new EmprestimoJaDevolvidoException();
    }
    dataDevolucao = LocalDate.now();
    if(getStatus().equals("Devolvido com atraso")) {
      return new Multa(this);
    }
    return null;
  }

  public boolean renovar() {
    if(getStatus().equals("Aguardando devolução")) {
      int dias;
      if(usuario instanceof Servidor) dias =Servidor.tempoDeEmprestimo;
      else dias = Aluno.tempoDeEmprestimo;
      this.vencimento = this.vencimento.plusDays(dias);
      return true;
    } else return false;
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
