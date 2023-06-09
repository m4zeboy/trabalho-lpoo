package biblioteca.verificacoes;

import biblioteca.Reserva;
import biblioteca.excecoes.UsuarioTemReservasAssociadasException;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
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
  public static boolean temReservaAtivaParaOExemplar(ArrayList<Reserva> reservas, Usuario usuario, Exemplar exemplar) {
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(usuario) && reserva.getExemplar().equals(exemplar) && reserva.estaAtiva()) {
        return true;
      }
    }
    return false;
  }
  public static boolean temReservasAtivasNoPeriodo(ArrayList<Reserva> reservas, Usuario usuario,LocalDate inicio, LocalDate fim) {
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(usuario)) {
        if(reserva.getDataReserva().isEqual(inicio) && reserva.getDataExpiracao().isEqual(fim)) return true;
      }
    }
    return false;
  }
}
