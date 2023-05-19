package biblioteca;

import biblioteca.operacoes.Grafica;
import biblioteca.operacoes.Operacoes;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    Operacoes operacoes = new Grafica();

    Semente.semear(usuarios);

    boolean continuar = true;
    while(continuar) {
      int opcaoPrincipal = operacoes.selecionarOpcaoPrincipal();
      switch (opcaoPrincipal) {
        case 1:
          boolean continuarUsuarios = true;
          while(continuarUsuarios) {
            int opcaoDeUsuarios = operacoes.selecionarOpcaoDeUsuarios();
            switch (opcaoDeUsuarios) {
              case 1:
                /* cadastrar usuário */
                Usuario novo = operacoes.criarUsuario();
                usuarios.add(novo);
                break;
              case 2:
                break;
              case 3:
                break;
              case 4:
                break;
              case 5:
                operacoes.listarUsuarios(usuarios);
                break;
              case 6:
                continuarUsuarios = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, "Opção Inválida.");
                break;
            }
          }

          break;
        case 2:
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          continuar = false;
          break;
        default:
          JOptionPane.showMessageDialog(null, "Opção Inválida.");
          break;
      }
    }

  }
}