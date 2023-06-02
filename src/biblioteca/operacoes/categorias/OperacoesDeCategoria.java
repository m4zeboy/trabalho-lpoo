package biblioteca.operacoes.categorias;

import biblioteca.Categoria;
import biblioteca.exemplar.Exemplar;

import java.util.ArrayList;

public abstract class OperacoesDeCategoria {
  public static String CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada.";

  public static Categoria buscarPorNome(ArrayList<Categoria> categorias, String nome) {
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }

  public static Categoria buscarPorCodigo(ArrayList<Categoria> categorias, int codigo) {
    for(Categoria categoria: categorias) {
      if(categoria.getId() == codigo) return categoria;
    }
    return null;
  }

  protected String getMenu() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Consultar por código\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem += "5 - Listar todos os cadastros\n";
    mensagem += "6 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcao();
  public abstract Categoria criar(ArrayList<Categoria> categorias);
  public abstract Categoria buscarPorNome(ArrayList<Categoria> categorias);
  public abstract Categoria buscarPorCodigo(ArrayList<Categoria> categorias);
  public abstract void consultarPorCodigo(ArrayList<Categoria> categorias);
  public abstract void excluir(ArrayList<Categoria> categorias,ArrayList<Exemplar> acervo);
  public abstract void listar(ArrayList<Categoria> categorias);
  public abstract void editar(ArrayList<Categoria> categorias);
}
