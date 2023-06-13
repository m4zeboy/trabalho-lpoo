package biblioteca.emprestimo;

import biblioteca.excecoes.EmprestimoNaoPodeSerRenovadoException;
import biblioteca.exemplar.Exemplar;
import biblioteca.excecoes.EmprestimoJaDevolvidoException;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Emprestimo implements Serializable {
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
  public StatusDoEmprestimo getStatus() {
    LocalDate hoje = LocalDate.now();
    if(dataDevolucao != null) {
      if(dataDevolucao.isAfter(vencimento)) return StatusDoEmprestimo.DEVOLVIDO_COM_ATRASO;
      else return StatusDoEmprestimo.DEVOLVIDO_NO_PRAZO;
    } else {
      if(hoje.isAfter(vencimento)) return StatusDoEmprestimo.EM_ATRASO;
      else return StatusDoEmprestimo.AGUARDANDO_DEVOLUCAO;
    }


  }
  public Multa devolver() throws EmprestimoJaDevolvidoException {
    if(dataDevolucao != null) {
      throw new EmprestimoJaDevolvidoException();
    }
    dataDevolucao = LocalDate.now();
    if(getStatus() == StatusDoEmprestimo.DEVOLVIDO_COM_ATRASO) {
      return new Multa(this);
    }
    return null;
  }
  public void renovar() throws EmprestimoNaoPodeSerRenovadoException {
    if(getStatus() == StatusDoEmprestimo.AGUARDANDO_DEVOLUCAO) {
      int dias;
      if(usuario instanceof Servidor) dias = Servidor.tempoDeEmprestimo;
      else dias = Aluno.tempoDeEmprestimo;
      this.vencimento = this.vencimento.plusDays(dias);
    } else throw new EmprestimoNaoPodeSerRenovadoException();
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
