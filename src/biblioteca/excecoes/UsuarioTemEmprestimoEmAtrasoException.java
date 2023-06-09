package biblioteca.excecoes;

public class UsuarioTemEmprestimoEmAtrasoException extends Exception {
  public UsuarioTemEmprestimoEmAtrasoException() {
    super("O usuário tem empréstimos em atraso.");
  }
}
