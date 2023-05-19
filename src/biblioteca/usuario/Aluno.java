package biblioteca.usuario;

public class Aluno extends Usuario {
  private int rga;

  public Aluno(String nome, String cpf, int rga) {
    super(nome, cpf);
    this.rga = rga;
  }

  public int getRga() {
    return rga;
  }

  public void setRga(int rga) {
    this.rga = rga;
  }
}
