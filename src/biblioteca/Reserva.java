package biblioteca;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;
import biblioteca.verificacoes.VerificacoesExemplarReserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Reserva implements Serializable {
  private int id;
  private Usuario usuario;
  private Exemplar exemplar;
  private LocalDate dataReserva;
  private LocalDate dataExpiracao;

  public Reserva(Usuario usuario, Exemplar exemplar, LocalDate dataExpiracao) {
    this.id = new Random().nextInt(10000);
    this.usuario = usuario;
    this.exemplar = exemplar;
    this.dataReserva = LocalDate.now();
    this.dataExpiracao = dataExpiracao;
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

  public LocalDate getDataReserva() {
    return dataReserva;
  }

  public LocalDate getDataExpiracao() {
    return dataExpiracao;
  }
  public boolean estaAtiva() {
    return LocalDate.now().isBefore(dataExpiracao);
  }
  public void cancelar() {
    dataExpiracao = LocalDate.now();
  }

  public static LocalDate calcularDataDeExpiracao(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas, Exemplar exemplar) {
    if(exemplar.estaDisponivel(emprestimos) && !VerificacoesExemplarReserva.temReservasAtivas(reservas, exemplar)) {
      return LocalDate.now().plusDays(1);
    }
    else if(exemplar.estaDisponivel(emprestimos) && VerificacoesExemplarReserva.temReservasAtivas(reservas, exemplar)) {
      Reserva ultima = exemplar.getUltimaReserva(reservas);
      return ultima.getDataExpiracao().plusDays(1);
    }
    else if(!exemplar.estaDisponivel(emprestimos) && !VerificacoesExemplarReserva.temReservasAtivas(reservas, exemplar)) {
      return exemplar.getEmprestimoAtual(emprestimos).getVencimento().plusDays(1);
    }
    else {
      Reserva ultima = exemplar.getUltimaReserva(reservas);
      return ultima.getDataExpiracao().plusDays(1);
    }
  }
  public String toString() {
    String saida = "Reserva #" + id + "\n";
    saida += usuario + "\n";
    saida += exemplar + "\n";
    saida += "Data da reserva: " + dataReserva + "\n";
    saida += "Data da expiração: " + dataExpiracao + "\n";
    saida += "Está ativa: " + (estaAtiva() ? "sim" : "não") + "\n";
    return saida;
  }

}
