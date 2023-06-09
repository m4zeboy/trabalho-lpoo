package biblioteca.excecoes;

public class UsuarioTemEmprestimosAssociadoException extends Exception {
  public UsuarioTemEmprestimosAssociadoException() {
    super("O usuário tem empréstimos associados.");
  }
}
