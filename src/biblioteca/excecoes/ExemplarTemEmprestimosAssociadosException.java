package biblioteca.excecoes;

public class ExemplarTemEmprestimosAssociadosException extends Exception {
  public ExemplarTemEmprestimosAssociadosException() {
    super("O exemplar tem empréstimos associados.");
  }
}
