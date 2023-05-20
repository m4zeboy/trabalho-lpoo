package biblioteca.operacoes;

import biblioteca.exemplar.Exemplar;
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

  public Usuario criarUsuario(ArrayList<Usuario> usuarios) {
    String nome = JOptionPane.showInputDialog("Nome: ");
    String cpf = JOptionPane.showInputDialog("CPF: ");

    Usuario temp = buscarUsuario(usuarios, cpf);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um usuário com esse CPF.");
      return null;
    }
    String[] tipos = {"Aluno", "Servidor"};
    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Usuário você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
    if(tipo == 1) {
      String siape = JOptionPane.showInputDialog(null, "SIAPE: ");
      Servidor servidorTemp = buscaServidorPorSiape(usuarios, siape);
      if(servidorTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
        return null;
      }
      return new Servidor(nome, cpf, siape);
    }
    String rga = JOptionPane.showInputDialog(null, "RGA: ");
    Aluno alunoTemp = buscaAlunoPorRga(usuarios, rga);
    if(alunoTemp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
      return null;
    }
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

  public Usuario buscarUsuario(ArrayList<Usuario> usuarios) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) { return usuario; }
    }
    return null;
  }

  public Servidor buscaServidorPorSiape(ArrayList<Usuario> usuarios, String siape) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Servidor) {
        if(((Servidor) usuario).getSiape() == Integer.parseInt(siape)) {
          return ((Servidor) usuario);
        }
      }
    }
    return null;
  }

  public Aluno buscaAlunoPorRga(ArrayList<Usuario> usuarios, String rga) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Aluno) {
        if(((Aluno) usuario).getRga() == Integer.parseInt(rga)) {
          return ((Aluno) usuario);
        }
      }
    }
    return null;
  }


  public Usuario buscarUsuario(ArrayList<Usuario> usuarios, String cpf) {
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) { return usuario; }
    }
    return null;
  }

  public int selecionarOpcaoDeEditarUsuario() {
    String mensagem = "1 - Editar Nome\n";
    mensagem += "2 - Editar CPF\n";
    mensagem += "3 - Editar RGA/SIAPE\n";
    mensagem += "4 - Voltar\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
  }

  public void editarNomeDoUsuario(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, MENSAGEM_USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo Nome: ");
    usuario.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
  }
  public void editarCPFDoUsuario(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, MENSAGEM_USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoCPF = JOptionPane.showInputDialog("Novo CPF: ");
    /* Verificar se já existe um usuário com esse cpf */
    Usuario temp = buscarUsuario(usuarios, novoCPF);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe um usuário com esse CPF.");
      return;
    }
    usuario.setCpf(novoCPF);
    JOptionPane.showMessageDialog(null, "CPF Editado com sucesso.");
  }
  public void editarRGAOuSIAPEDoUsuario(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, MENSAGEM_USUARIO_NAO_ENCONTRADO);
      return;
    }
    if(usuario instanceof Servidor) {
      String novoSIAPE = JOptionPane.showInputDialog("Novo SIAPE: ");
      Servidor servidorTemp = buscaServidorPorSiape(usuarios, novoSIAPE);
      if(servidorTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Servidor com esse SIAPE.");
        return;
      }
      ((Servidor) usuario).setSiape(Integer.parseInt(novoSIAPE));
      JOptionPane.showMessageDialog(null, "SIAPE Atualizado com sucesso.");
    } else {
      String novoRGA = JOptionPane.showInputDialog("Novo RGA: ");
      Aluno alunoTemp = buscaAlunoPorRga(usuarios, novoRGA);
      if(alunoTemp != null) {
        JOptionPane.showMessageDialog(null, "Já existe um Aluno com esse RGA.");
        return;
      }
      ((Aluno) usuario).setRga(Integer.parseInt(novoRGA));
      JOptionPane.showMessageDialog(null, "RGA Atualizado com sucesso.");
    }
  }

  /* Exemplares */
  public int selecionarOpcaoDeExemplares() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Consultar por código\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem += "5 - Listar todos os cadastros\n";
    mensagem += "6 - Voltar\n\n";
    String opcao = JOptionPane.showInputDialog(mensagem);
    return Integer.parseInt(opcao);
  }
  public Exemplar criarExemplar() {
    return null;
  }
}
