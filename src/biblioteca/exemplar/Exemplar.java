package biblioteca.exemplar;

import biblioteca.Categoria;

import java.util.ArrayList;
import java.util.Random;

public abstract class Exemplar {
    protected int id;
    protected String titulo;

    protected ArrayList<Categoria> categorias;
    public Exemplar(String titulo) {
        this.id = new Random().nextInt(10000);
        this.titulo = titulo;
        this.categorias = new ArrayList<>();
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

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void adicionarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public String toString() {
        String saida = "Exemplar #" + id + "\n";
        saida += "Título: " + titulo + "\n";
        saida += "Categorias: ";
        for(Categoria categoria: categorias) {
            saida += categoria.getNome() + ", ";
        }
        saida += "\n";
        return saida;
    }
    public boolean estaDisponivel() {
        /* Verificar se está emprestado */
        return false;
    }

}
