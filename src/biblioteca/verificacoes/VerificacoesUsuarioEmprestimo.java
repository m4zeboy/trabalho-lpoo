package biblioteca.verificacoes;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.StatusDoEmprestimo;
import biblioteca.excecoes.UsuarioTemEmprestimoEmAtrasoException;
import biblioteca.excecoes.UsuarioTemEmprestimosAssociadoException;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public class VerificacoesUsuarioEmprestimo {
  private static ArrayList<Emprestimo> getEmprestimos(ArrayList<Emprestimo> emprestimos, Usuario usuario) {
    ArrayList<Emprestimo> emprestimosAssociados = new ArrayList<>();
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(usuario)) {
        emprestimosAssociados.add(emprestimo);
      }
    }
    return emprestimosAssociados;
  }

  public static void naoTemEmprestimo(ArrayList<Emprestimo> emprestimos, Usuario usuario) throws UsuarioTemEmprestimosAssociadoException {
    if(getEmprestimos(emprestimos, usuario).size() > 0) throw new UsuarioTemEmprestimosAssociadoException();
  }

  public static void naoTemEmprestimoEmAtraso(ArrayList<Emprestimo> emprestimos, Usuario usuario)
          throws UsuarioTemEmprestimoEmAtrasoException {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getUsuario().equals(usuario)) {
        if(emprestimo.getStatus() == StatusDoEmprestimo.EM_ATRASO) throw new UsuarioTemEmprestimoEmAtrasoException();
      }
    }
  }
}
