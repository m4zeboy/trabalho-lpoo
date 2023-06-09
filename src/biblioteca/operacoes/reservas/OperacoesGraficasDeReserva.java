package biblioteca.operacoes.reservas;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.ExemplarNaoEncontradoException;
import biblioteca.excecoes.UsuarioNaoEncontradoException;
import biblioteca.excecoes.UsuarioTemEmprestimoEmAtrasoException;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.usuario.Usuario;
import biblioteca.verificacoes.VerificacoesUsuarioEmprestimo;

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
      if(usuario.temReservaAtivaParaOExemplar(reservas,exemplar)) {
        JOptionPane.showMessageDialog(null, "O usúario já tem reservas ativas para esse exemplar.");
        return;
      }
      LocalDate dataExpiracao = exemplar.calcularDataDeExpiracao(emprestimos,reservas);
      if(usuario.temReservasAtivasNoPeriodo(reservas,LocalDate.now(), dataExpiracao)) {
        JOptionPane.showMessageDialog(null, "O usuário já tem reservas no período de hoje até o dia " + dataExpiracao + ".");
        return;
      }
      Reserva reserva = new Reserva(usuario, exemplar, dataExpiracao);
      reservas.add(reserva);
      JOptionPane.showMessageDialog(null, "Exemplar #" + exemplar.getTitulo() + " reservado para " + usuario.getNome() + ".");
    } catch (UsuarioNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    } catch (UsuarioTemEmprestimoEmAtrasoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (ExemplarNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void consultarPorCodigo(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    try {
      int id = Integer.parseInt(codigo);
      Reserva reserva = buscarPorCodigo(reservas, id);
      if(reserva == null) {
        JOptionPane.showMessageDialog(null, OperacoesDeReserva.RESERVA_NAO_ENCONTRADA);
        return;
      }
      JOptionPane.showMessageDialog(null, reserva);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    }
  }
  public void cancelar(ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código da reserva: ");
    try {
      int id = Integer.parseInt(codigo);
      Reserva reserva = buscarPorCodigo(reservas, id);
      if(reserva == null) {
        JOptionPane.showMessageDialog(null, "Reserva não encontrada");
        return;
      }
      reserva.cancelar();
      JOptionPane.showMessageDialog(null, "Reserva " + reserva.getId() + " cancelada.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    }
  }

  public void listarReservasAtivasParaUmExemplar(ArrayList<Exemplar> acervo, ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código do exemplar: ");
    try{
      int id = Integer.parseInt(codigo);
      Exemplar exemplar = OperacoesDeExemplar.buscarPorCodigo(acervo, id);
      String saida = "";
      for(Reserva reserva: reservas) {
        if(reserva.getExemplar().equals(exemplar) && reserva.estaAtiva()) {
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
    Categoria categoria = OperacoesDeCategoria.buscarPorNome(categorias,nomeCategoria);
    if(categoria == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeCategoria.CATEGORIA_NAO_ENCONTRADA);
      return;
    }
    String dataInicio = JOptionPane.showInputDialog("Início - yyyy-mm-dd: ");
    String dataFim = JOptionPane.showInputDialog("Fim- yyyy-mm-dd: ");
    try {
      LocalDate inicio = LocalDate.parse(dataInicio);
      LocalDate fim = LocalDate.parse(dataFim);

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

    } catch (DateTimeParseException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado não corresponde a este padrão 'yyyy-mm-dd'");
    }
  }
}
