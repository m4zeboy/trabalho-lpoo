package biblioteca.operacoes.exemplares;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;

import java.util.ArrayList;

public abstract class OperacoesDeExemplar {
  public static String EXEMPLAR_NAO_ENCONTRADO = "Exemplar não encontrado.";
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
  public abstract Exemplar criar();
  public abstract void listar(ArrayList<Exemplar> acervo);
  public Exemplar buscarPorCodigo(ArrayList<Exemplar> acervo, int codigo) {
    for(Exemplar exemplar: acervo) {
      if(exemplar.getId() == codigo) {
        return exemplar;
      }
    }
    return null;
  }
  public abstract void consultarPorCodigo(ArrayList<Exemplar> acervo);
  public abstract void excluir(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);

  protected String getMenuEditar() {
    String mensagem = "1 - Editar Título\n";
    mensagem += "2 - Editar Ano/Tipo de arquivo\n";
    mensagem += "3 - Adicionar categoria\n";
    mensagem += "4 - Remover Categoria\n";
    mensagem += "5 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcaoDeEditar();
  public abstract void editarTitulo(ArrayList<Exemplar> acervo);
  public abstract void editarAnoOuTipoDeArquivo(ArrayList<Exemplar> acervo);
  public abstract void adicionarCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);
  public abstract void removerCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);
}
