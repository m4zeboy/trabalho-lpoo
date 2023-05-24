package biblioteca.exemplar;

import biblioteca.Emprestimo;

import java.util.ArrayList;

public class Digital extends Exemplar {
    public Digital(String titulo) {
        super(titulo);
    }
    public boolean estaDisponivel(ArrayList<Emprestimo> emprestimos) {
        return true;
    }
}
