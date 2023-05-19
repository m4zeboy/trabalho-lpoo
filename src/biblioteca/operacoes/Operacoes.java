package biblioteca.operacoes;

import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public abstract class  Operacoes {
  public abstract int selecionarOpcaoPrincipal();
  public abstract int selecionarOpcaoDeUsuarios();

  public abstract Usuario criarUsuario();
  public abstract void listarUsuarios(ArrayList<Usuario> usuarios);

//  public abstract void mostrarMenuExemplaresOuCategorias();
//  public abstract void mostrarMenuEmprestimos();
//  public abstract void mostrarMenuReservas();
}
