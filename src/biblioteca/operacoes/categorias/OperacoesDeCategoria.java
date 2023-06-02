package biblioteca.operacoes.categorias;

import biblioteca.Categoria;

import java.util.ArrayList;

public abstract class OperacoesDeCategoria {
  public static Categoria buscarPorNome(ArrayList<Categoria> categorias, String nome) {
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }
}
