package biblioteca.operacoes;

public abstract class Operacoes {
  public static String OPCAO_INVALIDA = "Operação inválida.";
  protected static String getMenu() {
    StringBuilder mensagem = new StringBuilder( "1 - Gerenciar usuários\n");
    mensagem.append("2 - Gerenciar exemplares\n");
    mensagem.append("3 - Gerenciar categorias\n");
    mensagem.append("4 - Gerenciar empréstimos\n");
    mensagem.append("5 - Gerenciar reservas\n");
    mensagem.append("6 - Consultar total de exemplares reservados por categoria em um período\n");
    mensagem.append("7 - Sair do programa\n\n");
    return mensagem.toString();
  }
  public abstract int selecionarOpcao();
  public abstract void mostrarMensagemDeOpcaoInvalida();
}
