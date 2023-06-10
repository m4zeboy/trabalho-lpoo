package biblioteca.excecoes;

public class UsuarioJaTemReservaAtivaParaEsseExemplarException extends Exception {
  public UsuarioJaTemReservaAtivaParaEsseExemplarException() {
    super("O usúario já tem reservas ativas para esse exemplar.");
  }
}
