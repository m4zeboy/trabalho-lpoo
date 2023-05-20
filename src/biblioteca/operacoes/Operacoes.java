package biblioteca.operacoes;

import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class  Operacoes {
  public static String MENSAGEM_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";
  public static String MENSAGEM_OPCAO_INVALIDA = "Opção Inválida.";
  public abstract int selecionarOpcaoPrincipal();
  public abstract int selecionarOpcaoDeUsuarios();

  public abstract Usuario criarUsuario(ArrayList<Usuario> usuarios);
  public abstract void listarUsuarios(ArrayList<Usuario> usuarios);

  public abstract Usuario buscarUsuario(ArrayList<Usuario> usuarios);
  public abstract Usuario buscarUsuario(ArrayList<Usuario> usuarios, String CPF);
  public abstract Servidor buscaServidorPorSiape(ArrayList<Usuario> usuarios, String siape);
  public abstract Aluno buscaAlunoPorRga(ArrayList<Usuario> usuarios, String rga);
  public abstract int selecionarOpcaoDeEditarUsuario();

  public abstract void editarNomeDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarCPFDoUsuario(ArrayList<Usuario> usuarios);
  public abstract void editarRGAOuSIAPEDoUsuario(ArrayList<Usuario> usuarios);

  public abstract int selecionarOpcaoDeExemplares();
  public abstract Exemplar criarExemplar();
}
