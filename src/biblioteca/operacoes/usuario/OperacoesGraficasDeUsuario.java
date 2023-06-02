package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.operacoes.OperacoesAntiga;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import javax.swing.*;
import java.util.ArrayList;

public class OperacoesGraficasDeUsuario extends OperacoesDeUsuario {
  public int selecionarOpcao() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
  }
  public void listar(ArrayList<Usuario> usuarios) {
    if(usuarios.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há usuários.");
    }
    String saida = "";
    for(Usuario usuario: usuarios) {
      saida += usuario.toString();
      saida += "==================================================\n";
    }
    PainelGrafico.mostrarMensagemComScroll("Lista de Usuários",saida);
  }
  public Usuario criar(ArrayList<Usuario> usuarios) {
    String nome = JOptionPane.showInputDialog("Nome: ");
    String cpf = JOptionPane.showInputDialog("CPF: ");

    Usuario temp = consultarPorCPF(usuarios, cpf);
    if(temp != null) {
      JOptionPane.showMessageDialog(null,"Já existe um usuário com esse CPF.");
      return null;
    }
    String[] tipos = {"Aluno", "Servidor"};
    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Usuário você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
    if(tipo == 1) {
      String siape = JOptionPane.showInputDialog(null, "SIAPE: ");
      Servidor servidorTemp = buscarPorSiape(usuarios, siape);
      if(servidorTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
        return null;
      }
      return new Servidor(nome, cpf, siape);
    }
    String rga = JOptionPane.showInputDialog(null, "RGA: ");
    Aluno alunoTemp = buscarPorRga(usuarios, rga);
    if(alunoTemp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
      return null;
    }
    return new Aluno(nome,cpf,rga);
  }
  public void consultarPorCPF(ArrayList<Usuario> usuarios) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) {
        JOptionPane.showMessageDialog(null, usuario);
        return;
      }
    }
    JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
  }
  public Usuario buscarPorCPF(ArrayList<Usuario> usuarios) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) {
        return usuario;
      }
    }
    return null;
  }
  public int selecionarOpcaoDeEditar() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenuEditar()));
  }
  public void editarNome(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo Nome: ");
    usuario.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
  }
  public void editarCPF(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoCPF = JOptionPane.showInputDialog("Novo CPF: ");
    /* Verificar se já existe um usuário com esse cpf */
    Usuario temp = consultarPorCPF(usuarios, novoCPF);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um usuário com esse CPF.");
      return;
    }
    usuario.setCpf(novoCPF);
    JOptionPane.showMessageDialog(null, "CPF Editado com sucesso.");
  }
  public void editarRGAOuSIAPE(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return;
    }
    if(usuario instanceof Servidor) {
      String novoSIAPE = JOptionPane.showInputDialog("Novo SIAPE: ");
      Servidor servidorTemp = buscarPorSiape(usuarios, novoSIAPE);
      if(servidorTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
        return;
      }
      ((Servidor) usuario).setSiape(Integer.parseInt(novoSIAPE));
      JOptionPane.showMessageDialog(null, "SIAPE Atualizado com sucesso.");
    } else {
      String novoRGA = JOptionPane.showInputDialog("Novo RGA: ");
      Aluno alunoTemp = buscarPorRga(usuarios, novoRGA);
      if(alunoTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
        return;
      }
      ((Aluno) usuario).setRga(Integer.parseInt(novoRGA));
      JOptionPane.showMessageDialog(null, "RGA Atualizado com sucesso.");
    }
  }
  public void excluir(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return;
    }
    if(usuario.temEmprestimo(emprestimos) || usuario.temReservas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possivel excluir o usuário pois, ele tem empréstimos ou reservas em seu nome.");
      return;
    }
    usuarios.remove(usuario);
    JOptionPane.showMessageDialog(null, "Usuário #" + usuario.getId() + " excluido.");
  }
}
