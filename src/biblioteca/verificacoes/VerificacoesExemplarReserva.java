package biblioteca.verificacoes;

import biblioteca.Reserva;
import biblioteca.excecoes.ExemplarTemReservasAssociadasException;
import biblioteca.excecoes.ExemplarTemReservasAtivasException;
import biblioteca.exemplar.Exemplar;

import java.util.ArrayList;

public class VerificacoesExemplarReserva {
  public static void naoTemReservas(ArrayList<Reserva> reservas, Exemplar exemplar) throws ExemplarTemReservasAssociadasException {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(exemplar)) {
        throw new ExemplarTemReservasAssociadasException();
      }
    }
  }
  public static boolean temReservasAtivas(ArrayList<Reserva> reservas, Exemplar exemplar) {
    for(Reserva reserva: reservas) {
      if(reserva.getExemplar().equals(exemplar) && reserva.estaAtiva()) {
        return true;
      }
    }
    return false;
  }
}
