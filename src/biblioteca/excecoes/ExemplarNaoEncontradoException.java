package biblioteca.excecoes;

public class ExemplarNaoEncontradoException extends Exception {
  public ExemplarNaoEncontradoException() {
    super("Exemplar não encontrado.");
  }
}
