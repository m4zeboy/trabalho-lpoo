package biblioteca.operacoes.categorias;

import biblioteca.Categoria;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.OperacoesAntiga;
import biblioteca.operacoes.PainelGrafico;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class OperacoesGraficasDeCategoria extends OperacoesDeCategoria {
  public int selecionarOpcao() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
  }

  public Categoria criar(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    Categoria temp = buscarPorNome(categorias, nome);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe uma categoria com esse nome.");
      return null;
    }
    return new Categoria(nome);
  }

  public Categoria buscarPorNome(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    return buscarPorNome(categorias, nome);
  }

  public Categoria buscarPorCodigo(ArrayList<Categoria> categorias) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código da categoria: "));
    return buscarPorCodigo(categorias, codigo);
  }

  public void consultarPorCodigo(ArrayList<Categoria> categorias) {
    Categoria categoria = buscarPorCodigo(categorias);
    if(categoria == null) JOptionPane.showMessageDialog(null, OperacoesDeCategoria.CATEGORIA_NAO_ENCONTRADA);
    else JOptionPane.showMessageDialog(null, categoria);
  }

  public void excluir(ArrayList<Categoria> categorias, ArrayList<Exemplar> acervo) {
    Categoria temp = buscarPorCodigo(categorias);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
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

  public void listar(ArrayList<Categoria> categorias) {
    if(categorias.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há categorias.");
    }
    String saida =  "";
    for(Categoria categoria: categorias) {
      saida += categoria;
      saida +=      "==================================================\n";
    }
    PainelGrafico.mostrarMensagemComScroll("Lista de Categorias", saida);
  }
  public void editar(ArrayList<Categoria> categorias) {
    Categoria temp = buscarPorCodigo(categorias);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo nome da categoria: ");
    temp.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Categoria editada com sucesso.");
  }
}
