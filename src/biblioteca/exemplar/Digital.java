package biblioteca.exemplar;

public class Digital extends Exemplar {
    public Digital(int id,String titulo) {
        super(id,titulo);
    }
    public Digital(String titulo) {
        super(titulo);
    }
    public boolean estaDisponivel() {
        return true;
    }
}
