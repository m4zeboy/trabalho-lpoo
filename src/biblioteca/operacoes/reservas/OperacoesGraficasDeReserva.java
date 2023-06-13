package biblioteca.operacoes.reservas;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.*;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.usuario.Usuario;
import biblioteca.verificacoes.VerificacoesUsuarioEmprestimo;
import biblioteca.verificacoes.VerificacoesUsuarioExemplarReserva;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class OperacoesGraficasDeReserva extends OperacoesDeReserva {
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }
  public void reservar(ArrayList<Reserva> reservas, ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Emprestimo> emprestimos) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    try {
      Usuario usuario = OperacoesDeUsuario.buscarPorCPF(usuarios, cpf);
      VerificacoesUsuarioEmprestimo.naoTemEmprestimoEmAtraso(emprestimos, usuario);
      String codigo = JOptionPane.showInputDialog("Código do exemplar: ");
      int id = Integer.parseInt(codigo);
      Exemplar exemplar = OperacoesDeExemplar.buscarPorCodigo(acervo, id);
      if(exemplar instanceof Digital) {
        JOptionPane.showMessageDialog(null, "Um exemplar do tipo digital está sempre disponível, não é necessário reservar ele.");
        return;
      }
      LocalDate dataExpiracao = Reserva.calcularDataDeExpiracao(emprestimos,reservas,exemplar);
      VerificacoesUsuarioExemplarReserva.usuarioNaoTemReservasParaOExemplarNoPeriodo(reservas, usuario, exemplar, LocalDate.now(), dataExpiracao);
      Reserva reserva = new Reserva(usuario, exemplar, dataExpiracao);
      reservas.add(reserva);
      JOptionPane.showMessageDialog(null, "Exemplar #" + exemplar.getTitulo() + " reservado para " + usuario.getNome() + ".");
    } catch (UsuarioNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    } catch (UsuarioTemEmprestimoEmAtrasoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException | UsuarioTemReservasAtivasParaOExemplarNoPeriodoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void consultarPorCodigo(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    try {
      int id = Integer.parseInt(codigo);
      Reserva reserva = buscarPorCodigo(reservas, id);
      JOptionPane.showMessageDialog(null, reserva);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ReservaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void cancelar(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    try {
      int id = Integer.parseInt(codigo);
      Reserva reserva = buscarPorCodigo(reservas, id);
      reserva.cancelar();
      JOptionPane.showMessageDialog(null, "Reserva " + reserva.getId() + " cancelada.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ReservaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código do exemplar: ");
    try{
      int id = Integer.parseInt(codigo);
      Exemplar exemplar = OperacoesDeExemplar.buscarPorCodigo(acervo, id);
      String saida = "";
      for(Reserva reserva: reservas) {
        if(reserva.getExemplar().getId() == exemplar.getId() && reserva.estaAtiva()) {
          saida += reserva;
          saida += "====================================================\n";
        }
      }
      PainelGrafico.mostrarMensagemComScroll("Lista de Reservas", saida);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
  public void consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeriodo(ArrayList<Reserva> reservas, ArrayList<Categoria> categorias) {
    String nomeCategoria = JOptionPane.showInputDialog("Categoria: ");
    try {
      Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias,nomeCategoria);
      String dataInicio = JOptionPane.showInputDialog("Início - yyyy-mm-dd: ");
      String dataFim = JOptionPane.showInputDialog("Fim- yyyy-mm-dd: ");
      LocalDate inicio = LocalDate.parse(dataInicio);
      LocalDate fim = LocalDate.parse(dataFim);
      int total = OperacoesDeReserva.getTotalDeExemplaresReservadosPorCategoriaEmUmPeriodo(reservas, categoria, inicio, fim);
      JOptionPane.showMessageDialog(null, "Total de exemplares reservados pela categoria " + categoria.getNome() + " no periodo de " + inicio + " até " + fim + ": " + total + ".");
    }catch (CategoriaNaoEncontradaException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    } catch (DateTimeParseException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado não corresponde a este padrão 'yyyy-mm-dd'");
    }
  }
}
