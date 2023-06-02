package biblioteca.operacoes.exemplares;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
import biblioteca.operacoes.OperacoesAntiga;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class OperacoesGraficasDeExemplar extends OperacoesDeExemplar {
  public int selecionarOpcao() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
  }
  public Exemplar criar() {
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
  public void listar(ArrayList<Exemplar> acervo) {
    if(acervo.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há exemplares.");
    }
    String saida = "";
    for(Exemplar exemplar: acervo) {
      saida += exemplar.toString();
      saida +=     "================================================\n";
    }
    PainelGrafico.mostrarMensagemComScroll("Acervo",saida);
  }
  public void consultarPorCodigo(ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    Exemplar exemplar = buscarPorCodigo(acervo, Integer.parseInt(codigo));
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeExemplar.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    JOptionPane.showMessageDialog(null, exemplar);
  }
  public void excluir(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do exemplar: "));
    Exemplar exemplar = buscarPorCodigo(acervo,codigo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    if(exemplar.temEmprestimos(emprestimos) || exemplar.temReservas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possivel excluir o exemplar pois, ele tem empréstimos ou reservas associados.");
      return;
    }
    acervo.remove(exemplar);
    JOptionPane.showMessageDialog(null, "Exemplar excluido.");
  }
  public int selecionarOpcaoDeEditar() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenuEditar()));
  }
  public void editarTitulo(ArrayList<Exemplar> acervo) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarPorCodigo(acervo, codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String novoTitulo = JOptionPane.showInputDialog("Novo Título: ");
    temp.setTitulo(novoTitulo);
    JOptionPane.showMessageDialog(null,"Título editado com sucesso.");
  }
  public void editarAnoOuTipoDeArquivo(ArrayList<Exemplar> acervo) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarPorCodigo(acervo, codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
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
  public void adicionarCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarPorCodigo(acervo,codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
    Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias, nomeCategoria);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    temp.adicionarCategoria(categoria);
    JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " adicionada ao exemplar " + temp.getTitulo() + ".");
  }

  public void removerCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar temp = buscarPorCodigo(acervo, codigo);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }
    String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
    Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias, nomeCategoria);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    temp.getCategorias().remove(categoria);
    JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " removida do exemplar " + temp.getTitulo() + ".");
  }

}
