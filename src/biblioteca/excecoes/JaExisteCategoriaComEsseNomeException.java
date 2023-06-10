package biblioteca.excecoes;

public class JaExisteCategoriaComEsseNomeException extends Exception {
  public JaExisteCategoriaComEsseNomeException() {
    super("Já existe uma categoria com esse nome.");
  }
}
