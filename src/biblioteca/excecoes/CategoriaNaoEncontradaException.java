package biblioteca.excecoes;

public class CategoriaNaoEncontradaException extends Exception {
  public CategoriaNaoEncontradaException() {
    super("Categoria não encontrada.");
  }
}
