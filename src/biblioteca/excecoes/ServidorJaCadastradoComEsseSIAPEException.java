package biblioteca.excecoes;

public class ServidorJaCadastradoComEsseSIAPEException extends Exception {
  public ServidorJaCadastradoComEsseSIAPEException() {
    super("Servidor já cadastrado com esse SIAPE.");
  }
}
