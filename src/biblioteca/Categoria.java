package biblioteca;

import java.io.Serializable;
import java.util.Random;

public class Categoria implements Serializable {
    private int id;
    private String nome;

    public Categoria(String nome) {
        this.id = new Random().nextInt(10000);
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        String saida = "Categoria #" + id + "\n";
        saida += "Nome: " + nome + "\n";
        return saida;
    }
}
