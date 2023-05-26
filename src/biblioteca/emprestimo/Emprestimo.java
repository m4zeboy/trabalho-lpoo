package biblioteca.emprestimo;

import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import javax.swing.*;
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

  private Multa multa;

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

  public boolean devolver() {
    if(dataDevolucao == null) {
      dataDevolucao = LocalDate.now();
      if(getStatus().equals("Devolvido com atraso")) {
        this.multa = new Multa(this);
      }
      return true;
    } else {
      return false;
    }
  }

  public void renovar() {
    if(getStatus().equals("Aguardando Devolução")) {
      if(usuario instanceof Aluno) {
        this.vencimento = vencimento.plusDays(Aluno.tempoDeEmprestimo);
      } else if(usuario instanceof Servidor) {
        this.vencimento = vencimento.plusDays(Servidor.tempoDeEmprestimo);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Não é possível renovar esse empréstimo.");
    }
  }
  public String getStatus() {
    LocalDate hoje = LocalDate.now();
    if(dataDevolucao == null) {
      if(hoje.isAfter(vencimento)) {
        return "Em atraso";
      } else {
        return "Aguardando Devolução";
      }
    } else {
      if(dataDevolucao.isAfter(vencimento)) {
        return "Devolvido com atraso";
      } else {
        return  "Devolvido no prazo";
      }
    }
  }

  public Multa getMulta() {
    return multa;
  }

  public String toString() {
    String saida = "Empréstimo #" + id + "\n";
    saida += "Usuário: " + usuario.getNome() + "\n";
    saida += "Exemplar: " + exemplar.getTitulo() + "\n";
    saida += "Status: " + getStatus() + "\n";
    saida += "Data do Empréstimo: " + dataEmprestimo + "\n";
    saida += "Vencimento: " + vencimento + "\n";
    if(dataDevolucao != null) {
      saida += "Data da devolução: "+ dataDevolucao + "\n";
    }
    if(multa != null) {
      saida += multa;
    }
    return saida;
  }

}
