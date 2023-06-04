package biblioteca.operacoes.reservas;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeReserva {
  public static String RESERVA_NAO_ENCONTRADA = "Reserva não encontrada.";
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
  public static Reserva buscarPorCodigo(ArrayList<Reserva> reservas, int codigo) {
    for(Reserva reserva: reservas) {
      if(reserva.getId() == codigo) {
        return reserva;
      }
    }
    return null;
  }
  public abstract void consultarPorCodigo(ArrayList<Reserva> reservas);
  public abstract void cancelar(ArrayList<Reserva> reservas);
  public abstract void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas);
  public abstract void consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeriodo(ArrayList<Reserva> reservas, ArrayList<Categoria> categorias);
}
