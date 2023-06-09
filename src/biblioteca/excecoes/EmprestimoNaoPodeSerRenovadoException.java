package biblioteca.excecoes;

public class EmprestimoNaoPodeSerRenovadoException extends Exception {
  public EmprestimoNaoPodeSerRenovadoException() {
    super("O empréstimo não pode ser renovado.");
  }
}
