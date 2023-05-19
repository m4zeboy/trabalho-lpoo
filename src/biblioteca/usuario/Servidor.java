package biblioteca.usuario;

public class Servidor extends Usuario {
  private int siape;
  public Servidor(String nome, String cpf, int siape) {
    super(nome, cpf);
    this.siape = siape;
  }
  public Servidor(String nome, String cpf, String siape) {
    super(nome, cpf);
    this.siape = Integer.parseInt(siape);
  }
  public int getSiape() {
    return siape;
  }
  public void setSiape(int siape) {
    this.siape = siape;
  }

  public String toString() {
    String saida = super.toString();
    saida += "SIAPE: " + this.siape + "\n";
    return saida;
  }
}
