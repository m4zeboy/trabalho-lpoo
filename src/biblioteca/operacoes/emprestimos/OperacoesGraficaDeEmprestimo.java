package biblioteca.operacoes.emprestimos;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.PainelGrafico;
import biblioteca.operacoes.excecoes.EmprestimoJaDevolvidoException;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.usuario.Usuario;

import javax.swing.*;
import java.util.ArrayList;

public class OperacoesGraficaDeEmprestimo extends OperacoesDeEmprestimo {
  public int selecionarOpcao() {
    return Integer.parseInt(JOptionPane.showInputDialog(getMenu()));
  }
  public Emprestimo emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    String cpf = JOptionPane.showInputDialog("CPF: ");
    Usuario usuario = OperacoesDeUsuario.buscarPorCPF(usuarios,cpf);

    if(usuario == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeUsuario.USUARIO_NAO_ENCONTRADO);
      return null;
    }
    if(usuario.temEmprestimoEmAtraso(emprestimos)) {
      JOptionPane.showMessageDialog(null, "Não é possível emprestar pois o usuário tem empréstimos em atraso.");
      return null;
    }
    int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do Exemplar: "));
    Exemplar exemplar = OperacoesDeExemplar.buscarPorCodigo(acervo,codigo);
    if(exemplar == null) {
      JOptionPane.showMessageDialog(null, OperacoesDeExemplar.EXEMPLAR_NAO_ENCONTRADO);
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
  public void consultarPorCodigo(ArrayList<Emprestimo> emprestimos) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos,id);
      if(emprestimo == null) {
        JOptionPane.showMessageDialog(null,OperacoesDeEmprestimo.EMPRESTIMO_NAO_ENCONTRADO);
        return;
      }
      JOptionPane.showMessageDialog(null, emprestimo);
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado precisa ser um número inteiro.");
    }
  }
  public void devolver(ArrayList<Emprestimo> emprestimos, ArrayList<Multa> multas) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos, id);
      if(emprestimo == null) {
        JOptionPane.showMessageDialog(null,OperacoesDeEmprestimo.EMPRESTIMO_NAO_ENCONTRADO);
        return;
      }
      try {
        Multa multa = emprestimo.devolver();
        if(multa != null) {
          multas.add(multa);
          JOptionPane.showMessageDialog(null, "Empréstimo devolvido com atraso. Multa de R$" + multa.getValor() + ".");
          return;
        }
        JOptionPane.showMessageDialog(null, "Empréstimo devolvido.");
      } catch (EmprestimoJaDevolvidoException exception) {
        JOptionPane.showMessageDialog(null,exception.getMessage());
      }
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado precisa ser um número inteiro.");
    }
  }
  public void renovar(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas) {
    String codigo = JOptionPane.showInputDialog("Código do empréstimo: ");
    try {
      int id = Integer.parseInt(codigo);
      Emprestimo emprestimo = buscarPorCodigo(emprestimos, id);
      if(emprestimo == null) {
        JOptionPane.showMessageDialog(null, OperacoesDeEmprestimo.EMPRESTIMO_NAO_ENCONTRADO);
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
    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(null, "O valor informado precisa ser um número inteiro.");
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
