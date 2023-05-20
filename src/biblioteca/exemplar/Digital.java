package biblioteca.exemplar;

public class Digital extends Exemplar {
    public Digital(String titulo) {
        super(titulo);
    }
    @Override
    public boolean estaDisponivel() {
        return true;
    }
}
