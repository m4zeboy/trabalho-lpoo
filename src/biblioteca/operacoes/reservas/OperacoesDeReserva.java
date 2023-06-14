package biblioteca.operacoes.reservas;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.ReservaNaoEncontradaException;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class OperacoesDeReserva {
  protected String getMenu() {
    String mensagem = "1 - Reservar\n";
    mensagem += "2 - Consultar status de uma reserva\n";
    mensagem += "3 - Cancelar\n";
    mensagem += "4 - Listar reservas ativas para um exemplar\n";
    mensagem += "5 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcao();
  public abstract void reservar(ArrayList<Reserva> reservas, ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos);
  public static Reserva buscarPorCodigo(ArrayList<Reserva> reservas, int codigo)
          throws ReservaNaoEncontradaException {
    for(Reserva reserva: reservas) {
      if(reserva.getId() == codigo) {
        return reserva;
      }
    }
    throw new ReservaNaoEncontradaException();
  }
  public abstract void consultarPorCodigo(ArrayList<Reserva> reservas);
  public abstract void cancelar(ArrayList<Reserva> reservas);
  public abstract void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas);
  public abstract void consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeriodo(ArrayList<Reserva> reservas, ArrayList<Categoria> categorias);
  public static Reserva getProximaReservaParaOExemplar(ArrayList<Reserva> reservas, Exemplar exemplar) {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(exemplar) && reserva.estaAtiva()) {
        return reserva;
      }
    }
    return null;
  }
  public static int getTotalDeExemplaresReservadosPorCategoriaEmUmPeriodo(ArrayList<Reserva> reservas, Categoria categoria, LocalDate inicio, LocalDate fim) {
    int contador = 0;
    for(Reserva reserva: reservas) {
      if(
        (reserva.getDataReserva().isEqual(inicio) || reserva.getDataReserva().isAfter(inicio))
          &&
        (reserva.getDataReserva().isEqual(fim) || reserva.getDataReserva().isBefore(fim))
      ) {
        if(reserva.getExemplar().getCategorias().contains(categoria)) {
          contador++;
        }
      }
    }
    return contador;
  }

  public static boolean reservaEstaNoPeriodo(Reserva reserva, LocalDate inicio, LocalDate fim) {
    return (reserva.getDataReserva().isEqual(inicio) || reserva.getDataReserva().isAfter(inicio)) &&
    (reserva.getDataExpiracao().isBefore(fim) || reserva.getDataExpiracao().isEqual(fim));
  }
}
