package biblioteca.operacoes.exemplares;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.CategoriaNaoEncontradaException;
import biblioteca.excecoes.ExemplarNaoEncontradoException;
import biblioteca.excecoes.ExemplarTemEmprestimosAssociadosException;
import biblioteca.excecoes.ExemplarTemReservasAssociadasException;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;
import biblioteca.verificacoes.VerificacoesExemplarEmprestimo;
import biblioteca.verificacoes.VerificacoesExemplarReserva;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class OperacoesGraficasDeExemplar extends OperacoesDeExemplar {
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }
  public void criar(ArrayList<Exemplar> exemplares) {
    String titulo = JOptionPane.showInputDialog("Título: ");
    String[] tipos = { "Livro", "Mídia", "Digital"};
    int tipo = JOptionPane.showOptionDialog(null, "Qual tipo de Exemplar você deseja criar?", "Biblioteca", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);
    Exemplar novo = null;
    if(tipo == 1) {
      String tipoArquivo = JOptionPane.showInputDialog("Tipo de Arquivo: ");
      novo = new Midia(titulo,tipoArquivo);
      exemplares.add(novo);
    } else if(tipo == 2){
      novo = new Digital(titulo);
      exemplares.add(novo);
    } else {
      String ano = JOptionPane.showInputDialog("Ano: ");
      novo = new Livro(titulo, ano);
      exemplares.add(novo);
    }
    JOptionPane.showMessageDialog(null, "Exemplar criado com código #" +novo.getId() + ".");
  }
  public void listar(ArrayList<Exemplar> acervo) {
    if(acervo.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há exemplares.");
    } else {
      String saida = "";
      for(Exemplar exemplar: acervo) {
        saida += exemplar.toString();
        saida +=     "================================================\n";
      }
      PainelGrafico.mostrarMensagemComScroll("Acervo",saida);
    }
  }
  public void consultarPorCodigo(ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar exemplar = buscarPorCodigo(acervo, id);
      JOptionPane.showMessageDialog(null, exemplar);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código informado precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
  public void excluir(ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código do exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar exemplar = buscarPorCodigo(acervo,id);
      try {
        VerificacoesExemplarReserva.naoTemReservas(reservas, exemplar);
        VerificacoesExemplarEmprestimo.naoTemEmprestimos(emprestimos,exemplar);
        acervo.remove(exemplar);
        JOptionPane.showMessageDialog(null, "Exemplar excluido.");
      } catch (ExemplarTemReservasAssociadasException exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage());
      } catch (ExemplarTemEmprestimosAssociadosException exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage());
      }
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código informado prrecisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
  public int selecionarOpcaoDeEditar() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenuEditar()));
  }
  public void editarTitulo(ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar temp = buscarPorCodigo(acervo, id);
      String novoTitulo = JOptionPane.showInputDialog("Novo Título: ");
      temp.setTitulo(novoTitulo);
      JOptionPane.showMessageDialog(null,"Título editado com sucesso.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());

    }

  }
  public void editarAnoOuTipoDeArquivo(ArrayList<Exemplar> acervo) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar temp = buscarPorCodigo(acervo, id);
      if(temp instanceof Livro) {
        int novoAno = Integer.parseInt(JOptionPane.showInputDialog("Novo ano: "));
        ((Livro) temp).setAno(novoAno);
        JOptionPane.showMessageDialog(null, "Ano editado com sucesso");
      } else if(temp instanceof Midia) {
        String novoTipoDeArquivo = JOptionPane.showInputDialog("Novo Tipo de Arquivo: ");
        ((Midia) temp).setTipoArquivo(novoTipoDeArquivo);
        JOptionPane.showMessageDialog(null, "Tipo de Arquivo editado com sucesso");
      }
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());

    }


  }
  public void adicionarCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar temp = buscarPorCodigo(acervo,id);
      String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
      Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias, nomeCategoria);
      temp.adicionarCategoria(categoria);
      JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " adicionada ao exemplar " + temp.getTitulo() + ".");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException | CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());

    }
  }
  public void removerCategoria(ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
    try {
      int id = Integer.parseInt(codigo);
      Exemplar temp = buscarPorCodigo(acervo, id);
      String nomeCategoria = JOptionPane.showInputDialog("Nome da categoria: ");
      Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias, nomeCategoria);
      temp.getCategorias().remove(categoria);
      JOptionPane.showMessageDialog(null, "Categoria " + categoria.getNome() + " removida do exemplar " + temp.getTitulo() + ".");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException | CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());

    }

  }

}
