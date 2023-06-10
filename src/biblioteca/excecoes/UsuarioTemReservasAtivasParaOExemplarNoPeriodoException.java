package biblioteca.excecoes;

public class UsuarioTemReservasAtivasParaOExemplarNoPeriodoException extends Exception {
  public UsuarioTemReservasAtivasParaOExemplarNoPeriodoException() {
    super("O usuário tem reservas ativas para o exemplar no período.");
  }
}
