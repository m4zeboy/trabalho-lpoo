package biblioteca.excecoes;

public class OExemplarEstaReservadoParaOutroUsuarioException extends Exception {
  public OExemplarEstaReservadoParaOutroUsuarioException() {
    super("O exemplar está reservado para outro usuário.");
  }
}
