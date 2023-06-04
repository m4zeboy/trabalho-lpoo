package biblioteca.operacoes.usuario;

import javax.swing.JOptionPane;

public class LeituraDeDadosDoUsuario {
  public static String lerNome() {
    return JOptionPane.showInputDialog("Nome: ");
  }
  public static String lerCpf() {
    return JOptionPane.showInputDialog("CPF: ");
  }
  public static String lerRga() {
    return JOptionPane.showInputDialog("RGA: ");
  }
  public static String lerSiape() {
    return JOptionPane.showInputDialog("SIAPE: ");
  }
}
