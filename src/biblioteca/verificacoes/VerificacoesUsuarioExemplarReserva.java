package biblioteca.verificacoes;

import biblioteca.Reserva;
import biblioteca.excecoes.UsuarioTemReservasAtivasParaOExemplarNoPeriodoException;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.reservas.OperacoesDeReserva;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class VerificacoesUsuarioExemplarReserva {
  public static void usuarioNaoTemReservasParaOExemplarNoPeriodo(ArrayList<Reserva> reservas, Usuario usuario, Exemplar exemplar, LocalDate inicio, LocalDate fim)
          throws UsuarioTemReservasAtivasParaOExemplarNoPeriodoException {
    for(Reserva reserva: reservas) {
      if(reserva.getUsuario().equals(usuario) && reserva.getExemplar().equals(exemplar)) {
        if(OperacoesDeReserva.reservaEstaNoPeriodo(reserva, inicio, fim)) throw new UsuarioTemReservasAtivasParaOExemplarNoPeriodoException();
      }
    }
  }
}
