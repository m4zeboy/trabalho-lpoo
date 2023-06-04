package biblioteca.operacoes.exemplares;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;

import java.util.ArrayList;

public abstract class OperacoesDeExemplar {
  public static String EXEMPLAR_NAO_ENCONTRADO = "Exemplar não encontrado.";
  /* a função getMenu() obtém o menu de gerenciar exemplares em formato string */
  protected String getMenu() {
    StringBuilder mensagem = new StringBuilder("1 - Cadastrar\n");
    mensagem.append("2 - Consultar por código\n");
    mensagem.append("3 - Excluir\n");
    mensagem.append("4 - Editar\n");
    mensagem.append("5 - Listar todos os cadastros\n");
    mensagem.append("6 - Voltar\n\n");
    return mensagem.toString();
  }
  /* a função selecionarOpcao() mostra o menu de gerenciar exemplares para que o usuário possa escolher uma operação */
  public abstract int selecionarOpcao();
  /* a função criar le os dados, instancia um novo exemplar e adiciona na lista de exemplares */
  public abstract Exemplar criar();
  /* a função listar() percorre a lista de exemplares e os exibe em tela */
  public abstract void listar(ArrayList<Exemplar> acervo);
  /* a função buscarPorCodigo() percorre a lista de exemplares em busca de um correspondente pelo id, se encontrar retorna-o*/
  public static Exemplar buscarPorCodigo(ArrayList<Exemplar> acervo, int codigo) {
    for(Exemplar exemplar: acervo) {
      if(exemplar.getId() == codigo) {
        return exemplar;
      }
    }
    return null;
  }
  /* a função consultarPorCodigo() le um código informado pelo usuário e chama a função buscarPorCodigo, se encontrar exibe os dados em tela */
  public abstract void consultarPorCodigo(ArrayList<Exemplar> acervo);
  /* a função excluir() busca por um exemplar, e o remove do acervo se ele não tiver reservas e nem empréstimos associados. */
  public abstract void excluir(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);
  /* a função getMenuEditar() obtem o menu de editar exemplar no formato string */
  protected String getMenuEditar() {
    StringBuilder mensagem = new StringBuilder("1 - Editar Título\n");
    mensagem.append("2 - Editar Ano/Tipo de arquivo\n");
    mensagem.append("3 - Adicionar categoria\n");
    mensagem.append("4 - Remover Categoria\n");
    mensagem.append("5 - Voltar\n\n");
    return mensagem.toString();
  }
  /* a função selecionarOpcaoDeEditar() mostra o menu de editar e lê do usuário a opção que ele deseja */
  public abstract int selecionarOpcaoDeEditar();
  /* a função editarTitulo() busca por um exemplar e atualiza seu titulo */
  public abstract void editarTitulo(ArrayList<Exemplar> acervo);
  /* a função editarAnoOuTipoDeArquivo() busca por um exemplar e atualiza seu ano ou tipo de arquivo (a depender do tipo de exempalr) */
  public abstract void editarAnoOuTipoDeArquivo(ArrayList<Exemplar> acervo);
  /* a função adicionarCategoria() busca por um exemplar, lê uma categoria do usuário, se a categoria existir, adiciona-a na lista de categorias do exemplar. */
  public abstract void adicionarCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);
  /* a função adicionarCategoria() busca por um exemplar, lê uma categoria do usuário, se a categoria existir, remove-a da lista de categorias do exemplar. */
  public abstract void removerCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias);
}
