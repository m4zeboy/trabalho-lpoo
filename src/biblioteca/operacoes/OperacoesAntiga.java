package biblioteca.operacoes;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesAntiga {
  public static String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
  public static String EXEMPLAR_NAO_ENCONTRADO = "Exemplar não encontrado.";
  public static String OPCAO_INVALIDA = "Opção Inválida.";
  public static String CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada.";
  public static String EMPRESTIMO_NAO_ENCONTRADO = "Empréstimo não encontrado.";
  public abstract int selecionarOpcaoPrincipal();
  public abstract int selecionarOpcaoDeUsuarios();
  public abstract Usuario criarUsuario(ArrayList<Usuario> usuarios);
  public abstract void listarUsuarios(ArrayList<Usuario> usuarios);
  public abstract Usuario buscarUsuario(ArrayList<Usuario> usuarios);
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
  public abstract int selecionarOpcaoDeEditarUsuario();
  public abstract void editarNomeDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarCPFDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarRGAOuSIAPEDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void excluirUsuario(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos);

  /* Exemplares */
  public abstract int selecionarOpcaoDeExemplares();
  public abstract Exemplar criarExemplar();

  public abstract void listarExemplares(ArrayList<Exemplar> acervo);

  public abstract Exemplar buscarExemplarPorCodigo(ArrayList<Exemplar> acervo);

  public abstract int selecionarOpcaoDeEditarExemplar();

  public abstract void editarTituloDoExemplar(ArrayList<Exemplar> acervo);

  public abstract void editarAnoOuTipoDeArquivoDoExemplar(ArrayList<Exemplar> acervo);

  public abstract void adicionarCategoriaAoExemplar(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);

  public abstract void removerCategoriaDoExemplar(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);

  public abstract void excluirExemplar(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);

  /* Categorias */
  public abstract int selecionarOpcaoDeCategorias();

  public Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias, String nome) {
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }
  public abstract Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias);

  public abstract Categoria criarCategoria(ArrayList<Categoria> categorias);

  public abstract void listarCategorias(ArrayList<Categoria> categorias);

  public abstract Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias);

  public abstract void excluirCategoria(ArrayList<Categoria> categorias,ArrayList<Exemplar> acervo);

  public abstract void editarCategoria(ArrayList<Categoria> categorias);

  /* emprestimos */
  public abstract int selecionarOpcaoDeEmprestimos();

  public abstract Emprestimo emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);

  public abstract void listarEmprestimos(ArrayList<Emprestimo> emprestimos);
  public abstract Emprestimo buscarEmprestimoPorCodigo(ArrayList<Emprestimo> emprestimos);

  public abstract void consultaStatusDeUmEmprestimo(ArrayList<Emprestimo> emprestimos);

  public abstract void devolverEmprestimo(ArrayList<Emprestimo> emprestimos);

  public abstract void renovarEmprestimo(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);

  /* Reservas */
  public abstract int selecionarOpcaoDeReservas();

  public abstract Reserva reservar(ArrayList<Reserva> reservas, ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos);

  public abstract Reserva buscarReservaPorCodigo(ArrayList<Reserva> reservas);
  public abstract void cancelarReserva(ArrayList<Reserva> reservas);

  public abstract void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas);

  public abstract void consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeríodo(ArrayList<Reserva> reservas, ArrayList<Categoria> categorias);
}
