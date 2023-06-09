package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.*;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;
import biblioteca.verificacoes.VerificacoesUsuarioEmprestimo;
import biblioteca.verificacoes.VerificacoesUsuarioReserva;

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

    try {
      naoExisteUsuarioComEsseCPF(usuarios, cpf);
      String[] tipos = { "Aluno", "Servidor" };
      int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Usuário você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
      switch (tipo) {
        case 0:
          String rga = LeituraDeDadosDoUsuario.lerRga();
          try {
            int rgaInt = Integer.parseInt(rga);
            naoExisteAlunoComEsseRGA(usuarios, rgaInt);
            usuarios.add(new Aluno(nome,cpf,rga));
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
          } catch (AlunoJaCadastradoComEsseRGAException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
          }
          break;
        case 1:
          String siape = LeituraDeDadosDoUsuario.lerSiape();
          try {
            int siapeInt = Integer.parseInt(siape);
            naoExisteServidorComEsseSiape(usuarios, siapeInt);
            usuarios.add(new Servidor(nome, cpf, siape));
          } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
          } catch (ServidorJaCadastradoComEsseSIAPEException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
          }
          break;
      }

    } catch (UsuarioJaCadastradoComEsseCPFException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }


  }
  /* a função consultarPorCPF() faz a leitura do cpf, busca por um usuário correspondente e mostra na tela seus dados caso encontre */
  public void consultarPorCPF(ArrayList<Usuario> usuarios) {
    String cpf = LeituraDeDadosDoUsuario.lerCpf();
    try {
      Usuario usuario = buscarPorCPF(usuarios,cpf);
      JOptionPane.showMessageDialog(null, usuario);
    } catch (UsuarioNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  /* a função buscarPorCPF() faz a leitura do cpf, busca por um correspondente e se encontrar, retorna-o */
  public Usuario buscarPorCPF(ArrayList<Usuario> usuarios) throws UsuarioNaoEncontradoException {
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
    try {
      Usuario usuario = buscarPorCPF(usuarios);
      String novoNome = LeituraDeDadosDoUsuario.lerNome();
      usuario.setNome(novoNome);
      JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
    } catch (UsuarioNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
  /* a função editarNome busca por um usuário pelo cpf e se não existir um usuário com o novo cpf informado, a informação é atualizada. */
  public void editarCPF(ArrayList<Usuario> usuarios) {
    try {
      Usuario usuario = buscarPorCPF(usuarios);
      String novoCPF = LeituraDeDadosDoUsuario.lerCpf();
      /* Verificar se já existe um usuário com esse cpf */
      naoExisteUsuarioComEsseCPF(usuarios, novoCPF);
      usuario.setCpf(novoCPF);
      JOptionPane.showMessageDialog(null, "CPF Editado com sucesso.");
    } catch (UsuarioNaoEncontradoException | UsuarioJaCadastradoComEsseCPFException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  /* a função editarRGAOuSIAPE() busca por um usuário pelo cpf e de acordo com o tipo de usuário é atualizado o rga ou siape, se já existir um usuário com dado correspondente ao novo a atualização não é feita. */
  public void editarRGAOuSIAPE(ArrayList<Usuario> usuarios) {
    try {
      Usuario usuario = buscarPorCPF(usuarios);
      if(usuario instanceof Servidor) {
        String novoSIAPE = LeituraDeDadosDoUsuario.lerSiape();
        try {
          int novoSiapeInt = Integer.parseInt(novoSIAPE);
          naoExisteServidorComEsseSiape(usuarios, novoSiapeInt);
          ((Servidor) usuario).setSiape(novoSiapeInt);
          JOptionPane.showMessageDialog(null, "SIAPE Atualizado com sucesso.");
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
        } catch (ServidorJaCadastradoComEsseSIAPEException exception) {
          JOptionPane.showMessageDialog(null, exception.getMessage());
        }
      } else {
        String novoRGA = LeituraDeDadosDoUsuario.lerRga();
        try {
          int novoRgaInt = Integer.parseInt(novoRGA);
          naoExisteAlunoComEsseRGA(usuarios, novoRgaInt);
          ((Aluno) usuario).setRga(Integer.parseInt(novoRGA));
          JOptionPane.showMessageDialog(null, "RGA Atualizado com sucesso.");
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
        } catch (AlunoJaCadastradoComEsseRGAException exception) {
          JOptionPane.showMessageDialog(null, exception.getMessage());
        }
      }
    } catch (UsuarioNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  /* a função excluir() busca por um usuário pelo cpf e se ele não tiver reservas ou empréstimo, é feito a sua remoção da lista de usuários */
  public void excluir(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos) {
    try {
      Usuario usuario = buscarPorCPF(usuarios);
      VerificacoesUsuarioReserva.naoTemReservas(reservas, usuario);
      VerificacoesUsuarioEmprestimo.naoTemEmprestimo(emprestimos, usuario);
      usuarios.remove(usuario);
      JOptionPane.showMessageDialog(null, "Usuário #" + usuario.getId() + " excluido.");
    } catch (UsuarioNaoEncontradoException |
             UsuarioTemEmprestimosAssociadoException |
             UsuarioTemReservasAssociadasException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
}
