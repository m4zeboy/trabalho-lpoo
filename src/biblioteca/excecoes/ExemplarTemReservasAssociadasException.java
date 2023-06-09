package biblioteca.excecoes;

public class ExemplarTemReservasAssociadasException extends Exception {
  public ExemplarTemReservasAssociadasException() {
    super("O exemplar tem reservas associadas.");
  }
}
