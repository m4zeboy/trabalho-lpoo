package biblioteca;

import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class Emprestimo {
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

  public Usuario getUsuario() {
    return usuario;
  }

  public Exemplar getExemplar() {
    return exemplar;
  }

  public void devolver() {
    if(dataDevolucao == null) {
      dataDevolucao = LocalDate.now();
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
    return saida;
  }

}
