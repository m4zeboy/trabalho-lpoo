package biblioteca.operacoes;

import javax.swing.*;

public class Grafica extends Operacoes {
  @Override
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }

  @Override
  public void mostrarMensagemDeOpcaoInvalida() {
    JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
  }
}
