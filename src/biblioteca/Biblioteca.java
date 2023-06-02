package biblioteca;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Exemplar;
import biblioteca.operacoes.categorias.OperacoesDeCategoria;
import biblioteca.operacoes.categorias.OperacoesGraficasDeCategoria;
import biblioteca.operacoes.emprestimos.OperacoesDeEmprestimo;
import biblioteca.operacoes.emprestimos.OperacoesGraficaDeEmprestimo;
import biblioteca.operacoes.exemplares.OperacoesDeExemplar;
import biblioteca.operacoes.exemplares.OperacoesGraficasDeExemplar;
import biblioteca.operacoes.usuario.OperacoesDeUsuario;
import biblioteca.operacoes.usuario.OperacoesGraficasDeUsuario;
import biblioteca.usuario.Usuario;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import biblioteca.operacoes.*;
public class Biblioteca {
  public static void main(String[] args) {
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Exemplar> acervo = new ArrayList<>();
    ArrayList<Categoria> categorias = new ArrayList<>();
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();

    Operacoes menuPrincipal = new Grafica();

    Semente.semear(usuarios, acervo, categorias, emprestimos, reservas);
    // todo A classe Grafica deveria ser separada em outras mais específicas, cada qual gerenciando a sua parte.
    //    todo classe de operações de exemplares
    //    todo classe de operações de categorias
    //    todo classe de operações de emprestimos
    //    todo classe de operações de reservas


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
          OperacoesDeUsuario operacoesDeUsuario = new OperacoesGraficasDeUsuario();
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
                      JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
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
          OperacoesDeExemplar operacoesDeExemplares = new OperacoesGraficasDeExemplar();
          boolean continuarExemplares = true;
          while(continuarExemplares) {
            int opcaoDeExemplares = operacoesDeExemplares.selecionarOpcao();
            switch (opcaoDeExemplares) {
              case 1:
                /* cadastrar exemplar */
                Exemplar novo = operacoesDeExemplares.criar();
                acervo.add(novo);
                JOptionPane.showMessageDialog(null, "Exemplar criado com código #" +novo.getId() + ".");
                break;
              case 2:
                /* consultar por codigo */
                operacoesDeExemplares.consultarPorCodigo(acervo);
                break;
              case 3:
                operacoesDeExemplares.excluir(acervo,emprestimos, reservas);
                break;
              case 4:
                /* editar exemplar */
                boolean continuarEditarExemplares = true;
                while(continuarEditarExemplares) {
                  int opcaoEditarExemplar = operacoesDeExemplares.selecionarOpcaoDeEditar();
                  switch (opcaoEditarExemplar) {
                    case 1:
                      /* editar título */
                      operacoesDeExemplares.editarTitulo(acervo);
                      break;
                    case 2:
                      /* editar ano ou tipo de arquivo */
                      operacoesDeExemplares.editarAnoOuTipoDeArquivo(acervo);
                      break;
                    case 3:
                      /* adicionar categoria */
                      operacoesDeExemplares.adicionarCategoria(acervo, categorias);
                      break;
                    case  4:
                      /* remover categoria */
                      operacoesDeExemplares.removerCategoria(acervo,categorias);
                      break;
                    case 5:
                      /* voltar */
                      continuarEditarExemplares = false;
                      break;
                    default:
                      JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
                      break;
                  }
                }

                break;
              case 5:
                /* listar todos os exemplares */
                operacoesDeExemplares.listar(acervo);
                break;
              case 6:
                continuarExemplares = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 3:
          /* categorias */
          OperacoesDeCategoria operacoesDeCategoria = new OperacoesGraficasDeCategoria();
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
                JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 4:
          OperacoesDeEmprestimo operacoesDeEmprestimo = new OperacoesGraficaDeEmprestimo();
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
                operacoes.consultaStatusDeUmEmprestimo(emprestimos);
                break;
              case 3:
                operacoes.devolverEmprestimo(emprestimos);
                break;
              case 4:
                operacoes.renovarEmprestimo(emprestimos, reservas);
                break;
              case 5:
                operacoes.listarEmprestimos(emprestimos);
                break;
              case 6:
                /* voltar */
                continuarEmprestimos = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 5:
          boolean continuarReservas = true;
          while(continuarReservas) {
            int opcaoDeReservas = operacoes.selecionarOpcaoDeReservas();
            switch (opcaoDeReservas) {
              case 1:
                Reserva reserva = operacoes.reservar(reservas, usuarios, acervo,emprestimos);
                if(reserva != null) {
                  reservas.add(reserva);
                  JOptionPane.showMessageDialog(null, "Reserva criada com código #" + reserva.getId() + ".");
                }
                break;
              case 2:
                Reserva temp = operacoes.buscarReservaPorCodigo(reservas);
                if(temp != null) {
                  JOptionPane.showMessageDialog(null, temp);
                } else {
                  JOptionPane.showMessageDialog(null, "Reserva não encontrada.");
                }
                break;
              case 3:
                operacoes.cancelarReserva(reservas);
                break;
              case 4:
                operacoes.listarReservasAtivasParaUmExemplar(acervo, reservas);
                break;
              case 5:
                continuarReservas = false;
                break;
              default:
                JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
                break;
            }
          }
          break;
        case 6:
          operacoes.consultarTotalDeExemplaresReservadosPorCategoriaEmUmPeríodo(reservas,categorias);
          break;
        case 7:
          continuar = false;
          break;
        default:
          JOptionPane.showMessageDialog(null, OperacoesAntiga.OPCAO_INVALIDA);
          break;
      }
    }

  }
}