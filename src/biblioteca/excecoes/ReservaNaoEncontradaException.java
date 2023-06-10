package biblioteca.excecoes;

public class ReservaNaoEncontradaException extends Exception {
  public ReservaNaoEncontradaException() {
    super("Reserva não encontrada.");
  }
}
