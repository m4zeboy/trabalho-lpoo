package biblioteca.operacoes;

import biblioteca.Categoria;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
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
      JOptionPane.showMessageDialog(null, USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo Nome: ");
    usuario.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
  }
  public void editarCPFDoUsuario(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, USUARIO_NAO_ENCONTRADO);
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
      JOptionPane.showMessageDialog(null, USUARIO_NAO_ENCONTRADO);
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
    String titulo = JOptionPane.showInputDialog("Título: ");
    String[] tipos = { "Livro", "Mídia", "Digital"};
    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Exemplar você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
    if(tipo == 1) {
      String tipoArquivo = JOptionPane.showInputDialog("Tipo de Arquivo: ");
      return new Midia(titulo,tipoArquivo);
    } else if(tipo == 2){
      return new Digital(titulo);
    }
    String ano = JOptionPane.showInputDialog("Ano: ");
    return new Livro(titulo, ano);
  }

  public void listarExemplares(ArrayList<Exemplar> acervo) {
    String saida = "==================== ACERVO ====================\n";
    for(Exemplar exemplar: acervo) {
      saida += exemplar.toString();
      saida +=     "================================================\n";
    }
    JOptionPane.showMessageDialog(null,saida);
  }

  public Exemplar buscarExemplarPorCodigo(ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    for(Exemplar exemplar: acervo) {
      if(exemplar.getId() == Integer.parseInt(codigo)) {
        return exemplar;
      }
    }
    return null;
  }
  public Exemplar buscarExemplarPorCodigo(ArrayList<Exemplar> acervo, int codigo) {
    for(Exemplar exemplar: acervo) {
      if(exemplar.getId() == codigo) {
        return exemplar;
      }
    }
    return null;
  }

  public int selecionarOpcaoDeEditarExemplar() {
    String saida = "1 - Editar Título\n";
    saida += "2 - Editar Ano/Tipo de arquivo\n";
    saida += "3 - Adicionar categoria\n";
    saida += "4 - Remover Categoria\n";
    saida += "5 - Voltar\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(saida));
  }

  public void editarTituloDoExemplar(ArrayList<Exemplar> acervo) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarExemplarPorCodigo(acervo, codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String novoTitulo = JOptionPane.showInputDialog("Novo Título: ");
    temp.setTitulo(novoTitulo);
    JOptionPane.showMessageDialog(null,"Título editado com sucesso.");
  }

  public void editarAnoOuTipoDeArquivoDoExemplar(ArrayList<Exemplar> acervo) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarExemplarPorCodigo(acervo, codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    if(temp instanceof Livro) {
      int novoAno = Integer.parseInt(JOptionPane.showInputDialog("Novo ano: "));
      ((Livro) temp).setAno(novoAno);
      JOptionPane.showMessageDialog(null, "Ano editado com sucesso");
    } else if(temp instanceof Midia) {
      String novoTipoDeArquivo = JOptionPane.showInputDialog("Novo Tipo de Arquivo: ");
      ((Midia) temp).setTipoArquivo(novoTipoDeArquivo);
      JOptionPane.showMessageDialog(null, "Tipo de Arquivo editado com sucesso");
    }
  }

  /* Categorias */
  public int selecionarOpcaoDeCategorias() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Consultar por código\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem += "5 - Listar todos os cadastros\n";
    mensagem += "6 - Voltar\n\n";
    String opcao = JOptionPane.showInputDialog(mensagem);
    return Integer.parseInt(opcao);
  }

  public Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias, String nome) {
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }

  public Categoria criarCategoria(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    Categoria temp = buscarCategoriaPorNome(categorias, nome);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe uma categoria com esse nome.");
      return null;
    }
    return new Categoria(nome);
  }

  public void listarCategorias(ArrayList<Categoria> categorias) {

  }
}
