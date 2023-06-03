package biblioteca;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;
import biblioteca.operacoes.categorias.OperacoesGraficasDeCategoria;
import biblioteca.operacoes.emprestimos.OperacoesDeEmprestimo;
import biblioteca.operacoes.emprestimos.OperacoesGraficaDeEmprestimo;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.exemplares.OperacoesGraficasDeExemplar;
import biblioteca.operacoes.reservas.OperacoesDeReserva;
import biblioteca.operacoes.reservas.OperacoesGraficasDeReserva;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.operacoes.usuario.OperacoesGraficasDeUsuario;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import biblioteca.operacoes.Operacoes;
import biblioteca.operacoes.Grafica;
public class Biblioteca {
  public static void main(String[] args) {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Exemplar> acervo = new ArrayList<>();
    ArrayList<Categoria> categorias = new ArrayList<>();
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();
    ArrayList<Multa> multas = new ArrayList<>();

    Operacoes menuPrincipal = new Grafica();
    OperacoesDeUsuario operacoesDeUsuario = new OperacoesGraficasDeUsuario();
    OperacoesDeExemplar operacoesDeExemplar = new OperacoesGraficasDeExemplar();
    OperacoesDeCategoria operacoesDeCategoria = new OperacoesGraficasDeCategoria();
    OperacoesDeEmprestimo operacoesDeEmprestimo = new OperacoesGraficaDeEmprestimo();
    OperacoesDeReserva operacoesDeReserva = new OperacoesGraficasDeReserva();

    Semente.semear(usuarios, acervo, categorias, emprestimos, reservas);

    // todo alterar as funções getMenu para capturar uma exceção caso a conversão para inteiro falhe.
    //    todo getMenu usuario
    //      todo getMenuEditar
    //    todo getMenu exemplar
    //    todo getMenu Categoria
    //    todo getMenu Emprestimo

    // todo Na classe Biblioteca eu recomendaria o uso do switch para os casos do menu, aumenta a legibilidade do código, especialmente ao utilizar o formato mais atual do JAVA.

    // todo Tecnicamente a data do empréstimo deve ser a data em que o objeto foi criado (data atual). Nesse sentido, o construtor não deveria receber essa data como parametro.

    // todo A classe multa faltou o campo valor conforme especifica o diagrama presente na descrição do trabalho.

    // todo o método estaAtiva da classe reserva poderia ser simplificado, retornando diretamente o resutlado do I

    // todo Recomendo delegar as operações de condicionais para uma classe específica que trate o contexto de cada funcionalidade (Usuario, Categoria, Exemplar, etc)

    // todo A parte de devolução de empréstimos está permitindo a devolução de um item que já foi devolvido anteriormente.

    // todo Sugiro estudarem um pouco sobre o conceito de ENUM e como utilizar em JAVA.

    boolean continuar = true;
    while(continuar) {
      int opcaoPrincipal = menuPrincipal.selecionarOpcao();
      switch (opcaoPrincipal) {
        case 1:
          boolean continuarUsuarios = true;
          while(continuarUsuarios) {
            int opcaoDeUsuarios = operacoesDeUsuario.selecionarOpcao();
            switch (opcaoDeUsuarios) {
              case 1:
                /* cadastrar usuário */
                Usuario novo = operacoesDeUsuario.criar(usuarios);
                usuarios.add(novo);
                break;
              case 2:
                /* Buscar Usuário por CPF */
                  operacoesDeUsuario.consultarPorCPF(usuarios);
                break;
              case 3:
                operacoesDeUsuario.excluir(usuarios, reservas,emprestimos);
                break;
              case 4:
                /* editar usuário */
                boolean continuarEditarUsuario = true;
                while(continuarEditarUsuario) {
                  int opcaoEditarUsuario = operacoesDeUsuario.selecionarOpcaoDeEditar();
                  switch (opcaoEditarUsuario) {
                    case 1:
                      /* Editar nome */
                      operacoesDeUsuario.editarNome(usuarios);
                      break;
                    case 2:
                      /* Editar CPF */
                      operacoesDeUsuario.editarCPF(usuarios);
                      break;
                    case 3:
                      /* Editar RGA/SIAPE */
                      operacoesDeUsuario.editarRGAOuSIAPE(usuarios);
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
                operacoesDeUsuario.listar(usuarios);
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
            int opcaoDeExemplares = operacoesDeExemplar.selecionarOpcao();
            switch (opcaoDeExemplares) {
              case 1:
                /* cadastrar exemplar */
                Exemplar novo = operacoesDeExemplar.criar();
                acervo.add(novo);
                JOptionPane.showMessageDialog(null, "Exemplar criado com código #" +novo.getId() + ".");
                break;
              case 2:
                /* consultar por codigo */
                operacoesDeExemplar.consultarPorCodigo(acervo);
                break;
              case 3:
                operacoesDeExemplar.excluir(acervo,emprestimos, reservas);
                break;
              case 4:
                /* editar exemplar */
                boolean continuarEditarExemplares = true;
                while(continuarEditarExemplares) {
                  int opcaoEditarExemplar = operacoesDeExemplar.selecionarOpcaoDeEditar();
                  switch (opcaoEditarExemplar) {
                    case 1:
                      /* editar título */
                      operacoesDeExemplar.editarTitulo(acervo);
                      break;
                    case 2:
                      /* editar ano ou tipo de arquivo */
                      operacoesDeExemplar.editarAnoOuTipoDeArquivo(acervo);
                      break;
                    case 3:
                      /* adicionar categoria */
                      operacoesDeExemplar.adicionarCategoria(acervo, categorias);
                      break;
                    case  4:
                      /* remover categoria */
                      operacoesDeExemplar.removerCategoria(acervo,categorias);
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
                operacoesDeExemplar.listar(acervo);
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
            int opcaoDeCategorias = operacoesDeCategoria.selecionarOpcao();
            switch (opcaoDeCategorias) {
              case 1:
                /* criar categoria */
                Categoria nova = operacoesDeCategoria.criar(categorias);
                if(nova != null) {
                  categorias.add(nova);
                  JOptionPane.showMessageDialog(null,"Categoria criada com código #" + nova.getId() + ".");
                }
                break;
              case 2:
                /* pesquisar por codigo */
                operacoesDeCategoria.consultarPorCodigo(categorias);
                break;
              case 3:
                /* excluir categorias */
                operacoesDeCategoria.excluir(categorias, acervo);
                break;
              case 4:
                /* editar categorias */
                operacoesDeCategoria.editar(categorias);
                break;
              case 5:
                /* listar categorias */
                operacoesDeCategoria.listar(categorias);
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
            int opcaoDeEmprestimos = operacoesDeEmprestimo.selecionarOpcao();
            switch (opcaoDeEmprestimos) {
              case 1:
                /* emprestar */
                Emprestimo emprestimo = operacoesDeEmprestimo.emprestar(acervo, usuarios, emprestimos, reservas);
                if(emprestimo != null) {
                  emprestimos.add(emprestimo);
                  JOptionPane.showMessageDialog(null, "Exemplar " + emprestimo.getExemplar().getTitulo() + " emprestado para o usuário " + emprestimo.getUsuario().getNome() + ".");
                }
                break;
              case 2:
                operacoesDeEmprestimo.consultarPorCodigo(emprestimos);
                break;
              case 3:
                operacoesDeEmprestimo.devolver(emprestimos,multas);
                break;
              case 4:
                operacoesDeEmprestimo.renovar(emprestimos, reservas);
                break;
              case 5:
                operacoesDeEmprestimo.listar(emprestimos);
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
          boolean continuarReservas = true;
          while(continuarReservas) {
            int opcaoDeReservas = operacoesDeReserva.selecionarOpcao();
            switch (opcaoDeReservas) {
              case 1:
                operacoesDeReserva.reservar(reservas, usuarios, acervo,emprestimos);
                break;
              case 2:
                operacoesDeReserva.consultarPorCodigo(reservas);
                break;
              case 3:
                operacoesDeReserva.cancelar(reservas);
                break;
              case 4:
                operacoesDeReserva.listarReservasAtivasParaUmExemplar(acervo, reservas);
                break;
              case 5:
                continuarReservas = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, Operacoes.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 6:
          operacoesDeReserva.consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeríodo(reservas,categorias);
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