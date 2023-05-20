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
                /* Buscar Usuário por CPF */
                  Usuario usuario = operacoes.buscarUsuario(usuarios);
                  if(usuario != null) {
                    JOptionPane.showMessageDialog(null, usuario);
                  } else {
                    JOptionPane.showMessageDialog(null,operacoes.MENSAGEM_USUARIO_NAO_ENCONTRADO);
                  }
                break;
              case 3:
                /* excluir usuário */

                break;
              case 4:
                /* editar usuário */
                boolean continuarEditarUsuario = true;
                while(continuarEditarUsuario) {
                  int opcaoEditarUsuario = operacoes.selecionarOpcaoDeEditarUsuario();
                  switch (opcaoEditarUsuario) {
                    case 1:
                      /* Editar nome */
                      operacoes.editarNomeDoUsuario(usuarios);
                      break;
                    case 2:
                      /* Editar CPF */
                      operacoes.editarCPFDoUsuario(usuarios);
                      break;
                    case 3:
                      /* Editar RGA/SIAPE */
                      operacoes.editarRGAOuSIAPEDoUsuario(usuarios);
                      break;
                    case 4:
                      /* Voltar */
                      continuarEditarUsuario = false;
                      break;
                    default:
                      /* opção invalida */
                      JOptionPane.showMessageDialog(null, operacoes.MENSAGEM_OPCAO_INVALIDA);
                      break;

                  }
                }
                break;
              case 5:
                operacoes.listarUsuarios(usuarios);
                break;
              case 6:
                continuarUsuarios = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, operacoes.MENSAGEM_OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 2:
          boolean continuarExemplares = true;
          while(continuarExemplares) {
            int opcaoDeExemplares = operacoes.selecionarOpcaoDeExemplares();
            switch (opcaoDeExemplares) {
              case 1:
                /* cadastrar exemplar */
                break;
              case 2:
                /* consultar por codigo */
                break;
              case 3:
                /* excluir exemplar */
                break;
              case 4:
                /* editar exemplar */
                break;
              case 5:
                /* listar todos os exemplares */
                break;
              case 6:
                continuarExemplares = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, operacoes.MENSAGEM_OPCAO_INVALIDA);
                break;
            }
          }
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
          JOptionPane.showMessageDialog(null, operacoes.MENSAGEM_OPCAO_INVALIDA);
          break;
      }
    }

  }
}