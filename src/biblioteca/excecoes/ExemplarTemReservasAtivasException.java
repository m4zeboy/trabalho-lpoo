package biblioteca.excecoes;

public class ExemplarTemReservasAtivasException extends Exception {
  public ExemplarTemReservasAtivasException() {
    super("O Exemplar tem reservas ativas.");
  }
}
