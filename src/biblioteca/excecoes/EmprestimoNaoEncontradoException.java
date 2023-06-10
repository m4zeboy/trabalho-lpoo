package biblioteca.excecoes;

public class EmprestimoNaoEncontradoException extends Exception {
  public EmprestimoNaoEncontradoException() {
    super("Empréstimo não encontrado.");
  }
}
