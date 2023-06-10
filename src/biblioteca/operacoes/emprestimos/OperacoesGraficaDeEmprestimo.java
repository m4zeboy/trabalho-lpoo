package biblioteca.operacoes.emprestimos;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.excecoes.*;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.reservas.OperacoesDeReserva;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.usuario.Usuario;
import biblioteca.verificacoes.VerificacoesExemplarReserva;
import biblioteca.verificacoes.VerificacoesUsuarioEmprestimo;
import biblioteca.verificacoes.VerificacoesUsuarioReserva;

import javax.swing.*;
import java.util.ArrayList;

public class OperacoesGraficaDeEmprestimo extends OperacoesDeEmprestimo {
  public int selecionarOpcao() {
    try {
      return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
    } catch (NumberFormatException exception) {
      return -1;
    }
  }
  public void emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    try {
      Usuario usuario = OperacoesDeUsuario.buscarPorCPF(usuarios,cpf);
      VerificacoesUsuarioEmprestimo.naoTemEmprestimoEmAtraso(emprestimos, usuario);
      String codigo = JOptionPane.showInputDialog("Código do Exemplar: ");
      try {
        int id = Integer.parseInt(codigo);
        Exemplar exemplar = OperacoesDeExemplar.buscarPorCodigo(acervo,id);
        /* verificar se o exemplar está disponivel */
        if(!exemplar.estaDisponivel(emprestimos)) {
          JOptionPane.showMessageDialog(null, "O exemplar não está disponível para empréstimo.");
          return;
        }
        if(VerificacoesExemplarReserva.temReservasAtivas(reservas, exemplar)) {
          VerificacoesUsuarioReserva.aProximaReservaPertenceAoUsuario(reservas, exemplar, usuario);
        }
        Emprestimo emprestimo = new Emprestimo(usuario,exemplar);
        emprestimos.add(emprestimo);
        JOptionPane.showMessageDialog(null, "Exemplar " + emprestimo.getExemplar().getTitulo() + " emprestado para o usuário " + emprestimo.getUsuario().getNome() + ".");
      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(null, "O valor informado não é um número.");
      } catch (ExemplarNaoEncontradoException | OExemplarEstaReservadoParaOutroUsuarioException exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage());
      }
    } catch(UsuarioNaoEncontradoException | UsuarioTemEmprestimoEmAtrasoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }

  }
  public void consultarPorCodigo(ArrayList<Emprestimo> emprestimos) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos,id);
      JOptionPane.showMessageDialog(null, emprestimo);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado precisa ser um número inteiro.");
    } catch (EmprestimoNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void devolver(ArrayList<Emprestimo> emprestimos, ArrayList<Multa> multas) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos, id);
      Multa multa = emprestimo.devolver();
      if (multa != null) {
        multas.add(multa);
        JOptionPane.showMessageDialog(null, "Empréstimo devolvido com atraso. Multa de R$" + multa.getValor() + ".");
        return;
      }
      JOptionPane.showMessageDialog(null, "Empréstimo devolvido.");
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O código precisa ser um número inteiro.");
    } catch (EmprestimoNaoEncontradoException |  EmprestimoJaDevolvidoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void renovar(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos, id);
      if(VerificacoesExemplarReserva.temReservasAtivas(reservas, emprestimo.getExemplar())) {
        JOptionPane.showMessageDialog(null, "Não é possível renovar o empréstimo pois o exemplar já está reservado.");
        return;
      }
      try {
        emprestimo.renovar();
        JOptionPane.showMessageDialog(null, "Emprestimo #" + emprestimo.getId() + " renovado.");
      } catch (EmprestimoNaoPodeSerRenovadoException exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage());
      }
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado precisa ser um número inteiro.");
    } catch (EmprestimoNaoEncontradoException exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
  }
  public void listar(ArrayList<Emprestimo> emprestimos) {
    if(emprestimos.size() == 0) {
      JOptionPane.showMessageDialog(null, "Não há empréstimos.");
    }
    String saida = "";
    for(Emprestimo emprestimo: emprestimos) {
      saida += emprestimo;
      saida +=   "====================================================\n";
    }
    PainelGrafico.mostrarMensagemComScroll("Lista de empréstimos",saida);
  }
}
