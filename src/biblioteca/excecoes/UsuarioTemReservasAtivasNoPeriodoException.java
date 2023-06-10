package biblioteca.excecoes;

public class UsuarioTemReservasAtivasNoPeriodoException extends Exception {
  public UsuarioTemReservasAtivasNoPeriodoException() {
    super("O usuário tem reservas ativas no período.");
  }
}
