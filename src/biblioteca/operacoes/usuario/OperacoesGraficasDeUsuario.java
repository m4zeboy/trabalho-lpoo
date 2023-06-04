package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import javax.swing.*;
import java.util.ArrayList;

public class OperacoesGraficasDeUsuario extends OperacoesDeUsuario {
  /* a função selecionarOpcao() mostra o menu de gerencia de usuarios para que o usuário escolha uma opção.*/
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }
  /* a função listar() percorre a lista de usuários e mostra na tela em um painel com barra de rolagem. */
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
  /* a função criar le os dados do usuário, instancia um novo objeto e insere-o na lista de usuários */
  public void criar(ArrayList<Usuario> usuarios) {

    String nome = LeituraDeDadosDoUsuario.lerNome();
    String cpf = LeituraDeDadosDoUsuario.lerCpf();

    Usuario temp = buscarPorCPF(usuarios, cpf);
    if(temp != null) {
      JOptionPane.showMessageDialog(null,"Já existe um usuário com esse CPF.");
      return;
    }

    String[] tipos = { "Aluno", "Servidor" };

    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Usuário você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

    if(tipo == 1) {
      String siape = LeituraDeDadosDoUsuario.lerSiape();
      try {
        int siapeInt = Integer.parseInt(siape);
        Servidor servidorTemp = buscarPorSiape(usuarios, siapeInt);
        if(servidorTemp != null) {
          JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
          return;
        }
        usuarios.add(new Servidor(nome, cpf, siape));
      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
        return;
      }
    }
    String rga = LeituraDeDadosDoUsuario.lerRga();
    try {
      int rgaInt = Integer.parseInt(rga);
      Aluno alunoTemp = buscarPorRga(usuarios, rgaInt);
      if(alunoTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
        return;
      }
      usuarios.add(new Aluno(nome,cpf,rga));
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
    }
  }
  /* a função consultarPorCPF() faz a leitura do cpf, busca por um usuário correspondente e mostra na tela seus dados caso encontre */
  public void consultarPorCPF(ArrayList<Usuario> usuarios) {
    String cpf = LeituraDeDadosDoUsuario.lerCpf();
    Usuario usuario = buscarPorCPF(usuarios,cpf);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
      return;
    }
    JOptionPane.showMessageDialog(null, usuario);
  }
  /* a função buscarPorCPF() faz a leitura do cpf, busca por um correspondente e se encontrar, retorna-o */
  public Usuario buscarPorCPF(ArrayList<Usuario> usuarios) {
    String cpf = LeituraDeDadosDoUsuario.lerCpf();
     return buscarPorCPF(usuarios, cpf);
  }
  /* a função selecionarOpcaoDeEditar() mostra o menu de editar usuários para que o usuário escolha uma opção.*/
  public int selecionarOpcaoDeEditar() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenuEditar()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }
  /* a função editarNome busca por um usuário pelo cpf e altera o nome */
  public void editarNome(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoNome = LeituraDeDadosDoUsuario.lerNome();
    usuario.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
  }
  /* a função editarNome busca por um usuário pelo cpf e se não existir um usuário com o novo cpf informado, a informação é atualizada. */
  public void editarCPF(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoCPF = LeituraDeDadosDoUsuario.lerCpf();
    /* Verificar se já existe um usuário com esse cpf */
    Usuario temp = buscarPorCPF(usuarios, novoCPF);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um usuário com esse CPF.");
      return;
    }
    usuario.setCpf(novoCPF);
    JOptionPane.showMessageDialog(null, "CPF Editado com sucesso.");
  }
  /* a função editarRGAOuSIAPE() busca por um usuário pelo cpf e de acordo com o tipo de usuário é atualizado o rga ou siape, se já existir um usuário com dado correspondente ao novo a atualização não é feita. */
  public void editarRGAOuSIAPE(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
      return;
    }
    if(usuario instanceof Servidor) {
      String novoSIAPE = LeituraDeDadosDoUsuario.lerSiape();
      try {
        int novoSiapeInt = Integer.parseInt(novoSIAPE);
        Servidor servidorTemp = buscarPorSiape(usuarios, novoSiapeInt);
        if(servidorTemp != null) {
          JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
          return;
        }
        ((Servidor) usuario).setSiape(novoSiapeInt);
        JOptionPane.showMessageDialog(null, "SIAPE Atualizado com sucesso.");
      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
      }
    } else {
      String novoRGA = LeituraDeDadosDoUsuario.lerRga();
      try {
        int novoRgaInt = Integer.parseInt(novoRGA);
        Aluno alunoTemp = buscarPorRga(usuarios, novoRgaInt);
        if(alunoTemp != null) {
          JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
          return;
        }
        ((Aluno) usuario).setRga(Integer.parseInt(novoRGA));
        JOptionPane.showMessageDialog(null, "RGA Atualizado com sucesso.");
      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
      }
    }
  }
  /* a função excluir() busca por um usuário pelo cpf e se ele não tiver reservas ou empréstimo, é feito a sua remoção da lista de usuários */
  public void excluir(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos) {
    Usuario usuario = buscarPorCPF(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
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
