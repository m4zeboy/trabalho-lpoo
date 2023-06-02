package biblioteca.operacoes;

import javax.swing.*;
import java.awt.*;

public class Grafica extends Operacoes {
  @Override
  public int selecionarOpcao() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
  }

}
