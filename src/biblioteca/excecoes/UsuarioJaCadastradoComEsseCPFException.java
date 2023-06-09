package biblioteca.excecoes;

public class UsuarioJaCadastradoComEsseCPFException extends Exception {
  public UsuarioJaCadastradoComEsseCPFException() {
    super("Já existe um usuário com esse CPF.");
  }
}
