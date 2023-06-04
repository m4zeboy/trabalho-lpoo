package biblioteca.operacoes.emprestimos;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeEmprestimo {
  public static String EMPRESTIMO_NAO_ENCONTRADO = "Empréstimo não encontrado.";
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
  public abstract void emprestar(ArrayList<Exemplar> acervo, ArrayList<Usuario> usuarios, ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reservas);
  public static Emprestimo buscarPorCodigo(ArrayList<Emprestimo> emprestimos, int codigo) {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getId() == codigo) return emprestimo;
    }
    return null;
  }
  public abstract void consultarPorCodigo(ArrayList<Emprestimo> emprestimos);
  public abstract void devolver(ArrayList<Emprestimo> emprestimos, ArrayList<Multa> multas);
  public abstract void renovar(ArrayList<Emprestimo> emprestimos, ArrayList<Reserva> reserva);
  public abstract void listar(ArrayList<Emprestimo> emprestimos);
}
