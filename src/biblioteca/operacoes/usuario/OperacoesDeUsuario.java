package biblioteca.operacoes.usuario;

import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.excecoes.AlunoJaCadastradoComEsseRGAException;
import biblioteca.excecoes.ServidorJaCadastradoComEsseSIAPEException;
import biblioteca.excecoes.UsuarioJaCadastradoComEsseCPFException;
import biblioteca.excecoes.UsuarioNaoEncontradoException;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class OperacoesDeUsuario {
  public static String USUARIO_NAO_ENCONTRADO= "Usuário não encontrado";

  /* Métodos estáticos úteis para outras classes de operações, que não dependem de interface (gráfica ou terminal) */
  public static void naoExisteServidorComEsseSiape(ArrayList<Usuario> usuarios, int siape)
  throws ServidorJaCadastradoComEsseSIAPEException {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Servidor) {
        if(((Servidor) usuario).getSiape() == siape) {
          throw new ServidorJaCadastradoComEsseSIAPEException();
        }
      }
    }
  }
  public static void naoExisteAlunoComEsseRGA(ArrayList<Usuario> usuarios, int rga)
  throws AlunoJaCadastradoComEsseRGAException {
    for(Usuario usuario: usuarios) {
      if(usuario instanceof Aluno) {
        if(((Aluno) usuario).getRga() == rga) {
          throw new AlunoJaCadastradoComEsseRGAException();
        }
      }
    }
  }
  public static Usuario buscarPorCPF(ArrayList<Usuario> usuarios, String cpf)
  throws UsuarioNaoEncontradoException {
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) { return usuario; }
    }
    throw new UsuarioNaoEncontradoException();
  }

  public static void naoExisteUsuarioComEsseCPF(ArrayList<Usuario> usuarios, String cpf)
          throws UsuarioJaCadastradoComEsseCPFException {
    for(Usuario usuario: usuarios) {
      if(usuario.getCpf().equals(cpf)) { throw new UsuarioJaCadastradoComEsseCPFException();}
    }
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
  public abstract Usuario buscarPorCPF(ArrayList<Usuario> usuarios) throws UsuarioNaoEncontradoException;
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
