package biblioteca.exemplar;

import java.util.Random;

public abstract class Exemplar {
    protected int id;
    protected String titulo;
    public Exemplar(String titulo) {
        this.id = new Random().nextInt(10000);
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String toString() {
        String saida = "Exemplar #" + id + "\n";
        saida += "Título: " + titulo + "\n";
        return saida;
    }
    public boolean estaDisponivel() {
        /* Verificar se está emprestado */
        return false;
    }

}
