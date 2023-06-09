package biblioteca.excecoes;

public class AlunoJaCadastradoComEsseRGAException extends Exception {
  public AlunoJaCadastradoComEsseRGAException() {
    super("Aluno já cadastrado com esse RGA.");
  }
}
