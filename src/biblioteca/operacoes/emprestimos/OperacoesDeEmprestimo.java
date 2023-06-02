package biblioteca.operacoes.emprestimos;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeEmprestimo {
  protected String getMenu() {
    String mensagem = "1 - Emprestar\n";
    mensagem += "2 - Consultar status de um empréstimo\n";
    mensagem += "3 - Devolver\n";
    mensagem += "4 - Renovar\n";
    mensagem += "5 - Listar todos os empréstimos\n";
    mensagem += "6 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcao();
  public abstract Emprestimo emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);
}
