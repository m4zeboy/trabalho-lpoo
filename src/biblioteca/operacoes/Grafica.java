package biblioteca.operacoes;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;

public class Grafica {
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
    if(usuarios.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há usuários.");
    }
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
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo Nome: ");
    usuario.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Nome editado com sucesso.");
  }
  public void editarCPFDoUsuario(ArrayList<Usuario> usuarios) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
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
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
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
  public void excluirUsuario(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
      return;
    }
    if(usuario.temEmprestimo(emprestimos) || usuario.temReservas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possivel excluir o usuário pois, ele tem empréstimos ou reservas em seu nome.");
      return;
    }
    usuarios.remove(usuario);
    JOptionPane.showMessageDialog(null, "Usuário #" + usuario.getId() + " excluido.");
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
    if(acervo.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há exemplares.");
    }
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

  public void adicionarCategoriaAoExemplar(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    Exemplar temp = buscarExemplarPorCodigo(acervo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
    Categoria categoria = buscarCategoriaPorNome(categorias, nomeCategoria);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, Operacoes.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    temp.adicionarCategoria(categoria);
    JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " adicionada ao exemplar " + temp.getTitulo() + ".");
  }

  public void removerCategoriaDoExemplar(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    Exemplar temp = buscarExemplarPorCodigo(acervo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
    Categoria categoria = buscarCategoriaPorNome(categorias, nomeCategoria);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, Operacoes.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    temp.getCategorias().remove(categoria);
    JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " removida do exemplar " + temp.getTitulo() + ".");
  }

  public void excluirExemplar(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    if(exemplar.temEmprestimos(emprestimos) || exemplar.temReservas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possivel excluir o exemplar pois, ele tem empréstimos ou reservas associados.");
      return;
    }
    acervo.remove(exemplar);
    JOptionPane.showMessageDialog(null, "Exemplar Removido.");
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
    if(categorias.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há categorias.");
    }
    String saida =  "=============== LISTA DE CATEGORIAS ===============\n";
    for(Categoria categoria: categorias) {
      saida += categoria;
      saida +=      "==================================================\n";
    }
    JOptionPane.showMessageDialog(null, saida);
  }

  public Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Código da categoria: "));
    for(Categoria categoria: categorias) {
      if(categoria.getId() == codigo) return categoria;
    }
    return null;
  }

  public void excluirCategoria(ArrayList<Categoria> categorias,ArrayList<Exemplar> acervo) {
    Categoria temp = buscarCategoriaPorCodigo(categorias);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    for(Exemplar exemplar: acervo) {
      if(exemplar.getCategorias().contains(temp)) {
        JOptionPane.showMessageDialog(null, "Não é possível excluir a categoria, existe exemplares que pertencem a essa categoria.");
        return;
      }
    }
    categorias.remove(temp);
    JOptionPane.showMessageDialog(null, "Categoria " + temp.getNome() + " excluida.");
  }

  public void editarCategoria(ArrayList<Categoria> categorias) {
    Categoria temp = buscarCategoriaPorCodigo(categorias);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, Operacoes.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo nome da categoria: ");
    temp.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Categoria editada com sucesso.");
  }

  /* emprestimos */
  public int selecionarOpcaoDeEmprestimos() {
    String mensagem = "1 - Emprestar\n";
    mensagem += "2 - Consultar status de um empréstimo\n";
    mensagem += "3 - Devolver\n";
    mensagem += "4 - Renovar\n";
    mensagem += "5 - Listar todos os empréstimos\n";
    mensagem += "6 - Voltar\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
  }

  public Emprestimo emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
      return null;
    }
    if(usuario.temEmprestimoEmAtraso(emprestimos)) {
      JOptionPane.showMessageDialog(null, "Não é possível emprestar pois o usuário tem empréstimos em atraso.");
      return null;
    }
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return null;
    }
    /* verificar se o exemplar está disponivel */
    if(!exemplar.estaDisponivel(emprestimos)) {
      JOptionPane.showMessageDialog(null, "O exemplar não está disponível para empréstimo.");
      return null;
    }
    if(exemplar.temReservasAtivas(reservas)) {
      Reserva proxima = exemplar.getProximaReserva(reservas);
      if(!proxima.getUsuario().equals(usuario)) {
        JOptionPane.showMessageDialog(null, "Outro usuário já reservou esse exemplar.");
        return null;
      } else {
        proxima.cancelar();
      }
    }
    Emprestimo emprestimo = new Emprestimo(usuario,exemplar, LocalDate.now());
    return emprestimo;

  }

  public void listarEmprestimos(ArrayList<Emprestimo> emprestimos) {
    if(emprestimos.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há empréstimos.");
    }
    String saida = "=============== LISTA DE EMPRÉSTIMOS ===============\n";
    for(Emprestimo emprestimo: emprestimos) {
      saida += emprestimo;
      saida +=   "====================================================\n";
    }
    JOptionPane.showMessageDialog(null, saida);
  }
  public Emprestimo buscarEmprestimoPorCodigo(ArrayList<Emprestimo> emprestimos) {
    String codigo = JOptionPane.showInputDialog(null, "Código do empréstimo: ");
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getId() == Integer.parseInt(codigo)) {
        return emprestimo;
      }
    }
    return null;
  }

  public void consultaStatusDeUmEmprestimo(ArrayList<Emprestimo> emprestimos) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    JOptionPane.showMessageDialog(null, emprestimo);
  }

  public void devolverEmprestimo(ArrayList<Emprestimo> emprestimos) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    Multa multa = emprestimo.devolver();
    if(multa != null) {
      JOptionPane.showMessageDialog(null, "Empréstimo devolvido com atraso. Multa de R$" + multa.getValor() + ".");
      return;
    }
    JOptionPane.showMessageDialog(null, "Empréstimo #" + emprestimo.getId() + " devolvido.");
  }

  public void renovarEmprestimo(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    if(emprestimo.getExemplar().temReservasAtivas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possível renovar o empréstimo pois o exemplar já está reservado.");
      return;
    }
    if(emprestimo.renovar() == false) {
      JOptionPane.showMessageDialog(null, "Não é possível renovar o emprestimo.");
      return;
    }
    JOptionPane.showMessageDialog(null, "Emprestimo #" + emprestimo.getId() + " renovado.");
  }

  /* Reservas */
  public int selecionarOpcaoDeReservas() {
    String mensagem = "1 - Reservar\n";
    mensagem += "2 - Consultar status de uma reserva\n";
    mensagem += "3 - Cancelar\n";
    mensagem += "4 - Listar reservas ativas para um exemplar\n";
    mensagem += "5 - Voltar\n\n";
    String opcao = JOptionPane.showInputDialog(mensagem);
    return Integer.parseInt(opcao);
  }

  public Reserva reservar(ArrayList<Reserva> reservas, ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, Operacoes.USUARIO_NAO_ENCONTRADO);
      return null;
    }
    if(usuario.temEmprestimoEmAtraso(emprestimos)) {
      JOptionPane.showMessageDialog(null, "Esse usuário não pode realizar reservas.");
      return null;
    }
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
      return null;
    }
    if(exemplar instanceof Digital) {
      JOptionPane.showMessageDialog(null, "Um exemplar do tipo digital está sempre disponível, não é necessário reservar ele.");
      return null;
    }
    LocalDate dataExpiracao = exemplar.calcularDataDeExpiracao(emprestimos,reservas);

    if(usuario.temReservasAtivasNoPeriodo(reservas,LocalDate.now(), dataExpiracao)) {
      JOptionPane.showMessageDialog(null, "O usuário já tem reservas no período de hoje até o dia " + dataExpiracao + ".");
      return null;
    }
    Reserva reserva = new Reserva(usuario, exemplar, dataExpiracao);
    return reserva;
  }

  public Reserva buscarReservaPorCodigo(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    for(Reserva reserva: reservas) {
      if(reserva.getId() == Integer.parseInt(codigo)) {
        return reserva;
      }
    }
    return null;
  }
  public void cancelarReserva(ArrayList<Reserva> reservas) {
    Reserva reserva = buscarReservaPorCodigo(reservas);
    if(reserva == null) {
      JOptionPane.showMessageDialog(null, "Reserva não encontrada");
      return;
    }
    if(reserva.cancelar()) {
      JOptionPane.showMessageDialog(null, "Reserva " + reserva.getId() + " cancelada.");
    } else {
      JOptionPane.showMessageDialog(null, "A reserva encontra-se expirada.");
    }
  }

  public void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas) {
      String saida = "=============== RESERVAS ===============\n";
      for(Reserva reserva: reservas) {
        saida += reserva;
        saida += "====================================================\n";
      }
      JOptionPane.showMessageDialog(null, saida);
  }
}
