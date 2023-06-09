package biblioteca.excecoes;

public class UsuarioNaoEncontradoException extends Exception {
  public UsuarioNaoEncontradoException() {
    super("Usuário não encontrado.");
  }
}
