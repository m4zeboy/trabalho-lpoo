package biblioteca.usuario;

public class Aluno extends Usuario {
  public static int tempoDeEmprestimo = 7;
  private int rga;

  public Aluno(int id,String nome, String cpf, int rga) {
    super(id,nome, cpf);
    this.rga = rga;
  }
  public Aluno(String nome, String cpf, int rga) {
    super(nome, cpf);
    this.rga = rga;
  }
  public Aluno(String nome, String cpf, String rga) {
    super(nome, cpf);
    this.rga = Integer.parseInt(rga);
  }



  public int getRga() {
    return rga;
  }

  public void setRga(int rga) {
    this.rga = rga;
  }

  public String toString() {
    String saida = super.toString();
    saida += "RGA: " + this.rga + "\n";
    return saida;
  }
}
