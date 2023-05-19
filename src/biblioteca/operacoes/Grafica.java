package biblioteca.operacoes;

import javax.swing.*;

public class Grafica extends Operacoes {
  public int selecionarOpcaoPrincipal() {
    String mensagem = "1 - Gerenciar usuários\n";
    mensagem += "2 - Gerenciar exemplares\n";
    mensagem += "3 - Gerenciar categorias\n";
    mensagem += "4 - Gerenciar empréstimos\n";
    mensagem += "5 - Gerenciar reservas\n";
    mensagem +=  "6 - Consultar total de exemplares reservados por categoria em um período\n";
    mensagem +=  "7 - Sair do programa\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
  }
}
