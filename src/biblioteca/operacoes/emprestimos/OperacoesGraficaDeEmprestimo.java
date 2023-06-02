package biblioteca.operacoes.emprestimos;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.OperacoesAntiga;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.operacoes.usuario.OperacoesGraficasDeUsuario;
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
}
