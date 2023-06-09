package biblioteca.excecoes;

public class UsuarioTemReservasAssociadasException extends Exception {
  public UsuarioTemReservasAssociadasException() {
    super("O usuário tem reservas associadas.");
  }
}
