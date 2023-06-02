package biblioteca.operacoes;

import javax.swing.*;
import java.awt.*;

public class PainelGrafico {
  public static void mostrarMensagemComScroll(String titulo, String mensagem) {
    JTextArea textArea = new JTextArea(mensagem);
    JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
    JOptionPane.showMessageDialog(null, scrollPane, titulo,
            JOptionPane.YES_NO_OPTION);
  }
}
