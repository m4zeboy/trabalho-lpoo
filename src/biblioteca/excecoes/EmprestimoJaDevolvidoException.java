package biblioteca.excecoes;

public class EmprestimoJaDevolvidoException extends Exception{
    public EmprestimoJaDevolvidoException() {
        super("O empréstimo já foi devolvido.");
    }
}
