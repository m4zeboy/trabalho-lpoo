package biblioteca.operacoes;

import biblioteca.Categoria;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class  Operacoes {
  public static String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
  public static String EXEMPLAR_NAO_ENCONTRADO = "Exemplar não encontrado.";
  public static String OPCAO_INVALIDA = "Opção Inválida.";
  public abstract int selecionarOpcaoPrincipal();
  public abstract int selecionarOpcaoDeUsuarios();

  public abstract Usuario criarUsuario(ArrayList<Usuario> usuarios);
  public abstract void listarUsuarios(ArrayList<Usuario> usuarios);

  public abstract Usuario buscarUsuario(ArrayList<Usuario> usuarios);
  public abstract Usuario buscarUsuario(ArrayList<Usuario> usuarios, String CPF);
  public abstract Servidor buscaServidorPorSiape(ArrayList<Usuario> usuarios, String siape);
  public abstract Aluno buscaAlunoPorRga(ArrayList<Usuario> usuarios, String rga);
  public abstract int selecionarOpcaoDeEditarUsuario();

  public abstract void editarNomeDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarCPFDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarRGAOuSIAPEDoUsuario(ArrayList<Usuario> usuarios);

  public abstract int selecionarOpcaoDeExemplares();
  public abstract Exemplar criarExemplar();

  public abstract void listarExemplares(ArrayList<Exemplar>acervo);
  public abstract Exemplar buscarExemplarPorCodigo(ArrayList<Exemplar> acervo);
  public abstract Exemplar buscarExemplarPorCodigo(ArrayList<Exemplar> acervo, int codigo);

  public abstract int selecionarOpcaoDeEditarExemplar();
  public abstract void editarTituloDoExemplar(ArrayList<Exemplar> acervo);
  public abstract void editarAnoOuTipoDeArquivoDoExemplar(ArrayList<Exemplar> acervo);

  /* Categorias */
  public abstract int selecionarOpcaoDeCategorias();

  public abstract Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias, String nome);
  public abstract Categoria criarCategoria(ArrayList<Categoria> categorias);

  public abstract void listarCategorias(ArrayList<Categoria> categorias);
}
