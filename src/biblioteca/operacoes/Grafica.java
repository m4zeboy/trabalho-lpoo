package biblioteca.operacoes;

import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.util.ArrayList;

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

  public int selecionarOpcaoDeUsuarios() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Pesquisar por CPF\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem +=  "5 - Listar todos os cadastros\n";
    mensagem +=  "6 - Voltar\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
  }

  public Usuario criarUsuario() {
    String nome = JOptionPane.showInputDialog("Nome: ");
    String cpf = JOptionPane.showInputDialog("Nome: ");
    String[] tipos = {"Aluno", "Servidor"};
    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Usuário você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
    if(tipo == 1) {
      String siape = JOptionPane.showInputDialog(null, "SIAPE: ");
      return new Servidor(nome, cpf, siape);
    }
    String rga = JOptionPane.showInputDialog(null, "RGA: ");
    return new Aluno(nome,cpf,rga);
  }

  public void listarUsuarios(ArrayList<Usuario> usuarios) {
    String saida = "================ LISTA DE USUÁRIOS ===============\n";
    for(Usuario usuario: usuarios) {
      saida += usuario.toString();
      saida += "==================================================\n";
    }
    JOptionPane.showMessageDialog(null,saida);
  }
}
