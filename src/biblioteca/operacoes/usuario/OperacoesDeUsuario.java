package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeUsuario {
  public static String USUARIO_NAO_ENCONTRADO= "Usuário não encontrado";

  /* Métodos estáticos úteis para outras classes de operações, que não dependem de interface (gráfica ou terminal) */
  public static Servidor buscarPorSiape(ArrayList<Usuario> usuarios, int siape) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Servidor) {
        if(((Servidor) usuario).getSiape() == siape) {
          return ((Servidor) usuario);
        }
      }
    }
    return null;
  }
  public static Aluno buscarPorRga(ArrayList<Usuario> usuarios, int rga) {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Aluno) {
        if(((Aluno) usuario).getRga() == rga) {
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
  /* a função getMenu() obtem o menu de gerência de usuários no formato de string */
  protected String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1 - Cadastrar\n");
    menu.append("2 - Pesquisar por CPF\n");
    menu.append("3 - Excluir\n");
    menu.append("4 - Editar\n");
    menu.append("5 - Listar todos os cadastros\n");
    menu.append("6 - Voltar\n\n");
    return menu.toString();
  }
  public abstract int selecionarOpcao();

  public abstract void criar(ArrayList<Usuario> usuarios);
  public abstract void listar(ArrayList<Usuario> usuarios);
  public abstract void consultarPorCPF(ArrayList<Usuario> usuarios);
  public abstract Usuario buscarPorCPF(ArrayList<Usuario> usuarios);
  public String getMenuEditar() {
    StringBuilder mensagem = new StringBuilder("1 - Editar Nome\n");
    mensagem.append("2 - Editar CPF\n");
    mensagem.append("3 - Editar RGA/SIAPE\n");
    mensagem.append("4 - Voltar\n\n");
    return mensagem.toString();
  }
  public abstract int selecionarOpcaoDeEditar();
  public abstract void editarNome(ArrayList<Usuario> usuarios);
  public abstract void editarCPF(ArrayList<Usuario> usuarios);
  public abstract void editarRGAOuSIAPE(ArrayList<Usuario> usuarios);
  public abstract void excluir(ArrayList<Usuario> usuarios, ArrayList<Reserva> reservas, ArrayList<Emprestimo> emprestimos);
}
