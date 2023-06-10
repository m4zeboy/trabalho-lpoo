package biblioteca.verificacoes;

import biblioteca.Reserva;
import biblioteca.excecoes.OExemplarEstaReservadoParaOutroUsuarioException;
import biblioteca.excecoes.UsuarioTemReservasAssociadasException;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.reservas.OperacoesDeReserva;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public class VerificacoesUsuarioReserva {
  private static ArrayList<Reserva> getReservas(ArrayList<Reserva> reservas, Usuario usuario) {
    ArrayList<Reserva> reservasAssociadas = new ArrayList<>();
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(usuario)) {
        reservasAssociadas.add(reserva);
      }
    }
    return reservasAssociadas;
  }
  public static void naoTemReservas(ArrayList<Reserva> reservas, Usuario usuario) throws UsuarioTemReservasAssociadasException {
    if(getReservas(reservas,usuario).size() > 0) throw new UsuarioTemReservasAssociadasException();
  }
  public static void aProximaReservaPertenceAoUsuario(ArrayList<Reserva> reservas, Exemplar exemplar, Usuario usuario)
  throws OExemplarEstaReservadoParaOutroUsuarioException {
    Reserva proxima = OperacoesDeReserva.getProximaReservaParaOExemplar(reservas, exemplar);
    if(proxima.getUsuario().equals(usuario) == false) throw new OExemplarEstaReservadoParaOutroUsuarioException();
    else { proxima.cancelar(); }
  }

}
