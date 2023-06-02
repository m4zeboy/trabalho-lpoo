package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeUsuario {
  public static String USUARIO_NAO_ENCONTRADO= "Usuário não encontrado";
  protected String getMenu() {
    String mensagem = "1 - Cadastrar\n";
    mensagem += "2 - Pesquisar por CPF\n";
    mensagem += "3 - Excluir\n";
    mensagem += "4 - Editar\n";
    mensagem +=  "5 - Listar todos os cadastros\n";
    mensagem +=  "6 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcao();
  public abstract Usuario criar(ArrayList<Usuario> usuarios);
  public abstract void listar(ArrayList<Usuario> usuarios);
  public abstract void consultarPorCPF(ArrayList<Usuario> usuarios);
  public abstract Usuario buscarPorCPF(ArrayList<Usuario> usuarios);
  public static Servidor buscarPorSiape(ArrayList<Usuario> usuarios, String siape) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Servidor) {
        if(((Servidor) usuario).getSiape() == Integer.parseInt(siape)) {
          return ((Servidor) usuario);
        }
      }
    }
    return null;
  }
  public static Aluno buscarPorRga(ArrayList<Usuario> usuarios, String rga) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Aluno) {
        if(((Aluno) usuario).getRga() == Integer.parseInt(rga)) {
          return ((Aluno) usuario);
        }
      }
    }
    return null;
  }
  public static Usuario buscarPorCPF(ArrayList<Usuario> usuarios, String cpf) {
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) { return usuario; }
    }
    return null;
  }
  public static String getMenuEditar() {
    String mensagem = "1 - Editar Nome\n";
    mensagem += "2 - Editar CPF\n";
    mensagem += "3 - Editar RGA/SIAPE\n";
    mensagem += "4 - Voltar\n\n";
    return mensagem;
  }
  public abstract int selecionarOpcaoDeEditar();
  public abstract void editarNome(ArrayList<Usuario> usuarios);
  public abstract void editarCPF(ArrayList<Usuario> usuarios);
  public abstract void editarRGAOuSIAPE(ArrayList<Usuario> usuarios);
  public abstract void excluir(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos);
}
