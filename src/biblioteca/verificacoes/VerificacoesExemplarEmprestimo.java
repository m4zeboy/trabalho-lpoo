package biblioteca.verificacoes;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.ExemplarTemEmprestimosAssociadosException;
import biblioteca.exemplar.Exemplar;

import java.util.ArrayList;

public class VerificacoesExemplarEmprestimo {
  public static void naoTemEmprestimos(ArrayList<Emprestimo> emprestimos, Exemplar exemplar)
          throws ExemplarTemEmprestimosAssociadosException {
    for(Emprestimo emprestimo: emprestimos) {
      if(emprestimo.getExemplar().equals(exemplar)) {
        throw new ExemplarTemEmprestimosAssociadosException();
      }
    }
  }
}
