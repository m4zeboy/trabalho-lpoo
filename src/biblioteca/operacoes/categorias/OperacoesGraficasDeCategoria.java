package biblioteca.operacoes.categorias;

import biblioteca.Categoria;
import biblioteca.excecoes.CategoriaNaoEncontradaException;
import biblioteca.excecoes.JaExisteCategoriaComEsseNomeException;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.PainelGrafico;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class OperacoesGraficasDeCategoria extends OperacoesDeCategoria {
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }

  public void criar(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    try {
      naoExisteCategoriaComEsseNome(categorias, nome);
      categorias.add(new Categoria(nome));
      JOptionPane.showMessageDialog(null,"Categoria criada");
    } catch (JaExisteCategoriaComEsseNomeException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }


  public void consultarPorCodigo(ArrayList<Categoria> categorias) {
    String codigo = JOptionPane.showInputDialog("Código da categoria: ");
    try {
      int id = Integer.parseInt(codigo);
      Categoria categoria = buscarPorCodigo(categorias, id);
      JOptionPane.showMessageDialog(null, categoria);
    }catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }

  public void excluir(ArrayList<Categoria> categorias, ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código da categoria: ");
    try {
      int id = Integer.parseInt(codigo);
      Categoria temp = buscarPorCodigo(categorias, id);
      for(Exemplar exemplar: acervo) {
        if(exemplar.getCategorias().contains(temp)) {
          JOptionPane.showMessageDialog(null, "Não é possível excluir a categoria, existe exemplares que pertencem a essa categoria.");
          return;
        }
      }
      categorias.remove(temp);
      JOptionPane.showMessageDialog(null, "Categoria " + temp.getNome() + " excluida.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
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
    String codigo = JOptionPane.showInputDialog("Código da categoria: ");
    try {
      int id = Integer.parseInt(codigo);
      Categoria temp = buscarPorCodigo(categorias,id);
      String novoNome = JOptionPane.showInputDialog("Novo nome da categoria: ");
      temp.setNome(novoNome);
      JOptionPane.showMessageDialog(null, "Categoria editada com sucesso.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
}
