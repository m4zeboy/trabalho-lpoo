package biblioteca.operacoes;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;

import biblioteca.usuario.Usuario;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GraficaAntiga extends OperacoesAntiga {
  /* Categorias */
  public int selecionarOpcaoDeCategorias() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Consultar por código\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem += "5 - Listar todos os cadastros\n";
    mensagem += "6 - Voltar\n\n";
    String opcao = JOptionPane.showInputDialog(mensagem);
    return Integer.parseInt(opcao);
  }

  public Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias, String nome) {
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }

  public Categoria buscarCategoriaPorNome(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    for(Categoria categoria: categorias) {
      if(categoria.getNome().equals(nome)) return categoria;
    }
    return null;
  }

  public Categoria criarCategoria(ArrayList<Categoria> categorias) {
    String nome = JOptionPane.showInputDialog("Nome da Categoria: ");
    Categoria temp = buscarCategoriaPorNome(categorias, nome);
    if(temp != null) {
      JOptionPane.showMessageDialog(null, "Já existe uma categoria com esse nome.");
      return null;
    }
    return new Categoria(nome);
  }

  public void listarCategorias(ArrayList<Categoria> categorias) {
    if(categorias.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há categorias.");
    }
    String saida =  "";
    for(Categoria categoria: categorias) {
      saida += categoria;
      saida +=      "==================================================\n";
    }
    mostrarMensagemComScroll("Lista de Categorias", saida);
  }

  public Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias) {
    int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Código da categoria: "));
    for(Categoria categoria: categorias) {
      if(categoria.getId() == codigo) return categoria;
    }
    return null;
  }

  public void excluirCategoria(ArrayList<Categoria> categorias,ArrayList<Exemplar> acervo) {
    Categoria temp = buscarCategoriaPorCodigo(categorias);
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

  public void editarCategoria(ArrayList<Categoria> categorias) {
    Categoria temp = buscarCategoriaPorCodigo(categorias);
    if(temp == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    String novoNome = JOptionPane.showInputDialog("Novo nome da categoria: ");
    temp.setNome(novoNome);
    JOptionPane.showMessageDialog(null, "Categoria editada com sucesso.");
  }

  /* emprestimos */
  public int selecionarOpcaoDeEmprestimos() {
    String mensagem = "1 - Emprestar\n";
    mensagem += "2 - Consultar status de um empréstimo\n";
    mensagem += "3 - Devolver\n";
    mensagem += "4 - Renovar\n";
    mensagem += "5 - Listar todos os empréstimos\n";
    mensagem += "6 - Voltar\n\n";
    return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
  }

  public Emprestimo emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return null;
    }
    if(usuario.temEmprestimoEmAtraso(emprestimos)) {
      JOptionPane.showMessageDialog(null, "Não é possível emprestar pois o usuário tem empréstimos em atraso.");
      return null;
    }
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return null;
    }
    /* verificar se o exemplar está disponivel */
    if(!exemplar.estaDisponivel(emprestimos)) {
      JOptionPane.showMessageDialog(null, "O exemplar não está disponível para empréstimo.");
      return null;
    }
    if(exemplar.temReservasAtivas(reservas)) {
      Reserva proxima = exemplar.getProximaReserva(reservas);
      if(!proxima.getUsuario().equals(usuario)) {
        JOptionPane.showMessageDialog(null, "Outro usuário já reservou esse exemplar.");
        return null;
      } else {
        proxima.cancelar();
      }
    }
    Emprestimo emprestimo = new Emprestimo(usuario,exemplar);
    return emprestimo;

  }

  public void listarEmprestimos(ArrayList<Emprestimo> emprestimos) {
    if(emprestimos.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há empréstimos.");
    }
    String saida = "";
    for(Emprestimo emprestimo: emprestimos) {
      saida += emprestimo;
      saida +=   "====================================================\n";
    }
    mostrarMensagemComScroll("Lista de Empréstimos", saida);
  }
  public Emprestimo buscarEmprestimoPorCodigo(ArrayList<Emprestimo> emprestimos) {
    String codigo = JOptionPane.showInputDialog(null, "Código do empréstimo: ");
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getId() == Integer.parseInt(codigo)) {
        return emprestimo;
      }
    }
    return null;
  }

  public void consultaStatusDeUmEmprestimo(ArrayList<Emprestimo> emprestimos) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    JOptionPane.showMessageDialog(null, emprestimo);
  }

  public void devolverEmprestimo(ArrayList<Emprestimo> emprestimos) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    Multa multa = emprestimo.devolver();
    if(multa != null) {
      JOptionPane.showMessageDialog(null, "Empréstimo devolvido com atraso. Multa de R$" + multa.getValor() + ".");
      return;
    }
    JOptionPane.showMessageDialog(null, "Empréstimo #" + emprestimo.getId() + " devolvido.");
  }

  public void renovarEmprestimo(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    Emprestimo emprestimo = buscarEmprestimoPorCodigo(emprestimos);
    if(emprestimo == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EMPRESTIMO_NAO_ENCONTRADO);
      return;
    }
    if(emprestimo.getExemplar().temReservasAtivas(reservas)) {
      JOptionPane.showMessageDialog(null, "Não é possível renovar o empréstimo pois o exemplar já está reservado.");
      return;
    }
    boolean renovou = emprestimo.renovar();
    if(!renovou) {
      JOptionPane.showMessageDialog(null, "Não é possível renovar o emprestimo.");
      return;
    }
    JOptionPane.showMessageDialog(null, "Emprestimo #" + emprestimo.getId() + " renovado.");
  }

  /* Reservas */
  public int selecionarOpcaoDeReservas() {
    String mensagem = "1 - Reservar\n";
    mensagem += "2 - Consultar status de uma reserva\n";
    mensagem += "3 - Cancelar\n";
    mensagem += "4 - Listar reservas ativas para um exemplar\n";
    mensagem += "5 - Voltar\n\n";
    String opcao = JOptionPane.showInputDialog(mensagem);
    return Integer.parseInt(opcao);
  }

  public Reserva reservar(ArrayList<Reserva> reservas, ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos) {
    Usuario usuario = buscarUsuario(usuarios);
    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.USUARIO_NAO_ENCONTRADO);
      return null;
    }
    if(usuario.temEmprestimoEmAtraso(emprestimos)) {
      JOptionPane.showMessageDialog(null, "Esse usuário não pode realizar reservas.");
      return null;
    }
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return null;
    }
    if(exemplar instanceof Digital) {
      JOptionPane.showMessageDialog(null, "Um exemplar do tipo digital está sempre disponível, não é necessário reservar ele.");
      return null;
    }
    if(usuario.temReservaAtivaParaOExemplar(reservas,exemplar)) {
      JOptionPane.showMessageDialog(null, "O usúario já tem reservas ativas para esse exemplar.");
      return null;
    }
    LocalDate dataExpiracao = exemplar.calcularDataDeExpiracao(emprestimos,reservas);

    if(usuario.temReservasAtivasNoPeriodo(reservas,LocalDate.now(), dataExpiracao)) {
      JOptionPane.showMessageDialog(null, "O usuário já tem reservas no período de hoje até o dia " + dataExpiracao + ".");
      return null;
    }
    Reserva reserva = new Reserva(usuario, exemplar, dataExpiracao);
    return reserva;
  }

  public Reserva buscarReservaPorCodigo(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    for(Reserva reserva: reservas) {
      if(reserva.getId() == Integer.parseInt(codigo)) {
        return reserva;
      }
    }
    return null;
  }
  public void cancelarReserva(ArrayList<Reserva> reservas) {
    Reserva reserva = buscarReservaPorCodigo(reservas);
    if(reserva == null) {
      JOptionPane.showMessageDialog(null, "Reserva não encontrada");
      return;
    }
    reserva.cancelar();
    JOptionPane.showMessageDialog(null, "Reserva " + reserva.getId() + " cancelada.");
  }

  public void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas) {
    Exemplar exemplar = buscarExemplarPorCodigo(acervo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.EXEMPLAR_NAO_ENCONTRADO);
      return;
    }

    String saida = "";
      for(Reserva reserva: reservas) {
        if(reserva.getExemplar().equals(exemplar) && reserva.estaAtiva()) {
          saida += reserva;
          saida += "====================================================\n";
        }
      }
      mostrarMensagemComScroll("Lista de Reservas", saida);
  }

  public void consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeríodo(ArrayList<Reserva> reservas, ArrayList<Categoria> categorias) {
    Categoria categoria = buscarCategoriaPorNome(categorias);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, OperacoesAntiga.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    int diaInicio = Integer.parseInt(JOptionPane.showInputDialog("Dia de início: "));
    int mesInicio = Integer.parseInt(JOptionPane.showInputDialog("Mês de início: "));
    int anoInicio = Integer.parseInt(JOptionPane.showInputDialog("Ano de início: "));

    int diaFim = Integer.parseInt(JOptionPane.showInputDialog("Dia de fim: "));
    int mesFim = Integer.parseInt(JOptionPane.showInputDialog("Mês de fim: "));
    int anoFim = Integer.parseInt(JOptionPane.showInputDialog("Ano de fim: "));

    LocalDate inicio = LocalDate.of(anoInicio,mesInicio,diaInicio);
    LocalDate fim = LocalDate.of(anoFim,mesFim,diaFim);

    int contador = 0;
    for(Reserva reserva: reservas) {
      if(
              (reserva.getDataReserva().isEqual(inicio) || reserva.getDataReserva().isAfter(inicio)) &&
              (reserva.getDataReserva().isEqual(fim) || reserva.getDataReserva().isBefore(fim))
      ) {
        if(reserva.getExemplar().getCategorias().contains(categoria)) {
          contador++;
        }
      }
    }
    JOptionPane.showMessageDialog(null, "Total de exemplares reservados pela categoria " + categoria.getNome() + " no periodo de " + inicio + " até " + fim + ": " + contador + ".");
  }
}
