package biblioteca;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import biblioteca.operacoes.*;
public class Main {
  public static void main(String[] args) {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Exemplar> acervo = new ArrayList<>();
    ArrayList<Categoria> categorias = new ArrayList<>();
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    Operacoes operacoes = new Grafica();

    Semente.semear(usuarios, acervo, categorias, emprestimos);

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
                Usuario novo = operacoes.criarUsuario(usuarios);
                usuarios.add(novo);
                break;
              case 2:
                /* Buscar Usuário por CPF */
                  Usuario usuario = operacoes.buscarUsuario(usuarios);
                  if(usuario != null) {
                    JOptionPane.showMessageDialog(null, usuario);
                  } else {
                    JOptionPane.showMessageDialog(null,Operacoes.USUARIO_NAO_ENCONTRADO);
                  }
                break;
              case 3:
                /* TODO excluir usuário */
                operacoes.excluirUsuario(usuarios);
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
                      JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
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
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
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
                Exemplar novo = operacoes.criarExemplar();
                acervo.add(novo);
                JOptionPane.showMessageDialog(null, "Exemplar criado com código #" +novo.getId() + ".");
                break;
              case 2:
                /* consultar por codigo */
                Exemplar temp = operacoes.buscarExemplarPorCodigo(acervo);
                if(temp == null) {
                  JOptionPane.showMessageDialog(null, Operacoes.EXEMPLAR_NAO_ENCONTRADO);
                  return;
                }
                JOptionPane.showMessageDialog(null, temp);
                break;
              case 3:
                /* TODO excluir exemplar */
                break;
              case 4:
                /* editar exemplar */
                boolean continuarEditarExemplares = true;
                while(continuarEditarExemplares) {
                  int opcaoEditarExemplar = operacoes.selecionarOpcaoDeEditarExemplar();
                  switch (opcaoEditarExemplar) {
                    case 1:
                      /* editar título */
                      operacoes.editarTituloDoExemplar(acervo);
                      break;
                    case 2:
                      /* editar ano ou tipo de arquivo */
                      operacoes.editarAnoOuTipoDeArquivoDoExemplar(acervo);
                      break;
                    case 3:
                      /* adicionar categoria */
                      operacoes.adicionarCategoriaAoExemplar(acervo, categorias);
                      break;
                    case  4:
                      /* remover categoria */
                      operacoes.removerCategoriaDoExemplar(acervo,categorias);
                      break;
                    case 5:
                      /* voltar */
                      continuarEditarExemplares = false;
                      break;
                    default:
                      JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                      break;
                  }
                }

                break;
              case 5:
                /* listar todos os exemplares */
                operacoes.listarExemplares(acervo);
                break;
              case 6:
                continuarExemplares = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 3:
          /* categorias */
          boolean continuarCategorias = true;
          while(continuarCategorias) {
            int opcaoDeCategorias = operacoes.selecionarOpcaoDeCategorias();
            switch (opcaoDeCategorias) {
              case 1:
                /* criar categoria */
                Categoria nova = operacoes.criarCategoria(categorias);
                if(nova != null) {
                  categorias.add(nova);
                  JOptionPane.showMessageDialog(null,"Categoria criada com código #" + nova.getId() + ".");
                }
                break;
              case 2:
                /* pesquisar por codigo */
                Categoria temp = operacoes.buscarCategoriaPorCodigo(categorias);
                if(temp == null) JOptionPane.showMessageDialog(null, Operacoes.CATEGORIA_NAO_ENCONTRADA);
                else JOptionPane.showMessageDialog(null, temp);
                break;
              case 3:
                /* excluir categorias */
                operacoes.excluirCategoria(categorias, acervo);
                break;
              case 4:
                /* editar categorias */
                operacoes.editarCategoria(categorias);
                break;
              case 5:
                /* listar categorias */
                operacoes.listarCategorias(categorias);
                break;
              case 6:
                continuarCategorias = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 4:
          boolean continuarEmprestimos = true;
          while(continuarEmprestimos) {
            int opcaoDeEmprestimos = operacoes.selecionarOpcaoDeEmprestimos();
            switch (opcaoDeEmprestimos) {
              case 1:
                /* emprestar */
                Emprestimo emprestimo = operacoes.emprestar(acervo, usuarios);
                if(emprestimo != null) {
                  emprestimos.add(emprestimo);
                  JOptionPane.showMessageDialog(null, "Exemplar " + emprestimo.getExemplar() + " emprestado para o usuário " + emprestimo.getUsuario() + ".");
                }
                break;
              case 2:
                operacoes.consultaStatusDeUmEmprestimo(emprestimos);
                break;
              case 3:
                operacoes.devolverEmprestimo(emprestimos);
                break;
              case 4:
                operacoes.renovarEmprestimo(emprestimos);
                break;
              case 5:
                operacoes.listarEmprestimos(emprestimos);
                break;
              case 6:
                /* voltar */
                continuarEmprestimos = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 5:
          /* TODO menu para reservas */
          boolean continuarReservas = true;
          while(continuarReservas) {
            int opcaoDeReservas = operacoes.selecionarOpcaoDeReservas();
            switch (opcaoDeReservas) {
              case 1:
                /* TODO Reservar*/
                break;
              case 2:
                /* TODO Consultar status de uma reserva */
                break;
              case 3:
                /* TODO Cancelar */
                break;
              case 4:
                /* TODO Listar reservas ativas para um exemplar */
                break;
              case 5:
                /* TODO voltar */
                break;
              default:
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 6:
          break;
        case 7:
          continuar = false;
          break;
        default:
          JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
          break;
      }
    }

  }
}